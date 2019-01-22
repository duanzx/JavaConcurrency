package datastruct.vector;

import org.junit.Test;

public class VectorTest {

    @Test
    public void testDuplicate(){
        int[] sourceArr = new int[]{1,2,3,4,2,6,2,5};
        printArr(sourceArr);
//        System.out.println("Index :"+findByRange(sourceArr,1,0,4));
        int i = 1;
        while (i < sourceArr.length){
            if(findByRange(sourceArr,sourceArr[i],0,i) != -1){
                System.out.println("删除元素："+sourceArr[i]);
                removeByRange(sourceArr,i,i+1);
            }
            i++;
        }
        printArr(sourceArr);
    }

    public void removeByRange(int[] sourceArr,int start,int limit){
        while (limit < sourceArr.length){
            int temp = limit++;
            sourceArr[start++] = sourceArr[temp];
            sourceArr[temp] = 0;
        }
    }

    public int findByRange(int[] sourceArr,int target,int start,int limit){
        while (start < limit--){
            if(sourceArr[limit] == target){
                return limit;
            }
        }
        return -1;
    }

    @Test
    public void testFindRange(){
        int[] sourceArr = initializerArray();
        printArr(sourceArr);
        int target = 4;
        int start = 2;
        int limit = 5;
        while (start < limit){
            if(sourceArr[limit] == target){
                System.out.println("Index : "+limit+", Data="+sourceArr[limit]);
            }
            limit--;
        }
    }

    @Test
    public void testFind(){
        int[] sourceArr = initializerArray();
        printArr(sourceArr);
        int target = 6;
        int i = sourceArr.length;
        while (i > 0){
            if(sourceArr[i-1] == target){
                System.out.println("Index : "+ (i-1));
                return;
            }
        }
        System.out.println("Index : -1");
    }

    @Test
    /** >=start , < limit */
    public void deleteRange(){
        int[] sourceArr = initializerArray();
        printArr(sourceArr);
        int start = 2;
        int limit = 4;
        while (limit < sourceArr.length){
            int temp = limit++;
            sourceArr[start++] = sourceArr[temp];
            sourceArr[temp] = 0;
        }
        printArr(sourceArr);
    }

    @Test
    public void testInsertArray(){
        int index = 2;
        int obj = 20;
        int[] sourceArr = initializerArray();
        printArr(sourceArr);
        int size = sourceArr.length;
        sourceArr = increaseTimesArray(sourceArr);
        printArr(sourceArr);
        for(int i = size;i>index;i--){
            sourceArr[i] = sourceArr[i-1];
        }
        sourceArr[index] = obj;
        printArr(sourceArr);
    }

    public void printArr(int[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+" ,");
        }
        System.out.println(" ");
    }

    /**以固定容量递增扩容*/
    @Test
    public void testIncreaseArray() {
        int n = 100;
        int fix = 2;
        int[] sourceArr = initializerArray();
        for (int i = 0; i < n; i++) {
            if (i < sourceArr.length) {
                sourceArr[i] = i;
            } else {
                sourceArr = increaseArray(sourceArr, fix);
            }
        }
        //算法时间复杂度
        //7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73,75,77,79,81,83,85,87,89,91,93,95,97,99,101,
        //算数级数：an = a1 + (n-1)d    O(n^2)
    }

    @Test
    public void testIncreaseTimesArray(){
        int n = 100;
        int[] sourceArr = initializerArray();
        for (int i = 0; i < n; i++) {
            if (i < sourceArr.length) {
                sourceArr[i] = i;
            } else {
                sourceArr = increaseTimesArray(sourceArr);
            }
        }
        //时间复杂度：10,20,40,80,160,
        //an = a1*2^(x-1)  几何级数 O(n) = an  = O(n)
    }

    public int[] increaseTimesArray(int[] sourceArr){
        int length =  sourceArr.length;
        /**每次增加一倍*/
        int[] newArr = new int[length << 1];
        for(int j = 0;j<newArr.length;j++){
            if(j < length){
                newArr[j] = sourceArr[j];
            }else{
                newArr[j] = 0;
            }
        }
       // System.out.print(newArr.length+",");
        return newArr;
    }

    public int[] increaseArray(int[] sourceArr,int fix){
        int length =  sourceArr.length;
        int[] newArr = new int[length+fix];
        for(int j = 0;j<newArr.length;j++){
            if(j < length){
                newArr[j] = sourceArr[j];
            }else{
                newArr[j] = 0;
            }
        }
        System.out.print(newArr.length+",");
        return newArr;
    }

    public int[] initializerArray(){
        return new int[]{1,4,2,5,3,6,7};
    }
}
