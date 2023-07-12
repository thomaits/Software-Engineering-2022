CREATE DATABASE  IF NOT EXISTS `course_directory`;
USE `course_directory`;

-- SECURITY --
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;


create table `users` (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    enabled boolean not null
);

create table `authorities` (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);


insert into users(username, password, enabled)values('zarras','{noop}zarras',true);
insert into users(username, password, enabled)values('pvassil','{noop}pvassil',true);
 
insert into authorities(username,authority)values('zarras','ROLE_ADMIN');
insert into authorities(username,authority)values('zarras','ROLE_USER');
insert into authorities(username,authority)values('pvassil','ROLE_USER');
--
-- Table structure for table `course`
--
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `courses`;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE `courses` (
  `course_Id` INTEGER(10) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) DEFAULT NULL,
  `instructor_Login` VARCHAR(50) REFERENCES users(username),
  `academic_year` int DEFAULT NULL,
  `semester` varchar(45) DEFAULT NULL,
  `syllabus` varchar(45) DEFAULT NULL,
  PRIMARY KEY(`course_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `course`
--

INSERT INTO `courses` VALUES 
    (1, 'Software Engineering', 'zarras',2022, 8, 'CSS, Java, Springboot'),
    (2, 'Evolutionary Computa', 'zarras',2021, 0, 'Evol Algorithms'),
    (3, 'Evolutionary Compu 2', 'pvassil',2024, 5, 'Evol Algorithms');
    
--
-- Table structure for table `student_registrations`
--

DROP TABLE IF EXISTS `student_registrations`;

CREATE TABLE `student_registrations` (
  `student_Id` int NOT NULL AUTO_INCREMENT,
  `student_Name` varchar(45) DEFAULT NULL,
  `student_Year_Of_Registration` int DEFAULT NULL,
  `student_Year_Of_Studies` int DEFAULT NULL,
  `student_Current_Semester` int DEFAULT NULL,
  `student_Course_Grade` FLOAT DEFAULT NULL,
  `student_Project_Grade` FLOAT DEFAULT NULL,
  `course_Id` INTEGER(10),
  CONSTRAINT FK_courseId FOREIGN KEY(`course_Id`) REFERENCES courses(`course_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`student_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `student_registration`
--


INSERT INTO `student_registrations` VALUES 
	(1,'Thomai',2000,10,1,10,10,1),
    (2,'Nikolis',2000,10,1,9,9,1),
    (3,'John',2000,10,1,8,8,1),
	(4,'Name1',2000,10,1,7,7,2),
	(5,'Name2',2000,10,1,6,6,2),
    (6,'Name2',2000,10,1,5,5,1),
	(7,'Name3',2000,10,1,4,4,2),
	(8,'Name4',2000,10,1,6,6,1),
    (9,'Name5',2000,10,1,5,5,1),
	(10,'Name6',2000,10,1,6,6,1),
    (11,'Name7',2000,10,1,5,5,1);


