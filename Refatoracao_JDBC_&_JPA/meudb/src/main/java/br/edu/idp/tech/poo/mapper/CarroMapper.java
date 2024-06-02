package br.edu.idp.tech.poo.mapper;


import br.edu.idp.tech.poo.entidade.Carro;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CarroMapper {

    public static Carro rsToCarro(ResultSet rs) throws SQLException {
        // mapeamento da linha do resultado para o objeto carro
        Carro carro = new Carro();
        carro.setId( rs.getLong("id") );
        carro.setMarca( rs.getString("marca") );
        carro.setModelo( rs.getString("modelo") );
        carro.setAnoModelo( rs.getInt("ano_modelo") );
        return carro;
    }

}
