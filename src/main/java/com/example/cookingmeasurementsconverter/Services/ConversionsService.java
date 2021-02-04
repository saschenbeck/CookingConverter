package com.example.cookingmeasurementsconverter.Services;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ConversionsService {
    private final DecimalFormat numberFormat = new DecimalFormat("#.0");

    //Selects correct function based on unit types
    public String selector(String firstUnit, double firstAmount, String secondUnit){
        return teaspoonToTableSpoon(firstAmount, firstUnit, secondUnit);
    }
    //Test Function
    //Testing Teaspoons to Tablespoons
    public double tspToTbsp(double numberOfTeaspoons){
        return Math.floor(numberOfTeaspoons/3);
    }

    public String noRemainderStatement(double firstValue, String firstUnit, double secondValue, String secondUnit){
        return firstValue + " " +  firstUnit + " = " + secondValue + " " + secondUnit;
    }

    public String remainderStatement(double firstValue, String firstUnit, double secondValue, String secondUnit, double remainingValue){
        return firstValue + " " +  firstUnit + " ≈ " + secondValue + " " + secondUnit + " and " + numberFormat.format(remainingValue) + " "  + firstUnit;
    }

    public String teaspoonToTableSpoon(double numberOfTeaspoons, String firstUnit, String secondUnit){
        double numberOfTableSpoons = Math.floor(numberOfTeaspoons/3);
        if (numberOfTeaspoons % 3 == 0){
            return noRemainderStatement(numberOfTeaspoons, firstUnit+"(s)", numberOfTableSpoons, secondUnit+"(s)");
        } else {
           return remainderStatement(numberOfTeaspoons, firstUnit+"(s)", numberOfTableSpoons, secondUnit+"(s)", numberOfTeaspoons%3);
        }
    }

}
