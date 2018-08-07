create table pmt_permit
(
   permit_id              varchar(10) not null comment '权限ID',
   parent_permit_id       varchar(10) not null comment '父权限ID',
   name                   varchar(50) not null comment '名称',
   icon                   varchar(20) not null comment '图标',
   url                    varchar(100) not null comment 'url',
   status                 varchar(1) comment '是否可用',
   create_time            datetime comment '创建时间',
   last_update_time       datetime comment '最近更新时间',
   primary key (permit_id)
)
/
alter table pmt_user comment '权限表'
/
