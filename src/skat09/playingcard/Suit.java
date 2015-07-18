package skat09.playingcard;

/**
 * Die Enum Farbe beinhaltet die m&ouml;glichen Farben einer Spielkarte.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 *
 */
public enum Suit {
	
	/**
	 * Halter der Farbe Karo
	 */
	KARO(9),
	/**
	 * Halter der Farbe Herz
	 */
	HERZ(10),
	/**
	 * Halter der Farbe Pik
	 */
	PIK(11),
	/**
	 * Halter der Farbe Kreuz
	 */
	KREUZ(12);

	/**
	 * Der Wert, den ein Element aus der Enum haben kann
	 */
	private int ordnungswert;

	/**
	 * Gibt den Ordnungswert eines Elements zur&uuml;ck
	 * @return Den Wert eines Elements
	 */
	public int wert() {

		return ordnungswert;
	}

	/**
	 * Der Konstruktor der Enum Farbe
	 * @param ordnungswert Der Ordnungswert, den eine Farbe haben soll
	 */
	private Suit(int ordnungswert) {

		this.ordnungswert = ordnungswert;
	}

}
