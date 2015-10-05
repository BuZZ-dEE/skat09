package main.playingcard;
/**
 * Die Enum Wert beinhaltet die Werte, die eine Spielkarte haben kann
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public enum Value {
	
	/**
	 * Halter des Farbwertes 6
	 */
	SIX,
	/**
	 * Halter des Farbwertes 7
	 */
	SEVEN,
	/**
	 * Halter des Farbwertes 8 
	 */
	EIGHT,
	/**
	 * Halter des Farbwertes 9
	 */
	NINE,
	/**
	 * Halter des Farbwertes 10
	 */
	TEN,
	/**
	 * Halter des Farbwertes Bube
	 * Under knave (Unter) == Jack (Junge / Bube)
	 */
	UNDER_KNAVE,
	/**
	 * Halter des Farbwertes Dame
	 * Over knave (Ober) == Queen (Dame)
	 */
	OVER_KNAVE,
	/**
	 * Halter des Farbwertes König
	 * King == King (König)
	 */
	KING,
	/**
	 * Halter des Farbwertes Ass
	 * Daus (Daus) == Ace (Ass)
	 */
	DAUS
}
