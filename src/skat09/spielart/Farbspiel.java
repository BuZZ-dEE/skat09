package skat09.spielart;

import java.util.ArrayList;

import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;


/**
 * Die Klasse Farbspiel implementiert alle Regeln des Farbspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verf&uuml;gung.
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Farbspiel extends Spielart {

	private Farbe trumpffarbe;

	/**
	 * Instanziert ein Farb - Spiel
	 */
	public Farbspiel(Farbe trumpffarbe) {

		this.trumpffarbe = trumpffarbe;
		setSpielart(Spielartbezeichnung.FARBE);

	}

	/**
	 * gibt die Trumpffarbe zurueck.
	 * 
	 * @return trumpffarbe
	 */
	public Farbe getTrumpffarbe() {
		return trumpffarbe;
	}

//	/**
//	 * Prueft in Abhaengigkeit von den vorher gespielten Karten, ob eine Karte
//	 * zu dem Zeitpunkt gespielt werden darf.
//	 * 
//	 * @return true, wenn Karte gespielt werden darf
//	 * @param blatt
//	 * @param gespielteKarten
//	 * @param zuPruefendeKarte
//	 */
//	public boolean gespielteKartePruefen(ArrayList<Spielkarte> blatt, Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte) {
//
//		boolean ergebnis = false;
//
//		if (gespielteKarten[0] == null) {
//
//			ergebnis = true;
//		} else if (gespielteKarten[0].getWert() == Wert.BUBE || gespielteKarten[0].getFarbe() == getTrumpffarbe()) {
//			
//			// Wenn Bube gespielt wurde und korrekt bedient wurde gib true zurueck.
//			if (zuPruefendeKarte.getWert() == Wert.BUBE || getTrumpffarbe() == zuPruefendeKarte.getFarbe()) {
//
//				ergebnis = true;
//			}
//			// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
//			else {
//
//				boolean hatBube = false;
//				boolean hatTrumpf = false;
//
//				for (int i = 0; i < blatt.size(); i++) {
//
//					// Nach Buben suchen
//					if (blatt.get(i).getWert() == Wert.BUBE) {
//
//						hatBube = true;
//					}
//
//					// Nach Trumpfkarten suchen
//					else if (blatt.get(i).getFarbe() == getTrumpffarbe()) {
//
//						hatTrumpf = true;
//					}
//				}
//				
//				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
//				if (hatBube || hatTrumpf) {
//
//					ergebnis = false;
//				} else {
//
//					ergebnis = true;
//				}
//			}
//		}
//
//		else if (gespielteKarten[0].getFarbe() == zuPruefendeKarte.getFarbe()) {
//
//			ergebnis = true;
//		} else {
//
//			boolean hatFarbe = false;
//
//			// nachsehen, ob der Spieler die Farbe bedienen konnte.
//			for (int i = 0; i < blatt.size(); i++) {
//
//				if (blatt.get(i).getFarbe() == gespielteKarten[0].getFarbe()) {
//
//					hatFarbe = true;
//				}
//			}
//
//			if (hatFarbe) {
//
//				ergebnis = false;
//			} else {
//
//				ergebnis = true;
//			}
//
//		}
//		return ergebnis;
//	}
	
	@Override
	public boolean gespielteKartePruefen(ArrayList<Spielkarte> blatt, Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte) {

		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}
		
		else if (gespielteKarten[0].getWert() == Wert.BUBE || gespielteKarten[0].getFarbe() == getTrumpffarbe()) {
			
			ergebnis = bubeOderTrumpfBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}

		else {
			
			ergebnis = farbeBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}
		
		return ergebnis;
	}
	
	/**
	 * Wenn die zuerst gespielte Karte ein Bube oder Trumpf war wird gepr&uuml;ft,
	 * ob der Spieler noch Buben oder Trumpf hat.
	 * 
	 * @param blatt - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param gespielteKarten - Die Karten, die schon gespielt wurden.
	 * @param zuPruefendeKarte - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	public boolean bubeOderTrumpfBedienen(ArrayList<Spielkarte> blatt, Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte) {
		
		boolean ergebnis = true;
		
		// Wenn Bube oder Trumpf gespielt wurde und korrekt bedient wurde gib true zurueck.
		if (zuPruefendeKarte.getWert() == Wert.BUBE || getTrumpffarbe() == zuPruefendeKarte.getFarbe()) {

			ergebnis = true;
		}
		
		// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
		else {

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < blatt.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (blatt.get(i).getWert() == Wert.BUBE || blatt.get(i).getFarbe() == getTrumpffarbe()) {

					ergebnis = false;
					break;
				}
			}
		}
		
		return ergebnis;
	}

	@Override
	public Spielkarte hoehereKarte(Spielkarte karte1, Spielkarte karte2) {

		Spielkarte hoehereKarte = null;

		if (karte1.getWert() == Wert.BUBE && karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehererBube(karte1, karte2);
		}

		else if (karte1.getWert() == Wert.BUBE || karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehereKarteEinBube(karte1, karte2);
		}

		else if (karte1.getFarbe() == trumpffarbe
				&& karte2.getFarbe() == trumpffarbe) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);
		}

		else if (karte1.getFarbe() == trumpffarbe
				|| karte2.getFarbe() == trumpffarbe) {

			hoehereKarte = hoehereKarteEinTrumpf(karte1, karte2);
		}

		else if (karte1.getFarbe() == karte2.getFarbe()) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);
		}

		else {

			hoehereKarte = karte1;
		}

		return hoehereKarte;
	}
	
	/**
	 * Liefert von 2 Karten die h&ouml;here zur&uuml;ck, wobei eine Karte davon Trumpf ist.
	 * Die andere Karte ist nicht Trumpf und auch kein Bube.
	 * 
	 * @param karte1 - die erste Karte
	 * @param karte2 - die zweite Karte
	 * @return Der Bube wird zur&uuml;ck geliefert.
	 */
	public Spielkarte hoehereKarteEinTrumpf(Spielkarte karte1, Spielkarte karte2) {
		
		Spielkarte ergebnis;
		
		if (karte1.getFarbe() == trumpffarbe) {

			ergebnis = karte1;
		}

		else {

			ergebnis = karte2;
		}
		
		return ergebnis;
	}

	@Override
	public Spielkarte sortiereKarte(Spielkarte karte1, Spielkarte karte2) {

		Spielkarte hoehereKarte = null;

		if (karte1.getWert() == Wert.BUBE && karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehererBube(karte1, karte2);
		}

		else if (karte1.getWert() == Wert.BUBE || karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehereKarteEinBube(karte1, karte2);
		}

		else if (karte1.getFarbe() == trumpffarbe
				&& karte2.getFarbe() == trumpffarbe) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);

		}

		else if (karte1.getFarbe() == trumpffarbe
				|| karte2.getFarbe() == trumpffarbe) {

			hoehereKarte = hoehereKarteEinTrumpf(karte1, karte2);

		}

		else if (karte1.getFarbe() == karte2.getFarbe()) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);

		}

		else {
			
			hoehereKarte = hoehereFarbe(karte1, karte2);
		}
		
		return hoehereKarte;

	}
	
	@Override
	public String toString() {
		return trumpffarbe.toString();
	}

}
