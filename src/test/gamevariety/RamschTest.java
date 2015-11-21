package test.gamevariety;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.gamevariety.Ramsch;
import main.playingcard.PlayingCard;


public class RamschTest {

	Ramsch ramsch = new Ramsch();
	
	@Test
	public void checkPlayedCards() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard cardToCheck = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard cardToCheck = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		assertFalse(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard cardToCheck = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void checkPlayedCards4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN);
		PlayingCard cardToCheck = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		assertTrue(ramsch.checkedPlayedCards(hand, playedCards, cardToCheck));
	}
	
	@Test
	public void followingUnderKnaveTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard check = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertTrue(ramsch.followingUnderKnave(hand, playedCards, check));
	}

	@Test
	public void followingUnderKnaveTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		assertTrue(ramsch.followingUnderKnave(hand, playedCards, check));
	}
	
	@Test
	public void followingUnderKnaveTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard check = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		assertFalse(ramsch.followingUnderKnave(hand, playedCards, check));
	}
	
	@Test
	public void higherCardTest() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest3() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest4() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest5() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(card2, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void higherCardTest6() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void higherCardTest7() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		assertEquals(card1, ramsch.higherCard(card1, card2));
	}
	
	@Test
	public void sortCardTest() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard2Test() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard3Test() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(card2, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard4Test() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard5Test() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		assertEquals(card1, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void sortCard6Test() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		assertEquals(card2, ramsch.sortCard(card1, card2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void evaluateCardTest() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		assertEquals(7, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		assertEquals(8, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		assertEquals(9, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN);
		assertEquals(10, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		assertEquals(11, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);
		assertEquals(12, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		assertEquals(13, ramsch.evaluateCard(card));
	}
	
	@Test
	public void evaluateCardTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(15, ramsch.evaluateCard(card));
	}
}
