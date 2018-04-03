-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Apr 2018 um 16:06
-- Server-Version: 10.1.21-MariaDB
-- PHP-Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `flughafen`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE `benutzer` (
  `ID` int(10) NOT NULL,
  `Nutzername` varchar(20) COLLATE utf8_bin NOT NULL,
  `Vorname` varchar(40) COLLATE utf8_bin NOT NULL,
  `Nachname` varchar(40) COLLATE utf8_bin NOT NULL,
  `Rolle` enum('Manager','Mitarbeiter') COLLATE utf8_bin NOT NULL,
  `Passwort` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`ID`, `Nutzername`, `Vorname`, `Nachname`, `Rolle`, `Passwort`) VALUES
(1, 'hhhhhhhh', 'dddddddd', 'hhhhhhhhh', 'Mitarbeiter', '107523'),
(2, 'admin', 'Jan', 'Getschmann', 'Manager', '92668751'),
(3, 'holz', 'holz', 'holz', 'Mitarbeiter', '3208405'),
(4, 'benutzer', 'Ben', 'Ben', 'Mitarbeiter', '1701114365');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchungen`
--

CREATE TABLE `buchungen` (
  `ID` int(11) NOT NULL,
  `kundeID` int(11) NOT NULL,
  `fluegeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `buchungen`
--

INSERT INTO `buchungen` (`ID`, `kundeID`, `fluegeID`) VALUES
(5, 5, 1),
(6, 5, 2),
(7, 5, 3),
(13, 6, 1),
(14, 6, 2),
(15, 6, 3),
(18, 3, 1),
(20, 3, 3),
(21, 3, 2);

--
-- Trigger `buchungen`
--
DELIMITER $$
CREATE TRIGGER `del1` BEFORE UPDATE ON `buchungen` FOR EACH ROW SET FOREIGN_KEY_CHECKS=0
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `del2` BEFORE DELETE ON `buchungen` FOR EACH ROW SET FOREIGN_KEY_CHECKS=0
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `del3` AFTER UPDATE ON `buchungen` FOR EACH ROW SET FOREIGN_KEY_CHECKS=1
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `del4` AFTER DELETE ON `buchungen` FOR EACH ROW SET FOREIGN_KEY_CHECKS=1
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fluege`
--

CREATE TABLE `fluege` (
  `ID` int(20) NOT NULL,
  `Abflugzeit` datetime NOT NULL,
  `route` int(10) NOT NULL,
  `Essen` enum('Käse/Schinken- Brot','Pizza','Döner','Pasta') COLLATE utf8_bin NOT NULL,
  `Flugzeug` int(10) NOT NULL,
  `gebucht` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Daten für Tabelle `fluege`
--

INSERT INTO `fluege` (`ID`, `Abflugzeit`, `route`, `Essen`, `Flugzeug`, `gebucht`) VALUES
(1, '2018-04-18 00:00:00', 12, 'Käse/Schinken- Brot', 6, 0),
(2, '2018-04-26 00:00:00', 23, 'Käse/Schinken- Brot', 20, 0),
(3, '2018-04-24 00:00:00', 11, 'Pizza', 8, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeug`
--

CREATE TABLE `flugzeug` (
  `ID` int(10) NOT NULL,
  `Code` varchar(7) COLLATE utf8_bin NOT NULL COMMENT 'Flugzeugcode',
  `Hersteller` enum('Airbus','Boing','Embraer','Bombardier') COLLATE utf8_bin NOT NULL,
  `Flugzeugtyp` varchar(6) COLLATE utf8_bin NOT NULL,
  `MaxPassagiere` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Daten für Tabelle `flugzeug`
--

INSERT INTO `flugzeug` (`ID`, `Code`, `Hersteller`, `Flugzeugtyp`, `MaxPassagiere`) VALUES
(5, 'affsf', 'Airbus', 'sfs', 5),
(6, 'gshsh', 'Airbus', 'jf', 6),
(7, 'dhdhd', 'Airbus', 'ddg', 8),
(8, 'fhfh', 'Airbus', 'fh', 8),
(9, 'hfth', 'Airbus', 'thf', 6),
(10, 'fhtj', 'Airbus', 'kkk', 9),
(11, 'gjg', 'Airbus', 'gjzt', 9),
(12, 'cfzgj', 'Airbus', 'fjfj', 8),
(13, 'fjtjr', 'Airbus', 'dthj', 1),
(14, 'djrthfd', 'Airbus', 'drtzrt', 66),
(15, 'dhdh', 'Airbus', 'dhdh', 8),
(16, 'dhkftzj', 'Airbus', 'hsxt', 8),
(18, 'izz', 'Airbus', 'hrt', 47),
(19, 'dfhm', 'Airbus', 'nfb', 888),
(20, 'dhdhd', 'Airbus', 'dhdh', 6),
(21, 'mnb', 'Airbus', 'xbv', 34),
(22, 'cbc', 'Airbus', 'brr', 55),
(23, 'cbbccbc', 'Airbus', 'cbcbb', 5),
(24, 'ljhg', 'Airbus', 'jhhg', 47),
(25, 'fhfh', 'Airbus', 'kkm', 88),
(26, 'fnfnfn', 'Airbus', 'nfn', 88),
(27, 'MH 380', 'Embraer', 'A526', 600);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunde`
--

CREATE TABLE `kunde` (
  `ID` int(20) NOT NULL,
  `Vorname` varchar(40) COLLATE utf8_bin NOT NULL,
  `Nachname` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Daten für Tabelle `kunde`
--

INSERT INTO `kunde` (`ID`, `Vorname`, `Nachname`) VALUES
(3, 'Dieter', 'Holz'),
(4, 'John', 'Cena'),
(5, 'Ash', 'Ketchum'),
(6, 'Hugo', 'Boss');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ort`
--

CREATE TABLE `ort` (
  `ID` int(10) NOT NULL,
  `Name` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Daten für Tabelle `ort`
--

INSERT INTO `ort` (`ID`, `Name`) VALUES
(33, 'Almia'),
(34, 'Alabastia'),
(35, 'Lavandia'),
(36, 'Zinnoberinsel'),
(37, 'Vertania City'),
(38, 'Prismania City'),
(39, 'Safari- Zone'),
(40, 'Seeschauminsel'),
(41, 'Ruinental'),
(42, 'Silberberg'),
(43, 'Viola City'),
(44, 'Strudelinsel'),
(45, 'Wurzelheim'),
(46, 'Bad Lavastadt'),
(47, 'Flossbrunn'),
(48, 'Faustauhaven'),
(49, 'Jubelstadt'),
(50, 'Ewigenau'),
(51, 'Trostu'),
(52, 'Schleiede'),
(53, 'Fleetburg'),
(54, 'Sonnewik'),
(55, 'Blizzach'),
(56, 'Stratos City'),
(57, 'Ondula'),
(58, 'Orange Inseln');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `route`
--

CREATE TABLE `route` (
  `ID` int(10) NOT NULL,
  `start` int(10) NOT NULL,
  `ziel` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `route`
--

INSERT INTO `route` (`ID`, `start`, `ziel`) VALUES
(8, 33, 34),
(10, 33, 35),
(12, 33, 36),
(14, 33, 37),
(16, 33, 40),
(18, 33, 45),
(9, 34, 33),
(20, 34, 35),
(22, 34, 37),
(11, 35, 33),
(21, 35, 34),
(13, 36, 33),
(15, 37, 33),
(23, 37, 34),
(17, 40, 33),
(24, 40, 44),
(25, 44, 40),
(19, 45, 33);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indizes für die Tabelle `buchungen`
--
ALTER TABLE `buchungen`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `kundeID` (`kundeID`),
  ADD KEY `fluegeID` (`fluegeID`);

--
-- Indizes für die Tabelle `fluege`
--
ALTER TABLE `fluege`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `route` (`route`,`Flugzeug`),
  ADD KEY `ID_2` (`ID`,`route`,`Flugzeug`),
  ADD KEY `Datum` (`Abflugzeit`),
  ADD KEY `fluege_ibfk_2` (`Flugzeug`);

--
-- Indizes für die Tabelle `flugzeug`
--
ALTER TABLE `flugzeug`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `ID_2` (`ID`);

--
-- Indizes für die Tabelle `kunde`
--
ALTER TABLE `kunde`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `ID_2` (`ID`);

--
-- Indizes für die Tabelle `ort`
--
ALTER TABLE `ort`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indizes für die Tabelle `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `start` (`start`,`ziel`),
  ADD KEY `ziel` (`ziel`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `buchungen`
--
ALTER TABLE `buchungen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT für Tabelle `fluege`
--
ALTER TABLE `fluege`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `flugzeug`
--
ALTER TABLE `flugzeug`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT für Tabelle `kunde`
--
ALTER TABLE `kunde`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT für Tabelle `ort`
--
ALTER TABLE `ort`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT für Tabelle `route`
--
ALTER TABLE `route`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
