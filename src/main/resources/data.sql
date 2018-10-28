delete from user_role;
delete from roles;
delete from users;

delete from singer;
delete from album;
delete from instrument;
delete from singer_instrument;

INSERT INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO users (id, username, password, full_name) VALUES
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin'),
(2, 'siva@gmail.com', '$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi', 'Siva'),
(3, 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User');

insert into user_role(user_id, role_id) values
(1,1),
(1,2),
(3,2);


insert into singer (first_name, last_name, birth_date)
values ('John', 'Mayer', '1977-10-16');
insert into singer (first_name, last_name, birth_date)
values ('Eric', 'Clapton', '1945-03-30');
insert into singer (first_name, last_name, birth_date)
values ('John', 'Butler', '1975-04-01');
insert into album (singer_id, title, release_date)
values (1, 'The Search For Everything', '2017-01-20');
insert into album (singer_id, title, release_date)
values (1, 'Battle Studies', '2009-11-17');
insert into album (singer_id, title, release_date)
values (2, 'From The Cradle ', '1994-09-13');
insert into instrument (id) values ('Guitar');
insert into instrument (id) values ('Piano');
insert into instrument (id) values ('Voice');
insert into instrument (id) values ('Drums');
insert into instrument (id) values ('Synthesizer');
insert into singer_instrument(singer_id, instrument_id) values (1, 'Guitar');
insert into singer_instrument(singer_id, instrument_id) values (1, 'Piano');
insert into singer_instrument(singer_id, instrument_id) values (2, 'Guitar')