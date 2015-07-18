package skat09.spielart;

import java.util.*; //ArrayList

import skat09.spielkarte.Suit;
import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;
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
		if (gespielteKarten[0].getFarbe() == zuPruefendeKarte.getFarbe() && zuPruefendeKarte.getWert() != Value.BUBE) {

			ergebnis = true;
		}
		
		// nachsehen, ob der Spieler die Farbe bedienen konnte.
		else {

			for (int i = 0; i < blatt.size(); i++) {

				if (blatt.get(i).getFarbe() == gespielteKarten[0].getFarbe() && blatt.get(i).getWert() != Value.BUBE) {

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
		
		if (karte1.getFarbe() == Suit.KREUZ
				&& karte2.getFarbe() != Suit.KREUZ) {

			ergebnis = karte1;
		}

		else if (karte1.getFarbe() == Suit.PIK
				&& karte2.getFarbe() != Suit.KREUZ) {

			ergebnis = karte1;
		}

		else if (karte1.getFarbe() == Suit.HERZ
				&& (karte2.getFarbe() != Suit.KREUZ && karte2.getFarbe() != Suit.PIK)) {

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

		if (karte1.getWert() == Value.BUBE) {

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

		Value wert = karte.getWert();
		int ergebnis = 0;

		switch (wert) {
		case SECHS:
			ergebnis = 6;
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
		case DAME:
			ergebnis = 10;
			break;
		case KOENIG:
			ergebnis = 11;
			break;
		case ZEHN:
			ergebnis = 12;
			break;
		case ASS:
			ergebnis = 13;
			break;
		case BUBE:
			ergebnis = bubeBewerten(karte);
			break;
		default:
			ergebnis = -1;

		}
		return ergebnis;
	}

	//@Override
	public int bubeBewerten(PlayingCard karte) {

		Suit farbe = karte.getFarbe();
		int ergebnis = 0;

		switch (farbe) {

		case KARO:
			ergebnis = 14;
			break;
		case HERZ:
			ergebnis = 15;
			break;
		case PIK:
			ergebnis = 16;
			break;
		case KREUZ:
			ergebnis = 17;
			break;
		default:
			ergebnis = -1;

		}
		return ergebnis;
	}

}
