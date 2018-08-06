create table error_code
(
   err_code             varchar(100) not null comment '错误码',
   message              varchar(128) not null comment '错误信息',
   author               varchar(64) comment '创建者',
   create_time          datetime comment '创建时间',
   last_update_time     datetime comment '最近更新时间',
   primary key (err_code)
)
/
alter table error_code comment '错误码表'
/
