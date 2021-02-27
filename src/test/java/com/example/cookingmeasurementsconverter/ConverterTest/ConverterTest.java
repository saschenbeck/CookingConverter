package com.example.cookingmeasurementsconverter.ConverterTest;
import static org.junit.Assert.*;
import com.example.cookingmeasurementsconverter.Services.ConversionsService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConverterTest {
    @Autowired
    private ConversionsService conversionsService;

    private final String start = "Test Start";
    private static void checkpoint(int counter){
        System.out.println("Checkpoint " + counter);
    }
    private final String finish = "Test Completed";

    @Test
    public void teaspoonsToTablespoonsEquals(){
        System.out.println(start);
        double actual = conversionsService.tspToTbsp(3);
        assertEquals(1.0, actual, 0);

        checkpoint(1);

        actual = conversionsService.tspToTbsp(4);
        assertEquals(1, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void teaspoonsToTablespoonsNotEquals(){
        System.out.println(start);
        double actual = conversionsService.tspToTbsp(3);
        assertNotEquals(1.1, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void tablespoonsToTeaspoonsEquals(){
        System.out.println(start);
        double actual = conversionsService.tbspToTsp(1);
        assertEquals(3.0, actual, 0);

        checkpoint(1);

        actual = conversionsService.tbspToTsp(1.1);
        assertEquals(4.0, actual, 0);

        checkpoint(2);

        actual = conversionsService.tbspToTsp(1.4);
        assertEquals(5.0, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void tablespoonsToTeaspoonsNotEquals(){
        System.out.println(start);
        double actual = conversionsService.tbspToTsp(1);
        assertNotEquals(3.1, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void tablespoonsToCupsEquals(){
        System.out.println(start);
        double actual = conversionsService.tbspToCups(16);
        assertEquals(1, actual, 0);
        checkpoint(1);
        actual = conversionsService.tbspToCups(8);
        assertEquals(.5, actual, .5);
        checkpoint(2);
        actual = conversionsService.tbspToCups(4);
        assertEquals(.25, actual, .25);
        System.out.println(finish);
    }

    @Test
    public void tablespoonsToCupsNotEquals(){
        System.out.println(start);
        double actual = conversionsService.tbspToCups(16);
        assertNotEquals(1.1, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void cupsToTeaspoonsEquals(){
        System.out.println(start);
        double actual = conversionsService.cupsToTsp(1);
        assertEquals(48, actual, 0);
        checkpoint(1);
        actual = conversionsService.cupsToTsp(1.1);
        assertEquals(53, actual, 1);
        System.out.println(finish);
    }

    @Test
    public void cupsToTeaspoonsNotEquals(){
        System.out.println(start);
        double actual = conversionsService.cupsToTsp(1);
        assertNotEquals(49, actual, 0);
        checkpoint(1);
        actual = conversionsService.cupsToTsp(1.1);
        assertNotEquals(48, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void cupsToOuncesEquals(){
        System.out.println(start);
        double actual = conversionsService.cupsToOz(1);
        assertEquals(8, actual, 0);
        checkpoint(1);
        actual = conversionsService.cupsToOz(2);
        assertEquals(16, actual, 0);
        checkpoint(2);
        actual = conversionsService.cupsToOz(1.5);
        assertEquals(12, actual, 0);
        System.out.println(finish);
    }

    @Test
    public void cupsToOuncesNotEquals(){
        System.out.println(start);
        double actual = conversionsService.cupsToOz(1);
        assertNotEquals(7, actual, 0);
        System.out.println(finish);
    }
}
