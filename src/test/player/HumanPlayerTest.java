package test.player;

import main.IController;
import main.Table;
import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.SuitGame;
import main.player.HumanPlayer;
import main.playingcard.PlayingCard;
import main.ui.IOutput;
import org.junit.Before;
import org.junit.Test;
import test.ControllerStub;
import test.gamevariety.NullGameStub;
import test.ui.OutputStub;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class HumanPlayerTest {

	Table table = new Table();
	IOutput output = new OutputStub(null,null,null);
	IController controller = new ControllerStub(table, output);
	HumanPlayer humanPlayer = new HumanPlayer("Peter", controller);

	@Before
	public void setUp() throws Exception {

		humanPlayer.setHand(new ArrayList<PlayingCard>());
	}

	@Test
	public void respondTest1() {

		assertTrue(humanPlayer.respond(20));
	}

	@Test
	public void respondTest2() {

		assertFalse(humanPlayer.respond(23));
	}

	@Test
	public void bidTest1() {

		assertTrue(humanPlayer.bid(20));
	}

	@Test
	public void bidTest2() {

		assertFalse(humanPlayer.bid(23));
	}

	@Test
	public void drueckenTest() {

		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.OVER_KNAVE);
		skat[1] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.KING);
		skat[2] = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.DAUS);
		assertArrayEquals(skat, humanPlayer.druecken(skat));
	}

	@Test
	public void drueckenTest2() {

		PlayingCard[] skat = new PlayingCard[3];
		skat[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);
		skat[1] = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
		skat[2] = null;
		assertArrayEquals(skat, humanPlayer.druecken(skat));
	}

	@Test
	public void playCardTest() {

		boolean result = false;

		humanPlayer.setGameVariety(new GrandGame());
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard[] playedCards = new PlayingCard[3];
		playedCards[0] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.UNDER_KNAVE);
		playedCards[1] = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.NINE);
		if (card.equals(humanPlayer.playCard(playedCards))) {

			result = true;
		}
		assertTrue(result);
	}
	
	@Test
	public void playCardTest2() {

		GameVariety nullGame = new NullGameStub();
		boolean result = false;

		humanPlayer.setGameVariety(nullGame);
		PlayingCard card = new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE);
		PlayingCard[] playedCards = new PlayingCard[3];
		
		if (card.equals(humanPlayer.playCard(playedCards))) {
				result = true;
		}
		assertTrue(result);
	}

	@Test
	public void handGameTest() {

		assertFalse(humanPlayer.handgame());
	}

	@Test
	public void ouvertTest() {

		assertTrue(humanPlayer.ouvert());
	}

	@Test
	public void schneiderTest() {

		assertTrue(humanPlayer.schneider());
	}

	@Test
	public void schwarzTest() {

		assertFalse(humanPlayer.schwarz());
	}

	@Test
	public void annouceGameTest() {

		boolean result = false;

		GrandGame gameVariety = new GrandGame();
		if (gameVariety.getGameVariety().equals(humanPlayer.declareGame().getGameVariety())) {

			result = true;
		}
		assertTrue(result);
	}

	@Test
	public void suitTest() {

		SuitGame suitGame = new SuitGame(PlayingCard.Suit.LEAVES);
		assertEquals(suitGame.getTrumpSuit(), humanPlayer.suit()
				.getTrumpSuit());
	}

	@Test
	public void setBidLimitTest() {

		assertEquals(23, humanPlayer.setBidLimit());
	}

	@Test
	public void biddingAgentTest() {

		assertFalse(humanPlayer.agent());
	}
}
