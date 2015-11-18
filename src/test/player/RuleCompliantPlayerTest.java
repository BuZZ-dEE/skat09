package test.player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.player.RuleCompliantPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


public class RuleCompliantPlayerTest {
	
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	PlayingCard playingCard1;
	PlayingCard playingCard2;
	PlayingCard playingCard3;
	PlayingCard playingCard4;
	RuleCompliantPlayer player = new RuleCompliantPlayer("Mimi");
	PlayingCard[] playedCards = new PlayingCard[3];
	SuitGame suitGame = new SuitGame(Suit.HEARTS);
	
	@Before
	public void setUp() {
		playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		hand.add(playingCard4);
		hand.add(playingCard3);
		playedCards[0] = playingCard1;
		
		player.setHand(hand);
		player.setGameVariety(suitGame);
	}

//	@Test
//	public void testPlayCard() {
//		assertEquals(playingcard4, player.playCard(playedCards));
//	}
//	
//	@Test
//	public void testSpieleKarte2() {
//		hand.clear();
//		hand.add(playingcard3);
//		hand.add(playingcard4);
//		assertEquals(playingcard3, player.playCard(playedCards));
//	}
//	
//	@Test
//	public void testSpieleKarte3() {
//		playedCards[0] = playingcard2;
//		assertEquals(playingcard4, player.playCard(playedCards));
//	}
//	
//	@Test
//	public void testSpieleKarte4() {
//		hand.clear();
//		hand.add(playingcard3);
//		hand.add(playingcard4);
//		playedCards[0] = playingcard2;
//		assertEquals(playingcard4, player.playCard(playedCards));
//	}
	
	//Der regelkonforme Spieler spielt eine zufaellige Karte
	//aus seinem Blatt. Es wird getestet, ob die gespielte
	//Karte vorher im Blatt enthalten war und daraufhin auch 
	//entfernt wurde.
	@SuppressWarnings("unchecked")
	@Test
	public void testPlayCard() {
	
		boolean testSuccess = false;
		
		hand.clear();
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.BELLS, Value.EIGHT));
		ArrayList<PlayingCard> oldHand = new ArrayList<PlayingCard>();
		oldHand = (ArrayList<PlayingCard>) hand.clone();
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playedCard = new PlayingCard(null,null);
		playedCard = player.playCard(playedCards);
		hand = player.getHand();
		
		if(oldHand.contains(playedCard)) {
			
			if (!hand.contains(playedCard)) {
			
				testSuccess = true;
			}
		}
		assertTrue(testSuccess);
	}
	
	@Test
	public void testRuleCompliantPlayer() {
		assertEquals("Hans", new RuleCompliantPlayer("Hans").getName());
	}


	@Test
	public void drueckenTest() {
		PlayingCard[] skat = new PlayingCard[2];
		assertArrayEquals(null, player.druecken(skat));
	}
	
	@Test
	public void handgameTest() {
		assertTrue(player.handgame());
	}
	
	@Test
	public void schneiderTest() {
		assertFalse(player.schneider());
	}
	
	@Test
	public void schwarzTest() {
		assertFalse(player.schwarz());
	}
	
	@Test
	public void ouvertTest() {
		assertFalse(player.ouvert());
	}
	
	@Test
	public void declareGameTest() {
		boolean test = false;
		if (player.declareGame() instanceof NullGame) {
			test = true;
		}
		assertTrue(test);
	}
	
	@Test
	public void respondTest() {
		assertTrue(player.respond(0));
	}
	
	@Test
	public void respondTest2() {
		assertTrue(player.respond(18));
	}
	
	@Test
	public void respondTest3() {
		assertTrue(player.respond(20));
	}
	
	@Test
	public void respondTest4() {
		assertFalse(player.respond(35));
	}
	
	@Test
	public void bidTest() {
		assertTrue(player.bid(0));
	}
	
	@Test
	public void bidTest2() {
		assertTrue(player.bid(18));
	}
	
	@Test
	public void suitTest() {
		assertEquals(null, player.suit());
	}
}
