package serwis;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.util.Date;

public class Klient extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComponent panel1 = makePanel();
	private JComponent panel2 = makePanel();
	private JComponent panel3 = makePanel();
	private JTextField textFieldNumer_seryjjny;
	private JTextField textFieldUrzadzenie;
	private TextArea textAreaOpis_usterki;
	private TextArea textAreaUwagi_klienta;
	private Checkbox checkboxGwarancyjna;
	private Checkbox checkboxPogwarancyjna;
	public Connection conn;
	public Statement statement;

	public static JFrame frame = new JFrame("R&T SERWIS: Klient");
	public Color kolor = new Color(0, 128, 255);

	private static String konto;
	private static String id;

	public Klient(String konto, String id) {
		super(new GridLayout(1, 1));

		Klient.setKonto(konto);
		Klient.setId((id));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = null;

		panel1.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel1.setBackground(kolor);
		tabbedPane.addTab("1.Dodaj urz¹dzenie do zg³oszenia", icon, panel1, "Dodawanie zg³oszenia");
		panel1.setLayout(null);

		panel1.add(Data());

		JLabel lblKonto = new JLabel("Witaj: " + konto);// wyœwietlany text
		lblKonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKonto.setBounds(20, 0, 350, 40);
		lblKonto.setForeground(Color.BLACK);
		panel1.add(lblKonto);

		JLabel lblDodaj_urz¹dzenie = new JLabel("Dodaj urz¹dzenie do serwisu");// wyœwietlany
																				// text
		lblDodaj_urz¹dzenie.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDodaj_urz¹dzenie.setBounds(50, 25, 350, 40);
		lblDodaj_urz¹dzenie.setForeground(Color.BLACK);
		panel1.add(lblDodaj_urz¹dzenie);

		JLabel lblNumer_seryjjny = new JLabel("Numer seryjny lub MAC");// wyœwietlany
																		// text
		lblNumer_seryjjny.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumer_seryjjny.setBounds(50, 70, 150, 40);
		lblNumer_seryjjny.setForeground(Color.BLACK);
		panel1.add(lblNumer_seryjjny);

		textFieldNumer_seryjjny = new JTextField();
		textFieldNumer_seryjjny.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumer_seryjjny.setBounds(210, 80, 150, 25);
		panel1.add(textFieldNumer_seryjjny);
		textFieldNumer_seryjjny.setColumns(20);

		JLabel lblUrzadzenie = new JLabel("Urz¹dzenie");// wyœwietlany text
		lblUrzadzenie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUrzadzenie.setBounds(50, 110, 100, 40);
		lblUrzadzenie.setForeground(Color.BLACK);
		panel1.add(lblUrzadzenie);

		textFieldUrzadzenie = new JTextField();
		textFieldUrzadzenie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUrzadzenie.setBounds(210, 120, 150, 25);
		panel1.add(textFieldUrzadzenie);
		textFieldUrzadzenie.setColumns(20);

		JLabel lblRodzaj_naprawy = new JLabel("Rodzaj naprawy");// wyœwietlany
																// text
		lblRodzaj_naprawy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRodzaj_naprawy.setBounds(50, 150, 100, 40);
		lblRodzaj_naprawy.setForeground(Color.BLACK);
		panel1.add(lblRodzaj_naprawy);

		checkboxGwarancyjna = new Checkbox("Gwarancyjna");
		checkboxGwarancyjna.setBounds(210, 150, 100, 25);
		checkboxGwarancyjna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkboxGwarancyjna.setForeground(Color.BLACK);
		panel1.add(checkboxGwarancyjna);

		checkboxPogwarancyjna = new Checkbox("Pogwarancyjna");
		checkboxPogwarancyjna.setBounds(210, 170, 120, 25);
		checkboxPogwarancyjna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkboxPogwarancyjna.setForeground(Color.BLACK);
		panel1.add(checkboxPogwarancyjna);

		JLabel lblOpis_usterki = new JLabel("Opis usterki");// wyœwietlany text
		lblOpis_usterki.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpis_usterki.setBounds(500, 70, 100, 40);
		lblOpis_usterki.setForeground(Color.BLACK);
		panel1.add(lblOpis_usterki);

		textAreaOpis_usterki = new TextArea();
		textAreaOpis_usterki.setForeground(Color.BLACK);
		textAreaOpis_usterki.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaOpis_usterki.setRows(5);
		textAreaOpis_usterki.setBounds(600, 60, 370, 90);
		panel1.add(textAreaOpis_usterki);

		JLabel lblUwagi_klienta = new JLabel("Uwagi klienta");// wyœwietlany
																// text
		lblUwagi_klienta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUwagi_klienta.setBounds(500, 190, 100, 40);
		lblUwagi_klienta.setForeground(Color.BLACK);
		panel1.add(lblUwagi_klienta);

		textAreaUwagi_klienta = new TextArea();
		textAreaUwagi_klienta.setForeground(Color.BLACK);
		textAreaUwagi_klienta.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaUwagi_klienta.setRows(5);
		textAreaUwagi_klienta.setBounds(600, 170, 370, 90);
		panel1.add(textAreaUwagi_klienta);

		JLabel lblSprawdz = new JLabel("Sprawdz czy urz¹dzenie popsiada gwarancjê (NS lub MAC)");// wyœwietlany
																									// text
		lblSprawdz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSprawdz.setBounds(50, 190, 500, 40);
		lblSprawdz.setForeground(Color.BLACK);
		panel1.add(lblSprawdz);

		JButton btnSprawdz = new JButton("Sprawdz!");// przycisk zaloguj
														// otwieraj¹cy okno
														// danego uzytkownika
														// jezeli dane s¹
														// poprawne
		btnSprawdz.setBounds(50, 250, 89, 25);
		btnSprawdz.setForeground(Color.BLACK);
		btnSprawdz.addActionListener(new PrzyciskListenerSprawdz());
		panel1.add(btnSprawdz);

		JButton btnDodaj = new JButton("Dodaj urz¹dzenie");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnDodaj.setBounds(600, 280, 140, 25);
		btnDodaj.setForeground(Color.BLACK);
		btnDodaj.addActionListener(new PrzyciskListenerDodaj());
		panel1.add(btnDodaj);

		JLabel lblAktualne_zg³oszenia = new JLabel("Aktualne zg³oszenia serwisowe");// wyœwietlany
																					// text
		lblAktualne_zg³oszenia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAktualne_zg³oszenia.setBounds(50, 300, 400, 40);
		lblAktualne_zg³oszenia.setForeground(Color.BLACK);
		panel1.add(lblAktualne_zg³oszenia);

		Aktualne_Zlecenia(id);

		panel1.add(Aktualizuj_Aktualne());

		panel1.add(Wyloguj());

		///////////////////////////////

		///////////////////////////////
		panel2.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel2.setBackground(kolor);
		tabbedPane.addTab("2.Historia zg³oszeñ", icon, panel2, "Podgl¹d historii zg³oszeñ");
		panel2.setLayout(null);

		panel2.add(Data());

		panel2.add(Wyloguj());

		JLabel lblKonto2 = new JLabel("Witaj: " + konto);// wyœwietlany text
		lblKonto2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKonto2.setBounds(20, 0, 350, 40);
		lblKonto2.setForeground(Color.BLACK);
		panel2.add(lblKonto2);

		JLabel lblHistoria_zg³oszen = new JLabel("Historia zg³oszeñ");// wyœwietlany
																		// text
		lblHistoria_zg³oszen.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHistoria_zg³oszen.setBounds(50, 25, 400, 40);
		lblHistoria_zg³oszen.setForeground(Color.BLACK);
		panel2.add(lblHistoria_zg³oszen);

		Aktualizuj_Zakonczone(id);
		////////////////////////////////

		panel2.add(Aktualizuj_Zakonczone());

		///////////////////////////////
		panel3.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel3.setBackground(kolor);
		tabbedPane.addTab("3.Rozliczenia fakrur", icon, panel3, "Rozliczenia");
		panel3.setLayout(null);

		panel3.add(Data());

		JLabel lblKonto3 = new JLabel("Witaj: " + konto);// wyœwietlany text
		lblKonto3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKonto3.setBounds(20, 0, 350, 40);
		lblKonto3.setForeground(Color.BLACK);
		panel3.add(lblKonto3);

		JLabel lblRozliczenia = new JLabel("Rozliczenia nale¿noœci");// wyœwietlany
																		// text
		lblRozliczenia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRozliczenia.setBounds(50, 25, 400, 40);
		lblRozliczenia.setForeground(Color.BLACK);
		panel3.add(lblRozliczenia);

		Aktualizuj_Rozliczone(id);

		panel3.add(Aktualizuj_Rozliczone());

		////////////////////////////////////////////////////////////////////////

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select dk.Nazwa_Firmy, dk.NIP, k.Imie, k.Nazwisko, ak.Ulica, ak.Nr_domu, "
					+ "ak.Miejscowosc, ak.Kod_Pocztowy, k.Telefon, k.Email from klient k, adres_klient ak, "
					+ "dodatkowe_klient dk WHERE k.ID_Dodatkowe_Klient = dk.ID_Dodatkowe_Klient and "
					+ "k.ID_Adres_Klient = ak.ID_Adres_Klient and k.ID_Konto='" + id + "'";// zapytanie
																							// wyci¹gaj¹ce
																							// odp.
																							// dane
																							// z
																							// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			JLabel lblTwoje_dane = new JLabel("Twoje dane:");// wyœwietlany text
			lblTwoje_dane.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTwoje_dane.setBounds(50, 450, 400, 40);
			lblTwoje_dane.setForeground(Color.BLACK);
			panel3.add(lblTwoje_dane);

			while (rs.next()) {

				JLabel lblImie_Firma = new JLabel("Imiê/Firma: " + rs.getString("Imie") + " " + rs.getString("Nazwisko")
						+ " Firma: " + rs.getString("Nazwa_Firmy") + " NIP: " + rs.getString("NIP"));// wyœwietlany
																										// text
				lblImie_Firma.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblImie_Firma.setBounds(50, 480, 450, 40);
				lblImie_Firma.setForeground(Color.BLACK);
				panel3.add(lblImie_Firma);

				JLabel lblUlica = new JLabel("Ulica: " + rs.getString("Ulica") + " " + rs.getString("Nr_domu"));// wyœwietlany
																												// text
				lblUlica.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblUlica.setBounds(50, 510, 400, 40);
				lblUlica.setForeground(Color.BLACK);
				panel3.add(lblUlica);

				JLabel lblMisato = new JLabel("Misato: " + rs.getString("Miejscowosc"));// wyœwietlany
																						// text
				lblMisato.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMisato.setBounds(50, 540, 400, 40);
				lblMisato.setForeground(Color.BLACK);
				panel3.add(lblMisato);

				JLabel lblKod = new JLabel("Kod pocztowy: " + rs.getString("Kod_Pocztowy"));// wyœwietlany
																							// text
				lblKod.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblKod.setBounds(50, 570, 400, 40);
				lblKod.setForeground(Color.BLACK);
				panel3.add(lblKod);

				JLabel lblTelefon = new JLabel("Telefon: " + rs.getString("Telefon"));// wyœwietlany
																						// text
				lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTelefon.setBounds(50, 600, 400, 40);
				lblTelefon.setForeground(Color.BLACK);
				panel3.add(lblTelefon);

				JLabel lblEmail = new JLabel("Email: " + rs.getString("Email"));// wyœwietlany
																				// text
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEmail.setBounds(50, 630, 400, 40);
				lblEmail.setForeground(Color.BLACK);
				panel3.add(lblEmail);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		////////////////////////////////

		panel3.add(Faktura());

		panel3.add(Wyloguj());

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// Uncomment the following line to use scrolling tabs.
		// tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	// do zakomentowania po testach

	// public static void main(String[] args) {
	// //Schedule a job for the event-dispatching thread:
	// //creating and showing this application's GUI.
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// GUI();
	// }
	// });
	// }

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Klient.id = id;
	}

	public static String getKonto() {
		return konto;
	}

	public static void setKonto(String konto) {
		Klient.konto = konto;
	}

	protected JComponent makePanel() {
		JPanel panel = new JPanel(false);

		panel.setLayout(new GridLayout(1, 1));

		return panel;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void GUI() {

		// Create and set up the window.
		// JFrame frame = new JFrame("R&T SERWIS: Kierownik");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		frame.setBounds(0, 0, 1024, 734);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// Create and set up the content pane.
		JComponent newContentPane = new Klient(getKonto(), getId());
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.getContentPane().add(new Klient(getKonto(), getId()), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public JLabel Data() {

		Date data1 = new Date();// pole data
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String dataS = format1.format(data1);
		JLabel data11 = new JLabel(dataS);
		data11.setBounds(950, 5, 161, 36);
		data11.setForeground(Color.BLACK);
		return data11;

	}

	public JButton Aktualizuj_Aktualne() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 640, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujAktualne());
		return btnAktualizuj;

	}

	public JButton Aktualizuj_Zakonczone() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 600, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujZakonczone());
		return btnAktualizuj;

	}

	public JButton Aktualizuj_Rozliczone() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 420, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujRozliczone());
		return btnAktualizuj;
	}
	
	public JButton Faktura() {

		JButton btnFaktura = new JButton("Faktura");// przycisk zaloguj
													// otwieraj¹cy okno danego
													// uzytkownika jezeli dane
													// s¹ poprawne
		btnFaktura.setBounds(880, 420, 89, 25);
		btnFaktura.setForeground(Color.BLACK);
		btnFaktura.addActionListener(new PrzyciskListenerFaktura());
		return btnFaktura;

	}

	public JButton Wyloguj() {

		JButton btnWyloguj = new JButton("Wyloguj");// przycisk zaloguj
													// otwieraj¹cy okno danego
													// uzytkownika jezeli dane
													// s¹ poprawne
		btnWyloguj.setBounds(935, 650, 89, 25);
		btnWyloguj.setForeground(Color.BLACK);
		btnWyloguj.addActionListener(new PrzyciskListenerWyloguj());
		return btnWyloguj;

	}

	public void Aktualne_Zlecenia(String id_klient) {

		JScrollPane scrollPaneAktualne_zg³oszenia = new JScrollPane();// tworzenie
																		// scrolpanelu
																		// dla
		// tabeli
		scrollPaneAktualne_zg³oszenia.setBounds(50, 350, 920, 280);
		panel1.add(scrollPaneAktualne_zg³oszenia);

		DefaultTableModel modeloAktualne_zg³oszenia = new DefaultTableModel();// tworzenie
																				// tabeli
		// do wypisywania
		// wyników
		JTable tableAktualne_zg³oszenia = new JTable(modeloAktualne_zg³oszenia);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select z.ID_Zlecenie, z.Nazwa_Urzadzenia, z.MAC, z.Opis, z.Uwagi, z.Status, "
					+ "z.Rodzaj_Naprawy, zd.ID_Klient from zlecenie z, zlecenie_dodatkowe zd, konto k, "
					+ "klient kl where z.ID_Zlecenie_Dodatkowe = zd.ID_Zlecenie_Dodatkowe and "
					+ "k.ID_Konto = kl.ID_Konto and zd.ID_Klient = kl.ID_Klient and "
					+ "k.ID_Konto ='" + id_klient + "'";// zapytanie
																													// wyci¹gaj¹ce
																													// odp.
																													// dane
																													// z
																													// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			try {

				Object[][] wierszeAktualne_zg³oszenia = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 0 || rs.getInt("Status") == 1) {
						wierszeAktualne_zg³oszenia[i++] = new Object[] { rs.getString("ID_Zlecenie"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"), rs.getString("Opis"),
								rs.getString("Uwagi"), rs.getString("Rodzaj_Naprawy"), rs.getString("Status") };
					}
				}

				tableAktualne_zg³oszenia.setModel(new DefaultTableModel(wierszeAktualne_zg³oszenia,
						new String[] { "RMA", "Sprzêt", "MAC", "Opis usterki", "Uwagi", "Rodzaj naprawy", "Status" }));

				////////////////////////

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableAktualne_zg³oszenia.getColumnModel().getColumn(0).setPreferredWidth(35);// ustawienia
																						// kolumn
																						// tabeli
		tableAktualne_zg³oszenia.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableAktualne_zg³oszenia.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableAktualne_zg³oszenia.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableAktualne_zg³oszenia.getColumnModel().getColumn(4).setPreferredWidth(10);

		scrollPaneAktualne_zg³oszenia.setViewportView(tableAktualne_zg³oszenia);

	}

	public void Dodaj_Zlecenie() {

		String numer_seryjny = textFieldNumer_seryjjny.getText();
		String urzadzenie = textFieldUrzadzenie.getText();
		String opis_usterki = textAreaOpis_usterki.getText();
		String uwagi_klienta = textAreaUwagi_klienta.getText();

		boolean gwarancja = checkboxGwarancyjna.getState();
		boolean po_gwarancji = checkboxPogwarancyjna.getState();

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {

			String id_klient = "select kl.ID_Klient, max(zd.ID_Zlecenie_Dodatkowe)+1 as numer from konto k, "
					+ "klient kl, zlecenie_dodatkowe zd where k.ID_Konto = kl.ID_Konto and " + "k.ID_Konto =" + id + "";// zapytanie

			// String id_zleccenied = "select max(ID_Zlecenie_Dodatkowe) as
			// numer from zlecenie_dodatkowe";// zapytanie
			// bazy
			// bazy
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(id_klient);
			// ResultSet rs2 = statement.executeQuery(id_zleccenied);

			while (rs.next()) {
				// while (rs2.next()){

				if (numer_seryjny != null && urzadzenie != null && opis_usterki != null && uwagi_klienta != null) {

					if (gwarancja == true && po_gwarancji == false) {

						String klient = rs.getString("ID_Klient");
						int numerZ = rs.getInt("numer");
						// naprawa gwarancyjna
						// System.out.println(klient);

						String zlecenie_gw_dod = "insert into zlecenie_dodatkowe (ID_Klient) values('" + klient + "')";

						statement.executeUpdate(zlecenie_gw_dod);

						// dzia³a

						String zlecenie_gw = "insert into zlecenie (MAC, Nazwa_Urzadzenia, Opis, Uwagi, "
								+ "Rodzaj_Naprawy, Status, ID_Zlecenie_Dodatkowe) values('" + numer_seryjny + "','"
								+ urzadzenie + "','" + opis_usterki + "','" + uwagi_klienta
								+ "','1','0','" + numerZ + "')";

						statement.executeUpdate(zlecenie_gw);

					} else if (gwarancja == false && po_gwarancji == true) {

						// naprawa pogwarancyjna

						String klient = rs.getString("ID_Klient");
						int numerZ = rs.getInt("numer");

						// System.out.println(klient);

						String zlecenie_pogw_dod = "insert into zlecenie_dodatkowe (ID_Klient) values('" + klient
								+ "')";

						statement.executeUpdate(zlecenie_pogw_dod);

						// dzia³a

						String zlecenie_gw = "insert into zlecenie (MAC, Nazwa_Urzadzenia, Opis, Uwagi, "
								+ "Rodzaj_Naprawy, Status, ID_Zlecenie_Dodatkowe) values('" + numer_seryjny + "','"
								+ urzadzenie + "','" + opis_usterki + "','" + uwagi_klienta
								+ "','0','0','" + numerZ + "')";

						statement.executeUpdate(zlecenie_gw);

					} else {
						new Info();
					}

				} else {
					new Info();

				}
			}
			// }
			rs.close();

		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public void Aktualizuj_Zakonczone(String id_klient) {

		JScrollPane scrollPaneHistoria = new JScrollPane();// tworzenie
															// scrolpanelu dla
		// tabeli
		scrollPaneHistoria.setBounds(50, 80, 920, 500);
		panel2.add(scrollPaneHistoria);

		DefaultTableModel modeloHistoria = new DefaultTableModel();// tworzenie
																	// tabeli
		// do wypisywania
		// wyników
		JTable tableHistoria = new JTable(modeloHistoria);

		// wyswietlanie kolejnych rekordow:

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select z.ID_Zlecenie, z.Nazwa_Urzadzenia, z.MAC, z.Opis, "
					+ "z.Uwagi, z.Rodzaj_Naprawy, s.Status, s.Uwagi_Serwisanta, s.Koszt, zd.ID_Klient from zlecenie z, "
					+ "zlecenie_dodatkowe zd, serwis s where z.ID_Zlecenie_Dodatkowe = zd.ID_Zlecenie_Dodatkowe "
					+ "and s.ID_Serwis = z.ID_Zlecenie_Dodatkowe and zd.ID_Klient='" + id_klient + "'";// zapytanie
																										// wyci¹gaj¹ce
																										// odp.
																										// dane
																										// z
																										// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			try {

				Object[][] wierszeHistoria = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 2) {
						wierszeHistoria[i++] = new Object[] { rs.getString("ID_Zlecenie"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"), rs.getString("Opis"),
								rs.getString("Uwagi"), rs.getString("Uwagi_Serwisanta"), rs.getString("Rodzaj_Naprawy"),
								rs.getString("Koszt") };
					}
				}

				tableHistoria.setModel(new DefaultTableModel(wierszeHistoria, new String[] { "RMA", "Sprzêt", "MAC",
						"Opis usterki", "Uwagi", "Uwagi serwisanta", "Rodzaj naprawy", "Koszt" }));

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
		scrollPaneHistoria.setViewportView(tableHistoria);

	}

	public void Aktualizuj_Rozliczone(String id_klient) {

		JScrollPane scrollPaneRozliczenia = new JScrollPane();// tworzenie
																// scrolpanelu
																// dla
		// tabeli
		scrollPaneRozliczenia.setBounds(50, 80, 920, 320);
		panel3.add(scrollPaneRozliczenia);

		DefaultTableModel modeloRozliczenia = new DefaultTableModel();// tworzenie
																		// tabeli
		// do wypisywania
		// wyników
		JTable tableRozliczenia = new JTable(modeloRozliczenia);

		// wyswietlanie kolejnych rekordow:

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select f.ID_Faktura, f.Data_Wystawienia, f.Termin_Platnosci, "
					+ "f.Kwota_Netto, f.Czy_Zaplacone from faktura  f join faktura_dodatkowe fd on "
					+ "f.ID_Faktura_Dodatkowe = fd.ID_Faktura_Dodatkowe " + "where fd.ID_Klient ='" + id_klient + "'";// zapytanie
																														// wyci¹gaj¹ce
																														// odp.
																														// dane
																														// z
																														// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			try {

				Object[][] wierszeRozliczenia = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;

				// double iloczyn = rs.getInt("Kwota_Netto") * 1.23;
				// String iloczynS = String.valueOf(iloczyn);

				while (rs.next()) {
					if (rs.getInt("ID_Faktura") > 0) {
						wierszeRozliczenia[i++] = new Object[] { "FS/" + rs.getString("ID_Faktura") + "/2018",
								rs.getString("Data_Wystawienia"), rs.getString("Termin_Platnosci"),
								rs.getString("Kwota_Netto"), rs.getString("Czy_Zaplacone") };
					}
				}

				tableRozliczenia.setModel(new DefaultTableModel(wierszeRozliczenia,
						new String[] { "Dokument", "Data powstania", "Termin p³atnoœci", "Kwota netto", "Status" }));

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
		scrollPaneRozliczenia.setViewportView(tableRozliczenia);
	}

	public void Sprawdz_Sprzet(JTextField mac) {

		String mac_tekst = mac.getText();

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																						// z
																						// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select MAC, Data_Konca_Gwarancji from sprzet";// zapytanie
																				// wyci¹gaj¹ce
																				// odp.
																				// dane
																				// z
																				// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs.next()) {

				if (mac_tekst.equals(rs.getString("MAC"))) {

					// System.out.println("Urz¹dzenie jest na gwarancji do " +
					// rs.getString("Data_Konca_Gwarancji"));

					new Sprawdz(rs.getString("Data_Konca_Gwarancji"));
					break;
				}

				// else if(mac_tekst !=(rs.getString("MAC"))){
				//
				// new Info();
				// continue;
				// }

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

	}

	private class PrzyciskListenerAktualizujAktualne implements ActionListener {// klasa
																				// realizuj¹ca
																				// funkcjonalnoœc
																				// przycisku
																				// zaloguj

		public void actionPerformed(ActionEvent event) {

			Aktualne_Zlecenia(id);

		}
	}

	private class PrzyciskListenerAktualizujZakonczone implements ActionListener {// klasa
																					// realizuj¹ca
																					// funkcjonalnoœc
																					// przycisku
																					// zaloguj

		public void actionPerformed(ActionEvent event) {

			Aktualizuj_Zakonczone(id);

		}
	}

	private class PrzyciskListenerAktualizujRozliczone implements ActionListener {// klasa
																					// realizuj¹ca
																					// funkcjonalnoœc
																					// przycisku
																					// zaloguj

		public void actionPerformed(ActionEvent event) {

			Aktualizuj_Rozliczone(id);

		}
	}

	private class PrzyciskListenerFaktura implements ActionListener {// klasa
		// realizuj¹ca
		// funkcjonalnoœc
		// przycisku
		// zaloguj

		public void actionPerformed(ActionEvent event) {
			
			Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
			// po³¹czenie z baz¹
			baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

			try {
				// System.out.println(id_klient);
				String queryString = "select max(f.ID_Faktura) as numer FROM klient k, konto ko, "
						+ "faktura f, faktura_dodatkowe fd where f.ID_Faktura_Dodatkowe = "
						+ "fd.ID_Faktura_Dodatkowe and fd.ID_Klient = k.ID_Klient and k.ID_Konto = "
						+ "ko.ID_Konto and ko.ID_Konto = '" + id + "'";
				
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(queryString);// wykonanie
																	// zapytania			
				while (rs.next()) {
					
					if(rs.getInt("numer") > 0){
			
			Faktura f = new Faktura(id);
			f.setVisible(true);
			
					}
				
				else{
					
					new Info();
					}
				}

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

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
			frame.setVisible(false);
			setVisible(false);

		}
	}

	private class PrzyciskListenerSprawdz implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Sprawdz_Sprzet(textFieldNumer_seryjjny);

		}
	}

	private class PrzyciskListenerDodaj implements ActionListener {// klasa
																	// realizuj¹ca
																	// funkcjonalnoœc
																	// przycisku
																	// zaloguj

		public void actionPerformed(ActionEvent event) {

			Dodaj_Zlecenie();

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
