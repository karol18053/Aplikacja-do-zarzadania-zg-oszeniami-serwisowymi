package serwis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Ksiegowy extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNumer_faktury;
	public Connection conn;
	public Statement statement;
	private JPanel panel;

	public Ksiegowy(){
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));		
		setTitle("R&T SERWIS: Ksiêgowy");//ustawienia jframa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 734);//800x600
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();//tworzenie kontenera
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//setVisible(true);
		
		Color kolor = new Color(0, 128, 255);
		panel = new JPanel();//tworzenie i itawienie panela (t³a)
		panel.setBackground(kolor);
		panel.setBounds(0, 0, 1024, 734);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Date data1 = new Date();// pole data
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String dataS = format1.format(data1);
		JLabel data11 = new JLabel(dataS);
		data11.setBounds(950, 5, 161, 36);
		data11.setForeground(Color.BLACK);
		panel.add(data11);
		
		JLabel lblFaktury = new JLabel("Lista faktur do rozliczenia");//wyœwietlany text
		lblFaktury.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFaktury.setBounds(50, 10, 400, 40);
		lblFaktury.setForeground(Color.BLACK);
		panel.add(lblFaktury);
		
		Aktualizuj_zakonczone();
		
		JLabel lblNumer_faktury = new JLabel("Numer faktury do rozliczenia");//wyœwietlany text
		lblNumer_faktury.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNumer_faktury.setBounds(50, 550, 400, 40);
		lblNumer_faktury.setForeground(Color.BLACK);
		panel.add(lblNumer_faktury);
		
		textFieldNumer_faktury = new JTextField();
		textFieldNumer_faktury.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumer_faktury.setBounds(50, 600, 150, 25);
		panel.add(textFieldNumer_faktury);
		textFieldNumer_faktury.setColumns(20);
		
		panel.add(Aktualizuj_Zakonczone_btn());
		
		JButton btnRozlicz = new JButton("Rozlicz");//przycisk zaloguj otwieraj¹cy okno danego uzytkownika jezeli dane s¹ poprawne
		btnRozlicz.setBounds(50, 650, 100, 25);
		btnRozlicz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRozlicz.setForeground(Color.BLACK);
		panel.add(btnRozlicz);
		btnRozlicz.addActionListener(new PrzyciskListenerRozlicz());
		
		JButton btnWyloguj = new JButton("Wyloguj");//przycisk zaloguj otwieraj¹cy okno danego uzytkownika jezeli dane s¹ poprawne
		btnWyloguj.setBounds(930, 680, 89, 25);
		btnWyloguj.setForeground(Color.BLACK);
		panel.add(btnWyloguj);
		btnWyloguj.addActionListener(new PrzyciskListenerWyloguj());
		
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				
//					Ksiegowy frame = new Ksiegowy();
//					frame.setVisible(true);
//
//				}
//		});
//
//	}
	
	public JButton Aktualizuj_Zakonczone_btn(){
		  
	  	JButton btnAktualizuj = new JButton("Aktualizuj");//przycisk zaloguj otwieraj¹cy okno danego uzytkownika jezeli dane s¹ poprawne
	  	btnAktualizuj.setBounds(50, 500, 89, 25);
	  	btnAktualizuj.setForeground(Color.BLACK);
	  	btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujZakonczone());
		return btnAktualizuj;
	  
}
	
	public void Aktualizuj_zakonczone(){
		
		JScrollPane scrollPane = new JScrollPane();// tworzenie scrolpanelu dla
		// tabeli
		scrollPane.setBounds(50, 80, 920, 400);
		panel.add(scrollPane);
		
		DefaultTableModel modelo = new DefaultTableModel();//tworzenie tabeli do wypisywania wyników
		JTable faktury = new JTable(modelo);
		
		Baza baza = new Baza();//tworzenie obiektu klasy realizuj¹cej po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");//³¹czenie z baz¹
		
		try {
			//statement = conn.createStatement();
			String queryString = "select f.ID_Faktura, f.Data_Wystawienia, f.Termin_Platnosci, "
					+ "(select Nazwisko from klient WHERE ID_Klient= fd.ID_Klient) as Nazwa, "
					+ "(select Telefon from klient WHERE ID_Klient= fd.ID_Klient) as Telefon, f.Kwota_Netto, "
					+ "f.Czy_Zaplacone from faktura as f join faktura_dodatkowe as fd on "
					+ "f.ID_Faktura_Dodatkowe=fd.ID_Faktura_Dodatkowe";//zapytanie wyci¹gaj¹ce odp. dane z bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);//wykonanie zapytania
			
			try {

				Object[][] wiersze = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Czy_Zaplacone") == 0) {
					wiersze[i++] = new Object[] { "FS " + rs.getString("ID_Faktura") + "/2018",  rs.getString("Data_Wystawienia"),
							rs.getString("Termin_Platnosci"), rs.getString("Nazwa"), rs.getString("Telefon"), 
							rs.getString("Kwota_Netto")};
					}
				}
				faktury.setModel(new DefaultTableModel(wiersze,
						new String[] { "Dokument", "Data powstania", "Termin p³atnoœci", 
								"Nazwa", "Telefon", "Nale¿noœæ" }));

				////////////////////////

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		faktury.getColumnModel().getColumn(0).setPreferredWidth(35);// ustawienia kolumn tabeli
		faktury.getColumnModel().getColumn(1).setPreferredWidth(20);
		faktury.getColumnModel().getColumn(2).setPreferredWidth(20);
		faktury.getColumnModel().getColumn(3).setPreferredWidth(90);
		faktury.getColumnModel().getColumn(4).setPreferredWidth(10);
		faktury.getColumnModel().getColumn(5).setPreferredWidth(10);
		scrollPane.setViewportView(faktury);
	}
	
	public void Rozlicz(){
		
		int NrFaktury = Integer.parseInt(textFieldNumer_faktury.getText());
				
		try {
			statement = conn.createStatement();
			String queryString = "select * from faktura";
			
			ResultSet rs2 = statement.executeQuery(queryString);

			while (rs2.next()) {

				if (NrFaktury > 0 && rs2.getInt("Czy_Zaplacone") == 0) {
		
		String rozlicz_fa = "update faktura set Czy_Zaplacone=1 where Czy_Zaplacone=0 and ID_Faktura ='"
		+ NrFaktury + "'";

		statement.executeUpdate(rozlicz_fa);
		
		
				} else {
					
					new Info();
					
				}
				
			}	
			rs2.close();

		} catch (SQLException e) {

			//System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych
			
			
		}
		
		//rs2.close();
	}
	
	private class PrzyciskListenerAktualizujZakonczone implements ActionListener {//klasa realizuj¹ca funkcjonalnoœc przycisku zaloguj

		public void actionPerformed(ActionEvent event) {
				
			Aktualizuj_zakonczone();				
			}
		}
	
	private class PrzyciskListenerRozlicz implements ActionListener {//klasa realizuj¹ca funkcjonalnoœc przycisku zaloguj

		public void actionPerformed(ActionEvent event) {
			
			Rozlicz();
			
		}
	}
	
	private class PrzyciskListenerWyloguj implements ActionListener {//klasa realizuj¹ca funkcjonalnoœc przycisku zaloguj

		public void actionPerformed(ActionEvent event) {
			
			Logowanie l = new Logowanie();
			l.setVisible(true);
			setVisible(false);
			
		}
	}
	
	class Baza {//klasa realizuj¹ca po³¹czenie z baza danych

		public Baza() {
		}

		//String polaczenieURL = "jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=";
		public void dbConnect(String polaczenieURL){
		
		try {
			 //Ustawiamy dane dotycz¹ce pod³¹czenia
			conn = DriverManager.getConnection(polaczenieURL);
			//System.out.println ("Po³¹czono z baz¹");
		}
	
			catch (SQLException e) {
					System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
				}
			
			 try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
	}
	};
	
}
