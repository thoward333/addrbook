create table person (
	id int auto_increment primary key,
	username varchar(255) not null,
	first_name varchar(255),
	last_name varchar(255)
);

insert into person (username, first_name, last_name) values ('thoward333', 'Trey', 'Howard');
insert into person (username, first_name, last_name) values ('joeherbers', 'Joe', 'Herbers');
insert into person (username, first_name, last_name) values ('jdoe', 'John', 'Doe');

select * from person;
