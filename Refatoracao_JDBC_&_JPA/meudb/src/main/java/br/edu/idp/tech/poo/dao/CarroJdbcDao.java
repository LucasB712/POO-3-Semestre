package br.edu.idp.tech.poo.dao;


import br.edu.idp.tech.poo.mapper.CarroMapper;
import br.edu.idp.tech.poo.entidade.Carro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarroJdbcDao {

    private Connection conexao;

    private static Connection conectarBD() {
        String dadosConexao = "jdbc:h2:~/nosso_bd2";
        try {
            Connection connection = DriverManager.getConnection(dadosConexao, "sa", "");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CarroJdbcDao() {
        // criação da conexão com BD
        this.conexao = conectarBD();
    }

    public void salvar(Carro carro) {
        String query = "insert into tb_carros (marca, modelo, ano_modelo) values (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAnoModelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar(List<Carro> novosCarros) {
        for (Carro carro : novosCarros) {
            this.salvar(carro);
        }
    }

    public List<Carro> findAll() {
        List<Carro> carrosDoBanco = new ArrayList<>();
        String query = "select * from tb_carros";
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Carro carro = CarroMapper.rsToCarro(rs);
                carrosDoBanco.add(carro);
            }
            rs.close();
            return carrosDoBanco;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void criarTabela() throws SQLException {
        // execução de DDLs - data definition language --- SQL

        Connection conn = conectarBD();

        // criação da tabela no banco de dados (se a tabela não existir)
        String query = "create table if not exists tb_carros (id bigint generated always as identity primary key, marca varchar(20), modelo varchar(20), ano_modelo integer)";
        // obs: a tabela carro NÃO está normalizada
        Statement stmt = conn.createStatement();
        stmt.execute(query);

        // exemplo de alteração da estrutura da tabela
        query = "alter table tb_carros alter marca varchar(30)";
        stmt = conn.createStatement();
        stmt.execute(query);

        conn.close();
    }

}
