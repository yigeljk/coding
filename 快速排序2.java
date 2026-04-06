import java.util.List;
import java.util.Stack;

public class 快速排序2 {
    public void sort(int[] nums){
        if(nums==null || nums.length==0) return ;
        quickSort(nums,0,nums.length-1);
    }
    public  void quickSort(int[] nums,int left,int right){
        if(left>=right) return;

        //找到快速排序的基准数的位置
        int middleIndex=partitionIndex(nums,left,right);
        quickSort(nums,left,middleIndex-1);
        quickSort(nums,middleIndex+1,right);
    }

    public int partitionIndex(int[] nums,int left,int right){
       int end=nums[right];    //数组的最后一个元素为基准数
        int pointer=left;      //pointer左边的数字是小于基准数的
       for(int i=left;i<=right-1;i++){
           if(nums[i]<end){
               swap(nums,i,pointer);
               pointer++;
           }
       }
       swap(nums,right,pointer);
       return pointer;
    }

    public void swap(int[] nums,int left,int right){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
}
