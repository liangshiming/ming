create table pmt_role
(
   role_id              varchar(50) not null comment '角色ID',
   name                 varchar(100) not null comment '角色名',
   create_time          datetime comment '创建时间',
   last_update_time     datetime comment '最近更新时间',
   primary key (role_id)
)
/
alter table pmt_user comment '角色表'
/
