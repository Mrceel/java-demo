package com.example.practicejava.ref;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class ref {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        Refdemo refdemo = new Refdemo1();
//        refdemo.setAge(18);
//        refdemo.setName("张三");
//        refdemo.setCc("cValue");

        //获取类
//        Class ref = refdemo.getClass();
        //ref.getName() 获取refdemo的全名，包名+类名
//        Field f = ref.getField("cc"); // 获取类中指定的属性
         //f.getName() 获取属性值
//        System.out.println(f.getName());
//        System.out.println(f.getType());
//        Field[] fd = ref.getDeclaredFields();
//        System.out.println(Arrays.stream(fd).toList());
//        System.out.println(fd[0].getName());
//        System.out.println(fd[0].getName());
//        System.out.println(f.get(refdemo));

//        for (Field field : fd) {
//            field.setAccessible(true); // 设置属性可访问
//            //field.getName() 获取属性名
//            //field.get(refdemo) 获取属性值
//            System.out.println(field.getName() +", " + field.get(refdemo));
//        }


        Refdemo1 refdemo1 = new Refdemo1();

        Field f = Refdemo1.class.getDeclaredField("name");
        Report annotation = f.getAnnotation(Report.class);
        if (annotation != null) {
//            System.out.println(annotation.type());
//            System.out.println(annotation.level());
//            System.out.println(annotation.value());
            refdemo1.name = annotation.value();
        }

        System.out.println(refdemo1.name);

    }

}

class Refdemo1 {
    @Report("cel")
    public String name;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Report {
    int type() default 0;
    String level() default "info";
    String value() default "";
}
