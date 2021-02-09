package com.example.cookingmeasurementsconverter.Controllers;

import com.example.cookingmeasurementsconverter.Services.ConversionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConversionsController {
    @Autowired
    private ConversionsService conversionsService;

    @GetMapping("/")
    public String index(Model model){
//        model.addAttribute("results");
        return "home";
    }

    @PostMapping("/convert")
    public String conversions(@RequestParam(value = "firstUnit") String firstUnit,
                              @RequestParam(value = "firstAmount") double firstAmount,
                              @RequestParam(value = "secondUnit") String secondUnit,
                              Model model){
        model.addAttribute("results", conversionsService.selector(firstUnit, firstAmount, secondUnit));
        return "home";
    }
}
