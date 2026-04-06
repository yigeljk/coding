import java.util.Stack;

public class 每日温度 {
    public int[] dailyTemperature(int[] temperatures){
        int len=temperatures.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();

        for(int i=0;i<len;i++){
            int currTemp=temperatures[i];
            while(!stack.isEmpty() && currTemp>temperatures[stack.peek()]){
                int prevIndex=stack.pop();
                result[prevIndex]=i-prevIndex;
            }
            stack.push(i);
        }
        return result;
    }
}
