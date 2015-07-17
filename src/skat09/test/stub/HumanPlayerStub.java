package skat09.test.stub;

import java.util.ArrayList;

import skat09.spielart.SuitGame;
import skat09.spieler.Position;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.IPlayer;


public class HumanPlayerStub implements IPlayer, IHumanPlayer {

	//
	//Datenfelder
	//
	
	private String name;
	private boolean istAlleinspieler;
	private ArrayList<Spielkarte> blatt;
	Position position;
	
	public HumanPlayerStub(String name) {
		
		this.name = name;
		this.istAlleinspieler = false;
		this.blatt = new ArrayList<Spielkarte>();
	}
	
	public boolean agent() {
		
		boolean ergebnis = true;
		return ergebnis;
	}

	public void blattSortieren(ISpielart spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public Spielkarte[] druecken(Spielkarte[] skat) {
		
		return skat;
	}


	public boolean equals(IPlayer spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public SuitGame farbe() {
		
		return new SuitGame(Farbe.KARO);
	}

	
	public ArrayList<Spielkarte> getBlatt() {
		
		return blatt;
	}

	
	public boolean getIstAlleinspieler() {
		
		return istAlleinspieler;
	}

	
	public String getName() {
		
		return name;
	}

	
	public Position getPosition() {
		
		return position;
	}

	
	public ArrayList<Integer> getSpiele() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Spielkarte> getStiche() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean handspiel() {
		
		return false;
	}

	
	public boolean hoeren(int reizwert) {
	
		boolean ergebnis = false;
		
		if (reizwert <= 22) {
			ergebnis = true;
		}
		return ergebnis;
	}

	
	public ArrayList<Integer> neuerEintrag(int punkte) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean ouvert() {
	
		return true;
	}

	
	public int reizlimitFestlegen() {
		
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

	
	public void setBlatt(ArrayList<Spielkarte> blatt) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIstAlleinspieler(boolean istAlleinspieler) {
	
		this.istAlleinspieler = istAlleinspieler;
	}

	
	public void setPosition(Position position) {
		
		this.position = position;
	}

	
	public void setSpielart(ISpielart spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public void setStiche(ArrayList<Spielkarte> stiche) {
		// TODO Auto-generated method stub
		
	}

	
	public ISpielart spielAnsagen() {
		
		return new SuitGame(null);
	}

	
	public Spielkarte spieleKarte(Spielkarte[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void stichHinzufuegen(Spielkarte[] stich) {
		// TODO Auto-generated method stub
		
	}

	public void bubeneinordnen() {
		// TODO Auto-generated method stub
		
	}

	public void farbeneinordnen() {
		// TODO Auto-generated method stub
		
	}

	public void gespielteKartenHinzufuegen(Spielkarte[] gespielteKarten) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Spielkarte> getAllegespieltenkarten() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPlayer getMitspieler() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Spielkarte> getRestblatt() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISpielart getSpielart() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAlleGespieltenKarten(ArrayList<Spielkarte> karten) {
		// TODO Auto-generated method stub
		
	}

	public void setDeck(ArrayList<Spielkarte> deck) {
		// TODO Auto-generated method stub
		
	}

	public void setMitspieler(IPlayer mitspieler) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setSkat(ArrayList<Spielkarte> skat) {
		// TODO Auto-generated method stub
		
	}

	public void setSpiele(ArrayList<Integer> spiele) {
		// TODO Auto-generated method stub
		
	}

	public void setTruempfe(Spielkarte[] truempfe) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Spielkarte> spielbareKarten(Spielkarte[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public Spielkarte[] spitzenEinordnen() {
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

	public Spielkarte zufaelligErlaubteKarteSpielen(Spielkarte[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHandspiele() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHandspiele(int handspiele) {
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
