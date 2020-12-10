package com.wendel.config;

import io.r2dbc.spi.ConnectionFactory;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.r2dbc.ReactiveSqlSession;
import org.apache.ibatis.r2dbc.ReactiveSqlSessionFactory;
import org.apache.ibatis.r2dbc.impl.DefaultReactiveSqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/11/27 17:16
 */
@Configuration
public class R2dbcAutoConfiguration{

    @Bean
    public ReactiveSqlSessionFactory reactiveSqlSessionFactory(ConnectionFactory connectionFactory)  {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(this.getClass().getResourceAsStream("/config/mybatis-config.xml"));
        org.apache.ibatis.session.Configuration configuration = xmlConfigBuilder.parse();
        return new DefaultReactiveSqlSessionFactory(configuration, connectionFactory);
    }

    @Primary
    @Bean
    public ReactiveSqlSession masterSqlSessionTemplate(ReactiveSqlSessionFactory sqlSessionFactory) {
        return sqlSessionFactory.openSession();
    }

    @Bean
    @ConditionalOnMissingBean(ReactiveTransactionManager.class)
    public R2dbcTransactionManager connectionFactoryTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
