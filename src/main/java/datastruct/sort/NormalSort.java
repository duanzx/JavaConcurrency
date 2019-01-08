package datastruct.sort;

import org.junit.Test;

public class NormalSort {

    @Test
    public void  testInsertSort(){
        int[] arr = buildSource();
        for(int i = 1;i<arr.length;i++){
            if(arr[i-1] > arr[i]){
                int temp = arr[i];
                int j = i; // i i+1
                while (j > 0 && arr[j -1] > temp){
                    arr[j] = arr[j-1];
                    j--;
                }
                arr[j] = temp;
            }
        }
        printArr(arr);
    }


    @Test
    public void testInsertArr() {
        // 向已排序的数组 [1 2 4 5 6]中插入 3
        int[] arr = new int[6];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 5;
        arr[4] = 6;
        int target = 3;
        //比较 a[0] < 3
        for (int i = 0;i<5;i++) {
            if (arr[i] > 3) {//a[2] > 3 重新排列 a[2] = 3 ,a[3] = 4 ,a[4] = 5 ,a[5] = 6 , arr[i+1] = arr[i], 在arr[i]后面的元素都后移一位
                int temp = arr[i];
                int j = arr.length-1;
                while (j > i){
                    arr[j] = arr[j-1];
                    j--;
                }
                arr[i] = 3;
                break;
            }
        }
        printArr(arr);
    }

    @Test
    public void testBubble() {
        int[] arr = buildSource();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        printArr(arr);
    }


    @Test
    public void sortSelect() {
        int[] arr = buildSource();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printArr(arr);
    }

    @Test
    public void bubbleSortTail() {
        int[] arr = buildSource();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        printArr(arr);
    }


    @Test
    public void bubbleSortHead() {
        int[] arr = buildSource();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
        printArr(arr);
    }

    public int[] buildSource() {
        int[] arr = new int[5];
        for (int x = 0; x < 5; x++) {
            int randomInt = (int) (Math.random() * 100 + 1);
            arr[x] = randomInt;
        }
        printArr(arr);
        return arr;
    }

    public void printArr(int[] arr) {
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                System.out.print(arr[x] + " , \r\n");
            } else {
                System.out.print(arr[x] + " , ");
            }
        }
        System.out.println("-----------------------");
    }
}

