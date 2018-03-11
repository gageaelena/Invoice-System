
public class MagazinFactory {

	public Magazin getMagazin(String tipMagazin, String numeMagazin) {
		if(tipMagazin == null) {
			return null;
		}
		
		if (tipMagazin.equalsIgnoreCase("MiniMarket")) {
			return new MiniMarket(tipMagazin,numeMagazin);
		}
		
		if (tipMagazin.equalsIgnoreCase("HyperMarket")) {
			return new HyperMarket(tipMagazin,numeMagazin);
		}
		
		if (tipMagazin.equalsIgnoreCase("MediumMarket")) {
			return new MediumMarket(tipMagazin,numeMagazin);
		}
		
		return null;
	}
	
}
