package it.mario.service;

import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.repository.IntefacciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class SecurServiceImpl implements SecurSevice{

    @Autowired
    IntefacciaRepository intefacciaRepository;

    @Override
    @Primary
    public int salvaNewUser(Utente utente, Ruoli ruoli) {
    return this.intefacciaRepository.salvaSuDb(utente,ruoli);

    }
}
