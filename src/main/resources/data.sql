insert into user (name, email, password, status) values ('admin', 'admin@codenation.dev', 'codenation', 'S');
insert into role (name, created_at) values ('CLIENT',current_timestamp);
insert into role (name, created_at) values ('ADMIN',current_timestamp);