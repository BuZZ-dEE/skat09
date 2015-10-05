package main.ui;

import java.io.IOException;
import java.util.ArrayList;

import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.IPlayer;
import main.playingcard.PlayingCard;


/**
/**
 * Das Interface IAusgabe beinhaltet die Methodenr&uuml;mpfe f&uuml;r die CLIAusgabe
 * und die GUIAusgabe
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IOutput {

	/**
	 * Diese Methode gibt den Status der Variable release zur&uuml;ck
	 * 
	 * @return True, falls die Ausgabe frei ist, false, falls sie wartet
	 */
	public abstract boolean getRelease();

	/**
	 * Setzt den Status der Variable release. Diese soll true sein falls die
	 * Ausgabe frei ist und falls, falls sie warten soll.
	 * 
	 * @param release
	 *            Den Wert von release. True falls die Ausgabe frei ist, false
	 *            falls die Ausgabe wartet
	 */
	public abstract void setRelease(boolean release);


	
	/**
	 * Fragt den Spieler nach seinen Namen.
	 * 
	 * @return name des Spielers
	 */
	public abstract String name();

	/**
	 * L&auml;sst einen Spieler h&ouml;ren und fragt, ob er bei dem besagten
	 * Reizwert mitgehen m&ouml;chte.
	 * 
	 * @param wert
	 *            - aktueller Reizwert
	 * @return true, falls der Spieler mitgeht
	 */
	public abstract boolean respond(int wert);

	/**
	 * L&auml;sst einen Spieler bid und fragt, ob er bei dem besagten Reizwert
	 * mitgehen m&ouml;chte.
	 * 
	 * @param reizWert
	 *            - aktueller Reizwert
	 * @return true, falls der spieler mitgeht
	 */
	public abstract boolean bid(int reizWert);

	/**
	 * Fordert den Spieler auf, seine Gegener auszuw&auml;len.
	 * 
	 * @param nummer
	 *            1, falls 1. Gegner; 2. falls 1. Gegner
	 * @return String, der Gegerbezeichnung enth&auml;lt
	 */
	public abstract String adversary(int nummer);

	/**
	 * Fragt den Spieler, ob er R&auml;uberskat oder Skat nach internationaler
	 * Skatordnung spielen m&ouml;chte.
	 * 
	 * @return String, der die Variante enth&auml;lt r = R&auml;uberskat, i =
	 *         internationale Skatordnung
	 */
	public abstract String askForVariant();

	/**
	 * Informiert den Spieler dar&uuml;ber, dass er eine falsche Eingabe gemacht
	 * hat und bittet um erneute Eingabe.
	 */
	public abstract void wrongInputHint();

	/**
	 * Fragt den Spieler ob er hand spielen m&ouml;chte.
	 * 
	 * @return true, falls der Spieler hand spielt
	 */
	public abstract boolean handgame();

	/**
	 * Steuert den gesamten Spielablauf.
	 */
	public abstract void play();


	/**
	 * Zeigt dem Spieler seine Karten und fragt, welche davon er dr&uuml;cken
	 * m&ouml;chte.
	 * 
	 * @param blatt
	 *            - Blatt des Spielers, inklusive Skat
	 * @param nummer
	 *            - 1 fuer 1. Karte dr&uuml;cken, 2 fuer 2. Karte dr&uuml;cken
	 * @return nummer der gedr&uuml;ckten Karte
	 */
	public abstract int druecken(ArrayList<PlayingCard> blatt, int nummer);

	/**
	 * Fragt den menschlichen Spieler, ob er schneider spielen m&ouml;chte.
	 * 
	 * @return true, falls schneider
	 */
	public abstract boolean schneider();

	/**
	 * Fragt den menschlichen Spieler, ob er schwarz spielen m&ouml;chte.
	 * 
	 * @return true, falls schwarz
	 */
	public abstract boolean schwarz();

	/**
	 * Fragt den menschlichen Spieler, ob er ouvert spielen m&ouml;chte.
	 * 
	 * @return true, falls ouvert
	 */
	public abstract boolean ouvert();

	/**
	 * Fragt den menschlichen Spieler, welches Spiel er ansagen m&ouml;chte.
	 * 
	 * @return Spielart - die vom Spieler angesagte Spielart
	 */
	public abstract IGameVariety declareSuit();

	/**
	 * Diese Methode fragt den Spieler nach der Trumpffarbe, die er spielen
	 * m&ouml;chte, sofern er ein Farbspiel gew&auml;hlt hat.
	 * 
	 * @return - das neue Farbspiel mit der gew&auml;hlten Trumpffarbe
	 */
	public abstract SuitGame suitGame();

	/**
	 * Zeigt dem Spieler seine Handkarten und die bisher in den Stich gespielten
	 * Karten und fordert den Spieler auf seine n&auml;chste Karte zu spielen.
	 * 
	 * @param gespielteKarten
	 *            - Karten die auf dem Tisch liegen
	 * @param spieler
	 *            - der Spieler, der die Karte auch spielen soll
	 * @return Spielkarte, die der Spieler spielt
	 * @throws IOException
	 */
	public abstract PlayingCard playCard(PlayingCard[] gespielteKarten,
										 IPlayer spieler) throws IOException;

	/**
	 * Diese Methode gibt aus, welcher Spieler den Stich gewonnen hat.
	 * 
	 * @param spieler
	 *            - der Gewinner des Stichs
	 */
	public abstract void trickWon(IPlayer spieler);

	/**
	 * Gibt das Blatt des Spielers aus.
	 * 
	 * @param spieler
	 *            - spieler, dessen Blatt ausgegeben werden soll
	 * @throws IOException
	 */
	public abstract void outputHand(IPlayer spieler) throws IOException;

	/**
	 * Diese Methoden gibt die im Skat liegende Karten aus.
	 * 
	 * @throws IOException
	 */
	public abstract void outputSkat(PlayingCard[] skat) throws IOException;

	/**
	 * Teilt mit, dass ein Spieler pass ist.
	 * 
	 * @param spieler
	 *            - spieler der pass ist.
	 */
	public abstract void pass(IPlayer spieler);

	/**
	 * Diese Methode teilt dem Spieler mit, dass das Spiel beendet ist.
	 */
	public abstract void gameOver();

	/**
	 * Gibt nach einem Spiel aus, ob der Alleinspieler isWon oder verloren
	 * hat, wieviele Augen er hat und wieviele Punkte er f&uuml;r das Spiel
	 * bekommt.
	 */
	public abstract void showEvaluation(boolean isWon);

	/**
	 * L&auml;sst die Hinterhand gegen den vorherigen Gewinner reizen.
	 * 
	 * @param winner
	 *            - Spieler, der das Reizen zuvor gewonnen hat
	 */
	public abstract void hinterhandVsWinner(IPlayer winner);

	/**
	 * L&auml;sst die Mittelhand gegen die Vorhand reizen.
	 */
	public abstract void mittelhandVsVorhand();

	/**
	 * Gibt eine Leerzeile aus.
	 */
	public abstract void blankLine();

	/**
	 * Teilt dem Spieler mit, dass das Spiel eingepasst wird.
	 */
	public abstract void passGame();

	/**
	 * Teilt dem Spieler mit, dass das Spiel beginnt.
	 */
	public abstract void gameBegins();

	/**
	 * Fordert den Spieler auf eine andere Karte zu spielen.
	 */
	public abstract void anotherCard();

	/**
	 * Gibt aus wieviele Augen der Alleinspieler erspielt hat.
	 * 
	 * @param augen
	 *            - die vom Alleinspieler erspielten Augen
	 */
	public abstract void augen(int augen);

	/**
	 * Gibt aus wieviele Punkte der Alleinspieler erreicht hat.
	 * 
	 * @param punkte
	 *            - die vom Alleinspieler erzielten Punkte
	 */
	public abstract void points(int punkte);

	/**
	 * Gibt aus, wer der Alleinspieler ist und dass dieser spielt.
	 */
	public abstract void showDeclarer();

	/**
	 * Gibt aus welche Spielart der Alleinspieler spielt.
	 */
	public abstract void trump();

	/**
	 * Gibt bekannt, dass ein neues Spiel gestartet wurde.
	 */
	public abstract void newGame();

	/**
	 * Liest einen String von der Ausgabe ein.
	 * 
	 * @return eingelesener String
	 */
	public abstract String readInput();

	/**
	 * Gibt aus, welche Karte ein Spieler gespielt hat.
	 * 
	 * @param player
	 *            - Spieler, der die Karte spielt
	 * @param card
	 *            - Karte, die gespielt wird
	 */
	public abstract void spieltKarte(IPlayer player, PlayingCard card);

	/**
	 * Fragt den Spieler, wie hoch er reizen m&ouml;chte.
	 * 
	 * @return maximaler Reizwert des Spielers
	 */
	public abstract int reizlimitFestlegen();

	/**
	 * Fragt den Spieler, ob der Reizagent aktiviert werden soll.
	 * 
	 * @return true, falls der Spieler mit Agent spielen will.
	 */
	public abstract boolean reizAgent();

	/**
	 * Fragt den Spieler, ob er das Spiel beenden m&ouml;chte.
	 * 
	 * @return true, falls das Spiel beendet werden soll
	 */
	public abstract boolean quitGame();

	/**
	 * Gibt die aktuelle Punkteliste aus.
	 */
	public abstract void outputPoints();

	/**
	 * Loescht den Tisch. Fuer GUI.
	 */
	public abstract void deleteTable();

	/**
	 * Fragt den menschlichen Spieler nach seinem gew&uuml;nschten Blatt 
	 * @return Das gew&auml;hlte Blatt
	 */
	public abstract String getDeckSelection();

	/**
	 * Fragt den Spieler, ob er Sechserskat spielen m&ouml;chte oder nicht.
	 * 
	 * @return Ob Sechserskat gespielt werden soll oder nicht.
	 */
	public abstract String askForSixSkat();

	/**
	 * L&auml;sst die GUI das Hauptfenster &ouml;ffnen. Bleibt in der CLI leer.
	 */
	public abstract void openGameTable();

	/**
	 * Zeigt die Namen und Positionen der Computerspieler an.
	 */
	public abstract void showPosition();

	/**
	 * Wird nach abgeschlossem Spiel (10, bzw. 11 Stiche) aufgerufen.
	 * L&ouml;scht &uuml;berfl&uuml;ssige Elemente.
	 */
	public abstract void cleanUpGUI();

	/**
	 * Die Statistik der Spieler wird auf dem Bildschirm ausgegeben.
	 */
	public abstract void statistics();

}