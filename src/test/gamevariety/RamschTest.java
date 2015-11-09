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
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard cardToCheck = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard cardToCheck = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertFalse(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard cardToCheck = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.SEVEN);
		PlayingCard cardToCheck = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void followingUnderKnaveTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.followingUnderKnave(hand, playedCards, check));
	}

	@Test
	public void followingUnderKnaveTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertTrue(ramsch.followingUnderKnave(hand, playedCards, check));
	}
	
	@Test
	public void followingUnderKnaveTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertFalse(ramsch.followingUnderKnave(hand, playedCards, check));
	}
	
	@Test
	public void higherCardTest() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest2() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest3() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest4() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest5() {
		
		PlayingCard card1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest6() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.SIX);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void higherCardTest7() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void sortCardTest() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard2Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard3Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(card2, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard4Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard5Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard6Test() {
		
		PlayingCard card1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(card2, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void evaluateCardTest() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		assertEquals(7, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest2() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(8, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest3() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.NINE);
		assertEquals(9, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest4() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.TEN);
		assertEquals(10, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest5() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(11, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest6() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(12, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest7() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertEquals(13, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest8() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(15, ramsch.evaluateCard(card));
	}
}
