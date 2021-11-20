/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-11-19 23:56:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `user_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `content` longtext COMMENT '内容',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `flag` varchar(255) DEFAULT NULL COMMENT '原创，转载，翻译',
  `appreciation` bit(1) DEFAULT NULL COMMENT '赞赏',
  `commentabled` bit(1) DEFAULT NULL COMMENT '评论',
  `recommend` bit(1) DEFAULT NULL COMMENT '推荐',
  `share_statement` bit(1) DEFAULT NULL COMMENT '转载声明',
  `published` bit(1) DEFAULT NULL COMMENT '发布/保存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `views` int(11) DEFAULT NULL COMMENT '浏览量',
  `type_id` int(11) DEFAULT NULL COMMENT '类型id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_blog_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('1', 'Design and implementation of coaxial holographic optical storage data channel driver', '1', '# Abstract\r\n```c++\r\nclass Solution {\r\npublic:\r\n    int calculate(string s) {\r\n        stack<int> numstk;\r\n        int num=0;\r\n        char sign=\'+\';\r\n        for(int i=0;i<s.size();i++){\r\n            if(s[i]>=\'0\'&&s[i]<=\'9\'){\r\n                num = 10*num +(s[i]-\'0\');\r\n            }\r\n            if(s[i]==\'+\'||s[i]==\'-\'||s[i]==\'*\'||s[i]==\'/\'||i==s.size()-1){\r\n                if(sign==\'+\'){\r\n                    numstk.push(num);\r\n                }else if(sign==\'-\'){\r\n                    numstk.push((-1)*num);\r\n                }else if(sign==\'*\'){\r\n                    numstk.top()=numstk.top()*num;\r\n                }else if(sign==\'/\'){\r\n                    numstk.top()=numstk.top()/num;\r\n                }\r\n                num=0;\r\n                sign=s[i];\r\n            }\r\n        }\r\n        int res=0;\r\n        while(!numstk.empty()){\r\n            res+=numstk.top();\r\n            numstk.pop();\r\n        }\r\n        return res;\r\n    }\r\n};\r\n```\r\nWith the advent of the big data era, low-cost storage of ultra-large-scale cold data has become an important challenge. Optical storage has low cost, long life, green, energy-saving, and easy to store, making it very suitable for long-term storage of large-scale cold data. Coaxial holographic optical storage uses holographic multiplexing to store data, which can greatly increase storage density, and use two-dimensional data pages to access data. In order to enable the host to achieve a data read and write throughput rate of 1GB/s, it is necessary Design a high-speed holographic storage data channel.\r\nIn response to the high-speed access requirements of holographic storage, a holographic storage data channel framework is designed. At present, the server host is used as the drive controller of the coaxial holographic optical storage to realize the common block device interface-Small Computer System Interface (SCSI) device End target, and connect multiple initiators through the network SCSI interface (SCSI over internet, iSCSI) to support remote server access through standard block device interface. Design and implement the holographic storage block device driver, convert the upper-level read and write requests into access to the back-end optical storage medium, and support the memory and hard disk analog holographic optical storage, which can be debugged and verified. In order to adapt to the parallel access of multiple read-write heads of coaxial holographic optical discs, a block device multi-queue request processing mechanism based on blk-mq (Block-Multiqueue, blk-mq for short) is designed and implemented to improve the concurrency of IO and reduce the overhead of lock contention. And reduce Cache pollution. In order to simulate the movement of the optical disc by the robotic arm, the optical disc insertion and removal function has also been added. In order to increase the flexibility of the system, the system can also respond to commands sent by the linux system call.\r\nThe prototype of the holographic storage channel was realized and tested. The experimental results can realize the function of the iSCSI target, and its IO throughput rate is about 1082.625MB/s. It can support 4-way concurrency, and its aggregate throughput rate is about 1820.5MB/s.', 'Key words: Holographic-optical-storage, Data access channel, Block device driver', '原创', '', '', '', '\0', '', '2021-10-28 22:48:20', '2021-10-22 13:51:38', '125', '2');
INSERT INTO `t_blog` VALUES ('2', 'Design and implementation of coaxial holographic optical storage data channel driver', '1', '# Abstract\r\n```c++\r\nclass Solution {\r\npublic:\r\n    int calculate(string s) {\r\n        stack<int> numstk;\r\n        int num=0;\r\n        char sign=\'+\';\r\n        for(int i=0;i<s.size();i++){\r\n            if(s[i]>=\'0\'&&s[i]<=\'9\'){\r\n                num = 10*num +(s[i]-\'0\');\r\n            }\r\n            if(s[i]==\'+\'||s[i]==\'-\'||s[i]==\'*\'||s[i]==\'/\'||i==s.size()-1){\r\n                if(sign==\'+\'){\r\n                    numstk.push(num);\r\n                }else if(sign==\'-\'){\r\n                    numstk.push((-1)*num);\r\n                }else if(sign==\'*\'){\r\n                    numstk.top()=numstk.top()*num;\r\n                }else if(sign==\'/\'){\r\n                    numstk.top()=numstk.top()/num;\r\n                }\r\n                num=0;\r\n                sign=s[i];\r\n            }\r\n        }\r\n        int res=0;\r\n        while(!numstk.empty()){\r\n            res+=numstk.top();\r\n            numstk.pop();\r\n        }\r\n        return res;\r\n    }\r\n};\r\n```\r\nWith the advent of the big data era, low-cost storage of ultra-large-scale cold data has become an important challenge. Optical storage has low cost, long life, green, energy-saving, and easy to store, making it very suitable for long-term storage of large-scale cold data. Coaxial holographic optical storage uses holographic multiplexing to store data, which can greatly increase storage density, and use two-dimensional data pages to access data. In order to enable the host to achieve a data read and write throughput rate of 1GB/s, it is necessary Design a high-speed holographic storage data channel.\r\nIn response to the high-speed access requirements of holographic storage, a holographic storage data channel framework is designed. At present, the server host is used as the drive controller of the coaxial holographic optical storage to realize the common block device interface-Small Computer System Interface (SCSI) device End target, and connect multiple initiators through the network SCSI interface (SCSI over internet, iSCSI) to support remote server access through standard block device interface. Design and implement the holographic storage block device driver, convert the upper-level read and write requests into access to the back-end optical storage medium, and support the memory and hard disk analog holographic optical storage, which can be debugged and verified. In order to adapt to the parallel access of multiple read-write heads of coaxial holographic optical discs, a block device multi-queue request processing mechanism based on blk-mq (Block-Multiqueue, blk-mq for short) is designed and implemented to improve the concurrency of IO and reduce the overhead of lock contention. And reduce Cache pollution. In order to simulate the movement of the optical disc by the robotic arm, the optical disc insertion and removal function has also been added. In order to increase the flexibility of the system, the system can also respond to commands sent by the linux system call.\r\nThe prototype of the holographic storage channel was realized and tested. The experimental results can realize the function of the iSCSI target, and its IO throughput rate is about 1082.625MB/s. It can support 4-way concurrency, and its aggregate throughput rate is about 1820.5MB/s.', 'Key words: Holographic-optical-storage, Data access channel, Block device driver', '原创', '', '', '', '\0', '', '2021-10-28 22:48:20', '2021-10-22 13:51:38', '125', '2');
INSERT INTO `t_blog` VALUES ('3', 'Design and implementation of coaxial holographic optical storage data channel driver', '1', '# Abstract\r\n```c++\r\nclass Solution {\r\npublic:\r\n    int calculate(string s) {\r\n        stack<int> numstk;\r\n        int num=0;\r\n        char sign=\'+\';\r\n        for(int i=0;i<s.size();i++){\r\n            if(s[i]>=\'0\'&&s[i]<=\'9\'){\r\n                num = 10*num +(s[i]-\'0\');\r\n            }\r\n            if(s[i]==\'+\'||s[i]==\'-\'||s[i]==\'*\'||s[i]==\'/\'||i==s.size()-1){\r\n                if(sign==\'+\'){\r\n                    numstk.push(num);\r\n                }else if(sign==\'-\'){\r\n                    numstk.push((-1)*num);\r\n                }else if(sign==\'*\'){\r\n                    numstk.top()=numstk.top()*num;\r\n                }else if(sign==\'/\'){\r\n                    numstk.top()=numstk.top()/num;\r\n                }\r\n                num=0;\r\n                sign=s[i];\r\n            }\r\n        }\r\n        int res=0;\r\n        while(!numstk.empty()){\r\n            res+=numstk.top();\r\n            numstk.pop();\r\n        }\r\n        return res;\r\n    }\r\n};\r\n```\r\nWith the advent of the big data era, low-cost storage of ultra-large-scale cold data has become an important challenge. Optical storage has low cost, long life, green, energy-saving, and easy to store, making it very suitable for long-term storage of large-scale cold data. Coaxial holographic optical storage uses holographic multiplexing to store data, which can greatly increase storage density, and use two-dimensional data pages to access data. In order to enable the host to achieve a data read and write throughput rate of 1GB/s, it is necessary Design a high-speed holographic storage data channel.\r\nIn response to the high-speed access requirements of holographic storage, a holographic storage data channel framework is designed. At present, the server host is used as the drive controller of the coaxial holographic optical storage to realize the common block device interface-Small Computer System Interface (SCSI) device End target, and connect multiple initiators through the network SCSI interface (SCSI over internet, iSCSI) to support remote server access through standard block device interface. Design and implement the holographic storage block device driver, convert the upper-level read and write requests into access to the back-end optical storage medium, and support the memory and hard disk analog holographic optical storage, which can be debugged and verified. In order to adapt to the parallel access of multiple read-write heads of coaxial holographic optical discs, a block device multi-queue request processing mechanism based on blk-mq (Block-Multiqueue, blk-mq for short) is designed and implemented to improve the concurrency of IO and reduce the overhead of lock contention. And reduce Cache pollution. In order to simulate the movement of the optical disc by the robotic arm, the optical disc insertion and removal function has also been added. In order to increase the flexibility of the system, the system can also respond to commands sent by the linux system call.\r\nThe prototype of the holographic storage channel was realized and tested. The experimental results can realize the function of the iSCSI target, and its IO throughput rate is about 1082.625MB/s. It can support 4-way concurrency, and its aggregate throughput rate is about 1820.5MB/s.', 'Key words: Holographic-optical-storage, Data access channel, Block device driver', '原创', '', '', '', '\0', '', '2021-10-28 22:48:20', '2021-10-22 13:51:38', '125', '2');
INSERT INTO `t_blog` VALUES ('4', 'Design and implementation of coaxial holographic optical storage data channel driver', '1', '# Abstract\r\n```c++\r\nclass Solution {\r\npublic:\r\n    int calculate(string s) {\r\n        stack<int> numstk;\r\n        int num=0;\r\n        char sign=\'+\';\r\n        for(int i=0;i<s.size();i++){\r\n            if(s[i]>=\'0\'&&s[i]<=\'9\'){\r\n                num = 10*num +(s[i]-\'0\');\r\n            }\r\n            if(s[i]==\'+\'||s[i]==\'-\'||s[i]==\'*\'||s[i]==\'/\'||i==s.size()-1){\r\n                if(sign==\'+\'){\r\n                    numstk.push(num);\r\n                }else if(sign==\'-\'){\r\n                    numstk.push((-1)*num);\r\n                }else if(sign==\'*\'){\r\n                    numstk.top()=numstk.top()*num;\r\n                }else if(sign==\'/\'){\r\n                    numstk.top()=numstk.top()/num;\r\n                }\r\n                num=0;\r\n                sign=s[i];\r\n            }\r\n        }\r\n        int res=0;\r\n        while(!numstk.empty()){\r\n            res+=numstk.top();\r\n            numstk.pop();\r\n        }\r\n        return res;\r\n    }\r\n};\r\n```\r\nWith the advent of the big data era, low-cost storage of ultra-large-scale cold data has become an important challenge. Optical storage has low cost, long life, green, energy-saving, and easy to store, making it very suitable for long-term storage of large-scale cold data. Coaxial holographic optical storage uses holographic multiplexing to store data, which can greatly increase storage density, and use two-dimensional data pages to access data. In order to enable the host to achieve a data read and write throughput rate of 1GB/s, it is necessary Design a high-speed holographic storage data channel.\r\nIn response to the high-speed access requirements of holographic storage, a holographic storage data channel framework is designed. At present, the server host is used as the drive controller of the coaxial holographic optical storage to realize the common block device interface-Small Computer System Interface (SCSI) device End target, and connect multiple initiators through the network SCSI interface (SCSI over internet, iSCSI) to support remote server access through standard block device interface. Design and implement the holographic storage block device driver, convert the upper-level read and write requests into access to the back-end optical storage medium, and support the memory and hard disk analog holographic optical storage, which can be debugged and verified. In order to adapt to the parallel access of multiple read-write heads of coaxial holographic optical discs, a block device multi-queue request processing mechanism based on blk-mq (Block-Multiqueue, blk-mq for short) is designed and implemented to improve the concurrency of IO and reduce the overhead of lock contention. And reduce Cache pollution. In order to simulate the movement of the optical disc by the robotic arm, the optical disc insertion and removal function has also been added. In order to increase the flexibility of the system, the system can also respond to commands sent by the linux system call.\r\nThe prototype of the holographic storage channel was realized and tested. The experimental results can realize the function of the iSCSI target, and its IO throughput rate is about 1082.625MB/s. It can support 4-way concurrency, and its aggregate throughput rate is about 1820.5MB/s.', 'Key words: Holographic-optical-storage, Data access channel, Block device driver', '原创', '', '', '', '\0', '', '2021-10-28 22:48:20', '2021-10-22 13:51:38', '125', '2');

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag` (
  `blog_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`blog_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `t_blog_tag_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_blog_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tag
-- ----------------------------
INSERT INTO `t_blog_tag` VALUES ('1', '1');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) DEFAULT NULL COMMENT '是否是博主',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `email` varchar(255) DEFAULT NULL COMMENT '评论邮箱',
  `nickname` varchar(255) DEFAULT NULL COMMENT '评论昵称',
  `blog_id` int(11) DEFAULT NULL COMMENT 'blog的id',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '', 'https://i.loli.net/2020/10/23/vjgIGxdVzKiUytw.jpg', 'welcome to my blog!', '2021-10-21 15:49:31', '3052720966@qq.com', 'CSerxzm', '1');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', 'mysql');
INSERT INTO `t_tag` VALUES ('2', 'spring');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '分类',
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', 'java', 'https://img13.360buyimg.com/ddimg/jfs/t1/214939/14/1329/933868/61711bd6E17edbb3d/2c70f2fc9563ea5c.jpg');
INSERT INTO `t_type` VALUES ('2', 'c', 'https://img13.360buyimg.com/ddimg/jfs/t1/214939/14/1329/933868/61711bd6E17edbb3d/2c70f2fc9563ea5c.jpg');
INSERT INTO `t_type` VALUES ('3', 'go', 'https://img13.360buyimg.com/ddimg/jfs/t1/214939/14/1329/933868/61711bd6E17edbb3d/2c70f2fc9563ea5c.jpg');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `usertype` int(11) DEFAULT NULL COMMENT '类型,admin',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '5f4dcc3b5aa765d61d8327deb882cf99', 'CSerxzm', 'https://i.loli.net/2020/10/23/vjgIGxdVzKiUytw.jpg', '2020-09-30 15:35:17', '3052720966@qq.com', '1');
