/**
 * 最小栈
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
 */

//使用两个栈，一个最小栈，一个顺序栈
class MinStack {

    /** initialize your data structure here. */
    private Stack<Integer> Stack;
    private Stack<Integer> minStack;
    public MinStack() {
        Stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        Stack.push(x);
        if(!minStack.isEmpty()){
            if(minStack.peek() >= x)
                minStack.push(x);
        }
        else{
            minStack.push(x);
        }
    }
    
    public void pop() {
        if(Stack.peek().equals(minStack.peek()) ) 
            minStack.pop();
        Stack.pop();
    }
    
    public int top() {
        return Stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

//代码优化，注意注释。对于peek()函数的返回值并不是int类型，而是一个Object类型，这是一个基本的对象类型，如果我们直接用==来比较的话，那么肯定不会返回true
//因为是两个不同的对象，所以我们一定要先将一个转为int型，然后再和另一个进行比较，这样才能得到我们想要的答案
class MinStack {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    
    /** initialize your data structure here. */
    public MinStack() {}
    
    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty() || s2.peek() >= x) s2.push(x);
    }
    
    public void pop() {
        // Cannot write like the following:
        // if (s2.peek() == s1.peek()) s2.pop();
        // s1.pop();
        int x = s1.pop();
        if (s2.peek() == x) s2.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}


//粗糙版
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));//不停的push，也不停的pop
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */