create table sys_service_api
(
   api_id               varchar(32) not null comment '主键',
   api_name             varchar(64) not null comment '接口名称',
   description          varchar(512) comment '接口描述',
   service              varchar(64) comment '对应service',
   method               varchar(64) comment '对应主方法',
   api_version          varchar(10) comment '接口版本',
   status               varchar(1) comment '接口状态：0停用，1可用',
   remark               varchar(512) comment '备注',
   author               varchar(32) comment '开发者',
   need_login           varchar(1) comment '是否需要登录：0不需要，1需要',
   need_token           varchar(1) comment '是否需要token：0不需要，1需要',
   need_req_token       varchar(1) comment '是否需要请求token：0不需要，1需要',
   need_permit          varchar(1) comment '是否需要权限：0不需要，1需要',
   create_time          datetime comment '创建时间',
   last_update_time     datetime comment '最近更新时间',
   primary key (api_id)
)
/
alter table sys_service_api comment '接口总线配置表'
/
