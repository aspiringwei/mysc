package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;

/**
 * @author WeiBin Lin
 * @since 2018/9/17
 */
@Log4j2
public final class BubbleSort {

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
        return arr;
    }
}
