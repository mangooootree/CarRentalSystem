CREATE DATABASE IF NOT EXISTS Carrentalservice;

CREATE USER IF NOT EXISTS `admin`@`localhost` IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON `Carrentalservice`.* TO `admin`@`localhost`;
FLUSH PRIVILEGES;

USE Carrentalservice;

CREATE TABLE IF NOT EXISTS `USER` (
  `id`         integer NOT NULL AUTO_INCREMENT,
  `firstname`  varchar(20)  NOT NULL,
  `lastname` varchar(20)  NOT NULL,
  `passport`   varchar(255) NOT NULL,
  `login`   varchar(255) NOT NULL,
  `password`   varchar(255) NOT NULL,
  `role` integer NOT NULL,
  PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `Car` (
  `id`           integer NOT NULL AUTO_INCREMENT,
  `model`        varchar(50) NOT NULL,
  `color`        varchar(50) NOT NULL,
  `licenceplate` varchar(8) NOT NULL,
  `isAutomaticTransmission` boolean NOT NULL,
  `isOrdered`       boolean NOT NULL,
  `price`   integer NOT NULL,
  PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `Order` (
  `id`        integer NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL REFERENCES `user` (`id`) ,
  `car_id`    integer NOT NULL REFERENCES `Car` (`id`) ,
  `date`      DATE NOT NULL,
  `amountOfDays` integer NOT NULL,
  `totalCost` integer NOT NULL,
  `isPaid`      boolean NOT NULL,
  `isAccepted`    boolean NOT NULL,
  `reviewed`      boolean NOT NULL,
  `comments`   varchar(255),
  PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `Bill` (
  `id`       integer NOT NULL AUTO_INCREMENT,
  `order_id` integer NOT NULL REFERENCES `Order` (`id`) ,
  `price`      integer NOT NULL,
  `damageDescription`   varchar(255),
  `paid`     boolean  NOT NULL,
  PRIMARY KEY(`id`)
);