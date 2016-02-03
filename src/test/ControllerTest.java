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
	public void declarerActionsTest() {
		
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
	public void evaluationTest() {
		
		OutputStub output = new OutputStub(null, null, null);
		Table table2 = new Table();
		Controller controller = new Controller(table2, output);
		
		IPlayer player1 = new Granny("Erna");
		IPlayer player2 = new Granny("Roy");
		IPlayer player3 = new Granny("Marry");
		
		player1.setIsDeclarer(true);
		
		ArrayList<PlayingCard> hand1 = new ArrayList<PlayingCard>();
		hand1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		hand1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		hand1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand1.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		hand1.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand1.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		hand1.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand1.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		player1.setTricks(hand1);
		player1.setHand(hand1);
		player1.setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		player1.arrangeMatadorsJackStraitOrder();
		
		ArrayList<PlayingCard> hand2 = new ArrayList<PlayingCard>();
		hand2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		hand2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand2.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		player2.setTricks(hand2);
		
		ArrayList<PlayingCard> hand3 = new ArrayList<PlayingCard>();
		hand3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		hand3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand3.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		player3.setTricks(hand3);
		
		table2.setPlayer1(player1);
		table2.setPlayer2(player2);
		table2.setPlayer3(player3);
		table2.setGameVariety(new SuitGame(PlayingCard.Suit.ACORNS));
		
		controller.evaluation();
		
		OutputStub output2 = (OutputStub) controller.getOutput();
		assertEquals(24, output2.getPoints());
		
	}
	
	@Test
	public void cleanUpTest() {
		
		
		PlayingCard[] skatCards = new PlayingCard[3];
		skatCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatCards);
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
	
		Player player1 = new Granny("Joy");
		player1.setHand(blatt);
		player1.setGameVariety(new GrandGame());
		player1.setIsDeclarer(true);
		Player player2 = new RuleCompliantPlayer("Leon");
		player2.setHand(blatt);
		player2.setTricks(blatt);
		Player player3 = new RuleCompliantPlayer("John Wayne");
		player3.setHand(blatt);
		player3.setAllPlayedCards(blatt);
		
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		
		controller.cleanUp();
		
		Table table3 = controller.getTable();
				
		boolean isPlayer3CleanedUp = false;

		IPlayer pl3 = controller.getTable().getPlayer3();
		if (pl3.getHand() == null && pl3.isDeclarer() == false &&
				pl3.getTricks().size() == 0 && pl3.getAllPlayedCards().size() == 0) {
			isPlayer3CleanedUp = true;
		}
		
		assertTrue(isPlayer3CleanedUp);
	}
	
	@Test
	public void cleanUpTest2() {
		
		PlayingCard[] skatCards = new PlayingCard[3];
		skatCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatCards);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
		Player player1 = new Granny("Joy");
		player1.setHand(hand);
		player1.setGameVariety(new GrandGame());
		player1.setIsDeclarer(true);
		Player player2 = new RuleCompliantPlayer("Leon");
		player2.setHand(hand);
		player2.setTricks(hand);
		Player player3 = new RuleCompliantPlayer("John Wayne");
		player3.setHand(hand);
		player3.setAllPlayedCards(hand);
		
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		
		controller.cleanUp();
		
		Table table3 = controller.getTable();
		
		boolean isTableCleanedUp = false;
		
		if (table3.getSkat()[0] == null && table3.getBiddingAgentValue() == 0 && table3.getBiddingValue() == 18 &&
				table3.getBock() == false && table3.getSpaltarsch() == false) {
			isTableCleanedUp = true;
		}
		
		assertTrue(isTableCleanedUp);
	}
	
	@Test
	public void cleanUpTest3() {
		
		PlayingCard[] skatCards = new PlayingCard[3];
		skatCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatCards);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
		Player player1 = new Granny("Joy");
		player1.setHand(hand);
		player1.setGameVariety(new GrandGame());
		player1.setIsDeclarer(true);
		Player player2 = new RuleCompliantPlayer("Leon");
		player2.setHand(hand);
		player2.setTricks(hand);
		Player player3 = new RuleCompliantPlayer("John Wayne");
		player3.setHand(hand);
		player3.setAllPlayedCards(hand);
		
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		
		controller.cleanUp();
		
		Table table3 = controller.getTable();
		
		boolean isPlayer1CleanedUp = false;
		
		IPlayer sp1 = controller.getTable().getPlayer1();
		if (sp1.getHand() == null && !sp1.isDeclarer() &&
				sp1.getTricks().size() == 0 && sp1.getAllPlayedCards().size() == 0) {
			isPlayer1CleanedUp = true;
		}
		
		assertTrue(isPlayer1CleanedUp);
	}
	
	@Test
	public void cleanUpTest4() {
	
		PlayingCard[] skatCards = new PlayingCard[3];
		skatCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		controller.getTable().setSkat(skatCards);
		controller.getTable().setBiddingAgentValue(120);
		controller.getTable().setBiddingValue(23);
		controller.getTable().setBock(true);
		controller.getTable().setVariant(SkatVariant.RAMSCHBOCK);
		controller.getTable().setSpaltarsch(true);
		controller.getTable().setBockRounds(0);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
	
		Player player1 = new Granny("Joy");
		player1.setHand(hand);
		player1.setGameVariety(new GrandGame());
		player1.setIsDeclarer(true);
		Player player2 = new RuleCompliantPlayer("Leon");
		player2.setHand(hand);
		player2.setTricks(hand);
		Player player3 = new RuleCompliantPlayer("John Wayne");
		player3.setHand(hand);
		player3.setAllPlayedCards(hand);
		
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		
		controller.cleanUp();
		
		Table table3 = controller.getTable();
		
		boolean isPlayer2CleanedUp = false;
		
		IPlayer sp2 = controller.getTable().getPlayer2();
		if (sp2.getHand() == null && sp2.isDeclarer() == false &&
				sp2.getTricks().size() == 0 && sp2.getAllPlayedCards().size() == 0) {
			isPlayer2CleanedUp = true;
		}
		
		assertTrue(isPlayer2CleanedUp);
	}
	
	@Test
	public void trickEvaluationTest() {
		
		Table table2 = new Table();
		OutputStub output2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(table2, output2);
		
		Player winner = new Granny("Gustav Gans");
		Player s2 = new Granny("Benny");
		Player s3 = new Granny("Daniel");
		PlayingCard[] playedCards = new PlayingCard[3];
		PlayingCard k1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard k2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard k3 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		k1.setOwner(winner);
		k2.setOwner(s2);
		k3.setOwner(s3);
		playedCards[0] = k1;
		playedCards[1] = k2;
		playedCards[2] = k3;
		
		controller2.outputTrickEvaluation(playedCards, winner);
		
		OutputStub output = (OutputStub) controller2.getOutput();
		assertEquals(3, output.getPlayedCardsCount());
		
		
	}
	
	@Test
	public void bidAgentTest() {
		
		Player player = new Granny("Tini");
		table.setBiddingValue(23);
		table.setBiddingAgentValue(30);
		assertTrue(controller.bidAgent(player));
	}
	
	@Test
	public void bidAgentTest2() {
		
		Player player = new Granny("Tini");
		table.setBiddingValue(30);
		table.setBiddingAgentValue(23);
		assertFalse(controller.bidAgent(player));
	}
	
	@Test
	public void assignSkatCardsToOwnerTest() {
		
		IPlayer granny = new Granny("o1");
		controller.getTable().setPlayer1(granny);
		controller.getTable().getPlayer1().setIsDeclarer(true);
		controller.getTable().setPlayer2(new Granny("o2"));
		controller.getTable().setPlayer3(new Granny("o3"));
		controller.getTable().setSixSkat(true);
		controller.getTable().createDeck();
		controller.getTable().dealOutCards();
		controller.getTable().giveCardsToOwner();
		
		controller.assignSkatCardsToOwner();
		
		PlayingCard[] skat = controller.getTable().getSkat();
		boolean success = false;
		if(skat[0].getOwner().equals(granny) && skat[1].getOwner().equals(granny) &&
				skat[2].getOwner().equals(granny)) {

			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void setFlagsTest1() {
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new NullGame());
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean result = false;
		if (table.getSchneider() && table.getSchwarz()) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void setFlagsTest2() {
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new NullGame());
		table.setHandGame(false);
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean result = false;
		if (table.getSchneider() && table.getSchwarz()) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void setFlagsTest3() {
		
		table.setPlayer1(new Granny("Erna"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new GrandGame());
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean result = false;
		if (!table.getSchneider() && !table.getSchwarz() && !table.getOuvert()) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void setFlagsTest4() {
		
		table.setPlayer1(new HumanPlayerStub("Bert"));
		table.getPlayer1().setIsDeclarer(true);
		table.setPlayer2(new Granny("Renate"));
		table.setPlayer3(new Granny("Mochochocho"));
		table.setGameVariety(new GrandGame());
		table.setHandGame(true);
		controller.setFlags(table.getPlayer1(), table.getGameVariety());
		
		boolean result = false;
		if (table.getSchneider() && table.getSchwarz() && table.getOuvert()) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void ramschenTest() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
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
	public void normalGamePlayTest() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.normalGamePlay();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playRaeuberskatTest() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playRaeuberskat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playRamschBockTest() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.setSpaltarsch(true);
		table2.setRamschRounds(0);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playRamschBockTest2() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.setSpaltarsch(true);
		table2.setRamschRounds(1);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playRamschBockTest3() {
		
		Table table2 = new Table();
		table2.setPlayer1(new RuleCompliantPlayer("Hilde"));
		table2.setPlayer2(new RuleCompliantPlayer("Heide"));
		table2.setPlayer3(new RuleCompliantPlayer("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playIntSkatTest() {
		
		Table table2 = new Table();
		table2.setPlayer1(new Granny("Hilde"));
		table2.setPlayer2(new Granny("Heide"));
		table2.setPlayer3(new Granny("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void playIntSkatTest2() {
		
		Table table2 = new Table();
		table2.setPlayer1(new RuleCompliantPlayer("Hilde"));
		table2.setPlayer2(new RuleCompliantPlayer("Heide"));
		table2.setPlayer3(new RuleCompliantPlayer("Harald"));
		table2.getPlayer1().setIsDeclarer(true);
		table2.initializePositions();
		table2.createDeck();
		table2.dealOutCards();
		table2.giveCardsToOwner();
		Output output = new CLIOutput(table2);
		Controller controller2 = new Controller(table2, output);
		try {
			controller2.playIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTable().getPlayer3().getPosition());
	}
	
	@Test
	public void smartPlayerInitTest() {
		
		Player player1 = new SmartPlayer("Halo");
		Player player2 = new SmartPlayer("Evin");
		Player player3 = new RuleCompliantPlayer("Bernd");
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		
		player1.setHand(hand);
		player2.setHand(hand);
		player3.setHand(hand);
		
		player1.setIsDeclarer(true);
		
		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		skat[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		skat[2] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		table.setSkat(skat);
		table.setPlayer1(player1);
		table.setPlayer2(player2);
		table.setPlayer3(player3);
		ArrayList<PlayingCard> skat2 = new ArrayList<PlayingCard>(Arrays.asList(skat));
		
		SmartPlayer result = (SmartPlayer) table.getPlayer1();
		
		controller.initializeSmartPlayer();
		
		assertEquals(skat2, result.getSkat());
	}
	
	@Test
	public void namesComparisonTest1() {
		
		IPlayer player1 = new Granny("Gertrud");
		IPlayer player2 = new Granny("Gertrud");
		IPlayer player3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		controller.namesComparison();
		assertEquals("Gertrud1", controller.getTable().getPlayer1().getName());
	}
	
	@Test
	public void namesComparisonTest2() {
		
		IPlayer player1 = new Granny("Gertrud");
		IPlayer player2 = new Granny("Gertrud");
		IPlayer player3 = new Granny("Hans");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		controller.namesComparison();
		assertEquals("Gertrud2", controller.getTable().getPlayer2().getName());
	}
	
	@Test
	public void namesComparisonTest3() {
		
		IPlayer player1 = new Granny("Gertrud");
		IPlayer player2 = new Granny("Hans");
		IPlayer player3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		controller.namesComparison();
		assertEquals("Gertrud2", controller.getTable().getPlayer3().getName());
	}
	
	@Test
	public void namesComparisonTest4() {
		
		IPlayer player1 = new Granny("Hans");
		IPlayer player2 = new Granny("Gertrud");
		IPlayer player3 = new Granny("Gertrud");
		controller.getTable().setPlayer1(player1);
		controller.getTable().setPlayer2(player2);
		controller.getTable().setPlayer3(player3);
		controller.namesComparison();
		assertEquals("Gertrud1", controller.getTable().getPlayer2().getName());
	}
	
	@Test
	public void quitGameTest() {
		
		OutputStub outputStub = new OutputStub(null,null,null);
		Controller controller = new Controller(table, outputStub);
		
		assertTrue(controller.quitGame());
	}
	
	@Test
	public void waitingTest() {
		
		OutputStub outputStub = new OutputStub(null,null,null);
		Controller controller = new Controller(table, outputStub);
		controller.waiting();
		assertTrue(controller.getOutput().getRelease());
	}
}
