package priorityQueue;
/*
* 2022/9/11 857. 雇佣 K 名工人的最低成本 困难
* https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
*
*
* */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class minimumCostToHireKWorkers {
    public static void main(String[] args) {
        int[] q = {3,1,10,10,1};
        int[] w = {4,8,2,2,7};
        System.out.println(new minimumCostToHireKWorkers().mincostToHireWorkers(q, w, 3));
    }

    /*
    * 贪心+优先队列
    * quality: 每个工人的工作质量
    * wage: 最低期望工资
    * */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        // 保存工人性价比 - 工资/质量，质量
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; ++i) {
            workers[i] = new double[]{(double)(wage[i]) / quality[i], (double)quality[i]};
        }
        // 按性价比排序
        Arrays.sort(workers, Comparator.comparingDouble(a -> a[0]));
        double res = Double.MAX_VALUE;
        double qualitySum = 0.0;
        // 大根堆（取负数）：优先级队列的头部是基于自然排序或基于比较器的排序的最小元素
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker: workers) {
            qualitySum += worker[1];  // 取出性价比最高的工人质量
            pq.add(-worker[1]);  // 将该工人加入优先队列
            if (pq.size() > k) {
                qualitySum += pq.poll(); // 如果当前人数多了，减去人数最多的
            }
            if (pq.size() == k) {
                res = Math.min(res, qualitySum * worker[0]); // 要满足性价比最低的工作的需求
            }
        }
        return res;
    }
}
