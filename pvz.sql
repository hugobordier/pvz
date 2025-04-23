-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: pvz
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Map`
--

DROP TABLE IF EXISTS `Map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Map` (
  `id_map` int NOT NULL AUTO_INCREMENT,
  `ligne` int unsigned NOT NULL,
  `colonne` int unsigned NOT NULL,
  `chemin_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_map`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Map`
--

LOCK TABLES `Map` WRITE;
/*!40000 ALTER TABLE `Map` DISABLE KEYS */;
INSERT INTO `Map` VALUES (1,5,9,'images/map/gazon.png'),(2,6,9,'images/map/gazon.png'),(3,4,8,'images/map/gazon.png');
/*!40000 ALTER TABLE `Map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plante`
--

DROP TABLE IF EXISTS `Plante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Plante` (
  `id_plante` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `point_de_vie` int unsigned NOT NULL,
  `attaque_par_seconde` decimal(5,2) DEFAULT '0.00',
  `degat_attaque` int unsigned DEFAULT '0',
  `cout` int unsigned NOT NULL,
  `soleil_par_seconde` decimal(5,2) DEFAULT '0.00',
  `effet` enum('normal','slow low','slow medium','slow stop') DEFAULT 'normal',
  `chemin_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_plante`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plante`
--

LOCK TABLES `Plante` WRITE;
/*!40000 ALTER TABLE `Plante` DISABLE KEYS */;
INSERT INTO `Plante` VALUES (1,'Tournesol',100,0.00,0,50,25.00,'normal','images/plante/maison1.png'),(2,'Pois Tireur',150,1.50,20,100,0.00,'normal','images/plante/maison2.png'),(3,'Double Pisto P',150,1.50,40,200,0.00,'normal','images/plante/maison3.png'),(4,'Glace Pois',120,1.00,10,175,0.00,'slow low','images/plante/maison4.png'),(5,'Noix',300,0.00,0,50,0.00,'normal','images/plante/maison5.jpg');
/*!40000 ALTER TABLE `Plante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Zombie`
--

DROP TABLE IF EXISTS `Zombie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Zombie` (
  `id_zombie` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `point_de_vie` int unsigned NOT NULL,
  `attaque_par_seconde` decimal(5,2) DEFAULT '0.00',
  `degat_attaque` int unsigned NOT NULL,
  `vitesse_de_deplacement` decimal(5,2) DEFAULT '0.00',
  `chemin_image` varchar(255) DEFAULT NULL,
  `id_map` int DEFAULT NULL,
  PRIMARY KEY (`id_zombie`),
  KEY `fk_zombie_map` (`id_map`),
  CONSTRAINT `fk_zombie_map` FOREIGN KEY (`id_map`) REFERENCES `Map` (`id_map`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zombie`
--

LOCK TABLES `Zombie` WRITE;
/*!40000 ALTER TABLE `Zombie` DISABLE KEYS */;
INSERT INTO `Zombie` VALUES (1,'Zombie de base',100,0.80,10,0.50,'images/zombie/avion1.jpg',1),(2,'Zombie Cone',200,0.80,10,0.45,'images/zombie/avion2.webp',1),(3,'Zombie Seau',300,0.70,10,0.40,'images/zombie/avion3.jpg',1),(4,'Runner Zombie',80,1.00,8,0.70,'images/zombie/avion4.webp',2),(5,'Football Zombie',250,0.90,12,0.60,'images/zombie/avion5.jpg',3);
/*!40000 ALTER TABLE `Zombie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pvz'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-18 21:21:18
