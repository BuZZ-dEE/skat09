package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.GrandGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;


public class GrandGameTest {

	GrandGame spiel;
	PlayingCard karte1;
	PlayingCard karte2;
	PlayingCard karte3;
	PlayingCard karte4;
	PlayingCard karte5;
	PlayingCard karte6;
	PlayingCard karte7;
	PlayingCard karte8;
	PlayingCard karte9;
	ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> blatt2 = new ArrayList<PlayingCard>();
	PlayingCard[] tischkarten = new PlayingCard[3];
	
	@Before
	public void setUp() {
		spiel = new GrandGame();
		karte1 = new PlayingCard(Suit.HEARTS, Value.KOENIG);
		karte2 = new PlayingCard(Suit.HEARTS, Value.ACHT);
		karte3 = new PlayingCard(Suit.BELLS, Value.BUBE);
		karte4 = new PlayingCard(Suit.BELLS, Value.KOENIG);
		karte5 = new PlayingCard(Suit.ACORNS, Value.ASS);
		karte6 = new PlayingCard(Suit.ACORNS, Value.NEUN);
		karte7 = new PlayingCard(Suit.ACORNS, Value.BUBE);
		karte8 = new PlayingCard(Suit.LEAVES, Value.ACHT);
		karte9 = new PlayingCard(Suit.BELLS, Value.NEUN);
		
		blatt.add(karte2);
		blatt.add(karte6);
		tischkarten[0] = karte3;
		
		blatt2.add(karte5);
		blatt2.add(karte7);
	}
	
	@Test
	public void testGrandspiel() {
		assertEquals(GameVarietyName.GRAND, spiel.getSpielart());
	}
	
	//
	//Gespielte Karte Pruefen Tests
	//
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte7));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		assertEquals(false, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));
	}
	
	@Test
	public void testGespielteKartePruefen6() {
		tischkarten[0] = karte1;
		assertEquals(false, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));
	}
	
	@Test
	public void testGespielteKartePruefen7() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte7));
	}
	
	@Test
	public void testGespielteKartePruefen8() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}
	
	@Test
	public void testGespielteKartePruefen9() {
		blatt.add(karte3);
		tischkarten[0] = karte4;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}

	@Test
	public void testGespielteKartePruefen10() {
		tischkarten[0] = null;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));
	}

	//
	//HoehereKarteTests
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(karte3, spiel.hoehereKarte(karte2, karte3));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte5));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(karte7, spiel.hoehereKarte(karte3, karte7));
	}
	
	@Test
	public void testhoehereKarte5() {
		assertEquals(karte7, spiel.hoehereKarte(karte7, karte3));
	}
	
	@Test
	public void testhoehereKarte6() {
		assertEquals(karte1, spiel.hoehereKarte(karte2, karte1));
	}
	
	@Test
	public void testhoehereKarte7() {
		assertEquals(karte3, spiel.hoehereKarte(karte3, karte2));
	}
	
	@Test
	public void testhoehereKarte8() {
		assertEquals(karte5, spiel.hoehereKarte(karte5, karte1));
	}
	
	//
	//SortiereKarte Tests
	//
	@Test
	public void testsortiereKarte() {
		assertEquals(karte1, spiel.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void testsortiereKarte2() {
		assertEquals(karte7, spiel.sortiereKarte(karte3, karte7));
	}
	
	@Test
	public void testsortiereKarte3() {
		assertEquals(karte7, spiel.sortiereKarte(karte7, karte3));
	}
	
	@Test
	public void testsortiereKarte4() {
		assertEquals(karte3, spiel.sortiereKarte(karte3, karte2));
	}

	@Test
	public void testsortiereKarte5() {
		assertEquals(karte5, spiel.sortiereKarte(karte5, karte1));
	}
	
	@Test
	public void testsortiereKarte6() {
		assertEquals(karte5, spiel.sortiereKarte(karte1, karte5));
	}
	
	@Test
	public void testsortiereKarte7() {
		assertEquals(karte3, spiel.sortiereKarte(karte2, karte3));
	}

	@Test
	public void testsortiereKarte8() {
		assertEquals(karte1, spiel.sortiereKarte(karte2, karte1));
	}
	
	@Test
	public void testsortiereKarte9() {
		assertEquals(karte8, spiel.sortiereKarte(karte8, karte9));
	}
	
	@Test
	public void testsortiereKarte10() {
		assertEquals(karte8, spiel.sortiereKarte(karte9, karte8));
	}
	
	@Test
	public void testsortiereKarte11() {
		assertEquals(karte2, spiel.sortiereKarte(karte2, karte9));
	}
	
	@Test
	public void testsortiereKarte12() {
		assertEquals(karte2, spiel.sortiereKarte(karte9, karte2));
	}
}
