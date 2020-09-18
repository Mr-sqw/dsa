package me.javawold.dsa.greedy;

import java.util.Arrays;

/**
 * 区间问题
 */
public class Interval {

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * <p>
     * 注意:
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * <p>
     * 示例 1:
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * <p>
     * 示例 2:
     * 输入: [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * <p>
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * <p>
     * 示例 3:
     * 输入: [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        return intervals.length - intervalSchedule(intervals);
    }

    /**
     * 最大不重叠区间数量
     *
     * @param intervals
     * @return
     */
    public int intervalSchedule(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        //按区间的endIndex升序排序
        Arrays.sort(intervals, (int[] a, int[] b) -> a[1] - b[1]);

        int count = 1;
        int candidateIntervalEndIndex = intervals[0][1];//1.取endIndex最小的区间为第一个候选区间
        for (int[] interval : intervals) {
            if (interval[0] >= candidateIntervalEndIndex) {//新区间的startIndex>=候选区间的endIndex：新区间即为新的不重叠候选区间
                count++;
                candidateIntervalEndIndex = interval[1];
            } else {
                //去掉和当前所选候选区间重叠的区间，下一个endIndex最小的区间即为候选区间，...这样即可求得最大不重叠区间数量
            }
        }
        return count;
    }

}
