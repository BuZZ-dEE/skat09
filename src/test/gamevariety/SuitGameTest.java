package test.gamevariety;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GameVarietyName;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;

public class SuitGameTest {
	Suit a = Suit.HEARTS;
	SuitGame game = new SuitGame(a);
	Suit b = Suit.LEAVES;
	SuitGame game2 = new SuitGame(b);
	PlayingCard card1;
	PlayingCard card2;
	PlayingCard card3;
	PlayingCard card4;
	PlayingCard card5;
	PlayingCard card6;
	PlayingCard card7;
	PlayingCard card8;
	PlayingCard card9;
	PlayingCard card10;
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> hand2 = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> hand3 = new ArrayList<PlayingCard>();
	PlayingCard[] tableCards = new PlayingCard[3];

	@Before
	public void setUp() {
		card1 = new PlayingCard(Suit.HEARTS, Value.KING);
		card2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		card3 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		card4 = new PlayingCard(Suit.BELLS, Value.KING);
		card5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		card6 = new PlayingCard(Suit.ACORNS, Value.NINE);
		card7 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		card8 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		card9 = new PlayingCard(Suit.BELLS, Value.NINE);
		
		
		hand.add(card2);
		hand.add(card6);
		tableCards[0] = card3;
		
		hand2.add(card5);
		hand2.add(card4);
		
		hand3.add(card6);
		hand3.add(card7);
	}

	@Test
	public void FarbspielTest3() {
		assertEquals(Suit.BELLS, new SuitGame(Suit.BELLS).getTrumpSuit());
	}

	@Test
	public void FarbspielTest2() {
		assertEquals(GameVarietyName.SUIT, new SuitGame(Suit.BELLS)
				.getGameVariety());
	}

	@Test
	public void testgetTrumpfFarbe() {
		assertEquals(Suit.HEARTS, game.getTrumpSuit());
	}

	@Test
	public void testGespielteKartepruefen() {
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testGespielteKartepruefen2() {
		assertEquals(false, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testGespielteKartepruefen3() {
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testGespielteKartepruefen4() {
		assertEquals(true , game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testGespielteKartepruefen5() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testGespielteKartepruefen6() {
		tableCards[0] = card1;
		assertEquals(false, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testGespielteKartepruefen7() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testGespielteKartepruefen8() {
		tableCards[0] = card6;
		assertEquals(false, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testGespielteKartepruefen9() {
		tableCards[0] = card6;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testGespielteKartepruefen10() {
		tableCards[0] = null;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testGespielteKartepruefen11() {
		tableCards[0] = card3;
		assertEquals(false, game.checkedPlayedCards(hand3, tableCards, card6));
	}
	
	@Test
	public void testGespielteKartepruefen12() {
		tableCards[0] = card4;
		assertEquals(true, game.checkedPlayedCards(hand3, tableCards, card6));
	}
	
	@Test
	public void testGespielteKartepruefen13() {
		tableCards[0] = card4;
		hand.add(card3);
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testHoehereKarte(){
		assertEquals(card1, game.higherCard(card1, card2));
		
	}
	
	@Test
	public void testHoehereKarte2(){
		assertEquals(card3, game.higherCard(card1, card3));
		
	}
	
	@Test
	public void testHoehereKarte3(){
		assertEquals(card1, game.higherCard(card1, card4));
		
	}
	
	@Test
	public void testHoehereKarte4(){
		assertEquals(card6, game.higherCard(card6, card4));
		
	}
	
	@Test
	public void testHoehereKarte5(){
		assertEquals(card7, game.higherCard(card3, card7));
		
	}
	
	@Test
	public void testHoehereKarte6(){
		assertEquals(card7, game.higherCard(card7, card3));
		
	}
	
	@Test
	public void testHoehereKarte8(){
		assertEquals(card3, game.higherCard(card3, card1));
		
	}
	
	@Test
	public void testHoehereKarte9(){
		assertEquals(card1, game.higherCard(card2, card1));
		
	}
	
	@Test
	public void testHoehereKarte10(){
		assertEquals(card1, game.higherCard(card4, card1));
		
	}
	
	@Test
	public void testHoehereKarte11(){
		assertEquals(card5, game.higherCard(card5, card6));
		
	}
	
	@Test
	public void testHoehereKarte12(){
		assertEquals(card5, game.higherCard(card6, card5));
		
	}
	
	@Test
	public void testSortiereKarte(){
		assertEquals(card2, game.sortCard(card5, card2));
	}
	
	@Test
	public void testSortiereKarte2(){
		assertEquals(card7, game.sortCard(card3, card7));
	}
	
	@Test
	public void testSortiereKarte3(){
		assertEquals(card3, game.sortCard(card3, card2));
	}
	
	@Test
	public void testSortiereKarte4(){
		assertEquals(card3, game.sortCard(card3, card5));
	}
	
	@Test
	public void testSortiereKarte5(){
		assertEquals(card1, game.sortCard(card1, card2));
	}
	
	@Test
	public void testSortiereKarte6(){
		assertEquals(card5, game.sortCard(card5, card4));
	}
	
	@Test
	public void testSortiereKarte7(){
		assertEquals(card7, game.sortCard(card7, card3));
	}
	
	@Test
	public void testSortiereKarte8(){
		assertEquals(card3, game.sortCard(card5, card3));
	}
	
	@Test
	public void testSortiereKarte9(){
		assertEquals(card3, game.sortCard(card2, card3));
	}

	@Test
	public void testSortiereKarte10(){
		assertEquals(card1, game.sortCard(card2, card1));
	}
	
	@Test
	public void testSortiereKarte11(){
		assertEquals(card2, game.sortCard(card2, card5));
	}
	
	@Test
	public void testSortiereKarte12(){
		assertEquals(card5, game.sortCard(card5, card6));
	}
	
	@Test
	public void testSortiereKarte13(){
		assertEquals(card5, game.sortCard(card6, card5));
	}
	
	@Test
	public void testSortiereKarte14(){
		assertEquals(card8, game.sortCard(card8, card4));
	}
	
	@Test
	public void testSortiereKarte15(){
		assertEquals(card8, game.sortCard(card4, card8));
	}
	
	@Test
	public void testSortiereKarte16(){
		assertEquals(card2, game2.sortCard(card4, card2));
	}
	
	@Test
	public void testSortiereKarte17(){
		assertEquals(card2, game2.sortCard(card2, card4));
	}
	
	@Test
	public void testSortiereKarte18(){
		assertEquals(card4, game.sortCard(card9, card4));
	}
	
	@Test
	public void testSortiereKarte19(){
		assertEquals(card4, game.sortCard(card4, card9));
	}
}
