package datastruct.sort;

import org.junit.Test;

/**
 * 插入排序
 * 将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，时间复杂度为O(n^2)。
 * 是稳定的排序方法。插入算法把要排序的数组分成两部分：第一部分包含了这个数组的所有元素，但将最后一个元素除外（让数组多一个空间才有插入的位置）
 * ，而第二部分就只包含这一个元素（即待插入元素）。在第一部分排序完成后，再将这个最后元素插入到已排好序的第一部分中。
 */
public class InsertSort {

    /**
     * 将一个数字插入到一个有序的集合中，得到一个新的有序集合
     */
    @Test
    public void sortTail() {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 5;
        int target = 3;
        for (int x = 0; x < arr.length; x++) {
            if (target < arr[x]) {
                //将x -> arr.length -2  的角标都向后移动一步，以容纳新的元素target
                for (int y = arr.length-1; y > x; y--) {
                    arr[y] = arr[y-1];
                }
                arr[x] = target;
                break;
            }
        }
        System.out.println(arr);
    }

    @Test
    public void sortHead() {
        int[] arr = new int[5];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        arr[4] = 5;
        int target = 3;
        for (int x = 1; x < arr.length; x++) {
            if (target < arr[x]) {
                //将x -> arr.length -2  的角标都向前移动一步，以容纳新的元素target
                for (int y = 0; y < x; y++) {
                    arr[y] = arr[y+1];
                }
                arr[x-1] = target;
                break;
            }
        }

        System.out.println(arr);
    }

    /**
     * 对一个已有的数组使用插入排序进行编程
     * */
    @Test
    public void sortArrAsc(){
        int[] arr = new int[]{1,3,2,4,8,5};
        //假设已经排好序的数组为 int[]{1,X},待插入数字为3
        //假设已经排好序的数组为 int[]{1,3,XX},待插入数字为2
        //假设已经排好序的数组为 int[]{1,2,3,XX},待插入数字为4
        for(int i=1;i<arr.length;i++){
            if(arr[i-1] > arr[i]){
                int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j-1] > temp){
                    arr[j] = arr[j-1];
                    j--;
                }
                arr[j] = temp;
            }
        }
//        for (int i = 1; i < unsorted.length; i++)
//        {
//            if (unsorted[i - 1] > unsorted[i])
//            {
//                int temp = unsorted[i];
//                int j = i;
//                while (j > 0 && unsorted[j - 1] > temp)
//                {
//                    unsorted[j] = unsorted[j - 1];
//                    j--;
//                }
//                unsorted[j] = temp;
//            }
//        }
        System.out.println(arr);
    }

    @Test
    public void sortDesc(){
        int[] arr = new int[]{1,3,2,4,8,5};
    }

    @Test
    public void sortInsert(){
        int[] arr = new int[]{1,3,6,2,4,8,5};
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0 && arr[j] < arr[j-1];j--){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
        System.out.println(arr);
    }



    //二分插入发
    //归并排序

    public static void main(String[] args) throws Exception {

    }
}
