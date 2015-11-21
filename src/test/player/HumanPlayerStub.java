package test.player;

import java.util.ArrayList;

import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.IHumanPlayer;
import main.player.IPlayer;
import main.player.Position;
import main.playingcard.PlayingCard;

public class HumanPlayerStub implements IPlayer, IHumanPlayer {
	
	private String name;
	private boolean isDeclarer;
	private ArrayList<PlayingCard> hand;
	Position position;
	
	public HumanPlayerStub(String name) {
		
		this.name = name;
		this.isDeclarer = false;
		this.hand = new ArrayList<PlayingCard>();
	}
	
	public boolean agent() {
		
		boolean result = true;
		return result;
	}

	public void sortHand(IGameVariety gameVariety) {
		// TODO Auto-generated method stub
		
	}

	
	public PlayingCard[] druecken(PlayingCard[] skat) {
		
		return skat;
	}


	public boolean equals(IPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public SuitGame suit() {
		
		return new SuitGame(PlayingCard.Suit.BELLS);
	}

	
	public ArrayList<PlayingCard> getHand() {
		
		return hand;
	}

	
	public boolean isDeclarer() {
		
		return isDeclarer;
	}

	
	public String getName() {
		
		return name;
	}

	
	public Position getPosition() {
		
		return position;
	}

	
	public ArrayList<Integer> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<PlayingCard> getTricks() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean handgame() {
		
		return false;
	}

	
	public boolean respond(int biddingValue) {
	
		boolean result = false;
		
		if (biddingValue <= 22) {
			result = true;
		}
		return result;
	}

	
	public ArrayList<Integer> addPoints(int points) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean ouvert() {
	
		return true;
	}

	
	public int setBidLimit() {
		
		return 30;
	}

	
	public boolean bid(int oldBiddingValue) {
		
		boolean result = false;
		
		if (oldBiddingValue <= 22) {
			result = true;
		}
		return result;
	}

	
	public boolean schneider() {
		
		return true;
	}

	
	public boolean schwarz() {

		return true;
	}

	
	public void setHand(ArrayList<PlayingCard> hand) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIsDeclarer(boolean isDeclarer) {
	
		this.isDeclarer = isDeclarer;
	}

	
	public void setPosition(Position position) {
		
		this.position = position;
	}

	
	public void setGameVariety(IGameVariety gameVariety) {
		// TODO Auto-generated method stub
		
	}

	
	public void setTricks(ArrayList<PlayingCard> tricks) {
		// TODO Auto-generated method stub
		
	}

	
	public IGameVariety declareGame() {
		
		return new SuitGame(null);
	}

	
	public PlayingCard playCard(PlayingCard[] playedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void addTrick(PlayingCard[] trick) {
		// TODO Auto-generated method stub
		
	}

	public void rankUnderKnaves() {
		// TODO Auto-generated method stub
		
	}

	public void rankSuit() {
		// TODO Auto-generated method stub
		
	}

	public void addPlayedCards(PlayingCard[] playedCards) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PlayingCard> getAllPlayedCards() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPlayer getTeammate() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayingCard> getRestHand() {
		// TODO Auto-generated method stub
		return null;
	}

	public IGameVariety getGameVariety() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAllPlayedCards(ArrayList<PlayingCard> cards) {
		// TODO Auto-generated method stub
		
	}

	public void setDeck(ArrayList<PlayingCard> deck) {
		// TODO Auto-generated method stub
		
	}

	public void setTeammate(IPlayer teammate) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setSkat(ArrayList<PlayingCard> skat) {
		// TODO Auto-generated method stub
		
	}

	public void setGames(ArrayList<Integer> games) {
		// TODO Auto-generated method stub
		
	}

	public void setTrumps(PlayingCard[] trumps) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PlayingCard> playableCards(PlayingCard[] playedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayingCard[] arrangeMatadorsJackStraitOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public int matadorsJackStraitWith(int erg) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int matadorsJackStraitWithout(int erg) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int matadorsJackStraitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PlayingCard playRamdonAllowedCard(PlayingCard[] playedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHandGames() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHandGames(int handGames) {
		// TODO Auto-generated method stub
		
	}

	public int rankUnderKnavesHelp(int underKnaveValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int rankSuitHelp(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
