/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 * 配置Mapper扫描包，加快初始化速度。
 * 
 * @author billy (billy_zh@126.com)
 */
@Configuration
@MapperScan({
        "com.kfayun.app.witkey.dao"
})
public class MyBatisConfig {

}
