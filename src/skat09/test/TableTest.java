package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import skat09.Controller;
import skat09.SkatVariant;
import skat09.Table;
import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spielart.Ramsch;
import skat09.spielart.Spielart;
import skat09.spieler.HumanPlayer;
import skat09.spieler.Granny;
import skat09.spieler.Position;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.IPlayer;
import skat09.ui.CLIOutput;


public class TableTest {

	//
	// Datenfelder
	// 

	Spielkarte spielkarte1;
	Spielkarte spielkarte2;
	Spielkarte spielkarte3;
	Spielkarte spielkarte4;
	IPlayer spieler1 = new Granny("Bert");
	IPlayer spieler2 = new Granny("Ernie");

	Table tisch = new Table();
	ArrayList<Spielkarte> deck = new ArrayList<Spielkarte>();
	ArrayList<Spielkarte> spitzen = new ArrayList<Spielkarte>();
	CLIOutput ausgabe = new CLIOutput(tisch);
	IController controller = new Controller(tisch, ausgabe);
	IPlayer spieler3 = new HumanPlayer("Hans", controller);
	Spielkarte[] skat = new Spielkarte[3];

	@Before
	public void setUp() {
		
		spielkarte1 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		spielkarte2 = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		spielkarte3 = new Spielkarte(Farbe.PIK, Wert.NEUN);
		spielkarte4 = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		spieler1.setPosition(Position.VORHAND);
		spieler2.setPosition(Position.MITTELHAND);
		spieler3.setPosition(Position.HINTERHAND);
		tisch.setSpieler1(spieler1);
		tisch.setSpieler2(spieler2);
		tisch.setSpieler3(spieler3);
		tisch.getSpieler1().setIstAlleinspieler(true);
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		tisch.getSpieler1().getBlatt().clear();
		tisch.getSpieler1().getBlatt().add(spielkarte1);
		tisch.getSpieler1().getBlatt().add(spielkarte2);
		tisch.getSpieler1().getBlatt().add(spielkarte3);
	    tisch.getSpieler2().getBlatt().clear();
	    tisch.getSpieler2().getBlatt().add(spielkarte4);
	    tisch.getSpieler3().getBlatt().clear();
	    tisch.getSpieler3().getBlatt().add(spielkarte3);
	    skat[0] = null;
	    skat[1] = null;
	    tisch.setSkat(skat);
		
		for (Farbe farbe : Farbe.values()) {
			for (Wert wert : Wert.values()) {
				Spielkarte karte = new Spielkarte(farbe, wert);
				deck.add(karte);
			}
		}

		Spielkarte[] gespieltekarten = tisch.getGespielteKarten();
		gespieltekarten[0] = new Spielkarte(Farbe.HERZ, Wert.ASS);
		gespieltekarten[1] = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		gespieltekarten[2] = new Spielkarte(Farbe.KARO, Wert.NEUN);
		tisch.setGespielteKarten(gespieltekarten);
		tisch.getSpieler1().stichHinzufuegen(gespieltekarten);
		tisch.setReizwert(18);
		ISpielart spielart = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spielart);
		tisch.getSpieler1().setSpielart(spielart);
	}

	@After
	public void after() {
		Spielart spielart = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spielart);
	}

	// Konstruktortest fuer Spieler1
	@Test
	public void tischSpieler1Test() {
		assertEquals("Bert", tisch.getSpieler1().getName());
	}

	// Konstruktortest fuer Spieler2
	@Test
	public void tischSpieler2Test() {
		assertEquals("Ernie", tisch.getSpieler2().getName());
	}

	// Konstruktortest fuer Spieler3
	@Test
	public void tischSpieler3Test() {
		assertEquals("Hans", tisch.getSpieler3().getName());
	}
	
	//Testet die Methode getHandspiel
	@Test
	public void getHandspiel1Test() {
		tisch.setHandspiel(true);
		assertTrue(tisch.getHandspiel());
	}
	
	//Testet die Methode getHandspiel
	@Test
	public void getHandspiel2Test() {
		tisch.setHandspiel(false);
		assertFalse(tisch.getHandspiel());
	}
	
	//Testet die Methode getSpielart
	@Test
	public void getSpielartTest() {
		ISpielart spielart = new Grandspiel();
		tisch.setSpielart(spielart);
		assertEquals(spielart, tisch.getSpielart());
	}
	
	//Testet die Methode getVariante
	@Test
	public void getVarianteTest() {
		tisch.setVariante(SkatVariant.RAEUBER);
		assertEquals(SkatVariant.RAEUBER, tisch.getVariante());
	}
	
	//Testet die Methode getReizwert
	@Test
	public void getReizwertTest() {
		tisch.setReizwert(35);
		assertEquals(35, tisch.getReizwert());
	}
	
	
	//Testet die Methode getReizagentWert
	@Test
	public void reizagentWertTest() {
		tisch.setReizagentWert(42);
		assertEquals(42, tisch.getReizagentWert());
	}
	
	//Testet , ob die Reizwerte korrekt zurueckgegeben werden
	@Test
	public void getReizwerteTest() {
		SortedSet<Integer> reizwerte = new TreeSet<Integer>();
		for (int i = 18; i <= 162; i += 9) {
			reizwerte.add(i);
		}
		// Alle Herz-Reizwerte hinzufuegen
		for (int i = 20; i <= 180; i += 10) {
			reizwerte.add(i);
		}
		// Alle Pik-Reizwerte hinzufuegen
		for (int i = 22; i <= 198; i += 11) {
			reizwerte.add(i);
		}
	// Alle Kreuz-Reizwerte hinzufuegen
		for (int i = 24; i <= 216; i += 12) {
			reizwerte.add(i);
		}
		// Die restlichen Reizwerte von Nullspiel und Grand/Grand-Ouvert
		reizwerte.add(23);
		reizwerte.add(35);
		reizwerte.add(46);
		reizwerte.add(59);
		reizwerte.add(240);
		reizwerte.add(264);
		reizwerte.add(171);
		reizwerte.add(190);
		reizwerte.add(209);
		reizwerte.add(228);
		
		assertEquals(reizwerte, tisch.getReizwerte());
	}
	
	//Testet die Methode getSkat
	@Test
	public void getSkatTest() {
		Spielkarte[] skat = new Spielkarte[2];
		skat[0] = new Spielkarte(Farbe.HERZ, Wert.ASS);
		skat[1] = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		tisch.setSkat(skat);
		
		assertArrayEquals(skat, tisch.getSkat());
	}
	
	// Testet ob das erstellte Deck tatsaechlich alle Karten enthaelt
	@Test
	public void erstelleDeckTest() {
		tisch.setSechserskat(true);
		tisch.erstelleDeck();
		assertEquals(36, tisch.getDeck().size());
	}

	@Test
	public void erstelleDeckTest2() {
		int test = 0;
		tisch.erstelleDeck();
		for (int i = 0; i<deck.size(); i++) {
			for (int j = 0; j<tisch.getDeck().size(); j++) {
				if (deck.get(i).equals(tisch.getDeck().get(j)) == true) {
					test = test + 1;
				}
			}
		}
		assertEquals(32, test);
	}
	
	// Test ob Kartenzahl gleich bleibt
	@Test
	public void mischeDeckTest() {
		tisch.erstelleDeck();
		tisch.mischeDeck();
		assertEquals(32, tisch.getDeck().size());
	}

	/**
	 * In dieser Methode wird &uuml;berpr&uuml;ft, ob jede Karte nur einmal vorhanden
	 * ist.
	 */
	@Test
	public void mischeDeckTest2() {
		// test dient zum durchzaehlen, ob jede Karte nur einmal vorhanden ist
		int test = 0;
		tisch.erstelleDeck();
		tisch.mischeDeck();
		
		for (int i = 0; i<deck.size(); i++) {
			for (int j = 0; j<tisch.getDeck().size(); j++) {
				if (deck.get(i).equals(tisch.getDeck().get(j)) == true) {
					test = test + 1;
				}
			}
		}
		assertEquals(32, test);
	}

	@Test
	public void stichAuswertenTest1() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.BUBE);
		gespielteKarten[2] = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		ISpielart spielart = new Nullspiel();
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler2, tisch.stichAuswerten(spielart, gespielteKarten));
		
	}
	
	@Test
	public void stichAuswertenTest2() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.BUBE);
		gespielteKarten[2] = new Spielkarte(Farbe.KARO, Wert.KOENIG);
		ISpielart spielart = new Nullspiel();
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler3, tisch.stichAuswerten(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest3() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.ASS);
		gespielteKarten[2] = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		ISpielart spielart = new Farbspiel(Farbe.HERZ);
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler3, tisch.stichAuswerten(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest4() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.PIK, Wert.BUBE);
		gespielteKarten[1] = new Spielkarte(Farbe.KARO, Wert.BUBE);
		gespielteKarten[2] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		ISpielart spielart = new Grandspiel();
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler1, tisch.stichAuswerten(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest5() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.BUBE);
		gespielteKarten[2] = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		ISpielart spielart = new Nullspiel();
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler1, tisch.stichAuswerten(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest6() {
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		gespielteKarten[1] = new Spielkarte(Farbe.PIK, Wert.ASS);
		gespielteKarten[2] = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		ISpielart spielart = new Farbspiel(Farbe.KREUZ);
		gespielteKarten[0].setBesitzer(spieler1);
		gespielteKarten[1].setBesitzer(spieler2);
		gespielteKarten[2].setBesitzer(spieler3);
		assertEquals(spieler1, tisch.stichAuswerten(spielart, gespielteKarten));
	}
	
	@Test
	public void KartenAusteilenTest() {
		boolean test = false;
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		if(tisch.getSpieler1().getBlatt().size() == 10){
			test = true;
		}
		assertTrue(test);
	}

	@Test
	public void kartenAusteilenTest2() {
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		assertEquals(10, tisch.getSpieler2().getBlatt().size());
	}

	@Test
	public void kartenAusteilenTest3() {
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		assertEquals(10, tisch.getSpieler3().getBlatt().size());
	}

	@Test
	public void kartenAusteilenTest4() {
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		assertEquals(0, tisch.getDeck().size());
	}
	
	@Test
	public void kartenAusteilenTest5() {
		tisch.setSechserskat(true);
		tisch.erstelleDeck();
		tisch.kartenAusteilen();
		assertEquals(0, tisch.getDeck().size());
	}
	
	@Test
	public void getProzentAlleinTest() {
		tisch.getSpieler1().getSpiele().clear();
		tisch.getSpieler1().getSpiele().add(18);
		//tisch.addAnzahlSpiele();
		
		assertEquals(100, tisch.getProzentAllein(spieler1));
	}
	
	@Test
	public void getAnzahlAlleinTest() {
		tisch.getSpieler1().getSpiele().clear();
		tisch.getSpieler1().getSpiele().add(18);
		//tisch.addAnzahlSpiele();
		
		assertEquals(1, tisch.getAnzahlAllein(spieler1));
	}
	
	@Test
	public void anzahlGewinneTest() {
		tisch.getSpieler1().getSpiele().clear();
		tisch.getSpieler1().getSpiele().add(18);
		assertEquals(1, tisch.anzahlderGewinne(spieler1));
	}

	@Test
	public void ermittleAlleinspielerTest() {
		assertEquals(spieler1, tisch.ermittleAlleinspieler());
	}

	@Test
	public void ermittleAlleinspielerTest2() {
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler2().setIstAlleinspieler(true);
		assertEquals(spieler2, tisch.ermittleAlleinspieler());
	}
	
	@Test
	public void ermittleAlleinspielerTest3() {
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler3().setIstAlleinspieler(true);
		assertEquals(spieler3, tisch.ermittleAlleinspieler());
	}
	
	@Test
	public void werteAugenTest() {
		assertEquals(144, tisch.werteAugen(deck));
	}

	@Test
	public void getVorhandTest() {
		assertEquals(spieler1, tisch.getVorhand());
	}

	@Test
	public void getMittelhandTest() {
		tisch.erstelleDeck();
		assertEquals(spieler2, tisch.getMittelhand());
	}

	@Test
	public void getHinterhandTest() {
		assertEquals(spieler3, tisch.getHinterhand());
	}

	@Test
	public void positionWechselnTest() {
		tisch.positionWechseln();
		int erg = 0;
		if (tisch.getHinterhand().equals(spieler1)) {
			erg = 1;
		}
		if (tisch.getVorhand().equals(spieler2)) {
			erg = erg + 1;
		}
		if (tisch.getMittelhand().equals(spieler3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}
	
	@Test
	public void positionWechselnTest2() {
		tisch.positionWechseln();
		tisch.positionWechseln();
		int erg = 0;
		if (tisch.getMittelhand().equals(spieler1)) {
			erg = 1;
		}
		if (tisch.getHinterhand().equals(spieler2)) {
			erg = erg + 1;
		}
		if (tisch.getVorhand().equals(spieler3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}
	
	@Test
	public void positionWechselnTest3() {
		tisch.positionWechseln();
		tisch.positionWechseln();
		tisch.positionWechseln();
		int erg = 0;
		if (tisch.getVorhand().equals(spieler1)) {
			erg = 1;
		}
		if (tisch.getMittelhand().equals(spieler2)) {
			erg = erg + 1;
		}
		if (tisch.getHinterhand().equals(spieler3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}

	@Test
	public void positionInitialisierenTest() {
		tisch.positionInitialisieren();
		int erg = 0;
		if (tisch.getSpieler1().getPosition() == Position.VORHAND) {
			erg = erg + 1;
		}
		if (tisch.getSpieler2().getPosition() == Position.MITTELHAND) {
			erg = erg + 1;
		}
		if (tisch.getSpieler3().getPosition() == Position.HINTERHAND) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}

	@Test
	public void berechneStufeTest() {
		assertEquals(1, tisch.berechneStufe(61));
	}

	@Test
	public void berechneStufeTest2() {
		assertEquals(2, tisch.berechneStufe(92));
	}

	@Test
	public void berechneStufeTest3() {
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertEquals(3, tisch.berechneStufe(0));
	}

	@Test
	public void berechneStufeTest4() {
		tisch.setHandspiel(true);
		assertEquals(2, tisch.berechneStufe(61));
	}

	@Test
	public void berechneStufeTest5() {
		tisch.setHandspiel(true);
		assertEquals(3, tisch.berechneStufe(92));
	}

	@Test
	public void berechneStufeTest6() {
		tisch.setHandspiel(true);
		assertEquals(3, tisch.berechneStufe(120));
	}
	
	@Test
	public void berechneStufeTest7() {
		tisch.setHandspiel(true);
		tisch.setSchneider(true);
		tisch.setSchwarz(true);
		tisch.setOuvert(true);
		assertEquals(6, tisch.berechneStufe(100));
	}

	@Test
	public void checkverlorenTest() {
		assertEquals(false, tisch.checkVerloren(61));
	}

	@Test
	public void checkverlorenTest2() {
		assertEquals(false, tisch.checkVerloren(99));
	}

	@Test
	public void checkverlorenTest3() {
		tisch.ermittleAlleinspieler().setStiche(deck);
		assertEquals(false, tisch.checkVerloren(120));
	}

	@Test
	public void checkverlorenTest4() {
		assertEquals(true, tisch.checkVerloren(60));
	}

	@Test
	public void checkverlorenTest5() {
		assertEquals(true, tisch.checkVerloren(29));
	}

	@Test
	public void checkverlorenTest6() {
		tisch.ermittleAlleinspieler().setStiche(null);
		assertEquals(true, tisch.checkVerloren(0));
	}

	@Test
	public void ueberreizCheckTest() {
		
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest4() {
		Spielart spiel = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spiel);
		tisch.setReizwert(23);
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		tisch.ueberreizCheck(18);
		assertEquals(true, tisch.istUeberreizt());
	}
	
	@Test
	public void ueberreizCheckTest5() {
		tisch.setVariante(SkatVariant.SKAT);
		tisch.setHandspiel(false);
		tisch.setSchneider(false);
		tisch.setSchwarz(false);
		tisch.setOuvert(false);
		Spielart spiel = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spiel);
		tisch.setReizwert(23);
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		
		assertEquals(-54, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest2() {
		
		tisch.setSpielart(new Grandspiel());
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest3() {
		
		tisch.setSchneider(true);
		tisch.setSchwarz(true);
		tisch.setHandspiel(true);
		tisch.setOuvert(true);
		tisch.setSpielart(new Grandspiel());
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	

	@Test
	public void naechsterSpielerTest() {
		assertEquals(spieler2, tisch.naechsterSpieler(spieler1));
	}

	@Test
	public void naechsterSpielerTest2() {
		assertEquals(spieler3, tisch.naechsterSpieler(spieler2));
	}

	@Test
	public void naechsterSpielerTest3() {
		assertEquals(spieler1, tisch.naechsterSpieler(spieler3));
	}
	
	@Test
	public void gibMenschlicherSpielerTest1() {
		assertEquals(spieler3, tisch.gibMenschlicherSpieler());
	}
	
	@Test
	public void gibMenschlicherSpielerTest2() {
		tisch.setSpieler1(spieler3);
		tisch.setSpieler2(spieler1);
		tisch.setSpieler3(spieler2);
		assertEquals(spieler3, tisch.gibMenschlicherSpieler());
	}
	
	@Test
	public void gibMenschlicherSpielerTest3() {
		tisch.setSpieler1(spieler1);
		tisch.setSpieler2(spieler3);
		tisch.setSpieler3(spieler2);
		assertEquals(spieler3, tisch.gibMenschlicherSpieler());
	}
	
	@Test
	public void getAktuellePunkteTest() {
		tisch.getSpieler1().getSpiele().clear();
		tisch.getSpieler1().getSpiele().add(18);
		assertEquals(18, tisch.getAktuellePunkte(tisch.getSpieler1()));
	}
	
	@Test
	public void kartenbesitzerGebenTest() {
		boolean test = false;
		tisch.kartenBesitzergeben();
		for (int i= 0; i<tisch.getSpieler1().getBlatt().size(); i++){
			if (tisch.getSpieler1().getBlatt().get(i).getBesitzer().equals(tisch.getSpieler1())) {
				test = true;
			}
		}
		assertTrue(test);

	}
	
	@Test
	public void kartenbesitzerGebenTest2() {
		boolean test = false;
		tisch.kartenBesitzergeben();
		for (int i= 0; i<tisch.getSpieler3().getBlatt().size(); i++){
			if (tisch.getSpieler3().getBlatt().get(i).getBesitzer().equals(tisch.getSpieler3())) {
				test = true;
			}
		}
		assertTrue(test);
	}

	@Test
	public void naechstHoehererReizwertTest() {
		assertEquals(20, tisch.naechstHoehererReizwert(18));
	}
	
	@Test
	public void pruefeNeuenReizwertTest() {
		assertEquals(true, tisch.pruefeNeuenReizwert(18 , 0));
	}
	
	@Test
	public void pruefeNeuenReizwertTest2() {
		assertEquals(true, tisch.pruefeNeuenReizwert(18 , 20));
	}
	
	@Test
	public void pruefeNeuenReizwertTest3() {
		assertEquals(false, tisch.pruefeNeuenReizwert(18 , 21));
	}
	
	@Test
	public void naechstniedrigerReizwertTest() {
		assertEquals(18, tisch.naechstNiedrigererReizwert(20));
	}
	
	@Test
	public void naechstniedrigerReizwertTest2() {
		assertEquals(20, tisch.naechstNiedrigererReizwert(22));
	}
	
	@Test
	public void naechstniedrigerReizwertTest3() {
		assertEquals(22, tisch.naechstNiedrigererReizwert(23));
	}
	
	@Test
	public void wertePunkteTest1() {
		tisch.setVariante(SkatVariant.RAEUBER);
		Nullspiel spiel = new Nullspiel();
		tisch.setSpielart(spiel);
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertEquals(23, tisch.wertePunkte(0));
	}
	
	@Test
	public void wertePunkteTest() {
		tisch.setVariante(SkatVariant.SKAT);
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(18, tisch.wertePunkte(62));
	}
	
	@Test
	public void wertePunkteTest2() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		ISpielart spielart = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spielart);
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(27, tisch.wertePunkte(91));
	}
	
	@Test
	public void wertePunkteTest3() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		assertEquals(27, tisch.wertePunkte(120));
	}
	
	@Test
	public void wertePunkteTest4() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		tisch.ermittleAlleinspieler().setStiche(deck);
		tisch.ermittleAlleinspieler().getStiche().remove(31);
		tisch.ermittleAlleinspieler().getStiche().remove(30);
		assertEquals(27, tisch.wertePunkte(144));
	}
	
	@Test
	public void wertePunkteTest5() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Grandspiel();
		tisch.setSpielart(spielart);
		assertEquals(48, tisch.wertePunkte(62));
	}
	
	@Test
	public void wertePunkteTest6() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Grandspiel();
		tisch.setSpielart(spielart);
		assertEquals(-96, tisch.wertePunkte(45));
	}
	
	@Test
	public void wertePunkteTest7() {
		tisch.setVariante(SkatVariant.SKAT);
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spielart);
		tisch.setReizwert(48);
		assertEquals(-108, tisch.wertePunkte(62));
	}
	
	@Test
	public void wertePunkteTest8() {
		
		tisch.setBock(true);
		tisch.setVariante(SkatVariant.RAMSCHBOCK);
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		tisch.ermittleAlleinspieler().setStiche(deck);
		tisch.ermittleAlleinspieler().getStiche().remove(31);
		tisch.ermittleAlleinspieler().getStiche().remove(30);
		assertEquals(54, tisch.wertePunkte(144));
	}
	
	@Test
	public void punkteFarbspielTest() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Farbspiel(Farbe.KARO);
		tisch.setSpielart(spielart);
	assertEquals(18, tisch.punkteFarbspiel(62));
	}
	
	@Test
	public void punkteGrandspielTest() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Grandspiel();
		tisch.setSpielart(spielart);
		assertEquals(48, tisch.punkteGrandspiel(62));
	}
	
	@Test
	public void punkteNullspielTest() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Nullspiel();
		tisch.setSpielart(spielart);
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertEquals(23, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest2() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Nullspiel();
		tisch.setSpielart(spielart);
		tisch.ermittleAlleinspieler().getStiche().clear();
		tisch.setHandspiel(true);
		assertEquals(35, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest3() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Nullspiel();
		tisch.setSpielart(spielart);
		tisch.ermittleAlleinspieler().getStiche().clear();
		tisch.setHandspiel(false);
		tisch.setOuvert(true);
		assertEquals(46, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest4() {
		tisch.ermittleAlleinspieler().getBlatt().clear();
		tisch.ermittleAlleinspieler().getBlatt().add(
				new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		ISpielart spielart = new Nullspiel();
		tisch.setSpielart(spielart);
		tisch.ermittleAlleinspieler().getStiche().clear();
		tisch.setHandspiel(true);
		tisch.setOuvert(true);
		assertEquals(59, tisch.punkteNullspiel());
	}
	
	@Test
	public void spielAuswertenTest() {
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertFalse(tisch.spielAuswerten());
	}
	
	@Test
	public void spielAuswertenTest2() {
		tisch.ermittleAlleinspieler().getStiche().clear();
		ISpielart spielart = new Nullspiel();
		tisch.setSpielart(spielart);
		assertTrue(tisch.spielAuswerten());
	}
	
	@Test
	public void spielAuswertenTest3() {
		
		tisch.setSpielart(new Farbspiel(Farbe.KREUZ));
		tisch.ermittleAlleinspieler().getStiche().clear();
		tisch.ermittleAlleinspieler().setSpielart(new Farbspiel(Farbe.KREUZ));
		Spielkarte karte = new Spielkarte(Farbe.HERZ, Wert.ZEHN);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		tisch.ermittleAlleinspieler().getStiche().add(karte);
		Spielkarte karte2 = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		tisch.ermittleAlleinspieler().getStiche().add(karte2);
		Spielkarte karte3 = new Spielkarte(Farbe.HERZ, Wert.KOENIG);
		tisch.ermittleAlleinspieler().getStiche().add(karte3);
		tisch.ermittleAlleinspieler().getStiche().add(karte3);
		tisch.ermittleAlleinspieler().getBlatt().add(karte2);
		tisch.ermittleAlleinspieler().spitzenEinordnen();
		
		tisch.setHandspiel(true);
		tisch.setSchneider(true);
		assertTrue(tisch.spielAuswerten());
	}
	
	@Test
	public void spielAuswertenTest4() {
		IPlayer tmp = tisch.getSpieler1();
		tisch.setSpieler1(spieler2);
		tisch.setSpieler2(tmp);
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertFalse(tisch.spielAuswerten());
	}
	
	@Test
	public void spielAuswertenTest5() {
		IPlayer tmp = tisch.getSpieler1();
		tisch.setSpieler1(spieler3);
		tisch.setSpieler3(tmp);
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertFalse(tisch.spielAuswerten());
	}
	
	@Test
	public void spielAuswertenTest6() {
		
		tisch.setSpielart(new Ramsch());
		tisch.setBock(true);
		tisch.setSechserskat(true);
		Spielkarte[] skat = new Spielkarte[3];
		skat[0] = new Spielkarte(Farbe.HERZ, Wert.SIEBEN);
		skat[1] = new Spielkarte(Farbe.HERZ, Wert.ZEHN);
		skat[2] = new Spielkarte(Farbe.PIK, Wert.ACHT);
		tisch.setSkat(skat);
		spieler1.getStiche().clear();
		spieler2.getStiche().clear();
		spieler3.getStiche().clear();
		spieler1.getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		spieler1.getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.KOENIG));
		spieler2.getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.SECHS));
		assertTrue(tisch.spielAuswerten());
	}
	
	@Test
	public void sortiereSpielerRamschTest() {
		
		tisch.getSpieler1().getStiche().clear();
		tisch.getSpieler1().getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		tisch.getSpieler1().getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.KOENIG));
		tisch.getSpieler2().getStiche().clear();
		tisch.getSpieler2().getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.SIEBEN));
		tisch.getSpieler2().getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.ACHT));
		tisch.getSpieler3().getStiche().clear();
		tisch.getSpieler3().getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		tisch.getSpieler3().getStiche().add(new Spielkarte(Farbe.PIK, Wert.KOENIG));
		
		IPlayer[] spielerU = new IPlayer[3];
		spielerU[0] = spieler1;
		spielerU[1] = spieler2;
		spielerU[2] = spieler3;
	
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler2;
		spieler[1] = spieler3;
		spieler[2] = spieler1;
	
		assertArrayEquals(spieler, tisch.sortiereSpielerRamsch(spielerU));
	}
	
	@Test
	public void entscheideRamschTest() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler1;
		spieler[1] = spieler2;
		spieler[2] = spieler3;
		spieler[2].getStiche().clear();
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.DAME));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.KOENIG));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.ZEHN));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.ASS));
		spieler[2].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler[2].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.DAME));
		spieler[2].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.KOENIG));
		spieler[2].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		spieler[2].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.ASS));
		spieler[2].getStiche().add(new Spielkarte(Farbe.PIK, Wert.BUBE));
		spieler[2].getStiche().add(new Spielkarte(Farbe.PIK, Wert.DAME));
		spieler[2].getStiche().add(new Spielkarte(Farbe.PIK, Wert.KOENIG));
		spieler[2].getStiche().add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		spieler[2].getStiche().add(new Spielkarte(Farbe.PIK, Wert.ASS));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.KOENIG));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 0, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getSpiele().add(240);
		
		assertEquals(vergleich.getSpiele().get(0), ergebnis[2].getSpiele().get(0)); 
	}
	
	@Test
	public void entscheideRamschTest2() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler1;
		spieler[1] = spieler2;
		spieler[2] = spieler3;
		spieler[0].getStiche().clear();
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.ASS));
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getSpiele().add(-124);
		
		assertEquals(vergleich.getSpiele().get(0), ergebnis[2].getSpiele().get(0));
	}
	
	@Test
	public void entscheideRamschTest3() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler1;
		spieler[1] = spieler2;
		spieler[2] = spieler3;
		spieler[0].getStiche().add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		spieler[2].getStiche().add(new Spielkarte(Farbe.KARO, Wert.ASS));
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getSpiele().add(-62);
		
		assertEquals(vergleich.getSpiele().get(0), ergebnis[2].getSpiele().get(0));
	}
	
	@Test
	public void addAnzahlSpieleTest() {
		
		tisch.addAnzahlSpiele();
		assertEquals(2, tisch.getAnzahlSpiele());
	}
	
	@Test
	public void ermittleMitspielerTest1() {
		
		tisch.getSpieler1().setIstAlleinspieler(true);
		assertEquals(spieler3, tisch.ermittleMitspieler(spieler2));
	}
	
	@Test
	public void ermittleMitspielerTest2() {
		
		tisch.getSpieler1().setIstAlleinspieler(true);
		assertEquals(spieler2, tisch.ermittleMitspieler(spieler3));
	}
	
	//Spieler 1 ist default Alleinspieler =D
	@Test
	public void ermittleMitspielerTest3() {
		
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler2().setIstAlleinspieler(true);
		assertEquals(spieler1, tisch.ermittleMitspieler(spieler3));
	}
	
	@Test
	public void ermittleMitspielerTest4() {
		
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler2().setIstAlleinspieler(true);
		assertEquals(spieler3, tisch.ermittleMitspieler(spieler1));
	}
	
	@Test
	public void ermittleMitspielerTest5() {
		
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler3().setIstAlleinspieler(true);
		assertEquals(spieler1, tisch.ermittleMitspieler(spieler2));
	}
	
	@Test
	public void ermittleMitspielerTest6() {
		
		tisch.getSpieler1().setIstAlleinspieler(false);
		tisch.getSpieler3().setIstAlleinspieler(true);
		assertEquals(spieler2, tisch.ermittleMitspieler(spieler1));
	}
	
	@Test
	public void mitspielerSetzenTest1() {
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getSpieler1().getMitspieler());
	}
	
	@Test
	public void mitspielerSetzenTest2() {
		tisch.mitspielerSetzen();
		assertEquals(spieler3, tisch.getSpieler2().getMitspieler());
	}
	
	@Test 
	public void mitspielerSetzenTest3() {
		tisch.mitspielerSetzen();
		assertEquals(spieler2, tisch.getSpieler3().getMitspieler());
	}
	
	@Test
	public void mitspielerSetzenTest4() {
		
		spieler1.setIstAlleinspieler(false);
		spieler2.setIstAlleinspieler(true);
		tisch.mitspielerSetzen();
		assertEquals(spieler3, tisch.getSpieler1().getMitspieler());
	}
	
	@Test
	public void mitspielerSetzenTest5() {
		tisch.setSpielart(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getSpieler1().getMitspieler());
	}
	
	@Test
	public void mitspielerSetzenTest6() {
		tisch.setSpielart(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getSpieler2().getMitspieler());
	}
	
	@Test
	public void mitspielerSetzenTest7() {
		tisch.setSpielart(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getSpieler3().getMitspieler());
	}
	
	@Test
	public void nullVerlorenTest() {
		
		tisch.ermittleAlleinspieler().getStiche().clear();
		assertFalse(tisch.nullVerloren());
	}
	
	@Test
	public void nullVerlorenTest2() {
		
		tisch.ermittleAlleinspieler().getStiche().clear();
		ArrayList<Spielkarte> stiche = new ArrayList<Spielkarte>();
		stiche.add(spielkarte1);
		stiche.add(spielkarte2);
		stiche.add(spielkarte3);
		tisch.ermittleAlleinspieler().setStiche(stiche);
		assertTrue(tisch.nullVerloren());
	}
	
	@Test
	public void anderesSpielVerlorenTest() {
		
		assertTrue(tisch.anderesSpielVerloren(60));
	}
	
	@Test
	public void anderesSpielVerlorenTest2() {
		
		tisch.setSchneider(true);
		assertTrue(tisch.anderesSpielVerloren(88));
	}
	
	@Test
	public void anderesSpielVerlorenTest3() {
	
		tisch.setSechserskat(false);
		tisch.setSchwarz(true);
		tisch.erstelleDeck();
		spieler1.getStiche().clear();
		for (Spielkarte karte : tisch.getDeck()) {
			
			spieler1.getStiche().add(karte);
		}
		for (int i = 0; i < 2; i++) {
			
			spieler1.getStiche().remove(0);
		}
		System.out.println(spieler1.getStiche().size());
		assertTrue(tisch.anderesSpielVerloren(100));
	}
	
	@Test
	public void anderesSpielVerlorenTest4() {
		
		tisch.setSchneider(false);
		tisch.setSchwarz(false);
		tisch.setOuvert(true);
		spieler1.getStiche().clear();
		assertTrue(tisch.anderesSpielVerloren(61));
	}
	
	@Test
	public void anderesSpielVerlorenTest5() {
		
		tisch.setOuvert(false);
		assertFalse(tisch.anderesSpielVerloren(62));
	}
}