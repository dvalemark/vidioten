package se.vidioten.databas.controllers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.vidioten.databas.FoundMovie;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Kund;
import se.vidioten.databas.entities.Uthyrning;
import se.vidioten.databas.forms.FilmForm;
import se.vidioten.databas.omdb.OmdbService;
import se.vidioten.databas.repositories.FilmRepository;
import se.vidioten.databas.repositories.KundRepository;
import se.vidioten.databas.repositories.UthyrningRepository;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/filmer")
public class FilmController {

    @Autowired
    private KundRepository kundRepository;
    @Autowired
    private UthyrningRepository uthyrningRepository;
    @Autowired
    private FilmRepository filmRepository;
    
    @GetMapping("/{alternativ}")

    public String getFilmer(Model model, FilmForm filmForm, @PathVariable(required = false) String alternativ, @RequestParam(required = false) Integer page) {

        if (alternativ != null) {
            if (alternativ.equals("Alla")) {
                model.addAttribute("filmer", filmRepository.findAll(PageRequest.of(page != null ? page : 0, 10)));
            } else {
                model.addAttribute("filmer", filmRepository.findAllByKategori(alternativ, PageRequest.of(page != null ? page : 0, 10) ));
            }
        }else{
            model.addAttribute("filmer", filmRepository.findAll(PageRequest.of(page != null ? page : 0, 10)));
        }
        model.addAttribute("data", null);
        return "filmer";
    }


    @GetMapping("byId/{produktnummer}")
    public String getById(@PathVariable Long produktnummer, Model model, FilmForm filmForm) {
        model.addAttribute("film", filmRepository.findByProduktnummer(produktnummer));
        return "film";
    }

    @PostMapping("addMovie")
    public String addFilm(Model model, @Valid FilmForm f, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            filmRepository.save(new Film(f.getNamn(), f.getBeskrivning(), f.getUtgivningsdatum(), f.getKategori(), f.getFormat()));
            return "redirect:/filmer/Alla";
        }
        return "redirect:/filmer/Alla";
    }

    @PostMapping("/imdbSearch")
    public String searchImdbTitle(@RequestParam String title, Model model, FilmForm filmForm) {
        System.out.println("IMDbSEARCH");
        List<FoundMovie> titleList = new ArrayList<>();
        OmdbService omdbService = new OmdbService();
        String jsonResponse = omdbService.searchMovieByTitle(title, "1bff0c57");
        if (!jsonResponse.isEmpty()) {
            JSONArray r = null;
            try {
                r = omdbService.getMovieArray(jsonResponse);
                for (int i = 0; i < r.length(); i++) {
                    JSONObject object = (JSONObject) r.get(i);
                    titleList.add(new FoundMovie(object.getString("Title"), object.getString("imdbID")));
                }
                model.addAttribute("data", titleList);
                model.addAttribute("filmer", filmRepository.findAll(PageRequest.of(0, 10)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("data", null);
            return "redirect:/filmer/Alla";
        }

        return "filmer";
    }

    @PostMapping("/imdbAdd")
    public String addFilmWithImdb(@RequestParam String imdbId, Model model, FilmForm filmForm) {
        System.out.println(imdbId);
        OmdbService omdbService = new OmdbService();
        String jsonResponse = omdbService.searchMovieByImdb(imdbId, "1bff0c57");
        if (!jsonResponse.isEmpty()) {
            JSONObject r = null;
            try {
                r = new JSONObject(jsonResponse);
                System.out.println(r.getString("Title"));
                Film film = new Film(r.getString("Title"), r.getString("Plot"), r.getString("Released"),
                        r.getString("Genre").split(",")[0], "DVD");
                film.setImdb(r.getString("imdbRating"));
                film.setBild(r.getString("Poster"));
                filmRepository.save(film);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            return "redirect:/filmer/Alla";
        }
        return "redirect:/filmer/Alla";
    }

    @PostMapping("byId/delete{produktnummer}")
    public String deleteFilm(@PathVariable Long produktnummer, Model model) {
        Film film = filmRepository.findByProduktnummer(produktnummer);
        filmRepository.delete(film);
        return "redirect:/filmer/Alla";
    }

    @PostMapping("/rent/{produktnummer}")
    public String hyrFilm(@RequestParam String personnummer, @PathVariable Long produktnummer) {
        Kund kund = kundRepository.findByPersonnummer(personnummer);
        if (kund != null) {
            Film film = filmRepository.findByProduktnummer(produktnummer);
            Date uthyrningsdatum = Date.valueOf(LocalDate.now());
            Date senasteInlamning = Date.valueOf(LocalDate.now().plusDays(1));
            uthyrningRepository.save(new Uthyrning(kund, film, uthyrningsdatum, senasteInlamning));
            film.setKund(kund);
            filmRepository.save(film);
        } else {
            System.out.println("KUND FINNS INTE");
        }
        return "redirect:/filmer/Alla";
    }

    @PostMapping("byId/{produktnummer}")
    public String updateFilm(Model model, @Valid FilmForm f, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Film film = filmRepository.findByProduktnummer(f.getProduktnummer());
            film.setProduktnummer(f.getProduktnummer());
            film.setNamn(f.getNamn());
            film.setBeskrivning(f.getBeskrivning());
            film.setFormat(f.getFormat());
            film.setKategori(f.getKategori());
            film.setUtgivningsdatum(f.getUtgivningsdatum());
            film.setKund(f.getKund());
            filmRepository.save(film);
            return "redirect:/filmer/Alla";
        }
        model.addAttribute("filmer", filmRepository.findAll());
        return "redirect:/filmer/Alla";
    }
}
