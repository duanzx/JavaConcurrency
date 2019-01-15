package datastruct.complex;

import org.junit.Test;

public class ComplexTest {

    /**
     * 分治递归 求一个数组中的最大值和次最大值
     */
    public void max2Arr(int[] arr, int start, int end) {
        if (start + 1 == end) {
            System.out.println("Start: " + start + ", End: " + end);
            return;
        }
        int middle = (start + end) >> 1;
        max2Arr(arr, start, middle);
        max2Arr(arr, middle + 1, end);
    }

    /**
     * 分治递归 求一个数组中的最大值
     */

    public int maxArr(int[] arr, int start, int end) {
        if (arr[start] == arr[end]) {
            return arr[start];
        }
        int mi = (start + end) >> 1;
        int left = maxArr(arr, start, mi);
        int right = maxArr(arr, mi + 1, end);
        if (left < right) {
            return right;
        }
        return left;
    }

    @Test
    public void test() {
        int[] arr = buildArr();
        max2Arr(arr, 0, arr.length - 1);
    }

    public int[] buildArr() {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            int randomInt = (int) (Math.random() * 100 + i);
            arr[i] = randomInt;
            System.out.println(arr[i]);
        }
        return arr;
    }

    @Test
    public void testFib() {
        int n = 5;
        int result = fib3(n);
        System.out.println(String.format("第%d项的值是：%d", n, result));
    }

    public int fib1(int n) {
        return n < 2 ? n : fib1(n - 1) + fib1(n - 2);
    }

    public int fib2(int n) {
        int minus2 = 0;
        int minus1 = 1;
        for (int i = 1; i < n; i++) {
            minus1 = minus2 + minus1;
            minus2 = minus1 - minus2;
        }
        return minus1;
    }

    public int fib3(int n) {
        int minus2 = 0;
        int minus1 = 1;
        while (n > 1) {
            minus1 = minus2 + minus1;
            minus2 = minus1 - minus2;
            n--;
        }
        return minus1;
    }

    /**
     * 任意给定两个序列，求两个序列的最长公共子序列的长度
     */
    @Test
    public void publicSubLength() {
        String a = "123456";
        String b = "23412356";
        char[] arr = a.toCharArray();
        char[] brr = b.toCharArray();
        int[] pl = new int[a.length()];//记录以数组中每个元素开头的公共子序列长度
        for (int i = 0; i < a.length(); i++) {
            int aindex = i;
            int acount = 0;
            int j = -1;//brr的角标， -1代表是否从新开始遍历
            while (aindex < arr.length && j < brr.length) {
                if (j == -1 || arr[aindex] != brr[j]) {
                    j++;
                } else {
                    aindex++;
                    j++;
                    acount++;
                }
            }
            pl[i] = acount;
        }
        int maxlength = pl[0];
        for (int i = 1; i < pl.length; i++) {
            System.out.println(pl[i]);
            if (maxlength < pl[i]) {
                maxlength = pl[i];
            }
        }
        System.out.println("最长公共子序列长度是：" + maxlength);
    }

}
