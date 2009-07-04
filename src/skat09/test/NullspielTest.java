package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.Nullspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.INullspiel;


public class NullspielTest {

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
	INullspiel spiel = new Nullspiel();
	ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
	Spielkarte[] gespielteKarten = new Spielkarte[3];
	
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
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		gespielteKarten[0] = spielkarte1;
		
	}
	
	@Test
	public void testNullspiel() {
		assertEquals(Spielartbezeichnung.NULL, new Nullspiel().getSpielart());
	}
	
	
	//
	//GespielteKartePruefen Test
	//
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(false, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		gespielteKarten[0] = null;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	//
	//KarteBewerten Test
	//
	@Test
	public void testKarteBewerten() {
		assertEquals(1, spiel.karteBewerten(spielkarte1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(2, spiel.karteBewerten(spielkarte2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(3, spiel.karteBewerten(spielkarte3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(6, spiel.karteBewerten(spielkarte4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(7, spiel.karteBewerten(spielkarte5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(4, spiel.karteBewerten(spielkarte6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(8, spiel.karteBewerten(spielkarte7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(5, spiel.karteBewerten(spielkarte8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(5, spiel.karteBewerten(spielkarte9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(5, spiel.karteBewerten(spielkarte10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(5, spiel.karteBewerten(spielkarte11));
	}
	
	//
	//HoehereKarte Test
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(spielkarte5, spiel.hoehereKarte(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(spielkarte5, spiel.hoehereKarte(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(spielkarte1, spiel.hoehereKarte(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(spielkarte2, spiel.hoehereKarte(spielkarte2, spielkarte1));
	}
	
	//
	//SortiereKarte Test
	//
	@Test
	public void testsortiereKarten() {
		assertEquals(spielkarte5, spiel.sortiereKarte(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testsortiereKarten2() {
		assertEquals(spielkarte5, spiel.sortiereKarte(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten3() {
		assertEquals(spielkarte2, spiel.sortiereKarte(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten4() {
		assertEquals(spielkarte2, spiel.sortiereKarte(spielkarte2, spielkarte1));
	}
	
	@Test
	public void testsortiereKarten5() {
		assertEquals(spielkarte3, spiel.sortiereKarte(spielkarte2, spielkarte3));
	}
	
	@Test
	public void testsortiereKarten6() {
		assertEquals(spielkarte3, spiel.sortiereKarte(spielkarte3, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten7() {
		assertEquals(spielkarte4, spiel.sortiereKarte(spielkarte3, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten8() {
		assertEquals(spielkarte4, spiel.sortiereKarte(spielkarte4, spielkarte3));
	}
}
