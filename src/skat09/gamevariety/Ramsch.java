package skat09.gamevariety;

import java.util.ArrayList;

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Value;


/**
 * Die Klasse Grandspiel implementiert alle Regeln des Grandspiels und stellt diese für ein Skat-Spiel zur Verfügung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Ramsch extends GameVariety {
	/**
	 * Instanziert ein Ramsch - Spiel
	 */
	public Ramsch() {
		setGameVariety(GameVarietyName.RAMSCH);
	}

	@Override
	public boolean checkedPlayedCards(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}
		
		else if (gespielteKarten[0].getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = bubeBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}

		else {
			
			ergebnis = followingSuit(blatt, gespielteKarten, zuPruefendeKarte);
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
		if (zuPruefendeKarte.getValue() == Value.UNDER_KNAVE) {

			ergebnis = true;
		}
		
		// Wenn nicht bedient wurde, schaue, ob bedient werden konnte.
		else {

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < blatt.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (blatt.get(i).getValue() == Value.UNDER_KNAVE) {

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

		else if (karte1.getSuit() == karte2.getSuit()) {

			hoehereKarte = higherCardSuit(karte1, karte2);

		}

		else {

			hoehereKarte = karte1;
		}

		return hoehereKarte;
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
		return "Ramsch";
	}
	
	/**
	 * Ordnet einer Karte einen Zahlenwert zu. Die geschieht in der Wertigkeit der Karten
	 * f&uuml;r die jeweilige Spielart.
	 * 
	 * @param karte - Die Karte der ein Zahlenwert zugeordnet werden soll.
	 * @return Der Zahlenwert der jeweiligen Karte.
	 */
	public int evaluateCard(PlayingCard karte) {

		Value wert = karte.getValue();
		int ergebnis = 0;

		switch (wert) {
		case SIX:
			ergebnis = 7;
			break;
		case SEVEN:
			ergebnis = 7;
			break;
		case EIGHT:
			ergebnis = 8;
			break;
		case NINE:
			ergebnis = 9;
			break;
		case TEN:
			ergebnis = 10;
			break;	
		case OVER_KNAVE:
			ergebnis = 11;
			break;
		case KING:
			ergebnis = 12;
			break;
		case DAUS:
			ergebnis = 13;
			break;
		case UNDER_KNAVE:
			ergebnis = evaluateUnderKnave(karte);
			break;
//		default:
//			ergebnis = -1;

		}
		return ergebnis;
	}

}
