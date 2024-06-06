TRUNCATE TABLE users RESTART IDENTITY CASCADE;

INSERT INTO user_detail (firstname, lastname, middlename, birth_date)
VALUES ('Захарова', 'Евгения', 'Владимировна', '1992-02-19'),
       ('Иванов', 'Иван', 'Иванович', '1971-11-02');

INSERT INTO account (account)
VALUES (1000),
       (500);

INSERT INTO users (login, password, user_detail_id, account_id)
VALUES ('user1', '{noop}1234500', 1, 1),
       ('user2', '{noop}1230000', 2, 2);

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1),
       ('USER', 2);

INSERT INTO phone (user_id, number)
VALUES (1, '79500024743'),
       (2, '79113456789');

INSERT INTO mail (user_id, email)
VALUES (1, 'user@yandex.ru'),
       (2, 'user2@gmail.com');
