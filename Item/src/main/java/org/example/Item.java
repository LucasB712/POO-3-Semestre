package org.example;



public class Item {

    public static final double LIMITE_DESCONTO = 70.0;
    private String produto;
    private double quantidade;
    private double precoUnitario;

    public String getProduto() {
        return produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getValorUnitario() {
        return this.precoUnitario * this.quantidade;
    }

    public Item(String produto, double quantidade, double valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = valorUnitario;
    }

    public boolean aplicarDesconto(double desconto) {
        if (desconto > LIMITE_DESCONTO) {
            return false;
        }
        this.precoUnitario = this.precoUnitario * desconto/100;
        return true;
    }

}

