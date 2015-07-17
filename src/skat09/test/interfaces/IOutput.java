package skat09.test.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import skat09.spielart.SuitGame;
import skat09.spielkarte.Spielkarte;


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
	public abstract boolean hoeren(int wert);

	/**
	 * L&auml;sst einen Spieler sagen und fragt, ob er bei dem besagten Reizwert
	 * mitgehen m&ouml;chte.
	 * 
	 * @param reizWert
	 *            - aktueller Reizwert
	 * @return true, falls der spieler mitgeht
	 */
	public abstract boolean sagen(int reizWert);

	/**
	 * Fordert den Spieler auf, seine Gegener auszuw&auml;len.
	 * 
	 * @param nummer
	 *            1, falls 1. Gegner; 2. falls 1. Gegner
	 * @return String, der Gegerbezeichnung enth&auml;lt
	 */
	public abstract String gegner(int nummer);

	/**
	 * Fragt den Spieler, ob er R&auml;uberskat oder Skat nach internationaler
	 * Skatordnung spielen m&ouml;chte.
	 * 
	 * @return String, der die Variante enth&auml;lt r = R&auml;uberskat, i =
	 *         internationale Skatordnung
	 */
	public abstract String frageVariante();

	/**
	 * Informiert den Spieler dar&uuml;ber, dass er eine falsche Eingabe gemacht
	 * hat und bittet um erneute Eingabe.
	 */
	public abstract void falscheEingabe();

	/**
	 * Fragt den Spieler ob er hand spielen m&ouml;chte.
	 * 
	 * @return true, falls der Spieler hand spielt
	 */
	public abstract boolean handspiel();

	/**
	 * Steuert den gesamten Spielablauf.
	 */
	public abstract void spiel();


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
	public abstract int druecken(ArrayList<Spielkarte> blatt, int nummer);

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
	public abstract ISpielart spielAnsagen();

	/**
	 * Diese Methode fragt den Spieler nach der Trumpffarbe, die er spielen
	 * m&ouml;chte, sofern er ein Farbspiel gew&auml;hlt hat.
	 * 
	 * @return - das neue Farbspiel mit der gew&auml;hlten Trumpffarbe
	 */
	public abstract SuitGame farbe();

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
	public abstract Spielkarte spieleKarte(Spielkarte[] gespielteKarten,
			IPlayer spieler) throws IOException;

	/**
	 * Diese Methode gibt aus, welcher Spieler den Stich gewonnen hat.
	 * 
	 * @param spieler
	 *            - der Gewinner des Stichs
	 */
	public abstract void stichGewonnen(IPlayer spieler);

	/**
	 * Gibt das Blatt des Spielers aus.
	 * 
	 * @param spieler
	 *            - spieler, dessen Blatt ausgegeben werden soll
	 * @throws IOException
	 */
	public abstract void blattAusgeben(IPlayer spieler) throws IOException;

	/**
	 * Diese Methoden gibt die im Skat liegende Karten aus.
	 * 
	 * @throws IOException
	 */
	public abstract void skatAusgeben(Spielkarte[] skat) throws IOException;

	/**
	 * Teilt mit, dass ein Spieler weg ist.
	 * 
	 * @param spieler
	 *            - spieler der weg ist.
	 */
	public abstract void weg(IPlayer spieler);

	/**
	 * Diese Methode teilt dem Spieler mit, dass das Spiel beendet ist.
	 */
	public abstract void spielBeendet();

	/**
	 * Gibt nach einem Spiel aus, ob der Alleinspieler gewonnen oder verloren
	 * hat, wieviele Augen er hat und wieviele Punkte er f&uuml;r das Spiel
	 * bekommt.
	 */
	public abstract void auswertung(boolean gewonnen);

	/**
	 * L&auml;sst die Hinterhand gegen den vorherigen Gewinner reizen.
	 * 
	 * @param gewinner
	 *            - Spieler, der das Reizen zuvor gewonnen hat
	 */
	public abstract void hhVSgewinner(IPlayer gewinner);

	/**
	 * L&auml;sst die Mittelhand gegen die Vorhand reizen.
	 */
	public abstract void mhVSvh();

	/**
	 * Gibt eine Leerzeile aus.
	 */
	public abstract void leerzeile();

	/**
	 * Teilt dem Spieler mit, dass das Spiel eingepasst wird.
	 */
	public abstract void spielEinpassen();

	/**
	 * Teilt dem Spieler mit, dass das Spiel beginnt.
	 */
	public abstract void spielBeginnt();

	/**
	 * Fordert den Spieler auf eine andere Karte zu spielen.
	 */
	public abstract void andereKarte();

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
	public abstract void punkte(int punkte);

	/**
	 * Gibt aus, wer der Alleinspieler ist und dass dieser spielt.
	 */
	public abstract void alleinspieler();

	/**
	 * Gibt aus welche Spielart der Alleinspieler spielt.
	 */
	public abstract void trumpf();

	/**
	 * Gibt bekannt, dass ein neues Spiel gestartet wurde.
	 */
	public abstract void neuesSpiel();

	/**
	 * Liest einen String von der Ausgabe ein.
	 * 
	 * @return eingelesener String
	 */
	public abstract String einlesen();

	/**
	 * Gibt aus, welche Karte ein Spieler gespielt hat.
	 * 
	 * @param spieler
	 *            - Spieler, der die Karte spielt
	 * @param karte
	 *            - Karte, die gespielt wird
	 */
	public abstract void spieltKarte(IPlayer spieler, Spielkarte karte);

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
	public abstract boolean spielBeenden();

	/**
	 * Gibt die aktuelle Punkteliste aus.
	 */
	public abstract void punkteAusgeben();

	/**
	 * Loescht den Tisch. Fuer GUI.
	 */
	public abstract void tischLoeschen();

	/**
	 * Fragt den menschlichen Spieler nach seinem gew&uuml;nschten Blatt 
	 * @return Das gew&auml;hlte Blatt
	 */
	public abstract String getBlattwahl();

	/**
	 * Fragt den Spieler, ob er Sechserskat spielen m&ouml;chte oder nicht.
	 * 
	 * @return Ob Sechserskat gespielt werden soll oder nicht.
	 */
	public abstract String frageSechserskat();

	/**
	 * L&auml;sst die GUI das Hauptfenster &ouml;ffnen. Bleibt in der CLI leer.
	 */
	public abstract void hauptfensterOeffnen();

	/**
	 * Zeigt die Namen und Positionen der Computerspieler an.
	 */
	public abstract void positionAnzeigen();

	/**
	 * Wird nach abgeschlossem Spiel (10, bzw. 11 Stiche) aufgerufen.
	 * L&ouml;scht &uuml;berfl&uuml;ssige Elemente.
	 */
	public abstract void guiAufraumen();

	/**
	 * Die Statistik der Spieler wird auf dem Bildschirm ausgegeben.
	 */
	public abstract void statistik();

}