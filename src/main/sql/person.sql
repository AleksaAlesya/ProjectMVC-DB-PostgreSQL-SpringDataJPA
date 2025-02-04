DROP  TABLE Person;
DROP TABLE Item;
--удалить все данные из таблицы
TRUNCATE TABLE Person;

--Добавить столбец в таблицу
ALTER TABLE person ADD  COLUMN address varchar(50) NOT NULL ;

/*просто дата без точного времени в формате 01/01/1981*/
ALTER TABLE person ADD  COLUMN date_of_birth DATE ;

/* Точное время
 Значения  TIMESTAMP сохраняются  в секундах до или после полуночи до 1 января 2000 г*/
ALTER TABLE person ADD  COLUMN created_at TIMESTAMP ;
ALTER TABLE  person ADD  COLUMN  mood VARCHAR(20);

ALTER TABLE item ADD  COLUMN created_at TIMESTAMP ;

CREATE TABLE person(
    id int GENERATED BY DEFAULT AS IDENTITY  PRIMARY KEY ,
    name varchar(30) NOT NULL ,
    age int check ( age < 100 and age >= 0),
    email varchar(30) UNIQUE,
    address varchar(50) NOT NULL
);

INSERT INTO person(name, age, email,address) VALUES ('Aleksa',40,'aleksa@email.ru', 'Belarus, Gomel, 246005');
INSERT INTO person(name, age, email,address) VALUES ('Edvard',44, 'edvard@email.ru', 'Belarus, Gomel, 246005');
INSERT INTO person(name, age, email,address) VALUES ('Edvard',34, 'edvard2@email.ru', 'Belarus, Gomel, 246005');
INSERT INTO person(name, age, email, address) VALUES ( 'Evgeny', 33, 'evgeny@email.ru', 'Belarus, Gomel, 246005');
INSERT INTO person(name, age, email, address) VALUES ( 'Egor', 33, 'egor@email.ru', 'Belarus, Gomel, 246005');


CREATE TABLE Item
(
    id         INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    title_item VARCHAR(100) NOT NULL ,
    person_id  INT REFERENCES Person (id) ON DELETE SET NULL
);

INSERT INTO Item (title_item, person_id) VALUES ('Заказ1', 1);
INSERT INTO Item (title_item, person_id) VALUES ('Заказ2', 2);
INSERT INTO Item (title_item, person_id) VALUES ('Заказ3', 1);

SELECT *FROM person;
SELECT *FROM item;

SELECT name, email FROM person WHERE id=1;