import java.util.Stack;

public class 字符串解码 {
    public static void main(String[] args) {
        String s = decodeString("3[a2[c]]");
        System.out.println(s);
    }
    public static String decodeString(String s){

        //存储每一次括号的重复次数
        Stack<Integer> numStack=new Stack<>();

        //存储进入每一层括号之前已经拼接好的字符串
        Stack<StringBuilder> strStack=new Stack<>();

        //存储当前正在构建的字符串，括号内
        StringBuilder curr=new StringBuilder();

        int num=0;
        for(char ch : s.toCharArray()){
            int charNum=ch-'0';

            //如果是数字
            if(charNum>=0 && charNum<=9){
                num=num*10+(ch-'0');
            }else if(ch=='['){
                numStack.push(num);
                strStack.push(curr);

                num=0;
                curr=new StringBuilder();
            }else if(ch==']'){
                int k=numStack.pop();
                StringBuilder prev=strStack.pop();
                for(int i=0;i<k;i++){
                    prev.append(curr);
                }
                curr=prev;
            }else{
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}
