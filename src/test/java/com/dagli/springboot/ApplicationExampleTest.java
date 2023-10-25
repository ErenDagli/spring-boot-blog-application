package com.dagli.springboot;

import com.dagli.springboot.unittest.CollegeStudent;
import com.dagli.springboot.unittest.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        count += 1;
        System.out.println("Testing : " + appInfo + " which is " + appDescription + " version: " + appVersion + ". Execution of test method");

        student.setFirstName("eren");
        student.setLastName("dagli");
        student.setEmailAddress("eren.dagli@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,85.0,76.5,91.75)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("Add grade results for student grades")
    void addGradeResultsForStudentGrades() {
        assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @Test
    @DisplayName("Add grade results for student grades not equal")
    void addGradeResultsForStudentGradesNotEquals() {
        assertNotEquals(0,studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @Test
    @DisplayName("Is grade greater")
    void isGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(90,75),"failure - should be true");
    }
    @Test
    @DisplayName("Is grade greater false")
    void isGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(70,75),"failure - should be false");
    }
    @Test
    @DisplayName("Check null for student grades")
    void checkNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),"object should not be null");
    }

    @DisplayName("Create student without grade init")
    @Test
    void createStudentWithoutGradesInit() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstName("ahmet");
        studentTwo.setLastName("dagli");
        studentTwo.setEmailAddress("ahmet.dagli@gmail.com");
        assertNotNull(studentTwo.getFirstName());
        assertNotNull(studentTwo.getLastName());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @DisplayName("Verify students are prototypes")
    @Test
    void verifyStudentsArePrototypes() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(student,studentTwo);
    }

    @DisplayName("Find Grade Point Average")
    @Test
    void findGradePointAverage() {
        assertAll("Testing all assertEquals",() -> assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        )),() -> assertEquals(88.31,studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults())));
    }
}
