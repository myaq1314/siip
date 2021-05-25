package com.pay.siip.util;

import com.pay.siip.exceptions.CommonException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Component(value = "springFactoryUtil")
public class SpringFactoryUtil implements BeanFactoryPostProcessor {

    // Spring应用上下文环境
    private static ConfigurableListableBeanFactory beanFactory;

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        try {
            return (T) beanFactory.getBean(name);
        } catch (BeansException e) {
            throw new CommonException("Bean获取异常");
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return (T) beanFactory.getBean(clazz);
        } catch (BeansException e) {
            throw new CommonException("Bean获取异常");
        }
    }

    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    public static Class<?> getType(String name) {
        try {
            return beanFactory.getType(name);
        } catch (NoSuchBeanDefinitionException e) {
            throw new CommonException("获取Bean类型异常");
        }
    }

    @Override
    public void postProcessBeanFactory(@NotNull ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        SpringFactoryUtil.beanFactory = beanFactory;
    }
}
