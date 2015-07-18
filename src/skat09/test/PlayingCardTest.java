package skat09.test;
import static org.junit.Assert.*;

import org.junit.Test;

import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;

/**
 * Testklasse fuer die Spielkarte
 * 
 * @author zuletzt bearbeitet von Metty
 * @version 05.05.2009
 */

public class PlayingCardTest {
	
	Value wert1 = Value.ACHT;
	Suit farbe1 = Suit.HEARTS;
	PlayingCard karte1 = new PlayingCard(farbe1, wert1);
	
	Value wert2 = Value.DAME;
	Suit farbe2 = Suit.LEAVES;
	PlayingCard karte2 = new PlayingCard(farbe2, wert2);
	
	
	
	/**
	 *  Testet ob der Konstruktor der Spielkarte den Wert der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte1() {
		assertEquals(Value.ACHT, karte1.getWert() );
	}
	
	/**
	 * Testet ob der Konstruktor der Spielkarte die Farbe der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte2() {
		assertEquals(Suit.HEARTS, karte1.getFarbe() );
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
		PlayingCard karte = karte1;
		assertTrue(karte1.equals(karte));
	}
	
	@Test
	public void toStringTest() {
		String text = "HERZ ACHT";
		assertEquals(text, karte1.toString());
	}
	
	@Test
	public void toStringTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.ASS);
		assertEquals("PIK ASS", karte.toString());
		
	}
	
	@Test
	public void compareToTest() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.ACHT);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.ACHT);
		assertEquals(0, karte1.compareTo(karte2));
	}
	
	@Test
	public void compareToTest2() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.ACHT);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.NEUN);
		assertEquals(Integer.MAX_VALUE, karte1.compareTo(karte2));
	}
	
	@Test
	public void deutFarbeTest() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.BUBE);
		assertEquals("SCHELLEN", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertEquals("HERZ", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.BUBE);
		assertEquals("GRUEN", karte.deutFarbe());
	}
	
	@Test
	public void deutFarbeTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.BUBE);
		assertEquals("EICHEL", karte.deutFarbe());
	}
	
	@Test
	public void deutWertTest() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.BUBE);
		assertEquals("UNTER", karte.deutWert());
	}
	
	@Test
	public void deutWertTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAME);
		assertEquals("OBER", karte.deutWert());
	}
	
	@Test
	public void deutWertTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.ASS);
		assertEquals("DAUS", karte.deutWert());
	}
	
	@Test
	public void deutWertTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NEUN);
		assertEquals("NEUN", karte.deutWert());
	}
	
	@Test
	public void dateiPfadTest() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAME);
		karte.setDeutsch(true);
		assertEquals("deutkarten/HERZ_OBER", karte.dateiPfad());
	}
	
	@Test
	public void dateiPfadTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAME);
		karte.setDeutsch(false);
		assertEquals("frankarten/HERZ_DAME", karte.dateiPfad());
	}
}
