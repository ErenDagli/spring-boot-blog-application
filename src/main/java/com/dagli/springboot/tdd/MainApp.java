package com.dagli.springboot.tdd;

import com.dagli.springboot.Application;
import org.springframework.boot.SpringApplication;

public class MainApp {

    public static void main(String[] args) {
        for(int i=1;i<=100;i++) {
            System.out.println(i + " , " + FizzBuzz.compute(i));
        }
    }

}
