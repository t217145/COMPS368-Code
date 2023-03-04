create database u7demo;
use u7demo;

create table users(
	userId			int not null primary key auto_increment,
    usercode 		varchar(255) not null unique,
    `password`		varchar(255) not null
);

create table roles(
	rId				int not null primary key auto_increment,
    usercode		varchar(255) not null,
    rolename		varchar(255) not null,
    constraint ak_roles unique(usercode, rolename),
    constraint fk_roles_usercode_users foreign key (usercode) 
		references users(usercode)
);

--create table users(
--    userId		INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
--    usercode 		varchar(255) not null unique,
--    password		varchar(255) not null
--);
--
--create table roles(
--    rId			INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
--    usercode		varchar(255) not null,
--    rolename		varchar(255) not null,
--    constraint ak_roles unique(usercode, rolename),
--    constraint fk_roles_usercode_users foreign key (usercode) 
--		references users(usercode)
--);

--insert into users (usercode, password) values ('cyrus', '579f0af7c8ca854aa37b6d9cba1e400b1ada83fcae5b875d115de27ff480df01');
--insert into users (usercode, password) values ('mandy', '579f0af7c8ca854aa37b6d9cba1e400b1ada83fcae5b875d115de27ff480df01');
--insert into users (usercode, password) values ('ruby', '579f0af7c8ca854aa37b6d9cba1e400b1ada83fcae5b875d115de27ff480df01');

--insert into roles (usercode, rolename) values ('cyrus', 'admin');
--insert into roles (usercode, rolename) values ('cyrus', 'user');
--insert into roles (usercode, rolename) values ('mandy', 'user');

--select
--    usercode, rolename
--from roles;