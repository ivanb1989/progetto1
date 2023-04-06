package it.mario.repository;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SicurezzaRepoImpl implements IntefacciaRepository {
    @Autowired

    JdbcTemplate jdbcTemplate;
    @Override
    public int salvaSuDb(Utente utente, Ruoli ruoli) {
        try {

            jdbcTemplate.update("insert ignore into utenti (username,password,enabled) values (?,?,?)",
                    utente.getUsername(),"{bcrypt}"+utente.getPassword(), utente.getEnabled());

            jdbcTemplate.update("insert ignore into ruoli (user ,ruolo) values (?,?)",
                    ruoli.getUser(),ruoli.getRuolo());

        }
        catch (Exception e){
            System.out.println(e);
       return 0;
        }


        return 1;
    }


    @Override
    public int salvaImpSuDb(Impiegati impiegato) {
        return 0;
    }
}
