package se.vidioten.databas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Uthyrning;
import se.vidioten.databas.repositories.UthyrningRepository;

import javax.validation.constraints.Max;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/uthyrningar")
public class UthyrningController {

    @Autowired
    private UthyrningRepository uthyrningRepository;

    @GetMapping("")
    public String getUthyrningar(Model model, @RequestParam(required = false) String alternativ) {
        if(alternativ!=null) {
            switch (alternativ) {
                case "Alla":
                    model.addAttribute("uthyrningar", uthyrningRepository.findAll());
                    break;
                case "Uthyrda":
                    model.addAttribute("uthyrningar", uthyrningRepository.findByInlamningsdatumIsNull());
                    break;
                case "Försenade":
                    List<Uthyrning> uthyrningar = uthyrningRepository.findByInlamningsdatumIsNull();
                    List<Uthyrning> försenade = new ArrayList<>();

                    for (Uthyrning uthyrning : uthyrningar) {
                        if (uthyrning.checkIfLate()) {
                            försenade.add(uthyrning);
                        }
                    }
                    model.addAttribute("uthyrningar", försenade);
                    break;

                case "Gammla":
                    model.addAttribute("uthyrningar", uthyrningRepository.findByInlamningsdatumIsNotNull());
                    break;
                default:
                    model.addAttribute("uthyrningar", uthyrningRepository.findAll());
                    break;
            }
        }else{
            model.addAttribute("uthyrningar", uthyrningRepository.findAll());
        }

        return "uthyrningar";
    }


}
