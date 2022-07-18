/**
1667. 修复表中的名字

SQL架构
表： Users

+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| name           | varchar |
+----------------+---------+
user_id 是该表的主键。
该表包含用户的 ID 和名字。名字仅由小写和大写字符组成。
 

编写一个 SQL 查询来修复名字，使得只有第一个字符是大写的，其余都是小写的。

返回按 user_id 排序的结果表。

查询结果格式示例如下。

 

示例 1：

输入：
Users table:
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | aLice |
| 2       | bOB   |
+---------+-------+
输出：
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | Alice |
| 2       | Bob   |
+---------+-------+
*/

-- CONCAT 可以将多个字符串拼接在一起。
-- LEFT(str, length), 从左开始截取字符串，length 是截取的长度。
-- UPPER(str) 将字符串中所有字符转为大写
-- LOWER(str) 将字符串中所有字符转为小写
-- SUBSTRING(str, begin, end), SUBSTRING(name, 2) 从第二个截取到末尾，注意并不是下标，就是第二个
SELECT
    user_id,
    CONCAT(UPPER(LEFT(name, 1)), LOWER(SUBSTRING(name, 2))) AS name
FROM
    Users
ORDER BY
    user_id;