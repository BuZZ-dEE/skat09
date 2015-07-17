package skat09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.GrandGame;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;



public class SpielartTest {

	//
	// benoetigte Datenfelder
	//

	Spielkarte spielkarte1;
	Spielkarte spielkarte2;
	Spielkarte spielkarte3;
	Spielkarte spielkarte4;
	Spielkarte spielkarte5;
	Spielkarte spielkarte6;
	Spielkarte spielkarte7;
	Spielkarte spielkarte8;
	Spielkarte spielkarte9;
	Spielkarte spielkarte10;
	Spielkarte spielkarte11;
	Spielkarte spielkarte12;
	Spielkarte spielkarte13;
	GrandGame spiel = new GrandGame();

	// Bei diesem Test enthaelt das Blatt des Spielers noch einige Karten.
	// Wir gehen davon aus, dass die zu spielende Karte Kreuz/Zehn ist.
	// Es wurde bisher keine Karte gespielt.
	// 

	@Before
	public void setUp() {

		spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		spielkarte5 = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		spielkarte7 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		spielkarte8 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		spielkarte9 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		spielkarte10 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		spielkarte11 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		spielkarte12 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		spielkarte13 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
	}
	
	@Test
	public void farbeBedienenTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte9);
		blatt.add(spielkarte11);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		blatt.add(spielkarte2);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = spielkarte13;
		
		assertTrue(spiel.farbeBedienen(blatt, gespielteKarten, spielkarte2));
	}
	
	@Test
	public void farbeBedienenTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte9);
		blatt.add(spielkarte11);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		blatt.add(spielkarte2);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = spielkarte13;
		
		assertFalse(spiel.farbeBedienen(blatt, gespielteKarten, spielkarte10));
	}
	
	@Test
	public void hoehererBubeTest1() {
		
		assertEquals(spielkarte11, spiel.hoehererBube(spielkarte11, spielkarte9));
	}
	
	@Test
	public void hoehererBubeTest2() {
		
		assertEquals(spielkarte10, spiel.hoehererBube(spielkarte8, spielkarte10));
	}
	
	@Test
	public void hoehererBubeTest3() {
		
		assertEquals(spielkarte9, spiel.hoehererBube(spielkarte8, spielkarte9));
	}
	
	@Test
	public void hoehererBubeTest4() {
		
		assertEquals(spielkarte8, spiel.hoehererBube(spielkarte8, spielkarte1));
	}
	
	@Test
	public void hoehereFarbeTest1() {
		
		assertEquals(spielkarte11, spiel.hoehereFarbe(spielkarte11, spielkarte12));
	}
	
	@Test
	public void hoehereFarbeTest2() {
		
		assertEquals(spielkarte11, spiel.hoehereFarbe(spielkarte12, spielkarte11));
	}
	
	@Test
	public void hoehereFarbeTest3() {
		
		assertEquals(spielkarte3, spiel.hoehereFarbe(spielkarte3, spielkarte2));
	}
	
	@Test
	public void hoehereFarbeTest4() {
		
		assertEquals(spielkarte3, spiel.hoehereFarbe(spielkarte1, spielkarte3));
	}
	
	@Test
	public void hoehereFarbeTest5() {
		
		assertEquals(spielkarte13, spiel.hoehereFarbe(spielkarte13, spielkarte9));
	}
	
	@Test
	public void hoehereFarbeTest6() {
		
		assertEquals(spielkarte7, spiel.hoehereFarbe(spielkarte1, spielkarte7));
	}
	
	@Test
	public void hoehereKarteEinBubeTest1() {
		
		assertEquals(spielkarte9, 
				spiel.hoehereKarteEinBube(spielkarte9, spielkarte12));
	}
	
	@Test
	public void hoehereKarteEinBubeTest2() {
		
		assertEquals(spielkarte7, 
				spiel.hoehereKarteEinBube(spielkarte13, spielkarte7));
	}
	
	@Test
	public void hoehereKarteFarbeTest() {
		
		assertEquals(spielkarte5, spiel.hoehereKarteFarbe(spielkarte2, spielkarte5));
	}
	
	@Test
	public void hoehereKarteFarbeTest2() {
		
		assertEquals(spielkarte12, spiel.hoehereKarteFarbe(spielkarte12, spielkarte13));
	}

	@Test
	public void testKarteBewerten() {
		assertEquals(7, spiel.karteBewerten(spielkarte1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(8, spiel.karteBewerten(spielkarte2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(9, spiel.karteBewerten(spielkarte3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(10, spiel.karteBewerten(spielkarte4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(11, spiel.karteBewerten(spielkarte5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(12, spiel.karteBewerten(spielkarte6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(13, spiel.karteBewerten(spielkarte7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(14, spiel.karteBewerten(spielkarte8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(15, spiel.karteBewerten(spielkarte9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(16, spiel.karteBewerten(spielkarte10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(17, spiel.karteBewerten(spielkarte11));
	}
	
	@Test
	public void testKarteBewerten12() {
		assertEquals(6, spiel.karteBewerten(spielkarte12));
	}
	
	@Test
	public void testBubeBewerten1() {
		
		assertEquals(14, spiel.bubeBewerten(spielkarte8));
	}
	
	@Test
	public void testBubeBewerten2() {
		
		assertEquals(15, spiel.bubeBewerten(spielkarte9));
	}
	
	@Test
	public void testBubeBewerten3() {
		
		assertEquals(16, spiel.bubeBewerten(spielkarte10));
	}

	@Test
	public void testBubeBewerten4() {
		
		assertEquals(17, spiel.bubeBewerten(spielkarte11));
	}
}
