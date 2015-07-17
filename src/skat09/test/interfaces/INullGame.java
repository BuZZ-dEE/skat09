package skat09.test.interfaces;

import java.util.ArrayList;

import skat09.spielkarte.Spielkarte;

/**
 * Das Interface INullspiel beinhaltet die Methodenr&uuml;mpfe f&uuml;r Nullspiel
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface INullGame {
	
	/**
	 * pr&uuml;ft die gespielte Karte
	 * @param blatt
	 * @param gespielteKarten
	 * @param zuPruefendeKarte
	 * @return boolean ob die gew&auml;hlte Karte g&uuml;ltig ist
	 */
	public abstract boolean gespielteKartePruefen(ArrayList<Spielkarte> blatt,
			Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte);
	
	/**
	 * pr&uuml;ft ob mit der gegeben Karte zu bedienen ist 
	 * @param blatt
	 * @param gespielteKarten
	 * @param zuPruefendeKarte
	 * @return boolean
	 */
	public abstract boolean farbeBedienen(ArrayList<Spielkarte> blatt, Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte);

	/**
	 * ermittelt die h&ouml;here Karte
	 * @param karte1
	 * @param karte2
	 * @return die h&ouml;here Karte
	 */
	public abstract Spielkarte hoehereKarte(Spielkarte karte1, Spielkarte karte2);

	/**
	 * sortiert Karten
	 * @param karte1
	 * @param karte2
	 * @return die h&ouml;here Karte
	 */
	public abstract Spielkarte sortiereKarte(Spielkarte karte1,
			Spielkarte karte2);

	/**
	 * Bewertet die Karte
	 * @param karte
	 * @return int
	 */
	public abstract int karteBewerten(Spielkarte karte);

	/**
	 * gibt nen String aus
	 * @return String
	 */
	public abstract String toString();

}