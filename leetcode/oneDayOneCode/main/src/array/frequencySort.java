package array;


import java.util.*;

/*
* 1636. 按照频率将数组升序排序
* https://leetcode.cn/problems/sort-array-by-increasing-frequency/
* 简单
* 2022/9/19
* */
public class frequencySort {
    public static void main(String[] args) {
        int[] input = {1, 1,1,2,2,4,3,3,4};
        int[] res = new frequencySort().frequencyS(input);
        for(int i: res)
            System.out.println(i);
    }

    public int[] frequencyS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, (a, b) -> {
            int cnt1 = map.get(a), cnt2 = map.get(b);
            return cnt1 != cnt2 ? cnt1 - cnt2 : b - a;
        });
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
}
