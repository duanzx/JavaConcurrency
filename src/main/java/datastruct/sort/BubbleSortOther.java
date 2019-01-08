package datastruct.sort;

import org.junit.Test;

public class BubbleSortOther {


    @Test
    public void testBubbleOne() {
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
    public void testInsertOne() {
        int[] arr = buildSource();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j - 1] > arr[j]) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
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
