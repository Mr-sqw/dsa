package me.javawold.dsa.stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {

    /**
     * 原始栈：
     */
    private int[] elements;

    /**
     * 最小栈：最小栈的栈顶始终保存原始栈中的最小元素
     */
    private int[] minElements;

    private int size;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        elements = new int[10];
        minElements = new int[10];

        size = 0;
    }

    public void push(int x) {
        if (size >= elements.length) {
            int[] newElements = new int[elements.length * 2];
            int[] newMinElements = new int[minElements.length * 2];

            System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);
            System.arraycopy(this.minElements, 0, newMinElements, 0, this.minElements.length);

            this.elements = newElements;
            this.minElements = newMinElements;
        }

        int oldSize = size;
        elements[size++] = x;
        /*最小栈的栈顶始终保存原始栈中的最小元素*/
        if (oldSize == 0) {
            minElements[size - 1] = x;
        } else/* if (oldSize > 0)*/ {
            if (x > minElements[oldSize - 1]) {//
                minElements[size - 1] = minElements[oldSize - 1];//
            } else {
                minElements[size - 1] = x;
            }
        }
    }

    public void pop() {
        if (size == 0) {
            return;
        }
        size--;
    }

    public int top() {
        if (size == 0) {
            return 0;
        }
        return elements[size - 1];
    }

    public int getMin() {
        if (size == 0) {
            return 0;
        }
        return minElements[size - 1];
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

