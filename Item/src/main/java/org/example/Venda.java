package org.example;

import java.util.ArrayList;
import java.util.List;


public class Venda {

    public static final String SITUACAO_ENCERRADA = "ENCERRADA";
    public static final String SITUACAO_NAO_INICIADA = "NÃO INICIADA";
    public static final String SITUACAO_EM_ANDAMENTO = "EM ANDAMENTO";
    private List<Item> itens;
    private String cliente;
    private String situacao;

    public Venda(String cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.situacao = SITUACAO_NAO_INICIADA;
    }

    public String getCliente() {
        return cliente;
    }

    public String getSituacao() {
        return situacao;
    }

    public double getValor() {
        double valorTotal = 0.0;
        for (Item item : this.itens) {
            valorTotal += item.getValorUnitario();
        }
        return valorTotal;
    }

    public boolean aplicarDesconto(double percentual) {
        if (percentual < 0) {
            return false;
        }
        if (!this.getSituacao().equals(SITUACAO_EM_ANDAMENTO)) {
            return false;
        }
        for (Item item : this.itens) {
            if (!item.aplicarDesconto(percentual)) {
                return false;
            }
        }
        return true;
    }

    public void vender(String produto, double valor, double quantidade) {
        if (this.getSituacao().equals(SITUACAO_ENCERRADA)) {
            throw new RuntimeException("venda encerrada");
        }
        this.situacao = SITUACAO_EM_ANDAMENTO;
        Item novoItem = new Item(produto, valor, quantidade);
        this.itens.add(novoItem);
    }

    public void finalizar() {
        if (this.getSituacao().equals(SITUACAO_NAO_INICIADA)) {
            throw new RuntimeException("venda não iniciada");
        }
        this.situacao = SITUACAO_ENCERRADA;
    }

}
