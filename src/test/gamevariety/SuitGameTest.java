package test.gamevariety;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GameVariety;
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
	public void SuitGameTest3() {
		assertEquals(Suit.BELLS, new SuitGame(Suit.BELLS).getTrumpSuit());
	}

	@Test
	public void SuitGameTest2() {
		assertEquals(GameVariety.Name.SUIT, new SuitGame(Suit.BELLS)
				.getGameVariety());
	}

	@Test
	public void testgetTrumpSuit() {
		assertEquals(Suit.HEARTS, game.getTrumpSuit());
	}

	@Test
	public void testCheckedPlayedCards() {
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testCheckedPlayedCards2() {
		assertEquals(false, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards3() {
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testCheckedPlayedCards4() {
		assertEquals(true , game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testCheckedPlayedCards5() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testCheckedPlayedCards6() {
		tableCards[0] = card1;
		assertEquals(false, game.checkedPlayedCards(hand, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards7() {
		tableCards[0] = card1;
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testCheckedPlayedCards8() {
		tableCards[0] = card6;
		assertEquals(false, game.checkedPlayedCards(hand2, tableCards, card4));
	}
	
	@Test
	public void testCheckedPlayedCards9() {
		tableCards[0] = card6;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testCheckedPlayedCards10() {
		tableCards[0] = null;
		assertEquals(true, game.checkedPlayedCards(hand2, tableCards, card5));
	}
	
	@Test
	public void testCheckedPlayedCards11() {
		tableCards[0] = card3;
		assertEquals(false, game.checkedPlayedCards(hand3, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards12() {
		tableCards[0] = card4;
		assertEquals(true, game.checkedPlayedCards(hand3, tableCards, card6));
	}
	
	@Test
	public void testCheckedPlayedCards13() {
		tableCards[0] = card4;
		hand.add(card3);
		assertEquals(true, game.checkedPlayedCards(hand, tableCards, card2));
	}
	
	@Test
	public void testHigherCard(){
		assertEquals(card1, game.higherCard(card1, card2));
		
	}
	
	@Test
	public void testHigherCard2(){
		assertEquals(card3, game.higherCard(card1, card3));
		
	}
	
	@Test
	public void testHigherCard3(){
		assertEquals(card1, game.higherCard(card1, card4));
		
	}
	
	@Test
	public void testHigherCard4(){
		assertEquals(card6, game.higherCard(card6, card4));
		
	}
	
	@Test
	public void testHigherCard5(){
		assertEquals(card7, game.higherCard(card3, card7));
		
	}
	
	@Test
	public void testHigherCard6(){
		assertEquals(card7, game.higherCard(card7, card3));
		
	}
	
	@Test
	public void testHigherCard8(){
		assertEquals(card3, game.higherCard(card3, card1));
		
	}
	
	@Test
	public void testHigherCard9(){
		assertEquals(card1, game.higherCard(card2, card1));
		
	}
	
	@Test
	public void testHigherCard10(){
		assertEquals(card1, game.higherCard(card4, card1));
		
	}
	
	@Test
	public void testHigherCard11(){
		assertEquals(card5, game.higherCard(card5, card6));
		
	}
	
	@Test
	public void testHigherCard12(){
		assertEquals(card5, game.higherCard(card6, card5));
		
	}
	
	@Test
	public void testSortCard(){
		assertEquals(card2, game.sortCard(card5, card2));
	}
	
	@Test
	public void testSortCard2(){
		assertEquals(card7, game.sortCard(card3, card7));
	}
	
	@Test
	public void testSortCard3(){
		assertEquals(card3, game.sortCard(card3, card2));
	}
	
	@Test
	public void testSortCard4(){
		assertEquals(card3, game.sortCard(card3, card5));
	}
	
	@Test
	public void testSortCard5(){
		assertEquals(card1, game.sortCard(card1, card2));
	}
	
	@Test
	public void testSortCard6(){
		assertEquals(card5, game.sortCard(card5, card4));
	}
	
	@Test
	public void testSortCard7(){
		assertEquals(card7, game.sortCard(card7, card3));
	}
	
	@Test
	public void testSortCard8(){
		assertEquals(card3, game.sortCard(card5, card3));
	}
	
	@Test
	public void testSortCard9(){
		assertEquals(card3, game.sortCard(card2, card3));
	}

	@Test
	public void testSortCard10(){
		assertEquals(card1, game.sortCard(card2, card1));
	}
	
	@Test
	public void testSortCard11(){
		assertEquals(card2, game.sortCard(card2, card5));
	}
	
	@Test
	public void testSortCard12(){
		assertEquals(card5, game.sortCard(card5, card6));
	}
	
	@Test
	public void testSortCard13(){
		assertEquals(card5, game.sortCard(card6, card5));
	}
	
	@Test
	public void testSortCard14(){
		assertEquals(card8, game.sortCard(card8, card4));
	}
	
	@Test
	public void testSortCard15(){
		assertEquals(card8, game.sortCard(card4, card8));
	}
	
	@Test
	public void testSortCard16(){
		assertEquals(card2, game2.sortCard(card4, card2));
	}
	
	@Test
	public void testSortCard17(){
		assertEquals(card2, game2.sortCard(card2, card4));
	}
	
	@Test
	public void testSortCard18(){
		assertEquals(card4, game.sortCard(card9, card4));
	}
	
	@Test
	public void testSortCard19(){
		assertEquals(card4, game.sortCard(card4, card9));
	}
}
