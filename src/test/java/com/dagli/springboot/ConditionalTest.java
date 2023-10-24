package com.dagli.springboot;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest(){
        // execute method perform assertions
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void testForWindowsAndMacOnly() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testOnlyForJava17() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testOnlyForJava13() {
        // execute method and perform assertions
    }
    @Test
    @EnabledForJreRange(min=JRE.JAVA_13,max = JRE.JAVA_18)
    void testOnlyForJavaRange() {
        // execute method and perform assertions
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11)
    void testOnlyForJavaRangeMin() {
        // execute method and perform assertions
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "EREN_ENV",matches = "DEV")
    void testOnlyForDevEnviroment() {
        // execute method and perform assertions
    }

    @Test
    @EnabledIfSystemProperty(named = "EREN_SYS_PROP",matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProperty() {
        // execute method and perform assertions
    }
}
