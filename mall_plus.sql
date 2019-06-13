/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : mall_plus

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 13/06/2019 18:24:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `operation_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志描述',
  `action_args` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `user_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户主键',
  `class_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `model_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `action` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `succeed` int(2) NULL DEFAULT NULL COMMENT '是否成功 1:成功 2异常',
  `message` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常堆栈信息',
  PRIMARY KEY (`operation_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `per_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限代码字符串',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `per_code`(`per_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '查看用户', 'user:view');
INSERT INTO `sys_permission` VALUES (2, '操作用户', 'user:edit');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin');
INSERT INTO `sys_role` VALUES (2, 'customer');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1, 2);
INSERT INTO `sys_role_permission` VALUES (3, 2, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帐号',
  `password` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'QUJBNUYyM0M3OTNEN0I4MUFBOTZBOTkwOEI1NDI0MUE=', 'admin', '2019-03-07 16:47:05');
INSERT INTO `sys_user` VALUES (2, 'wang', 'RTM3MDJENjU0MDg5QURFNUZEQTkxNTNCOEZFQ0MzMkM=', 'wang', '2019-03-07 16:47:05');
INSERT INTO `sys_user` VALUES (3, 'guest', 'QTNCMzMwREY3MkMwQjRGQjNBQzUyOTM0NTFFMzJCNDg=', 'guest', '2019-03-07 16:47:05');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gzh_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公众号openid',
  `xcx_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序openid',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'unionid',
  `register_from` int(2) NULL DEFAULT NULL COMMENT '注册来源 1:h5 2:android 3:ios 4:小程序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES (4, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1anSA3Jxibms1BrdTpxSkfL0UMATDwSkgzPicgh0hialao5v6GhummpC7cnxyNBupkHb3UktajnLdQ/132', 'A二师兄🏆', 'o0h5h1Vvlmp7Z4leij7gnuTg65ak', NULL, NULL, 1, '2019-06-13 18:06:32', '2019-06-13 18:06:32', NULL, 0);
INSERT INTO `user_base` VALUES (5, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1anSA3Jxibms1BrdTpxSkfL0UMATDwSkgzPicgh0hialao5v6GhummpC7cnxyNBupkHb3UktajnLdQ/132', 'A二师兄🏆', 'o0h5h1Vvlmp7Z4leij7gnuTg65ak', NULL, NULL, 1, '2019-06-13 18:07:25', '2019-06-13 18:07:25', NULL, 0);
INSERT INTO `user_base` VALUES (6, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1anSA3Jxibms1BrdTpxSkfL0UMATDwSkgzPicgh0hialao5v6GhummpC7cnxyNBupkHb3UktajnLdQ/132', 'A二师兄🏆', 'o0h5h1Vvlmp7Z4leij7gnuTg65ak', NULL, NULL, 1, '2019-06-13 18:08:08', '2019-06-13 18:08:08', NULL, 0);
INSERT INTO `user_base` VALUES (7, NULL, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1anSA3Jxibms1BrdTpxSkfL0UMATDwSkgzPicgh0hialao5v6GhummpC7cnxyNBupkHb3UktajnLdQ/132', 'A二师兄🏆', 'o0h5h1Vvlmp7Z4leij7gnuTg65ak', NULL, NULL, 1, '2019-06-13 18:17:02', '2019-06-13 18:17:02', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
