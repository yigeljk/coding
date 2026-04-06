public class 下一个排列 {
    public static void nextPermutation(int[] nums){
        int len=nums.length;
        if(len<=1) return;

        //从后往前，寻找第一个下降点
        int i=len-2;
        while (i>=0 && nums[i]>=nums[i+1]){
            i--;
        }

        if(i<0){
            reverse(nums,0,len-1);
            return;
        }

        int j=len-1;
        while(nums[j]<=nums[i]){
            j--;
        }

        swap(nums,i,j);

        reverse(nums,i+1,len-1);


    }

    public  static  void reverse(int[] nums,int left,int right){
        while (left<right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }

    public static  void swap(int[] nums,int left,int right){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
}
