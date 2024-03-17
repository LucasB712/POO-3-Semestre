public class ISS {
    public void calcularIss(float valor) {
     System.out.println("Servicos ISS");
     float imposto;
    float valorTotal;
     System.out.println("O valor do servico é " + valor);
    imposto = valor * 4.6f/100;
    System.out.println("O valor do imposto é " + imposto);
    valorTotal = valor + imposto;
    System.out.println("O valor total é " + valorTotal);    }
        
    }
