/**
行程和用户

Trips 表中存所有出租车的行程信息。每段行程有唯一键 Id，Client_Id 和 Driver_Id 是 Users 表中 Users_Id 的外键。Status 是枚举类型，枚举成员为 (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’)。

+----+-----------+-----------+---------+--------------------+----------+
| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
+----+-----------+-----------+---------+--------------------+----------+
| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
| 9  |     3     |    10     |    12   |     completed      |2013-10-03| 
| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
+----+-----------+-----------+---------+--------------------+----------+
Users 表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（‘client’, ‘driver’, ‘partner’）的枚举类型。

+----------+--------+--------+
| Users_Id | Banned |  Role  |
+----------+--------+--------+
|    1     |   No   | client |
|    2     |   Yes  | client |
|    3     |   No   | client |
|    4     |   No   | client |
|    10    |   No   | driver |
|    11    |   No   | driver |
|    12    |   No   | driver |
|    13    |   No   | driver |
+----------+--------+--------+
写一段 SQL 语句查出 2013年10月1日 至 2013年10月3日 期间非禁止用户的取消率。基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。

+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+
*/

-- CASE expression
--     WHEN condition1 THEN result1
--     WHEN condition2 THEN result2
--    ...
--     WHEN conditionN THEN resultN
--     ELSE result
-- END

-- 这道题给了我们一个Trips表里面有一些Id和状态，还有请求时间，然后还有一个Users表，里面有顾客和司机的信息，然后有该顾客和司机有没有被Ban的信息
-- 让我们返回一个结果看某个时间段内由没有被ban的用户提出的取消率是多少，注意，由司机提出的取消请求也应计算进去
-- 我们用Case When ... Then ... Else ... End关键字来做，我们用cancelled%来表示开头是cancelled的所有项，这样就包括了driver和client，
-- 然后分母是所有项，限制条件里限定了时间段，然后是没有被ban的，由于结果需要保留两位小数，所以我们用Round关键字且给定参数2即可
SELECT 
    Request_at AS Day,
    ROUND(
        SUM(
            CASE WHEN Status = 'completed' THEN 0
            ELSE 1
            END
        ) / count(*), 2)
    AS 'Cancellation Rate'
FROM 
    Trips INNER JOIN Users
ON
    Trips.Client_Id = Users.Users_Id AND Users.Banned = 'NO'
WHERE
    Request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY Request_at;
