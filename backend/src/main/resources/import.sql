
insert into user (neptun_code, name, password, type) values ('student','student', '$2a$12$ch8y9wIC6wD5H7h7rvTpNOsbrAH8a6WINEhcNklD2YHLMUaN.K6VO', 'STUDENT');
insert into user (neptun_code, name, password, type) values ('teacher','teacher', '$2a$12$5YWBn96C6pyqP52lwU0OaeVTCi8Jr9Mg5TB8UYXg6w5dWQzB9Mzsy', 'TEACHER');


insert into room(name,max_limit,occupied) values ('bolyai',11,true);
insert into room(name,max_limit,occupied) values ('821',11,true);
insert into room(name,max_limit,occupied) values ('822',11,true);

insert into course(name,max_limit,date,room_number, room_id) values('linalg',11,'Hétfő 2',1,1);
insert into course(name,max_limit,date,room_number,room_id) values('dimat',11,'Kedd 4',1,1);
insert into course(name,max_limit,date,room_number,room_id) values('progalap',11,'Hétfő 6',1,1);
insert into course(name,max_limit,date,room_number, room_id) values('linalg',11,'Szerda 2',1,1);
insert into course(name,max_limit,date,room_number,room_id) values('dimat',11,'Csütörtök 4',1,1);
insert into course(name,max_limit,date,room_number,room_id) values('progalap',11,'Péntek 6',1,1);


insert into exam(name,max_limit,date,room_number, room_id) values('linalg',11,'Hétfő 2',1,1);
insert into exam(name,max_limit,date,room_number,room_id) values('dimat',11,'Kedd 4',1,1);
insert into exam(name,max_limit,date,room_number,room_id) values('progalap',11,'Hétfő 6',1,1);
insert into exam(name,max_limit,date,room_number, room_id) values('linalg',11,'Szerda 2',1,1);
insert into exam(name,max_limit,date,room_number,room_id) values('dimat',11,'Csütörtök 4',1,1);
insert into exam(name,max_limit,date,room_number,room_id) values('progalap',11,'Péntek 6',1,1);
