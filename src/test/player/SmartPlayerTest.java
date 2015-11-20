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
import main.playingcard.Suit;
import main.playingcard.Value;


public class SmartPlayerTest {

	SmartPlayer player = new SmartPlayer("Max");
	ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
	PlayingCard playingCard1;
	PlayingCard playingCard2;
	PlayingCard playingCard3;
	PlayingCard playingCard4;
	PlayingCard playingCard5;
	PlayingCard[] playedCards = new PlayingCard[3];
	SuitGame suitGame = new SuitGame(Suit.HEARTS);

	@Before
	public void setUp() {
		playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		assertEquals(Value.UNDER_KNAVE, player.alleinspielerRauskommenGrand(playedCards).getValue());
	}
	
	@Test
	public void testValue() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, Value.UNDER_KNAVE);
		if (value.size() == 2) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	public void testValue2() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, Value.UNDER_KNAVE);
		for (PlayingCard card : value) {
			if (card.equals(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	

	@Test
	public void testValue3() {
		boolean flag = false;
		ArrayList<PlayingCard> value = player.cardsOfRank(hand, Value.UNDER_KNAVE);
		for (PlayingCard card : value) {
			if (card.equals(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void rauskommenNullTest() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		assertEquals(playingCard1, player.rauskommenNull(gespielteKarten));
	}
	
	@Test
	public void rauskommenNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.MITTELHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.rauskommenNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	
	@Test
	public void rauskommenNullTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.rauskommenNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void rauskommenNullTest5() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		player.setTeammate(mate);
		player.setPosition(Position.VORHAND);
		
		boolean success = false;
		if(hand.contains(player.rauskommenNull(playedCards))) {
			
			success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer adversary = new Granny("gegen");
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playedCards[0].setOwner(mate);
		
		assertEquals(playingCard2, player.alsZweiterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(playingCard1);
		hand.add(playingCard2);
		hand.add(playingCard3);
		hand.add(playingCard4);
		hand.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(hand);
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer adversary = new Granny("gegen");
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playedCards[0].setOwner(adversary);
		
		assertEquals(playingCard2, player.alsZweiterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		assertEquals(playingCard1, player.alsDritterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard playingCard8 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard5, player.alsDritterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard8 = new PlayingCard(Suit.HEARTS, Value.DAUS);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.KING);
		playedCards[0].setOwner(adversary);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard5, player.alsDritterKarteSpielenNull(playedCards));
	}

	@Test
	public void alsDritterKartenSpielenNullTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard8 = new PlayingCard(Suit.HEARTS, Value.DAUS);
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
		player.setAnfangsblatt(hand);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new NullGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		IPlayer adversary = new Granny("adversary");
		playedCards[0] = new PlayingCard(Suit.ACORNS, Value.KING);
		playedCards[0].setOwner(mate);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.NINE);
		playedCards[1].setOwner(adversary);
		
		assertEquals(playingCard5, player.alsDritterKarteSpielenNull(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest1() {
		
		Table table = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		table.createDeck();
		deck = table.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[0].setOwner(adversary);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		playedCards[0].setOwner(mate);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest4() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		player.setDeck(deck);
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.KING);
		playedCards[0].setOwner(mate);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.KING);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.KING);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void rauskommenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.rauskommen(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		if (playingCard3.getSuit().equals((player.rauskommen(playedCards)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.ACORNS, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		if (playingCard3.getSuit().equals((player.rauskommen(playedCards)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		
		boolean erfolg = false;
		if (!blatt.contains(player.alsZweiterKarteSpielen(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[0].setOwner(mate);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[0].setOwner(gegner);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		playedCards[0].setOwner(gegner);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.alsDritterKarteSpielen(playedCards))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsDritterKarteSpielenTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsDritterKarteSpielen(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard3, player.alsDritterKarteSpielen(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.KING);
		playedCards[0].setOwner(wicht);
		playedCards[1] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		playedCards[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsDritterKarteSpielen(playedCards));
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard playingCard6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
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
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest3() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest4() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setAnfangsblatt(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (gespielt.getValue() == Value.DAUS) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest5() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setAnfangsblatt(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest6() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setAnfangsblatt(blatt);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
		if (gespielt.getValue() == Value.TEN) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest7() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard playingCard6 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard playingCard7 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.getAllPlayedCards().add(playingCard6);
		player.getAllPlayedCards().add(playingCard7);
		player.setHand(blatt);
		player.setAnfangsblatt(blatt);
		player.getAnfangsBlatt().add(playingCard6);
		player.getAnfangsBlatt().add(playingCard7);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(playedCards);
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
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(Value.UNDER_KNAVE, player.rauskommenGrand(playedCards).getValue());
	}
	
	@Test
	public void rauskommenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (!blatt.contains(player.rauskommenGrand(playedCards))) {
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
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.KING);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.NINE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (Suit.ACORNS == player.rauskommenGrand(playedCards).getSuit()) {
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
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest1() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.KING);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.KING);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest2() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new GrandGame());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		playedCards[1] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(playedCards));
	}
	
	@Test
	public void rauskommenRamsch() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
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
				player.rauskommenRamsch(playedCards));
	}
	
	@Test
	public void alsZweiterKarteSpielenRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		
		assertEquals(playingCard1, 
				player.alsZweiterKarteSpielenRamsch(playedCards));
	}
	
	@Test
	public void alsDritterKarteSpielenRamschTest() {
		
		PlayingCard playingCard1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard playingCard2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard playingCard3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard playingCard4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard playingCard5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(playingCard1);
		blatt.add(playingCard2);
		blatt.add(playingCard3);
		blatt.add(playingCard4);
		blatt.add(playingCard5);
		player.setHand(blatt);
		player.setGameVariety(new Ramsch());
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		playedCards[1] = new PlayingCard(Suit.BELLS, Value.TEN);
		
		assertEquals(playingCard1, 
				player.alsDritterKarteSpielenRamsch(playedCards));
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
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		
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
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.SIX);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.SEVEN);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest2() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest3() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest4() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest5() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.KING);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest6() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest7() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard hcard = new PlayingCard(Suit.BELLS, Value.DAUS);
		boolean result = false;
		if (hcard.equals(player.naechstHoehereKarte(Suit.BELLS, card))) {
			
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void naechstHoehereKarteTest8() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(null, player.naechstHoehereKarte(Suit.BELLS, card));
	}
	
	@Test
	public void naechstHoehereKarteNeunTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean success = false;
		if (card.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteTest1() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.SIX);
		
		assertEquals(null, player.naechstNiedrigereKarte(Suit.BELLS, card));
	}
	
	@Test
	public void naechstNiedrigereKarteTest2() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.SIX);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest3() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest4() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest5() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest6() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest7() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.KING);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void naechstNiedrigereKarteTest8() {
		
		PlayingCard card = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean result = false;
		if (card2.equals(player.naechstNiedrigereKarte(Suit.BELLS, card))) {
			result = true;
		}
		assertTrue(result);
	}
	@Test
	public void naechstNiedrigereKarteDameTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard card = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean success = false;
		if (card.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			success = true;
		}
		
		assertTrue(success);
	}
	
	@Test
	public void kartenEinesWertesTest1() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard card6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
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
		assertEquals(expected, player.cardsOfRank(hand, Value.DAUS));
	}
	
	@Test
	public void kartenEinerFarbeTest1() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard card6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
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
		
		assertEquals(karo, player.cardsOfSuit(hand, Suit.BELLS));
	}
	
	@Test
	public void kartenEinerFarbeTest2() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard card6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
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
		
		assertEquals(herz, player.cardsOfSuit(hand, Suit.HEARTS));
	}
	
	@Test
	public void kartenEinerFarbeTest3() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard card6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard card10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
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
		
		assertEquals(pik, player.cardsOfSuit(hand, Suit.LEAVES));
	}
	
	@Test
	public void kartenEinerFarbeTest4() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard card4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard card5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard card6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard card10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
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
		
		assertEquals(kreuz, player.cardsOfSuit(hand, Suit.ACORNS));
	}
	
	@Test
	public void hoechsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		
		playableCards.add(card1);
		playableCards.add(card2);
		playableCards.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		playableCards.add(new PlayingCard(Suit.BELLS, Value.KING));
		playableCards.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		
		assertEquals(card1, player.hoechsteSpielbareKarte(playableCards));
	}
	
	@Test
	public void niedrigsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> playableCards = new ArrayList<PlayingCard>();
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		playableCards.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		playableCards.add(card1);
		playableCards.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		playableCards.add(new PlayingCard(Suit.BELLS, Value.KING));
		playableCards.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		
		assertEquals(card1, player.niedrigsteSpielbareKarte(playableCards));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest1() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new GrandGame());
		
		assertEquals(card1,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest2() {
		
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card2,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest3() {
		
		PlayingCard card1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(card1,
				player.hoechsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest1() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		
		assertEquals(card2,
				player.niedrigsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		assertEquals(card1,
				player.niedrigsteSpielbareKarteBestimmen(card1, card2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest3() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard card1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
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
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.KING));
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		hand.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		hand.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.setHand(hand);
		
		assertEquals(new GrandGame().getGameVariety(), 
				player.declareGame().getGameVariety());
	}
	
	@Test
	public void hoerenTest() {
		
		player.setMaxReizwert(23);
		
		assertTrue(player.respond(18));
	}
	
	@Test
	public void hoerenTest2() {
		
		player.setMaxReizwert(23);
		
		assertFalse(player.respond(26));
	}
	
	@Test
	public void sagenTest() {
		
		
		player.setMaxReizwert(23);
		
		assertTrue(player.bid(18));
	}
	
	@Test
	public void sagenTest2() {
		
		
		player.setMaxReizwert(23);
		
		assertFalse(player.bid(26));
	}
	
	@Test
	public void farbeTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		player.setHand(hand);
		
		assertEquals(new SuitGame(Suit.HEARTS).getGameVariety(), 
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
		hand.add(new PlayingCard(Suit.BELLS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		player.setHand(hand);
		player.bestimmeMaxReizwert();
		assertEquals(23, player.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert2() {
	
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		player.setHand(hand);
		player.bestimmeMaxReizwert();
		assertEquals(24, player.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert3() {
	
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.KING));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(hand);
		player.bestimmeMaxReizwert();
		assertEquals(33, player.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertFarbeTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.KING));
		player.setHand(hand);
		player.maxReizwertFarbe(1);
		assertEquals(24, player.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertGrandTest() {
		
		player.maxReizwertGrand(1);
		assertEquals(48, player.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertNullTest() {
		
		player.maxReizwertNull();
		assertEquals(23, player.getMaxReizwert());
	}
	
	@Test
	public void ermittleSpitzenTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card6 = new PlayingCard(Suit.BELLS, Value.TEN);
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		player.setHand(hand);
		
		assertEquals(6, player.ermittleSpitzen(new SuitGame(Suit.BELLS)));
	}
	
	@Test
	public void ermittleSpitzenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		blatt.add(card1);
		blatt.add(card2);
		player.setHand(blatt);
		
		assertEquals(2, player.ermittleSpitzen(new GrandGame()));
	}
	
	@Test
	public void spitzenZaehlenTest1() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard card6 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard card10 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard card11 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard card12 = new PlayingCard(Suit.LEAVES, Value.SIX);
	
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
		spitzen[4] = new PlayingCard(Suit.HEARTS, Value.DAUS);
		
		assertEquals(4, player.spitzenZaehlen(spitzen));
	}
	@Test
	public void ohneTest1() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[12] = new PlayingCard(Suit.LEAVES, Value.SIX);
		assertEquals(12, player.ohne(spitzen));
	}
	
	@Test
	public void ohneTest2() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[3] = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals(3, player.ohne(spitzen));
	}
	
	@Test
	public void mitTest1() {
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard card6 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard card7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard card8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard card9 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard card10 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard card11 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard card12 = new PlayingCard(Suit.LEAVES, Value.SIX);
	
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
		
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);

		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		
		assertEquals(3, player.mit(spitzen));
	}
	
	@Test
	public void farbeSpitzenTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard card5 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard card6 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard card7 = new PlayingCard(Suit.BELLS, Value.NINE);
		
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		hand.add(card5);
		hand.add(card6);
		hand.add(card7);
		
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = card1;
		spitzen[1] = card2;
		spitzen[2] = card3;
		spitzen[3] = card4;
		spitzen[4] = card5;
		spitzen[7] = card6;
		spitzen[8] = card7;
		player.setHand(hand);
		
		PlayingCard[] spielerSpitzen = player.farbeSpitzen(new SuitGame(Suit.BELLS));
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
	
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
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
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
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.SIX);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
		player.setHand(hand);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, null, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.SIX);
		PlayingCard card2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard card3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard card4 = new PlayingCard(Suit.BELLS, Value.SIX);
	
		hand.add(card1);
		hand.add(card2);
		hand.add(card3);
		hand.add(card4);
		
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
		player.setHand(hand);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, card3, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void bestimmeSpielartTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		player.setHand(hand);
		
		assertEquals(new NullGame().getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		hand.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		player.setHand(hand);
		
		assertEquals(new GrandGame().getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.KING));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(hand);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.KING));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(hand);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.BELLS, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		hand.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		hand.add(card1);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.TEN);
		hand.add(card2);
		PlayingCard card3 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		hand.add(card3);
		PlayingCard card4 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		hand.add(card4);
		player.setHand(hand);
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(card1);
		kreuz.add(card2);
		kreuz.add(card3);
		kreuz.add(card4);
		
		assertEquals(kreuz, player.ermittleKurzeLangeFarbe(true));
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.BELLS, Value.NINE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		PlayingCard card5 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		hand.add(card5);
		PlayingCard card1 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		hand.add(card1);
		PlayingCard card2 = new PlayingCard(Suit.ACORNS, Value.TEN);
		hand.add(card2);
		PlayingCard card3 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		hand.add(card3);
		PlayingCard card4 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		hand.add(card4);
		player.setHand(hand);
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(card5);
		
		assertEquals(pik, player.ermittleKurzeLangeFarbe(false));
	}
	
	@Test
	public void ermittleKurzeFarbeTest() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(karo, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest2() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(herz, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest3() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(pik, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleKurzeFarbeTest4() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(kreuz, player.ermittleKurzeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest1() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(karo, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest2() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(herz, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest3() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		herz.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		pik.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(pik, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleLangeFarbeTest4() {
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		
		karo.add(new PlayingCard(Suit.BELLS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		kreuz.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		
		assertEquals(kreuz, player.ermittleLangeFarbe(karo, herz, pik, kreuz));
	}
	
	@Test
	public void ermittleTrumpffarbeTest1() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.KING));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(hand);
		
		assertEquals(Suit.LEAVES, player.ermittleTrumpffarbe());
	}

	@Test
	public void ermittleTrumpffarbeTest2() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.KING));
		hand.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(hand);
		
		assertEquals(Suit.ACORNS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest3() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.BELLS, Value.KING));
		hand.add(new PlayingCard(Suit.BELLS, Value.TEN));
		hand.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(hand);
		
		assertEquals(Suit.BELLS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest4() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.HEARTS, Value.KING));
		hand.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		hand.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		hand.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		hand.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(hand);
		
		assertEquals(Suit.HEARTS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void werteAugenTest() {
		
		ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();
		
		hand.add(new PlayingCard(Suit.BELLS, Value.SIX));
		hand.add(new PlayingCard(Suit.BELLS, Value.EIGHT));
		hand.add(new PlayingCard(Suit.BELLS, Value.NINE));
		hand.add(new PlayingCard(Suit.BELLS, Value.TEN));
		hand.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		hand.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		hand.add(new PlayingCard(Suit.BELLS, Value.KING));
		hand.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		
		assertEquals(36, player.werteAugen(hand));
	}

	@Test
	public void augenKarteTest1() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.SIX);
		
		assertEquals(6, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest2() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest3() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest4() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.NINE);
		
		assertEquals(0, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest5() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.TEN);
		
		assertEquals(10, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest6() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		
		assertEquals(2, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest7() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		
		assertEquals(3, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest8() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.KING);
		
		assertEquals(4, player.augenKarte(card));
	}
	
	@Test
	public void augenKarteTest9() {
		
		PlayingCard card = new PlayingCard(Suit.HEARTS, Value.DAUS);
		
		assertEquals(11, player.augenKarte(card));
	}
}
