public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return people;
        }

        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }

        int[][] ret = new int[people.length][people[0].length];
        for (int i = 0; i < ret.length; i++) {
            ret[i][0] = list.get(i)[0];
            ret[i][1] = list.get(i)[1];
        }

        return ret;
    }
}