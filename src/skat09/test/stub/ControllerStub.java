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

	
	public void anmelden() {
		// TODO Auto-generated method stub
		
	}

	
	public void aufrauemen() {
		// TODO Auto-generated method stub
		
	}

	
	public void auswertung() {
		// TODO Auto-generated method stub
		
	}

	
	public void bereiteSpielvor() {
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

	
	public void leiteReizen() throws IOException {
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

	
	public IPlayer reizen1(IPlayer spieler1, IPlayer spieler2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IPlayer reizen2(IPlayer spieler1, IPlayer spieler2) {
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

	
	public boolean spielBeenden() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void spielIntSkat() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void spielRaeuberskat() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void spielRamschBock() throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void stichAuswertung(PlayingCard[] gespielteKarten, IPlayer gewinner) {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Observable tisch, Object gespielteKarten) {
		// TODO Auto-generated method stub
	}

	
	public void waehleGegner() {
		// TODO Auto-generated method stub
		
	}

	
	public void waehleSkatart() {
		// TODO Auto-generated method stub
		
	}

	
	public void waehleSkatblatt() {
		// TODO Auto-generated method stub
		
	}

	
	public void warte() {
		// TODO Auto-generated method stub
		
	}


	public void namenVergleich() {
		// TODO Auto-generated method stub
		
	}

}
