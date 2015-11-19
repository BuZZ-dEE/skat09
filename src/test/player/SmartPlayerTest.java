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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(mate);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsZweiterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(mate);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(gegner);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( playingCard1, player.alsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.rauskommen(gespielteKarten))) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean erfolg = false;
		if (playingCard3.getSuit().equals((player.rauskommen(gespielteKarten)).getSuit())) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean erfolg = false;
		if (playingCard3.getSuit().equals((player.rauskommen(gespielteKarten)).getSuit())) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		
		boolean erfolg = false;
		if (!blatt.contains(player.alsZweiterKarteSpielen(gespielteKarten))) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(mate);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsZweiterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(player.alsDritterKarteSpielen(gespielteKarten))) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsDritterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(playingCard3, player.alsDritterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		player.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(playingCard1, player.alsDritterKarteSpielen(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = player.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(Value.UNDER_KNAVE, player.rauskommenGrand(gespielteKarten).getValue());
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (!blatt.contains(player.rauskommenGrand(gespielteKarten))) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		player.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (Suit.ACORNS == player.rauskommenGrand(gespielteKarten).getSuit()) {
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.KING);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		assertEquals(playingCard1, 
				player.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		assertEquals(playingCard1, 
				player.rauskommenRamsch(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		
		assertEquals(playingCard1, 
				player.alsZweiterKarteSpielenRamsch(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.TEN);
		
		assertEquals(playingCard1, 
				player.alsDritterKarteSpielenRamsch(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(gespielteKarten)));
		
		ArrayList<PlayingCard> ergebnisKarten = new ArrayList<PlayingCard>();
		for (int k = 0; k < deck.size(); k++) {
			
			if(player.getGameVariety().
					checkedPlayedCards(deck, gespielteKarten, deck.get(k))) {
				
				ergebnisKarten.add(deck.get(k));
			}
		}
		
		assertEquals(ergebnisKarten, player.gegnerMoeglicheSpielbareKarten(gespielteKarten));
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
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = deck.remove(0);
		player.setSkat(new ArrayList<PlayingCard>(Arrays.asList(gespielteKarten)));
	
		assertEquals(deck, 
				player.moeglicheGegnerKarten(alleGespielteKarten, gespielteKarten));
	}
	
	@Test
	public void zufallszahlTest() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		int zufallszahl = player.zufallszahl(blatt);
		
		boolean ergebnis = false;
		if (zufallszahl >= 0 && zufallszahl < blatt.size()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest1() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SIX);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.SEVEN);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.KING);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.DAUS);
		boolean ergebnis = false;
		if (hkarte.equals(player.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(null, player.naechstHoehereKarte(Suit.BELLS, karte));
	}
	
	@Test
	public void naechstHoehereKarteNeunTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteTest1() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SIX);
		
		assertEquals(null, player.naechstNiedrigereKarte(Suit.BELLS, karte));
	}
	
	@Test
	public void naechstNiedrigereKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.SIX);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.KING);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean ergebnis = false;
		if (karte2.equals(player.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	@Test
	public void naechstNiedrigereKarteDameTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest1() {
		
		player.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest3() {
		
		player.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(player.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void kartenEinesWertesTest1() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard karte6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<PlayingCard> erwartet = new ArrayList<PlayingCard>();
		erwartet.add(karte2);
		erwartet.add(karte3);
		assertEquals(erwartet, player.cardsOfRank(blatt, Value.DAUS));
	}
	
	@Test
	public void kartenEinerFarbeTest1() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard karte6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<PlayingCard> karo = new ArrayList<PlayingCard>();
		karo.add(karte1);
		karo.add(karte2);
		
		assertEquals(karo, player.cardsOfSuit(blatt, Suit.BELLS));
	}
	
	@Test
	public void kartenEinerFarbeTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard karte6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<PlayingCard> herz = new ArrayList<PlayingCard>();
		herz.add(karte3);
		herz.add(karte4);
		herz.add(karte5);
		herz.add(karte6);
		
		assertEquals(herz, player.cardsOfSuit(blatt, Suit.HEARTS));
	}
	
	@Test
	public void kartenEinerFarbeTest3() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard karte6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard karte10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(karte7);
		pik.add(karte8);
		pik.add(karte9);
		
		assertEquals(pik, player.cardsOfSuit(blatt, Suit.LEAVES));
	}
	
	@Test
	public void kartenEinerFarbeTest4() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard karte4 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard karte5 = new PlayingCard(Suit.HEARTS, Value.NINE);
		PlayingCard karte6 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard karte10 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		blatt.add(karte8);
		blatt.add(karte9);
		blatt.add(karte10);
		
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(karte10);
		
		assertEquals(kreuz, player.cardsOfSuit(blatt, Suit.ACORNS));
	}
	
	@Test
	public void hoechsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> spielbareKarten = new ArrayList<PlayingCard>();
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		
		spielbareKarten.add(karte1);
		spielbareKarten.add(karte2);
		spielbareKarten.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		spielbareKarten.add(new PlayingCard(Suit.BELLS, Value.KING));
		spielbareKarten.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		
		assertEquals(karte1, player.hoechsteSpielbareKarte(spielbareKarten));
	}
	
	@Test
	public void niedrigsteSpielbareKarteTest() {
		
		ArrayList<PlayingCard> spielbareKarten = new ArrayList<PlayingCard>();
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		
		spielbareKarten.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spielbareKarten.add(karte1);
		spielbareKarten.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		spielbareKarten.add(new PlayingCard(Suit.BELLS, Value.KING));
		spielbareKarten.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		
		assertEquals(karte1, player.niedrigsteSpielbareKarte(spielbareKarten));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest1() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new GrandGame());
		
		assertEquals(karte1, 
				player.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(karte2, 
				player.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest3() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		player.setGameVariety(new NullGame());
		
		assertEquals(karte1, 
				player.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest1() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		
		assertEquals(karte2, 
				player.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest2() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		assertEquals(karte1, 
				player.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest3() {
		
		player.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		assertEquals(karte1, 
				player.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.KING));
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		player.setHand(blatt);
		
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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		player.setHand(blatt);
		
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
	
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		player.setHand(blatt);
		player.bestimmeMaxReizwert();
		assertEquals(23, player.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert2() {
	
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		player.setHand(blatt);
		player.bestimmeMaxReizwert();
		assertEquals(24, player.getMaxReizwert());
	}
	
	@Test
	public void bestimmeMaxReizwert3() {
	
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.KING));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(blatt);
		player.bestimmeMaxReizwert();
		assertEquals(33, player.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertFarbeTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.KING));
		player.setHand(blatt);
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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard karte5 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte6 = new PlayingCard(Suit.BELLS, Value.TEN);
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		player.setHand(blatt);
		
		assertEquals(6, player.ermittleSpitzen(new SuitGame(Suit.BELLS)));
	}
	
	@Test
	public void ermittleSpitzenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		blatt.add(karte1);
		blatt.add(karte2);
		player.setHand(blatt);
		
		assertEquals(2, player.ermittleSpitzen(new GrandGame()));
	}
	
	@Test
	public void spitzenZaehlenTest1() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard karte5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard karte6 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard karte10 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard karte11 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard karte12 = new PlayingCard(Suit.LEAVES, Value.SIX);
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[5] = karte6;
		spitzen[6] = karte7;
		spitzen[7] = karte8;
		spitzen[8] = karte9;
		spitzen[9] = karte10;
		spitzen[10] = karte11;
		spitzen[11] = karte12;
		
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
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard karte5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard karte6 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard karte7 = new PlayingCard(Suit.LEAVES, Value.KING);
		PlayingCard karte8 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		PlayingCard karte9 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard karte10 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard karte11 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard karte12 = new PlayingCard(Suit.LEAVES, Value.SIX);
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[5] = karte6;
		spitzen[6] = karte7;
		spitzen[7] = karte8;
		spitzen[8] = karte9;
		spitzen[9] = karte10;
		spitzen[10] = karte11;
		spitzen[11] = karte12;
		
		assertEquals(12, player.mit(spitzen));
	}
	
	@Test
	public void mitTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);

		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		
		assertEquals(3, player.mit(spitzen));
	}
	
	@Test
	public void farbeSpitzenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE); 
		PlayingCard karte5 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte6 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard karte7 = new PlayingCard(Suit.BELLS, Value.NINE);
		
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		blatt.add(karte5);
		blatt.add(karte6);
		blatt.add(karte7);
		
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
	
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[0] = karte1;
		spitzen[1] = karte2;
		spitzen[2] = karte3;
		spitzen[3] = karte4;
		spitzen[4] = karte5;
		spitzen[7] = karte6;
		spitzen[8] = karte7;
		player.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = player.farbeSpitzen(new SuitGame(Suit.BELLS));
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest1() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
	
		PlayingCard[] spitzen = {karte1, karte2, karte3, karte4};
		
		player.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		boolean ergebnis = true;
		for (int i = 0; i < 4; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				ergebnis = false;
			}
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void grandSpitzenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.SIX); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
		player.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {karte1, null, null, null};
		
		boolean ergebnis = true;
		for (int i = 0; i < 1; i++) {
			
			if (!spitzen[i].equals(spielerSpitzen[i])) {
				
				ergebnis = false;
			}
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void grandSpitzenTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.SIX);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.SIX);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.SIX); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
		player.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, null, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void grandSpitzenTest4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.SIX);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.SIX);
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard karte4 = new PlayingCard(Suit.BELLS, Value.SIX); 
	
		blatt.add(karte1);
		blatt.add(karte2);
		blatt.add(karte3);
		blatt.add(karte4);
		
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		
		player.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = player.grandSpitzen(new GrandGame());
		
		PlayingCard[] spitzen = {null, null, karte3, null};
		
		assertArrayEquals(spitzen, spielerSpitzen);
	}
	
	@Test
	public void bestimmeSpielartTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		player.setHand(blatt);
		
		assertEquals(new NullGame().getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.TEN));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.DAUS));
		player.setHand(blatt);
		
		assertEquals(new GrandGame().getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.KING));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(blatt);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void bestimmeSpielartTest4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.KING));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SEVEN));
		player.setHand(blatt);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				player.bestimmeSpielart().getGameVariety());
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE));
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		blatt.add(karte1);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.TEN);
		blatt.add(karte2);
		PlayingCard karte3 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		blatt.add(karte3);
		PlayingCard karte4 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		blatt.add(karte4);
		player.setHand(blatt);
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(karte1);
		kreuz.add(karte2);
		kreuz.add(karte3);
		kreuz.add(karte4);
		
		assertEquals(kreuz, player.ermittleKurzeLangeFarbe(true));
	}
	
	@Test
	public void ermittleKurzeLangeFarbeTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SEVEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		PlayingCard karte5 = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE); 
		blatt.add(karte5);
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		blatt.add(karte1);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.TEN);
		blatt.add(karte2);
		PlayingCard karte3 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		blatt.add(karte3);
		PlayingCard karte4 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		blatt.add(karte4);
		player.setHand(blatt);
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(karte5);
		
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
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.KING));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(blatt);
		
		assertEquals(Suit.LEAVES, player.ermittleTrumpffarbe());
	}

	@Test
	public void ermittleTrumpffarbeTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.KING));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(blatt);
		
		assertEquals(Suit.ACORNS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest3() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.KING));
		blatt.add(new PlayingCard(Suit.BELLS, Value.TEN));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.SIX));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(blatt);
		
		assertEquals(Suit.BELLS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void ermittleTrumpffarbeTest4() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.KING));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.TEN));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.SIX));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.NINE));
		blatt.add(new PlayingCard(Suit.LEAVES, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.SIX));
		player.setHand(blatt);
		
		assertEquals(Suit.HEARTS, player.ermittleTrumpffarbe());
	}
	
	@Test
	public void werteAugenTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		
		blatt.add(new PlayingCard(Suit.BELLS, Value.SIX));
		blatt.add(new PlayingCard(Suit.BELLS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.BELLS, Value.NINE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.TEN));
		blatt.add(new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.BELLS, Value.KING));
		blatt.add(new PlayingCard(Suit.BELLS, Value.DAUS));
		
		assertEquals(36, player.werteAugen(blatt));
	}

	@Test
	public void augenKarteTest1() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SIX);
		
		assertEquals(6, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		
		assertEquals(0, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		
		assertEquals(0, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.NINE);
		
		assertEquals(0, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.TEN);
		
		assertEquals(10, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		
		assertEquals(2, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		
		assertEquals(3, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.KING);
		
		assertEquals(4, player.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest9() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAUS);
		
		assertEquals(11, player.augenKarte(karte));
	}
}
