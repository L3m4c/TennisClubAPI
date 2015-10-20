CREATE TABLE IF NOT EXISTS `user` (
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`    VARCHAR(255) DEFAULT NULL,
  `name`    VARCHAR(255) DEFAULT NULL,
  `surname`    VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `role`     VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);