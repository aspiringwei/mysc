package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;


/**
 * 冒泡排序：：
 * 时间复杂度 O(n^2)
 * 两个 for 循环，比较相邻元素交换位置
 *
 * @author WeiBin Lin
 * @since 2018/9/17
 */
@Log4j2
public final class BubbleSort {

    public static void main(String[] args) {
        int[] arr = Constants.INT_ARRAY;
        bubbleSort(arr);
    }

    /**
     * 冒泡排序
     * 相邻元素两两比较的方式
     * 时间复杂度O(n^2)
     * 0    5，8，6，3，9，2，1，7
     * 1    56382179
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        log.info("冒泡排序..before：{}", arr);
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
            // 如果前一轮判断已排序，则退出
            if (isSorted) {
                break;
            }

        }
        log.info("冒泡排序..after：{}", arr);
    }
}
