package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;

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
	boolean checkedPlayedCards(ArrayList<PlayingCard> blatt,
							   PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte);
	
	/**
	 * pr&uuml;ft ob with der gegeben Karte zu bedienen ist
	 * @param blatt
	 * @param gespielteKarten
	 * @param zuPruefendeKarte
	 * @return boolean
	 */
	boolean followingSuit(ArrayList<PlayingCard> blatt, PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte);

	/**
	 * ermittelt die h&ouml;here Karte
	 * @param karte1
	 * @param karte2
	 * @return die h&ouml;here Karte
	 */
	PlayingCard higherCard(PlayingCard karte1, PlayingCard karte2);

	/**
	 * sortiert Karten
	 * @param karte1
	 * @param karte2
	 * @return die h&ouml;here Karte
	 */
	PlayingCard sortCard(PlayingCard karte1,
						 PlayingCard karte2);

	/**
	 * Bewertet die Karte
	 * @param karte
	 * @return int
	 */
	int evaluateCard(PlayingCard karte);

	/**
	 * gibt nen String aus
	 * @return String
	 */
	String toString();

}