/*
Navicat MySQL Data Transfer

Source Server         : 149.28.142.243
Source Server Version : 80025
Source Host           : 149.28.142.243:3306
Source Database       : include

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-01-31 08:07:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_instagram_query
-- ----------------------------
DROP TABLE IF EXISTS `t_instagram_query`;
CREATE TABLE `t_instagram_query` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ins_id` bigint NOT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `short_code` varchar(255) DEFAULT NULL,
  `display_url` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `taken_at_timestamp` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `file_id` bigint DEFAULT NULL,
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重新备注',
  `ischild` int DEFAULT '0' COMMENT '是否是详情页',
  `createtime` datetime DEFAULT (sysdate()) COMMENT '创建时间',
  `end_cursor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '下一页',
  `like_count` int DEFAULT '0' COMMENT '点赞数',
  `location_id` bigint DEFAULT NULL,
  `location_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5406 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*
Navicat MySQL Data Transfer

Source Server         : 149.28.142.243
Source Server Version : 80025
Source Host           : 149.28.142.243:3306
Source Database       : include

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-01-31 08:13:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime NOT NULL DEFAULT (sysdate()) COMMENT '创建时间',
  `http_header` varchar(8024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
