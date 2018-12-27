/*
 Navicat Premium Data Transfer

 Source Server         : sql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 19/11/2018 22:16:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agency
-- ----------------------------
DROP TABLE IF EXISTS `agency`;
CREATE TABLE `agency`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '经纪机构名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电子邮件',
  `about_us` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电话',
  `web_site` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '网站',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agency
-- ----------------------------
INSERT INTO `agency` VALUES (1, '恋家', '恋家地址', '010-89898989', '010@gmail.com', '1', '1', '1');
INSERT INTO `agency` VALUES (2, '交点房产', '交点房产地址', '010-87898989', '020@gmail.com', '1', '1', '1');
INSERT INTO `agency` VALUES (3, '唛田', '唛田地址', '010-88898989', '030@gmail.com', '1', '1', '1');
INSERT INTO `agency` VALUES (4, '安聚客', '安聚客地址', '010-81898989', '040@gmail.com', '1', '1', '1');

-- ----------------------------
-- Table structure for agent_msg
-- ----------------------------
DROP TABLE IF EXISTS `agent_msg`;
CREATE TABLE `agent_msg`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `msg` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '消息',
  `create_time` date NOT NULL COMMENT '创建时间',
  `agent_id` bigint(20) NOT NULL COMMENT '经纪人id',
  `house_id` bigint(20) NOT NULL COMMENT '房屋id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agent_msg
-- ----------------------------
INSERT INTO `agent_msg` VALUES (1, 'spring_boot@163.com', '2017-02-12', 13, 5, 'sadfasd');
INSERT INTO `agent_msg` VALUES (2, 'd', '2017-03-27', 13, 9, '111');
INSERT INTO `agent_msg` VALUES (3, '1', '2017-04-29', 7, 24, '11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电子邮件',
  `aboutme` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '自我介绍',
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '加密密码',
  `avatar` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像图片',
  `type` tinyint(1) NOT NULL COMMENT '1:普通用户，2:房产经纪人',
  `create_time` date NOT NULL COMMENT '创建时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用,1启用，0停用',
  `agency_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属经纪机构',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (7, 'hello99', 'hello', 'spring_boot@163.com', '2255', '6b0f3585b8a229b38f9c1a0ae0c51690', '/1493438523/4.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (8, 'hello1', 'hello', 'spring_boot2@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/1.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (9, 'hello2', 'hello', 'spring_boot3@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/2.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (10, 'hello3', 'hello', 'spring_boot4@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/3.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (11, 'hello4', 'hello', 'spring_boot5@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/4.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (12, 'hello5', 'hello', 'spring_boot6@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/5.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (13, 'hello6', 'hello', 'spring_boot7@163.com', '2255', '75fb23b165249cedeb60544c4095ec99', '/1493438523/6.jpg', 2, '2017-01-31', 1, 1);
INSERT INTO `user` VALUES (14, '张晶', '18909090909', 'spring_boot8@163.com', '张晶', '75fb23b165249cedeb60544c4095ec99', '/1493438523/7.jpg', 2, '2017-04-29', 1, 0);
INSERT INTO `user` VALUES (15, '王将军', '18909090909', 'sin1ewy4@163.com', '王将军', '75fb23b165249cedeb60544c4095ec99', '/1493439911/client-01.jpg', 2, '2017-04-29', 1, 0);
INSERT INTO `user` VALUES (20, '李四', '18909090909', 'mooc_hello@163.com', '李啊', '75fb23b165249cedeb60544c4095ec99', '/1493442257/member-03.jpg', 1, '2017-04-29', 1, 0);
INSERT INTO `user` VALUES (24, '刘大', '18909090909', 'ugx1zl@163.com', '刘大啊', '75fb23b165249cedeb60544c4095ec99', '/1493452900/client-01.jpg', 1, '2017-04-29', 1, 0);
INSERT INTO `user` VALUES (25, '1', '18909090909', 'mooc_hello2@163.com', '11111', '75fb23b165249cedeb60544c4095ec99', '/1494253659/agent-01.jpg', 1, '2017-05-08', 1, 0);
INSERT INTO `user` VALUES (38, 'mooc_hello', '12090909090', 'mooc_hello1@163.com', '新用户111', '3bf8013c27e39f2bb7060368bf5f6e49', '/1514816998/client-01.jpg', 1, '2018-01-01', 1, 0);
INSERT INTO `user` VALUES (42, '王大治', '12090909090', 'ganglihu@163.com', '111111', '3bf8013c27e39f2bb7060368bf5f6e49', '/1515259075/agent-01.jpg', 2, '2018-01-07', 1, 0);
INSERT INTO `user` VALUES (56, 'hellobody', '13330298446', '111@qq.com', 'fdsfs', '3feb21cc8bc017b54f8edc17b534172f', '/1542454203/client-01.jpg', 1, '2018-11-17', 0, 0);
INSERT INTO `user` VALUES (60, 'gerry123', '1333029845', '271314998@qq.com', '我很帅', 'cc3aa4876e823c5e9e37a46b83f0451d', 'FmMCUHSYBAG4hQHVw-WkXKkmVOKk', 2, '2018-11-19', 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
