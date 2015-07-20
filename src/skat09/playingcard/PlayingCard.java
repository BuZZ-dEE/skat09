package skat09.playingcard;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import skat09.test.interfaces.IPlayer;
import skat09.ui.gui.SvgImageProcessing;

/**
 * Die Klasse Spielkarte erzeugt eine Spielkarte mit einer Farbe und einem Wert.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class PlayingCard implements Comparable<PlayingCard> {

	/**
	 * Feld f&uuml;r die Farbe
	 */
	private Suit farbe;
	/**
	 * Feld f&uuml;r den Wert
	 */
	private Value wert;
	/**
	 * Feld f&uuml;r den Besitzer
	 */
	private IPlayer besitzer;
	/**
	 * Feld das angibt, ob deutsches oder franz&ouml;sisches Blatt benutzt wird
	 */
	private static boolean deutsch;
	
	/**
	 * Konstruktor der Klasse Spielkarte. Setzt die Farbe und den Wert der Karte
	 * @param farbe Die Farbe der zu erzeugenden Karte
	 * @param wert Der Wert der zu erzeugenden Karte
	 */
	public PlayingCard(Suit farbe, Value wert) {
		this.wert = wert;
		this.farbe = farbe;
		//deutsch = false;
	}

	/**
	 * Gibt die Farbe einer Karte zur&uuml;ck.
	 * 
	 * @return Farbe der Karte
	 */
	public Suit getFarbe() {
		return farbe;
	}
	
	/**
	 * Liefert den Inhalt der Variable deutsch.
	 * 
	 * @return true, falls die Karte in deutscher Sprache ist
	 */
	public static boolean getDeutsch() {
		return deutsch;
	}

	/**
	 * Gibt den Wert einer Karte zur&uuml;ck.
	 * 
	 * @return wert der Karte
	 */
	public Value getWert() {
		return wert;
	}

	/**
	 * Gibt den Besitzer zur&uuml;
	 * @return der Besitzer der Karte
	 */
	public IPlayer getBesitzer() {
		return besitzer;
	}

	/**
	 * Setzt den Besitzer einer Karte neu.
	 * 
	 * @param besitzer
	 *            - der zuk&uuml;nfitge Besitzer der Karte
	 */
	public void setBesitzer(IPlayer besitzer) {
		this.besitzer = besitzer;
	}
	
	/**
	 * Setzt die Variable deutsch neu.
	 * 
	 * @param blatt - boolean Wert, der angibt, ob es sich um eine deutsche Karte 
	 * handelt.
	 */
	public static void setDeutsch(boolean blatt) {
		deutsch = blatt;
	}

	/**
	 * Gibt die Eigenschaften einer Karte als String aus.
	 * @return Eigenschaften der Karte
	 */
	public String toString() {
		String string = "";
		if (deutsch == false) {
			string = farbe + " " + wert;
		} else {
			String deutfarbe = deutFarbe();
			String deutwert = deutWert();
			string = deutfarbe + " " + deutwert;
		}
		
		
		return string;
	}

	/**
	 * equals vergleich die Spielkarte mit einer &uuml;bergebenen Spielkarte und
	 * stellt fest, ob die Datenfelder der beiden Karten gleich sind.
	 * 
	 * @param karte
	 *            Karte mit der verglichen werden soll
	 * @return true, falls die beiden Karten gleich sind
	 */
	public boolean equals(PlayingCard karte) {

		boolean ergebnis;

		if (this.farbe.ordinal() == karte.getFarbe().ordinal()
				&& this.wert.ordinal() == karte.getWert().ordinal()) {

			ergebnis = true;
		}

		else {

			ergebnis = false;
		}

		return ergebnis;
	}
	
	/**
	 * Vergleicht zwei Spielkarten gem&auml;ß der compareTo Konvention.
	 * 
	 * Achtung, die Methode ist nur zur &Uuml;berpr&uuml;fung von 
	 * Gleichheit zweier Karten implementiert, sie kann (noch) nicht
	 * zur Sortierung verwendet werden!
	 * 
	 * @param karte - spielkarte, die mit dem aktuellen Objekt verglichen werden soll
	 * @return 0 falls gleich, ansonsten negativen/positiven Wert
	 */
	public int compareTo(PlayingCard karte) {
		
		int ergebnis = Integer.MAX_VALUE;
		
		if (wert == karte.getWert() && farbe == karte.getFarbe()) {
			
			ergebnis = 0;
		}
		return ergebnis;
	}
	
	/**
	 * Wandelt franz&ouml;sische Farbe in deutsche Farbe um.
	 * @return Die Methode gibt den deutschen Begriff f&uuml;r die Kartenfarbe wieder.
	 */
	public String deutFarbe() {
		String deutfarbe = "";
		if (farbe.equals(Suit.LEAVES)) {
			deutfarbe = "LEAVES";
		}
		if (farbe.equals(Suit.HEARTS)) {
			deutfarbe = ""+farbe;
		}
		if (farbe.equals(Suit.ACORNS)) {
			deutfarbe = "ACORNS";
		}
		if (farbe.equals(Suit.BELLS)) {
			deutfarbe = "BELLS";
		}
		return deutfarbe;
	}
	
	/**
	 * Gibt den deutschen Wert einer Spielkarte zur&uuml;ck.
	 * @return Den deutschen Wert einer Spielkarte als String
	 */
	public String deutWert() {
		String deutwert ="";
		if (wert == Value.UNDER_KNAVE) {
			deutwert = "UNDER_KNAVE";
		} else if (wert == Value.OVER_KNAVE) {
			deutwert = "OVER_KNAVE";
		} else if (wert == Value.DAUS) {
			deutwert = "DAUS";
		} else {
			deutwert = "" + wert;
		}
		return deutwert;
	}
	
	/**
	 * Gibt den Dateipfad einer Karte zur&uuml;ck.
	 * 
	 * @return Den Dateipfad einer Karte
	 */
	public String dateiPfad() {
		String dateipfad = "";
		if (deutsch) {
			dateipfad += "deutkarten/";
		} else {
			dateipfad += "frankarten/";
//			dateipfad += "svg/";
		}
		String neuerString = this.toString().replace(' ', '_');
		dateipfad += neuerString;
		return dateipfad;
	}
	
	/**
	 * Get the complete path URL to the given card.
	 * 
	 * @return the path for the card
	 * 
	 * @since 13.06.2015 21:45:49
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public URL getCardPath() {
		return this.getClass().getClassLoader().getResource("img/" + dateiPfad() + ".png");
	}
	
	/**
	 * Get the image for the given SVG card.
	 * 
	 * @return the image for the card
	 * 
	 * @since 14.06.2015 22:45:57
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public Image getCardImage() {
		File svgFile;
		try {
			svgFile = new File(this.getClass().getClassLoader().getResource("img/" + dateiPfad() + ".svg").toURI());
			try {
				return SvgImageProcessing.rasterize(svgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}