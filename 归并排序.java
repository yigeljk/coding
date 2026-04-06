import java.util.SortedMap;

public class 归并排序 {
    public static void mergeSort(int[] arr,int left,int right) {
        if(left>=right)  return;
        int middle=(left+right)/2;
        mergeSort(arr,left,middle);
        mergeSort(arr,middle+1,right);
        merge(arr,left,middle,right);

    }

    public static void merge(int[] arr,int left,int mid,int right){
        int len1=mid-left+1;
        int len2=right-mid;

        int[] L=new int[len1];
        int[] R=new int[len2];

        for(int i=0;i<len1;i++){
            L[i]=arr[left+i];
        }

        for(int i=0;i<len2;i++){
            R[i]=arr[mid+i+1];
        }

        int i=0,j=0;
        int k=left;
        while(i<len1 && j<len2){
            if(L[i]<=R[j]){
                arr[k]=L[i];
                i++;
            }else{
                arr[k]=R[j];
                j++;
            }
            k++;
        }
        if(i==len1){
            while(j<len2){
                arr[k]=R[j];
                k++;
                j++;
            }
        }
        if(j==len2){
            while(i<len1){
                arr[k]=L[i];
                k++;
                i++;
            }
        }
        return ;
    }
}