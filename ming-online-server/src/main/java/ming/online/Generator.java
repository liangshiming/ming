package ming.online;


import ming.framework.util.MybatisGeneratorUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 * Created by Jrick on 2018/1/14.
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "ming-framework";
    private static String DATABASE = "ming";
    private static String[] TABLES = {"service_api"};
    private static String PACKAGE_NAME = "ming.framework.core";
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/ming";
    private static String JDBC_USERNAME = "root";
    private static String JDBC_PASSWORD = "123";

    /**
     * 自动代码生成
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLES, PACKAGE_NAME);
    }

}
