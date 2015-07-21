package skat09;

import java.io.IOException;

import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.tools.OSValidator;
import skat09.ui.CLIOutput;
import skat09.ui.GUIOutput;

/**
 * Die Main Klasse dient dem Programmstart, sowie dem Initialisieren der
 * f&uuml;r das Programm ben&ouml;tigten Objekte.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class Skat09 {

	/**
	 * Die Mainmethode f&uuml;hrt das Programm aus.
	 * 
	 * @param args
	 *            &Uuml;bergegebene Parameter
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws IOException {

		Table table = new Table();
		String s = null;
		IOutput output;
		
		try {
			s = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			s = "";
		}

		if (s.compareTo("-nogui") == 0) {
			output = new CLIOutput(table);
		} else {
			OSValidator.setOperatingSystemProperties();
			output = new GUIOutput(table);
		}

		IController controller = new Controller(table, output);
		if (output instanceof GUIOutput) {
			controller.warte();
		}
		controller.spiel();
	}
}
