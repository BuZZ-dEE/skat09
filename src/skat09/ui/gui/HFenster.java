package skat09.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import skat09.Tisch;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Spielkarte;
import skat09.test.interfaces.ISpieler;
import skat09.ui.GUIausgabe;


/**
 * Die Klasse beinhaltet das Hauptfenster der GUI. In diesem Fenster wird der
 * Spielablauf f&uuml;r den menschlichen Spieler dargestellt
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 02.07.09
 */
public class HFenster extends JFrame implements ActionListener, MouseListener,
		KeyListener {

	/**
	 * Das JPanel, das die Karten der Gegner darstellt
	 */
	private JPanel gegnerHand;
	/**
	 * JPanel, auf dem die momentan gespielten Karten dargestellt werden
	 */
	private JPanel aufTisch;
	/**
	 * Panel, auf dem die Karten des menschlichen Spielers dargestellt werden
	 */
	private JPanel aufHand;

	/**
	 * Punkte-Panel auf der rechen Seite
	 */
	private JPanel rechteSeite;
	/**
	 * Status-Panel auf der linken Seite
	 */
	private JPanel linkeSeite;
	/**
	 * markiert die gew&auml;hlte Karte
	 */
	private JLabel gewaehlt;

	/**
	 * Diese Komponente beinhaltet das Blatt des ersten Gegners
	 */
	private JLayeredPane pane1;
	/**
	 * Diese Komponente beinhaltet das Blatt des zweiten Gegners
	 */
	private JLayeredPane pane2;
	/**
	 * Diese Komponente beinhaltet den Tisch und die Infobox
	 */
	private JPanel mitte;
	/**
	 * Die Infobox beinhaltet den Ausgang des letzten Spiels
	 */
	private JPanel infobox;

	/**
	 * Der Tisch, auf dem das Spiel stattfindet
	 */
	private Tisch tisch;
	/**
	 * boolean der angibt wer zur Zeit spielt
	 */
	private boolean zug = false;

	/**
	 * Ausgabe, durch die die Interaktion mit dem menschlichen Spieler
	 * stattfindet
	 */
	private GUIausgabe ausgabe;

	/**
	 * dient zur identifizierung der vom Spieler gew&auml;hlten Karte
	 */
	private int gewaehlteKarte = 0;
	/**
	 * speichert die ehemals gew&auml;hlte Karte
	 */
	private int altegewaehlteKarte = 0;

	/**
	 * gibt die gew&auml;hlte Spielart aus
	 */
	private String spielart;

	/**
	 * erfasst, ob handspiel erw&uuml;nscht ist
	 */
	private boolean handspiel = false;
	/**
	 * erfasst, ob schwarz erw&uuml;nscht ist
	 */
	private boolean schwarz = false;
	/**
	 * erfasst, ob ouvert erw&uuml;nscht ist
	 */
	private boolean ouvert = false;
	/**
	 * erfasst, ob schneider erw&uuml;nscht ist
	 */
	private boolean schneider = false;
	/**
	 * erfasst, ob ein reizagent erw&uuml;nscht ist
	 */
	private boolean reizagent = false;
	/**
	 * erfasst, in welchem Zustand des Reizens sich der menschliche Spieler
	 * befindet - in diesem Fall sagen
	 */
	private boolean sagen = false;
	/**
	 * erfasst, in welchem Zustand des Reizens sich der menschliche Spieler
	 * befindet - in diesem Fall h&ouml;ren
	 */
	private boolean hoeren = false;
	/**
	 * h&auml;lt das Reizlimit, falls mit Reizagent gespielt wird
	 */
	private int reizlimit = 0;
	/**
	 * erfasst, ob nur spielbare Karten angezeigt werden sollen
	 */
	private boolean spielbarhilfe = false;
	/**
	 * erfasst, ob die ehemaligen Stiche angezeigt werden sollen
	 */
	private boolean stichehilfe = false;
	/**
	 * Beinhaltet die bereits gespielten Karten
	 */
	private Spielkarte[] gespielteKarten = null;
	/**
	 * Beinhaltet die gewonnenen Augen des Alleinspielers
	 */
	private int augen;

	// Fuer Menue:
	/**
	 * Die Men&uuml;leiste des Programms
	 */
	private JMenuBar menuBar;
	/**
	 * Das Men&uuml; des Fensters
	 */
	private JMenu menu;
	/**
	 * Das MenuItem, das das Spiel beenden soll
	 */
	private JMenuItem menuItem;
	/**
	 * Die Checkbox im Men&uuml;, in der ausgew&auml;hlt werden kann, ob die
	 * spielbaren Karten angezeigt werden sollen
	 */
	private JCheckBoxMenuItem cbMenuItem;
	/**
	 * Die Checkbox im Men&uuml;, in der ausgew&auml;hlt werden kann, ob die
	 * vergangenen Stiche angezeigt werden sollen
	 */
	private JCheckBoxMenuItem cbMenuItem2;
	/**
	 * Das Panel rechteSeite soll scrollbar sein
	 */
	private JScrollPane scroller;
	/**
	 * Das Fenster Stiche wird gezeigt, falls der Benutzer die Hilfe
	 * "Vergangene Stiche anzeigen" ausgew&auml;hlt hat
	 */
	private Stiche stichfenster;

	/**
	 * Der Konstruktor des HFensters, der das Fenster erstellt und die
	 * Komponenten einrichtet.
	 * 
	 * @param tisch
	 *            Der Tisch, auf dem gespielt wird
	 * @param ausgabe
	 *            Die GUIausgabe, die genutzt wird
	 */
	public HFenster(Tisch tisch, GUIausgabe ausgabe) {

		super("Skat 09");
		this.ausgabe = ausgabe;
		this.tisch = tisch;

		setResizable(false);
		stichfenster = null;
		// setzt das Layout und aehnliches
		init();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension frameSize = this.getSize();
		// Position des JFrames errechnen
		int top = (screenSize.height - frameSize.height) / 2;
		int left = (screenSize.width - frameSize.width) / 2;
		// Größe zuordnen
		// setSize(frameSize);

		// Position zuordnen
		setLocation(left, top);
		setVisible(true);
	}

	/**
	 * Die Methoden liefert den Wert der Variable sagen. Dieser soll true sein,
	 * falls der Spieler den aktuellen Reizwert sagt und false, falls der
	 * Spieler weg ist.
	 * 
	 * @return Den Wert von sagen
	 */
	public boolean getSagen() {
		boolean erg = sagen;
		sagen = false;
		return erg;
	}

	/**
	 * Die Methoden liefert den Wert der Variable hoeren. Dieser soll true sein,
	 * falls der Spieler den aktuellen Reizwert mitgeht und false, falls der
	 * Spieler weg ist.
	 * 
	 * @return Den Wert von hoeren
	 */
	public boolean getHoeren() {
		boolean erg = hoeren;
		hoeren = false;
		return erg;
	}

	/**
	 * Diese Methode setzt den Wert der Variable zug. zug soll true sein, falls
	 * der menschliche Spieler einen Zug machen muss und false, falls er gerade
	 * nicht am zug ist.
	 * 
	 * @param zug
	 *            Der Wert von zug
	 */
	public void setZug(boolean zug) {
		this.zug = zug;
	}

	/**
	 * Gibt den Index der Karte zur&uuml;ck, die vom Menschen gew&auml;hlt
	 * wurde.
	 * 
	 * @return die gew&auml;hlte Karte
	 */
	public int getGewaehlteKarte() {
		return gewaehlteKarte;
	}

	/**
	 * Gibt die Farbe zur&uuml;ck, die der Mensch ausgew&auml;hlt hat.
	 * 
	 * @return Die gew&auml;hlte Farbe
	 */
	public String getFarbe() {
		return spielart;
	}

	/**
	 * Gibt die Spielart zur&uuml;ck, die der Mensch ausgew&auml;hlt hat.
	 * 
	 * @return Die gew&auml;hlte Spielart
	 */
	public String getSpielart() {
		return spielart;
	}

	/**
	 * Gibt den Wert der Variable handspiel zur&uuml;ck. Diese ist true, falls
	 * der Mensch Handspiel w&uuml;nscht, ansonsten false.
	 * 
	 * @return Den Wert von handspiel
	 */
	public boolean getHandspiel() {
		return handspiel;
	}

	/**
	 * Gibt den Wert der Variable schneider zur&uuml;ck. Diese ist true, falls
	 * der Mensch Schneider ansagt, ansonsten false.
	 * 
	 * @return Den Wert von schneider
	 */
	public boolean getSchneider() {
		return schneider;
	}

	/**
	 * Gibt den Wert der Variable schwarz zur&uuml;ck. Diese ist true, falls der
	 * Mensch Schwarz ansagt, ansonsten false.
	 * 
	 * @return Den Wert von schwarz
	 */
	public boolean getSchwarz() {
		return schwarz;
	}

	/**
	 * Gibt den Wert der Variable ouvert zur&uuml;ck. Diese ist true, falls der
	 * Mensch Ouvert ansagt, ansonsten false.
	 * 
	 * @return Den Wert von ouvert
	 */
	public boolean getOuvert() {
		return ouvert;
	}

	/**
	 * Gibt den Wert der Variable reizagent zur&uuml;ck. Diese ist true, falls
	 * der Mensch den Reizagenten nutzen will, ansonsten false.
	 * 
	 * @return Den Wert von reizagent
	 */
	public boolean getAgent() {
		return reizagent;
	}

	/**
	 * Gibt den Wert der Variable reizlimit zur&uuml;ck.
	 * 
	 * @return Den Wert von reizlimit
	 */
	public int getReizlim() {
		return reizlimit;
	}

	/**
	 * setzt die Augen
	 * @param augen
	 *            
	 */
	public void setAugen(int augen) {
		this.augen = augen;
	}

	/**
	 * Setzt die bereits gespielten Karten.
	 * 
	 * @param gespielteKarten
	 *            Die Karten, die bereits gespielt wurden.
	 */
	public void setGespielteKarten(Spielkarte[] gespielteKarten) {
		this.gespielteKarten = gespielteKarten;
	}

	/**
	 * In dieser Methode werden die verwendenden Komponenten initialisiert und
	 * Einstellungen vorgenommen
	 */
	public void init() {

		this.addKeyListener(this);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 600));
		setResizable(false);
		linkeSeiteInit();
		mitteInit();
		rechteSeiteInit();
		gegnerHandInit();
		aufHandInit();
		erstelleMenu();

	}

	/**
	 * Initialisiert das Feld, das die Bl&auml;tter der Gegner beinhaltet
	 */
	public void gegnerHandInit() {

		gegnerHand = new JPanel();
		gegnerHand.setFocusable(false);
		gegnerHand.setLayout(new FlowLayout());
		gegnerHand.setBorder(BorderFactory.createTitledBorder("Gegner"));
		add(gegnerHand, BorderLayout.NORTH);
	}

	/**
	 * Initialisiert die Hand des menschlichen Spielers
	 */
	public void aufHandInit() {
		aufHand = new JPanel();

		aufHand.setBorder(BorderFactory.createTitledBorder("Ihr Blatt "));
		aufHand.setPreferredSize(new Dimension(960, 150));
		aufHand.setFocusCycleRoot(false);
		aufHand.addKeyListener(this);

		add(aufHand, BorderLayout.SOUTH);
	}

	/**
	 * Initialisiert die Spielinformationen
	 */
	public void linkeSeiteInit() {
		linkeSeite = new JPanel();
		linkeSeite.setFocusable(false);
		linkeSeite.setLayout(new BoxLayout(linkeSeite, BoxLayout.PAGE_AXIS));
		linkeSeite.setBorder(BorderFactory.createTitledBorder("Spielinfos"));
		linkeSeite.setPreferredSize(new Dimension(150, 300));

		add(linkeSeite, BorderLayout.WEST);
	}

	/**
	 * Initialisiert das Auswertungsfeld auf der GUI
	 */
	public void rechteSeiteInit() {

		rechteSeite = new JPanel();
		rechteSeite.setFocusable(false);
		rechteSeite.setLayout(new GridBagLayout());
		rechteSeite.setBorder(BorderFactory.createTitledBorder("Auswertung:"));
		rechteSeite.setPreferredSize(new Dimension(400, 150));
		scroller = new JScrollPane(rechteSeite);
		scroller.setPreferredSize(new Dimension(150, 300));

		add(scroller, BorderLayout.EAST);

	}

	/**
	 * Initialisiert das Feld, in das die gespielten Karten kommen und die
	 * Infobox
	 */
	public void mitteInit() {
		mitte = new JPanel();
		mitte.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridheight = 4;
		infobox = new JPanel();
		infobox.setLayout(new FlowLayout());
		aufTisch = new JPanel();
		aufTisch.addMouseListener(this);
		aufTisch.setFocusable(false);
		aufTisch.setLayout(new FlowLayout());

		infobox.setBorder(BorderFactory.createTitledBorder("Spielergebnis:"));
		aufTisch.setPreferredSize(new Dimension(500, 120));
		infobox.setPreferredSize(new Dimension(500, 50));

		add(mitte, BorderLayout.CENTER);
		mitte.add(aufTisch, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		mitte.add(infobox, c);
		mitte.setPreferredSize(new Dimension(500, 400));

	}

	/**
	 * Diese Methode gibt das Blatt des Spielers in der GUI aus.
	 * 
	 * @param spieler
	 *            Der Spieler, dessen Blatt ausgegeben werden soll.
	 * @throws IOException
	 */
	public void blattAusgeben(ISpieler spieler) throws IOException {

		aufHand.removeAll();
		aufHand.setBorder(BorderFactory.createTitledBorder("Ihr Blatt: "
				+ spieler.getPosition().toString()));

		for (Spielkarte karte : spieler.getBlatt()) {

			Image image;
			if (spielbarhilfe == true && spieler.getSpielart() != null
					&& gespielteKarten != null
					&& spieler.spielbareKarten(gespielteKarten).contains(karte)) {

				image = new ImageIcon(Fenster.getFileUrl("img/"
						+ karte.dateiPfad() + "s.png")).getImage();

			} else {

				image = new ImageIcon(Fenster.getFileUrl("img/"
						+ karte.dateiPfad() + ".png")).getImage();

			}

			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(70,
					110, 1)));
			label.addMouseListener(this);
			label.setFocusable(false);

			aufHand.add(label);
		}
		aufHand.setVisible(true);
		aufHand.setFocusCycleRoot(false);
		pack();
		// this.repaint();
		aufHand.repaint();

	}

	/**
	 * Diese Methode gibt den Skat auf der Hand des Spielers aus.
	 * 
	 * @param skat
	 *            Die im Skat liegenden Karten
	 * @throws IOException
	 */
	public void skatAusgeben(Spielkarte[] skat) throws IOException {

		for (int i = 0; i < 2; i++) {

			Image image;

			image = new ImageIcon(Fenster.getFileUrl("img/"
					+ skat[i].dateiPfad() + ".png")).getImage();

			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(70,
					110, 1)));
			aufHand.add(label);

		}
		add(aufHand, BorderLayout.SOUTH);

	}

	/**
	 * Diese Methode gibt die aktuelle Puntkeliste auf der GUI aus.
	 * 
	 * @param gewonnen
	 *            Ob das Spiel gewonnen wurde oder nicht.
	 */
	public void auswertung(boolean gewonnen) {

		JLabel label;
		JLabel label1;
		JLabel label2;
		JLabel punkte;
		JLabel punkte1;
		JLabel punkte2;
		JLabel punkt = new JLabel("Punkte:");
		JLabel name = new JLabel("Name:");
		GridBagConstraints c = new GridBagConstraints();

		// Alle Elemente entfernen und sichtbar machen
		rechteSeite.removeAll();
		rechteSeite.setVisible(true);

		// Die Position des ersten Elements festlegen
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		ISpieler spieler1 = tisch.getSpieler1();
		label = new JLabel(spieler1.getName());
		punkte = new JLabel(tisch.getAktuellePunkte(spieler1) + "");

		ISpieler spieler2 = tisch.getSpieler2();
		label1 = new JLabel(spieler2.getName());
		punkte1 = new JLabel(tisch.getAktuellePunkte(spieler2) + "");

		ISpieler spieler3 = tisch.getSpieler3();
		label2 = new JLabel(spieler3.getName());
		punkte2 = new JLabel(tisch.getAktuellePunkte(spieler3) + "");

		setzeStats(name, 0, 0);
		setzeStats(punkt, 0, 1);
		setzeStats(label, 1, 0);
		setzeStats(punkte, 1, 1);
		setzeStats(label1, 2, 0);
		setzeStats(punkte1, 2, 1);
		setzeStats(label2, 3, 0);
		setzeStats(punkte2, 3, 1);

		pack();
		auswertung2(gewonnen);
		hoeren = false;
		sagen = false;
		reizagent = false;
		handspiel = false;
		schneider = false;
		schwarz = false;
		ouvert = false;
	}

	/**
	 * Diese Methode gibt die im letzten Spiel erreichten Punkte und die
	 * erreichte Augenzahl in der Infobox auf der GUI aus.
	 * 
	 * @param gewonnen
	 *            Ob das Spiel gewonnen wurde oder nicht
	 */
	public void auswertung2(boolean gewonnen) {

		JLabel spiel = new JLabel();
		Point origin = new Point(0, 0);
		// JLabel punkte = new JLabel();

		infobox.removeAll();

		if (gewonnen
				&& tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			spiel.setText(""
					+ tisch.ermittleAlleinspieler().getName()
					+ " hat mit "
					+ tisch.ermittleAlleinspieler().getSpiele()
							.get(
									tisch.ermittleAlleinspieler().getSpiele()
											.size() - 1)
					+ " Punkten gewonnen und " + augen + " Augen erspielt");
		}
		if (!gewonnen
				&& tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			spiel.setText(""
					+ tisch.ermittleAlleinspieler().getName()
					+ " hat mit "
					+ tisch.ermittleAlleinspieler().getSpiele()
							.get(
									tisch.ermittleAlleinspieler().getSpiele()
											.size() - 1)
					+ " Punkten verloren und " + augen + " Augen erspielt");
		}
		spiel.setText(ramschAuswertung(gewonnen));

		spiel.setLocation(origin);
		infobox.add(spiel);
		pack();
		mitte.repaint();
	}
	
	/**
	 * Regelt die Ausgabe falls Ramsch gespielt wird - dient zur MLOC Einhaltung
	 * @param gewonnen 
	 * @return String
	 */
	public String ramschAuswertung(boolean gewonnen) {
		String s = "";
		if (gewonnen
				&& tisch.getSpielart().getSpielart() == Spielartbezeichnung.RAMSCH) {
			s = "Sie haben gewonnen! Sie haben "
					+ tisch.gibMenschlicherSpieler().getSpiele()
							.get(
									tisch.gibMenschlicherSpieler().getSpiele()
											.size() - 1) + " Punkte erreicht!";
		}
		if (!gewonnen
				&& tisch.getSpielart().getSpielart() == Spielartbezeichnung.RAMSCH) {
			s = "Sie haben verloren! Sie haben "
					+ tisch.gibMenschlicherSpieler().getSpiele()
							.get(
									tisch.gibMenschlicherSpieler().getSpiele()
											.size() - 1) + " Punkte verloren!";
		}
		return s;
	}

	/**
	 * Die Methode bestimmt anhand der globalen Variable gewaehlt, welchen Index
	 * die vom Menschen gew&auml;hlte Karte auf der Hand hat.
	 * 
	 * @return Die Karte, die vom menschlichen Spieler von seiner Hand
	 *         gew&auml;hlt wurde
	 */
	public int handkarte() {
		int erg = -1;
		for (int i = 0; i < aufHand.getComponentCount(); i++) {
			if (aufHand.getComponent(i) == gewaehlt) {
				erg = i;
			}
		}
		return erg;
	}

	/**
	 * Diese Methode wird aufgerufen, falls der menschliche Spieler eine Karte
	 * ausgew&auml;hlt hat, die er eigentlich nicht spielen d&uuml;rfte. Es wird
	 * ein Dialogfenster dargestellt, welches den menschlichen Spieler darauf
	 * hinweist.
	 */
	public void andereKarte() {
		// JOptionPane ok = new JOptionPane();

		JOptionPane.showMessageDialog(null,
				"Falsche Auswahl! Andere Karte waehlen!");
		aufTisch.removeAll();
		aufHand.setFocusCycleRoot(false);
	}

	/**
	 * Diese Methode bittet den Menschen zur Angabe eines maximalen Reizwertes
	 * und pr&uuml;ft, ob das Ergebnis ein gueltiger Integerwert ist. Der
	 * Reizwert wird in der globalen Variable reizlimit gespeichert. Falls die
	 * Eingabe ung&uuml;ltig war, wird -1 in reizlimit geschrieben.
	 * Anschliessend wird die Ausgabe wiedergelassen.
	 */
	public void reizLimfest() {
		String s = (String) JOptionPane.showInputDialog(null,
				"Bitte geben Sie einen Reizwert an:");

		// If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			int zahl = -1;
			try {
				zahl = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Kein Integer bei Reizagent!");
				reizlimit = -1;
				// e.printStackTrace();
			}
			reizlimit = zahl;
		} else {
			System.out.println("Fehler beim Reizagenten!");
			reizlimit = -1;
		}

		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode zeigt dem Menschen ein Dialogfeld mit den Optionen Ja und
	 * Nein. Er soll entscheiden, ob er Hand spielen will oder nicht. Wird Ja
	 * gew&auml;hlt, wird die globale Variable handspiel true gesetzt, ansonsten
	 * bleibt sie false. Anschliessend wird die Ausgabe wiedergelassen.
	 */
	public void handspiel() {
		handspiel = false;

		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null, "Wollen Sie Hand spielen?", // question
				"Handspiel", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			handspiel = true;
		}
		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode zeigt dem Menschen ein Dialogfeld mit den Optionen Ja und
	 * Nein. Er soll entscheiden, ob er Schneider spielen will oder nicht. Wird
	 * Ja gew&auml;hlt, wird die globale Variable schneider true gesetzt,
	 * ansonsten bleibt sie false. Anschliessend wird die Ausgabe
	 * wiedergelassen.
	 */
	public void schneider() {
		schneider = false;
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null,
				"Wollen Sie Schneider ansagen?", // question
				"Schneider", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			schneider = true;
		}
		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode zeigt dem Menschen ein Dialogfeld mit den Optionen Ja und
	 * Nein. Er soll entscheiden, ob er Schwarz spielen will oder nicht. Wird Ja
	 * gew&auml;hlt, wird die globale Variable schwarz true gesetzt, ansonsten
	 * bleibt sie false. Anschliessend wird die Ausgabe wiedergelassen.
	 */
	public void schwarz() {
		schwarz = false;
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null,
				"Wollen Sie Schwarz ansagen?", // question
				"Schwarz", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			schwarz = true;
		}
		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode zeigt dem Menschen ein Dialogfeld mit den Optionen Ja und
	 * Nein. Er soll entscheiden, ob er Ouvert spielen will oder nicht. Wird Ja
	 * gew&auml;hlt, wird die globale Variable ouvert true gesetzt, ansonsten
	 * bleibt sie false. Anschliessend wird die Ausgabe wiedergelassen.
	 */
	public void ouvert() {
		ouvert = false;
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null,
				"Wollen Sie ouvert spielen?", // question
				"Ouvert", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			ouvert = true;
		}
		ausgabe.setRelease(true);
	}

	/**
	 * Zeigt dem Menschen ein Dialogfenster, in dem er die Spielart
	 * ausgew&auml;hlen soll, die er als Alleinspieler spielen m&ouml;chte. Das
	 * Ergebnis der Auswahl wird in der globalen Variable spielart gespeichert.
	 * Danach wird die Ausgabe wieder freigegeben.
	 */
	public void ansagen() {
		aufHand.repaint();
		String[] spielart = { "Karo", "Herz", "Pik", "Kreuz", "Grand", "Null" };

		if (Spielkarte.getDeutsch()) {
			spielart[0] = "Schellen";
			spielart[1] = "Herz";
			spielart[2] = "Gruen";
			spielart[3] = "Eichel";
		}

		String spielarte = (String) JOptionPane.showInputDialog(null,
				"Spielart", "Bitte die Spielart waehlen!",
				JOptionPane.QUESTION_MESSAGE, null, spielart, spielart[0]);
		this.spielart = spielarte;
		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode zeigt ein Dialogfeld an, in dem der Mensch dar&uuml;ber
	 * informiert wird, wer Alleinspieler ist.
	 */
	public void alleinspieler() {
		JLabel text = new JLabel(tisch.ermittleAlleinspieler().getName()
				+ " spielt:");
		linkeSeite.add(text);
		pack();
		linkeSeite.repaint();
	}

	/**
	 * Mit dieser Methode wird die gerade gespielte Spielart und die
	 * Erg&auml;nzungen, wie Hand oder Schneider, in den Spielinformationen auf
	 * der linken Seite des Fensters angezeigt.
	 */
	public void trumpf() {
		String s = "";
		if (tisch.getOuvert()) {
			s = s + "Ouvert ";
		} else if (tisch.getSchwarz()) {
			s = s + "Schwarz ";
		} else if (tisch.getSchneider()) {
			s = s + "Schneider ";
		}
		if (tisch.getHandspiel()) {
			s = s + "Hand";
		}
		String spielart = tisch.getSpielart().toString();
		if (Spielkarte.getDeutsch()) {
			if (spielart.compareTo("KARO") == 0) {
				spielart = "Schellen";
			}
			if (spielart.compareTo("PIK") == 0) {
				spielart = "Gruen";
			}
			if (spielart.compareTo("KREUZ") == 0) {
				spielart = "Eichel";
			}
		}
		JLabel text2 = new JLabel(s);
		JLabel text = new JLabel(spielart);

		linkeSeite.add(text);
		linkeSeite.add(text2);
		pack();
	}

	/**
	 * In dieser Methode wird ein Dialogfenster mit der Nachricht:
	 * "Das Spiel beginnt!" angezeigt.
	 */
	public void spielBeginnt() {

		JOptionPane.showMessageDialog(null, "Das Spiel beginnt!");

	}

	/**
	 * Diese Methode zeigt ein Dialogfenster an, in dem der Name des Spielers
	 * angezeigt wird, der den letzten Stich gewonnen hat.
	 * 
	 * @param spieler
	 *            Der Spieler, der den Stich gewonnen hat.
	 */
	public void stichGewonnen(ISpieler spieler) {
		
		JOptionPane.showMessageDialog(null, spieler.getName()
				+ " hat den Stich gewonnen!");
		aufTisch.removeAll();
		pack();

		if (stichfenster != null) {
			stichfenster.update();
		}
	}

	/**
	 * Diese Methode zeichnet die von einem Gegner gespielte Karte auf den
	 * Tisch.
	 * 
	 * @param karte
	 *            Die Karte, die der Gegner gespielt hat.
	 */
	public void spieltKarte(Spielkarte karte) {
		Image image = null;

		image = new ImageIcon(Fenster.getFileUrl("img/" + karte.dateiPfad()
				+ ".png")).getImage();

		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(70,
				110, 1)));

		// label.setBorder(BorderFactory.createTitledBorder(karte.getBesitzer().getName()
		// + "spielte:"));
		aufTisch.add(label);
		pack();
		aufTisch.repaint();
		aufHand.setFocusCycleRoot(false);
	}

	/**
	 * Zeichnet die vom menschlichen Spieler vorher ausgw&auml;hlte Karte auf
	 * den Tisch und entfernt sie aus dem Blatt.
	 */
	public void menschSpieltKarte() {
		aufTisch.add(aufHand.getComponent(gewaehlteKarte));
		aufHand.repaint();
		pack();
	}

	/**
	 * Diese Methode zeigt dem Menschen ein Dialogfenster, welches die
	 * Auswahlm&ouml,glichkeiten Ja und Nein bietet. Der Mensch kann hierbei
	 * entscheiden, ob er den Reizagenten nutzen m&ouml;chte oder nicht. Hat er
	 * Ja ausgew&auml;hlt, wird die globale Variable reizagent auf true gesetzt.
	 */
	public void reizagent() {
		// Das Fenster soll zentriert werden
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension frameSize = this.getSize();
		// Position des JFrames errechnen
		int top = (screenSize.height - frameSize.height) / 2;
		int left = (screenSize.width - frameSize.width) / 2;
		// Größe zuordnen
		// setSize(frameSize);

		// Position zuordnen
		setLocation(left, top);

		pack();
		reizagent = false;
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null,
				"Wollen Sie den Reizagenten nutzen ?", // question
				"Reizagent", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			reizagent = true;
		}
		ausgabe.setRelease(true);

	}

	/***
	 * Diese Methode wird aufgerufen, wenn der Mensch einem anderen Mitspieler
	 * einen Reizwert sagen soll. Hierbei wird ihm der n&auml;chste Reizwert
	 * angeboten und er kann mit Ja best&auml;tigen oder mit Nein weg sein. Hat
	 * er den Reizwert best&auml;tigt, wird die globale Variable sagen auf true
	 * gesetzt.
	 * 
	 * @param reizwert
	 *            Der n&auml;chste m&ouml;gliche Reizwert.
	 */
	public void sagen(int reizwert) {
		pack();
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null, "Sagen Sie " + reizwert
				+ "?", // question
				"Sagen", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			sagen = true;
		}
		ausgabe.setRelease(true);
	}

	/***
	 * Diese Methode wird aufgerufen, wenn der Mensch von einem anderen
	 * Mitspieler einen Reizwert h&ouml;ren soll. Hierbei wird ihm der gebotene
	 * Reizwert angeboten und er kann mit Ja best&auml;tigen oder mit Nein weg
	 * sein. Hat er den Reizwert best&auml;tigt, wird die globale Variable
	 * hoeren auf true gesetzt.
	 * 
	 * @param reizwert
	 *            Der gebotene Reizwert.
	 */
	public void hoeren(int reizwert) {
		pack();
		String[] yesNoOptions = { "Ja", "Nein" };

		int n = JOptionPane.showOptionDialog(null, "Es wird " + reizwert
				+ " gereizt. Gehen Sie mit?", // question
				"Hoeren", // title
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // icon
				null, yesNoOptions, yesNoOptions[0]);

		if (n == JOptionPane.YES_OPTION) {
			hoeren = true;
		}
		ausgabe.setRelease(true);
	}

	/**
	 * Diese Methode wird von dem Interface Actionlistener &uuml;berschrieben.
	 * Diese Methode wird aufgerufen, falls ein ActionEvent ausgel&ouml;st
	 * wurde. In der Klasse HFenster werden ActionEvents nur von dem Men&uuml;
	 * benutzt. Die Quelle des Events wird ermittelt und anhand der Texte der
	 * Items wird entschieden, von welchem Men&uuml;item das Event gesendet
	 * wurde. Anhand dessen wird entweder eine Hilfe ausgew&auml;hlt oder das
	 * Fenster geschlossen.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) (e.getSource());
		if (item.getText() == "Spiel beenden") {
			System.exit(1);
		} else if (item.getText() == "Spielbare Karten zeigen") {
			if (!spielbarhilfe) {
				spielbarhilfe = true;
			} else {
				spielbarhilfe = false;
			}
		} else if (item.getText() == "Vergangene Stiche anzeigen") {
			if (!stichehilfe) {
				stichfenster = new Stiche(tisch);
				stichehilfe = true;
			} else {
				stichfenster.schliessen();
				stichfenster = null;
				stichehilfe = false;
			}
		}
	}

	/**
	 * Das Blatt des Spielers wird neu gezeichnet.
	 * 
	 * @param spieler
	 *            Der Spieler, dessen Blatt neu gezeichnet werden soll
	 */
	public void blattneu(ISpieler spieler) {
		aufHand.removeAll();
		pack();
		try {
			blattAusgeben(spieler);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Diese Methode veranlasst, dass der Tisch aufger&auml;umt wird. Alle auf
	 * ihm befindlichen Komponenten werden gel&ouml;scht.
	 */
	public void tischRaumen() {
		aufTisch.removeAll();
		pack();
	}

	/**
	 * Wird ausgef&uuml;hrt, falls eine Komponente mit der Maus angeklickt
	 * wurde.
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() > 1) {
			if (e.getComponent().getParent() == aufHand && zug) {
				gewaehlt = (JLabel) e.getComponent();
				gewaehlteKarte = handkarte();

				ausgabe.setRelease(true);
				pack();
			}

		}

	}

	/**
	 * Die Methode beschreibt das Verhalten bei Eintritt der Maus in eine
	 * Komponente
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Verlassen der Maus eine
	 * Komponente
	 */
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Dr&uuml;cken der Maustaste in
	 * einer Komponente
	 */
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Loslassen der Maustaste in
	 * einer Komponente
	 */
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Erstellt ein JLayeredPane mit so vielen Kartenr&uuml;cken, wie der
	 * Spieler noch Karten auf der Hand hat
	 * 
	 * @param spieler
	 *            Der Spieler, dessen Kartenr&uuml;cken ausgegeben werden
	 *            sollen.
	 * @return Gibt die JLayeredPane zur&uuml;ck, auf der die Kartenr&uuml;cken
	 *         angezeigt werden.
	 */
	public JLayeredPane kartenruecken(ISpieler spieler) {
		JLayeredPane layeredPane;
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(450, 150));
		layeredPane.setBorder(BorderFactory.createTitledBorder(""
				+ spieler.getName() + ": " + spieler.getPosition().toString()));
		// Dies ist der Punkt, von dem aus das erste Element gezeichnet wird
		Point origin = new Point(10, 20);

		// Dies ist der Offset, der benutzt wird um das naechste Label zu
		// zeichnen
		int offset = 35;

		if (spieler.getIstAlleinspieler() && tisch.getOuvert()) {
			for (int i = 0; i < spieler.getBlatt().size(); i++) {
				Spielkarte karte = spieler.getBlatt().get(i);
				Image image;

				image = new ImageIcon(Fenster.getFileUrl(
						"img/" + karte.dateiPfad() + ".png").getPath())
						.getImage();

				JLabel label = new JLabel(new ImageIcon(image
						.getScaledInstance(70, 110, 1)));
				layeredPane.add(label);// , new Integer(i)
				layeredPane.getComponent(i).setBounds(origin.x, origin.y, 70,
						110);
				layeredPane.getComponent(i).setLocation(origin);
				origin.x = origin.x + offset;
			}
		} else {

			for (int i = 0; i < spieler.getBlatt().size(); i++) {
				Image image = null;

				image = new ImageIcon(Fenster
						.getFileUrl("img/kartenruecken.png")).getImage();

				JLabel label = new JLabel(new ImageIcon(image
						.getScaledInstance(70, 110, 1)));
				layeredPane.add(label);// , new Integer(i)
				layeredPane.getComponent(i).setBounds(origin.x, origin.y, 70,
						110);
				layeredPane.getComponent(i).setLocation(origin);
				origin.x = origin.x + offset;

			}
		}

		return layeredPane;

	}

	/**
	 * Erstellt das Men&uuml;.
	 */
	public void erstelleMenu() {
		// Die MenuBar wird initialisiert:
		menuBar = new JMenuBar();

		// Spielmenue, fuer verlassen, speichern...
		menu = new JMenu("Spiel");
		menu.setMnemonic(KeyEvent.VK_S);
		menu.getAccessibleContext().setAccessibleDescription("Spielmenue");
		menuBar.add(menu);

		// Um das Spiel zu beenden, dieser Menuepunkt.
		menuItem = new JMenuItem("Spiel beenden", KeyEvent.VK_B);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Spiel beenden");

		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Das Hilfemenue, enthaelt vorhandene Spielhilfen
		menu = new JMenu("Hilfe");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("Hilfen");
		menuBar.add(menu);

		// Hilfe: Spielbare Karten zeigen als Checkbox
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("Spielbare Karten zeigen");
		cbMenuItem.setMnemonic(KeyEvent.VK_K);
		cbMenuItem.addActionListener(this);
		menu.add(cbMenuItem);

		// Hilfe: Stiche Werten in neuem Fenster angezeigt
		cbMenuItem2 = new JCheckBoxMenuItem("Vergangene Stiche anzeigen");
		cbMenuItem2.setMnemonic(KeyEvent.VK_A);
		cbMenuItem2.addActionListener(this);
		menu.add(cbMenuItem2);

		this.setJMenuBar(menuBar);

	}

	/**
	 * Diese Methode stellt die Karten der Gegner auf der GUI dar.
	 * 
	 * @param spieler2
	 *            Der erste Gegener, dessen Karten dargestellt werden sollen.
	 * @param spieler3
	 *            Der zweite Gegener, dessen Karten dargestellt werden sollen.
	 */
	public void gegnerKarten(ISpieler spieler2, ISpieler spieler3) {
		gegnerHand.removeAll();
		pane1 = kartenruecken(spieler2);
		gegnerHand.add(pane1);
		pack();
		gegnerHand.repaint();
		gegnerHand.add(Box.createRigidArea(new Dimension(30, 0)));
		pane2 = kartenruecken(spieler3);
		gegnerHand.add(pane2);
		pack();
		gegnerHand.repaint();
	}

	/**
	 * Diese Methode r&auml;umt nach einem abgeschlossenen Spiel den Tisch auf.
	 */
	public void spielAufrauemen() {
		gegnerHand.removeAll();
		aufTisch.removeAll();
		aufHand.removeAll();
		linkeSeite.removeAll();
		pack();
		repaint();
	}

	/**
	 * Die Methode beschreibt das Verhalten beim Dr&uuml;cken einer Taste, wenn
	 * der Fokus auf der Komponente ist.
	 */
	public void keyPressed(KeyEvent arg0) {

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Loslassen einer Taste, wenn
	 * der Fokus auf der Komponente ist.
	 */
	public void keyReleased(KeyEvent e) {
		JLabel label = new JLabel();
		// Wenn der Spieler am Zug ist, wird diese Schleife betreten

		if (zug) {
			altegewaehlteKarte = gewaehlteKarte;

			// Wenn die alte gewahlte Karte nicht mehr im Blatt ist, wird sie 0
			// gesetzt.
			if ((aufHand.getComponentCount() - 1) < altegewaehlteKarte) {
				altegewaehlteKarte = 0;
			}

			// Wurde die linke Pfeiltaste gedrueckt, wird diese Schleife
			// betreten
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				gewaehlteKarte(true);
				
				label = (JLabel) aufHand.getComponent(gewaehlteKarte);
				label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				// Ist die alte gewaehlte Karte nicht gleich der neuen
				// gewaehlten Karte, wird der Rand der alten gewaehlten Karte
				// geloescht.
				if (altegewaehlteKarte != gewaehlteKarte) {
					label = (JLabel) aufHand.getComponent(altegewaehlteKarte);
					label.setBorder(null);
				}
				aufHand.repaint();
			}

			// Wurde die rechte Pfeiltaste gedrueckt, wird diese Schleife
			// betreten
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				gewaehlteKarte(false);
				
				label = (JLabel) aufHand.getComponent(gewaehlteKarte);
				label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

				// Ist die alte gewaehlte Karte nicht gleich der neuen
				// gewaehlten Karte, wird der Rand der alten gewaehlten Karte
				// geloescht.
				if (altegewaehlteKarte != gewaehlteKarte) {
					label = (JLabel) aufHand.getComponent(altegewaehlteKarte);
					label.setBorder(null);
				}
				aufHand.repaint();
			}

			// Wurde Enter gedrueckt, wird diese Schleife betreten
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Die gerade gewaehlte Karte wird als gewaehlte Karte gesetzt
				// und der Zug ist beendet
				label = (JLabel) aufHand.getComponent(gewaehlteKarte);
				label.setBorder(null);
				ausgabe.setRelease(true);

			}
			aufHand.setFocusCycleRoot(false);

			pack();
		}
	}

	/**
	 * Die Methode beschreibt das Verhalten beim Tippen einer Taste, wenn
	 * der Fokus auf der Komponente ist.
	 */
	public void keyTyped(KeyEvent e) {

	}
	
	/**
	 * Die Methode &auml;ndert die Variable gewaehlteKarte
	 * @param rechts falls die rechte Pfeiltaste genutzt wurde
	 */
	public void gewaehlteKarte(boolean rechts) {
		if (rechts) {
			// Ist die gewaehlte Karte an einem Platz kleiner als der
			// groesstmoegliche Index, wird der Index inkrementiert
			if ((aufHand.getComponentCount() - 1) > gewaehlteKarte) {
				gewaehlteKarte = gewaehlteKarte + 1;
				// Ist der Index gleich dem groesstmoeglichen Index, ist die
				// gewaehlte Karte nun 0
			} else {
				gewaehlteKarte = 0;
			}
		} else {
			// Ist die gewaehlte Karte an einem Platz groesser als 0, wird
			// der Index dekrementiert
			if (0 < gewaehlteKarte) {
				gewaehlteKarte = gewaehlteKarte - 1;
				// Ist der Index gleich Null, ist die gewaehlte Karte nun
				// die mit dem hoechsten Index
			} else {
				gewaehlteKarte = aufHand.getComponentCount() - 1;
			}
		}
	}

	/**
	 * Zeigt die Statistik der Spieler an, wie Anzahl der Gewinne, Prozent der
	 * Spiele gespielt usw.
	 */
	public void statistik() {
		String[] s = statistikText(tisch.getSpieler1());
		JLabel auswert1 = new JLabel(s[0]);
		JLabel gewinne1 = new JLabel(s[1]);
		JLabel hand1 = new JLabel(s[2]);
		s = statistikText(tisch.getSpieler2());
		JLabel auswert2 = new JLabel(s[0]);
		JLabel gewinne2 = new JLabel(s[1]);
		JLabel hand2 = new JLabel(s[2]);
		s = statistikText(tisch.getSpieler3());
		JLabel auswert3 = new JLabel(s[0]);
		JLabel gewinne3 = new JLabel(s[1]);
		JLabel hand3 = new JLabel(s[2]);
		JLabel auswert = new JLabel("Prozent der Alleinspiele:");
		JLabel gewinne = new JLabel("Anzahl der Gewinne:");
		JLabel hand = new JLabel("Handspiele:");
	
		setzeStats(auswert, 0, 2);
		setzeStats(gewinne, 0, 3);
		setzeStats(hand, 0, 4);
		
		setzeStats(auswert1, 1, 2);
		setzeStats(gewinne1, 1, 3);
		setzeStats(hand1, 1, 4);
		
		setzeStats(auswert2, 2, 2);
		setzeStats(gewinne2, 2, 3);
		setzeStats(hand2, 2, 4);
		
		setzeStats(auswert3, 3, 2);
		setzeStats(gewinne3, 3, 3);
		setzeStats(hand3, 3, 4);

		pack();
		rechteSeite.repaint();

	}
	
	/**
	 * Setzt den Text f&uuml;r die Statistikausgabe
	 * @param spieler Der Spieler, dessen Statistik ausgegeben werden soll
	 * @return Das Array mit den Strings, die die Daten enthalten
	 */
	public String[] statistikText(ISpieler spieler) {
		String[] s = new String[3];
		
		//Prozent allein
		s[0]= tisch.getProzentAllein(spieler) + "("
		+ +tisch.getAnzahlAllein(spieler) + ")";
		//Anzahl Gewinne
		s[1]= tisch.anzahlderGewinne(spieler) + "/"
		+ tisch.getAnzahlAllein(spieler);
		//Handspiele
		s[2]= spieler.getHandspiele() + "/"
		+ tisch.getAnzahlAllein(spieler);
		
		return s;
	}

	/**
	 * Setzt Elemente auf rechteSeite
	 * 
	 * @param label
	 *            Das Label, welches zu rechteSeite hinzugef&uuml;gt werden soll
	 * @param x
	 *            Die Spalte, in der das Label gesetzt werden soll
	 * @param y
	 *            Die Zeile, in der das Label gesetzt werden soll
	 */
	public void setzeStats(JLabel label, int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		rechteSeite.add(label, c);
	}

}