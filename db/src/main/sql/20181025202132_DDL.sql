/*==============================================================*/
/* table: online_resume                                         */
/*==============================================================*/
create table online_resume
(
   id                   varchar(32) not null comment '主键',
   cust_id              varchar(32) comment '客户id',
   name                 varchar(64) comment '真实姓名',
   sex                  varchar(32) comment '性别',
   id_number            varchar(32) comment '身份证号',
   birthday             varchar(32) comment '生日',
   address              varchar(32) comment '通讯地址',
   phone                varchar(32) comment '手机',
   mail                 varchar(32) comment '邮箱',
   education            varchar(32) comment '最高学历',
   graduate_school      varchar(32) comment '毕业学校',
   major                varchar(32) comment '主修专业',
   education_experience varchar(32) comment '教育经历',
   position             varchar(32) comment '应聘岗位',
   salary               varchar(32) comment '月薪要求',
   work_experience      varchar(32) comment '工作经验',
   person_introduce     varchar(32) comment '个人介绍',
   last_update_time     datetime comment '最近更新时间',
   create_time          datetime comment '创建时间',
   primary key (id)
)
/
alter table online_resume comment '客户简历表'
/
