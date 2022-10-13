-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_service_v5
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abstraction`
--

DROP TABLE IF EXISTS `abstraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abstraction`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `address`     varchar(255) DEFAULT NULL,
    `close_time`  varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `open_time`   varchar(255) DEFAULT NULL,
    `hotel_id`    bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `FKfl7vyct96i523kh58iv784eov` (`hotel_id`),
    CONSTRAINT `FKfl7vyct96i523kh58iv784eov` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstraction`
--

LOCK
TABLES `abstraction` WRITE;
/*!40000 ALTER TABLE `abstraction` DISABLE KEYS */;
/*!40000 ALTER TABLE `abstraction` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `create_by`      varchar(255) DEFAULT NULL,
    `create_date`    varchar(255) DEFAULT NULL,
    `last_modify_by` varchar(255) DEFAULT NULL,
    `total_amount`   double NOT NULL,
    `update_date`    varchar(255) DEFAULT NULL,
    `customer_id`    bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `FKcdveik90g4pvk7m249scu73pg` (`customer_id`),
    CONSTRAINT `FKcdveik90g4pvk7m249scu73pg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK
TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill`
VALUES (1, NULL, NULL, NULL, 0, NULL, 1);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `bill_detail`
--

DROP TABLE IF EXISTS `bill_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_detail`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `status`     int    NOT NULL,
    `amount`     double NOT NULL,
    `price`      double NOT NULL,
    `quantity`   int    NOT NULL,
    `bill_id`    bigint DEFAULT NULL,
    `service_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `FKeolgwyayei3o80bb7rj7t207q` (`bill_id`),
    KEY          `FKey47deq1ge5g16c8ardnfy9b5` (`service_id`),
    CONSTRAINT `FKeolgwyayei3o80bb7rj7t207q` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
    CONSTRAINT `FKey47deq1ge5g16c8ardnfy9b5` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_detail`
--

LOCK
TABLES `bill_detail` WRITE;
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
INSERT INTO `bill_detail`
VALUES (1, 0, 200000, 100000, 2, 1, 1),
       (2, 1, 100000, 50000, 2, 1, 2);
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `bill_payment`
--

DROP TABLE IF EXISTS `bill_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_payment`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `date_time`         varchar(255) DEFAULT NULL,
    `payment_amount`    double NOT NULL,
    `bill_id`           bigint       DEFAULT NULL,
    `payment_method_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                 `FKjnqsfr0b9lukfdd6577vtngd5` (`bill_id`),
    KEY                 `FKhapeoij07p5auakaw6ywopwkc` (`payment_method_id`),
    CONSTRAINT `FKhapeoij07p5auakaw6ywopwkc` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
    CONSTRAINT `FKjnqsfr0b9lukfdd6577vtngd5` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_payment`
--

LOCK
TABLES `bill_payment` WRITE;
/*!40000 ALTER TABLE `bill_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_payment` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `actual_arrival_date`   varchar(255) DEFAULT NULL,
    `actual_departure_date` varchar(255) DEFAULT NULL,
    `arrival_date`          varchar(255) DEFAULT NULL,
    `confirmation_no`       int    NOT NULL,
    `create_by`             varchar(255) DEFAULT NULL,
    `create_date`           varchar(255) DEFAULT NULL,
    `departure_date`        varchar(255) DEFAULT NULL,
    `last_modify_by`        varchar(255) DEFAULT NULL,
    `num_of_adult`          int    NOT NULL,
    `num_of_children`       int    NOT NULL,
    `room_payment`          varchar(255) DEFAULT NULL,
    `special_note`          varchar(255) DEFAULT NULL,
    `status`                bit(1) NOT NULL,
    `total_amount`          double NOT NULL,
    `update_date`           varchar(255) DEFAULT NULL,
    `bill_id`               bigint       DEFAULT NULL,
    `customer_id`           bigint       DEFAULT NULL,
    `hotel_id`              bigint       DEFAULT NULL,
    `room_id`               bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                     `FKgi35fflyy8jm388q42yn93hu8` (`bill_id`),
    KEY                     `FKlnnelfsha11xmo2ndjq66fvro` (`customer_id`),
    KEY                     `FKhacdq9bfa3r9xdimovsnonbyi` (`hotel_id`),
    KEY                     `FKq83pan5xy2a6rn0qsl9bckqai` (`room_id`),
    CONSTRAINT `FKgi35fflyy8jm388q42yn93hu8` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
    CONSTRAINT `FKhacdq9bfa3r9xdimovsnonbyi` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
    CONSTRAINT `FKlnnelfsha11xmo2ndjq66fvro` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `FKq83pan5xy2a6rn0qsl9bckqai` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK
TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking`
VALUES (1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 3, 2, NULL, 'khong co', _binary '', 200000, NULL, 1, 1, 1, 1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `create_by`      varchar(255) DEFAULT NULL,
    `create_date`    varchar(255) DEFAULT NULL,
    `email`          varchar(255) DEFAULT NULL,
    `first_name`     varchar(255) DEFAULT NULL,
    `gender`         int    NOT NULL,
    `id_no`          int    NOT NULL,
    `last_modify_by` varchar(255) DEFAULT NULL,
    `last_name`      varchar(255) DEFAULT NULL,
    `middle_name`    varchar(255) DEFAULT NULL,
    `passport_no`    int    NOT NULL,
    `phone_number`   varchar(255) DEFAULT NULL,
    `update_date`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK
TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer`
VALUES (1, NULL, NULL, 'fang.longpc@gmail.com', 'cun', 0, 272734701, NULL, 'long', 'phuong', 0, '0984065979', NULL),
       (2, NULL, NULL, 'fang.longpc@gmail.com', 'hoang', 0, 272734701, NULL, 'yen', 'tran', 0, '0984065979', NULL),
       (3, NULL, NULL, 'fang.longpc@gmail.com', 'hoang', 0, 272734701, NULL, 'yen', 'tran', 0, '0984065979', NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `customer_booking`
--

DROP TABLE IF EXISTS `customer_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_booking`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `booking_id`  bigint DEFAULT NULL,
    `customer_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `FKrxentp412tb5uw3d6mc3pecqy` (`booking_id`),
    KEY           `FKj2e4knfmkk3aw0rd7udru6j1w` (`customer_id`),
    CONSTRAINT `FKj2e4knfmkk3aw0rd7udru6j1w` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `FKrxentp412tb5uw3d6mc3pecqy` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_booking`
--

LOCK
TABLES `customer_booking` WRITE;
/*!40000 ALTER TABLE `customer_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_booking` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `customer_feedback`
--

DROP TABLE IF EXISTS `customer_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_feedback`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `date_time`           varchar(255) DEFAULT NULL,
    `rating`              int    NOT NULL,
    `booking_id`          bigint       DEFAULT NULL,
    `feedback_content_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                   `FKii8mirltpbagwu9kehjxvfgsm` (`booking_id`),
    KEY                   `FKo5j6vu5hs22amqgfgav5qh4mc` (`feedback_content_id`),
    CONSTRAINT `FKii8mirltpbagwu9kehjxvfgsm` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
    CONSTRAINT `FKo5j6vu5hs22amqgfgav5qh4mc` FOREIGN KEY (`feedback_content_id`) REFERENCES `feedback_content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_feedback`
--

LOCK
TABLES `customer_feedback` WRITE;
/*!40000 ALTER TABLE `customer_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_feedback` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `brand`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `part_number` varchar(255) DEFAULT NULL,
    `serial_no`   varchar(255) DEFAULT NULL,
    `room_id`     bigint       DEFAULT NULL,
    `status`      bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `FK7wlgyldku7qmsg5qb8yv9nwtw` (`room_id`),
    CONSTRAINT `FK7wlgyldku7qmsg5qb8yv9nwtw` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK
TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices`
VALUES (1, 'ios', 'des1', 'iosphong1', 'part001', 'serial001', 1, _binary '\0'),
       (2, 'ios', 'des1', 'iosphong1', 'part001', 'serial001', 2, _binary '\0');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `address`            varchar(255) DEFAULT NULL,
    `description`        varchar(255) DEFAULT NULL,
    `end_date`           varchar(255) DEFAULT NULL,
    `end_time`           varchar(255) DEFAULT NULL,
    `name`               varchar(255) DEFAULT NULL,
    `number_of_view`     int    NOT NULL,
    `start_date`         varchar(255) DEFAULT NULL,
    `start_time`         varchar(255) DEFAULT NULL,
    `status`             bit(1) NOT NULL,
    `ticket_information` varchar(255) DEFAULT NULL,
    `hotel_id`           bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                  `FKseoc2993s4yyw6wihdhe83pkf` (`hotel_id`),
    CONSTRAINT `FKseoc2993s4yyw6wihdhe83pkf` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK
TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `feedback_content`
--

DROP TABLE IF EXISTS `feedback_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_content`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `content` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_content`
--

LOCK
TABLES `feedback_content` WRITE;
/*!40000 ALTER TABLE `feedback_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_content` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `address`        varchar(255) DEFAULT NULL,
    `breakfast`      bit(1) NOT NULL,
    `check_in_time`  varchar(255) DEFAULT NULL,
    `check_out_time` varchar(255) DEFAULT NULL,
    `create_by`      varchar(255) DEFAULT NULL,
    `create_date`    varchar(255) DEFAULT NULL,
    `description`    varchar(255) DEFAULT NULL,
    `email`          varchar(255) DEFAULT NULL,
    `full_name`      varchar(255) DEFAULT NULL,
    `last_modify_by` varchar(255) DEFAULT NULL,
    `latitude`       varchar(255) DEFAULT NULL,
    `longitude`      varchar(255) DEFAULT NULL,
    `phone_number`   varchar(255) DEFAULT NULL,
    `short_name`     varchar(255) DEFAULT NULL,
    `status`         bit(1) NOT NULL,
    `total_area`     int    NOT NULL,
    `total_room`     int    NOT NULL,
    `update_date`    varchar(255) DEFAULT NULL,
    `website`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK
TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel`
VALUES (1, 'HCM city', _binary '\0', NULL, NULL, NULL, NULL, NULL, 'cunplong.1@gmail.com', 'Fang Long', NULL, NULL,
        NULL, '0984065979', 'choi vui', _binary '', 0, 0, NULL, NULL);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `picture_description` varchar(255) DEFAULT NULL,
    `picture_type`        varchar(255) DEFAULT NULL,
    `picture_url`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK
TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image`
VALUES (1, 'khong co picture', 'overview_show_1', 'chaomung1.png');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `message_content` varchar(255) DEFAULT NULL,
    `booking_id`      bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY               `FKnym1kppv755cr0xmw03sl4ywb` (`booking_id`),
    CONSTRAINT `FKnym1kppv755cr0xmw03sl4ywb` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK
TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `method` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK
TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `picture_description` varchar(255) DEFAULT NULL,
    `picture_type`        varchar(255) DEFAULT NULL,
    `picture_url`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK
TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `description`        varchar(255) DEFAULT NULL,
    `detail_information` varchar(255) DEFAULT NULL,
    `end_date`           varchar(255) DEFAULT NULL,
    `name`               varchar(255) DEFAULT NULL,
    `start_date`         varchar(255) DEFAULT NULL,
    `hotel_id`           bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                  `FKd1a1gx3agdddefq7ioku2b0k1` (`hotel_id`),
    CONSTRAINT `FKd1a1gx3agdddefq7ioku2b0k1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK
TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `request_service`
--

DROP TABLE IF EXISTS `request_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_service`
(
    `id`                   bigint NOT NULL AUTO_INCREMENT,
    `date_time`            varchar(255) DEFAULT NULL,
    `request_service_name` varchar(255) DEFAULT NULL,
    `status`               bit(1) NOT NULL,
    `booking_id`           bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FKol6a967w3a9pw3ryvot19xn89` (`booking_id`),
    CONSTRAINT `FKol6a967w3a9pw3ryvot19xn89` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_service`
--

LOCK
TABLES `request_service` WRITE;
/*!40000 ALTER TABLE `request_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_service` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `create_by`      varchar(255) DEFAULT NULL,
    `create_date`    varchar(255) DEFAULT NULL,
    `description`    varchar(255) DEFAULT NULL,
    `last_modify_by` varchar(255) DEFAULT NULL,
    `name`           varchar(255) DEFAULT NULL,
    `room_no`        varchar(255) DEFAULT NULL,
    `status`         bit(1) NOT NULL,
    `update_date`    varchar(255) DEFAULT NULL,
    `hotel_id`       bigint       DEFAULT NULL,
    `room_type_id`   bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
    KEY              `FKd468eq7j1cbue8mk20qfrj5et` (`room_type_id`),
    CONSTRAINT `FKd468eq7j1cbue8mk20qfrj5et` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`),
    CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK
TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room`
VALUES (1, NULL, NULL, NULL, NULL, 'room001', '001', _binary '\0', NULL, 1, 1),
       (2, NULL, NULL, NULL, NULL, 'room002', '002', _binary '\0', NULL, 1, 2),
       (3, NULL, NULL, NULL, NULL, 'room003', '003', _binary '\0', NULL, 1, 3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `room_alarm`
--

DROP TABLE IF EXISTS `room_alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_alarm`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `date`       varchar(255) DEFAULT NULL,
    `booking_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `FKo3st13mbexfx1jatcp5t7brd4` (`booking_id`),
    CONSTRAINT `FKo3st13mbexfx1jatcp5t7brd4` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_alarm`
--

LOCK
TABLES `room_alarm` WRITE;
/*!40000 ALTER TABLE `room_alarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_alarm` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `room_price`
--

DROP TABLE IF EXISTS `room_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_price`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `date`             varchar(255) DEFAULT NULL,
    `max_booking_room` int    NOT NULL,
    `price`            double NOT NULL,
    `room_type_id`     bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `FKde9lg3uwjg6oi3fioh6c7o4c2` (`room_type_id`),
    CONSTRAINT `FKde9lg3uwjg6oi3fioh6c7o4c2` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_price`
--

LOCK
TABLES `room_price` WRITE;
/*!40000 ALTER TABLE `room_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_price` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `default_occupancy` int    NOT NULL,
    `description`       varchar(255) DEFAULT NULL,
    `is_active`         bit(1) NOT NULL,
    `max_adult`         int    NOT NULL,
    `max_children`      int    NOT NULL,
    `max_occupancy`     int    NOT NULL,
    `name`              varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK
TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type`
VALUES (1, 10, 'khongco', _binary '', 5, 5, 10, 'Double Room'),
       (2, 10, 'khongco', _binary '', 5, 5, 10, 'Single'),
       (3, 10, 'khongco', _binary '', 5, 5, 10, 'Deluxes');
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `room_type_utilities`
--

DROP TABLE IF EXISTS `room_type_utilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type_utilities`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `room_type_id` bigint DEFAULT NULL,
    `utilities_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `FKmlubnxx07q401vhmr5m56awq7` (`room_type_id`),
    KEY            `FK483jmuraj0p0cpguppu1t11qc` (`utilities_id`),
    CONSTRAINT `FK483jmuraj0p0cpguppu1t11qc` FOREIGN KEY (`utilities_id`) REFERENCES `utilities` (`id`),
    CONSTRAINT `FKmlubnxx07q401vhmr5m56awq7` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type_utilities`
--

LOCK
TABLES `room_type_utilities` WRITE;
/*!40000 ALTER TABLE `room_type_utilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_type_utilities` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `create_by`           varchar(255) DEFAULT NULL,
    `create_date`         varchar(255) DEFAULT NULL,
    `description`         varchar(255) DEFAULT NULL,
    `last_modify_by`      varchar(255) DEFAULT NULL,
    `name`                varchar(255) DEFAULT NULL,
    `price`               double NOT NULL,
    `status`              bit(1) NOT NULL,
    `update_date`         varchar(255) DEFAULT NULL,
    `service_category_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                   `FKowiok9o2e4m07fvmifjnvwtd2` (`service_category_id`),
    CONSTRAINT `FKowiok9o2e4m07fvmifjnvwtd2` FOREIGN KEY (`service_category_id`) REFERENCES `service_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK
TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service`
VALUES (1, NULL, NULL, 'khong co', NULL, 'banh xeo', 100000, _binary '\0', NULL, 1),
       (2, NULL, NULL, 'nuoc uong', NULL, 'nuoc mia', 50000, _binary '\0', NULL, 1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `service_category`
--

DROP TABLE IF EXISTS `service_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_category`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `hotel_id`    bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `FKrb4ink0hjuqv2jr9vxdb3l4vo` (`hotel_id`),
    CONSTRAINT `FKrb4ink0hjuqv2jr9vxdb3l4vo` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_category`
--

LOCK
TABLES `service_category` WRITE;
/*!40000 ALTER TABLE `service_category` DISABLE KEYS */;
INSERT INTO `service_category`
VALUES (1, 'khong', 'do an', 1);
/*!40000 ALTER TABLE `service_category` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `special_request`
--

DROP TABLE IF EXISTS `special_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_request`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `booking_id`         bigint DEFAULT NULL,
    `special_utility_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                  `FKicgkxmlgpywdr2s3qekh3i3ng` (`booking_id`),
    KEY                  `FKq7711orafokse7uodhg1mh6pr` (`special_utility_id`),
    CONSTRAINT `FKicgkxmlgpywdr2s3qekh3i3ng` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
    CONSTRAINT `FKq7711orafokse7uodhg1mh6pr` FOREIGN KEY (`special_utility_id`) REFERENCES `special_utility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_request`
--

LOCK
TABLES `special_request` WRITE;
/*!40000 ALTER TABLE `special_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_request` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `special_utility`
--

DROP TABLE IF EXISTS `special_utility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_utility`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `status`      bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_utility`
--

LOCK
TABLES `special_utility` WRITE;
/*!40000 ALTER TABLE `special_utility` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_utility` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `date_of_birth` varchar(255) DEFAULT NULL,
    `first_name`    varchar(255) DEFAULT NULL,
    `gender`        bit(1) NOT NULL,
    `is_active`     bit(1) NOT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `middle_name`   varchar(255) DEFAULT NULL,
    `password`      varchar(255) DEFAULT NULL,
    `phone_number`  varchar(255) DEFAULT NULL,
    `user_role`     varchar(255) DEFAULT NULL,
    `username`      varchar(255) DEFAULT NULL,
    `hotel_id`      bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `FK1m1qn3edjeuhxd21p502t9rs4` (`hotel_id`),
    CONSTRAINT `FK1m1qn3edjeuhxd21p502t9rs4` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, NULL, 'admin@gmail.com', _binary '\0', _binary '\0', NULL, NULL,
        '$2a$10$SJbSK9jmtIZJDnbxAtmjJOUZe0KurYXzXce/zLJJ.6mVSucBihE1e', NULL, 'ROLE_ADMIN', 'admin', 1),
       (2, NULL, 'Client@gmail.com', _binary '\0', _binary '\0', NULL, NULL,
        '$2a$10$fa1EkA/aatbWqZBBjyXA7.MZqOWkiJa7CmngQ4q5H5GwtreOcap3q', NULL, 'ROLE_USER', 'user', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `utilities`
--

DROP TABLE IF EXISTS `utilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilities`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilities`
--

LOCK
TABLES `utilities` WRITE;
/*!40000 ALTER TABLE `utilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilities` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-13 21:02:12
