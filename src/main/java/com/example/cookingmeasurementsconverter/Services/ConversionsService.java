package com.example.cookingmeasurementsconverter.Services;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ConversionsService {
    //Keep outputs to only 1 decimal place
    private final DecimalFormat numberFormat = new DecimalFormat("#.0");

    //Round up a decimal to its next whole number
    //Currently all decimals will round up, follow up to see about using .25 to get more specific
    private double roundUp(double amount){
        return (Math.ceil(amount * 2) / 2);
    }

    //Selects correct function based on unit types
    public String selector(String firstUnit, double firstAmount, String secondUnit){
        switch (firstUnit){
            case "teaspoon(s)":
                return nestedSwitchTsp(secondUnit, firstUnit, firstAmount);
            case "tablespoon(s)":
                return nestedSwitchTbsp(secondUnit, firstUnit, firstAmount);
            default:
                return "Something went wrong with this conversion";
        }
    }

    //Nested switch statement for converting teaspoons to another unit
    public String nestedSwitchTsp(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "tablespoon(s)":
                return teaspoonToTableSpoon(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting teaspoons";
        }
    }

    //Nested switch statement for converting tablespoons to another unit
    private String nestedSwitchTbsp(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "teaspoon(s)":
                return tablespoonsToTeaspoons(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting tablespoons";
        }
    }
    //Test Function
    //Testing Teaspoons to Tablespoons
    public double tspToTbsp(double numberOfTeaspoons){
        return Math.floor(numberOfTeaspoons/3);
    }

    //Return String for when conversion results in only a whole number
    public String noRemainderStatement(double firstValue, String firstUnit, double secondValue, String secondUnit){
        return firstValue + " " +  firstUnit + " = " + secondValue + " " + secondUnit;
    }

    //Return String for when conversion is approximated
    public String approximateStatement(double firstValue, String firstUnit, double secondValue,  String secondUnit){
        return firstValue + " " + firstUnit + " ≈ " + secondValue + " " + secondUnit;
    }

    //Return string for when conversion results in a decimal
    public String remainderStatement(double firstValue, String firstUnit, double secondValue, String secondUnit, double remainingValue){
        return firstValue + " " +  firstUnit + " ≈ " + secondValue + " " + secondUnit + " and " + numberFormat.format(remainingValue) + " "  + firstUnit;
    }

    //Converts number of teaspoons to tablespoons
    public String teaspoonToTableSpoon(double numberOfTeaspoons, String teaspoons, String tablespoons){
        double numberOfTableSpoons = tspToTbsp(numberOfTeaspoons);
        if (numberOfTeaspoons % 3 == 0){
            return noRemainderStatement(numberOfTeaspoons, teaspoons, numberOfTableSpoons, tablespoons);
        } else {
           return remainderStatement(numberOfTeaspoons, teaspoons, numberOfTableSpoons, tablespoons, numberOfTeaspoons%3);
        }
    }

    //Converts number of tablespoons to teaspoons
    public String tablespoonsToTeaspoons(double numberOfTablespoons, String tablespoons, String teaspoons){
        if (numberOfTablespoons - Math.floor(numberOfTablespoons) != 0){
            return approximateStatement(numberOfTablespoons, tablespoons, roundUp((numberOfTablespoons*3)), teaspoons);
        } else {
            return noRemainderStatement(numberOfTablespoons, tablespoons, numberOfTablespoons*3, teaspoons);
        }
    }

}
