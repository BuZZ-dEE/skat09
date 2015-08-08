package skat09.ui;

import java.io.IOException;
import java.util.ArrayList;

import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


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
	public abstract boolean sagen(int reizWert);

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
	public abstract void weg(IPlayer spieler);

	//@Override
	public abstract void gameOver();

	//@Override
	public abstract void auswertung(boolean gewonnen);

	//@Override
	public abstract void hhVSgewinner(IPlayer gewinner);

	//@Override
	public abstract void mhVSvh();

	//@Override
	public abstract void leerzeile();

	//@Override
	public abstract void spielEinpassen();

	//@Override
	public abstract void gameBegins();

	//@Override
	public abstract void anotherCard();

	//@Override
	public abstract void augen(int augen);

	//@Override
	public abstract void points(int punkte);

	//@Override
	public abstract void alleinspieler();

	//@Override
	public abstract void trump();

	//@Override
	public abstract void newGame();

	//@Override
	public abstract String readInput();

	//@Override
	public abstract void spieltKarte(IPlayer spieler, PlayingCard karte);

	//@Override
	public abstract int reizlimitFestlegen();

	//@Override
	public abstract boolean reizAgent();

	//@Override
	public abstract boolean quitGame();

	//@Override
	public abstract void outputPoints();

	//@Override
	public abstract void deleteTable();

	//@Override
	public abstract String getBlattwahl();

	//@Override
	public abstract String askForSixSkat();

	//@Override
	public abstract void hauptfensterOeffnen();

	//@Override
	public abstract void statistik();

	//@Override
	public abstract void guiAufraumen();

	//@Override
	public abstract void showPosition();

}