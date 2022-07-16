/**
196. 删除重复的电子邮箱

SQL架构
表: Person

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id是该表的主键列。
该表的每一行包含一封电子邮件。电子邮件将不包含大写字母。
 

编写一个 SQL 删除语句来 删除 所有重复的电子邮件，只保留一个id最小的唯一电子邮件。

以 任意顺序 返回结果表。 （注意： 仅需要写删除语句，将自动对剩余结果进行查询）

查询结果格式如下所示。

 

 

示例 1:

输入: 
Person 表:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
输出: 
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
解释: john@example.com重复两次。我们保留最小的Id = 1。
*/

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