package com.wendel.scan;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @author: sunrise
 * @description:
 * @copyright: Copyright 2020 riskeys Corporation. All rights reserved.
 * @Date: 2020-11-27 22:18:49
 */
public class SqlMapperScanner extends ClassPathBeanDefinitionScanner {
    // 这个构造器必须的
    public SqlMapperScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    // 扫描
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 直接调用父类的扫描方法，bean的定义已经被注册
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            // 循环设置属性，为后面实例化做准备
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            // spring源码就是采用这种方式给工厂类注入属性
            // serviceInterface方法名，第二个参数是bean定义
            // definition.getPropertyValues().add("serviceInterface", (Object)
            // definition.getBeanClassName());
            // 我的工厂采用的是构造注入，并且只有一个参数
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, definition.getBeanClassName());
            definition.getConstructorArgumentValues().addArgumentValues(constructorArgumentValues);
            // 设置自己的工厂类
            //definition.setBeanClass(SqlFactoryBean.class);
        }
        return beanDefinitions;
    }

    protected void registerFilters() {
        this.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            if (metadataReader.getClassMetadata().isInterface())
                return true;
            return false;
        });
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
