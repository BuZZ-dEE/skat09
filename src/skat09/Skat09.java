package skat09;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
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
			controller.warte();
		}
		controller.spiel();
		
		
		
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
		
		
		
		
		
		
//		GridPane grid = new GridPane();
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25, 25, 25, 25));
//
//		Scene scene = new Scene(grid, 300, 275);
//		primaryStage.setScene(scene);
//		
//		Text scenetitle = new Text("Welcome");
//		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//		grid.add(scenetitle, 0, 0, 2, 1);
//
//		Label userName = new Label("User Name:");
//		grid.add(userName, 0, 1);
//
//		TextField userTextField = new TextField();
//		grid.add(userTextField, 1, 1);
//
//		Label pw = new Label("Password:");
//		grid.add(pw, 0, 2);
//
//		PasswordField pwBox = new PasswordField();
//		grid.add(pwBox, 1, 2);
//
//		Button btn = new Button("Sign in");
//		HBox hbBtn = new HBox(10);
//		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn.getChildren().add(btn);
//		grid.add(hbBtn, 1, 4);
//
//		final Text actiontarget = new Text();
//        grid.add(actiontarget, 1, 6);
//
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//        	 
//            @Override
//            public void handle(ActionEvent e) {
//                actiontarget.setFill(Color.FIREBRICK);
//                actiontarget.setText("Sign in button pressed");
//            }
//        });
//
//		
//		primaryStage.show();

	}
}
