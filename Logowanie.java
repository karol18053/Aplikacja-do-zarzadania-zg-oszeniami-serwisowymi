package serwis;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Logowanie extends JFrame {

	private static final long serialVersionUID = 1L;// deklaracja potrzebnych
													// atrybutów
	private JPanel contentPane;
	public JTextField textField;
	private JPasswordField passwordField;
	public Connection conn;
	public Statement statement;
	public String konto;
	public String id;
	private JPanel panel;// tworzenie i itawienie panela (t³a)

	public Logowanie() {// konstruktor
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		setTitle("R&T SERWIS wita - zaloguj siê!");// ustawienia jframa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 408, 232);
		setLocationRelativeTo(null);
		// setResizable(false);

		contentPane = new JPanel();// tworzenie kontenera
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// setVisible(true);

		Color kolor = new Color(0, 128, 255);
		panel = new JPanel();
		panel.setBackground(kolor);
		panel.setBounds(0, 0, 408, 232);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblZaloguj = new JLabel("Podaj dane logowania");// wyœwietlany
																// text
		lblZaloguj.setBounds(122, 11, 164, 20);
		lblZaloguj.setForeground(Color.BLACK);
		panel.add(lblZaloguj);

		JLabel lblNazwaUytkownika = new JLabel("Nazwa u¿ytkownika");// wyœwietlany
																	// text
		lblNazwaUytkownika.setBounds(34, 49, 122, 25);
		lblNazwaUytkownika.setForeground(Color.BLACK);
		panel.add(lblNazwaUytkownika);

		JLabel lblHaso = new JLabel("Has³o");// wyœwietlany text
		lblHaso.setBounds(114, 73, 33, 25);
		lblHaso.setForeground(Color.BLACK);
		panel.add(lblHaso);

		JButton btnZaloguj = new JButton("Zaloguj");// przycisk zaloguj
													// otwieraj¹cy okno danego
													// uzytkownika jezeli dane
													// s¹ poprawne
		btnZaloguj.setBounds(152, 110, 89, 23);
		btnZaloguj.setForeground(Color.BLACK);
		panel.add(btnZaloguj);
		btnZaloguj.addActionListener(new PrzyciskListenerZaloguj());

		// btnZaloguj.addActionListener(new PrzyciskListenerZaloguj());//dodanie
		// funkcjonalnoœci do przycisku

		textField = new JTextField();// miejsce do wpisania nazwy u¿ytkownika
		textField.setBounds(155, 51, 85, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();// miejsce do wpisania has³a
												// u¿ytkownika
		passwordField.setBounds(155, 75, 85, 20);
		panel.add(passwordField);

		JLabel lblKonto = new JLabel("Nie masz konta?");// wyœwietlany text
		lblKonto.setBounds(150, 135, 122, 25);
		lblKonto.setForeground(Color.BLACK);
		panel.add(lblKonto);

		JButton btnZarejestruj = new JButton("Zarejestruj siê!");// przycisk
																	// zaloguj
																	// otwieraj¹cy
																	// okno
																	// danego
																	// uzytkownika
																	// jezeli
																	// dane s¹
																	// poprawne
		btnZarejestruj.setBounds(130, 165, 130, 23);
		btnZarejestruj.setForeground(Color.BLACK);
		panel.add(btnZarejestruj);
		btnZarejestruj.addActionListener(new PrzyciskListenerZarejestruj());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Logowanie frame = new Logowanie();
					frame.setVisible(true);
				} catch (Exception e) {
					////
				}
				assert (this != null);
			}
		});

	}

	private void Zaloguj() {

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹
//		baza.dbConnect("jdbc:mysql://www.mkwk018.cba.pl/mysql/db_structure.php?server=1&db="
//				+ "karol18053&token=ce3a57f189cc0511fa98f172db548cb0/karol18053?user=karol18053&"
//				+ "password=P0l@nk@3018053");
		
		try {
			// statement = conn.createStatement();
			String queryString = "select * from konto";// zapytanie wyci¹gaj¹ce
														// odp. dane z bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytanie
			String nazwaw = textField.getText();// pobieranie loginu
			@SuppressWarnings("deprecation")
			String haslow = passwordField.getText();// pobieranie has³a

			while (rs.next()) {
				
				// System.out.println(rs.getString("ID_Konto"));
				// System.out.println(rs.getString("Login"));
				// System.out.println(rs.getString("Haslo"));

				if (nazwaw.equals(rs.getString("Login")) && haslow.equals(rs.getString("Haslo"))
						&& rs.getString("Status").equals("1")) {// weryfikacja
																// danych

					// System.out.println("zalogowany");
					// System.out.println(rs.getString("ID_Konto"));

					konto = rs.getString("Login");
					id = rs.getString("ID_Konto");
					Klient kl = new Klient(konto, id);
					// System.out.println(konto);

					Klient.GUI();
					kl.setVisible(true);
					setVisible(false);

					continue;
				}

				else if (nazwaw.equals(rs.getString("Login")) && haslow.equals(rs.getString("Haslo"))
						&& rs.getString("Status").equals("3")) {// weryfikacja
																// danych

					// System.out.println(rs.getString("ID_Konto"));
					// System.out.println(rs.getString("Status"));

					Kierownik k = new Kierownik();
					Kierownik.GUI();
					k.setVisible(true);
					setVisible(false);

					continue;
				}

				else if (nazwaw.equals(rs.getString("Login")) && haslow.equals(rs.getString("Haslo"))
						&& rs.getString("Status").equals("4")) {// weryfikacja
																// danych

					// System.out.println(rs.getString("ID_Konto"));
					// System.out.println(rs.getString("Status"));

					Ksiegowy ks = new Ksiegowy();

					ks.setVisible(true);
					setVisible(false);

					continue;
				}

				else if (nazwaw.equals(rs.getString("Login")) && haslow.equals(rs.getString("Haslo"))
						&& rs.getString("Status").equals("2")) {// weryfikacja
																// danych

					// System.out.println(rs.getString("ID_Konto"));
					// System.out.println(rs.getString("Status"));

					konto = rs.getString("Login");
					id = rs.getString("ID_Konto");
					
					Serwisant s = new Serwisant(konto, id);
					// System.out.println(konto);

					s.setVisible(true);
					setVisible(false);

					continue;
				} else if (nazwaw.equals(rs.getString("Login")) != haslow.equals(rs.getString("Haslo"))
						&& rs.getString("Status").equals("2")) {
					// do poprawki
					Logowanie l = new Logowanie();
					JLabel lblBlad = new JLabel("B³êdny login lub has³o");// wyœwietlany
																			// text
					lblBlad.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblBlad.setBounds(115, 27, 164, 20);
					lblBlad.setForeground(Color.RED);
					l.panel.add(lblBlad);

					l.setVisible(true);
					setVisible(false);

				}

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class PrzyciskListenerZaloguj implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Zaloguj();

		}
	}

	private class PrzyciskListenerZarejestruj implements ActionListener {// klasa
																			// realizuj¹ca
																			// funkcjonalnoœc
																			// przycisku
																			// zaloguj

		public void actionPerformed(ActionEvent event) {

			Rejestracja r = new Rejestracja();
			r.setVisible(true);
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
