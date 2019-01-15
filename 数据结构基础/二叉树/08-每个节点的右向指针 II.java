/**
 * 每个节点的右向指针 II
给定一个二叉树

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

说明:

你只能使用额外常数空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
示例:

给定二叉树，

     1
   /  \
  2    3
 / \    \
4   5    7
调用你的函数后，该二叉树变为：

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
 */


// https://segmentfault.com/a/1190000003465911
// 因为我们知道肯定是左右左右的顺序，而这题中左右节点可能不存在，所有我们要用一个指针记录这一层中我们链接到了哪个节点，方便我们链接下一个节点。
class Solution {
    public void connect(TreeLinkNode root) {
        // head是上一层的节点，我们用上一层节点的next形成链表，来链接当前这层
        TreeLinkNode head = root;
        while (head != null) {
            // 记录链接到哪个节点的额外指针
            TreeLinkNode curr = new TreeLinkNode(0);
            // tmp指向该层的第一节点
            TreeLinkNode tmp = curr;
            while (head != null) {
                // 尝试链接左节点
                if (head.left != null) {
                    curr.next = head.left;
                    curr = curr.next;
                }
                // 尝试链接右节点
                if (head.right != null) {
                    curr.next = head.right;
                    curr = curr.next;
                }
                head = head.next;
            }
            // 将head移动到当前这层的的第一个，准备链接下一层
            head = tmp.next;
        }
    }
}

// 同上
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = root;
        while (head != null) {
            TreeLinkNode temp = new TreeLinkNode(0);
            TreeLinkNode curr = temp;
            while (head != null) {
                if (head.left != null) {
                    temp.next = head.left;
                    temp = temp.next;
                }
                if (head.right != null) {
                    temp.next = head.right;
                    temp = temp.next;
                }
                head = head.next;
            }
            head = curr.next;
        }
    }
}

// BFS
class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode tmp = queue.poll();
                if (tmp.left != null)
                    queue.offer(tmp.left);
                if (tmp.right != null)
                    queue.offer(tmp.right);
                if (i == size - 1) {
                    break;
                }
                tmp.next = queue.peek();
            }
        }
    }
}

// DFS，此题中情况太多，不见得是个好方法
public class Solution {
    public void connect(TreeLinkNode root) {
        helper(root);
    }

    void helper(TreeLinkNode node) {
        if (node == null)
            return;
        TreeLinkNode next = null;
        if (node.left != null) {
            if (node.right != null) {
                node.left.next = node.right;
            } else {
                next = node.next;
                while (next != null) {
                    if (next.left != null) {
                        node.left.next = next.left;
                        break;
                    } else if (next.right != null) {
                        node.left.next = next.right;
                        break;
                    }
                    next = next.next;
                }
            }
        }
        if (node.right != null) {
            next = node.next;
            while (next != null) {
                if (next.left != null) {
                    node.right.next = next.left;
                    break;
                } else if (next.right != null) {
                    node.right.next = next.right;
                    break;
                }
                next = next.next;
            }
        }
        helper(node.right);
        helper(node.left);
    }
}