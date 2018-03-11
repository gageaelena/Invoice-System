
public class MiniMarket extends Magazin{
String tip;

	public MiniMarket() {
		super();
		this.tip = null;
	}
	
	public MiniMarket(String tip, String nume) {
		super(nume);
		this.tip = tip;
	}

	@Override
	public double calculScutiriTaxe() {
		String tara=null;
				
		for(int i = 0; i<f.size(); i++) {
			for(int j = 0; j<(f.get(i)).vect.size(); j++) {
				tara = f.get(i).vect.get(j).getProdus().getTaraOrigine();
				
				if(super.getTotalTaraCuTaxe(tara) >0.5*super.getTotalCuTaxe()) {
					return 10.0;//0.1*super.getTotalCuTaxe();
				}
			}
		}
		
		return 0;
	}

}
