create  function currval(seq_name varchar(50)) returns int(11)
    reads sql data
    deterministic
begin
declare value integer;
set value = 0;
select current_value into value from sys_sequence where seq_name = seq_name;
return value;
end
/
