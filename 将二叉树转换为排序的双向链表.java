import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 将二叉树转换为排序的双向链表 {
    public static void main(String[] args) {
        String s="s,ddfd,fdff,dfdfsfs";
        String[] array = s.split(",");
        List<String> list = Arrays.asList(array);
        Queue<String> queue=new LinkedList<>(list);
        System.out.println(queue.poll());

        System.out.println(queue);
    }
}
