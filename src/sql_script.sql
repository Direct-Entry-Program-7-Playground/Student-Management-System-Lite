/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

DROP DATABASE IF EXISTS SMSLite;

CREATE DATABASE SMSLite;

USE SMSLite;

CREATE TABLE student
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(450) NOT NULL
);

CREATE TABLE contact
(
    contact    VARCHAR(12) NOT NULL,
    student_id INT         NOT NULL,
    CONSTRAINT PRIMARY KEY (contact, student_id),
    CONSTRAINT fk_contact FOREIGN KEY (student_id) REFERENCES student (id)
);

INSERT INTO student (name)
VALUES ('Wanidu Hasaranga');
INSERT INTO student (name)
VALUES ('Lasith Malinga');
INSERT INTO student (name)
VALUES ('Nuwan Pradeep');
INSERT INTO student (name)
VALUES ('Nuwan Kulasekara');
INSERT INTO student (name)
VALUES ('Rangana Herath');

INSERT INTO contact
VALUES ('071 152 1458', '1');
INSERT INTO contact
VALUES ('074 359 1589', '1');
INSERT INTO contact
VALUES ('185 369 2578', '2');
INSERT INTO contact
VALUES ('359 268 2578', '2');
INSERT INTO contact
VALUES ('157 486 2589', '2');
INSERT INTO contact
VALUES ('174 258 3698', '3');
INSERT INTO contact
VALUES ('157 248 2685', '4');
INSERT INTO contact
VALUES ('357 158 7596', '4');
INSERT INTO contact
VALUES ('145 258 1452', '4');
INSERT INTO contact
VALUES ('952 122 1436', '5');
