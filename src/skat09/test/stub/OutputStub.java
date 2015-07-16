package skat09.test.stub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;


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
	
	public void alleinspieler() {
		
	}

	
	public void andereKarte() {
		System.out.println("Bitte spiele eine andere Karte!");
		
	}

	
	public void augen(int augen) {
		// TODO Auto-generated method stub
		
	}

	
	public void auswertung(boolean gewonnen) {
		
	}

	
	public void blattAusgeben(ISpieler spieler) throws IOException {
		
	}

	//Drueckt immer die erste Karte des Blattes
	
	public int druecken(ArrayList<Spielkarte> blatt, int nummer) {
		
		return 0; 
	}

	
	public String einlesen() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void falscheEingabe() {
		// TODO Auto-generated method stub
		
	}

	
	public Farbspiel farbe() {
		
		Farbspiel farbspiel = new Farbspiel(Farbe.PIK);
		return farbspiel;
	}

	
	public String frageSechserskat() {
		// TODO Auto-generated method stub
		return "j";
	}

	
	public String frageVariante() {
		return variante;
	}

	
	public String gegner(int nummer) {
		
		return gegner;
	}

	
	public String getBlattwahl() {
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

	
	public boolean handspiel() {

		return false;
	}

	
	public void hauptfensterOeffnen() {
		
	}

	
	public void hhVSgewinner(ISpieler gewinner) {
		
	}

	
	public boolean hoeren(int wert) {
		
		boolean ergebnis = false;
		
		if(wert <= 20) {
			
			ergebnis = true;
		}
		return ergebnis;
	}

	
	public void leerzeile() {
		
	}

	
	public IController makeController() {
		return null;
	}

	
	public void makeView() {
		
	}

	
	public void mhVSvh() {
		
	}

	
	public String name() {
		
		return "Knut";
	}

	
	public void neuesSpiel() {
		
	}

	
	public boolean ouvert() {
		
		return true;
	}

	
	public void punkte(int punkte) {
		
		this.punkte = punkte;
	}

	
	public void punkteAusgeben() {
		
	}

	
	public boolean reizAgent() {
		
		return false;
	}

	
	public int reizlimitFestlegen() {
		
		return 23;
	}

	
	public void release() {
		
	}

	
	public boolean sagen(int reizWert) {
		
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


	public void skatAusgeben(Spielkarte[] skat) throws IOException {
		
	}

	
	public void spiel() {
		// TODO Auto-generated method stub
		
	}


	public ISpielart spielAnsagen() {
		
		return new Grandspiel();
	}


	public boolean spielBeenden() {
		// TODO Auto-generated method stub
		return true;
	}


	public void spielBeendet() {
		
	}

	
	public void spielBeginnt() {
		// TODO Auto-generated method stub
		
	}


	public void spielEinpassen() {
		// TODO Auto-generated method stub
		
	}


	public Spielkarte spieleKarte(Spielkarte[] gespielteKarten, ISpieler spieler)
			throws IOException {
		
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		return karte;
	}

	
	public void spieltKarte(ISpieler spieler, Spielkarte karte) {
		
		gespielteKartenZahl++;
	}

	
	public void stichGewonnen(ISpieler spieler) {
		
	}


	public void tischLoeschen() {
		
	}

	
	public void trumpf() {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void weg(ISpieler spieler) {
		// TODO Auto-generated method stub
		
	}


	public void guiAufraumen() {

		
	}


	public void positionAnzeigen() {
		// TODO Auto-generated method stub
		
	}

	public void statistik() {
		// TODO Auto-generated method stub
		
	}

}
