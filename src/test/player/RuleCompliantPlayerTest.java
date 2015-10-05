package test.player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.player.RuleCompliantPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


public class RuleCompliantPlayerTest {
	
	ArrayList<PlayingCard> blatt = new ArrayList<PlayingCard>();
	PlayingCard spielkarte1;
	PlayingCard spielkarte2;
	PlayingCard spielkarte3;
	PlayingCard spielkarte4;
	RuleCompliantPlayer spieler = new RuleCompliantPlayer("Mimi");
	PlayingCard[] gespielteKarten = new PlayingCard[3];
	SuitGame spiel = new SuitGame(Suit.HEARTS);
	
	@Before
	public void setUp() {
		spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
		spielkarte2 = new PlayingCard(Suit.HEARTS, Value.EIGHT);
		spielkarte3 = new PlayingCard(Suit.LEAVES, Value.NINE);
		spielkarte4 = new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE);
		
		blatt.add(spielkarte4);
		blatt.add(spielkarte3);
		gespielteKarten[0] = spielkarte1;
		
		spieler.setHand(blatt);
		spieler.setGameVariety(spiel);
	}

//	@Test
//	public void testSpieleKarte() {
//		assertEquals(spielkarte4, spieler.playCard(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte2() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		assertEquals(spielkarte3, spieler.playCard(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte3() {
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.playCard(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte4() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.playCard(gespielteKarten));
//	}
	
	//Der regelkonforme Spieler spielt eine zufaellige Karte
	//aus seinem Blatt. Es wird getestet, ob die gespielte
	//Karte vorher im Blatt enthalten war und daraufhin auch 
	//entfernt wurde.
	@SuppressWarnings("unchecked")
	@Test
	public void testSpieleKarte() {
	
		boolean testErfolgreich = false;
		
		blatt.clear();
		blatt.add(new PlayingCard(Suit.HEARTS, Value.EIGHT));
		blatt.add(new PlayingCard(Suit.BELLS, Value.EIGHT));
		ArrayList<PlayingCard> altesBlatt = new ArrayList<PlayingCard>();
		altesBlatt = (ArrayList<PlayingCard>) blatt.clone();
		spieler.setHand(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN); 
		PlayingCard gespielteKarte = new PlayingCard(null,null);
		gespielteKarte = spieler.playCard(gespielteKarten);
		blatt = spieler.getHand();
		
		if(altesBlatt.contains(gespielteKarte)) {
			
			if (!blatt.contains(gespielteKarte)) {
			
				testErfolgreich = true;
			}
		}
		assertTrue(testErfolgreich);
	}
	
	@Test
	public void testRegelkonformerSpieler() {
		assertEquals("Hans", new RuleCompliantPlayer("Hans").getName());
	}


	@Test
	public void drueckenTest() {
		PlayingCard[] skat = new PlayingCard[2];
		assertArrayEquals(null, spieler.druecken(skat));
	}
	
	@Test
	public void handspielTest() {
		assertTrue(spieler.handgame());
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
	public void ouvertTest() {
		assertFalse(spieler.ouvert());
	}
	
	@Test
	public void spielAnsagenTest() {
		boolean test = false;
		if (spieler.declareGame() instanceof NullGame) {
			test = true;
		}
		assertTrue(test);
	}
	
	@Test
	public void hoerenTest() {
		assertTrue(spieler.respond(0));
	}
	
	@Test
	public void hoerenTest2() {
		assertTrue(spieler.respond(18));
	}
	
	@Test
	public void hoerenTest3() {
		assertTrue(spieler.respond(20));
	}
	
	@Test
	public void hoerenTest4() {
		assertFalse(spieler.respond(35));
	}
	
	@Test
	public void sagenTest() {
		assertTrue(spieler.bid(0));
	}
	
	@Test
	public void sagenTest2() {
		assertTrue(spieler.bid(18));
	}
	
	@Test
	public void farbeTest() {
		assertEquals(null, spieler.suit());
	}
}
