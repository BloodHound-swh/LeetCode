/**
 * 两数相加

给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
 */

// 未看答案版
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        ListNode dummy = p;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + carry;
            carry = tmp / 10;
            tmp = tmp % 10;
            ListNode node = new ListNode(tmp);
            p.next = node;
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int tmp = l1.val + carry;
            carry = tmp / 10;
            tmp = tmp % 10;
            ListNode node = new ListNode(tmp);
            p.next = node;
            p = p.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int tmp = l2.val + carry;
            carry = tmp / 10;
            tmp = tmp % 10;
            ListNode node = new ListNode(tmp);
            p.next = node;
            p = p.next;
            l2 = l2.next;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            p.next = node;
        }

        return dummy.next;
    }
}

// 答案一
// 分三块，第一：位数重复部分 第二：某一list多出的部分 第三：需要新建新的最高位
// 使用carry = sum / 10表示进位，sum % 10表示本位留下的数字
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int dig = l1.val + l2.val + carry;
            int val = dig % 10;
            carry = dig / 10;
            ListNode newnode = new ListNode(val);
            current.next = newnode;
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            current.next = new ListNode(val);
            current = current.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            current.next = new ListNode(val);
            current = current.next;
            l2 = l2.next;
        }

        if (carry != 0)
            current.next = new ListNode(carry);
        return dummy.next;
    }
}

// 代码优化
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int carry = sum / 10;
        ListNode result = new ListNode(sum % 10);
        l1 = l1.next;
        l2 = l2.next;
        ListNode cache = result;
        while (l1 != null || l2 != null || carry > 0) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            cache.next = new ListNode(sum % 10);
            carry = sum / 10;
            cache = cache.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return result;
    }
}

// 使用表达式也很简洁
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while ((l1 != null) || (l2 != null)) {
            // 要注意两个链表的长度不同的情况，当链表为null时，设置为零
            int d1 = (l1 == null ? 0 : l1.val);
            int d2 = (l2 == null ? 0 : l2.val);
            int sum = d1 + d2 + carry;
            // carry=sum/10;
            carry = sum >= 10 ? 1 : 0;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            // null无法链接next
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry > 0)
            cur.next = new ListNode(carry);
        return dummy.next;
    }
}
