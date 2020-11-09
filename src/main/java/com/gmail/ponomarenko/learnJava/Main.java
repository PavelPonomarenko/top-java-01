package com.gmail.ponomarenko.learnJava;


public class Main {

    public static void main(String[] args) {


        func2();

//        f1();

    }

    private static void func2() {
        Integer num = 20;
        String s = String.valueOf(num);
        StringBuffer sb = new StringBuffer(s);


        System.out.println(sb.reverse());

        if (!(sb.reverse()).equals(s)) {
            System.out.println(1);
        } else {

            System.out.println(37);
        }


    }

    private static void f1() {
        //        java.util.Scanner scanner = new java.util.Scanner(System.in);
//        int aa = Integer.parseInt(scanner.nextLine());
//
//        int bb = Integer.parseInt(scanner.nextLine());
//
//        for (int dd = 1; ; dd++) {
//            if ((dd % aa == 0) && (dd % bb == 0)) {
//                System.out.println(dd);
//                return;
//            }
//        }
    }
}
