/**
 * 字符串有它自己的比较函数（我们将在下面的代码中向你展示比较函数的用法）。

然而，存在这样一个问题：

我们可以用 “==” 来比较两个字符串吗？

这取决于下面这个问题的答案：

我们使用的语言是否支持运算符重载？

如果答案是 yes （例如 C++）。我们可以使用 “==” 来比较两个字符串。
如果答案是 no （例如 Java），我们可能无法使用 “==” 来比较两个字符串。当我们使用  “==” 时，它实际上会比较这两个对象是否是同一个对象。

 */

 // "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // initialize
        String s1 = "Hello World";
        System.out.println("s1 is \"" + s1 + "\"");
        String s2 = s1;
        System.out.println("s2 is another reference to s1.");
        String s3 = new String(s1);
        System.out.println("s3 is a copy of s1.");
        // compare using '=='
        System.out.println("Compared by '==':");
        // true since string is immutable and s1 is binded to "Hello World"
        System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
        // true since s1 and s2 is the reference of the same object
        System.out.println("s1 and s2: " + (s1 == s2));
        // false since s3 is refered to another new object
        System.out.println("s1 and s3: " + (s1 == s3));
        // compare using 'equals'
        System.out.println("Compared by 'equals':");
        System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
        System.out.println("s1 and s2: " + s1.equals(s2));
        System.out.println("s1 and s3: " + s1.equals(s3));
        // compare using 'compareTo'
        System.out.println("Compared by 'compareTo':");
        System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
        System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
        System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));
    }
}

/**
 * 是否可变
不可变意味着一旦字符串被初始化，你就无法改变它的内容。

在某些语言（如 C ++）中，字符串是可变的。 也就是说，你可以像在数组中那样修改字符串。
在其他一些语言（如  Java）中，字符串是不可变的。 此特性将带来一些问题。 我们将在下一篇文章中阐明问题和解决方案。
你可以通过测试修改操作来确定你喜欢的语言中的字符串是否可变。这里有一个示
 */

 // "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello World";
        s1[5] = ',';
        System.out.println(s1);
    }
}

/**
 * 额外操作
 

与数组相比，我们可以对字符串执行一些额外的操作。这里有一些例子：
 */
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello World";
        // 1. concatenate
        s1 += "!";
        System.out.println(s1);
        // 2. find
        System.out.println("The position of first 'o' is: " + s1.indexOf('o'));
        System.out.println("The position of last 'o' is: " + s1.lastIndexOf('o'));
        // 3. get substring
        System.out.println(s1.substring(6, 11));
    }
}