DROP DATABASE IF EXISTS reading_room;

CREATE DATABASE reading_room DEFAULT CHARACTER SET 'utf8';

USE reading_room;

CREATE TABLE `book` (
                        `book_id` int NOT NULL AUTO_INCREMENT,
                        `author` varchar(255) NOT NULL,
                        `title` varchar(255) NOT NULL,
                        `publisher` varchar(255) NOT NULL,
                        `year` int NOT NULL,
                        `translator` varchar(255),
                        `description` varchar(255) NOT NULL,
                        `is_free`     BOOLEAN             NOT NULL,
                        PRIMARY KEY (`book_id`)
);

CREATE TABLE `book_order` (
                         `order_id` int NOT NULL AUTO_INCREMENT,
                         `date` DATE NOT NULL,
                         `user_name` varchar(255) NOT NULL,
                         `book_id` int NOT NULL,
                         PRIMARY KEY (`order_id`)
);

ALTER TABLE `book_order` ADD CONSTRAINT `order_item_fk0` FOREIGN KEY (`book_id`) REFERENCES `book`(`book_id`);


