-- 测试用sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) DEFAULT NULL,
	`age` int(4) DEFAULT NULL,
	`address` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;