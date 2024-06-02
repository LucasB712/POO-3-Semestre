package br.edu.idp.tech.poo;


import br.edu.idp.tech.poo.dao.CarroJdbcDao;
import br.edu.idp.tech.poo.dao.CarroJpaDao;
import br.edu.idp.tech.poo.entidade.Carro;
import br.edu.idp.tech.poo.ui.CliIavel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Programa {

    private String sufixo;
    private CliIavel ui;

    public Programa(CliIavel cli) {
        this.ui = cli;
    }

    public void executar(String tipo) {
        // passo 1 - criando a "marca" da execução

        // marca criada para diferenciar o que é cadastrado em cada execução
        sufixo = " /" + gerarLetraAletoria();
        ui.exibirInformacao("Sufixo da execução: " + sufixo);

        // passo 2 - criação do banco de dados

        // criação da tabela
        prepararBD();

        if (tipo.equals("JDBC")) {
            CarroJdbcDao dao = new CarroJdbcDao();

            // passo 3 - várias operações com JDBC
            // - insere 3 carros
            criarRegistrosPorJdbc(dao);

            List<Carro> carros = dao.findAll();
            ui.exibirInformacao("execução via JDBC - início");
            ui.exibirListaCarros(carros);
            ui.exibirInformacao("execução via JDBC - término");


        } else if (tipo.equals("JPA")) {
            CarroJpaDao dao = new CarroJpaDao();
            // passo 4 - várias operações com JPA

            // versão 2: operações com JPA
            // - insere 4 carros
            criarRegistrosPorJpa(dao);
            List<Carro> carros = dao.findAll();
            ui.exibirInformacao("execução via JPA - início");
            ui.exibirListaCarros(carros);
            ui.exibirInformacao("execução via JPA - término");
        }

    }


    private void criarRegistrosPorJdbc(CarroJdbcDao dao) {
        // gerador aleatório de carros para "economizar" a inclusão de dados pelo usuário
        CarregadorDados carregador = new CarregadorDados(sufixo);

        Carro novoCarro = carregador.gerarCarro();
        // salvar carro criado
        dao.salvar(novoCarro);

        List<Carro> novosCarros = new ArrayList<>();
        novosCarros.addAll(carregador.gerarListaCarros(2));
        // salvar coleção de carros
        dao.salvar(novosCarros);
    }

    private void criarRegistrosPorJpa(CarroJpaDao dao) {
        // gerador aleatório de carros para "economizar" a inclusão de dados pelo usuário
        CarregadorDados carregador = new CarregadorDados(sufixo);

        Carro novoCarro = carregador.gerarCarro();
        // salvar carro criado
        dao.salvar(novoCarro);

        List<Carro> novosCarros = new ArrayList<>();
        novosCarros.addAll(carregador.gerarListaCarros(3));
        // salvar coleção de carros
        dao.salvar(novosCarros);
    }

    private void prepararBD() {
        try {
            CarroJdbcDao.criarTabela();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private char gerarLetraAletoria() {
        final int NUM_LETRA_BASE = 97;
        final int QUANTIDADE_LETRAS = 25;

        int numero = GeradorNumAletorio.gerarInt(QUANTIDADE_LETRAS);

        return (char) (NUM_LETRA_BASE + numero);
    }

}
