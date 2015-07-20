package skat09.gamevariety;

import java.util.ArrayList;

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Value;


/**
 * Die Klasse Grandspiel implementiert alle Regeln des Grandspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verf&uuml;gung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class GrandGame extends GameVariety {

	/**
	 * Instanziert ein Grand - Spiel
	 */
	public GrandGame() {

		setSpielart(GameVarietyName.GRAND);
	}


	
	@Override
	public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {

		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}
		
		else if (gespielteKarten[0].getWert() == Value.UNDER_KNAVE) {
			
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
		if (zuPruefendeKarte.getWert() == Value.UNDER_KNAVE) {

			ergebnis = true;
		}
		
		// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
		else {

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < blatt.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (blatt.get(i).getWert() == Value.UNDER_KNAVE) {

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

		if (karte1.getWert() == Value.UNDER_KNAVE && karte2.getWert() == Value.UNDER_KNAVE) {

			hoehereKarte = hoehererBube(karte1, karte2);

		}

		else if (karte1.getWert() == Value.UNDER_KNAVE || karte2.getWert() == Value.UNDER_KNAVE) {

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

		if (karte1.getWert() == Value.UNDER_KNAVE && karte2.getWert() == Value.UNDER_KNAVE) {

			hoehereKarte = hoehererBube(karte1, karte2);

		}

		else if (karte1.getWert() == Value.UNDER_KNAVE || karte2.getWert() == Value.UNDER_KNAVE) {

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
		return "Grand";
	}
}