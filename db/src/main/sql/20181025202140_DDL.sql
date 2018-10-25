/*==============================================================*/
/* table: online_position                                       */
/*==============================================================*/
create table online_position
(
   id                   varchar(32) not null comment '主键',
   company_id           varchar(32) comment '公司id',
   position_name        varchar(64) comment '职位名称',
   recruit_num          varchar(64) comment '招聘人数',
   work_address         varchar(64) comment '工作地点',
   salary               varchar(64) comment '薪资待遇',
   telephone            varchar(64) comment '联系电话',
   post_duty            varchar(64) comment '岗位职责',
   skill_require        varchar(64) comment '技术要求',
   education_require    varchar(64) comment '学历要求',
   position_attract     varchar(64) comment '职位诱惑',
   last_update_time     datetime comment '最近更新时间',
   create_time          datetime comment '创建时间',
   primary key (id)
)
/
alter table online_position comment '企业职位表'
/
