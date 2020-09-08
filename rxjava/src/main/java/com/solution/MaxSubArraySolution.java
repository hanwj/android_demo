package com.solution;

/**
 * 求两个连续不重叠子数组的最大和
 * 将数组分为两部分，分别计算左右两侧连续数组的最大和left[i]和right[i+1]
 * 循环遍历上面步骤，比较left[i] + right[i+1]，输出最大值
 */
public class MaxSubArraySolution {

    public static void main(String[] args){
        int[] arr = {1, 3, -1, 2, -1, 2};
//        int[] arr = {5,4};
        int max = maxSubArray(arr);
        System.out.println("max :" + max);
    }

    private static int maxSubArray(int[] arr){
        int size = arr.length;

        //从左侧开始遍历计算前n项的最大和
        int[] left = new int[size];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < size;i++){
            sum = sum < 0 ? arr[i] : sum + arr[i];
            max = Math.max(max,sum);
            left[i] = max;
        }

        //从右侧开始遍历计算后n项的最大和
        sum = 0;
        max = Integer.MIN_VALUE;
        int[] right = new int[size];
        for (int i = size-1;i >= 0;i--){
            sum = sum < 0 ? arr[i] : sum + arr[i];
            max = Math.max(max,sum);
            right[i] = max;
        }

        //遍历计算以i为分割点，左右两侧连续子数组的最大和
        max = Integer.MIN_VALUE;
        for (int i = 0; i < size-1;i++){
            max = Math.max(max,left[i] + right[i+1]);
        }
        return max;
    }
}
