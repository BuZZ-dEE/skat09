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
import test.player.HumanPlayerStub;
import test.player.HumanPlayerStub2;
import test.ui.OutputStub;
import main.ui.Output;
import main.ui.CLIOutput;
import main.ui.IOutput;


public class ControllerTest {
	
	private Table table;
	private Output output;
	private Controller controller;
	
	
	public ControllerTest(){
		
		table = new Table();
		output = new CLIOutput(table);
		controller = new Controller(table, output);
		
	}
	
	@Test
	public void getOutputTest() {
		assertEquals(output,controller.getOutput());
	}
	
	
	@Test
	public void releaseTest() {
		
		boolean success;
		controller.release();
		
		if (controller.getTable() == null && controller.getOutput() == null){
			success = true;
			
			assertTrue(success);
		}
	}
	
	@Test
	public void logInTest() {
		
		OutputStub stubbie = new OutputStub(null, null, null);
		Controller controller2 = new Controller(table, stubbie);
		controller2.logIn();
		assertEquals("Knut",controller2.getOutput().name());
	}
	
	@Test
	public void selectAdversaryTest1(){
		
		OutputStub adversary1 = new OutputStub("o", null, null);
		Controller controller = new Controller(table, adversary1);
		controller.selectAdversary();
		
		IPlayer player = new Granny("Oma Karla");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer2())) {
				check = true;
		}
		
		assertTrue(check);
		
	}

	@Test
	public void selectAdversaryTest2(){
		
		OutputStub adversry1 = new OutputStub("o", null, null);
		Controller controller = new Controller(table, adversry1);
		controller.selectAdversary();
		
		IPlayer player = new Granny("Oma Berta");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer3())) {
				check = true;
		}
		
		assertTrue(check);
		
	}
	
	@Test
	public void selectAdversaryTest3(){
		
		OutputStub adversary1 = new OutputStub("r", null, null);
		Controller controller = new Controller(table, adversary1);
		controller.selectAdversary();
		
		IPlayer player = new RuleCompliantPlayer("Hans");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer2())) {
				check = true;
		}
		
		assertTrue(check);
		
	}
	
	@Test
	public void selectAdversaryTest4(){
		
		OutputStub adversary1 = new OutputStub("r", null, null);
		Controller controller = new Controller(table, adversary1);
		controller.selectAdversary();
		
		IPlayer player = new RuleCompliantPlayer("Franz");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer3())) {
				check = true;
		}
		
		assertTrue(check);
		
	}
	
	@Test
	public void selectAdversaryTest5(){
		
		OutputStub adversary1 = new OutputStub("s", null, null);
		Controller controller = new Controller(table, adversary1);
		controller.selectAdversary();
		
		IPlayer player = new SmartPlayer("Heinz");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer2())) {
				check = true;
		}
		
		assertTrue(check);
		
	}
	
	@Test
	public void selectAdversaryTest6(){
		
		OutputStub adversary1 = new OutputStub("s", null, null);
		Controller controller = new Controller(table, adversary1);
		controller.selectAdversary();
		
		IPlayer player = new SmartPlayer("Wolfgang");
		
		boolean check = false;
		
		if (player.equals(controller.getTable().getPlayer3())) {
				check = true;
		}
		
		assertTrue(check);
		
	}
	
	@Test
	public void chooseSkatVariantTest1() {
		
		OutputStub skat1 = new OutputStub(null, "r", null);
		Controller controller = new Controller(table, skat1);
		controller.chooseSkatVariant();
		SkatVariant variant = SkatVariant.RAEUBER;
		assertEquals(variant,controller.getTable().getVariant());
	}
	
	@Test
	public void chooseSkatVariantTest2() {
		
		OutputStub skat1 = new OutputStub(null, "i", null);
		Controller controller = new Controller(table, skat1);
		controller.chooseSkatVariant();
		SkatVariant variant = SkatVariant.SKAT;
		assertEquals(variant,controller.getTable().getVariant());
	}
	
	@Test
	public void chooseSkatVariantTest3() {
		
		OutputStub skat1 = new OutputStub(null, "b", null);
		Controller controller = new Controller(table, skat1);
		controller.chooseSkatVariant();
		SkatVariant variant = SkatVariant.RAMSCHBOCK;
		assertEquals(variant,controller.getTable().getVariant());
	}
	
	@Test
	public void selectSkatDeckTest1() {
		
		OutputStub selection1 = new OutputStub(null, null, "f");
		Controller controller = new Controller(table, selection1);
		controller.selectSkatDeck();
		assertFalse(PlayingCard.isGermanDeck());
		}
	
	@Test
	@Ignore
	public void selectSkatDeckTest2() {
		// FIXME failing test
		OutputStub selection1 = new OutputStub(null, null, "d");
		Controller controller = new Controller(table, selection1);
		controller.selectSkatDeck();
		assertTrue(PlayingCard.isGermanDeck());
		}
	
	@Test
	public void coordinateBiddingTest() {
		
		Player player = new Granny("Hannelore");
		table.setPlayer1(player);
		table.setPlayer2(new Granny("Lara"));
		table.setPlayer3(new Granny("Hoi"));
		table.initializePositions();
		IOutput output = new OutputStub(null, null, null);
		Controller controller2 = new Controller(table, output);
		try {
			controller2.coordinateBidding();
		} catch (IOException e) {
			e.printStackTrace();
		}
				assertEquals(null, controller.getTable().getDeclarer());
	}
	
	@Test
	public void coordinateBiddingTest2() {
		
		IPlayer player = new HumanPlayerStub("Hannelore");
		table.setPlayer1(player);
		table.setPlayer2(new RuleCompliantPlayer("Lara"));
		table.setPlayer3(new RuleCompliantPlayer("Hoi"));
		table.initializePositions();
		IOutput output = new OutputStub(null, null, null);
		Controller controller2 = new Controller(table, output);
		try {
			controller2.coordinateBidding();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(player.getName(), controller.getTable().getDeclarer().getName());
	}
	
	@Test
	public void bidOrBiddingAgentTest() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(20);
		
		assertTrue(controller.bidOrBiddingAgent(player1, 18, true));
	}
	
	@Test
	public void bidOrBiddingAgentTest2() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(20);
		
		assertTrue(controller.bidOrBiddingAgent(player1, 23, true));
	}
	
	@Test
	@Ignore
	public void bidOrBiddingAgentTest3() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(0);
		// FIXME for return false
		assertTrue(controller.bidOrBiddingAgent(player1, 20, true));
	}
	
	@Test
	public void bidOrBiddingAgentTest4() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(0);
		
		assertFalse(controller.bidOrBiddingAgent(player1, 23, true));
	}
	
	@Test
	@Ignore
	public void bidOrBiddingAgentTest5() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(0);
		// FIXME for return false
		assertTrue(controller.bidOrBiddingAgent(player1, 20, false));
	}
	
	@Test
	public void bidOrBiddingAgentTest6() {
		
		IPlayer player1 = new HumanPlayerStub("Benn");
		controller.getTable().setPlayer1(player1);
		IPlayer player2 = new RuleCompliantPlayer("Mike");
		controller.getTable().setPlayer2(player2);
		IPlayer player3 = new RuleCompliantPlayer("Sven");
		controller.getTable().setPlayer3(player3);
		controller.getTable().setBiddingAgentValue(0);
		
		assertFalse(controller.bidOrBiddingAgent(player1, 23, false));
	}
	
	@Test
	public void bidding1Test1() {
		
		IPlayer player1 = new Granny("Hannelore");
		IPlayer player2 = new Granny("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		
		assertEquals(player1.getName(), controller.bidding1(player1, player2).getName());
	}
	
	@Test
	public void bidding1Test2() {
		
		IPlayer player1 = new Granny("Hannelore");
		IPlayer player2 = new RuleCompliantPlayer("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		
		assertEquals(player2.getName(), controller.bidding1(player1, player2).getName());
	}
	
	
	@Test
	public void bidding2Test1() {
		
		IPlayer player1 = new Granny("Hannelore");
		IPlayer player2 = new Granny("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		
		assertEquals(null, controller.bidding2(player1, player2));
	}
	
	@Test
	@Ignore
	public void bidding2Test2() {
		
		IPlayer player1 = new HumanPlayerStub("Hannelore");
		IPlayer player2 = new HumanPlayerStub2("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		// FIXME if returned player is null
		assertEquals(player1, controller.bidding2(player1, player2));
	}
	
	@Test
	@Ignore
	public void bidding2Test3() {
		
		IPlayer player1 = new HumanPlayerStub2("Hannelore");
		IPlayer player2 = new HumanPlayerStub("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		// FIXME if returned player is null
		assertEquals(player2, controller.bidding2(player1, player2));
	}
	
	@Test
	@Ignore
	public void bidding2Test4() {
		
		IPlayer player1 = new HumanPlayerStub2("Hannelore");
		IPlayer player2 = new Granny("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		// FIXME if returned player is null
		assertEquals(player1, controller.bidding2(player1, player2));
	}
	
	@Test
	@Ignore
	public void bidding2Test5() {
		
		IPlayer player1 = new Granny("Hannelore");
		IPlayer player2 = new HumanPlayerStub2("Friedel");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		// FIXME if returned player is null
		assertEquals(player2, controller.bidding2(player1, player2));
	}
	
	
	@Test
	public void decideRaeuberGameTest() {
		
		
		table.setPlayer1(new Granny("Hildegard"));
		table.setPlayer2(new Granny("Gräfin Johanita von Schwanenstein"));
		
		table.setPlayer3(new Granny("Zensursula"));
		table.getPlayer1().setPosition(Position.VORHAND);
		controller.decideRaeuberGame();
		
		assertTrue(table.getVorhand().isDeclarer());
		

	}
	
	@Test
	public void prepareGameTest() {
		
		table.setPlayer1(new HumanPlayer("Hildegard", controller));
		SmartPlayer smartPlayer = new SmartPlayer("Gräfin Johanita von Schwanenstein");
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		smartPlayer.setHand(hand);
		smartPlayer.setStartHand(hand);
		table.setPlayer2(smartPlayer);
		table.setPlayer3(new Granny("Zensursula"));
		
		Controller controller = new Controller(table, output);
		
		controller.prepareGame();
			boolean hasNames = true;
		for (PlayingCard card : table.getPlayer1().getHand()) {
			if (card.getOwner() == null) {
				hasNames = false;
			}
				}
		for (PlayingCard card : table.getPlayer2().getHand()) {
			if (card.getOwner() == null) {
				hasNames = false;
			}
				}
		for (PlayingCard card : table.getPlayer3().getHand()) {
			if (card.getOwner() == null) {
				hasNames = false;
			}
				}
		assertTrue(hasNames);
	}
	
	@Test
	@Ignore
	public void alleinspielerAktionenTest() {
		
		Table table2 = new Table();
		OutputStub output2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(table2, output2);
		HumanPlayerStub human = new HumanPlayerStub("Bernd");
		human.setIsDeclarer(true);
		human.setHand(new ArrayList<PlayingCard>());
		for (int i = 0; i < 10; i++) {
			human.getHand().add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		}
		controller2.getTable().setPlayer1(human);
		Player player2 = new Granny("Basti");
		Player player3 = new Granny("ungluecklich");
		controller2.getTable().setPlayer2(player2);
		controller2.getTable().setPlayer3(player3);
		controller2.getTable().setSixSkat(true);
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		skat[1] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		skat[2] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		controller2.getTable().setSkat(skat);
		controller2.getTable().setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));

		
		//handgame pruefen
		boolean handGame = false;
		if (!controller2.getTable().getHandGame()) {
			handGame = true;
		}
		
		//spielart pruefen
		boolean gameVariety2 = false;
		System.out.println(controller2.getTable().getGameVariety().toString());
		if (controller2.getTable().getGameVariety().toString().equals((new SuitGame(PlayingCard.Suit.BELLS)).toString())) {
			gameVariety2 = true;
		}
		
		try {
			// FIXME
			controller2.declarerActions();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean allCorrect = false;
		if (handGame && gameVariety2) {
			allCorrect = true;
		}
		
		assertTrue(allCorrect);
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
		blatt1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		blatt1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		blatt1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		blatt1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		blatt1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		blatt1.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		blatt1.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		blatt1.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		blatt1.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		spieler1.setTricks(blatt1);
		spieler1.setHand(blatt1);
		spieler1.setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		spieler1.arrangeMatadorsJackStraitOrder();
		
		ArrayList<PlayingCard> blatt2 = new ArrayList<PlayingCard>(); 
		blatt2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		blatt2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		blatt2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		blatt2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		spieler2.setTricks(blatt2);
		
		ArrayList<PlayingCard> blatt3 = new ArrayList<PlayingCard>(); 
		blatt3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		blatt3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		blatt3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		blatt3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		spieler3.setTricks(blatt3);
		
		tisch2.setPlayer1(spieler1);
		tisch2.setPlayer2(spieler2);
		tisch2.setPlayer3(spieler3);
		tisch2.setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		
		controller.evaluation();
		
		OutputStub ausgabe2 = (OutputStub) controller.getOutput();
		assertEquals(24, ausgabe2.getPoints());
		
	}
	
	@Test
	public void aufrauemenTest() {
		
		
		PlayingCard[] skatkarten = new PlayingCard[3];
		skatkarten[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
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
		skatkarten[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
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
		skatkarten[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
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
		skatkarten[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatkarten);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
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
		PlayingCard k1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard k2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard k3 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		k1.setOwner(gewinner);
		k2.setOwner(s2);
		k3.setOwner(s3);
		gespielteKarten[0] = k1;
		gespielteKarten[1] = k2;
		gespielteKarten[2] = k3;
		
		controller2.outputTrickEvaluation(gespielteKarten, gewinner);
		
		OutputStub ausgabe = (OutputStub) controller2.getOutput();
		assertEquals(3, ausgabe.getPlayedCardsCount());
		
		
	}
	
	@Test
	public void reizagentTest() {
		
		Player spieler = new Granny("Tini");
		table.setBiddingValue(23);
		table.setBiddingAgentValue(30);
		assertTrue(controller.bidAgent(spieler));
	}
	
	@Test
	public void reizagentTest2() {
		
		Player spieler = new Granny("Tini");
		table.setBiddingValue(30);
		table.setBiddingAgentValue(23);
		assertFalse(controller.bidAgent(spieler));
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
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new NullGame());
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean ergebnis = false;
		if (table.getSchneider() && table.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest2() {
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new NullGame());
		table.setHandGame(false);
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean ergebnis = false;
		if (table.getSchneider() && table.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest3() {
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new GrandGame());
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean ergebnis = false;
		if (!table.getSchneider() && !table.getSchwarz() && !table.getOuvert()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest4() {
		
		table.setPlayer1(new HumanPlayerStub("Bert"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new GrandGame());
		table.setHandGame(true);
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean ergebnis = false;
		if (table.getSchneider() && table.getSchwarz() && table.getOuvert()) {
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
		blatt.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		blatt.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		blatt.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		blatt.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		blatt.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		blatt.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		
		spieler1.setHand(blatt);
		spieler2.setHand(blatt);
		spieler3.setHand(blatt);
		
		spieler1.setIsDeclarer(true);
		
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		skat[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		skat[2] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		table.setSkat(skat);
		table.setPlayer1(spieler1);
		table.setPlayer2(spieler2);
		table.setPlayer3(spieler3);
		ArrayList<PlayingCard> skat2 = new ArrayList<PlayingCard>(Arrays.asList(skat));
		
		SmartPlayer ergebnis = (SmartPlayer) table.getPlayer1();
		
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
		Controller controll = new Controller(table,stubbie);
		
		assertTrue(controll.quitGame());
	}
	
	@Test
	public void warteTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controll = new Controller(table,stubbie);
		controll.waiting();
		assertTrue(controll.getOutput().getRelease());
	}
}
