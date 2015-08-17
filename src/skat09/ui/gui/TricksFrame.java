package skat09.ui.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import skat09.Messages;
import skat09.Table;
import skat09.playingcard.PlayingCard;


/**
 * Diese Klasse beschreibt das Fenster, welches die vergangenen Stiche ausgibt.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class TricksFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6359770795617608420L;

	/**
	 * Das Panel, auf dem die Karten ausgegeben werden sollen.
	 */
	private JPanel panel = new JPanel();

	/**
	 * Der Tisch, auf dem das Spiel gespielt wird.
	 */
	private Table table;
	/**
	 * Das Panel, auf dem sich die Karten befinden, soll scrollbar sein.
	 */
	private JScrollPane scroller;

	/**
	 * Der konstruktor der Klasse Stiche. In diesem werden die Komponenten
	 * initialisiert und das erste mal die update()-Methode aufgerufen.
	 * 
	 * @param table
	 *            Der Tisch auf dem das Spiel gespielt wird
	 */
	public TricksFrame(Table table, boolean allTricks) {
		super(Messages.getI18n("game.trick.past"));
		this.table = table;
		setSize(300, 200);
		setResizable(false);
		init();
		setLayout(new FlowLayout());
		add(panel);
		scroller = new JScrollPane(panel);
		scroller.setPreferredSize(new Dimension(300, 200));
		add(scroller);
		update(allTricks);
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
	 * Mit dieser Methode wird die entsprechende Updatemethode aufgerufen, je
	 * nachdem ob alle Stiche oder nur der letzte Stich ausgegeben werden soll.
	 * 
	 * @param allTricks
	 *            Gibt an, ob alle Stiche oder nur der letzte Stiche angezeigt
	 *            werden sollen
	 */
	public void update(boolean allTricks) {
		if (allTricks) {
			updateAllTricks();
		} else {
			updateLastTrick();
		}
	}

	/**
	 * Mit dieser Methode wird ein bestehendes Stichefenster aktualisiert, indem
	 * die aktuelle alleGespielteKarten-ArrayList ausgegeben wird
	 */
	private void updateAllTricks() {
		int spalte = 0;
		int reihe = 0;
		GridBagConstraints c = new GridBagConstraints();
		ArrayList<PlayingCard> cards = table.getPlayer1()
				.getAllPlayedCards();

		panel.removeAll();
		panel.setSize(new Dimension(300, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		for (PlayingCard card : cards) {

			Image image = null;

			image = new ImageIcon(card.getCardPath()).getImage();

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
	 * Gibt den letzten Stich auf dem Stichefenster aus
	 */
	private void updateLastTrick() {
		int spalte = 0;
		int reihe = 0;
		GridBagConstraints c = new GridBagConstraints();
		ArrayList<PlayingCard> karten = table.getPlayer1()
				.getAllPlayedCards();

		panel.removeAll();
		panel.setSize(new Dimension(300, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		if (karten.size() > 0) {
			for (int i = karten.size() - 3; i < karten.size(); i++) {

				Image image = null;

				image = new ImageIcon(karten.get(i).getCardPath()).getImage();

				JLabel label = new JLabel(new ImageIcon(image
						.getScaledInstance(70, 110, 1)));
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
		}
		panel.setVisible(true);

		pack();

	}

	/**
	 * L&ouml;scht den Inhalt des Stiche-Fensters
	 */
	public void clear() {
		panel.removeAll();
		panel.setSize(new Dimension(300, 200));
	}

	/**
	 * Diese Methode schliesst das Stichefenster.
	 */
	public void schliessen() {
		setVisible(false);
	}
}
