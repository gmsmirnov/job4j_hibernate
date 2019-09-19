create table if not exists body(
	id serial primary key,
	name varchar(100),
	serial_number varchar(100)
);

create table if not exists engine(
	id serial primary key,
	name varchar(100),
	serial_number varchar(100)
);

create table if not exists transmission(
	id serial primary key,
	name varchar(100),
	serial_number varchar(100)
);

create table if not exists car(
	id serial primary key,
	name varchar(100),
	body int references body(id) not null,
	engine int references engine(id) not null,
	transmission int references transmission(id) not null
);