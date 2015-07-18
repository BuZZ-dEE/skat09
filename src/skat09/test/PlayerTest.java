package skat09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.gamevariety.GrandGame;
import skat09.gamevariety.SuitGame;
import skat09.player.Granny;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;
import skat09.test.stub.NullGameStub2;


public class PlayerTest{

	private IPlayer spieler;
	ArrayList<PlayingCard> stiche = new ArrayList<PlayingCard>();
	
	
	@Before
	public void setUp(){
		String name_bsp = "Rainer Hohn";
		spieler = new Granny(name_bsp);
		stiche.clear();
		spieler.setStiche(stiche);
		spieler.getSpiele().clear();
	}
	
	@Test
	public void testSpieler() {
		assertEquals("Rainer Hohn",spieler.getName());
	}

	@Test
	public void stichHinzufuegenTest() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KOENIG);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SIEBEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.ZEHN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(3, spieler.getStiche().size());
		
	}
	
	@Test
	public void stichHinzufuegenTest2() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KOENIG);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SIEBEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.ZEHN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte1, spieler.getStiche().get(0));
		
	}
	
	@Test
	public void stichHinzufuegenTest3() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KOENIG);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SIEBEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.ZEHN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte2, spieler.getStiche().get(1));
	}
	
	@Test
	public void stichHinzufuegenTest4() {
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KOENIG);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SIEBEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.ZEHN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte3, spieler.getStiche().get(2));
		
	}
	
	@Test
	public void neuerEintragTest() {
		ArrayList<Integer> spiele;
		spiele=spieler.neuerEintrag(18);
		assertEquals(1, spiele.size());
	}
	
	@Test
	public void neuerEintragTest2() {
		ArrayList<Integer> spiele;
		spiele=spieler.neuerEintrag(18);
		assertEquals(18,(int) spiele.get(0));
	}
	
	@Test
	public void agentTest() {
		assertFalse(spieler.agent());
	}
	
	@Test
	public void reizlimitTest() {
		assertEquals(0, spieler.reizlimitFestlegen());
	}
	
	@Test
	public void test1sortiereBlatt(){
		final IGameVariety spiel = new GrandGame();
		IPlayer spieler = new Granny("Hallo");
		
		spieler.setSpielart(spiel);
		ArrayList<PlayingCard> blattest = new ArrayList<PlayingCard>();
		blattest.add(new PlayingCard(Suit.HEARTS,Value.KOENIG));
		blattest.add(new PlayingCard(Suit.BELLS,Value.SIEBEN));
		blattest.add(new PlayingCard(Suit.ACORNS,Value.ZEHN));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.ACHT));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.KOENIG));
		blattest.add(new PlayingCard(Suit.HEARTS,Value.ASS));
		blattest.add(new PlayingCard(Suit.HEARTS,Value.DAME));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.ZEHN));
		blattest.add(new PlayingCard(Suit.LEAVES,Value.ASS));
		blattest.add(new PlayingCard(Suit.ACORNS,Value.BUBE));
		
		spieler.setBlatt(blattest);
		
		ArrayList<PlayingCard> testproof = new ArrayList<PlayingCard>();
		
		testproof.add(new PlayingCard(Suit.BELLS,Value.SIEBEN));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.DAME));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.KOENIG));
		testproof.add(new PlayingCard(Suit.HEARTS,Value.ASS));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.ACHT));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.KOENIG));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.ZEHN));
		testproof.add(new PlayingCard(Suit.LEAVES,Value.ASS));
		testproof.add(new PlayingCard(Suit.ACORNS,Value.ZEHN));
		testproof.add(new PlayingCard(Suit.ACORNS,Value.BUBE));
		
		spieler.blattSortieren(spiel);
		boolean vergleiche = true;
		
		for(int i =0; i<10; i++){
			if (!testproof.get(i).equals(spieler.getBlatt().get(i))){
				vergleiche = false;
			}
		}
		assertTrue(vergleiche);
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest() {
		
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.DAME);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.ACHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.ASS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[0], spieler.getAllegespieltenkarten().get(0));
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest2() {
		
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.DAME);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.ACHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.ASS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[2], spieler.getAllegespieltenkarten().get(2));
	}
	
	@Test
	public void spitzenEinordnenTest() {
		boolean test = false;
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.getBlatt().add(
		new PlayingCard(Suit.ACORNS, Value.BUBE));
		IGameVariety spielart = new GrandGame();
		spieler.setSpielart(spielart);
		spieler.spitzenEinordnen();
			test = true;
		
		assertTrue(test);
	}
	
	@Test
	public void testSpitzenZahl() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.setSpielart(new GrandGame());
		spieler.spitzenEinordnen();
		assertEquals(1, spieler.spitzenZahl());
	}
	
	@Test
	public void testSpitzenZahl2() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenZahl());
	}

	@Test
	public void testSpitzenMit() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(1, spieler.spitzenMit(1));
	}

	@Test
	public void testSpitzenMit2() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(2, spieler.spitzenMit(2));
	}

	@Test
	public void testSpitzenMit3() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(3, spieler.spitzenMit(3));
	}

	@Test
	public void testSpitzenMit4() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(4, spieler.spitzenMit(4));
	}

	@Test
	public void testSpitzenMit5() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.spitzenEinordnen();
		assertEquals(5, spieler.spitzenMit(5));
	}

	@Test
	public void testSpitzenMit6() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.spitzenEinordnen();
		assertEquals(6, spieler.spitzenMit(6));
	}

	@Test
	public void testSpitzenMit7() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.spitzenEinordnen();
		assertEquals(7, spieler.spitzenMit(7));
	}

	@Test
	public void testSpitzenMit8() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.spitzenEinordnen();
		assertEquals(8, spieler.spitzenMit(8));
	}

	@Test
	public void testSpitzenMit9() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NEUN));
		spieler.spitzenEinordnen();
		assertEquals(9, spieler.spitzenMit(9));
	}

	@Test
	public void testSpitzenMit10() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NEUN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ACHT));
		spieler.spitzenEinordnen();
		assertEquals(10, spieler.spitzenMit(10));
	}

	@Test
	public void testSpitzenMit11() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NEUN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ACHT));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.SIEBEN));
		spieler.spitzenEinordnen();
		assertEquals(11, spieler.spitzenMit(11));
	}

	@Test
	public void testSpitzenOhne() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-1, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne2() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-2, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne3() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.spitzenEinordnen();
		assertEquals(-3, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne4() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.spitzenEinordnen();
		assertEquals(-4, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne5() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.spitzenEinordnen();
		assertEquals(-5, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne6() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.spitzenEinordnen();
		assertEquals(-6, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne7() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-7, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne8() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NEUN));
		spieler.spitzenEinordnen();
		assertEquals(-8, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne9() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ACHT));
		spieler.spitzenEinordnen();
		assertEquals(-9, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne10() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.SIEBEN));
		spieler.spitzenEinordnen();
		assertEquals(-10, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne11() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne12() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.DAME));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenOhne(0));
	} 
	
	@Test
	public void testSpitzenOhne13() {
		spieler.setSpielart(new GrandGame());
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.spitzenEinordnen();
		assertEquals(-4, spieler.spitzenOhne(0));
	}
	
	@Test
	public void spielbarteKartenTest() {
		spieler.setSpielart(new NullGameStub2());
		
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.BUBE));
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.BUBE);
		spieler.getBlatt().add(karte3);
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.BUBE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ASS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ZEHN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KOENIG));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAME));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NEUN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.ACHT));
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		ArrayList<PlayingCard> erwartet = new ArrayList<PlayingCard>();
		erwartet.add(karte3);
		assertEquals(erwartet, spieler.spielbareKarten(gespielteKarten));
	}
	
	
	@Test
	public void equalsTest() {
		
		IPlayer spieler2 = new Granny("Rainer Hohn");
		assertTrue(spieler.equals(spieler2));
	}
	@Test
	public void farbenEinordnenTest() {
		
		
	}

}
