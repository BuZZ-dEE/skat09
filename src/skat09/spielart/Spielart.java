package skat09.spielart;

import java.util.*; //ArrayList

import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.ISpielart;


/**
 * Die Klasse Spielart legt fest, ob es sich bei dem Spiel um ein Null-, Grand-,
 * oder Farbspiel handelt und bietet die entsprechenden Regeln an.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
abstract public class Spielart implements ISpielart {

	//
	// Datenfelder
	//
    /**
     * H&auml;t die Spielart
     */
	private Spielartbezeichnung spielart;

	//
	// get-Methoden
	//

	//@Override
	public Spielartbezeichnung getSpielart() {
		return spielart;
	}

	//
	// set-Methoden
	//

	//@Override
	public void setSpielart(Spielartbezeichnung art) {
		spielart = art;
	}

	//
	// weitere Methoden
	//

	//@Override
	abstract public boolean gespielteKartePruefen(ArrayList<Spielkarte> blatt,
			Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte);
	
	//@Override
	public boolean farbeBedienen(ArrayList<Spielkarte> blatt, Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte) {
		
		boolean ergebnis = true;
		
		// Wenn die Farbe korrekt bedient wurde gib true zurueck.
		if (gespielteKarten[0].getFarbe() == zuPruefendeKarte.getFarbe() && zuPruefendeKarte.getWert() != Wert.BUBE) {

			ergebnis = true;
		}
		
		// nachsehen, ob der Spieler die Farbe bedienen konnte.
		else {

			for (int i = 0; i < blatt.size(); i++) {

				if (blatt.get(i).getFarbe() == gespielteKarten[0].getFarbe() && blatt.get(i).getWert() != Wert.BUBE) {

					ergebnis = false;
					break;
				}
			}
		}
		
		return ergebnis;
	}

	//@Override
	abstract public Spielkarte hoehereKarte(Spielkarte karte1, Spielkarte karte2);

	//@Override
	abstract public Spielkarte sortiereKarte(Spielkarte karte1,
			Spielkarte karte2);

	@Override
	public abstract String toString();

	//@Override
	public Spielkarte hoehererBube(Spielkarte karte1, Spielkarte karte2) {

		Spielkarte ergebnis;

		if (bubeBewerten(karte1) < bubeBewerten(karte2)) {

			ergebnis = karte2;
		}

		else {

			ergebnis = karte1;
		}

		return ergebnis;
	}
	
	//@Override
	public Spielkarte hoehereFarbe(Spielkarte karte1, Spielkarte karte2) {
		
		Spielkarte ergebnis;
		
		if (karte1.getFarbe() == Farbe.KREUZ
				&& karte2.getFarbe() != Farbe.KREUZ) {

			ergebnis = karte1;
		}

		else if (karte1.getFarbe() == Farbe.PIK
				&& karte2.getFarbe() != Farbe.KREUZ) {

			ergebnis = karte1;
		}

		else if (karte1.getFarbe() == Farbe.HERZ
				&& (karte2.getFarbe() != Farbe.KREUZ && karte2.getFarbe() != Farbe.PIK)) {

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
	public Spielkarte hoehereKarteEinBube(Spielkarte karte1, Spielkarte karte2) {

		Spielkarte ergebnis;

		if (karte1.getWert() == Wert.BUBE) {

			ergebnis = karte1;
		}

		else {

			ergebnis = karte2;
		}

		return ergebnis;
	}
	
	//@Override
	public Spielkarte hoehereKarteFarbe(Spielkarte karte1, Spielkarte karte2) {

		Spielkarte ergebnis;

		if (karteBewerten(karte1) < karteBewerten(karte2)) {

			ergebnis = karte2;
		}

		else {

			ergebnis = karte1;
		}

		return ergebnis;
	}
	
	//@Override
	public int karteBewerten(Spielkarte karte) {

		Wert wert = karte.getWert();
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
	public int bubeBewerten(Spielkarte karte) {

		Farbe farbe = karte.getFarbe();
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
