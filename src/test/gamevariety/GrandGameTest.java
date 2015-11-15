package test.gamevariety;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GameVarietyName;
import main.gamevariety.GrandGame;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


public class GrandGameTest {

	GrandGame game;
	PlayingCard card1;
	PlayingCard card2;
	PlayingCard card3;
	PlayingCard card4;
	PlayingCard card5;
	PlayingCard card6;
	PlayingCard card7;
	PlayingCard card8;
	PlayingCard card9;
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> hand2 = new ArrayList<PlayingCard>();
	PlayingCard[] tableCards = new PlayingCard[3];
	
	@Before
	public void setUp() {
		game = new GrandGame();
		card1 = new PlayingCard(Suit.HEARTS, Value.KING);
		card2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		card3 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		card4 = new PlayingCard(Suit.BELLS, Value.KING);
		card5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		card6 = new PlayingCard(Suit.ACORNS, Value.NINE);
		card7 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		card8 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		card9 = new PlayingCard(Suit.BELLS, Value.NINE);
		
		hand.add(card2);
		hand.add(card6);
		tableCards[0] = card3;
		
		hand2.add(card5);
		hand2.add(card7);
	}
	
	@Test
	public void testGrandGame() {
		assertEquals(GameVarietyName.GRAND, game.getGameVariety());
	}
	
	//
	//Gespielte Karte Pruefen Tests
	//
	@Test
	public void testCheckedPlayedCards() {
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testCheckedPlayedCards2() {
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards3() {
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card7));
	}
	
	@Test
	public void testCheckedPlayedCards4() {
		assertEquals(false, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testCheckedPlayedCards5() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testCheckedPlayedCards6() {
		tableCards[0] = card1;
		assertEquals(false, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards7() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card7));
	}
	
	@Test
	public void testCheckedPlayedCards8() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testCheckedPlayedCards9() {
		hand.add(card3);
		tableCards[0] = card4;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}

	@Test
	public void testCheckedPlayedCards10() {
		tableCards[0] = null;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}



	@Test
	public void testHigherCard() {
		assertEquals(card1, game.higherCard(card1, card2));
	}
	
	@Test
	public void testHigherCard2() {
		assertEquals(card3, game.higherCard(card2, card3));
	}
	
	@Test
	public void testHigherCard3() {
		assertEquals(card1, game.higherCard(card1, card5));
	}
	
	@Test
	public void testHigherCard4() {
		assertEquals(card7, game.higherCard(card3, card7));
	}
	
	@Test
	public void testHigherCard5() {
		assertEquals(card7, game.higherCard(card7, card3));
	}
	
	@Test
	public void testHigherCard6() {
		assertEquals(card1, game.higherCard(card2, card1));
	}
	
	@Test
	public void testHigherCard7() {
		assertEquals(card3, game.higherCard(card3, card2));
	}
	
	@Test
	public void testHigherCard8() {
		assertEquals(card5, game.higherCard(card5, card1));
	}
	


	@Test
	public void testSortCard() {
		assertEquals(card1, game.sortCard(card1, card2));
	}
	
	@Test
	public void testSortCard2() {
		assertEquals(card7, game.sortCard(card3, card7));
	}
	
	@Test
	public void testSortCard3() {
		assertEquals(card7, game.sortCard(card7, card3));
	}
	
	@Test
	public void testSortCard4() {
		assertEquals(card3, game.sortCard(card3, card2));
	}

	@Test
	public void testSortCard5() {
		assertEquals(card5, game.sortCard(card5, card1));
	}
	
	@Test
	public void testSortCard6() {
		assertEquals(card5, game.sortCard(card1, card5));
	}
	
	@Test
	public void testSortCard7() {
		assertEquals(card3, game.sortCard(card2, card3));
	}

	@Test
	public void testSortCard8() {
		assertEquals(card1, game.sortCard(card2, card1));
	}
	
	@Test
	public void testSortCard9() {
		assertEquals(card8, game.sortCard(card8, card9));
	}
	
	@Test
	public void testSortCard10() {
		assertEquals(card8, game.sortCard(card9, card8));
	}
	
	@Test
	public void testSortCard11() {
		assertEquals(card2, game.sortCard(card2, card9));
	}
	
	@Test
	public void testSortCard12() {
		assertEquals(card2, game.sortCard(card9, card2));
	}
}
