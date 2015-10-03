package test;

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
import main.player.Position;
import main.player.RuleCompliantPlayer;
import main.player.SmartPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;
import test.interfaces.IPlayer;


public class SmartPlayerTest {

	SmartPlayer spieler = new SmartPlayer("Max");
	ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
	PlayingCard spielkarte1;
	PlayingCard spielkarte2;
	PlayingCard spielkarte3;
	PlayingCard spielkarte4;
	PlayingCard spielkarte5;
	PlayingCard[] gespielteKarten = new PlayingCard[3];
	SuitGame spiel = new SuitGame(Suit.HEARTS);
	@Before
	public void setUp() {
		spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		blatt.add(spielkarte5);
		gespielteKarten[0] = spielkarte1;
		
		spieler.setHand(blatt);
		spieler.setGameVariety(spiel);
	}
	
	@Test
	public void testSchlauerSpieler() {
		assertEquals("Max", new SmartPlayer("Max").getName());
	}
	
	@Test
	public void testSpieleKarte() {
		
	}
	
	@Test
	public void testalleinspielerRauskommenGrand() {
		assertEquals(Value.UNDER_KNAVE, spieler.alleinspielerRauskommenGrand(gespielteKarten).getValue());
	}
	
	@Test
	public void testWert() {
		boolean flag = false;
		ArrayList<PlayingCard> wert = spieler.kartenEinesWertes(blatt, Value.UNDER_KNAVE);
		if (wert.size() == 2) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	public void testWert2() {
		boolean flag = false;
		ArrayList<PlayingCard> wert = spieler.kartenEinesWertes(blatt, Value.UNDER_KNAVE);
		for (PlayingCard karte : wert) {
			if (karte.equals(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	

	@Test
	public void testWert3() {
		boolean flag = false;
		ArrayList<PlayingCard> wert = spieler.kartenEinesWertes(blatt, Value.UNDER_KNAVE);
		for (PlayingCard karte : wert) {
			if (karte.equals(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE))) {
				flag = true;
			}
		}
		assertTrue(flag);
	}
	
	@Test
	public void rauskommenNullTest() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIsDeclarer(true);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		assertEquals(spielkarte1, spieler.rauskommenNull(gespielteKarten));
	}
	
	@Test
	public void rauskommenNullTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.MITTELHAND);
		spieler.setTeammate(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	
	@Test
	public void rauskommenNullTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		spieler.setTeammate(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	@Test
	public void rauskommenNullTest5() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("ho");
		mate.setPosition(Position.HINTERHAND);
		spieler.setTeammate(mate);
		spieler.setPosition(Position.VORHAND);
		
		boolean erfolgreich = false;
		if(blatt.contains(spieler.rauskommenNull(gespielteKarten))) {
			
			erfolgreich = true;
		}
		assertTrue(erfolgreich);
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIsDeclarer(true);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		IPlayer gegner = new Granny("gegen");
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		gespielteKarten[0].setOwner(mate);
		
		assertEquals(spielkarte2, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenNullTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		IPlayer gegner = new Granny("gegen");
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals(spielkarte2, spieler.alsZweiterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIsDeclarer(true);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.ACORNS, Value.EIGHT);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard spielkarte8 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setTeammate(mate);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer gegnger = new Granny("adversary");
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		gespielteKarten[0].setOwner(gegnger);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsDritterKartenSpielenNullTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte8 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setTeammate(mate);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer gegnger = new Granny("adversary");
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.KING);
		gespielteKarten[0].setOwner(gegnger);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}

	@Test
	public void alsDritterKartenSpielenNullTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte8 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte8);
		IPlayer mate = new RuleCompliantPlayer("mine");
		spieler.setTeammate(mate);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new NullGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer gegnger = new Granny("adversary");
		gespielteKarten[0] = new PlayingCard(Suit.ACORNS, Value.KING);
		gespielteKarten[0].setOwner(mate);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.NINE);
		gespielteKarten[1].setOwner(gegnger);
		
		assertEquals(spielkarte5, spieler.alsDritterKarteSpielenNull(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest1() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		spieler.setIsDeclarer(true);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(mate);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest3() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenGrandTest4() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		spieler.setDeck(deck);
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals( spielkarte1, spieler.alsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		spieler.setIsDeclarer(true);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(mate);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(gegner);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenGrandTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals( spielkarte1, spieler.alsDritterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void rauskommenTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setIsDeclarer(true);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.rauskommen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		mate.setPosition(Position.MITTELHAND);
		IPlayer gegner = new Granny("adversary");
		gegner.setPosition(Position.HINTERHAND);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean erfolg = false;
		if (spielkarte3.getSuit().equals((spieler.rauskommen(gespielteKarten)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void rauskommenTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.ACORNS, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setPosition(Position.VORHAND);
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		mate.setPosition(Position.HINTERHAND);
		IPlayer gegner = new Granny("adversary");
		gegner.setPosition(Position.MITTELHAND);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean erfolg = false;
		if (spielkarte3.getSuit().equals((spieler.rauskommen(gespielteKarten)).getSuit())) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setIsDeclarer(true);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.alsZweiterKarteSpielen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsZweiterKarteSpielenTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(mate);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(gegner);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsZweiterKarteSpielenTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		IPlayer mate = new Granny("mate");
		IPlayer gegner = new Granny("adversary");
		spieler.setTeammate(mate);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.TEN);
		gespielteKarten[0].setOwner(gegner);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte1, spieler.alsZweiterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setIsDeclarer(true);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.alsDritterKarteSpielen(gespielteKarten))) {
			erfolg = true;
		}
		assertTrue(erfolg);
	}
	
	@Test
	public void alsDritterKarteSpielenTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte3, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		IPlayer wicht = new Granny("wicht");
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[0].setOwner(wicht);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1].setOwner(mate);
		
		assertEquals(spielkarte1, spieler.alsDritterKarteSpielen(gespielteKarten));
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		PlayingCard spielkarte6 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		blatt.add(spielkarte6);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest3() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getValue() == Value.UNDER_KNAVE) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest4() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.DAUS);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getValue() == Value.DAUS) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest5() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.ACORNS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (blatt.contains(gespielt)) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest6() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.TEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.DAUS);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.DAUS);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
		if (gespielt.getValue() == Value.TEN) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void alleinspielerRauskommenGrandTest7() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.SEVEN);
		PlayingCard spielkarte6 = new PlayingCard(Suit.LEAVES, Value.EIGHT);
		PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.getAllPlayedCards().add(spielkarte6);
		spieler.getAllPlayedCards().add(spielkarte7);
		spieler.setHand(blatt);
		spieler.setAnfangsblatt(blatt);
		spieler.getAnfangsBlatt().add(spielkarte6);
		spieler.getAnfangsBlatt().add(spielkarte7);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		boolean ergebnis = false;
		PlayingCard gespielt = spieler.alleinspielerRauskommenGrand(gespielteKarten);
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
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setIsDeclarer(true);
		spieler.setDeck(deck);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(Value.UNDER_KNAVE, spieler.rauskommenGrand(gespielteKarten).getValue());
	}
	
	@Test
	public void rauskommenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		IPlayer gegner = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		spieler.setTeammate(mate);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spieler.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (!blatt.contains(spieler.rauskommenGrand(gespielteKarten))) {
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
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.KING);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.NINE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		IPlayer gegner = new Granny("adversary");
		IPlayer mate = new Granny("mate");
		spieler.setTeammate(mate);
		spieler.setTeammate(mate);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spieler.setPosition(Position.VORHAND);
		mate.setPosition(Position.MITTELHAND);
		gegner.setPosition(Position.HINTERHAND);
		
		boolean erfolg = false;
		if (Suit.ACORNS == spieler.rauskommenGrand(gespielteKarten).getSuit()) {
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
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alleinspieleralsZweiteKarteSpielenGrandTest2() {
		
		Table tisch = new Table();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		tisch.createDeck();
		deck = tisch.getDeck();
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setDeck(deck);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten));
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest1() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.KING);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.KING);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));	
	}
	
	@Test
	public void alleinspieleralsDritterKarteSpielenGrandTest2() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new GrandGame());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[1] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		assertEquals(spielkarte1, 
				spieler.alleinspieleralsDritterKarteSpielenGrand(gespielteKarten));	
	}
	
	@Test
	public void rauskommenRamsch() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new Ramsch());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		assertEquals(spielkarte1, 
				spieler.rauskommenRamsch(gespielteKarten));	
	}
	
	@Test
	public void alsZweiterKarteSpielenRamschTest() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new Ramsch());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		
		assertEquals(spielkarte1, 
				spieler.alsZweiterKarteSpielenRamsch(gespielteKarten));
	}
	
	@Test
	public void alsDritterKarteSpielenRamschTest() {
		
		PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		PlayingCard spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		PlayingCard spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard spielkarte5 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(spielkarte1);
		blatt.add(spielkarte2);
		blatt.add(spielkarte3);
		blatt.add(spielkarte4);
		blatt.add(spielkarte5);
		spieler.setHand(blatt);
		spieler.setGameVariety(new Ramsch());
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.NINE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.TEN);
		
		assertEquals(spielkarte1, 
				spieler.alsDritterKarteSpielenRamsch(gespielteKarten));
	}
	
	@Test
	public void gegnerMoeglicheSpielbareKartenTest() {
		
		Table tisch = new Table();
		tisch.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		//Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		spieler.setDeck(deck);
		spieler.setIsDeclarer(true);
		spieler.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			spieler.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> alleGespielteKarten = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = deck.remove(0);
		spieler.setSkat(new ArrayList<PlayingCard>(Arrays.asList(gespielteKarten)));
		
		ArrayList<PlayingCard> ergebnisKarten = new ArrayList<PlayingCard>();
		for (int k = 0; k < deck.size(); k++) {
			
			if(spieler.getGameVariety().
					checkedPlayedCards(deck, gespielteKarten, deck.get(k))) {
				
				ergebnisKarten.add(deck.get(k));
			}
		}
		
		assertEquals(ergebnisKarten, spieler.gegnerMoeglicheSpielbareKarten(gespielteKarten));
	}
	
	@Test
	public void moeglicheGegnerKarteTest() {
		
		Table tisch = new Table();
		tisch.createDeck();
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		Collections.shuffle(deck);
		deck.addAll(tisch.getDeck());
		spieler.setDeck(deck);
		spieler.setIsDeclarer(true);
		spieler.setHand(new ArrayList<PlayingCard>());
		for (int j = 0; j < 6; j++) {
			spieler.getHand().add(deck.remove(0));
		}
		ArrayList<PlayingCard> alleGespielteKarten = new ArrayList<PlayingCard>();
		for (int i = 0; i < 12; i++) {
			
			alleGespielteKarten.add(deck.remove(0));
		}
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = deck.remove(0);
		spieler.setSkat(new ArrayList<PlayingCard>(Arrays.asList(gespielteKarten)));
	
		assertEquals(deck, 
				spieler.moeglicheGegnerKarten(alleGespielteKarten, gespielteKarten));
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
		int zufallszahl = spieler.zufallszahl(blatt);
		
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
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.KING);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard hkarte = new PlayingCard(Suit.BELLS, Value.DAUS);
		boolean ergebnis = false;
		if (hkarte.equals(spieler.naechstHoehereKarte(Suit.BELLS, karte))) {
			
			ergebnis = true;
		}
		
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstHoehereKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAUS);
		
		assertEquals(null, spieler.naechstHoehereKarte(Suit.BELLS, karte));
	}
	
	@Test
	public void naechstHoehereKarteNeunTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteNeunTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteNeun(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechsteHoehereKarteKoenigTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteKoenig(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.LEAVES));
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.DAUS);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstHoehereKarteZehnTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.LEAVES, Value.OVER_KNAVE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstHoehereKarteZehn(Suit.LEAVES))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteTest1() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SIX);
		
		assertEquals(null, spieler.naechstNiedrigereKarte(Suit.BELLS, karte));
	}
	
	@Test
	public void naechstNiedrigereKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.SEVEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.SIX);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.NINE);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.KING);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.KING);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void naechstNiedrigereKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.BELLS, Value.DAUS);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.TEN);
		boolean ergebnis = false;
		if (karte2.equals(spieler.naechstNiedrigereKarte(Suit.BELLS, karte))) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	@Test
	public void naechstNiedrigereKarteDameTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteDameTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteDame(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteZehnTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteZehn(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest1() {
		
		spieler.setGameVariety(new SuitGame(Suit.BELLS));
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.TEN);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
			erfolgreich = true;
		}
		
		assertTrue(erfolgreich);
	}
	
	@Test
	public void naechstNiedrigereKarteAssTest3() {
		
		spieler.setGameVariety(new NullGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.KING);
		
		boolean erfolgreich = false;
		if (karte.equals(spieler.naechstNiedrigereKarteAss(Suit.ACORNS))) {
			
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
		assertEquals(erwartet, spieler.kartenEinesWertes(blatt, Value.DAUS));
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
		
		assertEquals(karo, spieler.kartenEinerFarbe(blatt, Suit.BELLS));
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
		
		assertEquals(herz, spieler.kartenEinerFarbe(blatt, Suit.HEARTS));
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
		
		assertEquals(pik, spieler.kartenEinerFarbe(blatt, Suit.LEAVES));
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
		
		assertEquals(kreuz, spieler.kartenEinerFarbe(blatt, Suit.ACORNS));
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
		
		assertEquals(karte1, spieler.hoechsteSpielbareKarte(spielbareKarten));
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
		
		assertEquals(karte1, spieler.niedrigsteSpielbareKarte(spielbareKarten));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest1() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		spieler.setGameVariety(new GrandGame());
		
		assertEquals(karte1, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest2() {
		
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		spieler.setGameVariety(new NullGame());
		
		assertEquals(karte2, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void hoechsteSpielbareKarteBestimmenTest3() {
		
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		spieler.setGameVariety(new NullGame());
		
		assertEquals(karte1, 
				spieler.hoechsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest1() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.TEN);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		
		assertEquals(karte2, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest2() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.EIGHT);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		assertEquals(karte1, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void niedrigsteSpielbareKarteBestimmenTest3() {
		
		spieler.setGameVariety(new GrandGame());
		PlayingCard karte1 = new PlayingCard(Suit.BELLS, Value.NINE);
		PlayingCard karte2 = new PlayingCard(Suit.ACORNS, Value.NINE);
		
		assertEquals(karte1, 
				spieler.niedrigsteSpielbareKarteBestimmen(karte1, karte2));
	}
	
	@Test
	public void drueckenTest() {
		
		PlayingCard[] skat = new PlayingCard[3];
		assertArrayEquals(null, spieler.druecken(skat));
	}
	
	@Test
	public void handspielTest() {
		
		assertTrue(spieler.handgame());
	}
	
	@Test
	public void ouvertTest() {
		
		assertFalse(spieler.ouvert());
	}
	
	@Test
	public void schneiderTest() {
		
		assertFalse(spieler.schneider());
	}
	
	@Test
	public void schwarzTest() {
		
		assertFalse(spieler.schwarz());
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
		spieler.setHand(blatt);
		
		assertEquals(new GrandGame().getGameVariety(), 
				spieler.declareGame().getGameVariety());
	}
	
	@Test
	public void hoerenTest() {
		
		spieler.setMaxReizwert(23);
		
		assertTrue(spieler.respond(18));
	}
	
	@Test
	public void hoerenTest2() {
		
		spieler.setMaxReizwert(23);
		
		assertFalse(spieler.respond(26));
	}
	
	@Test
	public void sagenTest() {
		
		
		spieler.setMaxReizwert(23);
		
		assertTrue(spieler.bid(18));
	}
	
	@Test
	public void sagenTest2() {
		
		
		spieler.setMaxReizwert(23);
		
		assertFalse(spieler.bid(26));
	}
	
	@Test
	public void farbeTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.HEARTS, Value.DAUS));
		spieler.setHand(blatt);
		
		assertEquals(new SuitGame(Suit.HEARTS).getGameVariety(), 
				spieler.suit().getGameVariety());
	}
	
	@Test
	public void agentTest() {
		
		assertFalse(spieler.agent());
	}
	
	@Test
	public void reizlimitFestlegenTest() {
		
		assertEquals(0, spieler.setBidLimit());
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
		spieler.setHand(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(23, spieler.getMaxReizwert());
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
		spieler.setHand(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(24, spieler.getMaxReizwert());
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
		spieler.setHand(blatt);
		spieler.bestimmeMaxReizwert();
		assertEquals(33, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertFarbeTest() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		blatt.add(new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE));
		blatt.add(new PlayingCard(Suit.ACORNS, Value.KING));
		spieler.setHand(blatt);
		spieler.maxReizwertFarbe(1);
		assertEquals(24, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertGrandTest() {
		
		spieler.maxReizwertGrand(1);
		assertEquals(48, spieler.getMaxReizwert());
	}
	
	@Test
	public void maxReizwertNullTest() {
		
		spieler.maxReizwertNull();
		assertEquals(23, spieler.getMaxReizwert());
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
		spieler.setHand(blatt);
		
		assertEquals(6, spieler.ermittleSpitzen(new SuitGame(Suit.BELLS)));
	}
	
	@Test
	public void ermittleSpitzenTest2() {
		
		ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
		PlayingCard karte1 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard karte2 = new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE);
		blatt.add(karte1);
		blatt.add(karte2);
		spieler.setHand(blatt);
		
		assertEquals(2, spieler.ermittleSpitzen(new GrandGame()));
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
		
		assertEquals(12, spieler.spitzenZaehlen(spitzen));
	}
	
	@Test
	public void spitzenZaehlenTest2() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[4] = new PlayingCard(Suit.HEARTS, Value.DAUS);
		
		assertEquals(4, spieler.spitzenZaehlen(spitzen));
	}
	@Test
	public void ohneTest1() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[12] = new PlayingCard(Suit.LEAVES, Value.SIX);
		assertEquals(12, spieler.ohne(spitzen));
	}
	
	@Test
	public void ohneTest2() {
		
		PlayingCard[] spitzen = new PlayingCard[13];
		spitzen[3] = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
		assertEquals(3, spieler.ohne(spitzen));
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
		
		assertEquals(12, spieler.mit(spitzen));
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
		
		assertEquals(3, spieler.mit(spitzen));
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
		spieler.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = spieler.farbeSpitzen(new SuitGame(Suit.BELLS));
		
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
		
		spieler.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = spieler.grandSpitzen(new GrandGame());
		
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
		
		spieler.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = spieler.grandSpitzen(new GrandGame());
		
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
		
		spieler.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = spieler.grandSpitzen(new GrandGame());
		
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
		
		spieler.setHand(blatt);
		
		PlayingCard[] spielerSpitzen = spieler.grandSpitzen(new GrandGame());
		
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
		spieler.setHand(blatt);
		
		assertEquals(new NullGame().getGameVariety(), 
				spieler.bestimmeSpielart().getGameVariety());
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
		spieler.setHand(blatt);
		
		assertEquals(new GrandGame().getGameVariety(), 
				spieler.bestimmeSpielart().getGameVariety());
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
		spieler.setHand(blatt);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				spieler.bestimmeSpielart().getGameVariety());
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
		spieler.setHand(blatt);
		
		assertEquals(new SuitGame(Suit.LEAVES).getGameVariety(), 
				spieler.bestimmeSpielart().getGameVariety());
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
		spieler.setHand(blatt);
		ArrayList<PlayingCard> kreuz = new ArrayList<PlayingCard>();
		kreuz.add(karte1);
		kreuz.add(karte2);
		kreuz.add(karte3);
		kreuz.add(karte4);
		
		assertEquals(kreuz, spieler.ermittleKurzeLangeFarbe(true));
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
		spieler.setHand(blatt);
		ArrayList<PlayingCard> pik = new ArrayList<PlayingCard>();
		pik.add(karte5);
		
		assertEquals(pik, spieler.ermittleKurzeLangeFarbe(false));
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
		
		assertEquals(karo, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(herz, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(pik, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(kreuz, spieler.ermittleKurzeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(karo, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(herz, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(pik, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
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
		
		assertEquals(kreuz, spieler.ermittleLangeFarbe(karo, herz, pik, kreuz));
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
		spieler.setHand(blatt);
		
		assertEquals(Suit.LEAVES, spieler.ermittleTrumpffarbe());
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
		spieler.setHand(blatt);
		
		assertEquals(Suit.ACORNS, spieler.ermittleTrumpffarbe());
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
		spieler.setHand(blatt);
		
		assertEquals(Suit.BELLS, spieler.ermittleTrumpffarbe());
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
		spieler.setHand(blatt);
		
		assertEquals(Suit.HEARTS, spieler.ermittleTrumpffarbe());
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
		
		assertEquals(36, spieler.werteAugen(blatt));
	}

	@Test
	public void augenKarteTest1() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SIX);
		
		assertEquals(6, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest2() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.SEVEN);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest3() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest4() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.NINE);
		
		assertEquals(0, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest5() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.TEN);
		
		assertEquals(10, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest6() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		
		assertEquals(2, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest7() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE);
		
		assertEquals(3, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest8() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.KING);
		
		assertEquals(4, spieler.augenKarte(karte));
	}
	
	@Test
	public void augenKarteTest9() {
		
		PlayingCard karte = new PlayingCard(Suit.HEARTS, Value.DAUS);
		
		assertEquals(11, spieler.augenKarte(karte));
	}
}
