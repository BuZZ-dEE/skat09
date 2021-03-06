package main.player;

import java.io.IOException;

import main.IController;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;


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
	 * Controller zur direkten Kommunikation with der TUI/GUI
	 */
	IController controller;
	
	/**
	 * Der Konstruktor der Klasse MenschlicherSpieler bekommt einen Namen und
	 * den Controller &uuml;bergeben
	 * 
	 * @param name
	 *            Der gew&uuml;nschte Name des Spielers
	 * @param controller
	 *            Der Controller, with dem gespielt werden soll. Diesen braucht
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
	public boolean respond(int reizwert) {

		return controller.getOutput().respond(reizwert);
	}

	/**
	 * Fragt den menschlichen Spieler, welchen Wert er als n&auml;chstes reizen
	 * will.
	 * 
	 * @param reizWert
	 *            - dieser Wert muss &uuml;berschritten werden
	 * @return reizwert des menschlichen Spielers
	 */
	public boolean bid(int reizWert) {

		return controller.getOutput().bid(reizWert);
	}

	@Override
	public PlayingCard playCard(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;
		boolean fertig = false;

		PlayingCard karte = null;
		try {
			karte = controller.getOutput().playCard(gespielteKarten, this);
		} catch (IOException e) {

			e.printStackTrace();
		}

		while (!fertig) {

			if (gameVariety.checkedPlayedCards(hand, gespielteKarten, karte)) {
				ergebnis = karte;
				fertig = true;
			} else {
				controller.getOutput().anotherCard();
				hand.add(karte);
				sortHand(gameVariety);
				try {
					karte = controller.getOutput().playCard(
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

		hand.add(skat[0]);
		hand.add(skat[1]);
		if (skat[2] instanceof PlayingCard) {
			hand.add(skat[2]);
			sechserskat = true;
		}

		for (int i = 0; i < gedrueckteKarten.length; i++) {
			karte1 = controller.getOutput().druecken(hand, i + 1);
			gedrueckteKarten[i] = hand.remove(karte1);
			if (i == 1 && !sechserskat) {
				gedrueckteKarten[i + 1] = null;
				break;
			}
		}
		return gedrueckteKarten;
	}

	@Override
	public boolean handgame() {

		return controller.getOutput().handgame();
	}

	@Override
	public boolean ouvert() {

		return controller.getOutput().ouvert();
	}

	@Override
	public boolean schneider() {

		return controller.getOutput().schneider();
	}

	@Override
	public boolean schwarz() {

		return controller.getOutput().schwarz();
	}

	@Override
	public IGameVariety declareGame() {

		return controller.getOutput().declareSuit();
	}

	@Override
	public SuitGame suit() {

		return controller.getOutput().suitGame();
	}

	@Override
	public int setBidLimit() {
		int erg = controller.getOutput().setBiddingLimit();
		while (erg == -1) {
			erg = controller.getOutput().setBiddingLimit();
		}

		return erg;
	}

	@Override
	public boolean agent() {

		return controller.getOutput().biddingAgent();
	}
}
