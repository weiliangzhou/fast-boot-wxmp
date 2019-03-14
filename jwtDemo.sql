/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : shirojwt

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 14/03/2019 20:26:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能描述',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `version` int(11) NULL DEFAULT NULL,
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1381 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (5, '2019-02-25 18:26:46', '绑定当前用户微信信息和手机', 'X15921', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4178\",\"phone\":\"18807614397\"}');
INSERT INTO `sys_log` VALUES (6, '2019-02-25 18:29:19', '绑定当前用户微信信息的其它账号的手机', 'X15922', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"6171\",\"phone\":\"18117388117\"}');
INSERT INTO `sys_log` VALUES (7, '2019-02-25 18:41:02', '绑定当前用户微信信息和手机', 'X6225', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4643\",\"phone\":\"13805221233\"}');
INSERT INTO `sys_log` VALUES (8, '2019-02-25 18:57:00', '代理补货', 'X0518', NULL, '分销-前台-销售卡订单', '[类名]:com.xc.sale.web.controller.CardOrderController,[方法]:supplyCard,[参数]:{\"agentId\":9818,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/c870fd319d0a46ff98abc74da1597bbd.jpg\",\"saleCardNum\":10}');
INSERT INTO `sys_log` VALUES (9, '2019-02-25 19:04:19', '完善用户信息', 'X0994', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"茶江湖&搜企-王赞宁（王碧来）\",\"idCardType\":1,\"province\":350000,\"city\":350200,\"phone\":\"13599541354\",\"prefecture\":350206,\"idCardNum\":\"350524198310234019\",\"detailAddress\":\"枋湖路9号军梦创业园A栋三楼\"}');
INSERT INTO `sys_log` VALUES (10, '2019-02-25 19:06:18', '新增代理或vip', 'X0053', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"18914805800\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/279d13a153954e7ea1170840973a1e30.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":53,\"userName\":\"渠慎举\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (11, '2019-02-25 19:13:25', '代理补货', 'X0053', NULL, '分销-前台-销售卡订单', '[类名]:com.xc.sale.web.controller.CardOrderController,[方法]:supplyCard,[参数]:{\"agentId\":9749,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/571c932715e94f08b7ad4517a5dd1aeb.jpeg\",\"saleCardNum\":50}');
INSERT INTO `sys_log` VALUES (12, '2019-02-25 19:14:08', '新增代理或vip', 'X0097', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15361309857\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/ff2df0cee9e044d5ad951b5b54e1a419.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":97,\"userName\":\"廖丰涛\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (13, '2019-02-25 19:20:30', '完善用户信息', 'X15925', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"廖丰涛\",\"idCardType\":1,\"province\":440000,\"city\":442000,\"phone\":\"15361309857\",\"prefecture\":442000,\"idCardNum\":\"450330198401051656\",\"detailAddress\":\"广东中山市小榄镇宝丰市场金龙西街8号\"}');
INSERT INTO `sys_log` VALUES (14, '2019-02-25 19:23:15', '绑定当前用户微信信息和手机', 'X15926', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4168\",\"phone\":\"15513086819\"}');
INSERT INTO `sys_log` VALUES (15, '2019-02-25 19:25:05', '绑定当前用户微信信息和手机', 'X15860', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"9687\",\"phone\":\"18914805800\"}');
INSERT INTO `sys_log` VALUES (16, '2019-02-25 19:27:01', '完善用户信息', 'X15918', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"孙菊莲\",\"idCardType\":1,\"province\":440000,\"city\":440300,\"phone\":\"15815597389\",\"prefecture\":440304,\"idCardNum\":\"432524199202087425\",\"detailAddress\":\"岗厦村东3坊5栋202\"}');
INSERT INTO `sys_log` VALUES (17, '2019-02-25 19:27:42', '绑定当前用户微信信息的其它账号的手机', 'X12613', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"8138\",\"phone\":\"16606279359\"}');
INSERT INTO `sys_log` VALUES (18, '2019-02-25 19:28:22', '绑定当前用户微信信息的其它账号的手机', 'X15927', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"3503\",\"phone\":\"15815597389\"}');
INSERT INTO `sys_log` VALUES (19, '2019-02-25 19:32:09', '绑定当前用户微信信息的其它账号的手机', 'X4954', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"0504\",\"phone\":\"16606279359\"}');
INSERT INTO `sys_log` VALUES (20, '2019-02-25 19:52:36', '绑定当前用户微信信息和手机', 'X15928', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"8054\",\"phone\":\"18914805800\"}');
INSERT INTO `sys_log` VALUES (21, '2019-02-25 20:13:07', '新增代理或vip', 'X0569', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13940300622\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/67aec9e9a92847e1adf031b88ab0e931.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":569,\"userName\":\"马晓龙\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (22, '2019-02-25 20:16:33', '完善用户信息', 'X15930', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"马晓龙\",\"idCardType\":1,\"province\":210000,\"city\":210100,\"phone\":\"13940300622\",\"prefecture\":210102,\"idCardNum\":\"211204198807100030\",\"detailAddress\":\"长白岛实远集团4楼中科爱伽\"}');
INSERT INTO `sys_log` VALUES (23, '2019-02-25 20:20:55', '新增代理或vip', 'X0097', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15925886671\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/6ed56927765b4e5493ced4ab30c54a40.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":97,\"userName\":\"葛亚琼\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (24, '2019-02-25 20:22:26', '绑定当前用户微信信息的其它账号的手机', 'X15933', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"7221\",\"phone\":\"18673119156\"}');
INSERT INTO `sys_log` VALUES (25, '2019-02-25 20:22:32', '绑定当前用户微信信息和手机', 'X15932', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"3662\",\"phone\":\"13913489671\"}');
INSERT INTO `sys_log` VALUES (26, '2019-02-25 20:23:06', '完善用户信息', 'X15931', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"葛亚琼\",\"idCardType\":1,\"province\":330000,\"city\":330600,\"phone\":\"15925886671\",\"prefecture\":330681,\"idCardNum\":\"330681198501016582\",\"detailAddress\":\"浣东街道暨东路68号建银大厦\"}');
INSERT INTO `sys_log` VALUES (27, '2019-02-25 20:24:39', '新增代理或vip', 'X0053', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"18238199201\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/378321adf1cc4361955679996b2921d9.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":53,\"userName\":\"刘月梅\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (28, '2019-02-25 20:28:02', '绑定当前用户微信信息和手机', 'X6545', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0970\",\"phone\":\"15258027068\"}');
INSERT INTO `sys_log` VALUES (29, '2019-02-25 20:32:15', '绑定当前用户微信信息和手机', 'X13138', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0397\",\"phone\":\"15000833883\"}');
INSERT INTO `sys_log` VALUES (30, '2019-02-25 20:34:04', '新增代理或vip', 'X0416', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":200,\"areaCode\":\"86\",\"cellPhone\":\"13913489671\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/8abedd313c364bfcbcfbb5be4f3fcb81.png\",\"saleCardNum\":10,\"saleType\":0,\"sellUid\":416,\"userName\":\"薛静\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (31, '2019-02-25 20:38:21', '新增代理或vip', 'X7044', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"18587883587\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/a60ce9a8c093480d81ddb003b25d3087.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":7044,\"userName\":\"李文俊\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (32, '2019-02-25 20:41:02', '完善用户信息', 'X15935', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"李文俊\",\"idCardType\":3,\"province\":310000,\"city\":310100,\"phone\":\"18587883587\",\"prefecture\":310107,\"idCardNum\":\"K0500882P\",\"detailAddress\":\"上海市普陀区中山北路2000号B617-619。\\n沈艳娟 代收。13701887172\"}');
INSERT INTO `sys_log` VALUES (33, '2019-02-25 20:57:20', '完善用户信息', 'X9623', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"李志鹏\",\"idCardType\":1,\"province\":110000,\"city\":110100,\"phone\":\"18515308698\",\"prefecture\":110105,\"idCardNum\":\"140702199011127190\",\"detailAddress\":\"中建玲珑山6-1105\"}');
INSERT INTO `sys_log` VALUES (34, '2019-02-25 21:01:41', '绑定当前用户微信信息和手机', 'X6899', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0279\",\"phone\":\"13585697149\"}');
INSERT INTO `sys_log` VALUES (35, '2019-02-25 21:08:52', '绑定当前用户微信信息的其它账号的手机', 'X15936', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"3280\",\"phone\":\"18238199201\"}');
INSERT INTO `sys_log` VALUES (36, '2019-02-25 21:09:33', '绑定当前用户微信信息和手机', 'X9201', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"8113\",\"phone\":\"13201330778\"}');
INSERT INTO `sys_log` VALUES (37, '2019-02-25 21:12:30', '完善用户信息', 'X15934', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"刘月梅\",\"idCardType\":1,\"province\":410000,\"city\":411300,\"phone\":\"18238199201\",\"prefecture\":411381,\"idCardNum\":\"411381198001123941\",\"detailAddress\":\"罗庄镇一初中\"}');
INSERT INTO `sys_log` VALUES (38, '2019-02-25 21:13:13', '绑定当前用户微信信息和手机', 'X15938', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0761\",\"phone\":\"18839622268\"}');
INSERT INTO `sys_log` VALUES (39, '2019-02-25 21:17:46', '绑定当前用户微信信息的其它账号的手机', 'X11422', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"7493\",\"phone\":\"18016221125\"}');
INSERT INTO `sys_log` VALUES (40, '2019-02-25 21:40:26', '绑定当前用户微信信息和手机', 'X9167', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0968\",\"phone\":\"15870918866\"}');
INSERT INTO `sys_log` VALUES (41, '2019-02-25 21:47:41', '绑定当前用户微信信息和手机', 'X9236', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"6180\",\"phone\":\"13217833960\"}');
INSERT INTO `sys_log` VALUES (42, '2019-02-25 21:50:17', '绑定当前用户微信信息和手机', 'X9221', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0900\",\"phone\":\"18701622970\"}');
INSERT INTO `sys_log` VALUES (43, '2019-02-25 21:50:24', '完善用户信息', 'X9068', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"王晓静\",\"idCardType\":1,\"province\":610000,\"city\":610200,\"phone\":\"18291592179\",\"prefecture\":610204,\"idCardNum\":\"610202198601172822\",\"detailAddress\":\"新区鑫苑雅居6号楼B单元\"}');
INSERT INTO `sys_log` VALUES (44, '2019-02-25 22:25:21', '代理补货', 'X0595', NULL, '分销-前台-销售卡订单', '[类名]:com.xc.sale.web.controller.CardOrderController,[方法]:supplyCard,[参数]:{\"agentId\":578,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/b79472fa352647018c183c39b258c9bc.png\",\"saleCardNum\":10}');
INSERT INTO `sys_log` VALUES (45, '2019-02-25 22:29:31', '绑定当前用户微信信息的其它账号的手机', 'X11517', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"0371\",\"phone\":\"13732318185\"}');
INSERT INTO `sys_log` VALUES (46, '2019-02-25 22:38:50', '绑定当前用户微信信息的其它账号的手机', 'X15940', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"8010\",\"phone\":\"15777152967\"}');
INSERT INTO `sys_log` VALUES (47, '2019-02-25 22:51:13', '新增代理或vip', 'X0578', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13002983611\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190225/87defe0351404a57a5667e14f9824457.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":578,\"userName\":\"肖经纬\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (48, '2019-02-25 23:05:34', '完善用户信息', 'X1052', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"王豪杰\",\"idCardType\":1,\"province\":330000,\"city\":330100,\"phone\":\"18217698124\",\"prefecture\":330104,\"idCardNum\":\"412725199312034635\",\"detailAddress\":\"通惠中路金鹭银座\"}');
INSERT INTO `sys_log` VALUES (49, '2019-02-25 23:06:54', '绑定当前用户微信信息的其它账号的手机', 'X15942', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"6965\",\"phone\":\"18217698124\"}');
INSERT INTO `sys_log` VALUES (50, '2019-02-25 23:45:51', '绑定当前用户微信信息和手机', 'X1559', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"6092\",\"phone\":\"15611666532\"}');
INSERT INTO `sys_log` VALUES (51, '2019-02-25 23:49:07', '绑定当前用户微信信息和手机', 'X15943', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"7311\",\"phone\":\"13941464148\"}');
INSERT INTO `sys_log` VALUES (52, '2019-02-26 00:00:00', '定时统计商品销售排行榜', NULL, NULL, '分销定时任务-商品排行榜', '[类名]:com.xc.sale.web.controller.SaleGoodsRankController,[方法]:cardRankListStatistics,[参数]:null');
INSERT INTO `sys_log` VALUES (53, '2019-02-26 00:00:01', '统计用户销售卡数排行榜', NULL, NULL, '分销定时任务-销售卡排行榜任务', '[类名]:com.xc.sale.web.controller.SaleCardRankController,[方法]:cardRankListStatistics,[参数]:null');
INSERT INTO `sys_log` VALUES (54, '2019-02-26 00:17:04', '绑定当前用户微信信息和手机', 'X15945', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"7559\",\"phone\":\"18888928780\"}');
INSERT INTO `sys_log` VALUES (55, '2019-02-26 00:19:36', '完善用户信息', 'X9723', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"‍吴公子\",\"idCardType\":1,\"province\":320000,\"city\":320600,\"phone\":\"13228058159\",\"prefecture\":320612,\"idCardNum\":\"320624197601248416\",\"detailAddress\":\"朝东圩村17组\"}');
INSERT INTO `sys_log` VALUES (56, '2019-02-26 00:23:21', '绑定当前用户微信信息和手机', 'X5845', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"5071\",\"phone\":\"15103057278\"}');
INSERT INTO `sys_log` VALUES (57, '2019-02-26 08:29:13', '绑定当前用户微信信息和手机', 'X15949', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"0640\",\"phone\":\"18602424999\"}');
INSERT INTO `sys_log` VALUES (58, '2019-02-26 09:00:43', '新增代理或vip', 'X2230', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15052848962\",\"mid\":1,\"referrerPhone\":\"13952828765\",\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/4ecba15408fd466b989f3e667df1591d.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":2230,\"userName\":\"孙永梅\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (59, '2019-02-26 09:04:54', '完善用户信息', 'X13277', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"疏影暗香\",\"idCardType\":1,\"province\":320000,\"city\":321200,\"phone\":\"15052848962\",\"prefecture\":321202,\"idCardNum\":\"321284198610171043\",\"detailAddress\":\"泰安路39号\"}');
INSERT INTO `sys_log` VALUES (60, '2019-02-26 09:35:42', '完善用户信息', 'X10181', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"褚冰侠～纤怡本草® 全国总代\",\"idCardType\":1,\"province\":340000,\"city\":340300,\"phone\":\"15955257807\",\"prefecture\":340321,\"idCardNum\":\"340321197606106220\",\"detailAddress\":\"安徽省蚌埠市怀远县启程纤怡工作室禇冰侠\"}');
INSERT INTO `sys_log` VALUES (61, '2019-02-26 09:36:54', '新增代理或vip', 'X0035', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15665755872\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/9c8c293f679e41919ec202ea71246f7f.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":35,\"userName\":\"刘春乐\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (62, '2019-02-26 09:42:49', '完善用户信息', 'X11880', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"珠珠\",\"idCardType\":1,\"province\":310000,\"city\":310100,\"phone\":\"13601870343\",\"prefecture\":310114,\"idCardNum\":\"310111196901081683\",\"detailAddress\":\"瑞林路1280弄381号\"}');
INSERT INTO `sys_log` VALUES (63, '2019-02-26 09:45:41', '绑定当前用户微信信息的其它账号的手机', 'X6093', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"2513\",\"phone\":\"13755269116\"}');
INSERT INTO `sys_log` VALUES (64, '2019-02-26 09:46:46', '绑定当前用户微信信息和手机', 'X15954', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"6811\",\"phone\":\"15665755872\"}');
INSERT INTO `sys_log` VALUES (65, '2019-02-26 09:48:45', '新增代理或vip', 'X0598', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13773078987\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/09ff45530cfa46f699494c64c04d5938.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":598,\"userName\":\"胡小燕\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (66, '2019-02-26 09:53:12', '新增代理或vip', 'X0598', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"18973109852\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/487a51594e8d46228c7d8170532f67d8.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":598,\"userName\":\"杨翎\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (67, '2019-02-26 09:58:21', '绑定当前用户微信信息的其它账号的手机', 'X11932', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"9737\",\"phone\":\"17307854919\"}');
INSERT INTO `sys_log` VALUES (68, '2019-02-26 09:59:04', '绑定当前用户微信信息的其它账号的手机', 'X11932', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"4895\",\"phone\":\"17307854919\"}');
INSERT INTO `sys_log` VALUES (69, '2019-02-26 10:20:01', '绑定当前用户微信信息和手机', 'X0686', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"1307\",\"phone\":\"13755269116\"}');
INSERT INTO `sys_log` VALUES (70, '2019-02-26 10:29:00', '绑定当前用户微信信息和手机', 'X15957', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"3675\",\"phone\":\"17621231650\"}');
INSERT INTO `sys_log` VALUES (71, '2019-02-26 10:30:46', '绑定当前用户微信信息的其它账号的手机', 'X15957', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"1963\",\"phone\":\"17621231650\"}');
INSERT INTO `sys_log` VALUES (72, '2019-02-26 10:39:06', '新增代理或vip', 'X0248', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"17621231650\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/bef356a82e974b97bf6e192f78f1a2a0.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":248,\"userName\":\"马龙\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (73, '2019-02-26 11:13:40', '新增代理或vip', 'X0598', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15958481558\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/caffe46cde084bae8adf77ad494368bc.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":598,\"userName\":\"汪宏昕\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (74, '2019-02-26 11:19:59', '完善用户信息', 'X15959', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"汪宏昕\",\"idCardType\":1,\"province\":330000,\"city\":330700,\"phone\":\"15958481558\",\"prefecture\":330782,\"idCardNum\":\"330721197503067417\",\"detailAddress\":\"江东街道久府和园3栋2单元1502室\"}');
INSERT INTO `sys_log` VALUES (75, '2019-02-26 11:41:56', '完善用户信息', 'X15852', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"瘦归人品牌创始人@橙子老师\",\"idCardType\":1,\"province\":430000,\"city\":430100,\"phone\":\"18973109852\",\"prefecture\":430111,\"idCardNum\":\"43302319820318422X\",\"detailAddress\":\"长沙大道278号高桥国际大厦\"}');
INSERT INTO `sys_log` VALUES (76, '2019-02-26 12:41:14', '绑定当前用户微信信息和手机', 'X15961', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4822\",\"phone\":\"18080866680\"}');
INSERT INTO `sys_log` VALUES (77, '2019-02-26 13:10:12', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13631162370\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/e445b698c3514fffaea601f987a257a1.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"刘梅红\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (78, '2019-02-26 13:12:18', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13823019282\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/4c7f69fe3e3e4ab0bb0496de105a806a.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"黄翩跹\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (79, '2019-02-26 13:17:46', '新增代理或vip', 'X0993', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13660124129\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/1289370f7f814c5b884396818c4f5523.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":993,\"userName\":\"陈华\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (80, '2019-02-26 13:18:55', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13648840538\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/b6504a08dacf4776aeb076e7545c6544.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"周永丽\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (81, '2019-02-26 13:19:05', '新增代理或vip', 'X0463', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"17720540803\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/b91c35e5718148718d9053c116f4ab41.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":463,\"userName\":\"申汉\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (82, '2019-02-26 13:19:49', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13590912638\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/655bf6aac69c4cf8a5ca8a5f2ba6d497.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"朱槿娴\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (83, '2019-02-26 13:22:02', '完善用户信息', 'X15962', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"刘梅红\",\"idCardType\":1,\"province\":440000,\"city\":442000,\"phone\":\"13631162370\",\"prefecture\":442000,\"idCardNum\":\"360321198912222527\",\"detailAddress\":\"广东省中山市小榄镇联丰村吉安路四巷四横巷九栋\"}');
INSERT INTO `sys_log` VALUES (84, '2019-02-26 13:28:26', '完善用户信息', 'X15966', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"朱槿娴\",\"idCardType\":1,\"province\":440000,\"city\":442000,\"phone\":\"13590912638\",\"prefecture\":442000,\"idCardNum\":\"43042119801221316X\",\"detailAddress\":\"坦洲镇文康路49号依美婷化妆品店铺\"}');
INSERT INTO `sys_log` VALUES (85, '2019-02-26 13:33:19', '完善用户信息', 'X15963', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"黄翩跹\",\"idCardType\":1,\"province\":440000,\"city\":440400,\"phone\":\"13823019282\",\"prefecture\":440402,\"idCardNum\":\"440882199112059340\",\"detailAddress\":\"广东省珠海市香洲区南屏镇南屏街口环屏路好家酒店\"}');
INSERT INTO `sys_log` VALUES (86, '2019-02-26 13:34:53', '绑定当前用户微信信息和手机', 'X15967', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"6781\",\"phone\":\"13631162370\"}');
INSERT INTO `sys_log` VALUES (87, '2019-02-26 13:48:19', '新增代理或vip', 'X12990', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"17307854919\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/fb21c31ce8654a08aa7c0ae13adfe8bd.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":12990,\"userName\":\"李珍\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (88, '2019-02-26 13:53:46', '完善用户信息', 'X15965', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"周永丽\",\"idCardType\":1,\"province\":530000,\"city\":530100,\"phone\":\"13648840538\",\"prefecture\":530128,\"idCardNum\":\"53012819941119242X\",\"detailAddress\":\"云南省昆明市西山区棕树营街道云投景苑小区5⃣️栋2406 周雅琪收\"}');
INSERT INTO `sys_log` VALUES (89, '2019-02-26 13:55:59', '完善用户信息', 'X11932', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"Molly珍\",\"idCardType\":1,\"province\":450000,\"city\":450800,\"phone\":\"17307854919\",\"prefecture\":450804,\"idCardNum\":\"450802198512022729\",\"detailAddress\":\"樟木镇樟木街\"}');
INSERT INTO `sys_log` VALUES (90, '2019-02-26 14:05:56', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13665878674\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/8b27fc460f874c7388801290f16a5063.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"张琴芳\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (91, '2019-02-26 14:49:59', '绑定当前用户微信信息的其它账号的手机', 'X15974', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"4073\",\"phone\":\"13660124129\"}');
INSERT INTO `sys_log` VALUES (92, '2019-02-26 14:50:49', '新增代理或vip', 'X0581', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13922777282\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/3c31937b8ccc444999353296e763a51b.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":581,\"userName\":\"苏演如\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (93, '2019-02-26 14:54:32', '新增代理或vip', 'X5684', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13415315520\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/10eb27b67cd541b182b2558a509e5793.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":5684,\"userName\":\"田艳娟\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (94, '2019-02-26 14:56:47', '绑定当前用户微信信息的其它账号的手机', 'X15978', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"0730\",\"phone\":\"13415315520\"}');
INSERT INTO `sys_log` VALUES (95, '2019-02-26 14:58:12', '新增代理或vip', 'X15506', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13816365573\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/61a712054e8e44a28b485c8bb2ba3e6b.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":15506,\"userName\":\"卢绮璇\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (96, '2019-02-26 14:59:49', '完善用户信息', 'X15980', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"卢绮璇\",\"idCardType\":1,\"province\":410000,\"city\":411700,\"phone\":\"13816365573\",\"prefecture\":411722,\"idCardNum\":\"41282519890402532X\",\"detailAddress\":\"上海浦东新区高科西路2763弄303号104室\"}');
INSERT INTO `sys_log` VALUES (97, '2019-02-26 15:00:48', '新增代理或vip', 'X7044', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13918258604\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/82733bd19ae4436ca22c4747847cec5d.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":7044,\"userName\":\"洪齐鸿\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (98, '2019-02-26 15:06:42', '绑定当前用户微信信息和手机', 'X15982', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"7537\",\"phone\":\"13918258604\"}');
INSERT INTO `sys_log` VALUES (99, '2019-02-26 15:08:25', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13926939713\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/065d7ae256134844ba3ff0ff6fc5fd5f.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"黄曼丽\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (100, '2019-02-26 15:09:51', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13924948613\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/a11ebd17de8e42b389d5f4786250fa5a.jpeg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"郑谨瑗\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (101, '2019-02-26 15:10:54', '完善用户信息', 'X7121', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"苏演如\",\"idCardType\":1,\"province\":440000,\"city\":440100,\"phone\":\"13922777282\",\"prefecture\":440113,\"idCardNum\":\"441324198803045621\",\"detailAddress\":\"广州番禺区市桥镇西城花园1街8座\"}');
INSERT INTO `sys_log` VALUES (102, '2019-02-26 15:13:01', '完善用户信息', 'X15984', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"郑谨瑗\",\"idCardType\":1,\"province\":360000,\"city\":360700,\"phone\":\"13924948613\",\"prefecture\":360733,\"idCardNum\":\"360733198403264523\",\"detailAddress\":\"广东省中山市古镇岗东市场松桑围大街左六巷1号发丝名匠理发店\\n\"}');
INSERT INTO `sys_log` VALUES (103, '2019-02-26 15:14:53', '绑定当前用户微信信息和手机', 'X15985', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4189\",\"phone\":\"13924948613\"}');
INSERT INTO `sys_log` VALUES (104, '2019-02-26 15:21:42', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13817184987\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/bf6c0ff581b8497bbd98c91e8ad92d75.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"郭丽君\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (105, '2019-02-26 15:24:48', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13692097860\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/269665719d6f4c5d9c973fe202a73b43.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"林群珊\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (106, '2019-02-26 15:25:41', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13636429090\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/a45c22073d414c4f9596589099b7707e.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"宋萌\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (107, '2019-02-26 15:30:05', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"18016221125\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/8c37e61e957248cda6e48a722258e573.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"雷素珍\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (108, '2019-02-26 15:32:13', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13472526464\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/6c0e7e2a19184c7cb862ef9398245bb1.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"徐春凤\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (109, '2019-02-26 16:00:38', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13816501191\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/086c355d2e8e4548bb804fe87b021215.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"陈娟\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (110, '2019-02-26 16:02:09', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13531668036\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/374568c9599644938944dbc74b15247e.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"彭艳\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (111, '2019-02-26 16:27:25', '新增代理或vip', 'X6764', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13968878238\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/10d21f23cc9e42de9b2bc860ab5ec820.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":6764,\"userName\":\"虞海珍\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (112, '2019-02-26 16:29:24', '完善用户信息', 'X11095', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"鸿酒世家~天天快乐\",\"idCardType\":1,\"province\":330000,\"city\":330300,\"phone\":\"13968878238\",\"prefecture\":330302,\"idCardNum\":\"330302197205273246\",\"detailAddress\":\"温州南汇锦园2幢507\"}');
INSERT INTO `sys_log` VALUES (113, '2019-02-26 16:51:09', '新增代理或vip', 'X0570', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13922949659\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/37740310d2c7499fa5267a35085338b2.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":570,\"userName\":\"刘晓萍\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (114, '2019-02-26 16:53:32', '新增代理或vip', 'X7044', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13697742787\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/6e46a6da155a43b195ab938655b70533.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":7044,\"userName\":\"叶林\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (115, '2019-02-26 17:07:14', '完善用户信息', 'X15988', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"叶林\",\"idCardType\":1,\"province\":440000,\"city\":440300,\"phone\":\"13697742787\",\"prefecture\":440306,\"idCardNum\":\"500225198312068892\",\"detailAddress\":\"龙珠花园23栋101\"}');
INSERT INTO `sys_log` VALUES (116, '2019-02-26 17:47:04', '绑定当前用户微信信息和手机', 'X15989', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"4776\",\"phone\":\"18004798802\"}');
INSERT INTO `sys_log` VALUES (117, '2019-02-26 18:06:20', '完善用户信息', 'X15900', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"胡慧颖\",\"idCardType\":1,\"province\":350000,\"city\":350100,\"phone\":\"13665055778\",\"prefecture\":350103,\"idCardNum\":\"350322197709210045\",\"detailAddress\":\"西二环南路118号金星四季花城2——604\"}');
INSERT INTO `sys_log` VALUES (118, '2019-02-26 18:15:16', '新增代理或vip', 'X0053', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13611909640\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/3a7802304d744885b4053a1a97f5a45d.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":53,\"userName\":\"高夫山\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (119, '2019-02-26 18:20:13', '完善用户信息', 'X15990', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"高夫山\",\"idCardType\":1,\"province\":310000,\"city\":310100,\"phone\":\"13611909640\",\"prefecture\":310106,\"idCardNum\":\"320322198710192517\",\"detailAddress\":\"汶水路405号\"}');
INSERT INTO `sys_log` VALUES (120, '2019-02-26 18:24:29', '绑定当前用户微信信息的其它账号的手机', 'X2286', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"3835\",\"phone\":\"18663711166\"}');
INSERT INTO `sys_log` VALUES (121, '2019-02-26 18:27:02', '绑定当前用户微信信息和手机', 'X5710', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"8952\",\"phone\":\"15547568020\"}');
INSERT INTO `sys_log` VALUES (122, '2019-02-26 18:33:17', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13718909618\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/96e531fe4e87449e842f1d518953aaaf.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"宗建梅\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (123, '2019-02-26 18:33:55', '新增代理或vip', 'X11402', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"13918465410\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/0e4130bdc7a44c2b823c9ae8c4a931a6.jpg\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":11402,\"userName\":\"杨洋\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (124, '2019-02-26 18:35:50', '完善用户信息', 'X0483', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"龙柏集团董事长\",\"idCardType\":1,\"province\":370000,\"city\":371000,\"phone\":\"18663711166\",\"prefecture\":371002,\"idCardNum\":\"412828196407071536\",\"detailAddress\":\"香港路18一1号智慧大厦六楼\"}');
INSERT INTO `sys_log` VALUES (125, '2019-02-26 18:37:33', '绑定当前用户微信信息的其它账号的手机', 'X15991', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"0453\",\"phone\":\"13611909640\"}');
INSERT INTO `sys_log` VALUES (126, '2019-02-26 18:39:20', '添加银行卡信息', 'X0483', NULL, '分销-前台-添加银行卡', '[类名]:com.xc.sale.web.controller.BankInfoController,[方法]:addBankInfo,[参数]:{\"bankInfo\":{\"bankBranck\":\"工商银行\",\"bankCard\":\"6222081614000954390\",\"bankCity\":\"威海\",\"bankType\":4,\"id\":2064,\"identityCard\":\"412828196407071536\",\"phone\":\"18663711166\",\"realName\":\"龙柏集团董事长\",\"uid\":483}}');
INSERT INTO `sys_log` VALUES (127, '2019-02-26 18:41:32', '绑定当前用户微信信息的其它账号的手机', 'X2286', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"2823\",\"phone\":\"18663711166\"}');
INSERT INTO `sys_log` VALUES (128, '2019-02-26 19:15:57', '绑定当前用户微信信息和手机', 'X15993', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"7793\",\"phone\":\"13637554885\"}');
INSERT INTO `sys_log` VALUES (129, '2019-02-26 19:32:29', '新增代理或vip', 'X0097', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:add,[参数]:{\"addAgentVo\":{\"agentLevel\":100,\"areaCode\":\"86\",\"cellPhone\":\"15161054371\",\"mid\":1,\"remittanceImg\":\"http://chuang-saas.oss-cn-hangzhou.aliyuncs.com/upload/image/20190226/65b0d3322c4f4430b7135d4b2988fff2.png\",\"saleCardNum\":1,\"saleType\":1,\"sellUid\":97,\"userName\":\"黄杰铭\",\"userType\":0}}');
INSERT INTO `sys_log` VALUES (130, '2019-02-26 19:35:51', '完善用户信息', 'X14576', NULL, '分销-前台-用户', '[类名]:com.xc.sale.web.controller.UserController,[方法]:importUserInfo,[参数]:{\"realName\":\"你若盛开，蝴蝶自来\",\"idCardType\":1,\"province\":320000,\"city\":321200,\"phone\":\"15161054371\",\"prefecture\":321282,\"idCardNum\":\"321282199212141254\",\"detailAddress\":\"靖江市大觉北路21号中祥汽配\"}');
INSERT INTO `sys_log` VALUES (131, '2019-02-26 19:44:10', '绑定当前用户微信信息和手机', 'X15361', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCellphoneForce,[参数]:{\"areaCode\":\"86\",\"code\":\"2481\",\"phone\":\"13999082099\"}');
INSERT INTO `sys_log` VALUES (132, '2019-02-26 19:53:41', '绑定当前用户微信信息的其它账号的手机', 'X15361', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"2150\",\"phone\":\"13709072198\"}');
INSERT INTO `sys_log` VALUES (133, '2019-02-26 20:20:51', '绑定当前用户微信信息的其它账号的手机', 'X11887', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"8153\",\"phone\":\"18705298858\"}');
INSERT INTO `sys_log` VALUES (134, '2019-02-26 20:22:04', '绑定当前用户微信信息的其它账号的手机', 'X11887', NULL, '商城-前台-用户', '[类名]:com.xc.mall.web.controller.UserController,[方法]:bindCurrentWxUserToCellphoneUser,[参数]:{\"areaCode\":\"86\",\"code\":\"0251\",\"phone\":\"18705298858\"}');


SET FOREIGN_KEY_CHECKS = 1;
