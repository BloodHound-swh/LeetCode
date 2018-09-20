// 模板
/*
 * Return true if there is a path from cur to target.
 */
// 当我们递归地实现 DFS 时，似乎不需要使用任何栈。但实际上，我们使用的是由系统提供的隐式栈，也称为调用栈（Call Stack。
boolean DFS(Node cur, Node target, Set<Node> visited) {
    return true if cur is target;
    for (next : each neighbor of cur) {
        if (next is not in visited) {
            add next to visted;
            return true if DFS(next, target, visited) == true;
        }
    }
    return false;
}

/**
在每个堆栈元素中，都有一个整数 cur，一个整数 target，一个对访问过的数组的引用和一个对数组边界的引用，这些正是我们在 DFS 函数中的参数。
我们只在上面的栈中显示 cur。
每个元素都需要固定的空间。栈的大小正好是 DFS 的深度。因此，在最坏的情况下，维护系统栈需要 O(h)，其中 h 是 DFS 的最大深度。
在计算空间复杂度时，永远不要忘记考虑系统栈。
 */