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
	public void testalleinspielerRauskommenGrand() {
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
	public void rauskommenNullTest() {
		
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		assertEquals(playingCard1, player.playFirstCardNull(gespielteKarten));
	}
	
	@Test
	public void rauskommenNullTest2() {
		
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
	public void rauskommenNullTest4() {
		
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
	public void rauskommenNullTest5() {
		
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
	public void alsZweiterKarteSpielenNullTest1() {
		
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
	public void alsZweiterKarteSpielenNullTest2() {
		
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
	public void alsZweiterKarteSpielenNullTest3() {
		
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
	public void alsDritterKartenSpielenNullTest1() {
		
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
	public void alsDritterKartenSpielenNullTest2() {
		
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
	public void alsDritterKartenSpielenNullTest3() {
		
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
	public void alsDritterKartenSpielenNullTest4() {
		
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
	public void alsZweiterKarteSpielenGrandTest1() {
		
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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer adversary = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(mate);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest4() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.playSecondCardGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(mate);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(gegner);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.playThirdCardGrand(playedCards));
	}
	
	@Test
	public void rauskommenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.playFirstCardSuit(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		mate.setPosition(Position.MITTELHAND);
		IPlayer gegner = new Granny("adversary");
		gegner.setPosition(Position.HINTERHAND);
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean erfolg = false;
		if (playingCard3.getSuit().equals((player.playFirstCardSuit(playedCards)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		mate.setPosition(Position.HINTERHAND);
		IPlayer gegner = new Granny("adversary");
		gegner.setPosition(Position.MITTELHAND);
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean erfolg = false;
		if (playingCard3.getSuit().equals((player.playFirstCardSuit(playedCards)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		
		boolean erfolg = false;
		if (!blatt.contains(player.playSecondCardSuit(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(mate);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playSecondCardSuit(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setIsDeclarer(true);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.playThirdCardSuit(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsDritterKarteSpielenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard3, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.playThirdCardSuit(playedCards));
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		blatt.add(playingCard6);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (gespielt.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (gespielt.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (gespielt.getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setStartHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (gespielt.getRank() == PlayingCard.Rank.DAUS) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest5() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setStartHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest6() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setStartHand(blatt);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (gespielt.getRank() == PlayingCard.Rank.TEN) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest7() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setStartHand(blatt);
		player.getStartHand().add(playingCard6);
		player.getStartHand().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.playFirstCardGrandAsDeclarer(playedCards);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void rauskommenGrandTest() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setIsDeclarer(true);
		player.setDeck(deck);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		assertEquals(PlayingCard.Rank.UNDER_KNAVE, player.playFirstCardGrand(playedCards).getRank());
	}
	
	@Test
	public void rauskommenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setDeck(deck);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		IPlayer gegner = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (!blatt.contains(player.playFirstCardGrand(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setDeck(deck);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		IPlayer gegner = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		player.setTeammate(mate);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (PlayingCard.Suit.ACORNS == player.playFirstCardGrand(playedCards).getSuit()) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest1() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setDeck(deck);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setDeck(deck);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.KING);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void rauskommenRamsch() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		assertEquals(playingCard1, 
				player.playFirstCardRamsch(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		
		assertEquals(playingCard1, 
				player.playSecondCardRamsch(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.NINE);
		PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		
		assertEquals(playingCard1, 
				player.playThirdCardRamsch(playedCards));
	}
	
	@Test
	public void gegnerMoeglicheSpielbareKartenTest() {
		
		Table tisch = new Table();
		tisch.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		//Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		player.setDeck(deck);
		player.setIsDeclarer(true);
		player.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			player.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> alleGespielteKarten = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(playedCards)));
		
		ArrayList<PlayingCard> ergebnisKarten = new ArrayList<PlayingCard>();
		for (int k = 0; k < deck.size(); k++) {
			
			if(player.getGameVariety().
					checkedPlayedCards(deck, playedCards, deck.get(k))) {
				
				ergebnisKarten.add(deck.get(k));
			}
		}
		
		assertEquals(ergebnisKarten, player.gegnerMoeglicheSpielbareKarten(playedCards));
	}
	
	@Test
	public void moeglicheGegnerKarteTest() {
		
		Table tisch = new Table();
		tisch.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		player.setDeck(deck);
		player.setIsDeclarer(true);
		player.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			player.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> alleGespielteKarten = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(playedCards)));
	
		assertEquals(deck, 
				player.moeglicheGegnerKarten(alleGespielteKarten, playedCards));
	}
	
	@Test
	public void zufallszahlTest() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		PlayingCard card4 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		PlayingCard card5 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(card1);
		blatt.add(card2);
		blatt.add(card3);
		blatt.add(card4);
		blatt.add(card5);
		int zufallszahl = player.zufallszahl(blatt);
		
		boolean ergebnis = false;
		if (zufallszahl >= 0 && zufallszahl < blatt.size()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard hcard = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		
		assertEquals(null, player.naechstHoehereKarte(PlayingCard.Suit.BELLS, card));
	}
	
	@Test
	public void naechstHoehereKarteNeunTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.LEAVES));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(PlayingCard.Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		
		assertEquals(null, player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card));
	}
	
	@Test
	public void naechstNiedrigereKarteTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(PlayingCard.Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	@Test
	public void naechstNiedrigereKarteDameTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest1() {
		
		player.setGameVariety(new SuitGame(PlayingCard.Suit.BELLS));
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(PlayingCard.Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void kartenEinesWertesTest1() {
		
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
	public void kartenEinerFarbeTest1() {
		
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
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		karo.add(card1);
		karo.add(card2);
		
		assertEquals(karo, player.cardsOfSuit(hand, PlayingCard.Suit.BELLS));
	}
	
	@Test
	public void kartenEinerFarbeTest2() {
		
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
		
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		herz.add(card3);
		herz.add(card4);
		herz.add(card5);
		herz.add(card6);
		
		assertEquals(herz, player.cardsOfSuit(hand, PlayingCard.Suit.HEARTS));
	}
	
	@Test
	public void kartenEinerFarbeTest3() {
		
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
		
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(card7);
		pik.add(card8);
		pik.add(card9);
		
		assertEquals(pik, player.cardsOfSuit(hand, PlayingCard.Suit.LEAVES));
	}
	
	@Test
	public void kartenEinerFarbeTest4() {
		
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
		
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(card10);
		
		assertEquals(kreuz, player.cardsOfSuit(hand, PlayingCard.Suit.ACORNS));
	}
	
	@Test
	public void hoechsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		
		playableCards.add(card1);
		playableCards.add(card2);
		playableCards.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		playableCards.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		playableCards.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		
		assertEquals(card1, player.hoechsteSpielbareKarte(playableCards));
	}
	
	@Test
	public void niedrigsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.EIGHT);
		
		playableCards.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE));
		playableCards.add(card1);
		playableCards.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE));
		playableCards.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		playableCards.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.OVER_KNAVE));
		
		assertEquals(card1, player.niedrigsteSpielbareKarte(playableCards));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest1() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new GrandGame());
		
		assertEquals(card1,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card2,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest3() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card1,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest1() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.TEN);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(card2,
				player.niedrigsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		assertEquals(card1,
				player.niedrigsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest3() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.NINE);
		
		assertEquals(card1,
				player.niedrigsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void drueckenTest() {
		
		PlayingCard[] skat = new PlayingCard[3];
		assertArrayEquals(null, player.druecken(skat));
	}
	
	@Test
	public void handspielTest() {
		
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
	public void spielAnsagenTest() {
		
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
	public void hoerenTest() {
		
		player.setMaxBiddingValue(23);
		
		assertTrue(player.respond(18));
	}
	
	@Test
	public void hoerenTest2() {
		
		player.setMaxBiddingValue(23);
		
		assertFalse(player.respond(26));
	}
	
	@Test
	public void sagenTest() {
		
		
		player.setMaxBiddingValue(23);
		
		assertTrue(player.bid(18));
	}
	
	@Test
	public void sagenTest2() {
		
		
		player.setMaxBiddingValue(23);
		
		assertFalse(player.bid(26));
	}
	
	@Test
	public void farbeTest() {
		
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
	public void reizlimitFestlegenTest() {
		
		assertEquals(0, player.setBidLimit());
	}
	
	@Test
	public void bestimmeMaxReizwert1() {
	
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
		player.bestimmeMaxReizwert();
		assertEquals(23, player.getMaxBiddingValue());
	}
	
	@Test
	public void bestimmeMaxReizwert2() {
	
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
		player.bestimmeMaxReizwert();
		assertEquals(24, player.getMaxBiddingValue());
	}
	
	@Test
	public void bestimmeMaxReizwert3() {
	
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
		player.bestimmeMaxReizwert();
		assertEquals(33, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxReizwertFarbeTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING));
		player.setHand(hand);
		player.maxReizwertFarbe(1);
		assertEquals(24, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxReizwertGrandTest() {
		
		player.maxReizwertGrand(1);
		assertEquals(48, player.getMaxBiddingValue());
	}
	
	@Test
	public void maxReizwertNullTest() {
		
		player.maxReizwertNull();
		assertEquals(23, player.getMaxBiddingValue());
	}
	
	@Test
	public void ermittleSpitzenTest1() {
		
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
		
		assertEquals(6, player.ermittleSpitzen(new SuitGame(PlayingCard.Suit.BELLS)));
	}
	
	@Test
	public void ermittleSpitzenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		blatt.add(card1);
		blatt.add(card2);
		player.setHand(blatt);
		
		assertEquals(2, player.ermittleSpitzen(new GrandGame()));
	}
	
	@Test
	public void spitzenZaehlenTest1() {
		
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
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		spitzen[3] = card4;
		spitzen[4] = card5;
		spitzen[5] = card6;
		spitzen[6] = card7;
		spitzen[7] = card8;
		spitzen[8] = card9;
		spitzen[9] = card10;
		spitzen[10] = card11;
		spitzen[11] = card12;
		
		assertEquals(12, player.spitzenZaehlen(spitzen));
	}
	
	@Test
	public void spitzenZaehlenTest2() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[4] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		
		assertEquals(4, player.spitzenZaehlen(spitzen));
	}
	@Test
	public void ohneTest1() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[12] = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX);
		assertEquals(12, player.ohne(spitzen));
	}
	
	@Test
	public void ohneTest2() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[3] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
		assertEquals(3, player.ohne(spitzen));
	}
	
	@Test
	public void mitTest1() {
		
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
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		spitzen[3] = card4;
		spitzen[4] = card5;
		spitzen[5] = card6;
		spitzen[6] = card7;
		spitzen[7] = card8;
		spitzen[8] = card9;
		spitzen[9] = card10;
		spitzen[10] = card11;
		spitzen[11] = card12;
		
		assertEquals(12, player.mit(spitzen));
	}
	
	@Test
	public void mitTest2() {
		
		PlayingCard card1 = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);

		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		
		assertEquals(3, player.mit(spitzen));
	}
	
	@Test
	public void farbeSpitzenTest() {
		
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
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		spitzen[3] = card4;
		spitzen[4] = card5;
		spitzen[7] = card6;
		spitzen[8] = card7;
		player.setHand(hand);
		
		PlayingCard[] spielerSpitzen = player.farbeSpitzen(new SuitGame(PlayingCard.Suit.BELLS));
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest1() {
		
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
	
		PlayingCard[] spitzen = {card1, card2, card3, card4};
		
		player.setHand(hand);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		boolean result = true;
		for (int i = 0; i < 4; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				result = false;
			}
		}
		assertTrue(result);
	}
	
	@Test
	public void grandSpitzenTest2() {
		
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
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {card1, null, null, null};
		
		boolean result = true;
		for (int i = 0; i < 1; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				result = false;
			}
		}
		assertTrue(result);
	}
	
	@Test
	public void grandSpitzenTest3() {
		
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
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, null, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest4() {
		
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
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, card3, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void bestimmeSpielartTest() {
		
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
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest2() {
		
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
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest3() {
		
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
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest4() {
		
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
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest() {
		
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
	public void ermittleKurzeLangeFarbeTest2() {
		
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
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(card5);
		
		assertEquals(pik, player.determineShortLongSuit(false));
	}
	
	@Test
	public void ermittleKurzeFarbeTest() {
		
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
		
		assertEquals(karo, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest2() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(herz, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest3() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(pik, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest4() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(kreuz, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest1() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(karo, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest2() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(herz, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest3() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		herz.add(new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		pik.add(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(pik, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest4() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		kreuz.add(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.SIX));
		
		assertEquals(kreuz, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleTrumpffarbeTest1() {
		
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
		
		assertEquals(PlayingCard.Suit.LEAVES, player.ermittleTrumpffarbe());
	}

	@Test
	public void ermittleTrumpffarbeTest2() {
		
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
		
		assertEquals(PlayingCard.Suit.ACORNS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest3() {
		
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
		
		assertEquals(PlayingCard.Suit.BELLS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest4() {
		
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
		
		assertEquals(PlayingCard.Suit.HEARTS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void werteAugenTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SIX));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING));
		hand.add(new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS));
		
		assertEquals(36, player.werteAugen(hand));
	}

	@Test
	public void augenKarteTest1() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SIX);
		
		assertEquals(6, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest2() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest3() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest4() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest5() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.TEN);
		
		assertEquals(10, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest6() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		
		assertEquals(2, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest7() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.OVER_KNAVE);
		
		assertEquals(3, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest8() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING);
		
		assertEquals(4, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest9() {
		
		PlayingCard card = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.DAUS);
		
		assertEquals(11, player.augenKarte(card));
	}
}
