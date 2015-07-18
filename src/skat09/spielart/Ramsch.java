package skat09.spielart;

import java.util.ArrayList;

import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Wert;


/**
 * Die Klasse Grandspiel implementiert alle Regeln des Grandspiels und stellt diese für ein Skat-Spiel zur Verfügung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Ramsch extends Spielart {
	/**
	 * Instanziert ein Ramsch - Spiel
	 */
	public Ramsch() {
		setSpielart(Spielartbezeichnung.RAMSCH);
	}

	@Override
	public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}
		
		else if (gespielteKarten[0].getWert() == Wert.BUBE) {
			
			ergebnis = bubeBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}

		else {
			
			ergebnis = farbeBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}
		
		return ergebnis;
	}
	
	/**
	 * Wenn die zuerst gespielte Karte ein Bube war wird gepr&uuml;ft,
	 * ob der Spieler noch Buben hat.
	 * 
	 * @param blatt - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param gespielteKarten - Die Karten, die schon gespielt wurden.
	 * @param zuPruefendeKarte - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	public boolean bubeBedienen(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		
		boolean ergebnis = true;
		
		// Wenn Bube gespielt wurde und korrekt bedient wurde gib true zurueck.
		if (zuPruefendeKarte.getWert() == Wert.BUBE) {

			ergebnis = true;
		}
		
		// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
		else {

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < blatt.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (blatt.get(i).getWert() == Wert.BUBE) {

					ergebnis = false;
					break;
				}
			}
		}
		
		return ergebnis;
	}

	@Override
	public PlayingCard hoehereKarte(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard hoehereKarte = null;

		if (karte1.getWert() == Wert.BUBE && karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehererBube(karte1, karte2);

		}

		else if (karte1.getWert() == Wert.BUBE || karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehereKarteEinBube(karte1, karte2);

		}

		else if (karte1.getFarbe() == karte2.getFarbe()) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);

		}

		else {

			hoehereKarte = karte1;
		}

		return hoehereKarte;
	}

	@Override
	public PlayingCard sortiereKarte(PlayingCard karte1, PlayingCard karte2) {
		PlayingCard hoehereKarte = null;

		if (karte1.getWert() == Wert.BUBE && karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehererBube(karte1, karte2);

		}

		else if (karte1.getWert() == Wert.BUBE || karte2.getWert() == Wert.BUBE) {

			hoehereKarte = hoehereKarteEinBube(karte1, karte2);

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
		return "Ramsch";
	}
	
	/**
	 * Ordnet einer Karte einen Zahlenwert zu. Die geschieht in der Wertigkeit der Karten
	 * f&uuml;r die jeweilige Spielart.
	 * 
	 * @param karte - Die Karte der ein Zahlenwert zugeordnet werden soll.
	 * @return Der Zahlenwert der jeweiligen Karte.
	 */
	public int karteBewerten(PlayingCard karte) {

		Wert wert = karte.getWert();
		int ergebnis = 0;

		switch (wert) {
		case SECHS:
			ergebnis = 7;
			break;
		case SIEBEN:
			ergebnis = 7;
			break;
		case ACHT:
			ergebnis = 8;
			break;
		case NEUN:
			ergebnis = 9;
			break;
		case ZEHN:
			ergebnis = 10;
			break;	
		case DAME:
			ergebnis = 11;
			break;
		case KOENIG:
			ergebnis = 12;
			break;
		case ASS:
			ergebnis = 13;
			break;
		case BUBE:
			ergebnis = bubeBewerten(karte);
			break;
//		default:
//			ergebnis = -1;

		}
		return ergebnis;
	}

}
