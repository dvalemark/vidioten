package se.vidioten.databas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Kund;
import se.vidioten.databas.entities.Uthyrning;
import se.vidioten.databas.forms.KundForm;
import se.vidioten.databas.repositories.FilmRepository;
import se.vidioten.databas.repositories.KundRepository;
import se.vidioten.databas.repositories.UthyrningRepository;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/kunder")
public class KundController {

    @Autowired
    private KundRepository kundRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private UthyrningRepository uthyrningRepository;

    @GetMapping("")
    public String getKunder(Model model, KundForm kundForm) {
        model.addAttribute("kunder", kundRepository.findAll());
        return "kunder";
    }

    @GetMapping("byId/{personnummer}")
    public String getById(@PathVariable String personnummer, Model model, KundForm kundForm) {
        model.addAttribute("kund", kundRepository.findByPersonnummer(personnummer));
        return "kund";
    }

    @PostMapping("")
    public String addKund(Model model, @Valid KundForm k, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            kundRepository.save(new Kund(k.getPersonnummer(), k.getNamn(), k.getAddress(), k.getTelefonnummer(), k.getEpost(), k.getPostnummer(), k.getStad(), k.getLand()));
            model.addAttribute("kunder", kundRepository.findAll());
            model.addAttribute("error", null);
            return "redirect:/kunder";
        }else{
            model.addAttribute("kunder", kundRepository.findAll());
            model.addAttribute("error", "error");
        }
        return "kunder";
    }

    @PostMapping("byId/delete{personnummer}")
    public String deleteKund(@PathVariable String personnummer) {
        Kund kund = kundRepository.findByPersonnummer(personnummer);
        kundRepository.delete(kund);
        return "redirect:/kunder";
    }

    @PostMapping("byId/return{personnummer}")
    public String returnMovies(@PathVariable String personnummer) {
        System.out.println(personnummer);
        List<Film> filmer = filmRepository.findAllByKund_Personnummer(personnummer);
        Date inlamingsdatum = Date.valueOf(LocalDate.now());
        for (Film film : filmer) {
            film.setKund(null);
            filmRepository.save(film);
            Uthyrning uthyrning = uthyrningRepository.findByFilmAndAndInlamningsdatumIsNull(film);
            uthyrning.setInlamningsdatum(inlamingsdatum);
            uthyrningRepository.save(uthyrning);
        }
        return "redirect:/kunder";
    }

    @PostMapping("byId/{personnummer}")
    public String updateKund(Model model, @Valid KundForm k, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Kund kund = kundRepository.findByPersonnummer(k.getPersonnummer());
            kund.setPersonnummer(k.getPersonnummer());
            kund.setAddress(k.getAddress());
            kund.setEpost(k.getEpost());
            kund.setLand(k.getLand());
            kund.setStad(k.getStad());
            kund.setTelefonnummer(k.getTelefonnummer());
            kund.setNamn(k.getNamn());
            kund.setPostnummer(k.getPostnummer());
            kundRepository.save(kund);
            return "redirect:/kunder";
        }
        model.addAttribute("kunder", kundRepository.findAll());
        return "kunder";
    }


}
