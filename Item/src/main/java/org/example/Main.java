package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class Main {
    private Venda venda1;
    private Venda venda2;
    private Venda venda3;
    private Venda venda4;
    private Venda venda5;
    private Venda venda6;
    boolean x;


    @BeforeEach
    //Setup para antes de tudo
    public void setUp() {
        venda1 = new Venda("Cliente Teste");
        venda2 = new Venda("Cliente Teste2");
        venda3 = new Venda("Cliente Teste3");
        venda4 = new Venda("Cliente Teste4");
        venda5 = new Venda("Cliente Teste5");
        venda6 = new Venda("Cliente Teste6");
    }


    //Verificar se funciona um teste onde não se adiciona item;
    @Test
    public void testeSemNada() {

        Assertions.assertEquals("Cliente Teste", venda1.getCliente());
        Assertions.assertEquals(Venda.SITUACAO_NAO_INICIADA, venda1.getSituacao());
        Assertions.assertEquals(0.0, venda1.getValor());
        System.out.println(venda1.getValor());
    }


    //Conferir se funciona a adicao de item e valor total batem;

    @Test
    public void testAdicaoDeItemEValorTotal() {
        venda2.vender("Produto A", 50.0, 2);
        Assertions.assertEquals(100.0, venda2.getValor());
        System.out.println(venda2.getValor());
    }


    // Verificar se o valor total foi ajustado corretamente após o desconto

    @Test
    public void testAplicarDescontoValido() {
        venda3.vender("Produto A", 50.0, 2);
        boolean descontoAplicado = venda3.aplicarDesconto(10.0);
        Assertions.assertTrue(descontoAplicado);
        Assertions.assertEquals(90.0, venda3.getValor());
    }


    // Verificar se o valor total permanece o mesmo

    @Test
    public void testAplicarDescontoInvalido() {
        venda4.vender("Produto A", 50.0, 2);
        boolean descontoAplicado = venda4.aplicarDesconto(-10.0);
        Assertions.assertFalse(descontoAplicado);

        Assertions.assertEquals(100.0, venda4.getValor());
        System.out.println(venda4.getValor());
    }


    // Tentativa de adicionar outro item após finalização deve lançar exceção
    @Test
    public void testFinalizarVenda() {
        venda5.finalizar();

        Assertions.assertThrows(RuntimeException.class, () -> venda5.vender("Produto B", 30.0, 1));
    }


    // Tentativa de finalizar uma venda não iniciada deve lançar exceção
    @Test
    public void testFinalizarVendaNaoIniciada() {
        venda6.finalizar();
        Assertions.assertThrows(RuntimeException.class, () -> venda6.finalizar());
    }
}



