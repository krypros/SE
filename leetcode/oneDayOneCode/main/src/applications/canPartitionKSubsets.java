package applications;

import java.util.*;

/*
* 2022-9-20
* https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
* 中等  698. 划分为k个相等的子集
* */
public class canPartitionKSubsets {
    int[] nums;
    int per, n;
    boolean[] dp;
    public static void main(String[] args) {
        System.out.println(new canPartitionKSubsets().canPartitionKS(new int[] {1,2,3,4,5}, 4));
    }

    // 状态压缩 + 记忆化搜索
    public boolean canPartitionKS(int[] nums, int k) {
        this.nums = nums;
        int all = Arrays.stream(nums).sum();
        if (all % k != 0) {
            return false;
        }
        per = all / k;
        Arrays.sort(nums);
        n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        dp = new boolean[1 << n];
        Arrays.fill(dp, true);
        // 可用的数字状态为S的情况下是否可行
        return dfs((1 << n) - 1, 0);
    }

    public boolean dfs(int s, int p) {
        if (s == 0) {
            return true;
        }
        if (!dp[s]) {
            return dp[s];
        }
        dp[s] = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] + p > per) {
                break;
            }
            if (((s >> i) & 1) != 0) {
                if (dfs(s ^ (1 << i), (p + nums[i]) % per)) {
                    return true;
                }
            }
        }
        return false;
    }
}
