/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/22 14:41:46                           */
/*==============================================================*/


drop table if exists button_table;

drop table if exists menu_button_table;

drop table if exists menu_table;

drop table if exists role_menu_table;

drop table if exists role_table;

drop table if exists user_role_table;

drop table if exists user_table;

/*==============================================================*/
/* Table: button_table                                          */
/*==============================================================*/
create table button_table
(
   id                   varchar(255) not null comment '主键',
   button_name          varchar(255) comment '按钮中文名称',
   button_code          varchar(255) comment '按钮标识',
   remark               varchar(255) comment '备注',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id),
   unique key AK_Key_2 (button_code)
);

alter table button_table comment '按钮权限表';

/*==============================================================*/
/* Table: menu_button_table                                     */
/*==============================================================*/
create table menu_button_table
(
   id                   varchar(255) not null comment '主键',
   menu_id              varchar(255) comment '菜单主键',
   button_id            varchar(255) comment '按钮主键',
   primary key (id)
);

alter table menu_button_table comment '菜单按钮中间表';

/*==============================================================*/
/* Table: menu_table                                            */
/*==============================================================*/
create table menu_table
(
   id                   varchar(255) not null comment '主键',
   pid                  varchar(255) comment '父级主键',
   menu_name            varchar(255) comment '菜单中文名',
   menu_code            varchar(255) comment '菜单标识',
   menu_type            varchar(255) comment '菜单类型（0、一级菜单，1、二级菜单）',
   remark               varchar(255) comment '备注',
   data                 varchar(255) comment '其他的数据标识',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id),
   unique key AK_Key_2 (menu_code)
);

alter table menu_table comment '菜单资源表';

/*==============================================================*/
/* Table: role_menu_table                                       */
/*==============================================================*/
create table role_menu_table
(
   id                   varchar(255) not null comment '主键',
   role_id              varchar(255) comment '角色主键',
   menu_id              varchar(255) comment '菜单主键',
   primary key (id)
);

alter table role_menu_table comment '角色菜单中间表';

/*==============================================================*/
/* Table: role_table                                            */
/*==============================================================*/
create table role_table
(
   id                   varchar(255) not null,
   role_name            varchar(255) comment '角色的中文名称',
   role_code            varchar(255) comment '角色的标识',
   remark               varchar(255) comment '备注',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id),
   unique key AK_Key_2 (role_code)
);

alter table role_table comment '角色表';

/*==============================================================*/
/* Table: user_role_table                                       */
/*==============================================================*/
create table user_role_table
(
   id                   varchar(255) not null comment '主键',
   user_id              varchar(255) comment '用户id',
   role_id              varchar(255) comment '角色id',
   primary key (id)
);

alter table user_role_table comment '用户角色中间表';

/*==============================================================*/
/* Table: user_table                                            */
/*==============================================================*/
create table user_table
(
   id                   varchar(255) not null comment '主键',
   user_name            varchar(255) not null comment '昵称',
   file_id              varchar(255) comment '附件id',
   user_code            varchar(255) not null comment '账号',
   password             varchar(255) not null comment '密码',
   sex                  int comment '性别（1男2女）',
   "dec"                varchar(255) comment '描述',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id),
   unique key AK_user_code (user_code)
);

alter table user_table comment '用户表';

