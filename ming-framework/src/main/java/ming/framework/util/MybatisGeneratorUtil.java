package ming.framework.util;

import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MybatisGenerator生成类
 * Created by Jrick on 2018/1/14.
 */
public class MybatisGeneratorUtil {

	// generatorConfig模板路径
	private static String generatorConfig_vm = "/generatorConfig.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 *
	 * @param jdbcDriver      驱动路径
	 * @param jdbcUrl         链接
	 * @param jdbcUsername    帐号
	 * @param jdbcPassword    密码
	 * @param tableModelNames 表名与实体名
	 * @param packageName     包名
	 */
	public static void generator(String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword,
			Map<String, String> tableModelNames, String packageName) throws Exception {

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			generatorConfig_vm =
					MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
		} else {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
		}

		String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "");
		String generatorConfigXml =
				MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "")
						+ "/src/main/resources/generatorConfig.xml";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			for (String tableName : tableModelNames.keySet()) {
				System.out.println(tableName);
				table = new HashMap<>(2);
				table.put("table_name", tableName);
				table.put("model_name", tableModelNames.get(tableName));
				tables.add(table);
			}

			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", packageName + ".po");
			context.put("generator_sqlMapGenerator_targetPackage", packageName + ".dao");
			context.put("generator_javaClientGenerator_targetPackage", packageName + ".dao");
			context.put("targetProject", basePath);
			context.put("generator_jdbc_driver", jdbcDriver);
			context.put("generator_jdbc_url", jdbcUrl);
			context.put("generator_jdbc_username", jdbcUsername);
			context.put("generator_jdbc_password", jdbcPassword);
			VelocityUtil.generate(generatorConfig_vm, generatorConfigXml, context);
		} catch (Exception e) {
			System.out.println("生成generatorConfig.xml文件发生异常" + e.getMessage());
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");
		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		File configFile = new File(generatorConfigXml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		configFile.delete();//生成完毕,删除配置文件
		System.out.println("========== 结束运行MybatisGenerator ==========");
	}
}
