package it.mario.repository;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;

public interface IntefacciaRepository {

    int salvaSuDb (Utente utente, Ruoli ruoli);

    int salvaImpSuDb (Impiegati impiegato);
}
