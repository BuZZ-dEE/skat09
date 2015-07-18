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
		PlayingCard karte1 = new PlayingCard(Suit.HEARTS,Value.KING);
		PlayingCard karte2 =	new PlayingCard(Suit.BELLS,Value.SEVEN);
		PlayingCard karte3 =	new PlayingCard(Suit.ACORNS,Value.TEN);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = karte1;
		gespielteKarten[1] = karte2;
		gespielteKarten[2] = karte3;
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(3, spieler.getStiche().size());
		
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
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte1, spieler.getStiche().get(0));
		
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
		spieler.stichHinzufuegen(gespielteKarten);
		assertEquals(karte2, spieler.getStiche().get(1));
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
		
		spieler.setBlatt(blattest);
		
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
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[0], spieler.getAllegespieltenkarten().get(0));
	}
	
	@Test
	public void gespielteKartenHinzufuegenTest2() {
		
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		gespielteKarten[2] = new PlayingCard(Suit.LEAVES, Value.DAUS);
		spieler.gespielteKartenHinzufuegen(gespielteKarten);
		assertEquals(gespielteKarten[2], spieler.getAllegespieltenkarten().get(2));
	}
	
	@Test
	public void spitzenEinordnenTest() {
		boolean test = false;
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.getBlatt().add(
		new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
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
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
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
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(1, spieler.spitzenMit(1));
	}

	@Test
	public void testSpitzenMit2() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(2, spieler.spitzenMit(2));
	}

	@Test
	public void testSpitzenMit3() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(3, spieler.spitzenMit(3));
	}

	@Test
	public void testSpitzenMit4() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new GrandGame());
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(4, spieler.spitzenMit(4));
	}

	@Test
	public void testSpitzenMit5() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.spitzenEinordnen();
		assertEquals(5, spieler.spitzenMit(5));
	}

	@Test
	public void testSpitzenMit6() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.spitzenEinordnen();
		assertEquals(6, spieler.spitzenMit(6));
	}

	@Test
	public void testSpitzenMit7() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.spitzenEinordnen();
		assertEquals(7, spieler.spitzenMit(7));
	}

	@Test
	public void testSpitzenMit8() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(8, spieler.spitzenMit(8));
	}

	@Test
	public void testSpitzenMit9() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		spieler.spitzenEinordnen();
		assertEquals(9, spieler.spitzenMit(9));
	}

	@Test
	public void testSpitzenMit10() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		spieler.spitzenEinordnen();
		assertEquals(10, spieler.spitzenMit(10));
	}

	@Test
	public void testSpitzenMit11() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.SEVEN));
		spieler.spitzenEinordnen();
		assertEquals(11, spieler.spitzenMit(11));
	}

	@Test
	public void testSpitzenOhne() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(-1, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne2() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(-2, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne3() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(-3, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne4() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.spitzenEinordnen();
		assertEquals(-4, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne5() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.spitzenEinordnen();
		assertEquals(-5, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne6() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.spitzenEinordnen();
		assertEquals(-6, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne7() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(-7, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne8() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		spieler.spitzenEinordnen();
		assertEquals(-8, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne9() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
		spieler.spitzenEinordnen();
		assertEquals(-9, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne10() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.SEVEN));
		spieler.spitzenEinordnen();
		assertEquals(-10, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne11() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
		spieler.spitzenEinordnen();
		assertEquals(-11, spieler.spitzenOhne(0));
	}

	@Test
	public void testSpitzenOhne12() {
		spieler.setBlatt(new ArrayList<PlayingCard>());
		spieler.setSpielart(new SuitGame(Suit.BELLS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.HEARTS, Value.OVER_KNAVE));
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
				new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE));
		PlayingCard karte3 = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		spieler.getBlatt().add(karte3);
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.DAUS));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.TEN));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.KING));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.OVER_KNAVE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.NINE));
		spieler.getBlatt().add(
				new PlayingCard(Suit.BELLS, Value.EIGHT));
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
