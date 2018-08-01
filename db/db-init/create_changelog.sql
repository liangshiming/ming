create table changelog
(
  change_number bigint(22) not null,
  complete_dt   timestamp(6) not null,
  applied_by    varchar(100) not null,
  description   varchar(500) not null
);
alter table changelog add constraint pk_changelog primary key (change_number);
