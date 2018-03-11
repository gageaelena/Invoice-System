
public class MediumMarket extends Magazin{
	String tip;
	
	public MediumMarket() {
		super();
		this.tip = null;
	}
	
	public MediumMarket(String tip, String nume) {
		super(nume);
		this.tip = tip;
	}
	
	
	@Override
	public double calculScutiriTaxe() {
		String categ=null;
		Produs p = new Produs();
		for(int i = 0; i<f.size(); i++) {
			for(int j = 0; j<f.get(i).vect.size(); j++) {
				
				p = f.get(i).vect.get(j).getProdus();
				categ = p.getCategorie();
				if(super.TotalCategorieCuTaxe(categ) > 0.5 * super.getTotalCuTaxe()) {
					return 5;//0.05*super.getTotalCuTaxe();
				}
				
			}
		}
		
		
		return 0;
	}
	
	
	
}
