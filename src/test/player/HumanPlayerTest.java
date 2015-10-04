package test.player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.IController;
import main.Table;
import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.HumanPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;
import main.ui.IOutput;
import test.ControllerStub;
import test.gamevariety.NullGameStub;
import test.ui.OutputStub;


public class HumanPlayerTest {

	Table tisch = new Table();
	IOutput ausgabe = new OutputStub(null,null,null);
	IController controller = new ControllerStub(tisch, ausgabe);
	HumanPlayer mensch = new HumanPlayer("Peter", controller);

	@Before
	public void setUp() throws Exception {

		mensch.setHand(new ArrayList<PlayingCard>());
	}

	@Test
	public void hoerenTest1() {

		assertTrue(mensch.respond(20));
	}

	@Test
	public void hoerenTest2() {

		assertFalse(mensch.respond(23));
	}

	@Test
	public void sagenTest1() {

		assertTrue(mensch.bid(20));
	}

	@Test
	public void sagenTest2() {

		assertFalse(mensch.bid(23));
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

		mensch.setGameVariety(new GrandGame());
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.HEARTS, Value.UNDER_KNAVE);
		gespielteKarten[1] = new PlayingCard(Suit.HEARTS, Value.NINE);
		if (karte.equals(mensch.playCard(gespielteKarten))) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void spieleKarteTest2() {

		GameVariety nullspiel = new NullGameStub();
		boolean ergebnis = false;

		mensch.setGameVariety(nullspiel);
		PlayingCard karte = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		
		if (karte.equals(mensch.playCard(gespielteKarten))) {
				ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void handspielTest() {

		assertFalse(mensch.handgame());
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
		if (spielart.getGameVariety().equals(mensch.declareGame().getGameVariety())) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void farbeTest() {

		SuitGame farbspiel = new SuitGame(Suit.LEAVES);
		assertEquals(farbspiel.getTrumpSuit(), mensch.suit()
				.getTrumpSuit());
	}

	@Test
	public void reizlimitFestlegen() {

		assertEquals(23, mensch.setBidLimit());
	}

	@Test
	public void reizAgentTest() {

		assertFalse(mensch.agent());
	}
}
