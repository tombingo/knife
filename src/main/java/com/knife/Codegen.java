package com.knife;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/7/31 15:29
 * @Version 1.0
 */
public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/multiple_phone?characterEncoding=utf-8&useSSL=true&serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("euwieuwieuweuewu&&@#*@#^23!D");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("com.knife.mybaties");


        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
//        globalConfig.setServiceGenerateEnable(true);
//        globalConfig.setServiceImplGenerateEnable(true);

        //设置生成controller
        globalConfig.setControllerGenerateEnable(true);

        return globalConfig;
    }
}
