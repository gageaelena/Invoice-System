
public class ProdusComandat {
	
	private Produs produs;
	private double taxa;
	private int cantitate;
	
	public ProdusComandat() {
		produs  = new Produs();
		this.taxa = 0;
		this.cantitate = 0;
	}
	
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	
	public Produs getProdus() {
		return this.produs;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return this.taxa;
	}
	
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	public int getCantitate() {
		return this.cantitate;
	}
	
}
