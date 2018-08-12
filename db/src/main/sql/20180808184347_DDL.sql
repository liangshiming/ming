create  function nextval(seq_name varchar(50)) returns int(11)
    deterministic
begin
update sys_sequence set current_value = current_value + increment where seq_name = seq_name;
return currval(seq_name);
end
/
