DROP DATABASE IF EXISTS reading_room;

CREATE DATABASE reading_room DEFAULT CHARACTER SET 'utf8';

USE reading_room;

CREATE TABLE `Book` (
                        `bookId` int NOT NULL AUTO_INCREMENT,
                        `author` varchar(255) NOT NULL,
                        `title` varchar(255) NOT NULL,
                        `publisher` varchar(255) NOT NULL,
                        `year` int NOT NULL,
                        `translator` varchar(255),
                        `description` varchar(255) NOT NULL,
                        `orderId` int,
                        PRIMARY KEY (`bookId`)
);

CREATE TABLE `Order` (
                         `orderId` int NOT NULL AUTO_INCREMENT,
                         `date` DATE NOT NULL,
                         `userName` varchar(255) NOT NULL,
                         PRIMARY KEY (`orderId`)
);

ALTER TABLE `Book` ADD CONSTRAINT `OrderItem_fk0` FOREIGN KEY (`orderId`) REFERENCES `Order`(`orderId`);


