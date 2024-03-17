public class Main
{
	public static void main(String[] args) {
	 float produto1 = 15;
     float servico1 = 20;
     float produtoServico1 = 25;
    
    ISS isst = new ISS();
    isst.calcularIss(produto1);
    
    
    ICMS icms = new ICMS();
    icms.calcularIcms(servico1);
    
    IPI ipi = new IPI();
    ipi.calcularIpi(produtoServico1);
    
	    
	
	}
}
