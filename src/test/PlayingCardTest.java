package test;
import static org.junit.Assert.*;

import org.junit.Test;

import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;

/**
 * Testklasse fuer die Spielkarte
 * 
 * @author zuletzt bearbeitet von Metty
 * @version 05.05.2009
 */

public class PlayingCardTest {
	
	Value wert1 = Value.EIGHT;
	Suit farbe1 = Suit.HEARTS;
	PlayingCard karte1 = new PlayingCard(farbe1, wert1);
	
	Value wert2 = Value.OVER_KNAVE;
	Suit farbe2 = Suit.LEAVES;
	PlayingCard karte2 = new PlayingCard(farbe2, wert2);
	
	
	
	/**
	 *  Testet ob der Konstruktor der Spielkarte den Wert der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte1() {
		assertEquals(Value.EIGHT, karte1.getValue() );
	}
	
	/**
	 * Testet ob der Konstruktor der Spielkarte die Farbe der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte2() {
		assertEquals(Suit.HEARTS, karte1.getSuit() );
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
		String text = "HEARTS EIGHT";
		assertEquals(text, karte1.toString());
	}
	
	@Test
	public void toStringTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals("LEAVES DAUS", karte.toString());
		
	}
	
	@Test
	public void compareToTest() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(0, karte1.compareTo(karte2));
	}
	
	@Test
	public void compareToTest2() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.NINE);
		assertEquals(Integer.MAX_VALUE, karte1.compareTo(karte2));
	}
	
	@Test
	public void frenchSuitTest() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals("DIAMONDS", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals("HEARTS", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals("SPADES", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		assertEquals("CLUBS", karte.frenchSuit());
	}
	
	@Test
	public void frenchValueTest() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals("JACK", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		assertEquals("QUEEN", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAUS);
		assertEquals("ACE", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NINE);
		assertEquals("NINE", karte.frenchValue());
	}
	
	@Test
	public void dateiPfadTest() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		karte.setGermanDeck(true);
		assertEquals("germancards/HEARTS_OVER_KNAVE", karte.filePath());
	}
	
	@Test
	public void dateiPfadTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		karte.setGermanDeck(false);
		assertEquals("frenchcards/HEARTS_QUEEN", karte.filePath());
	}
}
