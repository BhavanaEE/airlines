package com.everest.airline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

}
