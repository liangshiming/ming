create table CHANGELOG
(
  CHANGE_NUMBER NUMBER(22) not null,
  COMPLETE_DT   TIMESTAMP(6) not null,
  APPLIED_BY    VARCHAR2(100) not null,
  DESCRIPTION   VARCHAR2(500) not null
)
/

alter table CHANGELOG add constraint PK_CHANGELOG primary key (CHANGE_NUMBER) 
/