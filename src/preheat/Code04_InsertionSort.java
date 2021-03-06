package preheat;

import java.util.Arrays;

/**
 * @Author: duccio
 * @Date: 17, 03, 2022
 * @Description: Insertion Sort
 * @Note:   1. Iterate through the array, with ending position increasing
 *          2. For each iteration, insert the value of the end index into the right position
 *          ======
 *          1. It is actually swapping until meet the right position, rather than directly inserting into the position
 *          2. After each iteration, the former segment is fixed
 *          3. Time complexity varies based on how the original list is sorted
 */
public class Code04_InsertionSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 5, 7};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int i = end; i > 0 && arr[i - 1] > arr[i]; i--) {
                swap(arr, i - 1, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
