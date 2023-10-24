package com.dagli.springboot.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {


    // If number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible by Three")
    @Order(1)
    void testForDivisibleByThree() {

        String excepted = "Fizz";

        assertEquals(excepted, FizzBuzz.compute(3), "should return Fizz");
    }

    // If number is divisible by 5, print Buzz
    @Test
    @DisplayName("Divisible by Five")
    @Order(2)
    void testForDivisibleByFive() {

        String excepted = "Buzz";

        assertEquals(excepted, FizzBuzz.compute(5), "should return Buzz");
    }

    // If number is divisible by 3 and 5, print FizzBuzz
    @Test
    @DisplayName("Divisible by Three and Five")
    @Order(3)
    void testForDivisibleByThreeAndFive() {

        String excepted = "FizzBuzz";

        assertEquals(excepted, FizzBuzz.compute(15), "should return FizzBuzz");
    }

    // If number is NOT divisible by 3 or 5, then print the number
    @Test
    @DisplayName("NOT Divisible by Three or Five")
    @Order(4)
    void testForNotDivisibleByThreeOrFive() {

        String excepted = "1";

        assertEquals(excepted, FizzBuzz.compute(1), "should return 1");
    }

    @ParameterizedTest(name = "value={0}, expected = {1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @DisplayName("Testing with small data file")
    @Order(5)
    void testSmallDataFile(int value,String excepted) {
        assertEquals(excepted,FizzBuzz.compute(value) );

    }


}
