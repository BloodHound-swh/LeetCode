/**
 * 每个节点的右向指针
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
你可以假设它是一个完美二叉树（即所有叶子节点都在同一层，每个父节点都有两个子节点）。
示例:

给定完美二叉树，

     1
   /  \
  2    3
 / \  / \
4  5  6  7
调用你的函数后，该完美二叉树变为：

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
 */



// 递归
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        //左子节点的next是右子节点
        if(root.left != null) root.left.next = root.right;
        if(root.right != null){
        //右子节点的next是父节点的next的左子节点
            root.right.next = root.next == null? null:root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
}

// 先将每个节点的左右孩子连接，然后连接左节点的右孩子，和右节点的左孩子
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.next != null && root.right != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
}

// 相同的思路，换一种写法
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        root.left.next = root.right;
        dfs(root.left);
        dfs(root.right);
    }

    public void dfs(TreeLinkNode node) {
        if (node == null || node.left == null || node.right == null) {
            return;
        }
        node.left.next = node.right;
        if (node.next != null)
            node.right.next = node.next.left;
        dfs(node.left);
        dfs(node.right);
    }
}

// 写法三
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root != null)
            Solve(root, null);
    }

    public void Solve(TreeLinkNode root, TreeLinkNode next) {
        if (next != null) {
            root.next = next;
            if (root.left != null) {
                Solve(root.left, root.right);
                Solve(root.right, next.left);
            }
        } else {
            root.next = null;
            if (root.left != null) {
                Solve(root.left, root.right);
                Solve(root.right, null);
            }
        }
    }
}