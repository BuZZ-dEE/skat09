package test.gamevariety;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.gamevariety.Ramsch;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


public class RamschTest {

	Ramsch ramsch = new Ramsch();
	
	@Test
	public void checkPlayedCards() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(blatt, playedCards, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertFalse(ramsch.checkedPlayedCards(blatt, playedCards, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.checkedPlayedCards(blatt, playedCards, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.SEVEN);
		PlayingCard cardToCheck = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(blatt, playedCards, cardToCheck));
	}
	
	@Test
	public void bubeBedienenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.followingUnderKnave(blatt, playedCards, check));
	}

	@Test
	public void bubeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertTrue(ramsch.followingUnderKnave(blatt, playedCards, check));
	}
	
	@Test
	public void bubeBedienenTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertFalse(ramsch.followingUnderKnave(blatt, playedCards, check));
	}
	
	@Test
	public void hoehereKarteTest() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void hoehereKarteTest2() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void hoehereKarteTest3() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void hoehereKarteTest4() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void hoehereKarteTest5() {
		
		PlayingCard card1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void hoehereKarteTest6() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.SIX);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void hoehereKarteTest7() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void sortiereKarteTest() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortiereKarte2Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortiereKarte3Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortiereKarte4Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortiereKarte5Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortiereKarte6Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(karte2, ramsch.sortCard(card1, karte2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void karteBewertenTest() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		assertEquals(7, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest2() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(8, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest3() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.NINE);
		assertEquals(9, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest4() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.TEN);
		assertEquals(10, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest5() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(11, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest6() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(12, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest7() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertEquals(13, ramsch.evaluateCard(card));
	}
	
	@Test
	public void karteBewertenTest8() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(15, ramsch.evaluateCard(card));
	}
}
