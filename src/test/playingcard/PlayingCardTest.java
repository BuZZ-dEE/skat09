package test.playingcard;
import static org.junit.Assert.*;

import org.junit.Test;

import main.playingcard.PlayingCard;

/**
 * Testklasse fuer die Spielkarte
 * 
 * @author zuletzt bearbeitet von Metty
 * @version 05.05.2009
 */

public class PlayingCardTest {
	
	PlayingCard.Rank wert1 = PlayingCard.Rank.EIGHT;
	PlayingCard.Suit farbe1 = PlayingCard.Suit.HEARTS;
	PlayingCard karte1 = new PlayingCard(farbe1, wert1);
	
	PlayingCard.Rank wert2 = PlayingCard.Rank.OVER_KNAVE;
	PlayingCard.Suit farbe2 = PlayingCard.Suit.LEAVES;
	PlayingCard karte2 = new PlayingCard(farbe2, wert2);
	
	
	
	/**
	 *  Testet ob der Konstruktor der Spielkarte den Wert der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte1() {
		assertEquals(PlayingCard.Rank.EIGHT, karte1.getRank() );
	}
	
	/**
	 * Testet ob der Konstruktor der Spielkarte die Farbe der Karte richtig initialisiert.
	 */
	@Test
	public void testSpielkarte2() {
		assertEquals(PlayingCard.Suit.HEARTS, karte1.getSuit() );
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
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		assertEquals("LEAVES DAUS", karte.toString());
		
	}
	
	@Test
	public void compareToTest() {
		PlayingCard karte1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard karte2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		assertEquals(0, karte1.compareTo(karte2));
	}
	
	@Test
	public void compareToTest2() {
		PlayingCard karte1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard karte2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		assertEquals(Integer.MAX_VALUE, karte1.compareTo(karte2));
	}
	
	@Test
	public void frenchSuitTest() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("DIAMONDS", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest2() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("HEARTS", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest3() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("SPADES", karte.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest4() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("CLUBS", karte.frenchSuit());
	}
	
	@Test
	public void frenchValueTest() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("JACK", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest2() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		assertEquals("QUEEN", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest3() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		assertEquals("ACE", karte.frenchValue());
	}
	
	@Test
	public void frenchValueTest4() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		assertEquals("NINE", karte.frenchValue());
	}
	
	@Test
	public void dateiPfadTest() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard.setGermanDeck(true);
		assertEquals("germancards/HEARTS_OVER_KNAVE", karte.filePath());
	}
	
	@Test
	public void dateiPfadTest2() {
		
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard.setGermanDeck(false);
		assertEquals("frenchcards/HEARTS_QUEEN", karte.filePath());
	}
}
