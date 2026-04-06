import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class jingdong {
    public static void main(String[] args) {
        int i=0;
       while (true){
           i++;
       }
    }

    public static List<Integer> question(int[] nums, int target){
        List<Integer> list=new ArrayList<>();
        int len=nums.length;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<len;i++){
            if(map.containsKey(target-nums[i])){
                list.add(map.get(target-nums[i]));
                list.add(i+1);
                return list;
            }else{
                map.put(nums[i],i+1);
            }
        }
        return list;

    }
}
