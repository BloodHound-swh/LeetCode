/**
 * 二叉搜索树迭代器
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

 

示例：



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // 返回 3
iterator.next();    // 返回 7
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 9
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 15
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 20
iterator.hasNext(); // 返回 false
 

提示：

next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */


// 方法一，使用递归和队列，也就是中序遍历
class BSTIterator {
    Queue<Integer> q;

    public BSTIterator(TreeNode root) {
        q = new LinkedList<>();
        DFS(root, q);
    }

    public void DFS(TreeNode root, Queue<Integer> q) {
        if (root == null)
            return;
        DFS(root.left, q);
        q.offer(root.val);
        DFS(root.right, q);
    }

    public int next() {
        int res = q.poll();
        return res;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}

// 迭代，且使用的是栈
public class BSTIterator {

    Stack<TreeNode> stk;

    public BSTIterator(TreeNode root) {
        stk = new Stack<TreeNode>();
        // 先找到第一个节点，并把路径入栈
        while (root != null) {
            stk.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        // 栈为空时不再有下一个
        return !stk.isEmpty();
    }

    public int next() {
        // 栈顶是待返回元素
        TreeNode curr = stk.pop();
        int res = curr.val;
        // 如果该元素有右节点，把它的右节点及右节点的所有左边节点都压入栈中
        if (curr.right != null) {
            curr = curr.right;
            while (curr != null) {
                stk.push(curr);
                curr = curr.left;
            }
        }
        return res;
    }
}
