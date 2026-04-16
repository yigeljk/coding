public class 字符串转换为数字 {

    public static void main(String[] args) {
        System.out.println(stringToInt("-2147483648"));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
    public static int stringToInt(String s){

        if(s==null || s.isEmpty()){
            throw new NumberFormatException("空字符串或为null");
        }

        int len=s.length();
        int index=0;
        int sign=1;

        char first=s.charAt(0);
        if(first=='+' || first=='-'){
             index++;
             sign= (first=='-') ? -1 : 1;
        }
        int result=0;

        while(index<len){
            char ch=s.charAt(index);
            if(ch-'0'>9 || ch-'0'<0) {
                throw new NumberFormatException("存在其他字符");
            }
            int digit=ch-'0';

            if(result>(Integer.MAX_VALUE-digit)/10 && sign==1){
                throw new NumberFormatException("超出最大值");
            }
            if(result>(Integer.MAX_VALUE+1-digit)/10 && sign==-1){
                throw new NumberFormatException("超出最小值");
            }
            result=10*result+digit;
            index++;
        }
        return result*sign;
    }
}
