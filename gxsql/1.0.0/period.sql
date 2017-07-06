/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : gxshop

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-07-06 22:57:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `period`
-- ----------------------------
DROP TABLE IF EXISTS `period`;
CREATE TABLE `period` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '作为期数',
  `periodNumber` bigint(40) DEFAULT NULL COMMENT '期数',
  `prizeUserId` bigint(40) DEFAULT NULL COMMENT '获奖用户',
  `needCount` int(40) DEFAULT NULL COMMENT '需要人次',
  `alreadyCount` int(40) DEFAULT NULL COMMENT '已参与人次',
  `surplusCount` int(40) DEFAULT NULL COMMENT '剩余人次',
  `numberId` bigint(40) DEFAULT NULL COMMENT '幸运号码',
  `productId` bigint(40) DEFAULT NULL COMMENT '商品ID',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `prizeTime` date DEFAULT NULL COMMENT '揭晓时间',
  `status` int(20) DEFAULT NULL COMMENT '1.未开奖;2.已开奖',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of period
-- ----------------------------
