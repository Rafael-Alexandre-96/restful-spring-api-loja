CREATE TABLE IF NOT EXISTS `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

﻿INSERT INTO permission (description) VALUES
('ADMIN'), ('MANAGER'),	('COMMON_USER');

﻿CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB;

INSERT INTO users (user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
('rafael', 'Rafael Alexandre', 'cab2d314ab4ffa9019eb2e40abe5a2d7c293fd372634ddc95df6d6bc89f49fb7308e2cbde501ee43', b'1', b'1', b'1', b'1');

﻿CREATE TABLE IF NOT EXISTS `user_permission` (
  `id_user` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_permission`),
  KEY `fk_user_permission_permission` (`id_permission`),
  CONSTRAINT `fk_user_permission` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_user_permission_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB;

﻿INSERT INTO user_permission (id_user, id_permission) VALUES (1, 1);