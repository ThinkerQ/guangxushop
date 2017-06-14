CREATE TABLE `systemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opUser_id` bigint(20) DEFAULT NULL,
  `opTime` datetime NOT NULL,
  `opIp` varchar(50) NOT NULL,
  `function` varchar(200) NOT NULL,
  `params` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6318 DEFAULT CHARSET=utf8;

