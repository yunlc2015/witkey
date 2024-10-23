/*
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'f1267d293d6fcad1896f0c4acc268cc4', 'aw8hty2', NULL, 0, NULL, '2015-09-12 00:00:00');

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1001, '关于我们', '关于我们.', 's1', 'aboutus', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1002, '联系我们', '联系我们', 's1', 'contact', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1003, '法律声明', '法律声明', 's1', 'statement', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1004, '服务商入门', '服务商入门', 's3', 'service-guide', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1005, '服务协议', '服务协议', 's2', 'service-agreement', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1006, '如何抢标', '如何抢标', 's3', 'how-gettask', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1007, '担保交易', '担保交易', 's5', 'danbao-jiaoyi', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1008, '三方协议', '三方协议', 's5', 'three-agreement', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1009, '争议仲裁', '争议仲裁', 's5', 'zhengyi-zhongcai', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1010, '版权说明', '版权说明', 's5', 'copyright', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1011, '雇主入门', '雇主入门', 's2', 'employer-guide', '2016-08-06 00:00:00');
INSERT INTO `t_article` VALUES (1012, '加入我们', '加入我们', 's1', 'joinus', '2016-08-06 00:00:00');

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1000, 'LOGO设计', 0);
INSERT INTO `t_category` VALUES (2000, 'VIS设计', 0);
INSERT INTO `t_category` VALUES (3000, '画册设计', 0);
INSERT INTO `t_category` VALUES (4000, '包装设计', 0);
INSERT INTO `t_category` VALUES (5000, 'APP设计', 0);
INSERT INTO `t_category` VALUES (6000, '网店装修', 0);
INSERT INTO `t_category` VALUES (7000, '漫画制作', 0);
INSERT INTO `t_category` VALUES (8000, '工业设计', 0);
INSERT INTO `t_category` VALUES (9000, '店面设计', 0);
INSERT INTO `t_category` VALUES (10000, '印刷制作', 0);

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES (1001, '北京市', '', 1);
INSERT INTO `t_city` VALUES (1002, '北京市', '东城区', 0);
INSERT INTO `t_city` VALUES (1003, '北京市', '西城区', 0);
INSERT INTO `t_city` VALUES (1005, '上海市', '', 1);
INSERT INTO `t_city` VALUES (1006, '重庆市', '', 1);
INSERT INTO `t_city` VALUES (1007, '广东省', '', 1);
INSERT INTO `t_city` VALUES (1008, '广东省', '广州市', 0);
INSERT INTO `t_city` VALUES (1009, '广东省', '深圳市', 0);

-- ----------------------------
-- Records of t_settings
-- ----------------------------
INSERT INTO `t_settings` VALUES (100001, 'appName', '云联创威客系统', '应用名称', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100002, 'appPcLogo', '', '应用PC版Logo', 'general', 'image', NULL);
INSERT INTO `t_settings` VALUES (100003, 'appMobLogo', '', '应用移动版Logo', 'general', 'image', NULL);
INSERT INTO `t_settings` VALUES (100004, 'seoKeywords', '', 'SEO关键词', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100005, 'seoDescription', '', 'SEO描述', 'general', 'textarea', NULL);
INSERT INTO `t_settings` VALUES (100006, 'icpBeianNo', '京ICP备12345678号', 'ICP备案号', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100007, 'serviceTel', '4008-###-6666', '客服电话', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100008, 'cookieDomain', '', '站点Cookie域', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100009, 'cookiePrefix', 'witkey', '站点Cookie前缀', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100010, 'cookieSecret', 'Witkey$235', '站点Cookie密钥', 'general', 'text', NULL);
INSERT INTO `t_settings` VALUES (100011, 'statisScript', '', '统计脚本', 'general', 'textarea', NULL);
INSERT INTO `t_settings` VALUES (100012, 'alipayEnable', '1', '启用支付宝支付', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100013, 'wxpayEnable', '0', '启用微信支付', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100016, 'alipaySellerEmail', '', '支付宝商户Email', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100017, 'alipayAppid', '', '支付宝AppId', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100018, 'alipayVersion', '', '支付宝接口版本', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100019, 'alipaySigntype', '', '支付宝签名类型', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100020, 'alipayRsaPrivatekey', '', '支付宝RSA私钥', 'pay', 'textarea', NULL);
INSERT INTO `t_settings` VALUES (100021, 'alipayRsaPublickey', '', '支付宝RSA公钥', 'pay', 'textarea', NULL);
INSERT INTO `t_settings` VALUES (100022, 'alipayMd5Key', '', '支付宝MD5密钥', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100023, 'wxpayAppid', '', '微信AppId', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100024, 'wxpayAppsecret', '', '微信App密钥', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100025, 'wxpayMchid', '', '微信支付商户ID', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100026, 'wxpayMchkey', '', '微信支付商户密钥', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100027, 'alipayNotifyUrl', '', '支付宝通知回调URL', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100028, 'wxpayNotifyUrl', '', '微信支付通知回调URL', 'pay', 'text', NULL);
INSERT INTO `t_settings` VALUES (100029, 'appFootLogo', '', '应用页脚Logo', 'general', 'image', NULL);
INSERT INTO `t_settings` VALUES (100030, 'wxQrcode', '', '微信二唯码', 'general', 'image', NULL);

SET FOREIGN_KEY_CHECKS = 1;
