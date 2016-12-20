-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.39 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4994
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных inetshop
CREATE DATABASE IF NOT EXISTS `inetshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inetshop`;


-- Дамп структуры для таблица inetshop.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы inetshop.role: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`) VALUES
	(1, 'role User'),
	(2, 'role Admin'),
	(3, 'role Admin1');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Дамп структуры для таблица inetshop.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_syftr7gx86fwf7ox7bgvnnta7` (`name`),
  KEY `FK_fe6nqh4mlcjr068gcfrstmh2y` (`role_id`),
  CONSTRAINT `FK_fe6nqh4mlcjr068gcfrstmh2y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы inetshop.user: ~12 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `password`, `salt`, `role_id`) VALUES
	(1, 'user1@mail.ru', '$2a$10$.NcOHlbMo4GBVsNtdp1ZOuuIObJGjSwdmd26eKRJq2QxjOdLktpiS', '$2a$10$.NcOHlbMo4GBVsNtdp1ZOu', 1),
	(2, 'user-2@mail.ru', '$2a$10$XKirhNVkOrwUAZdIJveR5.5uX5mQHKzlHTCCkcJbasObCBdddn2qm', '$2a$10$XKirhNVkOrwUAZdIJveR5.', 1),
	(3, 'user33@mail.ru', '$2a$10$7IAkHcbuWn81Ce0mZi5COucj8IxT6rHnYEdip/Im3rMB3ZrvDI8YW', '$2a$10$7IAkHcbuWn81Ce0mZi5COu', 2),
	(5, 'user5@mail.ru', '$2a$10$lTcv9xAInSG2bOat9V2xiOGr9ir9IvVom.xSz3KsHZyL7/zOHr9gG', '$2a$10$lTcv9xAInSG2bOat9V2xiO', 1),
	(6, 'admin1@tut.by', '$2a$10$xJyeUzgedrmTdKXEWSK4PuHmbbFtbj/uCvFwM4CxA0O/mCQg3URpm', '$2a$10$xJyeUzgedrmTdKXEWSK4Pu', 2),
	(7, 'Admin2@tt.by', '$2a$10$43jpYamqlGwb2hDyMF9RteMVxEUKz.rU6rNIPG7eXo6Llf9RktoKi', '$2a$10$43jpYamqlGwb2hDyMF9Rte', 2),
	(8, 'admin3@tut.by', '$2a$10$oAgvDnQxc0sQToR4ddOAh.xVW87hsXIhuTKuJB8qfoDpcH6cIysBe', '$2a$10$oAgvDnQxc0sQToR4ddOAh.', 3),
	(9, 'user-7@mail.ru', '$2a$10$jcCZ6jkaUnvpCRyc5qwxB.JZajwo8oiB2UCBIIlGTuaEXY80MLfm2', '$2a$10$jcCZ6jkaUnvpCRyc5qwxB.', 1),
	(10, 'user4@mail.ru', '$2a$10$hxsXAhUOqfaYe.CTgDOEoeCYyEQ1wU2YE.2SsYXsJpO7758fkPicG', '$2a$10$hxsXAhUOqfaYe.CTgDOEoe', 1),
	(11, 'user6@mail.ru', '$2a$10$MwSL7rL.9cslg7HEjsBiN.akF5Ch6iLm08hsPEaAh5sBW5SsiLVCi', '$2a$10$MwSL7rL.9cslg7HEjsBiN.', 1),
	(12, 'admin-2@tt.by', '$2a$10$QpVIgpaDUjBbj5uunQ2zUeZXZCFCWJLHZRKDOrjH0F2r2xK43nQ1m', '$2a$10$QpVIgpaDUjBbj5uunQ2zUe', 2),
	(13, 'admin-22@tt.by', '$2a$10$GpAj2vGR8CtJdFhP45GtlegXtYXcCYkD.E0nT8KJd9kxk5gMsY0o.', '$2a$10$GpAj2vGR8CtJdFhP45Gtle', 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
