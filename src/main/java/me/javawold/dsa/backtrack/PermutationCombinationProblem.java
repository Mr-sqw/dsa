package me.javawold.dsa.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationCombinationProblem {

    //电话号码盘
    public static final char[][] DIGIT_LETTER_DIAL = {
            {'_'},//0
            {'!', '@', '#'},//1
            {'a', 'b', 'c'},//2
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     *
     *
     * 示例:
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0){
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        char[] chars = digits.toCharArray();
        char[][] letterMatrix = new char[chars.length][];
        for (int i = 0; i < chars.length; i++) {
            letterMatrix[i] = DIGIT_LETTER_DIAL[chars[i]];
        }

        return  result;
    }

}
