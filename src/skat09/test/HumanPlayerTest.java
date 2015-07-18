package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.Table;
import skat09.gamevariety.GameVariety;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.SuitGame;
import skat09.player.HumanPlayer;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IGameVariety;
import skat09.test.stub.OutputStub;
import skat09.test.stub.ControllerStub;
import skat09.test.stub.NullGameStub;


public class HumanPlayerTest {

	Table tisch = new Table();
	IOutput ausgabe = new OutputStub(null,null,null);
	IController controller = new ControllerStub(tisch, ausgabe);
	HumanPlayer mensch = new HumanPlayer("Peter", controller);

	@Before
	public void setUp() throws Exception {

		mensch.setBlatt(new ArrayList<PlayingCard>());
	}

	@Test
	public void hoerenTest1() {

		assertTrue(mensch.hoeren(20));
	}

	@Test
	public void hoerenTest2() {

		assertFalse(mensch.hoeren(23));
	}

	@Test
	public void sagenTest1() {

		assertTrue(mensch.sagen(20));
	}

	@Test
	public void sagenTest2() {

		assertFalse(mensch.sagen(23));
	}

	@Test
	public void drueckenTest() {

		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(Suit.ACORNS, Value.OVER_KNAVE);
		skat[1] = new PlayingCard(Suit.ACORNS, Value.KING);
		skat[2] = new PlayingCard(Suit.ACORNS, Value.DAUS);
		assertArrayEquals(skat, mensch.druecken(skat));
	}

	@Test
	public void drueckenTest2() {

		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		skat[1] = new PlayingCard(Suit.BELLS, Value.EIGHT);
		skat[2] = null;
		assertArrayEquals(skat, mensch.druecken(skat));
	}

	@Test
	public void spieleKarteTest() {

		boolean ergebnis = false;

		mensch.setSpielart(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.HEARTS, Value.NINE);
		if (karte.equals(mensch.spieleKarte(gespielteKarten))) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void spieleKarteTest2() {

		GameVariety nullspiel = new NullGameStub();
		boolean ergebnis = false;

		mensch.setSpielart(nullspiel);
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		if (karte.equals(mensch.spieleKarte(gespielteKarten))) {
				ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void handspielTest() {

		assertFalse(mensch.handspiel());
	}

	@Test
	public void ouvertTest() {

		assertTrue(mensch.ouvert());
	}

	@Test
	public void schneiderTest() {

		assertTrue(mensch.schneider());
	}

	@Test
	public void schwarzTest() {

		assertFalse(mensch.schwarz());
	}

	@Test
	public void spielAnsagenTest() {

		boolean ergebnis = false;

		IGameVariety spielart = new GrandGame();
		if (spielart.getSpielart().equals(mensch.spielAnsagen().getSpielart())) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void farbeTest() {

		SuitGame farbspiel = new SuitGame(Suit.LEAVES);
		assertEquals(farbspiel.getTrumpffarbe(), mensch.farbe()
				.getTrumpffarbe());
	}

	@Test
	public void reizlimitFestlegen() {

		assertEquals(23, mensch.reizlimitFestlegen());
	}

	@Test
	public void reizAgentTest() {

		assertFalse(mensch.agent());
	}
}
