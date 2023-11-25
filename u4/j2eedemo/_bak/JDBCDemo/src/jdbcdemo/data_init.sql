create database comps368demo;

use comps368demo;

create table students(
	stdId 		int not null primary key auto_increment,
	stdName 	varchar(100) not null,
	gender		char(1) not null,
	addr		varchar(255) null,
	dateOfBirth	Date null,
	constraint chk_student_gender check( gender in ('M', 'F'))
);

insert into students (stdName, gender, dateOfBirth) values ('Cyrus', 'M', '1983-08-22');
insert into students (stdName, gender, addr) values ('Mandy', 'F', 'Tsz Wan Shan');
insert into students (stdName, gender) values ('Ruby', 'F');