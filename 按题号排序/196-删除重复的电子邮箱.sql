/**
 *删除重复的电子邮箱

编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id 是这个表的主键。
例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
 */

-- 使用自联结
DELETE p1
FROM Person AS p1, Person As p2
WHERE p1.Email = p2.Email AND p1.Id > p2.Id;

-- 使用DELETE结合JION
-- https://dev.mysql.com/doc/refman/8.0/en/delete.html
-- https://leetcode.cn/problems/delete-duplicate-emails/solution/guan-fang-de-si-lu-wo-bu-quan-liao-mei-yi-bu-kan-d/
DELETE p1
From
    Person AS p1
    INNER JOIN Person AS p2 ON p1.email = p2.email
    AND p1.id > p2.id;

-- 使用WHERE自联结
DELETE p1
From
    Person AS p1,
    Person AS p2
WHERE
    p1.email = p2.email
    AND p1.id > p2.id;