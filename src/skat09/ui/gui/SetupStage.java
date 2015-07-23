package skat09.ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import skat09.Messages;
import skat09.player.PlayerEnum;
import skat09.ui.GUIOutput;

/**
 * The skat entry setp window.
 * 
 * @since 21.07.2015 21:18:01
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class SetupStage extends Stage implements EventHandler<Event> {
	
	private Pane setupPane;
	private Pane optionsPane;
	private Pane logoPane;
	private ComboBox<String> adversary1;
	private ComboBox<String> adversary2;
	private Text name;
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
	public SetupStage(GUIOutput output) {
		
//        Scene scene = new Scene(new Group(new Text(25, 25, "Hello World!"))); 

        setTitle(Messages.getI18n("application.name")); 
//        stage.setScene(scene); 
//        stage.sizeToScene(); 
//        stage.show();

		this.output = output;
		
		init();

		try {
			ClassLoader cl = this.getClass().getClassLoader();
			getIcons().add(new Image(cl.getResource("res/test.png").openStream()));
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int top = (int) ((screenSize.height - this.getHeight()) / 2);
		int left = (int) ((screenSize.width - this.getWidth()) / 2);
//		setLocation(left, top);
		setX(left);
		setY(top);
		setResizable(false);
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
        setScene(scene);
        setTitle("Layout Sample");
        show();
	}

	private void setzteOptionen() {
		// TODO Auto-generated method stub
		
	}

	private void erstelleOptions() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * l&auml;dt das Logo und gibt es aus
	 */
	public void logo() {
		
//		new ImageIcon(getFileUrl("img/bild.jpeg"))
		Image image = new Image(getClass().getResourceAsStream("img/bild.jpeg"));
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

		return SetupStage.class.getClassLoader().getResource(path);
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
