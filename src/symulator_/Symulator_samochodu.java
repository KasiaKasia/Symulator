package symulator_;


import java.awt.Container;

import javax.swing.JFrame;

public class Symulator_samochodu {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub


         
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			 
			public void run() {
				// wywolanie aplikacji GUI
				Okno ramka = new Okno();
				// wyswietlenie ranki
				ramka.setVisible(true);
			}
		});
	}
}

class Okno extends JFrame {

	public Okno() {
		// ustawienie rozmiaru okna
		setSize(1000, 700);
		setTitle("Temat projektu: Symulator samochodu, język Java.");

		// utworzenie obiektu kontenera
		Container kontener = getContentPane();

		// utworzenie obiektu komponentu
		Komponent panel = new Komponent();
		// Wskaznik_predkosci wskaznik_predkosci = new Wskaznik_predkosci();

		// dodanie Komponentu do kontenera
		// kontener.add(wskaznik_predkosci);
		kontener.add(panel);
	}

}
