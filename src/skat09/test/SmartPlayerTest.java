package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import skat09.Table;
import skat09.spielart.SuitGame;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spielart.Ramsch;
import skat09.spieler.Granny;
import skat09.spieler.Position;
import skat09.spieler.RuleCompliantPlayer;
import skat09.spieler.SmartPlayer;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.IPlayer;


public class SmartPlayerTest {

	SmartPlayer spieler = new SmartPlayer("Max");
	ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
	Spielkarte spielkarte1;
	Spielkarte spielkarte2;
	Spielkarte spielkarte3;
	Spielkarte spielkarte4;
	Spielkarte spielkarte5;
	Spielkarte[] gespielteKarten = new Spielkarte[3];
	SuitGame spiel = new SuitGame(Farbe.HERZ);
	@Before
	public void setUp() {
		spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		blatt.add(spielkarte5);
		gespielteKarten[0] = spielkarte1;
		
		spieler.setBlatt(blatt);
		spieler.setSpielart(spiel);
	}
	
	@Test
	public void testSchlauerSpieler() {
		assertEquals("Max", new SmartPlayer("Max").getName());
	}
	
	@Test
	public void testSpieleKarte() {
		
	}
	
	@Test
	public void testalleinspielerRauskommenGrand() {
		assertEquals(Wert.BUBE, spieler.alleinspielerRauskommenGrand(gespielteKarten).getWert());
	}
	
	@Test
	public void testWert() {
		boolean flag = false;
		ArrayList<Spielkarte> wert = spieler.kartenEinesWertes(blatt, Wert.BUBE);
		if (wert.size() == 2) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	public void testWert2() {
		boolean flag = false;
		ArrayList<Spielkarte> wert = spieler.kartenEinesWertes(blatt, Wert.BUBE);
		for (Spielkarte karte : wert) {
			if (karte.equals(new Spielkarte(Farbe.PIK, Wert.BUBE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	

	@Test
	public void testWert3() {
		boolean flag = false;
		ArrayList<Spielkarte> wert = spieler.kartenEinesWertes(blatt, Wert.BUBE);
		for (Spielkarte karte : wert) {
			if (karte.equals(new Spielkarte(Farbe.KREUZ, Wert.BUBE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void rauskommenNullTest() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIstAlleinspieler(true);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		assertEquals(spielkarte1, spieler.rauskommenNull(gespielteKarten));
	}
	
	@Test
	public void rauskommenNullTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.MITTELHAND);
		spieler.setMitspieler(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	
	@Test
	public void rauskommenNullTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		spieler.setMitspieler(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	@Test
	public void rauskommenNullTest5() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.KARO, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		spieler.setMitspieler(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIstAlleinspieler(true);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		IPlayer gegner = new Granny("gegen");
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		gespielteKarten[0].setBesitzer(mate);
		
		assertEquals(spielkarte2, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		IPlayer gegner = new Granny("gegen");
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		gespielteKarten[0].setBesitzer(gegner);
		
		assertEquals(spielkarte2, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIstAlleinspieler(true);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ACHT);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte spielkarte8 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setMitspieler(mate);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer gegnger = new Granny("gegner");
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		gespielteKarten[0].setBesitzer(gegnger);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte8 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setMitspieler(mate);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer gegnger = new Granny("gegner");
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(gegnger);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}

	@Test
	public void alsDritterKartenSpielenNullTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte8 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setMitspieler(mate);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Nullspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer gegnger = new Granny("gegner");
		gespielteKarten[0] = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(mate);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.NEUN);
		gespielteKarten[1].setBesitzer(gegnger);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest1() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		spieler.setIstAlleinspieler(true);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		gespielteKarten[0].setBesitzer(mate);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		gespielteKarten[0].setBesitzer(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest4() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		gespielteKarten[0].setBesitzer(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		spieler.setIstAlleinspieler(true);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(gegner);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(mate);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(gegner);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(gegner);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(gegner);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void rauskommenTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setIstAlleinspieler(true);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.rauskommen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		mate.setPosition(Position.MITTELHAND);
		IPlayer gegner = new Granny("gegner");
		gegner.setPosition(Position.HINTERHAND);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean erfolg = false;
		if (spielkarte3.getFarbe().equals((spieler.rauskommen(gespielteKarten)).getFarbe())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		mate.setPosition(Position.HINTERHAND);
		IPlayer gegner = new Granny("gegner");
		gegner.setPosition(Position.MITTELHAND);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean erfolg = false;
		if (spielkarte3.getFarbe().equals((spieler.rauskommen(gespielteKarten)).getFarbe())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setIstAlleinspieler(true);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.alsZweiterKarteSpielen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(mate);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(gegner);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("gegner");
		spieler.setMitspieler(mate);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		gespielteKarten[0].setBesitzer(gegner);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setIstAlleinspieler(true);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.alsDritterKarteSpielen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsDritterKarteSpielenTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(wicht);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new Spielkarte(Farbe.PIK, Wert.SIEBEN);
		gespielteKarten[0].setBesitzer(wicht);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte3, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		gespielteKarten[0].setBesitzer(wicht);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.DAME);
		gespielteKarten[1].setBesitzer(mate);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getWert() == Wert.BUBE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getWert() == Wert.BUBE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest3() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getWert() == Wert.BUBE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest4() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ASS);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getWert() == Wert.ASS) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest5() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest6() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ASS);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getWert() == Wert.ZEHN) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest7() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.SIEBEN);
		Spielkarte spielkarte6 = new Spielkarte(Farbe.PIK, Wert.ACHT);
		Spielkarte spielkarte7 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllegespieltenkarten().add(spielkarte6);
		spieler.getAllegespieltenkarten().add(spielkarte7);
		spieler.setBlatt(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		boolean ergebnis = false;
		Spielkarte gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void rauskommenGrandTest() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIstAlleinspieler(true);
		spieler.setDeck(deck);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		
		assertEquals(Wert.BUBE, spieler.rauskommenGrand(gespielteKarten).getWert());
	}
	
	@Test
	public void rauskommenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		IPlayer gegner = new Granny("gegner");
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		spieler.setMitspieler(mate);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spieler.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.rauskommenGrand(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		IPlayer gegner = new Granny("gegner");
		IPlayer mate = new Granny("mate");
		spieler.setMitspieler(mate);
		spieler.setMitspieler(mate);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spieler.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (Farbe.KREUZ == spieler.rauskommenGrand(gespielteKarten).getFarbe()) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest1() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		tisch.erstelleDeck();
		deck = tisch.getDeck();
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ASS);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest1() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));	
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest2() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Grandspiel());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.ASS);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));	
	}
	
	@Test
	public void rauskommenRamsch() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Ramsch());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		assertEquals(spielkarte1, 
				spieler.rauskommenRamsch(gespielteKarten));	
	}
	
	@Test
	public void alsZweiterKarteSpielenRamschTest() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Ramsch());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.NEUN);
		
		assertEquals(spielkarte1, 
				spieler.alsZweiterKarteSpielenRamsch(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenRamschTest() {
		
		Spielkarte spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		Spielkarte spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte spielkarte5 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setBlatt(blatt);
		spieler.setSpielart(new Ramsch());
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.NEUN);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		
		assertEquals(spielkarte1, 
				spieler.alsDritterKarteSpielenRamsch(gespielteKarten));
	}
	
	@Test
	public void gegnerMoeglicheSpielbareKartenTest() {
		
		Table tisch = new Table();
		tisch.erstelleDeck();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		//Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		spieler.setDeck(deck);
		spieler.setIstAlleinspieler(true);
		spieler.setBlatt(new ArrayList<Spielkarte>());
		for (int j = 0; j < 6; j++) {
			spieler.getBlatt().add(deck.remove(0));
		}
		ArrayList<Spielkarte> alleGespielteKarten = new ArrayList<Spielkarte>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = deck.remove(0);
		spieler.setSkat(new ArrayList<Spielkarte>(Arrays.asList(gespielteKarten)));
		
		ArrayList<Spielkarte> ergebnisKarten = new ArrayList<Spielkarte>();
		for (int k = 0; k < deck.size(); k++) {
			
			if(spieler.getSpielart().
					gespielteKartePruefen(deck, gespielteKarten, deck.get(k))) {
				
				ergebnisKarten.add(deck.get(k));
			}
		}
		
		assertEquals(ergebnisKarten, spieler.gegnerMoeglicheSpielbareKarten(gespielteKarten));
	}
	
	@Test
	public void moeglicheGegnerKarteTest() {
		
		Table tisch = new Table();
		tisch.erstelleDeck();
		ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
		Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		spieler.setDeck(deck);
		spieler.setIstAlleinspieler(true);
		spieler.setBlatt(new ArrayList<Spielkarte>());
		for (int j = 0; j < 6; j++) {
			spieler.getBlatt().add(deck.remove(0));
		}
		ArrayList<Spielkarte> alleGespielteKarten = new ArrayList<Spielkarte>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = deck.remove(0);
		spieler.setSkat(new ArrayList<Spielkarte>(Arrays.asList(gespielteKarten)));
	
		assertEquals(deck, 
				spieler.moeglicheGegnerKarten(alleGespielteKarten, gespielteKarten));
	}
	
	@Test
	public void zufallszahlTest() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		int zufallszahl = spieler.zufallszahl(blatt);
		
		boolean ergebnis = false;
		if (zufallszahl >= 0 && zufallszahl < blatt.size()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest1() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.SECHS);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.ACHT);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest3() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.NEUN);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest4() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.DAME);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest5() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.DAME);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest6() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest7() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte hkarte = new Spielkarte(Farbe.KARO, Wert.ASS);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Farbe.KARO, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest8() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ASS);
		
		assertEquals(null, spieler.naechstHoehereKarte(Farbe.KARO, karte));
	}
	
	@Test
	public void naechstHoehereKarteNeunTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.PIK));
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.PIK));
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ASS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.PIK));
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ASS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.ASS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.PIK, Wert.DAME);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Farbe.PIK))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteTest1() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.SECHS);
		
		assertEquals(null, spieler.naechstNiedrigereKarte(Farbe.KARO, karte));
	}
	
	@Test
	public void naechstNiedrigereKarteTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.SECHS);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest3() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest4() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest5() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.DAME);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest6() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.DAME);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest7() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest8() {
		
		Spielkarte karte = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Farbe.KARO, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	@Test
	public void naechstNiedrigereKarteDameTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.KARO));
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.KARO));
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest1() {
		
		spieler.setSpielart(new SuitGame(Farbe.KARO));
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest3() {
		
		spieler.setSpielart(new Nullspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Farbe.KREUZ))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void kartenEinesWertesTest1() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		Spielkarte karte6 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte10 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<Spielkarte> erwartet = new ArrayList<Spielkarte>();
		erwartet.add(karte2);
		erwartet.add(karte3);
		assertEquals(erwartet, spieler.kartenEinesWertes(blatt, Wert.ASS));
	}
	
	@Test
	public void kartenEinerFarbeTest1() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		Spielkarte karte6 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte10 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		karo.add(karte1);
		karo.add(karte2);
		
		assertEquals(karo, spieler.kartenEinerFarbe(blatt, Farbe.KARO));
	}
	
	@Test
	public void kartenEinerFarbeTest2() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		Spielkarte karte6 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte10 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		herz.add(karte3);
		herz.add(karte4);
		herz.add(karte5);
		herz.add(karte6);
		
		assertEquals(herz, spieler.kartenEinerFarbe(blatt, Farbe.HERZ));
	}
	
	@Test
	public void kartenEinerFarbeTest3() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		Spielkarte karte6 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte karte10 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		pik.add(karte7);
		pik.add(karte8);
		pik.add(karte9);
		
		assertEquals(pik, spieler.kartenEinerFarbe(blatt, Farbe.PIK));
	}
	
	@Test
	public void kartenEinerFarbeTest4() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte karte4 = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		Spielkarte karte5 = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		Spielkarte karte6 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte karte10 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		kreuz.add(karte10);
		
		assertEquals(kreuz, spieler.kartenEinerFarbe(blatt, Farbe.KREUZ));
	}
	
	@Test
	public void hoechsteSpielbareKarteTest() {
		
		ArrayList<Spielkarte> spielbareKarten = new ArrayList<Spielkarte>();
		
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		
		spielbareKarten.add(karte1);
		spielbareKarten.add(karte2);
		spielbareKarten.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		spielbareKarten.add(new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spielbareKarten.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		
		assertEquals(karte1, spieler.hoechsteSpielbareKarte(spielbareKarten));
	}
	
	@Test
	public void niedrigsteSpielbareKarteTest() {
		
		ArrayList<Spielkarte> spielbareKarten = new ArrayList<Spielkarte>();
		
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		
		spielbareKarten.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spielbareKarten.add(karte1);
		spielbareKarten.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		spielbareKarten.add(new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spielbareKarten.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		
		assertEquals(karte1, spieler.niedrigsteSpielbareKarte(spielbareKarten));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest1() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.DAME);
		spieler.setSpielart(new Grandspiel());
		
		assertEquals(karte1, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest2() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.DAME);
		spieler.setSpielart(new Nullspiel());
		
		assertEquals(karte2, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest3() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.HERZ, Wert.DAME);
		Spielkarte karte2 = new Spielkarte(Farbe.KARO, Wert.DAME);
		spieler.setSpielart(new Nullspiel());
		
		assertEquals(karte1, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest1() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		
		assertEquals(karte2, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest2() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.ACHT);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		
		assertEquals(karte1, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest3() {
		
		spieler.setSpielart(new Grandspiel());
		Spielkarte karte1 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.NEUN);
		
		assertEquals(karte1, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void drueckenTest() {
		
		Spielkarte[] skat = new Spielkarte[3];
		assertArrayEquals(null, spieler.druecken(skat));
	}
	
	@Test
	public void handspielTest() {
		
		assertTrue(spieler.handspiel());
	}
	
	@Test
	public void ouvertTest() {
		
		assertFalse(spieler.ouvert());
	}
	
	@Test
	public void schneiderTest() {
		
		assertFalse(spieler.schneider());
	}
	
	@Test
	public void schwarzTest() {
		
		assertFalse(spieler.schwarz());
	}
	
	@Test
	public void spielAnsagenTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.PIK, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler.setBlatt(blatt);
		
		assertEquals(new Grandspiel().getSpielart(), 
				spieler.spielAnsagen().getSpielart());
	}
	
	@Test
	public void hoerenTest() {
		
		spieler.setMaxReizwert(23);
		
		assertTrue(spieler.hoeren(18));
	}
	
	@Test
	public void hoerenTest2() {
		
		spieler.setMaxReizwert(23);
		
		assertFalse(spieler.hoeren(26));
	}
	
	@Test
	public void sagenTest() {
		
		
		spieler.setMaxReizwert(23);
		
		assertTrue(spieler.sagen(18));
	}
	
	@Test
	public void sagenTest2() {
		
		
		spieler.setMaxReizwert(23);
		
		assertFalse(spieler.sagen(26));
	}
	
	@Test
	public void farbeTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		spieler.setBlatt(blatt);
		
		assertEquals(new SuitGame(Farbe.HERZ).getSpielart(), 
				spieler.farbe().getSpielart());
	}
	
	@Test
	public void agentTest() {
		
		assertFalse(spieler.agent());
	}
	
	@Test
	public void reizlimitFestlegenTest() {
		
		assertEquals(0, spieler.reizlimitFestlegen());
	}
	
	@Test
	public void bestimmeMaxReizwert1() {
	
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		spieler.setBlatt(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(23, spieler.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert2() {
	
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		spieler.setBlatt(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(24, spieler.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert3() {
	
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		spieler.setBlatt(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(33, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertFarbeTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.KOENIG));
		spieler.setBlatt(blatt);
		spieler.maxReizwertFarbe(1);
		assertEquals(24, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertGrandTest() {
		
		spieler.maxReizwertGrand(1);
		assertEquals(48, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertNullTest() {
		
		spieler.maxReizwertNull();
		assertEquals(23, spieler.getMaxReizwert());
	}
	
	@Test
	public void ermittleSpitzenTest1() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte karte5 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte6 = new Spielkarte(Farbe.KARO, Wert.ZEHN);
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		spieler.setBlatt(blatt);
		
		assertEquals(6, spieler.ermittleSpitzen(new SuitGame(Farbe.KARO)));
	}
	
	@Test
	public void ermittleSpitzenTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		blatt.add(karte1);
		blatt.add(karte2);
		spieler.setBlatt(blatt);
		
		assertEquals(2, spieler.ermittleSpitzen(new Grandspiel()));
	}
	
	@Test
	public void spitzenZaehlenTest1() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte karte5 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte karte6 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte karte10 = new Spielkarte(Farbe.PIK, Wert.ACHT);
		Spielkarte karte11 = new Spielkarte(Farbe.PIK, Wert.SIEBEN);
		Spielkarte karte12 = new Spielkarte(Farbe.PIK, Wert.SECHS);
	
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[5] = karte6;
		spitzen[6] = karte7;
		spitzen[7] = karte8;
		spitzen[8] = karte9;
		spitzen[9] = karte10;
		spitzen[10] = karte11;
		spitzen[11] = karte12;
		
		assertEquals(12, spieler.spitzenZaehlen(spitzen));
	}
	
	@Test
	public void spitzenZaehlenTest2() {
		
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[4] = new Spielkarte(Farbe.HERZ, Wert.ASS);
		
		assertEquals(4, spieler.spitzenZaehlen(spitzen));
	}
	@Test
	public void ohneTest1() {
		
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[12] = new Spielkarte(Farbe.PIK, Wert.SECHS);
		assertEquals(12, spieler.ohne(spitzen));
	}
	
	@Test
	public void ohneTest2() {
		
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[3] = new Spielkarte(Farbe.KARO, Wert.BUBE);
		assertEquals(3, spieler.ohne(spitzen));
	}
	
	@Test
	public void mitTest1() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.BUBE);
		Spielkarte karte5 = new Spielkarte(Farbe.PIK, Wert.ASS);
		Spielkarte karte6 = new Spielkarte(Farbe.PIK, Wert.ZEHN);
		Spielkarte karte7 = new Spielkarte(Farbe.PIK, Wert.KOENIG);
		Spielkarte karte8 = new Spielkarte(Farbe.PIK, Wert.DAME);
		Spielkarte karte9 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		Spielkarte karte10 = new Spielkarte(Farbe.PIK, Wert.ACHT);
		Spielkarte karte11 = new Spielkarte(Farbe.PIK, Wert.SIEBEN);
		Spielkarte karte12 = new Spielkarte(Farbe.PIK, Wert.SECHS);
	
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[5] = karte6;
		spitzen[6] = karte7;
		spitzen[7] = karte8;
		spitzen[8] = karte9;
		spitzen[9] = karte10;
		spitzen[10] = karte11;
		spitzen[11] = karte12;
		
		assertEquals(12, spieler.mit(spitzen));
	}
	
	@Test
	public void mitTest2() {
		
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);

		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		
		assertEquals(3, spieler.mit(spitzen));
	}
	
	@Test
	public void farbeSpitzenTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.BUBE); 
		Spielkarte karte5 = new Spielkarte(Farbe.KARO, Wert.ASS);
		Spielkarte karte6 = new Spielkarte(Farbe.KARO, Wert.DAME);
		Spielkarte karte7 = new Spielkarte(Farbe.KARO, Wert.NEUN);
		
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
	
		Spielkarte[] spitzen = new Spielkarte[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[7] = karte6;
		spitzen[8] = karte7;
		spieler.setBlatt(blatt);
		
		Spielkarte[] spielerSpitzen = spieler.farbeSpitzen(new SuitGame(Farbe.KARO));
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest1() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.BUBE);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.BUBE); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
	
		Spielkarte[] spitzen = {karte1, karte2, karte3, karte4};
		
		spieler.setBlatt(blatt);
		
		Spielkarte[] spielerSpitzen = spieler.grandSpitzen(new Grandspiel());
		
		boolean ergebnis = true;
		for (int i = 0; i < 4; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				ergebnis = false;
			}
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void grandSpitzenTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.SECHS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.SECHS); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		
		spieler.setBlatt(blatt);
		
		Spielkarte[] spielerSpitzen = spieler.grandSpitzen(new Grandspiel());
		
		Spielkarte[] spitzen = {karte1, null, null, null};
		
		boolean ergebnis = true;
		for (int i = 0; i < 1; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				ergebnis = false;
			}
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void grandSpitzenTest3() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.SECHS);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.SECHS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.SECHS); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		
		spieler.setBlatt(blatt);
		
		Spielkarte[] spielerSpitzen = spieler.grandSpitzen(new Grandspiel());
		
		Spielkarte[] spitzen = {null, null, null, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest4() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.SECHS);
		Spielkarte karte2 = new Spielkarte(Farbe.PIK, Wert.SECHS);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte karte4 = new Spielkarte(Farbe.KARO, Wert.SECHS); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		
		spieler.setBlatt(blatt);
		
		Spielkarte[] spielerSpitzen = spieler.grandSpitzen(new Grandspiel());
		
		Spielkarte[] spitzen = {null, null, karte3, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void bestimmeSpielartTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		spieler.setBlatt(blatt);
		
		assertEquals(new Nullspiel().getSpielart(), 
				spieler.bestimmeSpielart().getSpielart());
	}
	
	@Test
	public void bestimmeSpielartTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		spieler.setBlatt(blatt);
		
		assertEquals(new Grandspiel().getSpielart(), 
				spieler.bestimmeSpielart().getSpielart());
	}
	
	@Test
	public void bestimmeSpielartTest3() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		spieler.setBlatt(blatt);
		
		assertEquals(new SuitGame(Farbe.PIK).getSpielart(), 
				spieler.bestimmeSpielart().getSpielart());
	}
	
	@Test
	public void bestimmeSpielartTest4() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		spieler.setBlatt(blatt);
		
		assertEquals(new SuitGame(Farbe.PIK).getSpielart(), 
				spieler.bestimmeSpielart().getSpielart());
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		blatt.add(karte1);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		blatt.add(karte2);
		Spielkarte karte3 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		blatt.add(karte3);
		Spielkarte karte4 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		blatt.add(karte4);
		spieler.setBlatt(blatt);
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		kreuz.add(karte1);
		kreuz.add(karte2);
		kreuz.add(karte3);
		kreuz.add(karte4);
		
		assertEquals(kreuz, spieler.ermittleKurzeLangeFarbe(true));
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		Spielkarte karte5 = new Spielkarte(Farbe.PIK, Wert.DAME); 
		blatt.add(karte5);
		Spielkarte karte1 = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		blatt.add(karte1);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.ZEHN);
		blatt.add(karte2);
		Spielkarte karte3 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		blatt.add(karte3);
		Spielkarte karte4 = new Spielkarte(Farbe.KREUZ, Wert.ACHT);
		blatt.add(karte4);
		spieler.setBlatt(blatt);
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		pik.add(karte5);
		
		assertEquals(pik, spieler.ermittleKurzeLangeFarbe(false));
	}
	
	@Test
	public void ermittleKurzeFarbeTest() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(karo, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest2() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(herz, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest3() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(pik, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest4() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(kreuz, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest1() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(karo, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest2() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(herz, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest3() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		herz.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		pik.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(pik, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest4() {
		
		ArrayList<Spielkarte> karo = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> herz = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> pik = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> kreuz = new ArrayList<Spielkarte>();
		
		karo.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		kreuz.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		
		assertEquals(kreuz, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleTrumpffarbeTest1() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		spieler.setBlatt(blatt);
		
		assertEquals(Farbe.PIK, spieler.ermittleTrumpffarbe());
	}

	@Test
	public void ermittleTrumpffarbeTest2() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		spieler.setBlatt(blatt);
		
		assertEquals(Farbe.KREUZ, spieler.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest3() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		spieler.setBlatt(blatt);
		
		assertEquals(Farbe.KARO, spieler.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest4() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		spieler.setBlatt(blatt);
		
		assertEquals(Farbe.HERZ, spieler.ermittleTrumpffarbe());
	}
	
	@Test
	public void werteAugenTest() {
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.DAME));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.KOENIG));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		
		assertEquals(36, spieler.werteAugen(blatt));
	}

	@Test
	public void augenKarteTest1() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		
		assertEquals(6, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest2() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest3() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest4() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest5() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.ZEHN);
		
		assertEquals(10, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest6() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		
		assertEquals(2, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest7() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.DAME);
		
		assertEquals(3, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest8() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.KOENIG);
		
		assertEquals(4, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest9() {
		
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.ASS);
		
		assertEquals(11, spieler.augenKarte(karte));
	}
}
