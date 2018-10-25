/*==============================================================*/
/* table: online_company                                        */
/*==============================================================*/
create table online_company
(
   id                   varchar(32) not null comment '主键',
   phone                varchar(32) comment '手机号',
   password             varchar(64) comment '密码',
   company_name         varchar(64) comment '公司名称',
   industry             varchar(64) comment '所属行业',
   enterprise_attribute varchar(64) comment '企业属性',
   staff_num            varchar(64) comment '员工人数',
   company_address      varchar(64) comment '公司地址',
   company_profile      varchar(64) comment '公司简介',
   contact              varchar(64) comment '联系人',
   telephone            varchar(64) comment '联系电话',
   email                varchar(64) comment '邮箱',
   last_update_time     datetime comment '最近更新时间',
   create_time          datetime comment '创建时间',
   primary key (id)
)
/
alter table online_company comment '企业账户表'
/
