package test.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.Granny;
import main.player.IPlayer;
import main.playingcard.PlayingCard;
import test.gamevariety.NullGameStub2;


public class PlayerTest{

	private IPlayer player;
	ArrayList<PlayingCard> tricks = new ArrayList<PlayingCard>();
	
	
	@Before
	public void setUp(){
		String exampleName = "Rainer Hohn";
		player = new Granny(exampleName);
		tricks.clear();
		player.setTricks(tricks);
		player.getGames().clear();
	}
	
	@Test
	public void testPlayer() {
		assertEquals("Rainer Hohn",player.getName());
	}

	@Test
	public void addTrickTest() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING);
		PlayingCard card2 =	new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN);
		PlayingCard card3 =	new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = card1;
		playedCards[1] = card2;
		playedCards[2] = card3;
		player.addTrick(playedCards);
		assertEquals(3, player.getTricks().size());
		
	}
	
	@Test
	public void addTrickTest2() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING);
		PlayingCard card2 =	new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN);
		PlayingCard card3 =	new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = card1;
		playedCards[1] = card2;
		playedCards[2] = card3;
		player.addTrick(playedCards);
		assertEquals(card1, player.getTricks().get(0));
		
	}
	
	@Test
	public void addTrickTest3() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING);
		PlayingCard card2 =	new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN);
		PlayingCard card3 =	new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = card1;
		playedCards[1] = card2;
		playedCards[2] = card3;
		player.addTrick(playedCards);
		assertEquals(card2, player.getTricks().get(1));
	}
	
	@Test
	public void addTrickTest4() {
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING);
		PlayingCard card2 =	new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN);
		PlayingCard card3 =	new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = card1;
		playedCards[1] = card2;
		playedCards[2] = card3;
		player.addTrick(playedCards);
		assertEquals(card3, player.getTricks().get(2));
		
	}
	
	@Test
	public void neuerEintragTest() {
		ArrayList<Integer> games;
		games=player.addPoints(18);
		assertEquals(1, games.size());
	}
	
	@Test
	public void neuerEintragTest2() {
		ArrayList<Integer> games;
		games=player.addPoints(18);
		assertEquals(18,(int) games.get(0));
	}
	
	@Test
	public void agentTest() {
		assertFalse(player.agent());
	}
	
	@Test
	public void setBidLimitTest() {
		assertEquals(0, player.setBidLimit());
	}
	
	@Test
	public void test1SortHand(){
		final IGameVariety games = new GrandGame();
		IPlayer player = new Granny("Hallo");
		
		player.setGameVariety(games);
		ArrayList<PlayingCard> handTest = new ArrayList<PlayingCard>();
		handTest.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING));
		handTest.add(new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN));
		handTest.add(new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN));
		handTest.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.EIGHT));
		handTest.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.KING));
		handTest.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.DAUS));
		handTest.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.OVER_KNAVE));
		handTest.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.TEN));
		handTest.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.DAUS));
		handTest.add(new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.UNDER_KNAVE));
		
		player.setHand(handTest);
		
		ArrayList<PlayingCard> testproof = new ArrayList<PlayingCard>();
		
		testproof.add(new PlayingCard(PlayingCard.Suit.BELLS,PlayingCard.Rank.SEVEN));
		testproof.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.OVER_KNAVE));
		testproof.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.KING));
		testproof.add(new PlayingCard(PlayingCard.Suit.HEARTS,PlayingCard.Rank.DAUS));
		testproof.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.EIGHT));
		testproof.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.KING));
		testproof.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.TEN));
		testproof.add(new PlayingCard(PlayingCard.Suit.LEAVES,PlayingCard.Rank.DAUS));
		testproof.add(new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.TEN));
		testproof.add(new PlayingCard(PlayingCard.Suit.ACORNS,PlayingCard.Rank.UNDER_KNAVE));
		
		player.sortHand(games);
		boolean compare = true;
		
		for(int i =0; i<10; i++){
			if (!testproof.get(i).equals(player.getHand().get(i))){
				compare = false;
			}
		}
		assertTrue(compare);
	}
	
	@Test
	public void addPlayedCardsTest() {
		
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		player.addPlayedCards(playedCards);
		assertEquals(playedCards[0], player.getAllPlayedCards().get(0));
	}
	
	@Test
	public void addPlayedCardsTest2() {
		
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[2] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		player.addPlayedCards(playedCards);
		assertEquals(playedCards[2], player.getAllPlayedCards().get(2));
	}
	
	@Test
	public void arrangeMatadorsJackStraitOrderTest() {
		boolean test = false;
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
		new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		IGameVariety grandGame = new GrandGame();
		player.setGameVariety(grandGame);
		player.arrangeMatadorsJackStraitOrder();
			test = true;
		
		assertTrue(test);
	}
	
	@Test
	public void testMatadorsJackStraitCount() {
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.setGameVariety(new GrandGame());
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(1, player.matadorsJackStraitCount());
	}
	
	@Test
	public void testMatadorsJackStraitCount2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitCount());
	}

	@Test
	public void testMatadorsJackStraitWith() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(1, player.matadorsJackStraitWith(1));
	}

	@Test
	public void testMatadorsJackStraitWith2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(2, player.matadorsJackStraitWith(2));
	}

	@Test
	public void testMatadorsJackStraitWith3() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(3, player.matadorsJackStraitWith(3));
	}

	@Test
	public void testMatadorsJackStraitWith4() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(4, player.matadorsJackStraitWith(4));
	}

	@Test
	public void testMatadorsJackStraitWith5() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(5, player.matadorsJackStraitWith(5));
	}

	@Test
	public void testMatadorsJackStraitWith6() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(6, player.matadorsJackStraitWith(6));
	}

	@Test
	public void testMatadorsJackStraitWith7() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(7, player.matadorsJackStraitWith(7));
	}

	@Test
	public void testMatadorsJackStraitWith8() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(8, player.matadorsJackStraitWith(8));
	}

	@Test
	public void testMatadorsJackStraitWith9() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(9, player.matadorsJackStraitWith(9));
	}

	@Test
	public void testMatadorsJackStraitWith10() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(10, player.matadorsJackStraitWith(10));
	}

	@Test
	public void testMatadorsJackStraitWith11() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(11, player.matadorsJackStraitWith(11));
	}

	@Test
	public void testMatadorsJackStraitWithout() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-1, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-2, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout3() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-3, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout4() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-4, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout5() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-5, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout6() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-6, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout7() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-7, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout8() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-8, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout9() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-9, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout10() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-10, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout11() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testMatadorsJackStraitWithout12() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitWithout(0));
	} 
	
	@Test
	public void testMatadorsJackStraitWithout13() {
		player.setGameVariety(new GrandGame());
		player.setHand(new ArrayList<PlayingCard>());
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-4, player.matadorsJackStraitWithout(0));
	}
	
	@Test
	public void playableCardsTest() {
		player.setGameVariety(new NullGameStub2());
		
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		player.getHand().add(card3);
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		player.getHand().add(
				new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		PlayingCard[] playedCards = new PlayingCard[3];
		ArrayList<PlayingCard> expected = new ArrayList<PlayingCard>();
		expected.add(card3);
		assertEquals(expected, player.playableCards(playedCards));
	}
	
	
	@Test
	public void equalsTest() {
		
		IPlayer player2 = new Granny("Rainer Hohn");
		assertTrue(player.equals(player2));
	}
	@Test
	public void rankSuitTest() {
		
		
	}

}
