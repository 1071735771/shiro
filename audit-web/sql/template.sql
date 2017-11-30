/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-10-09 15:35:16
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', null, '系统设置', '1', '', null, 'fa-cogs', '0', '', '0', '2016-06-21 14:34:07', '1', '2016-09-01 23:25:03', '', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', null, '菜单管理', '2', '/admin/sys/menu/menu_list', null, 'fa-book', '0', 'sys:menu:list', '0', '2016-06-21 14:44:34', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', null, '角色管理', '3', '/admin/sys/role/role_list', null, 'fa-group', '0', 'sys:role:list', '0', '2016-06-23 16:10:15', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', null, '用户管理', '4', '/admin/sys/user/user_list', null, 'fa-user', '0', 'sys:user:list', '0', '2016-06-23 23:01:31', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('12', '2', null, '查看', '1', '', null, '', '1', 'sys:menu:list', '0', '2016-06-24 10:31:18', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('13', '2', null, '编辑', '2', '', null, '', '1', 'sys:menu:edit', '0', '2016-06-24 12:43:48', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('14', '2', null, '删除', '3', '', null, '', '1', 'sys:menu:delete', '0', '2016-06-24 12:44:46', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('16', '2', null, '批量排序', '4', '', null, '', '1', 'sys:menu:batchsort', '0', '2016-06-24 12:46:17', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('17', '3', null, '查看', '1', '', null, '', '1', 'sys:role:list', '0', '2016-06-24 12:48:29', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('18', '3', null, '编辑', '2', '', null, '', '1', 'sys:role:eidt', '0', '2016-06-24 13:18:21', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('19', '3', null, '删除', '3', '', null, '', '1', 'sys:role:delete', '0', '2016-06-24 13:19:03', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('20', '4', null, '查看', '1', '', null, '', '1', 'sys:user:list', '0', '2016-06-24 13:20:04', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('21', '4', null, '编辑', '2', '', null, '', '1', 'sys:user:edit', '0', '2016-06-24 13:20:52', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('22', '4', null, '删除', '3', '', null, '', '1', 'sys:user:delete', '0', '2016-06-24 13:21:28', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('67', '0', null, '资源申请', '2', '', null, 'fa-desktop', '0', '', '1', '2016-08-23 09:31:15', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('68', '67', null, '账户开通申请', '1', '/admin/apply/account/list', null, 'fa-user-plus', '0', 'apply:account:list', '1', '2016-08-23 09:47:24', '1', '2016-09-01 23:25:04', 'IT部大数据平台账户开通申请管理', '0');
INSERT INTO `sys_menu` VALUES ('69', '68', null, '查看', '1', '', null, '', '1', 'apply:account:list', '1', '2016-08-23 09:53:43', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('70', '68', null, '申请', '2', '', null, '', '1', 'apply:account:apply', '1', '2016-08-23 09:54:46', '1', '2016-09-01 23:25:04', '', '0');
INSERT INTO `sys_menu` VALUES ('71', '68', null, '审核', '3', '', null, '', '1', 'apply:account:audit', '1', '2016-08-23 09:59:52', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('72', '67', null, '服务套餐管理', '4', '/admin/apply/packages/list', null, 'fa-list', '0', 'apply:packages:list', '1', '2016-08-24 09:41:17', '1', '2016-09-01 23:25:05', '大数据挖掘能力服务套餐', '0');
INSERT INTO `sys_menu` VALUES ('73', '72', null, '查看', '1', '', null, '', '1', 'apply:packages:list', '1', '2016-08-24 09:41:43', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('74', '67', null, 'KV资源申请', '2', 'admin/apply/kv/list.do', null, 'fa-table', '0', 'apply:kv:list', '1', '2016-08-29 09:00:40', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('75', '74', null, '查看', '1', '', null, '', '1', 'apply:kv:list', '1', '2016-08-29 09:01:08', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('76', '74', null, '申请', '2', '', null, '', '1', 'apply:kv:apply', '1', '2016-08-29 09:01:41', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('77', '74', null, '审核', '3', '', null, '', '1', 'apply:kv:audit', '1', '2016-08-29 09:04:01', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('78', '67', null, '使用方管理', '5', 'admin/apply/useinfo/list', null, 'fa-user-secret', '0', 'apply:useinfo:list', '1', '2016-08-29 09:13:43', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('79', '78', null, '查看', '1', '', null, '', '1', 'apply:useinfo:list', '1', '2016-08-29 09:14:23', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('80', '78', null, '编辑', '2', '', null, '', '1', 'apply:useinfo:edit', '1', '2016-08-29 09:14:46', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('81', '78', null, '删除', '3', '', null, '', '1', 'apply:useinfo:delete', '1', '2016-08-29 09:15:28', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('82', '67', null, '作业申请', '3', 'admin/apply/task/list', null, 'fa-tasks', '0', 'apply:task:list', '1', '2016-09-01 23:24:49', '1', '2016-09-01 23:25:05', '', '0');
INSERT INTO `sys_menu` VALUES ('83', '82', null, '查看', '1', '', null, '', '1', 'apply:task:list', '1', '2016-09-01 23:25:33', null, null, '', '0');
INSERT INTO `sys_menu` VALUES ('84', '82', null, '申请', '2', '', null, '', '1', 'apply:task:apply', '1', '2016-09-01 23:25:59', null, null, '', '0');
INSERT INTO `sys_menu` VALUES ('85', '82', null, '审核', '3', '', null, '', '1', 'apply:task:audit', '1', '2016-09-01 23:26:25', null, null, '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `is_show` int(11) DEFAULT NULL COMMENT '是否可用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测试角色', '', '0', '2016-08-24 11:06:45', '2016-08-24 11:06:45');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色和系统菜单的关系表';

-- ----------------------------
-- Records of sys_role_menu_relation
-- ----------------------------
INSERT INTO `sys_role_menu_relation` VALUES ('1', '1', '67');
INSERT INTO `sys_role_menu_relation` VALUES ('2', '1', '68');
INSERT INTO `sys_role_menu_relation` VALUES ('3', '1', '69');
INSERT INTO `sys_role_menu_relation` VALUES ('4', '1', '70');
INSERT INTO `sys_role_menu_relation` VALUES ('5', '1', '71');
INSERT INTO `sys_role_menu_relation` VALUES ('6', '1', '72');
INSERT INTO `sys_role_menu_relation` VALUES ('7', '1', '73');

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
  `user_type` int(11) NOT NULL DEFAULT '0' COMMENT '类型(0:会员,1:管理员)',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0:正常,1:锁定,2:待验证)',
  `province_id` int(11) DEFAULT NULL COMMENT '省份ID',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_FK_user_deptId` (`department`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'super_admin', 'ff3d907ad68798860b15ae77328ad11fd4ff4f19', '056a0b37e2549271', '13816082680@189.cn', '111122', '0013', '管理员', '0', null, null, null, null, '1', '0', null, null, '大数据业务部', '2016-06-17 14:03:33', '2016-09-07 12:14:33');

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