CREATE TABLE IF NOT EXISTS `client` (
                                        `id` bigint NOT NULL AUTO_INCREMENT,
                                        `nume` varchar(100) NOT NULL,
    `telefon` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `users` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `password` varchar(255) NOT NULL,
    `role` varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `material` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `grosime` int DEFAULT NULL,
                                          `name` varchar(255) DEFAULT NULL,
    `pret` double DEFAULT NULL,
    `tip_material` enum('Granit','Marmura','Quartz') DEFAULT NULL,
    `origine` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `comanda` (
                                         `id` bigint NOT NULL AUTO_INCREMENT,
                                         `data_comenzii` date DEFAULT NULL,
                                         `observatii` varchar(255) DEFAULT NULL,
    `status` enum('Gata','InLucru','Noua','Ridicata') DEFAULT NULL,
    `client_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKph3u97ueymbryq6utfel2ge50` (`client_id`),
    CONSTRAINT `FKph3u97ueymbryq6utfel2ge50` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `linie_comanda` (
                                               `id` bigint NOT NULL AUTO_INCREMENT,
                                               `cant` varchar(255) DEFAULT NULL,
    `grosime` double DEFAULT NULL,
    `latime` double DEFAULT NULL,
    `lungime` double DEFAULT NULL,
    `material` varchar(255) DEFAULT NULL,
    `pret` double DEFAULT NULL,
    `comanda_id` bigint DEFAULT NULL,
    `cant_dreapta` bit(1) DEFAULT NULL,
    `cant_jos` bit(1) DEFAULT NULL,
    `cant_stanga` bit(1) DEFAULT NULL,
    `cant_sus` bit(1) DEFAULT NULL,
    `colt_jos_dreapta` bit(1) DEFAULT NULL,
    `colt_jos_stanga` bit(1) DEFAULT NULL,
    `colt_sus_dreapta` bit(1) DEFAULT NULL,
    `colt_sus_stanga` bit(1) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKi052b49plr3wgvlwoo6w42kfr` (`comanda_id`),
    CONSTRAINT `FKi052b49plr3wgvlwoo6w42kfr` FOREIGN KEY (`comanda_id`) REFERENCES `comanda` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;