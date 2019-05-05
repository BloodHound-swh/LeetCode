/**
 * 连续出现的数字

编写一个 SQL 查询，查找所有至少连续出现三次的数字。

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。

+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
 */

-- 思路
-- 查询目标：Num (As ConsecutiveNums)
-- 查询范围：Logs表
-- 查询条件：所有至少连续出现三次的数字
SELECT DISTINCT
    l1.Num AS ConsecutiveNums
FROM
    Logs AS l1, Logs AS l2, Logs AS l3
WHERE
    l1.Num = l2.Num AND l2.Num = l3.Num AND l1.Id = l2.Id - 1 AND l2.Id = l3.Id - 1;


-- 使用联结
SELECT DISTINCT l1.Num AS ConsecutiveNums FROM Logs l1
LEFT JOIN Logs l2 ON l1.Id = l2.Id - 1
LEFT JOIN Logs l3 ON l1.Id = l3.Id - 2
WHERE l1.Num = l2.Num AND l2.Num = l3.Num;