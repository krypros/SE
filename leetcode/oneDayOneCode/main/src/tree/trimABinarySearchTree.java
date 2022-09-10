package tree;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.right = right;
    }
}
/*
* 2022/9/10 https://leetcode.cn/problems/trim-a-binary-search-tree/
* 左边的小于目标，检查左边的
* */
public class trimABinarySearchTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(0, new TreeNode(1), new TreeNode(2));
    }

    // 递归
    public TreeNode trimBST1(TreeNode root, int low, int high) {
        if(root == null)
            return root;
        if(root.val < low)
            return trimBST1(root.right, low, high);
        if(root.val > high)
            return trimBST1(root.left, low, high);
        // 处理正常节点
        root.left = trimBST1(root.left, low, high);
        root.right = trimBST1(root.right, low, high);
        return root;
    }

    // 迭代
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        // root移动到需要调整的位置
        while(root != null && (root.val < low || root.val > high)) {
            if(root.val < low)
                root = root.right;
            else
                root = root.left;
        }
        if (root == null)
            return root;
        for(TreeNode node = root; node.left != null; ) {
            if(node.left.val < low)
                node.left = node.left.right;
            else
                node = node.left;
        }
        for(TreeNode node = root; node.right != null; ) {
            if(node.right.val > high)
                node.right = node.right.left;
            else
                node = node.right;
        }
        return root;
    }
}
