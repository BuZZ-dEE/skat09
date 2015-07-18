package skat09.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import skat09.spielart.Ramsch;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.PlayingCard;
import skat09.spielkarte.Value;


public class RamschTest {

	Ramsch ramsch = new Ramsch();
	
	@Test
	public void gespielteKartePruefen() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		blatt.add(new PlayingCard(Farbe.PIK, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard zuPruefendeKarte = new PlayingCard(Farbe.KREUZ, Value.ASS);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		blatt.add(new PlayingCard(Farbe.PIK, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Farbe.KREUZ, Value.ASS);
		assertFalse(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		blatt.add(new PlayingCard(Farbe.PIK, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard zuPruefendeKarte = new PlayingCard(Farbe.HERZ, Value.BUBE);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void gespielteKartePruefen4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		blatt.add(new PlayingCard(Farbe.PIK, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Farbe.KREUZ, Value.SIEBEN);
		PlayingCard zuPruefendeKarte = new PlayingCard(Farbe.KREUZ, Value.ASS);
		assertTrue(ramsch.gespielteKartePruefen(blatt, gespielteKarten, zuPruefendeKarte));
	}
	
	@Test
	public void bubeBedienenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard pruefen = new PlayingCard(Farbe.HERZ, Value.BUBE);
		assertTrue(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}

	@Test
	public void bubeBedienenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard pruefen = new PlayingCard(Farbe.HERZ, Value.ASS);
		assertTrue(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void bubeBedienenTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Farbe.PIK, Value.ACHT));
		blatt.add(new PlayingCard(Farbe.PIK, Value.NEUN));
		blatt.add(new PlayingCard(Farbe.HERZ, Value.DAME));
		blatt.add(new PlayingCard(Farbe.PIK, Value.BUBE));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard pruefen = new PlayingCard(Farbe.HERZ, Value.ASS);
		assertFalse(ramsch.bubeBedienen(blatt, gespielteKarten, pruefen));
	}
	
	@Test
	public void hoehereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.HERZ, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Farbe.KARO, Value.BUBE);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest2() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.HERZ, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.ACHT);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest3() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.HERZ, Value.SIEBEN);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.BUBE);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest4() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.HERZ, Value.DAME);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.KOENIG);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest5() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.PIK, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.BUBE);
		assertEquals(karte2, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void hoehereKarteTest6() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.HERZ, Value.SIEBEN);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.SECHS);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	
	//Letzter ELSE-Fall, macht das so Sinn?
	@Test
	public void hoehereKarteTest7() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KARO, Value.NEUN);
		PlayingCard karte2 = new PlayingCard(Farbe.HERZ, Value.DAME);
		assertEquals(karte1, ramsch.hoehereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarteTest() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Farbe.PIK, Value.BUBE);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte2Test() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KREUZ, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Farbe.PIK, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte3Test() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KREUZ, Value.NEUN);
		PlayingCard karte2 = new PlayingCard(Farbe.PIK, Value.BUBE);
		assertEquals(karte2, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte4Test() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KREUZ, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Farbe.PIK, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte5Test() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.KARO, Value.BUBE);
		PlayingCard karte2 = new PlayingCard(Farbe.KARO, Value.ASS);
		assertEquals(karte1, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void sortiereKarte6Test() {
		
		PlayingCard karte1 = new PlayingCard(Farbe.PIK, Value.ZEHN);
		PlayingCard karte2 = new PlayingCard(Farbe.PIK, Value.ASS);
		assertEquals(karte2, ramsch.sortiereKarte(karte1, karte2));
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("Ramsch", ramsch.toString());
	}
	
	@Test
	public void karteBewertenTest() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.SIEBEN);
		assertEquals(7, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest2() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.ACHT);
		assertEquals(8, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest3() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.NEUN);
		assertEquals(9, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest4() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.ZEHN);
		assertEquals(10, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest5() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.DAME);
		assertEquals(11, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest6() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.KOENIG);
		assertEquals(12, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest7() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.ASS);
		assertEquals(13, ramsch.karteBewerten(karte));
	}
	
	@Test
	public void karteBewertenTest8() {
		
		PlayingCard karte = new PlayingCard(Farbe.HERZ, Value.BUBE);
		assertEquals(15, ramsch.karteBewerten(karte));
	}
}
