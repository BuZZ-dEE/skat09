package main;

import java.io.IOException;

import test.interfaces.IOutput;
import test.interfaces.IController;
import main.tools.OSValidator;
import main.ui.CLIOutput;
import main.ui.GUIOutput;

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
			controller.waiting();
		}
		controller.play();
	}
}
