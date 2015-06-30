package skat09.ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import skat09.Messages;
import skat09.spieler.SpielerEnum;
import skat09.tools.Configuration;
import skat09.ui.GUIausgabe;

/**
 * 
 * 
 * die Klasse Fenster stellt das Einstellungsmen&uuml; des Skatspiels f&uumlr;
 * den Fall, das eine graphische Ausgabe gew&uuml;nscht wird
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class Fenster extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1429828124945726723L;
	/**
	 * das Hauptfenster, in das die verschiednen Bereich wie Logo, Optionen etc.
	 * integirert werden
	 */
	private JPanel haupt;
	/**
	 * Panel, in dem die Gegner und Skatvariante gew&aauml;lt werden k&ouml;nnen
	 */
	private JPanel options;
	/**
	 * Enth&auml;lt das Logo
	 */
	private JPanel logo;
	/**
	 * Wahlbox f&uuml;r den ersten Gegner
	 */
	private JComboBox<String> gegner1;
	/**
	 * Wahlbox f&uuml;r den zweiten Gegner
	 */
	private JComboBox<String> gegner2;
	/**
	 * Eingabefeld in das der menschliche Spiel seinen Spielname eingeben kann
	 */
	private JTextField name;
	/**
	 * Wahlbox f&uuml;r die Skatvariante
	 */
	private JComboBox<String> skatvariante;
	/**
	 * Wahl, ob nach deutschen oder franz&ouml;sischem Blatt gespielt werden
	 * soll
	 */
	private JComboBox<String> blattwahl;
	/**
	 * Zeigt an wo der Spielername eingetragen werden soll
	 */
	private JLabel lname;
	/**
	 * kennzeichnet die Wahl f&uuml;r den ersten Gegner
	 */
	private JLabel lgegner1;
	/**
	 * kennzeichnet die Wahl f&uuml;r den zweiten Gegner
	 */
	private JLabel lgegner2;
	/**
	 * kennzeichnet die Wahl f&uuml;r die Skatvariante
	 */
	private JLabel lvariante;
	/**
	 * kennzeichnet die Wahl ob Sechserskat gespielt werden soll
	 */
	private JLabel lsechser;
	/**
	 * kennzeichnet die Wahl ob mit deutschen oder franz&ouml;sischen Blatt
	 * gespielt werden soll
	 */
	private JLabel lblattw;
	/**
	 * Der Button zum &Uuml;bernehmen der gew&auml;hlten Einstellungen
	 */
	private JButton button;
	/**
	 * Auswahl f&uuml;r Sechserskat
	 */
	private JCheckBox sechser;
	/**
	 * Setzt einen Rahmen
	 */
	private JLabel bilderrahmen;

	/**
	 * Diesem DatenFeld wird der Eingegebene SpielerName &uuml;bergeben
	 */
	private String sname = "";
	/**
	 * Je nach Eingabe wird der zur Eingabe in Gegnerwahl der passende ENUM-Wert
	 * gehalten
	 */
	private SpielerEnum mitspieler1;
	/**
	 * Je nach Eingabe wird der zur Eingabe in Gegnerwahl der passende ENUM-Wert
	 * gehalten
	 */
	private SpielerEnum mitspieler2;
	/**
	 * Je nach eingabe wird hier ein bestimmter Wert f&uuml;r jede Skatvariante
	 * gehalten
	 */
	private int variante = 0;
	/**
	 * wird je nachdem gesetzt, ob franz&ouml;sisches oder deutsches Spielblatt
	 * erw&uuml;nscht wird
	 */
	private boolean deutsch = false;
	/**
	 * wird je nachdem gesetzt, ob sechserskat gew&auml;hlt wird oder nicht
	 */
	private boolean sechserskat = false;

	/**
	 * H&auml;lt die Methoden, um f&uuml;r eine grafische Darstellung zu sorgen
	 */
	private GUIausgabe ausgabe;

	/**
	 * 
	 * @param ausgabe
	 *            Die aktuelle GUIAusgabe wird &uuml;bergeben, damit sie
	 *            released werden kann.
	 */
	public Fenster(GUIausgabe ausgabe) {
		super(Messages.getI18n("application.name"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.ausgabe = ausgabe;

		init();

		try {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon programIcon = new ImageIcon(
					cl.getResource("res/test.png"));
			setIconImage(programIcon.getImage());
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension frameSize = this.getSize();
		int top = (screenSize.height - frameSize.height) / 2;
		int left = (screenSize.width - frameSize.width) / 2;
		// setSize(frameSize);
		setLocation(left, top);
		setResizable(false);
	}

	/**
	 * Die Methode initialisiert die Einstellungselemente, das Logo und den
	 * OK-Button
	 */
	private void init() {

		haupt = new JPanel();
		getContentPane().add(new Toolbar(), BorderLayout.NORTH);
		haupt.setLayout(new FlowLayout());
		options = new JPanel();
		options.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		logo = new JPanel();
		logo();
		erstelleOptions();
		setzteOptionen();

		button = new JButton(Messages.getI18n("application.ok"));
		button.setName("ok");
		button.addActionListener(this);
		button.addKeyListener(this);
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		options.add(button, c);

		haupt.add(options);
		haupt.add(logo);
		add(haupt);

		pack();
		setVisible(true);
	}

	/**
	 * Setzt die Optionen auf dem options-Panel
	 */
	public void setzteOptionen() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		options.add(lname, c);
		c.gridx = 1;
		options.add(name, c);

		c.gridx = 0;
		c.gridy = 1;
		options.add(lgegner1, c);
		c.gridx = 1;
		options.add(gegner1, c);

		c.gridx = 0;
		c.gridy = 2;
		options.add(lgegner2, c);
		c.gridx = 1;
		options.add(gegner2, c);

		c.gridx = 0;
		c.gridy = 3;
		options.add(lvariante, c);
		c.gridx = 1;
		options.add(skatvariante, c);

		c.gridx = 0;
		c.gridy = 4;
		options.add(lsechser, c);
		c.gridx = 1;
		options.add(sechser, c);

		c.gridx = 0;
		c.gridy = 5;
		options.add(lblattw, c);
		c.gridx = 1;
		options.add(blattwahl, c);
	}

	/**
	 * Erstellt die Textfelder und Comboboxen, die die Einstellungen beinhalten
	 */
	private void erstelleOptions() {

		// Beschriftungen der ComboBoxes
		String[] gegner = { Messages.getI18n("player.granny"),
				Messages.getI18n("player.rule.compliant"),
				Messages.getI18n("player.smart") };
		String[] variante = { Messages.getI18n("game.skat.international"),
				Messages.getI18n("game.skat.raeuber"),
				Messages.getI18n("game.skat.ramschbock") };
		String[] blattwahl2 = { Messages.getI18n("game.skat.sheet.german"),
				Messages.getI18n("game.skat.sheet.french") };

		// Namen der Items
		lname = new JLabel(Messages.getI18n("player.name") + ":",
				JLabel.TRAILING);
		lgegner1 = new JLabel(Messages.getI18n("player.adversary.first") + ":",
				JLabel.TRAILING);
		lgegner2 = new JLabel(
				Messages.getI18n("player.adversary.second") + ":",
				JLabel.TRAILING);
		lvariante = new JLabel(Messages.getI18n("game.skat.variant") + ":",
				JLabel.TRAILING);
		lsechser = new JLabel(Messages.getI18n("game.skat.variant.six") + ":",
				JLabel.TRAILING);
		lblattw = new JLabel(Messages.getI18n("game.sheet.choice") + ":",
				JLabel.TRAILING);

		// Textfeld
		name = new JTextField(Configuration.getInstance().getDefaultName());
		name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Configuration.getInstance().setDefaultName(name.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		lname.setLabelFor(name);

		// ComboBox mit Label:
		gegner1 = new JComboBox<String>(gegner);
		gegner1.setSelectedIndex(1);
		lgegner1.setLabelFor(gegner1);

		// Combobox Gegner2 mit Label:
		gegner2 = new JComboBox<String>(gegner);
		gegner2.setSelectedIndex(1);
		lgegner2.setLabelFor(gegner2);

		// Combobox Skatvariante mit Label
		skatvariante = new JComboBox<String>(variante);
		skatvariante.setSelectedIndex(0);
		lvariante.setLabelFor(skatvariante);

		// ComboBox Blattwahl mit Label
		blattwahl = new JComboBox<String>(blattwahl2);
		
		if (Configuration.getInstance().getDeck().equalsIgnoreCase("de")) {
			blattwahl.setSelectedIndex(0);
		} else if (Configuration.getInstance().getDeck().equalsIgnoreCase("fr")) {
			blattwahl.setSelectedIndex(1);
		}
		
		blattwahl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(blattwahl.getSelectedIndex());
				if (blattwahl.getSelectedIndex() == 0) {
					Configuration.getInstance().setDeck("de");
				} else if (blattwahl.getSelectedIndex() == 1) {
					Configuration.getInstance().setDeck("fr");
				}
			}
		});
		
		if (Configuration.getInstance().getDeck().equalsIgnoreCase("de")) {
			blattwahl.setSelectedIndex(0);
		} else if (Configuration.getInstance().getDeck().equalsIgnoreCase("fr")) {
			blattwahl.setSelectedIndex(1);
		} else {
			blattwahl.setSelectedIndex(1);
		}
		lblattw.setLabelFor(blattwahl);

		// Checkbox Sechserskat mit Label
		sechser = new JCheckBox();
		sechser.setName("sechser");
		lsechser.setLabelFor(sechser);
		sechser.setFocusable(true);
		sechser.addKeyListener(this);

	}

	/**
	 * l&auml;dt das Logo und gibt es aus
	 */
	public void logo() {

		// System.out.println(getFileUrl("data/img/bild.jpeg"));

		// System.out.println(getFileUrl("img/bild.jpeg").getPath());

		bilderrahmen = new JLabel(new ImageIcon(getFileUrl("img/bild.jpeg")));
		// bilderrahmen.setPreferredSize(new Dimension(200, 400));

		logo.add(bilderrahmen);

	}

	/**
	 * gibt den Link an, an dem sich die ben&oetigten Mediendateien befinden und
	 * erlaubt es, diese auch innerhalb einer Jar-Datei anzusprechen
	 * 
	 * @param path
	 * @return the File URL
	 */
	public static URL getFileUrl(String path) {

		return Fenster.class.getClassLoader().getResource(path);
	}

	/**
	 * dient zum Beenden des Spieles
	 */
	public void actionPerformed(ActionEvent arg0) {
		beenden();
	}

	/**
	 * Gibt die Art des 1. Mitspielers zur&uuml;ck
	 * 
	 * @return mitspieler1
	 */
	public SpielerEnum getMitspieler1() {
		// SpielerEnum ergebnis = mitspieler1;
		// mitspieler1 = null; //vermutlich zu switchen ueberfluessig?!
		// return ergebnis;

		// neu
		return mitspieler1;
	}

	/**
	 * Gibt die Art des 2. Mitspielers zur&uuml;ck
	 * 
	 * @return mitspieler2
	 */
	public SpielerEnum getMitspieler2() {
		// SpielerEnum ergebnis = mitspieler2;
		// mitspieler2 = null;
		// return ergebnis;

		// neu
		return mitspieler2;
	}

	/**
	 * dient zur Abfrage der gew&auml;hlten Skatvariante
	 * 
	 * @return Die Skatvariante
	 */
	public int getSkatWahl() {
		int erg = variante;
		variante = 0;
		return erg;
	}

	/**
	 * dient zur Abfrage des gew&auml;hlten Spielernamens
	 * 
	 * @return Der Spielername
	 */
	public String getName() {
		return sname;
	}

	/**
	 * Setzt die gew&auml;hlte Spielblattwahl
	 * 
	 * @param deutsch
	 *            - Spielblattwahl
	 * 
	 */
	public void setDeutsch(boolean deutsch) {
		this.deutsch = deutsch;
	}

	/**
	 * dient zur Abfrage der gew&auml;hlten Spielblattvariante
	 * 
	 * @return die gew&auml;hlte Spielblattvariante
	 */
	public boolean getDeutsch() {
		return deutsch;
	}

	/**
	 * dient zur Abfrage ob SechserSkat gespielt werden soll
	 * 
	 * @return ob Sechserskat gew&auml;hlt wurde
	 */
	public boolean getSechserkat() {
		return sechserskat;
	}

	/**
	 * Die Methode beschreibt das Verhalten beim Dr&uuml;cken einer Taste, wenn
	 * der Fokus auf der Komponente ist.
	 */
	public void keyPressed(KeyEvent arg0) {

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Loslassen einer Taste, wenn der
	 * Fokus auf der Komponente ist.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getComponent().getName().compareTo("sechser") == 0) {
				JCheckBox box = (JCheckBox) e.getComponent();
				if (box.isSelected() == true) {
					box.setSelected(false);
				} else {
					box.setSelected(true);
				}
			}
			if (e.getComponent().getName().compareTo("ok") == 0) {
				beenden();
			}
		}

	}

	/**
	 * Die Methode beschreibt das Verhalten beim Tippen einer Taste, wenn der
	 * Fokus auf der Komponente ist.
	 */
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * beendet das Spiel
	 */
	public void beenden() {
		ausgabe.hauptfensterOeffnen();

		sname = name.getText();
		int index;
		index = gegner1.getSelectedIndex();
		if (index == 0) {
			mitspieler1 = SpielerEnum.OMA;
		} else if (index == 1) {
			mitspieler1 = SpielerEnum.REGELKONFORM;
		} else {
			mitspieler1 = SpielerEnum.INTELLIGENT;
		}

		index = gegner2.getSelectedIndex();
		if (index == 0) {
			mitspieler2 = SpielerEnum.OMA;
		} else if (index == 1) {
			mitspieler2 = SpielerEnum.REGELKONFORM;
		} else {
			mitspieler2 = SpielerEnum.INTELLIGENT;
		}

		index = skatvariante.getSelectedIndex();
		if (index == 0) {
			variante = 1;
		} else if (index == 1) {
			variante = 0;
		} else {
			variante = 2;
		}

		index = blattwahl.getSelectedIndex();
		if (index == 0) {
			deutsch = true;
		} else {
			deutsch = false;
		}

		if (sechser.isSelected()) {
			sechserskat = true;
		}

		ausgabe.setRelease(true);

		setVisible(false);
	}
}
