package applications;

import java.util.*;

public class maximumSwap {
    public static void main(String[] args) {
        System.out.println(new maximumSwap().maximumS2(9178));
    }

    public int maximumSw(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] maxIndex = new int[chars.length];
        int max = chars.length - 1;
        // 从右向左找最大数的下标
        for (int i = max; i >= 0; i--) {
            if (chars[i] - '0' > chars[max] - '0') {
                max = i;
            }
            maxIndex[i] = max;
        }
        // 从左向右找到第一个不是最大数的数，将其与最大数位置交换
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] - '0' != chars[maxIndex[j]] - '0') {
                char tmp = chars[maxIndex[j]];
                chars[maxIndex[j]] = chars[j];
                chars[j] = tmp;
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }


    public int maximumS2(int num) {
        StringBuilder n = new StringBuilder(String.valueOf(num));
        if (n.length() == 1) {
            return num;
        }
        int ans = num;
        // 每次交换两个字符，维护一个最大值（暴力解法）
        for (int i = 0; i < n.length(); ++i) {
            for (int j = i + 1; j < n.length(); ++j) {
                char c1 = n.charAt(i);
                char c2 = n.charAt(j);
                n.setCharAt(i,c2);
                n.setCharAt(j,c1);
                int tmp = Integer.valueOf(n.toString());
                if (tmp >= ans) {
                    ans = tmp;
                }
                n.setCharAt(i,c1);
                n.setCharAt(j,c2);
            }
        }
        return ans;
    }
}
