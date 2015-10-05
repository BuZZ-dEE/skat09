package main.ui.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Messages;
import main.player.PlayerEnum;
import main.tools.Configuration;
import main.ui.GUIOutput;

/**
 * The skat entry setp window.
 * 
 * @since 21.07.2015 21:18:01
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class SetupPane extends Pane implements EventHandler<Event> {
	
	private Pane setupPane;
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
//        stage.setScene(scene); 
//        stage.sizeToScene(); 
//        stage.show();

		this.output = output;
		this.setupPane = new Pane();
		
		init();

		try {
			ClassLoader cl = this.getClass().getClassLoader();
			this.stage.getIcons().add(new Image(cl.getResource("res/test.png").openStream()));
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int top = (int) ((screenSize.height - this.getHeight()) / 2);
		int left = (int) ((screenSize.width - this.getWidth()) / 2);
//		setLocation(left, top);
		this.stage.setX(left);
		this.stage.setY(top);
		this.stage.setResizable(false);
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
//		setupPane.setLayout(new FlowLayout());
		setupPane.getChildren().add(new FlowPane());
		optionsPane = new Pane();
		GridPane gridPane = new GridPane();
		
//		optionsPane.setLayout(new GridBagLayout());
		optionsPane.getChildren().add(gridPane);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		logoPane = new Pane();
		logo();
		erstelleOptions();
		setzteOptionen();

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
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
//		optionsPane.add(useSetupButton, c);
		optionsPane.getChildren().add(useSetupButton);

		setupPane.getChildren().add(optionsPane);
		setupPane.getChildren().add(logoPane);
		
//		add(setupPane);
//		pack();
//		setVisible(true);
		
		Scene scene = new Scene(setupPane);
		this.stage.setScene(scene);
		this.stage.setTitle("Layout Sample");
		this.stage.show();
	}

	private void setzteOptionen() {
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
//		optionsPane.getChildren().add(nameLabel, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(name, c);
//
//		c.gridx = 0;
//		c.gridy = 1;
//		optionsPane.getChildren().add(adversary1Label, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(adversary1, c);
//
//		c.gridx = 0;
//		c.gridy = 2;
//		optionsPane.getChildren().add(adversary2Label, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(adversary2, c);
//
//		c.gridx = 0;
//		c.gridy = 3;
//		optionsPane.getChildren().add(variantLabel, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(variant, c);
//
//		c.gridx = 0;
//		c.gridy = 4;
//		optionsPane.getChildren().add(sixskatLabel, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(sixskat, c);
//
//		c.gridx = 0;
//		c.gridy = 5;
//		optionsPane.getChildren().add(deckLabel, c);
//		c.gridx = 1;
//		optionsPane.getChildren().add(deck, c);
		
		
		
		optionsPane.getChildren().add(nameLabel);
		c.gridx = 1;
		optionsPane.getChildren().add(name);

		c.gridx = 0;
		c.gridy = 1;
		optionsPane.getChildren().add(adversary1Label);
		c.gridx = 1;
		optionsPane.getChildren().add(adversary1);

		c.gridx = 0;
		c.gridy = 2;
		optionsPane.getChildren().add(adversary2Label);
		c.gridx = 1;
		optionsPane.getChildren().add(adversary2);

		c.gridx = 0;
		c.gridy = 3;
		optionsPane.getChildren().add(variantLabel);
		c.gridx = 1;
		optionsPane.getChildren().add(skatVariant);

		c.gridx = 0;
		c.gridy = 4;
		optionsPane.getChildren().add(sixskatLabel);
		c.gridx = 1;
		optionsPane.getChildren().add(sixskat);

		c.gridx = 0;
		c.gridy = 5;
		optionsPane.getChildren().add(deckLabel);
		c.gridx = 1;
		optionsPane.getChildren().add(deck);
	}

	private void erstelleOptions() {
		
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
		nameLabel.setLabelFor(name);

		// ComboBox mit Label:
		adversary1 = new ComboBox<String>();
		adversary1.getItems().addAll(adversary);
		adversary1.getSelectionModel().select(1);
		adversary1Label.setLabelFor(adversary1);

		// Combobox adversary2 mit Label:
		adversary2 = new ComboBox<String>();
		adversary2.getItems().addAll(adversary);
		adversary2.getSelectionModel().select(1);
		adversary2Label.setLabelFor(adversary2);

		// Combobox Skatvariante mit Label
		skatVariant = new ComboBox<String>();
		skatVariant.getItems().addAll(variant);
		skatVariant.getSelectionModel().select(0);
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
	 * l&auml;dt das Logo und gibt es aus
	 */
	public void logo() {
		
//		new ImageIcon(getFileUrl("img/bild.jpeg"))
		
//		ClassLoader cl = this.getClass().getClassLoader();
//		this.stage.getIcons().add(new Image(cl.getResource("res/test.png").openStream()));
		
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
	
	private void quit() {
		
	}
}
