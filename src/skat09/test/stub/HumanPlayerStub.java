package skat09.test.stub;

import java.util.ArrayList;

import skat09.gamevariety.SuitGame;
import skat09.player.Position;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


public class HumanPlayerStub implements IPlayer, IHumanPlayer {

	//
	//Datenfelder
	//
	
	private String name;
	private boolean istAlleinspieler;
	private ArrayList<PlayingCard> blatt;
	Position position;
	
	public HumanPlayerStub(String name) {
		
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

	
	public boolean hoeren(int reizwert) {
	
		boolean ergebnis = false;
		
		if (reizwert <= 22) {
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
		
		return 30;
	}

	
	public boolean sagen(int alterWert) {
		
		boolean ergebnis = false;
		
		if (alterWert <= 22) {
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
		
		this.position = position;
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

	public void bubeneinordnen() {
		// TODO Auto-generated method stub
		
	}

	public void farbeneinordnen() {
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
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHandGames(int handspiele) {
		// TODO Auto-generated method stub
		
	}

	public int bubeneinordnenhilf(int bubenwert) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int farbeeinordnenhilf(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
