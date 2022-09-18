package dfs;

import java.util.*;

public class largestIsland {
    public static void main(String[] args) {
        int[][] s = {{1,0}, {0,1}};
        System.out.println(new largestIsland().largestLand(s));
    }

    // 控制一个点上下左右移动
    static int[] d = {0, -1, 0, 1, 0};

    public int largestLand(int[][] grid) {
        int n = grid.length, res = 0;
        // tag记录每个点所属的岛屿的标记
        int[][] tag = new int[n][n];
        Map<Integer, Integer> area = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 如果值为1，且还没标记属于那个岛
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    // 标记值与岛屿的某个i,j有关
                    int t = i * n + j + 1;
                    area.put(t, dfs(grid, i, j, tag, t));
                    res = Math.max(res, area.get(t));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int z = 1;
                    Set<Integer> connected = new HashSet<Integer>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k], y = j + d[k + 1];
                        if (!valid(n, x, y) || tag[x][y] == 0 || connected.contains(tag[x][y])) {
                            continue;
                        }
                        z += area.get(tag[x][y]);
                        connected.add(tag[x][y]);
                    }
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    // 对一个没计算过的点，从四周深度遍历
    public int dfs(int[][] grid, int x, int y, int[][] tag, int t) {
        int n = grid.length, res = 1;
        tag[x][y] = t;
        for (int i = 0; i < 4; i++) {
            // 二维坐标上下左右依次移动一位
            int x1 = x + d[i], y1 = y + d[i + 1];
            if (valid(n, x1, y1) && grid[x1][y1] == 1 && tag[x1][y1] == 0) {
                res += dfs(grid, x1, y1, tag, t);
            }
        }
        return res;
    }

    // 确保x,y都在(0,n)的范围内
    public boolean valid(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
