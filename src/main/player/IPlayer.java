package main.player;

import java.io.IOException;
import java.util.ArrayList;

import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;

/**
 * Das Interface ISpieler beinhaltet die Methodenrümpfe für den Spieler
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public interface IPlayer {

	/**
	 * Gibt den Namen des Spielers zurück.
	 * 
	 * @return Name des Spielers
	 */
	public abstract String getName();

	/**
	 * Gibt die Position des Spielers zurück.
	 * 
	 * @return Position des Spielers
	 */
	public abstract Position getPosition();

	/**
	 * Gibt das Blatt des Spielers zurück.
	 * 
	 * @return Blatt des Spielers
	 */
	public abstract ArrayList<PlayingCard> getHand();

	/**
	 * Gibt die vom Spieler gewonnenen Stiche zurück.
	 * 
	 * @return vom Spieler gewonnen Stiche
	 */
	public abstract ArrayList<PlayingCard> getTricks();

	/**
	 * Gibt an, ob der Spieler Alleinspieler ist.
	 * 
	 * @return true, wenn Spieler Alleinspieler ist
	 */
	public abstract boolean isDeclarer();

	/**
	 * Liefert alle bisher vom Spieler gespielten Spiele in einer ArrayList
	 * zurück.
	 * 
	 * @return alle vom Spieler bisher gespielten Spiele
	 */
	public abstract ArrayList<Integer> getGames();

	/**
	 * Gibt den Mitspieler eines Spielers zurück, sofern dieser nicht der
	 * Alleinspieler ist
	 * 
	 * @return Mitspieler des Spielers
	 */
	public abstract IPlayer getTeammate();

	/**
	 * Gibt die bisher gespielten Karten
	 * 
	 * @return alle bisher gefallenen Karten
	 */
	public abstract ArrayList<PlayingCard> getAllPlayedCards();

	/**
	 * Liefert das Restblatt zur&uumlck.
	 * 
	 * @return das restblatt
	 */
	public abstract ArrayList<PlayingCard> getRestHand();

	/**
	 * Liefert die im Spieler gesetzte Spielart zurück.
	 * 
	 * @return die gesetzte Spielart
	 */
	public abstract IGameVariety getGameVariety();
	
	/**
	 * gibt die Handspiele zurück
	 * @return the handgame
	 */
	public int getHandGames();
	
	/**
	 * Setzt die Handspiele
	 * @param handspiele
	 */
	public void setHandGames(int handspiele);

	/**
	 * Setzt die Spielart im Spieler.
	 * 
	 * @param spielart
	 *            - die Spielart, die momentan gespielt wird
	 */
	public abstract void setGameVariety(IGameVariety spielart);

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
	public abstract void setHand(ArrayList<PlayingCard> blatt);

	/**
	 * Setzt das Alleinspielerflag.
	 * 
	 * @param isDeclarer
	 *            - true, wenn Spieler Alleinspieler ist
	 */
	public abstract void setIsDeclarer(boolean isDeclarer);

	/**
	 * Setzt das Mitspielerflag.
	 * 
	 * @param teammate
	 *            - Der Spieler, der Mitspieler ist.
	 */
	public abstract void setTeammate(IPlayer teammate);

	/**
	 * Setzt die Liste mit den vom Spieler gespielten Spielen
	 * 
	 * @param games
	 */
	public abstract void setGames(ArrayList<Integer> games);

	/**
	 * Fügt der Stichliste einen gewonnenen Stich hinzu.
	 * 
	 * @param tricks
	 *            - der vom Spieler gewonnene Stich
	 */
	public abstract void setTricks(ArrayList<PlayingCard> tricks);

	/**
	 * Setzt den Namen des Spielers. Wird benötigt, damit der Name
	 * geändert werden kann, falls er doppelt auftaucht.
	 * 
	 * @param name
	 */
	public abstract void setName(String name);

	/**
	 * 
	 */
	public abstract void setAllPlayedCards(ArrayList<PlayingCard> cards);

	/**
	 * Setzt die Truempfe Variable neu.
	 * 
	 * @param trumps - die neuen Truempfe
	 */
	public abstract void setTrumps(PlayingCard[] trumps);

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
	 * Fügt einen gewonnenen Stich zu den bisher gewonnenen Stichen hinzu.
	 * 
	 * @param trick
	 *            - die drei am Tisch gewonnenen Karten
	 */
	public abstract void addTrick(PlayingCard[] trick);

	/**
	 * Fügt einen gewonnenen Stich zu den bisher gespielten Stichen hinzu.
	 * @param gespielteKarten
	 */
	/**
	 * Fügt die gespielten Karten, in die alleGespieltenKarten
	 * Variable hinzu.
	 * 
	 * @param playedCards - die in einer Runde gespielten Karten
	 */
	public abstract void addPlayedCards(PlayingCard[] playedCards);

	/**
	 * Ermittelt die Karten, die der Spieler spielen darf.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die auf dem Tisch liegen.
	 * @return Die Karten, die gespielt werden dürfen.
	 */
	public abstract ArrayList<PlayingCard> playableCards(
			PlayingCard[] gespielteKarten);

	/**
	 * Gibt die Karte zurück, die der Spieler auf den Tisch legen
	 * möchte.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 * @throws IOException
	 */
	abstract public PlayingCard playCard(PlayingCard[] playedCards)
			throws IOException;

	/**
	 * Die Methode wählt zufällig eine Karte aus, die dann gespielt wird.
	 * 
	 * @param gespielteKarten - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return - Spielkarte, die zufällig ausgewählt wurde.
	 */
	public abstract PlayingCard playRamdonAllowedCard(
			PlayingCard[] gespielteKarten);

	/**
	 * Gibt die gedrückten Spielkarten zurück.
	 * 
	 * @return gedrückte Spielkarten
	 */
	abstract public PlayingCard[] druecken(PlayingCard[] skat);

	/**
	 * SpielAnsagen ermittelt, ob der Spieler ein Farb-, Grand-, oder Nullspiel
	 * wünscht.
	 * 
	 * @return die gewünschte Spielart
	 */
	abstract public IGameVariety declareGame();

	/**
	 * Handspiel ermittelt, ob der Spieler ein Handspiel ansagt.
	 * 
	 * @return true, falls der Spieler ein Handspiel wünscht
	 */
	abstract public boolean handgame();

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
	 * Liefert ein Farbspiel mit korrekt gesetzten Trumpffarbe zurück,
	 * nachdem der Spieler seine Wahl getroffen hat.
	 * 
	 * @return Farbspiel mit gesetzter Trumpffarbe
	 */
	abstract public SuitGame suit();

	/**
	 * Fügt einen neuen Eintrag zu Liste der Spiele dazu, wenn der Spieler
	 * ein Spiel als Alleinspieler gespielt hat.
	 * 
	 * @param punkte
	 *            - points, die der Spieler erreicht hat
	 * @return die neue Liste
	 */
	public abstract ArrayList<Integer> addPoints(int punkte);

	/**
	 * Diese Methode simuliert das hören eines Spielers.
	 * 
	 * @param reizwert
	 *            - reizwert der gesagt wurde
	 * @return false, falls Spieler pass, sonst true
	 */
	abstract public boolean respond(int reizwert);

	/**
	 * Diese Methode simuliert das bid eines Spielers. Möchte der Spieler
	 * nicht höher gehen, muss er 0 zurückgeben.
	 * 
	 * @param alterWert
	 *            - Reizwert, der vorher gesagt wurde
	 */
	abstract public boolean bid(int alterWert);

	/**
	 * Sortiert das Blatt des Spielers in aufsteigender Reihenfolge, nach der im
	 * Parameter übergebenen Spielart.
	 * 
	 * @param spielart
	 *            - spielart nach der sortiert werden soll
	 */
	public abstract void sortHand(final IGameVariety spielart);

	/**
	 * Fragt den Spieler, ob er mit Reizagent spielen möchte.
	 * 
	 * @return true, falls der Spieler mit Reizagent spielt
	 */
	public abstract boolean agent();

	/**
	 * Vergleicht den Spieler mit dem übergebenen Spieler. Das Ergebnis ist
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
	 * Reizwert, den der Spieler spielen würde.
	 * 
	 * @return maximaler Reizwert
	 */
	public abstract int setBidLimit();

	/**
	 * Diese Methode speichert die Trümpfe des übergebenen Spielers im
	 * Spitzen-Array. Die Spitzen werden benötigt, um die Spielstufe zu
	 * berechnen. Dies kann für eine Hilfe verwendet werden und für
	 * einen Check, ob der Spieler sich überreizt hat.
	 * 
	 * Achtung: Die truempfe werden direkt in der Tischvariablen gesetzt, aber
	 * zusätzlich auch noch zurück gegeben.
	 * 
	 * 
	 */
	public abstract PlayingCard[] arrangeMatadorsJackStraitOrder();

	/**
	 * Legt jeden Buben, die der Alleinspieler auf der Hand hält in ein
	 * Array. Wird zur Berechnung der Spitzen benötigt.
	 * 
	 */
	public abstract void rankUnderKnaves();
	
	/**
	 * Liefert für die Methode Bubeneinordnen den Index, an welcher
	 * Position der Bube im Array gespeichert werden soll.
	 * 
	 * @param underKnaveValue
	 *            - bubenwert
	 * @return Position
	 */
	public abstract int rankUnderKnavesHelp(int underKnaveValue);
	
	/**
	 * Ordnet jeder Trumpfkarte einen Platz im Array zu.
	 * 
	 * @param i
	 *            Kartennummer
	 * @return Position
	 */
	public abstract int rankSuitHelp(int i);

	/**
	 * Ordnet alle Trümpfe eines Spielers in ein Array, um später die
	 * Spitzen berechnen zu können.
	 * 
	 */
	public abstract void rankSuit();

	/**
	 * Rechnet die Spitzen aus. Ist der Kreuz-Bube vorhanden, wird die Methode
	 * matadorsJackStraitWith() aufgerufen und die Anzahl der Spitzen in ununterbrochender
	 * Reihenfolge wird berechnet, ansonsten wird die Methode matadorsJackStraitWithout()
	 * aufgerufen und die fehlenden Spitzen werden berechnet.
	 * 
	 * @return Positiver Wert von 1-11 fuer vorhandene Spitzen, Negativer Wert
	 *         von -1 bis -11 für fehlende Spitzen.
	 */
	public abstract int matadorsJackStraitCount();

	/**
	 * Rechnet die Spitzen in ununterbrochener Reihenfolge aus.
	 * 
	 * @param erg
	 *            Bisheriger Spitzenwert, sollte 1 sein.
	 * @return Die Anzahl der Spitzen.
	 */
	public abstract int matadorsJackStraitWith(int erg);

	/**
	 * Rechnet die fehlenden Spitzen in ununterbrochener Reihenfolge aus. Wird
	 * aufgerufen wenn der Kreuz-Bube fehlt.
	 * 
	 * @param erg
	 *            Bisherige Spitzen, sollte 0 sein.
	 * @return Gibt die Anzahl der fehlenden Spitzen zurück.
	 */
	public abstract int matadorsJackStraitWithout(int erg);
	
	

}