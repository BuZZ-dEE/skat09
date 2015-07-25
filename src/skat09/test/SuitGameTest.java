package skat09.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;

public class SuitGameTest {
	Suit a = Suit.HEARTS;
	SuitGame spiel = new SuitGame(a);
	Suit b = Suit.LEAVES;
	SuitGame spiel2 = new SuitGame(b);
	PlayingCard karte1;
	PlayingCard karte2;
	PlayingCard karte3;
	PlayingCard karte4;
	PlayingCard karte5;
	PlayingCard karte6;
	PlayingCard karte7;
	PlayingCard karte8;
	PlayingCard karte9;
	PlayingCard karte10;
	ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> blatt2 = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> blatt3 = new ArrayList<PlayingCard>();
	PlayingCard[] tischkarten = new PlayingCard[3];

	@Before
	public void setUp() {
		karte1 = new PlayingCard(Suit.HEARTS, Value.KING);
		karte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		karte3 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		karte4 = new PlayingCard(Suit.BELLS, Value.KING);
		karte5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		karte6 = new PlayingCard(Suit.ACORNS, Value.NINE);
		karte7 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		karte8 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		karte9 = new PlayingCard(Suit.BELLS, Value.NINE);
		
		
		blatt.add(karte2);
		blatt.add(karte6);
		tischkarten[0] = karte3;
		
		blatt2.add(karte5);
		blatt2.add(karte4);
		
		blatt3.add(karte6);
		blatt3.add(karte7);
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
		assertEquals(Suit.HEARTS, spiel.getTrumpSuit());
	}

	@Test
	public void testGespielteKartepruefen() {
		assertEquals(true, spiel.checkedPlayedCards(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testGespielteKartepruefen2() {
		assertEquals(false, spiel.checkedPlayedCards(blatt, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen3() {
		assertEquals(true, spiel.checkedPlayedCards(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen4() {
		assertEquals(true , spiel.checkedPlayedCards(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen5() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.checkedPlayedCards(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen6() {
		tischkarten[0] = karte1;
		assertEquals(false, spiel.checkedPlayedCards(blatt, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen7() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.checkedPlayedCards(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testGespielteKartepruefen8() {
		tischkarten[0] = karte6;
		assertEquals(false, spiel.checkedPlayedCards(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen9() {
		tischkarten[0] = karte6;
		assertEquals(true, spiel.checkedPlayedCards(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen10() {
		tischkarten[0] = null;
		assertEquals(true, spiel.checkedPlayedCards(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen11() {
		tischkarten[0] = karte3;
		assertEquals(false, spiel.checkedPlayedCards(blatt3, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen12() {
		tischkarten[0] = karte4;
		assertEquals(true, spiel.checkedPlayedCards(blatt3, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen13() {
		tischkarten[0] = karte4;
		blatt.add(karte3);
		assertEquals(true, spiel.checkedPlayedCards(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testHoehereKarte(){
		assertEquals(karte1, spiel.higherCard(karte1, karte2));
		
	}
	
	@Test
	public void testHoehereKarte2(){
		assertEquals(karte3, spiel.higherCard(karte1, karte3));
		
	}
	
	@Test
	public void testHoehereKarte3(){
		assertEquals(karte1, spiel.higherCard(karte1, karte4));
		
	}
	
	@Test
	public void testHoehereKarte4(){
		assertEquals(karte6, spiel.higherCard(karte6, karte4));
		
	}
	
	@Test
	public void testHoehereKarte5(){
		assertEquals(karte7, spiel.higherCard(karte3, karte7));
		
	}
	
	@Test
	public void testHoehereKarte6(){
		assertEquals(karte7, spiel.higherCard(karte7, karte3));
		
	}
	
	@Test
	public void testHoehereKarte8(){
		assertEquals(karte3, spiel.higherCard(karte3, karte1));
		
	}
	
	@Test
	public void testHoehereKarte9(){
		assertEquals(karte1, spiel.higherCard(karte2, karte1));
		
	}
	
	@Test
	public void testHoehereKarte10(){
		assertEquals(karte1, spiel.higherCard(karte4, karte1));
		
	}
	
	@Test
	public void testHoehereKarte11(){
		assertEquals(karte5, spiel.higherCard(karte5, karte6));
		
	}
	
	@Test
	public void testHoehereKarte12(){
		assertEquals(karte5, spiel.higherCard(karte6, karte5));
		
	}
	
	@Test
	public void testSortiereKarte(){
		assertEquals(karte2, spiel.sortCard(karte5, karte2));
	}
	
	@Test
	public void testSortiereKarte2(){
		assertEquals(karte7, spiel.sortCard(karte3, karte7));
	}
	
	@Test
	public void testSortiereKarte3(){
		assertEquals(karte3, spiel.sortCard(karte3, karte2));
	}
	
	@Test
	public void testSortiereKarte4(){
		assertEquals(karte3, spiel.sortCard(karte3, karte5));
	}
	
	@Test
	public void testSortiereKarte5(){
		assertEquals(karte1, spiel.sortCard(karte1, karte2));
	}
	
	@Test
	public void testSortiereKarte6(){
		assertEquals(karte5, spiel.sortCard(karte5, karte4));
	}
	
	@Test
	public void testSortiereKarte7(){
		assertEquals(karte7, spiel.sortCard(karte7, karte3));
	}
	
	@Test
	public void testSortiereKarte8(){
		assertEquals(karte3, spiel.sortCard(karte5, karte3));
	}
	
	@Test
	public void testSortiereKarte9(){
		assertEquals(karte3, spiel.sortCard(karte2, karte3));
	}

	@Test
	public void testSortiereKarte10(){
		assertEquals(karte1, spiel.sortCard(karte2, karte1));
	}
	
	@Test
	public void testSortiereKarte11(){
		assertEquals(karte2, spiel.sortCard(karte2, karte5));
	}
	
	@Test
	public void testSortiereKarte12(){
		assertEquals(karte5, spiel.sortCard(karte5, karte6));
	}
	
	@Test
	public void testSortiereKarte13(){
		assertEquals(karte5, spiel.sortCard(karte6, karte5));
	}
	
	@Test
	public void testSortiereKarte14(){
		assertEquals(karte8, spiel.sortCard(karte8, karte4));
	}
	
	@Test
	public void testSortiereKarte15(){
		assertEquals(karte8, spiel.sortCard(karte4, karte8));
	}
	
	@Test
	public void testSortiereKarte16(){
		assertEquals(karte2, spiel2.sortCard(karte4, karte2));
	}
	
	@Test
	public void testSortiereKarte17(){
		assertEquals(karte2, spiel2.sortCard(karte2, karte4));
	}
	
	@Test
	public void testSortiereKarte18(){
		assertEquals(karte4, spiel.sortCard(karte9, karte4));
	}
	
	@Test
	public void testSortiereKarte19(){
		assertEquals(karte4, spiel.sortCard(karte4, karte9));
	}
}
