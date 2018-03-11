import java.util.Vector;

public abstract class Magazin implements IMagazin{

	String nume;
	Vector<Factura> f = new Vector<Factura>();

	public Magazin(){
		this.nume = null;
		
	}
	
	public Magazin(String nume) {
		this.nume = nume;
		
	}
	
	public double getTotalFaraTaxe() {
		double total = 0;
		
		for(int i = 0; i<f.size(); i++) {
			total = total +f.get(i).getTotalFaraTaxe();
		}
		return total;
	}
	
	public double getTotalCuTaxe() {
		double total = 0;
		
		for(int i = 0; i<f.size(); i++) {
			total = total + f.get(i).getTotalCuTaxe();
		}
		
		return total;
	}
	
	public double getTotalCuTaxeScutite() {
		double total = 0;
		
		total = getTotalCuTaxe()-getTotalCuTaxe()*calculScutiriTaxe()/100;
		
		return total;
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		double total = 0;
		

		for(int i = 0; i<f.size(); i++) {
			total = total + f.get(i).getTotalTaraFaraTaxe(tara);
		}
		
		return total;
		
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		double total = 0;
		

		for(int i = 0; i<f.size(); i++) {
			total = total + f.get(i).getTotalTaraCuTaxe(tara);
		}
		
		return total;
		
	}
	
	public double getTotalTaraCuTaxeScutite(String tara) {
		double total = 0;
		
		total = getTotalTaraCuTaxe(tara)-getTotalTaraCuTaxe(tara)*calculScutiriTaxe()/100;
		
		return total;
		
		
	}
	public double TotalCategorieCuTaxe(String categorie) {
		double total = 0;
		double taxa = 0;
		String categ = null;
		Produs p = new Produs();
		ProdusComandat pc = new ProdusComandat();
		for(int i = 0; i<this.f.size(); i++) {
			for(int j = 0; j<f.get(i).vect.size(); j++) {
				
				p = f.get(i).vect.get(j).getProdus();
				pc = f.get(i).vect.get(j);
				categ = p.getCategorie();
				
				if(categ.equals(categorie)) {
					
					taxa = p.getPret()+p.getPret()*(pc.getTaxa()/100);
					total = total + p.getPret()*pc.getCantitate()*taxa;
				}
				
			}
		}
		
		return total;
	}
	
	public abstract double calculScutiriTaxe();
	
}
