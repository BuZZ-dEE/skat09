package skat09.test;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.SuitGame;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;

public class SuitGameTest {
	Farbe a = Farbe.HERZ;
	SuitGame spiel = new SuitGame(a);
	Farbe b = Farbe.PIK;
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
		karte1 = new PlayingCard(Farbe.HERZ, Value.KOENIG);
		karte2 = new PlayingCard(Farbe.HERZ, Value.ACHT);
		karte3 = new PlayingCard(Farbe.KARO, Value.BUBE);
		karte4 = new PlayingCard(Farbe.KARO, Value.KOENIG);
		karte5 = new PlayingCard(Farbe.KREUZ, Value.ASS);
		karte6 = new PlayingCard(Farbe.KREUZ, Value.NEUN);
		karte7 = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		karte8 = new PlayingCard(Farbe.PIK, Value.ASS);
		karte9 = new PlayingCard(Farbe.KARO, Value.NEUN);
		
		
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
		assertEquals(Farbe.KARO, new SuitGame(Farbe.KARO).getTrumpffarbe());
	}

	@Test
	public void FarbspielTest2() {
		assertEquals(Spielartbezeichnung.FARBE, new SuitGame(Farbe.KARO)
				.getSpielart());
	}

	@Test
	public void testgetTrumpfFarbe() {
		assertEquals(Farbe.HERZ, spiel.getTrumpffarbe());
	}

	@Test
	public void testGespielteKartepruefen() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testGespielteKartepruefen2() {
		assertEquals(false, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen3() {
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen4() {
		assertEquals(true , spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen5() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen6() {
		tischkarten[0] = karte1;
		assertEquals(false, spiel.gespielteKartePruefen(blatt, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen7() {
		tischkarten[0] = karte1;
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testGespielteKartepruefen8() {
		tischkarten[0] = karte6;
		assertEquals(false, spiel.gespielteKartePruefen(blatt2, tischkarten, karte4));		
	}
	
	@Test
	public void testGespielteKartepruefen9() {
		tischkarten[0] = karte6;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen10() {
		tischkarten[0] = null;
		assertEquals(true, spiel.gespielteKartePruefen(blatt2, tischkarten, karte5));		
	}
	
	@Test
	public void testGespielteKartepruefen11() {
		tischkarten[0] = karte3;
		assertEquals(false, spiel.gespielteKartePruefen(blatt3, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen12() {
		tischkarten[0] = karte4;
		assertEquals(true, spiel.gespielteKartePruefen(blatt3, tischkarten, karte6));		
	}
	
	@Test
	public void testGespielteKartepruefen13() {
		tischkarten[0] = karte4;
		blatt.add(karte3);
		assertEquals(true, spiel.gespielteKartePruefen(blatt, tischkarten, karte2));		
	}
	
	@Test
	public void testHoehereKarte(){
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte2));
		
	}
	
	@Test
	public void testHoehereKarte2(){
		assertEquals(karte3, spiel.hoehereKarte(karte1, karte3));
		
	}
	
	@Test
	public void testHoehereKarte3(){
		assertEquals(karte1, spiel.hoehereKarte(karte1, karte4));
		
	}
	
	@Test
	public void testHoehereKarte4(){
		assertEquals(karte6, spiel.hoehereKarte(karte6, karte4));
		
	}
	
	@Test
	public void testHoehereKarte5(){
		assertEquals(karte7, spiel.hoehereKarte(karte3, karte7));
		
	}
	
	@Test
	public void testHoehereKarte6(){
		assertEquals(karte7, spiel.hoehereKarte(karte7, karte3));
		
	}
	
	@Test
	public void testHoehereKarte8(){
		assertEquals(karte3, spiel.hoehereKarte(karte3, karte1));
		
	}
	
	@Test
	public void testHoehereKarte9(){
		assertEquals(karte1, spiel.hoehereKarte(karte2, karte1));
		
	}
	
	@Test
	public void testHoehereKarte10(){
		assertEquals(karte1, spiel.hoehereKarte(karte4, karte1));
		
	}
	
	@Test
	public void testHoehereKarte11(){
		assertEquals(karte5, spiel.hoehereKarte(karte5, karte6));
		
	}
	
	@Test
	public void testHoehereKarte12(){
		assertEquals(karte5, spiel.hoehereKarte(karte6, karte5));
		
	}
	
	@Test
	public void testSortiereKarte(){
		assertEquals(karte2, spiel.sortiereKarte(karte5, karte2));
	}
	
	@Test
	public void testSortiereKarte2(){
		assertEquals(karte7, spiel.sortiereKarte(karte3, karte7));
	}
	
	@Test
	public void testSortiereKarte3(){
		assertEquals(karte3, spiel.sortiereKarte(karte3, karte2));
	}
	
	@Test
	public void testSortiereKarte4(){
		assertEquals(karte3, spiel.sortiereKarte(karte3, karte5));
	}
	
	@Test
	public void testSortiereKarte5(){
		assertEquals(karte1, spiel.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void testSortiereKarte6(){
		assertEquals(karte5, spiel.sortiereKarte(karte5, karte4));
	}
	
	@Test
	public void testSortiereKarte7(){
		assertEquals(karte7, spiel.sortiereKarte(karte7, karte3));
	}
	
	@Test
	public void testSortiereKarte8(){
		assertEquals(karte3, spiel.sortiereKarte(karte5, karte3));
	}
	
	@Test
	public void testSortiereKarte9(){
		assertEquals(karte3, spiel.sortiereKarte(karte2, karte3));
	}

	@Test
	public void testSortiereKarte10(){
		assertEquals(karte1, spiel.sortiereKarte(karte2, karte1));
	}
	
	@Test
	public void testSortiereKarte11(){
		assertEquals(karte2, spiel.sortiereKarte(karte2, karte5));
	}
	
	@Test
	public void testSortiereKarte12(){
		assertEquals(karte5, spiel.sortiereKarte(karte5, karte6));
	}
	
	@Test
	public void testSortiereKarte13(){
		assertEquals(karte5, spiel.sortiereKarte(karte6, karte5));
	}
	
	@Test
	public void testSortiereKarte14(){
		assertEquals(karte8, spiel.sortiereKarte(karte8, karte4));
	}
	
	@Test
	public void testSortiereKarte15(){
		assertEquals(karte8, spiel.sortiereKarte(karte4, karte8));
	}
	
	@Test
	public void testSortiereKarte16(){
		assertEquals(karte2, spiel2.sortiereKarte(karte4, karte2));
	}
	
	@Test
	public void testSortiereKarte17(){
		assertEquals(karte2, spiel2.sortiereKarte(karte2, karte4));
	}
	
	@Test
	public void testSortiereKarte18(){
		assertEquals(karte4, spiel.sortiereKarte(karte9, karte4));
	}
	
	@Test
	public void testSortiereKarte19(){
		assertEquals(karte4, spiel.sortiereKarte(karte4, karte9));
	}
}
