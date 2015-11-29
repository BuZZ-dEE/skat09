package main.player;

import java.io.IOException;

import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;


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
	boolean respond(int reizwert);

	/**
	 * Fragt den menschlichen Spieler, welchen Wert er als n&auml;chstes reizen
	 * will.
	 * 
	 * @param reizWert
	 *            - dieser Wert muss &uuml;berschritten werden
	 * @return reizwert des menschlichen Spielers
	 */
	boolean bid(int reizWert);

	/**
	 * Gibt die Karte zur&uuml;ck, die der Spieler auf den Tisch legen
	 * m&ouml;chte.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 * @throws IOException
	 */
	PlayingCard playCard(PlayingCard[] gespielteKarten);

	/**
	 * Gibt die gedr&uuml;ckten Spielkarten zur&uuml;ck.
	 * 
	 * @return gedr&uuml;ckte Spielkarten
	 */
	PlayingCard[] druecken(PlayingCard[] skat);

	/**
	 * Handspiel ermittelt, ob der Spieler ein Handspiel ansagt.
	 * 
	 * @return true, falls der Spieler ein Handspiel w&uuml;nscht
	 */
	boolean handgame();

	/**
	 * Ouvert ermittelt, ob der Spieler with offenen Karten spielen will.
	 * 
	 * @return true, falls offenes Spiel
	 */
	boolean ouvert();

	/**
	 * schneider ermittelt, ob der Spieler schneider spielen will.
	 * 
	 * @return true, falls schneider
	 */
	boolean schneider();

	/**
	 *Schwarz ermittelt, ob der Spieler schwarz spielen will.
	 * 
	 * @return true, falls schwarz
	 */
	boolean schwarz();

	/**
	 * SpielAnsagen ermittelt, ob der Spieler ein Farb-, Grand-, oder Nullspiel
	 * w&uuml;nscht.
	 * 
	 * @return die gew&uuml;nschte Spielart
	 */
	IGameVariety declareGame();

	/**
	 * Liefert ein Farbspiel with korrekt gesetzten Trumpffarbe zur&uuml;ck,
	 * nachdem der Spieler seine Wahl getroffen hat.
	 * 
	 * @return Farbspiel with gesetzter Trumpffarbe
	 */
	SuitGame suit();

	/**
	 * Benutzt der Spieler den Reizagenten, liefert diese Methode den maximalen
	 * Reizwert, den der Spieler spielen w&uuml;rde.
	 * 
	 * @return maximaler Reizwert
	 */
	int setBidLimit();

	/**
	 * Fragt den Spieler, ob er with Reizagent spielen m&ouml;chte.
	 * 
	 * @return true, falls der Spieler with Reizagent spielt
	 */
	boolean agent();

}