public class 快速排序 {
    public static void sort(int[] nums){
        if(nums==null || nums.length<=0) return ;
        quickSort(nums,0,nums.length-1);
    }

    private static void quickSort(int[] nums,int start,int end) {
        if(start>=end){
            return;
        }
        int pivotIndex=partition(nums,start,end);
        quickSort(nums,start,pivotIndex-1);
        quickSort(nums,pivotIndex+1,end);

    }
    private static int partition(int[] nums,int start,int end){
        int pivot=nums[end];
        int pointer=start;
        for(int i=start;i<end;i++){
            if(nums[i]<pivot){
                swap(nums,i,pointer);
                pointer++;
            }
        }
        swap(nums,pointer,end);
        return pointer;     //返回基准的位置
    }
    public static void swap(int[] nums,int left,int right){
        int temp=nums[right];
        nums[right]=nums[left];
        nums[left]=temp;
    }
}
