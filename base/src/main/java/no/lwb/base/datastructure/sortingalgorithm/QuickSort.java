package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Objects;

/**
 * 快排： 分治 分区
 * 对一组数据 p - r ，选择分区点 pivot
 * 小于 pivot 的放置在其左边 大于 pivot 的放置在其右边
 * 原地排序 空间复杂度 O(1) 时间复杂度 O(nlogn) 本身是已排序的情况会退化成O(n^2)
 * 非原地排序 利用拷贝到临时数组的方式 空间复杂度 O(n)
 *
 * @author WeiBin Lin
 * @since 2018/9/17
 */
@Log4j2
public final class QuickSort {

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
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
        // 获取分区点 基准元素
        int pivotIdx = partition(arr, left, right);
        quickSort(arr, left, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, right);
    }

    /**
     * 随机选取一个元素作为 pivot
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        // 第一个元素
        int pivot = arr[left];
        int pivotIdx = left;
        for (int i = left + 1; i <= right; i++) {
            // 如果元素值小于基准元素，则基准往后移
            if (arr[i] < pivot) {
                pivotIdx++;
                // 如果相同不交换，否则swap
                if (pivotIdx != i) {
//                    int temp = arr[pivotIdx];
//                    arr[pivotIdx] = arr[i];
//                    arr[i] = temp;
                    arr[pivotIdx] = arr[i] ^ arr[pivotIdx];
                    arr[i] = arr[i] ^ arr[pivotIdx];
                    arr[pivotIdx] = arr[i] ^ arr[pivotIdx];
                }
            }
        }
        arr[left] = arr[pivotIdx];
        arr[pivotIdx] = pivot;
        log.info("arr:{},pivot:{}", Arrays.toString(arr), pivotIdx);
        return pivotIdx;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 8, 3, 9, 7, 1, 2, 5};
        quickSort(arr);
        log.info(Arrays.toString(arr));
    }

}
