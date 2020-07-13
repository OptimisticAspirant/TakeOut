/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : takeout

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 13/07/2020 15:48:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `add_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) NULL DEFAULT NULL,
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contacts` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`add_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, '浙江', '杭州', '拱墅', '浙大城市学院北校区理四', '李先生', '13588375104');
INSERT INTO `address` VALUES (2, 1, '浙江', '绍兴', '越城', '百合花园26幢', '李女士', '13588375104');
INSERT INTO `address` VALUES (3, 1, '浙江', '杭州', '拱墅', '浙大城市学院南校区教三', '李某人', '13588375104');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `coup_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NULL DEFAULT NULL,
  `coup_amount` float NOT NULL,
  `coup_count` int(11) NOT NULL,
  `startdate` datetime(0) NOT NULL,
  `enddate` datetime(0) NOT NULL,
  PRIMARY KEY (`coup_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, 1, 2, 4, '2020-01-01 00:00:00', '2020-09-09 00:00:00');
INSERT INTO `coupon` VALUES (2, 1, 5, 4, '2020-07-01 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `coupon` VALUES (3, 2, 3, 5, '2020-01-01 00:00:00', '2020-09-09 00:00:00');
INSERT INTO `coupon` VALUES (4, 2, 6, 10, '2020-01-01 00:00:00', '2020-09-09 00:00:00');
INSERT INTO `coupon` VALUES (5, 4, 1, 2, '2020-01-01 00:00:00', '2020-09-09 00:00:00');

-- ----------------------------
-- Table structure for couponhold
-- ----------------------------
DROP TABLE IF EXISTS `couponhold`;
CREATE TABLE `couponhold`  (
  `coup_id` int(11) NOT NULL,
  `cust_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `hold_mount` int(11) NOT NULL,
  `subtract` float NULL DEFAULT NULL,
  `hold_deadline` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`coup_id`, `cust_id`, `shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of couponhold
-- ----------------------------
INSERT INTO `couponhold` VALUES (1, 1, 1, 0, 4, '2020-09-09 00:00:00');
INSERT INTO `couponhold` VALUES (5, 1, 4, 2, 2, '2020-09-09 00:00:00');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cust_gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cust_password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cust_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cust_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cust_city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rig_time` datetime(0) NOT NULL,
  `ifVIP` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIPdeadline` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '李楠', '女', '1', '13588375104', '1602113077@qq.com', '杭州', '2020-07-13 02:29:01', '是', '2020-08-13 02:38:35');

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`  (
  `cust_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `coup_id` int(11) NOT NULL,
  `collect_count` int(11) NOT NULL,
  `collect_require` int(11) NOT NULL,
  PRIMARY KEY (`cust_id`, `shop_id`, `coup_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES (1, 1, 1, 1, 4);
INSERT INTO `discount` VALUES (1, 1, 2, 1, 4);
INSERT INTO `discount` VALUES (1, 2, 3, 2, 5);
INSERT INTO `discount` VALUES (1, 2, 4, 2, 10);
INSERT INTO `discount` VALUES (1, 4, 5, 0, 2);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `manager_id` int(11) NOT NULL,
  `manager_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'Jackson', '1');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `pro_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `mount` int(11) NOT NULL,
  `price` float NOT NULL,
  `perdiscount` float NOT NULL,
  PRIMARY KEY (`pro_id`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES (1, 1, 1, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 2, 5, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 6, 2, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 7, 1, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 8, 3, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 12, 2, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 13, 2, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 14, 2, 12, 10);
INSERT INTO `orderdetail` VALUES (1, 15, 1, 12, 10);
INSERT INTO `orderdetail` VALUES (2, 1, 1, 8, 8);
INSERT INTO `orderdetail` VALUES (2, 8, 1, 8, 8);
INSERT INTO `orderdetail` VALUES (2, 15, 1, 8, 8);
INSERT INTO `orderdetail` VALUES (3, 1, 1, 4, 2);
INSERT INTO `orderdetail` VALUES (3, 15, 1, 4, 2);
INSERT INTO `orderdetail` VALUES (4, 3, 3, 14, 12);
INSERT INTO `orderdetail` VALUES (4, 9, 1, 14, 12);
INSERT INTO `orderdetail` VALUES (5, 3, 1, 24, 22);
INSERT INTO `orderdetail` VALUES (7, 3, 1, 8, 6);
INSERT INTO `orderdetail` VALUES (11, 4, 3, 34, 24);
INSERT INTO `orderdetail` VALUES (11, 5, 1, 34, 24);
INSERT INTO `orderdetail` VALUES (12, 4, 2, 12, 10);
INSERT INTO `orderdetail` VALUES (12, 5, 1, 12, 10);
INSERT INTO `orderdetail` VALUES (12, 10, 1, 12, 10);
INSERT INTO `orderdetail` VALUES (12, 11, 1, 12, 10);

-- ----------------------------
-- Table structure for preferential
-- ----------------------------
DROP TABLE IF EXISTS `preferential`;
CREATE TABLE `preferential`  (
  `pre_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NULL DEFAULT NULL,
  `pre_require` float NOT NULL,
  `pre_cut` float NOT NULL,
  `ifcoupon` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pre_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of preferential
-- ----------------------------
INSERT INTO `preferential` VALUES (1, 1, 25, 2, '是');
INSERT INTO `preferential` VALUES (2, 1, 30, 5, '是');
INSERT INTO `preferential` VALUES (3, 2, 35, 8, '否');
INSERT INTO `preferential` VALUES (4, 2, 20, 1, '否');
INSERT INTO `preferential` VALUES (5, 4, 20, 4, '是');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pro_id` int(11) NOT NULL,
  `shop_id` int(11) NULL DEFAULT NULL,
  `cate_id` int(11) NULL DEFAULT NULL,
  `pro_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pro_price` float NOT NULL,
  `pro_discount` float NOT NULL,
  PRIMARY KEY (`pro_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, 4, '蛋黄肉粽', 12, 10);
INSERT INTO `product` VALUES (2, 1, 4, '鲜肉粽', 8, 8);
INSERT INTO `product` VALUES (3, 1, 4, '细沙粽', 4, 2);
INSERT INTO `product` VALUES (4, 2, 3, '番茄蛋面', 14, 12);
INSERT INTO `product` VALUES (5, 2, 3, '酸菜牛肉面', 24, 22);
INSERT INTO `product` VALUES (7, 2, 3, '清汤面', 8, 6);
INSERT INTO `product` VALUES (11, 4, 2, '牛肉炒饭', 34, 24);
INSERT INTO `product` VALUES (12, 4, 2, '蛋炒饭', 12, 10);

-- ----------------------------
-- Table structure for productcategory
-- ----------------------------
DROP TABLE IF EXISTS `productcategory`;
CREATE TABLE `productcategory`  (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT,
  `columnname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pro_count` int(11) NOT NULL,
  PRIMARY KEY (`cate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productcategory
-- ----------------------------
INSERT INTO `productcategory` VALUES (1, '饺子', 0);
INSERT INTO `productcategory` VALUES (2, '炒饭', 2);
INSERT INTO `productcategory` VALUES (3, '面条', 3);
INSERT INTO `productcategory` VALUES (4, '粽子', 3);

-- ----------------------------
-- Table structure for productevaluate
-- ----------------------------
DROP TABLE IF EXISTS `productevaluate`;
CREATE TABLE `productevaluate`  (
  `shop_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `cust_id` int(11) NOT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eval_date` datetime(0) NULL DEFAULT NULL,
  `star` float NULL DEFAULT NULL,
  `photo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`, `pro_id`, `cust_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productevaluate
-- ----------------------------
INSERT INTO `productevaluate` VALUES (1, 1, 1, '可以', '2020-07-13 02:40:10', 12, '没拍');
INSERT INTO `productevaluate` VALUES (1, 2, 1, '可以', '2020-07-13 02:41:51', 12, '没拍');
INSERT INTO `productevaluate` VALUES (1, 3, 1, '也可以', '2020-07-13 02:42:03', 24, '没拍');
INSERT INTO `productevaluate` VALUES (2, 4, 1, '可以', '2020-07-13 02:40:37', 5, '没拍');
INSERT INTO `productevaluate` VALUES (4, 12, 1, '可以', '2020-07-13 02:40:50', 8, '没拍');

-- ----------------------------
-- Table structure for productorder
-- ----------------------------
DROP TABLE IF EXISTS `productorder`;
CREATE TABLE `productorder`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `pre_id` int(11) NULL DEFAULT NULL,
  `add_id` int(11) NULL DEFAULT NULL,
  `cust_id` int(11) NULL DEFAULT NULL,
  `shop_id` int(11) NULL DEFAULT NULL,
  `coup_id` int(11) NULL DEFAULT NULL,
  `rider_id` int(11) NULL DEFAULT NULL,
  `originprice` float NOT NULL,
  `finalprice` float NOT NULL,
  `starttime` datetime(0) NOT NULL,
  `requiretime` datetime(0) NOT NULL,
  `orderstate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productorder
-- ----------------------------
INSERT INTO `productorder` VALUES (1, NULL, 1, 1, 1, -1, 1, 24, 18, '2020-07-13 02:32:01', '2020-07-13 03:02:01', '已送达');
INSERT INTO `productorder` VALUES (2, NULL, 2, 1, 1, -1, 1, 60, 50, '2020-07-13 02:32:24', '2020-07-13 03:02:24', '已送达');
INSERT INTO `productorder` VALUES (3, NULL, 1, 1, 2, -1, 3, 74, 64, '2020-07-13 02:34:19', '2020-07-13 03:04:19', '已送达');
INSERT INTO `productorder` VALUES (4, NULL, 1, 1, 4, -1, 2, 126, 92, '2020-07-13 02:37:27', '2020-07-13 02:49:27', '已送达');
INSERT INTO `productorder` VALUES (5, 1, 1, 1, 4, -1, NULL, 46, 34, '2020-07-13 02:42:27', '2020-07-13 03:12:27', '等待骑手接单');
INSERT INTO `productorder` VALUES (6, 5, 2, 1, 1, -1, NULL, 24, 18, '2020-07-13 02:42:54', '2020-07-13 03:02:54', '等待骑手接单');
INSERT INTO `productorder` VALUES (7, NULL, 2, 1, 1, -1, NULL, 12, 6, '2020-07-13 02:43:29', '2020-07-13 02:55:29', '等待骑手接单');
INSERT INTO `productorder` VALUES (8, 5, 2, 1, 1, 1, NULL, 44, 34, '2020-07-13 03:02:36', '2020-07-13 03:32:36', '等待骑手接单');
INSERT INTO `productorder` VALUES (9, 5, 2, 1, 2, -1, NULL, 14, 8, '2020-07-13 03:03:14', '2020-07-13 03:33:14', '等待骑手接单');
INSERT INTO `productorder` VALUES (10, 5, 1, 1, 4, 1, NULL, 12, 2, '2020-07-13 03:03:45', '2020-07-13 03:15:45', '等待骑手接单');
INSERT INTO `productorder` VALUES (11, NULL, 1, 1, 4, -1, NULL, 12, 6, '2020-07-13 03:04:03', '2020-07-13 03:05:03', '等待骑手接单');
INSERT INTO `productorder` VALUES (12, NULL, 1, 1, 1, -1, NULL, 24, 18, '2020-07-13 03:39:45', '2020-07-13 03:40:45', '等待骑手接单');
INSERT INTO `productorder` VALUES (13, 1, 1, 1, 1, -1, NULL, 24, 18, '2020-07-13 03:40:16', '2020-07-13 03:41:16', '等待骑手接单');
INSERT INTO `productorder` VALUES (14, 1, 2, 1, 1, -1, NULL, 24, 18, '2020-07-13 03:40:49', '2020-07-13 03:41:49', '等待骑手接单');
INSERT INTO `productorder` VALUES (15, NULL, 2, 1, 1, -1, NULL, 24, 18, '2020-07-13 03:41:11', '2020-07-13 03:42:11', '等待骑手接单');

-- ----------------------------
-- Table structure for rider
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider`  (
  `rider_id` int(11) NOT NULL AUTO_INCREMENT,
  `rider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entrydate` datetime(0) NOT NULL,
  `identity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`rider_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rider
-- ----------------------------
INSERT INTO `rider` VALUES (1, '马尔克斯', '2020-07-13 02:19:06', '新手');
INSERT INTO `rider` VALUES (2, '巴尔札札', '2020-07-13 02:19:26', '新手');
INSERT INTO `rider` VALUES (3, '弗尔弗尔', '2020-07-13 02:19:42', '新手');

-- ----------------------------
-- Table structure for riderbill
-- ----------------------------
DROP TABLE IF EXISTS `riderbill`;
CREATE TABLE `riderbill`  (
  `rider_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `taketime` datetime(0) NOT NULL,
  `evaluate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `income` float NOT NULL,
  PRIMARY KEY (`rider_id`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of riderbill
-- ----------------------------
INSERT INTO `riderbill` VALUES (1, 1, '2020-07-13 02:39:01', '好评', 3);
INSERT INTO `riderbill` VALUES (1, 2, '2020-07-13 02:39:04', '好评', 3);
INSERT INTO `riderbill` VALUES (2, 4, '2020-07-13 02:39:09', '差评', -17.5);
INSERT INTO `riderbill` VALUES (3, 3, '2020-07-13 02:39:17', '差评', -17.5);

-- ----------------------------
-- Table structure for shopkeeper
-- ----------------------------
DROP TABLE IF EXISTS `shopkeeper`;
CREATE TABLE `shopkeeper`  (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_star` float NULL DEFAULT NULL,
  `per_consume` float NULL DEFAULT NULL,
  `total_sale` int(11) NOT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopkeeper
-- ----------------------------
INSERT INTO `shopkeeper` VALUES (1, '张氏粽子', 16, 22, 24);
INSERT INTO `shopkeeper` VALUES (2, '李氏面馆', 5, 36, 6);
INSERT INTO `shopkeeper` VALUES (4, '刘氏炒饭', 8, 33.5, 9);

SET FOREIGN_KEY_CHECKS = 1;
