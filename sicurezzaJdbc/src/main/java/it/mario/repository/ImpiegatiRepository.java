package it.mario.repository;


import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.service.ImpiegatiRowMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary

public class ImpiegatiRepository implements IntefacciaRepository{


    @Autowired
    JdbcTemplate jdbcTemplateSecondo;

    @Override
    public int salvaSuDb(Utente utente, Ruoli ruoli) {
      return 0;
    }

    @Override

    public int salvaImpSuDb(Impiegati impiegato) {
        String sql = "INSERT INTO impiegato (id,eta,cognome,stipendio) VALUES (?,?,?,?)";
                return jdbcTemplateSecondo.update(sql,
                        impiegato.getId(),impiegato.getEta(),impiegato.getCognome(),impiegato.getStipendio());
    }

    // estraggo tutti gli impiegati
    public  List ricercaTutti (){
        String sql = "select * from impiegato";
        List<Impiegati> impiegatilista = jdbcTemplateSecondo.query(sql, new ImpiegatiRowMap());
        return  impiegatilista;
    }



}
