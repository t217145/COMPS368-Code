create database u9demo;

CREATE USER 'u9demo'@'localhost';
GRANT ALL PRIVILEGES ON u9demo.* TO 'u9demo'@'localhost';

use u9demo;

create table chatLog(
	log_id		int not null primary key auto_increment,
    message		varchar(500) not null,
    create_dtm	datetime not null default now()
);