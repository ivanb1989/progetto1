package it.mario.controllers;

import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.service.SecurSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.Encoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppGeneralController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecurSevice securSevice;

    @GetMapping("/benvenuto")
    @ResponseBody
    public String welcome() {
        return "Benvenuto sul sito";
    }

    @GetMapping("/ospiti")
    @ResponseBody
    public String ospiti() {
        return "Per gli ospiti sono previsti sconti";
    }



    @GetMapping("/orario")
    public String orario( Model model) {

            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            model.addAttribute(localTime);
            model.addAttribute( localDate);
            return "mostraorario";
        }

    @GetMapping("/oraedata")
    public ModelAndView orarioeview( ) {
        ModelAndView modelAndView = new ModelAndView("dataeora");

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        modelAndView.addObject("data", localDate);
        modelAndView.addObject("orario", localTime);
        return modelAndView;
    }



    @PostMapping("/inserisci")
    public void creauser (@RequestParam(value = "username")     String username,
                               @RequestParam(value = "password")     String password,
                               @RequestParam(value = "enabled")    int enabled,
                               @RequestParam(value = "ruolo")    String ruolo) {

        String passwordCriptata = passwordEncoder.encode(password); //huhhoioh9999

        Utente utente = new Utente(username, passwordCriptata, enabled);
        Ruoli ruoli = new Ruoli(username, ruolo);

        securSevice.salvaNewUser(utente, ruoli);
    }
}
