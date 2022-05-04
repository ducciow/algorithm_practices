package systematic.section20_MonotonousStack;

/**
 * @Author: duccio
 * @Date: 04, 05, 2022
 * @Description: The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *      Given an array of integers, return the maximum min-product of any non-empty subarray.
 *      https://leetcode.com/problems/maximum-subarray-min-product/
 * @Note:   1. Firstly get pre-sums.
 *          2. Use a monotonous stack for searching the range where current item is the smallest.
 *          3. Multiply current item with range-sum computed from pre-sums.
 *          ======
 *          Here the monotonous stack pops elements when the element is >= current item, instead of >. By doing this,
 *              for repeated values, only the last appearance of the value has the correct range, which does not matter
 *              for this problem because the maximum can still be captured.
 */
public class Code02_MaximumSubarrayMinProduct {

    public int maxSumMinProduct(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // get pre-sums
        long[] pres = new long[N];
        pres[0] = arr[0];
        for (int i = 1; i < N; i++) {
            pres[i] = pres[i - 1] + arr[i];
        }
        // get maximum min-product
        long ans = Long.MIN_VALUE;
        int[] stack = new int[N];
        int s = 0;
        for (int i = 0; i < N; i++) {
            while (s > 0 && arr[stack[s - 1]] >= arr[i]) {
                int idx = stack[--s];
                long presL = s < 1 ? 0 : pres[stack[s - 1]];
                long presR = pres[i - 1];
                long prod = (presR - presL) * arr[idx];
                ans = Math.max(ans, prod);
            }
            stack[s++] = i;
        }
        while (s > 0) {
            int idx = stack[--s];
            long presL = s < 1 ? 0 : pres[stack[s - 1]];
            long presR = pres[N - 1];
            long prod = (presR - presL) * arr[idx];
            ans = Math.max(ans, prod);
        }
        return (int) (ans % 1000000007);
    }

}
