package skat09.test.stub;

import java.util.ArrayList;

import skat09.spielart.Spielart;
import skat09.spielkarte.Spielkarte;
import skat09.test.interfaces.INullGame;


public class NullspielStub extends Spielart implements INullGame {

	private int aufrufe = 0;
	
	@Override
	public boolean gespielteKartePruefen(ArrayList<Spielkarte> blatt,
			Spielkarte[] gespielteKarten, Spielkarte zuPruefendeKarte) {
		
		boolean ergebnis = false;
		aufrufe++;
		if(aufrufe >= 2) {
			
			ergebnis = true;
		}
		return ergebnis;
	}

	@Override
	public Spielkarte hoehereKarte(Spielkarte karte1, Spielkarte karte2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int karteBewerten(Spielkarte karte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Spielkarte sortiereKarte(Spielkarte karte1, Spielkarte karte2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
