create database studentmanager;
use studentmanager;
create table Classroom(
id int not null auto_increment primary key,
name varchar(200) not null,
dateOfBirth date not null,
address varchar(200),
phoneNumber varchar(10) not null,
email varchar(200) not null,
classroom varchar(50)
);
insert into Classroom(name,dateOfBirth,address,phoneNumber,email,classRoom)values
('Nguyễn Văn A','2000-12-19','Hải Dương',0986886222,'xuanhieuvn@gmail.com','C0622I1'),
('Nguyễn Văn B','2000-12-19','Hà Nội',0966266656,'abc@gmail.com','C0622I1'),
('Nguyễn Văn C','2000-12-19','Thái Bình',0923987666,'defn@gmail.com','C0622I1');

select * from Classroom;