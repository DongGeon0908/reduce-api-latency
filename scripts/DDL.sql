-- CREATE TABLE
CREATE DATABASE reduce_api_latency CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `test_1`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_2`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_3`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `test_4`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
