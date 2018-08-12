create table  sys_sequence (
  seq_name varchar(50)    not null comment  '序列名称',
  current_value int(11)   not null comment  '当前序列值',
  increment int(11)       not null default '1' comment  '序列增量',
  primary key(seq_name)
)
/
alter table sys_sequence comment '系统序列表'
/
