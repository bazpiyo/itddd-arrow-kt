insert into users(name, type)
VALUES ('user1', 0);
insert into users(name, type)
VALUES ('user2', 0);
insert into users(name, type)
VALUES ('user3', 1);
insert into users(name, type)
VALUES ('user4', 1);

insert into circles(name, owner_id)
VALUES ('circle1', (select id from users where name = 'user3'));
insert into circles(name, owner_id)
VALUES ('circle2', (select id from users where name = 'user4'));

insert into user_circles(user_id, circle_id)
VALUES ((select id from users where name = 'user1'),
        (select id from circles where name = 'circle1'));

insert into user_circles(user_id, circle_id)
VALUES ((select id from users where name = 'user2'),
        (select id from circles where name = 'circle1'));

insert into user_circles(user_id, circle_id)
VALUES ((select id from users where name = 'user1'),
        (select id from circles where name = 'circle2'));

