package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class paginaFis {

	private JFrame frame;
	private JTextField TaxeF;
	private JTextField ProduseF;
	private JTextField FacturiF;

	/**
	 * Launch the application.
	 */
	public static void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paginaFis window = new paginaFis();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public paginaFis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnGo.setForeground(Color.MAGENTA);
		btnGo.setBounds(153, 243, 117, 25);
		frame.getContentPane().add(btnGo);
		
		JLabel Titlu = DefaultComponentFactory.getInstance().createLabel("Input Files");
		Titlu.setHorizontalAlignment(SwingConstants.CENTER);
		Titlu.setForeground(Color.PINK);
		Titlu.setBounds(140, 41, 136, 15);
		frame.getContentPane().add(Titlu);
		
		JLabel taxe = new JLabel("Taxe");
		taxe.setForeground(Color.GRAY);
		taxe.setHorizontalAlignment(SwingConstants.CENTER);
		taxe.setBounds(38, 89, 70, 15);
		frame.getContentPane().add(taxe);
		
		JLabel produse = new JLabel("Produse");
		produse.setForeground(Color.GRAY);
		produse.setHorizontalAlignment(SwingConstants.CENTER);
		produse.setBounds(38, 138, 70, 15);
		frame.getContentPane().add(produse);
		
		JLabel facturi = new JLabel("Facturi");
		facturi.setForeground(Color.GRAY);
		facturi.setHorizontalAlignment(SwingConstants.CENTER);
		facturi.setBounds(38, 184, 70, 15);
		frame.getContentPane().add(facturi);
		
		TaxeF = new JTextField();
		TaxeF.setBounds(190, 87, 114, 19);
		frame.getContentPane().add(TaxeF);
		TaxeF.setColumns(10);
		
		ProduseF = new JTextField();
		ProduseF.setBounds(190, 136, 114, 19);
		frame.getContentPane().add(ProduseF);
		ProduseF.setColumns(10);
		
		FacturiF = new JTextField();
		FacturiF.setBounds(190, 182, 114, 19);
		frame.getContentPane().add(FacturiF);
		FacturiF.setColumns(10);
	}
}
