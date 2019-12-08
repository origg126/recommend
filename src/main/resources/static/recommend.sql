/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 127.0.0.1:3306
 Source Schema         : recommend

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/12/2019 22:36:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1000', '崔峰槐', '123456');
INSERT INTO `user` VALUES ('1001', '徐竞鹿', '123456');
INSERT INTO `user` VALUES ('123', 'origg', '123456');
INSERT INTO `user` VALUES ('124', 'xiaovv', '123456');
INSERT INTO `user` VALUES ('125', 'xjl', '123456');

-- ----------------------------
-- Table structure for userlike
-- ----------------------------
DROP TABLE IF EXISTS `userlike`;
CREATE TABLE `userlike`  (
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `videoId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` double(10, 1) NULL DEFAULT NULL,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `videoId`(`videoId`) USING BTREE,
  CONSTRAINT `userlike_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userlike_ibfk_2` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlike
-- ----------------------------
INSERT INTO `userlike` VALUES ('123', '111', 4.0);
INSERT INTO `userlike` VALUES ('123', '113', 3.0);
INSERT INTO `userlike` VALUES ('123', '114', 5.0);
INSERT INTO `userlike` VALUES ('124', '111', 5.0);
INSERT INTO `userlike` VALUES ('124', '112', 4.0);
INSERT INTO `userlike` VALUES ('124', '113', 2.0);
INSERT INTO `userlike` VALUES ('125', '111', 2.0);
INSERT INTO `userlike` VALUES ('125', '112', 4.0);
INSERT INTO `userlike` VALUES ('125', '114', 3.0);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('111', '本杰明巴顿奇事');
INSERT INTO `video` VALUES ('112', '泰坦尼克号');
INSERT INTO `video` VALUES ('113', '只有我不存在的城市');
INSERT INTO `video` VALUES ('114', '名侦探柯南');
INSERT INTO `video` VALUES ('115', '武林外传');
INSERT INTO `video` VALUES ('116', '速度与激情');
INSERT INTO `video` VALUES ('117', '钢铁侠');
INSERT INTO `video` VALUES ('118', '美国队长');
INSERT INTO `video` VALUES ('119', '搞笑一家人');
INSERT INTO `video` VALUES ('120', '神探夏洛克');

SET FOREIGN_KEY_CHECKS = 1;
