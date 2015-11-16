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
import main.playingcard.Suit;
import main.playingcard.Value;
import test.gamevariety.NullGameStub2;


public class PlayerTest{

	private IPlayer player;
	ArrayList<PlayingCard> tricks = new ArrayList<PlayingCard>();
	
	
	@Before
	public void setUp(){
		String name_bsp = "Rainer Hohn";
		player = new Granny(name_bsp);
		tricks.clear();
		player.setTricks(tricks);
		player.getGames().clear();
	}
	
	@Test
	public void testSpieler() {
		assertEquals("Rainer Hohn",player.getName());
	}

	@Test
	public void stichHinzufuegenTest() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KING);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SEVEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.TEN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		player.addTrick(gespielteKarten);
		assertEquals(3, player.getTricks().size());
		
	}
	
	@Test
	public void stichHinzufuegenTest2() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KING);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SEVEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.TEN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		player.addTrick(gespielteKarten);
		assertEquals(karte1, player.getTricks().get(0));
		
	}
	
	@Test
	public void stichHinzufuegenTest3() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KING);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SEVEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.TEN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		player.addTrick(gespielteKarten);
		assertEquals(karte2, player.getTricks().get(1));
	}
	
	@Test
	public void stichHinzufuegenTest4() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KING);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SEVEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.TEN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		player.addTrick(gespielteKarten);
		assertEquals(karte3, player.getTricks().get(2));
		
	}
	
	@Test
	public void neuerEintragTest() {
		ArrayList<Integer> spiele;
		spiele=player.addPoints(18);
		assertEquals(1, spiele.size());
	}
	
	@Test
	public void neuerEintragTest2() {
		ArrayList<Integer> spiele;
		spiele=player.addPoints(18);
		assertEquals(18,(int) spiele.get(0));
	}
	
	@Test
	public void agentTest() {
		assertFalse(player.agent());
	}
	
	@Test
	public void reizlimitTest() {
		assertEquals(0, player.setBidLimit());
	}
	
	@Test
	public void test1sortiereBlatt(){
		final IGameVariety spiel = new GrandGame();
		IPlayer spieler = new Granny("Hallo");
		
		spieler.setGameVariety(spiel);
		ArrayList<PlayingCard> blattest = new ArrayList<PlayingCard>();
		blattest.add(new PlayingCard(Suit.HEARTS,Value.KING));
		blattest.add(new PlayingCard(Suit.BELLS,Value.SEVEN));
		blattest.add(new PlayingCard(Suit.ACORNS,Value.TEN));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.EIGHT));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.KING));
		blattest.add(new PlayingCard(Suit.HEARTS,Value.DAUS));
		blattest.add(new PlayingCard(Suit.HEARTS,Value.OVER_KNAVE));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.TEN));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.DAUS));
		blattest.add(new PlayingCard(Suit.ACORNS,Value.UNDER_KNAVE));
		
		spieler.setHand(blattest);
		
		ArrayList<PlayingCard> testproof = new ArrayList<PlayingCard>();
		
		testproof.add(new PlayingCard(Suit.BELLS,Value.SEVEN));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.OVER_KNAVE));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.KING));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.DAUS));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.EIGHT));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.KING));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.TEN));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.DAUS));
		testproof.add(new PlayingCard(Suit.ACORNS,Value.TEN));
		testproof.add(new PlayingCard(Suit.ACORNS,Value.UNDER_KNAVE));
		
		spieler.sortHand(spiel);
		boolean vergleiche = true;
		
		for(int i =0; i<10; i++){
			if (!testproof.get(i).equals(spieler.getHand().get(i))){
				vergleiche = false;
			}
		}
		assertTrue(vergleiche);
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest() {
		
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		player.addPlayedCards(gespielteKarten);
		assertEquals(gespielteKarten[0], player.getAllPlayedCards().get(0));
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest2() {
		
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		player.addPlayedCards(gespielteKarten);
		assertEquals(gespielteKarten[2], player.getAllPlayedCards().get(2));
	}
	
	@Test
	public void spitzenEinordnenTest() {
		boolean test = false;
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
		new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		IGameVariety spielart = new GrandGame();
		player.setGameVariety(spielart);
		player.arrangeMatadorsJackStraitOrder();
			test = true;
		
		assertTrue(test);
	}
	
	@Test
	public void testSpitzenZahl() {
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.setGameVariety(new GrandGame());
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(1, player.matadorsJackStraitCount());
	}
	
	@Test
	public void testSpitzenZahl2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitCount());
	}

	@Test
	public void testSpitzenMit() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(1, player.matadorsJackStraitWith(1));
	}

	@Test
	public void testSpitzenMit2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(2, player.matadorsJackStraitWith(2));
	}

	@Test
	public void testSpitzenMit3() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(3, player.matadorsJackStraitWith(3));
	}

	@Test
	public void testSpitzenMit4() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new GrandGame());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(4, player.matadorsJackStraitWith(4));
	}

	@Test
	public void testSpitzenMit5() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(5, player.matadorsJackStraitWith(5));
	}

	@Test
	public void testSpitzenMit6() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(6, player.matadorsJackStraitWith(6));
	}

	@Test
	public void testSpitzenMit7() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(7, player.matadorsJackStraitWith(7));
	}

	@Test
	public void testSpitzenMit8() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(8, player.matadorsJackStraitWith(8));
	}

	@Test
	public void testSpitzenMit9() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(9, player.matadorsJackStraitWith(9));
	}

	@Test
	public void testSpitzenMit10() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(10, player.matadorsJackStraitWith(10));
	}

	@Test
	public void testSpitzenMit11() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.SEVEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(11, player.matadorsJackStraitWith(11));
	}

	@Test
	public void testSpitzenOhne() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-1, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne2() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-2, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne3() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-3, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne4() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-4, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne5() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-5, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne6() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-6, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne7() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-7, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne8() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-8, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne9() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-9, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne10() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.SEVEN));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-10, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne11() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitWithout(0));
	}

	@Test
	public void testSpitzenOhne12() {
		player.setHand(new ArrayList<PlayingCard>());
		player.setGameVariety(new SuitGame(Suit.BELLS));
		player.getHand().add(
				new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-11, player.matadorsJackStraitWithout(0));
	} 
	
	@Test
	public void testSpitzenOhne13() {
		player.setGameVariety(new GrandGame());
		player.setHand(new ArrayList<PlayingCard>());
		player.arrangeMatadorsJackStraitOrder();
		assertEquals(-4, player.matadorsJackStraitWithout(0));
	}
	
	@Test
	public void spielbarteKartenTest() {
		player.setGameVariety(new NullGameStub2());
		
		player.setHand(new ArrayList<PlayingCard>());
		player.getHand().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		player.getHand().add(karte3);
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		player.getHand().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		ArrayList<PlayingCard> erwartet = new ArrayList<PlayingCard>();
		erwartet.add(karte3);
		assertEquals(erwartet, player.playableCards(gespielteKarten));
	}
	
	
	@Test
	public void equalsTest() {
		
		IPlayer spieler2 = new Granny("Rainer Hohn");
		assertTrue(player.equals(spieler2));
	}
	@Test
	public void farbenEinordnenTest() {
		
		
	}

}
