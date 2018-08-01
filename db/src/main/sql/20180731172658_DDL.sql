create table SERVICE_API
(
   API_ID               varchar(32) not null comment '主键',
   API_NAME             varchar(64) not null comment '接口名称',
   DESCRIPTION          varchar(512) comment '接口描述',
   SERVICE              varchar(64) comment '对应Service',
   METHOD               varchar(64) comment '对应主方法',
   API_VERSION          varchar(10) comment '接口版本',
   STATUS               varchar(1) comment '接口状态：0停用，1可用',
   REMARK               varchar(512) comment '备注',
   AUTHOR               varchar(32) comment '开发者',
   NEED_LOGIN           varchar(1) comment '是否需要登录：0不需要，1需要',
   NEED_TOKEN           varchar(1) comment '是否需要Token：0不需要，1需要',
   NEED_REQ_TOKEN       varchar(1) comment '是否需要请求Token：0不需要，1需要',
   NEED_PERMIT          varchar(1) comment '是否需要权限：0不需要，1需要',
   CREATE_TIME          timestamp comment '创建时间',
   LAST_UPDATE_TIME     timestamp comment '最近更新时间',
   primary key (API_ID)
)
/
alter table SERVICE_API comment '接口总线配置表'
/
