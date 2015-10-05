package main;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import main.tools.OSValidator;
import main.ui.CLIOutput;
import main.ui.GUIOutput;
import main.ui.IOutput;
import main.ui.IOutput;
import main.IController;

/**
 * Die Main Klasse dient dem Programmstart, sowie dem Initialisieren der
 * f&uuml;r das Programm ben&ouml;tigten Objekte.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class Skat09 extends Application {

	/**
	 * Die Mainmethode f&uuml;hrt das Programm aus.
	 * 
	 * @param args
	 *            &Uuml;bergegebene Parameter
	 * @throws IOException
	 * 
	 * @version 23.07.2015 00:04:57
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 * 
	 */
	public static void main(String[] args) throws IOException {
		launch(args);
	}
    
	/**
	 * The JavaFx application start.
	 * 
	 * @since 23.07.2015 00:05:21
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Table table = new Table();
		String s = "";
		IOutput output;
        final Parameters params = getParameters();
        final List<String> parameters = params.getRaw();
        final String imageUrl = !parameters.isEmpty() ? parameters.get(0) : "";
        
        if (imageUrl != null) {
        	s = imageUrl;
        }      

		if (s.compareTo("-nogui") == 0) {
			output = new CLIOutput(table);
		} else {
//			OSValidator.setOperatingSystemProperties();
			output = new GUIOutput(primaryStage, table);
		}

		IController controller = new Controller(table, output);
		if (output instanceof GUIOutput) {
			controller.waiting();
		}
		controller.play();
	}
}
