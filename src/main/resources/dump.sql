-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: localhost    Database: bookmarkstool
-- ------------------------------------------------------
-- Server version	5.6.15-log

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
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark` (
  `bookmarkId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `URL` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookmarkId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES (1,'Google Name','https://www.google.com.ua/','Google Start Page'),(2,'CSS course Google Name','http://htmlbook.ru/css','CSS study'),(3,'Angular Name','https://docs.angularjs.org/api/ng/directive/ngDisabled','Angular tutorial'),(4,'Angular Name','http://ngmodules.org/modules/angular.treeview','Angular tree view'),(5,'UI Name','http://www.w3schools.com/','UI Tutorials'),(6,'HTML Name','http://www.w3schools.com/html/default.asp','HTML Tutorials'),(7,'','http://www.w3schools.com/css/default.asp','CSS Tutorials'),(11,'bookmarkDTO','bookmarkDTO','bookmarkDTO'),(12,'bookmarkDTO','bookmarkDTO','bookmarkDTO'),(13,'bookmarkDTO','bookmarkDTO','bookmarkDTO'),(14,'Stack Overflow','http://stackoverflow.com',''),(15,'google','https://www.google.com.ua',NULL),(16,'facebook','https://www.facebook.com/',NULL),(17,'','http://godzilla.org.ua/',NULL),(18,'YouTube','https://www.youtube.com',NULL),(19,'CS','http://counter-strike.com.ua/','counter strike'),(20,'Github','https://github.com/',NULL),(21,'Zaycev Net','http://zaycev.net/',NULL),(22,'Google Translator','https://translate.google.com',NULL),(23,NULL,'http://developerslife.ru/',NULL),(24,NULL,'http://developerslife.ru/',NULL),(25,NULL,'http://developerslife1.ru/',NULL),(26,'vasa','vasa',NULL);
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmark_reference`
--

DROP TABLE IF EXISTS `bookmark_reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark_reference` (
  `bookmarkReferenceId` int(11) NOT NULL AUTO_INCREMENT,
  `bookmarkId` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `referenceId` int(11) NOT NULL,
  `referenceType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookmarkReferenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark_reference`
--

LOCK TABLES `bookmark_reference` WRITE;
/*!40000 ALTER TABLE `bookmark_reference` DISABLE KEYS */;
INSERT INTO `bookmark_reference` VALUES (1,1,NULL,'Tutorials/Angular',3,'USER'),(2,2,NULL,'Tutorials',3,'USER'),(3,3,NULL,NULL,3,'USER'),(4,1,NULL,'Favorite/Folder1',4,'TEAM'),(5,3,NULL,'Favorite/Folder2',4,'TEAM'),(6,4,NULL,NULL,4,'TEAM'),(7,5,NULL,'Tutorials/UI',3,'USER'),(8,6,NULL,'Tutorials/UI',3,'USER'),(9,7,NULL,'Fun',3,'USER'),(10,7,NULL,'Tutorials/Angular',3,'USER'),(11,7,NULL,'Tutorials/UI',3,'USER'),(12,1,NULL,'Traveling/Europe',4,'TEAM'),(13,2,NULL,'Traveling/Europe',4,'TEAM'),(14,7,NULL,'Traveling/Europe',4,'TEAM'),(15,3,NULL,'Traveling/Africa',4,'TEAM'),(16,4,NULL,'Traveling/Africa/SouthernAfrica',4,'TEAM'),(17,5,NULL,'Traveling/Africa/SouthernAfrica',4,'TEAM'),(18,6,NULL,'Traveling/Africa/SouthernAfrica',4,'TEAM'),(19,14,'2015-02-02','work',6,'USER'),(20,15,'2015-02-02',NULL,6,'USER'),(21,16,'2015-02-02','fun',6,'USER'),(22,17,'2015-02-02','fun',6,'USER'),(23,18,'2015-02-04','fun/music',6,'USER'),(24,19,'2015-02-04','fun/games',6,'USER'),(25,20,'2015-02-04','work',6,'USER'),(26,21,'2015-02-04','fun/music',6,'USER'),(27,22,'2015-02-04','other/other2',6,'USER'),(28,23,'2015-02-04','fun',6,'USER'),(29,24,'2015-02-04','fun/other',6,'USER'),(30,25,'2015-02-04','fun/other',6,'USER'),(31,26,'2015-02-04',NULL,6,'USER');
/*!40000 ALTER TABLE `bookmark_reference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_ANONYMOUS');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Music'),(2,'Dance'),(3,'Sport'),(4,'fun'),(5,'haha');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'habra2@mail.ru','ww','www','wwwwwwww','wwww'),(2,'habra3@mail.ru','ee','eee','eeeeeeee','eeee'),(3,'habra@mail.ru','rr','rrr','rrrrrrrr','rrrr'),(4,'vp@gmail.com','qq','qqq','qqqqqqqq','qqqq'),(5,'vp2@gmail.com','aa','aaa','aaaaaaaa','aaaa'),(6,'kushnirykoleh@gmail.com','Oleh','Kushniryk','WzxOezmT','Scorpio');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FKADBB8186C303F071` (`roleId`),
  KEY `FKADBB81865EE65AF0` (`userId`),
  CONSTRAINT `FKADBB81865EE65AF0` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FKADBB8186C303F071` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,2),(4,2),(5,2),(6,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_teams`
--

DROP TABLE IF EXISTS `users_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_teams` (
  `userTeamId` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `teamId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userTeamId`),
  KEY `FKADD2FC7F5C69AB94` (`teamId`),
  CONSTRAINT `FKADD2FC7F5C69AB94` FOREIGN KEY (`teamId`) REFERENCES `team` (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_teams`
--

LOCK TABLES `users_teams` WRITE;
/*!40000 ALTER TABLE `users_teams` DISABLE KEYS */;
INSERT INTO `users_teams` VALUES (1,'accepted',1,1),(2,'accepted',2,2),(3,'accepted',3,3),(4,'owner',3,4),(6,'accepted',2,4),(8,'owner',2,5);
/*!40000 ALTER TABLE `users_teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-04 23:58:06
