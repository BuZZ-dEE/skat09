package skat09.test;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import skat09.spielart.Farbspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;

public class FarbspielTest {
	Farbe a = Farbe.HERZ;
	Farbspiel spiel = new Farbspiel(a);
	Farbe b = Farbe.PIK;
	Farbspiel spiel2 = new Farbspiel(b);
	Spielkarte karte1;
	Spielkarte karte2;
	Spielkarte karte3;
	Spielkarte karte4;
	Spielkarte karte5;
	Spielkarte karte6;
	Spielkarte karte7;
	Spielkarte karte8;
	Spielkarte karte9;
	Spielkarte karte10;
	ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
	ArrayList<Spielkarte> blatt2 = new ArrayList<Spielkarte>();
	ArrayList<Spielkarte> blatt3 = new ArrayList<Spielkarte>();
	Spielkarte[] tischkarten = new Spielkarte[3];

	@Before
	public void setUp() {
		karte1 = new Spielkarte(Farbe.HERZ, Wert.KOENIG);
		karte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		karte3 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		karte4 = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		karte5 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		karte6 = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		karte7 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		karte8 = new Spielkarte(Farbe.PIK, Wert.ASS);
		karte9 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		
		
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
		assertEquals(Farbe.KARO, new Farbspiel(Farbe.KARO).getTrumpffarbe());
	}

	@Test
	public void FarbspielTest2() {
		assertEquals(Spielartbezeichnung.FARBE, new Farbspiel(Farbe.KARO)
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
