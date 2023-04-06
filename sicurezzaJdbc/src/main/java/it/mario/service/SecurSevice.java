package it.mario.service;

import it.mario.model.Ruoli;
import it.mario.model.Utente;

public interface SecurSevice {

    int salvaNewUser (Utente utente, Ruoli ruoli);
}
