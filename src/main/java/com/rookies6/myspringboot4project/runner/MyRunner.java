package com.rookies6.myspringboot4project.runner;

import jakarta.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${spring.application.name}")
    String applicationName;

    @Value("${myboot.name}")
    String name;
    @Value("${myboot.age}")
    int age;
    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        System.out.println("MyRunner run() 호출됨!!");
        System.out.println("application name:" + applicationName);
        // Consumer 인터페이스를 Anonymous Inner Class로 표현
        System.out.println("-----------------------------------------");
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("args name = " + s);
            }
        });
        // args.getOptionNames()의 리턴 타입은 Collection의 Set<String>이다.
        // Iterable의 forEach(Consumer)
        // Consumer의 추상메서드 void accept(T, t)
        // Consumer의 인터페이스를 람다식으로 변환
        System.out.println("-----------------------------------------");
        args.getOptionNames().forEach(optionName -> System.out.println(optionName));
        System.out.println("-----------------------------------------");
        // Consumer의 인터페이스를 Method Reference으로 변환
        args.getOptionNames().forEach(System.out::println);
        System.out.println("-----------------------------------------");

        System.out.println("${myboot.name} = " + name);
        System.out.println("${myboot.age} = " + age);
        System.out.println("${myboot.fullName} = " + environment.getProperty("myboot.fullName"));
    }
}
