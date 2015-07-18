package skat09.spielart;

import java.util.ArrayList;

import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;
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

		case SECHS:
			ergebnis = Value.SECHS.ordinal();
			break;
		case SIEBEN:
			ergebnis = Value.SIEBEN.ordinal();
			break;
		case ACHT:
			ergebnis = Value.ACHT.ordinal();
			break;
		case NEUN:
			ergebnis = Value.NEUN.ordinal();
			break;
		case ZEHN:
			ergebnis = Value.ZEHN.ordinal();
			break;
		case BUBE:
			ergebnis = Value.BUBE.ordinal();
			break;
		case DAME:
			ergebnis = Value.DAME.ordinal();
			break;
		case KOENIG:
			ergebnis = Value.KOENIG.ordinal();
			break;
		case ASS:
			ergebnis = Value.ASS.ordinal();
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
