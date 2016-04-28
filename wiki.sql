-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2016 at 12:24 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wiki`
--
CREATE DATABASE IF NOT EXISTS `wiki` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `wiki`;

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE IF NOT EXISTS `file` (
  `file_id` int(11) NOT NULL,
  `file_name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `file`:
--

--
-- Dumping data for table `file`
--

INSERT INTO `file` (`file_id`, `file_name`) VALUES
(7, '0dc47eef-2b37-4eaf-bd84-d30f0e902041'),
(8, '26fe1e77-020e-4e30-98c2-cb37d63df95e'),
(9, '7066674b-fb19-4280-a141-98c18255bfc9'),
(6, 'bc93b4ee-a81d-4cc3-a6cc-2eef3fd450a6');

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
CREATE TABLE IF NOT EXISTS `page` (
  `page_id` int(11) NOT NULL,
  `title` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `page`:
--

--
-- Dumping data for table `page`
--

INSERT INTO `page` (`page_id`, `title`) VALUES
(8, 'Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `page_file`
--

DROP TABLE IF EXISTS `page_file`;
CREATE TABLE IF NOT EXISTS `page_file` (
  `page_file_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `file_id` int(11) NOT NULL,
  `version_number` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `page_file`:
--   `page_id`
--       `page` -> `page_id`
--   `file_id`
--       `file` -> `file_id`
--   `user_id`
--       `users` -> `user_id`
--

--
-- Dumping data for table `page_file`
--

INSERT INTO `page_file` (`page_file_id`, `page_id`, `file_id`, `version_number`, `user_id`) VALUES
(7, 8, 6, 1, 4),
(8, 8, 7, 2, 6),
(9, 8, 8, 3, 4),
(10, 8, 9, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `roles`:
--

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Administrator'),
(2, 'Teacher'),
(3, 'student reader'),
(4, 'student writer\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL,
  `fname` text NOT NULL,
  `lname` text NOT NULL,
  `password` text NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `users`:
--   `role_id`
--       `roles` -> `role_id`
--

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `fname`, `lname`, `password`, `role_id`) VALUES
(3, 'evanso', 'overmars', 'munene', 1),
(4, 'Will', 'Itegi', 'munene', 2),
(5, 'John', 'Doe', 'munene', 3),
(6, 'Jane', 'Doe', 'munene', 4),
(7, 'James', 'Smith', 'jamos', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`file_id`),
  ADD UNIQUE KEY `file_name` (`file_name`);

--
-- Indexes for table `page`
--
ALTER TABLE `page`
  ADD PRIMARY KEY (`page_id`);

--
-- Indexes for table `page_file`
--
ALTER TABLE `page_file`
  ADD PRIMARY KEY (`page_file_id`),
  ADD KEY `page_id` (`page_id`),
  ADD KEY `file_id` (`file_id`),
  ADD KEY `version_number` (`version_number`),
  ADD KEY `page_id_2` (`page_id`),
  ADD KEY `file_id_2` (`file_id`),
  ADD KEY `version_number_2` (`version_number`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `role_id_2` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `file`
--
ALTER TABLE `file`
  MODIFY `file_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `page`
--
ALTER TABLE `page`
  MODIFY `page_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `page_file`
--
ALTER TABLE `page_file`
  MODIFY `page_file_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `page_file`
--
ALTER TABLE `page_file`
  ADD CONSTRAINT `page_file_ibfk_1` FOREIGN KEY (`page_id`) REFERENCES `page` (`page_id`),
  ADD CONSTRAINT `page_file_ibfk_2` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`),
  ADD CONSTRAINT `page_file_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
