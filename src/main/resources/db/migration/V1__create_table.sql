create table users
(
    id   serial primary key,
    name varchar(20) unique not null,
    type integer            not null
);

create table circles
(
    id       serial primary key,
    name     varchar(20) unique not null,
    owner_id integer            not null,
    constraint FK_circles_user_owner_id foreign key (owner_id) references users (id)
);

create table user_circles
(
    id        serial primary key,
    user_id   integer not null,
    circle_id integer not null,
    constraint FK_user_circles_user_id foreign key (user_id) references users (id),
    constraint FK_user_circles_circles_id foreign key (circle_id) references circles (id),
    constraint UQ_user_circles_user_id_circle_id unique (user_id, circle_id)
);
