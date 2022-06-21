/**
 * 根据身高重建队列

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

// 无思路


class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0)
            return people;

        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return b[0] - a[0];
                else
                    return a[1] - b[1];
            }
        });

        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }

        int[][] res = new int[people.length][people[0].length];
        for (int i = 0; i < people.length; i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }

        return res;
    }
}

// 或者用toArray
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[queue.size()][]);

    }
}

// https://leetcode.cn/problems/queue-reconstruction-by-height/solution/xian-pai-xu-zai-cha-dui-dong-hua-yan-shi-suan-fa-g/
// 一般这种数对，还涉及排序的，根据第一个元素正向排序，根据第二个元素反向排序，或者根据第一个元素反向排序，根据第二个元素正向排序，往往能够简化解题过程。
// 首先对数对进行排序，按照数对的元素 1 降序排序，按照数对的元素 2 升序排序。
// 原因是，按照第1个进行降序排序，对于每个元素，在其之前的元素的个数，就是大于等于他的元素的数量。按照第2个元素正向排序，我们希望k大的尽量在后面，减少插入list重排的操作次数。
// 一句话解释插入原理：高个子的人是看不到低个子人的
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> res = new LinkedList<>();
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });
        
        for (int i = 0; i < people.length; i++) {
            res.add(people[i][1], people[i]);
        }

        return res.toArray(new int[res.size()][]);
    }
}