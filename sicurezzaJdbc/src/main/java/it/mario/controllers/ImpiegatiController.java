package it.mario.controllers;

import it.mario.model.Impiegati;
import it.mario.service.ImpiegatiSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ImpiegatiController {
    @Autowired
    ImpiegatiSevice impiegatiSevice;

    // gestione endpoint per il recupero di tutti gli impiegati
    @GetMapping("/estraidati")
   // @ResponseBody // E' necessaria l'annotazione se marchoamo la classe con @Controller
     public List impiegati (){

        List<Impiegati> impiegatiList = new ArrayList<>();
       impiegatiList = impiegatiSevice.estraiTuttiImpiegati();// questo si richama il controller con il metodo di ricerca tutti
       return impiegatiList; //-->flusso di byte


        }
// contrelle per la visualizzazione silla pagina html di mostadipendenti.html
    @GetMapping("/estraidipendenti")
    public ModelAndView orarioeview( ) {
        ModelAndView modelAndView = new ModelAndView("mostadipendenti");
        List<Impiegati> impiegatiList = new ArrayList<>();
        impiegatiList = impiegatiSevice.estraiTuttiImpiegati();// questo si richama il controller con il metodo di ricerca tutti

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        modelAndView.addObject("data", localDate);
        modelAndView.addObject("orario", localTime);
        modelAndView.addObject(impiegatiList);
        return modelAndView;
    }
}
