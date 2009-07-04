package skat09.test.stub;

import java.io.IOException;
import java.util.Observable;

import skat09.Tisch;
import skat09.spielkarte.Spielkarte;
import skat09.test.interfaces.IAusgabe;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;


public class ControllerStub implements IController{

	Tisch tisch;
	IAusgabe ausgabe;
	
	public ControllerStub(Tisch tisch, IAusgabe ausgabe) {
		
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

	
	public void flagsSetzen(ISpieler alleinspieler, ISpielart spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public IAusgabe getAusgabe() {
		
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

	
	public boolean reizagent(ISpieler spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public ISpieler reizen1(ISpieler spieler1, ISpieler spieler2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ISpieler reizen2(ISpieler spieler1, ISpieler spieler2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean reizenOderReizagent(ISpieler spieler, int reizwert, boolean sagen) {
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

	
	public void spiel() throws IOException {
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

	
	public void stichAuswertung(Spielkarte[] gespielteKarten, ISpieler gewinner) {
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
