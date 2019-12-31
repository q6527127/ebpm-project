package com.ebpm.util;

/**
 * 自定义注解测试类
 * @author xiaodi
 */
@AnnotationUtil(name="xingoo",age=25)
public class AnnotationMainUtil {
    public static void print(Class c){
        System.out.println(c.getName());
        
        //java.lang.Class的getAnnotation方法，如果有注解，则返回注解。否则返回null
        AnnotationUtil person = (AnnotationUtil)c.getAnnotation(AnnotationUtil.class);
        
        if(person != null){
            System.out.println("name:"+person.name()+" age:"+person.age());
        }else{
            System.out.println("person unknown!");
        }
    }
    public static void main(String[] args){
    	AnnotationMainUtil.print(AnnotationMainUtil.class);
    }
}