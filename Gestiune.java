import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;

public class Gestiune {
	
	//aici am implementat singleton pattern
	private static Gestiune firstInstance = null;
	
	
	private Gestiune() {
		
	}
	
	public static Gestiune getInstance() {
		
		if(firstInstance == null) {
			firstInstance = new Gestiune();
		}
		
		return firstInstance;
	}
	
	
	ArrayList<Produs> produse = new ArrayList<Produs>();
	ArrayList<Magazin> magazine = new ArrayList<Magazin>();
	HashMap<String, HashMap<String,Double>> taxe = new HashMap<String, HashMap<String,Double>>();
	
	public double cautTaxa(String tara, String categorie) {
		return taxe.get(tara).get(categorie);
	}
	
	public Produs cautProdus(String denumire, String tara) {
		
		for(int i=0; i<produse.size(); i++) {
			 if (produse.get(i).getDenumire().equals(denumire) && produse.get(i).getTaraOrigine().equals(tara)) {
					return produse.get(i);
			 } 
		}
		
		return null;
	}
	
	
	public void citireTaxe() {

		ArrayList<HashMap<String, Double>> prodTaxa = new ArrayList<HashMap<String,Double>>();
		
		Vector<String> tarile = new Vector<String>();
		
		File fTaxe = new File("/home/miruna/anul2sem1/poo/tema/AnexeTema/taxe.txt");
		
		{	
			try {
				
				RandomAccessFile fisier = new RandomAccessFile(fTaxe, "r");
				
				String linie = fisier.readLine();
				
				StringTokenizer token = new StringTokenizer(linie);
				token.nextToken();//sar peste primul cuvant
				
				while (token.hasMoreTokens()) {
					tarile.add(token.nextToken());
					prodTaxa.add(new HashMap<String,Double>());
				}
				
				while((linie = fisier.readLine()) != null) {
					int tax = 0;
					
					token = new StringTokenizer(linie);
					
					String tip = token.nextToken();
					
					while(token.hasMoreTokens()) {
						Double procent = Double.parseDouble(token.nextToken());
						
						HashMap<String, Double> h = prodTaxa.get(tax);//
						
						h.put(tip, procent);
						
						tax = tax +1;
						
					}
				}
				//aici am populat hashmap-ul taxe
				for(int i = 0; i<tarile.size(); i++) {
					taxe.put(tarile.get(i), prodTaxa.get(i));
				}
				
				fisier.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/*
		Iterator<Entry<String, HashMap<String, Double>>> it = taxe.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, HashMap<String, Double>> pair = it.next();
			
			System.out.println(pair.getKey() + pair.getValue());
		}
		*/
	}

	public void citireProduse() {
		
		Vector<String> tari = new Vector<String>();
		
		File fProduse = new File("/home/miruna/anul2sem1/poo/tema/AnexeTema/produse.txt");
		
		{
			try {
				
				RandomAccessFile f = new RandomAccessFile(fProduse, "r");
				
				String lin = f.readLine();
				
				StringTokenizer tok = new StringTokenizer(lin);
				tok.nextToken();
				tok.nextToken();//sar peste primele doua cuvinte.
				
				//aici citesc tarile
				while(tok.hasMoreTokens()) {
					String tara = tok.nextToken();
					
					tari.add(tara);
				}
				
				while((lin = f.readLine())!=null) {
					
					int k = 0;
					StringTokenizer tk = new StringTokenizer(lin);
					String prod = tk.nextToken();//citesc numele produsului
					String tip = tk.nextToken();//citesc tipul produsului
					
					//aici creeaz produsul si il adaug in array ul de produse
					while (tk.hasMoreTokens()) {
						Produs p = new Produs();
						p.setDenumire(prod);
						p.setCategorie(tip);
						
						Double pret = Double.parseDouble(tk.nextToken());
						p.setPret(pret);
						
						String tara = tari.get(k);
						p.setTaraOrigine(tara);
						
						k++;
						
						produse.add(p);
						
					}
					
					
				}
				
				f.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void citireFacturi() {
		
	File fFacturi = new File("/home/miruna/anul2sem1/poo/tema/AnexeTema/facturi.txt");
	MagazinFactory magazinF = new MagazinFactory();

	try {
			
		RandomAccessFile fi = new RandomAccessFile(fFacturi, "r");		
		String linie;			
			
		while ((linie = fi.readLine()) != null) {
					
			StringTokenizer tok = new StringTokenizer(linie, ":");
			//citesc tipul magazinului si numele		
			if (tok.nextToken().equals("Magazin")) {
						
				String tip = tok.nextToken(":");
				String nume = tok.nextToken(":");
				
				magazine.add(magazinF.getMagazin(tip,nume));
				linie = fi.readLine();//sar peste spatiu
					
			}
					
				String cuvant = linie;
			//aici citesc facturile		
			if(cuvant.startsWith("Factura")) {
				Factura fact = new Factura();
						
				fact.denumire = cuvant;
						
				linie = fi.readLine();//sar peste linia cu cuv orientative
				linie = fi.readLine();
						
				while(!linie.isEmpty()) {

					String d = null, t = null, c=null;
					
					//crez un produs comandat si un produs
					//ca sa ma ajute sa generez vectorul de facturi din magazin
					
					ProdusComandat pc = new ProdusComandat();
					Produs pp = new Produs();
							
							
					StringTokenizer token = new StringTokenizer(linie);
						
					if(token.hasMoreTokens()) {
						d = token.nextToken();
								//d = denumirea
					}
							
					if(token.hasMoreTokens()) {
						t = token.nextToken();
								//t = tara de provenienta
					}
							
					if(token.hasMoreTokens()) {
						c = token.nextToken();
								//c = cantitatea
					}
							
					pp = cautProdus(d, t); //caut produsul cu denumirea si tara respectiva 
							
					pc.setProdus(pp);//am pus in produs comandat produsul
							
							
					Integer cantitate = Integer.parseInt(c);
					pc.setCantitate(cantitate);//am pus in produs comandat cantitatea
					
					//caut taxa produsului
					Double taxaaa = cautTaxa(t, pp.getCategorie());
							
					pc.setTaxa(taxaaa);//am pus taxa in produs comandat
					
					//adaug produsul in vectorul de facturi
					fact.vect.add(pc);
						
					if ((linie = fi.readLine())!=null){}else break;
							
							
				}
				//adaug in vectorul de magazine vectorul de facturi
				magazine.get(magazine.size()-1).f.add(fact);		
			}	
		}
		
		fi.close();
			
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
			e.printStackTrace();
	}
		
}

	//genereaza cun vector cu toate tarile prezente intr un magazin
	public void getTariMarket(Magazin magazin, Vector<String> tari) {
				
		for(int i = 0; i<magazin.f.size(); i++) {
			for(int j = 0; j<magazin.f.get(i).vect.size(); j++) {
				String taracrt;
				taracrt = magazin.f.get(i).vect.get(j).getProdus().getTaraOrigine();
				
				if(!tari.contains(taracrt)) {
					tari.add(taracrt);
				}
				
			}
		}
		
	}
	//sorteaza vectorul de facturi dintr un magazin
	public void SortareFacturi(Magazin magazin, Vector<Factura> fact) {
		Comparator<Factura> c = new Comparator<Factura>() {

			@Override
			public int compare(Factura o1, Factura o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getTotalCuTaxe()-o2.getTotalCuTaxe());
			}
		};
		
		for(int i=0; i<magazin.f.size(); i++) {
			fact.add(magazin.f.get(i));
		}
		
		Collections.sort(fact,c);
	
	}
	//genereaza un vector care contine tarile prezente intr o factura
	public void getTariFactura(Factura f, Vector<String> tari) {
		for(int i=0; i<f.vect.size();i++) {
			if(!tari.contains(f.vect.get(i).getProdus().getTaraOrigine())) {
				tari.add(f.vect.get(i).getProdus().getTaraOrigine());
			}
			
		}
	}
	
	public void task1() {
		
		Vector<Magazin> Mini = new Vector<Magazin>();
		Vector<Magazin> Medium = new Vector<Magazin>();
		Vector<Magazin> Hyper = new Vector<Magazin>();
		Vector<String> ToateTarile = new Vector<String>();		
	
		File file = new File("out.txt");
		try {
			file.createNewFile();
			PrintWriter out = new PrintWriter(file);
			
			
			Iterator<Entry<String, HashMap<String, Double>>> it = taxe.entrySet().iterator();
			
			while (it.hasNext()) {
				Entry<String, HashMap<String, Double>> pair = it.next();
				
				//System.out.println(pair.getKey());
				ToateTarile.add(pair.getKey());
			}
			
			
			DecimalFormat form = new DecimalFormat();
			form.setMaximumFractionDigits(4);
			
			for(int i = 0; i<magazine.size(); i++) {
				
				if(magazine.get(i).getClass().getName().equals("MiniMarket")) {
					Mini.add(magazine.get(i));
				}
				if(magazine.get(i).getClass().getName().equals("MediumMarket")) {
					Medium.add(magazine.get(i));
				}
				if(magazine.get(i).getClass().getName().equals("HyperMarket")) {
					Hyper.add(magazine.get(i));
				}
				
			}
			
			
			Vector<Double> totalFtaxeMini = new Vector<Double>();
			Vector<Double> totalFtaxeMedium = new Vector<Double>();
			Vector<Double> totalFtaxeHyper = new Vector<Double>();
			
			//populez vectorii de taxe;
			//sortez magazinele.
			for(int i = 0; i<Mini.size(); i++) {
				totalFtaxeMini.add(Mini.get(i).getTotalFaraTaxe());
				
			}//sortez totalurile
			totalFtaxeMini.sort(null);
			
			for(int i = 0; i<Medium.size(); i++) {
				totalFtaxeMedium.add(Medium.get(i).getTotalFaraTaxe());
				
			}//sortez totalurile
			totalFtaxeMedium.sort(null);
			
			for(int i = 0; i<Hyper.size(); i++) {
				totalFtaxeHyper.add(Hyper.get(i).getTotalFaraTaxe());
				
			}//sortez totalurile
			totalFtaxeHyper.sort(null);
			
			
			//afisez Minimarketurile 
			
			out.println("MiniMarket");
			for(int i=0; i<totalFtaxeMini.size(); i++) {
				Double taxaCurenta = totalFtaxeMini.get(i);
				
				for(int j = 0; j<Mini.size(); j++) {
					if (Mini.get(j).getTotalFaraTaxe() == taxaCurenta) {
						
						out.format("%s",Mini.get(j).nume);
						out.println();
						out.println();
						out.print("Total ");
						out.format("%s %s %s",form.format(Mini.get(j).getTotalFaraTaxe()), form.format(Mini.get(j).getTotalCuTaxe()), form.format(Mini.get(j).getTotalCuTaxeScutite()));
						out.println();
						out.println();
						//aici afisez toate detaliile
						//aici afisez tarile+totalurile/tara
						
						Vector<String> tari = new Vector<String>();
						
						getTariMarket(Mini.get(j), tari);
						
						out.println("Tara");
						
						for(int k = 0; k<ToateTarile.size();k++) {
							if(tari.contains(ToateTarile.get(k))) {
								out.format("%s %s %s %s", ToateTarile.get(k), form.format(Mini.get(j).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(Mini.get(j).getTotalTaraCuTaxe(ToateTarile.get(k))), form.format(Mini.get(j).getTotalTaraCuTaxeScutite(ToateTarile.get(k))));
								out.println();
							}else {
								out.format("%s %s",ToateTarile.get(k),"0");
								out.println();
							}
						}
						
						out.println();
						
						//aici sortez facturile
						Vector<Factura> fact = new Vector<Factura>();
						
						SortareFacturi(Mini.get(j), fact);
						
						
						//aici afisez facturile
						for(int l=0; l<fact.size(); l++) {
							Factura factura =fact.get(l);
							out.format("%s",factura.denumire);
							out.println();
							out.println();
							
							out.format("%s %s %s","Total ", form.format(factura.getTotalFaraTaxe()), form.format(factura.getTotalCuTaxe()));
							out.println();
							out.println();
							//aici afisez tarie din facturi
							Vector<String> tariFacturi = new Vector<String>();
							
							getTariFactura(fact.get(l), tariFacturi);
							
							out.println("Tara");
							for(int k = 0; k<ToateTarile.size();k++) {
								if(tariFacturi.contains(ToateTarile.get(k))) {
									out.format("%s %s %s", ToateTarile.get(k), form.format(fact.get(l).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(fact.get(l).getTotalTaraCuTaxe(ToateTarile.get(k))));
									out.println();
								}else {
									out.format("%s %s",ToateTarile.get(k),"0");
									out.println();
								}
							}
							out.println();
						}
					}
				}
			}
			
			//afisez MediumMarketurile
			
			
			
			out.println("MediumMarket");
			
			for(int i=0; i<totalFtaxeMedium.size(); i++) {
				Double taxaCurenta = totalFtaxeMedium.get(i);
				
				for(int j = 0; j<Medium.size(); j++) {
					if (Medium.get(j).getTotalFaraTaxe() == taxaCurenta) {
						
						out.format("%s",Medium.get(j).nume);
						out.println();
						out.println();
						//aici afisez toate detaliile
						
						out.println("Total ");
						out.format("%s %s %s",form.format(Medium.get(j).getTotalFaraTaxe()), form.format(Medium.get(j).getTotalCuTaxe()), form.format(Medium.get(j).getTotalCuTaxeScutite()));
						out.println();
						out.println();
						//aici afisez toate detaliile
						//aici afisez tarile + totalurile/tara
						Vector<String> tari = new Vector<String>();
						
						getTariMarket(Medium.get(j), tari);
						
						out.println("Tara");
						
						for(int k = 0; k<ToateTarile.size();k++) {
							if(tari.contains(ToateTarile.get(k))) {
								out.format("%s %s %s %s",ToateTarile.get(k), form.format(Medium.get(j).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(Medium.get(j).getTotalTaraCuTaxe(ToateTarile.get(k))), form.format(Medium.get(j).getTotalTaraCuTaxeScutite(ToateTarile.get(k))));
								out.println();
							}else {
								out.format("%s %s",ToateTarile.get(k),"0");
								out.println();
							}
						}
						out.println();
						//aici sortez facturile
						Vector<Factura> fact = new Vector<Factura>();
						
						SortareFacturi(Medium.get(j), fact);
						
						//aici afisez facturile
						for(int l=0; l<fact.size(); l++) {
							Factura factura =fact.get(l);
							out.println(factura.denumire);
							out.println();
							
							out.format("%s %s %s","Total ", form.format(factura.getTotalFaraTaxe()), form.format(factura.getTotalCuTaxe()));
							out.println();
							out.println();
							//aici afisez tarile din facturi
							
							Vector<String> tariFacturi = new Vector<String>();
							
							getTariFactura(fact.get(l), tariFacturi);
							
							out.println("Tara");
							for(int k = 0; k<ToateTarile.size();k++) {
								if(tariFacturi.contains(ToateTarile.get(k))) {
									out.format("%s %s %s", ToateTarile.get(k), form.format(fact.get(l).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(fact.get(l).getTotalTaraCuTaxe(ToateTarile.get(k))));
									out.println();
								}else {
									out.format("%s %s", ToateTarile.get(k), "0");
								}
							}
							out.println();
						}
					}
				}
			}
			

			//afizes HyperMarketurile
			
			out.print("HyperMarket");
			out.println();
			for(int i=0; i<totalFtaxeHyper.size(); i++) {
				Double taxaCurenta = totalFtaxeHyper.get(i);
				
				for(int j = 0; j<Hyper.size(); j++) {
					if (Hyper.get(j).getTotalFaraTaxe() == taxaCurenta) {
						
						out.format("%s",Hyper.get(j).nume);
						out.println();
						out.println();
						//aici afisez toate detaliile 
						out.print("Total ");
						out.format("%s %s %s", form.format(Hyper.get(j).getTotalFaraTaxe()),form.format(Hyper.get(j).getTotalCuTaxe()),form.format(Hyper.get(j).getTotalCuTaxeScutite()));
						out.println();
						out.println();
						//aici afisez toate detaliile
						//aici afisez tarile
						Vector<String> tari = new Vector<String>();
						
						getTariMarket(Hyper.get(j), tari);
						
						out.println("Tara");
						
						//VARIANTA CORECTA!
						for(int k = 0; k<ToateTarile.size();k++) {
							if(tari.contains(ToateTarile.get(k))) {
								out.format("%s %s %s %s",ToateTarile.get(k),form.format(Hyper.get(j).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(Hyper.get(j).getTotalTaraCuTaxe(ToateTarile.get(k))), form.format(Hyper.get(j).getTotalTaraCuTaxeScutite(ToateTarile.get(k))));
								out.println();
							}else {
								out.format("%s %s",ToateTarile.get(k),"0");
								out.println();
							}
						}
						out.println();
						//aici sortez facturile
						Vector<Factura> fact = new Vector<Factura>();
						
						SortareFacturi(Hyper.get(j), fact);
						
						//aici afisez facturile
						for(int l=0; l<fact.size(); l++) {
							Factura factura =fact.get(l);
							out.format("%s",factura.denumire);
							out.println();
							out.println();
							
							out.format("%s %s %s","Total ", form.format(factura.getTotalFaraTaxe()), form.format(factura.getTotalCuTaxe()));
							out.println();
							out.println();
							
							//aici afisesz tarile din facturi
							
							Vector<String> tariFacturi = new Vector<String>();
							
							getTariFactura(fact.get(l), tariFacturi);
							
							out.println("Tara");
							for(int k = 0; k<ToateTarile.size();k++) {
								if(tariFacturi.contains(ToateTarile.get(k))) {
									out.format("%s %s %s",ToateTarile.get(k), form.format(fact.get(l).getTotalTaraFaraTaxe(ToateTarile.get(k))), form.format(fact.get(l).getTotalTaraCuTaxe(ToateTarile.get(k))));
									out.println();
								}else {
									out.format("%s %s",ToateTarile.get(k),"0");
									
								}
							}
						}
						
						
					}
				}
			}
			
			out.close();
		}catch(FileAlreadyExistsException e) {
			
		}catch(IOException e) {
			 
		}
	}
	
	
	
	
public static void main(String args[]){
	
	Gestiune g = Gestiune.getInstance();
	
	g.citireTaxe();
	g.citireProduse();
	g.citireFacturi();
	
	g.task1();
	
}


}
