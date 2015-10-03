package test.stub;

import java.util.ArrayList;

import main.gamevariety.SuitGame;
import main.player.Position;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import test.interfaces.IHumanPlayer;
import test.interfaces.IGameVariety;
import test.interfaces.IPlayer;


public class HumanPlayerStub2 implements IPlayer, IHumanPlayer {

	
	private String name;
	private boolean istAlleinspieler;
	private ArrayList<PlayingCard> blatt;
	
	public HumanPlayerStub2(String name) {
		
		this.name = name;
		this.istAlleinspieler = false;
		this.blatt = new ArrayList<PlayingCard>();
	}
	
	public boolean agent() {
		
		boolean ergebnis = true;
		return ergebnis;
	}

	public void sortHand(IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public PlayingCard[] druecken(PlayingCard[] skat) {
		
		return skat;
	}


	public boolean equals(IPlayer spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public SuitGame suit() {
		
		return new SuitGame(Suit.BELLS);
	}

	
	public ArrayList<PlayingCard> getHand() {
		
		return blatt;
	}

	
	public boolean isDeclarer() {
		
		return istAlleinspieler;
	}

	
	public String getName() {
		
		return name;
	}

	
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
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

	
	public boolean respond(int reizwert) {
	
		boolean ergebnis = false;
		
		if (reizwert <= 18) {
			ergebnis = true;
		}
		return ergebnis;
	}

	
	public ArrayList<Integer> addPoints(int punkte) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean ouvert() {
	
		return true;
	}

	
	public int setBidLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean bid(int alterWert) {
		
		boolean ergebnis = false;
		
		if (alterWert <= 18) {
			ergebnis = true;
		}
		return ergebnis;
	}

	
	public boolean schneider() {
		
		return true;
	}

	
	public boolean schwarz() {

		return true;
	}

	
	public void setHand(ArrayList<PlayingCard> blatt) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIsDeclarer(boolean istAlleinspieler) {
	
		this.istAlleinspieler = istAlleinspieler;
	}

	
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		
	}

	
	public void setGameVariety(IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public void setTricks(ArrayList<PlayingCard> stiche) {
		// TODO Auto-generated method stub
		
	}

	
	public IGameVariety declareGame() {
		
		return new SuitGame(null);
	}

	
	public PlayingCard playCard(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void addTrick(PlayingCard[] stich) {
		// TODO Auto-generated method stub
		
	}

	public void rankUnderKnaves() {
		// TODO Auto-generated method stub
		
	}

	public void rankSuit() {
		// TODO Auto-generated method stub
		
	}

	public void addPlayedCards(PlayingCard[] gespielteKarten) {
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

	public void setAllPlayedCards(ArrayList<PlayingCard> karten) {
		// TODO Auto-generated method stub
		
	}

	public void setDeck(ArrayList<PlayingCard> deck) {
		// TODO Auto-generated method stub
		
	}

	public void setTeammate(IPlayer mitspieler) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setSkat(ArrayList<PlayingCard> skat) {
		// TODO Auto-generated method stub
		
	}

	public void setGames(ArrayList<Integer> spiele) {
		// TODO Auto-generated method stub
		
	}

	public void setTrumps(PlayingCard[] truempfe) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PlayingCard> playableCards(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayingCard[] spitzenEinordnen() {
		// TODO Auto-generated method stub
		return null;
	}

	public int spitzenMit(int erg) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int spitzenOhne(int erg) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int spitzenZahl() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PlayingCard playRamdonAllowedCard(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHandGames() {
		return 0;
	}

	public void setHandGames(int handspiele) {
		
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
