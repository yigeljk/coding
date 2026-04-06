import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.util.ArrayList;
import java.util.List;

public class 全排列 {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        boolean[] visited=new boolean[nums.length];
        backtrack(result,path,visited,nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result,List<Integer> path,
                           boolean[] visited,int[] nums) {
        int len=nums.length;
        if(path.size()==len){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<len;i++){
            if(visited[i]){
                continue;
            }
            path.add(nums[i]);
            visited[i]=true;
            backtrack(result,path,visited,nums);
            visited[i]=false;
            path.remove(path.size()-1);
        }

    }
}
