import java.util.*;

public class LRUCache {
    public static void main(String[] args) {
        LRUCache lru=new LRUCache(10);
        Deque<Integer> stack=new LinkedList<>();
    }
    static class LinkedNode{
        int key;
        int val;
        LinkedNode prev;
        LinkedNode next;
        public LinkedNode(){}
        public LinkedNode(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    int capacity;
    int currNums;
    Map<Integer,LinkedNode> map;
    LinkedNode tail;
    LinkedNode head;


    public LRUCache(int capacity) {
        this.currNums=0;
        this.capacity=capacity;
        this.tail=new LinkedNode();
        this.head=new LinkedNode();
        this.map=new HashMap<>();
        tail.prev=head;
        head.next=tail;
    }

    public  int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        LinkedNode node=map.get(key);
        removeNode(node);
        putHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            LinkedNode node=map.get(key);
            node.val=value;
            removeNode(node);
            putHead(node);
            return;
        }
        LinkedNode node=new LinkedNode(key,value);
        putHead(node);
        currNums++;
        while(currNums>capacity){
            removeTail();
        }
    }

    public void removeNode(LinkedNode node){  //移除一个节点
        map.remove(node.key);
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void putHead(LinkedNode node){       //把某个节点移到前面
        map.put(node.key,node);
        node.prev=head;
        node.next=head.next;
        head.next=node;
        node.next.prev=node;
    }

    public void removeTail(){
        map.remove(tail.prev.key);
        tail.prev.prev.next=tail;
        tail.prev=tail.prev.prev;
        currNums--;

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */