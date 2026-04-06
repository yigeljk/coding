import java.util.ArrayList;
import java.util.List;

public class 括号生成 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));

    }
    public static List<String> generateParenthesis(int n){
        List<String> result=new ArrayList<>();
        StringBuilder path=new StringBuilder();
        backtrack(n,0,0,path,result);
        return result;
    }


//left表示左括号的数量，right表示右括号的数量
    private static void  backtrack(int n,int left,int right,
                            StringBuilder path,List<String> result) {
        if(path.length()==2*n){
            result.add(path.toString());
            return ;
        }
        if(left<n){
            path.append('(');
            backtrack(n,left+1,right,path,result);
            path.deleteCharAt(path.length()-1);
        }
        if(right<left){
            path.append(')');
            backtrack(n,left,right+1,path,result);
            path.deleteCharAt(path.length()-1);
        }

    }
}
