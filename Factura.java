import java.util.Vector;

public class Factura {

	String denumire;
	Vector<ProdusComandat> vect = new Vector<ProdusComandat>();
	
	
	
	public double getTotalFaraTaxe() {
		double total = 0;
		Produs p;
		
		for(int i = 0; i<vect.size(); i++) {
			p = vect.get(i).getProdus();
			total = total + p.getPret()*vect.get(i).getCantitate();
		}
		
		return total;
		
	}
	
	public double getTotalCuTaxe() {
		double totall = 0;
		Produs pp;
		double pret = 0;//trebuie refacut zero in for?
		
		for(int i = 0; i<vect.size(); i++) {
			pp = vect.get(i).getProdus();
			
			pret = pp.getPret() + pp.getPret()*(vect.get(i).getTaxa()/100);
			
			totall = totall + pret *vect.get(i).getCantitate();
		}
		return totall;
		
	}
	
	public double getTaxe() {
		double totalTaxa = 0;
		
		for(int i = 0; i<vect.size(); i++) {
			totalTaxa += vect.get(i).getTaxa();
		}
		
		return totalTaxa;
		
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		
		Produs prod;
		double totalTara = 0;
		
		for(int i = 0; i<vect.size(); i++) {
			prod = vect.get(i).getProdus();
			
			if(prod.getTaraOrigine().equals(tara)) {
				totalTara = totalTara + prod.getPret()*vect.get(i).getCantitate();
			}
		}
		
		return totalTara;
		
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		
		Produs p;
		double totalTaraTax = 0;
		double pret = 0;//trebuie refacut zero in for?
		
		for(int i = 0; i<vect.size(); i++) {
			p = vect.get(i).getProdus();
			
			if(p.getTaraOrigine().equals(tara)) {
			
				p = vect.get(i).getProdus();
				
				pret = p.getPret() + p.getPret()*(vect.get(i).getTaxa()/100);
				
				totalTaraTax = totalTaraTax + pret *vect.get(i).getCantitate();
				
			}
		}
		return totalTaraTax;
	}
	
	public double getTaxeTara(String tara) {
		
		Produs p;
		double totalTaxaTara = 0;
		
		for(int i = 0; i<vect.size(); i++) {
			p = vect.get(i).getProdus();
			
			if(p.getTaraOrigine().equals(tara)) {
			
				totalTaxaTara += vect.get(i).getTaxa();
				
			}
			
		}
		
		return totalTaxaTara;
	}
	
}
