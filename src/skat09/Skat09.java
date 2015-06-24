package skat09;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

import skat09.test.interfaces.IAusgabe;
import skat09.test.interfaces.IController;
import skat09.tools.AppConfiguration;
import skat09.tools.OSValidator;
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
public class Skat09 extends Application<AppConfiguration> {

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
			OSValidator.setOperatingSystemProperties();
			ausgabe = new GUIausgabe(tisch);
		}

		IController controller = new Controller(tisch, ausgabe);
		if (ausgabe instanceof GUIausgabe) {
			controller.warte();
		}
		controller.spiel();

	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(AppConfiguration configuration, Environment environment)
			throws Exception {

		System.out.println("App runs");

	}
}
