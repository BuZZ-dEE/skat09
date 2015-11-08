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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertFalse(ramsch.checkedPlayedCards(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.checkedPlayedCards(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void checkPlayedCards4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.SEVEN);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertTrue(ramsch.checkedPlayedCards(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void bubeBedienenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertTrue(ramsch.followingUnderKnave(blatt, gespielteKarten, pruefen));
	}

	@Test
	public void bubeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertTrue(ramsch.followingUnderKnave(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void bubeBedienenTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertFalse(ramsch.followingUnderKnave(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void hoehereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals(karte1, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(karte1, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest3() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(karte2, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest4() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(karte2, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest5() {
		
		PlayingCard karte1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(karte2, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest6() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.SIX);
		assertEquals(karte1, ramsch.higherCard(karte1, karte2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void hoehereKarteTest7() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(karte1, ramsch.higherCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(karte1, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte2Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(karte1, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte3Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		assertEquals(karte2, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte4Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(karte1, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte5Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		assertEquals(karte1, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte6Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		assertEquals(karte2, ramsch.sortCard(karte1, karte2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void karteBewertenTest() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		assertEquals(7, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		assertEquals(8, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.NINE);
		assertEquals(9, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.TEN);
		assertEquals(10, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		assertEquals(11, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.KING);
		assertEquals(12, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAUS);
		assertEquals(13, ramsch.evaluateCard(karte));
	}
	
	@Test
	public void karteBewertenTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		assertEquals(15, ramsch.evaluateCard(karte));
	}
}
