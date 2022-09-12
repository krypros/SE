package applications;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 2022/9/12    1608. 特殊数组的特征值
 * 简单
 * https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
 * */
public class specialArray {
    public static void main(String[] args) {
        int[] nums = {3, 5};
        System.out.println(new specialArray().specialArrayNum(nums));
    }

    public int specialArrayNum(int[] nums) {
        for (int i = nums.length; i > 0; i--) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] >= i)
                    sum += 1;
            }
            if (sum == i)
                return sum;
        }
        return -1;
    }

    public int specialArraySort(int[] nums) {
        Arrays.sort(nums);
        // 最小值大于数组长度 -> 所有元素
        if (nums[0] >= nums.length)
            return nums.length;
        for (int i = 1; i < nums.length - 1; i++) {
            // 倒数第i个元素大于等于i，倒数第i+1个元素小于i
            if (nums[nums.length - i] >= i && nums[nums.length - i - 1] < i)
                return i;
        }
        return -1;
    }
}
