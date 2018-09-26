/*
 * 用队列实现栈
使用队列实现栈的下列操作：

push(x) -- 元素 x 入栈
pop() -- 移除栈顶元素
top() -- 获取栈顶元素
empty() -- 返回栈是否为空
注意:

你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 */

// 当push时，我们将数字offer进非空的队列就行了。
// 当pop时，因为要拿的是队列最后一个数，我们先将它前面的数offer进另一个队列，然后单独拿出最后一个数，并且不再offer进另一个队列
// 这样，另一个队列就少了最后一个数
// 而我们也拿到了最后一个数，而本来的队列就变成空了，等待下次的pop操作来填满。
// top操作和pop一样，区别在于我们拿到最后一个数后，还要再把它offer进另一个队列中。
class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int size;

    public MyStack() {
        this.queue1 = new LinkedList<Integer>();
        this.queue2 = new LinkedList<Integer>();
        this.size = 0;
    }

    // Push element x onto stack.
    public void push(int x) {
        if (queue1.isEmpty()) {
            queue2.offer(x);
        } else {
            queue1.offer(x);
        }
        size++;
    }

    // Removes the element on top of the stack.
    public int pop() {
        if (size == 0) {
            return -1;
        }
        int res = 0;
        // 将前面的数都offer进另一个队列，然后拿出最后一个数
        if (queue1.isEmpty()) {
            for (int i = 0; i < size - 1; i++) {
                queue1.offer(queue2.poll());
            }
            res = queue2.poll();
        } else {
            for (int i = 0; i < size - 1; i++) {
                queue2.offer(queue1.poll());
            }
            res = queue1.poll();
        }
        size--;
        return res;
    }

    // Get the top element.
    public int top() {
        if (size == 0) {
            return -1;
        }
        // 先执行pop
        int top = pop();
        // 然后将pop出来的数再塞回队列就行了
        if (queue1.isEmpty()) {
            queue2.offer(top);
        } else {
            queue1.offer(top);
        }
        // 注意size也要加1
        size++;
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return size == 0;
    }
}

// 使用tempQueue
class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> myQueue = new LinkedList();

    public MyStack() {
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int n = myQueue.size();
        Queue<Integer> tempQueue = new LinkedList();
        for (int i = 0; i < n; i++) {
            tempQueue.add(myQueue.poll());
        }
        myQueue.add(x);
        for (int i = 0; i < n; i++) {
            myQueue.add(tempQueue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return (int) myQueue.poll();
    }

    /** Get the top element. */
    public int top() {
        return (int) myQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return myQueue.isEmpty();
    }
}

// 其实可以只用一个queue连temp都可以不用了，每次push一个元素，就将前面的元素poll()出来，重新push()回去
class MyStack {
    private Queue<Integer> q;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.poll();
    }

    /** Get the top element. */
    public int top() {
        return q.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}