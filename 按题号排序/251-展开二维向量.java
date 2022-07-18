/**
 * 251. 展开二维向量
请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。

 

示例：

Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // 返回 1
iterator.next(); // 返回 2
iterator.next(); // 返回 3
iterator.hasNext(); // 返回 true
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 4
iterator.hasNext(); // 返回 false
 

注意：

请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 

进阶：尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
 */

// 注意这里使用到了moveForward()函数来通过m,n的值辅助判断hasNext()
class Vector2D {

    int m;
    int n;
    int[][] vec;

    public Vector2D(int[][] vec) {
        this.vec = vec;
    }

    private void moveForward() {
        // 注意这里要用while, 因为后边的向量本身也可能为空
        while (m < vec.length && n == vec[m].length) {
            n = 0;
            m++;
        }
    }
    
    public int next() {
        if (!hasNext()) {
            // 其实也可以直接抛异常
            return -1;
        }
        return vec[m][n++];
    }
    
    public boolean hasNext() {
        moveForward();
        return m < vec.length;
    }
}

// 官方题解
import java.util.NoSuchElementException;

class Vector2D {
    
    private int[][] vector;
    private int inner = 0;
    private int outer = 0;
    
    public Vector2D(int[][] v) {
        vector = v;
    }

    private void advanceToNext() {
        while (outer < vector.length && inner == vector[outer].length) {
            inner = 0;
            outer++;
        }
    }
    
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return vector[outer][inner++];
    }
    
    public boolean hasNext() {
        advanceToNext();
        return outer < vector.length;
    }
}

// 官方题解，但是这样可能面试官不会满意，因为使用到了别的数据结构了
import java.util.NoSuchElementException;

class Vector2D {
    
    private List<Integer> nums = new ArrayList<>();

    private int position = 0;
    
    public Vector2D(int[][] v) {
        for (int[] innerVector : v) {
            for (int num : innerVector) {
                nums.add(num);
            }
        }
    }
    
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = nums.get(position);
        position++;
        return result;
    }
    
    public boolean hasNext() {
        return position < nums.size();
    }
}