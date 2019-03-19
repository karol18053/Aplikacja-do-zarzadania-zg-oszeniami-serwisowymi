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

public class Kierownik extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComponent panel1 = makePanel();
	private JComponent panel2 = makePanel();
	private JComponent panel3 = makePanel();
	private JComponent panel4 = makePanel();
	private TextArea textAreaDodatkowe;
	private JTextField textFieldRMA;
	private JTextField textFieldID;
	private JTextField textFieldNr_pracownikaZ;
	private JTextField textFieldNr_pracownikaU;
	private JTextField textFieldImie;
	private JTextField textFieldNazwisko;
	private JTextField textFieldUlica;
	private JTextField textFieldNr_domu;
	private JTextField textFieldKod_pocztowy;
	private JTextField textFieldMiasto;
	private JTextField textFieldPESEL;
	private JTextField textFieldTelefon;
	private JTextField textFieldEmail;
	private JTextField textFieldSpecjalizacja;
	public Connection conn;
	public Statement statement;

	public static JFrame frame = new JFrame("R&T SERWIS: Kierownik");
	public Color kolor = new Color(0, 128, 255);

	public Kierownik() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = null;

		panel1.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel1.setBackground(kolor);
		tabbedPane.addTab("1.Przydzia³ serwisów", icon, panel1, "Pozwala dopasowaæ serwis do pracownika");
		panel1.setLayout(null);

		panel1.add(Data());

		JLabel lblAktualne = new JLabel("Nowe serwisy");// wyœwietlany text
		lblAktualne.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAktualne.setBounds(50, 10, 150, 40);
		lblAktualne.setForeground(Color.BLACK);
		panel1.add(lblAktualne);

		Nowe_Serwisy();

		panel1.add(Aktualizuj());

		JLabel lblPracownicy = new JLabel("Pracownicy");// wyœwietlany text
		lblPracownicy.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPracownicy.setBounds(50, 300, 150, 40);
		lblPracownicy.setForeground(Color.BLACK);
		panel1.add(lblPracownicy);

		Lista_Pracownikow();

		panel1.add(Aktualizuj_Pracownicy());

		JLabel lblPrzydziel = new JLabel("Przydziel serwis do pracownika");// wyœwietlany
																			// text
		lblPrzydziel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPrzydziel.setBounds(600, 300, 400, 40);
		lblPrzydziel.setForeground(Color.BLACK);
		panel1.add(lblPrzydziel);

		JLabel lblRMA = new JLabel("RMA");// wyœwietlany text
		lblRMA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRMA.setBounds(600, 350, 150, 40);
		lblRMA.setForeground(Color.BLACK);
		panel1.add(lblRMA);

		textFieldRMA = new JTextField();
		textFieldRMA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRMA.setBounds(700, 360, 150, 25);
		panel1.add(textFieldRMA);
		textFieldRMA.setColumns(20);

		JLabel lblID = new JLabel("ID Pracownika");// wyœwietlany text
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(600, 400, 150, 40);
		lblID.setForeground(Color.BLACK);
		panel1.add(lblID);

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldID.setBounds(700, 410, 150, 25);
		panel1.add(textFieldID);
		textFieldID.setColumns(20);

		panel1.add(Przydziel());

		panel1.add(Wyloguj());

		///////////////////////////////
		panel2.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel2.setBackground(kolor);
		tabbedPane.addTab("2.Aktualne serwisy", icon, panel2, "Pokazuje bie¿¹ce serwisy");
		panel2.setLayout(null);

		panel2.add(Data());

		JLabel lblAktualne_serwisy = new JLabel("Aktualne serwisy");// wyœwietlany
																	// text
		lblAktualne_serwisy.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAktualne_serwisy.setBounds(50, 10, 350, 40);
		lblAktualne_serwisy.setForeground(Color.BLACK);
		panel2.add(lblAktualne_serwisy);

		Aktualne_Serwisy();

		panel2.add(Aktualizuj_Aktualne());

		panel2.add(Wyloguj());

		///////////////////////////////
		panel3.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel3.setBackground(kolor);
		tabbedPane.addTab("3.Zakoñczone serwisy", icon, panel3, "Pokazuje listê zrobionych serwisów");
		panel3.setLayout(null);

		panel3.add(Data());

		JLabel lblZakonczone_serwisy = new JLabel("Zakoñczone serwisy");// wyœwietlany
																		// text
		lblZakonczone_serwisy.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblZakonczone_serwisy.setBounds(50, 10, 350, 40);
		lblZakonczone_serwisy.setForeground(Color.BLACK);
		panel3.add(lblZakonczone_serwisy);

		Zakoñczone_Serwisy();

		panel3.add(Aktualizuj_Zakonczone());

		panel3.add(Wyloguj());

		////////////////////////////////
		panel4.setPreferredSize(new Dimension(1024, 674));// zak³adka 1
		panel4.setBackground(kolor);
		tabbedPane.addTab("4.Modyfikacja pracowników", icon, panel4,
				"Pozwala na dodawanie / usówanie / zawieszanie pracowników");
		panel4.setLayout(null);

		panel4.add(Data());

		JLabel lblLista_pracownikow = new JLabel("Lista pracowników");// wyœwietlany
																		// text
		lblLista_pracownikow.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLista_pracownikow.setBounds(50, 10, 350, 40);
		lblLista_pracownikow.setForeground(Color.BLACK);
		panel4.add(lblLista_pracownikow);

		Lista_Pracownikow2();

		panel4.add(Aktualizuj_Pracownicy2());

		JLabel lblZawies_pracownika = new JLabel("Pracownicy na L4");// wyœwietlany
																		// text
		lblZawies_pracownika.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblZawies_pracownika.setBounds(630, 50, 350, 40);
		lblZawies_pracownika.setForeground(Color.BLACK);
		panel4.add(lblZawies_pracownika);

		JLabel lblNumer_pracownikaZ = new JLabel("Numer pracownika");// wyœwietlany
																		// text
		lblNumer_pracownikaZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumer_pracownikaZ.setBounds(630, 90, 350, 40);
		lblNumer_pracownikaZ.setForeground(Color.BLACK);
		panel4.add(lblNumer_pracownikaZ);

		textFieldNr_pracownikaZ = new JTextField();
		textFieldNr_pracownikaZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNr_pracownikaZ.setBounds(760, 100, 150, 25);
		textFieldNr_pracownikaZ.setColumns(20);
		panel4.add(textFieldNr_pracownikaZ);

		panel4.add(Zawies());

		panel4.add(Przywroc());

		JLabel lblUsun_pracownika = new JLabel("Usuñ pracownika");// wyœwietlany
																	// text
		lblUsun_pracownika.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsun_pracownika.setBounds(630, 180, 350, 40);
		lblUsun_pracownika.setForeground(Color.BLACK);
		panel4.add(lblUsun_pracownika);

		JLabel lblNumer_pracownikaU = new JLabel("Numer pracownika");// wyœwietlany
																		// text
		lblNumer_pracownikaU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumer_pracownikaU.setBounds(630, 220, 350, 40);
		lblNumer_pracownikaU.setForeground(Color.BLACK);
		panel4.add(lblNumer_pracownikaU);

		panel4.add(Usun());

		textFieldNr_pracownikaU = new JTextField();
		textFieldNr_pracownikaU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNr_pracownikaU.setBounds(760, 230, 150, 25);
		textFieldNr_pracownikaU.setColumns(20);
		panel4.add(textFieldNr_pracownikaU);

		JLabel lblDodawanie_pracownika = new JLabel("Dodawanie pracownika: podaj potrzebne dane");// wyœwietlany
																									// text
		lblDodawanie_pracownika.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDodawanie_pracownika.setBounds(50, 310, 500, 40);
		lblDodawanie_pracownika.setForeground(Color.BLACK);
		panel4.add(lblDodawanie_pracownika);

		JLabel lblImie = new JLabel("Imiê");// wyœwietlany text
		lblImie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImie.setBounds(50, 370, 50, 40);
		lblImie.setForeground(Color.BLACK);
		panel4.add(lblImie);

		textFieldImie = new JTextField();
		textFieldImie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldImie.setBounds(150, 380, 150, 25);
		textFieldImie.setColumns(20);
		panel4.add(textFieldImie);

		JLabel lblNazwisko = new JLabel("Nazwisko");// wyœwietlany text
		lblNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNazwisko.setBounds(50, 400, 80, 40);
		lblNazwisko.setForeground(Color.BLACK);
		panel4.add(lblNazwisko);

		textFieldNazwisko = new JTextField();
		textFieldNazwisko.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNazwisko.setBounds(150, 410, 150, 25);
		textFieldNazwisko.setColumns(20);
		panel4.add(textFieldNazwisko);

		JLabel lblUlica = new JLabel("Ulica");// wyœwietlany text
		lblUlica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUlica.setBounds(50, 430, 50, 40);
		lblUlica.setForeground(Color.BLACK);
		panel4.add(lblUlica);

		textFieldUlica = new JTextField();
		textFieldUlica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUlica.setBounds(150, 440, 150, 25);
		textFieldUlica.setColumns(20);
		panel4.add(textFieldUlica);

		JLabel lblNr_domu = new JLabel("Nr. domu");// wyœwietlany text
		lblNr_domu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNr_domu.setBounds(50, 460, 80, 40);
		lblNr_domu.setForeground(Color.BLACK);
		panel4.add(lblNr_domu);

		textFieldNr_domu = new JTextField();
		textFieldNr_domu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNr_domu.setBounds(150, 470, 150, 25);
		textFieldNr_domu.setColumns(20);
		panel4.add(textFieldNr_domu);

		JLabel lblKod_pocztowy = new JLabel("Kod pocztowy");// wyœwietlany text
		lblKod_pocztowy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKod_pocztowy.setBounds(50, 490, 100, 40);
		lblKod_pocztowy.setForeground(Color.BLACK);
		panel4.add(lblKod_pocztowy);

		textFieldKod_pocztowy = new JTextField();
		textFieldKod_pocztowy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldKod_pocztowy.setBounds(150, 500, 150, 25);
		textFieldKod_pocztowy.setColumns(20);
		panel4.add(textFieldKod_pocztowy);

		JLabel lblMiasto = new JLabel("Miasto");// wyœwietlany text
		lblMiasto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiasto.setBounds(50, 520, 50, 40);
		lblMiasto.setForeground(Color.BLACK);
		panel4.add(lblMiasto);

		textFieldMiasto = new JTextField();
		textFieldMiasto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMiasto.setBounds(150, 530, 150, 25);
		textFieldMiasto.setColumns(20);
		panel4.add(textFieldMiasto);

		JLabel lblPESEL = new JLabel("PESEL");// wyœwietlany text
		lblPESEL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPESEL.setBounds(500, 370, 50, 40);
		lblPESEL.setForeground(Color.BLACK);
		panel4.add(lblPESEL);

		textFieldPESEL = new JTextField();
		textFieldPESEL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPESEL.setBounds(650, 380, 150, 25);
		textFieldPESEL.setColumns(20);
		panel4.add(textFieldPESEL);

		JLabel lblTelefon = new JLabel("Telefon");// wyœwietlany text
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefon.setBounds(500, 400, 50, 40);
		lblTelefon.setForeground(Color.BLACK);
		panel4.add(lblTelefon);

		textFieldTelefon = new JTextField();
		textFieldTelefon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTelefon.setBounds(650, 410, 150, 25);
		textFieldTelefon.setColumns(20);
		panel4.add(textFieldTelefon);

		JLabel lblEmail = new JLabel("Email");// wyœwietlany text
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(500, 430, 50, 40);
		lblEmail.setForeground(Color.BLACK);
		panel4.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEmail.setBounds(650, 440, 150, 25);
		textFieldEmail.setColumns(20);
		panel4.add(textFieldEmail);

		JLabel lblSpecjalizacja = new JLabel("Specjalizacja");// wyœwietlany
																// text
		lblSpecjalizacja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecjalizacja.setBounds(500, 460, 80, 40);
		lblSpecjalizacja.setForeground(Color.BLACK);
		panel4.add(lblSpecjalizacja);

		textFieldSpecjalizacja = new JTextField();
		textFieldSpecjalizacja.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSpecjalizacja.setBounds(650, 470, 150, 25);
		textFieldSpecjalizacja.setColumns(20);
		panel4.add(textFieldSpecjalizacja);

		JLabel lblDodatkowe = new JLabel("Dodatkowe informacje");// wyœwietlany
																	// text
		lblDodatkowe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDodatkowe.setBounds(500, 490, 140, 40);
		lblDodatkowe.setForeground(Color.BLACK);
		panel4.add(lblDodatkowe);

		textAreaDodatkowe = new TextArea();
		textAreaDodatkowe.setForeground(Color.BLACK);
		textAreaDodatkowe.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaDodatkowe.setRows(5);
		textAreaDodatkowe.setBounds(650, 500, 280, 120);
		panel4.add(textAreaDodatkowe);

		panel4.add(Dodaj_pracownika());

		panel4.add(Wyloguj());

		////////////////////////////////

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// Uncomment the following line to use scrolling tabs.
		// tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	// public static void main(String[] args) {
	// //Schedule a job for the event-dispatching thread:
	// //creating and showing this application's GUI.
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// GUI();
	// }
	// });
	// }

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
		// Make sure we have nice window decorations.
		// JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		// JFrame frame = new JFrame("R&T SERWIS: Kierownik");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		frame.setBounds(0, 0, 1024, 734);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// Create and set up the content pane.
		JComponent newContentPane = new Kierownik();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.getContentPane().add(new Kierownik(), BorderLayout.CENTER);

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

	public JButton Aktualizuj() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 270, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizuj());
		return btnAktualizuj;

	}

	public JButton Aktualizuj_Pracownicy() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 560, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizuj_Pracownicy());
		return btnAktualizuj;

	}

	public JButton Aktualizuj_Aktualne() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 500, 89, 25);
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
		btnAktualizuj.setBounds(50, 500, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizujZakonczone());
		return btnAktualizuj;

	}

	public JButton Przydziel() {

		JButton btnPrzydzielj = new JButton("Przydziel");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnPrzydzielj.setBounds(700, 450, 90, 25);
		btnPrzydzielj.setForeground(Color.BLACK);
		btnPrzydzielj.addActionListener(new PrzyciskListenerPrzydziel());
		return btnPrzydzielj;

	}

	public JButton Aktualizuj_Pracownicy2() {

		JButton btnAktualizuj = new JButton("Aktualizuj");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnAktualizuj.setBounds(50, 270, 89, 25);
		btnAktualizuj.setForeground(Color.BLACK);
		btnAktualizuj.addActionListener(new PrzyciskListenerAktualizuj_Pracownicy2());
		return btnAktualizuj;

	}

	public JButton Zawies() {

		JButton btnZawies = new JButton("Zawieœ pracownika");// przycisk zaloguj
																// otwieraj¹cy
																// okno danego
																// uzytkownika
																// jezeli dane
																// s¹ poprawne
		btnZawies.setBounds(610, 140, 150, 25);
		btnZawies.setForeground(Color.BLACK);
		btnZawies.addActionListener(new PrzyciskListenerZawies());
		return btnZawies;

	}

	public JButton Przywroc() {

		JButton btnZawies = new JButton("Przywroæ pracownika");// przycisk
																// zaloguj
																// otwieraj¹cy
																// okno danego
																// uzytkownika
																// jezeli dane
																// s¹ poprawne
		btnZawies.setBounds(760, 140, 160, 25);
		btnZawies.setForeground(Color.BLACK);
		btnZawies.addActionListener(new PrzyciskListenerPrzywroc());
		return btnZawies;

	}

	public JButton Usun() {

		JButton btnUsun = new JButton("Usuñ pracownika");// przycisk zaloguj
															// otwieraj¹cy okno
															// danego
															// uzytkownika
															// jezeli dane s¹
															// poprawne
		btnUsun.setBounds(760, 270, 150, 25);
		btnUsun.setForeground(Color.BLACK);
		btnUsun.addActionListener(new PrzyciskListenerUsun());
		return btnUsun;

	}

	public JButton Dodaj_pracownika() {

		JButton Dodaj_pracownika = new JButton("Dodaj pracownika");// przycisk
																	// zaloguj
																	// otwieraj¹cy
																	// okno
																	// danego
																	// uzytkownika
																	// jezeli
																	// dane s¹
																	// poprawne
		Dodaj_pracownika.setBounds(50, 600, 150, 25);
		Dodaj_pracownika.setForeground(Color.BLACK);
		Dodaj_pracownika.addActionListener(new PrzyciskListenerDodaj_pracownika());
		return Dodaj_pracownika;

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

	public void Lista_Pracownikow() {

		JScrollPane scrollPanePracownicy = new JScrollPane();// tworzenie
		// scrolpanelu
		// dla
		// tabeli
		scrollPanePracownicy.setBounds(50, 350, 520, 200);
		panel1.add(scrollPanePracownicy);

		DefaultTableModel modeloPracownicy = new DefaultTableModel();// tworzenie
		// tabeli
		// do wypisywania
		// wyników
		JTable tablePracownicy = new JTable(modeloPracownicy);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select p.ID_Pracownik, p.Imie, p.Nazwisko, p.Status, dp.Specjalizacja "
					+ "from pracownik p, dodatkowe_pracownik dp WHERE p.ID_Dodatkowe_Pracownik = "
					+ "dp.ID_Dodatkowe_Pracownik and p.Status = 1";// zapytanie
																	// wyci¹gaj¹ce
																	// odp.
																	// dane
																	// z
																	// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszePracownicy = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 1) {
						wierszePracownicy[i++] = new Object[] { rs.getString("ID_Pracownik"), rs.getString("Imie"),
								rs.getString("Nazwisko"), rs.getString("Specjalizacja") };
					}
				}

				tablePracownicy.setModel(new DefaultTableModel(wierszePracownicy,
						new String[] { "ID_Pracownika", "Imie", "Nazwisko", "Specjalizacja" }));

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tablePracownicy.getColumnModel().getColumn(0).setPreferredWidth(15);// ustawienia
		// kolumn
		// tabeli
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(1).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(2).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(3).setPreferredWidth(90);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(4).setPreferredWidth(10);

		scrollPanePracownicy.setViewportView(tablePracownicy);
	}

	public void Lista_Pracownikow2() {

		JScrollPane scrollPanePracownicy2 = new JScrollPane();// tworzenie
		// scrolpanelu
		// dla
		// tabeli
		scrollPanePracownicy2.setBounds(50, 60, 520, 200);
		panel4.add(scrollPanePracownicy2);

		DefaultTableModel modeloPracownicy2 = new DefaultTableModel();// tworzenie
		// tabeli
		// do wypisywania
		// wyników
		JTable tablePracownicy2 = new JTable(modeloPracownicy2);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select p.ID_Pracownik, p.Imie, p.Nazwisko, p.Status, dp.Specjalizacja from pracownik p, "
					+ "dodatkowe_pracownik dp WHERE p.ID_Dodatkowe_Pracownik = dp.ID_Dodatkowe_Pracownik "
					+ "and (p.Status = 0 or p.Status = 1)";// zapytanie
															// wyci¹gaj¹ce
															// odp.
															// dane
															// z
															// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszePracownicy2 = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 1) {
						wierszePracownicy2[i++] = new Object[] { rs.getString("ID_Pracownik"), rs.getString("Imie"),
								rs.getString("Nazwisko"), rs.getString("Specjalizacja"), rs.getString("Status") };
					}
				}

				tablePracownicy2.setModel(new DefaultTableModel(wierszePracownicy2,
						new String[] { "ID_Pracownika", "Imie", "Nazwisko", "Specjalizacja", "Status" }));

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tablePracownicy2.getColumnModel().getColumn(0).setPreferredWidth(20);// ustawienia
		// kolumn
		// tabeli
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(1).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(2).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(3).setPreferredWidth(90);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(4).setPreferredWidth(10);

		scrollPanePracownicy2.setViewportView(tablePracownicy2);
	}

	public void Nowe_Serwisy() {

		JScrollPane scrollPaneAktualne = new JScrollPane();// tworzenie
															// scrolpanelu dla
		// tabeli
		scrollPaneAktualne.setBounds(50, 60, 920, 200);
		panel1.add(scrollPaneAktualne);

		DefaultTableModel modeloAktualne = new DefaultTableModel();// tworzenie
																	// tabeli
		// do wypisywania
		// wyników
		JTable tableAktualne = new JTable(modeloAktualne);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "SELECT ID_Zlecenie, Nazwa_Urzadzenia, MAC, Opis, Uwagi, Rodzaj_Naprawy, "
					+ "Status from zlecenie WHERE Status = 0";// zapytanie
																// wyci¹gaj¹ce
																// odp.
																// dane
																// z
																// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszeAktualne = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 0) {
						wierszeAktualne[i++] = new Object[] { rs.getString("ID_Zlecenie"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"), rs.getString("Opis"),
								rs.getString("Uwagi"), rs.getString("Rodzaj_Naprawy") };
					}
				}

				tableAktualne.setModel(new DefaultTableModel(wierszeAktualne,
						new String[] { "RMA", "Sprzêt", "MAC", "Opis usterki", "Uwagi", "Rodzaj naprawy" }));

				////////////////////////

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableAktualne.getColumnModel().getColumn(0).setPreferredWidth(35);// ustawienia
		// kolumn
		// tabeli
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(1).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(2).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(3).setPreferredWidth(90);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(4).setPreferredWidth(10);

		scrollPaneAktualne.setViewportView(tableAktualne);
	}

	public void Przydziel_Pracownika() {

		int IDPracownika = Integer.parseInt(textFieldID.getText());
		int IDZlecenie = Integer.parseInt(textFieldRMA.getText());

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {
			statement = conn.createStatement();

			String queryString2 = "select z.ID_Zlecenie, z.Status, p.ID_Pracownik from zlecenie z, pracownik p";

			ResultSet rs2 = statement.executeQuery(queryString2);

			while (rs2.next()) {

				if (IDPracownika > 0 && rs2.getInt("ID_Pracownik") == IDPracownika && IDZlecenie > 0
						&& rs2.getInt("ID_Zlecenie") == IDZlecenie && rs2.getInt("Status") == 0) {

					String Przydziel_zlecenie = "update zlecenie set Status=1 where Status=0 and ID_Zlecenie ='"
					+ IDZlecenie + "'";

					statement.executeUpdate(Przydziel_zlecenie);

					String Przydziel_serwis_dod = "INSERT INTO serwis_dodatkowe (ID_Pracownik, ID_Zlecenie) VALUES "
							+ "('" + IDPracownika + "','" + IDZlecenie + "')";

					statement.executeUpdate(Przydziel_serwis_dod);

				} else {

					//new Info();

				}

			}
			rs2.close();

		} catch (SQLException e) {

			//e.printStackTrace();
		
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych

		}

		//////////////////////////////////////////////////////////////////////////////

		try {
			statement = conn.createStatement();

			String queryString = "select max(sd.ID_Serwis_Dodatkowe) as serwis_dod from serwis_dodatkowe sd";

			ResultSet rs = statement.executeQuery(queryString);

			// select max(sd.ID_Serwis_Dodatkowe) as serwis_dod, z.ID_Zlecenie,
			// z.Status, p.ID_Pracownik from zlecenie z,
			// pracownik p, serwis_dodatkowe sd where z.Status =0

			while (rs.next()) {
				
				int numer_ser_dod = rs.getInt("serwis_dod");

					String Przydziel_serwis = "insert into serwis (Koszt, Status, ID_Serwis_Dodatkowe) values "
							+ "('0','1','1')";

					statement.executeUpdate(Przydziel_serwis);
					
					String Aktualizuj_serwis = "update serwis set ID_Serwis_Dodatkowe ='" + numer_ser_dod + 
							"' where ID_Serwis='"+ IDZlecenie +"'";

					statement.executeUpdate(Aktualizuj_serwis);
			}
			rs.close();

		} catch (SQLException e) {

			//e.printStackTrace();
			
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych

		}

	}

	public void Aktualne_Serwisy() {

		JScrollPane scrollPaneAktualneS = new JScrollPane();// tworzenie
															// scrolpanelu dla
		// tabeli
		scrollPaneAktualneS.setBounds(50, 80, 920, 400);
		panel2.add(scrollPaneAktualneS);

		DefaultTableModel modeloAktualne = new DefaultTableModel();// tworzenie
																	// tabeli
		// do wypisywania
		// wyników
		JTable tableAktualneS = new JTable(modeloAktualne);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "SELECT z.ID_Zlecenie, z.Nazwa_Urzadzenia, z.MAC, z.Opis, "
					+ "z.Uwagi, z.Rodzaj_Naprawy, z.Status, sd.ID_Pracownik from zlecenie z, "
					+ "serwis_dodatkowe sd WHERE z.ID_Zlecenie = sd.ID_Zlecenie and Status = 1";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszeAktualne = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 1) {
						wierszeAktualne[i++] = new Object[] { rs.getString("ID_Zlecenie"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"), rs.getString("Opis"),
								rs.getString("Uwagi"), rs.getString("Rodzaj_Naprawy"), rs.getString("ID_Pracownik") };
					}
				}

				tableAktualneS.setModel(new DefaultTableModel(wierszeAktualne, new String[] { "RMA", "Sprzêt", "MAC",
						"Opis usterki", "Uwagi", "Rodzaj naprawy", "ID Serwisanta" }));

				////////////////////////
				//rs.close();

			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableAktualneS.getColumnModel().getColumn(0).setPreferredWidth(35);// ustawienia
		// kolumn
		// tabeli
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(1).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(2).setPreferredWidth(20);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(3).setPreferredWidth(90);
		// tableAktualne_zg³oszenia.getColumnModel().getColumn(4).setPreferredWidth(10);

		scrollPaneAktualneS.setViewportView(tableAktualneS);
	}

	public void Zakoñczone_Serwisy() {

		JScrollPane scrollPaneZakonczone_serwisy = new JScrollPane();// tworzenie
		// scrolpanelu
		// dla
		// tabeli
		scrollPaneZakonczone_serwisy.setBounds(50, 80, 920, 400);
		panel3.add(scrollPaneZakonczone_serwisy);

		DefaultTableModel modeloZakonczone_serwisy = new DefaultTableModel();// tworzenie
		// tabeli
		// do wypisywania
		// wyników
		JTable tableZakonczone_serwisy = new JTable(modeloZakonczone_serwisy);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
		// z
		// baz¹

		try {
			// System.out.println(id_klient);
			String queryString = "select s.ID_Serwis, z.Nazwa_Urzadzenia, z.MAC, z.Opis, z.Uwagi, "
					+ "z.Rodzaj_Naprawy, s.Uwagi_Serwisanta, s.Status, f.Kwota_Netto, f.Czy_Zaplacone, "
					+ "sd.ID_Pracownik from zlecenie z, serwis s, faktura f, faktura_dodatkowe fd, "
					+ "serwis_dodatkowe sd where z.ID_Zlecenie = s.ID_Serwis and s.ID_Serwis_Dodatkowe = "
					+ "sd.ID_Serwis_Dodatkowe and f.ID_Faktura_Dodatkowe = fd.ID_Faktura_Dodatkowe and "
					+ "fd.ID_Serwis = s.ID_Serwis and s.Status = 2";// zapytanie

			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
			// zapytania

			try {

				Object[][] wierszeZakonczone_serwisy = new Object[100][100];

				// wyswietlanie kolejnych rekordow:

				int i = 0;
				while (rs.next()) {
					if (rs.getInt("Status") == 2) {
						wierszeZakonczone_serwisy[i++] = new Object[] { rs.getString("ID_Serwis"),
								rs.getString("Nazwa_Urzadzenia"), rs.getString("MAC"), rs.getString("Opis"),
								rs.getString("Uwagi"), rs.getString("Rodzaj_Naprawy"), rs.getString("ID_Pracownik"),
								rs.getString("Uwagi_Serwisanta"), rs.getString("Kwota_Netto"),
								rs.getString("Czy_Zaplacone") };
					}
				}

				tableZakonczone_serwisy
						.setModel(
								new DefaultTableModel(wierszeZakonczone_serwisy,
										new String[] { "RMA", "Sprzêt", "MAC", "Opis usterki", "Uwagi",
												"Rodzaj naprawy", "ID pracownika", "Uwagi serwisanta", "Koszt",
												"Czy zap³acone" }));

				////////////////////////
				//rs.close();
			} catch (SQLException e) {
				System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableZakonczone_serwisy.getColumnModel().getColumn(0).setPreferredWidth(10);// ustawienia
		tableZakonczone_serwisy.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableZakonczone_serwisy.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableZakonczone_serwisy.getColumnModel().getColumn(6).setPreferredWidth(15);
		tableZakonczone_serwisy.getColumnModel().getColumn(9).setPreferredWidth(15);

		scrollPaneZakonczone_serwisy.setViewportView(tableZakonczone_serwisy);
	}

	public void Zawies_Pracownika() {

		int IDPracownika = Integer.parseInt(textFieldNr_pracownikaZ.getText());

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {
			statement = conn.createStatement();
			String queryString = "select * from pracownik";

			ResultSet rs2 = statement.executeQuery(queryString);

			while (rs2.next()) {

				if (IDPracownika > 0 && rs2.getInt("Status") == 1) {

					String zawies = "update pracownik set Status=0 where Status=1 and ID_Pracownik='" + 
					IDPracownika + "'";

					statement.executeUpdate(zawies);

				} else {

					new Info();

				}

			}
			rs2.close();

		} catch (SQLException e) {

			// System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ":
			// " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych

		}

	}

	public void Przywroc_Pracownika() {

		int IDPracownika = Integer.parseInt(textFieldNr_pracownikaZ.getText());

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {
			statement = conn.createStatement();
			String queryString = "select * from pracownik";

			ResultSet rs2 = statement.executeQuery(queryString);

			while (rs2.next()) {

				if (IDPracownika > 0) {

					String zawies = "update pracownik set Status=1 where Status=0 and ID_Pracownik='" + 
					IDPracownika + "'";

					statement.executeUpdate(zawies);

				} else {

					new Info();

				}

			}
			rs2.close();

		} catch (SQLException e) {

			// System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ":
			// " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych

		}

	}

	public void Usun_Pracownika() {

		int IDPracownika = Integer.parseInt(textFieldNr_pracownikaU.getText());

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {
			statement = conn.createStatement();
			String queryString = "select * from pracownik";

			ResultSet rs2 = statement.executeQuery(queryString);

			while (rs2.next()) {

				if (IDPracownika > 0 && rs2.getInt("Status") == 0) {

					String zawies = "update pracownik set Status=2 where Status=0 and ID_Pracownik='" + 
					IDPracownika + "'";

					statement.executeUpdate(zawies);

				} else {

					new Info();

				}

			}
			rs2.close();

		} catch (SQLException e) {

			// System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ":
			// " + e.getErrorCode());
			// new Info();//pokazanie komunikatu o niepoprawnoœci danych

		}

	}

	public void Dodaj_Pracownika() {

		String imie = textFieldImie.getText();
		String nazwisko = textFieldNazwisko.getText();
		String ulica = textFieldUlica.getText();
		String nr_domu = textFieldNr_domu.getText();
		String miejscowosc = textFieldMiasto.getText();
		String kod_pocztowy = textFieldKod_pocztowy.getText();
		String pesel = textFieldPESEL.getText();
		String telefon = textFieldTelefon.getText();
		String email = textFieldEmail.getText();
		String specjalizacja = textFieldSpecjalizacja.getText();
		String opis = textAreaDodatkowe.getText();

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
								// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");// ³¹czenie
																					// z
																					// baz¹

		try {

			String id_tabele = "select max(k.ID_Konto)+1 as konto, max(dk.ID_Dodatkowe_Pracownik)+1 as dpracownik, "
					+ "max(ak.ID_Adres_Pracownik)+1 as apracownik from konto k, dodatkowe_pracownik dk, adres_pracownik ak";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(id_tabele);

			while (rs.next()) {

				if (imie != null && nazwisko != null && ulica != null && nr_domu != null && miejscowosc != null
						&& kod_pocztowy != null && pesel != null && telefon != null && email != null
						&& specjalizacja != null) {
					// dzia³a

					int pracownikK = rs.getInt("konto");
					int pracownikD = rs.getInt("dpracownik");
					int pracownikA = rs.getInt("apracownik");

					String konto = "insert into konto (Login, Haslo, Status) values('" + nazwisko + "','" + imie + "',"
							+ " '2')";

					statement.executeUpdate(konto);

					// dzia³a

					String dod_klient = "insert into dodatkowe_pracownik (Specjalizacja, Opis) values('" + specjalizacja
							+ "','" + opis + "')";
					statement.executeUpdate(dod_klient);
					// dzia³a

					String adr_klient = "insert into adres_pracownik (Ulica, Nr_domu, Miejscowosc, Kod_Pocztowy) "
							+ "values('" + ulica + "','" + nr_domu + "','" + miejscowosc + "','" + kod_pocztowy + "')";
					statement.executeUpdate(adr_klient);
					// dzia³a

					// System.out.println(kontoK);
					// System.out.println(klientD);
					// System.out.println(klientA);

					String klient = "insert into pracownik (Imie, Nazwisko, PESEL, Telefon, Email, Status,"
							+ " ID_Konto, ID_Adres_Pracownik, ID_Dodatkowe_Pracownik) values('" + imie + "','"
							+ nazwisko + "','" + pesel + "','" + telefon + "','" + email + "','1','" + pracownikK
							+ "','" + pracownikA + "','" + pracownikD + "')";

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

	private class PrzyciskListenerAktualizujAktualne implements ActionListener {// klasa
																				// realizuj¹ca
																				// funkcjonalnoœc
																				// przycisku
																				// zaloguj

		public void actionPerformed(ActionEvent event) {

			Aktualne_Serwisy();

		}
	}

	private class PrzyciskListenerAktualizuj_Pracownicy implements ActionListener {// klasa
																					// realizuj¹ca
																					// funkcjonalnoœc
																					// przycisku
																					// zaloguj

		public void actionPerformed(ActionEvent event) {

			Lista_Pracownikow();

		}
	}

	private class PrzyciskListenerAktualizuj implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Nowe_Serwisy();

		}
	}

	private class PrzyciskListenerAktualizujZakonczone implements ActionListener {// klasa
																					// realizuj¹ca
																					// funkcjonalnoœc
																					// przycisku
																					// zaloguj

		public void actionPerformed(ActionEvent event) {

			Zakoñczone_Serwisy();

		}
	}

	private class PrzyciskListenerPrzydziel implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Przydziel_Pracownika();

		}
	}

	private class PrzyciskListenerAktualizuj_Pracownicy2 implements ActionListener {// klasa
																					// realizuj¹ca
																					// funkcjonalnoœc
																					// przycisku
																					// zaloguj

		public void actionPerformed(ActionEvent event) {

			Lista_Pracownikow2();

		}
	}

	private class PrzyciskListenerZawies implements ActionListener {// klasa
																	// realizuj¹ca
																	// funkcjonalnoœc
																	// przycisku
																	// zaloguj

		public void actionPerformed(ActionEvent event) {

			Zawies_Pracownika();

		}
	}

	private class PrzyciskListenerPrzywroc implements ActionListener {// klasa
																		// realizuj¹ca
																		// funkcjonalnoœc
																		// przycisku
																		// zaloguj

		public void actionPerformed(ActionEvent event) {

			Przywroc_Pracownika();

		}
	}

	private class PrzyciskListenerUsun implements ActionListener {// klasa
																	// realizuj¹ca
																	// funkcjonalnoœc
																	// przycisku
																	// zaloguj

		public void actionPerformed(ActionEvent event) {

			Usun_Pracownika();

		}
	}

	private class PrzyciskListenerDodaj_pracownika implements ActionListener {// klasa
																				// realizuj¹ca
																				// funkcjonalnoœc
																				// przycisku
																				// zaloguj

		public void actionPerformed(ActionEvent event) {

			Dodaj_Pracownika();

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
				e1.printStackTrace();
			}

		}
	};

}
