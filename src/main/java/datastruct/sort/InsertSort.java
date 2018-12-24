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
    public void sortOne() {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 5;
        int target = 3;
        for (int x = 0; x < arr.length; x++) {
            if (target < arr[x]) {
                //将x -> arr.length -2  的角标都向前移动一步，以容纳新的元素target
                for (int y = arr.length-1; y > x; y--) {
                    arr[y] = arr[y-1];
                }
                arr[x] = target;
                break;
            }
        }
        System.out.println(arr);

    }

    public static void main(String[] args) throws Exception {

    }
}
