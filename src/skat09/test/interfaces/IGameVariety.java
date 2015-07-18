package skat09.test.interfaces;

import java.util.ArrayList;

import skat09.gamevariety.GameVarietyName;
import skat09.playingcard.PlayingCard;


/**
 * Das Interface ISpielart beinhaltet die Methodenr&uuml;mpfe f&uuml;r die Spielart
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IGameVariety {

	/**
	 * gibt die aktuelle Spielart zurueck.
	 * 
	 * @return aktuelle Spielart
	 */
	public abstract GameVarietyName getSpielart();

	/**
	 * Setzt die Spielartbezeichnung der Spielart
	 * @param art Die Spielartbezeichnung
	 */
	public abstract void setSpielart(GameVarietyName art);

	/**
	 * Prueft in Abh&auml;ngigkeit von der zuerst gespielten Karten, ob eine Karte
	 * gespielt werden darf.
	 * 
	 * @param blatt - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param gespielteKarten - Die Karten, die schon gespielt wurden.
	 * @param zuPruefendeKarte - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	abstract public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt,
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
	public abstract boolean farbeBedienen(ArrayList<PlayingCard> blatt,
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
	abstract public PlayingCard hoehereKarte(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Vergleicht 2 Spielkarten und gibt die hoeherwertige Karte zurueck. Diese
	 * Kartenvergleich geschieht nur aufgrund von Wert und Farbe.
	 * 
	 * @param karte1
	 * @param karte2
	 * @return Spielkarte , die hoeherwertig ist
	 */
	abstract public PlayingCard sortiereKarte(PlayingCard karte1,
			PlayingCard karte2);

	/**
	 * Gibt den Namen der Spielart zur&uuml;ck.
	 */
	public abstract String toString();

	/**
	 * Liefert von 2 Karten den h&ouml;heren Buben zurück. Beide Karten sind Buben.
	 * 
	 * @param karte1 - der erste Bube
	 * @param karte2 - der zweite Bube
	 * @return Der h&ouml;here Bube von 2 Karten.
	 */
	public abstract PlayingCard hoehererBube(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Ermittelt von 2 Karten die Karte mit der ho&uml;herwertigeren Farbe und
	 * gibt diese zur&uuml;ck. In Falle der gleichen Farbe, wird die erste Karte 
	 * zur&uuml;ck gegeben.
	 * 
	 * @param karte1 - Die erste Karte.
	 * @param karte2 - Die zweite Karte.
	 * @return Die Karte mit der ho&uml;heren Farbe.
	 */
	public abstract PlayingCard hoehereFarbe(PlayingCard karte1, PlayingCard karte2);

	/**
	 * Liefert von 2 Karten die h&ouml;here zur&uuml;ck, wobei eine Karte davon ein Bube ist.
	 * Die andere Karte kann eine Trumpfkarte oder eine Karte sein, die kein Bube ist.
	 * 
	 * @param karte1 - die erste Karte
	 * @param karte2 - die zweite Karte
	 * @return Der Bube wird zurück geliefert.
	 */
	public abstract PlayingCard hoehereKarteEinBube(PlayingCard karte1,
			PlayingCard karte2);

	/**
	 * Liefert von 2 Karten, welche die gleiche Farbe haben, die h&ouml;here zurück.
	 * 
	 * @param karte1 - die erste Karte
	 * @param karte2 - die zweite Karte
	 * @return Die ho&uml;here Karte.
	 */
	public abstract PlayingCard hoehereKarteFarbe(PlayingCard karte1,
			PlayingCard karte2);

	/**
	 * Ordnet einer Karte einen Zahlenwert zu. Die geschieht in der Wertigkeit der Karten
	 * für die jeweilige Spielart.
	 * 
	 * @param karte - Die Karte der ein Zahlenwert zugeordnet werden soll.
	 * @return Der Zahlenwert der jeweiligen Karte.
	 */
	public abstract int karteBewerten(PlayingCard karte);

	/**
	 * Ordnet einem Buben einen Zahlenwert zu. Die geschieht in der Wertigkeit der Buben
	 * nach der Farbe.
	 * 
	 * @param karte - Bube, der einen Zahlenwert zugeordnet bekommen soll.
	 * @return Der Zahlenwert des jeweiligen Bubens.
	 */
	public abstract int bubeBewerten(PlayingCard karte);

}