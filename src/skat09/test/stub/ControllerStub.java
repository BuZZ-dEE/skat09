package skat09.test.stub;

import java.io.IOException;
import java.util.Observable;

import skat09.Table;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


public class ControllerStub implements IController{

	Table tisch;
	IOutput ausgabe;
	
	public ControllerStub(Table tisch, IOutput ausgabe) {
		
		this.tisch = tisch;
		this.ausgabe = ausgabe;
	}
	
	
	public void alleinspielerAktionen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void logIn() {
		// TODO Auto-generated method stub
		
	}

	
	public void aufrauemen() {
		// TODO Auto-generated method stub
		
	}

	
	public void auswertung() {
		// TODO Auto-generated method stub
		
	}

	
	public void prepareGame() {
		// TODO Auto-generated method stub
		
	}

	
	public void einpassen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void entscheideraeuberspiel() {
		// TODO Auto-generated method stub
		
	}

	
	public void flagsSetzen(IPlayer alleinspieler, IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public IOutput getOutput() {
		
		return ausgabe;
	}

	
	public void coordinateBidding() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void leiteSpiel() throws NullPointerException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void normalerSpielverlauf() throws IOException {
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

	
	public boolean reizenOderReizagent(IPlayer spieler, int reizwert, boolean sagen) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void release() {
		// TODO Auto-generated method stub
		
	}

	
	public void schlauerSpielerInit() {
		// TODO Auto-generated method stub
		
	}

	
	public void skatkartenBesitzergeben() {
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

	
	public void stichAuswertung(PlayingCard[] gespielteKarten, IPlayer gewinner) {
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

	
	public void warte() {
		// TODO Auto-generated method stub
		
	}


	public void namesComparison() {
		// TODO Auto-generated method stub
		
	}

}
