package applications;

import java.util.Arrays;
import java.util.Scanner;
/*
* 2022/9/14
* https://leetcode.cn/problems/mean-of-array-after-removing-some-elements/
* 简单  1619. 删除某些元素后的数组均值
* */
public class trimMean {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int[] l = {1,2,2,2,2,2,2,2,2,2,2,5};
        System.out.println(new trimMean().meanOfArray(l));
    }

    public double meanOfArray(int[] arr) {
        Arrays.sort(arr);
        int num = arr.length / 20;
        double sum = 0.0;
        for (int i = num; i < arr.length - num; i++) {
            sum += arr[i];
        }
        return sum / (arr.length - 2 * num);
    }
}
