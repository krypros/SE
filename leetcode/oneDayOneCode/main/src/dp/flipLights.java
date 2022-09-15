package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* 672. 灯泡开关 Ⅱ
* https://leetcode.cn/problems/bulb-switcher-ii/
* 中等
* 2022/9/15
* */
public class flipLights {
    public static void main(String[] args) {
        System.out.println(4 ^ 1);
//        System.out.println(new flipLights().test1(3,100));
    }

    /*
    * 不需要考虑按钮按动的顺序，只需考虑每个按钮被按了几次
    * -> 只需要考虑被按了奇数次还是偶数次
    *
    * 有n个已经打开的灯泡
    * */
    public int test1(int n, int presses) {
        Set<Integer> state = new HashSet<Integer>();
        // 开关的组合数最多16种 0000, 0001, ... 1111
        for (int i = 0; i < 1 << 4; i++) {
            // pressArr表示四个按钮的状态
            int[] pressArr = new int[4];
            for (int j = 0; j < 4; j++) {
                // 初始状态是开关1打开，其他关闭
                pressArr[j] = (i >> j) & 1;
            }
            int sum = Arrays.stream(pressArr).sum();
            //  若 sum <= press，若相差为偶数，只需开，关任意灯各一次即可为press次的状态，状态不变
            if (sum % 2 == presses % 2 && sum <= presses) {
                // 一号位灯状态,受到1,3,4开关影响，异或
                int status = pressArr[0] ^ pressArr[2] ^ pressArr[3];
                if (n >= 2) {
                    // 二号灯状态，受到1,2开关影响
                    status |= (pressArr[0] ^ pressArr[1]) << 1;
                }
                if (n >= 3) {
                    //三号灯状态，受到1,3开关影响
                    status |= (pressArr[0] ^ pressArr[2]) << 2;
                }
                if (n >= 4) {
                    // 四号灯状态，受到1，2,4开关影响
                    status |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3;
                }
                state.add(status);
            }
        }
        return state.size();
    }
}

/*

开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
开关 4 ：反转编号为 j = 3k + 1 的灯的状态（1, 4, 7, ...）

1,7号灯(6k+1)  1,3,4可控制
2,6,8,12号灯(6k+2, 6k+6)  1,2可控制
3,5,9,11号灯(6k+3, 6k+5)  1,3可控制
4,10号灯(6k+4)  1,2,4可控制

只需要考虑1,2,3,4号灯的变化就行了，因为其他灯都是联动的

*/