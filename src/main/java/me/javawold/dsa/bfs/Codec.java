package me.javawold.dsa.bfs;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import me.javawold.dsa.tree.TreeNode;

import java.util.*;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Codec {

    // Encodes a tree to a single string.

    /**
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();

            if (node != null) {
                sb.append(node.val).append(",");

                //每一个非null节点都会追加子节点
                queue.addLast(node.left);
                queue.addLast(node.right);
            } else {//null节点不会追加子节点
                sb.append("null,");
            }
        }

        return sb.substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.

    /**
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        //
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode parent = queue.removeFirst();

            /*左子节点*/
            if (!"null".equals(arr[index])) {//左子节点不是null节点，会追加子节点
                TreeNode left = new TreeNode(Integer.valueOf(arr[index]));

                parent.left = left;

                queue.addLast(left);
            }//null节点不会追加子节点
            index++;

            /*右子节点*/
            if (!"null".equals(arr[index])) {//右子节点不是null节点，会追加子节点
                TreeNode right = new TreeNode(Integer.valueOf(arr[index]));

                parent.right = right;

                queue.addLast(right);
            }//null节点不会追加子节点
            index++;
        }

        return root;
    }


    public TreeNode deserializeV22(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        Map<Integer, TreeNode> map = new HashMap<>();
        map.put(0, root);
        //
        for (int index = 1; index < arr.length; index++) {
            if (!"null".equals(arr[index])) {
                TreeNode node = new TreeNode(Integer.valueOf(arr[index]));
                map.put(index, node);

                int parentIndex = (index - 1) / 2;
                TreeNode parent = map.get(parentIndex);
                if (index % 2 == 1) {//左子节点
                    parent.left = node;
                } else {//右子节点
                    parent.right = node;
                }
            }
        }

        return root;
    }

    /**
     *
     * @param root
     * @return 满二叉树
     */
    public String serializeV2(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNode dummy = new TreeNode();//虚节点(dummy)：null节点
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isAllDummy = true;//下一层是否都是虚节点(dummy)
            for (int i = 0; i < size; i++) {//当前层的所有节点
                TreeNode node = queue.removeFirst();

                if (node != dummy) {
                    sb.append(node.val).append(",");
                } else {
                    sb.append("null,");
                }

                //向队列中加入当前层节点的子节点，构成下一层节点。
                if (node.left == null) {
                    queue.addLast(dummy);
                } else {
                    queue.addLast(node.left);
                    isAllDummy = false;
                }
                //
                if (node.right == null) {
                    queue.addLast(dummy);
                } else {
                    queue.addLast(node.right);
                    isAllDummy = false;
                }
            }

            if (isAllDummy) {//下一层都是虚节点(dummy)，退出
                break;
            }
        }

        return sb.substring(0, sb.length() - 1);
    }

    /**
     *
     * @param data 满二叉树
     * @return
     */
    public TreeNode deserializeV2(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        Deque<TreeNode> queue = new ArrayDeque<>();//存储不为null的节点
        queue.addLast(root);
        int index = 1;
        TreeNode dummy = new TreeNode();
        while (!queue.isEmpty() && index < arr.length) {
            int size = queue.size();
            boolean isAllDummy = true;//下一层是否都是虚节点(dummy)
            for (int i = 0; i < size; i++) {//当前层的所有节点
                TreeNode parent = queue.removeFirst();

                if (parent != dummy) {//当前层节点不是虚节点
                    /*左子节点*/
                    if ("null".equals(arr[index])) {//左子节点是虚节点
                        queue.addLast(dummy);
                    } else {//左子节点不是虚节点
                        TreeNode left = new TreeNode(Integer.valueOf(arr[index]));

                        parent.left = left;

                        queue.addLast(left);
                        isAllDummy = false;
                    }
                    index++;

                    /*右子节点*/
                    if ("null".equals(arr[index])) {//右子节点是虚节点
                        queue.addLast(dummy);
                    } else {//右子节点不是虚节点
                        TreeNode right = new TreeNode(Integer.valueOf(arr[index]));

                        parent.right = right;

                        queue.addLast(right);
                        isAllDummy = false;
                    }
                    index++;
                } else {//当前层节点是虚节点，则下一层也必然是虚节点
                    queue.addLast(dummy);
                    queue.addLast(dummy);
                    index += 2;
                }
            }

            if (isAllDummy) {//下一层都是虚节点(dummy)，退出
                break;
            }
        }

        return root;
    }

    public static void main(String[] args){
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("1,2,3,null,null,4,5");
        System.out.println(root.val);
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
