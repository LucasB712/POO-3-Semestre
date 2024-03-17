public class ISS {
   
   
    public void calcularIss(float valor) {
     float imposto;
    float valorTotal;
     System.out.println(valor);
    imposto = valor * 4.6f/100;
    System.out.println(imposto);
    valorTotal = valor + imposto;
    System.out.println(valorTotal);
    }
        
    }