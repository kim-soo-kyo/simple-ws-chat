# simple-ws-chat
test websocket project for simple chat

# spec

- jdk 11
- spring-boot 2.3.1 / jpa / lombok
- maria-db
- html / sock.js / stomp.js / jquery 
- maven

# required DB (maria-db)

1. connect DB & create table 

```
CREATE TABLE `message` (
  `message_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `room` varchar(19) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `sender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`message_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 |
```

2. set db info to application.properties 

```
spring.datasource.url=jdbc:mariadb://db_ip:db_port/db_table_name?characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=db_username
spring.datasource.password=db_password
```

# run 
start springboot & connect with browser

```
http://{server-ip}:8080/rooms.html
```
