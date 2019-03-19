package serwis;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class Serwisant extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextArea textAreaUwagi;
	private JTextField textFieldNumer_zlecenia;
	private JTextField textFieldNaleznosc;
	public JPanel panel;
	public Connection conn;
	public Statement statement;

	private static String konto;
	private static String id;

	public Serwisant(String konto, String id) {

		Serwisant.setKonto(konto);
		Serwisant.setId(id);
		// tworzenie okna nadaniemu wymiaru i przypisanie ikony
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		setTitle("R&T SERWIS: Serwisant");// ustawienia jframa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 734);// 800x600
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();// tworzenie kontenera
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// setVisible(true);

		// utworzenie panelu w oknie o odpowiedniej wielkosci
		Color kolor = new Color(0, 128, 255);
		panel = new JPanel();// tworzenie i itawienie panela (t³a)
		panel.setBackground(kolor);
		panel.setBounds(0, 0, 1024, 734);
		contentPane.add(panel);
		panel.setLayout(null);

		// dodanie daty
		Date data1 = new Date();// pole data
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String dataS = format1.format(data1);
		JLabel data11 = new JLabel(dataS);
		data11.setBounds(950, 5, 161, 36);
		data11.setForeground(Color.BLACK);
		panel.add(data11);

		JLabel lblKonto = new JLabel("Witaj: " + konto);// wyœwietlany text
		lblKonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKonto.setBounds(20, 0, 350, 40);
		lblKonto.setForeground(Color.BLACK);
		panel.add(lblKonto);

		// Labele opisuj¹ce co jest do czego
		JLabel lblAktualne = new JLabel("Aktualne serwisy");// wyœwietlany text
		lblAktualne.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAktualne.setBounds(50, 25, 400, 40);
		lblAktualne.setForeground(Color.BLACK);
		panel.add(lblAktualne);
		
		Aktualizuj_Aktualne_Serwisy(id);

		panel.add(Aktualizuj_Aktualne());

		JLabel lblNumer_zlecenia = new JLabel("Numer wykonywanego zlecenia");// wyœwietlany
																				// text
		lblNumer_zlecenia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNumer_zlecenia.setBounds(50, 330, 400, 40);
		lblNumer_zlecenia.setForeground(Color.BLACK);
		panel.add(lblNumer_zlecenia);

		textFieldNumer_zlecenia = new JTextField();
		textFieldNumer_zlecenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumer_zlecenia.setBounds(50, 380, 150, 25);
		panel.add(textFieldNumer_zlecenia);
		textFieldNumer_zlecenia.setColumns(20);

		JLabel lblNaleznosc = new JLabel("Nale¿noœæ za serwis");// wyœwietlany
																// text
		lblNaleznosc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNaleznosc.setBounds(450, 330, 400, 40);
		lblNaleznosc.setForeground(Color.BLACK);
		panel.add(lblNaleznosc);

		textFieldNaleznosc = new JTextField();
		textFieldNaleznosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNaleznosc.setBounds(450, 380, 150, 25);
		panel.add(textFieldNaleznosc);
		textFieldNaleznosc.setColumns(20);

		JLabel lblUwagi = new JLabel("Uwagi do serwisowanego sprzêtu");// wyœwietlany
																		// text
		lblUwagi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUwagi.setBounds(50, 420, 400, 40);
		lblUwagi.setForeground(Color.BLACK);
		panel.add(lblUwagi);

		textAreaUwagi = new TextArea();
		textAreaUwagi.setForeground(Color.BLACK);
		textAreaUwagi.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaUwagi.setRows(5);
		textAreaUwagi.setBounds(50, 470, 920, 160);
		panel.add(textAreaUwagi);

		// dodanie przycisku
		JButton btnZrobione = new JButton("Zrobione");// przycisk zaloguj
														// otwieraj¹cy okno
														// danego uzytkownika
														// jezeli dane s¹
														// poprawne
		btnZrobione.setBounds(50, 650, 120, 25);
		btnZrobione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnZrobione.setForeground(Color.BLACK);
		panel.add(btnZrobione);
		btnZrobione.addActionListener(new PrzyciskListenerZrobione());

		JButton btnWyloguj = new JButton("Wyloguj");// przycisk zaloguj
													// otwieraj¹cy okno danego
													// uzytkownika jezeli dane
													// s¹ poprawne
		btnWyloguj.setBounds(930, 680, 89, 25);
		btnWyloguj.setForeground(Color.BLACK);
		panel.add(btnWyloguj);
		btnWyloguj.addActionListener(new PrzyciskListenerWyloguj());

	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Serwisant.id = id;
	}

	public static String getKonto() {
		return konto;
	}

	public static void setKonto(String konto) {
		Serwisant.konto = konto;
	}

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Serwisant frame = new Serwisant(getKonto(), getId());
				frame.setVisible(true);

			}
		});

	}
	*/

	private class PrzyciskListenerAktualizujAktualne implements ActionListener {// klasa
																				// realizuj¹ca
																				// funkcjonalnoœc
																				// przycisku
																				// zaloguj

		public void actionPerformed(ActionEvent event) {

			Aktualizuj_Aktualne_Serwisy(id);

		}
	}

	public JButton Aktualizuj_Aktualne() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 300, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujAktualne());
		return btnAktualizuj;

	}

	public void Aktualizuj_Aktualne_Serwisy(String id_konto) {

		JScrollPane scrollPane = new JScrollPane();// tworzenie
		// scrolpanelu
		// dla
		// tabeli
		scrollPane.setBounds(50, 80, 920, 200);
		panel.add(scrollPane);

		DefaultTableModel modelA = new DefaultTableModel();// tworzenie
		// tabeli
		// do wypisywania
		// wyników
		JTable tableAktualne = new JTable(modelA);

		// wyswietlanie kolejnych rekordow:

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹
		
//		int id_kintoint = Integer.parseInt(id_konto);
//		int id_wynik = id_kintoint - 3;
//		String id_kontoS

		try {
			// System.out.println(id_klient);
			String queryString = "select s.ID_Serwis, z.MAC, z.Nazwa_Urzadzenia, z.Opis, z.Uwagi, "
					+ "z.Rodzaj_Naprawy, s.Status from serwis s, serwis_dodatkowe sd, zlecenie z, "
					+ "zlecenie_dodatkowe zd, pracownik p, konto k where s.ID_Serwis_Dodatkowe = "
					+ "sd.ID_Serwis_Dodatkowe and z.ID_Zlecenie_Dodatkowe = zd.ID_Zlecenie_Dodatkowe and "
					+ "s.ID_Serwis = z.ID_Zlecenie and sd.ID_Pracownik = p.ID_Pracownik and k.ID_Konto = "
					+ "p.ID_Konto and k.ID_Konto ='" + id_konto + "'";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszeA = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;

				// double iloczyn = rs.getInt("Kwota_Netto") * 1.23;
				// String iloczynS = String.valueOf(iloczyn);

				while (rs.next()) {
					if (rs.getInt("Status") == 1) {
						wierszeA[i++] = new Object[] {rs.getString("ID_Serwis"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"),
								rs.getString("Opis"), rs.getString("Uwagi"), rs.getString("Rodzaj_Naprawy") };
					}
				}

				tableAktualne.setModel(new DefaultTableModel(wierszeA,
						new String[] { "RMA", "Sprzêt", "MAC", "Opis usterki", "Uwagi", "Rodzaj naprawy" }));

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// table.getColumnModel().getColumn(0).setPreferredWidth(35);//
		// ustawienia kolumn tabeli
		// table.getColumnModel().getColumn(1).setPreferredWidth(20);
		// table.getColumnModel().getColumn(2).setPreferredWidth(20);
		// table.getColumnModel().getColumn(3).setPreferredWidth(90);
		// table.getColumnModel().getColumn(4).setPreferredWidth(10);
		// table.getColumnModel().getColumn(5).setPreferredWidth(10);
		scrollPane.setViewportView(tableAktualne);

	}
	
	public void Zrobione(){
		
		int NrSerwisu = Integer.parseInt(textFieldNumer_zlecenia.getText());
		int Koszt = Integer.parseInt(textFieldNaleznosc.getText());
		
		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");
		
		try {
			statement = conn.createStatement();
			String queryString = "select * from serwis, zlecenie";
			
			ResultSet rs2 = statement.executeQuery(queryString);

			while (rs2.next()) {

				if (NrSerwisu > 0 && rs2.getInt("ID_Serwis") == NrSerwisu && rs2.getInt("Status") == 1) {
		
		String zakoncz_serwis = "update serwis set Koszt = '"+ Koszt +"', Uwagi_Serwisanta = '"+ 
									textAreaUwagi.getText() +"', Status = 2 where Status=1 and ID_Serwis ='"
				+ NrSerwisu + "'";
		String zakoncz_zlecenie = "update zlecenie set Status=2 where Status=1 and ID_Zlecenie ='"
				+ NrSerwisu + "'";

		statement.executeUpdate(zakoncz_serwis);
		statement.executeUpdate(zakoncz_zlecenie);
		
		
				} else {
					
					//new Info();
					
				}
				
			}	
			rs2.close();

		} catch (SQLException e) {

			//System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych
			
			
		}
		
		Date data1 = new Date();// pole data
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String data_wyst = format1.format(data1);

		//Date data2 = new Date();// pole data
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		String data_plat = format2.format(calendar.getTime());
		
		
		///////////////////////////
		try {
			statement = conn.createStatement();
			String queryString = "select max(fd.ID_Faktura_Dodatkowe)+1 as "
					+ "faktura_dod from serwis s, faktura_dodatkowe fd";
			
			ResultSet rs3 = statement.executeQuery(queryString);

			while (rs3.next()) {

				if (NrSerwisu > 0) {
		
		String faktura_dod = "insert into faktura_dodatkowe (ID_Serwis, ID_Klient) VALUES ('" + 
				NrSerwisu + "','1')";
		
		String faktura = "insert into faktura (Kwota_Netto, Data_Wystawienia, Termin_Platnosci, Czy_Zaplacone, "
				+ "ID_Faktura_Dodatkowe, ID_Dane_Firmy) VALUES ('" + Koszt + "','" + data_wyst + "','" 
				+ data_plat + "','0','" + rs3.getInt("faktura_dod") + "','1')";

		statement.executeUpdate(faktura_dod);
		statement.executeUpdate(faktura);
		
		
				} else {
					
					//new Info();
					
				}
				
			}	
			rs3.close();

		} catch (SQLException e) {

			//System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych
			
			
		}
		
		//////////////////////////
		try {
			statement = conn.createStatement();
			String queryString = "SELECT zd.ID_Klient as id_klient FROM serwis s, zlecenie z, zlecenie_dodatkowe zd "
					+ "WHERE s.ID_Serwis = z.ID_Zlecenie and z.ID_Zlecenie_Dodatkowe = zd.ID_Zlecenie_Dodatkowe "
					+ "and z.ID_Zlecenie = '" + NrSerwisu + "'";
			
			ResultSet rs4 = statement.executeQuery(queryString);

			while (rs4.next()) {

				if (NrSerwisu > 0) {
		
		String klient_faktura = "update faktura_dodatkowe set ID_Klient = '" + rs4.getInt("id_klient") + 
				"' where ID_Serwis = '" + NrSerwisu + "'";
		
		statement.executeUpdate(klient_faktura);
		
		
		
				} else {
					
					//new Info();
					
				}
				
			}	
			rs4.close();

		} catch (SQLException e) {

			//System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych
			
			
		}
		
		/////////////////////////
		
	}

	// klasa obs³uguj¹ca dzia³anie przycisku
	private class PrzyciskListenerZrobione implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Zrobione();

		}
	}

	private class PrzyciskListenerWyloguj implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Logowanie l = new Logowanie();
			l.setVisible(true);
			setVisible(false);

		}
	}

	class Baza {// klasa realizuj¹ca po³¹czenie z baza danych

		public Baza() {
		}

		// String polaczenieURL =
		// "jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=";
		public void dbConnect(String polaczenieURL) {

			try {
				// Ustawiamy dane dotycz¹ce pod³¹czenia
				conn = DriverManager.getConnection(polaczenieURL);
				// System.out.println ("Po³¹czono z baz¹");
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
