package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author WeiBin Lin
 * @since 2018/9/17
 */
@Log4j2
public final class QuickSort {

    /**
     * 快速排序
     *  - 对冒泡排序的改进
     *  任取一个元素作为关键数据，比她小的放前面，比她大的放后面
     *  3 5 4 2
     *  -> 3
     *  2 3 5 4
     * @param arr
     */
    public static int[] quickSort(int[] arr, int left, int right) {
        Objects.requireNonNull(arr);
        if (left >= right) {
            return arr;
        }
        // 基准元素
        int pivotIdx = partition(arr, left, right);
        quickSort(arr, left, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, right);
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 第一个元素
        int pivot = arr[left];
        int pivotIdx = left;
        for (int i = left + 1; i <= right; i++) {
            // 如果元素值小于基准元素，则基准往后移
            if (arr[i] < pivot) {
                pivotIdx++;
                //
                if (pivotIdx != i) {
                    int temp = arr[pivotIdx];
                    arr[pivotIdx] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        arr[left] = arr[pivotIdx];
        arr[pivotIdx] = pivot;
        log.info(Arrays.toString(arr));
        return pivotIdx;
    }

}
