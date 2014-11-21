package symulator_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Komponent extends JPanel implements ActionListener, KeyListener {
	private BufferedImage image_g, image_d, image_l, image_p;
	protected JButton silnik, sprzeglo, wskaznik_predkosc, gaz, hamulec,
			swiatla, wycieraczki;
	boolean hold, hold_gaz, hold_hamulec; // zmienna do zapamietania
							// tylko momentu nacisniecia
							// klawisza
	boolean swiatla_wlaczone_wylaczone = true,
			wycieraczki_wlaczone_wylaczone = true, kluczyk = true; // nacisniecia
																	// klawisza

	// boolean spacePressed = true;
	static String bieg_luz = "Bieg ustawiony na luz";
	static String bieg_1 = "Bieg I";
	static String bieg_2 = "Bieg II";
	static String bieg_3 = "Bieg III";
	static String bieg_4 = "Bieg IV";
	int 	predkosc = 0, 	predkosc_gaz = 0, 	predkosc_hamulec = 0;
	double 	remainder = 0, 	remainder_gaz = 0, 	remainder_hamulec = 0;
	double 	poczatek = 0, 	poczatek_gaz = 0, 	poczatek_hamulec = 0;
	double 	koniec = 0, 	koniec_gaz = 0, 	koniec_hamulec = 0;
	JLabel 	picLabel1, picLabel2, picLabel3, picLabel4,theLabel;
	File imageFile, imageFile_dodolu, imageFile_lewa, imageFile_prawa;
	
	private Panel pnlCiz = null;
	final ButtonGroup group;
	FlowLayout Layout_gorny, Layout_dolny;

	final JPanel panel_gorny, panel_dolny, dolny_Panel;
	JPanel panel1, panel2, panel3, panel4, controls_radio;

	final JRadioButton RadioBieg_luz, RadioBieg_1, RadioBieg_2, RadioBieg_3,
			RadioBieg_4;

	public Komponent() {
		initialize();
		setFocusable(true);

		// ustawienie przyciskow w pionie
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));

		silnik = new JButton("Uruchamianie silnika - Nacisnij ENTER ");
		silnik.addActionListener(this);
		silnik.setEnabled(true);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // sprawdzenie czy
															// cisniety jest
															// ENTER
					kluczyk = !kluczyk;
					silnik.setEnabled(false); // jesli tak to wylacz przycisk
					repaint(); // odswiezanie
					if (kluczyk == false
							&& sprzeglo.getBackground().equals(Color.BLUE)) {
						silnik.setEnabled(true);

					} else {
						silnik.setEnabled(false);
					}
				}

			}
		});

		sprzeglo = new JButton("Nacisnij sprzeglo - Nacisnij strzalke lewa "); // ustawienie
																				// koloru
																				// przycisku
																				// na
																				// czarny
		// zmiana koloru na niebieski gdy sie wcisnie strzalke lewa
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) { // SHIFT
					sprzeglo.setBackground(Color.BLUE); // wykonaj zmiane
														// przycisku na kolor
														// niebieski
					sprzeglo.setEnabled(false);
					repaint(); // odświeżanie 
				}
			}
		});

		// jesli puscimy SHIFT to zmieni na aktualny kolor (czarny)
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					sprzeglo.setBackground(Color.BLACK);
					sprzeglo.setEnabled(true);
					repaint(); // odświeżanie 
				}
			}
		});
		sprzeglo.setBackground(Color.black);

		imageFile = new File(
				"C:/Users/KatarzynaAleksandra/Documents/NetBeansProjects/Symulator/strzalka_do_gory.png");
		imageFile_dodolu = new File(
				"C:/Users/KatarzynaAleksandra/Documents/NetBeansProjects/Symulator/strzalka_do_dolu.png");
		imageFile_lewa = new File(
				"C:/Users/KatarzynaAleksandra/Documents/NetBeansProjects/Symulator/strzalka_do_lewa.png");
		imageFile_prawa = new File(
				"C:/Users/KatarzynaAleksandra/Documents/NetBeansProjects/Symulator/strzalka_do_prawa.png");

		// wyświetlenie ścieżki do pliku
		System.out.println("imageFile.getAbsolutePath() "
				+ imageFile_dodolu.getAbsolutePath());

		try {
			if (image_g == null && image_d == null && image_l == null
					&& image_p == null) {
				image_g = ImageIO.read(imageFile);
				picLabel1 = new JLabel(new ImageIcon(image_g));
		
				panel1 = new JPanel(new GridLayout(1, 1));
				panel1.add(picLabel1);
				add(panel1);

				image_l = ImageIO.read(imageFile_lewa);
				picLabel3 = new JLabel(new ImageIcon(image_l));

				panel3 = new JPanel(new GridLayout(1, 3));
				panel3.add(picLabel3);

				image_p = ImageIO.read(imageFile_prawa);
				picLabel4 = new JLabel(new ImageIcon(image_p));

				panel3.add(picLabel4);
				add(panel3);
				
				image_d = ImageIO.read(imageFile_dodolu);
				picLabel2 = new JLabel(new ImageIcon(image_d));
			
				panel2 = new JPanel(new GridLayout(1, 1));
				panel2.add(picLabel2);
				add(panel2);

			} else {
				System.out.print("zdjecie jest dodane.");

			}
		} catch (IOException error) {
			System.out
					.print("Blad wyswietlenia obrazka informujacego, ze pojazd porusza sie do przodu . ");
		}

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
						if (!hold_gaz) {

							poczatek_gaz = System.currentTimeMillis() / 1000.0;
							System.out.println("Czas poczatek_gaz = "
									+ poczatek_gaz);
							hold_gaz = true;
						}
					}
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (silnik.isEnabled() == false) {

					if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
						koniec_gaz = System.currentTimeMillis() / 1000.0;
						remainder_gaz += (koniec_gaz - poczatek_gaz);
						predkosc_gaz = (int) Math.floor(remainder_gaz);
						hold_gaz = false;

					}
					// setText - wyswietla string w przycisku
					//  String.valueOf - konwertuje double na string					
					wskaznik_predkosc
							.setText("Prędkość pojazdu: "
									+ String.valueOf((predkosc + predkosc_gaz) + "m/s"+"to w przeliczeniu na "+(predkosc + predkosc_gaz)*3.6 +"km/h" ));
					System.out.println("Czas koniec_gaz = " + koniec_gaz);
					System.out.println("Czas wykonania gazu " + remainder_gaz);

				}
			}

		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						if (!hold) {
							
							picLabel1.setEnabled(true);
							picLabel2.setEnabled(false);
							picLabel3.setEnabled(false);
							picLabel4.setEnabled(false);
							repaint();

							poczatek = System.currentTimeMillis() / 1000.0;
							System.out.println("Czas poczatek = " + poczatek);
							hold = true;
						}
					}
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (silnik.isEnabled() == false) {

					if (e.getKeyCode() == KeyEvent.VK_UP) {
				
						picLabel1.setEnabled(true);
						picLabel2.setEnabled(false);
						picLabel3.setEnabled(false);
						picLabel4.setEnabled(false);
						repaint();

						// dzielac przez 1000.0 otrzymujemy tylko sekundy o
						// typie double
						koniec = System.currentTimeMillis() / 1000.0;

						// += dodany aby po puszczeniu i ponownym nacisnieciu
						// zwiekazalo predkosc, a nie liczyli od poczatku
						remainder += (koniec - poczatek) + remainder_gaz;
						/*
						 * Math.floor - funkcja zaokroglajaca w pol, konwersja
						 * double do int .
						 */

						predkosc = (int) Math.floor(remainder);

						hold = false;

					}
					wskaznik_predkosc
							.setText("Prędkość pojazdu: "
									+ String.valueOf((predkosc + predkosc_gaz)	+ "m/s" +"to w przeliczeniu na "+(predkosc + predkosc_gaz)*3.6 +"km/h" ));
					System.out.println("Czas koniec = " + koniec);
					System.out.println("Czas wykonania " + remainder);
					System.out.println("predkosc " + predkosc);

				}
			}

		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_ALT) {
						if (!hold_hamulec) {

							poczatek_hamulec = System.currentTimeMillis() / 1000.0;
							System.out.println("Czas poczatek = "
									+ poczatek_hamulec);
							hold_hamulec = true;

						}
					}
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (silnik.isEnabled() == false) {

					if (e.getKeyCode() == KeyEvent.VK_ALT) {

						koniec_hamulec = System.currentTimeMillis() / 1000.0;
						remainder_hamulec += (koniec_hamulec - poczatek_hamulec);
						predkosc_hamulec = (int) Math.floor(remainder_hamulec);

						hold_hamulec = false;
					}
					wskaznik_predkosc
							.setText("Prędkość pojazdu: "
									+ String.valueOf((predkosc + predkosc_gaz - predkosc_hamulec) + "m/s" +"to w przeliczeniu na "+(predkosc + predkosc_gaz - predkosc_hamulec)*3.6 +"km/h" ));
					System.out.println("Czas koniec_hamulec = "	+ koniec_hamulec);
					System.out.println("Czas wykonania _hamulec" + remainder_hamulec);
					System.out.println("predkosc_hamulec " + predkosc_hamulec);

				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						picLabel2.setEnabled(true);
						picLabel1.setEnabled(false);
						picLabel3.setEnabled(false);
						picLabel4.setEnabled(false);
						repaint();

					}
				}

			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						picLabel3.setEnabled(true);
						picLabel1.setEnabled(false);
						picLabel2.setEnabled(false);
						picLabel4.setEnabled(false);
						repaint();
					}
				}

			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						picLabel4.setEnabled(true);
						picLabel1.setEnabled(false);
						picLabel2.setEnabled(false);
						picLabel3.setEnabled(false);
						repaint();
					}
				}
			}
		});

		wskaznik_predkosc = new JButton("Prędkość pojazdu: "
				+ String.valueOf((predkosc + predkosc_gaz) + "m/s"+"to w przeliczeniu na "+(predkosc + predkosc_gaz)*3.6 +"km/h" ));
		swiatla = new JButton("Wlacz swiatla - F1 ");
		wycieraczki = new JButton("Wlacz wycieraczki - F2 ");

		// obsluga F1 - zmiana swiatla
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (silnik.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_F1) {
						swiatla_wlaczone_wylaczone = !swiatla_wlaczone_wylaczone;
					}
					if (swiatla_wlaczone_wylaczone == true) {
						// metoda setText - wstawia napis do przycisku swiatla
						swiatla.setText("Wlacz swiatla - F1 ");
					} else {
						swiatla.setText("Wylacz swiatla - F1 ");
					}
					// jesli ENTER silnik wlaczony , mozna uruchomic wycieraczki
					if (silnik.isEnabled() == false) {

						// wlaczanie wylaczanie wycieraczek , przycisk F2
						if (e.getKeyCode() == KeyEvent.VK_F2) {
							wycieraczki_wlaczone_wylaczone = !wycieraczki_wlaczone_wylaczone;
						}
						if (wycieraczki_wlaczone_wylaczone == true) {
							// metoda setText - wstawia napis do przycisku
							// swiatla
							wycieraczki.setText("Wlacz wycieraczki - F2 ");
						} else {
							wycieraczki.setText("Wylacz wycieraczki - F2 ");
						}
					}
				}
			}

		});

		hamulec = new JButton("Nacisnij hamulec - Nacisnij strzalke dolna ");

		RadioBieg_luz = new JRadioButton(bieg_luz);
		RadioBieg_luz.setMnemonic(KeyEvent.VK_B);
		RadioBieg_luz.setActionCommand(bieg_luz);
		RadioBieg_luz.setSelected(true);

		RadioBieg_1 = new JRadioButton(bieg_1);
		RadioBieg_1.setMnemonic(KeyEvent.VK_C);
		RadioBieg_1.setActionCommand(bieg_1);

		RadioBieg_2 = new JRadioButton(bieg_2);
		RadioBieg_2.setMnemonic(KeyEvent.VK_D);
		RadioBieg_2.setActionCommand(bieg_2);

		RadioBieg_3 = new JRadioButton(bieg_3);
		RadioBieg_3.setMnemonic(KeyEvent.VK_R);
		RadioBieg_3.setActionCommand(bieg_3);

		RadioBieg_4 = new JRadioButton(bieg_4);
		RadioBieg_4.setMnemonic(KeyEvent.VK_P);
		RadioBieg_4.setActionCommand(bieg_4);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (sprzeglo.isEnabled() == false) {
					if (e.getKeyCode() == KeyEvent.VK_1) { // jesli 1

						RadioBieg_1.setSelected(true);
						RadioBieg_luz.setSelected(false);
						RadioBieg_2.setSelected(false);
						RadioBieg_3.setSelected(false);
						RadioBieg_4.setSelected(false);
						repaint(); // odswiezanie

					} else if (e.getKeyCode() == KeyEvent.VK_2) { // jesli 2
						if (predkosc >= 20) {
							RadioBieg_1.setSelected(false);
							RadioBieg_luz.setSelected(false);
							RadioBieg_2.setSelected(true);
							RadioBieg_3.setSelected(false);
							RadioBieg_4.setSelected(false);
							repaint(); // odswiezanie
						} else {
							JOptionPane.showConfirmDialog(null,
									"Predkosc powinna byc wieksza niz 30 km/h",
									"Nieprawidlowa predkosc",
									JOptionPane.WARNING_MESSAGE, // wyswietla
																	// trójkat z
																	// !
									JOptionPane.OK_CANCEL_OPTION);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_3) { // jesli 3
						if (predkosc >= 30) {
							RadioBieg_1.setSelected(false);
							RadioBieg_luz.setSelected(false);
							RadioBieg_2.setSelected(false);
							RadioBieg_3.setSelected(true);
							RadioBieg_4.setSelected(false);
							repaint(); // odswiezanie
						} else {
							JOptionPane.showConfirmDialog(null,
									"Predkosc powinna byc wieksza niz 40 km/h",
									"Nieprawidlowa predkosc",
									JOptionPane.WARNING_MESSAGE, // wyswietla
																	// trójkat z
																	// !
									JOptionPane.OK_CANCEL_OPTION);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_4) { // jesli 4
						if (predkosc >= 50) {
							RadioBieg_1.setSelected(false);
							RadioBieg_luz.setSelected(false);
							RadioBieg_2.setSelected(false);
							RadioBieg_3.setSelected(false);
							RadioBieg_4.setSelected(true);
							repaint(); // odswiezanie
						} else {
							JOptionPane.showConfirmDialog(null,
									"Predkosc powinna byc wieksza niz 60 km/h",
									"Nieprawidlowa predkosc",
									JOptionPane.WARNING_MESSAGE, // wyswietla
																	// trójkat z
																	// !
									JOptionPane.OK_CANCEL_OPTION);
						}
					}
				}
			}
		});

		Layout_gorny = new FlowLayout();
		panel_gorny = new JPanel();
		panel_gorny.setLayout(Layout_gorny);
		Layout_gorny.setAlignment(FlowLayout.TRAILING);

		controls_radio = new JPanel();
		controls_radio.setLayout(new FlowLayout());

		panel_gorny.add(swiatla);
		panel_gorny.add(wycieraczki);

		group = new ButtonGroup();
		group.add(RadioBieg_luz);
		group.add(RadioBieg_1);
		group.add(RadioBieg_2);
		group.add(RadioBieg_3);
		group.add(RadioBieg_4);
		controls_radio.add(RadioBieg_luz);
		controls_radio.add(RadioBieg_1);
		controls_radio.add(RadioBieg_2);
		controls_radio.add(RadioBieg_3);
		controls_radio.add(RadioBieg_4);
		panel_gorny.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		add(panel_gorny, BorderLayout.CENTER);
		add(controls_radio, BorderLayout.CENTER);

		Layout_dolny = new FlowLayout();
		panel_dolny = new JPanel();
		panel_dolny.setLayout(Layout_dolny);
		Layout_dolny.setAlignment(FlowLayout.CENTER);

		panel_dolny.add(sprzeglo);
		panel_dolny.add(silnik);
		panel_dolny.add(wskaznik_predkosc);
		panel_dolny.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		add(panel_dolny, BorderLayout.CENTER);
		
		
		 String initialText = "<html>\n" +
				  "Opis działania przycisków z klawiatury: " +
	                "<ul>\n" +
	                "<li><font color=blue><b>ENTER- przekręcenie klucza</b></font>\n" +
	                "<li><font color=blue><b>1 - bieg na luz, 2 - drugi bieg, 3 - trzeci bieg, 4 - czwarty bieg</b></font>\n" +
	                "<li><font color=blue><b>SHIFT - włączenie/wyłączenie sprzęgła </b></font>\n" +
	                "<li><font color=blue><b>STRZAŁKA W PRAWO - skręca pojazd w prawo </b></font>\n" +
	                "<li><font color=blue><b>STRZAŁKA W LEWO - skręca pojazd w lewo </b></font>\n" +
	                "<li><font color=blue><b>STRZAŁKA DO GÓRY - trzymanie gazu </b></font>\n" +
	                "<li><font color=blue><b>STRZAŁKA DO DOŁU - dodaj gazu, aby jechać do tylu </b></font>\n" +
	                "</ul>\n";
		 dolny_Panel = new JPanel();
		 theLabel = new JLabel(initialText);
		 dolny_Panel.add(theLabel);
		 add(dolny_Panel);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void initialize() {
		this.add(getPnlCiz());
	}

	// metoda do tworzenia panelu
	private Panel getPnlCiz() {
		if (pnlCiz == null) {
			pnlCiz = new Panel();
		}
		return pnlCiz;
	}

}
