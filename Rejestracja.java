package serwis;

import java.awt.Checkbox;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Rejestracja extends JFrame {

	private static final long serialVersionUID = 1L;// deklaracja potrzebnych
													// atrybutów
	private JPanel contentPane;
	private JTextField textFieldImie;
	private JTextField textFieldNazwisko;
	private JTextField textFieldUlica;
	private JTextField textFieldNumer_domu;
	private JTextField textFieldMiejscowosc;
	private JTextField textFieldKod_pocztowy;
	private JTextField textFieldPESEL;
	private JTextField textFieldNazwa_firmy;
	private JTextField textFieldNIP;
	private JTextField textFieldTelefon;
	private JTextField textFieldEmail;
	private JTextField textFieldLogin;
	private JPasswordField passwordFieldHaslo;
	private JPasswordField passwordFieldPHaslo;
	private Checkbox chckbxAkceptuje;

	public Connection conn;
	public Statement statement;

	public Rejestracja() {
		// tworzenie okna nadaniemu wymiaru i przypisanie ikony
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		setTitle("R&T SERWIS: Rejestracja");// ustawienia jframa
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
		JPanel panel = new JPanel();// tworzenie i itawienie panela (t³a)
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

		// Labele opisuj¹ce co jest do czego
		JLabel lblRejestracja = new JLabel("Rejestracja: podaj wymagane dane");// wyœwietlany
																				// text
		lblRejestracja.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRejestracja.setBounds(50, 10, 400, 40);
		lblRejestracja.setForeground(Color.BLACK);
		panel.add(lblRejestracja);

		JLabel lblDane = new JLabel("Dane do faktury i dostawy");// wyœwietlany
																	// text
		lblDane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDane.setBounds(50, 50, 400, 40);
		lblDane.setForeground(Color.BLACK);
		panel.add(lblDane);

		JLabel lblImie = new JLabel("Imiê*");// wyœwietlany text
		lblImie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImie.setBounds(50, 110, 122, 25);
		lblImie.setForeground(Color.BLACK);
		panel.add(lblImie);

		textFieldImie = new JTextField();
		textFieldImie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldImie.setBounds(160, 110, 150, 25);
		panel.add(textFieldImie);
		textFieldImie.setColumns(20);

		JLabel lblNazwisko = new JLabel("Nazwisko*");// wyœwietlany text
		lblNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNazwisko.setBounds(50, 140, 122, 25);
		lblNazwisko.setForeground(Color.BLACK);
		panel.add(lblNazwisko);

		textFieldNazwisko = new JTextField();
		textFieldNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNazwisko.setBounds(160, 140, 150, 25);
		panel.add(textFieldNazwisko);
		textFieldNazwisko.setColumns(20);

		JLabel lblUlica = new JLabel("Ulica*");// wyœwietlany text
		lblUlica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUlica.setBounds(50, 170, 122, 25);
		lblUlica.setForeground(Color.BLACK);
		panel.add(lblUlica);

		textFieldUlica = new JTextField();
		textFieldUlica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUlica.setBounds(160, 170, 150, 25);
		panel.add(textFieldUlica);
		textFieldUlica.setColumns(40);

		JLabel lblNumer_domu = new JLabel("Numer domu*");// wyœwietlany text
		lblNumer_domu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumer_domu.setBounds(50, 200, 122, 25);
		lblNumer_domu.setForeground(Color.BLACK);
		panel.add(lblNumer_domu);

		textFieldNumer_domu = new JTextField();
		textFieldNumer_domu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumer_domu.setBounds(160, 200, 150, 25);
		panel.add(textFieldNumer_domu);
		textFieldNumer_domu.setColumns(20);

		JLabel lblMiejscowosc = new JLabel("Miejscowoœæ*");// wyœwietlany text
		lblMiejscowosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiejscowosc.setBounds(50, 230, 122, 25);
		lblMiejscowosc.setForeground(Color.BLACK);
		panel.add(lblMiejscowosc);

		textFieldMiejscowosc = new JTextField();
		textFieldMiejscowosc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMiejscowosc.setBounds(160, 230, 150, 25);
		panel.add(textFieldMiejscowosc);
		textFieldMiejscowosc.setColumns(40);

		JLabel lblKod_pocztowy = new JLabel("Kod pocztowy*");// wyœwietlany text
		lblKod_pocztowy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKod_pocztowy.setBounds(50, 260, 122, 25);
		lblKod_pocztowy.setForeground(Color.BLACK);
		panel.add(lblKod_pocztowy);

		textFieldKod_pocztowy = new JTextField();
		textFieldKod_pocztowy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldKod_pocztowy.setBounds(160, 260, 150, 25);
		panel.add(textFieldKod_pocztowy);
		textFieldKod_pocztowy.setColumns(20);

		JLabel lblPESEL = new JLabel("PESEL");// wyœwietlany text
		lblPESEL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPESEL.setBounds(50, 290, 122, 25);
		lblPESEL.setForeground(Color.BLACK);
		panel.add(lblPESEL);

		textFieldPESEL = new JTextField();
		textFieldPESEL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPESEL.setBounds(160, 290, 150, 25);
		panel.add(textFieldPESEL);
		textFieldPESEL.setColumns(20);

		JLabel lblNazwa_firmy = new JLabel("Nazwa firmy**");// wyœwietlany text
		lblNazwa_firmy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNazwa_firmy.setBounds(50, 320, 122, 25);
		lblNazwa_firmy.setForeground(Color.BLACK);
		panel.add(lblNazwa_firmy);

		textFieldNazwa_firmy = new JTextField();
		textFieldNazwa_firmy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNazwa_firmy.setBounds(160, 320, 150, 25);
		panel.add(textFieldNazwa_firmy);
		textFieldNazwa_firmy.setColumns(40);

		JLabel lblNIP = new JLabel("NIP**");// wyœwietlany text
		lblNIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNIP.setBounds(50, 350, 122, 25);
		lblNIP.setForeground(Color.BLACK);
		panel.add(lblNIP);

		textFieldNIP = new JTextField();
		textFieldNIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNIP.setBounds(160, 350, 150, 25);
		panel.add(textFieldNIP);
		textFieldNIP.setColumns(20);

		JLabel lblDane_kontaktowe = new JLabel("Dane kontaktowe");// wyœwietlany
																	// text
		lblDane_kontaktowe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDane_kontaktowe.setBounds(550, 60, 200, 25);
		lblDane_kontaktowe.setForeground(Color.BLACK);
		panel.add(lblDane_kontaktowe);

		JLabel lblTelefon = new JLabel("Telefon*");// wyœwietlany text
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefon.setBounds(550, 110, 122, 25);
		lblTelefon.setForeground(Color.BLACK);
		panel.add(lblTelefon);

		textFieldTelefon = new JTextField();
		textFieldTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTelefon.setBounds(650, 110, 150, 25);
		panel.add(textFieldTelefon);
		textFieldTelefon.setColumns(20);

		JLabel lblEmail = new JLabel("Email*");// wyœwietlany text
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(550, 140, 122, 25);
		lblEmail.setForeground(Color.BLACK);
		panel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEmail.setBounds(650, 140, 150, 25);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(20);

		JLabel lblDane_do_logowania = new JLabel("Dane do logowania");// wyœwietlany
																		// text
		lblDane_do_logowania.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDane_do_logowania.setBounds(550, 180, 250, 30);
		lblDane_do_logowania.setForeground(Color.BLACK);
		panel.add(lblDane_do_logowania);

		JLabel lblLogin = new JLabel("Login*");// wyœwietlany text
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(550, 230, 122, 25);
		lblLogin.setForeground(Color.BLACK);
		panel.add(lblLogin);

		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLogin.setBounds(650, 230, 150, 25);
		panel.add(textFieldLogin);
		textFieldLogin.setColumns(20);

		JLabel lblHaslo = new JLabel("Has³o*");// wyœwietlany text
		lblHaslo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHaslo.setBounds(550, 260, 122, 25);
		lblHaslo.setForeground(Color.BLACK);
		panel.add(lblHaslo);

		passwordFieldHaslo = new JPasswordField();
		passwordFieldHaslo.setBounds(650, 260, 150, 25);
		panel.add(passwordFieldHaslo);

		JLabel lblPHaslo = new JLabel("Powtórz has³o*");// wyœwietlany text
		lblPHaslo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPHaslo.setBounds(550, 290, 122, 25);
		lblPHaslo.setForeground(Color.BLACK);
		panel.add(lblPHaslo);

		passwordFieldPHaslo = new JPasswordField();
		passwordFieldPHaslo.setBounds(650, 290, 150, 25);
		panel.add(passwordFieldPHaslo);

		JLabel lblGwiazdka = new JLabel("* Pola oznaczone gwiazdk¹ s¹ wymagane");// wyœwietlany
																					// text
		lblGwiazdka.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGwiazdka.setBounds(50, 620, 300, 25);
		lblGwiazdka.setForeground(Color.BLACK);
		panel.add(lblGwiazdka);

		JLabel lblGwiazdka2 = new JLabel(
				"** Pola oznaczone podwójn¹ gwiazdk¹ s¹ wymagane, gdy chcesz fakturê na firmê");// wyœwietlany
																								// text
		lblGwiazdka2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGwiazdka2.setBounds(50, 650, 600, 25);
		lblGwiazdka2.setForeground(Color.BLACK);
		panel.add(lblGwiazdka2);

		JButton btnDodaj = new JButton("Dodaj u¿ytkownika");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnDodaj.setBounds(550, 400, 220, 40);
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDodaj.setForeground(Color.BLACK);
		panel.add(btnDodaj);
		btnDodaj.addActionListener(new PrzyciskListenerDodaj());

		chckbxAkceptuje = new Checkbox("Akceptuje regulamin znajduj¹cy siê na naszej stronie");
		chckbxAkceptuje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxAkceptuje.setForeground(Color.BLACK);
		chckbxAkceptuje.setBounds(550, 350, 400, 28);
		panel.add(chckbxAkceptuje);

		JButton btnZakoncz = new JButton("Zakoñcz");// przycisk zaloguj
													// otwieraj¹cy okno danego
													// uzytkownika jezeli dane
													// s¹ poprawne
		btnZakoncz.setBounds(930, 680, 89, 25);
		btnZakoncz.setForeground(Color.BLACK);
		panel.add(btnZakoncz);
		btnZakoncz.addActionListener(new PrzyciskListenerZakoncz());

	}

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Rejestracja frame = new Rejestracja();
				frame.setVisible(true);

			}
		});
	}
	*/

	public void Dodaj_uzytkownika() {

		String imie = textFieldImie.getText();
		String nazwisko = textFieldNazwisko.getText();
		String ulica = textFieldUlica.getText();
		String nr_domu = textFieldNumer_domu.getText();
		String miejscowosc = textFieldMiejscowosc.getText();
		String kod_pocztowy = textFieldKod_pocztowy.getText();
		String pesel = textFieldPESEL.getText();
		String nazwa_firmy = textFieldNazwa_firmy.getText();
		String nip = textFieldNIP.getText();

		String telefon = textFieldTelefon.getText();
		String email = textFieldEmail.getText();

		String login = textFieldLogin.getText();
		@SuppressWarnings("deprecation")
		String haslo = passwordFieldHaslo.getText();
		@SuppressWarnings("deprecation")
		String powtorz_haslo = passwordFieldPHaslo.getText();
		
		boolean akceptacja = chckbxAkceptuje.getState();

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {

			String id_tabele = "select max(k.ID_Konto)+1 as konto, max(dk.ID_Dodatkowe_Klient)+1 as dklient, "
					+ "max(ak.ID_Adres_Klient)+1 as aklient from konto k, dodatkowe_klient dk, adres_klient ak";// zapytanie
																												// wyci¹gaj¹ce
																												// odp.
																												// dane
																												// z
																												// bazy

			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(id_tabele);

			while (rs.next()) {

				if (imie != null && nazwisko != null && ulica != null && nr_domu != null && miejscowosc != null
						&& kod_pocztowy != null && pesel != null && nazwa_firmy != null && nip != null
						&& telefon != null && email != null && login != null && haslo != null
						&& powtorz_haslo != null && akceptacja == true) {
					// dzia³a
					
					int kontoK = rs.getInt("konto");
					int klientD = rs.getInt("dklient");
					int klientA = rs.getInt("aklient");

					if (haslo.equals(powtorz_haslo)) {

					 String konto = "insert into konto (Login, Haslo, Status) values('" + login + "','" + haslo
								+ "'," + " '1')";

					statement.executeUpdate(konto);

					} else {
						new Info();
					}
					// dzia³a

					String dod_klient = "insert into dodatkowe_klient (Nazwa_Firmy, NIP) values('" + nazwa_firmy + "','"
							+ nip + "')";
					statement.executeUpdate(dod_klient);
					// dzia³a

					String adr_klient = "insert into adres_klient (Ulica, Nr_domu, Miejscowosc, Kod_Pocztowy) "
							+ "values('" + ulica + "','" + nr_domu + "','" + miejscowosc + "','" + kod_pocztowy + "')";
					statement.executeUpdate(adr_klient);
					// dzia³a
					
//					System.out.println(kontoK);
//					System.out.println(klientD);
//					System.out.println(klientA);

					String klient = "insert into klient (Imie, Nazwisko, PESEL, Telefon, Email, ID_Konto, ID_Adres_Klient,"
							+ " ID_Dodatkowe_Klient) values('" + imie + "','" + nazwisko + "','" + pesel + "','"
							+ telefon + "','" + email + "','" + kontoK + "','" + klientA + "','" + klientD + "')";

					statement.executeUpdate(klient);

				} else {
					new Info();

				}
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class PrzyciskListenerDodaj implements ActionListener {// klasa
																	// realizuj¹ca
																	// funkcjonalnoœc
																	// przycisku
																	// zaloguj

		public void actionPerformed(ActionEvent event) {

			Dodaj_uzytkownika();

		}
	}

	private class PrzyciskListenerZakoncz implements ActionListener {// klasa
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
				//System.out.println("Po³¹czono z baz¹");
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
