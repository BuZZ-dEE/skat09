package skat09.test;

import java.util.ArrayList;
//import java.util.Random;

import org.junit.Test;

import skat09.spielart.GrandGame;
import skat09.spieler.Granny;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import static org.junit.Assert.*;

//import de.uniluebeck.skat09.*;
public class GrannyTest {

	private Granny oma = new Granny("Franz");
	Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
	Spielkarte spielkarte2 = new Spielkarte(Farbe.KARO, Wert.ACHT);
	Spielkarte spielkarte3 = new Spielkarte(Farbe.KARO, Wert.NEUN);
	Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
	Spielkarte spielkarte5 = new Spielkarte(Farbe.KARO, Wert.BUBE);
	Spielkarte spielkarte6 = new Spielkarte(Farbe.KARO, Wert.DAME);
	Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.KOENIG);
	Spielkarte spielkarte8 = new Spielkarte(Farbe.KARO, Wert.ASS);
	Spielkarte spielkarte9 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
	Spielkarte spielkarte10 = new Spielkarte(Farbe.HERZ, Wert.ACHT);

	ArrayList<Spielkarte> omablatt = new ArrayList<Spielkarte>();
	
	public GrannyTest(){
		omablatt.add(spielkarte1);
		omablatt.add(spielkarte2);
		omablatt.add(spielkarte3);
		omablatt.add(spielkarte4);
		omablatt.add(spielkarte5);
		omablatt.add(spielkarte6);
		omablatt.add(spielkarte7);
		omablatt.add(spielkarte8);
		omablatt.add(spielkarte9);
		omablatt.add(spielkarte10);
		oma.setBlatt(omablatt);
		
	}


	/**
	 * Ueberprueft, ob die Oma in Richtiger Reihenfolge
	 * die richtige Karte wirft
	 */
	@Test
	public void testeGibKarte(){
		boolean temp = true;
	for (int i=0; i<10; i++){
		if (!omablatt.get(omablatt.size()-1).equals(oma.spieleKarte(null))){
			temp = false;
		}
	}
		
		assertTrue(temp);
	}
	
	@Test
	public void testsagen(){
		
		assertEquals(false,(oma.sagen(18)));
	}
	
	@Test
	public void testhoeren(){
		
		assertFalse(oma.hoeren(18));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testdruecken(){
		
		Spielkarte[] temp = new Spielkarte[2];
		temp[0] = oma.getBlatt().get(1);
		temp[1] = oma.getBlatt().get(2);
		assertEquals(null,oma.druecken(temp));
		
	}
	
	@Test
	public void testhandspiel(){
		
		assertTrue(oma.handspiel());
	}
	
	@Test
	public void testouvert(){
		assertFalse(oma.ouvert());
	}
	
	@Test
	public void testschneider(){
		assertFalse(oma.schneider());
	}
	
	@Test
	public void testschwarz(){
		
		assertFalse(oma.schwarz());
	}
	
	@Test
	public void testSpielAnsagen(){
		boolean test = false;
		if (oma.spielAnsagen() instanceof GrandGame) {
			test = true;
		}
		assertEquals(true, test);
	}
	
	@Test
	public void testFarbe(){
		
		assertEquals(null, oma.farbe());
	}
}