/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.15 : Database - incentive
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`incentive` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `incentive`;

/*Table structure for table `default_info` */

DROP TABLE IF EXISTS `default_info`;

CREATE TABLE `default_info` (
  `id` bigint(20) NOT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  `server_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKocb21xfijgxeyuklb2c0fj72b` (`server_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `default_info` */

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(6),
(6),
(6),
(6);

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `id` bigint(20) NOT NULL,
  `app_name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `from_email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  `notification_url` varchar(255) DEFAULT NULL,
  `request_id` bigint(20) NOT NULL,
  `server_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKci9h7a779ysys8yg5repv5sll` (`request_id`),
  KEY `FK4k9kb5gti4ubp8akufvry0bh1` (`server_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `request` */

/*Table structure for table `request_details` */

DROP TABLE IF EXISTS `request_details`;

CREATE TABLE `request_details` (
  `id` bigint(20) NOT NULL,
  `message_data` varchar(255) DEFAULT NULL,
  `report` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `to_email` varchar(255) DEFAULT NULL,
  `request_id` bigint(20) NOT NULL,
  `template_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKglmfjt1al3gw5cl488n34ss3r` (`request_id`),
  KEY `FKj2bj66ecu8nmwx2dmrqhcbbf9` (`template_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `request_details` */

/*Table structure for table `scheduler_info` */

DROP TABLE IF EXISTS `scheduler_info`;

CREATE TABLE `scheduler_info` (
  `id` bigint(20) NOT NULL,
  `is_running` bit(1) NOT NULL,
  `scheduler_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `scheduler_info` */

insert  into `scheduler_info`(`id`,`is_running`,`scheduler_name`) values 
(4,'\0','REQUEST_SCHEDULER');

/*Table structure for table `server_info` */

DROP TABLE IF EXISTS `server_info`;

CREATE TABLE `server_info` (
  `id` bigint(20) NOT NULL,
  `smtp_authentication` bit(1) NOT NULL,
  `smtp_debug` bit(1) NOT NULL,
  `smtp_host` varchar(255) DEFAULT NULL,
  `smtp_password` varchar(255) DEFAULT NULL,
  `smtp_port` int(11) NOT NULL,
  `smtp_security_protocol` bit(1) NOT NULL,
  `smtp_socket_factory_port` int(11) NOT NULL,
  `smtp_socketfactory_fallback` bit(1) NOT NULL,
  `smtp_tls_enabled` bit(1) NOT NULL,
  `smtp_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `server_info` */

/*Table structure for table `templates` */

DROP TABLE IF EXISTS `templates`;

CREATE TABLE `templates` (
  `id` bigint(20) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `templates` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`password`,`username`) values 
(1,'$2a$10$H.7sYIXowhQ6qEQyKFsnEuAjx4kv2pJtUWXhdaBFULpdMATmvW/Yu','rana'),
(5,'$2a$10$fWMTuvuO.k9.Lcv728LHSOe9r2GUsvqyV.9.7F0oq0ZMWceD8IopK','imrul');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
