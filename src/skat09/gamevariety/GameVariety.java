package skat09.gamevariety;

import java.util.*; //ArrayList

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IGameVariety;


/**
 * Die Klasse Spielart legt fest, ob es sich bei dem Spiel um ein Null-, Grand-,
 * oder Farbspiel handelt und bietet die entsprechenden Regeln an.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
abstract public class GameVariety implements IGameVariety {

    /**
     * H&auml;t die Spielart
     */
	private GameVarietyName spielart;
	

	//@Override
	public GameVarietyName getSpielart() {
		return spielart;
	}
	
	//@Override
	public void setSpielart(GameVarietyName art) {
		spielart = art;
	}

	//@Override
	abstract public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte);
	
	//@Override
	public boolean farbeBedienen(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		
		boolean ergebnis = true;
		
		// Wenn die Farbe korrekt bedient wurde gib true zurueck.
		if (gespielteKarten[0].getSuit() == zuPruefendeKarte.getSuit() && zuPruefendeKarte.getValue() != Value.UNDER_KNAVE) {

			ergebnis = true;
		}
		
		// nachsehen, ob der Spieler die Farbe bedienen konnte.
		else {

			for (int i = 0; i < blatt.size(); i++) {

				if (blatt.get(i).getSuit() == gespielteKarten[0].getSuit() && blatt.get(i).getValue() != Value.UNDER_KNAVE) {

					ergebnis = false;
					break;
				}
			}
		}
		
		return ergebnis;
	}

	//@Override
	abstract public PlayingCard hoehereKarte(PlayingCard karte1, PlayingCard karte2);

	//@Override
	abstract public PlayingCard sortiereKarte(PlayingCard karte1,
			PlayingCard karte2);

	@Override
	public abstract String toString();

	//@Override
	public PlayingCard hoehererBube(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard ergebnis;

		if (bubeBewerten(karte1) < bubeBewerten(karte2)) {

			ergebnis = karte2;
		}

		else {

			ergebnis = karte1;
		}

		return ergebnis;
	}
	
	//@Override
	public PlayingCard hoehereFarbe(PlayingCard karte1, PlayingCard karte2) {
		
		PlayingCard ergebnis;
		
		if (karte1.getSuit() == Suit.ACORNS
				&& karte2.getSuit() != Suit.ACORNS) {

			ergebnis = karte1;
		}

		else if (karte1.getSuit() == Suit.LEAVES
				&& karte2.getSuit() != Suit.ACORNS) {

			ergebnis = karte1;
		}

		else if (karte1.getSuit() == Suit.HEARTS
				&& (karte2.getSuit() != Suit.ACORNS && karte2.getSuit() != Suit.LEAVES)) {

			ergebnis = karte1;
		}

		// else if (karte1.getFarbe() == Farbe.KARO
		// && (karte2.getFarbe() != Farbe.KREUZ
		// && karte2.getFarbe() != Farbe.PIK && karte2.getFarbe() !=
		// Farbe.HERZ)) {
		//
		// ergebnis = karte1;
		// }

		else {
			
			ergebnis = karte2;
		}

		
		return ergebnis;
	}
	
	//@Override
	public PlayingCard hoehereKarteEinBube(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard ergebnis;

		if (karte1.getValue() == Value.UNDER_KNAVE) {

			ergebnis = karte1;
		}

		else {

			ergebnis = karte2;
		}

		return ergebnis;
	}
	
	//@Override
	public PlayingCard hoehereKarteFarbe(PlayingCard karte1, PlayingCard karte2) {

		PlayingCard ergebnis;

		if (karteBewerten(karte1) < karteBewerten(karte2)) {

			ergebnis = karte2;
		}

		else {

			ergebnis = karte1;
		}

		return ergebnis;
	}
	
	//@Override
	public int karteBewerten(PlayingCard karte) {

		Value wert = karte.getValue();
		int ergebnis = 0;

		switch (wert) {
		case SIX:
			ergebnis = 6;
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
		case OVER_KNAVE:
			ergebnis = 10;
			break;
		case KING:
			ergebnis = 11;
			break;
		case TEN:
			ergebnis = 12;
			break;
		case DAUS:
			ergebnis = 13;
			break;
		case UNDER_KNAVE:
			ergebnis = bubeBewerten(karte);
			break;
		default:
			ergebnis = -1;

		}
		return ergebnis;
	}

	//@Override
	public int bubeBewerten(PlayingCard karte) {

		Suit farbe = karte.getSuit();
		int ergebnis = 0;

		switch (farbe) {

		case BELLS:
			ergebnis = 14;
			break;
		case HEARTS:
			ergebnis = 15;
			break;
		case LEAVES:
			ergebnis = 16;
			break;
		case ACORNS:
			ergebnis = 17;
			break;
		default:
			ergebnis = -1;

		}
		return ergebnis;
	}

}
