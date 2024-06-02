package br.edu.idp.tech.poo.ui;


import br.edu.idp.tech.poo.entidade.Carro;

import java.util.List;


public interface CliIavel {

    void exibirListaCarros(List<Carro> carros);

    void exibirInformacao(String mensagem);

}
