package com.dagli.springboot.unittest;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class StudentGrades {

    List<Double> mathGradeResults;

    public StudentGrades() {
    }

    public StudentGrades(List<Double> mathGradeResults) {
        this.mathGradeResults = mathGradeResults;
    }


    public List<Double> getMathGradeResults() {
        return mathGradeResults;
    }

    public void setMathGradeResults(List<Double> mathGradeResults) {
        this.mathGradeResults = mathGradeResults;
    }

    @Override
    public String toString() {
        return "StudentGrades{" +
                "mathGradeResults=" + mathGradeResults +
                '}';
    }

    public double addGradeResultsForSingleClass(List<Double> grades) {
        double result = 0;
        for(double i : grades){
            result +=i;
        }
        return result;
    }

    public double findGradePointAverage (List<Double> grades) {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double average = grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        BigDecimal roundedAverage = BigDecimal.valueOf(average)
                .setScale(2, RoundingMode.HALF_UP);

        return roundedAverage.doubleValue();
    }
    public Boolean isGradeGreater(int numberOne, int numberTwo) {
        return numberOne > numberTwo;
    }

    public Object checkNull(Object obj) {
        return obj;
    }
}
