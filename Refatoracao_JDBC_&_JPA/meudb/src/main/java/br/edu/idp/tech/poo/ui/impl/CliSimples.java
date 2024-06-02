package br.edu.idp.tech.poo.ui.impl;


import br.edu.idp.tech.poo.entidade.Carro;
import br.edu.idp.tech.poo.ui.CliIavel;

import java.util.List;


public class CliSimples implements CliIavel {

    private void imprimirQtdCarros(int quantidade) {
        System.out.println("Existem " + quantidade + " registros cadastrados");
    }

    @Override
    public void exibirListaCarros(List<Carro> carros) {
        System.out.println("Carros cadastrados");
        System.out.println("id | marca | modelo | ano modelo");
        for (Carro carro : carros) {
            System.out.println(carro.getId() + " |" +
                    "\t" + carro.getMarca() + " |" +
                    "\t" + carro.getModelo() + " |" +
                    "\t" + carro.getAnoModelo() );
        }
        this.imprimirQtdCarros(carros.size());
    }

    @Override
    public void exibirInformacao(String mensagem) {
        System.out.println(mensagem);
    }

}
