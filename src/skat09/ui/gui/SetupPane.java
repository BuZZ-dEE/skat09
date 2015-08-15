package skat09.ui.gui;

import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import skat09.Messages;
import skat09.player.PlayerEnum;
import skat09.tools.Configuration;
import skat09.ui.GUIOutput;

/**
 * The skat entry setp window.
 * 
 * @since 21.07.2015 21:18:01
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class SetupPane extends Pane implements EventHandler<Event> {
	
	private BorderPane setupPane;
	private Pane optionsPane;
	private Pane logoPane;
	private ComboBox<String> adversary1;
	private ComboBox<String> adversary2;
	private TextField name;
	private ComboBox<String> skatVariant;
	private ComboBox<String> deck;
	private Label nameLabel;
	private Label adversary1Label;
	private Label adversary2Label;
	private Label variantLabel;
	private Label sixskatLabel;
	private Label deckLabel;
	private Button useSetupButton;
	private CheckBox sixskat;
	private Label imageFrameLabel;
	private String playerName = "";
	private Stage stage;
	/**
	 * Je nach Eingabe wird der zur Eingabe in Gegnerwahl der passende ENUM-Wert
	 * gehalten
	 */
	private PlayerEnum player1;
	/**
	 * Je nach Eingabe wird der zur Eingabe in Gegnerwahl der passende ENUM-Wert
	 * gehalten
	 */
	private PlayerEnum player2;
	/**
	 * Je nach eingabe wird hier ein bestimmter Wert f&uuml;r jede Skatvariante
	 * gehalten
	 */
	private int variant = 0;
	/**
	 * wird je nachdem gesetzt, ob franz&ouml;sisches oder deutsches Spielblatt
	 * erw&uuml;nscht wird
	 */
	private boolean isGermanDeck = false;
	/**
	 * wird je nachdem gesetzt, ob sechserskat gew&auml;hlt wird oder nicht
	 */
	private boolean isSixskat = false;
	/**
	 * H&auml;lt die Methoden, um f&uuml;r eine grafische Darstellung zu sorgen
	 */
	private GUIOutput output;
	
	/**
	 * 
	 * @param output
	 *            Die aktuelle GUIAusgabe wird &uuml;bergeben, damit sie
	 *            released werden kann.
	 */
	public SetupPane(GUIOutput output, Stage stage) {
		
//        Scene scene = new Scene(new Group(new Text(25, 25, "Hello World!"))); 
		this.stage = stage;
		this.stage.setTitle(Messages.getI18n("application.name")); 

		this.output = output;
		this.setupPane = new BorderPane();
		
		init();

		try {
			ClassLoader cl = this.getClass().getClassLoader();
			this.stage.getIcons().add(new Image(cl.getResource("res/test.png").openStream()));
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		int top = (int) ((primaryScreenBounds.getHeight() - this.getHeight()) / 2);
		int left = (int) ((primaryScreenBounds.getWidth() - this.getWidth()) / 2);
		
		this.stage.setX(left);
		this.stage.setY(top);
		this.stage.setResizable(false);
		this.stage.show();
	}
	
	/**
	 * Initialize the setup elements (image, button etc.)
	 * 
	 * @since 21.07.2015 22:28:15
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	private void init() {
		
//		getContentPane().add(new Toolbar(), BorderLayout.NORTH);
		setupPane.getChildren().add(new FlowPane());
		optionsPane = new Pane();

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
//		//Defining the Name text field
//		final TextField name = new TextField();
//		name.setPromptText("Enter your name.");
//		name.setPrefColumnCount(10);
//		name.getText();
//		GridPane.setConstraints(name, 0, 0);
//		gridPane.getChildren().add(name);
//		//Defining the Last Name text field
//		final TextField lastName = new TextField();
//		lastName.setPromptText("Enter your last name.");
//		GridPane.setConstraints(lastName, 0, 1);
//		gridPane.getChildren().add(lastName);
//		//Defining the Comment text field
//		final TextField comment = new TextField();
//		comment.setPrefColumnCount(15);
//		comment.setPromptText("Enter your comment.");
//		GridPane.setConstraints(comment, 0, 2);
//		gridPane.getChildren().add(comment);
//		//Defining the Submit button
//		Button submit = new Button("Submit");
//		GridPane.setConstraints(submit, 1, 0);
//		gridPane.getChildren().add(submit);
//		//Defining the Clear button
//		Button clear = new Button("Clear");
//		GridPane.setConstraints(clear, 1, 1);
//		gridPane.getChildren().add(clear);
		
		useSetupButton = new Button(Messages.getI18n("application.ok"));
//		useSetupButton.setName("ok");
		useSetupButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e) {
				quit();
			}
		});

		useSetupButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getEventType() == KeyEvent.KEY_RELEASED) {
					keyReleased(event);
				}
				
			}
		});		
		
		logoPane = new Pane();
		logo();
		createOptions(gridPane);
		setOptions(gridPane);
		
		GridPane.setConstraints(useSetupButton, 0, 6);
		gridPane.getChildren().add(useSetupButton);
		
		optionsPane.getChildren().add(gridPane);
		
//		optionsPane.getChildren().add(useSetupButton);

		setupPane.setLeft(optionsPane);
		setupPane.setRight(logoPane);
		
		Scene scene = new Scene(setupPane);
		this.stage.setScene(scene);
		this.stage.show();
	}

	private void setOptions(GridPane gridPane) {
		
		GridPane.setConstraints(name, 0, 0);
		gridPane.getChildren().add(nameLabel);
		
		GridPane.setConstraints(name, 1, 0);
		gridPane.getChildren().add(name);
		
		GridPane.setConstraints(adversary1Label, 0, 1);
		gridPane.getChildren().add(adversary1Label);
		
		GridPane.setConstraints(adversary1, 1, 1);
		gridPane.getChildren().add(adversary1);
		
		GridPane.setConstraints(adversary2Label, 0, 2);
		gridPane.getChildren().add(adversary2Label);
		
		GridPane.setConstraints(adversary2, 1, 2);
		gridPane.getChildren().add(adversary2);
		
		GridPane.setConstraints(variantLabel, 0, 3);
		gridPane.getChildren().add(variantLabel);
		
		GridPane.setConstraints(skatVariant, 1, 3);
		gridPane.getChildren().add(skatVariant);
		
		GridPane.setConstraints(sixskatLabel, 0, 4);
		gridPane.getChildren().add(sixskatLabel);
		
		GridPane.setConstraints(sixskat, 1, 4);
		gridPane.getChildren().add(sixskat);
		
		GridPane.setConstraints(deckLabel, 0, 5);
		gridPane.getChildren().add(deckLabel);
		
		GridPane.setConstraints(deck, 1, 5);
		gridPane.getChildren().add(deck);
	}

	private void createOptions(GridPane gridPane) {
		
		// Beschriftungen der ComboBoxes
		String[] adversary = { Messages.getI18n("player.granny"),
				Messages.getI18n("player.rule.compliant"),
				Messages.getI18n("player.smart") };
		String[] variant = { Messages.getI18n("game.skat.international"),
				Messages.getI18n("game.skat.raeuber"),
				Messages.getI18n("game.skat.ramschbock") };
		String[] decks = { Messages.getI18n("game.skat.sheet.german"),
				Messages.getI18n("game.skat.sheet.french") };

		// Namen der Items
		nameLabel = new Label(Messages.getI18n("player.name") + ":");
		adversary1Label = new Label(Messages.getI18n("player.adversary.first") + ":");
		adversary2Label = new Label(
				Messages.getI18n("player.adversary.second") + ":");
		variantLabel = new Label(Messages.getI18n("game.skat.variant") + ":");
		sixskatLabel = new Label(Messages.getI18n("game.skat.variant.six") + ":");
		deckLabel = new Label(Messages.getI18n("game.sheet.choice") + ":");

		// Textfeld
		name = new TextField(Configuration.getInstance().getDefaultName());
		name.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				Configuration.getInstance().setDefaultName(newValue);
			}
		});
		name.setPrefColumnCount(10);
		nameLabel.setLabelFor(name);
		Double columnWidth = name.getMaxWidth();
		
		// ComboBox mit Label:
		adversary1 = new ComboBox<String>();
		adversary1.getItems().addAll(adversary);
		adversary1.getSelectionModel().select(1);
		adversary1.setPrefWidth(columnWidth);
		adversary1Label.setLabelFor(adversary1);

		// Combobox adversary2 mit Label:
		adversary2 = new ComboBox<String>();
		adversary2.getItems().addAll(adversary);
		adversary2.getSelectionModel().select(1);
		adversary2.setPrefWidth(columnWidth);
		adversary2Label.setLabelFor(adversary2);

		// Combobox Skatvariante mit Label
		skatVariant = new ComboBox<String>();
		skatVariant.getItems().addAll(variant);
		skatVariant.getSelectionModel().select(0);
		skatVariant.setPrefWidth(columnWidth);
		variantLabel.setLabelFor(skatVariant);

		// ComboBox Blattwahl mit Label
		deck = new ComboBox<String>();
		deck.getItems().addAll(decks);
		
		if (Configuration.getInstance().getDeck().equalsIgnoreCase("de")) {
			deck.getSelectionModel().select(0);
		} else if (Configuration.getInstance().getDeck().equalsIgnoreCase("fr")) {
			deck.getSelectionModel().select(1);
		}
		
		deck.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (deck.getSelectionModel().getSelectedIndex() == 0) {
					Configuration.getInstance().setDeck("de");
				} else if (deck.getSelectionModel().getSelectedIndex() == 1) {
					Configuration.getInstance().setDeck("fr");
				}
			}
		});
		
		if (Configuration.getInstance().getDeck().equalsIgnoreCase("de")) {
			deck.getSelectionModel().select(0);
		} else if (Configuration.getInstance().getDeck().equalsIgnoreCase("fr")) {
			deck.getSelectionModel().select(1);
		} else {
			deck.getSelectionModel().select(1);
		}
		deck.setPrefWidth(columnWidth);
		deckLabel.setLabelFor(deck);

		// Checkbox Sechserskat mit Label
		sixskat = new CheckBox();
//		sixskat.setName("sechser");
		sixskatLabel.setLabelFor(sixskat);
		sixskat.setFocusTraversable(true);
		sixskat.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getEventType() == KeyEvent.KEY_RELEASED) {
					keyReleased(event);
				}
				
			}
		});
	}
	
	/**
	 * Load the game logo into pane.
	 */
	public void logo() {
		
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("img/bild.jpeg"));
		imageFrameLabel = new Label();
		imageFrameLabel.setGraphic(new ImageView(image));
		// bilderrahmen.setPreferredSize(new Dimension(200, 400));
		logoPane.getChildren().add(imageFrameLabel);
	}

	/**
	 * gibt den Link an, an dem sich die ben&oetigten Mediendateien befinden und
	 * erlaubt es, diese auch innerhalb einer Jar-Datei anzusprechen
	 * 
	 * @param path
	 * @return the File URL
	 */
	public static URL getFileUrl(String path) {

		return SetupPane.class.getClassLoader().getResource(path);
	}

	/**
	 * Gibt die Art des 1. Mitspielers zur&uuml;ck
	 * 
	 * @return mitspieler1
	 */
	public PlayerEnum getMitspieler1() {
		
		return player1;
	}

	/**
	 * Gibt die Art des 2. Mitspielers zur&uuml;ck
	 * 
	 * @return mitspieler2
	 */
	public PlayerEnum getMitspieler2() {
		
		return player2;
	}

	/**
	 * dient zur Abfrage der gew&auml;hlten Skatvariante
	 * 
	 * @return Die Skatvariante
	 */
	public int getSkatWahl() {
		int erg = variant;
		variant = 0;
		return erg;
	}

	/**
	 * dient zur Abfrage des gew&auml;hlten Spielernamens
	 * 
	 * @return Der Spielername
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * Setzt die gew&auml;hlte Spielblattwahl
	 * 
	 * @param isGermanDeck
	 *            - Spielblattwahl
	 * 
	 */
	public void setDeutsch(boolean isGermanDeck) {
		this.isGermanDeck = isGermanDeck;
	}

	/**
	 * dient zur Abfrage der gew&auml;hlten Spielblattvariante
	 * 
	 * @return die gew&auml;hlte Spielblattvariante
	 */
	public boolean getDeutsch() {
		return isGermanDeck;
	}

	/**
	 * dient zur Abfrage ob SechserSkat gespielt werden soll
	 * 
	 * @return ob Sechserskat gew&auml;hlt wurde
	 */
	public boolean getSechserkat() {
		return isSixskat;
	}

	@Override
	public void handle(Event event) {
		
		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			KeyEvent keyEvent = (KeyEvent) event;
			keyReleased(keyEvent);
		}
	}
	
	/**
	 * Die Methode beschreibt das Verhalten beim Loslassen einer Taste, wenn der
	 * Fokus auf der Komponente ist.
	 * 
	 * @since 21.07.2015 23:40:48
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			if (e.getSource().equals(sixskat)) {
				CheckBox box = (CheckBox) e.getSource();
				if (box.isSelected() == true) {
					box.setSelected(false);
				} else {
					box.setSelected(true);
				}
			}
			if (e.getSource().equals(useSetupButton)) {
				quit();
			}
		}

	}
	
	/**
	 * Quit the game.
	 * 
	 * @version 27.07.2015 22:58:19
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	private void quit() {
		System.out.println("ok button clicked,");
		
		output.hauptfensterOeffnen();

		playerName = name.getText();
		int index;
		index = adversary1.getSelectionModel().getSelectedIndex();
		if (index == 0) {
			player1 = PlayerEnum.GRANNY;
		} else if (index == 1) {
			player1 = PlayerEnum.RULECOMPLIANT;
		} else {
			player1 = PlayerEnum.SMART;
		}

		index = adversary2.getSelectionModel().getSelectedIndex();
		if (index == 0) {
			player2 = PlayerEnum.GRANNY;
		} else if (index == 1) {
			player2 = PlayerEnum.RULECOMPLIANT;
		} else {
			player2 = PlayerEnum.SMART;
		}

		index = skatVariant.getSelectionModel().getSelectedIndex();
		if (index == 0) {
			variant = 1;
		} else if (index == 1) {
			variant = 0;
		} else {
			variant = 2;
		}

		index = deck.getSelectionModel().getSelectedIndex();
		if (index == 0) {
			isGermanDeck = true;
		} else {
			isGermanDeck = false;
		}

		if (sixskat.isSelected()) {
			isSixskat = true;
		}

		output.setRelease(true);

		setVisible(false);
	}
}
