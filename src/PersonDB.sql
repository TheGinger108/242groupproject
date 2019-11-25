CREATE DATABASE persondb;
USE persondb;
CREATE TABLE person (
  PersonID INT AUTO_INCREMENT ,
  PersonName VARCHAR(45) NOT NULL,
  PersonSalary DOUBLE NOT NULL,
  StateName VARCHAR(45) NOT NULL,
  PersonType VARCHAR(45) NOT NULL,
  PRIMARY KEY (PersonID));
  