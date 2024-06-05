DELETE FROM mail;
DELETE FROM phone;
DELETE FROM account;
DELETE FROM users;

INSERT INTO account (login, password, deposit_money)
VALUES ('user1', 1234500, 1000),
       ('user2', 1230000, 500);

INSERT INTO users (firstname, lastname, middlename, birth_date, account_id)
VALUES ('Захарова', 'Евгения', 'Владимировна', '1992-02-19', 1),
       ('Иванов', 'Иван', 'Иванович', '1971-11-02', 2);

INSERT INTO phone (user_id, number)
VALUES (1, '79500024743'),
       (2, '79113456789');

INSERT INTO mail (user_id, email)
VALUES (1, 'user@yandex.ru'),
       (2, 'user2@gmail.com');
