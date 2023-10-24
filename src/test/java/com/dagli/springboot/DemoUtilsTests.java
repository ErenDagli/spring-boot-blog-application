package com.dagli.springboot;

import com.dagli.springboot.junitdemo.DemoUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;


@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoUtilsTests {

    DemoUtils demoUtils;

    @BeforeAll
    static void setupBeforeEachClass() {
        System.out.println("@BeforeAll executes only once before all test methods execution in the class \n");
    }

    @AfterAll
    static void setupAfterEachClass() {
        System.out.println("@AfterAll executes only once after all test methods execution in the class \n");
    }

    @BeforeEach
    void setupBeforeEach() {
        // set up
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @AfterEach\n");
    }

    @Test
    @DisplayName("Equals and not equals")
    //@Order(1)
    void testEqualsAndNotEquals() {

        System.out.println("Running test : testEqualsAndNotEquals");
        int excepted = 6;
        int unexcepted = 8;

        // execute
        int actual = demoUtils.add(2, 4);

        // assert
        assertEquals(excepted, actual, "2+4 must be 6");
        assertNotEquals(unexcepted, actual, "2+4 must not be 8");
        assertEquals(excepted, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(unexcepted, demoUtils.add(2, 4), "2+4 must not be 8");
    }

    @Test
    void testNullAndNotNull() {
        System.out.println("Running test : testNullAndNotNull");
        String str1 = "dagli";

        assertNull(demoUtils.checkNull(null), "Object should be null");
        assertNotNull(demoUtils.checkNull(str1), "Object should not be null");

    }

    @Test
    void testSameAndNotSame() {
        String str = "ahmet";

        assertSame(demoUtils.getLastname(), demoUtils.getLastnameDuplicate(), "Objects should refer to same object");
        assertNotSame(str, demoUtils.getLastname(), "Objects should not refer to same object");
    }

    @Test
    void testTrueFalse() {
        int numberOne = 10;
        int numberTwo = 20;

        assertTrue(demoUtils.isGreater(numberTwo, numberOne), "This should return true");
        assertFalse(demoUtils.isGreater(numberOne, numberTwo), "This should return false");
    }

    @Test
    void testArrayEquals() {
        String[] strings = {"A","B","C"};

        assertArrayEquals(strings,demoUtils.getFirstThreeLetterOfAlphabet(),"Arrays should be the same");
    }

    @Test
    void testIterableEquals() {
        List<String> nameList = List.of("ahmet","eren","dagli");

        assertIterableEquals(demoUtils.getNameList(), nameList,"Excepted list should be same as actual list");
    }
    @Test
    void testLinesMatch() {
        List<String> nameList = List.of("ahmet","eren","dagli");

        assertLinesMatch(demoUtils.getNameList(), nameList,"Excepted list should be same as actual list");
    }

    @Test
    void testThrowsAndDoesNotThrowException() {

        assertThrows(Exception.class, () -> {demoUtils.throwException(-1);},"Should throw exception");
        assertDoesNotThrow(() -> {demoUtils.throwException(5);},"Should not throw exception");
    }

    @Test
    void testTimeout() {
        assertTimeoutPreemptively(Duration.ofSeconds(3),() -> {demoUtils.checkTimeout();},"Method should execute in 3 seconds");
    }

    @Test
    void testMultiply() {
        int expected = 10;
        assertEquals(expected,demoUtils.multiply(5,2),"5*2 must be 10");
        assertNotEquals(expected,demoUtils.multiply(5,3),"5*3 must not be 10");
    }

    // Maven Test Reports
    //mvn site -DgenerateReports=false
    //mvn clean test
}
