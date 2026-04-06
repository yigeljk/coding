import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和 {
    public static void main(String[] args) {
        int[] nums=new int[]{2,3,6,7};
        List<List<Integer>> lists = conbinationSum(nums, 7);
        System.out.println(lists);

    }
    public static List<List<Integer>> conbinationSum(int[] candidates,int target){
        List<Integer> path=new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(candidates);       //给数组排序
        backtrack(candidates,target,0,path,result);
        return result;

    }

    public static void backtrack(int[] candidates,int remain,int index,List<Integer> path,
                          List<List<Integer>> result){
        if(remain==0){
            result.add(new ArrayList<>(path));
            return;
        }
        if(remain<candidates[index]){
            return;
        }

        for(int i=index;i<candidates.length;i++){

            path.add(candidates[i]);
            backtrack(candidates,remain-candidates[i],i,path,result);
            path.remove(path.size()-1);
        }
    }
}
