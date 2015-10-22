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
	GrandGame spiel = new GrandGame();

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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingcard9);
		blatt.add(playingcard11);
		blatt.add(playingcard4);
		blatt.add(playingcard5);
		blatt.add(playingcard6);
		blatt.add(playingcard2);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = playingcard13;
		
		assertTrue(spiel.followingSuit(blatt, gespielteKarten, playingcard2));
	}
	
	@Test
	public void farbeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingcard9);
		blatt.add(playingcard11);
		blatt.add(playingcard4);
		blatt.add(playingcard5);
		blatt.add(playingcard6);
		blatt.add(playingcard2);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = playingcard13;
		
		assertFalse(spiel.followingSuit(blatt, gespielteKarten, playingcard10));
	}
	
	@Test
	public void hoehererBubeTest1() {
		
		assertEquals(playingcard11, spiel.higherUnderKnave(playingcard11, playingcard9));
	}
	
	@Test
	public void hoehererBubeTest2() {
		
		assertEquals(playingcard10, spiel.higherUnderKnave(playingcard8, playingcard10));
	}
	
	@Test
	public void hoehererBubeTest3() {
		
		assertEquals(playingcard9, spiel.higherUnderKnave(playingcard8, playingcard9));
	}
	
	@Test
	public void hoehererBubeTest4() {
		
		assertEquals(playingcard8, spiel.higherUnderKnave(playingcard8, playingcard1));
	}
	
	@Test
	public void hoehereFarbeTest1() {
		
		assertEquals(playingcard11, spiel.higherSuit(playingcard11, playingcard12));
	}
	
	@Test
	public void hoehereFarbeTest2() {
		
		assertEquals(playingcard11, spiel.higherSuit(playingcard12, playingcard11));
	}
	
	@Test
	public void hoehereFarbeTest3() {
		
		assertEquals(playingcard3, spiel.higherSuit(playingcard3, playingcard2));
	}
	
	@Test
	public void hoehereFarbeTest4() {
		
		assertEquals(playingcard3, spiel.higherSuit(playingcard1, playingcard3));
	}
	
	@Test
	public void hoehereFarbeTest5() {
		
		assertEquals(playingcard13, spiel.higherSuit(playingcard13, playingcard9));
	}
	
	@Test
	public void hoehereFarbeTest6() {
		
		assertEquals(playingcard7, spiel.higherSuit(playingcard1, playingcard7));
	}
	
	@Test
	public void hoehereKarteEinBubeTest1() {
		
		assertEquals(playingcard9,
				spiel.higherCardOneUnderKnave(playingcard9, playingcard12));
	}
	
	@Test
	public void hoehereKarteEinBubeTest2() {
		
		assertEquals(playingcard7,
				spiel.higherCardOneUnderKnave(playingcard13, playingcard7));
	}
	
	@Test
	public void hoehereKarteFarbeTest() {
		
		assertEquals(playingcard5, spiel.higherCardSuit(playingcard2, playingcard5));
	}
	
	@Test
	public void hoehereKarteFarbeTest2() {
		
		assertEquals(playingcard12, spiel.higherCardSuit(playingcard12, playingcard13));
	}

	@Test
	public void testKarteBewerten() {
		assertEquals(7, spiel.evaluateCard(playingcard1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(8, spiel.evaluateCard(playingcard2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(9, spiel.evaluateCard(playingcard3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(10, spiel.evaluateCard(playingcard4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(11, spiel.evaluateCard(playingcard5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(12, spiel.evaluateCard(playingcard6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(13, spiel.evaluateCard(playingcard7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(14, spiel.evaluateCard(playingcard8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(15, spiel.evaluateCard(playingcard9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(16, spiel.evaluateCard(playingcard10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(17, spiel.evaluateCard(playingcard11));
	}
	
	@Test
	public void testKarteBewerten12() {
		assertEquals(6, spiel.evaluateCard(playingcard12));
	}
	
	@Test
	public void testBubeBewerten1() {
		
		assertEquals(14, spiel.evaluateUnderKnave(playingcard8));
	}
	
	@Test
	public void testBubeBewerten2() {
		
		assertEquals(15, spiel.evaluateUnderKnave(playingcard9));
	}
	
	@Test
	public void testBubeBewerten3() {
		
		assertEquals(16, spiel.evaluateUnderKnave(playingcard10));
	}

	@Test
	public void testBubeBewerten4() {
		
		assertEquals(17, spiel.evaluateUnderKnave(playingcard11));
	}
}
