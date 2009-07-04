package skat09.test;
import static org.junit.Assert.*;

import org.junit.Test;

import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;

/**
 * Testklasse fuer die Spielkarte
 * 
 * @author zuletzt bearbeitet von Metty
 * @version 05.05.2009
 */

public class SpielkarteTest {
	
	Wert wert1 = Wert.ACHT;
	Farbe farbe1 = Farbe.HERZ;
	Spielkarte karte1 = new Spielkarte(farbe1, wert1);
	
	Wert wert2 = Wert.DAME;
	Farbe farbe2 = Farbe.PIK;
	Spielkarte karte2 = new Spielkarte(farbe2, wert2);
	
	
	
	/**
	 *  Testet ob der Konstruktor der Spielkarte den Wert der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte1() {
		assertEquals(Wert.ACHT, karte1.getWert() );
	}
	
	/**
	 * Testet ob der Konstruktor der Spielkarte die Farbe der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte2() {
		assertEquals(Farbe.HERZ, karte1.getFarbe() );
	}
	
	/**
	 * Testet ob die equals-Methode korrekt nach false auswertet.
	 */
	@Test
	public void testequals() {
		assertFalse(karte1.equals(karte2));
	}
	
	/**
	 * Testet ob die equals-Methode korrekt nach true auswertet.
	 */
	@Test
	public void testeequals2() {
		Spielkarte karte = karte1;
		assertTrue(karte1.equals(karte));
	}
	
	@Test
	public void toStringTest() {
		String text = "HERZ ACHT";
		assertEquals(text, karte1.toString());
	}
	
	@Test
	public void toStringTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ASS);
		assertEquals("PIK ASS", karte.toString());
		
	}
	
	@Test
	public void compareToTest() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte karte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		assertEquals(0, karte1.compareTo(karte2));
	}
	
	@Test
	public void compareToTest2() {
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte karte2 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		assertEquals(Integer.MAX_VALUE, karte1.compareTo(karte2));
	}
	
	@Test
	public void deutFarbeTest() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.BUBE);
		assertEquals("SCHELLEN", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		assertEquals("HERZ", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest3() {
		
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.BUBE);
		assertEquals("GRUEN", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest4() {
		
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		assertEquals("EICHEL", karte.deutFarbe());
	}
	
	@Test
	public void deutWertTest() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.BUBE);
		assertEquals("UNTER", karte.deutWert());
	}
	
	@Test
	public void deutWertTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.DAME);
		assertEquals("OBER", karte.deutWert());
	}
	
	@Test
	public void deutWertTest3() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ASS);
		assertEquals("DAUS", karte.deutWert());
	}
	
	@Test
	public void deutWertTest4() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.NEUN);
		assertEquals("NEUN", karte.deutWert());
	}
	
	@Test
	public void dateiPfadTest() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.DAME);
		karte.setDeutsch(true);
		assertEquals("deutkarten/HERZ_OBER", karte.dateiPfad());
	}
	
	@Test
	public void dateiPfadTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.DAME);
		karte.setDeutsch(false);
		assertEquals("frankarten/HERZ_DAME", karte.dateiPfad());
	}
}
