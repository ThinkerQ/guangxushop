/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : gxshop

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-07-06 22:57:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT 'id作为商品编号',
  `categoryId` bigint(40) DEFAULT NULL COMMENT '分类Id',
  `firstName` varchar(500) DEFAULT NULL COMMENT '主名称',
  `secondName` varchar(1000) DEFAULT NULL COMMENT '副名称',
  `littlePath` varchar(1000) DEFAULT NULL COMMENT '小图片',
  `bigPath` varchar(1000) DEFAULT NULL COMMENT '大图片',
  `time` date DEFAULT NULL COMMENT '创建时间',
  `status` int(20) DEFAULT NULL COMMENT '0.可用;1.停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
