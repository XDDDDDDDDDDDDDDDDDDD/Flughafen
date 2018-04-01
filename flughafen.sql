-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Apr 2018 um 15:00
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
(2, 'admin', 'Jan', 'Getschmann', 'Manager', '92668751');

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
  `Start` int(10) NOT NULL,
  `Ziel` int(10) NOT NULL,
  `Datum` date NOT NULL,
  `Zeit` time NOT NULL,
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
  `Code` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Flugzeugcode',
  `Hersteller` enum('Airbus','Boing','Embraer','Bombardier') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Flugzeugtyp` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `MaxPassagiere` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD UNIQUE KEY `ID` (`ID`);

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
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `kunde`
--
ALTER TABLE `kunde`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `ort`
--
ALTER TABLE `ort`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
