public class ICMS
{
  public void calcularIcms (float valor)
  {
      System.out.println("Produtos e Servicos ICMS");
    float valorTotal;
    float imposto;
     System.out.println("O valor do produto/servico é " + valor);
     imposto = valor * 4.6f/100;
    System.out.println("O valor do imposto é " + imposto);
	valorTotal = valor + imposto;
    System.out.println("O valor total é " + valorTotal);
  }

}

