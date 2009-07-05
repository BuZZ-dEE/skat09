package skat09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spieler.Oma;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;
import skat09.test.stub.NullspielStub2;


public class SpielerTest{

	private ISpieler spieler;
	ArrayList<Spielkarte> stiche = new ArrayList<Spielkarte>();
	
	
	@Before
	public void setUp(){
		String name_bsp = "Rainer Hohn";
		spieler = new Oma(name_bsp);
		stiche.clear();
		spieler.setStiche(stiche);
		spieler.getSpiele().clear();
	}
	
	@Test
	public void testSpieler() {
		assertEquals("Rainer Hohn",spieler.getName());
	}

	@Test
	public void stichHinzufuegenTest() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ,Wert.KOENIG);
		Spielkarte karte2 =	new Spielkarte(Farbe.KARO,Wert.SIEBEN);
		Spielkarte karte3 =	new Spielkarte(Farbe.KREUZ,Wert.ZEHN);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(3, spieler.getStiche().size());
		
	}
	
	@Test
	public void stichHinzufuegenTest2() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ,Wert.KOENIG);
		Spielkarte karte2 =	new Spielkarte(Farbe.KARO,Wert.SIEBEN);
		Spielkarte karte3 =	new Spielkarte(Farbe.KREUZ,Wert.ZEHN);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte1, spieler.getStiche().get(0));
		
	}
	
	@Test
	public void stichHinzufuegenTest3() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ,Wert.KOENIG);
		Spielkarte karte2 =	new Spielkarte(Farbe.KARO,Wert.SIEBEN);
		Spielkarte karte3 =	new Spielkarte(Farbe.KREUZ,Wert.ZEHN);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte2, spieler.getStiche().get(1));
	}
	
	@Test
	public void stichHinzufuegenTest4() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ,Wert.KOENIG);
		Spielkarte karte2 =	new Spielkarte(Farbe.KARO,Wert.SIEBEN);
		Spielkarte karte3 =	new Spielkarte(Farbe.KREUZ,Wert.ZEHN);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte3, spieler.getStiche().get(2));
		
	}
	
	@Test
	public void neuerEintragTest() {
		ArrayList<Integer> spiele;
		spiele=spieler.neuerEintrag(18);
		assertEquals(1, spiele.size());
	}
	
	@Test
	public void neuerEintragTest2() {
		ArrayList<Integer> spiele;
		spiele=spieler.neuerEintrag(18);
		//assertEquals(18, spiele.get(0));
	}
	
	@Test
	public void agentTest() {
		assertFalse(spieler.agent());
	}
	
	@Test
	public void reizlimitTest() {
		assertEquals(0, spieler.reizlimitFestlegen());
	}
	
	@Test
	public void test1sortiereBlatt(){
		final ISpielart spiel = new Grandspiel();
		ISpieler spieler = new Oma("Hallo");
		
		spieler.setSpielart(spiel);
		ArrayList<Spielkarte> blattest = new ArrayList<Spielkarte>();
		blattest.add(new Spielkarte(Farbe.HERZ,Wert.KOENIG));
		blattest.add(new Spielkarte(Farbe.KARO,Wert.SIEBEN));
		blattest.add(new Spielkarte(Farbe.KREUZ,Wert.ZEHN));
		blattest.add(new Spielkarte(Farbe.PIK,Wert.ACHT));
		blattest.add(new Spielkarte(Farbe.PIK,Wert.KOENIG));
		blattest.add(new Spielkarte(Farbe.HERZ,Wert.ASS));
		blattest.add(new Spielkarte(Farbe.HERZ,Wert.DAME));
		blattest.add(new Spielkarte(Farbe.PIK,Wert.ZEHN));
		blattest.add(new Spielkarte(Farbe.PIK,Wert.ASS));
		blattest.add(new Spielkarte(Farbe.KREUZ,Wert.BUBE));
		
		spieler.setBlatt(blattest);
		
		ArrayList<Spielkarte> testproof = new ArrayList<Spielkarte>();
		
		testproof.add(new Spielkarte(Farbe.KARO,Wert.SIEBEN));
		testproof.add(new Spielkarte(Farbe.HERZ,Wert.DAME));
		testproof.add(new Spielkarte(Farbe.HERZ,Wert.KOENIG));
		testproof.add(new Spielkarte(Farbe.HERZ,Wert.ASS));
		testproof.add(new Spielkarte(Farbe.PIK,Wert.ACHT));
		testproof.add(new Spielkarte(Farbe.PIK,Wert.KOENIG));
		testproof.add(new Spielkarte(Farbe.PIK,Wert.ZEHN));
		testproof.add(new Spielkarte(Farbe.PIK,Wert.ASS));
		testproof.add(new Spielkarte(Farbe.KREUZ,Wert.ZEHN));
		testproof.add(new Spielkarte(Farbe.KREUZ,Wert.BUBE));
		
		spieler.blattSortieren(spiel);
		boolean vergleiche = true;
		
		for(int i =0; i<10; i++){
			if (!testproof.get(i).equals(spieler.getBlatt().get(i))){
				vergleiche = false;
			}
		}
		assertTrue(vergleiche);
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest() {
		
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.DAME);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[2] = new Spielkarte(Farbe.PIK, Wert.ASS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[0], spieler.getAllegespieltenkarten().get(0));
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest2() {
		
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.DAME);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[2] = new Spielkarte(Farbe.PIK, Wert.ASS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[2], spieler.getAllegespieltenkarten().get(2));
	}
	
	@Test
	public void spitzenEinordnenTest() {
		boolean test = false;
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.getBlatt().add(
		new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		ISpielart spielart = new Grandspiel();
		spieler.setSpielart(spielart);
		spieler.spitzenEinordnen();
			test = true;
		
		assertTrue(test);
	}
	
	@Test
	public void testSpitzenZahl() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.setSpielart(new Grandspiel());
		spieler.spitzenEinordnen();
		assertEquals(1, spieler.spitzenZahl());
	}
	
	@Test
	public void testSpitzenZahl2() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenZahl());
	}

	@Test
	public void testSpitzenMit() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Grandspiel());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(1, spieler.spitzenMit(1));
	}

	@Test
	public void testSpitzenMit2() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Grandspiel());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(2, spieler.spitzenMit(2));
	}

	@Test
	public void testSpitzenMit3() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Grandspiel());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(3, spieler.spitzenMit(3));
	}

	@Test
	public void testSpitzenMit4() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Grandspiel());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(4, spieler.spitzenMit(4));
	}

	@Test
	public void testSpitzenMit5() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.spitzenEinordnen();
		assertEquals(5, spieler.spitzenMit(5));
	}

	@Test
	public void testSpitzenMit6() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.spitzenEinordnen();
		assertEquals(6, spieler.spitzenMit(6));
	}

	@Test
	public void testSpitzenMit7() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.spitzenEinordnen();
		assertEquals(7, spieler.spitzenMit(7));
	}

	@Test
	public void testSpitzenMit8() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.spitzenEinordnen();
		assertEquals(8, spieler.spitzenMit(8));
	}

	@Test
	public void testSpitzenMit9() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.NEUN));
		spieler.spitzenEinordnen();
		assertEquals(9, spieler.spitzenMit(9));
	}

	@Test
	public void testSpitzenMit10() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.NEUN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ACHT));
		spieler.spitzenEinordnen();
		assertEquals(10, spieler.spitzenMit(10));
	}

	@Test
	public void testSpitzenMit11() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.NEUN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ACHT));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.SIEBEN));
		spieler.spitzenEinordnen();
		assertEquals(11, spieler.spitzenMit(11));
	}

	@Test
	public void testSpitzenOhne() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-1, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne2() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-2, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne3() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-3, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne4() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.spitzenEinordnen();
		assertEquals(-4, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne5() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.spitzenEinordnen();
		assertEquals(-5, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne6() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.spitzenEinordnen();
		assertEquals(-6, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne7() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-7, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne8() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.NEUN));
		spieler.spitzenEinordnen();
		assertEquals(-8, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne9() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ACHT));
		spieler.spitzenEinordnen();
		assertEquals(-9, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne10() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.SIEBEN));
		spieler.spitzenEinordnen();
		assertEquals(-10, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne11() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne12() {
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.setSpielart(new Farbspiel(Farbe.KARO));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.HERZ, Wert.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenOhne(0));
	} 
	
	@Test
	public void testSpitzenOhne13() {
		spieler.setSpielart(new Grandspiel());
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.spitzenEinordnen();
		assertEquals(-4, spieler.spitzenOhne(0));
	}
	
	@Test
	public void spielbarteKartenTest() {
		spieler.setSpielart(new NullspielStub2());
		
		spieler.setBlatt(new ArrayList<Spielkarte>());
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.PIK, Wert.BUBE));
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		spieler.getBlatt().add(karte3);
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.NEUN));
		spieler.getBlatt().add(
				new Spielkarte(Farbe.KARO, Wert.ACHT));
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		ArrayList<Spielkarte> erwartet = new ArrayList<Spielkarte>();
		erwartet.add(karte3);
		assertEquals(erwartet, spieler.spielbareKarten(gespielteKarten));
	}
	
	
	@Test
	public void equalsTest() {
		
		ISpieler spieler2 = new Oma("Rainer Hohn");
		assertTrue(spieler.equals(spieler2));
	}
	@Test
	public void farbenEinordnenTest() {
		
		
	}

}
