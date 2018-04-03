-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Apr 2018 um 00:29
-- Server-Version: 10.1.31-MariaDB
-- PHP-Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
  `Nutzername` varchar(20) NOT NULL,
  `Vorname` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Nachname` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Rolle` enum('Manager','Mitarbeiter') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Passwort` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`ID`, `Nutzername`, `Vorname`, `Nachname`, `Rolle`, `Passwort`) VALUES
(1, 'hhhhhhhh', 'dddddddd', 'hhhhhhhhh', 'Mitarbeiter', '107523'),
(2, 'admin', 'Jan', 'Getschmann', 'Manager', '92668751'),
(3, 'holz', 'holz', 'holz', 'Mitarbeiter', '3208405');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buchungen`
--

CREATE TABLE `buchungen` (
  `ID` int(20) NOT NULL,
  `kundeID` int(20) NOT NULL,
  `fluegeID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `buchungen`
--
DELIMITER $$
CREATE TRIGGER `gebucht` AFTER INSERT ON `buchungen` FOR EACH ROW UPDATE fluege
SET fluege.gebucht = (fluege.gebucht + 1)
WHERE fluege.ID=fluegeID
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fluege`
--

CREATE TABLE `fluege` (
  `ID` int(20) NOT NULL,
  `Datum` date NOT NULL,
  `Zeit` time NOT NULL,
  `route` int(10) NOT NULL,
  `Essen` enum('Käse/Schinken- Brot','Pizza','Döner','Pasta') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Flugzeug` int(10) NOT NULL,
  `gebucht` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `flugzeug`
--

CREATE TABLE `flugzeug` (
  `ID` int(10) NOT NULL,
  `Code` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Flugzeugcode',
  `Hersteller` enum('Airbus','Boing','Embraer','Bombardier') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Flugzeugtyp` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `MaxPassagiere` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Vorname` varchar(40) NOT NULL,
  `Nachname` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ort`
--

CREATE TABLE `ort` (
  `ID` int(10) NOT NULL,
  `Name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `kundeID` (`kundeID`,`fluegeID`),
  ADD KEY `fluegeID` (`fluegeID`);

--
-- Indizes für die Tabelle `fluege`
--
ALTER TABLE `fluege`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indizes für die Tabelle `flugzeug`
--
ALTER TABLE `flugzeug`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indizes für die Tabelle `kunde`
--
ALTER TABLE `kunde`
  ADD PRIMARY KEY (`ID`);

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
  ADD UNIQUE KEY `start` (`start`,`ziel`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `buchungen`
--
ALTER TABLE `buchungen`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `fluege`
--
ALTER TABLE `fluege`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `flugzeug`
--
ALTER TABLE `flugzeug`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT für Tabelle `kunde`
--
ALTER TABLE `kunde`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;

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

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `buchungen`
--
ALTER TABLE `buchungen`
  ADD CONSTRAINT `buchungen_ibfk_1` FOREIGN KEY (`kundeID`) REFERENCES `kunde` (`ID`),
  ADD CONSTRAINT `buchungen_ibfk_2` FOREIGN KEY (`fluegeID`) REFERENCES `fluege` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
