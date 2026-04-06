import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache + TTL（过期时间）
 *
 * 设计：
 * - HashMap: key -> Node，O(1) 定位节点
 * - 双向链表：维护访问顺序（LRU）
 *   head 哨兵 <-> ... <-> tail 哨兵
 *   head.next 是最久未使用（LRU），tail.prev 是最近使用（MRU）
 *
 * TTL策略：
 * - get 时如果发现过期：懒惰删除并返回 -1
 * - put 时如果容量已满：循环从 LRU 端(head.next)开始删
 *     - 如果过期：删掉继续
 *     - 否则：删掉一个最久未使用的，腾出空间
 */
public class LRUCacheTTL {

    /** 双向链表节点 */
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        /** 过期时间戳（毫秒），currentTimeMillis() > expireAtMs 表示过期 */
        long expireAtMs;

        Node() {}

        Node(int key, int value, long ttlSeconds) {
            this.key = key;
            this.value = value;
            this.expireAtMs = System.currentTimeMillis() + ttlSeconds * 1000L;
        }
    }

    private final int capacity;
    private final int defaultTtlSeconds;

    /** key -> node */
    private final Map<Integer, Node> map = new HashMap<>();

    /** 哨兵头尾，便于统一插删逻辑 */
    private final Node head = new Node();
    private final Node tail = new Node();

    public LRUCacheTTL(int capacity, int defaultTtlSeconds) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        if (defaultTtlSeconds <= 0) throw new IllegalArgumentException("defaultTtlSeconds must be > 0");

        this.capacity = capacity;
        this.defaultTtlSeconds = defaultTtlSeconds;

        // 初始化双向链表：head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取 key 的值：
     * - 不存在返回 -1
     * - 存在但过期：删除并返回 -1
     * - 未过期：移动到 MRU（链表尾部），返回 value
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        if (isExpired(node)) {
            // 懒惰删除：只有访问到才清理
            removeNode(node);
            map.remove(key);
            return -1;
        }

        // 更新 LRU 顺序：移动到尾部（MRU）
        moveToTail(node);
        return node.value;
    }

    /**
     * 放入 key/value：
     * - ttlSeconds = -1 表示用默认TTL
     * - key 已存在：删除旧节点，插入新节点（等价于更新值+更新TTL+变成MRU）
     * - key 不存在：
     *    - 若已满：从 LRU 端开始清理（优先清过期，否则删一个 LRU）
     *    - 插入新节点到 MRU
     */
    public void put(int key, int value, int ttlSeconds) {
        int useTtl = (ttlSeconds == -1) ? defaultTtlSeconds : ttlSeconds;
        if (useTtl <= 0) throw new IllegalArgumentException("ttlSeconds must be > 0 or -1");

        // 若已存在，先删除旧节点
        Node existing = map.get(key);
        if (existing != null) {
            removeNode(existing);
            map.remove(key);
        } else {
            // 新key：如果容量满了，先腾位置
            evictIfNeeded();
        }

        Node node = new Node(key, value, useTtl);
        addToTail(node);     // 新节点是最近使用
        map.put(key, node);
    }

    /** 容量满时，从 LRU 端开始清理 */
    private void evictIfNeeded() {
        while (map.size() >= capacity) {
            Node lru = head.next;
            if (lru == tail) break; // 理论不会发生（map.size>=capacity时链表不可能空），防御一下

            // 如果 LRU 已过期，直接删掉继续；否则删掉一个 LRU 腾空间
            removeNode(lru);
            map.remove(lru.key);

            // 若删除的是未过期 LRU，则只删一个就够
            // 但如果是过期节点，可能还有更多过期节点，继续 while 清理更划算
            // 这里用条件判断：如果刚删的是未过期的，就 break
            if (!isExpiredTimestamp(lru.expireAtMs)) {
                break;
            }
        }
    }

    /** 判断 node 是否过期 */
    private boolean isExpired(Node node) {
        return System.currentTimeMillis() > node.expireAtMs;
    }

    /**
     * 注意：evictIfNeeded() 里先 remove 再判断是否过期会丢失信息，
     * 所以这里提供一个“基于expireAtMs”判断的方法：
     */
    private boolean isExpiredTimestamp(long expireAtMs) {
        return System.currentTimeMillis() > expireAtMs;
    }

    /** 把 node 移动到链表尾部（MRU） */
    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }

    /** 从链表中摘除 node（不操作 map） */
    private void removeNode(Node node) {
        Node p = node.prev;
        Node n = node.next;
        if (p != null) p.next = n;
        if (n != null) n.prev = p;
        node.prev = null;
        node.next = null;
    }

    /** 插入到尾部（tail 前面），成为 MRU */
    private void addToTail(Node node) {
        Node last = tail.prev;

        last.next = node;
        node.prev = last;

        node.next = tail;
        tail.prev = node;
    }

    // ---------------------- demo ----------------------
    public static void main(String[] args) throws Exception {
        LRUCacheTTL cache = new LRUCacheTTL(2, 5); // 容量2，默认TTL 5秒

        cache.put(1, 100, -1);     // TTL = 5秒
        cache.put(2, 200, 10);     // TTL = 10秒

        int val = cache.get(1);    // 未过期则返回100
        System.out.println("val: " + val);

        // 你也可以测试过期：
        // Thread.sleep(6000);
        // System.out.println(cache.get(1)); // 过期后返回 -1
    }
}
