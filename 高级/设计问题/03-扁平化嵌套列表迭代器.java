/**
 * 扁平化嵌套列表迭代器
给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

列表中的项或者为一个整数，或者是另一个列表。

示例 1:

输入: [[1,1],2,[1,1]]
输出: [1,1,2,1,1]
解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
示例 2:

输入: [1,[4,[6]]]
输出: [1,4,6]
解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。
 */



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 /**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) 
 *      v[f()] = i.next();
 */

// 注意迭代器中next() 和 hasNext()的区别
// hasNext():判断当前元素是否存在，并没有指向的移动
// next():返回当前元素，并指向下一个元素

// 先将所有的nestedList中的元素使用pushListToStack压入堆中(为了顺序符合题意，需要用temp作为中继)
// 然后从stack中的堆顶开始，如果是single Integer,则直接输出
// 否则，将堆顶的list再次pop出来，重新使用pushListToStack，直到堆顶是single Integer
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    private void pushListToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> temp = new Stack<>();
        for (NestedInteger nested : nestedList) {
            temp.push(nested);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        pushListToStack(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushListToStack(stack.pop().getList()); // 把堆顶的nested list 重新push到stack中，直到堆顶是single Integer
        }

        return !stack.isEmpty();
    }
}

// 相当于使用了递归，十分巧妙
public class NestedIterator implements Iterator<Integer> {

    private ArrayList<Integer> lst = new ArrayList<>();
    private Iterator<Integer> it;

    private void flat(List<NestedInteger> nestedList) {
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                lst.add(nest.getInteger());
            } else {
                flat(nest.getList()); // 迭代使用flat函数，使得lst中都是single Integer
            }
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        flat(nestedList);
        it = lst.iterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}
