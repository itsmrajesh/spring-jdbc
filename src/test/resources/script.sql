DROP TABLE IF EXISTS Course;
create table Course(cid varchar(10) primary key, title varchar(100),duration int(10),status varchar(50));
insert into Course(cid,title,duration,status) values ('101','Core Java',40,'COMPLETED');