package com.myproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext("com.myproject");

        var bean = context.getBean(OperationsConsoleListener.class);

        bean.start();
        bean.startListen();
        bean.end();
    }
}
