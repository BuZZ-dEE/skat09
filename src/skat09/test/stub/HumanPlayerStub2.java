package skat09.test.stub;

import java.util.ArrayList;

import skat09.gamevariety.SuitGame;
import skat09.player.Position;
import skat09.spielkarte.Suit;
import skat09.spielkarte.PlayingCard;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


public class HumanPlayerStub2 implements IPlayer, IHumanPlayer {

	
	private String name;
	private boolean istAlleinspieler;
	private ArrayList<PlayingCard> blatt;
	
	public HumanPlayerStub2(String name) {
		
		this.name = name;
		this.istAlleinspieler = false;
		this.blatt = new ArrayList<PlayingCard>();
	}
	
	public boolean agent() {
		
		boolean ergebnis = true;
		return ergebnis;
	}

	public void blattSortieren(IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public PlayingCard[] druecken(PlayingCard[] skat) {
		
		return skat;
	}


	public boolean equals(IPlayer spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public SuitGame farbe() {
		
		return new SuitGame(Suit.KARO);
	}

	
	public ArrayList<PlayingCard> getBlatt() {
		
		return blatt;
	}

	
	public boolean getIstAlleinspieler() {
		
		return istAlleinspieler;
	}

	
	public String getName() {
		
		return name;
	}

	
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Integer> getSpiele() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<PlayingCard> getStiche() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean handspiel() {
		
		return false;
	}

	
	public boolean hoeren(int reizwert) {
	
		boolean ergebnis = false;
		
		if (reizwert <= 18) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean sagen(int alterWert) {
		
		boolean ergebnis = false;
		
		if (alterWert <= 18) {
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

	
	public void setBlatt(ArrayList<PlayingCard> blatt) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIstAlleinspieler(boolean istAlleinspieler) {
	
		this.istAlleinspieler = istAlleinspieler;
	}

	
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSpielart(IGameVariety spielart) {
		// TODO Auto-generated method stub
		
	}

	
	public void setStiche(ArrayList<PlayingCard> stiche) {
		// TODO Auto-generated method stub
		
	}

	
	public IGameVariety spielAnsagen() {
		
		return new SuitGame(null);
	}

	
	public PlayingCard spieleKarte(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void stichHinzufuegen(PlayingCard[] stich) {
		// TODO Auto-generated method stub
		
	}

	public void bubeneinordnen() {
		// TODO Auto-generated method stub
		
	}

	public void farbeneinordnen() {
		// TODO Auto-generated method stub
		
	}

	public void gespielteKartenHinzufuegen(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PlayingCard> getAllegespieltenkarten() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPlayer getMitspieler() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayingCard> getRestblatt() {
		// TODO Auto-generated method stub
		return null;
	}

	public IGameVariety getSpielart() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAlleGespieltenKarten(ArrayList<PlayingCard> karten) {
		// TODO Auto-generated method stub
		
	}

	public void setDeck(ArrayList<PlayingCard> deck) {
		// TODO Auto-generated method stub
		
	}

	public void setMitspieler(IPlayer mitspieler) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setSkat(ArrayList<PlayingCard> skat) {
		// TODO Auto-generated method stub
		
	}

	public void setSpiele(ArrayList<Integer> spiele) {
		// TODO Auto-generated method stub
		
	}

	public void setTruempfe(PlayingCard[] truempfe) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PlayingCard> spielbareKarten(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayingCard[] spitzenEinordnen() {
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

	public PlayingCard zufaelligErlaubteKarteSpielen(PlayingCard[] gespielteKarten) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHandspiele() {
		return 0;
	}

	public void setHandspiele(int handspiele) {
		
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
