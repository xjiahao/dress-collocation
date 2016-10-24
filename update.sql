---------- 用户sql 20161024
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`user_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id' ,
`user_name`  varchar(255) NOT NULL COMMENT '用户名' ,
`user_pwd`  varchar(255) NOT NULL COMMENT '用户密码' ,
`user_nick`  varchar(255) NULL DEFAULT NULL COMMENT '用户昵称' ,
`email`  varchar(255) NULL DEFAULT NULL COMMENT '用户邮箱' ,
`phone_number`  varchar(255) NULL DEFAULT NULL COMMENT '用户手机号' ,
`sex`  tinyint(1) NULL DEFAULT 1 COMMENT '用户性别 0表示女性 1表示男性' ,
`attributes`  varchar(4096) NULL DEFAULT NULL ,
`gmt_create`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`gmt_update`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`user_id`),
UNIQUE INDEX `user_name` (`user_name`) USING BTREE ,
INDEX `email_index` (`email`) USING BTREE ,
INDEX `phone_numer_index` (`phone_number`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin;
