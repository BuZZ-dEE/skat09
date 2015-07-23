package skat09.player;

import java.io.IOException;

import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


/**
 * Die Klasse erstellt einen menschlichen Spieler, der an dem Skatspiel
 * teilnehmen kann.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class HumanPlayer extends Player implements IPlayer,
		IHumanPlayer {
	
	/**
	 * Controller zur direkten Kommunikation mit der TUI/GUI
	 */
	IController controller;
	
	/**
	 * Der Konstruktor der Klasse MenschlicherSpieler bekommt einen Namen und
	 * den Controller &uuml;bergeben
	 * 
	 * @param name
	 *            Der gew&uuml;nschte Name des Spielers
	 * @param controller
	 *            Der Controller, mit dem gespielt werden soll. Diesen braucht
	 *            der Spieler, damit er Benutzereingaben abfragen kann.
	 */
	public HumanPlayer(String name, IController controller) {

		super(name);
		this.controller = controller;
	}
	

	/**
	 * Die Methode simuliert das h&ouml;ren des menschlichen Spielers.
	 * 
	 * @param reizwert
	 *            - der aktuelle Reizwert
	 * @return boolean - true, falls der Spieler mitgeht
	 */
	public boolean hoeren(int reizwert) {

		return controller.getAusgabe().hoeren(reizwert);
	}

	/**
	 * Fragt den menschlichen Spieler, welchen Wert er als n&auml;chstes reizen
	 * will.
	 * 
	 * @param reizWert
	 *            - dieser Wert muss &uuml;berschritten werden
	 * @return reizwert des menschlichen Spielers
	 */
	public boolean sagen(int reizWert) {

		return controller.getAusgabe().sagen(reizWert);
	}

	@Override
	public PlayingCard spieleKarte(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;
		boolean fertig = false;

		PlayingCard karte = null;
		try {
			karte = controller.getAusgabe().spieleKarte(gespielteKarten, this);
		} catch (IOException e) {

			e.printStackTrace();
		}

		while (!fertig) {

			if (spielart.gespielteKartePruefen(blatt, gespielteKarten, karte)) {
				ergebnis = karte;
				fertig = true;
			} else {
				controller.getAusgabe().andereKarte();
				blatt.add(karte);
				blattSortieren(spielart);
				try {
					karte = controller.getAusgabe().spieleKarte(
							gespielteKarten, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ergebnis;
	}

	@Override
	public PlayingCard[] druecken(PlayingCard[] skat) {

		PlayingCard[] gedrueckteKarten = new PlayingCard[3];
		int karte1;

		boolean sechserskat = false;
		// Skat dem Spielerblatt hinzufuegen

		blatt.add(skat[0]);
		blatt.add(skat[1]);
		if (skat[2] instanceof PlayingCard) {
			blatt.add(skat[2]);
			sechserskat = true;
		}

		for (int i = 0; i < gedrueckteKarten.length; i++) {
			karte1 = controller.getAusgabe().druecken(blatt, i + 1);
			gedrueckteKarten[i] = blatt.remove(karte1);
			if (i == 1 && !sechserskat) {
				gedrueckteKarten[i + 1] = null;
				break;
			}
		}
		return gedrueckteKarten;
	}

	@Override
	public boolean handspiel() {

		return controller.getAusgabe().handspiel();
	}

	@Override
	public boolean ouvert() {

		return controller.getAusgabe().ouvert();
	}

	@Override
	public boolean schneider() {

		return controller.getAusgabe().schneider();
	}

	@Override
	public boolean schwarz() {

		return controller.getAusgabe().schwarz();
	}

	@Override
	public IGameVariety spielAnsagen() {

		return controller.getAusgabe().spielAnsagen();
	}

	@Override
	public SuitGame farbe() {

		return controller.getAusgabe().farbe();
	}

	@Override
	public int reizlimitFestlegen() {
		int erg = controller.getAusgabe().reizlimitFestlegen();
		while (erg == -1) {
			erg = controller.getAusgabe().reizlimitFestlegen();
		}

		return erg;
	}

	@Override
	public boolean agent() {

		return controller.getAusgabe().reizAgent();
	}
}
