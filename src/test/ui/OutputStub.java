package test.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import main.IController;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.IPlayer;
import main.playingcard.PlayingCard;
import main.ui.IOutput;


public class OutputStub implements IOutput{

	private String adversary;
	private String variant;
	private String selectedDeck;
	private boolean releasetest;
	private int releasecount = 5;
	private int points;
	private int playedCardsCount;
	
	public OutputStub(String adversary, String variant, String selectedDeck
	) {
	
		this.adversary = adversary;
		this.variant = variant;
		this.selectedDeck = selectedDeck;
		points = 0;
		playedCardsCount = 0;
	}
	
	public int getPoints() {
		
		return points;
	}
	
	public int getPlayedCardsCount() {
		
		return playedCardsCount;
	}
	
	public void showDeclarer() {
		
	}

	
	public void anotherCard() {
		System.out.println("Bitte spiele eine andere Karte!");
		
	}

	
	public void augen(int augen) {
		// TODO Auto-generated method stub
		
	}

	
	public void showEvaluation(boolean isWon) {
		
	}

	
	public void outputHand(IPlayer player) throws IOException {
		
	}
	
	public int druecken(ArrayList<PlayingCard> hand, int number) {
		
		return 0; 
	}

	
	public String readInput() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void wrongInputHint() {
		// TODO Auto-generated method stub
		
	}

	
	public SuitGame suitGame() {
		
		SuitGame suitGame = new SuitGame(PlayingCard.Suit.LEAVES);
		return suitGame;
	}

	
	public String askForSixSkat() {
		// TODO Auto-generated method stub
		return "j";
	}

	
	public String askForVariant() {
		return variant;
	}

	
	public String adversary(int number) {
		
		return adversary;
	}

	
	public String getDeckSelection() {
		return selectedDeck;
	}

	
	public boolean getRelease() {
		if (releasecount != 0) {
			releasecount--;
			return releasetest;
		}
		else {
			releasetest = true;
			return releasetest;
		}
	}

	
	public boolean handgame() {

		return false;
	}

	
	public void openGameTable() {
		
	}

	
	public void hinterhandVsWinner(IPlayer winner) {
		
	}

	
	public boolean respond(int value) {
		
		boolean result = false;
		
		if(value <= 20) {
			
			result = true;
		}
		return result;
	}

	
	public void blankLine() {
		
	}

	
	public IController makeController() {
		return null;
	}

	
	public void makeView() {
		
	}

	
	public void mittelhandVsVorhand() {
		
	}

	
	public String name() {
		
		return "Knut";
	}

	
	public void newGame() {
		
	}

	
	public boolean ouvert() {
		
		return true;
	}

	
	public void points(int points) {
		
		this.points = points;
	}

	
	public void outputPoints() {
		
	}

	
	public boolean biddingAgent() {
		
		return false;
	}

	
	public int setBiddingLimit() {
		
		return 23;
	}

	
	public void release() {
		
	}

	
	public boolean bid(int biddingValue) {
		
	boolean result = false;
		
		if(biddingValue <= 20) {
			
			result = true;
		}
		return result;
	}

	
	public boolean schneider() {
		
		return true;
	}

	
	public boolean schwarz() {
		
		return false;
	}

	
	public void setRelease(boolean arsch) {
		releasetest = false;
		rtest();
			
		}
	
	public boolean rtest() {
		return releasetest;
	}


	public void outputSkat(PlayingCard[] skat) throws IOException {
		
	}

	
	public void play() {
		// TODO Auto-generated method stub
		
	}


	public IGameVariety declareSuit() {
		
		return new GrandGame();
	}


	public boolean quitGame() {
		// TODO Auto-generated method stub
		return true;
	}


	public void gameOver() {
		
	}

	
	public void gameBegins() {
		// TODO Auto-generated method stub
		
	}


	public void passGame() {
		// TODO Auto-generated method stub
		
	}


	public PlayingCard playCard(PlayingCard[] playedCards, IPlayer player)
			throws IOException {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		return card;
	}

	
	public void playCard(IPlayer player, PlayingCard card) {
		
		playedCardsCount++;
	}

	
	public void trickWon(IPlayer player) {
		
	}


	public void deleteTable() {
		
	}

	
	public void trump() {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void pass(IPlayer player) {
		// TODO Auto-generated method stub
		
	}


	public void cleanUpGUI() {

		
	}


	public void showPosition() {
		// TODO Auto-generated method stub
		
	}

	public void statistics() {
		// TODO Auto-generated method stub
		
	}

}
