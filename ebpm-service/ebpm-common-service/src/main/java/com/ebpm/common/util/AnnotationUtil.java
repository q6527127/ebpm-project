package com.ebpm.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 注解定义类
 * @author xiaodi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationUtil {
	String name();
	int age();
}
