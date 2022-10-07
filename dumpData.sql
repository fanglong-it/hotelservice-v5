-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: aws-simplified.cklet7vbtiwj.ap-northeast-1.rds.amazonaws.com    Database: hotel_service_v2
-- ------------------------------------------------------
-- Server version	8.0.28

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bill_rooms`
--

LOCK TABLES `bill_rooms` WRITE;
/*!40000 ALTER TABLE `bill_rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bill_service`
--

LOCK TABLES `bill_service` WRITE;
/*!40000 ALTER TABLE `bill_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer_booking`
--

LOCK TABLES `customer_booking` WRITE;
/*!40000 ALTER TABLE `customer_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hotel_locations`
--

LOCK TABLES `hotel_locations` WRITE;
/*!40000 ALTER TABLE `hotel_locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `location_type`
--

LOCK TABLES `location_type` WRITE;
/*!40000 ALTER TABLE `location_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `overview_service`
--

LOCK TABLES `overview_service` WRITE;
/*!40000 ALTER TABLE `overview_service` DISABLE KEYS */;
INSERT INTO `overview_service` VALUES (1,'+ Khách sạn 5 sao chuẩn quốc tế + Hứa hẹn đem lại cho quý khách hàng trải nghiệm tốt nhất + Free bữa sáng - view hồ bơi - và một số sự kiện hấp dẫn đi kèm','https://i.ibb.co/BwGDt2f/Overview-hotel.jpg','5 Men Hotel'),(2,'+ Hotel cung cấp cho quý khách hàng tất tần tật mọi loại dịch vụ + Sẵn sáng đáp ứng yêu cầu của quý khách 24/24 + Đa dạng các dịch vụ như giặt ủi, thuê xe,...','https://i.ibb.co/42chHS2/Overview-service.webp','Dịch vụ'),(3,'+ Thức ăn phong phú đến từ mọi miền của đất nước + Từ núi cao cho đến biển cả + Đặc biệt chỉ cần yêu cầu chúng tôi sẽ đáp ứng menu theo yêu cầu của bạn','https://i.ibb.co/bg5BJW5/Overview-food.jpg','Thức Ăn'),(4,'+ 5 Men Hotel - nơi hội tụ mọi cuộc chơi + Xung quanh hotel luôn có những sự kiện đình đám trong và ngoài nước + Colors me run - festival - sea party - concert','https://i.ibb.co/m4w0vt5/Overview-event.jpg','Sự kiện');
/*!40000 ALTER TABLE `overview_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES (1,'khongco','default','non.png'),(2,'No Description','Room Type','Not found Image');
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Default Description',_binary '','Default name','Not found Image',10000,1),(2,'Default Description',_binary '','Default name','Not found Image',10000,1),(3,'Default Description',_binary '','Default name','Not found Image',10000,1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_category`
--

LOCK TABLES `service_category` WRITE;
/*!40000 ALTER TABLE `service_category` DISABLE KEYS */;
INSERT INTO `service_category` VALUES (1,'khong','khong','khong.png');
/*!40000 ALTER TABLE `service_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_provider`
--

LOCK TABLES `service_provider` WRITE;
/*!40000 ALTER TABLE `service_provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'fanglong','$2a$10$gNK1ITbki8CoYUaAZ13CtuuNGoQrkM2f2MMuwQR3deZbRK27PHbVa','fanglong'),(2,'admin','$2a$10$JxQdOxnNt3gPxJ6soqmSn.TpjE1et8kdhr6PSMb7E5ugpyhbIjQdC','admin'),(3,'thanh dat','$2a$10$0/IjXy0uzB.ndXq8C7klCOl50NFhM1KsJ53G0WdWtgYoA2iLlbgOi','dat');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,2),(2,2),(2,1),(3,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-29  6:44:52
