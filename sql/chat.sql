/*
Navicat MySQL Data Transfer

Source Server         : xty
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-11-18 15:53:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c_user`
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `createTime` varchar(45) DEFAULT NULL,
  `valid` int(8) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES ('1', 'xty', 'xty', '2016-11-20', '1');
INSERT INTO `c_user` VALUES ('2', 'admin', 'admin', '2016-11-20', '1');
INSERT INTO `c_user` VALUES ('3', 'test', 'test', '2016-11-20', '1');
INSERT INTO `c_user` VALUES ('4', 'adad', 'aaa', '2016-11-20', '1');
INSERT INTO `c_user` VALUES ('5', 'qwer', 'asd', '2016-11-20', '1');

-- ----------------------------
-- Table structure for `user_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `user_relationship`;
CREATE TABLE `user_relationship` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) DEFAULT NULL,
  `fid` int(20) DEFAULT NULL,
  `createTime` varchar(45) DEFAULT NULL,
  `valid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relationship
-- ----------------------------
INSERT INTO `user_relationship` VALUES ('1', '1', '2', '2016-11-18', '0');
INSERT INTO `user_relationship` VALUES ('2', '3', '1', '2016-11-18', '1');
INSERT INTO `user_relationship` VALUES ('3', '3', '4', '2016-11-18', '1');
INSERT INTO `user_relationship` VALUES ('4', '3', '2', '2016-11-18', '1');
