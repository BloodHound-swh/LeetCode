// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "";
        int n = 10000;
        for (int i = 0; i < n; i++) {
            s += "hello";
        }
    }
}
/**
 * 请注意对于 Java 来说字符串连接有多慢？ 另一方面，在 C++ 中没有明显的性能影响。

在 Java 中，由于字符串是不可变的，因此在连接时首先为新字符串分配足够的空间，复制旧字符串中的内容并附加到新字符串。

因此，总时间复杂度将是：

   5 + 5 × 2 + 5 × 3 + … + 5 × n
= 5 × (1 + 2 + 3 + … + n)
= 5 × n × (n + 1) / 2,

也就是 O(n^2)。
 */

// 如果你希望你的字符串是可变的，这里有一些替代方案：
// 1. 如果你确实希望你的字符串是可变的，则可以将其转换为字符数组。
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "Hello World";
        char[] str = s.toCharArray();
        str[5] = ',';
        System.out.println(str);
    }
}

// 2. 如果你经常必须连接字符串，最好使用一些其他的数据结构，如 StringBuilder 。 以下代码以 O(n) 的复杂度运行。
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int n = 10000;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        String s = str.toString();
    }
}

