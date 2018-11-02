package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;

/**
 * @author WeiBin Lin
 * modify::lin.wb copy from github
 */
@Log4j2
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = Constants.INT_ARRAY;
        log.info("..before：{}", arr);
        insertSort(arr);
        log.info("..after：{}", arr);
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            // 查询要插入的位置并移动元素
            for (; j >=0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = value;
        }

    }
}
