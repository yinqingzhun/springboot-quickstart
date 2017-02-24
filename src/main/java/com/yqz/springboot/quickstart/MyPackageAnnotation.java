package com.yqz.springboot.quickstart;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 包注释演示
 * 
 * @author Administrator
 *
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyPackageAnnotation {
	/**
	 * 获取或设置版本号
	 * 
	 * @return 返回版本号
	 */
	String version() default "0.1";
}
