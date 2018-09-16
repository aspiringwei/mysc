package no.lwb.base.排序;

import java.util.Objects;
import java.util.Random;
import java.util.Stack;

/**
 * @author ixm.
 * @date 2018/7/22
 */
public class 归并排序 {

    public static void main(String[] args) {

        // 产生随机数
        int length = 5;
        int[] waitingSortArr = new int[length];
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randomValue = random.nextInt(length * 2);
            waitingSortArr[i] = randomValue;
        }
        int[] before = waitingSortArr.clone();
        // 排序
        int[] result = bubbleSort(waitingSortArr);
        System.out.println("------------------");

    }

    /**
     * 冒泡排序
     * 相邻元素两两比较的方式
     * 时间复杂度O(N^2)
     * 0    5，8，6，3，9，2，1，7
     * 1    56382179
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                    isSorted = false;
                }
            }
            System.out.println(i);
            // 如果前一轮判断已排序，则退出
            if (isSorted) {
                break;
            }

        }
        return arr;
    }

    /**
     * 快速排序
     *  - 对冒泡排序的改进
     *  任取一个元素作为关键数据，比她小的放前面，比她大的放后面
     *  3 5 4 2
     *  -> 3
     *  2 3 5 4
     * @param arr
     */
    public static void quickSort(int[] arr, int left, int right) {
        Objects.requireNonNull(arr);
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(arr, left, right);
        quickSort(arr, left, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        // todo
        return 0;
    }
}
