Enter password: ****
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 11
Server version: 8.4.8 MySQL Community Server - GPL

Copyright (c) 2000, 2024, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (10.52 sec)

mysql> create table hotelmanagement;
ERROR 1046 (3D000): No database selected
mysql> create database hotelmanagement;
Query OK, 1 row affected (0.17 sec)

mysql> CREATE TABLE reservations (
    ->     reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    ->     guest_name VARCHAR(100),
    ->     contact_no VARCHAR(15),
    ->     room_no INT,
    ->     check_in_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ->     check_out_time TIMESTAMP
    -> );
