create table user (
                       id                    bigserial,
                       username              varchar(30) not null,
                       password              varchar(80) not null,
                       primary key (id)
);

create table role (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);

CREATE TABLE user_role (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references user (id),
                             foreign key (role_id) references role (id)
);

create table score(
                        id                  bigserial,
                        user_id             bigserial,
                        value               int not null default 0,
                        primary key         (id, user_id),
                        foreign key         (user_id) references user (id)
);


insert into role (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into user (username, password)
values
('pavel', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('helen', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('alex', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into user_role (user_id, role_id)
values
(1, 2),
(2, 1),
(3, 1);

insert into score(user_id, value)
values
(1,0),
(2,0),
(3,0);
