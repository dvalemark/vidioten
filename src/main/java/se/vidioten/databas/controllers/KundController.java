package se.vidioten.databas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.vidioten.databas.entities.Kund;
import se.vidioten.databas.forms.KundForm;
import se.vidioten.databas.repositories.KundRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/kunder")
public class KundController {

    @Autowired
    private KundRepository kundRepository;

    @GetMapping("")
    public String getKunder(Model model, KundForm kundForm) {
        model.addAttribute("kunder", kundRepository.findAll());
        return "kunder";
    }

    @PostMapping("")
    public String addKund(Model model, @Valid KundForm k, BindingResult bindingResult) {
            if(!bindingResult.hasErrors()){
                kundRepository.save(new Kund(k.getPersonnummer(),k.getNamn(),k.getAddress(),k.getTelefonnummer(),k.getEpost(),k.getPostnummer(),k.getStad(),k.getLand()));
                return "redirect:/kunder";
            }
            model.addAttribute("kunder", kundRepository.findAll());
        return "kunder";
    }


}
