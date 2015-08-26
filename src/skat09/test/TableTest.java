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
import skat09.gamevariety.GameVariety;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.NullGame;
import skat09.gamevariety.Ramsch;
import skat09.gamevariety.SuitGame;
import skat09.player.Granny;
import skat09.player.HumanPlayer;
import skat09.player.Position;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;
import skat09.ui.CLIOutput;


public class TableTest {

	//
	// Datenfelder
	// 

	PlayingCard spielkarte1;
	PlayingCard spielkarte2;
	PlayingCard spielkarte3;
	PlayingCard spielkarte4;
	IPlayer spieler1 = new Granny("Bert");
	IPlayer spieler2 = new Granny("Ernie");

	Table tisch = new Table();
	ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> spitzen = new ArrayList<PlayingCard>();
	CLIOutput ausgabe = new CLIOutput(tisch);
	IController controller = new Controller(tisch, ausgabe);
	IPlayer spieler3 = new HumanPlayer("Hans", controller);
	PlayingCard[] skat = new PlayingCard[3];

	@Before
	public void setUp() {
		
		spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		spielkarte4 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		spieler1.setPosition(Position.VORHAND);
		spieler2.setPosition(Position.MITTELHAND);
		spieler3.setPosition(Position.HINTERHAND);
		tisch.setPlayer1(spieler1);
		tisch.setPlayer2(spieler2);
		tisch.setPlayer3(spieler3);
		tisch.getPlayer1().setIsDeclarer(true);
		tisch.createDeck();
		tisch.dealOutCards();
		tisch.getPlayer1().getHand().clear();
		tisch.getPlayer1().getHand().add(spielkarte1);
		tisch.getPlayer1().getHand().add(spielkarte2);
		tisch.getPlayer1().getHand().add(spielkarte3);
	    tisch.getPlayer2().getHand().clear();
	    tisch.getPlayer2().getHand().add(spielkarte4);
	    tisch.getPlayer3().getHand().clear();
	    tisch.getPlayer3().getHand().add(spielkarte3);
	    skat[0] = null;
	    skat[1] = null;
	    tisch.setSkat(skat);
		
		for (Suit farbe : Suit.values()) {
			for (Value wert : Value.values()) {
				PlayingCard karte = new PlayingCard(farbe, wert);
				deck.add(karte);
			}
		}

		PlayingCard[] gespieltekarten = tisch.getPlayedCards();
		gespieltekarten[0] = new PlayingCard(Suit.HEARTS, Value.DAUS);
		gespieltekarten[1] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		gespieltekarten[2] = new PlayingCard(Suit.BELLS, Value.NINE);
		tisch.setPlayedCards(gespieltekarten);
		tisch.getPlayer1().addTrick(gespieltekarten);
		tisch.setBiddingValue(18);
		IGameVariety spielart = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spielart);
		tisch.getPlayer1().setGameVariety(spielart);
	}

	@After
	public void after() {
		GameVariety spielart = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spielart);
	}

	// Konstruktortest fuer Spieler1
	@Test
	public void tischSpieler1Test() {
		assertEquals("Bert", tisch.getPlayer1().getName());
	}

	// Konstruktortest fuer Spieler2
	@Test
	public void tischSpieler2Test() {
		assertEquals("Ernie", tisch.getPlayer2().getName());
	}

	// Konstruktortest fuer Spieler3
	@Test
	public void tischSpieler3Test() {
		assertEquals("Hans", tisch.getPlayer3().getName());
	}
	
	//Testet die Methode getHandGame
	@Test
	public void getHandspiel1Test() {
		tisch.setHandGame(true);
		assertTrue(tisch.getHandGame());
	}
	
	//Testet die Methode getHandGame
	@Test
	public void getHandspiel2Test() {
		tisch.setHandGame(false);
		assertFalse(tisch.getHandGame());
	}
	
	//Testet die Methode getGameVariety
	@Test
	public void getSpielartTest() {
		IGameVariety spielart = new GrandGame();
		tisch.setGameVariety(spielart);
		assertEquals(spielart, tisch.getGameVariety());
	}
	
	//Testet die Methode getVariant
	@Test
	public void getVarianteTest() {
		tisch.setVariant(SkatVariant.RAEUBER);
		assertEquals(SkatVariant.RAEUBER, tisch.getVariant());
	}
	
	//Testet die Methode getBiddingValue
	@Test
	public void getReizwertTest() {
		tisch.setBiddingValue(35);
		assertEquals(35, tisch.getBiddingValue());
	}
	
	
	//Testet die Methode getReizagentWert
	@Test
	public void reizagentWertTest() {
		tisch.setBiddingAgentValue(42);
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
		PlayingCard[] skat = new PlayingCard[2];
		skat[0] = new PlayingCard(Suit.HEARTS, Value.DAUS);
		skat[1] = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		tisch.setSkat(skat);
		
		assertArrayEquals(skat, tisch.getSkat());
	}
	
	// Testet ob das erstellte Deck tatsaechlich alle Karten enthaelt
	@Test
	public void erstelleDeckTest() {
		tisch.setSixSkat(true);
		tisch.createDeck();
		assertEquals(36, tisch.getDeck().size());
	}

	@Test
	public void erstelleDeckTest2() {
		int test = 0;
		tisch.createDeck();
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
		tisch.createDeck();
		tisch.shuffleDeck();
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
		tisch.createDeck();
		tisch.shuffleDeck();
		
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		gespielteKarten[2] = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		IGameVariety spielart = new NullGame();
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler2, tisch.evaluateTrick(spielart, gespielteKarten));
		
	}
	
	@Test
	public void stichAuswertenTest2() {
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		gespielteKarten[2] = new PlayingCard(Suit.BELLS, Value.KING);
		IGameVariety spielart = new NullGame();
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler3, tisch.evaluateTrick(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest3() {
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.DAUS);
		gespielteKarten[2] = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		IGameVariety spielart = new SuitGame(Suit.HEARTS);
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler3, tisch.evaluateTrick(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest4() {
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		gespielteKarten[2] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		IGameVariety spielart = new GrandGame();
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler1, tisch.evaluateTrick(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest5() {
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		gespielteKarten[2] = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		IGameVariety spielart = new NullGame();
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler1, tisch.evaluateTrick(spielart, gespielteKarten));
	}
	
	@Test
	public void stichAuswertenTest6() {
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		gespielteKarten[2] = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		IGameVariety spielart = new SuitGame(Suit.ACORNS);
		gespielteKarten[0].setOwner(spieler1);
		gespielteKarten[1].setOwner(spieler2);
		gespielteKarten[2].setOwner(spieler3);
		assertEquals(spieler1, tisch.evaluateTrick(spielart, gespielteKarten));
	}
	
	@Test
	public void KartenAusteilenTest() {
		boolean test = false;
		tisch.createDeck();
		tisch.dealOutCards();
		if(tisch.getPlayer1().getHand().size() == 10){
			test = true;
		}
		assertTrue(test);
	}

	@Test
	public void kartenAusteilenTest2() {
		tisch.createDeck();
		tisch.dealOutCards();
		assertEquals(10, tisch.getPlayer2().getHand().size());
	}

	@Test
	public void kartenAusteilenTest3() {
		tisch.createDeck();
		tisch.dealOutCards();
		assertEquals(10, tisch.getPlayer3().getHand().size());
	}

	@Test
	public void kartenAusteilenTest4() {
		tisch.createDeck();
		tisch.dealOutCards();
		assertEquals(0, tisch.getDeck().size());
	}
	
	@Test
	public void kartenAusteilenTest5() {
		tisch.setSixSkat(true);
		tisch.createDeck();
		tisch.dealOutCards();
		assertEquals(0, tisch.getDeck().size());
	}
	
	@Test
	public void getProzentAlleinTest() {
		tisch.getPlayer1().getGames().clear();
		tisch.getPlayer1().getGames().add(18);
		//tisch.addAnzahlSpiele();
		
		assertEquals(100, tisch.getProzentAllein(spieler1));
	}
	
	@Test
	public void getAnzahlAlleinTest() {
		tisch.getPlayer1().getGames().clear();
		tisch.getPlayer1().getGames().add(18);
		//tisch.addAnzahlSpiele();
		
		assertEquals(1, tisch.getAnzahlAllein(spieler1));
	}
	
	@Test
	public void anzahlGewinneTest() {
		tisch.getPlayer1().getGames().clear();
		tisch.getPlayer1().getGames().add(18);
		assertEquals(1, tisch.anzahlderGewinne(spieler1));
	}

	@Test
	public void ermittleAlleinspielerTest() {
		assertEquals(spieler1, tisch.getDeclarer());
	}

	@Test
	public void ermittleAlleinspielerTest2() {
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer2().setIsDeclarer(true);
		assertEquals(spieler2, tisch.getDeclarer());
	}
	
	@Test
	public void ermittleAlleinspielerTest3() {
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer3().setIsDeclarer(true);
		assertEquals(spieler3, tisch.getDeclarer());
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
		tisch.createDeck();
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
		tisch.initializePositions();
		int erg = 0;
		if (tisch.getPlayer1().getPosition() == Position.VORHAND) {
			erg = erg + 1;
		}
		if (tisch.getPlayer2().getPosition() == Position.MITTELHAND) {
			erg = erg + 1;
		}
		if (tisch.getPlayer3().getPosition() == Position.HINTERHAND) {
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
		tisch.getDeclarer().getTricks().clear();
		assertEquals(3, tisch.berechneStufe(0));
	}

	@Test
	public void berechneStufeTest4() {
		tisch.setHandGame(true);
		assertEquals(2, tisch.berechneStufe(61));
	}

	@Test
	public void berechneStufeTest5() {
		tisch.setHandGame(true);
		assertEquals(3, tisch.berechneStufe(92));
	}

	@Test
	public void berechneStufeTest6() {
		tisch.setHandGame(true);
		assertEquals(3, tisch.berechneStufe(120));
	}
	
	@Test
	public void berechneStufeTest7() {
		tisch.setHandGame(true);
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
		tisch.getDeclarer().setTricks(deck);
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
		tisch.getDeclarer().setTricks(null);
		assertEquals(true, tisch.checkVerloren(0));
	}

	@Test
	public void ueberreizCheckTest() {
		
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest4() {
		GameVariety spiel = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spiel);
		tisch.setBiddingValue(23);
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		tisch.ueberreizCheck(18);
		assertEquals(true, tisch.istUeberreizt());
	}
	
	@Test
	public void ueberreizCheckTest5() {
		tisch.setVariant(SkatVariant.SKAT);
		tisch.setHandGame(false);
		tisch.setSchneider(false);
		tisch.setSchwarz(false);
		tisch.setOuvert(false);
		GameVariety spiel = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spiel);
		tisch.setBiddingValue(23);
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		
		assertEquals(-54, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest2() {
		
		tisch.setGameVariety(new GrandGame());
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	
	@Test
	public void ueberreizCheckTest3() {
		
		tisch.setSchneider(true);
		tisch.setSchwarz(true);
		tisch.setHandGame(true);
		tisch.setOuvert(true);
		tisch.setGameVariety(new GrandGame());
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(0, tisch.ueberreizCheck(18));
	}
	

	@Test
	public void naechsterSpielerTest() {
		assertEquals(spieler2, tisch.nextPlayer(spieler1));
	}

	@Test
	public void naechsterSpielerTest2() {
		assertEquals(spieler3, tisch.nextPlayer(spieler2));
	}

	@Test
	public void naechsterSpielerTest3() {
		assertEquals(spieler1, tisch.nextPlayer(spieler3));
	}
	
	@Test
	public void gibMenschlicherSpielerTest1() {
		assertEquals(spieler3, tisch.getHumanPlayer());
	}
	
	@Test
	public void gibMenschlicherSpielerTest2() {
		tisch.setPlayer1(spieler3);
		tisch.setPlayer2(spieler1);
		tisch.setPlayer3(spieler2);
		assertEquals(spieler3, tisch.getHumanPlayer());
	}
	
	@Test
	public void gibMenschlicherSpielerTest3() {
		tisch.setPlayer1(spieler1);
		tisch.setPlayer2(spieler3);
		tisch.setPlayer3(spieler2);
		assertEquals(spieler3, tisch.getHumanPlayer());
	}
	
	@Test
	public void getAktuellePunkteTest() {
		tisch.getPlayer1().getGames().clear();
		tisch.getPlayer1().getGames().add(18);
		assertEquals(18, tisch.getAktuellePunkte(tisch.getPlayer1()));
	}
	
	@Test
	public void kartenbesitzerGebenTest() {
		boolean test = false;
		tisch.kartenBesitzergeben();
		for (int i= 0; i<tisch.getPlayer1().getHand().size(); i++){
			if (tisch.getPlayer1().getHand().get(i).getOwner().equals(tisch.getPlayer1())) {
				test = true;
			}
		}
		assertTrue(test);

	}
	
	@Test
	public void kartenbesitzerGebenTest2() {
		boolean test = false;
		tisch.kartenBesitzergeben();
		for (int i= 0; i<tisch.getPlayer3().getHand().size(); i++){
			if (tisch.getPlayer3().getHand().get(i).getOwner().equals(tisch.getPlayer3())) {
				test = true;
			}
		}
		assertTrue(test);
	}

	@Test
	public void naechstHoehererReizwertTest() {
		assertEquals(20, tisch.nextGreaterBiddingValue(18));
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
		tisch.setVariant(SkatVariant.RAEUBER);
		NullGame spiel = new NullGame();
		tisch.setGameVariety(spiel);
		tisch.getDeclarer().getTricks().clear();
		assertEquals(23, tisch.calculatePoints(0));
	}
	
	@Test
	public void wertePunkteTest() {
		tisch.setVariant(SkatVariant.SKAT);
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(18, tisch.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest2() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		IGameVariety spielart = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spielart);
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(27, tisch.calculatePoints(91));
	}
	
	@Test
	public void wertePunkteTest3() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		assertEquals(27, tisch.calculatePoints(120));
	}
	
	@Test
	public void wertePunkteTest4() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		tisch.getDeclarer().setTricks(deck);
		tisch.getDeclarer().getTricks().remove(31);
		tisch.getDeclarer().getTricks().remove(30);
		assertEquals(27, tisch.calculatePoints(144));
	}
	
	@Test
	public void wertePunkteTest5() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new GrandGame();
		tisch.setGameVariety(spielart);
		assertEquals(48, tisch.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest6() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new GrandGame();
		tisch.setGameVariety(spielart);
		assertEquals(-96, tisch.calculatePoints(45));
	}
	
	@Test
	public void wertePunkteTest7() {
		tisch.setVariant(SkatVariant.SKAT);
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spielart);
		tisch.setBiddingValue(48);
		assertEquals(-108, tisch.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest8() {
		
		tisch.setBock(true);
		tisch.setVariant(SkatVariant.RAMSCHBOCK);
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		tisch.getDeclarer().setTricks(deck);
		tisch.getDeclarer().getTricks().remove(31);
		tisch.getDeclarer().getTricks().remove(30);
		assertEquals(54, tisch.calculatePoints(144));
	}
	
	@Test
	public void punkteFarbspielTest() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new SuitGame(Suit.BELLS);
		tisch.setGameVariety(spielart);
	assertEquals(18, tisch.punkteFarbspiel(62));
	}
	
	@Test
	public void punkteGrandspielTest() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new GrandGame();
		tisch.setGameVariety(spielart);
		assertEquals(48, tisch.punkteGrandspiel(62));
	}
	
	@Test
	public void punkteNullspielTest() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new NullGame();
		tisch.setGameVariety(spielart);
		tisch.getDeclarer().getTricks().clear();
		assertEquals(23, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest2() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new NullGame();
		tisch.setGameVariety(spielart);
		tisch.getDeclarer().getTricks().clear();
		tisch.setHandGame(true);
		assertEquals(35, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest3() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new NullGame();
		tisch.setGameVariety(spielart);
		tisch.getDeclarer().getTricks().clear();
		tisch.setHandGame(false);
		tisch.setOuvert(true);
		assertEquals(46, tisch.punkteNullspiel());
	}
	
	@Test
	public void punkteNullspielTest4() {
		tisch.getDeclarer().getHand().clear();
		tisch.getDeclarer().getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getDeclarer().spitzenEinordnen();
		IGameVariety spielart = new NullGame();
		tisch.setGameVariety(spielart);
		tisch.getDeclarer().getTricks().clear();
		tisch.setHandGame(true);
		tisch.setOuvert(true);
		assertEquals(59, tisch.punkteNullspiel());
	}
	
	@Test
	public void spielAuswertenTest() {
		tisch.getDeclarer().getTricks().clear();
		assertFalse(tisch.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest2() {
		tisch.getDeclarer().getTricks().clear();
		IGameVariety spielart = new NullGame();
		tisch.setGameVariety(spielart);
		assertTrue(tisch.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest3() {
		
		tisch.setGameVariety(new SuitGame(Suit.ACORNS));
		tisch.getDeclarer().getTricks().clear();
		tisch.getDeclarer().setGameVariety(new SuitGame(Suit.ACORNS));
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.TEN);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		tisch.getDeclarer().getTricks().add(karte);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		tisch.getDeclarer().getTricks().add(karte2);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.KING);
		tisch.getDeclarer().getTricks().add(karte3);
		tisch.getDeclarer().getTricks().add(karte3);
		tisch.getDeclarer().getHand().add(karte2);
		tisch.getDeclarer().spitzenEinordnen();
		
		tisch.setHandGame(true);
		tisch.setSchneider(true);
		assertTrue(tisch.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest4() {
		IPlayer tmp = tisch.getPlayer1();
		tisch.setPlayer1(spieler2);
		tisch.setPlayer2(tmp);
		tisch.getDeclarer().getTricks().clear();
		assertFalse(tisch.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest5() {
		IPlayer tmp = tisch.getPlayer1();
		tisch.setPlayer1(spieler3);
		tisch.setPlayer3(tmp);
		tisch.getDeclarer().getTricks().clear();
		assertFalse(tisch.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest6() {
		
		tisch.setGameVariety(new Ramsch());
		tisch.setBock(true);
		tisch.setSixSkat(true);
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		skat[1] = new PlayingCard(Suit.HEARTS, Value.TEN);
		skat[2] = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		tisch.setSkat(skat);
		spieler1.getTricks().clear();
		spieler2.getTricks().clear();
		spieler3.getTricks().clear();
		spieler1.getTricks().add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		spieler1.getTricks().add(new PlayingCard(Suit.ACORNS, Value.KING));
		spieler2.getTricks().add(new PlayingCard(Suit.ACORNS, Value.SIX));
		assertTrue(tisch.evaluateGame());
	}
	
	@Test
	public void sortiereSpielerRamschTest() {
		
		tisch.getPlayer1().getTricks().clear();
		tisch.getPlayer1().getTricks().add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		tisch.getPlayer1().getTricks().add(new PlayingCard(Suit.ACORNS, Value.KING));
		tisch.getPlayer2().getTricks().clear();
		tisch.getPlayer2().getTricks().add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		tisch.getPlayer2().getTricks().add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		tisch.getPlayer3().getTricks().clear();
		tisch.getPlayer3().getTricks().add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		tisch.getPlayer3().getTricks().add(new PlayingCard(Suit.LEAVES, Value.KING));
		
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
		spieler[2].getTricks().clear();
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.KING));
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.TEN));
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler[2].getTricks().add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.HEARTS, Value.KING));
		spieler[2].getTricks().add(new PlayingCard(Suit.HEARTS, Value.TEN));
		spieler[2].getTricks().add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		spieler[2].getTricks().add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.LEAVES, Value.KING));
		spieler[2].getTricks().add(new PlayingCard(Suit.LEAVES, Value.TEN));
		spieler[2].getTricks().add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		spieler[2].getTricks().add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.ACORNS, Value.KING));
		spieler[2].getTricks().add(new PlayingCard(Suit.ACORNS, Value.TEN));
		spieler[2].getTricks().add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 0, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(240);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0)); 
	}
	
	@Test
	public void entscheideRamschTest2() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler1;
		spieler[1] = spieler2;
		spieler[2] = spieler3;
		spieler[0].getTricks().clear();
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.DAUS));
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(-124);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0));
	}
	
	@Test
	public void entscheideRamschTest3() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = spieler1;
		spieler[1] = spieler2;
		spieler[2] = spieler3;
		spieler[0].getTricks().add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(Suit.BELLS, Value.DAUS));
		IPlayer[] ergebnis = tisch.entscheideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(-62);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0));
	}
	
	@Test
	public void addAnzahlSpieleTest() {
		
		tisch.addAnzahlSpiele();
		assertEquals(2, tisch.getAnzahlSpiele());
	}
	
	@Test
	public void ermittleMitspielerTest1() {
		
		tisch.getPlayer1().setIsDeclarer(true);
		assertEquals(spieler3, tisch.ermittleMitspieler(spieler2));
	}
	
	@Test
	public void ermittleMitspielerTest2() {
		
		tisch.getPlayer1().setIsDeclarer(true);
		assertEquals(spieler2, tisch.ermittleMitspieler(spieler3));
	}
	
	//Spieler 1 ist default Alleinspieler =D
	@Test
	public void ermittleMitspielerTest3() {
		
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer2().setIsDeclarer(true);
		assertEquals(spieler1, tisch.ermittleMitspieler(spieler3));
	}
	
	@Test
	public void ermittleMitspielerTest4() {
		
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer2().setIsDeclarer(true);
		assertEquals(spieler3, tisch.ermittleMitspieler(spieler1));
	}
	
	@Test
	public void ermittleMitspielerTest5() {
		
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer3().setIsDeclarer(true);
		assertEquals(spieler1, tisch.ermittleMitspieler(spieler2));
	}
	
	@Test
	public void ermittleMitspielerTest6() {
		
		tisch.getPlayer1().setIsDeclarer(false);
		tisch.getPlayer3().setIsDeclarer(true);
		assertEquals(spieler2, tisch.ermittleMitspieler(spieler1));
	}
	
	@Test
	public void mitspielerSetzenTest1() {
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest2() {
		tisch.mitspielerSetzen();
		assertEquals(spieler3, tisch.getPlayer2().getTeammate());
	}
	
	@Test 
	public void mitspielerSetzenTest3() {
		tisch.mitspielerSetzen();
		assertEquals(spieler2, tisch.getPlayer3().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest4() {
		
		spieler1.setIsDeclarer(false);
		spieler2.setIsDeclarer(true);
		tisch.mitspielerSetzen();
		assertEquals(spieler3, tisch.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest5() {
		tisch.setGameVariety(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest6() {
		tisch.setGameVariety(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getPlayer2().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest7() {
		tisch.setGameVariety(new Ramsch());
		tisch.mitspielerSetzen();
		assertEquals(null, tisch.getPlayer3().getTeammate());
	}
	
	@Test
	public void nullVerlorenTest() {
		
		tisch.getDeclarer().getTricks().clear();
		assertFalse(tisch.nullVerloren());
	}
	
	@Test
	public void nullVerlorenTest2() {
		
		tisch.getDeclarer().getTricks().clear();
		ArrayList<PlayingCard> stiche = new ArrayList<PlayingCard>();
		stiche.add(spielkarte1);
		stiche.add(spielkarte2);
		stiche.add(spielkarte3);
		tisch.getDeclarer().setTricks(stiche);
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
	
		tisch.setSixSkat(false);
		tisch.setSchwarz(true);
		tisch.createDeck();
		spieler1.getTricks().clear();
		for (PlayingCard karte : tisch.getDeck()) {
			
			spieler1.getTricks().add(karte);
		}
		for (int i = 0; i < 2; i++) {
			
			spieler1.getTricks().remove(0);
		}
		System.out.println(spieler1.getTricks().size());
		assertTrue(tisch.anderesSpielVerloren(100));
	}
	
	@Test
	public void anderesSpielVerlorenTest4() {
		
		tisch.setSchneider(false);
		tisch.setSchwarz(false);
		tisch.setOuvert(true);
		spieler1.getTricks().clear();
		assertTrue(tisch.anderesSpielVerloren(61));
	}
	
	@Test
	public void anderesSpielVerlorenTest5() {
		
		tisch.setOuvert(false);
		assertFalse(tisch.anderesSpielVerloren(62));
	}
}