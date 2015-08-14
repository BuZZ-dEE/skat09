package skat09.test.stub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import skat09.gamevariety.GrandGame;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


public class OutputStub implements IOutput{

	private String gegner;
	private String variante;
	private String blattwahl;
	private boolean releasetest;
	private int releasecount = 5;
	private int punkte;
	private int gespielteKartenZahl;
	
	public OutputStub(String gegner, String variante, String blattwahl) {
	
		this.gegner = gegner;
		this.variante = variante;
		this.blattwahl = blattwahl;
		punkte = 0;
		gespielteKartenZahl = 0;
	}
	
	public int getPunkte() {
		
		return punkte;
	}
	
	public int getGespielteKartenZahl() {
		
		return gespielteKartenZahl;
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

	
	public void outputHand(IPlayer spieler) throws IOException {
		
	}

	//Drueckt immer die erste Karte des Blattes
	
	public int druecken(ArrayList<PlayingCard> blatt, int nummer) {
		
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
		
		SuitGame farbspiel = new SuitGame(Suit.LEAVES);
		return farbspiel;
	}

	
	public String askForSixSkat() {
		// TODO Auto-generated method stub
		return "j";
	}

	
	public String askForVariant() {
		return variante;
	}

	
	public String adversary(int nummer) {
		
		return gegner;
	}

	
	public String getDeckSelection() {
		return blattwahl;
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

	
	public boolean respond(int wert) {
		
		boolean ergebnis = false;
		
		if(wert <= 20) {
			
			ergebnis = true;
		}
		return ergebnis;
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

	
	public void points(int punkte) {
		
		this.punkte = punkte;
	}

	
	public void outputPoints() {
		
	}

	
	public boolean reizAgent() {
		
		return false;
	}

	
	public int reizlimitFestlegen() {
		
		return 23;
	}

	
	public void release() {
		
	}

	
	public boolean bid(int reizWert) {
		
	boolean ergebnis = false;
		
		if(reizWert <= 20) {
			
			ergebnis = true;
		}
		return ergebnis;
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


	public PlayingCard playCard(PlayingCard[] gespielteKarten, IPlayer spieler)
			throws IOException {
		
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		return karte;
	}

	
	public void spieltKarte(IPlayer spieler, PlayingCard karte) {
		
		gespielteKartenZahl++;
	}

	
	public void trickWon(IPlayer spieler) {
		
	}


	public void deleteTable() {
		
	}

	
	public void trump() {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void pass(IPlayer spieler) {
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
