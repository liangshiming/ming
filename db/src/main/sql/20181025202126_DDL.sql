/*==============================================================*/
/* table: online_customer                                       */
/*==============================================================*/
create table online_customer
(
   id                   varchar(32) not null comment '主键',
   phone                varchar(32) comment '手机号',
   nick_name            varchar(64) comment '昵称',
   password             varchar(64) comment '密码',
   last_update_time     datetime comment '最近更新时间',
   create_time          datetime comment '创建时间',
   primary key (id)
)
/
alter table online_customer comment '客户账户表'
/
