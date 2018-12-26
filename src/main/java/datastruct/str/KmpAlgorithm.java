package datastruct.str;

import org.junit.Test;

public class KmpAlgorithm {

    /**
     * 判断子串在母串里的位置
     */
    @Test
    public void direct() {
        //遍历字符串 str , 在每一次的遍历中 判断当前字符串以及后几位子串的字符串是否等于子字符串
        //123456   34   12  23  34  45 56   total = 6  end = 6-2+1
        char[] strArr = "abcdefsabcdidsasdfsabcdsdfsdfs".toCharArray();
        char[] subArr = "sabcd".toCharArray();
        int endIndex = strArr.length - subArr.length + 1;
        for (int i = 0; i < endIndex; i++) {
            int rightCount = 0;
            for (int j = 0; j < subArr.length; j++) {
                if (strArr[i + j] == subArr[j]) {
                    rightCount++;
                }
            }
            if(rightCount == subArr.length){
                System.out.println(i);
                System.out.println("-------------------------------");
            }
        }
        System.out.println("********************right*******************");
        System.out.println("abcdefsabcdidsasdfsabcdsdfsdfs".indexOf("sabcd"));
        System.out.println("abcdefsabcdidsasdfsabcdsdfsdfs".lastIndexOf("sabcd"));
    }

    /**
     * 判断子串在母串里的位置
     */
    public void useKMP() {
        String str = "abcdefsabcdidsasdfsabcdsdfsdfs";
        String part = "sabcd";
        //todo ???????????????????????????????????????????????????
    }
}
