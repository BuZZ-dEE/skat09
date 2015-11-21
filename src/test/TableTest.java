package test;

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

import main.Controller;
import main.IController;
import main.SkatVariant;
import main.Table;
import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.NullGame;
import main.gamevariety.Ramsch;
import main.gamevariety.SuitGame;
import main.player.Granny;
import main.player.HumanPlayer;
import main.player.IPlayer;
import main.player.Position;
import main.playingcard.PlayingCard;
import main.ui.CLIOutput;


public class TableTest {


	PlayingCard playingCard1;
	PlayingCard playingCard2;
	PlayingCard playingCard3;
	PlayingCard playingCard4;
	IPlayer player1 = new Granny("Bert");
	IPlayer player2 = new Granny("Ernie");

	Table table = new Table();
	ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	ArrayList<PlayingCard> spitzen = new ArrayList<PlayingCard>();
	CLIOutput output = new CLIOutput(table);
	IController controller = new Controller(table, output);
	IPlayer player3 = new HumanPlayer("Hans", controller);
	PlayingCard[] skat = new PlayingCard[3];

	@Before
	public void setUp() {
		
		playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		player1.setPosition(Position.VORHAND);
		player2.setPosition(Position.MITTELHAND);
		player3.setPosition(Position.HINTERHAND);
		table.setPlayer1(player1);
		table.setPlayer2(player2);
		table.setPlayer3(player3);
		table.getPlayer1().setIsDeclarer(true);
		table.createDeck();
		table.dealOutCards();
		table.getPlayer1().getHand().clear();
		table.getPlayer1().getHand().add(playingCard1);
		table.getPlayer1().getHand().add(playingCard2);
		table.getPlayer1().getHand().add(playingCard3);
	    table.getPlayer2().getHand().clear();
	    table.getPlayer2().getHand().add(playingCard4);
	    table.getPlayer3().getHand().clear();
	    table.getPlayer3().getHand().add(playingCard3);
	    skat[0] = null;
	    skat[1] = null;
	    table.setSkat(skat);
		
		for (PlayingCard.Suit farbe : PlayingCard.Suit.values()) {
			for (PlayingCard.Rank wert : PlayingCard.Rank.values()) {
				PlayingCard karte = new PlayingCard(farbe, wert);
				deck.add(karte);
			}
		}

		PlayingCard[] playedCards = table.getPlayedCards();
		playedCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		table.setPlayedCards(playedCards);
		table.getPlayer1().addTrick(playedCards);
		table.setBiddingValue(18);
		IGameVariety gameVariety = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(gameVariety);
		table.getPlayer1().setGameVariety(gameVariety);
	}

	@After
	public void after() {
		GameVariety gameVariety = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(gameVariety);
	}

	// Konstruktortest fuer Spieler1
	@Test
	public void tablePlayerTest() {
		assertEquals("Bert", table.getPlayer1().getName());
	}

	// Konstruktortest fuer Spieler2
	@Test
	public void tablePlayer2Test() {
		assertEquals("Ernie", table.getPlayer2().getName());
	}

	// Konstruktortest fuer Spieler3
	@Test
	public void tablePlayer3Test() {
		assertEquals("Hans", table.getPlayer3().getName());
	}
	
	//Testet die Methode getHandGame
	@Test
	public void getHandGame1Test() {
		table.setHandGame(true);
		assertTrue(table.getHandGame());
	}
	
	//Testet die Methode getHandGame
	@Test
	public void getHandGame2Test() {
		table.setHandGame(false);
		assertFalse(table.getHandGame());
	}
	
	//Testet die Methode getGameVariety
	@Test
	public void getGameVarietyTest() {
		IGameVariety gameVariety = new GrandGame();
		table.setGameVariety(gameVariety);
		assertEquals(gameVariety, table.getGameVariety());
	}
	
	//Testet die Methode getVariant
	@Test
	public void getVariantTest() {
		table.setVariant(SkatVariant.RAEUBER);
		assertEquals(SkatVariant.RAEUBER, table.getVariant());
	}
	
	//Testet die Methode getBiddingValue
	@Test
	public void getBiddingValueTest() {
		table.setBiddingValue(35);
		assertEquals(35, table.getBiddingValue());
	}
	
	
	//Testet die Methode getBiddingAgentValue
	@Test
	public void biddingAgentValueTest() {
		table.setBiddingAgentValue(42);
		assertEquals(42, table.getBiddingAgentValue());
	}
	
	//Testet , ob die Reizwerte korrekt zurueckgegeben werden
	@Test
	public void getReizwerteTest() {
		SortedSet<Integer> biddingValues = new TreeSet<Integer>();
		for (int i = 18; i <= 162; i += 9) {
			biddingValues.add(i);
		}
		// Alle Herz-Reizwerte hinzufuegen
		for (int i = 20; i <= 180; i += 10) {
			biddingValues.add(i);
		}
		// Alle Pik-Reizwerte hinzufuegen
		for (int i = 22; i <= 198; i += 11) {
			biddingValues.add(i);
		}
	// Alle Kreuz-Reizwerte hinzufuegen
		for (int i = 24; i <= 216; i += 12) {
			biddingValues.add(i);
		}
		// Die restlichen Reizwerte von Nullspiel und Grand/Grand-Ouvert
		biddingValues.add(23);
		biddingValues.add(35);
		biddingValues.add(46);
		biddingValues.add(59);
		biddingValues.add(240);
		biddingValues.add(264);
		biddingValues.add(171);
		biddingValues.add(190);
		biddingValues.add(209);
		biddingValues.add(228);
		
		assertEquals(biddingValues, table.getBiddingValues());
	}
	
	//Testet die Methode getSkat
	@Test
	public void getSkatTest() {
		PlayingCard[] skat = new PlayingCard[2];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		skat[1] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		table.setSkat(skat);
		
		assertArrayEquals(skat, table.getSkat());
	}
	
	// Testet ob das erstellte Deck tatsaechlich alle Karten enthaelt
	@Test
	public void erstelleDeckTest() {
		table.setSixSkat(true);
		table.createDeck();
		assertEquals(36, table.getDeck().size());
	}

	@Test
	public void erstelleDeckTest2() {
		int test = 0;
		table.createDeck();
		for (int i = 0; i<deck.size(); i++) {
			for (int j = 0; j< table.getDeck().size(); j++) {
				if (deck.get(i).equals(table.getDeck().get(j)) == true) {
					test = test + 1;
				}
			}
		}
		assertEquals(32, test);
	}
	
	// Test ob Kartenzahl gleich bleibt
	@Test
	public void mischeDeckTest() {
		table.createDeck();
		table.shuffleDeck();
		assertEquals(32, table.getDeck().size());
	}

	/**
	 * In dieser Methode wird &uuml;berpr&uuml;ft, ob jede Karte nur einmal vorhanden
	 * ist.
	 */
	@Test
	public void mischeDeckTest2() {
		// test dient zum durchzaehlen, ob jede Karte nur einmal vorhanden ist
		int test = 0;
		table.createDeck();
		table.shuffleDeck();
		
		for (int i = 0; i<deck.size(); i++) {
			for (int j = 0; j< table.getDeck().size(); j++) {
				if (deck.get(i).equals(table.getDeck().get(j)) == true) {
					test = test + 1;
				}
			}
		}
		assertEquals(32, test);
	}

	@Test
	public void evaluateTrickTest1() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		IGameVariety gameVariety = new NullGame();
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player2, table.evaluateTrick(gameVariety, playedCards));
		
	}
	
	@Test
	public void evaluateTrickTest2() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		IGameVariety gameVariety = new NullGame();
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player3, table.evaluateTrick(gameVariety, playedCards));
	}
	
	@Test
	public void evaluateTrickTest3() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		IGameVariety gameVariety = new SuitGame(PlayingCard.Suit.HEARTS);
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player3, table.evaluateTrick(gameVariety, playedCards));
	}
	
	@Test
	public void evaluateTrickTest4() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		IGameVariety gameVariety = new GrandGame();
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player1, table.evaluateTrick(gameVariety, playedCards));
	}
	
	@Test
	public void evaluateTrickTest5() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		IGameVariety gameVariety = new NullGame();
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player1, table.evaluateTrick(gameVariety, playedCards));
	}
	
	@Test
	public void evaluateTrickTest6() {
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		IGameVariety gameVariety = new SuitGame(PlayingCard.Suit.ACORNS);
		playedCards[0].setOwner(player1);
		playedCards[1].setOwner(player2);
		playedCards[2].setOwner(player3);
		assertEquals(player1, table.evaluateTrick(gameVariety, playedCards));
	}
	
	@Test
	public void KartenAusteilenTest() {
		boolean test = false;
		table.createDeck();
		table.dealOutCards();
		if(table.getPlayer1().getHand().size() == 10){
			test = true;
		}
		assertTrue(test);
	}

	@Test
	public void kartenAusteilenTest2() {
		table.createDeck();
		table.dealOutCards();
		assertEquals(10, table.getPlayer2().getHand().size());
	}

	@Test
	public void kartenAusteilenTest3() {
		table.createDeck();
		table.dealOutCards();
		assertEquals(10, table.getPlayer3().getHand().size());
	}

	@Test
	public void kartenAusteilenTest4() {
		table.createDeck();
		table.dealOutCards();
		assertEquals(0, table.getDeck().size());
	}
	
	@Test
	public void kartenAusteilenTest5() {
		table.setSixSkat(true);
		table.createDeck();
		table.dealOutCards();
		assertEquals(0, table.getDeck().size());
	}
	
	@Test
	public void getProzentAlleinTest() {
		table.getPlayer1().getGames().clear();
		table.getPlayer1().getGames().add(18);
		//table.addGameCount();
		
		assertEquals(100, table.getPercentDeclarer(player1));
	}
	
	@Test
	public void getAnzahlAlleinTest() {
		table.getPlayer1().getGames().clear();
		table.getPlayer1().getGames().add(18);
		//table.addGameCount();
		
		assertEquals(1, table.getAnzahlAllein(player1));
	}
	
	@Test
	public void anzahlGewinneTest() {
		table.getPlayer1().getGames().clear();
		table.getPlayer1().getGames().add(18);
		assertEquals(1, table.getDeclarersWonGamesSum(player1));
	}

	@Test
	public void ermittleAlleinspielerTest() {
		assertEquals(player1, table.getDeclarer());
	}

	@Test
	public void ermittleAlleinspielerTest2() {
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer2().setIsDeclarer(true);
		assertEquals(player2, table.getDeclarer());
	}
	
	@Test
	public void ermittleAlleinspielerTest3() {
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer3().setIsDeclarer(true);
		assertEquals(player3, table.getDeclarer());
	}
	
	@Test
	public void werteAugenTest() {
		assertEquals(144, table.calculateAugen(deck));
	}

	@Test
	public void getVorhandTest() {
		assertEquals(player1, table.getVorhand());
	}

	@Test
	public void getMittelhandTest() {
		table.createDeck();
		assertEquals(player2, table.getMittelhand());
	}

	@Test
	public void getHinterhandTest() {
		assertEquals(player3, table.getHinterhand());
	}

	@Test
	public void positionWechselnTest() {
		table.changePosition();
		int erg = 0;
		if (table.getHinterhand().equals(player1)) {
			erg = 1;
		}
		if (table.getVorhand().equals(player2)) {
			erg = erg + 1;
		}
		if (table.getMittelhand().equals(player3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}
	
	@Test
	public void positionWechselnTest2() {
		table.changePosition();
		table.changePosition();
		int erg = 0;
		if (table.getMittelhand().equals(player1)) {
			erg = 1;
		}
		if (table.getHinterhand().equals(player2)) {
			erg = erg + 1;
		}
		if (table.getVorhand().equals(player3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}
	
	@Test
	public void positionWechselnTest3() {
		table.changePosition();
		table.changePosition();
		table.changePosition();
		int erg = 0;
		if (table.getVorhand().equals(player1)) {
			erg = 1;
		}
		if (table.getMittelhand().equals(player2)) {
			erg = erg + 1;
		}
		if (table.getHinterhand().equals(player3)) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}

	@Test
	public void positionInitialisierenTest() {
		table.initializePositions();
		int erg = 0;
		if (table.getPlayer1().getPosition() == Position.VORHAND) {
			erg = erg + 1;
		}
		if (table.getPlayer2().getPosition() == Position.MITTELHAND) {
			erg = erg + 1;
		}
		if (table.getPlayer3().getPosition() == Position.HINTERHAND) {
			erg = erg + 1;
		}
		assertEquals(3, erg);
	}

	@Test
	public void berechneStufeTest() {
		assertEquals(1, table.calculateLevel(61));
	}

	@Test
	public void berechneStufeTest2() {
		assertEquals(2, table.calculateLevel(92));
	}

	@Test
	public void berechneStufeTest3() {
		table.getDeclarer().getTricks().clear();
		assertEquals(3, table.calculateLevel(0));
	}

	@Test
	public void berechneStufeTest4() {
		table.setHandGame(true);
		assertEquals(2, table.calculateLevel(61));
	}

	@Test
	public void berechneStufeTest5() {
		table.setHandGame(true);
		assertEquals(3, table.calculateLevel(92));
	}

	@Test
	public void berechneStufeTest6() {
		table.setHandGame(true);
		assertEquals(3, table.calculateLevel(120));
	}
	
	@Test
	public void berechneStufeTest7() {
		table.setHandGame(true);
		table.setSchneider(true);
		table.setSchwarz(true);
		table.setOuvert(true);
		assertEquals(6, table.calculateLevel(100));
	}

	@Test
	public void checkverlorenTest() {
		assertEquals(false, table.checkVerloren(61));
	}

	@Test
	public void checkverlorenTest2() {
		assertEquals(false, table.checkVerloren(99));
	}

	@Test
	public void checkverlorenTest3() {
		table.getDeclarer().setTricks(deck);
		assertEquals(false, table.checkVerloren(120));
	}

	@Test
	public void checkverlorenTest4() {
		assertEquals(true, table.checkVerloren(60));
	}

	@Test
	public void checkverlorenTest5() {
		assertEquals(true, table.checkVerloren(29));
	}

	@Test
	public void checkverlorenTest6() {
		table.getDeclarer().setTricks(null);
		assertEquals(true, table.checkVerloren(0));
	}

	@Test
	public void ueberreizCheckTest() {
		
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(0, table.checkOverbid(18));
	}
	
	@Test
	public void ueberreizCheckTest4() {
		GameVariety spiel = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(spiel);
		table.setBiddingValue(23);
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		table.checkOverbid(18);
		assertEquals(true, table.isOverbidding());
	}
	
	@Test
	public void ueberreizCheckTest5() {
		table.setVariant(SkatVariant.SKAT);
		table.setHandGame(false);
		table.setSchneider(false);
		table.setSchwarz(false);
		table.setOuvert(false);
		GameVariety spiel = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(spiel);
		table.setBiddingValue(23);
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		
		assertEquals(-54, table.checkOverbid(18));
	}
	
	@Test
	public void ueberreizCheckTest2() {
		
		table.setGameVariety(new GrandGame());
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(0, table.checkOverbid(18));
	}
	
	@Test
	public void ueberreizCheckTest3() {
		
		table.setSchneider(true);
		table.setSchwarz(true);
		table.setHandGame(true);
		table.setOuvert(true);
		table.setGameVariety(new GrandGame());
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(0, table.checkOverbid(18));
	}
	

	@Test
	public void naechsterSpielerTest() {
		assertEquals(player2, table.nextPlayer(player1));
	}

	@Test
	public void naechsterSpielerTest2() {
		assertEquals(player3, table.nextPlayer(player2));
	}

	@Test
	public void naechsterSpielerTest3() {
		assertEquals(player1, table.nextPlayer(player3));
	}
	
	@Test
	public void gibMenschlicherSpielerTest1() {
		assertEquals(player3, table.getHumanPlayer());
	}
	
	@Test
	public void gibMenschlicherSpielerTest2() {
		table.setPlayer1(player3);
		table.setPlayer2(player1);
		table.setPlayer3(player2);
		assertEquals(player3, table.getHumanPlayer());
	}
	
	@Test
	public void gibMenschlicherSpielerTest3() {
		table.setPlayer1(player1);
		table.setPlayer2(player3);
		table.setPlayer3(player2);
		assertEquals(player3, table.getHumanPlayer());
	}
	
	@Test
	public void getAktuellePunkteTest() {
		table.getPlayer1().getGames().clear();
		table.getPlayer1().getGames().add(18);
		assertEquals(18, table.getAktuellePunkte(table.getPlayer1()));
	}
	
	@Test
	public void kartenbesitzerGebenTest() {
		boolean test = false;
		table.giveCardsToOwner();
		for (int i= 0; i< table.getPlayer1().getHand().size(); i++){
			if (table.getPlayer1().getHand().get(i).getOwner().equals(table.getPlayer1())) {
				test = true;
			}
		}
		assertTrue(test);

	}
	
	@Test
	public void kartenbesitzerGebenTest2() {
		boolean test = false;
		table.giveCardsToOwner();
		for (int i= 0; i< table.getPlayer3().getHand().size(); i++){
			if (table.getPlayer3().getHand().get(i).getOwner().equals(table.getPlayer3())) {
				test = true;
			}
		}
		assertTrue(test);
	}

	@Test
	public void naechstHoehererReizwertTest() {
		assertEquals(20, table.nextGreaterBiddingValue(18));
	}
	
	@Test
	public void pruefeNeuenReizwertTest() {
		assertEquals(true, table.checkNewBiddingValue(18, 0));
	}
	
	@Test
	public void pruefeNeuenReizwertTest2() {
		assertEquals(true, table.checkNewBiddingValue(18, 20));
	}
	
	@Test
	public void pruefeNeuenReizwertTest3() {
		assertEquals(false, table.checkNewBiddingValue(18, 21));
	}
	
	@Test
	public void naechstniedrigerReizwertTest() {
		assertEquals(18, table.nextLowerBiddingValue(20));
	}
	
	@Test
	public void naechstniedrigerReizwertTest2() {
		assertEquals(20, table.nextLowerBiddingValue(22));
	}
	
	@Test
	public void naechstniedrigerReizwertTest3() {
		assertEquals(22, table.nextLowerBiddingValue(23));
	}
	
	@Test
	public void wertePunkteTest1() {
		table.setVariant(SkatVariant.RAEUBER);
		NullGame spiel = new NullGame();
		table.setGameVariety(spiel);
		table.getDeclarer().getTricks().clear();
		assertEquals(23, table.calculatePoints(0));
	}
	
	@Test
	public void wertePunkteTest() {
		table.setVariant(SkatVariant.SKAT);
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(18, table.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest2() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		IGameVariety spielart = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(spielart);
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(27, table.calculatePoints(91));
	}
	
	@Test
	public void wertePunkteTest3() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		assertEquals(27, table.calculatePoints(120));
	}
	
	@Test
	public void wertePunkteTest4() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		table.getDeclarer().setTricks(deck);
		table.getDeclarer().getTricks().remove(31);
		table.getDeclarer().getTricks().remove(30);
		assertEquals(27, table.calculatePoints(144));
	}
	
	@Test
	public void wertePunkteTest5() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new GrandGame();
		table.setGameVariety(spielart);
		assertEquals(48, table.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest6() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new GrandGame();
		table.setGameVariety(spielart);
		assertEquals(-96, table.calculatePoints(45));
	}
	
	@Test
	public void wertePunkteTest7() {
		table.setVariant(SkatVariant.SKAT);
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(spielart);
		table.setBiddingValue(48);
		assertEquals(-108, table.calculatePoints(62));
	}
	
	@Test
	public void wertePunkteTest8() {
		
		table.setBock(true);
		table.setVariant(SkatVariant.RAMSCHBOCK);
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		table.getDeclarer().setTricks(deck);
		table.getDeclarer().getTricks().remove(31);
		table.getDeclarer().getTricks().remove(30);
		assertEquals(54, table.calculatePoints(144));
	}
	
	@Test
	public void punkteFarbspielTest() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new SuitGame(PlayingCard.Suit.BELLS);
		table.setGameVariety(spielart);
	assertEquals(18, table.pointsSuitGame(62));
	}
	
	@Test
	public void punkteGrandspielTest() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new GrandGame();
		table.setGameVariety(spielart);
		assertEquals(48, table.pointsGrandGame(62));
	}
	
	@Test
	public void punkteNullspielTest() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new NullGame();
		table.setGameVariety(spielart);
		table.getDeclarer().getTricks().clear();
		assertEquals(23, table.pointsNullGame());
	}
	
	@Test
	public void punkteNullspielTest2() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new NullGame();
		table.setGameVariety(spielart);
		table.getDeclarer().getTricks().clear();
		table.setHandGame(true);
		assertEquals(35, table.pointsNullGame());
	}
	
	@Test
	public void punkteNullspielTest3() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new NullGame();
		table.setGameVariety(spielart);
		table.getDeclarer().getTricks().clear();
		table.setHandGame(false);
		table.setOuvert(true);
		assertEquals(46, table.pointsNullGame());
	}
	
	@Test
	public void punkteNullspielTest4() {
		table.getDeclarer().getHand().clear();
		table.getDeclarer().getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		IGameVariety spielart = new NullGame();
		table.setGameVariety(spielart);
		table.getDeclarer().getTricks().clear();
		table.setHandGame(true);
		table.setOuvert(true);
		assertEquals(59, table.pointsNullGame());
	}
	
	@Test
	public void spielAuswertenTest() {
		table.getDeclarer().getTricks().clear();
		assertFalse(table.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest2() {
		table.getDeclarer().getTricks().clear();
		IGameVariety spielart = new NullGame();
		table.setGameVariety(spielart);
		assertTrue(table.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest3() {
		
		table.setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		table.getDeclarer().getTricks().clear();
		table.getDeclarer().setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		PlayingCard karte = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		table.getDeclarer().getTricks().add(karte);
		PlayingCard karte2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		table.getDeclarer().getTricks().add(karte2);
		PlayingCard karte3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);
		table.getDeclarer().getTricks().add(karte3);
		table.getDeclarer().getTricks().add(karte3);
		table.getDeclarer().getHand().add(karte2);
		table.getDeclarer().arrangeMatadorsJackStraitOrder();
		
		table.setHandGame(true);
		table.setSchneider(true);
		assertTrue(table.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest4() {
		IPlayer tmp = table.getPlayer1();
		table.setPlayer1(player2);
		table.setPlayer2(tmp);
		table.getDeclarer().getTricks().clear();
		assertFalse(table.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest5() {
		IPlayer tmp = table.getPlayer1();
		table.setPlayer1(player3);
		table.setPlayer3(tmp);
		table.getDeclarer().getTricks().clear();
		assertFalse(table.evaluateGame());
	}
	
	@Test
	public void spielAuswertenTest6() {
		
		table.setGameVariety(new Ramsch());
		table.setBock(true);
		table.setSixSkat(true);
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		skat[1] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN);
		skat[2] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		table.setSkat(skat);
		player1.getTricks().clear();
		player2.getTricks().clear();
		player3.getTricks().clear();
		player1.getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		player1.getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		player2.getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		assertTrue(table.evaluateGame());
	}
	
	@Test
	public void sortiereSpielerRamschTest() {
		
		table.getPlayer1().getTricks().clear();
		table.getPlayer1().getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		table.getPlayer1().getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		table.getPlayer2().getTricks().clear();
		table.getPlayer2().getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		table.getPlayer2().getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		table.getPlayer3().getTricks().clear();
		table.getPlayer3().getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		table.getPlayer3().getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING));
		
		IPlayer[] spielerU = new IPlayer[3];
		spielerU[0] = player1;
		spielerU[1] = player2;
		spielerU[2] = player3;
	
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = player2;
		spieler[1] = player3;
		spieler[2] = player1;
	
		assertArrayEquals(spieler, table.sortPlayerRamsch(spielerU));
	}
	
	@Test
	public void entscheideRamschTest() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = player1;
		spieler[1] = player2;
		spieler[2] = player3;
		spieler[2].getTricks().clear();
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		
		IPlayer[] ergebnis = table.decideRamsch(spieler, 0, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(240);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0)); 
	}
	
	@Test
	public void entscheideRamschTest2() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = player1;
		spieler[1] = player2;
		spieler[2] = player3;
		spieler[0].getTricks().clear();
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		IPlayer[] ergebnis = table.decideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(-124);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0));
	}
	
	@Test
	public void entscheideRamschTest3() {
		
		IPlayer[] spieler = new IPlayer[3];
		spieler[0] = player1;
		spieler[1] = player2;
		spieler[2] = player3;
		spieler[0].getTricks().add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		spieler[2].getTricks().add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		IPlayer[] ergebnis = table.decideRamsch(spieler, 20, 2);
		
		Granny vergleich = new Granny("heino");
		vergleich.getGames().add(-62);
		
		assertEquals(vergleich.getGames().get(0), ergebnis[2].getGames().get(0));
	}
	
	@Test
	public void addAnzahlSpieleTest() {
		
		table.addGameCount();
		assertEquals(2, table.getGameRoundCounter());
	}
	
	@Test
	public void ermittleMitspielerTest1() {
		
		table.getPlayer1().setIsDeclarer(true);
		assertEquals(player3, table.determineTeammate(player2));
	}
	
	@Test
	public void ermittleMitspielerTest2() {
		
		table.getPlayer1().setIsDeclarer(true);
		assertEquals(player2, table.determineTeammate(player3));
	}
	
	//Spieler 1 ist default Alleinspieler =D
	@Test
	public void ermittleMitspielerTest3() {
		
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer2().setIsDeclarer(true);
		assertEquals(player1, table.determineTeammate(player3));
	}
	
	@Test
	public void ermittleMitspielerTest4() {
		
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer2().setIsDeclarer(true);
		assertEquals(player3, table.determineTeammate(player1));
	}
	
	@Test
	public void ermittleMitspielerTest5() {
		
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer3().setIsDeclarer(true);
		assertEquals(player1, table.determineTeammate(player2));
	}
	
	@Test
	public void ermittleMitspielerTest6() {
		
		table.getPlayer1().setIsDeclarer(false);
		table.getPlayer3().setIsDeclarer(true);
		assertEquals(player2, table.determineTeammate(player1));
	}
	
	@Test
	public void mitspielerSetzenTest1() {
		table.setTeammate();
		assertEquals(null, table.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest2() {
		table.setTeammate();
		assertEquals(player3, table.getPlayer2().getTeammate());
	}
	
	@Test 
	public void mitspielerSetzenTest3() {
		table.setTeammate();
		assertEquals(player2, table.getPlayer3().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest4() {
		
		player1.setIsDeclarer(false);
		player2.setIsDeclarer(true);
		table.setTeammate();
		assertEquals(player3, table.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest5() {
		table.setGameVariety(new Ramsch());
		table.setTeammate();
		assertEquals(null, table.getPlayer1().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest6() {
		table.setGameVariety(new Ramsch());
		table.setTeammate();
		assertEquals(null, table.getPlayer2().getTeammate());
	}
	
	@Test
	public void mitspielerSetzenTest7() {
		table.setGameVariety(new Ramsch());
		table.setTeammate();
		assertEquals(null, table.getPlayer3().getTeammate());
	}
	
	@Test
	public void nullVerlorenTest() {
		
		table.getDeclarer().getTricks().clear();
		assertFalse(table.nullVerloren());
	}
	
	@Test
	public void nullVerlorenTest2() {
		
		table.getDeclarer().getTricks().clear();
		ArrayList<PlayingCard> stiche = new ArrayList<PlayingCard>();
		stiche.add(playingCard1);
		stiche.add(playingCard2);
		stiche.add(playingCard3);
		table.getDeclarer().setTricks(stiche);
		assertTrue(table.nullVerloren());
	}
	
	@Test
	public void anderesSpielVerlorenTest() {
		
		assertTrue(table.anderesSpielVerloren(60));
	}
	
	@Test
	public void anderesSpielVerlorenTest2() {
		
		table.setSchneider(true);
		assertTrue(table.anderesSpielVerloren(88));
	}
	
	@Test
	public void anderesSpielVerlorenTest3() {
	
		table.setSixSkat(false);
		table.setSchwarz(true);
		table.createDeck();
		player1.getTricks().clear();
		for (PlayingCard karte : table.getDeck()) {
			
			player1.getTricks().add(karte);
		}
		for (int i = 0; i < 2; i++) {
			
			player1.getTricks().remove(0);
		}
		System.out.println(player1.getTricks().size());
		assertTrue(table.anderesSpielVerloren(100));
	}
	
	@Test
	public void anderesSpielVerlorenTest4() {
		
		table.setSchneider(false);
		table.setSchwarz(false);
		table.setOuvert(true);
		player1.getTricks().clear();
		assertTrue(table.anderesSpielVerloren(61));
	}
	
	@Test
	public void anderesSpielVerlorenTest5() {
		
		table.setOuvert(false);
		assertFalse(table.anderesSpielVerloren(62));
	}
}