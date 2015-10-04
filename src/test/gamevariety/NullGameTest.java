package test.gamevariety;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GameVarietyName;
import main.gamevariety.INullGame;
import main.gamevariety.NullGame;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


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
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		gespielteKarten[0] = spielkarte1;
		
	}
	
	@Test
	public void testNullspiel() {
		assertEquals(GameVarietyName.NULL, new NullGame().getGameVariety());
	}
	
	
	//
	//GespielteKartePruefen Test
	//
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, spiel.checkedPlayedCards(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, spiel.checkedPlayedCards(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(true, spiel.checkedPlayedCards(blatt, gespielteKarten, spielkarte4));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		gespielteKarten[0] = spielkarte5;
		assertEquals(false, spiel.checkedPlayedCards(blatt, gespielteKarten, spielkarte3));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		gespielteKarten[0] = null;
		assertEquals(true, spiel.checkedPlayedCards(blatt, gespielteKarten, spielkarte4));
	}
	
	//
	//KarteBewerten Test
	//
	@Test
	public void testKarteBewerten() {
		assertEquals(1, spiel.evaluateCard(spielkarte1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(2, spiel.evaluateCard(spielkarte2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(3, spiel.evaluateCard(spielkarte3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(6, spiel.evaluateCard(spielkarte4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(7, spiel.evaluateCard(spielkarte5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(4, spiel.evaluateCard(spielkarte6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(8, spiel.evaluateCard(spielkarte7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(5, spiel.evaluateCard(spielkarte8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(5, spiel.evaluateCard(spielkarte9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(5, spiel.evaluateCard(spielkarte10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(5, spiel.evaluateCard(spielkarte11));
	}
	
	//
	//HoehereKarte Test
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(spielkarte5, spiel.higherCard(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(spielkarte5, spiel.higherCard(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(spielkarte1, spiel.higherCard(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(spielkarte2, spiel.higherCard(spielkarte2, spielkarte1));
	}
	
	//
	//SortiereKarte Test
	//
	@Test
	public void testsortiereKarten() {
		assertEquals(spielkarte5, spiel.sortCard(spielkarte4, spielkarte5));
	}
	
	@Test
	public void testsortiereKarten2() {
		assertEquals(spielkarte5, spiel.sortCard(spielkarte5, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten3() {
		assertEquals(spielkarte2, spiel.sortCard(spielkarte1, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten4() {
		assertEquals(spielkarte2, spiel.sortCard(spielkarte2, spielkarte1));
	}
	
	@Test
	public void testsortiereKarten5() {
		assertEquals(spielkarte3, spiel.sortCard(spielkarte2, spielkarte3));
	}
	
	@Test
	public void testsortiereKarten6() {
		assertEquals(spielkarte3, spiel.sortCard(spielkarte3, spielkarte2));
	}
	
	@Test
	public void testsortiereKarten7() {
		assertEquals(spielkarte4, spiel.sortCard(spielkarte3, spielkarte4));
	}
	
	@Test
	public void testsortiereKarten8() {
		assertEquals(spielkarte4, spiel.sortCard(spielkarte4, spielkarte3));
	}
}
