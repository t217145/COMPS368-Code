/*
#JavaDB version
create schema comps368demo;

set schema comps368demo;

create table Customer(
    custId  int not null primary key generated always as identity,
    cusName varchar(255) not null,
    gender 	char(1) not null,
    addr 	varchar(255) not null default '',
    dob 	date,
    constraint chk_Customer_gender check (gender in ('M', 'F'))
);

create table Account(
    acctId  int not null primary key generated always as identity,
    custId 	int not null,
    balance decimal(12,2) not null default 0,
    constraint fk_Account_custId_Customer foreign key (custId) references Customer (custId),
    constraint chk_Account_balance check (balance >= 0)
);
*/

/*
#MySQL version
create database comps368demo;
use comps368demo;
create table Customer(
    custId  int not null primary key auto_increment,
    cusName varchar(255) not null,
    gender 	char(1) not null,
    addr 	varchar(255) not null default '',
    dob 	date,
    constraint chk_Customer_gender check (gender in ('M', 'F'))
);

create table Account(
    acctId  int not null primary key auto_increment,
    custId 	int not null,
    balance decimal(12,2) not null default 0,
    constraint fk_Account_custId_Customer foreign key (custId) references Customer (custId),
    constraint chk_Account_balance check (balance >= 0)
);
*/

insert into Customer (cusName, gender, dob) values ('Cyrus Cheng', 'M', '1983-08-22');
insert into Customer (cusName, gender, addr) values ('Mandy Ko', 'F', 'Tsz Wan Shan');

insert into Account (custId, balance)
select c.custId, 123.45
from Customer c
where c.cusName = 'Cyrus Cheng';

insert into Account (custId, balance)
select c.custId, 100.00
from Customer c
where c.cusName = 'Mandy Ko';

insert into Account (custId, balance)
select c.custId, 12345.67
from Customer c
where c.cusName = 'Mandy Ko';

select 
	c.custId, c.cusName, c.gender, c.addr, c.dob,
	a.acctId, a.balance
from Customer c, Account a
where c.custId = a.custId
order by c.custId;

/*
delete from account where custId = 3;
delete from customer where custId = 3;

alter table account auto_increment = 3;
alter table customer auto_increment = 2;
*/