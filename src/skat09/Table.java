package skat09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.SuitGame;
import skat09.player.Position;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


/**
 * Die Klasse Tisch bildet den momentanen Spielzustand ab und wird durch den
 * Controller gesteuert.
 * 
 * MVC: Der Tisch ist das Model, er erweitert die Klasse Observable. Eventuell
 * m&uuml;ssen noch Methoden &uuml;berschrieben werden.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */

public class Table extends Observable {
	
	/**
	 * Der erste Spieler am Tisch
	 */
	private IPlayer player1;
	/**
	 * Der zweite Spieler am Tisch
	 */
	private IPlayer player2;
	/**
	 * Der dritte Spieler am Tisch
	 */
	private IPlayer player3;
	/**
	 * Die aktuell gespielte Spielart
	 */
	private IGameVariety gameVariety;
	/**
	 * Das gesamte Kartenspiel
	 */
	private ArrayList<PlayingCard> deck;
	/**
	 * Karten die auf dem Tisch liegen
	 */
	private PlayingCard[] playedCards;
	/**
	 * Die Karten, die im Skat liegen
	 */
	private PlayingCard[] skat;
	/**
	 * True, wenn Handspiel gespielt wird
	 */
	private boolean handGame;
	/**
	 * True, wenn Ouvert gespielt wird
	 */
	private boolean ouvert;
	/**
	 * True, wenn Schneider angesagt wurde
	 */
	private boolean schneider;
	/**
	 * True, wenn Schwarz angesagt wurde
	 */
	private boolean schwarz;
	/**
	 * Der aktuell gebotene Reizwert
	 */
	private int biddingValue;
	/**
	 * Falls der Reizagent genutzt wird, wird hier der max. Reizwert bis zu dem
	 * der Spieler mitgeht gespeichert.
	 */
	private int biddingAgentValue;
	/**
	 * Skatvariante: Raeuberskat oder normales Skat oder Skat mit Ramsch und
	 * Bock?
	 */
	private SkatVariant skatVariant;
	/**
	 * Alle Reizwerte die vorkommen koennen
	 */
	private SortedSet<Integer> biddingValues;
	/**
	 * z&auml;hlt die bisher gespielten Spiele mit
	 */
	private int gameRoundCounter;
	/**
	 * Die Tr&uuml;mpfe, mit denen der Alleinspieler spielt
	 */
	private PlayingCard[] trumps = new PlayingCard[12];
	/**
	 * True, wenn die Situation Spaltarsch aufgetreten ist
	 */
	private boolean spaltarsch;
	/**
	 * True, wenn ein Bockspiel gespielt wird
	 */
	private boolean bock;
	/**
	 * Die noch zu spielenden Ramschspiele
	 */
	private int ramschRounds;
	/**
	 * Die noch zu spielenden Bockspiele
	 */
	private int bockRounds;
	/**
	 * True, wenn Sechserskat gespielt wird
	 */
	private boolean sixSkat;

	// Auswertung, benoetigte Werte
	/**
	 * Gewinngrenze ist bei 32 Karten 61, beim Sechserskat 73
	 */
	private int winLimit; // 32 Karten : 61, 6erSkat: 73
	/**
	 * Spaltarschwert ist bei 32 Karten 60, beim Sechserskat 72
	 */
	private int spaltarschwert; // 32 Karten: 60, 6erSkat 72
	/**
	 * Schneidergrenze ist bei 32 Karten 30, beim Sechserskat 36
	 */
	private int schneiderLimit; // 32 Karten: 30 , 6er Skat 36
	/**
	 * Schneidergrenze ist bei 32 Karten 90, beim Sechserskat 108
	 */
	private int gegnerschneider; // 32 Karten: 90, 6erSkat 108
	/**
	 * Maximale Augenzahl ist bei 32 Karten 120, beim Sechserskat 144
	 */
	private int maximaleaugen; // 32 Karten: 120, 6erSkat 144
	/**
	 * Anzahl der Karten, die ein Spieler am Ende haben muss, wenn er alle
	 * Stiche gewonnen hat. Bei 32 Karten 30, beim Sechserskat 33
	 */
	private int allTricks; // 32 Karten: 30 Karten, 6erSkat: 33 Karten
	/**
	 * Gibt an, ob das letzte Spiel ueberreizt war.
	 */
	private boolean ueberreizt;
	/**
	 * Enth&auml;lt so viele Eintr&auml;ge, wie Spiele gespielt wurden. F&uuml;r
	 * jedes Spiel ist notiert, ob das Spiel &uuml;berreizt war oder nicht.
	 */
	public ArrayList<Boolean> ueberreizliste;
	/**
	 * Enth&auml;lt so viele Eintr&auml;ge, wie Spiele gespielt wurden. F&uuml;r
	 * jedes Spiel ist notiert, wie viele Augen gewonnen wurden.
	 */
	public ArrayList<Integer> augenliste;
	/**
	 * Enth&auml;lt so viele Eintr&auml;ge, wie Spiele gespielt wurden. F&uuml;r
	 * jedes Spiel ist notiert, wie viele Punkte gewonnen wurden.
	 */
	public ArrayList<Integer> punkteliste;
	/**
	 * Enth&auml;lt so viele Eintr&auml;ge, wie Spiele gespielt wurden. F&uuml;r
	 * jedes Spiel ist notiert, wie hoch der Grundwert des Spiels war.
	 */
	public ArrayList<Integer> grundwertliste;

	//
	// Konstruktor
	//

	/**
	 * Instanziert einen Tisch
	 */
	public Table() {

		super();

		gameRoundCounter = 0;
		playedCards = new PlayingCard[3];
		skat = new PlayingCard[3];
		deck = new ArrayList<PlayingCard>();
		grundwertliste = new ArrayList<Integer>();
		augenliste = new ArrayList<Integer>();
		punkteliste = new ArrayList<Integer>();
		ueberreizliste = new ArrayList<Boolean>();
		biddingValues = new TreeSet<Integer>();
		biddingValue = 18;
		biddingAgentValue = 0;
		handGame = false;
		schneider = false;
		schwarz = false;
		ouvert = false;
		sixSkat = false;
		winLimit = 61;
		spaltarschwert = 60;
		schneiderLimit = 30;
		gegnerschneider = 90;
		maximaleaugen = 120;
		allTricks = 30;

		reizwerteErzeugen();

	}

	//
	// get-Methoden
	//

	/**
	 * Liefert den 1. Spieler zur&uuml;ck.
	 * 
	 * @return player1
	 */
	public IPlayer getPlayer1() {

		return player1;
	}

	/**
	 * Liefert den 2. Spieler zur&uuml;ck.
	 * 
	 * @return player2
	 */
	public IPlayer getPlayer2() {

		return player2;
	}

	/**
	 * Liefert den 3. Spieler zur&uuml;ck.
	 * 
	 * @return player3
	 */
	public IPlayer getPlayer3() {

		return player3;
	}
	
	/**
	 * Get all players from the table.
	 * 
	 * @return all player from table.
	 * 
	 * @since 11.06.2015 21:59:32
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public IPlayer[] getAllPlayer() {

		IPlayer[] playerAll = {player1, player2, player3};
		
		return playerAll;
	}

	/**
	 * Liefert die bereits auf dem Tisch liegenden Karten zur&uuml;ck.
	 * 
	 * @return auf dem Tisch liegende Karten
	 */
	public PlayingCard[] getPlayedCards() {

		return playedCards;
	}

	/**
	 * Gib das Deck des Tisches zur&uuml;ck.
	 * 
	 * @return deck des Tisches
	 */
	public ArrayList<PlayingCard> getDeck() {

		return deck;
	}

	/**
	 * Liefert alle existierenden Reizwerte in einem SortedSet zur&uuml;ck.
	 * 
	 * @return SortedSet (sortierte Reizwerte, die nicht doppelt vorkommen
	 *         k&ouml;nnen)
	 */
	public SortedSet<Integer> getBiddingValues() {

		return biddingValues;
	}

	/**
	 * Gibt den Skat des Tisches aus.
	 * 
	 * @return Skat vom Tisch
	 */
	public PlayingCard[] getSkat() {

		return skat;
	}

	/**
	 * Liefert die Variable handgame zur&uuml;ck.
	 * 
	 * @return true, falls handgame
	 */
	public boolean getHandGame() {

		return handGame;
	}

	/**
	 * Liefert die aktuell gesetzte Spielart zur&uuml;ck.
	 * 
	 * @return gameVariety der aktuellen Runde
	 */
	public IGameVariety getGameVariety() {

		return gameVariety;
	}

	/**
	 * Liefert die Spielvariante.
	 * 
	 * @return gew&auml;tle skatVariant
	 */
	public SkatVariant getVariant() {

		return skatVariant;
	}

	/**
	 * Liefert den aktuell gesetzten Reizwert zur&uuml;ck.
	 * 
	 * @return aktueller biddingValue
	 */
	public int getBiddingValue() {

		return biddingValue;
	}

	/**
	 * Liefert den maximalen Wert, den ein Spieler, der den Reizagenten benutzt,
	 * zu reizen bereit ist.
	 * 
	 * @return maximalWert
	 */
	public int getBiddingAgentValue() {

		return biddingAgentValue;
	}

	/**
	 * Liefert zur&uuml;ck, ob das letzte Spiel 60-60 war.
	 * 
	 * @return true, falls letztes Spiel 60-60 war.
	 */
	public boolean getSpaltarsch() {
		return spaltarsch;
	}

	/**
	 * @return Die Anzahl der verbleibenden Bockspiele
	 */
	public int getBockRounds() {
		return bockRounds;
	}

	/**
	 * @return Die Anzahl der verbleibenden Ramschspiele
	 */
	public int getRamschRounds() {
		return ramschRounds;
	}

	/**
	 * @return bock Ob das Spiel ein Bockspiel ist oder nicht.
	 */
	public boolean getBock() {
		return bock;
	}

	/**
	 * Die Methode gibt die Anzahl der Spiele zur&uuml;ck
	 * 
	 * @return gameRoundCounter Die Anzahl der Spiele
	 */
	public int getGameRoundCounter() {

		return gameRoundCounter;
	}

	/**
	 * Liefert die trumps Variable zur&uuml;ck.
	 * 
	 * @return trumps
	 */
	public PlayingCard[] getTrumps() {

		return trumps;
	}

	/**
	 * Die Methode gibt an, ob Sechserskat gespielt wird oder nicht
	 * 
	 * @return sixSkat Ob Sechserskat gespielt wird
	 */
	public boolean getSixSkat() {
		return sixSkat;
	}

	/**
	 * Ouvert ist true, falls der Alleinspieler Ouvert spielt.
	 * 
	 * @return ouvert
	 */
	public boolean getOuvert() {
		return ouvert;
	}

	/**
	 * Schwarz ist true, falls der Alleinspieler Schwarz angesagt hat.
	 * 
	 * @return schwarz
	 */
	public boolean getSchwarz() {
		return schwarz;
	}

	/**
	 * Schneider ist true, falls der Alleinspieler Schneider angesagt hat.
	 * 
	 * @return schneider
	 */
	public boolean getSchneider() {
		return schneider;
	}
	
	/**
	 * Gibt die ArrayList ueberreizliste zur&uuml;ck
	 * 
	 * @return ueberreizliste
	 */
	public ArrayList<Boolean> getUeber() {
		return ueberreizliste;
	}
	
	/**
	 * Gibt die ArrayList augenliste zur&uuml;ck
	 * 
	 * @return augenliste
	 */
	public ArrayList<Integer> getAugenListe() {
		return augenliste;
	}
	
	/**
	 * Gibt die ArrayList punkteliste zur&uuml;ck
	 * 
	 * @return punkteliste
	 */
	public ArrayList<Integer> getPunkteListe() {
		return punkteliste;
	}
	
	/**
	 * Gibt die ArrayList grundwertliste zur&uuml;ck
	 * 
	 * @return grundwertliste
	 */
	public ArrayList<Integer> getGrundwertListe() {
		return grundwertliste;
	}
	
	

	//
	// set-Methoden
	//

	/**
	 * @param ueberreizt
	 *            the ueberreizt to set
	 */
	public void setUeberreizt(boolean ueberreizt) {
		this.ueberreizt = ueberreizt;
	}

	/**
	 * @return the ueberreizt
	 */
	public boolean istUeberreizt() {
		return ueberreizt;
	}

	/**
	 * @param sixSkat
	 *            the sixSkat to set
	 */
	public void setSixSkat(boolean sixSkat) {
		this.sixSkat = sixSkat;
		winLimit = 73;
		spaltarschwert = 72;
		schneiderLimit = 36;
		gegnerschneider = 108;
		maximaleaugen = 144;
		allTricks = 33;
	}

	/**
	 * Setzt den ersten Spieler neu.
	 * 
	 * @param player1
	 *            - der neue Spieler
	 */
	public void setPlayer1(IPlayer player1) {

		this.player1 = player1;
		// Spielnachricht nachricht = new Spielnachricht(
		// Spielnachricht.Spieler.SPIELER1, Spielnachricht.Flags.NICHTS,
		// Spielnachricht.Bewegung.NICHTS);
		// setChanged();
		// notifyObservers(nachricht);
	}

	/**
	 * Setzt den zweiten Spieler neu.
	 * 
	 * @param player2
	 *            - der neue Spieler
	 */
	public void setPlayer2(IPlayer player2) {

		this.player2 = player2;
	}

	/**
	 * Setzt den dritten Spieler neu.
	 * 
	 * @param player3
	 *            - der neue Spieler
	 */
	public void setPlayer3(IPlayer player3) {

		this.player3 = player3;
	}

	/**
	 * Setzt das schneider-flag mit dem &uuml;bergebenen Wert.
	 * 
	 * @param value
	 *            - true falls gesetzt
	 */
	public void setSchneider(boolean value) {

		this.schneider = value;
	}

	/**
	 * Setzt das schwarz-flag mit dem &uuml;bergebenen Wert.
	 * 
	 * @param value
	 *            - true falls gesetzt
	 */
	public void setSchwarz(boolean value) {

		this.schwarz = value;
	}

	/**
	 * Setzt das ouvert-flag mit dem &uuml;bergebenen Wert.
	 * 
	 * @param value
	 *            - true falls gesetzt
	 */
	public void setOuvert(boolean value) {

		this.ouvert = value;
	}

	/**
	 * Setzt das gespielteKartenArray.
	 * 
	 * @param playedCards
	 *            - ein Array, dass die drei gespielten Karten.
	 */
	public void setPlayedCards(PlayingCard[] playedCards) {

		this.playedCards = playedCards;
		super.setChanged();
		super.notifyObservers(playedCards);
	}

	/**
	 * Legt zwei Karten in den Skat.
	 * 
	 * @param skatCards
	 *            - die in den Skat gelegt werden sollen.
	 */
	public void setSkat(PlayingCard[] skatCards) {

		skat = skatCards;
	}

	/**
	 * Legt die Spielart der aktuellen Runde fest.
	 * 
	 * @param gameVariety
	 *            f&uuml;r die akutelle Runde
	 */
	public void setGameVariety(IGameVariety gameVariety) {

		this.gameVariety = gameVariety;
	}

	public void setVariant(SkatVariant variant) {

		this.skatVariant = variant;
	}

	/**
	 * Setzt den Reizwert des Tisches.
	 * 
	 * @param value
	 *            - biddingValue
	 */
	public void setBiddingValue(int value) {

		this.biddingValue = value;
	}

	/**
	 * Setzt den Reizagentwert des Tisches.
	 * 
	 * @param value
	 *            - maximal gereizter Wert
	 */
	public void setBiddingAgentValue(int value) {

		biddingAgentValue = value;
	}

	/**
	 * Setzt den booleanWert für Handspiel.
	 */
	public void setHandGame(boolean value) {

		this.handGame = value;
	}

	/**
	 * Setzt die Variable spaltarsch. Soll nur vom Controller auf false gesetzt
	 * werden, nachdem sie gelesen wurde.
	 * 
	 * @param b
	 */
	public void setSpaltarsch(boolean b) {
		spaltarsch = b;
	}

	/**
	 * @param ramschRounds
	 *            Die Anzahl der verbleibenden Bockrunden.
	 */
	public void setRamschRounds(int ramschRounds) {
		this.ramschRounds = ramschRounds;
	}

	/**
	 * @param bockRounds
	 *            Die Anzahl der verbleibenden Bockrunden.
	 */
	public void setBockRounds(int bockRounds) {
		this.bockRounds = bockRounds;
	}

	/**
	 * @param bock
	 *            , ist Spiel Bockspiel oder nicht.
	 */
	public void setBock(boolean bock) {
		this.bock = bock;
	}

	/**
	 * setzt die trumps Variable neu.
	 * 
	 * @param trumps
	 *            - die neuen trumps
	 */
	public void setTruempfe(PlayingCard[] trumps) {

		this.trumps = trumps;
	}

	//
	// weitere Methoden
	//
	/**
	 * Erstellt ein neues Deck mit 32 Karten. Jede Karte darf nur einmal
	 * vorkommen.
	 */
	public void createDeck() {

		PlayingCard card;


		deck.clear();

		for (Suit suit : Suit.values()) {

			for (Value wert : Value.values()) {

				card = new PlayingCard(suit, wert);
				deck.add(card);

				// Falls kein 6er Skat gespielt wird, alle 6er Karten entfernen
				if ((card.getValue() == Value.SIX) && (sixSkat == false)) {
					deck.remove(card);
				}
			}
		}

	}

	/**
	 * Mischt das &uuml;bergebene Deck und liefert es zur&uuml;ck.
	 */
	public void shuffleDeck() {

		Collections.shuffle(deck);
	}

	/**
	 * Wertet einen gespielten Stich aus und gibt den Gewinner zur&uuml;ck.
	 * 
	 * @param playedCards
	 *            - im Stich enthaltene Karten@return das neue Deck bestehend
	 *            aus 32 Karten in einer ArrayList
	 * @param gameVariety
	 *            - &uuml;bergibt die Spielart
	 * @return Spieler, der den Stich gewonnen hat
	 */
	public IPlayer evaluateTrick(IGameVariety gameVariety,
								 PlayingCard[] playedCards) throws NullPointerException {

		IPlayer trickWinner = null;
		PlayingCard highestCard = null;

		highestCard = gameVariety.higherCard(playedCards[0],
				playedCards[1]);

		highestCard = gameVariety
				.higherCard(highestCard, playedCards[2]);

		trickWinner = highestCard.getOwner();

		return trickWinner;
	}

	/**
	 * Eine bestimmte Anzahl an Karten in ein Blatt reintun.
	 * 
	 * @param blatt
	 *            - in das die Karten rein sollen
	 * @param kartenAnzahl
	 *            - Anzahl der Karten, die in das Blatt rein sollen
	 */
	private void kartenInsBlatt(ArrayList<PlayingCard> blatt, int kartenAnzahl) {

		for (int i = 0; i < kartenAnzahl; i++) {

			blatt.add(deck.remove(0));
		}
	}

	/**
	 * Entnimmt die Spielkarten aus dem vom Tisch erzeugten Deck und verteilt
	 * sie nach den Vorgaben der internationalen Skatordnung an die Spieler und
	 * in den Skat.
	 */
	public void dealOutCards() {
		gameRoundCounter = gameRoundCounter + 1;
		ArrayList<PlayingCard> blatt1 = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> blatt2 = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> blatt3 = new ArrayList<PlayingCard>();

		// Das array speichert, wieviele Karten ein Spieler beim entsprechenden
		// Schleifendurchlauf bekommt
		int[] kartenAnzahl = new int[3];
		kartenAnzahl[0] = 3;
		kartenAnzahl[1] = 4;
		kartenAnzahl[2] = 3;
		if (sixSkat) {
			kartenAnzahl[2] = 4;
		}

		for (int i = 0; i < 3; i++) {

			kartenInsBlatt(blatt1, kartenAnzahl[i]);
			kartenInsBlatt(blatt2, kartenAnzahl[i]);
			kartenInsBlatt(blatt3, kartenAnzahl[i]);
			// 2 Karten in den Skat legen!
			if (i == 0) {

				skat[0] = deck.remove(0);
				skat[1] = deck.remove(0);
				if (sixSkat) {
					skat[2] = deck.remove(0);
				}
			}
		}
		// Den Spielern die Blaetter ueberreichen.
		player1.setHand(blatt1);
		player2.setHand(blatt2);
		player3.setHand(blatt3);
	}

	/**
	 * Addiert ein neues Spiel zu gameRoundCounter.
	 */
	public void addAnzahlSpiele() {
		gameRoundCounter = gameRoundCounter + 1;
	}

	/**
	 * Weist jedem Blatt der Spieler den jeweiligen Spieler als Besitzer zu.
	 */
	public void kartenBesitzergeben() {

		ArrayList<PlayingCard> blatt1 = player1.getHand();
		for (PlayingCard karte : blatt1) {
			karte.setOwner(player1);
		}
		ArrayList<PlayingCard> blatt2 = player2.getHand();
		for (PlayingCard karte : blatt2) {
			karte.setOwner(player2);
		}
		ArrayList<PlayingCard> blatt3 = player3.getHand();
		for (PlayingCard karte : blatt3) {
			karte.setOwner(player3);
		}
	}

	/**
	 * Diese Methode liefert den Alleinspieler zur&uuml;ck.
	 * 
	 * @return der Alleinspieler
	 */
	public IPlayer getDeclarer() {

		IPlayer alleinSpieler = null;

		if (player1.isDeclarer()) {

			alleinSpieler = player1;
		}

		else if (player2.isDeclarer()) {

			alleinSpieler = player2;
		}

		else if (player3.isDeclarer()) {

			alleinSpieler = player3;
		}
		return alleinSpieler;
	}

	/**
	 * Diese Methode ermittelt den Mitspieler zur&uuml;ck.
	 * 
	 * @return der Mitspieler
	 */
	public IPlayer ermittleMitspieler(IPlayer spieler) {

		IPlayer mitspieler = null;

		String spielername = spieler.getName();
		String alleinspielername = getDeclarer().getName();

		if (!player1.getName().equals(spielername)
				&& !player1.getName().equals(alleinspielername)) {

			mitspieler = player1;
		} else if (!player2.getName().equals(spielername)
				&& !player2.getName().equals(alleinspielername)) {

			mitspieler = player2;
		} else {

			mitspieler = player3;
		}
		return mitspieler;
	}

	/**
	 * Setzt die Mitspieler in den Spielern, die nicht Alleinspieler sind.
	 */
	public void mitspielerSetzen() {

		if (gameVariety.getGameVariety() != GameVarietyName.RAMSCH) {

			if (!player1.isDeclarer()) {

				player1.setTeammate(ermittleMitspieler(player1));
			}

			else {

				player1.setTeammate(null);
			}

			if (!player2.isDeclarer()) {

				player2.setTeammate(ermittleMitspieler(player2));
			}

			else {

				player2.setTeammate(null);
			}

			if (!player3.isDeclarer()) {

				player3.setTeammate(ermittleMitspieler(player3));
			}

			else {

				player3.setTeammate(null);
			}
		} else {

			player1.setTeammate(null);
			player2.setTeammate(null);
			player3.setTeammate(null);
		}
	}

	/**
	 * PositionInitialisieren weist jedem Spieler eine Anfangsposition am Tisch
	 * zu.
	 */
	public void initializePositions() {

		player1.setPosition(Position.VORHAND);
		player2.setPosition(Position.MITTELHAND);
		player3.setPosition(Position.HINTERHAND);
	}

	/**
	 * Diese Methode sorgt daf&uuml;r, dass nach einem Spiel die Positionen der
	 * Spieler gewechselt werden.
	 */
	public void changePosition() {

		Position position = player1.getPosition();

		switch (position) {
		case VORHAND:

			player1.setPosition(Position.HINTERHAND);
			player2.setPosition(Position.VORHAND);
			player3.setPosition(Position.MITTELHAND);
			break;

		case MITTELHAND:

			player1.setPosition(Position.VORHAND);
			player2.setPosition(Position.MITTELHAND);
			player3.setPosition(Position.HINTERHAND);
			break;

		case HINTERHAND:

			player1.setPosition(Position.MITTELHAND);
			player2.setPosition(Position.HINTERHAND);
			player3.setPosition(Position.VORHAND);
			break;
		}
	}

	/**
	 * Bef&uuml;llt das Reizwerte-Array mit allen Reizwerten, die vorkommen
	 * k&ouml;nnen.
	 */
	public void reizwerteErzeugen() {

		// Alle Karo-Reizwerte hinzufuegen
		for (int i = 18; i <= 162; i += 9) {
			biddingValues.add(i);
		}
		// Alle Herz-Reizwerte hinzufuegen
		for (int i = 20; i <= 180; i += 10) {
			biddingValues.add(i);
		}
		// Alle Pik-Reizwerte hinzufuegen
		for (int i = 22; i <= 198; i += 11) {
			biddingValues.add(i);
		}
		// Alle Kreuz-Reizwerte hinzufuegen
		for (int i = 24; i <= 216; i += 12) {
			biddingValues.add(i);
		}
		// Die restlichen Reizwerte von Nullspiel und Grand/Grand-Ouvert
		biddingValues.add(23);
		biddingValues.add(35);
		biddingValues.add(46);
		biddingValues.add(59);
		biddingValues.add(240);
		biddingValues.add(264);

		// neue Reizwerte durch 6erSkat
		biddingValues.add(171);
		biddingValues.add(190);
		biddingValues.add(209);
		biddingValues.add(228);
	}

	/**
	 * Ermittelt den Vorhandspieler und liefert ihn zur&uuml;ck.
	 * 
	 * @return den Vorhandspieler
	 */
	public IPlayer getVorhand() {

		IPlayer vorhand;

		if (player1.getPosition() == Position.VORHAND) {

			vorhand = player1;
		}

		else if (player2.getPosition() == Position.VORHAND) {

			vorhand = player2;
		}

		else {

			vorhand = player3;
		}
		return vorhand;
	}

	/**
	 * Ermittelt den Mittelhandspieler und liefert ihn zur&uuml;ck.
	 * 
	 * @return den Mittelhandspieler
	 */
	public IPlayer getMittelhand() {

		IPlayer mittelhand;

		if (player1.getPosition() == Position.MITTELHAND) {

			mittelhand = player1;
		}

		else if (player2.getPosition() == Position.MITTELHAND) {

			mittelhand = player2;
		}

		else {

			mittelhand = player3;
		}
		return mittelhand;
	}

	/**
	 * Ermittelt den Hinterhandspieler und liefert ihn zur&uuml;ck.
	 * 
	 * @return den Hinterhandspieler
	 */
	public IPlayer getHinterhand() {

		IPlayer hinterhand = null;

		if (player1.getPosition() == Position.HINTERHAND) {

			hinterhand = player1;
		}

		else if (player2.getPosition() == Position.HINTERHAND) {

			hinterhand = player2;
		}

		else {

			hinterhand = player3;
		}
		return hinterhand;
	}

	/**
	 * Die Methode erwartet einen Reizwert und gibt den darauf folgenden
	 * Reizwert zur&uuml;ck. Falls es keinen h&ouml;heren Reizwert gibt, wird
	 * der Eingabewert zur&uuml;ckgegeben.
	 * 
	 * @param reizwert
	 * @return der folgende Reizwert
	 */
	public int nextGreaterBiddingValue(int reizwert) {

		int ergebnis = reizwert;

		for (Integer element : biddingValues) {

			if (element > reizwert) {

				ergebnis = element;
				break;
			}
		}
		return ergebnis;
	}

	/**
	 * Liefert zu einem Reizwert den n&auml;chst kleineren Reizwert zur&uuml;ck.
	 * 
	 * @param reizwert
	 *            - biddingValue, zudem der kleinere Wert gesucht ist
	 * @return kleineren Reizwert
	 */
	public int naechstNiedrigererReizwert(int reizwert) {

		int ergebnis = reizwert;

		for (int i = reizwert - 1; i >= 18; i--) {

			if (biddingValues.contains(i)) {

				ergebnis = i;
				break;
			}
		}

		return ergebnis;
	}

	/**
	 * Ermittelt ob der neue Reizwert existiert und tats&auml;chlich h&ouml;her
	 * ist als der alte Reizwert.
	 * 
	 * @param alterWert
	 *            - zuvor gereizter Wert
	 * @param neuerWert
	 *            - vom Spieler neu vorgeschlagener Reizwert
	 * @return true - falls der neue Reizwert g&uuml;ltig ist
	 */
	public boolean pruefeNeuenReizwert(int alterWert, int neuerWert) {

		boolean ergebnis = false;

		if (neuerWert == 0) {
			// Der Spieler moechte aussteigen, dies ist zugelassen
			ergebnis = true;
		}

		else if (neuerWert > alterWert) {

			for (Integer element : biddingValues) {

				if (element == neuerWert) {

					ergebnis = true;
				}
			}
		}
		return ergebnis;
	}

	/**
	 * Diese Methode liefert den Folgespielers eines Spielers zur&uuml;ck, wenn
	 * im Uhrzeigersinn gespielt wird.
	 * 
	 * @param spieler
	 *            - aktueller Spieler
	 * @return der n&auml;chste Spieler
	 */
	public IPlayer nextPlayer(IPlayer spieler) {

		IPlayer ergebnis = null;

		if (spieler.getPosition() == Position.VORHAND) {

			ergebnis = getMittelhand();
		} else if (spieler.getPosition() == Position.MITTELHAND) {

			ergebnis = getHinterhand();
		} else {

			ergebnis = getVorhand();
		}
		return ergebnis;
	}

	/**
	 * Diese Methode liefert den menschlichen Spieler zur&uuml;ck.
	 */
	public IPlayer getHumanPlayer() {

		IPlayer ergebnis = null;

		if (player1 instanceof IHumanPlayer) {

			ergebnis = player1;
		} else if (player2 instanceof IHumanPlayer) {

			ergebnis = player2;
		} else {

			ergebnis = player3;
		}
		return ergebnis;
	}

	/**
	 * Bestimmt ob der Alleinspieler das Spiel gewonnen hat. Ausserdem werden
	 * zus&auml;tzlich weitere Variablen f&uers Ramschen gesetzt, falls
	 * notwendig.
	 * 
	 * @return true, falls der &uuml;bergebene Spieler das Spiel gewonnen hat
	 */
	public boolean evaluateGame() {
		boolean gewonnen = false;
		if (gameVariety.getGameVariety() != GameVarietyName.RAMSCH) {
			ArrayList<PlayingCard> temp = new ArrayList<PlayingCard>();

			temp = getDeclarer().getTricks();

			int augen = werteAugen(temp);

			int punkte = calculatePoints(augen);
			augenliste.add(augen);
			punkteliste.add(punkte);

			if (punkte > 0) {
				gewonnen = true;
			}

			if (punkte == spaltarschwert) {
				spaltarsch = true;
				ramschRounds = 3;
				bockRounds = 3;
			}

			if (player1.isDeclarer() == true) {
				player1.addPoints(punkte);
			} else {
				player1.addPoints(0);
			}

			if (player2.isDeclarer() == true) {
				player2.addPoints(punkte);
			} else {
				player2.addPoints(0);
			}

			if (player3.isDeclarer() == true) {
				player3.addPoints(punkte);
			} else {
				player3.addPoints(0);
			}
		} else {
			gewonnen = ramschAuswertung();
		}
		
		if (ueberreizt) {
			ueberreizliste.add(true);
		} else {
			ueberreizliste.add(false);
		}
		return gewonnen;
	}

	/**
	 * Ist die Spielart ein Ramsch, wird diese zur Wertung der Punkte gebraucht.
	 * Die Methode schreibt die gewonnenen Punkte in das Spielearray der
	 * Spieler.
	 * 
	 * @return Gibt true zur&uuml;ck, falls der menschliche Spieler 0 oder 120
	 *         Punkte erreicht hat.
	 */
	private boolean ramschAuswertung() {
		boolean gewonnen = false;
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = player1;
		spieler[1] = player2;
		spieler[2] = player3;
		int bock = 1;
		if (this.bock) {
			bock = 2;
		}

		spieler = sortiereSpielerRamsch(spieler);

		ArrayList<PlayingCard> skat = new ArrayList<PlayingCard>();
		skat.add(this.skat[0]);
		skat.add(this.skat[1]);
		if (sixSkat) {
			skat.add(this.skat[2]);
		}
		int skataugen = werteAugen(skat);

		spieler = entscheideRamsch(spieler, skataugen, bock);

		for (int i = 0; i < spieler.length; i++) {
			if (spieler[i].getName() == player1.getName()) {
				player1.setGames(spieler[i].getGames());
			}
			if (spieler[i].getName() == player2.getName()) {
				player2.setGames(spieler[i].getGames());
			}
			if (spieler[i].getName() == player3.getName()) {
				player3.setGames(spieler[i].getGames());
			}
		}
		// getHumanPlayer().getSpiele().add(0);
		int l = getHumanPlayer().getGames().size();
		// Hat der menschliche Spieler 0 oder mehr Punkte, gilt dies als
		// gewonnen.

		if (getHumanPlayer().getGames().get(l - 1) >= 0) {
			gewonnen = true;
		}

		return gewonnen;
	}

	/**
	 * Erstellt ein Array, dass alle Spieler enthaelt, aufsteigend sortiert, der
	 * Spieler mit den wenigsten Augen zuerst.
	 * 
	 * @param spieler
	 *            - array mit allen Spielern
	 * @return das sortierte Array
	 */
	public IPlayer[] sortiereSpielerRamsch(IPlayer[] spieler) {

		// Sortieren des Arrays nach gewonnenen Augen, [0] ist dabei die
		// niedrigste Augenzahl
		for (int i = 0; i < spieler.length; i++) {
			for (int j = 0; j < spieler.length - 1; j++) {
				if (werteAugen(spieler[j].getTricks()) > werteAugen(spieler[j + 1]
						.getTricks())) {
					IPlayer a = spieler[j];
					spieler[j] = spieler[j + 1];
					spieler[j + 1] = a;
				}
			}
		}
		return spieler;
	}

	/**
	 * Entscheidet, welcher Spieler das Spiel gewonnen hat und verteilt die
	 * Punkte.
	 * 
	 * @param spieler
	 *            Bekommt das sortierte Array mit den Spielern
	 * @param skataugen
	 *            Die Augen, die im Skat lagen
	 * @param bock
	 *            Ob Bock gespielt wird oder nicht
	 * @return Das ver&auml;nderte Spielerarray
	 */
	public IPlayer[] entscheideRamsch(IPlayer[] spieler, int skataugen,
			int bock) {
		grundwertliste.add(0);
		// Ist ein Durchmarsch gelungen?
		if (werteAugen(spieler[2].getTricks()) == maximaleaugen) {
			spieler[2].getGames().add(maximaleaugen * bock);
			augenliste.add(maximaleaugen);
			punkteliste.add(maximaleaugen * bock);
			spieler[1].getGames().add(0);
			spieler[0].getGames().add(0);
			// Ist ansonsten ein anderer Spieler Jungfrau geblieben? Dann werden
			// die Augen doppelt gezaehlt.
		} else if (werteAugen(spieler[0].getTricks()) == 0) {
			spieler[2].getGames().add(
					-((werteAugen(spieler[2].getTricks()) + skataugen) * 2)
							* bock);
			augenliste.add((werteAugen(spieler[2].getTricks()) + skataugen));
			punkteliste
					.add(-((werteAugen(spieler[2].getTricks()) + skataugen) * 2)
							* bock);
			spieler[1].getGames().add(0);
			spieler[0].getGames().add(0);
			// Ansonsten bekommt der Spieler mit den meisten Augen so viele
			// Minuspunkte, wie er Augen erspielt hat und Augen im Skat lagen.
		} else {
			spieler[2].getGames().add(
					-((werteAugen(spieler[2].getTricks()) + skataugen)) * bock);
			augenliste.add((werteAugen(spieler[2].getTricks()) + skataugen));
			punkteliste.add(-(werteAugen(spieler[2].getTricks()) + skataugen)
					* bock);
			spieler[1].getGames().add(0);
			spieler[0].getGames().add(0);
		}
		return spieler;
	}

	/**
	 * Berechnet die aktuelle Punktzahl eines Spielers aus seinem Spiele-Array.
	 * 
	 * @param spieler
	 *            - spieler dessen Punkte berechnet werden sollen
	 * @return Punktzahl des Spielers
	 */
	public int getAktuellePunkte(IPlayer spieler) {

		int ergebnis = 0;

		for (int i = 0; i < spieler.getGames().size(); i++) {

			ergebnis += spieler.getGames().get(i);
		}
		return ergebnis;
	}

	/**
	 * Berechnet, wie h&auml;ufig ein Spieler Alleinspieler war und gibt die
	 * Prozentzahl aus.
	 * 
	 * @param spieler
	 *            - spieler dessen Prozentwert gesucht ist
	 * @return Prozent Alleinspieler
	 */
	public int getProzentAllein(IPlayer spieler) {
		int erg = 0;
		for (int i = 0; i < spieler.getGames().size(); i++) {
			if (spieler.getGames().get(i) != 0) {
				erg = erg + 1;
			}

		}
		return ((100 * erg) / spieler.getGames().size());
	}

	/**
	 * Berechnet, wie h&auml;ufig ein Spieler Alleinspieler war.
	 * 
	 * @param spieler
	 *            - Spieler dessen Anzahl der Alleinspiele gesucht ist
	 * @return Anzahl der Spiele
	 */
	public int getAnzahlAllein(IPlayer spieler) {
		int erg = 0;
		for (int i = 0; i < spieler.getGames().size(); i++) {
			if (spieler.getGames().get(i) != 0) {
				erg = erg + 1;
			}

		}
		return erg;
	}

	/**
	 * Ermittelt die Punkte eines Spielers nach Spielabschluss.
	 * 
	 * 
	 * @param augenzahl
	 *            Zahl der Augen, die der Spieler bekommen hat
	 * @return Punkte des Spielers
	 */
	public int calculatePoints(int augenzahl) {

		int erg = 0;
		ueberreizt = false;
		// int zwerg = 0;
		// int stufe = berechneStufe(augenzahl);

		erg = punkteVarianten(erg, augenzahl);

		if (skatVariant == SkatVariant.RAMSCHBOCK && bock == true) {
			erg = erg * 2;
		}

		// erg = ( Math.abs((spitzenZahl() ) + 1 + stufe) * zwerg);
		if (checkVerloren(augenzahl)) {
			erg = erg * (-2);
		}

		if (skatVariant == SkatVariant.SKAT
				|| skatVariant == SkatVariant.RAMSCHBOCK) {
			if (ueberreizCheck(erg) != 0 && biddingValue != 0) {
				
				erg = ueberreizCheck(erg);
			} 
		}

		return erg;

	}

	public int punkteVarianten(int erg, int augenzahl) {
		if (gameVariety.getGameVariety() == GameVarietyName.NULL) {
			grundwertliste.add(23);
			erg = punkteNullspiel();
		}
		if (gameVariety.getGameVariety() == GameVarietyName.GRAND) {
			grundwertliste.add(24);
			erg = punkteGrandspiel(augenzahl);
		}
		if (gameVariety.getGameVariety() == GameVarietyName.SUIT) {
			erg = punkteFarbspiel(augenzahl);
		}
		return erg;
	}

	/**
	 * Ermittelt die Augen eines Spielers nach Spielschluss.
	 * 
	 * @param stiche
	 *            - Stiche, die der Spieler gewonnen hat
	 * @return Augen des Spielers
	 */
	public int werteAugen(ArrayList<PlayingCard> stiche) {

		int erg = 0;

		for (int i = 0; i < stiche.size(); i++) {

			if (stiche.get(i).getValue() == Value.DAUS) {

				erg += 11;

			} else if (stiche.get(i).getValue() == Value.TEN) {

				erg += 10;

			} else if (stiche.get(i).getValue() == Value.UNDER_KNAVE) {

				erg += 2;

			} else if (stiche.get(i).getValue() == Value.OVER_KNAVE) {

				erg += 3;

			} else if (stiche.get(i).getValue() == Value.KING) {

				erg += 4;

			} else if (stiche.get(i).getValue() == Value.SIX) {

				erg += 6;
			}
		}

		return erg;
	}

	/**
	 * Berechnet die Gewinnstufe des abgeschlossenen Spiels. (Erster Teil)
	 * 
	 * @param augenzahl
	 *            Bekommt die erreichte Augenzahl &uuml;bergeben.
	 * @return Die Gewinnstufe
	 */
	public int berechneStufe(int augenzahl) {

		int stufe = 1;

		// Hand gespielt?
		if (handGame == true) {
			stufe = 2;
		}

		// Schneider gespielt?
		if ((augenzahl > gegnerschneider) || (augenzahl < schneiderLimit)) {
			stufe = stufe + 1;
		}

		// Schwarz gespielt?
		if ((augenzahl == maximaleaugen && getDeclarer().getTricks()
				.size() == allTricks)
				|| (augenzahl == 0 && getDeclarer().getTricks()
						.size() == 0)) {
			stufe = stufe + 1;
		}

		stufe = berechnestufe2(stufe);

		return stufe;
	}

	/**
	 * Berechnet Gewinnstufe des abgeschlossenen Spiels. (2. Teil)
	 * 
	 * @param stufe
	 *            - bisher berechnete Stufe
	 * @return fertig berechnete Stufe
	 */
	private int berechnestufe2(int stufe) {

		// Schneider angesagt?
		if (schneider == true) {
			stufe = stufe + 1;
		}

		// Schwarz angesagt?
		if (schwarz == true) {
			stufe = stufe + 1;
		}

		// Ouvert gespielt?
		if (ouvert == true) {
			stufe = stufe + 1;
		}

		return stufe;
	}

	/**
	 * Die Methode pr&uuml;ft, ob ein Spiel gewonnen wurde oder nicht.
	 * 
	 * @param augenzahl
	 *            Die erreichte Augenzahl des Alleinspielers
	 * @return false f&uuml;r gewonnen, true f&uuml;r verloren
	 */
	public boolean checkVerloren(int augenzahl) {
		// Gr&uuml;nde zu verlieren_
		boolean verloren = false;

		if (gameVariety.getGameVariety() == GameVarietyName.NULL) {
			verloren = nullVerloren();
		}

		else if (gameVariety.getGameVariety() != GameVarietyName.NULL) {
			verloren = anderesSpielVerloren(augenzahl);
		}

		return verloren;
	}

	/**
	 * Die Methode pr&uuml;ft, ob ein Nullspiel gewonnen wurde oder nicht.
	 * 
	 * @return false f&uuml;r gewonnen, true f&uuml;r verloren
	 */
	public boolean nullVerloren() {

		boolean verloren = false;

		if (getDeclarer().getTricks().size() > 0) {
			verloren = true;
		}

		return verloren;
	}

	/**
	 * Die Methode pr&uuml;ft, ob ein Farbspiel oder Grandspiel gewonnen wurde
	 * oder nicht.
	 * 
	 * @param augenzahl
	 *            - die erreichte Augenzahl des Alleinspielers
	 * @return false f&uuml;r gewonnen, true f&uuml;r verloren
	 */
	public boolean anderesSpielVerloren(int augenzahl) {

		boolean verloren = false;

		if (augenzahl < winLimit) {
			verloren = true;
		}

		else if (schneider && augenzahl <= gegnerschneider) {
			verloren = true;
		}

		else if (schwarz
				&& getDeclarer().getTricks().size() < allTricks) {
			verloren = true;
		}

		else if (ouvert
				&& getDeclarer().getTricks().size() < allTricks) {
			verloren = true;
		}

		return verloren;
	}

	/**
	 * Die Methode pr&uuml;ft, ob sich der Alleinspieler &uuml;berreizt hat. Die
	 * Methode wird zur Auswertung ben&ouml;tigt.
	 * 
	 * @param punkte
	 *            Die erreichten Punkte werden &uuml;bergeben
	 * @return 0 f&uuml;r nicht &uuml;berreizt, die negative Punktzahl, falls
	 *         &uuml;berreizt wurde.
	 */
	public int ueberreizCheck(int punkte) {

		int zwierg = 0;
		int erg = 0;
		int stufe = 1;

		if (gameVariety.getGameVariety() == GameVarietyName.GRAND) {
			zwierg = 24;
		}
		if (gameVariety.getGameVariety() == GameVarietyName.SUIT) {
			SuitGame spiel = (SuitGame) gameVariety;
			zwierg = spiel.getTrumpSuit().value();
		}
		
		if (punkte < 0) {
			punkte = Math.abs(punkte / 2);
		}
		stufe = ueberreizcheck2(stufe);
		

		if (((Math.abs(getDeclarer().spitzenZahl()) + stufe) * zwierg) < biddingValue
				&& gameVariety.getGameVariety() != GameVarietyName.NULL) {
			
			if (biddingValue > punkte) {
				setUeberreizt(true);
				while (biddingValue > erg) {
					erg = erg + zwierg;
				}
			}
		}

		return (-erg) * 2;
	}

	/**
	 * Zweiter Teil des &Uuml;berreizchecks. Es werden zus&auml;tzlich
	 * schneider, schwarz, ouvert und handgame &uuml;berpr&uuml;ft.
	 * 
	 * @param stufe
	 *            - vorher berechnete Stufe
	 * @return berechnete Stufe
	 */
	private int ueberreizcheck2(int stufe) {

		if (schneider) {
			stufe = stufe + 1;
		}
		if (schwarz) {
			stufe = stufe + 1;
		}
		if (handGame) {
			stufe = stufe + 1;
		}
		if (ouvert) {
			stufe = stufe + 1;
		}
		return stufe;
	}

	/**
	 * Gibt die Anzahl der gewonnenen Spiele als Alleinspieler zur&uuml;ck.
	 * 
	 * @param spieler
	 *            - spieler, dessen gewonnen Spiele ausgegeben werden sollen
	 * @return Anzahl der gewonnen Spiele
	 */
	public int anzahlderGewinne(IPlayer spieler) {

		int ergebnis = 0;

		for (int i = 0; i < spieler.getGames().size(); i++) {

			if (spieler.getGames().get(i) > 0) {

				ergebnis++;
			}
		}
		return ergebnis;
	}

	/**
	 * Berechnet die Punkte, die ein Spieler f&uuml;r sein Nullspiel
	 * erh&auml;lt.
	 * 
	 * @return gewonnene Punkte
	 */
	public int punkteNullspiel() {
		int punkte = 23;
		if (handGame == true && ouvert == true) {
			punkte = 59;
		} else if (handGame == true) {
			punkte = 35;
		} else if (ouvert == true) {
			punkte = 46;
		}
		return punkte;
	}

	/**
	 * Berechnet wieviele Punkte ein Spieler f&uuml;r ein beendetes Grandspiel
	 * erh&auml;lt.
	 * 
	 * @param augenzahl
	 *            - Die vom Spieler gemachten Augen
	 * @return gewonnene Punkte
	 */
	public int punkteGrandspiel(int augenzahl) {
		int punkte = 0;
		punkte = (Math.abs(getDeclarer().spitzenZahl()) + berechneStufe(augenzahl)) * 24;
		return punkte;
	}

	/**
	 * Berechnet, wieviele Punkte ein Spieler f&uuml;r ein gewonnenes Farbspiel
	 * erh&auml;lt.
	 * 
	 * @param augenzahl
	 *            - vom Spieler erreichte Augenzahl
	 * @return gewonnene Punkte
	 */
	public int punkteFarbspiel(int augenzahl) {
		int punkte = 0;
		int grundwert = 0;
		SuitGame spiel = (SuitGame) gameVariety;
		grundwert = spiel.getTrumpSuit().value();
		grundwertliste.add(grundwert);
		punkte = (Math.abs(getDeclarer().spitzenZahl()) + berechneStufe(augenzahl))
				* grundwert;
		return punkte;
	}
}