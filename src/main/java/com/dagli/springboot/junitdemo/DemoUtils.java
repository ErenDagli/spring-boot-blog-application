package com.dagli.springboot.junitdemo;

import java.util.List;

public class DemoUtils {

    private String lastname = "dagli";
    private String lastnameDuplicate = lastname;

    private String[] firstThreeLetterOfAlphabet = {"A", "B", "C"};

    private List<String> nameList = List.of("ahmet", "eren", "dagli");

    public String getLastname() {
        return lastname;
    }

    public String getLastnameDuplicate() {
        return lastnameDuplicate;
    }

    public String[] getFirstThreeLetterOfAlphabet() {
        return firstThreeLetterOfAlphabet;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public Object checkNull(Object obj) {
        return obj;
    }

    public Boolean isGreater(int a, int b) {
        return a > b;
    }

    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
