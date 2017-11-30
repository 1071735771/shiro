/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-10-13 09:48:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dop_dev_tools_201707
-- ----------------------------
DROP TABLE IF EXISTS `dop_dev_tools_201707`;
CREATE TABLE `dop_dev_tools_201707` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='开发工具';

-- ----------------------------
-- Records of dop_dev_tools_201707
-- ----------------------------
INSERT INTO `dop_dev_tools_201707` VALUES ('1', '1', '1', '张三1', '2017-10-04 16:28:17', '1', '1', '12', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('2', '1', '2', '张三12', '2017-10-05 16:28:18', '1', '1', '11232', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('3', '1', '3', '张三13', '2017-10-09 16:28:19', '1', '1', '1123123', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('4', '2', '4', '张三14', '2017-10-09 16:25:55', '1', '1', '1', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('5', '3', '5', '张三15', '2017-10-09 16:28:21', '1', '1', '让我欺负 ', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('6', '4', '6', '张三16', '2017-10-09 16:28:22', '1', '1', '1阿斯顿发生', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('7', '5', '7', '李四1', '2017-10-09 16:28:26', '1', '1', '1啥打法', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('8', '6', '8', '李四12', '2017-10-09 16:28:25', '1', '1', '1时代啊啊是否风帆', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('9', '7', '9', '李四13', '2017-10-09 16:28:29', '1', '1', '废弃物发', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('10', '8', '1', '李四14', '2017-10-09 16:28:29', '1', '1', '1汪峰', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('11', '9', '2', '李四15', '2017-10-09 16:28:27', '1', '1', '1啥打法', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('12', '1', '3', '王五1', '2017-10-09 16:26:27', '1', '1', '1', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('13', '1', '4', '王五12', '2017-10-09 16:28:30', '1', '1', '1阿斯蒂芬', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('14', '1', '5', '王五13', '2017-10-09 16:28:31', '1', '1', '萨达', '2017-09-29');
INSERT INTO `dop_dev_tools_201707` VALUES ('15', '1', '6', '王五14', '2017-10-09 16:28:33', '1', '1', '啥打法', '2017-09-29');

-- ----------------------------
-- Table structure for dop_dev_tools_201708
-- ----------------------------
DROP TABLE IF EXISTS `dop_dev_tools_201708`;
CREATE TABLE `dop_dev_tools_201708` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='开发工具';

-- ----------------------------
-- Records of dop_dev_tools_201708
-- ----------------------------
INSERT INTO `dop_dev_tools_201708` VALUES ('1', '1', '1', '1', '2017-10-10 14:24:59', '登录', '成功', '1', '2017-09-29', '2');

-- ----------------------------
-- Table structure for dop_dev_tools_201709
-- ----------------------------
DROP TABLE IF EXISTS `dop_dev_tools_201709`;
CREATE TABLE `dop_dev_tools_201709` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='开发工具';

-- ----------------------------
-- Records of dop_dev_tools_201709
-- ----------------------------
INSERT INTO `dop_dev_tools_201709` VALUES ('1', '1', '1', '1', '2017-09-29 22:23:46', '1', '1', '1', '2017-09-29', '1');

-- ----------------------------
-- Table structure for `dop_dev_tools_201710`
-- ----------------------------
DROP TABLE IF EXISTS `dop_dev_tools_201710`;
CREATE TABLE `dop_dev_tools_201710` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='开发工具';

-- ----------------------------
-- Records of dop_dev_tools_201710
-- ----------------------------
INSERT INTO `dop_dev_tools_201710` VALUES ('1', '1', '登录', 'laji', '2017-10-13 08:28:18', '1', '成功', '1', '2017-10-12', '1');
INSERT INTO `dop_dev_tools_201710` VALUES ('2', '1', '登录', 'alaji', '2017-10-13 08:28:50', '2', '成功', '1', '2017-10-12', '1');
INSERT INTO `dop_dev_tools_201710` VALUES ('3', '1', '登录', 'laji', '2017-10-13 09:40:52', '1', '成功', '2', '2017-10-12', '1');

-- ----------------------------
-- Table structure for dop_mining_loginfo_201707
-- ----------------------------
DROP TABLE IF EXISTS `dop_mining_loginfo_201707`;
CREATE TABLE `dop_mining_loginfo_201707` (
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT '用户ID',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `open_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT '操作URL',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='挖掘工具';

-- ----------------------------
-- Records of dop_mining_loginfo_201707
-- ----------------------------

-- ----------------------------
-- Table structure for dop_mining_loginfo_201708
-- ----------------------------
DROP TABLE IF EXISTS `dop_mining_loginfo_201708`;
CREATE TABLE `dop_mining_loginfo_201708` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT '用户ID',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `open_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT '操作URL',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='挖掘工具';

-- ----------------------------
-- Records of dop_mining_loginfo_201708
-- ----------------------------
INSERT INTO `dop_mining_loginfo_201708` VALUES ('1', '1', '1', '1', '1', '2017-09-29 22:25:23', '1', '操作URL', '1', '1', '2017-09-29', '1');

-- ----------------------------
-- Table structure for dop_mining_loginfo_201709
-- ----------------------------
DROP TABLE IF EXISTS `dop_mining_loginfo_201709`;
CREATE TABLE `dop_mining_loginfo_201709` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT '用户ID',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `open_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT '操作URL',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='挖掘工具';

-- ----------------------------
-- Records of dop_mining_loginfo_201709
-- ----------------------------
INSERT INTO `dop_mining_loginfo_201709` VALUES ('1', '1', '1', '1', '1', '2017-09-29 22:25:48', '1', '操作URL', '1', '1', '2017-09-29', '1');

-- ----------------------------
-- Table structure for dop_openapi_201707
-- ----------------------------
DROP TABLE IF EXISTS `dop_openapi_201707`;
CREATE TABLE `dop_openapi_201707` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='openapi';

-- ----------------------------
-- Records of dop_openapi_201707
-- ----------------------------
INSERT INTO `dop_openapi_201707` VALUES ('11', '1', '1', '1', '2017-09-29 22:27:26', '1', '1', '1', '2017-09-29');

-- ----------------------------
-- Table structure for `dop_mining_loginfo_201710`
-- ----------------------------
DROP TABLE IF EXISTS `dop_mining_loginfo_201710`;
CREATE TABLE `dop_mining_loginfo_201710` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT '用户ID',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `open_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT '操作URL',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='挖掘工具';

-- ----------------------------
-- Records of dop_mining_loginfo_201710
-- ----------------------------
INSERT INTO `dop_mining_loginfo_201710` VALUES ('1', '1', '1', '1', '1', '2017-09-29 22:25:48', '1', '操作URL', '1', '1', '2017-09-29', '1');

-- ----------------------------
-- Table structure for dop_openapi_201708
-- ----------------------------
DROP TABLE IF EXISTS `dop_openapi_201708`;
CREATE TABLE `dop_openapi_201708` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='openapi';

-- ----------------------------
-- Records of dop_openapi_201708
-- ----------------------------

-- ----------------------------
-- Table structure for dop_openapi_201709
-- ----------------------------
DROP TABLE IF EXISTS `dop_openapi_201709`;
CREATE TABLE `dop_openapi_201709` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='openapi';

-- ----------------------------
-- Records of dop_openapi_201709
-- ----------------------------

-- ----------------------------
-- Table structure for `dop_openapi_201710`
-- ----------------------------
DROP TABLE IF EXISTS `dop_openapi_201710`;
CREATE TABLE `dop_openapi_201710` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_module` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `action_user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `action_info` text COLLATE utf8_unicode_ci COMMENT '操作信息',
  `action_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` date DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`log_id`),
  KEY `action_user` (`action_user`),
  KEY `action_time` (`action_time`),
  KEY `login_ip` (`login_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='openapi';

-- ----------------------------
-- Records of dop_openapi_201710
-- ----------------------------

-- ----------------------------
-- Table structure for log_warning_infor
-- ----------------------------
DROP TABLE IF EXISTS `log_warning_infor`;
CREATE TABLE `log_warning_infor` (
  `id` bigint(17) NOT NULL AUTO_INCREMENT COMMENT '告警日志信息表',
  `action_module` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作模块',
  `action_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT '用户ID',
  `action_user` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人名称',
  `action_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作时间',
  `action_info` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作信息',
  `open_url` varchar(512) COLLATE utf8_unicode_ci DEFAULT '操作URL',
  `action_result` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作结果',
  `login_ip` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `action_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of log_warning_infor
-- ----------------------------
INSERT INTO `log_warning_infor` VALUES ('1', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('2', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('3', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('4', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('5', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('6', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('7', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('8', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('9', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('10', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('11', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('12', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('13', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('14', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('15', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('16', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('17', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('18', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('19', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('20', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('21', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('22', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('23', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('24', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('25', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('26', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('27', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('28', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('29', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('30', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('31', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('32', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('33', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('34', '1', '登录', '用户ID', '1', '2017-10-12 13:29:06', '2', '操作URL', '2', '2', '2017-10-11');
INSERT INTO `log_warning_infor` VALUES ('35', '1', '登录', '用户ID', 'laji', '2017-10-13 08:28:18', '1', '操作URL', '成功', '1', '2017-10-12');
INSERT INTO `log_warning_infor` VALUES ('36', '1', '登录', '用户ID', 'alaji', '2017-10-13 08:28:50', '2', '操作URL', '成功', '1', '2017-10-12');

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` int(17) NOT NULL AUTO_INCREMENT,
  `interval_time` int(4) DEFAULT NULL COMMENT '两地登录的时间差',
  `error_try_number` int(4) DEFAULT NULL COMMENT '错误尝试次数',
  `common_number` int(4) DEFAULT NULL COMMENT '设置常用地址次数',
  `send_email` text COLLATE utf8_unicode_ci COMMENT '告警邮箱地址（多个邮箱用分号隔开）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='日志告警规则';

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES ('33', '3', '1', '1', '1');
INSERT INTO `rule` VALUES ('35', '2', '2', '2', '55515@www.com');

-- ----------------------------
-- Table structure for send
-- ----------------------------
DROP TABLE IF EXISTS `send`;
CREATE TABLE `send` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(300) DEFAULT NULL,
  `receiver_email` varchar(300) DEFAULT NULL COMMENT '接受者邮箱',
  `send_state` varchar(2) DEFAULT NULL COMMENT '发送状态 0：未发送  1:已发送',
  `send_content` varchar(300) DEFAULT NULL COMMENT '发送内容',
  `send_way` varchar(2) DEFAULT NULL COMMENT '发送方式选择，0：短信方式; 1：邮件方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send
-- ----------------------------
INSERT INTO `send` VALUES ('1', 'chuhaifeng', '1030056299@qq.com', '0', '有错误', '0');
INSERT INTO `send` VALUES ('2', 'super_admin', '777', '1', '777', '2');
INSERT INTO `send` VALUES ('5', 'chuhaifeng', '121', '0', '1223', '2');
INSERT INTO `send` VALUES ('6', 'chuhaifeng', '111', '0', '111', '2');

-- ----------------------------
-- Table structure for system_enum
-- ----------------------------
DROP TABLE IF EXISTS `system_enum`;
CREATE TABLE `system_enum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `describes` varchar(255) DEFAULT NULL,
  `groups` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_enum
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(300) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `full_name` varchar(300) DEFAULT NULL,
  `levels` int(11) NOT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_user` int(11) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `numbers` varchar(100) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级编号',
  `parent_ids` varchar(300) DEFAULT NULL COMMENT '所有父级编号',
  `menu_name` varchar(300) NOT NULL COMMENT '名称',
  `sort_number` int(11) DEFAULT NULL COMMENT '排序号',
  `href` varchar(500) DEFAULT NULL COMMENT '跳转链接',
  `target` varchar(15) DEFAULT NULL COMMENT '链接打开方式',
  `menu_icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_show` char(1) NOT NULL COMMENT '是否显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_user` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_user` int(11) DEFAULT NULL COMMENT '最近更新者',
  `modify_date` datetime DEFAULT NULL COMMENT '最近更新日期',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注信息',
  `is_delete` char(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', null, '系统设置', '1', '', null, 'fa-cogs', '0', '', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', null, '菜单管理', '2', '/admin/sys/menu/index', null, 'fa-book', '0', 'sys:menu:list', '0', '2016-06-21 14:44:34', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', null, '角色管理', '3', '/admin/sys/role/role_list', null, 'fa-group', '0', 'sys:role:list', '0', '2016-06-23 16:10:15', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', null, '用户管理', '4', '/admin/sys/user/index', null, 'fa-user', '0', 'sys:user:list', '0', '2016-06-23 23:01:31', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('86', '0', '', '审核', '6', '', '', 'fa-cogs', '0', '', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('89', '0', '', '告警', '7', '', '', 'fa-heart-o', '0', '', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('90', '89', '', '规则列表', '7', '/admin/rule/index', '', 'fa-heart-o', '0', 'send:index', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('101', '86', '', '迪科日志审查', '6', '/admin/dike/index', '', 'fa-user', '0', 'linux:index', '0', '2016-06-23 23:01:31', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('102', '0', null, '菜单1', '1', '12', null, '1', '0', '1', '1', '2017-10-12 15:12:08', null, '2017-10-12 15:12:02', '1', '1');
INSERT INTO `sys_menu` VALUES ('103', '0', null, '菜单2', '1', '222', null, '1', '0', '1', '1', '2017-10-12 15:21:01', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('104', '0', null, '菜单3', '1', 'wwww', null, '11', '0', '1', '1', '2017-10-12 15:24:05', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('105', '0', null, '菜单3', '1', '1', null, '1', '0', '1', '1', '2017-10-12 15:26:05', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('106', '2', null, '1', '1', '2', null, '1', '0', '1', '1', '2017-10-12 15:53:47', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('107', '86', null, '111', '1', '1', null, '1', '0', '1', '1', '2017-10-12 15:54:34', null, null, '1', '1');
INSERT INTO `sys_menu` VALUES ('108', '89', '', '告警列表', '7', '/admin/send/index', '', 'fa-heart-o', '0', 'send:index', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('110', '0', null, '', null, '', null, '', '0', '', '1', '2017-10-12 20:20:39', null, null, '', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `is_show` int(11) DEFAULT NULL COMMENT '是否可用 0 禁用 1：启用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测试角色', '1', '1', '2016-08-24 11:06:45', '2016-08-24 11:06:45');
INSERT INTO `sys_role` VALUES ('6', '超级管理员', '1', null, '2017-10-11 15:47:06', '2017-10-11 15:47:06');
INSERT INTO `sys_role` VALUES ('29', '3', '2222', '1', '2017-10-12 13:05:28', '2017-10-12 13:06:17');

-- ----------------------------
-- Table structure for sys_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_relation`;
CREATE TABLE `sys_role_menu_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `FK_FK_role_menu_menuId` (`menu_id`) USING BTREE,
  KEY `FK_FK_role_menu_roldId` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_relation_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `sys_role_menu_relation_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和系统菜单的关系表';

-- ----------------------------
-- Records of sys_role_menu_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `confound_code` varchar(50) DEFAULT NULL COMMENT '混淆码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `work_no` varchar(255) DEFAULT NULL COMMENT '工号',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `validate_type` varchar(50) DEFAULT NULL COMMENT '验证类型(用户激活,重置密码,邮箱激活)',
  `validate_key` varchar(100) DEFAULT NULL COMMENT '验证KEY',
  `rank` int(11) DEFAULT NULL COMMENT '等级',
  `user_type` int(11) DEFAULT '0' COMMENT '类型(0:会员,1:管理员)',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0:正常,1:锁定,2:待验证)',
  `province_id` int(11) DEFAULT NULL COMMENT '省份ID',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_FK_user_deptId` (`department`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'super_admin', 'c055854e660a205579fc47be366eb8cf0cc7a639', '37c092ed5dd71ab0', '13816082680@189.cn', '111122', '0013', '管理员', '0', null, null, null, null, '1', '0', null, null, '大数据业务部', '2016-06-17 14:03:33', '2017-10-11 10:37:31');
INSERT INTO `sys_user` VALUES ('2', 'chu', 'f87b26a9fb89a108d0ac9bbfe5a66f861749bdc1', 'af134b21b7116a2f', null, '18311160249', null, 'chu', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 15:35:43', '2017-10-11 15:35:43');
INSERT INTO `sys_user` VALUES ('3', 'chu1', '80d715ef3c2b053a8c36a3b1fdeafcd4816e575d', 'babc036f58da133c', null, '18311160249', null, 'chu1', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 15:40:51', '2017-10-11 15:45:30');
INSERT INTO `sys_user` VALUES ('4', 'hh', 'f3c4791559acc631dc4f9d8103fb7489bb6150a0', 'a653543abb4cdb62', null, '18311160249', null, 'hh', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 19:29:34', '2017-10-11 19:29:34');
INSERT INTO `sys_user` VALUES ('5', '1', '0a30731f78aa2868e57c29bae0d5699cacfb7a06', '45d51cd28c3189e1', null, '18311160249', null, '2', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 22:28:17', '2017-10-11 22:28:17');
INSERT INTO `sys_user` VALUES ('6', '1', '98f9e5708c1fc42c610e5a93d102496f02fa0f8a', '744dfadb74b173ab', null, '18311160249', null, '1', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 22:50:57', '2017-10-11 22:50:57');
INSERT INTO `sys_user` VALUES ('7', '1', 'eb0c16960a8f3db6398a09fe33d92503dc75041f', 'f0e258cf8c4c95aa', null, '18311160249', null, '1', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 22:58:35', '2017-10-11 22:58:35');
INSERT INTO `sys_user` VALUES ('8', '12', '8f2f9ef57148899067e72e549f23f7a6ded66962', 'ac68d52890cacb58', null, '18311160249', null, '12', '2', null, null, null, null, null, '0', null, null, null, '2017-10-11 23:03:09', '2017-10-11 23:03:09');
INSERT INTO `sys_user` VALUES ('9', 'super_admin', '766b10eb9fd8858c820239960687a0f1c0dc29e7', '1de0cdb8e95eb45f', null, '8545555555', null, 'super_admin', '2', null, null, null, null, null, '0', null, null, null, '2017-10-12 20:01:18', '2017-10-12 20:01:18');

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_FK_user_role_roleId` (`role_id`) USING BTREE,
  KEY `FK_FK_user_role_userId` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_role_relation_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_user_role_relation_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关系表';

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
