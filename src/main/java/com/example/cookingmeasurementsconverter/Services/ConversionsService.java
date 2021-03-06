package com.example.cookingmeasurementsconverter.Services;

import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ConversionsService {
    //Keep outputs to only 2 decimal place
    private final DecimalFormat twoDecimal = new DecimalFormat("#.00");

    //Keeps outputs to only 3 decimal place
    private final DecimalFormat threeDecimal = new DecimalFormat("#.000");

    //Round up a decimal to its next whole number
    //Currently all decimals will round up, follow up to see about using .25 to get more specific
    private double roundUp(double amount){
        return (Math.ceil(amount * 2) / 2);
    }

    //Converting larger units to smaller
    public String largeToSmall(double firstAmount, String firstUnit, String secondUnit, double multiplier){
        if (firstAmount - Math.floor(firstAmount) != 0){
            return approximateStatement(firstAmount, firstUnit, roundUp(firstAmount*multiplier), secondUnit);
        } else {
            return noRemainderStatement(firstAmount, firstUnit, firstAmount*multiplier, secondUnit);
        }
    }

    //Selects correct function based on unit types
    public String selector(String firstUnit, double firstAmount, String secondUnit){
        if (firstUnit.equals(secondUnit)){
            return noRemainderStatement(firstAmount, firstUnit, firstAmount, secondUnit);
        } else {
            switch (firstUnit) {
                case "teaspoon(s)":
                    return nestedSwitchTsp(secondUnit, firstUnit, firstAmount);
                case "tablespoon(s)":
                    return nestedSwitchTbsp(secondUnit, firstUnit, firstAmount);
                case "cup(s)":
                    return nestedSwitchCups(secondUnit, firstUnit, firstAmount);
                case "ounce(s)":
                    return nestedSwitchOunces(secondUnit, firstUnit, firstAmount);
                default:
                    return "Something went wrong with this conversion";
            }
        }
    }

    //Nested switch statement for converting teaspoons to another unit
    public String nestedSwitchTsp(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "tablespoon(s)":
                return teaspoonToTableSpoon(firstAmount, firstUnit, secondUnit);
            case "cup(s)":
                return teaspoonToCups(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting teaspoons";
        }
    }

    //Nested switch statement for converting tablespoons to another unit
    private String nestedSwitchTbsp(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "teaspoon(s)":
                return tablespoonsToTeaspoons(firstAmount, firstUnit, secondUnit);
            case "cup(s)":
                return tablespoonsToCups(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting tablespoons";
        }
    }

    //Nested switch statement for converting cups into another unit
    private String nestedSwitchCups(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "teaspoon(s)":
                return cupsToTeaspoons(firstAmount, firstUnit, secondUnit);
            case "tablespoon(s)":
                return cupsToTablespoons(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting cups";
        }
    }

    //Nested switch statment for converting ounces to another unit
    private String nestedSwitchOunces(String secondUnit, String firstUnit, double firstAmount){
        switch (secondUnit){
            case "teaspoons(s)":
                return ouncesToTeaspoons(firstAmount, firstUnit, secondUnit);
            case "tablespoon(s)":
                return ouncesToTablespoons(firstAmount, firstUnit, secondUnit);
            case "cup(s)":
                return ouncesToCups(firstAmount, firstUnit, secondUnit);
            default:
                return "Something went wrong with converting ounces";
        }
    }


    //Test Function
    //Testing Teaspoons to Tablespoons
    public double tspToTbsp(double numberOfTeaspoons){
        return Math.floor(numberOfTeaspoons/3);
    }

    //Test Function
    //Testing Teaspoons to Cups
    public double tspToCups(double numberOfTeaspoons){
        return tbspToCups(tspToTbsp(numberOfTeaspoons));
    }

    //Test Function
    //Testing Tablespoons to Teaspoons
    public double tbspToTsp(double numberOfTablespoons){
        return Math.ceil(numberOfTablespoons*3);
    }

    //Test Function
    //Testing Tablespoons to Cups
    public double tbspToCups(double numberOfTablespoons){
            return Math.floor(numberOfTablespoons/16);
    }

    //Test Function
    //Testing Cups to tablespoons
    public double cupsToTbsp(double numberOfCups){
        return Math.ceil(numberOfCups*16);
    }

    //Test Function
    //Testing Cups to teaspoons
    public double cupsToTsp(double numberOfCups){
        return tbspToTsp(cupsToTbsp(numberOfCups));
    }

    //Test Function
    //Testing Cups to ounces
    public double cupsToOz(double numberOfCups){
        return Math.ceil(numberOfCups*8);
    }

    //Test Function
    //Testing Ounces to teaspoons
    public double ozToTsp(double numberOfOunces){
        return cupsToTsp(ozToCups(numberOfOunces));
    }

    //Test Function
    //Testing Ounces to Tablespoons
    public double ozToTbsp(double numberOfOunces){
        return cupsToTbsp(ozToCups(numberOfOunces));
    }

    //Test Function
    //Testing Ounces to Cups
    public double ozToCups(double numberOfOunces){
        return Math.floor(numberOfOunces/8);
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
        return firstValue + " " +  firstUnit + " ≈ " + secondValue + " " + secondUnit + " and " + twoDecimal.format(remainingValue) + " "  + firstUnit;
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

    //Converts number of teaspoons to cups
    public String teaspoonToCups(double numberOfTeaspoons, String teaspoons, String cups){
        double numberOfCups = numberOfTeaspoons/48;
        if (numberOfTeaspoons % 48 == 0 || numberOfTeaspoons % 48 == 24 || numberOfTeaspoons % 48 == 12){
            return noRemainderStatement(numberOfTeaspoons, teaspoons, numberOfCups, cups);
        } else {
            return remainderStatement(numberOfTeaspoons, teaspoons, tspToCups(numberOfTeaspoons), cups, numberOfTeaspoons%48);
        }
    }

    //Converts number of tablespoons to teaspoons
    public String tablespoonsToTeaspoons(double numberOfTablespoons, String tablespoons, String teaspoons){
        return largeToSmall(numberOfTablespoons, tablespoons, teaspoons, 3);
    }

    //Converts number of tablespoons to cups
    public String tablespoonsToCups(double numberOfTablespoons, String tablespoons, String cups){
        double numberOfCups = numberOfTablespoons/16;
        if (numberOfTablespoons % 16 == 0 || numberOfTablespoons % 16 == 8 || numberOfTablespoons % 16 == 4){
            return noRemainderStatement(numberOfTablespoons, tablespoons, numberOfCups, cups);
        } else {
            return remainderStatement(numberOfTablespoons, tablespoons, tbspToCups(numberOfTablespoons), cups, numberOfTablespoons%16);
        }
    }

    //Converts number of cups to teaspoons
    public String cupsToTeaspoons(double numberOfCups, String cups, String teaspoons){
        return largeToSmall(numberOfCups, cups, teaspoons, 48);
    }

    //Converts number of cups to tablespoons
    public String cupsToTablespoons(double numberOfCups, String cups, String tablespoons){
        return largeToSmall(numberOfCups, cups, tablespoons, 16);
    }

    //Converts number of cups to ounces
    public String cupsToOunces(double numberOfCups, String cups, String ounces){
        return largeToSmall(numberOfCups, cups, ounces, 8);
    }

    //Converts number of ounces to cups
    public String ouncesToCups(double numberOfOunces, String ounces, String cups){
        double numberOfCups = numberOfOunces/8;
        if (numberOfOunces % 8 == 0 || numberOfOunces % 8 == 4 || numberOfOunces % 8 == 2){
            return noRemainderStatement(numberOfOunces, ounces, numberOfCups, cups);
        } else {
            return remainderStatement(numberOfOunces, ounces, ozToCups(numberOfOunces), cups, numberOfOunces%8);
        }
    }

    //Converts number of ounces to tablespoons
    public String ouncesToTablespoons(double numberOfOunces, String ounces, String tablespoons){
        return largeToSmall(numberOfOunces, ounces, tablespoons, .5);
    }

    //Converts number of ounces to teaspoons
    public String ouncesToTeaspoons(double numberOfOunces, String ounces, String teaspoons){
        return largeToSmall(numberOfOunces, ounces, teaspoons, .167);
    }

}
