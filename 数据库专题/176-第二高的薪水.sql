/**
176. 第二高的薪水

SQL架构
Employee 表：
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id 是这个表的主键。
表的每一行包含员工的工资信息。
 

编写一个 SQL 查询，获取并返回 Employee 表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 null 。

查询结果如下例所示。

 

示例 1：

输入：
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
输出：
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
示例 2：

输入：
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
输出：
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
*/

-- 将不同的薪资按降序排序，然后使用 LIMIT 子句获得第二高的薪资。
SELECT DISTINCT
    Salary AS SecondHighestSalary
FROM
    Employee
ORDER BY Salary DESC
LIMIT 1 OFFSET 1

-- 然而，如果没有这样的第二最高工资，这个解决方案将被判断为 “错误答案”，因为本表可能只有一项记录。为了克服这个问题，我们可以将其作为临时表。
SELECT
    (
        SELECT
            DISTINCT Salary
        FROM
            Employee
        ORDER BY
            Salary DESC
        LIMIT
            1 OFFSET 1
    ) AS SecondHighestSalary;

-- 解决 “NULL” 问题的另一种方法是使用 “IFNULL” 函数，如下所示。
SELECT
    IFNULL(
        (
            SELECT
                DISTINCT Salary
            FROM
                Employee
            ORDER BY
                Salary DESC
            LIMIT
                1 OFFSET 1
        ),
        NULL
    ) AS SecondHighestSalary;

-- https://leetcode.cn/problems/second-highest-salary/solution/tu-jie-sqlmian-shi-ti-ru-he-cha-zhao-di-ngao-de-sh/
SELECT
    IFNULL(
        (
            SELECT
                DISTINCT Salary
            FROM
                Employee
            ORDER BY
                Salary DESC
            LIMIT
                1, 1
        ), NULL
    ) AS SecondHighestSalary;