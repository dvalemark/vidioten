package se.vidioten.databas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/uthyrning")
public class UthyrningController {

    @GetMapping("")
    public String getUthyrning(){

        return "uthyrning";
    }

}
