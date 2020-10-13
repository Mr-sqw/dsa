package me.javawold.dsa.tree.bst;

import me.javawold.dsa.Builder;
import me.javawold.dsa.tree.TreeNode;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class BSTProblem {

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解题方案：
     *  1.https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yi-zhang-tu-rang-ni-ming-bai-shang-xia-jie-zui-da-/
     *  2.bst的中序遍历序列是升序序列
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        return doValidBST(root, null, null);
    }

    /**
     *
     * @param root 子树的root
     * @param leftMax 子树的root的父节点的父节点
     * @param rightMin 子树的root的父节点
     * @return
     */
    public boolean doValidBST(TreeNode root, TreeNode leftMax, TreeNode rightMin) {
        if (root == null) return true;
        if (leftMax != null && root.val >= leftMax.val) return false;
        if (rightMin != null && root.val <= rightMin.val) return false;
        return doValidBST(root.left, root, rightMin) //当前节点的值是其左子树的值的上界（最大值）。左子树的值都小于父节点的值，大于...
                && doValidBST(root.right, leftMax, root)//当前节点的值是其右子树的值的下界（最小值）。右子树的值都大于于父节点的值，小于父节点的父节点的值
                ;
    }

    public boolean isValidBSTByMidOrder(TreeNode root) {
        if (root == null) return true;

        return doValidBSTByMidOrder(root, new AtomicLong(Long.MIN_VALUE));
    }

    public boolean doValidBSTByMidOrder(TreeNode root, AtomicLong last) {
        if (root == null) return true;

        if (!doValidBSTByMidOrder(root.left, last)) {
            return false;
        }

        if (root.val <= last.get()) {
            return false;
        }
        last.set(root.val);

        return doValidBSTByMidOrder(root.right, last);
    }

    public boolean isValidBSTV2(TreeNode root) {//不正确
        if (root == null) return true;
        if (root.left != null && root.val <= root.left.val) return false;
        if (root.right != null && root.val >= root.right.val) return false;
        return isValidBSTV2(root.left) && isValidBSTV2(root.right);
    }

    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * 倒序中序遍历
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        AtomicInteger atomicInteger = new AtomicInteger(k);
        doGetKthLargest(root, atomicInteger, new AtomicBoolean(false));
        return atomicInteger.get();
    }

    /**
     * 倒序中序遍历
     *
     * @param root
     * @param k
     * @param found
     */
    private void doGetKthLargest(TreeNode root, AtomicInteger k, AtomicBoolean found) {
        if (root == null) return;

        //1.先右子树
        doGetKthLargest(root.right, k, found);

        //2.再根节点
        if (found.get()) return;
        if (k.decrementAndGet() == 0) {
            k.set(root.val);
            found.set(true);
            return;
        }

        //3.最后左子树
        doGetKthLargest(root.left, k, found);
    }

    /**
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     *
     *  
     *
     * 例如, 
     *
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 和 插入的值: 5
     * 你可以返回这个二叉搜索树:
     *
     *          4
     *        /   \
     *       2     7
     *      / \   /
     *     1   3 5
     * 或者这个树也是有效的:
     *
     *          5
     *        /   \
     *       2     7
     *      / \
     *     1   3
     *          \
     *           4
     *  
     *
     * 提示：
     *
     * 给定的树上的节点数介于 0 和 10^4 之间
     * 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
     * -10^8 <= val <= 10^8
     * 新值和原始二叉搜索树中的任意节点值都不同
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {//一路搜索过来，val所在位置
            return new TreeNode(val);//返回新节点
        }

        if (root.val < val) {//从右子树搜索
            root.right = insertIntoBST(root.right, val);
        } else if (root.val > val) {//从左子树搜索
            root.left = insertIntoBST(root.left, val);
        }
        return root;//原样接入，以前是某个节点的左孩子还是左孩子，以前是某个节点的右孩子还是右孩子
    }

    /**
     * 530. 二叉搜索树的最小绝对差
给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

 

示例：

输入：

   1
    \
     3
    /
   2

输出：
1

解释：
最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     *
     * @param root
     * @return
     * @author suqianwen 2020年10月12日
     */
	public int getMinimumDifference(TreeNode root) {
		if (root == null) {
			return 0;
		}

		AtomicReference<TreeNode> last = new AtomicReference<>();
		AtomicInteger minDiff = new AtomicInteger(Integer.MAX_VALUE);
		doGetMinimumDifference(root, last, minDiff);
		return minDiff.get();
	}

	private void doGetMinimumDifference(TreeNode root, AtomicReference<TreeNode> last, AtomicInteger minDiff) {
		if (root == null) {
			return;
		}

		// 左
		doGetMinimumDifference(root.left, last, minDiff);
		// 根
		if (last.get() != null) {
			int diff = root.val - last.get().val;
			if (diff < minDiff.get()) {
				minDiff.set(diff);
			}
		}
		last.set(root);
		// 右
		doGetMinimumDifference(root.right, last, minDiff);
	}

	void doGetMaximumDifference(TreeNode root, AtomicInteger min, AtomicInteger max) {
		if (root == null) {
			return;
		}

		//
		if (root.val < min.get()) {
			min.set(root.val);
		}
		if (root.val > max.get()) {
			max.set(root.val);
		}
		//
		doGetMaximumDifference(root.left, min, max);
		//
		doGetMaximumDifference(root.right, min, max);
	}

	public static void main(String[] args) {
		Integer[] arr = { 1, null, 3, 2 };
		TreeNode root = Builder.buildFST(arr);
		new BSTProblem().getMinimumDifference(root);
	}

}
