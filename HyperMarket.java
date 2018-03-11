
public class HyperMarket extends Magazin {

	String tip;
	
	public HyperMarket() {
		super();
		this.tip = null;
	}
	
	public HyperMarket(String tip, String nume) {
		super(nume);
		this.tip = tip;
	}
	
	@Override
	public double calculScutiriTaxe() {
		for (int i=0; i<f.size(); i++) {
			if (f.get(i).getTotalCuTaxe() > 0.1*super.getTotalCuTaxe()) {
				return 1.0;//0.01*super.getTotalCuTaxe();
			}
		}
		return 0;
	}

}
