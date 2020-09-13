package me.javawold.dsa.recursion;

public class RangeSumBST {

    public int rangeSumBST(TreeNode root, int L, int R) {
        rangeSumBSTByMidOrder(root, L, R, new boolean[2]);
        return rangeSumBSTByPreOrder(root, L, R);
    }

    private int rangeSumBSTByPreOrder(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int rangeSum = 0;

        // head
        if (root.val >= L && root.val <= R) {
            rangeSum = root.val;
        }
        // left
        rangeSum += rangeSumBSTByPreOrder(root.left, L, R);
        // right
        rangeSum += rangeSumBSTByPreOrder(root.right, L, R);

        return rangeSum;
    }

    private int rangeSumBSTByMidOrder(TreeNode root, int L, int R, boolean[] found) {// problem
        if (root == null) {
            return 0;
        }
        if (found[1]) {// R found
            return 0;
        }

        int rangeSum = 0;

        // left
        rangeSum += rangeSumBSTByMidOrder(root.left, L, R, found);

        // head
        if (found[1]) {// R found
            return rangeSum;
        }
        if (found[0]) {// L found
            rangeSum += root.val;
        } else { // L+R not found
            if (root.val == L) {// L found
                rangeSum += root.val;
                found[0] = true;
            }
            if (root.val == R) {// R found
                rangeSum += root.val;
                found[1] = true;
            }
        }

        // right
        rangeSum += rangeSumBSTByMidOrder(root.right, L, R, found);

        return rangeSum;
    }

    public static void main(String[] args) {
        Integer[] arr = { 10, 5, 15, 3, 7, null, 18 };

    }

    public TreeNode buildBST(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[arr.length];
        nodes[0] = new TreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {

        }
        return nodes[0];
    }

}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

}
