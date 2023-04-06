package it.mario.service;

import it.mario.model.Impiegati;
import it.mario.repository.ImpiegatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpiegatiSevice {
    @Autowired
    ImpiegatiRepository impiegatiRepository;

    public List<Impiegati> estraiTuttiImpiegati (){
        return impiegatiRepository.ricercaTutti();
    }

    //Giro:    controller --(richiama)--> service --- (Richiama)---> Repository

}
