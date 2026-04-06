import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 合并区间 {
    public int[][] merge(int[][] intervals){
        int rows=intervals.length;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        List<int[]> result=new ArrayList<>();
        result.add(intervals[0]);
        int[] prevArray=intervals[0];
        for(int i=1;i<rows;i++){
            int[] currArray=intervals[i];    //当前数组
            if(currArray[0]>prevArray[1]){
                prevArray=currArray;
                result.add(currArray);
            }else{
                if(currArray[1]>prevArray[1]){
                    prevArray[1]=currArray[1];
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
