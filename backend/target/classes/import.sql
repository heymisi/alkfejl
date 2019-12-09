-- insert into course (title, description, location, status, created_at, updated_at) values ('The course #1', 'Fatallica', 'PC42', 'NEW', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into course (title, description, location, status, created_at, updated_at) values ('MegaIssue', 'MegaFatal', 'PC12', 'INPROGRESS', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into course (title, description, location, status, created_at, updated_at) values ('Unknown course', 'U.F.O', 'PC11', 'INPROGRESS', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into course (title, description, location, status, created_at, updated_at) values ('Broken Windows 10', 'Everywhere', 'PC666', 'RESOLVED', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
--
-- insert into message (issue_id, text, created_at, updated_at) values (1, 'The things', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into message (issue_id, text, created_at, updated_at) values (2, 'that should', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into message (issue_id, text, created_at, updated_at) values (3, 'not', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into message (issue_id, text, created_at, updated_at) values (3, 'be', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
--
-- insert into label (text, created_at, updated_at) values ('labilis', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into label (text, created_at, updated_at) values ('e', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into label (text, created_at, updated_at) values ('az', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
-- insert into label (text, created_at, updated_at) values ('illabilis', CURRENT_TIMESTAMP(),  CURRENT_TIMESTAMP());
--
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
--
-- insert into issue_labels (issues_id, labels_id) values (1, 1);
-- insert into issue_labels (issues_id, labels_id) values (1, 2);
-- insert into issue_labels (issues_id, labels_id) values (1, 4);
-- insert into issue_labels (issues_id, labels_id) values (2, 1);
-- insert into issue_labels (issues_id, labels_id) values (2, 2);
-- insert into issue_labels (issues_id, labels_id) values (3, 2);
