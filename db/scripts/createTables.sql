-- main tables

create table if not exists users (
	id serial primary key,
	name varchar(30),
	expired timestamp
);

create table if not exists tasks
(
	id serial primary key,
	name varchar(50),
	description varchar(255),
	created timestamp,
	done boolean
);

INSERT INTO public.tasks (name, description, created, done) VALUES ('конфигурирование', 'конфигурирование hibernate через xml', '2019-09-13 11:00:46.673000', true);
INSERT INTO public.tasks (name, description, created, done) VALUES ('todo list', 'простое приложение для добавления задач', '2019-09-13 11:00:50.513000', false);
INSERT INTO public.tasks (name, description, created, done) VALUES ('лямбды и шаблон wrapper', 'рефакторинг задания todo list', '2019-09-13 11:02:34.125000', false);