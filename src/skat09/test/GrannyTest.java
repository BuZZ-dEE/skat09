package skat09.test;

import java.util.ArrayList;
//import java.util.Random;

import org.junit.Test;

import skat09.spielart.GrandGame;
import skat09.spieler.Granny;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;
import static org.junit.Assert.*;

//import de.uniluebeck.skat09.*;
public class GrannyTest {

	private Granny oma = new Granny("Franz");
	PlayingCard spielkarte1 = new PlayingCard(Farbe.KARO, Value.SIEBEN);
	PlayingCard spielkarte2 = new PlayingCard(Farbe.KARO, Value.ACHT);
	PlayingCard spielkarte3 = new PlayingCard(Farbe.KARO, Value.NEUN);
	PlayingCard spielkarte4 = new PlayingCard(Farbe.KARO, Value.ZEHN);
	PlayingCard spielkarte5 = new PlayingCard(Farbe.KARO, Value.BUBE);
	PlayingCard spielkarte6 = new PlayingCard(Farbe.KARO, Value.DAME);
	PlayingCard spielkarte7 = new PlayingCard(Farbe.KARO, Value.KOENIG);
	PlayingCard spielkarte8 = new PlayingCard(Farbe.KARO, Value.ASS);
	PlayingCard spielkarte9 = new PlayingCard(Farbe.HERZ, Value.SIEBEN);
	PlayingCard spielkarte10 = new PlayingCard(Farbe.HERZ, Value.ACHT);

	ArrayList<PlayingCard> omablatt = new ArrayList<PlayingCard>();
	
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
		
		PlayingCard[] temp = new PlayingCard[2];
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