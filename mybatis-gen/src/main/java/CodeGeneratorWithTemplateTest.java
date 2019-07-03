import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 二师兄超级帅
 * @Title: CodeGeneratorWithTemplateTest
 * @ProjectName mybatis-plus-samples
 * @Description: TODO
 * @date 2019/1/2516:32
 */
public class CodeGeneratorWithTemplateTest {

    /**
     * 是否强制带上注解
     */
    static boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    static IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    static String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * 默认是以I开头
     * user表 -> IUserService, UserServiceImpl
     */
    static boolean serviceClassNameStartWithI = true;

    public static void main(String[] args) {
        // TODO: 2019/1/26  生成包名
        String packageName = "com.zwl.mall";
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName + ".tmp", "user_energy_expire_time");
//        generateByTables(packageName, "statistics_by_day");
    }


    private static void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/mall_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Hongkong";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);
        config.setActiveRecord(true)
                .setIdType(tableIdType)
                .setAuthor("二师兄超级帅")
                // TODO: 2019/1/26  输出路径
                .setOutputDir("C:\\Users\\zwl\\Desktop\\生成代码")
                .setFileOverride(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList;
                .setBaseColumnList(true);
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        TemplateConfig templateConfig = new TemplateConfig()
                .setController("templates/controller.java")
                .setService("templates/service.java")
                .setServiceImpl("templates/serviceImpl.java")
                .setEntity("templates/entity.java")
                .setMapper("templates/mapper.java")
                .setXml("templates/mapper.xml");
        //api2Doc文档生成
//                .setController("templates/api2docController.java");
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            //自定义属性注入:abc
//            //在.vm/ftl模板中，通过${cfg.abc}获取属性
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                //配置自定义模板
                .setTemplate(templateConfig)
                //配置自定义属性注入
//                .setCfg(injectionConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}
