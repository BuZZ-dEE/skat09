package skat09.ui.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import skat09.Tisch;
import skat09.spielkarte.Spielkarte;


/**
 * Diese Klasse beschreibt das Fenster, welches die vergangenen Stiche ausgibt.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Stiche extends JFrame {

	/**
	 * Das Panel, auf dem die Karten ausgegeben werden sollen.
	 */
	private JPanel panel = new JPanel();

	/**
	 * Der Tisch, auf dem das Spiel gespielt wird.
	 */
	private Tisch tisch;
	/**
	 * Das Panel, auf dem sich die Karten befinden, soll scrollbar sein.
	 */
	private JScrollPane scroller;

	/**
	 * Der konstruktor der Klasse Stiche. In diesem werden die Komponenten
	 * initialisiert und das erste mal die update()-Methode aufgerufen.
	 * 
	 * @param tisch Der Tisch auf dem das Spiel gespielt wird
	 */
	public Stiche(Tisch tisch) {
		super("Vergangene Stiche");
		this.tisch = tisch;
		setSize(300, 200);
		setResizable(false);
		init();
		setLayout(new FlowLayout());
		add(panel);
		scroller = new JScrollPane(panel);
		scroller.setPreferredSize(new Dimension(300, 200));
		add(scroller);
		update();
	}

	/**
	 * Diese Methode initialisiert das Panel, auf dem die Karten ausgegeben
	 * werden sollen
	 */
	public void init() {
		panel.setLayout(new GridBagLayout());
		panel.setSize(new Dimension(300, 200));
		setVisible(true);

	}

	/**
	 * Mit dieser Methode wird ein bestehendes Stichefenster aktualisiert, indem
	 * die aktuelle alleGespielteKarten-ArrayList ausgegeben wird
	 */
	public void update() {
		int spalte = 0;
		int reihe = 0;
		GridBagConstraints c = new GridBagConstraints();
		ArrayList<Spielkarte> karten = tisch.getSpieler1()
				.getAllegespieltenkarten();

		panel.removeAll();
		panel.setSize(new Dimension(300, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		for (Spielkarte karte : karten) {

			Image image = null;

			image = new ImageIcon(Fenster.getFileUrl("img/" + karte.dateiPfad()
					+ ".png")).getImage();

			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(70,
					110, 1)));
			c.gridx = spalte;
			c.gridy = reihe;

			panel.add(label, c);

			if (spalte == 2) {
				spalte = 0;
				reihe = reihe + 1;
			} else {
				spalte = spalte + 1;
			}

		}
		panel.setVisible(true);

		pack();
	}

	/**
	 * Diese Methode schliesst das Stichefenster.
	 */
	public void schliessen() {
		setVisible(false);
	}
}
