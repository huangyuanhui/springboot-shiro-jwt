/*
SQLyog v10.2 
MySQL - 5.7.27-log : Database - shiro-end-starter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro-end-starter` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `shiro-end-starter`;

/*Table structure for table `role_per` */

DROP TABLE IF EXISTS `role_per`;

CREATE TABLE `role_per` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
  `roleId` varchar(32) DEFAULT NULL COMMENT '角色表的主键id',
  `perId` varchar(32) DEFAULT NULL COMMENT '权限表的主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `role_per` */

insert  into `role_per`(`id`,`roleId`,`perId`) values (1,'100','M01'),(2,'100','M02'),(3,'100','M03'),(4,'200','M204'),(5,'100','M204');

/*Table structure for table `sys_permissions` */

DROP TABLE IF EXISTS `sys_permissions`;

CREATE TABLE `sys_permissions` (
  `perId` varchar(32) NOT NULL COMMENT '权限表id 作为表主键 用于关联',
  `permissionsName` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `perRemarks` varchar(255) DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`perId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_permissions` */

insert  into `sys_permissions`(`perId`,`permissionsName`,`perRemarks`) values ('M01','resetPassword','重置密码'),('M02','querySystemLog','查看系统日志'),('M03','exportUserInfo','导出用户信息'),('M204','queryMyUserInfo','查看个人信息');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `roleId` varchar(32) NOT NULL COMMENT ' 角色id 作为表主键 用于关联',
  `roleName` varchar(32) DEFAULT NULL COMMENT '角色名',
  `roleRemarks` varchar(255) DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role` */

insert  into `sys_role`(`roleId`,`roleName`,`roleRemarks`) values ('100','admin','系统管理员'),('200','common','普通用户');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user表的id字段',
  `userId` varchar(255) NOT NULL COMMENT '用户id 作为表主键 用于关联',
  `userName` varchar(25) DEFAULT NULL COMMENT '用户登录帐号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户登录密码',
  `userRemarks` varchar(255) DEFAULT NULL COMMENT '备注，预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`userId`,`userName`,`password`,`userRemarks`) values (1,'10001','adminHong','202cb962ac59075b964b07152d234b70','小红'),(2,'15564819717','2','f10bfea990b657f6e8355c6e3ee67e2d','hehe'),(3,'177900','武文生','c4dbde004d409a733e4a7c8b00466613','生生酱'),(4,'20001','jc','e165421110ba03099a1c0393373c5b43','JC'),(5,'18864819717','myDear','756b3c2e758a2b8c728fa2e4d3f3294d','nice today i love you'),(8,'18262699169','hello','4cad6da13952ad1621e4f8ede54d9fad','hello'),(9,'15698756214','good luck','713741121ec6b5d854b9c15e78a36f27','good luck'),(10,'12345678922','测试一下','a753bbccb874ef05b43b9fceffb949dd','闲来无聊');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
  `userId` varchar(255) DEFAULT NULL COMMENT '帐号表的主键id',
  `roleId` varchar(32) DEFAULT NULL COMMENT '角色表的主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values (1,'10001','100'),(2,'20001','200'),(3,'177900','200'),(4,'15564819717','200'),(5,'18864819717','200'),(8,'18262699169','200'),(9,'15698756214','200'),(10,'12345678922','200');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
