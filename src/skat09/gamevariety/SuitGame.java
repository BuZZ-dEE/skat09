package skat09.gamevariety;

import java.util.ArrayList;

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;


/**
 * Die Klasse Farbspiel implementiert alle Regeln des Farbspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verf&uuml;gung.
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class SuitGame extends GameVariety {

	private Suit trumpffarbe;

	/**
	 * Instanziert ein Farb - Spiel
	 */
	public SuitGame(Suit trumpffarbe) {

		this.trumpffarbe = trumpffarbe;
		setGameVariety(GameVarietyName.SUIT);

	}

	/**
	 * gibt die Trumpffarbe zurueck.
	 * 
	 * @return trumpffarbe
	 */
	public Suit getTrumpffarbe() {
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
	public boolean checkedPlayedCards(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {

		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}
		
		else if (gespielteKarten[0].getValue() == Value.UNDER_KNAVE || gespielteKarten[0].getSuit() == getTrumpffarbe()) {
			
			ergebnis = bubeOderTrumpfBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}

		else {
			
			ergebnis = followingSuit(blatt, gespielteKarten, zuPruefendeKarte);
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
	public boolean bubeOderTrumpfBedienen(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		
		boolean ergebnis = true;
		
		// Wenn Bube oder Trumpf gespielt wurde und korrekt bedient wurde gib true zurueck.
		if (zuPruefendeKarte.getValue() == Value.UNDER_KNAVE || getTrumpffarbe() == zuPruefendeKarte.getSuit()) {

			ergebnis = true;
		}
		
		// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
		else {

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < blatt.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (blatt.get(i).getValue() == Value.UNDER_KNAVE || blatt.get(i).getSuit() == getTrumpffarbe()) {

					ergebnis = false;
					break;
				}
			}
		}
		
		return ergebnis;
	}

	@Override
	public PlayingCard higherCard(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard hoehereKarte = null;

		if (karte1.getValue() == Value.UNDER_KNAVE && karte2.getValue() == Value.UNDER_KNAVE) {

			hoehereKarte = higherUnderKnave(karte1, karte2);
		}

		else if (karte1.getValue() == Value.UNDER_KNAVE || karte2.getValue() == Value.UNDER_KNAVE) {

			hoehereKarte = higherCardOneUnderKnave(karte1, karte2);
		}

		else if (karte1.getSuit() == trumpffarbe
				&& karte2.getSuit() == trumpffarbe) {

			hoehereKarte = higherCardSuit(karte1, karte2);
		}

		else if (karte1.getSuit() == trumpffarbe
				|| karte2.getSuit() == trumpffarbe) {

			hoehereKarte = hoehereKarteEinTrumpf(karte1, karte2);
		}

		else if (karte1.getSuit() == karte2.getSuit()) {

			hoehereKarte = higherCardSuit(karte1, karte2);
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
	public PlayingCard hoehereKarteEinTrumpf(PlayingCard karte1, PlayingCard karte2) {
		
		PlayingCard ergebnis;
		
		if (karte1.getSuit() == trumpffarbe) {

			ergebnis = karte1;
		}

		else {

			ergebnis = karte2;
		}
		
		return ergebnis;
	}

	@Override
	public PlayingCard sortCard(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard hoehereKarte = null;

		if (karte1.getValue() == Value.UNDER_KNAVE && karte2.getValue() == Value.UNDER_KNAVE) {

			hoehereKarte = higherUnderKnave(karte1, karte2);
		}

		else if (karte1.getValue() == Value.UNDER_KNAVE || karte2.getValue() == Value.UNDER_KNAVE) {

			hoehereKarte = higherCardOneUnderKnave(karte1, karte2);
		}

		else if (karte1.getSuit() == trumpffarbe
				&& karte2.getSuit() == trumpffarbe) {

			hoehereKarte = higherCardSuit(karte1, karte2);

		}

		else if (karte1.getSuit() == trumpffarbe
				|| karte2.getSuit() == trumpffarbe) {

			hoehereKarte = hoehereKarteEinTrumpf(karte1, karte2);

		}

		else if (karte1.getSuit() == karte2.getSuit()) {

			hoehereKarte = higherCardSuit(karte1, karte2);

		}

		else {
			
			hoehereKarte = higherSuit(karte1, karte2);
		}
		
		return hoehereKarte;

	}
	
	@Override
	public String toString() {
		return trumpffarbe.toString();
	}

}
