--
-- Create schema pdg
--
DROP DATABASE IF EXISTS pdg;
CREATE DATABASE IF NOT EXISTS pdg;
USE pdg;

--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL, 
  `pass` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--


--
-- Definition of table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fromUser` int(11) NOT NULL,
  `toUser` int(11) NOT NULL,
  `last_mod` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`fromUser`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`toUser`) REFERENCES `user`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blacklist`
--


--
-- Definition of table `friend`
--

DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fromUser` int(11) NOT NULL,
  `toUser` int(11) NOT NULL,
  `last_mod` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`fromUser`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`toUser`) REFERENCES `user`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friend`
--


--
-- Definition of table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
CREATE TABLE `tournament` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tournament`
--


--
-- Definition of table `matchlist`
--

DROP TABLE IF EXISTS `matchlist`;
CREATE TABLE `matchlist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `TournamentID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`userID`) REFERENCES `user`(`ID`),
  FOREIGN KEY (`TournamentID`) REFERENCES `Tournament`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matchlist`
--

--
-- Definition of table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `player1` int(11) NOT NULL,
  `player2` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `last_activity` datetime NOT NULL,
  `tournament` int(11) DEFAULT NULL,
  `remaining_letters` VARCHAR(100) NOT NULL,
  `game_state` BLOB,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`player1`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`player2`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`tournament`) REFERENCES `tournament` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

--
-- Definition of table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `tournament` int(11) DEFAULT NULL,
  `game` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`tournament`) REFERENCES `tournament` (`ID`),
  FOREIGN KEY (`game`) REFERENCES `game` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) NOT NULL,
  `created` DATETIME NOT NULL,
  `user` int(11) NOT NULL,
  `chat` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`user`) REFERENCES `user` (`ID`),
  FOREIGN KEY (`chat`) REFERENCES `chat` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

