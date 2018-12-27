/*
 Navicat Premium Data Transfer

 Source Server         : sql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : house

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 19/11/2018 22:16:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `city_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市名称',
  `city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, '北京市', '110000');

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `city_code` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '小区名称',
  `city_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1, '110000', '西山华府', '北京市');
INSERT INTO `community` VALUES (2, '110000', '万柳书苑', '北京市');
INSERT INTO `community` VALUES (3, '110000', '太阳公元', '北京市');
INSERT INTO `community` VALUES (4, '110000', '橡树湾', '北京市');
INSERT INTO `community` VALUES (5, '110000', '阳光丽景', '北京市');
INSERT INTO `community` VALUES (6, '110000', '紫苑华府', '北京市');
INSERT INTO `community` VALUES (7, '110000', '北街嘉园', '北京市');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '房产名称',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '1:销售，2:出租',
  `price` int(11) NOT NULL COMMENT '单元元',
  `images` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `area` int(11) NOT NULL DEFAULT 0 COMMENT '面积',
  `beds` int(11) NOT NULL DEFAULT 0 COMMENT '卧室数量',
  `baths` int(11) NOT NULL DEFAULT 0 COMMENT '卫生间数量',
  `rating` double NOT NULL DEFAULT 0 COMMENT '评级',
  `remarks` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '房产描述',
  `properties` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性',
  `floor_plan` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '户型图',
  `tags` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签',
  `create_time` date NOT NULL COMMENT '创建时间',
  `city_id` int(11) NOT NULL DEFAULT 0 COMMENT '城市名称',
  `community_id` int(11) NOT NULL DEFAULT 0 COMMENT '小区名称',
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '房产地址',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '1-上架，2-下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (22, '西山华府 120平', 1, 600, '/1493370993/property-07.jpg,/1493370999/property-08.jpg', 120, 2, 12, 5, '西山华府 120平西山华府 120平西山华府 120平西山华府 120平西山华府 120平', '得房率高,户型好,落地窗', '', '', '2017-04-28', 1, 1, '西山华府', 1);
INSERT INTO `house` VALUES (23, '万柳书苑 180平 南北通透', 1, 800, '/1493381459/property-detail-01.jpg,/1493381460/property-detail-02.jpg,/1493381462/property-detail-03.jpg', 120, 2, 2, 5, '万柳书苑 180平 南北通透', '满五年,采光好,价格合理,税少,学区房', '', '', '2017-04-28', 1, 2, '清河中街', 1);
INSERT INTO `house` VALUES (24, '阳光丽景 三面采光 高楼层', 1, 140, '/1493432771/property-11.jpg,/1493432771/property-12.jpg,/1493432771/property-13.jpg', 140, 2, 2, 4, '阳光丽景 三面采光 高楼层', '南北通透,环境好,带阳台', '/1493432771/floor-plan-01.jpg,/1493432771/floor-plan-02.jpg', '', '2017-04-29', 1, 5, '西城区', 1);
INSERT INTO `house` VALUES (25, '橡树湾 南北通透 三居室', 2, 800, '/1494256315/property-12.jpg,/1494256315/property-13.jpg', 130, 2, 2, 0, '橡树湾 南北通透橡树湾 南北通透橡树湾 南北通透橡树湾 南北通透', '得房率高,临地铁,户型好,没有遮挡,落地窗,精装修', '/1494256315/floor-plan-01.jpg,/1494256315/floor-plan-02.jpg', '', '2017-05-08', 1, 4, '清河中街', 1);
INSERT INTO `house` VALUES (26, '中央广场3层大四居', 1, 500, '/1500801115/property-09.jpg,/1500801115/property-10.jpg,/1500801115/property-11.jpg', 400, 3, 3, 0, '中央广场3层大四居豪华装修', '满五年,满两年,采光好,高楼层,环境好,价格合理,楼龄新,带阳台,税少,得房率高,临地铁', '', '', '2018-01-06', 1, 3, '中北后海面一号', 1);
INSERT INTO `house` VALUES (27, '北街嘉园 全南向 南北通透', 1, 300, '/1515253220/property-13.jpg,/1515253220/property-12.jpg,/1515253220/property-11.jpg', 299, 3, 3, 0, '北街嘉园 全南向 南北通透', '满两年,南北通透,高楼层,环境好,楼龄新,带阳台,得房率高,临地铁', '/1515253246/floor-plan-01.jpg', '', '2018-01-06', 1, 7, '清河中街', 1);

-- ----------------------------
-- Table structure for house_msg
-- ----------------------------
DROP TABLE IF EXISTS `house_msg`;
CREATE TABLE `house_msg`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `msg` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '消息',
  `create_time` date NOT NULL COMMENT '创建时间',
  `agent_id` bigint(20) NOT NULL COMMENT '经纪人id',
  `house_id` bigint(20) NOT NULL COMMENT '房屋id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_msg
-- ----------------------------
INSERT INTO `house_msg` VALUES (12, '11', '2017-05-08', 7, 24, '11');
INSERT INTO `house_msg` VALUES (13, '111', '2017-05-08', 7, 24, '111');
INSERT INTO `house_msg` VALUES (14, '111', '2018-01-06', 7, 23, '11');

-- ----------------------------
-- Table structure for house_user
-- ----------------------------
DROP TABLE IF EXISTS `house_user`;
CREATE TABLE `house_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `house_id` bigint(20) NOT NULL COMMENT '房屋id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `type` tinyint(1) NOT NULL COMMENT '1-售卖，2-收藏',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `house_id_user_id_type`(`house_id`, `user_id`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_user
-- ----------------------------
INSERT INTO `house_user` VALUES (1, 11, 7, '2017-02-26', 1);
INSERT INTO `house_user` VALUES (2, 12, 7, '2017-02-26', 1);
INSERT INTO `house_user` VALUES (5, 15, 7, '2017-02-26', 2);
INSERT INTO `house_user` VALUES (7, 16, 7, '2017-03-21', 2);
INSERT INTO `house_user` VALUES (8, 14, 7, '2017-04-05', 1);
INSERT INTO `house_user` VALUES (9, 14, 7, '2017-04-10', 2);
INSERT INTO `house_user` VALUES (10, 17, 7, '2017-04-22', 2);
INSERT INTO `house_user` VALUES (11, 18, 7, '2017-04-28', 1);
INSERT INTO `house_user` VALUES (12, 19, 7, '2017-04-28', 1);
INSERT INTO `house_user` VALUES (13, 20, 7, '2017-04-28', 1);
INSERT INTO `house_user` VALUES (14, 22, 7, '2017-04-28', 1);
INSERT INTO `house_user` VALUES (16, 23, 7, '2017-04-28', 1);
INSERT INTO `house_user` VALUES (17, 24, 7, '2017-04-29', 1);
INSERT INTO `house_user` VALUES (18, 24, 14, '2017-04-29', 2);
INSERT INTO `house_user` VALUES (20, 24, 7, '2017-05-08', 2);
INSERT INTO `house_user` VALUES (21, 23, 7, '2017-05-08', 2);
INSERT INTO `house_user` VALUES (22, 25, 25, '2017-05-08', 1);
INSERT INTO `house_user` VALUES (23, 23, 38, '2018-01-06', 2);
INSERT INTO `house_user` VALUES (24, 24, 38, '2018-01-06', 2);
INSERT INTO `house_user` VALUES (25, 25, 38, '2018-01-06', 2);
INSERT INTO `house_user` VALUES (26, 26, 38, '2018-01-06', 1);
INSERT INTO `house_user` VALUES (27, 27, 38, '2018-01-06', 1);

SET FOREIGN_KEY_CHECKS = 1;
