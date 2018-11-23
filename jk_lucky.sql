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

 Date: 11/23/2018 23:56:46 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_lucky_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_lucky_user` VALUES ('1', '张三', '0', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '18600458426', null, null), ('2', '李四', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '18600457213', null, null), ('18', '宋学军', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '18600458426', '2018-11-22 14:06:53', ''), ('19', '王五', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '18600458421', '2018-11-22 16:15:02', 'fa:1c:79:93:20:64'), ('20', '郭秀', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '18811417189', '2018-11-22 18:12:28', '3c:2e:f9:69:da:af'), ('27', '豆腐', '0', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '13358586589', '2018-11-22 19:25:31', '80:ed:2c:bf:bf:dd'), ('30', '呃呃呃', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '12646464644', '2018-11-22 19:28:56', '20:f7:7c:d5:81:85'), ('31', '默默', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '26467973655', '2018-11-22 19:31:16', 'd8:1c:79:39:2a:82'), ('33', '测试', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '15564645465', '2018-11-22 20:10:33', '3c:a5:81:da:6c:d '), ('34', '刚好合适', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '12348998888', '2018-11-22 20:11:41', '34:78:d7:f6:61:1a'), ('35', 'jsjd', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '15664498866', '2018-11-22 20:12:45', '1c:15:1f:db:0:a3 '), ('36', 'j556', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '46866666666', '2018-11-22 20:14:24', 'f4:e3:fb:b9:1a:e0'), ('37', 'ID觉得', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '16646499494', '2018-11-22 20:16:10', 'b8:d7:af:5:ff:31 '), ('38', 'gtmp', '1', 'http://127.0.0.1:8081/e8d42846893a47bf9455623a396bfc29.png', '15646464944', '2018-11-22 20:26:12', '94:d0:29:7c:cb:5b');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_prize`
-- ----------------------------
BEGIN;
INSERT INTO `t_prize` VALUES ('1', '一等奖笔记本', 'http://localhost/images/dn.png', '1', '2018-11-23 19:53:57', null, '1'), ('2', '二等奖平衡车', 'http://localhost/images/phc.png', '2', '2018-11-23 19:54:01', null, '1'), ('4', '三等奖现金红包', 'http://localhost/images/hb.png', '3', '2018-11-23 19:54:04', '2018-11-23 19:54:06', '1');
COMMIT;

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

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
