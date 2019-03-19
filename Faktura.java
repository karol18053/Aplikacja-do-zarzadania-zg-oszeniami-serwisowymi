package serwis;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class Faktura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Connection conn;
	public Statement statement;
	public Connection connmysql;
	public Statement statementmysql;
	private static String id;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Faktura frame = new Faktura();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Faktura(String id) {

		Faktura.setId((id));

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\karol\\Desktop\\Studia\\In¿ynierka\\Serwis\\Logo\\RT.ico"));
		setTitle("Faktura");// ustawienia jframa
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 725, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		Color kolor = new Color(0, 128, 255);
		panel.setBackground(kolor);
		panel.setBounds(0, 0, 719, 721);
		contentPane.add(panel);
		panel.setLayout(null);

		Baza baza = new Baza();// tworzenie obiektu klasy realizuj¹cej
		// po³¹czenie z baz¹
		baza.dbConnect("jdbc:mysql://127.0.0.1:3306/serwis_baza?user=root&password=");

		try {
			// System.out.println(id_klient);
			String queryString = "select max(f.ID_Faktura) as numer from faktura f, "
					+ "faktura_dodatkowe fd, klient k, konto ko WHERE f.ID_Faktura_Dodatkowe = "
					+ "fd.ID_Faktura_Dodatkowe and fd.ID_Klient = k.ID_Klient and k.ID_Konto = "
					+ "ko.ID_Konto and ko.ID_Konto = '" + id + "'";// zapytanie
																	// wyci¹gaj¹ce
																	// odp.
																	// dane
																	// z
																	// bazy
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs.next()) {

				JLabel rt = new JLabel("R&T - Serwis");
				rt.setFont(new Font("Tahoma", Font.PLAIN, 30));
				rt.setForeground(Color.BLACK);
				rt.setBounds(30, 11, 189, 58);
				panel.add(rt);

				JLabel faktura = new JLabel("Faktura VAT FS/" + rs.getString("numer") + "/2018");
				faktura.setFont(new Font("Tahoma", Font.PLAIN, 30));
				faktura.setForeground(Color.BLACK);
				faktura.setBounds(393, 11, 316, 58);
				panel.add(faktura);

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

		JLabel sprzedawca = new JLabel("Sprzedawca:");
		sprzedawca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sprzedawca.setForeground(Color.BLACK);
		sprzedawca.setBounds(30, 67, 85, 58);
		panel.add(sprzedawca);

		try {
			// System.out.println(id_klient);
			String queryString = "select * FROM dane_firmy, adres_firmy";// zapytanie
																			// wyci¹gaj¹ce
																			// odp.
																			// dane
																			// z
																			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs2 = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs2.next()) {

				JLabel firma = new JLabel(rs2.getString("Nazwa_Firmy"));
				firma.setFont(new Font("Tahoma", Font.PLAIN, 15));
				firma.setBounds(30, 90, 85, 58);
				panel.add(firma);

				JLabel adres = new JLabel(rs2.getString("Ulica") + " " + rs2.getString("Nr_domu"));
				adres.setFont(new Font("Tahoma", Font.PLAIN, 15));
				adres.setBounds(30, 110, 150, 58);
				panel.add(adres);

				JLabel kod = new JLabel(rs2.getString("Kod_Pocztowy") + " " + rs2.getString("Miejscowosc"));
				kod.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kod.setBounds(30, 130, 150, 58);
				panel.add(kod);

				JLabel nip = new JLabel("NIP: " + rs2.getString("NIP"));
				nip.setFont(new Font("Tahoma", Font.PLAIN, 15));
				nip.setBounds(30, 150, 150, 58);
				panel.add(nip);

				JLabel telefon = new JLabel("tel: " + rs2.getString("Telefon"));
				telefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
				telefon.setBounds(30, 170, 150, 58);
				panel.add(telefon);

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

		JLabel nabywca = new JLabel("Nabywca:");
		nabywca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nabywca.setForeground(Color.BLACK);
		nabywca.setBounds(393, 67, 72, 58);
		panel.add(nabywca);

		try {
			// System.out.println(id_klient);
			String queryString = "select * FROM klient k, adres_klient ak, dodatkowe_klient dk, "
					+ "konto ko where k.ID_Adres_Klient = ak.ID_Adres_Klient and k.ID_Dodatkowe_Klient = "
					+ "dk.ID_Dodatkowe_Klient and k.ID_Konto = ko.ID_Konto and ko.ID_Konto = '" + id + "'";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs3 = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs3.next()) {

				JLabel imie_nazwisko = new JLabel(rs3.getString("Imie") + " " + rs3.getString("Nazwisko"));
				imie_nazwisko.setFont(new Font("Tahoma", Font.PLAIN, 15));
				imie_nazwisko.setBounds(393, 90, 150, 58);
				panel.add(imie_nazwisko);

				JLabel adres = new JLabel(rs3.getString("Ulica") + " " + rs3.getString("Nr_domu"));
				adres.setFont(new Font("Tahoma", Font.PLAIN, 15));
				adres.setBounds(393, 110, 150, 58);
				panel.add(adres);

				JLabel kod = new JLabel(rs3.getString("Kod_Pocztowy") + " " + rs3.getString("Miejscowosc"));
				kod.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kod.setBounds(393, 130, 150, 58);
				panel.add(kod);

				JLabel firma = new JLabel("Firma: " + rs3.getString("Nazwa_Firmy") + " NIP: " + rs3.getString("NIP"));
				firma.setFont(new Font("Tahoma", Font.PLAIN, 15));
				firma.setBounds(393, 150, 150, 58);
				panel.add(firma);

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

		try {
			// System.out.println(id_klient);
			String queryString = "select f.Data_Wystawienia, f.Termin_Platnosci FROM klient k, konto ko, "
					+ "faktura f, faktura_dodatkowe fd where f.ID_Faktura_Dodatkowe = fd.ID_Faktura_Dodatkowe "
					+ "and fd.ID_Klient = k.ID_Klient and k.ID_Konto = ko.ID_Konto and " + 
					"ko.ID_Konto = '" + id + "' ORDER by f.ID_Faktura DESC limit 1";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs4 = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs4.next()) {

				JLabel dataS = new JLabel("Data sprzeda¿y: " + rs4.getString("Data_Wystawienia"));
				dataS.setFont(new Font("Tahoma", Font.PLAIN, 15));
				dataS.setForeground(Color.BLACK);
				dataS.setBounds(30, 210, 333, 45);
				panel.add(dataS);

				JLabel dataW = new JLabel("Data wystawienia: " + rs4.getString("Data_Wystawienia"));
				dataW.setFont(new Font("Tahoma", Font.PLAIN, 15));
				dataW.setForeground(Color.BLACK);
				dataW.setBounds(30, 233, 333, 45);
				panel.add(dataW);

				JLabel sposobP = new JLabel("Sposób p³atnoœci: przelew (7 dni)");
				sposobP.setFont(new Font("Tahoma", Font.PLAIN, 15));
				sposobP.setForeground(Color.BLACK);
				sposobP.setBounds(30, 265, 333, 45);
				panel.add(sposobP);

				JLabel terminP = new JLabel("Termin p³atnoœci: " + rs4.getString("Termin_Platnosci"));
				terminP.setFont(new Font("Tahoma", Font.PLAIN, 15));
				terminP.setForeground(Color.BLACK);
				terminP.setBounds(30, 295, 333, 45);
				panel.add(terminP);

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

		JLabel konto = new JLabel("Na konto: xx xxxx xxxx xxxx xxxx xxxx xxxxx");
		konto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		konto.setForeground(Color.BLACK);
		konto.setBounds(30, 327, 333, 45);
		panel.add(konto);

		try {
			// System.out.println(id_klient);
			String queryString = "select f.Kwota_Netto FROM klient k, konto ko, faktura f, "
					+ "faktura_dodatkowe fd where f.ID_Faktura_Dodatkowe = fd.ID_Faktura_Dodatkowe and "
					+ "fd.ID_Klient = k.ID_Klient and k.ID_Konto = ko.ID_Konto and " + "ko.ID_Konto = '" + id + 
					"' ORDER by f.ID_Faktura DESC limit 1";// zapytanie
			// wyci¹gaj¹ce
			// odp.
			// dane
			// z
			// bazy
			Statement statement = conn.createStatement();
			ResultSet rs5 = statement.executeQuery(queryString);// wykonanie
																// zapytania

			while (rs5.next()) {

				JLabel lp = new JLabel("L.p.");
				lp.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lp.setForeground(Color.BLACK);
				lp.setBounds(30, 357, 34, 45);
				panel.add(lp);

				JLabel lpf = new JLabel("1.");
				lpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lpf.setBounds(30, 387, 34, 45);
				panel.add(lpf);

				JLabel nazwaU = new JLabel("Nazwa us³ugi");
				nazwaU.setFont(new Font("Tahoma", Font.PLAIN, 15));
				nazwaU.setForeground(Color.BLACK);
				nazwaU.setBounds(74, 357, 90, 45);
				panel.add(nazwaU);

				JLabel nazwaUf = new JLabel("Naprawa urz¹dzenia");
				nazwaUf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				nazwaUf.setBounds(74, 387, 100, 45);
				panel.add(nazwaUf);

				JLabel jm = new JLabel("J.m.");
				jm.setFont(new Font("Tahoma", Font.PLAIN, 15));
				jm.setForeground(Color.BLACK);
				jm.setBounds(174, 357, 43, 45);
				panel.add(jm);

				JLabel jmf = new JLabel("szt");
				jmf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				jmf.setBounds(174, 387, 43, 45);
				panel.add(jmf);

				JLabel ilosc = new JLabel("Iloœæ.");
				ilosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
				ilosc.setForeground(Color.BLACK);
				ilosc.setBounds(232, 357, 43, 45);
				panel.add(ilosc);

				JLabel iloscf = new JLabel("1.00");
				iloscf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				iloscf.setBounds(232, 387, 43, 45);
				panel.add(iloscf);

				JLabel cennaN = new JLabel("Cena netto");
				cennaN.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cennaN.setForeground(Color.BLACK);
				cennaN.setBounds(285, 357, 78, 45);
				panel.add(cennaN);

				double cenna_netto = rs5.getInt("Kwota_Netto");

				JLabel cennaNf = new JLabel(cenna_netto + " z³");
				cennaNf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cennaNf.setBounds(285, 387, 78, 45);
				panel.add(cennaNf);

				double cenna_brutto = cenna_netto * 1.23;

				JLabel cennaB = new JLabel("Cena brutto");
				cennaB.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cennaB.setForeground(Color.BLACK);
				cennaB.setBounds(373, 357, 85, 45);
				panel.add(cennaB);

				JLabel cennaBf = new JLabel(cenna_brutto + " z³");
				cennaBf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cennaBf.setBounds(373, 387, 85, 45);
				panel.add(cennaBf);

				JLabel vat = new JLabel("VAT");
				vat.setFont(new Font("Tahoma", Font.PLAIN, 15));
				vat.setForeground(Color.BLACK);
				vat.setBounds(468, 357, 34, 45);
				panel.add(vat);

				JLabel vatf = new JLabel("23 %");
				vatf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				vatf.setBounds(468, 387, 50, 45);
				panel.add(vatf);

				JLabel kwota_vat = new JLabel("Kwota VAT");
				kwota_vat.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kwota_vat.setForeground(Color.BLACK);
				kwota_vat.setBounds(512, 357, 85, 45);
				panel.add(kwota_vat);

				double kwota_z_vatf = cenna_netto * (0.23);

				JLabel kwota_vatf = new JLabel(kwota_z_vatf + " z³");
				kwota_vatf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kwota_vatf.setBounds(512, 387, 85, 45);
				panel.add(kwota_vatf);

				JLabel kwotaZvat = new JLabel("Wartoœæ z VAT");
				kwotaZvat.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kwotaZvat.setForeground(Color.BLACK);
				kwotaZvat.setBounds(607, 357, 102, 45);
				panel.add(kwotaZvat);

				JLabel kwotaZvatf = new JLabel(cenna_brutto + " z³");
				kwotaZvatf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kwotaZvatf.setBounds(607, 387, 102, 45);
				panel.add(kwotaZvatf);

				JLabel razem = new JLabel("Razem do zap³aty: ");
				razem.setFont(new Font("Tahoma", Font.PLAIN, 15));
				razem.setForeground(Color.BLACK);
				razem.setBounds(30, 550, 333, 45);
				panel.add(razem);

				JLabel razemf = new JLabel(cenna_brutto + " z³");
				razemf.setFont(new Font("Tahoma", Font.PLAIN, 15));
				razemf.setBounds(160, 550, 333, 45);
				panel.add(razemf);

			}

		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}

		JLabel Wystawil = new JLabel("Wystawi³(a): ");
		Wystawil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Wystawil.setForeground(Color.BLACK);
		Wystawil.setBounds(80, 600, 333, 45);
		panel.add(Wystawil);

		JLabel Nabywca = new JLabel("Nabywca: ");
		Nabywca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nabywca.setForeground(Color.BLACK);
		Nabywca.setBounds(500, 600, 333, 45);
		panel.add(Nabywca);

	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Faktura.id = id;
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
