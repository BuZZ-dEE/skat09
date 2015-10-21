package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

import main.Controller;
import main.SkatVariant;
import main.Table;
import main.gamevariety.GrandGame;
import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.player.Granny;
import main.player.HumanPlayer;
import main.player.IPlayer;
import main.player.Player;
import main.player.Position;
import main.player.RuleCompliantPlayer;
import main.player.SmartPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;
import test.player.HumanPlayerStub;
import test.player.HumanPlayerStub2;
import test.ui.OutputStub;
import main.ui.Output;
import main.ui.CLIOutput;
import main.ui.IOutput;


public class ControllerTest {
	
	private Table tisch;
	private Output ausgabe;
	private Controller controller;
	
	
	public ControllerTest(){
		
		tisch = new Table();
		ausgabe = new CLIOutput(tisch);
		controller = new Controller(tisch,ausgabe);
		
	}
	
	@Test
	public void getAusgabeTest() {
		assertEquals(ausgabe,controller.getOutput());
	}
	
	
	@Test
	public void releaseTest() {
		
		boolean erfuellt;
		controller.release();
		
		if (controller.getTable() == null && controller.getOutput() == null){
			erfuellt = true;
			
			assertTrue(erfuellt);
		}
	}
	
	@Test
	public void anmeldenTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controller2 = new Controller(tisch, stubbie);
		controller2.logIn();
		assertEquals("Knut",controller2.getOutput().name());
	}
	
	@Test
	public void waehleGegnerTest1(){
		
		OutputStub gegner1 = new OutputStub("o",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new Granny("Oma Karla");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}

	@Test
	public void waehleGegnerTest2(){
		
		OutputStub gegner1 = new OutputStub("o",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new Granny("Oma Berta");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest3(){
		
		OutputStub gegner1 = new OutputStub("r",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new RuleCompliantPlayer("Hans");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest4(){
		
		OutputStub gegner1 = new OutputStub("r",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new RuleCompliantPlayer("Franz");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest5(){
		
		OutputStub gegner1 = new OutputStub("s",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new SmartPlayer("Heinz");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest6(){
		
		OutputStub gegner1 = new OutputStub("s",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.selectAdversary();
		
		IPlayer spieler = new SmartPlayer("Wolfgang");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTable().getPlayer3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleSkartartTest1() {
		
		OutputStub skat1 = new OutputStub(null,"r",null);
		Controller controller = new Controller(tisch,skat1);
		controller.chooseSkatVariant();
		SkatVariant variante = SkatVariant.RAEUBER;
		assertEquals(variante,controller.getTable().getVariant());
	}
	
	@Test
	public void waehleSkartartTest2() {
		
		OutputStub skat1 = new OutputStub(null,"i",null);
		Controller controller = new Controller(tisch,skat1);
		controller.chooseSkatVariant();
		SkatVariant variante = SkatVariant.SKAT;
		assertEquals(variante,controller.getTable().getVariant());
	}
	
	@Test
	public void waehleSkartartTest3() {
		
		OutputStub skat1 = new OutputStub(null,"b",null);
		Controller controller = new Controller(tisch,skat1);
		controller.chooseSkatVariant();
		SkatVariant variante = SkatVariant.RAMSCHBOCK;
		assertEquals(variante,controller.getTable().getVariant());
	}
	
	@Test
	public void waehleSkatBlattTest1() {
		
		OutputStub wahl1 = new OutputStub(null,null,"f");
		Controller controller = new Controller(tisch,wahl1);
		controller.selectSkatDeck();
		assertFalse(PlayingCard.isGermanDeck());
		}
	
	@Test
	@Ignore
	public void waehleSkatBlattTest2() {
		// FIXME failing test
		OutputStub wahl1 = new OutputStub(null,null,"d");
		Controller controller = new Controller(tisch,wahl1);
		controller.selectSkatDeck();
		assertTrue(PlayingCard.isGermanDeck());
		}
	
	@Test
	public void leiteReizenTest() {
		
		Player spieler = new Granny("Hannelore");
		tisch.setPlayer1(spieler);
		tisch.setPlayer2(new Granny("Lara"));
		tisch.setPlayer3(new Granny("Hoi"));
		tisch.initializePositions();
		IOutput ausgabe = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch, ausgabe);
		try {
			controller2.coordinateBidding();
		} catch (IOException e) {
			e.printStackTrace();
		}
				assertEquals(null, controller.getTable().getDeclarer());
	}
	
	@Test
	public void leiteReizenTest2() {
		
		IPlayer spieler = new HumanPlayerStub("Hannelore");
		tisch.setPlayer1(spieler);
		tisch.setPlayer2(new RuleCompliantPlayer("Lara"));
		tisch.setPlayer3(new RuleCompliantPlayer("Hoi"));
		tisch.initializePositions();
		IOutput ausgabe = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch, ausgabe);
		try {
			controller2.coordinateBidding();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(spieler.getName(), controller.getTable().getDeclarer().getName());
	}
	
	@Test
	public void reizenOderReizagent() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(20);
		
		assertTrue(controller.bidOrBiddingAgent(spieler1, 18, true));
	}
	
	@Test
	public void reizenOderReizagent2() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(20);
		
		assertTrue(controller.bidOrBiddingAgent(spieler1, 23, true));
	}
	
	@Test
	@Ignore
	public void reizenOderReizagent3() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(0);
		// FIXME for return false
		assertTrue(controller.bidOrBiddingAgent(spieler1, 20, true));
	}
	
	@Test
	public void reizenOderReizagent4() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(0);
		
		assertFalse(controller.bidOrBiddingAgent(spieler1, 23, true));
	}
	
	@Test
	@Ignore
	public void reizenOderReizagent5() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(0);
		// FIXME for return false
		assertTrue(controller.bidOrBiddingAgent(spieler1, 20, false));
	}
	
	@Test
	public void reizenOderReizagent6() {
		
		IPlayer spieler1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(spieler1);
		IPlayer spieler2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(spieler2);
		IPlayer spieler3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(spieler3);
		controller.getTable().setBiddingAgentValue(0);
		
		assertFalse(controller.bidOrBiddingAgent(spieler1, 23, false));
	}
	
	@Test
	public void reizen1Test1() {
		
		IPlayer spieler1 = new Granny("Hannelore");
		IPlayer spieler2 = new Granny("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		
		assertEquals(spieler1.getName(), controller.bidding1(spieler1, spieler2).getName());
	}
	
	@Test
	public void reizen1Test2() {
		
		IPlayer spieler1 = new Granny("Hannelore");
		IPlayer spieler2 = new RuleCompliantPlayer("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		
		assertEquals(spieler2.getName(), controller.bidding1(spieler1, spieler2).getName());
	}
	
	
	@Test
	public void reizen2Test1() {
		
		IPlayer spieler1 = new Granny("Hannelore");
		IPlayer spieler2 = new Granny("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		
		assertEquals(null, controller.bidding2(spieler1, spieler2));
	}
	
	@Test
	@Ignore
	public void reizen2Test2() {
		
		IPlayer spieler1 = new HumanPlayerStub("Hannelore");
		IPlayer spieler2 = new HumanPlayerStub2("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		// FIXME if returned player is null
		assertEquals(spieler1, controller.bidding2(spieler1, spieler2));
	}
	
	@Test
	@Ignore
	public void reizen2Test3() {
		
		IPlayer spieler1 = new HumanPlayerStub2("Hannelore");
		IPlayer spieler2 = new HumanPlayerStub("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		// FIXME if returned player is null
		assertEquals(spieler2, controller.bidding2(spieler1, spieler2));
	}
	
	@Test
	@Ignore
	public void reizen2Test4() {
		
		IPlayer spieler1 = new HumanPlayerStub2("Hannelore");
		IPlayer spieler2 = new Granny("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		// FIXME if returned player is null
		assertEquals(spieler1, controller.bidding2(spieler1, spieler2));
	}
	
	@Test
	@Ignore
	public void reizen2Test5() {
		
		IPlayer spieler1 = new Granny("Hannelore");
		IPlayer spieler2 = new HumanPlayerStub2("Friedel");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		// FIXME if returned player is null
		assertEquals(spieler2, controller.bidding2(spieler1, spieler2));
	}
	
	
	@Test
	public void entscheideRauberspielTest() {
		
		
		tisch.setPlayer1(new Granny("Hildegard"));
		tisch.setPlayer2(new Granny("Gräfin Johanita von Schwanenstein"));
		
		tisch.setPlayer3(new Granny("Zensursula"));
		tisch.getPlayer1().setPosition(Position.VORHAND);
		controller.decideRaeuberGame();
		
		assertTrue(tisch.getVorhand().isDeclarer());
		

	}
	
	@Test
	public void bereiteSpielvorTest() {
		
		tisch.setPlayer1(new HumanPlayer("Hildegard", controller));
		SmartPlayer sspieler = new SmartPlayer("Gräfin Johanita von Schwanenstein");
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		sspieler.setHand(blatt);
		sspieler.setAnfangsblatt(blatt);
		tisch.setPlayer2(sspieler);
		tisch.setPlayer3(new Granny("Zensursula"));
		
		Controller controller = new Controller(tisch, ausgabe);
		
		controller.prepareGame();
			boolean hatnamen = true;
		for (PlayingCard karte : tisch.getPlayer1().getHand()) {
			if (karte.getOwner() == null) {
				hatnamen = false;
			}
				}
		for (PlayingCard karte : tisch.getPlayer2().getHand()) {
			if (karte.getOwner() == null) {
				hatnamen = false;
			}
				}
		for (PlayingCard karte : tisch.getPlayer3().getHand()) {
			if (karte.getOwner() == null) {
				hatnamen = false;
			}
				}
		assertTrue(hatnamen);
	}
	
	@Test
	@Ignore
	public void alleinspielerAktionenTest() {
		
		Table tisch2 = new Table();
		OutputStub ausgabe2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch2, ausgabe2);
		HumanPlayerStub mensch = new HumanPlayerStub("Bernd");
		mensch.setIsDeclarer(true);
		mensch.setHand(new ArrayList<PlayingCard>());
		for (int i = 0; i < 10; i++) {
			mensch.getHand().add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		}
		controller2.getTable().setPlayer1(mensch);
		Player spieler2 = new Granny("Basti");
		Player spieler3 = new Granny("ungluecklich");
		controller2.getTable().setPlayer2(spieler2);
		controller2.getTable().setPlayer3(spieler3);
		controller2.getTable().setSixSkat(true);
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		skat[1] = new PlayingCard(Suit.HEARTS, Value.SIX);
		skat[2] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		controller2.getTable().setSkat(skat);
		controller2.getTable().setGameVariety(new SuitGame(Suit.BELLS));

		
		//handgame pruefen
		boolean handspiel = false;
		if (!controller2.getTable().getHandGame()) {
			handspiel = true;
		}
		
		//spielart pruefen
		boolean spielart2 = false;
		System.out.println(controller2.getTable().getGameVariety().toString());
		if (controller2.getTable().getGameVariety().toString().equals((new SuitGame(Suit.BELLS)).toString())) {
			spielart2 = true;
		}
		
		try {
			// FIXME
			controller2.declarerActions();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean allesKorrekt = false;
		if (handspiel && spielart2) {
			allesKorrekt = true;
		}
		
		assertTrue(allesKorrekt);
	}
	
	@Test
	public void auswertungTest() {
		
		OutputStub ausgabe = new OutputStub(null, null, null);
		Table tisch2 = new Table();
		Controller controller = new Controller(tisch2, ausgabe);
		
		IPlayer spieler1 = new Granny("Erna");
		IPlayer spieler2 = new Granny("Roy");
		IPlayer spieler3 = new Granny("Marry");
		
		spieler1.setIsDeclarer(true);
		
		ArrayList<PlayingCard> blatt1 = new ArrayList<PlayingCard>(); 
		blatt1.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt1.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		blatt1.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		blatt1.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt1.add(new PlayingCard(Suit.ACORNS, Value.KING));
		blatt1.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt1.add(new PlayingCard(Suit.BELLS, Value.TEN));
		blatt1.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt1.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		spieler1.setTricks(blatt1);
		spieler1.setHand(blatt1);
		spieler1.setGameVariety(new SuitGame(Suit.ACORNS));
		spieler1.arrangeMatadorsJackStraitOrder();
		
		ArrayList<PlayingCard> blatt2 = new ArrayList<PlayingCard>(); 
		blatt2.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt2.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		blatt2.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt2.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		spieler2.setTricks(blatt2);
		
		ArrayList<PlayingCard> blatt3 = new ArrayList<PlayingCard>(); 
		blatt3.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		blatt3.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt3.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt3.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		spieler3.setTricks(blatt3);
		
		tisch2.setPlayer1(spieler1);
		tisch2.setPlayer2(spieler2);
		tisch2.setPlayer3(spieler3);
		tisch2.setGameVariety(new SuitGame(Suit.ACORNS));
		
		controller.evaluation();
		
		OutputStub ausgabe2 = (OutputStub) controller.getOutput();
		assertEquals(24, ausgabe2.getPunkte());
		
	}
	
	@Test
	public void aufrauemenTest() {
		
		
		PlayingCard[] skatkarten = new PlayingCard[3];
		skatkarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SIX));
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
	
		Player spieler1 = new Granny("Joy");
		spieler1.setHand(blatt);
		spieler1.setGameVariety(new GrandGame());
		spieler1.setIsDeclarer(true);
		Player spieler2 = new RuleCompliantPlayer("Leon");
		spieler2.setHand(blatt);
		spieler2.setTricks(blatt);
		Player spieler3 = new RuleCompliantPlayer("John Wayne");
		spieler3.setHand(blatt);
		spieler3.setAllPlayedCards(blatt);
		
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		
		controller.cleanUp();
		
		Table tisch3 = controller.getTable();
				
		boolean spieler3Aufgeraeumt = false;

		IPlayer sp3 = controller.getTable().getPlayer3();
		if (sp3.getHand() == null && sp3.isDeclarer() == false &&
				sp3.getTricks().size() == 0 && sp3.getAllPlayedCards().size() == 0) {
			spieler3Aufgeraeumt = true;
		}
		
		assertTrue(spieler3Aufgeraeumt);
	}
	
	@Test
	public void aufraeumenTest2() {
		
		PlayingCard[] skatkarten = new PlayingCard[3];
		skatkarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SIX));
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
	
		Player spieler1 = new Granny("Joy");
		spieler1.setHand(blatt);
		spieler1.setGameVariety(new GrandGame());
		spieler1.setIsDeclarer(true);
		Player spieler2 = new RuleCompliantPlayer("Leon");
		spieler2.setHand(blatt);
		spieler2.setTricks(blatt);
		Player spieler3 = new RuleCompliantPlayer("John Wayne");
		spieler3.setHand(blatt);
		spieler3.setAllPlayedCards(blatt);
		
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		
		controller.cleanUp();
		
		Table tisch3 = controller.getTable();
		
		boolean tischAufgeraeumt = false;
		
		if (tisch3.getSkat()[0] == null && tisch3.getBiddingAgentValue() == 0 && tisch3.getBiddingValue() == 18 &&
				tisch3.getBock() == false && tisch3.getSpaltarsch() == false) {
			tischAufgeraeumt = true;
		}
		
		assertTrue(tischAufgeraeumt);
	}
	
	@Test
	public void aufraeumenTest3() {
		
		PlayingCard[] skatkarten = new PlayingCard[3];
		skatkarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SIX));
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
	
		Player spieler1 = new Granny("Joy");
		spieler1.setHand(blatt);
		spieler1.setGameVariety(new GrandGame());
		spieler1.setIsDeclarer(true);
		Player spieler2 = new RuleCompliantPlayer("Leon");
		spieler2.setHand(blatt);
		spieler2.setTricks(blatt);
		Player spieler3 = new RuleCompliantPlayer("John Wayne");
		spieler3.setHand(blatt);
		spieler3.setAllPlayedCards(blatt);
		
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		
		controller.cleanUp();
		
		Table tisch3 = controller.getTable();
		
		boolean spieler1Aufgeraeumt = false;
		
		IPlayer sp1 = controller.getTable().getPlayer1();
		if (sp1.getHand() == null && !sp1.isDeclarer() &&
				sp1.getTricks().size() == 0 && sp1.getAllPlayedCards().size() == 0) {
			spieler1Aufgeraeumt = true;
		}
		
		assertTrue(spieler1Aufgeraeumt);
	}
	
	@Test
	public void aufraeumen4Test() {
	
		PlayingCard[] skatkarten = new PlayingCard[3];
		skatkarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SIX));
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
	
		Player spieler1 = new Granny("Joy");
		spieler1.setHand(blatt);
		spieler1.setGameVariety(new GrandGame());
		spieler1.setIsDeclarer(true);
		Player spieler2 = new RuleCompliantPlayer("Leon");
		spieler2.setHand(blatt);
		spieler2.setTricks(blatt);
		Player spieler3 = new RuleCompliantPlayer("John Wayne");
		spieler3.setHand(blatt);
		spieler3.setAllPlayedCards(blatt);
		
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		
		controller.cleanUp();
		
		Table tisch3 = controller.getTable();
		
		boolean spieler2Aufgeraeumt = false;
		
		IPlayer sp2 = controller.getTable().getPlayer2();
		if (sp2.getHand() == null && sp2.isDeclarer() == false &&
				sp2.getTricks().size() == 0 && sp2.getAllPlayedCards().size() == 0) {
			spieler2Aufgeraeumt = true;
		}
		
		assertTrue(spieler2Aufgeraeumt);
	}
	
	@Test
	public void stichAuswertungTest() {
		
		Table tisch2 = new Table();
		OutputStub ausgabe2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch2, ausgabe2);
		
		Player gewinner = new Granny("Gustav Gans");
		Player s2 = new Granny("Benny");
		Player s3 = new Granny("Daniel");
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		PlayingCard k1 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard k2 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard k3 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		k1.setOwner(gewinner);
		k2.setOwner(s2);
		k3.setOwner(s3);
		gespielteKarten[0] = k1;
		gespielteKarten[1] = k2;
		gespielteKarten[2] = k3;
		
		controller2.outputTrickEvaluation(gespielteKarten, gewinner);
		
		OutputStub ausgabe = (OutputStub) controller2.getOutput();
		assertEquals(3, ausgabe.getGespielteKartenZahl());
		
		
	}
	
	@Test
	public void reizagentTest() {
		
		Player spieler = new Granny("Tini");
		tisch.setBiddingValue(23);
		tisch.setBiddingAgentValue(30);
		assertTrue(controller.reizagent(spieler));
	}
	
	@Test
	public void reizagentTest2() {
		
		Player spieler = new Granny("Tini");
		tisch.setBiddingValue(30);
		tisch.setBiddingAgentValue(23);
		assertFalse(controller.reizagent(spieler));
	}
	
	@Test
	public void skartkartenBesitzerGebenTest() {
		
		IPlayer oma = new Granny("o1");
		controller.getTable().setPlayer1(oma);
		controller.getTable().getPlayer1().setIsDeclarer(true);
		controller.getTable().setPlayer2(new Granny("o2"));
		controller.getTable().setPlayer3(new Granny("o3"));
		controller.getTable().setSixSkat(true);
		controller.getTable().createDeck();
		controller.getTable().dealOutCards();
		controller.getTable().giveCardsToOwner();
		
		controller.assignSkatCardsToOwner();
		
		PlayingCard[] skat = controller.getTable().getSkat();
		boolean initErfolgreich = false;
		if(skat[0].getOwner().equals(oma) && skat[1].getOwner().equals(oma) && 
				skat[2].getOwner().equals(oma)) {

			initErfolgreich = true;
		}
		assertTrue(initErfolgreich);
	}
	
	@Test
	public void flagsSetzenTest1() {
		
		tisch.setPlayer1(new Granny("Erna"));
		tisch.getPlayer1().setIsDeclarer(true);
		tisch.setPlayer2(new Granny("Renate"));
		tisch.setPlayer3(new Granny("Mochochocho"));
		tisch.setGameVariety(new NullGame());
		controller.setFlags(tisch.getPlayer1(), tisch.getGameVariety());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest2() {
		
		tisch.setPlayer1(new Granny("Erna"));
		tisch.getPlayer1().setIsDeclarer(true);
		tisch.setPlayer2(new Granny("Renate"));
		tisch.setPlayer3(new Granny("Mochochocho"));
		tisch.setGameVariety(new NullGame());
		tisch.setHandGame(false);
		controller.setFlags(tisch.getPlayer1(), tisch.getGameVariety());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest3() {
		
		tisch.setPlayer1(new Granny("Erna"));
		tisch.getPlayer1().setIsDeclarer(true);
		tisch.setPlayer2(new Granny("Renate"));
		tisch.setPlayer3(new Granny("Mochochocho"));
		tisch.setGameVariety(new GrandGame());
		controller.setFlags(tisch.getPlayer1(), tisch.getGameVariety());
		
		boolean ergebnis = false;
		if (!tisch.getSchneider() && !tisch.getSchwarz() && !tisch.getOuvert()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest4() {
		
		tisch.setPlayer1(new HumanPlayerStub("Bert"));
		tisch.getPlayer1().setIsDeclarer(true);
		tisch.setPlayer2(new Granny("Renate"));
		tisch.setPlayer3(new Granny("Mochochocho"));
		tisch.setGameVariety(new GrandGame());
		tisch.setHandGame(true);
		controller.setFlags(tisch.getPlayer1(), tisch.getGameVariety());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz() && tisch.getOuvert()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void ramschenTest() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.ramschen();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void normalerSpielverlaufTest() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.normalGamePlay();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielRaeuberskatTest() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playRaeuberskat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielRamschBockTest() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.setSpaltarsch(true);
		tisch2.setRamschRounds(0);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielRamschBockTest2() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.setSpaltarsch(true);
		tisch2.setRamschRounds(1);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielRamschBockTest3() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new RuleCompliantPlayer("Hilde"));
		tisch2.setPlayer2(new RuleCompliantPlayer("Heide"));
		tisch2.setPlayer3(new RuleCompliantPlayer("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielIntSkatTest() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new Granny("Hilde"));
		tisch2.setPlayer2(new Granny("Heide"));
		tisch2.setPlayer3(new Granny("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void spielIntSkatTest2() {
		
		Table tisch2 = new Table();
		tisch2.setPlayer1(new RuleCompliantPlayer("Hilde"));
		tisch2.setPlayer2(new RuleCompliantPlayer("Heide"));
		tisch2.setPlayer3(new RuleCompliantPlayer("Harald"));
		tisch2.getPlayer1().setIsDeclarer(true);
		tisch2.initializePositions();
		tisch2.createDeck();
		tisch2.dealOutCards();
		tisch2.giveCardsToOwner();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.playIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void schlauerSpielerInitTest() {
		
		Player spieler1 = new SmartPlayer("Halo");
		Player spieler2 = new SmartPlayer("Evin");
		Player spieler3 = new RuleCompliantPlayer("Bernd");
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		
		spieler1.setHand(blatt);
		spieler2.setHand(blatt);
		spieler3.setHand(blatt);
		
		spieler1.setIsDeclarer(true);
		
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		skat[1] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		skat[2] = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		tisch.setSkat(skat);
		tisch.setPlayer1(spieler1);
		tisch.setPlayer2(spieler2);
		tisch.setPlayer3(spieler3);
		ArrayList<PlayingCard> skat2 = new ArrayList<PlayingCard>(Arrays.asList(skat));
		
		SmartPlayer ergebnis = (SmartPlayer) tisch.getPlayer1();
		
		controller.initializeSmartPlayer();
		
		assertEquals(skat2, ergebnis.getSkat());
	}
	
	@Test
	public void namenVergleichTest() {
		
		IPlayer spieler1 = new Granny("Gertrud");
		IPlayer spieler2 = new Granny("Gertrud");
		IPlayer spieler3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		controller.namesComparison();
		assertEquals("Gertrud1", controller.getTable().getPlayer1().getName());
	}
	
	@Test
	public void namenVergleichTest2() {
		
		IPlayer spieler1 = new Granny("Gertrud");
		IPlayer spieler2 = new Granny("Gertrud");
		IPlayer spieler3 = new Granny("Hans");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		controller.namesComparison();
		assertEquals("Gertrud2", controller.getTable().getPlayer2().getName());
	}
	
	@Test
	public void namenVergleichTest3() {
		
		IPlayer spieler1 = new Granny("Gertrud");
		IPlayer spieler2 = new Granny("Hans");
		IPlayer spieler3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		controller.namesComparison();
		assertEquals("Gertrud2", controller.getTable().getPlayer3().getName());
	}
	
	@Test
	public void namenVergleichTest4() {
		
		IPlayer spieler1 = new Granny("Hans");
		IPlayer spieler2 = new Granny("Gertrud");
		IPlayer spieler3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(spieler1);
		controller.getTable().setPlayer2(spieler2);
		controller.getTable().setPlayer3(spieler3);
		controller.namesComparison();
		assertEquals("Gertrud1", controller.getTable().getPlayer2().getName());
	}
	
	@Test
	public void spielbeendenTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controll = new Controller(tisch,stubbie);
		
		assertTrue(controll.quitGame());
	}
	
	@Test
	public void warteTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controll = new Controller(tisch,stubbie);
		controll.waiting();
		assertTrue(controll.getOutput().getRelease());
	}
}
