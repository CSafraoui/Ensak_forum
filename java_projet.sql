-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: java_projet
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
-- Table structure for table `atelier`
--

DROP TABLE IF EXISTS `atelier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atelier` (
  `id_atelier` bigint NOT NULL AUTO_INCREMENT,
  `description_atelier` varchar(255) DEFAULT NULL,
  `horaire_atelier` varchar(255) DEFAULT NULL,
  `titre_atelier` varchar(255) DEFAULT NULL,
  `superviseur_atelier_id_super` bigint DEFAULT NULL,
  PRIMARY KEY (`id_atelier`),
  KEY `FK5jh2i7b1ow1du2jctqjm057o` (`superviseur_atelier_id_super`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atelier`
--

LOCK TABLES `atelier` WRITE;
/*!40000 ALTER TABLE `atelier` DISABLE KEYS */;
INSERT INTO `atelier` VALUES (1,'Comment réussir son entretien de stage','2021-02-05T16:00','Entretien de stage',16),(2,'Comment réussir son entretien d\'embauche','2021-02-05T14:00','Entretien d\'embauche',18),(3,'Comment réussir son entretien d\'embauche','2021-02-05T15:00','Entretien d\'embauche',16);
/*!40000 ALTER TABLE `atelier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference` (
  `id_conf` bigint NOT NULL AUTO_INCREMENT,
  `description_conf` varchar(255) DEFAULT NULL,
  `horaire_conf` varchar(255) DEFAULT NULL,
  `titre_conf` varchar(255) DEFAULT NULL,
  `superviseur_conf_id_super` bigint DEFAULT NULL,
  PRIMARY KEY (`id_conf`),
  KEY `FK7knqxgw0u0e3ugam4kjhjhfw` (`superviseur_conf_id_super`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (1,'Artificial Intelligence: fron idea to value','2021-02-05T10:00','Artificial Intelligence',17),(2,'Maintenance intelligente et prédictive grâce a l\'inspection par drones et a l\'intelligence artificielle','2021-02-05T11:30','Maintenance 4.0',18),(3,'Artificial Intelligence: fron idea to value','2021-02-05T12:00','Artificial Intelligence',17);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entreprise` (
  `id_entreprise` bigint NOT NULL AUTO_INCREMENT,
  `activitee` varchar(255) DEFAULT NULL,
  `adressee` varchar(255) DEFAULT NULL,
  `emaile` varchar(255) DEFAULT NULL,
  `logoe` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `representant_id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id_entreprise`),
  KEY `FK3aou6gxemo31ci1bf4ysg4d01` (`representant_id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entreprise`
--

LOCK TABLES `entreprise` WRITE;
/*!40000 ALTER TABLE `entreprise` DISABLE KEYS */;
INSERT INTO `entreprise` VALUES (1,'Industrie','Atlantic Free Zone, km 4 commune Amer Saflia 14000 Kénitra - Maroc.\r\n','agc.automotive@gmail.com','agc_logo.png','AGC Automotive',NULL),(2,'Fabrication de tous les types d\'emballages en carton',' Route De Tanger Quartier Al Assam, Kénitra 14000','gpc.carton@gmail.com','gpc.jpg','GPC Carton',NULL),(3,'Usine automobile','Zone Industrielle Atlantic Free Zone, RN4, Commune Ammer Saflia, 14000? N4? Kenitra 14000','psa@gmail.com','psa.png','PSA',NULL),(4,'Informatique','Casablanca Nearshore Park\r\nShore 19\r\n1100 Bd Al Qods – Quartier Sidi Maârouf\r\n20270 CASABLANCA – Maroc','ibm.maroc@gmail.com','ibm.png','IBM Morocco',NULL),(5,'Informatique','av. Annakhil (hay Riyad) , imm. High tech - 4°ét. 10100 Rabat - Maroc','huawei@gmail.com','hwawei.png','Huawei',NULL),(6,'Industrie Agroalimentaire','8 rue El Mouatamid Ibnou Abbad BP3098 20 000 Casablanca','cosumar@gmail.com','Logo_cosumar.png','Cosumar',NULL),(7,'Informatique','Shore 4, 1er Etage, Plateau 102 - Casanearshore - Casablanca','dataprotect@gmail.com','dataprotect.png','DataProtect',NULL),(8,'Fabrication de faisceaux de cables et composants pour automobile','Lotissement 9 Juillet, zone franche, Kenitra','lear@gmail.com','lear.png','Lear automotive Morocco',NULL),(9,'EQUIPEMENTS AUTOMOBILES','ZONE INDUSTRIELLE BIR RAMI 399000 KENITRA','faurecia@gmail.com','faurecia.png','Faurecia',NULL);
/*!40000 ALTER TABLE `entreprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etudiant` (
  `cv` varchar(255) DEFAULT NULL,
  `ecole` varchar(255) DEFAULT NULL,
  `filiere` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` VALUES ('cv_final.pdf','ensak','Genie electrique','sans_image.png','3eme annee CI',2),('','ensak','Genie informatique','sans_image.png','2eme annee cp',4),(NULL,'ensak','Genie informatique','sans_image.png','2eme annee cp',5);
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscription` (
  `id_atelier` bigint NOT NULL,
  `id_etudiant` bigint NOT NULL,
  `date_insc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_atelier`,`id_etudiant`),
  KEY `FKmc9k6lgrd4m450srna9f3v4qp` (`id_etudiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscription`
--

LOCK TABLES `inscription` WRITE;
/*!40000 ALTER TABLE `inscription` DISABLE KEYS */;
INSERT INTO `inscription` VALUES (2,2,'2021-2-7 22:39:46'),(1,2,'2021-2-7 22:38:56');
/*!40000 ALTER TABLE `inscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offre` (
  `id_offre` bigint NOT NULL AUTO_INCREMENT,
  `date_pub` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `titre_offre` varchar(255) DEFAULT NULL,
  `representant` bigint DEFAULT NULL,
  PRIMARY KEY (`id_offre`),
  KEY `FK2kij0qk2vl00lmfctcjng149p` (`representant`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre`
--

LOCK TABLES `offre` WRITE;
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
INSERT INTO `offre` VALUES (1,'2021-01-30 11:44:45','Masarat App propose 3 stages PFE. Envoyez votre demande et partagez avec vos amis qui seraient intéressés.','Stage de fin d\'études en informatique & Systèmes d\'Information',1),(2,'2021-01-30 11:44:45','Masarat App propose 3 stages PFE. Envoyez votre demande et partagez avec vos amis qui seraient intéressés.','Stage de fin d\'études en informa',1),(3,'2021-01-30 11:44:45','Masarat App propose 3 stages PFE. Envoyez votre demande et partagez avec vos amis qui seraient intéressés.','Stage de fin d\'études en informatique & Systèmes d\'Information',1);
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id_post` varchar(255) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `date_pub` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  `titre_post` varchar(255) DEFAULT NULL,
  `etudiant` bigint DEFAULT NULL,
  PRIMARY KEY (`id_post`),
  KEY `FK3wwi899yr994qxbqapwr696vx` (`etudiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('3','0','2021-02-02 14:03:10','Can we disable the default web server in the Spring Boot application?','spring boot','default web server in the Spring Boot application',2),('4','0','2021-02-02 14:06:13','What are wrapper classes in Java?','java','classes in java',2),('2','1','2021-02-02 13:56:05','Thank u !!','spring boot','null',4),('1','0','2021-02-02 13:34:07','There are many people who advised me to use Spring Boot instead of Spring to develop REST web services. I want to know what exactly the difference between the two is?','spring boot','what is spring boot ?',4),('5','4','2021-02-02 14:07:42','Wrapper classes convert the Java primitives into the reference types (objects). Every primitive data type has a class dedicated to it. These are known as wrapper classes because they “wrap” the primitive data type into an object of that class. Refer to the below image which displays different primitive type, wrapper class and constructor argument.','java','null',4),('6','3','2021-02-02 14:08:46','The major strong point in Spring is to provide flexibility to build your application loosely coupled. Spring provides features to disable the web server in a quick configuration. Yes, we can use the application.properties to configure the web application type, i.e.  spring.main.web-application-type=none. All the best!','spring boot','null',4),('40283d8177770e610177778d8328000b','1','2021-02-06 13:34:59','a have the same qst','spring boot',NULL,2),('40283d8177770e61017777a1e7970010','1','2021-02-06 13:57:15','same qst','spring boot',NULL,2),('40283d8177770e61017777a2afc60011','3','2021-02-06 13:58:07','thanxxx','spring boot',NULL,2),('40283d8177d886d30177d89776fd0000','0','2021-02-25 09:49:01','c quoi springboot?','java framework','spring boot',2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postulation`
--

DROP TABLE IF EXISTS `postulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postulation` (
  `id_etudiant` bigint NOT NULL,
  `id_offre` bigint NOT NULL,
  `date_postulation` datetime DEFAULT NULL,
  PRIMARY KEY (`id_etudiant`,`id_offre`),
  KEY `FKa98643r03rg57aw7nir2c95ro` (`id_offre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postulation`
--

LOCK TABLES `postulation` WRITE;
/*!40000 ALTER TABLE `postulation` DISABLE KEYS */;
INSERT INTO `postulation` VALUES (4,1,'2021-01-12 20:20:20'),(5,3,'2021-01-12 20:20:20'),(2,2,'2021-02-07 21:43:56'),(2,1,'2021-02-07 21:42:56');
/*!40000 ALTER TABLE `postulation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profil`
--

DROP TABLE IF EXISTS `profil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profil` (
  `profil` varchar(255) NOT NULL,
  `idprofil` int NOT NULL,
  PRIMARY KEY (`idprofil`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profil`
--

LOCK TABLES `profil` WRITE;
/*!40000 ALTER TABLE `profil` DISABLE KEYS */;
INSERT INTO `profil` VALUES ('ROLE_ADMIN',1),('ROLE_ETUDIANT',2),('ROLE_REPRESENTANT',3),('ROLE_USER',4);
/*!40000 ALTER TABLE `profil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representant`
--

DROP TABLE IF EXISTS `representant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representant` (
  `id_user` bigint NOT NULL,
  `entreprise_id_entreprise` bigint DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FKjnnp49utkhr9ctysoba6mpskn` (`entreprise_id_entreprise`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representant`
--

LOCK TABLES `representant` WRITE;
/*!40000 ALTER TABLE `representant` DISABLE KEYS */;
INSERT INTO `representant` VALUES (1,1);
/*!40000 ALTER TABLE `representant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id_conf` bigint NOT NULL,
  `id_etudiant` bigint NOT NULL,
  `num_place` int NOT NULL,
  PRIMARY KEY (`id_conf`,`id_etudiant`),
  KEY `FK2rvsj5fhkc9r3dm29p19taiyb` (`id_etudiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (2,2,1),(1,2,1);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `id_sponsor` bigint NOT NULL AUTO_INCREMENT,
  `apropos_sponsor` varchar(255) NOT NULL,
  `logo_sponsor` varchar(255) NOT NULL DEFAULT 'sans_image.png',
  `nom_sponsor` varchar(255) NOT NULL,
  PRIMARY KEY (`id_sponsor`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (17,'Leader de l\'industrie pharmaceutique','cooper.png','Cooper'),(15,'Office national des chemins de fer','Logo-oncf.png','ONCF'),(16,'Usine d\'eau minérale naturelle','ainifranelogo.png','Ain Ifrane');
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superviseur`
--

DROP TABLE IF EXISTS `superviseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `superviseur` (
  `id_super` bigint NOT NULL AUTO_INCREMENT,
  `apropos_super` varchar(100) DEFAULT NULL,
  `nom_super` varchar(30) DEFAULT NULL,
  `prenom_super` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_super`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superviseur`
--

LOCK TABLES `superviseur` WRITE;
/*!40000 ALTER TABLE `superviseur` DISABLE KEYS */;
INSERT INTO `superviseur` VALUES (18,'CEO & CO-founder FARASHA SYSTEMS','Kriouile','Abderrahman'),(17,'Professeur de maching learning et big data Analysis à l\'université Cadi Ayyad de Marrackech','Mousannif','Hajar'),(16,'President du conseil electronique, social et environnemental','Mr. Chami','Ahmed Reda');
/*!40000 ALTER TABLE `superviseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profil` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FK226uwlp3ob89lygq5ifrb9ohd` (`profil`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'amal.zekri@uit.ac.ma','zekri','amal','amal',2),(1,'chaimae.safraoui@uit.ac.ma','Safraoui','chaimae','chaimae',3),(3,'salma.gouza@uit.ac.ma','Gouza','salma','salma',1),(4,'yassine.safraoui@uit.ac.ma','Safraoui','yassine','yassine',2),(5,'latifa.bouiad@uit.ac.ma','Bouiad','latifa','latifa',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-25 14:42:04
