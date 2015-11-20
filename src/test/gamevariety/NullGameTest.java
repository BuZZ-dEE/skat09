package test.gamevariety;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GameVariety;
import main.gamevariety.INullGame;
import main.gamevariety.NullGame;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


public class NullGameTest {

	PlayingCard playingCard1;
	PlayingCard playingCard2;
	PlayingCard playingCard3;
	PlayingCard playingCard4;
	PlayingCard playingCard5;
	PlayingCard playingCard6;
	PlayingCard playingCard7;
	PlayingCard playingCard8;
	PlayingCard playingCard9;
	PlayingCard playingCard10;
	PlayingCard playingCard11;
	INullGame game = new NullGame();
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	PlayingCard[] playedCards = new PlayingCard[3];
	
	@Before
	public void setUp() {
		
		playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		playingCard4 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		playingCard5 = new PlayingCard(Suit.ACORNS, Value.KING);
		playingCard6 = new PlayingCard(Suit.ACORNS, Value.TEN);
		playingCard7 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playingCard8 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		playingCard9 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		playingCard10 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		playingCard11 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		hand.add(playingCard4);
		hand.add(playingCard3);
		playedCards[0] = playingCard1;
		
	}
	
	@Test
	public void testNullGame() {
		assertEquals(GameVariety.Name.NULL, new NullGame().getGameVariety());
	}
	

	@Test
	public void testCheckedPlayedCards() {
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}
	
	@Test
	public void testCheckedPlayedCards2() {
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard3));
	}
	
	@Test
	public void testCheckedPlayedCards3() {
		playedCards[0] = playingCard5;
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}
	
	@Test
	public void testCheckedPlayedCards4() {
		playedCards[0] = playingCard5;
		assertEquals(false, game.checkedPlayedCards(hand, playedCards, playingCard3));
	}
	
	@Test
	public void testCheckedPlayedCards5() {
		playedCards[0] = null;
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}

	@Test
	public void testEvaluateCard() {
		assertEquals(1, game.evaluateCard(playingCard1));
	}
	
	@Test
	public void testEvaluateCard2() {
		assertEquals(2, game.evaluateCard(playingCard2));
	}
	
	@Test
	public void testEvaluateCard3() {
		assertEquals(3, game.evaluateCard(playingCard3));
	}
	
	@Test
	public void testEvaluateCard4() {
		assertEquals(6, game.evaluateCard(playingCard4));
	}
	
	@Test
	public void testEvaluateCard5() {
		assertEquals(7, game.evaluateCard(playingCard5));
	}
	
	@Test
	public void testEvaluateCard6() {
		assertEquals(4, game.evaluateCard(playingCard6));
	}
	
	@Test
	public void testEvaluateCard7() {
		assertEquals(8, game.evaluateCard(playingCard7));
	}
	
	@Test
	public void testEvaluateCard8() {
		assertEquals(5, game.evaluateCard(playingCard8));
	}
	
	@Test
	public void testEvaluateCard9() {
		assertEquals(5, game.evaluateCard(playingCard9));
	}
	
	@Test
	public void testEvaluateCard10() {
		assertEquals(5, game.evaluateCard(playingCard10));
	}
	
	@Test
	public void testEvaluateCard11() {
		assertEquals(5, game.evaluateCard(playingCard11));
	}

	@Test
	public void testHigherCard() {
		assertEquals(playingCard5, game.higherCard(playingCard4, playingCard5));
	}
	
	@Test
	public void testHigherCard2() {
		assertEquals(playingCard5, game.higherCard(playingCard5, playingCard4));
	}
	
	@Test
	public void testHigherCard3() {
		assertEquals(playingCard1, game.higherCard(playingCard1, playingCard2));
	}
	
	@Test
	public void testHigherCard4() {
		assertEquals(playingCard2, game.higherCard(playingCard2, playingCard1));
	}

	@Test
	public void testSortCard() {
		assertEquals(playingCard5, game.sortCard(playingCard4, playingCard5));
	}
	
	@Test
	public void testSortCard2() {
		assertEquals(playingCard5, game.sortCard(playingCard5, playingCard4));
	}
	
	@Test
	public void testSortCard3() {
		assertEquals(playingCard2, game.sortCard(playingCard1, playingCard2));
	}
	
	@Test
	public void testSortCard4() {
		assertEquals(playingCard2, game.sortCard(playingCard2, playingCard1));
	}
	
	@Test
	public void testSortCard5() {
		assertEquals(playingCard3, game.sortCard(playingCard2, playingCard3));
	}
	
	@Test
	public void testSortCard6() {
		assertEquals(playingCard3, game.sortCard(playingCard3, playingCard2));
	}
	
	@Test
	public void testSortCard7() {
		assertEquals(playingCard4, game.sortCard(playingCard3, playingCard4));
	}
	
	@Test
	public void testSortCard8() {
		assertEquals(playingCard4, game.sortCard(playingCard4, playingCard3));
	}
}
