create table pmt_user
(
   user_id              varchar(50) not null comment '用户ID',
   name                 varchar(100) not null comment '用户名',
   nick_name            varchar(100) not null comment '昵称',
   password             varchar(128) comment '密码',
   status               varchar(1) comment '账号状态',
   create_time          datetime comment '创建时间',
   last_update_time     datetime comment '最近更新时间',
   primary key (user_id)
)
/
alter table pmt_user comment '用户表'
/
