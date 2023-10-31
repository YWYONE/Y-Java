package com.example.demo.Annotation;

import java.lang.reflect.Field;

/**
 * Java的注解本身对代码逻辑没有任何影响。是一种的特殊的存有值的注释，由第三方程序根据其值及其注解的组件进行一系列操作
 * 根据@Retention的配置：
 *
 * SOURCE类型的注解在编译期就被丢掉了；（如Lombok的Data注解，读取后生成get set函数的字节码）
 * CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM；
 * RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取。
 * 如何使用注解完全由工具决定。SOURCE类型的注解主要由编译器使用，因此我们一般只使用，不编写。CLASS类型的注解主要由底层工具库使用，涉及到class的加载，一般我们很少用到。只有RUNTIME类型的注解不但要使用，还经常需要编写。
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Person person=new Person();
        person.setName("billlllfuyds");
        person.setAge(180);
        try {
            check(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理注解
     * @param person
     * @throws ReflectiveOperationException
     */
    public static void check(Person person) throws ReflectiveOperationException{
        for(Field field:person.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Range range=field.getAnnotation(Range.class);
            if(range!=null){
                Object value=field.get(person);
                if(value instanceof String s){
                    if(s.length()>range.stringMax()||s.length()< range.stringMin()){
                        throw new IllegalArgumentException("Invalid_field:"+field.getName());
                    }
                }
                if(value instanceof Integer x){
                    if(x>range.integerMax()||x< range.integerMin()){
                        throw new IllegalArgumentException("Invalid_field:"+field.getName());
                    }
                }

            }
        }
    }
}
