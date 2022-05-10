package com.example.test;

public class TestTryCatch {
    public static void main(String[] args) {
        try {
            int result = div(3, 0);
            System.out.println("result = " + result);
        } catch (Exception e) {
            /**
             *  多态调用
             *  父类 Exception , 子类是 ArithmeticException
             */
            System.out.println(e.getMessage());  // / by zero     java.lang.ArithmeticException: / by zero
            System.out.println(e.toString());    // java.lang.ArithmeticException: / by zero
            e.printStackTrace();
        }
        System.out.println("Hello World!");      // Hello World!
    }

    public static int div(int a, int b) {
        return  a / b;
    }
}
