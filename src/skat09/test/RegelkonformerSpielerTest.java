package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.Farbspiel;
import skat09.spielart.Nullspiel;
import skat09.spieler.RegelkonformerSpieler;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;


public class RegelkonformerSpielerTest {
	
	ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
	Spielkarte spielkarte1;
	Spielkarte spielkarte2;
	Spielkarte spielkarte3;
	Spielkarte spielkarte4;
	RegelkonformerSpieler spieler = new RegelkonformerSpieler("Mimi");
	Spielkarte[] gespielteKarten = new Spielkarte[3];
	Farbspiel spiel = new Farbspiel(Farbe.HERZ);
	
	@Before
	public void setUp() {
		spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		gespielteKarten[0] = spielkarte1;
		
		spieler.setBlatt(blatt);
		spieler.setSpielart(spiel);
	}

//	@Test
//	public void testSpieleKarte() {
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte2() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		assertEquals(spielkarte3, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte3() {
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte4() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
//	}
	
	//Der regelkonforme Spieler spielt eine zufaellige Karte
	//aus seinem Blatt. Es wird getestet, ob die gespielte
	//Karte vorher im Blatt enthalten war und daraufhin auch 
	//entfernt wurde.
	@Test
	public void testSpieleKarte() {
	
		boolean testErfolgreich = false;
		
		blatt.clear();
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ACHT));
		ArrayList<Spielkarte> altesBlatt = new ArrayList<Spielkarte>();
		altesBlatt = (ArrayList<Spielkarte>) blatt.clone();
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN); 
		Spielkarte gespielteKarte = new Spielkarte(null,null);
		gespielteKarte = spieler.spieleKarte(gespielteKarten);
		blatt = spieler.getBlatt();
		
		if(altesBlatt.contains(gespielteKarte)) {
			
			if (!blatt.contains(gespielteKarte)) {
			
				testErfolgreich = true;
			}
		}
		assertTrue(testErfolgreich);
	}
	
	@Test
	public void testRegelkonformerSpieler() {
		assertEquals("Hans", new RegelkonformerSpieler("Hans").getName());
	}


	@Test
	public void drueckenTest() {
		Spielkarte[] skat = new Spielkarte[2];
		assertArrayEquals(null, spieler.druecken(skat));
	}
	
	@Test
	public void handspielTest() {
		assertTrue(spieler.handspiel());
	}
	
	@Test
	public void schneiderTest() {
		assertFalse(spieler.schneider());
	}
	
	@Test
	public void schwarzTest() {
		assertFalse(spieler.schwarz());
	}
	
	@Test
	public void ouvertTest() {
		assertFalse(spieler.ouvert());
	}
	
	@Test
	public void spielAnsagenTest() {
		boolean test = false;
		if (spieler.spielAnsagen() instanceof Nullspiel) {
			test = true;
		}
		assertTrue(test);
	}
	
	@Test
	public void hoerenTest() {
		assertTrue(spieler.hoeren(0));
	}
	
	@Test
	public void hoerenTest2() {
		assertTrue(spieler.hoeren(18));
	}
	
	@Test
	public void hoerenTest3() {
		assertTrue(spieler.hoeren(20));
	}
	
	@Test
	public void hoerenTest4() {
		assertFalse(spieler.hoeren(35));
	}
	
	@Test
	public void sagenTest() {
		assertTrue(spieler.sagen(0));
	}
	
	@Test
	public void sagenTest2() {
		assertTrue(spieler.sagen(18));
	}
	
	@Test
	public void farbeTest() {
		assertEquals(null, spieler.farbe());
	}
}
