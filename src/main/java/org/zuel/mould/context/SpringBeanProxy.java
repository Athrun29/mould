package org.zuel.mould.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring bean  工具类
 */

@Component
public class SpringBeanProxy implements ApplicationContextAware {

	// Spring应用上下文环境
    private static ApplicationContext applicationContext;
 
    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        setApplicationContextStatic(applicationContext);
    }


    public static void setApplicationContextStatic(ApplicationContext context){
          applicationContext=context;
    }
 
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


 
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }
}

