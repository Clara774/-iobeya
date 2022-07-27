CREATE DATABASE iobeya COLLATE 'UTF-8';
USE iobeya;

-------------------------------------------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(250) COLLATE utf8_bin NOT NULL,
        `parent_id` int(11) DEFAULT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `name` (`name`),
        KEY `fk_parent_id` (`parent_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE `categories`
    ADD CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`);

-------------------------------------------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `username` varchar(250) COLLATE utf8_bin NOT NULL,
        `password` varchar(250) COLLATE utf8_bin NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `username` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `users` (`id`, `username`, `password`) VALUES
    (1, 'admin', '$2a$10$pNrWzzANP0.fg2.9Oq8vxeRCDdo6CQYwFeIjSyDJANTo5/ElPdbse');

-------------------------------------------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(20) COLLATE utf8_bin NOT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `roles` (`id`, `name`) VALUES
                                       (1, 'ROLE_USER'),
                                       (2, 'ROLE_MODERATOR'),
                                       (3, 'ROLE_ADMIN');

-------------------------------------------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
        `user_id` int(11) NOT NULL,
        `role_id` int(11) NOT NULL,
        KEY `fk_user_id` (`user_id`),
        KEY `fk_role_id` (`role_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
    (1, 1);

ALTER TABLE `user_roles`
    ADD CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;
