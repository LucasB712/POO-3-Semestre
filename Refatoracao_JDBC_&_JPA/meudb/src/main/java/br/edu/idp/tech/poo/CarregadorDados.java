package br.edu.idp.tech.poo;


import br.edu.idp.tech.poo.entidade.Carro;

import java.util.ArrayList;
import java.util.List;


public class CarregadorDados {

    private String sufixo;

    public CarregadorDados(String sufixo) {
        this.sufixo = sufixo;
    }

    public List<Carro> gerarListaCarros(int quantidade) {
        List<Carro> novosCarros = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            novosCarros.add(this.gerarCarro());
        }
        return novosCarros;
    }

    public Carro gerarCarro() {
        int modificadorModelo = GeradorNumAletorio.gerarInt(9);
        int posicao = GeradorNumAletorio.gerarInt(7);
        Carro carro = carregarBase().get(posicao);
        carro.setModelo(carro.getModelo() + "." + modificadorModelo + sufixo);
        carro.setAnoModelo(carro.getAnoModelo() - modificadorModelo);
        return carro;
    }

    private static List<Carro> carregarBase() {
        // geração da "base de dados" que será usada como base para a criação dos carros

        Carro c0 = new Carro();
        c0.setMarca("BYD");
        c0.setModelo("Dolphin");
        c0.setAnoModelo(2024);

        Carro c1 = new Carro();
        c1.setMarca("BYD");
        c1.setModelo("Mini-Dolphin");
        c1.setAnoModelo(2024);

        Carro c2 = new Carro();
        c2.setMarca("Volkswagen");
        c2.setModelo("Polo");
        c2.setAnoModelo(2019);

        Carro c3 = new Carro();
        c3.setMarca("Mercedes");
        c3.setModelo("AMG One");
        c3.setAnoModelo(2024);

        Carro c4 = new Carro();
        c4.setMarca("Porshe");
        c4.setModelo("911 GT3 RS");
        c4.setAnoModelo(2020);

        Carro c5 = new Carro();
        c5.setMarca("Volkswagen");
        c5.setModelo("Up Turbo");
        c5.setAnoModelo(2022);

        Carro c6 = new Carro();
        c6.setMarca("Mazda");
        c6.setModelo("MX-5");
        c6.setAnoModelo(1997);

        return new ArrayList<>(List.of(new Carro[]{
                c0, c1, c2, c3, c4, c5, c6
        }));
    }

}
