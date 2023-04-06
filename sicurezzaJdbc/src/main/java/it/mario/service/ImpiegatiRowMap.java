package it.mario.service;


import it.mario.model.Impiegati;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpiegatiRowMap implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Impiegati impiegati = new Impiegati();
        impiegati.setId(rs.getInt(1));
        impiegati.setCognome(rs.getString(2));
        impiegati.setEta(rs.getInt(3));
        impiegati.setStipendio(rs.getInt(4));
        return impiegati;
    }
}

