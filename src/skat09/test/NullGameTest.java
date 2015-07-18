package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.NullGame;
import skat09.spielart.GameVarietyName;
import skat09.spielkarte.Suit;
import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;
import skat09.test.interfaces.INullGame;


public class NullGameTest {

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
	INullGame spiel = new NullGame();
	ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
	PlayingCard[] gespielteKarten = new PlayingCard[3];
	
	@Before
	public void setUp() {
		
		spielkarte1 = new PlayingCard(Suit.KARO, Value.SIEBEN);
		spielkarte2 = new PlayingCard(Suit.HERZ, Value.ACHT);
		spielkarte3 = new PlayingCard(Suit.PIK, Value.NEUN);
		spielkarte4 = new PlayingCard(Suit.KREUZ, Value.DAME);
		spielkarte5 = new PlayingCard(Suit.KREUZ, Value.KOENIG);
		spielkarte6 = new PlayingCard(Suit.KREUZ, Value.ZEHN);
		spielkarte7 = new PlayingCard(Suit.KREUZ, Value.ASS);
		spielkarte8 = new PlayingCard(Suit.KARO, Value.BUBE);
		spielkarte9 = new PlayingCard(Suit.HERZ, Value.BUBE);
		spielkarte10 = new PlayingCard(Suit.PIK, Value.BUBE);
		spielkarte11 = new PlayingCard(Suit.KREUZ, Value.BUBE);
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		gespielteKarten[0] = spielkarte1;
		
	}
	
	@Test
	public void testNullspiel() {
		assertEquals(GameVarietyName.NULL, new NullGame().getSpielart());
	}
	
	
	//
	//GespielteKartePruefen Test
	//
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(false, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		gespielteKarten[0] = null;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, gespielteKarten, spielkarte4));
	}
	
	//
	//KarteBewerten Test
	//
	@Test
	public void testKarteBewerten() {
		assertEquals(1, spiel.karteBewerten(spielkarte1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(2, spiel.karteBewerten(spielkarte2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(3, spiel.karteBewerten(spielkarte3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(6, spiel.karteBewerten(spielkarte4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(7, spiel.karteBewerten(spielkarte5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(4, spiel.karteBewerten(spielkarte6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(8, spiel.karteBewerten(spielkarte7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(5, spiel.karteBewerten(spielkarte8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(5, spiel.karteBewerten(spielkarte9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(5, spiel.karteBewerten(spielkarte10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(5, spiel.karteBewerten(spielkarte11));
	}
	
	//
	//HoehereKarte Test
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(spielkarte5, spiel.hoehereKarte(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(spielkarte5, spiel.hoehereKarte(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(spielkarte1, spiel.hoehereKarte(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(spielkarte2, spiel.hoehereKarte(spielkarte2, spielkarte1));
	}
	
	//
	//SortiereKarte Test
	//
	@Test
	public void testsortiereKarten() {
		assertEquals(spielkarte5, spiel.sortiereKarte(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testsortiereKarten2() {
		assertEquals(spielkarte5, spiel.sortiereKarte(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten3() {
		assertEquals(spielkarte2, spiel.sortiereKarte(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten4() {
		assertEquals(spielkarte2, spiel.sortiereKarte(spielkarte2, spielkarte1));
	}
	
	@Test
	public void testsortiereKarten5() {
		assertEquals(spielkarte3, spiel.sortiereKarte(spielkarte2, spielkarte3));
	}
	
	@Test
	public void testsortiereKarten6() {
		assertEquals(spielkarte3, spiel.sortiereKarte(spielkarte3, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten7() {
		assertEquals(spielkarte4, spiel.sortiereKarte(spielkarte3, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten8() {
		assertEquals(spielkarte4, spiel.sortiereKarte(spielkarte4, spielkarte3));
	}
}
