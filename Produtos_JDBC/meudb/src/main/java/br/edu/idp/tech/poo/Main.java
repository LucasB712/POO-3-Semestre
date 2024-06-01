package br.edu.idp.tech.poo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conexao = conectarBD();
        criarTabela(conexao);
        prepararBD(conexao);


        int quant = contarProdutos(conexao);
        ListarProdutos(conexao);
        imprimirQtdProdutos(quant);

        procurarProduto(conexao, "Computador5");
        // TODO imprimir a lista de carros cadastrados
    }

    private static Connection conectarBD() {
        String dadosConexao = "jdbc:h2:~/Cadastro_pessoas";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dadosConexao, "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static void prepararBD(Connection conexao) {
        try {
            criarTabela(conexao);
            inserirProdutos(conexao, 10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void criarTabela(Connection conexao) throws SQLException {
        String query = "create table if not exists tb_produtos (tipo varchar(50) primary key, descricao varchar(20), peso float, quantidade bigint, unidade varchar(10))";
        Statement stmt = conexao.createStatement();
        stmt.execute(query);
    }

    private static void inserirProdutos(Connection conexao, int quant) throws SQLException {
        String query;
        PreparedStatement stmt;
        query = "insert into tb_produtos (tipo, descricao, peso, quantidade, unidade) values (?, ?, ?, ?, ?)";
        stmt = conexao.prepareStatement(query);

        for (int i = 0; i <= quant; i++) {
            String tipo = "Computador" + i;
            if (!verificarProdutoExistente(conexao, tipo)) {
                stmt.setString(1, tipo);
                stmt.setString(2, "informática" + i);
                stmt.setFloat(3, 100 + i);
                stmt.setInt(4, 12); // Definindo uma quantidade fixa para todos os produtos
                stmt.setString(5, "kg " + i);
                stmt.executeUpdate();
            }
        }
    }

    private static int contarProdutos(Connection conexao) {
        String query = "select count(*), 1 from tb_produtos";
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
            rs.first();
            System.out.println(rs);
            int resultado = 0;
            resultado = rs.getInt(1);
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void procurarProduto(Connection conexao, String pesquisa) throws SQLException {
        String query = "SELECT * FROM tb_produtos WHERE tipo = ?" + pesquisa;


        try {
            Statement stmt = conexao.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

                // Se o produto for encontrado, criar um objeto Produto
            System.out.printf("Tipo: %10s, Descricao: %10s, Peso: %.5f, Quantidade: %10d , Unidade: %10s",
                    resultSet.getString("tipo"),
                    resultSet.getString("descricao"),
                    resultSet.getFloat("peso"),
                    resultSet.getInt("quantidade"),
                    resultSet.getString("unidade"));
                    System.out.println("\n");
                } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void imprimirQtdProdutos(int quantidade) {
        System.out.println("Existem " + quantidade + " produtos cadastrados");
    }

    private static void ListarProdutos(Connection conexao) {
        String query = "select * from tb_produtos";

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                System.out.printf("Tipo: %10s, Descricao: %10s, Peso: %.5f, Quantidade: %10d , Unidade: %10s",
                        rs.getString("tipo"),
                        rs.getString("descricao"),
                        rs.getFloat("peso"),
                        rs.getInt("quantidade"),
                        rs.getString("unidade"));
                        System.out.println("\n");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private static boolean verificarProdutoExistente(Connection conexao, String tipo) throws SQLException {
        String query = "select count(*) from tb_produtos where tipo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
}
