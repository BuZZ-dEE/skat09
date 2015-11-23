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
	
	PlayingCard.Rank rank1 = PlayingCard.Rank.EIGHT;
	PlayingCard.Suit suit1 = PlayingCard.Suit.HEARTS;
	PlayingCard card1 = new PlayingCard(suit1, rank1);
	
	PlayingCard.Rank rank2 = PlayingCard.Rank.OVER_KNAVE;
	PlayingCard.Suit suit2 = PlayingCard.Suit.LEAVES;
	PlayingCard card2 = new PlayingCard(suit2, rank2);
	
	
	
	/**
	 *  Testet ob der Konstruktor der Spielkarte den Wert der Karte richtig initialisiert.
	 */
	@Test
	public void testPlayingCard1() {
		assertEquals(PlayingCard.Rank.EIGHT, card1.getRank() );
	}
	
	/**
	 * Testet ob der Konstruktor der Spielkarte die Farbe der Karte richtig initialisiert.
	 */
	@Test
	public void testPlayingCard2() {
		assertEquals(PlayingCard.Suit.HEARTS, card1.getSuit() );
	}
	
	/**
	 * Testet ob die equals-Methode korrekt nach false auswertet.
	 */
	@Test
	public void testequals() {
		assertFalse(card1.equals(card2));
	}
	
	/**
	 * Testet ob die equals-Methode korrekt nach true auswertet.
	 */
	@Test
	public void testequals2() {
		PlayingCard card = card1;
		assertTrue(card1.equals(card));
	}
	
	@Test
	public void toStringTest() {
		String text = "HEARTS EIGHT";
		assertEquals(text, card1.toString());
	}
	
	@Test
	public void toStringTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		assertEquals("LEAVES DAUS", card.toString());
		
	}
	
	@Test
	public void compareToTest() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		assertEquals(0, card1.compareTo(card2));
	}
	
	@Test
	public void compareToTest2() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		assertEquals(Integer.MAX_VALUE, card1.compareTo(card2));
	}
	
	@Test
	public void frenchSuitTest() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("DIAMONDS", card.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("HEARTS", card.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("SPADES", card.frenchSuit());
	}
	
	@Test
	public void frenchSuitTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("CLUBS", card.frenchSuit());
	}
	
	@Test
	public void frenchRankTest() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals("JACK", card.frenchRank());
	}
	
	@Test
	public void frenchRankTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		assertEquals("QUEEN", card.frenchRank());
	}
	
	@Test
	public void frenchRankTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		assertEquals("ACE", card.frenchRank());
	}
	
	@Test
	public void frenchRankTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		assertEquals("NINE", card.frenchRank());
	}
	
	@Test
	public void filePathTest() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard.setGermanDeck(true);
		assertEquals("germancards/HEARTS_OVER_KNAVE", card.filePath());
	}
	
	@Test
	public void filePath2Test() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard.setGermanDeck(false);
		assertEquals("frenchcards/HEARTS_QUEEN", card.filePath());
	}
}
