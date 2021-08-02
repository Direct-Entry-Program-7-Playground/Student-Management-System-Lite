/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

DROP DATABASE IF EXISTS SMSLite;

CREATE DATABASE SMSLite;

USE SMSLite;

CREATE TABLE student(
    id VARCHAR(7) PRIMARY KEY,
    name VARCHAR(450) NOT NULL
);

CREATE TABLE contact
(
    contact    VARCHAR(12) NOT NULL,
    student_id VARCHAR(7)  NOT NULL,
    CONSTRAINT PRIMARY KEY (contact, student_id),
    CONSTRAINT fk_contact FOREIGN KEY (student_id) REFERENCES student (id)
);

INSERT INTO student VALUES ('SID0001','Wanidu Hasaranga');
INSERT INTO student VALUES ('SID0002','Lasith Malinga');
INSERT INTO student VALUES ('SID0003','Nuwan Pradeep');
INSERT INTO student VALUES ('SID0004','Nuwan Kulasekara');
INSERT INTO student VALUES ('SID0005','Rangana Herath');

INSERT INTO contact VALUES ('071 152 1458','SID0001');
INSERT INTO contact VALUES ('074 359 1589','SID0001');
INSERT INTO contact VALUES ('185 369 2578','SID0002');
INSERT INTO contact VALUES ('359 268 2578','SID0002');
INSERT INTO contact VALUES ('157 486 2589','SID0002');
INSERT INTO contact VALUES ('174 258 3698','SID0003');
INSERT INTO contact VALUES ('157 248 2685','SID0004');
INSERT INTO contact VALUES ('357 158 7596','SID0004');
INSERT INTO contact VALUES ('145 258 1452','SID0004');
INSERT INTO contact VALUES ('952 122 1436','SID0005');
