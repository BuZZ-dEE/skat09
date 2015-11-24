package main.ui;

import java.io.IOException;
import java.util.ArrayList;

import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.IPlayer;
import main.playingcard.PlayingCard;


/**
 * Die Klasse Ausgabe beinhaltet die abstrakten Methoden f&uuml;r die CLIAusgabe
 * und die GUIAusgabe
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
abstract public class Output implements IOutput {

	/**
	 * Ist das Flag true, ist die Ausgabe frei, ist es false muss die Ausgabe
	 * warten
	 */
	private boolean release = false;

	//@Override
	public boolean getRelease() {
		return release;
	}

	//@Override
	public void setRelease(boolean release) {
		this.release = release;
	}

	//@Override
	public abstract String name();

	//@Override
	public abstract boolean respond(int wert);

	//@Override
	public abstract boolean bid(int reizWert);

	//@Override
	public abstract String adversary(int nummer);

	//@Override
	public abstract String askForVariant();

	//@Override
	public abstract void wrongInputHint();

	//@Override
	public abstract boolean handgame();

	//@Override
	public abstract void play();

	//@Override
	public abstract int druecken(ArrayList<PlayingCard> blatt, int nummer);

	//@Override
	public abstract boolean schneider();

	//@Override 
	 
	public abstract boolean schwarz();

	//@Override
	public abstract boolean ouvert();

	//@Override
	public abstract IGameVariety declareSuit();

	//@Override
	public abstract SuitGame suitGame();

	//@Override
	public abstract PlayingCard playCard(PlayingCard[] gespielteKarten,
										 IPlayer spieler) throws IOException;

	//@Override
	public abstract void trickWon(IPlayer spieler);

	//@Override
	public abstract void outputHand(IPlayer spieler) throws IOException;

	//@Override
	public abstract void outputSkat(PlayingCard[] skat) throws IOException;

	//@Override
	public abstract void pass(IPlayer spieler);

	//@Override
	public abstract void gameOver();

	//@Override
	public abstract void showEvaluation(boolean isWon);

	//@Override
	public abstract void hinterhandVsWinner(IPlayer winner);

	//@Override
	public abstract void mittelhandVsVorhand();

	//@Override
	public abstract void blankLine();

	//@Override
	public abstract void passGame();

	//@Override
	public abstract void gameBegins();

	//@Override
	public abstract void anotherCard();

	//@Override
	public abstract void augen(int augen);

	//@Override
	public abstract void points(int punkte);

	//@Override
	public abstract void showDeclarer();

	//@Override
	public abstract void trump();

	//@Override
	public abstract void newGame();

	//@Override
	public abstract String readInput();

	//@Override
	public abstract void spieltKarte(IPlayer player, PlayingCard card);

	//@Override
	public abstract int reizlimitFestlegen();

	//@Override
	public abstract boolean biddingAgent();

	//@Override
	public abstract boolean quitGame();

	//@Override
	public abstract void outputPoints();

	//@Override
	public abstract void deleteTable();

	//@Override
	public abstract String getDeckSelection();

	//@Override
	public abstract String askForSixSkat();

	//@Override
	public abstract void openGameTable();

	//@Override
	public abstract void statistics();

	//@Override
	public abstract void cleanUpGUI();

	//@Override
	public abstract void showPosition();

}