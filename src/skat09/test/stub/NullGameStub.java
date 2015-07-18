package skat09.test.stub;

import java.util.ArrayList;

import skat09.gamevariety.GameVariety;
import skat09.spielkarte.PlayingCard;
import skat09.test.interfaces.INullGame;


public class NullGameStub extends GameVariety implements INullGame {

	private int aufrufe = 0;
	
	@Override
	public boolean gespielteKartePruefen(ArrayList<PlayingCard> blatt,
			PlayingCard[] gespielteKarten, PlayingCard zuPruefendeKarte) {
		
		boolean ergebnis = false;
		aufrufe++;
		if(aufrufe >= 2) {
			
			ergebnis = true;
		}
		return ergebnis;
	}

	@Override
	public PlayingCard hoehereKarte(PlayingCard karte1, PlayingCard karte2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int karteBewerten(PlayingCard karte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PlayingCard sortiereKarte(PlayingCard karte1, PlayingCard karte2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
