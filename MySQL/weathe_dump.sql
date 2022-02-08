-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Feb 2022 um 09:31
-- Server-Version: 10.4.21-MariaDB
-- PHP-Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `weather`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `arduinogpio`
--

CREATE TABLE `arduinogpio` (
  `ID` int(11) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Pin` int(11) NOT NULL,
  `ArduinoID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `arduinogpio`
--

INSERT INTO `arduinogpio` (`ID`, `Type`, `Pin`, `ArduinoID`) VALUES
(1, 'temperture', 34, 1),
(2, 'humidity', 35, 1),
(3, 'output1', 14, 1),
(4, 'output2', 16, 1),
(5, 'output3', 25, 1),
(6, 'temperture', 34, 2),
(7, 'humidity', 35, 2),
(8, 'output1', 15, 2),
(9, 'output2', 18, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `arduinolist`
--

CREATE TABLE `arduinolist` (
  `ID` int(11) NOT NULL,
  `ipADD` varchar(15) NOT NULL,
  `Port` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `arduinolist`
--

INSERT INTO `arduinolist` (`ID`, `ipADD`, `Port`) VALUES
(1, '10.0.0.12', 5000),
(2, '10.0.0.13', 5000),
(3, '10.0.0.20', 5000),
(4, '10.0.0.14', 5000),
(5, '10.0.0.12', 5000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `weatherdata`
--

CREATE TABLE `weatherdata` (
  `ID` int(11) NOT NULL,
  `Date` date NOT NULL DEFAULT current_timestamp(),
  `Time` time NOT NULL DEFAULT current_timestamp(),
  `Temperature` double NOT NULL,
  `Humidity` double NOT NULL,
  `ArduinoID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `arduinogpio`
--
ALTER TABLE `arduinogpio`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `arduinolist_arduinogpio` (`ArduinoID`);

--
-- Indizes für die Tabelle `arduinolist`
--
ALTER TABLE `arduinolist`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `weatherdata`
--
ALTER TABLE `weatherdata`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `arduinolist_weatherdata` (`ArduinoID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `arduinogpio`
--
ALTER TABLE `arduinogpio`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `arduinolist`
--
ALTER TABLE `arduinolist`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `weatherdata`
--
ALTER TABLE `weatherdata`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `arduinogpio`
--
ALTER TABLE `arduinogpio`
  ADD CONSTRAINT `arduinolist_arduinogpio` FOREIGN KEY (`ArduinoID`) REFERENCES `arduinolist` (`ID`);

--
-- Constraints der Tabelle `weatherdata`
--
ALTER TABLE `weatherdata`
  ADD CONSTRAINT `arduinolist_weatherdata` FOREIGN KEY (`ArduinoID`) REFERENCES `arduinolist` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
