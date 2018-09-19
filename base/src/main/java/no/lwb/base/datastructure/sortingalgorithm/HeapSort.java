package no.lwb.base.datastructure.sortingalgorithm;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * 堆排序
 *
 * 本质也是完全二叉查找树
 * 最大堆：堆上任意父节点元素都大于等于左右子节点
 * 最小堆：堆上任意父节点元素都小于等于左右子节点
 *
 * TODO 实现
 *
 * @author WeiBin Lin
 * @since 2018/9/17
 */
@Log4j2
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5,2,9,7,5,3,4,6};
//        buildHeapSort(arr);
        heapSort(arr);
        log.info(Arrays.toString(arr));
    }

    private static int[] buildHeapSort(int[] array) {
        // 从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (array.length -2)/2;i >=0; i--) {
            adjustDownToUp(array, i, array.length);
        }
        return array;
    }


    private static void adjustDownToUp(int[] array,int k,int length){
        int temp = array[k];
        for(int i=2*k+1; i<length-1; i=2*i+1){
            if(i<length && array[i]<array[i+1]){  //取节点较大的子节点的下标
                i++;   //如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if(temp>=array[i]){  //根节点 >=左右子女中关键字较大者，调整结束
                break;
            }else{   //根节点 <左右子女中关键字较大者
                array[k] = array[i];  //将左右子结点中较大值array[i]调整到双亲节点上
                k = i; //【关键】修改k值，以便继续向下调整
            }
        }
        array[k] = temp;  //被调整的结点的值放人最终位置
    }

    public static int[] heapSort(int[] target) {
        if (target != null && target.length > 1) {

            // 调整为最大堆
            int pos = (target.length - 2) / 2;
            while (pos >= 0) {
                shiftDown(target, pos, target.length - 1);
                pos--;
            }

            // 堆排序
            for (int i = target.length-1; i > 0; i--) {
                int temp = target[i];
                target[i] = target[0];
                target[0] = temp;
                shiftDown(target, 0, i-1);
            }
            return target;
        }
        return target;
    }


    /**
     * @description 自上而下调整为最大堆
     * @author rico
     * @created 2017年5月25日 上午9:45:40
     * @param target
     * @param start
     * @param end
     */
    private static void shiftDown(int[] target, int start, int end) {
        int i = start;
        int j = 2 * start + 1;
        int temp = target[i];
        while (j <= end) {   // 迭代条件
            if (j < end && target[j + 1] > target[j]) {  //找出较大子女
                j = j + 1;
            }
            if (target[j] <= temp) {  // 父亲大于子女
                break;
            } else {
                target[i] = target[j];
                i = j;
                j = 2 * j + 1;
            }
        }
        target[i] = temp;
    }

}
