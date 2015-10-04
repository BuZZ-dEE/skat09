package test;

import java.io.IOException;
import java.util.Observable;

import main.IController;
import main.Table;
import main.gamevariety.IGameVariety;
import main.player.IPlayer;
import main.playingcard.PlayingCard;
import main.ui.IOutput;


public class ControllerStub implements IController{

	Table tisch;
	IOutput ausgabe;
	
	public ControllerStub(Table tisch, IOutput ausgabe) {
		
		this.tisch = tisch;
		this.ausgabe = ausgabe;
	}
	
	
	public void declarerActions() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	
	public void evaluation() {
		// TODO Auto-generated method stub
		
	}

	
	public void prepareGame() {
		// TODO Auto-generated method stub
		
	}

	
	public void passGame() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void decideRaeuberGame() {
		// TODO Auto-generated method stub
		
	}

	
	public void setFlags(IPlayer alleinspieler, IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public IOutput getOutput() {
		
		return ausgabe;
	}

	
	public void coordinateBidding() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void leadGame() throws NullPointerException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void normalGamePlay() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void ramschen() throws NullPointerException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	public boolean reizagent(IPlayer spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public IPlayer bidding1(IPlayer spieler1, IPlayer spieler2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IPlayer bidding2(IPlayer spieler1, IPlayer spieler2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean bidOrBiddingAgent(IPlayer spieler, int reizwert, boolean sagen) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void release() {
		// TODO Auto-generated method stub
		
	}

	
	public void initializeSmartPlayer() {
		// TODO Auto-generated method stub
		
	}

	
	public void assignSkatCardsToOwner() {
		// TODO Auto-generated method stub
		
	}

	
	public void play() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public boolean quitGame() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void playIntSkat() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void playRaeuberskat() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void playRamschBock() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void outputTrickEvaluation(PlayingCard[] gespielteKarten, IPlayer gewinner) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Observable tisch, Object gespielteKarten) {
		// TODO Auto-generated method stub
	}

	
	public void selectAdversary() {
		// TODO Auto-generated method stub
		
	}

	
	public void chooseSkatVariant() {
		// TODO Auto-generated method stub
		
	}

	
	public void selectSkatDeck() {
		// TODO Auto-generated method stub
		
	}

	
	public void waiting() {
		// TODO Auto-generated method stub
		
	}


	public void namesComparison() {
		// TODO Auto-generated method stub
		
	}

}
