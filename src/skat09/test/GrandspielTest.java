package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.Grandspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;


public class GrandspielTest {

	Grandspiel spiel;
	Spielkarte karte1;
	Spielkarte karte2;
	Spielkarte karte3;
	Spielkarte karte4;
	Spielkarte karte5;
	Spielkarte karte6;
	Spielkarte karte7;
	Spielkarte karte8;
	Spielkarte karte9;
	ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
	ArrayList<Spielkarte> blatt2 = new ArrayList<Spielkarte>();
	Spielkarte[] tischkarten = new Spielkarte[3];
	
	@Before
	public void setUp() {
		spiel = new Grandspiel();
		karte1 = new Spielkarte(Farbe.HERZ, Wert.KOENIG);
		karte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		karte3 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		karte4 = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		karte5 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		karte6 = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		karte7 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		karte8 = new Spielkarte(Farbe.PIK, Wert.ACHT);
		karte9 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		
		blatt.add(karte2);
		blatt.add(karte6);
		tischkarten[0] = karte3;
		
		blatt2.add(karte5);
		blatt2.add(karte7);
	}
	
	@Test
	public void testGrandspiel() {
		assertEquals(Spielartbezeichnung.GRAND, spiel.getSpielart());
	}
	
	//
	//Gespielte Karte Pruefen Tests
	//
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte7));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		assertEquals(false, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));
	}
	
	@Test
	public void testGespielteKartePruefen6() {
		tischkarten[0] = karte1;
		assertEquals(false, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));
	}
	
	@Test
	public void testGespielteKartePruefen7() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte7));
	}
	
	@Test
	public void testGespielteKartePruefen8() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}
	
	@Test
	public void testGespielteKartePruefen9() {
		blatt.add(karte3);
		tischkarten[0] = karte4;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}

	@Test
	public void testGespielteKartePruefen10() {
		tischkarten[0] = null;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}

	//
	//HoehereKarteTests
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(karte3, spiel.hoehereKarte(karte2, karte3));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte5));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(karte7, spiel.hoehereKarte(karte3, karte7));
	}
	
	@Test
	public void testhoehereKarte5() {
		assertEquals(karte7, spiel.hoehereKarte(karte7, karte3));
	}
	
	@Test
	public void testhoehereKarte6() {
		assertEquals(karte1, spiel.hoehereKarte(karte2, karte1));
	}
	
	@Test
	public void testhoehereKarte7() {
		assertEquals(karte3, spiel.hoehereKarte(karte3, karte2));
	}
	
	@Test
	public void testhoehereKarte8() {
		assertEquals(karte5, spiel.hoehereKarte(karte5, karte1));
	}
	
	//
	//SortiereKarte Tests
	//
	@Test
	public void testsortiereKarte() {
		assertEquals(karte1, spiel.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void testsortiereKarte2() {
		assertEquals(karte7, spiel.sortiereKarte(karte3, karte7));
	}
	
	@Test
	public void testsortiereKarte3() {
		assertEquals(karte7, spiel.sortiereKarte(karte7, karte3));
	}
	
	@Test
	public void testsortiereKarte4() {
		assertEquals(karte3, spiel.sortiereKarte(karte3, karte2));
	}

	@Test
	public void testsortiereKarte5() {
		assertEquals(karte5, spiel.sortiereKarte(karte5, karte1));
	}
	
	@Test
	public void testsortiereKarte6() {
		assertEquals(karte5, spiel.sortiereKarte(karte1, karte5));
	}
	
	@Test
	public void testsortiereKarte7() {
		assertEquals(karte3, spiel.sortiereKarte(karte2, karte3));
	}

	@Test
	public void testsortiereKarte8() {
		assertEquals(karte1, spiel.sortiereKarte(karte2, karte1));
	}
	
	@Test
	public void testsortiereKarte9() {
		assertEquals(karte8, spiel.sortiereKarte(karte8, karte9));
	}
	
	@Test
	public void testsortiereKarte10() {
		assertEquals(karte8, spiel.sortiereKarte(karte9, karte8));
	}
	
	@Test
	public void testsortiereKarte11() {
		assertEquals(karte2, spiel.sortiereKarte(karte2, karte9));
	}
	
	@Test
	public void testsortiereKarte12() {
		assertEquals(karte2, spiel.sortiereKarte(karte9, karte2));
	}
}
