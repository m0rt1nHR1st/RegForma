create database martinDatabase;
use martinDatabase;
CREATE TABLE potrebitel (
    USERNAME VARCHAR(30) NOT NULL,
    SURNAME VARCHAR(30),
    GENDER VARCHAR(10),
    ADDRESS VARCHAR(30),
    EMAIL VARCHAR(30),
    PHONE VARCHAR (15),
    PASSWRD VARCHAR(20),
    CONFIRMPASSWRD VARCHAR(20)
);