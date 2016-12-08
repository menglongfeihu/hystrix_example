/**
 * Copyright (c) 2012 Sohu. All Rights Reserved
 */
package com.sohu.tv.btrace;

import java.util.Scanner;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    /**
     * @description:
     * @author anzengli (anzengli@sohu-inc.com)
     * @date 2016-11-18 下午4:00:29
     * @version V1.0
     * @param args
     * @return void
     * @throws
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.println("caculate sum :");
        while (scanner.hasNext()) {
            System.out.println("please input first number:");
            int a = scanner.nextInt();
            System.out.println("please input second number:");
            int b = scanner.nextInt();
            int c = calculator.add(a, b);
            System.out.print("result is :");
            System.out.println(String.format("%d + %d = %d", a, b, c));
        }
    }

}
