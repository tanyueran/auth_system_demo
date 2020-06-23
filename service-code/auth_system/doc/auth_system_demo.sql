/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : auth_system_demo

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/06/2020 21:34:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for button_table
-- ----------------------------
DROP TABLE IF EXISTS `button_table`;
CREATE TABLE `button_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `button_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '按钮中文名称',
  `button_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '按钮标识',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`button_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '按钮权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of button_table
-- ----------------------------
INSERT INTO `button_table` VALUES ('1', '新增', 'BTN_ADD', '通用按钮', '2020-06-22 16:09:05', '2020-06-22 16:10:19');
INSERT INTO `button_table` VALUES ('1275058104270196736', '测试按钮', 'BTN_TEST', '123', '2020-06-22 21:28:49', '2020-06-22 21:28:57');
INSERT INTO `button_table` VALUES ('2', '删除', 'BTN_DEL', '通用按钮', '2020-06-22 16:09:13', '2020-06-22 16:10:21');
INSERT INTO `button_table` VALUES ('3', '更新', 'BTN_UPDATE', '通用按钮', '2020-06-22 16:09:50', '2020-06-22 16:10:22');
INSERT INTO `button_table` VALUES ('4', '查询', 'BTN_QUERY', '通用按钮', '2020-06-22 16:10:05', '2020-06-22 16:10:23');

-- ----------------------------
-- Table structure for menu_button_table
-- ----------------------------
DROP TABLE IF EXISTS `menu_button_table`;
CREATE TABLE `menu_button_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单主键',
  `button_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '按钮主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单按钮中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_button_table
-- ----------------------------
INSERT INTO `menu_button_table` VALUES ('1', '2', '1');
INSERT INTO `menu_button_table` VALUES ('10', '4', '2');
INSERT INTO `menu_button_table` VALUES ('11', '4', '3');
INSERT INTO `menu_button_table` VALUES ('12', '4', '4');
INSERT INTO `menu_button_table` VALUES ('13', '5', '1');
INSERT INTO `menu_button_table` VALUES ('14', '5', '2');
INSERT INTO `menu_button_table` VALUES ('15', '5', '3');
INSERT INTO `menu_button_table` VALUES ('16', '5', '4');
INSERT INTO `menu_button_table` VALUES ('2', '2', '2');
INSERT INTO `menu_button_table` VALUES ('3', '2', '3');
INSERT INTO `menu_button_table` VALUES ('4', '2', '4');
INSERT INTO `menu_button_table` VALUES ('5', '3', '1');
INSERT INTO `menu_button_table` VALUES ('6', '3', '2');
INSERT INTO `menu_button_table` VALUES ('7', '3', '3');
INSERT INTO `menu_button_table` VALUES ('8', '3', '4');
INSERT INTO `menu_button_table` VALUES ('9', '4', '1');

-- ----------------------------
-- Table structure for menu_table
-- ----------------------------
DROP TABLE IF EXISTS `menu_table`;
CREATE TABLE `menu_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级主键',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单中文名',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单标识',
  `menu_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单类型（0、一级菜单，1、二级菜单）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '其他的数据标识',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`menu_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_table
-- ----------------------------
INSERT INTO `menu_table` VALUES ('1', NULL, '系统管理', 'systemManager', '0', '系统管理分类', NULL, '2020-06-22 16:18:31', '2020-06-22 16:18:31');
INSERT INTO `menu_table` VALUES ('2', '1', '用户管理', 'userManager', '1', '用户管理', NULL, '2020-06-22 16:18:39', '2020-06-22 16:18:59');
INSERT INTO `menu_table` VALUES ('3', '1', '角色管理', 'roleManager', '1', '角色管理', NULL, '2020-06-22 16:19:36', '2020-06-22 16:19:36');
INSERT INTO `menu_table` VALUES ('4', '1', '菜单管理', 'menuManager', '1', '菜单管理', NULL, '2020-06-22 16:20:00', '2020-06-22 16:20:00');
INSERT INTO `menu_table` VALUES ('5', '1', '按钮管理', 'buttonManager', '1', '按钮管理', NULL, '2020-06-22 16:20:28', '2020-06-22 16:20:28');

-- ----------------------------
-- Table structure for role_menu_table
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_table`;
CREATE TABLE `role_menu_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色主键',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_table
-- ----------------------------
INSERT INTO `role_menu_table` VALUES ('1', '2', '2');
INSERT INTO `role_menu_table` VALUES ('2', '2', '3');
INSERT INTO `role_menu_table` VALUES ('3', '2', '4');
INSERT INTO `role_menu_table` VALUES ('4', '2', '5');

-- ----------------------------
-- Table structure for role_table
-- ----------------------------
DROP TABLE IF EXISTS `role_table`;
CREATE TABLE `role_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色的中文名称',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色的标识',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_table
-- ----------------------------
INSERT INTO `role_table` VALUES ('1', '超级管理员', 'ROOT', '超级管理员', '2020-06-22 16:12:39', '2020-06-22 16:13:16');
INSERT INTO `role_table` VALUES ('2', '普通管理员', 'MANAGER', '普通管理员', '2020-06-22 16:13:02', '2020-06-22 16:13:21');
INSERT INTO `role_table` VALUES ('3', '用户', 'USER', '用户', '2020-06-22 16:13:12', '2020-06-22 16:13:27');

-- ----------------------------
-- Table structure for user_role_table
-- ----------------------------
DROP TABLE IF EXISTS `user_role_table`;
CREATE TABLE `user_role_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_table
-- ----------------------------
INSERT INTO `user_role_table` VALUES ('1', '1', '1');
INSERT INTO `user_role_table` VALUES ('2', '2', '2');
INSERT INTO `user_role_table` VALUES ('3', '3', '3');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件id',
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别（1男2女）',
  `dec` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_user_code`(`user_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('1', '超级管理员', NULL, 'admin', 'password', 1, '超级管理员', '2020-06-22 16:11:28', '2020-06-22 17:02:57');
INSERT INTO `user_table` VALUES ('2', '普通管理员', NULL, 'manager', 'password', 2, '管理员', '2020-06-22 16:11:55', '2020-06-22 16:11:55');
INSERT INTO `user_table` VALUES ('3', '用户', NULL, 'user01', 'password', 1, '用户1', '2020-06-22 16:12:15', '2020-06-22 16:12:15');

SET FOREIGN_KEY_CHECKS = 1;
