package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import skat09.gamevariety.Ramsch;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;


public class RamschTest {

	Ramsch ramsch = new Ramsch();
	
	@Test
	public void gespielteKartePruefen() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.ASS);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.ASS);
		assertFalse(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.SIEBEN);
		PlayingCard zuPruefendeKarte = new PlayingCard(Suit.ACORNS, Value.ASS);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void bubeBedienenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertTrue(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}

	@Test
	public void bubeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.ASS);
		assertTrue(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void bubeBedienenTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.ACHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NEUN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAME));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard pruefen = new PlayingCard(Suit.HEARTS, Value.ASS);
		assertFalse(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void hoehereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.BUBE);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.ACHT);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest3() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.SIEBEN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest4() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.DAME);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.KOENIG);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest5() {
		
		PlayingCard karte1 = new PlayingCard(Suit.LEAVES, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest6() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.SIEBEN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.SECHS);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void hoehereKarteTest7() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NEUN);
		PlayingCard karte2 = new PlayingCard(Suit.HEARTS, Value.DAME);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.BUBE);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte2Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte3Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.NEUN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.BUBE);
		assertEquals(karte2, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte4Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte5Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte6Test() {
		
		PlayingCard karte1 = new PlayingCard(Suit.LEAVES, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.ASS);
		assertEquals(karte2, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void karteBewertenTest() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SIEBEN);
		assertEquals(7, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.ACHT);
		assertEquals(8, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.NEUN);
		assertEquals(9, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.ZEHN);
		assertEquals(10, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAME);
		assertEquals(11, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.KOENIG);
		assertEquals(12, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.ASS);
		assertEquals(13, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.BUBE);
		assertEquals(15, ramsch.karteBewerten(karte));
	}
}
