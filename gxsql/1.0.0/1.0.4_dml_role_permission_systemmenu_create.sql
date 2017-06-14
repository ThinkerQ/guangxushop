/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : gxshop

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-06-14 22:43:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `logininfo_role`
-- ----------------------------
DROP TABLE IF EXISTS `logininfo_role`;
CREATE TABLE `logininfo_role` (
  `l_id` bigint(40) DEFAULT NULL COMMENT '用户登录ID',
  `r_id` bigint(40) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logininfo_role
-- ----------------------------

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `resource` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `sn` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('3', '超级管理员', 'admin');
INSERT INTO `role` VALUES ('5', '财务', 'chart');
INSERT INTO `role` VALUES ('7', 'admin', 'chart');
INSERT INTO `role` VALUES ('13', '人事专员', 'HRBoy');
INSERT INTO `role` VALUES ('24', '市场专员', 'marketMan');
INSERT INTO `role` VALUES ('25', '销售主管', 'SaleManager');
INSERT INTO `role` VALUES ('26', '财务部人员', 'Financial');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `r_id` bigint(20) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  KEY `r_id` (`r_id`),
  KEY `p_id` (`p_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `systemmenu`
-- ----------------------------
DROP TABLE IF EXISTS `systemmenu`;
CREATE TABLE `systemmenu` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `state` varchar(4) DEFAULT NULL,
  `checked` tinyint(4) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `parent_id` bigint(255) DEFAULT NULL COMMENT '父菜单',
  `permission` varchar(100) DEFAULT NULL COMMENT '表达式',
  `children` bigint(40) DEFAULT NULL COMMENT ' 子菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemmenu
-- ----------------------------
INSERT INTO `systemmenu` VALUES ('1', '系统管理', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('2', '营销管理', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('3', '订单合同管理', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('4', '报表模块', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('5', '考勤模块', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('6', '工资模块', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('7', '售后管理', '1', null, null, null, null, null);
INSERT INTO `systemmenu` VALUES ('8', '广告内容管理', '1', null, '/supervisor/banner.do', '1', null, null);
