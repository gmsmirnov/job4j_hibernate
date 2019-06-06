-- main tables

create table if not exists users (
	id serial primary key,
	name varchar(30),
	expired timestamp
);