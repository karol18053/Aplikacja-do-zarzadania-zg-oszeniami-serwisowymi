package serwis;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;


public class Sprawdz extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static JFrame okno = new JFrame();
	
	public Sprawdz(String data) {//konstruktor klasy info
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();//tworzenie panelu (t³a)
		Color kolor = new Color(0, 128, 255);
		panel.setBackground(kolor);
		panel.setBounds(0, 0, 400, 123);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton okButton = new JButton("OK");// przycisk ok
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {//funkcjonalnoœc przycisku ok
				setVisible(false);
			}
		});
		okButton.setBounds(150, 80, 89, 23);
		panel.add(okButton);
		
		JLabel lblInfo = new JLabel("Urz¹dzenie jest na gwarancji do: " + data);// wyœwietlany text
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(Color.RED);
		lblInfo.setBounds(5, 10, 390, 69);
		panel.add(lblInfo);
		
		setTitle("Sprawdzanie gwarancji sprzêtu!");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		setVisible(true);
		setBounds(0, 0, 400, 150);
		setLocationRelativeTo(null);
		setResizable(false);		
	}
					
	
}




