package com.example.cookingmeasurementsconverter.Controllers;

import com.example.cookingmeasurementsconverter.Services.ConversionsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConversionsController {
    private ConversionsService conversionsService;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/convert")
    public String conversions(){
        return "home";
    }
}
