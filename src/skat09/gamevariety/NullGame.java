package skat09.gamevariety;

import java.util.ArrayList;

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Value;
import skat09.test.interfaces.INullGame;


/**
 * Die Klasse Nullspiel implementiert alle Regeln des Nullspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verfuegung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class NullGame extends GameVariety implements INullGame {

	/**
	 * Instanziert ein Null - Spiel
	 */
	public NullGame() {
		setSpielart(GameVarietyName.NULL);
	}

	
	@Override
	public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {

		boolean ergebnis = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (gespielteKarten[0] == null) {

			ergebnis = true;
		}

		else {

			ergebnis = farbeBedienen(blatt, gespielteKarten, zuPruefendeKarte);
		}

		return ergebnis;
	}

	
	@Override
	public boolean farbeBedienen(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {

		boolean ergebnis = true;

		// Wenn die Farbe korrekt bedient wurde gib true zurueck.
		if (gespielteKarten[0].getFarbe() == zuPruefendeKarte.getFarbe()) {

			ergebnis = true;
		}

		// nachsehen, ob der Spieler die Farbe bedienen konnte.
		else {

			for (int i = 0; i < blatt.size(); i++) {

				if (blatt.get(i).getFarbe() == gespielteKarten[0].getFarbe()) {

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

		if (karte1.getFarbe() == karte2.getFarbe()) {

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

		if (karte1.getFarbe() == karte2.getFarbe()) {

			hoehereKarte = hoehereKarteFarbe(karte1, karte2);

		}

		else {

			hoehereKarte = hoehereFarbe(karte1, karte2);
		}

		return hoehereKarte;
	}


	
	@Override
	public int karteBewerten(PlayingCard karte) {

		Value wert = karte.getWert();
		int ergebnis = -1;

		switch (wert) {

		case SIX:
			ergebnis = Value.SIX.ordinal();
			break;
		case SEVEN:
			ergebnis = Value.SEVEN.ordinal();
			break;
		case EIGHT:
			ergebnis = Value.EIGHT.ordinal();
			break;
		case NINE:
			ergebnis = Value.NINE.ordinal();
			break;
		case TEN:
			ergebnis = Value.TEN.ordinal();
			break;
		case UNDER_KNAVE:
			ergebnis = Value.UNDER_KNAVE.ordinal();
			break;
		case OVER_KNAVE:
			ergebnis = Value.OVER_KNAVE.ordinal();
			break;
		case KING:
			ergebnis = Value.KING.ordinal();
			break;
		case DAUS:
			ergebnis = Value.DAUS.ordinal();
			break;
		// default:
		// ergebnis = -1;
		//
		}
		return ergebnis;
	}


	@Override
	public String toString() {
		return "Null";
	}

}