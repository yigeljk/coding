import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的序列化和反序列化 {
   static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }
    public String serialize(TreeNode root){
        StringBuilder sb=new StringBuilder();
        dfsSerialize(root,sb);
        if(!sb.isEmpty()){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();

    }

    public  void dfsSerialize(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("#,");
            return;
        }

        sb.append(root.val).append(",");   //前序遍历
        dfsSerialize(root.left,sb);
        dfsSerialize(root.right,sb);
    }

    public  TreeNode deserialize(String data){
        Queue<String> q=new LinkedList<>(Arrays.asList(data.split(",")));
        return dfsDeserialize(q);
    }

    public TreeNode dfsDeserialize(Queue<String> q){
        String s=q.poll();

        if(s.equals("#")){
            return null;
        }
        TreeNode node=new TreeNode(Integer.parseInt(s));
        node.left=dfsDeserialize(q);
        node.right=dfsDeserialize(q);
        return node;
    }

    public static void main(String[] args) {
        二叉树的序列化和反序列化 s=new 二叉树的序列化和反序列化();
        String str="1,2,null,null,3,4,null,null,5,null,null";
        TreeNode node = s.deserialize(str);
        String s0 = s.serialize(node);
        System.out.println(s0);


    }


}
