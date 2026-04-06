import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class 子集 {
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backtrack(path,result,0,nums);
        return result;
    }

    private void backtrack(List<Integer> path,List<List<Integer>> result,
                           int start,int[] nums) {
        result.add(new ArrayList<>(path));
        for(int i=start;i<nums.length;i++){
            path.add(nums[i]);
            backtrack(path,result,i+1,nums);
            path.remove(path.size()-1);
        }
    }
}
