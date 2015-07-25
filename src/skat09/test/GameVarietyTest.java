package skat09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.gamevariety.GrandGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;



public class GameVarietyTest {

	//
	// benoetigte Datenfelder
	//

	PlayingCard spielkarte1;
	PlayingCard spielkarte2;
	PlayingCard spielkarte3;
	PlayingCard spielkarte4;
	PlayingCard spielkarte5;
	PlayingCard spielkarte6;
	PlayingCard spielkarte7;
	PlayingCard spielkarte8;
	PlayingCard spielkarte9;
	PlayingCard spielkarte10;
	PlayingCard spielkarte11;
	PlayingCard spielkarte12;
	PlayingCard spielkarte13;
	GrandGame spiel = new GrandGame();

	// Bei diesem Test enthaelt das Blatt des Spielers noch einige Karten.
	// Wir gehen davon aus, dass die zu spielende Karte Kreuz/Zehn ist.
	// Es wurde bisher keine Karte gespielt.
	// 

	@Before
	public void setUp() {

		spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		spielkarte4 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		spielkarte5 = new PlayingCard(Suit.ACORNS, Value.KING);
		spielkarte6 = new PlayingCard(Suit.ACORNS, Value.TEN);
		spielkarte7 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		spielkarte8 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		spielkarte9 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		spielkarte10 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		spielkarte11 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		spielkarte12 = new PlayingCard(Suit.HEARTS, Value.SIX);
		spielkarte13 = new PlayingCard(Suit.HEARTS, Value.SIX);
	}
	
	@Test
	public void farbeBedienenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte9);
		blatt.add(spielkarte11);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		blatt.add(spielkarte2);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = spielkarte13;
		
		assertTrue(spiel.followingSuit(blatt, gespielteKarten, spielkarte2));
	}
	
	@Test
	public void farbeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte9);
		blatt.add(spielkarte11);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		blatt.add(spielkarte2);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = spielkarte13;
		
		assertFalse(spiel.followingSuit(blatt, gespielteKarten, spielkarte10));
	}
	
	@Test
	public void hoehererBubeTest1() {
		
		assertEquals(spielkarte11, spiel.higherUnderKnave(spielkarte11, spielkarte9));
	}
	
	@Test
	public void hoehererBubeTest2() {
		
		assertEquals(spielkarte10, spiel.higherUnderKnave(spielkarte8, spielkarte10));
	}
	
	@Test
	public void hoehererBubeTest3() {
		
		assertEquals(spielkarte9, spiel.higherUnderKnave(spielkarte8, spielkarte9));
	}
	
	@Test
	public void hoehererBubeTest4() {
		
		assertEquals(spielkarte8, spiel.higherUnderKnave(spielkarte8, spielkarte1));
	}
	
	@Test
	public void hoehereFarbeTest1() {
		
		assertEquals(spielkarte11, spiel.higherSuit(spielkarte11, spielkarte12));
	}
	
	@Test
	public void hoehereFarbeTest2() {
		
		assertEquals(spielkarte11, spiel.higherSuit(spielkarte12, spielkarte11));
	}
	
	@Test
	public void hoehereFarbeTest3() {
		
		assertEquals(spielkarte3, spiel.higherSuit(spielkarte3, spielkarte2));
	}
	
	@Test
	public void hoehereFarbeTest4() {
		
		assertEquals(spielkarte3, spiel.higherSuit(spielkarte1, spielkarte3));
	}
	
	@Test
	public void hoehereFarbeTest5() {
		
		assertEquals(spielkarte13, spiel.higherSuit(spielkarte13, spielkarte9));
	}
	
	@Test
	public void hoehereFarbeTest6() {
		
		assertEquals(spielkarte7, spiel.higherSuit(spielkarte1, spielkarte7));
	}
	
	@Test
	public void hoehereKarteEinBubeTest1() {
		
		assertEquals(spielkarte9, 
				spiel.higherCardOneUnderKnave(spielkarte9, spielkarte12));
	}
	
	@Test
	public void hoehereKarteEinBubeTest2() {
		
		assertEquals(spielkarte7, 
				spiel.higherCardOneUnderKnave(spielkarte13, spielkarte7));
	}
	
	@Test
	public void hoehereKarteFarbeTest() {
		
		assertEquals(spielkarte5, spiel.higherCardSuit(spielkarte2, spielkarte5));
	}
	
	@Test
	public void hoehereKarteFarbeTest2() {
		
		assertEquals(spielkarte12, spiel.higherCardSuit(spielkarte12, spielkarte13));
	}

	@Test
	public void testKarteBewerten() {
		assertEquals(7, spiel.evaluateCard(spielkarte1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(8, spiel.evaluateCard(spielkarte2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(9, spiel.evaluateCard(spielkarte3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(10, spiel.evaluateCard(spielkarte4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(11, spiel.evaluateCard(spielkarte5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(12, spiel.evaluateCard(spielkarte6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(13, spiel.evaluateCard(spielkarte7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(14, spiel.evaluateCard(spielkarte8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(15, spiel.evaluateCard(spielkarte9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(16, spiel.evaluateCard(spielkarte10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(17, spiel.evaluateCard(spielkarte11));
	}
	
	@Test
	public void testKarteBewerten12() {
		assertEquals(6, spiel.evaluateCard(spielkarte12));
	}
	
	@Test
	public void testBubeBewerten1() {
		
		assertEquals(14, spiel.evaluateUnderKnave(spielkarte8));
	}
	
	@Test
	public void testBubeBewerten2() {
		
		assertEquals(15, spiel.evaluateUnderKnave(spielkarte9));
	}
	
	@Test
	public void testBubeBewerten3() {
		
		assertEquals(16, spiel.evaluateUnderKnave(spielkarte10));
	}

	@Test
	public void testBubeBewerten4() {
		
		assertEquals(17, spiel.evaluateUnderKnave(spielkarte11));
	}
}
