package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Color;

public class LoginF {

	private JFrame frame;
	private JTextField textF;
	private JPasswordField passwordF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginF window = new LoginF();
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
	public LoginF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel Nume = new JLabel("Login");
		Nume.setForeground(Color.MAGENTA);
		Nume.setBounds(188, 47, 70, 15);
		frame.getContentPane().add(Nume);
		
		JLabel ID = new JLabel("Username");
		ID.setForeground(Color.PINK);
		ID.setBounds(115, 109, 84, 15);
		frame.getContentPane().add(ID);
		
		JLabel password = new JLabel("password");
		password.setForeground(Color.CYAN);
		password.setBounds(115, 169, 84, 15);
		frame.getContentPane().add(password);
		
		textF = new JTextField();
		textF.setBounds(277, 107, 114, 19);
		frame.getContentPane().add(textF);
		textF.setColumns(10);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(277, 167, 114, 19);
		frame.getContentPane().add(passwordF);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.PINK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passw = passwordF.getText();
				String user =  textF.getText();
				
				//System.out.println(passw);
				//System.out.println(user);
				if(user.contains("miru") && passw.contains("123")) {
					textF.setText(null);
					passwordF.setText(null);
					//System.out.println("am facut null");
				}
				else {
					JOptionPane.showMessageDialog(null, "Too bad, wrong password or username..","Login Error", JOptionPane.ERROR_MESSAGE);
					textF.setText(null);
					passwordF.setText(null);
				}
				
			}
		});
		btnNewButton.setBounds(25, 248, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setForeground(Color.CYAN);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textF.setText(null);
				passwordF.setText(null);
				//System.out.println("reset");
				//
				paginaFis pag =new paginaFis();
				pag.show();
			}
		});
		btnNewButton_1.setBounds(175, 248, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setForeground(Color.MAGENTA);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frmLogin = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLogin, "Are you sure you want to exit?","Login",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
					System.out.println("exit");
				}
				
			}
		});
		btnNewButton_2.setBounds(321, 248, 117, 25);
		frame.getContentPane().add(btnNewButton_2);
	}
}
