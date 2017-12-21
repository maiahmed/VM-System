-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: vm
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  `comment_user_id` int(11) NOT NULL,
  `comment_request_id` int(11) NOT NULL,
  `extra_comment` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_request_id_idx` (`comment_request_id`),
  KEY `comment_user_id_idx` (`comment_user_id`),
  CONSTRAINT `comment_request_id` FOREIGN KEY (`comment_request_id`) REFERENCES `request` (`request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_user_id` FOREIGN KEY (`comment_user_id`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'xx','2013-09-04',1,1,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  `extra_impl` varchar(700) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `manager_idx` (`manager`),
  CONSTRAINT `manager` FOREIGN KEY (`manager`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'mai','mai@gmail.com','123','empl',NULL,NULL),(2,'mai','mai@gmail.com','123','manager',NULL,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `history_user_id` int(11) DEFAULT NULL,
  `history_request_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `history_user_id_idx` (`history_user_id`),
  KEY `history_request_id_idx` (`history_request_id`),
  CONSTRAINT `history_request_id` FOREIGN KEY (`history_request_id`) REFERENCES `request` (`request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `history_user_id` FOREIGN KEY (`history_user_id`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host`
--

DROP TABLE IF EXISTS `host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host` (
  `host_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `extra_host` varchar(700) DEFAULT NULL,
  `ip` varchar(39) NOT NULL,
  PRIMARY KEY (`host_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host`
--

LOCK TABLES `host` WRITE;
/*!40000 ALTER TABLE `host` DISABLE KEYS */;
INSERT INTO `host` VALUES (1,'mai',NULL,'80');
/*!40000 ALTER TABLE `host` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(400) DEFAULT NULL,
  `from` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `notif_request_id` int(11) DEFAULT NULL,
  `notif_user_id` int(11) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `hidden` varchar(1) DEFAULT NULL,
  `seen` varchar(1) DEFAULT NULL,
  `reminder` varchar(1) DEFAULT NULL,
  `expired` varchar(1) DEFAULT NULL,
  `extra_notif` varchar(555) DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `notif_request_id_idx` (`notif_request_id`),
  KEY `notif_user_id_idx` (`notif_user_id`),
  CONSTRAINT `notif_request_id` FOREIGN KEY (`notif_request_id`) REFERENCES `request` (`request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `notif_user_id` FOREIGN KEY (`notif_user_id`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os`
--

DROP TABLE IF EXISTS `os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `extra_os` varchar(45) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `os_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `os_user_id` (`os_user_id`),
  CONSTRAINT `os_user_id` FOREIGN KEY (`os_user_id`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os`
--

LOCK TABLES `os` WRITE;
/*!40000 ALTER TABLE `os` DISABLE KEYS */;
INSERT INTO `os` VALUES (1,'w',NULL,1,1);
/*!40000 ALTER TABLE `os` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `CPU` int(11) NOT NULL,
  `RAM` int(11) NOT NULL,
  `HD` int(11) NOT NULL,
  `creation_date` date NOT NULL,
  `expiring_date` date NOT NULL,
  `internetFacing` varchar(1) DEFAULT NULL,
  `request_user_id` int(11) NOT NULL,
  `submited_date` date DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `handeled_date` date DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `os_id` int(11) DEFAULT NULL,
  `os_type` varchar(20) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `os_id` (`os_id`),
  KEY `request_user_id_idx` (`request_user_id`),
  CONSTRAINT `os_id` FOREIGN KEY (`os_id`) REFERENCES `os` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `request_user_id` FOREIGN KEY (`request_user_id`) REFERENCES `employee` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,1,2,4,'2013-09-04','2013-09-04','0',1,'2013-09-04','2013-09-04','2013-09-04',3,1,NULL,NULL);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `sessionId` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(11) DEFAULT NULL,
  `token` int(11) DEFAULT NULL,
  `key` varchar(45) DEFAULT NULL,
  `lastInsertion` date DEFAULT NULL,
  `lastUpdate` date DEFAULT NULL,
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-21 19:08:37
