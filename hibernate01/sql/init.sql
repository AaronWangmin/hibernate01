drop  database IF EXISTS shbd_hibernate;
create database shbd_hibernate;
use shbd_hibernate;
GRANT ALL ON shbd_hibernate.* to 'aaron'@'localhost' IDENTIFIED BY 'aaron123';

create table t_user(
	id int(10) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100),
	born datetime	
);
