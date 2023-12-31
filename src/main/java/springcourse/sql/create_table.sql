drop table if exists book;
drop table if exists person;


CREATE TABLE Person
(
    id            int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name     varchar(100) NOT NULL UNIQUE,
    year_of_birth int          NOT NULL
);

CREATE TABLE Book
(
    id        int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title     varchar(100) NOT NULL,
    author    varchar(100) NOT NULL,
    year      int          NOT NULL,
    person_id int          REFERENCES Person (id) ON DELETE SET NULL
);

INSERT INTO person(full_name, year_of_birth)
VALUES ('Иванов Иван Иванович', 1970);
INSERT INTO person(full_name, year_of_birth)
VALUES ('Петров Петр Петрович', 2013);
INSERT INTO person(full_name, year_of_birth)
VALUES ('Васильев Василий Васильевич', 1999);


INSERT INTO book(title, author, year)
VALUES ('Богатый папа Бедный папа', 'Роберт Киосаки', 2005);
INSERT INTO book(title, author, year)
VALUES ('Самый богатый человек в Вавилоне', 'Джордж Клейсон', 2006);
INSERT INTO book(title, author, year)
VALUES ('Разумный инвестор', 'Бенджамин Грэхем', 2010);
INSERT INTO book(title, author, year)
VALUES ('Учебник логики', 'Георгий Челпанов', 1915);