/**
 * 用栈实现队列
使用栈实现队列的下列操作：

push(x) -- 将一个元素放入队列的尾部。
pop() -- 从队列首部移除元素。
peek() -- 返回队列首部的元素。
empty() -- 返回队列是否为空。
示例:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
说明:

你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */


// 使用两个stack，一个放处理前的数据，一个放处理后的数据
// 当s2为空的时候，就把s1里的数push到s2中，相当于倒序处理变成先进先出了
class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!s2.isEmpty()) {
            return s2.pop();
        } else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }

    /** Get the front element. */
    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        } else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

// 在push的时候就把未处理的suport中的数据线push到处理后的stack中
class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> suport;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        suport = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
        } else {
            while (!stack.isEmpty()) {
                suport.push(stack.pop());
            }
            stack.push(x);
            while (!suport.isEmpty()) {
                stack.push(suport.pop());
            }
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}