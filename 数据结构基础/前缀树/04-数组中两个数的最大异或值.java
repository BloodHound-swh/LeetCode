/**
 * 数组中两个数的最大异或值
给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。

找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。

你能在O(n)的时间解决这个问题吗？

示例:

输入: [3, 10, 5, 25, 2, 8]

输出: 28

解释: 最大的结果是 5 ^ 25 = 28.
 */

// 没看懂。。。
class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}

// 这里Trie树建立的思路是，整数在存储时是一个占据32bit的数，因此可以看成一个含32个字符的字符串，这个字符串中的每个字符只可能是0或1。
// 因此，将一个整数插入Trie树就是从它的最高位开始，根据每一位上的值进入不同的分支，直到最低位。
// 接下来，是如何找到最大的异或值，两个数异或得到一个数，这个数的值要尽量大，那么这个数的二进制表示法中，第一个1出现的位数越高这个数就越大，即置1位越高数越大。
// 所以，对于数组中的每一个数，要找到它和数组中其他数异或后得到的最大异或值，可以采用类似贪心的策略，从最高位开始，找和它在这一位相反的数。
// 如果有，那么和这个数异或就得到最大异或值，如果没有就依次往下一位找，直到找到相异的位。
// 一开始，我采用先将数组中所有的数建成一棵Trie树后再对每一个数求各自的最大异或值，然后再取最大值，建立Trie树的时间复杂度是O(32n)。
// 这里的32即Trie树的键值最大长度；Trie树的高度为32，因此查找每个数的最大异或值得时间复杂度是O(32n)，合起来是O(64n)
class Solution {
    class Trie {
        Trie[] next;

        public Trie() {
            next = new Trie[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1 || nums == null) {
            return 0;
        }
        Trie root = new Trie();
        for (int num : nums) {
            Trie node = root;
            for (int i = 31; i >= 0; i--) {
                int cur = (num >>> i) & 1;
                if (node.next[cur] == null) {
                    node.next[cur] = new Trie();
                }
                node = node.next[cur];
            }
        }

        int res = 0;
        for (int num : nums) {
            Trie node = root;
            int xor = 0;
            for (int i = 31; i >= 0; i--) {
                int cur = (num >>> i) & 1;
                if (node.next[cur ^ 1] != null) {
                    xor += (1 << i);
                    node = node.next[cur ^ 1];
                } else {
                    node = node.next[cur];
                }
            }
            res = Math.max(res, xor);
        }
        return res;
    }
}

// 优化
// 显然上面的方法有很多重复计算的部分，假设ai的最大异或值是 ai xor aj，那么aj的最大值肯定也是 aj xor ai，这里就是重复计算。
// 因此，我决定改成在建立Trie树的同时，对于正在插入的数，在已插入的数中查找能得到的最大异或值，即边建Trie边查找最大值，这样做也能得到正确的最大异或值。
class Solution {
    class Trie {
        Trie[] next;

        public Trie() {
            next = new Trie[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1 || nums == null)
            return 0;
        Trie root = new Trie();
        int result = 0;
        for (int num : nums) {
            int xor = 0;
            Trie insert = root, search = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                int rbit = bit ^ 1;
                if (insert.next[bit] == null) {
                    insert.next[bit] = new Trie();
                }
                insert = insert.next[bit];
                if (search != null) {
                    if (search.next[rbit] != null) {
                        xor += (1 << i);
                        search = search.next[rbit];
                    } else {
                        search = search.next[bit];
                    }
                }
            }
            result = Math.max(result, xor);
        }
        return result;
    }
}