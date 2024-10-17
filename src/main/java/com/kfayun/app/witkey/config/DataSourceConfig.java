/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 数据源配置
 * 
 * @author billy (billy_zh@126.com)
 */
@Configuration
public class DataSourceConfig {

    private static final String MYSQL_JDBC_URL = "jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";

    @Value("${app.db.server}")
    private String dbServer;

    @Value("${app.db.database}")
    private String dbDatabase;

    @Value("${app.db.username}")
    private String dbUsername;

    @Value("${app.db.password}")
    private String dbPassword;

    @Value("${datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "datasource")
    @Primary
    public DataSource dataSource() {

        checkDatabase();

        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(String.format(MYSQL_JDBC_URL, dbServer, dbDatabase))
                .username(dbUsername)
                .password(dbPassword);
        return builder.build();
    }

    private void checkDatabase() {

        //1、加载jdbc驱动
        try {
            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            String url = String.format(MYSQL_JDBC_URL, dbServer, "mysql");
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {

                // try (Statement stmt = conn.createStatement()) {
                //    stmt.executeQuery("SELECT 1");
                // }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
