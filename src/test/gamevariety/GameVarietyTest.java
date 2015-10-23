package test.gamevariety;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GrandGame;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;



public class GameVarietyTest {

	PlayingCard playingcard1;
	PlayingCard playingcard2;
	PlayingCard playingcard3;
	PlayingCard playingcard4;
	PlayingCard playingcard5;
	PlayingCard playingcard6;
	PlayingCard playingcard7;
	PlayingCard playingcard8;
	PlayingCard playingcard9;
	PlayingCard playingcard10;
	PlayingCard playingcard11;
	PlayingCard playingcard12;
	PlayingCard playingcard13;
	GrandGame game = new GrandGame();

	// Bei diesem Test enthaelt das Blatt des Spielers noch einige Karten.
	// Wir gehen davon aus, dass die zu spielende Karte Kreuz/Zehn ist.
	// Es wurde bisher keine Karte gespielt.

	@Before
	public void setUp() {

		playingcard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playingcard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		playingcard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		playingcard4 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		playingcard5 = new PlayingCard(Suit.ACORNS, Value.KING);
		playingcard6 = new PlayingCard(Suit.ACORNS, Value.TEN);
		playingcard7 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playingcard8 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		playingcard9 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		playingcard10 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		playingcard11 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		playingcard12 = new PlayingCard(Suit.HEARTS, Value.SIX);
		playingcard13 = new PlayingCard(Suit.HEARTS, Value.SIX);
	}
	
	@Test
	public void farbeBedienenTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingcard9);
		hand.add(playingcard11);
		hand.add(playingcard4);
		hand.add(playingcard5);
		hand.add(playingcard6);
		hand.add(playingcard2);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = playingcard13;
		
		assertTrue(game.followingSuit(hand, playedCards, playingcard2));
	}
	
	@Test
	public void farbeBedienenTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingcard9);
		hand.add(playingcard11);
		hand.add(playingcard4);
		hand.add(playingcard5);
		hand.add(playingcard6);
		hand.add(playingcard2);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = playingcard13;
		
		assertFalse(game.followingSuit(hand, playedCards, playingcard10));
	}
	
	@Test
	public void hoehererBubeTest1() {
		
		assertEquals(playingcard11, game.higherUnderKnave(playingcard11, playingcard9));
	}
	
	@Test
	public void hoehererBubeTest2() {
		
		assertEquals(playingcard10, game.higherUnderKnave(playingcard8, playingcard10));
	}
	
	@Test
	public void hoehererBubeTest3() {
		
		assertEquals(playingcard9, game.higherUnderKnave(playingcard8, playingcard9));
	}
	
	@Test
	public void hoehererBubeTest4() {
		
		assertEquals(playingcard8, game.higherUnderKnave(playingcard8, playingcard1));
	}
	
	@Test
	public void hoehereFarbeTest1() {
		
		assertEquals(playingcard11, game.higherSuit(playingcard11, playingcard12));
	}
	
	@Test
	public void hoehereFarbeTest2() {
		
		assertEquals(playingcard11, game.higherSuit(playingcard12, playingcard11));
	}
	
	@Test
	public void hoehereFarbeTest3() {
		
		assertEquals(playingcard3, game.higherSuit(playingcard3, playingcard2));
	}
	
	@Test
	public void hoehereFarbeTest4() {
		
		assertEquals(playingcard3, game.higherSuit(playingcard1, playingcard3));
	}
	
	@Test
	public void hoehereFarbeTest5() {
		
		assertEquals(playingcard13, game.higherSuit(playingcard13, playingcard9));
	}
	
	@Test
	public void hoehereFarbeTest6() {
		
		assertEquals(playingcard7, game.higherSuit(playingcard1, playingcard7));
	}
	
	@Test
	public void hoehereKarteEinBubeTest1() {
		
		assertEquals(playingcard9,
				game.higherCardOneUnderKnave(playingcard9, playingcard12));
	}
	
	@Test
	public void hoehereKarteEinBubeTest2() {
		
		assertEquals(playingcard7,
				game.higherCardOneUnderKnave(playingcard13, playingcard7));
	}
	
	@Test
	public void hoehereKarteFarbeTest() {
		
		assertEquals(playingcard5, game.higherCardSuit(playingcard2, playingcard5));
	}
	
	@Test
	public void hoehereKarteFarbeTest2() {
		
		assertEquals(playingcard12, game.higherCardSuit(playingcard12, playingcard13));
	}

	@Test
	public void testKarteBewerten() {
		assertEquals(7, game.evaluateCard(playingcard1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(8, game.evaluateCard(playingcard2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(9, game.evaluateCard(playingcard3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(10, game.evaluateCard(playingcard4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(11, game.evaluateCard(playingcard5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(12, game.evaluateCard(playingcard6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(13, game.evaluateCard(playingcard7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(14, game.evaluateCard(playingcard8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(15, game.evaluateCard(playingcard9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(16, game.evaluateCard(playingcard10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(17, game.evaluateCard(playingcard11));
	}
	
	@Test
	public void testKarteBewerten12() {
		assertEquals(6, game.evaluateCard(playingcard12));
	}
	
	@Test
	public void testBubeBewerten1() {
		
		assertEquals(14, game.evaluateUnderKnave(playingcard8));
	}
	
	@Test
	public void testBubeBewerten2() {
		
		assertEquals(15, game.evaluateUnderKnave(playingcard9));
	}
	
	@Test
	public void testBubeBewerten3() {
		
		assertEquals(16, game.evaluateUnderKnave(playingcard10));
	}

	@Test
	public void testBubeBewerten4() {
		
		assertEquals(17, game.evaluateUnderKnave(playingcard11));
	}
}
