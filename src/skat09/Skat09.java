package skat09;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.tools.OSValidator;
import skat09.ui.CLIOutput;
import skat09.ui.GUIOutput;
import skat09.ui.gui.SetupStage;

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
			OSValidator.setOperatingSystemProperties();
			output = new GUIOutput(primaryStage, table);
		}

		IController controller = new Controller(table, output);
		if (output instanceof GUIOutput) {
			controller.warte();
		}
		controller.spiel();
	}
}
