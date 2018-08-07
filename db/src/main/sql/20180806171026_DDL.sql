create table pmt_user_role
(
   user_id              varchar(50) not null comment '用户ID',
   role_id              varchar(50) not null comment '角色ID',
   primary key (user_id,role_id)
)
/
alter table pmt_user_role comment '用户角色映射表'
/
