package com.example.cookingmeasurementsconverter.ConverterTest;
import static org.junit.Assert.*;
import com.example.cookingmeasurementsconverter.Services.ConversionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConverterTest {
    @Autowired
    private ConversionsService conversionsService;


    @Test
    public void teaspoonsToTablespoonsEquals(){
        System.out.println("Test Start");
        double actual = conversionsService.tspToTbsp(3);
        assertEquals(1.0, actual, 0);
        System.out.println("Test complete");
    }

    @Test
    public void tablespoonsToTeaspoonsEquals(){
        System.out.println("Test Start");
        double actual = conversionsService.tbspToTsp(1);
        assertEquals(3.0, actual, 0);
        System.out.println("Test Complete");
    }
}
