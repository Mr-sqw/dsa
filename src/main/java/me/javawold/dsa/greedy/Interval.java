package me.javawold.dsa.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 输入: intervals = [[[2,3],[4,5],[6,7],[8,9],[1,10]]
     * 输出: [[[1,10]]
     * 解释:
     *
     * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return new int[0][];

        //按区间的 startIndex升序、endIndex升序 排序。开始的早，结束的晚
        Arrays.sort(intervals, (int[] a, int[] b) -> {
            int compareResult = a[0] - b[0];
            if (compareResult == 0) {
                return a[1] - b[1];
            } else {
                return compareResult;
            }
        });

        //
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (start <= interval[0] && interval[0] <= end) {//interval区间与合并区间[start,end]相交，更新end，合并区间向右扩充。
                if (interval[1] > end){//合并区间向右扩充。
                    end = interval[1];
                }
            } else {//interval区间与合并区间[start,end]不相交，旧的合并区间扩充结束；更新start、end，建立新的合并区间。
                list.add(new int[]{start, end});

                start = interval[0];
                end = interval[1];
            }
        }
        //
        list.add(new int[]{start, end});

        return list.toArray(new int[list.size()][]);
    }


    public int[][] mergeV2(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return new int[0][];

        //按区间的 endIndex升序 排序
        Arrays.sort(intervals, (int[] a, int[] b) -> a[1] - b[1]);

        //
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (/*start <= interval[0] && */interval[0] <= end) {//interval区间与合并区间[start,end]相交，更新start、end，合并区间向左向右扩充。
                end = interval[1];//合并区间向右扩充。

                if (interval[0] < start) {//合并区间向左扩充。
                    start = interval[0];
                }
            } else {//interval区间与合并区间[start,end]不相交，旧的合并区间扩充结束；更新start、end，建立新的合并区间。
                list.add(new int[]{start, end});

                start = interval[0];
                end = interval[1];
            }
        }
        //
        list.add(new int[]{start, end});

        return list.toArray(new int[list.size()][]);
    }

}
