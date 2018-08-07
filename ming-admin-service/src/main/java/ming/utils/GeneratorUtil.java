package ming.utils;

import ming.framework.util.MybatisGeneratorUtil;

import java.util.HashMap;
import java.util.Map;

public class GeneratorUtil {

	public static void main(String[] args) {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/ming";
		String jdbcUsername = "ming";
		String jdbcPassword = "ming";
		Map<String,String> tableModelNames= new HashMap<>();
//		tableModelNames.put("pmt_user","User");
//		tableModelNames.put("pmt_user_role","UseRole");
//		tableModelNames.put("pmt_role","Role");
//		tableModelNames.put("pmt_role_permit","RolePermit");
//		tableModelNames.put("pmt_permit","Permit");
		String packageName = "ming";
		try {
			MybatisGeneratorUtil
					.generator(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, tableModelNames, packageName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
