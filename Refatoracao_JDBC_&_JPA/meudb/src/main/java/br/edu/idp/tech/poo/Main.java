package br.edu.idp.tech.poo;


import br.edu.idp.tech.poo.ui.impl.CliBonitinha;


public class Main {

    public static void main(String[] args) {
        Programa programa = new Programa(new CliBonitinha());
        programa.executar("JPA");
        programa.executar("JDBC");
    }

}
