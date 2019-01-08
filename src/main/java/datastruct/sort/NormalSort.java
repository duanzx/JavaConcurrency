package datastruct.sort;

import org.junit.Test;

public class NormalSort {

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

