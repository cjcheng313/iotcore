package com.ceco;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * mybatisplus 自动生成代码
 * author: wp
 * Date: 2019/07/29
 */
@Slf4j
public class CodeGeneratorTest {

    //文件生成路径(一般都是生成在此项目的src/main/java下面)
    private static final String FILE_DIR = "/Users/duansy/work/ceco_lot/tmp";
    //数据库连接地址
    private static final String DB_URL = "jdbc:mysql://bj-cdb-nnkv8t0g.sql.tencentcdb.com:60707/ceiot?serverTimezone=CTT&characterEncoding=utf-8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull";
    //数据库用户名
    private static final String USER_NAME = "zflive";
    //数据库密码
    private static final String PASSWORD = "zfadmin!";
    //数据库驱动
    private static final String DRIVER_NANME = "com.mysql.cj.jdbc.Driver";
//    private static final String DRIVER_NANME = "com.mysql.jdbc.Driver";
    //表名前缀
    private static final String TABLE_PREX = "ce_";
    //生成代码的父包名
    private static final String PARENT_PACKAGE = "com.ceco.module";
    //实体类继承的父类
    private static final String BASE_ENTITY = "com.ceco.BaseEntity";

    //要生成实体类的表
    private static final String[] TABLE_NAMES = {"ce_version_support_device","ce_firmware_version","ce_device_model","ce_device_panel"};


    public static void main(String[] args) {
        //1、全局配置a
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)            //开启AR模式
                .setAuthor("zmj")           //设置作者
                .setOutputDir(FILE_DIR)         //生成路径(一般都是生成在此项目的src/main/java下面)
                .setFileOverride(true)          //第二次生成会把第一次生成的覆盖掉
                .setIdType(IdType.ASSIGN_ID)         //主键策略
                .setServiceName("I%sService")    //生成的service接口名字首字母是否为I，这样设置就没有I
                .setBaseResultMap(false)        //生成resultMap
                .setBaseColumnList(false);      //在xml中生成基础列

        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)             //数据库类型
                .setUrl(DB_URL)                      //数据库配置
                .setUsername(USER_NAME)              //数据库用户名
                .setPassword(PASSWORD)               //数据库密码
                .setDriverName(DRIVER_NANME);        //驱动

        //3、策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)                                //开启全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)        //下划线到驼峰的命名方式
                .setColumnNaming(NamingStrategy.underline_to_camel)  //下划线到驼峰的命名方式
                .setTablePrefix(TABLE_PREX)                          //表名前缀
                .setSuperEntityClass(BASE_ENTITY)                    //实体类继承的父类
                .setEntityLombokModel(true)                          //使用lombok
                .setRestControllerStyle(true)
                .setInclude(TABLE_NAMES);

        //包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(PARENT_PACKAGE)
                .setMapper("dao")             //持久层包名
                .setService("service")        //业务层包名
                .setController("controller")  //控制层包名
                .setEntity("entity")          //实体类包名
                .setXml("dao");

        //整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();  //执行
        log.info("生成完成");
    }
}
