use u7demo;

create table users (
	username varchar(255) not null primary key,
    `password` varchar(255) not null
);

create table user_roles(
	id bigint not null primary key auto_increment,
    username varchar(255) not null,
    `role` varchar(255) not null,
    constraint user_roles_username_users foreign key (username)
		references users (username)
);

/*
	A123456b
		hashed to
	579f0af7c8ca854aa37b6d9cba1e400b1ada83fcae5b875d115de27ff480df01
*/

insert into users (username, password) values
('user1', '579f0af7c8ca854aa37b6d9cba1e400b1ada83fcae5b875d115de27ff480df01');

insert into user_roles (username, role) values
('user1','admin'), ('user1','user');

select *
from users u, user_roles r
where u.username = r.username;