package skat09.test.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import skat09.gamevariety.SuitGame;
import skat09.player.Position;
import skat09.spielkarte.PlayingCard;

/**
 * Das Interface ISpieler beinhaltet die Methodenr&uuml;mpfe f&uuml;r den Spieler
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IPlayer {

	/**
	 * Gibt den Namen des Spielers zur&uuml;ck.
	 * 
	 * @return Name des Spielers
	 */
	public abstract String getName();

	/**
	 * Gibt die Position des Spielers zur&uuml;ck.
	 * 
	 * @return Position des Spielers
	 */
	public abstract Position getPosition();

	/**
	 * Gibt das Blatt des Spielers zur&uuml;ck.
	 * 
	 * @return Blatt des Spielers
	 */
	public abstract ArrayList<PlayingCard> getBlatt();

	/**
	 * Gibt die vom Spieler gewonnenen Stiche zur&uuml;ck.
	 * 
	 * @return vom Spieler gewonnen Stiche
	 */
	public abstract ArrayList<PlayingCard> getStiche();

	/**
	 * Gibt an, ob der Spieler Alleinspieler ist.
	 * 
	 * @return true, wenn Spieler Alleinspieler ist
	 */
	public abstract boolean getIstAlleinspieler();

	/**
	 * Liefert alle bisher vom Spieler gespielten Spiele in einer ArrayList
	 * zur&uuml;ck.
	 * 
	 * @return alle vom Spieler bisher gespielten Spiele
	 */
	public abstract ArrayList<Integer> getSpiele();

	/**
	 * Gibt den Mitspieler eines Spielers zur&uuml;ck, sofern dieser nicht der
	 * Alleinspieler ist
	 * 
	 * @return Mitspieler des Spielers
	 */
	public abstract IPlayer getMitspieler();

	/**
	 * Gibt die bisher gespielten Karten
	 * 
	 * @return alle bisher gefallenen Karten
	 */
	public abstract ArrayList<PlayingCard> getAllegespieltenkarten();

	/**
	 * Liefert das Restblatt zur&uumlck.
	 * 
	 * @return das restblatt
	 */
	public abstract ArrayList<PlayingCard> getRestblatt();

	/**
	 * Liefert die im Spieler gesetzte Spielart zur&uuml;ck.
	 * 
	 * @return die gesetzte Spielart
	 */
	public abstract IGameVariety getSpielart();
	
	/**
	 * gibt die Handspiele zur&uuml;ck
	 * @return the handspiel
	 */
	public int getHandspiele();
	
	/**
	 * Setzt die Handspiele
	 * @param handspiele
	 */
	public void setHandspiele(int handspiele);

	/**
	 * Setzt die Spielart im Spieler.
	 * 
	 * @param spielart
	 *            - die Spielart, die momentan gespielt wird
	 */
	public abstract void setSpielart(IGameVariety spielart);

	/**
	 * Setzt die Position des Spielers neu.
	 * 
	 * @param position
	 *            - neue Position des Spielers
	 */
	public abstract void setPosition(Position position);

	/**
	 * Gibt dem Spieler ein neues Blatt in die Hand.
	 * 
	 * @param blatt
	 *            - neues Blatt
	 */
	public abstract void setBlatt(ArrayList<PlayingCard> blatt);

	/**
	 * Setzt das Alleinspielerflag.
	 * 
	 * @param istAlleinspieler
	 *            - true, wenn Spieler Alleinspieler ist
	 */
	public abstract void setIstAlleinspieler(boolean istAlleinspieler);

	/**
	 * Setzt das Mitspielerflag.
	 * 
	 * @param mitspieler
	 *            - Der Spieler, der Mitspieler ist.
	 */
	public abstract void setMitspieler(IPlayer mitspieler);

	/**
	 * Setzt die Liste mit den vom Spieler gespielten Spielen
	 * 
	 * @param spiele
	 */
	public abstract void setSpiele(ArrayList<Integer> spiele);

	/**
	 * F&uuml;gt der Stichliste einen gewonnenen Stich hinzu.
	 * 
	 * @param stiche
	 *            - der vom Spieler gewonnene Stich
	 */
	public abstract void setStiche(ArrayList<PlayingCard> stiche);

	/**
	 * Setzt den Namen des Spielers. Wird ben&ouml;tigt, damit der Name
	 * ge&auml;ndert werden kann, falls er doppelt auftaucht.
	 * 
	 * @param name
	 */
	public abstract void setName(String name);

	/**
	 * 
	 */
	public abstract void setAlleGespieltenKarten(ArrayList<PlayingCard> karten);

	/**
	 * Setzt die Truempfe Variable neu.
	 * 
	 * @param truempfe - die neuen Truempfe
	 */
	public abstract void setTruempfe(PlayingCard[] truempfe);

	/**
	 * Setzt das Deck im Spieler, damit der Spieler das Deck kennt.
	 * 
	 * @param deck - Das Deck.
	 */
	public abstract void setDeck(ArrayList<PlayingCard> deck);

	/**
	 * Setzt den Skat im Spieler, damit der Spieler den Skat kennt. Dies soll nur
	 * gesetzt werden, wenn der Spieler Alleinspieler ist.
	 * 
	 * @param skat - Der Skat.
	 */
	public abstract void setSkat(ArrayList<PlayingCard> skat);

	/**
	 * F&uuml;gt einen gewonnenen Stich zu den bisher gewonnenen Stichen hinzu.
	 * 
	 * @param stich
	 *            - die drei am Tisch gewonnenen Karten
	 */
	public abstract void stichHinzufuegen(PlayingCard[] stich);

	/**
	 * F&uuml;gt einen gewonnenen Stich zu den bisher gespielten Stichen hinzu.
	 * @param gespielteKarten
	 */
	/**
	 * F&uuml;gt die gespielten Karten, in die alleGespieltenKarten
	 * Variable hinzu.
	 * 
	 * @param gespielteKarten - die in einer Runde gespielten Karten
	 */
	public abstract void gespielteKartenHinzufuegen(PlayingCard[] gespielteKarten);

	/**
	 * Ermittelt die Karten, die der Spieler spielen darf.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die auf dem Tisch liegen.
	 * @return Die Karten, die gespielt werden d&uuml;rfen.
	 */
	public abstract ArrayList<PlayingCard> spielbareKarten(
			PlayingCard[] gespielteKarten);

	/**
	 * Gibt die Karte zur&uuml;ck, die der Spieler auf den Tisch legen
	 * m&ouml;chte.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 * @throws IOException
	 */
	abstract public PlayingCard spieleKarte(PlayingCard[] gespielteKarten)
			throws IOException;

	/**
	 * Die Methode wählt zufällig eine Karte aus, die dann gespielt wird.
	 * 
	 * @param gespielteKarten - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return - Spielkarte, die zufällig ausgewählt wurde.
	 */
	public abstract PlayingCard zufaelligErlaubteKarteSpielen(
			PlayingCard[] gespielteKarten);

	/**
	 * Gibt die gedr&uuml;ckten Spielkarten zur&uuml;ck.
	 * 
	 * @return gedr&uuml;ckte Spielkarten
	 */
	abstract public PlayingCard[] druecken(PlayingCard[] skat);

	/**
	 * SpielAnsagen ermittelt, ob der Spieler ein Farb-, Grand-, oder Nullspiel
	 * w&uuml;nscht.
	 * 
	 * @return die gew&uuml;nschte Spielart
	 */
	abstract public IGameVariety spielAnsagen();

	/**
	 * Handspiel ermittelt, ob der Spieler ein Handspiel ansagt.
	 * 
	 * @return true, falls der Spieler ein Handspiel w&uuml;nscht
	 */
	abstract public boolean handspiel();

	/**
	 * Ouvert ermittelt, ob der Spieler mit offenen Karten spielen will.
	 * 
	 * @return true, falls offenes Spiel
	 */
	abstract public boolean ouvert();

	/**
	 * schneider ermittelt, ob der Spieler schneider spielen will.
	 * 
	 * @return true, falls schneider
	 */
	abstract public boolean schneider();

	/**
	 *Schwarz ermittelt, ob der Spieler schwarz spielen will.
	 * 
	 * @return true, falls schwarz
	 */
	abstract public boolean schwarz();

	/**
	 * Liefert ein Farbspiel mit korrekt gesetzten Trumpffarbe zur&uuml;ck,
	 * nachdem der Spieler seine Wahl getroffen hat.
	 * 
	 * @return Farbspiel mit gesetzter Trumpffarbe
	 */
	abstract public SuitGame farbe();

	/**
	 * F&uuml;gt einen neuen Eintrag zu Liste der Spiele dazu, wenn der Spieler
	 * ein Spiel als Alleinspieler gespielt hat.
	 * 
	 * @param punkte
	 *            - punkte, die der Spieler erreicht hat
	 * @return die neue Liste
	 */
	public abstract ArrayList<Integer> neuerEintrag(int punkte);

	/**
	 * Diese Methode simuliert das h&ouml;ren eines Spielers.
	 * 
	 * @param reizwert
	 *            - reizwert der gesagt wurde
	 * @return false, falls Spieler weg, sonst true
	 */
	abstract public boolean hoeren(int reizwert);

	/**
	 * Diese Methode simuliert das sagen eines Spielers. M&ouml;chte der Spieler
	 * nicht h&ouml;her gehen, muss er 0 zur&uuml;ckgeben.
	 * 
	 * @param alterWert
	 *            - Reizwert, der vorher gesagt wurde
	 */
	abstract public boolean sagen(int alterWert);

	/**
	 * Sortiert das Blatt des Spielers in aufsteigender Reihenfolge, nach der im
	 * Parameter &uuml;bergebenen Spielart.
	 * 
	 * @param spielart
	 *            - spielart nach der sortiert werden soll
	 */
	public abstract void blattSortieren(final IGameVariety spielart);

	/**
	 * Fragt den Spieler, ob er mit Reizagent spielen m&ouml;chte.
	 * 
	 * @return true, falls der Spieler mit Reizagent spielt
	 */
	public abstract boolean agent();

	/**
	 * Vergleicht den Spieler mit dem &uuml;bergebenen Spieler. Das Ergebnis ist
	 * true, falls die beiden Spieler den gleichen Namen haben.
	 * 
	 * @param spieler
	 *            - spieler, mit dem der Spieler an dem die Methode aufgerufen
	 *            wird verglichen werden soll
	 * @return true, falls die Spieler den gleichen Namen haben
	 */
	public abstract boolean equals(IPlayer spieler);

	/**
	 * Benutzt der Spieler den Reizagenten, liefert diese Methode den maximalen
	 * Reizwert, den der Spieler spielen w&uuml;rde.
	 * 
	 * @return maximaler Reizwert
	 */
	public abstract int reizlimitFestlegen();

	/**
	 * Diese Methode speichert die Tr&uuml;mpfe des &uuml;bergebenen Spielers im
	 * Spitzen-Array. Die Spitzen werden ben&ouml;tigt, um die Spielstufe zu
	 * berechnen. Dies kann f&uuml;r eine Hilfe verwendet werden und f&uuml;r
	 * einen Check, ob der Spieler sich &uuml;berreizt hat.
	 * 
	 * Achtung: Die truempfe werden direkt in der Tischvariablen gesetzt, aber
	 * zus&auml;tzlich auch noch zur&uuml;ck gegeben.
	 * 
	 * 
	 */
	public abstract PlayingCard[] spitzenEinordnen();

	/**
	 * Legt jeden Buben, die der Alleinspieler auf der Hand h&auml;lt in ein
	 * Array. Wird zur Berechnung der Spitzen ben&ouml;tigt.
	 * 
	 */
	public abstract void bubeneinordnen();
	
	/**
	 * Liefert f&uuml;r die Methode Bubeneinordnen den Index, an welcher
	 * Position der Bube im Array gespeichert werden soll.
	 * 
	 * @param bubenwert
	 *            - bubenwert
	 * @return Position
	 */
	public abstract int bubeneinordnenhilf(int bubenwert);
	
	/**
	 * Ordnet jeder Trumpfkarte einen Platz im Array zu.
	 * 
	 * @param i
	 *            Kartennummer
	 * @return Position
	 */
	public abstract int farbeeinordnenhilf(int i);

	/**
	 * Ordnet alle Tr&uuml;mpfe eines Spielers in ein Array, um sp&auml;ter die
	 * Spitzen berechnen zu k&ouml;nnen.
	 * 
	 */
	public abstract void farbeneinordnen();

	/**
	 * Rechnet die Spitzen aus. Ist der Kreuz-Bube vorhanden, wird die Methode
	 * spitzenMit() aufgerufen und die Anzahl der Spitzen in ununterbrochender
	 * Reihenfolge wird berechnet, ansonsten wird die Methode spitzenOhne()
	 * aufgerufen und die fehlenden Spitzen werden berechnet.
	 * 
	 * @return Positiver Wert von 1-11 fuer vorhandene Spitzen, Negativer Wert
	 *         von -1 bis -11 f&uuml;r fehlende Spitzen.
	 */
	public abstract int spitzenZahl();

	/**
	 * Rechnet die Spitzen in ununterbrochener Reihenfolge aus.
	 * 
	 * @param erg
	 *            Bisheriger Spitzenwert, sollte 1 sein.
	 * @return Die Anzahl der Spitzen.
	 */
	public abstract int spitzenMit(int erg);

	/**
	 * Rechnet die fehlenden Spitzen in ununterbrochener Reihenfolge aus. Wird
	 * aufgerufen wenn der Kreuz-Bube fehlt.
	 * 
	 * @param erg
	 *            Bisherige Spitzen, sollte 0 sein.
	 * @return Gibt die Anzahl der fehlenden Spitzen zur&uuml;ck.
	 */
	public abstract int spitzenOhne(int erg);
	
	

}