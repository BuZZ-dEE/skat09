package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import main.gamevariety.GameVariety;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.IHumanPlayer;
import main.player.IPlayer;
import main.player.Position;
import main.playingcard.PlayingCard;


/**
 * Die Klasse Tisch bildet den momentanen Spielzustand ab und wird durch den
 * Controller gesteuert.
 * 
 * MVC: Der Tisch ist das Model, er erweitert die Klasse Observable. Eventuell
 * müssen noch Methoden überschrieben werden.
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
	 * Skatvariante: Raeuberskat oder normales Skat oder Skat with Ramsch und
	 * Bock?
	 */
	private SkatVariant skatVariant;
	/**
	 * Alle Reizwerte die vorkommen koennen
	 */
	private SortedSet<Integer> biddingValues;
	/**
	 * zählt die bisher gespielten Spiele with
	 */
	private int gameRoundCounter;
	/**
	 * Die Trümpfe, with denen der Alleinspieler spielt
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
	private int spaltarschValue; // 32 Karten: 60, 6erSkat 72
	/**
	 * Schneidergrenze ist bei 32 Karten 30, beim Sechserskat 36
	 */
	private int schneiderLimit; // 32 Karten: 30 , 6er Skat 36
	/**
	 * Schneidergrenze ist bei 32 Karten 90, beim Sechserskat 108
	 */
	private int adversarySchneiderLimit; // 32 Karten: 90, 6erSkat 108
	/**
	 * Maximale Augenzahl ist bei 32 Karten 120, beim Sechserskat 144
	 */
	private int maximalAugen; // 32 Karten: 120, 6erSkat 144
	/**
	 * Anzahl der Karten, die ein Spieler am Ende haben muss, wenn er alle
	 * Stiche gewonnen hat. Bei 32 Karten 30, beim Sechserskat 33
	 */
	private int allTricks; // 32 Karten: 30 Karten, 6erSkat: 33 Karten
	/**
	 * Gibt an, ob das letzte Spiel isOverbidding war.
	 */
	private boolean isOverbidding;
	/**
	 * Enthält so viele Einträge, wie Spiele gespielt wurden. Für
	 * jedes Spiel ist notiert, ob das Spiel überreizt war oder nicht.
	 */
	public ArrayList<Boolean> overbidList;
	/**
	 * Enthält so viele Einträge, wie Spiele gespielt wurden. Für
	 * jedes Spiel ist notiert, wie viele Augen gewonnen wurden.
	 */
	public ArrayList<Integer> augenForRounds;
	/**
	 * Enthält so viele Einträge, wie Spiele gespielt wurden. Für
	 * jedes Spiel ist notiert, wie viele Punkte gewonnen wurden.
	 */
	public ArrayList<Integer> pointsList;
	/**
	 * Enthält so viele Einträge, wie Spiele gespielt wurden. Für
	 * jedes Spiel ist notiert, wie hoch der Grundwert des Spiels war.
	 */
	public ArrayList<Integer> baseValues;



	/**
	 * Instanziert einen Tisch
	 */
	public Table() {

		super();

		gameRoundCounter = 0;
		playedCards = new PlayingCard[3];
		skat = new PlayingCard[3];
		deck = new ArrayList<PlayingCard>();
		baseValues = new ArrayList<Integer>();
		augenForRounds = new ArrayList<Integer>();
		pointsList = new ArrayList<Integer>();
		overbidList = new ArrayList<Boolean>();
		biddingValues = new TreeSet<Integer>();
		biddingValue = 18;
		biddingAgentValue = 0;
		handGame = false;
		schneider = false;
		schwarz = false;
		ouvert = false;
		sixSkat = false;
		winLimit = 61;
		spaltarschValue = 60;
		schneiderLimit = 30;
		adversarySchneiderLimit = 90;
		maximalAugen = 120;
		allTricks = 30;

		generateBiddingValues();

	}

	/**
	 * Liefert den 1. Spieler zurück.
	 * 
	 * @return player1
	 */
	public IPlayer getPlayer1() {

		return player1;
	}

	/**
	 * Liefert den 2. Spieler zurück.
	 * 
	 * @return player2
	 */
	public IPlayer getPlayer2() {

		return player2;
	}

	/**
	 * Liefert den 3. Spieler zurück.
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
	 * Liefert die bereits auf dem Tisch liegenden Karten zurück.
	 * 
	 * @return auf dem Tisch liegende Karten
	 */
	public PlayingCard[] getPlayedCards() {

		return playedCards;
	}

	/**
	 * Gib das Deck des Tisches zurück.
	 * 
	 * @return deck des Tisches
	 */
	public ArrayList<PlayingCard> getDeck() {

		return deck;
	}

	/**
	 * Liefert alle existierenden Reizwerte in einem SortedSet zurück.
	 * 
	 * @return SortedSet (sortierte Reizwerte, die nicht doppelt vorkommen
	 *         können)
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
	 * Liefert die Variable handgame zurück.
	 * 
	 * @return true, falls handgame
	 */
	public boolean getHandGame() {

		return handGame;
	}

	/**
	 * Liefert die aktuell gesetzte Spielart zurück.
	 * 
	 * @return gameVariety der aktuellen Runde
	 */
	public IGameVariety getGameVariety() {

		return gameVariety;
	}

	/**
	 * Liefert die Spielvariante.
	 * 
	 * @return gewätle skatVariant
	 */
	public SkatVariant getVariant() {

		return skatVariant;
	}

	/**
	 * Liefert den aktuell gesetzten Reizwert zurück.
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
	 * Liefert zurück, ob das letzte Spiel 60-60 war.
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
	 * Die Methode gibt die Anzahl der Spiele zurück
	 * 
	 * @return gameRoundCounter Die Anzahl der Spiele
	 */
	public int getGameRoundCounter() {

		return gameRoundCounter;
	}

	/**
	 * Liefert die trumps Variable zurück.
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
	 * Gibt die ArrayList overbidList zurück
	 * 
	 * @return overbidList
	 */
	public ArrayList<Boolean> getOverbids() {
		return overbidList;
	}
	
	/**
	 * Gibt die ArrayList augenForRounds zurück
	 * 
	 * @return augenForRounds
	 */
	public ArrayList<Integer> getAugenForRounds() {
		return augenForRounds;
	}
	
	/**
	 * Gibt die ArrayList pointsList zurück
	 * 
	 * @return pointsList
	 */
	public ArrayList<Integer> getPunkteListe() {
		return pointsList;
	}
	
	/**
	 * Gibt die ArrayList grundwertliste zurück
	 * 
	 * @return grundwertliste
	 */
	public ArrayList<Integer> getBaseValues() {
		return baseValues;
	}

	/**
	 * @param overbidding
	 *            the isOverbidding to set
	 */
	public void setOverbidding(boolean overbidding) {
		this.isOverbidding = overbidding;
	}

	/**
	 * @return the isOverbidding
	 */
	public boolean isOverbidding() {
		return isOverbidding;
	}

	/**
	 * @param sixSkat
	 *            the sixSkat to set
	 */
	public void setSixSkat(boolean sixSkat) {
		this.sixSkat = sixSkat;
		winLimit = 73;
		spaltarschValue = 72;
		schneiderLimit = 36;
		adversarySchneiderLimit = 108;
		maximalAugen = 144;
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
	 * Setzt das schneider-flag with dem übergebenen Wert.
	 * 
	 * @param value
	 *            - true falls gesetzt
	 */
	public void setSchneider(boolean value) {

		this.schneider = value;
	}

	/**
	 * Setzt das schwarz-flag with dem übergebenen Wert.
	 * 
	 * @param value
	 *            - true falls gesetzt
	 */
	public void setSchwarz(boolean value) {

		this.schwarz = value;
	}

	/**
	 * Setzt das ouvert-flag with dem übergebenen Wert.
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
	 *            für die akutelle Runde
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
	public void setTrumps(PlayingCard[] trumps) {

		this.trumps = trumps;
	}

	/**
	 * Erstellt ein neues Deck with 32 Karten. Jede Karte darf nur einmal
	 * vorkommen.
	 */
	public void createDeck() {

		PlayingCard card;


		deck.clear();

		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {

			for (PlayingCard.Rank wert : PlayingCard.Rank.values()) {

				card = new PlayingCard(suit, wert);
				deck.add(card);

				// Falls kein 6er Skat gespielt wird, alle 6er Karten entfernen
				if ((card.getRank() == PlayingCard.Rank.SIX) && (sixSkat == false)) {
					deck.remove(card);
				}
			}
		}

	}

	/**
	 * Mischt das übergebene Deck und liefert es zurück.
	 */
	public void shuffleDeck() {

		Collections.shuffle(deck);
	}

	/**
	 * Wertet einen gespielten Stich aus und gibt den Gewinner zurück.
	 * 
	 * @param playedCards
	 *            - im Stich enthaltene Karten@return das neue Deck bestehend
	 *            aus 32 Karten in einer ArrayList
	 * @param gameVariety
	 *            - übergibt die Spielart
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
	 * @param hand
	 *            - in das die Karten rein sollen
	 * @param cardsCount
	 *            - Anzahl der Karten, die in das Blatt rein sollen
	 */
	private void cardsInToHand(ArrayList<PlayingCard> hand, int cardsCount) {

		for (int i = 0; i < cardsCount; i++) {

			hand.add(deck.remove(0));
		}
	}

	/**
	 * Entnimmt die Spielkarten aus dem vom Tisch erzeugten Deck und verteilt
	 * sie nach den Vorgaben der internationalen Skatordnung an die Spieler und
	 * in den Skat.
	 */
	public void dealOutCards() {
		gameRoundCounter = gameRoundCounter + 1;
		ArrayList<PlayingCard> hand1 = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hand2 = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hand3 = new ArrayList<PlayingCard>();

		// Das array speichert, wieviele Karten ein Spieler beim entsprechenden
		// Schleifendurchlauf bekommt
		int[] cardsCount = new int[3];
		cardsCount[0] = 3;
		cardsCount[1] = 4;
		cardsCount[2] = 3;
		if (sixSkat) {
			cardsCount[2] = 4;
		}

		for (int i = 0; i < 3; i++) {

			cardsInToHand(hand1, cardsCount[i]);
			cardsInToHand(hand2, cardsCount[i]);
			cardsInToHand(hand3, cardsCount[i]);
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
		player1.setHand(hand1);
		player2.setHand(hand2);
		player3.setHand(hand3);
	}

	/**
	 * Addiert ein neues Spiel zu gameRoundCounter.
	 */
	public void addGameCount() {
		gameRoundCounter = gameRoundCounter + 1;
	}

	/**
	 * Weist jedem Blatt der Spieler den jeweiligen Spieler als Besitzer zu.
	 */
	public void giveCardsToOwner() {

		ArrayList<PlayingCard> hand1 = player1.getHand();
		for (PlayingCard card : hand1) {
			card.setOwner(player1);
		}
		ArrayList<PlayingCard> hand2 = player2.getHand();
		for (PlayingCard card : hand2) {
			card.setOwner(player2);
		}
		ArrayList<PlayingCard> hand3 = player3.getHand();
		for (PlayingCard card : hand3) {
			card.setOwner(player3);
		}
	}

	/**
	 * Diese Methode liefert den Alleinspieler zurück.
	 * 
	 * @return der Alleinspieler
	 */
	public IPlayer getDeclarer() {

		IPlayer declarer = null;

		if (player1.isDeclarer()) {

			declarer = player1;
		}

		else if (player2.isDeclarer()) {

			declarer = player2;
		}

		else if (player3.isDeclarer()) {

			declarer = player3;
		}
		return declarer;
	}

	/**
	 * Diese Methode ermittelt den Mitspieler zurück.
	 * 
	 * @return der Mitspieler
	 */
	public IPlayer determineTeammate(IPlayer player) {

		IPlayer teammate = null;

		String playerName = player.getName();
		String declarerName = getDeclarer().getName();

		if (!player1.getName().equals(playerName)
				&& !player1.getName().equals(declarerName)) {

			teammate = player1;
		} else if (!player2.getName().equals(playerName)
				&& !player2.getName().equals(declarerName)) {

			teammate = player2;
		} else {

			teammate = player3;
		}
		return teammate;
	}

	/**
	 * Setzt die Mitspieler in den Spielern, die nicht Alleinspieler sind.
	 */
	public void setTeammate() {

		if (gameVariety.getGameVariety() != GameVariety.Name.RAMSCH) {

			if (!player1.isDeclarer()) {

				player1.setTeammate(determineTeammate(player1));
			}

			else {

				player1.setTeammate(null);
			}

			if (!player2.isDeclarer()) {

				player2.setTeammate(determineTeammate(player2));
			}

			else {

				player2.setTeammate(null);
			}

			if (!player3.isDeclarer()) {

				player3.setTeammate(determineTeammate(player3));
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
	 * Diese Methode sorgt dafür, dass nach einem Spiel die Positionen der
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
	 * Befüllt das Reizwerte-Array with allen Reizwerten, die vorkommen
	 * können.
	 */
	public void generateBiddingValues() {

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
	 * Ermittelt den Vorhandspieler und liefert ihn zurück.
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
	 * Ermittelt den Mittelhandspieler und liefert ihn zurück.
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
	 * Ermittelt den Hinterhandspieler und liefert ihn zurück.
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
	 * Reizwert zurück. Falls es keinen höheren Reizwert gibt, wird
	 * der Eingabewert zurückgegeben.
	 * 
	 * @param biddingValue
	 * @return der folgende Reizwert
	 */
	public int nextGreaterBiddingValue(int biddingValue) {

		int result = biddingValue;

		for (Integer element : biddingValues) {

			if (element > biddingValue) {

				result = element;
				break;
			}
		}
		return result;
	}

	/**
	 * Liefert zu einem Reizwert den nächst kleineren Reizwert zurück.
	 * 
	 * @param biddingValue
	 *            - biddingValue, zudem der kleinere Wert gesucht ist
	 * @return kleineren Reizwert
	 */
	public int nextLowerBiddingValue(int biddingValue) {

		int result = biddingValue;

		for (int i = biddingValue - 1; i >= 18; i--) {

			if (biddingValues.contains(i)) {

				result = i;
				break;
			}
		}

		return result;
	}

	/**
	 * Ermittelt ob der neue Reizwert existiert und tatsächlich höher
	 * ist als der alte Reizwert.
	 * 
	 * @param oldValue
	 *            - zuvor gereizter Wert
	 * @param newValue
	 *            - vom Spieler neu vorgeschlagener Reizwert
	 * @return true - falls der neue Reizwert gültig ist
	 */
	public boolean checkNewBiddingValue(int oldValue, int newValue) {

		boolean result = false;

		if (newValue == 0) {
			// Der Spieler moechte aussteigen, dies ist zugelassen
			result = true;
		}

		else if (newValue > oldValue) {

			for (Integer element : biddingValues) {

				if (element == newValue) {

					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Diese Methode liefert den Folgespielers eines Spielers zurück, wenn
	 * im Uhrzeigersinn gespielt wird.
	 * 
	 * @param player
	 *            - aktueller Spieler
	 * @return der nächste Spieler
	 */
	public IPlayer nextPlayer(IPlayer player) {

		IPlayer ergebnis = null;

		if (player.getPosition() == Position.VORHAND) {

			ergebnis = getMittelhand();
		} else if (player.getPosition() == Position.MITTELHAND) {

			ergebnis = getHinterhand();
		} else {

			ergebnis = getVorhand();
		}
		return ergebnis;
	}

	/**
	 * Diese Methode liefert den menschlichen Spieler zurück.
	 */
	public IPlayer getHumanPlayer() {

		IPlayer result = null;

		if (player1 instanceof IHumanPlayer) {

			result = player1;
		} else if (player2 instanceof IHumanPlayer) {

			result = player2;
		} else {

			result = player3;
		}
		return result;
	}

	/**
	 * Bestimmt ob der Alleinspieler das Spiel gewonnen hat. Ausserdem werden
	 * zusätzlich weitere Variablen f&uers Ramschen gesetzt, falls
	 * notwendig.
	 * 
	 * @return true, falls der übergebene Spieler das Spiel gewonnen hat
	 */
	public boolean evaluateGame() {
		boolean won = false;
		if (gameVariety.getGameVariety() != GameVariety.Name.RAMSCH) {
			ArrayList<PlayingCard> temp = new ArrayList<PlayingCard>();

			temp = getDeclarer().getTricks();

			int augen = calculateAugen(temp);

			int points = calculatePoints(augen);
			augenForRounds.add(augen);
			pointsList.add(points);

			if (points > 0) {
				won = true;
			}

			if (points == spaltarschValue) {
				spaltarsch = true;
				ramschRounds = 3;
				bockRounds = 3;
			}

			if (player1.isDeclarer() == true) {
				player1.addPoints(points);
			} else {
				player1.addPoints(0);
			}

			if (player2.isDeclarer() == true) {
				player2.addPoints(points);
			} else {
				player2.addPoints(0);
			}

			if (player3.isDeclarer() == true) {
				player3.addPoints(points);
			} else {
				player3.addPoints(0);
			}
		} else {
			won = ramschEvaluation();
		}
		
		if (isOverbidding) {
			overbidList.add(true);
		} else {
			overbidList.add(false);
		}
		return won;
	}

	/**
	 * Ist die Spielart ein Ramsch, wird diese zur Wertung der Punkte gebraucht.
	 * Die Methode schreibt die gewonnenen Punkte in das Spielearray der
	 * Spieler.
	 * 
	 * @return Gibt true zurück, falls der menschliche Spieler 0 oder 120
	 *         Punkte erreicht hat.
	 */
	private boolean ramschEvaluation() {
		boolean won = false;
		IPlayer[] players = new IPlayer[3];
		players[0] = player1;
		players[1] = player2;
		players[2] = player3;
		int bock = 1;
		if (this.bock) {
			bock = 2;
		}

		players = sortPlayerRamsch(players);

		ArrayList<PlayingCard> skat = new ArrayList<PlayingCard>();
		skat.add(this.skat[0]);
		skat.add(this.skat[1]);
		if (sixSkat) {
			skat.add(this.skat[2]);
		}
		int skataugen = calculateAugen(skat);

		players = decideRamsch(players, skataugen, bock);

		for (int i = 0; i < players.length; i++) {
			if (players[i].getName() == player1.getName()) {
				player1.setGames(players[i].getGames());
			}
			if (players[i].getName() == player2.getName()) {
				player2.setGames(players[i].getGames());
			}
			if (players[i].getName() == player3.getName()) {
				player3.setGames(players[i].getGames());
			}
		}
		// getHumanPlayer().getSpiele().add(0);
		int l = getHumanPlayer().getGames().size();
		// Hat der menschliche Spieler 0 oder mehr Punkte, gilt dies als
		// won.

		if (getHumanPlayer().getGames().get(l - 1) >= 0) {
			won = true;
		}

		return won;
	}

	/**
	 * Erstellt ein Array, dass alle Spieler enthaelt, aufsteigend sortiert, der
	 * Spieler with den wenigsten Augen zuerst.
	 * 
	 * @param player
	 *            - array with allen Spielern
	 * @return das sortierte Array
	 */
	public IPlayer[] sortPlayerRamsch(IPlayer[] player) {

		// Sortieren des Arrays nach gewonnenen Augen, [0] ist dabei die
		// niedrigste Augenzahl
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player.length - 1; j++) {
				if (calculateAugen(player[j].getTricks()) > calculateAugen(player[j + 1]
						.getTricks())) {
					IPlayer a = player[j];
					player[j] = player[j + 1];
					player[j + 1] = a;
				}
			}
		}
		return player;
	}

	/**
	 * Entscheidet, welcher Spieler das Spiel gewonnen hat und verteilt die
	 * Punkte.
	 * 
	 * @param player
	 *            Bekommt das sortierte Array with den Spielern
	 * @param skataugen
	 *            Die Augen, die im Skat lagen
	 * @param bock
	 *            Ob Bock gespielt wird oder nicht
	 * @return Das veränderte Spielerarray
	 */
	public IPlayer[] decideRamsch(IPlayer[] player, int skataugen,
								  int bock) {
		baseValues.add(0);
		// Ist ein Durchmarsch gelungen?
		if (calculateAugen(player[2].getTricks()) == maximalAugen) {
			player[2].getGames().add(maximalAugen * bock);
			augenForRounds.add(maximalAugen);
			pointsList.add(maximalAugen * bock);
			player[1].getGames().add(0);
			player[0].getGames().add(0);
			// Ist ansonsten ein anderer Spieler Jungfrau geblieben? Dann werden
			// die Augen doppelt gezaehlt.
		} else if (calculateAugen(player[0].getTricks()) == 0) {
			player[2].getGames().add(
					-((calculateAugen(player[2].getTricks()) + skataugen) * 2)
							* bock);
			augenForRounds.add((calculateAugen(player[2].getTricks()) + skataugen));
			pointsList
					.add(-((calculateAugen(player[2].getTricks()) + skataugen) * 2)
							* bock);
			player[1].getGames().add(0);
			player[0].getGames().add(0);
			// Ansonsten bekommt der Spieler with den meisten Augen so viele
			// Minuspunkte, wie er Augen erspielt hat und Augen im Skat lagen.
		} else {
			player[2].getGames().add(
					-((calculateAugen(player[2].getTricks()) + skataugen)) * bock);
			augenForRounds.add((calculateAugen(player[2].getTricks()) + skataugen));
			pointsList.add(-(calculateAugen(player[2].getTricks()) + skataugen)
					* bock);
			player[1].getGames().add(0);
			player[0].getGames().add(0);
		}
		return player;
	}

	/**
	 * Berechnet die aktuelle Punktzahl eines Spielers aus seinem Spiele-Array.
	 * 
	 * @param player
	 *            - spieler dessen Punkte berechnet werden sollen
	 * @return Punktzahl des Spielers
	 */
	public int getAktuellePunkte(IPlayer player) {

		int result = 0;

		for (int i = 0; i < player.getGames().size(); i++) {

			result += player.getGames().get(i);
		}
		return result;
	}

	/**
	 * Berechnet, wie häufig ein Spieler Alleinspieler war und gibt die
	 * Prozentzahl aus.
	 * 
	 * @param player
	 *            - spieler dessen Prozentwert gesucht ist
	 * @return Prozent Alleinspieler
	 */
	public int getPercentDeclarer(IPlayer player) {
		int result = 0;
		for (int i = 0; i < player.getGames().size(); i++) {
			if (player.getGames().get(i) != 0) {
				result = result + 1;
			}

		}
		return ((100 * result) / player.getGames().size());
	}

	/**
	 * Berechnet, wie häufig ein Spieler Alleinspieler war.
	 * 
	 * @param player
	 *            - Spieler dessen Anzahl der Alleinspiele gesucht ist
	 * @return Anzahl der Spiele
	 */
	public int getAnzahlAllein(IPlayer player) {
		int result = 0;
		for (int i = 0; i < player.getGames().size(); i++) {
			if (player.getGames().get(i) != 0) {
				result = result + 1;
			}

		}
		return result;
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

		int result = 0;
		isOverbidding = false;
		// int zwerg = 0;
		// int stufe = calculateLevel(augenzahl);

		result = pointsVariants(result, augenzahl);

		if (skatVariant == SkatVariant.RAMSCHBOCK && bock == true) {
			result = result * 2;
		}

		// erg = ( Math.abs((matadorsJackStraitCount() ) + 1 + stufe) * zwerg);
		if (checkVerloren(augenzahl)) {
			result = result * (-2);
		}

		if (skatVariant == SkatVariant.SKAT
				|| skatVariant == SkatVariant.RAMSCHBOCK) {
			if (checkOverbid(result) != 0 && biddingValue != 0) {
				
				result = checkOverbid(result);
			} 
		}

		return result;

	}

	public int pointsVariants(int result, int augenCount) {
		if (gameVariety.getGameVariety() == GameVariety.Name.NULL) {
			baseValues.add(23);
			result = pointsNullGame();
		}
		if (gameVariety.getGameVariety() == GameVariety.Name.GRAND) {
			baseValues.add(24);
			result = pointsGrandGame(augenCount);
		}
		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT) {
			result = pointsSuitGame(augenCount);
		}
		return result;
	}

	/**
	 * Ermittelt die Augen eines Spielers nach Spielschluss.
	 * 
	 * @param tricks
	 *            - Stiche, die der Spieler gewonnen hat
	 * @return Augen des Spielers
	 */
	public int calculateAugen(ArrayList<PlayingCard> tricks) {

		int result = 0;

		for (int i = 0; i < tricks.size(); i++) {

			if (tricks.get(i).getRank() == PlayingCard.Rank.DAUS) {

				result += 11;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.TEN) {

				result += 10;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.UNDER_KNAVE) {

				result += 2;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.OVER_KNAVE) {

				result += 3;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.KING) {

				result += 4;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.SIX) {

				result += 6;
			}
		}

		return result;
	}

	/**
	 * Berechnet die Gewinnstufe des abgeschlossenen Spiels. (Erster Teil)
	 * 
	 * @param augenzahl
	 *            Bekommt die erreichte Augenzahl übergeben.
	 * @return Die Gewinnstufe
	 */
	public int calculateLevel(int augenzahl) {

		int level = 1;

		// Hand gespielt?
		if (handGame == true) {
			level = 2;
		}

		// Schneider gespielt?
		if ((augenzahl > adversarySchneiderLimit) || (augenzahl < schneiderLimit)) {
			level = level + 1;
		}

		// Schwarz gespielt?
		if ((augenzahl == maximalAugen && getDeclarer().getTricks()
				.size() == allTricks)
				|| (augenzahl == 0 && getDeclarer().getTricks()
						.size() == 0)) {
			level = level + 1;
		}

		level = calculateLevel2(level);

		return level;
	}

	/**
	 * Berechnet Gewinnstufe des abgeschlossenen Spiels. (2. Teil)
	 * 
	 * @param level
	 *            - bisher berechnete Stufe
	 * @return fertig berechnete Stufe
	 */
	private int calculateLevel2(int level) {

		// Schneider angesagt?
		if (schneider == true) {
			level = level + 1;
		}

		// Schwarz angesagt?
		if (schwarz == true) {
			level = level + 1;
		}

		// Ouvert gespielt?
		if (ouvert == true) {
			level = level + 1;
		}

		return level;
	}

	/**
	 * Die Methode prüft, ob ein Spiel gewonnen wurde oder nicht.
	 * 
	 * @param augenzahl
	 *            Die erreichte Augenzahl des Alleinspielers
	 * @return false für gewonnen, true für verloren
	 */
	public boolean checkVerloren(int augenzahl) {
		// Gründe zu verlieren_
		boolean lost = false;

		if (gameVariety.getGameVariety() == GameVariety.Name.NULL) {
			lost = nullVerloren();
		}

		else if (gameVariety.getGameVariety() != GameVariety.Name.NULL) {
			lost = anderesSpielVerloren(augenzahl);
		}

		return lost;
	}

	/**
	 * Die Methode prüft, ob ein Nullspiel gewonnen wurde oder nicht.
	 * 
	 * @return false für gewonnen, true für verloren
	 */
	public boolean nullVerloren() {

		boolean lost = false;

		if (getDeclarer().getTricks().size() > 0) {
			lost = true;
		}

		return lost;
	}

	/**
	 * Die Methode prüft, ob ein Farbspiel oder Grandspiel gewonnen wurde
	 * oder nicht.
	 * 
	 * @param augenzahl
	 *            - die erreichte Augenzahl des Alleinspielers
	 * @return false für gewonnen, true für verloren
	 */
	public boolean anderesSpielVerloren(int augenzahl) {

		boolean lost = false;

		if (augenzahl < winLimit) {
			lost = true;
		}

		else if (schneider && augenzahl <= adversarySchneiderLimit) {
			lost = true;
		}

		else if (schwarz
				&& getDeclarer().getTricks().size() < allTricks) {
			lost = true;
		}

		else if (ouvert
				&& getDeclarer().getTricks().size() < allTricks) {
			lost = true;
		}

		return lost;
	}

	/**
	 * Die Methode prüft, ob sich der Alleinspieler überreizt hat. Die
	 * Methode wird zur Auswertung benötigt.
	 * 
	 * @param points
	 *            Die erreichten Punkte werden übergeben
	 * @return 0 für nicht überreizt, die negative Punktzahl, falls
	 *         überreizt wurde.
	 */
	public int checkOverbid(int points) {

		int zwierg = 0;
		int result = 0;

		if (gameVariety.getGameVariety() == GameVariety.Name.GRAND) {
			zwierg = 24;
		}
		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT) {
			SuitGame suit = (SuitGame) gameVariety;
			zwierg = suit.getTrumpSuit().value();
		}
		
		if (points < 0) {
			points = Math.abs(points / 2);
		}
		int level = getLLevel();
		

		if (((Math.abs(getDeclarer().matadorsJackStraitCount()) + level) * zwierg) < biddingValue
				&& gameVariety.getGameVariety() != GameVariety.Name.NULL) {
			
			if (biddingValue > points) {
				setOverbidding(true);
				while (biddingValue > result) {
					result = result + zwierg;
				}
			}
		}

		return (-result) * 2;
	}

	/**
	 * Zweiter Teil des überreizchecks. Es werden zusätzlich
	 * schneider, schwarz, ouvert und handgame überprüft.
	 *
	 * @return berechnete Stufe
	 */
	private int getLLevel() {

        int level = 1;

		if (schneider) {
			level = level + 1;
		}
		if (schwarz) {
			level = level + 1;
		}
		if (handGame) {
			level = level + 1;
		}
		if (ouvert) {
			level = level + 1;
		}

		return level;
	}

	/**
	 * Gibt die Anzahl der gewonnenen Spiele als Alleinspieler zurück.
	 * 
	 * @param player
	 *            - spieler, dessen gewonnen Spiele ausgegeben werden sollen
	 * @return Anzahl der gewonnen Spiele
	 */
	public int getDeclarersWonGamesSum(IPlayer player) {

		int result = 0;

		for (int i = 0; i < player.getGames().size(); i++) {

			if (player.getGames().get(i) > 0) {

				result++;
			}
		}
		return result;
	}

	/**
	 * Berechnet die Punkte, die ein Spieler für sein Nullspiel
	 * erhält.
	 * 
	 * @return gewonnene Punkte
	 */
	public int pointsNullGame() {
		int points = 23;
		if (handGame == true && ouvert == true) {
			points = 59;
		} else if (handGame == true) {
			points = 35;
		} else if (ouvert == true) {
			points = 46;
		}
		return points;
	}

	/**
	 * Berechnet wieviele Punkte ein Spieler für ein beendetes Grandspiel
	 * erhält.
	 * 
	 * @param augenzahl
	 *            - Die vom Spieler gemachten Augen
	 * @return gewonnene Punkte
	 */
	public int pointsGrandGame(int augenzahl) {
		int points = 0;
		points = (Math.abs(getDeclarer().matadorsJackStraitCount()) + calculateLevel(augenzahl)) * 24;
		return points;
	}

	/**
	 * Berechnet, wieviele Punkte ein Spieler für ein gewonnenes Farbspiel
	 * erhält.
	 * 
	 * @param augenzahl
	 *            - vom Spieler erreichte Augenzahl
	 * @return gewonnene Punkte
	 */
	public int pointsSuitGame(int augenzahl) {
		int points = 0;
		int baseValue = 0;
		SuitGame suitGame = (SuitGame) gameVariety;
		baseValue = suitGame.getTrumpSuit().value();
		baseValues.add(baseValue);
		points = (Math.abs(getDeclarer().matadorsJackStraitCount()) + calculateLevel(augenzahl))
				* baseValue;
		return points;
	}
}