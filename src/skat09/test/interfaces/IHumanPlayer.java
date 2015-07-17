package skat09.test.interfaces;

import java.io.IOException;

import skat09.spielart.SuitGame;
import skat09.spielkarte.Spielkarte;


/**
 * Das Interface IMenschlicherSpieler beinhaltet die Methodenr&uuml;mpfe f&uuml;r den
 * menschlichen Spieler
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IHumanPlayer {

	/**
	 * Die Methode simuliert das h&ouml;ren des menschlichen Spielers.
	 * 
	 * @param reizwert
	 *            - der aktuelle Reizwert
	 * @return boolean - true, falls der Spieler mitgeht
	 */
	public abstract boolean hoeren(int reizwert);

	/**
	 * Fragt den menschlichen Spieler, welchen Wert er als n&auml;chstes reizen
	 * will.
	 * 
	 * @param reizWert
	 *            - dieser Wert muss &uuml;berschritten werden
	 * @return reizwert des menschlichen Spielers
	 */
	public abstract boolean sagen(int reizWert);

	/**
	 * Gibt die Karte zur&uuml;ck, die der Spieler auf den Tisch legen
	 * m&ouml;chte.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 * @throws IOException
	 */
	public abstract Spielkarte spieleKarte(Spielkarte[] gespielteKarten);

	/**
	 * Gibt die gedr&uuml;ckten Spielkarten zur&uuml;ck.
	 * 
	 * @return gedr&uuml;ckte Spielkarten
	 */
	public abstract Spielkarte[] druecken(Spielkarte[] skat);

	/**
	 * Handspiel ermittelt, ob der Spieler ein Handspiel ansagt.
	 * 
	 * @return true, falls der Spieler ein Handspiel w&uuml;nscht
	 */
	public abstract boolean handspiel();

	/**
	 * Ouvert ermittelt, ob der Spieler mit offenen Karten spielen will.
	 * 
	 * @return true, falls offenes Spiel
	 */
	public abstract boolean ouvert();

	/**
	 * schneider ermittelt, ob der Spieler schneider spielen will.
	 * 
	 * @return true, falls schneider
	 */
	public abstract boolean schneider();

	/**
	 *Schwarz ermittelt, ob der Spieler schwarz spielen will.
	 * 
	 * @return true, falls schwarz
	 */
	public abstract boolean schwarz();

	/**
	 * SpielAnsagen ermittelt, ob der Spieler ein Farb-, Grand-, oder Nullspiel
	 * w&uuml;nscht.
	 * 
	 * @return die gew&uuml;nschte Spielart
	 */
	public abstract ISpielart spielAnsagen();

	/**
	 * Liefert ein Farbspiel mit korrekt gesetzten Trumpffarbe zur&uuml;ck,
	 * nachdem der Spieler seine Wahl getroffen hat.
	 * 
	 * @return Farbspiel mit gesetzter Trumpffarbe
	 */
	public abstract SuitGame farbe();

	/**
	 * Benutzt der Spieler den Reizagenten, liefert diese Methode den maximalen
	 * Reizwert, den der Spieler spielen w&uuml;rde.
	 * 
	 * @return maximaler Reizwert
	 */
	public abstract int reizlimitFestlegen();

	/**
	 * Fragt den Spieler, ob er mit Reizagent spielen m&ouml;chte.
	 * 
	 * @return true, falls der Spieler mit Reizagent spielt
	 */
	public abstract boolean agent();

}