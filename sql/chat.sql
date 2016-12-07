/*
Navicat MySQL Data Transfer

Source Server         : xty
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-12-07 16:47:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c_user`
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `createTime` varchar(45) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `valid` int(8) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES ('1', 'xty', '11B3BA4679223FF0A2E4A0F11AB137659C355B6A153AFC78C79BC53898345BB8', '2016-11-20', '78EFD93C1A02FA7963BD23EDA3EF3D34D63F890DD19F7E2660AD7CBF6E79F02BA3FF484B07AB2B65', '1');
INSERT INTO `c_user` VALUES ('2', 'admin', '0A421647CF321E97D80D0BD999DA26EFD0BC6B7A865EE2CB4840B4B18FF96527', '2016-11-20', 'A8F76D2FD7F1F9EB234249609E04F98D6CDAB52D0DB2F83B5B6CA2A986878E83E8045D6585C5027F', '1');
INSERT INTO `c_user` VALUES ('3', 'test', '21B70483EE75E9597B85AF504036DFBA9C315CA4CB3B76E57D0BA29E6E3445D1', '2016-11-20', '8FD66D3E65C058E25921D0976CC5FF4C48E27C33D1A4F743EA12ECD7AFCCCF51CD08153C88275377', '1');
INSERT INTO `c_user` VALUES ('4', 'adad', '2C82C087531868E003055411E90D5F6FA59456587A570745E82499A9830887AF', '2016-11-20', '8D2498836C3E566D43E3A702DD55B7DE1A44B955E42830F0E9ED0CA1836DCF2AC7E932EB534769D4', '1');
INSERT INTO `c_user` VALUES ('5', 'qwer', '2795E5D21706E10F29ED898AADAA509F9D81198B7EBB43827C12B569B97AB46B', '2016-11-20', '4FFF64E20D4FE917C99CF255EDEBC0A246CE01F95833BA18B5CE28EA84A60D774B0DFB57058015AC', '1');
INSERT INTO `c_user` VALUES ('8', 'zxcv', '480A0A5D562B250CAD3F353B3AA82A1D137F1314DBD9F8BB278F9D1CDEA67827', '2016-11-23', 'F9A11C6CC1615B95AD5B1E0B3F71A125F960599F63191F5AEE866D31A20696E4CB3DCFC5AB2F99BF', '1');

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

-- ----------------------------
-- Table structure for `whitelist`
-- ----------------------------
DROP TABLE IF EXISTS `whitelist`;
CREATE TABLE `whitelist` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) DEFAULT NULL,
  `isAuto` int(8) DEFAULT NULL,
  `sTime` varchar(45) DEFAULT NULL,
  `eTime` varchar(45) DEFAULT NULL,
  `valid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of whitelist
-- ----------------------------
INSERT INTO `whitelist` VALUES ('1', '0:0:0:0:0:0:0:1', '1', '2016-11-22 10:00:00', '2016-11-22 12:00:00', '1');
INSERT INTO `whitelist` VALUES ('2', '127.0.0.1', '0', '2016-11-21 11:00:00', '2016-11-21 13:00:00', '0');
