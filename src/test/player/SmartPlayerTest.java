package test.player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import main.Table;
import main.gamevariety.GrandGame;
import main.gamevariety.NullGame;
import main.gamevariety.Ramsch;
import main.gamevariety.SuitGame;
import main.player.Granny;
import main.player.IPlayer;
import main.player.Position;
import main.player.RuleCompliantPlayer;
import main.player.SmartPlayer;
import main.playingcard.PlayingCard;


public class SmartPlayerTest {

	SmartPlayer player = new SmartPlayer("Max");
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	PlayingCard playingCard1;
	PlayingCard playingCard2;
	PlayingCard playingCard3;
	PlayingCard playingCard4;
	PlayingCard playingCard5;
	PlayingCard[] playedCards = new PlayingCard[3];
	SuitGame suitGame = new SuitGame(PlayingCard.Suit.HEARTS);

	@Before
	public void setUp() {
		playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		hand.add(playingCard4);
		hand.add(playingCard3);
		hand.add(playingCard5);
		playedCards[0] = playingCard1;
		
		player.setHand(hand);
		player.setGameVariety(suitGame);
	}
	
	@Test
	public void testSmartPlayer() {
		assertEquals("Max", new SmartPlayer("Max").getName());
	}
	
	@Test
	public void testPlayCard() {
		
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest() {
		assertEquals(PlayingCard.Rank.UNDER_KNAVE, player.playFirstCardGrandAsDeclarer(playedCards).getRank());
	}
	
	@Test
	public void testValue() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, PlayingCard.Rank.UNDER_KNAVE);
		if (value.size() == 2) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	public void testValue2() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, PlayingCard.Rank.UNDER_KNAVE);
		for (PlayingCard card : value) {
			if (card.equals(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	

	@Test
	public void testValue3() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, PlayingCard.Rank.UNDER_KNAVE);
		for (PlayingCard card : value) {
			if (card.equals(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void playFirstCardNullTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setIsDeclarer(true);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		assertEquals(playingCard1, player.playFirstCardNull(playedCards));
	}
	
	@Test
	public void playFirstCardNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.MITTELHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.playFirstCardNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	
	@Test
	public void playFirstCardNullTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.playFirstCardNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playFirstCardNullTest5() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.playFirstCardNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playSecondCardNullTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setIsDeclarer(true);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(playingCard1, player.playSecondCardNull(playedCards));
	}
	
	@Test
	public void playSecondCardNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer adversary = new Granny("gegen");
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		playedCards[0].setOwner(mate);
		
		assertEquals(playingCard2, player.playSecondCardNull(playedCards));
	}
	
	@Test
	public void playSecondCardNullTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer adversary = new Granny("gegen");
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		playedCards[0].setOwner(adversary);
		
		assertEquals(playingCard2, player.playSecondCardNull(playedCards));
	}
	
	@Test
	public void playThirdCardNullTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setIsDeclarer(true);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(playingCard1, player.playThirdCardNull(playedCards));
	}
	
	@Test
	public void playThirdCardNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard8 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		player.setTeammate(mate);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard5, player.playThirdCardNull(playedCards));
	}
	
	@Test
	public void playThirdCardNullTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard8 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		player.setTeammate(mate);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard5, player.playThirdCardNull(playedCards));
	}

	@Test
	public void playThirdCardNullTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard8 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		player.setTeammate(mate);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(mate);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		playedCards[1].setOwner(adversary);
		
		assertEquals(playingCard5, player.playThirdCardNull(playedCards));
	}
	
	@Test
	public void playSecondCardGrandTest1() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void playSecondCardGrandTest2() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(mate);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void playSecondCardGrandTest3() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void playSecondCardGrandTest4() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void playThirdCardGrandTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void playThirdCardGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(mate);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(adversary);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void playThirdCardGrandTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void playThirdCardGrandTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void playFirstCardSuitTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (!hand.contains(player.playFirstCardSuit(playedCards))) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playFirstCardSuitTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		mate.setPosition(Position.MITTELHAND);
		IPlayer adversary = new Granny("adversary");
		adversary.setPosition(Position.HINTERHAND);
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean success = false;
		if (playingCard3.getSuit().equals((player.playFirstCardSuit(playedCards)).getSuit())) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playFirstCardSuitTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		mate.setPosition(Position.HINTERHAND);
		IPlayer adversary = new Granny("adversary");
		adversary.setPosition(Position.MITTELHAND);
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean success = false;
		if (playingCard3.getSuit().equals((player.playFirstCardSuit(playedCards)).getSuit())) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playSecondCardSuitTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		
		boolean success = false;
		if (!hand.contains(player.playSecondCardSuit(playedCards))) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playSecondCardSuitTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(mate);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void playSecondCardSuitTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void playSecondCardSuitTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void playThirdCardSuitTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (!hand.contains(player.playThirdCardSuit(playedCards))) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playThirdCardSuitTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer granny = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(granny);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void playThirdCardSuitTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer granny = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(granny);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard3, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void playThirdCardSuitTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer granny = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(granny);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		hand.add(playingCard6);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (playedCard.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (playedCard.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (playedCard.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setStartHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (playedCard.getRank() == PlayingCard.Rank.DAUS) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest5() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (hand.contains(playedCard)) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest6() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (playedCard.getRank() == PlayingCard.Rank.TEN) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandAsDeclarerTest7() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setStartHand(hand);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean result = false;
		PlayingCard playedCard = player.playFirstCardGrandAsDeclarer(playedCards);
		if (hand.contains(playedCard)) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void playFirstCardGrandTest() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setIsDeclarer(true);
		player.setDeck(deck);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		assertEquals(PlayingCard.Rank.UNDER_KNAVE, player.playFirstCardGrand(playedCards).getRank());
	}
	
	@Test
	public void playFirstCardGrandTest2() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setDeck(deck);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		IPlayer adversary = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		adversary.setPosition(Position.HINTERHAND);
		
		boolean success = false;
		if (!hand.contains(player.playFirstCardGrand(playedCards))) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playFirstCardGrandTest3() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setDeck(deck);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		IPlayer adversary = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		adversary.setPosition(Position.HINTERHAND);
		
		boolean success = false;
		if (PlayingCard.Suit.ACORNS == player.playFirstCardGrand(playedCards).getSuit()) {
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void playSecondCardGrandAsDeclarerTest1() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setDeck(deck);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		assertEquals(playingCard1, 
				player.playSecondCardGrandAsDeclarer(playedCards));
	}
	
	@Test
	public void playSecondCardGrandAsDeclarerTest2() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setDeck(deck);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		
		assertEquals(playingCard1, 
				player.playSecondCardGrandAsDeclarer(playedCards));
	}
	
	@Test
	public void playThirdCardGrandAsDeclarerTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		
		assertEquals(playingCard1, 
				player.playThirdCardGrandAsDeclarer(playedCards));
	}
	
	@Test
	public void playThirdCardGrandAsDeclarerTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		assertEquals(playingCard1, 
				player.playThirdCardGrandAsDeclarer(playedCards));
	}
	
	@Test
	public void playFirstCardRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		assertEquals(playingCard1, 
				player.playFirstCardRamsch(playedCards));
	}
	
	@Test
	public void playSecondCardRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		
		assertEquals(playingCard1, 
				player.playSecondCardRamsch(playedCards));
	}
	
	@Test
	public void playThirdCardRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.setHand(hand);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		
		assertEquals(playingCard1, 
				player.playThirdCardRamsch(playedCards));
	}
	
	@Test
	public void possiblePlayableAdversaryCardsTest() {
		
		Table table = new Table();
		table.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		//Collections.shuffle(deck);
		deck.addAll(table.getDeck());
		player.setDeck(deck);
		player.setIsDeclarer(true);
		player.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			player.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> allPlayedCards = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			allPlayedCards.add(deck.remove(0));
		}
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(playedCards)));
		
		ArrayList<PlayingCard> resultCards = new ArrayList<PlayingCard>();
		for (int k = 0; k < deck.size(); k++) {
			
			if(player.getGameVariety().
					checkedPlayedCards(deck, playedCards, deck.get(k))) {
				
				resultCards.add(deck.get(k));
			}
		}
		
		assertEquals(resultCards, player.possiblePlayableAdversaryCards(playedCards));
	}
	
	@Test
	public void possibleAdversaryCardsTest() {
		
		Table table = new Table();
		table.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		Collections.shuffle(deck);
		deck.addAll(table.getDeck());
		player.setDeck(deck);
		player.setIsDeclarer(true);
		player.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			player.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> allPlayedCards = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			allPlayedCards.add(deck.remove(0));
		}
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(playedCards)));
	
		assertEquals(deck, 
				player.possibleAdversaryCards(allPlayedCards, playedCards));
	}
	
	@Test
	public void randomNumberTest() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		int randomNumber = player.randomNumber(hand);
		
		boolean result = false;
		if (randomNumber >= 0 && randomNumber < hand.size()) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		boolean result = false;
		if (hcard.equals(player.nextHigherCard(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void nextHigherCardTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		
		assertEquals(null, player.nextHigherCard(PlayingCard.Suit.BELLS, card));
	}
	
	@Test
	public void nextHigherCardNineTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardNine(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardNineTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardNine(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardNineTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardNine(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardKingTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardKing(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardKingTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardKing(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardKingTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardKing(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardTenTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardTen(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardTenTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardTen(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextHigherCardTenTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.nextHigherCardTen(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		
		assertEquals(null, player.nextLowerCard(PlayingCard.Suit.BELLS, card));
	}
	
	@Test
	public void nextLowerCardTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void nextLowerCardTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		boolean result = false;
		if (card2.equals(player.nextLowerCard(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	@Test
	public void nextLowerCardOverKnaveTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardOverKnave(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardOverKnaveTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardOverKnave(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardOverKnaveTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardOverKnave(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardTenTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardTen(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardTenTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardTen(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardTenTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardTen(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardDausTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardDaus(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardDausTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardDaus(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void nextLowerCardDausTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.nextLowerCardDaus(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void cardsOfRankTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		hand.add(card8);
		hand.add(card9);
		hand.add(card10);
		
		ArrayList<PlayingCard> expected = new ArrayList<PlayingCard>();
		expected.add(card2);
		expected.add(card3);
		assertEquals(expected, player.cardsOfRank(hand, PlayingCard.Rank.DAUS));
	}
	
	@Test
	public void cardsOfSuitTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		hand.add(card8);
		hand.add(card9);
		hand.add(card10);
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		bells.add(card1);
		bells.add(card2);
		
		assertEquals(bells, player.cardsOfSuit(hand, PlayingCard.Suit.BELLS));
	}
	
	@Test
	public void cardsOfSuitTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		hand.add(card8);
		hand.add(card9);
		hand.add(card10);
		
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		hearts.add(card3);
		hearts.add(card4);
		hearts.add(card5);
		hearts.add(card6);
		
		assertEquals(hearts, player.cardsOfSuit(hand, PlayingCard.Suit.HEARTS));
	}
	
	@Test
	public void cardsOfSuitTest3() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		hand.add(card8);
		hand.add(card9);
		hand.add(card10);
		
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		leaves.add(card7);
		leaves.add(card8);
		leaves.add(card9);
		
		assertEquals(leaves, player.cardsOfSuit(hand, PlayingCard.Suit.LEAVES));
	}
	
	@Test
	public void cardsOfSuitTest4() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		hand.add(card8);
		hand.add(card9);
		hand.add(card10);
		
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		acorns.add(card10);
		
		assertEquals(acorns, player.cardsOfSuit(hand, PlayingCard.Suit.ACORNS));
	}
	
	@Test
	public void highestPlayableCardTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		
		playableCards.add(card1);
		playableCards.add(card2);
		playableCards.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		playableCards.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		playableCards.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		
		assertEquals(card1, player.highestPlayableCard(playableCards));
	}
	
	@Test
	public void lowestPlayableCardTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		playableCards.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		playableCards.add(card1);
		playableCards.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		playableCards.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		playableCards.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		
		assertEquals(card1, player.lowestPlayableCard(playableCards));
	}
	
	@Test
	public void determineHighestPlayableCardTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new GrandGame());
		
		assertEquals(card1,
				player.determineHighestPlayableCard(card1, card2));
	}
	
	@Test
	public void determineHighestPlayableCardTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card2,
				player.determineHighestPlayableCard(card1, card2));
	}
	
	@Test
	public void determineHighestPlayableCardTest3() {

		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card1,
				player.determineHighestPlayableCard(card1, card2));
	}
	
	@Test
	public void determineLowestPlayableCardTestTest1() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(card2,
				player.determineLowestPlayableCard(card1, card2));
	}
	
	@Test
	public void determineLowestPlayableCardTestTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		assertEquals(card1,
				player.determineLowestPlayableCard(card1, card2));
	}
	
	@Test
	public void determineLowestPlayableCardTestTest3() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		assertEquals(card1,
				player.determineLowestPlayableCard(card1, card2));
	}
	
	@Test
	public void drueckenTest() {
		
		PlayingCard[] skat = new PlayingCard[3];
		assertArrayEquals(null, player.druecken(skat));
	}
	
	@Test
	public void handgameTest() {
		
		assertTrue(player.handgame());
	}
	
	@Test
	public void ouvertTest() {
		
		assertFalse(player.ouvert());
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
	public void declareGameTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		player.setHand(hand);
		
		assertEquals(new GrandGame().getGameVariety(), 
				player.declareGame().getGameVariety());
	}
	
	@Test
	public void respondTest() {
		
		player.setMaxBiddingValue(23);
		
		assertTrue(player.respond(18));
	}
	
	@Test
	public void respondTest2() {
		
		player.setMaxBiddingValue(23);
		
		assertFalse(player.respond(26));
	}
	
	@Test
	public void bidTest() {
		
		
		player.setMaxBiddingValue(23);
		
		assertTrue(player.bid(18));
	}
	
	@Test
	public void bidTest2() {
		
		
		player.setMaxBiddingValue(23);
		
		assertFalse(player.bid(26));
	}
	
	@Test
	public void suitTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		player.setHand(hand);
		
		assertEquals(new SuitGame(PlayingCard.Suit.HEARTS).getGameVariety(), 
				player.suit().getGameVariety());
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
	public void determineMaxBiddingValueTest1() {
	
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		player.setHand(hand);
		player.determineMaxBiddingValue();
		assertEquals(23, player.getMaxBiddingValue());
	}
	
	@Test
	public void determineMaxBiddingValueTest2() {
	
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		player.setHand(hand);
		player.determineMaxBiddingValue();
		assertEquals(24, player.getMaxBiddingValue());
	}
	
	@Test
	public void determineMaxBiddingValueTest3() {
	
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		player.setHand(hand);
		player.determineMaxBiddingValue();
		assertEquals(33, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxBiddingValueSuitTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		player.setHand(hand);
		player.maxBiddingValueSuit(1);
		assertEquals(24, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxBiddingValueGrandTest() {
		
		player.maxBiddingValueGrand(1);
		assertEquals(48, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxBiddingValueNullTest() {
		
		player.maxBiddingValueNull();
		assertEquals(23, player.getMaxBiddingValue());
	}
	
	@Test
	public void determineMatadorsJackStraitTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		player.setHand(hand);
		
		assertEquals(6, player.determineMatadorsJackStrait(new SuitGame(PlayingCard.Suit.BELLS)));
	}
	
	@Test
	public void determineMatadorsJackStraitTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		hand.add(card1);
		hand.add(card2);
		player.setHand(hand);
		
		assertEquals(2, player.determineMatadorsJackStrait(new GrandGame()));
	}
	
	@Test
	public void countMatadorsJackStraitTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		PlayingCard card11 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		PlayingCard card12 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
	
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[0] = card1;
		matadorsJackStrait[1] = card2;
		matadorsJackStrait[2] = card3;
		matadorsJackStrait[3] = card4;
		matadorsJackStrait[4] = card5;
		matadorsJackStrait[5] = card6;
		matadorsJackStrait[6] = card7;
		matadorsJackStrait[7] = card8;
		matadorsJackStrait[8] = card9;
		matadorsJackStrait[9] = card10;
		matadorsJackStrait[10] = card11;
		matadorsJackStrait[11] = card12;
		
		assertEquals(12, player.countMatadorsJackStrait(matadorsJackStrait));
	}
	
	@Test
	public void countMatadorsJackStraitTest2() {
		
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[4] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		
		assertEquals(4, player.countMatadorsJackStrait(matadorsJackStrait));
	}
	@Test
	public void withoutTest1() {
		
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[12] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
		assertEquals(12, player.without(matadorsJackStrait));
	}
	
	@Test
	public void withoutTest2() {
		
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[3] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(3, player.without(matadorsJackStrait));
	}
	
	@Test
	public void withTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		PlayingCard card8 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard card10 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		PlayingCard card11 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		PlayingCard card12 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
	
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[0] = card1;
		matadorsJackStrait[1] = card2;
		matadorsJackStrait[2] = card3;
		matadorsJackStrait[3] = card4;
		matadorsJackStrait[4] = card5;
		matadorsJackStrait[5] = card6;
		matadorsJackStrait[6] = card7;
		matadorsJackStrait[7] = card8;
		matadorsJackStrait[8] = card9;
		matadorsJackStrait[9] = card10;
		matadorsJackStrait[10] = card11;
		matadorsJackStrait[11] = card12;
		
		assertEquals(12, player.with(matadorsJackStrait));
	}
	
	@Test
	public void withTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);

		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[0] = card1;
		matadorsJackStrait[1] = card2;
		matadorsJackStrait[2] = card3;
		
		assertEquals(3, player.with(matadorsJackStrait));
	}
	
	@Test
	public void suitMatadorsJackStraitTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card6 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
	
		PlayingCard[] matadorsJackStrait = new PlayingCard[13];
		matadorsJackStrait[0] = card1;
		matadorsJackStrait[1] = card2;
		matadorsJackStrait[2] = card3;
		matadorsJackStrait[3] = card4;
		matadorsJackStrait[4] = card5;
		matadorsJackStrait[7] = card6;
		matadorsJackStrait[8] = card7;
		player.setHand(hand);
		
		PlayingCard[] playerMatadorsJackStrait = player.suitMatadorsJackStrait(new SuitGame(PlayingCard.Suit.BELLS));
		
		assertArrayEquals(matadorsJackStrait, playerMatadorsJackStrait);
	}
	
	@Test
	public void grandMatadorsJackStraitTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
	
		PlayingCard[] matadorsJackStrait = {card1, card2, card3, card4};
		
		player.setHand(hand);
		
		PlayingCard[] playerMatadorsJackStrait = player.grandMatadorsJackStrait(new GrandGame());
		
		boolean result = true;
		for (int i = 0; i < 4; i++) {
			
			if (!matadorsJackStrait[i].equals(playerMatadorsJackStrait[i])) {
				
				result = false;
			}
		}
		assertTrue(result);
	}
	
	@Test
	public void grandMatadorsJackStraitTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		
		player.setHand(hand);
		
		PlayingCard[] playerMatadorsJackStrait = player.grandMatadorsJackStrait(new GrandGame());
		
		PlayingCard[] matadorsJackStrait = {card1, null, null, null};
		
		boolean result = true;
		for (int i = 0; i < 1; i++) {
			
			if (!matadorsJackStrait[i].equals(playerMatadorsJackStrait[i])) {
				
				result = false;
			}
		}
		assertTrue(result);
	}
	
	@Test
	public void grandMatadorsJackStraitTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		
		player.setHand(hand);
		
		PlayingCard[] playerMatadorsJackStrait = player.grandMatadorsJackStrait(new GrandGame());
		
		PlayingCard[] matadorsJackStrait = {null, null, null, null};
		
		assertArrayEquals(matadorsJackStrait, playerMatadorsJackStrait);
	}
	
	@Test
	public void grandMatadorsJackStraitTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		
		player.setHand(hand);
		
		PlayingCard[] playerMatadorsJackStrait = player.grandMatadorsJackStrait(new GrandGame());
		
		PlayingCard[] matadorsJackStrait = {null, null, card3, null};
		
		assertArrayEquals(matadorsJackStrait, playerMatadorsJackStrait);
	}
	
	@Test
	public void determineGameVarietyTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		player.setHand(hand);
		
		assertEquals(new NullGame().getGameVariety(), 
				player.determineGameVariety().getGameVariety());
	}
	
	@Test
	public void determineGameVarietyTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS));
		player.setHand(hand);
		
		assertEquals(new GrandGame().getGameVariety(), 
				player.determineGameVariety().getGameVariety());
	}
	
	@Test
	public void determineGameVarietyTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		player.setHand(hand);
		
		assertEquals(new SuitGame(PlayingCard.Suit.LEAVES).getGameVariety(), 
				player.determineGameVariety().getGameVariety());
	}
	
	@Test
	public void determineGameVarietyTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SEVEN));
		player.setHand(hand);
		
		assertEquals(new SuitGame(PlayingCard.Suit.LEAVES).getGameVariety(), 
				player.determineGameVariety().getGameVariety());
	}
	
	@Test
	public void determineShortLongSuitTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		hand.add(card1);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		hand.add(card2);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		hand.add(card3);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		hand.add(card4);
		player.setHand(hand);
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(card1);
		kreuz.add(card2);
		kreuz.add(card3);
		kreuz.add(card4);
		
		assertEquals(kreuz, player.determineShortLongSuit(true));
	}
	
	@Test
	public void determineShortLongSuitTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS));
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		hand.add(card5);
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		hand.add(card1);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		hand.add(card2);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		hand.add(card3);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		hand.add(card4);
		player.setHand(hand);
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		leaves.add(card5);
		
		assertEquals(leaves, player.determineShortLongSuit(false));
	}
	
	@Test
	public void determineShortSuitTest() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(karo, player.determineShortSuit(karo, herz, pik, kreuz));
	}
	
	@Test
	public void determineShortSuitTest2() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(hearts, player.determineShortSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineShortSuitTest3() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(leaves, player.determineShortSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineShortSuitTest4() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(acorns, player.determineShortSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineLongSuitTest1() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(bells, player.determineLongSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineLongSuitTest2() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(hearts, player.determineLongSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineLongSuitTest3() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> corns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hearts.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		leaves.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		corns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		corns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(leaves, player.determineLongSuit(bells, hearts, leaves, corns));
	}
	
	@Test
	public void determineLongSuitTest4() {
		
		ArrayList<PlayingCard> bells = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> hearts = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> leaves = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> acorns = new ArrayList<PlayingCard>();
		
		bells.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		acorns.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(acorns, player.determineLongSuit(bells, hearts, leaves, acorns));
	}
	
	@Test
	public void determineTrumpSuitTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		player.setHand(hand);
		
		assertEquals(PlayingCard.Suit.LEAVES, player.determineTrumpSuit());
	}

	@Test
	public void determineTrumpSuitTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		player.setHand(hand);
		
		assertEquals(PlayingCard.Suit.ACORNS, player.determineTrumpSuit());
	}
	
	@Test
	public void determineTrumpSuitTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		player.setHand(hand);
		
		assertEquals(PlayingCard.Suit.BELLS, player.determineTrumpSuit());
	}
	
	@Test
	public void determineTrumpSuitTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		player.setHand(hand);
		
		assertEquals(PlayingCard.Suit.HEARTS, player.determineTrumpSuit());
	}
	
	@Test
	public void calculateAugenTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		
		assertEquals(36, player.calculateAugen(hand));
	}

	@Test
	public void getCardAugenTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		
		assertEquals(6, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		
		assertEquals(0, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		
		assertEquals(0, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		
		assertEquals(0, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN);
		
		assertEquals(10, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		
		assertEquals(2, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(3, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);
		
		assertEquals(4, player.getCardAugen(card));
	}
	
	@Test
	public void getCardAugenTest9() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		
		assertEquals(11, player.getCardAugen(card));
	}
}
