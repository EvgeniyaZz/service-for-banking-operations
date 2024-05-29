DELETE FROM mail;
DELETE FROM phone;
DELETE FROM account;
DELETE FROM users;

INSERT INTO users (firstname, lastname, middlename, birth_date)
VALUES ('Захарова', 'Евгения', 'Владимировна', '1992-02-19'),
       ('Иванов', 'Иван', 'Иванович', '1971-11-02');

INSERT INTO phone (user_id, number)
VALUES (1, '79500482578'),
       (2, '79113456789');

INSERT INTO mail (user_id, email)
VALUES (1, 'user@yandex.ru'),
       (2, 'user2@gmail.com');

INSERT INTO account (user_id, login, password, deposit_money)
VALUES (1, 'user1', 1234500, 1000),
       (2, 'user2', 1230000, 500);