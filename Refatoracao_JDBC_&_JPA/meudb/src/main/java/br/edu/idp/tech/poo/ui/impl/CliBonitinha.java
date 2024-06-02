package br.edu.idp.tech.poo.ui.impl;


import br.edu.idp.tech.poo.entidade.Carro;
import br.edu.idp.tech.poo.ui.CliIavel;

import java.util.List;


public class CliBonitinha implements CliIavel {

    private void imprimirQtdCarros(int quantidade) {
        exibirInformacao("Existem " + quantidade + " registros cadastrados");
    }

    @Override
    public void exibirListaCarros(List<Carro> carros) {
        System.out.println("-> Carros cadastrados: ---------------");
        System.out.println("id     | marca          | modelo               |  ano modelo");
        System.out.println("------ | -------------- | -------------------- | -----------");
        for (Carro carro : carros) {
            System.out.println(String.format("%6s", carro.getId()) + " |" +
                    "\t" + String.format("%11s", carro.getMarca()) + " |" +
                    "\t" + String.format("%18s", carro.getModelo()) + " |" +
                    "\t" + String.format("%9s", carro.getAnoModelo()) );
        }
        this.imprimirQtdCarros(carros.size());
    }

    @Override
    public void exibirInformacao(String mensagem) {
        System.out.println(" -> " + mensagem);
    }

}
