package skat09.player;

import skat09.gamevariety.GrandGame;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IGameVariety;

/**
 * Diese Klasse beschreibt das Verhalten des Computerspielers "Oma". Die Oma
 * zeichnet sich dadurch aus, dass sie immer die oberste Karte ihres Decks in
 * den Stich spielt, demnach muss sie auch nicht bedienen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Granny extends Player {

	//
	// Datenfelder
	//

	//
	// Konstruktor
	//

	/**
	 * Der Konstruktor der Klasse Oma
	 * @param name Der Name der Oma
	 */
	public Granny(String name) {

		super(name);
	}

	//
	// get-Methoden
	//

	//
	// set-Methoden
	//

	//
	// weitere Methoden
	//

	/**
	 * Die Oma spielt immer die oberste Karte aus ihrem Blatt.
	 * 
	 * @param gespielteKarten
	 *            bereits gespielte Karten
	 * @return Spielkarte, die die Oma spielt
	 */
	public PlayingCard spieleKarte(PlayingCard[] gespielteKarten) {

		int index = blatt.size() - 1;

		return blatt.remove(index);
	}

	/**
	 * Die Oma ist beim h&ouml;ren immer sofort weg.
	 * 
	 * @param reizwert
	 *            der aktuelle Reizwert
	 */
	public boolean hoeren(int reizwert) {

		return false;
	}

	/**
	 * Falls die Oma sagen muss, ist sie weg.
	 * 
	 * @return reizwert der Oma
	 */
	public boolean sagen(int alterWert) {

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
	public boolean handspiel() {

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
	public IGameVariety spielAnsagen() {

		return new GrandGame();
	}

	@Override
	public SuitGame farbe() {
	
		return null;
	}

	@Override
	public boolean agent() {
		
		return false;
	}

	@Override
	public int reizlimitFestlegen() {
		
		return 0;
	}
}
