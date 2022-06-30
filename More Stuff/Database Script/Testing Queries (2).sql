select * from general_information
JOIN personal_position
ON general_information.ID = personal_position.person_ID
JOIN positions
ON personal_position.position_ID = positions.positions_ID;

insert into personal_position ( person_ID, position_ID) values (1,1);
insert into personal_position ( person_ID, position_ID) values (2,1);
insert into personal_position ( person_ID, position_ID) values (3,1);

insert into general_information (nickname, password_hash, email_address, phone_number, first_name, last_name, date_of_birth) 
values ('orlin123','Oi#123456789','orlin111@mail.com','0882487336','orlin','ivanov','1999-10-22');

insert into general_information (nickname, password_hash, email_address, phone_number, first_name, last_name, date_of_birth) 
values ('orlin12','Oi#1234567891','orlin111@maill.com','0882487331','orlin','ivanov','1999-10-22');

insert into general_information (nickname, password_hash, email_address, phone_number, first_name, last_name, date_of_birth) 
values ('orlin1112','Oi#123412567891','orlin111@mail1l.com','0882484331','orlin','ivanov','1999-10-22');


insert into positions (position) values ('MEMBER');
insert into positions (position) values ('VIP');
insert into positions (position) values ('BANNED');
insert into positions (position) values ('FITNESS_INSTRUCTOR');
insert into positions (position) values ('MODERATOR');
insert into positions (position) values ('ADMIN');

select * from general_information;
select * from more_information;
select * from activity;
select * from feedback;

select * from nutrients;

select * from foods
join nutrients
on foods.foods_ID = nutrients.food_ID;

delete from foods
where foods_ID = 7