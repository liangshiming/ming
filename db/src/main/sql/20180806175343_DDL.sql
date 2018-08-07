create table pmt_role_permit
(
   role_id                varchar(50) not null comment '角色ID',
   permit_id              varchar(50) not null comment '用户ID',
   primary key (role_id,permit_id)
)
/
alter table pmt_user_role comment '角色权限映射表'
/
