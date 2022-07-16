/**
183. 从不订购的客户

SQL架构
某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

Customers 表：

+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Orders 表：

+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
例如给定上述表格，你的查询应返回：

+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
*/

-- 使用子查询和NOT IN
SELECT
    Customers.Name AS Customers
FROM
    Customers
WHERE
    Customers.Id NOT IN (
        SELECT
            CustomerId
        FROM
            Orders
    )

-- 使用left join
-- https://mazhuang.org/2017/09/11/joins-in-sql/
-- 与inner join不同的是left join在不匹配时，对应的值会为NULL也会被select出来
SELECT
    Customers.Name AS Customers
FROM
    Customers
    LEFT JOIN Orders ON Customers.Id = Orders.CustomerId
WHERE
    Orders.CustomerId IS NULL;