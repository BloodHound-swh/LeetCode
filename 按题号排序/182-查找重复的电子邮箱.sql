/**
 * 查找重复的电子邮箱

编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。

示例：

+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
根据以上输入，你的查询应返回以下结果：

+---------+
| Email   |
+---------+
| a@b.com |
+---------+
说明：所有电子邮箱都是小写字母。
 */

-- HAVING支持WHERE的所有操作符，区别在于HAVING过滤分组，WHERE过滤行
SELECT Email
FROM Person
GROUP BY Email
HAVING COUNT(Email) > 1;


-- 方法二
-- 重复的电子邮箱存在多次。要计算每封电子邮件的存在次数，我们可以使用以下代码。

select Email, count(Email) as num
from Person
group by Email;

-- | Email   | num |
-- |---------|-----|
-- | a@b.com | 2   |
-- | c@d.com | 1   |

-- 以此作为临时表，我们可以得到下面的解决方案。
select Email from
(
  select Email, count(Email) as num
  from Person
  group by Email
) as statistic
where num > 1;

