package se.vidioten.databas.controllers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.vidioten.databas.FoundMovies;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Kund;
import se.vidioten.databas.entities.Uthyrning;
import se.vidioten.databas.forms.FilmForm;
import se.vidioten.databas.omdb.OmdbService;
import se.vidioten.databas.repositories.FilmRepository;
import se.vidioten.databas.repositories.KundRepository;
import se.vidioten.databas.repositories.UthyrningRepository;

import javax.swing.*;
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

    @GetMapping("")
    public String getFilmer(Model model, FilmForm filmForm) {
        model.addAttribute("filmer", filmRepository.findAll());
        model.addAttribute("data", null);
        return "filmer";
    }

    @GetMapping("byId/{produktnummer}")
    public String getById(@PathVariable Long produktnummer, Model model, FilmForm filmForm) {
        model.addAttribute("film", filmRepository.findByProduktnummer(produktnummer));
        return "film";
    }

    @PostMapping("")
    public String addFilm(Model model, @Valid FilmForm f, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            filmRepository.save(new Film(f.getNamn(), f.getBeskrivning(), f.getUtgivningsdatum(), f.getKategori(), f.getFormat()));
            return "redirect:/filmer";
        }
        System.out.println(bindingResult.getAllErrors());
        model.addAttribute("filmer", filmRepository.findAll());
        return "filmer";
    }

    @PostMapping("/imdbSearch")
    public String searchImdbTitle(@RequestParam String title, Model model, FilmForm filmForm) {
        System.out.println("IMDbSEARCH");
        List<FoundMovies> titleList = new ArrayList<>();
        OmdbService omdbService = new OmdbService();
        String jsonResponse = omdbService.searchMovieByTitle(title, "1bff0c57");
        if (!jsonResponse.isEmpty()) {
            JSONArray r = null;
            try {
                r = omdbService.getMovieArray(jsonResponse);
                for (int i = 0; i < r.length() ; i++) {
                    JSONObject object = (JSONObject) r.get(i);
                    titleList.add(new FoundMovies(object.getString("Title")));
                }
                model.addAttribute("data", titleList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("data", null);
            return "filmer";
        }


        model.addAttribute("filmer", filmRepository.findAll());
        System.out.println(titleList);

        return "filmer";
    }

    @PostMapping("/imdbAdd")
    public String addFilmWithImdb(@RequestParam String title, Model model) {
        OmdbService omdbService = new OmdbService();
        String jsonResponse = omdbService.searchMovieByTitle(title, "1bff0c57");
        if (!jsonResponse.isEmpty()) {
            JSONObject r = null;
            try {
                r = omdbService.getMovieObject(jsonResponse);
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
            model.addAttribute("filmer", filmRepository.findAll());
            model.addAttribute("data",null);
            return "filmer";
        }
        return "redirect:/filmer";
    }

    @PostMapping("byId/delete{produktnummer}")
    public String deleteFilm(@PathVariable Long produktnummer) {
        Film film = filmRepository.findByProduktnummer(produktnummer);
        filmRepository.delete(film);
        return "redirect:/filmer";
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
        return "redirect:/filmer";
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
            return "redirect:/filmer";
        }
        model.addAttribute("filmer", filmRepository.findAll());
        return "filmer";
    }
}
