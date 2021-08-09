/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

DROP DATABASE IF EXISTS SMSLite;

CREATE DATABASE SMSLite;

USE SMSLite;

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(450) NOT NULL
);
ALTER TABLE student
    AUTO_INCREMENT = 1;

DROP TABLE IF EXISTS provider;
CREATE TABLE provider
(
    id           INT PRIMARY KEY,
    name         VARCHAR(150) NOT NULL UNIQUE,
    operatorCode VARCHAR(3)   NOT NULL UNIQUE
);

DROP TABLE IF EXISTS contact;
CREATE TABLE contact
(
    contact     VARCHAR(12) NOT NULL,
    student_id  INT         NOT NULL,
    provider_id INT         NOT NULL,
    CONSTRAINT PRIMARY KEY (contact, student_id),
    CONSTRAINT fk_contact FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_provider FOREIGN KEY (provider_id) REFERENCES provider (id)
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


INSERT INTO provider
VALUES (1, 'SLTMobitel', '070');
INSERT INTO provider
VALUES (2, 'SLTMobitel', '071');
INSERT INTO provider
VALUES (3, 'Hutch', '072');
INSERT INTO provider
VALUES (4, 'Hutch', '078');
INSERT INTO provider
VALUES (5, 'Dialog', '074');
INSERT INTO provider
VALUES (6, 'Dialog', '076');
INSERT INTO provider
VALUES (7, 'Dialog', '077');
INSERT INTO provider
VALUES (8, 'Airtel', '075');

INSERT INTO contact
VALUES ('070 152 1458', 1, 1);
INSERT INTO contact
VALUES ('072 359 1589', 1, 3);
INSERT INTO contact
VALUES ('071 369 2578', 2, 2);
INSERT INTO contact
VALUES ('078 268 2578', 2, 4);
INSERT INTO contact
VALUES ('074 486 2589', 2, 5);
INSERT INTO contact
VALUES ('075 258 3698', 3, 8);
INSERT INTO contact
VALUES ('077 248 2685', 4, 7);
INSERT INTO contact
VALUES ('071 158 7596', 4, 2);
INSERT INTO contact
VALUES ('078 258 1452', 4, 4);
INSERT INTO contact
VALUES ('075 122 1436', 5, 8);
INSERT INTO contact
VALUES ('076 654 6585', 5, 6);
