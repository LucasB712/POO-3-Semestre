

public class IPI
{
  public void calcularIpi (float valor)
  {
      System.out.println("Produto IPI");
    float valorTotal;
    float imposto;
     System.out.println("O valor do produto é " + valor);
     imposto = valor * 4.6f/100;
    System.out.println("O valor do imposto é " + imposto);
	valorTotal = valor + imposto;
    System.out.println("O valor total é " + valorTotal);
}

}
