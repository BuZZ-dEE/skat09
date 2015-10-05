package main.player;

import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;

/**
 * Diese Klasse beschreibt das Verhalten des Computerspielers "Oma". Die Oma
 * zeichnet sich dadurch aus, dass sie immer die oberste Karte ihres Decks in
 * den Stich spielt, demnach muss sie auch nicht bedienen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Granny extends Player {
	
	/**
	 * Der Konstruktor der Klasse Oma
	 * @param name Der Name der Oma
	 */
	public Granny(String name) {

		super(name);
	}

	/**
	 * Die Oma spielt immer die oberste Karte aus ihrem Blatt.
	 * 
	 * @param gespielteKarten
	 *            bereits gespielte Karten
	 * @return Spielkarte, die die Oma spielt
	 */
	public PlayingCard playCard(PlayingCard[] gespielteKarten) {

		int index = hand.size() - 1;

		return hand.remove(index);
	}

	/**
	 * Die Oma ist beim h&ouml;ren immer sofort pass.
	 * 
	 * @param reizwert
	 *            der aktuelle Reizwert
	 */
	public boolean respond(int reizwert) {

		return false;
	}

	/**
	 * Falls die Oma bid muss, ist sie pass.
	 * 
	 * @return reizwert der Oma
	 */
	public boolean bid(int alterWert) {

		return false;
	}

	/**
	 * Die Oma kommt spielt nie, deshalb kann Sie auch nicht dr&uuml;cken.
	 */
	public PlayingCard[] druecken(PlayingCard[] skat) {

		return null;
	}

	/**
	 * Wenn die Oma beim Raeuberskat spielt, spielt sie Hand.
	 */
	public boolean handgame() {

		return true;
	}

	/**
	 * Die Oma spielt nie, demnach kann Sie nicht ouvert ansagen.
	 */
	public boolean ouvert() {

		return false;
	}

	/**
	 * Die Oma spielt nie, deshalb kann sie nicht schneider ansagen.
	 */
	public boolean schneider() {

		return false;
	}

	/**
	 * Die Oma spielt nie, demnach kann sie nicht schwarz ansagen!"
	 */
	public boolean schwarz() {

		return false;
	}

	/**
	 * Die Oma spielt immer Grand, wenn sie spielen muss.
	 */
	public IGameVariety declareGame() {

		return new GrandGame();
	}

	@Override
	public SuitGame suit() {
	
		return null;
	}

	@Override
	public boolean agent() {
		
		return false;
	}

	@Override
	public int setBidLimit() {
		
		return 0;
	}
}
