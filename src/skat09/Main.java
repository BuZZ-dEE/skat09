package skat09;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import skat09.test.interfaces.IAusgabe;
import skat09.test.interfaces.IController;
import skat09.ui.CLIausgabe;
import skat09.ui.GUIausgabe;

/**
 * Die Main Klasse dient dem Programmstart, sowie dem Initialisieren der
 * f&uuml;r das Programm ben&ouml;tigten Objekte.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class Main {

	/**
	 * Die Mainmethode f&uuml;hrt das Programm aus.
	 * 
	 * @param args
	 *            &Uuml;bergegebene Parameter
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws IOException {

		Tisch tisch = new Tisch();

		String s = null;
		IAusgabe ausgabe;
		try {
			s = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			s = "";
		}

		if (s.compareTo("-nogui") == 0) {
			ausgabe = new CLIausgabe(tisch);
		} else {

			try {
				// Set System L&F
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e) {
				// handle exception
			} catch (ClassNotFoundException e) {
				// handle exception
			} catch (InstantiationException e) {
				// handle exception
			} catch (IllegalAccessException e) {
				// handle exception
			}

			ausgabe = new GUIausgabe(tisch);
		}

		IController controller = new Controller(tisch, ausgabe);
		if (ausgabe instanceof GUIausgabe) {
			controller.warte();
		}
		controller.spiel();

	}

}
