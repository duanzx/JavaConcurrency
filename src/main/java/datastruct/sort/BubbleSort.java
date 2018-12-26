package datastruct.sort;

import org.junit.Test;

public class BubbleSort {

    /**
     * 冒泡排序,相邻两个元素比较
     */
    @Test
    public void bubbleSort() {
        int[] arr = new int[]{9, 5, 8, 3, 6, 1};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }
        System.out.println(arr);
    }


    /**
     * 插入排序
     */
    @Test
    public void insertSort() {
        int[] arr = new int[]{9, 5, 8, 3, 6, 1};
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j - 1] > temp) {
                    arr[j] = arr[j - 1]; //一直向前替换，直到1的位置
                    j--;
                }
                arr[j] = temp;
            }
        }
        System.out.println(arr);
    }


    /**
     * 选择排序
     */
    @Test
    public void selectSort() {
        int[] arr = new int[]{9, 5, 8, 3, 6, 1};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(arr);
    }

    /**
     * KMP
     */

    @Test
    public void kmp() {
        String str = "34223424";
        String subStr = "234";
        char[] strArr = str.toCharArray();
        char[] subArr = subStr.toCharArray();
        System.out.println("Index : " + str.indexOf(subStr));
        int[] next = getPMT(subStr);
        int i = 0;
        int j = 0;
        while (i < strArr.length && j < subArr.length) {
            if (j == -1 || strArr[i] == subArr[j]) {
                i++;
                j++;
                if (j == subArr.length) {
                    System.out.println("Index new = " + (i - j));
                }
            } else if (strArr != subArr) {
                j = next[j];
            }
        }
    }

    public int[] getPMT(String str) {
        char[] arr = str.toCharArray();
        int[] next = new int[arr.length];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < arr.length - 1) {
            if (j == -1) {
                j++;
                i++;
                next[i] = j;
            } else if (arr[i] == arr[j]) {
                j++;
                i++;
                next[i] = j;
            } else if (arr[i] != arr[j]) {
                j = next[j]; //去除可以省略的字符串 j = 要省略相同的前后缀的个数
            }
        }
        return next;
    }

}
