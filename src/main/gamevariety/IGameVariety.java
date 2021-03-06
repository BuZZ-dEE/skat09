package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;


/**
 * Das Interface ISpielart beinhaltet die Methodenr&uuml;mpfe f&uuml;r die Spielart
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IGameVariety {
	
	/**
	 * The game variety
	 * 
	 * <li>Grand
	 * <li>Null
	 * <li>Suit
	 * <li>Ramsch
	 * 
	 * @since 21.11.2015 00:27:37
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 *
	 */
	enum Name { GRAND, NULL,  SUIT, RAMSCH }
	
	/**
	 * gibt die aktuelle Spielart zurueck.
	 * 
	 * @return aktuelle Spielart
	 */
	Name getGameVariety();

	/**
	 * Setzt die Spielartbezeichnung der Spielart
	 * @param art Die Spielartbezeichnung
	 */
	void setGameVariety(Name art);

	/**
	 * Prueft in Abh&auml;ngigkeit von der zuerst gespielten Karten, ob eine Karte
	 * gespielt werden darf.
	 * 
	 * @param blatt - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param gespielteKarten - Die Karten, die schon gespielt wurden.
	 * @param zuPruefendeKarte - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	boolean checkedPlayedCards(ArrayList<PlayingCard> blatt,
							   PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte);

	/**
	 * Wenn die zuerst gespielte Karte eine Farbe war, die nicht Trumpf ist wird 
	 * gepr&uuml;ft, ob der Spieler noch Karten von dieser Farbe hat.
	 * 
	 * @param blatt - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param gespielteKarten - Die Karten, die schon gespielt wurden.
	 * @param zuPruefendeKarte - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	boolean followingSuit(ArrayList<PlayingCard> blatt,
						  PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte);

	/**
	 * Vergleicht 2 Spielkarten und gibt die hoeherwertige Karte zurueck. Wenn
	 * die Farben der Karten ungleich sind, ist die hoehere Karte, die Karte,
	 * die zuerst geworfen wurde.
	 * 
	 * @param karte1
	 * @param karte2
	 * @return Spielkarte , die hoeherwertig ist
	 */
	PlayingCard higherCard(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Vergleicht 2 Spielkarten und gibt die hoeherwertige Karte zurueck. Diese
	 * Kartenvergleich geschieht nur aufgrund von Wert und Farbe.
	 * 
	 * @param karte1
	 * @param karte2
	 * @return Spielkarte , die hoeherwertig ist
	 */
	PlayingCard sortCard(PlayingCard karte1,
						 PlayingCard karte2);

	/**
	 * Gibt den Namen der Spielart zur&uuml;ck.
	 */
	String toString();

	/**
	 * Liefert von 2 Karten den h&ouml;heren Buben zurück. Beide Karten sind Buben.
	 * 
	 * @param karte1 - der erste Bube
	 * @param karte2 - der zweite Bube
	 * @return Der h&ouml;here Bube von 2 Karten.
	 */
	PlayingCard higherUnderKnave(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Ermittelt von 2 Karten die Karte with der ho&uml;herwertigeren Farbe und
	 * gibt diese zur&uuml;ck. In Falle der gleichen Farbe, wird die erste Karte 
	 * zur&uuml;ck gegeben.
	 * 
	 * @param karte1 - Die erste Karte.
	 * @param karte2 - Die zweite Karte.
	 * @return Die Karte with der ho&uml;heren Farbe.
	 */
	PlayingCard higherSuit(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Liefert von 2 Karten die h&ouml;here zur&uuml;ck, wobei eine Karte davon ein Bube ist.
	 * Die andere Karte kann eine Trumpfkarte oder eine Karte sein, die kein Bube ist.
	 * 
	 * @param karte1 - die erste Karte
	 * @param karte2 - die zweite Karte
	 * @return Der Bube wird zurück geliefert.
	 */
	PlayingCard higherCardOneUnderKnave(PlayingCard karte1,
										PlayingCard karte2);

	/**
	 * Liefert von 2 Karten, welche die gleiche Farbe haben, die h&ouml;here zurück.
	 * 
	 * @param karte1 - die erste Karte
	 * @param karte2 - die zweite Karte
	 * @return Die ho&uml;here Karte.
	 */
	PlayingCard higherCardSuit(PlayingCard karte1,
							   PlayingCard karte2);

	/**
	 * Ordnet einer Karte einen Zahlenwert zu. Die geschieht in der Wertigkeit der Karten
	 * für die jeweilige Spielart.
	 * 
	 * @param karte - Die Karte der ein Zahlenwert zugeordnet werden soll.
	 * @return Der Zahlenwert der jeweiligen Karte.
	 */
	int evaluateCard(PlayingCard karte);

	/**
	 * Ordnet einem Buben einen Zahlenwert zu. Die geschieht in der Wertigkeit der Buben
	 * nach der Farbe.
	 * 
	 * @param karte - Bube, der einen Zahlenwert zugeordnet bekommen soll.
	 * @return Der Zahlenwert des jeweiligen Bubens.
	 */
	int evaluateUnderKnave(PlayingCard karte);

}