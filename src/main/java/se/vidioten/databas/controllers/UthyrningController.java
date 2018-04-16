package se.vidioten.databas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.vidioten.databas.repositories.UthyrningRepository;

@Controller
@RequestMapping("/uthyrningar")
public class UthyrningController {

    @Autowired
    private UthyrningRepository uthyrningRepository;

    @GetMapping("")
    public String getUthyrningar(Model model) {
        model.addAttribute("uthyrningar", uthyrningRepository.findAll());
        return "uthyrningar";
    }

}
