package com.wendel.config;

import io.r2dbc.spi.ConnectionFactory;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.r2dbc.ReactiveSqlSession;
import org.apache.ibatis.r2dbc.ReactiveSqlSessionFactory;
import org.apache.ibatis.r2dbc.impl.DefaultReactiveSqlSessionFactory;
import org.apache.ibatis.r2dbc.type.EnumOrdinalTypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.io.IOException;
import java.math.RoundingMode;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/11/27 17:16
 */
@Configuration
public class R2dbcAutoConfiguration{
    @Bean
    public ReactiveSqlSessionFactory createReactiveSqlSessionFactory(ConnectionFactory connectionFactory)  {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        try {
            configuration.getTypeHandlerRegistry().register(RoundingMode.class, EnumOrdinalTypeHandler.class);

            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*.xml");
            for (Resource resource : resources) {
                XMLMapperBuilder mapperParser = new XMLMapperBuilder(resource.getInputStream(), configuration, resource.getFilename(), configuration.getSqlFragments());
                mapperParser.parse();
                configuration.addLoadedResource(resource.getFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultReactiveSqlSessionFactory(configuration, connectionFactory);
    }

    @Primary
    @Bean
    public ReactiveSqlSession createMasterSqlSessionTemplate(ReactiveSqlSessionFactory sqlSessionFactory) {
        return sqlSessionFactory.openSession();
    }

    @Bean
    @ConditionalOnMissingBean(ReactiveTransactionManager.class)
    public R2dbcTransactionManager createConnectionFactoryTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
