CREATE TABLE `category` (
  `id` bigint(20) NOT NULL COMMENT '商品分类编号',
  `name` varchar(30) DEFAULT NULL COMMENT '商品分类名称',
  `parentId` varchar(20) DEFAULT NULL COMMENT '上级分类id',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

