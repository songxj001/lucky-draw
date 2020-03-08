/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : jk

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 03/08/2020 12:36:15 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_baoming_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_baoming_user`;
CREATE TABLE `t_baoming_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(255) DEFAULT NULL,
  `createtTime` timestamp NULL DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_disciplinet`
-- ----------------------------
DROP TABLE IF EXISTS `t_disciplinet`;
CREATE TABLE `t_disciplinet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_lucky_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_lucky_history`;
CREATE TABLE `t_lucky_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `luckyUserId` int(11) DEFAULT NULL,
  `prizeId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=544 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_lucky_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_lucky_user`;
CREATE TABLE `t_lucky_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prize`
-- ----------------------------
DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL COMMENT '性别：1：男 0：女',
  `entrancetime` datetime DEFAULT NULL COMMENT '入学时间',
  `score` int(10) DEFAULT '100' COMMENT '学分 默认100分',
  `phonenumber` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=660 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_student_disciplinet`
-- ----------------------------
DROP TABLE IF EXISTS `t_student_disciplinet`;
CREATE TABLE `t_student_disciplinet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(50) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `teacherName` varchar(255) DEFAULT NULL,
  `disciplinetId` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `scoreType` int(11) DEFAULT '1' COMMENT '扣分类型 1：扣分 2：增分',
  `img` varchar(2000) DEFAULT NULL,
  `detail` text,
  `createTime` datetime DEFAULT NULL,
  `studentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
