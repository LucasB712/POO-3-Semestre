public class IPI
{
  public void calcularIpi (float valor)
  {
    float valorTotal;
    float imposto;
     System.out.println(valor);
     imposto = valor * 4.6f/100;
    System.out.println(imposto);
	valorTotal = valor + imposto;
	System.out.println(valorTotal);
  }

}
