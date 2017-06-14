package com.guangxunet.shop.gxmgrsite.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chenmy 2017-06-10
 */
@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//存活在JVM,可以使用反射赋予RequiredPermission功能
public @interface RequiredPermission {
    String value();//表示权限的名称
}
