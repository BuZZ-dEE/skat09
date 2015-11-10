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
	public void testNullspiel() {
		assertEquals(GameVarietyName.NULL, new NullGame().getGameVariety());
	}
	
	
	@Test
	public void testGespielteKartePruefen() {
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}
	
	@Test
	public void testGespielteKartePruefen2() {
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard3));
	}
	
	@Test
	public void testGespielteKartePruefen3() {
		playedCards[0] = playingCard5;
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}
	
	@Test
	public void testGespielteKartePruefen4() {
		playedCards[0] = playingCard5;
		assertEquals(false, game.checkedPlayedCards(hand, playedCards, playingCard3));
	}
	
	@Test
	public void testGespielteKartePruefen5() {
		playedCards[0] = null;
		assertEquals(true, game.checkedPlayedCards(hand, playedCards, playingCard4));
	}
	
	//
	//KarteBewerten Test
	//
	@Test
	public void testKarteBewerten() {
		assertEquals(1, game.evaluateCard(playingCard1));
	}
	
	@Test
	public void testKarteBewerten2() {
		assertEquals(2, game.evaluateCard(playingCard2));
	}
	
	@Test
	public void testKarteBewerten3() {
		assertEquals(3, game.evaluateCard(playingCard3));
	}
	
	@Test
	public void testKarteBewerten4() {
		assertEquals(6, game.evaluateCard(playingCard4));
	}
	
	@Test
	public void testKarteBewerten5() {
		assertEquals(7, game.evaluateCard(playingCard5));
	}
	
	@Test
	public void testKarteBewerten6() {
		assertEquals(4, game.evaluateCard(playingCard6));
	}
	
	@Test
	public void testKarteBewerten7() {
		assertEquals(8, game.evaluateCard(playingCard7));
	}
	
	@Test
	public void testKarteBewerten8() {
		assertEquals(5, game.evaluateCard(playingCard8));
	}
	
	@Test
	public void testKarteBewerten9() {
		assertEquals(5, game.evaluateCard(playingCard9));
	}
	
	@Test
	public void testKarteBewerten10() {
		assertEquals(5, game.evaluateCard(playingCard10));
	}
	
	@Test
	public void testKarteBewerten11() {
		assertEquals(5, game.evaluateCard(playingCard11));
	}
	
	//
	//HoehereKarte Test
	//
	@Test
	public void testhoehereKarte() {
		assertEquals(playingCard5, game.higherCard(playingCard4, playingCard5));
	}
	
	@Test
	public void testhoehereKarte2() {
		assertEquals(playingCard5, game.higherCard(playingCard5, playingCard4));
	}
	
	@Test
	public void testhoehereKarte3() {
		assertEquals(playingCard1, game.higherCard(playingCard1, playingCard2));
	}
	
	@Test
	public void testhoehereKarte4() {
		assertEquals(playingCard2, game.higherCard(playingCard2, playingCard1));
	}
	
	//
	//SortiereKarte Test
	//
	@Test
	public void testsortiereKarten() {
		assertEquals(playingCard5, game.sortCard(playingCard4, playingCard5));
	}
	
	@Test
	public void testsortiereKarten2() {
		assertEquals(playingCard5, game.sortCard(playingCard5, playingCard4));
	}
	
	@Test
	public void testsortiereKarten3() {
		assertEquals(playingCard2, game.sortCard(playingCard1, playingCard2));
	}
	
	@Test
	public void testsortiereKarten4() {
		assertEquals(playingCard2, game.sortCard(playingCard2, playingCard1));
	}
	
	@Test
	public void testsortiereKarten5() {
		assertEquals(playingCard3, game.sortCard(playingCard2, playingCard3));
	}
	
	@Test
	public void testsortiereKarten6() {
		assertEquals(playingCard3, game.sortCard(playingCard3, playingCard2));
	}
	
	@Test
	public void testsortiereKarten7() {
		assertEquals(playingCard4, game.sortCard(playingCard3, playingCard4));
	}
	
	@Test
	public void testsortiereKarten8() {
		assertEquals(playingCard4, game.sortCard(playingCard4, playingCard3));
	}
}
