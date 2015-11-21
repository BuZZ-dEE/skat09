package main.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;


/**
 * Diese Klasse Spieler simuliert einen Skatspieler mit all seinen
 * Möglichkeiten am Spiel teilzunehmen. Ein Spieler hat zum Beispiel ein
 * Blatt, eine Position am Spieltisch, kann reizen und Karten Spielen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */

abstract public class Player implements IPlayer {
	

	/**
	 *  Name of the player
	 */
	protected String name;

	/**
	 *  The players position on the table.
	 */
	protected Position position;

	/**
	 * The Players hand cards.
	 */
	protected ArrayList<PlayingCard> hand;

	/**
	 * The players won tricks.
	 */
	protected ArrayList<PlayingCard> tricks;

	/**
	 *  Represents the declarer state for a player.
	 */
	protected boolean isDeclarer;

	/**
	 *  A player knows his teammate if he has one.
	 */
	protected IPlayer teammate;

	/**
	 *  List of the played games.
	 */
	protected ArrayList<Integer> games;

	/**
	 *  Player needs to know the game variet for card checking
	 */
	protected IGameVariety gameVariety;

	/**
	 * Holds the cards after taking the skat.
	 */
	protected ArrayList<PlayingCard> afterSkat;
	
	/**
	 *  For player knowing all cards.
	 */
	protected ArrayList<PlayingCard> deck;
	
	/**
	 * Holds the skat for declarer.
	 */
	protected ArrayList<PlayingCard> skat;

	/**
	 *  Player can remember the played cards.
	 */
	protected ArrayList<PlayingCard> allPlayedCards;

	/**
	 * The rest hand hold the not playable cards.
	 */
	private ArrayList<PlayingCard> restHand = new ArrayList<PlayingCard>();
	
	/**
	 *  Spieler kann seine Truempfe in diesem Array speichern, um sie zu zaehlen
	 */
	private PlayingCard[] trumps = new PlayingCard[12];
	
	/**
	 * Sum of the players played hand games.
	 */
	private int handGames = 0;

	/**
	 * Create a new player object.
	 * 
	 * @param name
	 *            - name of the player
	 * 
	 */
	public Player(String name) {

		games = new ArrayList<Integer>();
		this.name = name;
		tricks = new ArrayList<PlayingCard>();
		allPlayedCards = new ArrayList<PlayingCard>();
	}
	

	@Override
	public String getName() {

		return name;
	}

	@Override
	public Position getPosition() {

		return position;
	}

	@Override
	public ArrayList<PlayingCard> getHand() {

		return hand;
	}

	@Override
	public ArrayList<PlayingCard> getTricks() {

		return tricks;
	}

	@Override
	public boolean isDeclarer() {

		return isDeclarer;
	}

	@Override
	public ArrayList<Integer> getGames() {

		return games;
	}

	@Override
	public IPlayer getTeammate() {

		return teammate;
	}
	
	public ArrayList<PlayingCard> getSkat() {
		
		return skat;
	}
	
	@Override
	public ArrayList<PlayingCard> getAllPlayedCards() {
		return allPlayedCards;
	}

	@Override
	public ArrayList<PlayingCard> getRestHand() {

		return restHand;
	}

	@Override
	public IGameVariety getGameVariety() {

		return this.gameVariety;
	}
	
	@Override
	public int getHandGames() {
		return handGames;
	}
	
	@Override
	public void setHandGames(int handGames) {
		this.handGames = handGames;
	}



	@Override
	public void setGameVariety(IGameVariety gameVariety) {

		this.gameVariety = gameVariety;
	}

	@Override
	public void setPosition(Position position) {

		this.position = position;
	}

	@Override
	public void setHand(ArrayList<PlayingCard> hand) {

		this.hand = hand;
	}

	@Override
	public void setIsDeclarer(boolean isDeclarer) {

		this.isDeclarer = isDeclarer;
	}

	@Override
	public void setTeammate(IPlayer teammate) {

		this.teammate = teammate;
	}
	
	@Override
	public void setGames(ArrayList<Integer> games) {
		this.games = games;

	}

	@Override
	public void setTricks(ArrayList<PlayingCard> tricks) {

		this.tricks = tricks;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setAllPlayedCards(ArrayList<PlayingCard> cards) {
		this.allPlayedCards = cards;
	}
	
	@Override
	public void setTrumps(PlayingCard[] trumps) {
		this.trumps = trumps;
	}
	
	@Override
	public void setDeck(ArrayList<PlayingCard> deck) {
		
		this.deck = deck;
	}
	
	@Override
	public void setSkat(ArrayList<PlayingCard> skat) {
		
		this.skat = skat;
	}
	

	@Override
	public void addTrick(PlayingCard[] trick) {

		for (int i = 0; i < trick.length; i++) {

			tricks.add(trick[i]);
		}
	}

	/**
	 * Fügt einen gewonnenen Stich zu den bisher gespielten Stichen hinzu.
	 * @param playedCards
	 * 
	 */
	@Override
	public void addPlayedCards(PlayingCard[] playedCards) {

		for (int i = 0; i < playedCards.length; i++) {

			allPlayedCards.add(playedCards[i]);
		}
	}

	@Override
	public ArrayList<PlayingCard> playableCards(PlayingCard[] playableCards) {

		ArrayList<PlayingCard> cardsToPlay = new ArrayList<PlayingCard>();

		for (int i = 0; i < hand.size(); i++) {

			if (gameVariety.checkedPlayedCards(hand, playableCards, hand.get(i))) {

				cardsToPlay.add(hand.get(i));
			} else {
				
				restHand.add(hand.get(i));
			}
		}
		
		return cardsToPlay;
	}

	@Override
	abstract public PlayingCard playCard(PlayingCard[] playedCards)
			throws IOException;
	
	@Override
	public PlayingCard playRamdonAllowedCard(PlayingCard[] playedCards) {

		PlayingCard result = null;
		Random random = new Random();
		int zahl = random.nextInt(hand.size());

		while (!gameVariety.checkedPlayedCards(hand, playedCards, hand
				.get(zahl))) {

			zahl = random.nextInt(hand.size());
		}
		result = hand.remove(zahl);

		return result;
	}

	@Override
	abstract public PlayingCard[] druecken(PlayingCard[] skat);
	

	@Override
	abstract public IGameVariety declareGame();

	@Override
	abstract public boolean handgame();

	@Override
	abstract public boolean ouvert();

	@Override
	abstract public boolean schneider();

	@Override
	abstract public boolean schwarz();

	@Override
	abstract public SuitGame suit();

	@Override
	public ArrayList<Integer> addPoints(int points) {

		games.add(points);
		return games;
	}
	
	/**
	 * Hören
	 * 
	 * @version 29.07.2015 20:47:34
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	@Override
	abstract public boolean respond(int reizValue);

	@Override
	
	/**
	 * Sagen
	 * 
	 * @version 29.07.2015 20:48:32
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	abstract public boolean bid(int oldValue);

	@Override
	public void sortHand(final IGameVariety gameVariety) {

		Comparator<PlayingCard> comp = new Comparator<PlayingCard>() {

			public int compare(PlayingCard card1, PlayingCard card2) {

				int result;

				if (card1.equals(gameVariety.sortCard(card1, card2))) {

					result = 1;
					
				} else if (card1.equals(card2)) {

					result = 0;
					
				} else {

					result = -1;
					
				} return result;
			}
		};
		
		Collections.sort(hand, comp);
	}

	@Override
	public abstract boolean agent();

	@Override
	public boolean equals(IPlayer player) {

		boolean result = false;

		if (player.getName().equals(this.getName())) {

			result = true;
		}

		return result;
	}

	@Override
	public abstract int setBidLimit();
	
	@Override
	public PlayingCard[] arrangeMatadorsJackStraitOrder() {

		trumps = new PlayingCard[12];

		if (gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			rankUnderKnaves();
		}
		
		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT) {

			rankUnderKnaves();
			rankSuit();
		}
		
		return this.trumps;
	}
	
	@Override
	public void rankUnderKnaves() {
		
		int cardValue = 0;
		ArrayList<PlayingCard> hand = getHand();

		for (int i = 0; i < hand.size(); i++) {
			
			if (hand.get(i).getValue() == PlayingCard.Rank.UNDER_KNAVE) {

				cardValue = gameVariety.evaluateCard (hand.get(i));
				trumps[rankUnderKnavesHelp(cardValue)] = hand.get(i);
			}
		}
	}

	@Override
	public int rankUnderKnavesHelp(int underKnaveValue) {
		
		int erg = -1;
		
		if (underKnaveValue == 14) {
			
			erg = 3;
		}
		
		if (underKnaveValue == 15) {
			
			erg = 2;
		}
		
		if (underKnaveValue == 16) {
			
			erg = 1;
		}
		
		if (underKnaveValue == 17) {
			
			erg = 0;
		}
		
		return erg;
	}

	@Override
	public void rankSuit() {
		
		int cardValue = 0;
		SuitGame suitGame = (SuitGame) gameVariety;
		ArrayList<PlayingCard> hand = getHand();

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getValue() != PlayingCard.Rank.UNDER_KNAVE && hand.get(i).getSuit() == suitGame.getTrumpSuit()) {

				cardValue = gameVariety.evaluateCard(hand.get(i));
				trumps[rankSuitHelp(cardValue)] = hand.get(i);
			}
		}
	}

	@Override
	public int rankSuitHelp(int i) {
		
		int erg = -1;
		
		if (i == 6) {
			
			erg = 11;
		}
		
		if (i == 7) {
			
			erg = 10;
		}
		
		if (i == 8) {
			
			erg = 9;
		}
		
		if (i == 9) {
			
			erg = 8;
		}
		
		if (i == 10) {
			
			erg = 7;
		}
		
		if (i == 11) {
			
			erg = 6;
		}
		
		if (i == 12) {
			
			erg = 5;
		}
		
		if (i == 13) {
			
			erg = 4;
		}
		
		return erg;
	}
	
	@Override
	public int matadorsJackStraitCount() {
		
		int erg = 0;

		if (trumps[0] != null) {
			
			erg = matadorsJackStraitWith(erg);
		}
		
		else {
			
			erg = matadorsJackStraitWithout(erg);
		}

		return erg;
	}
	
	@Override
	public int matadorsJackStraitWith(int erg) {

		for (int i = 0; i < trumps.length; i++) {

			if (trumps[i] == null) {

				erg = i;
				break;
			}
		}
		return erg;
	}
	
	@Override
	public int matadorsJackStraitWithout(int erg) {

		for (int i = 0; i < trumps.length; i++) {
			
			if (trumps[i] != null) {
				
				erg = -i;
				break;
			}
		}
		
		if (erg == 0) {

			if (gameVariety instanceof GrandGame) {

				erg = -4;
			}
			
			if (gameVariety instanceof SuitGame) {

				erg = -11;
			}
		}
		
		return erg;
	}
}