package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.gamevariety.NullGame;
import skat09.gamevariety.SuitGame;
import skat09.player.RuleCompliantPlayer;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;


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
		
		spieler.setBlatt(blatt);
		spieler.setSpielart(spiel);
	}

//	@Test
//	public void testSpieleKarte() {
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte2() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		assertEquals(spielkarte3, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte3() {
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
//	}
//	
//	@Test
//	public void testSpieleKarte4() {
//		blatt.clear();
//		blatt.add(spielkarte3);
//		blatt.add(spielkarte4);
//		gespielteKarten[0] = spielkarte2;
//		assertEquals(spielkarte4, spieler.spieleKarte(gespielteKarten));
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
		spieler.setBlatt(blatt);
		PlayingCard[] gespielteKarten = new PlayingCard[3];
		gespielteKarten[0] = new PlayingCard(Suit.BELLS, Value.SEVEN); 
		PlayingCard gespielteKarte = new PlayingCard(null,null);
		gespielteKarte = spieler.spieleKarte(gespielteKarten);
		blatt = spieler.getBlatt();
		
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
		assertTrue(spieler.handspiel());
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
		if (spieler.spielAnsagen() instanceof NullGame) {
			test = true;
		}
		assertTrue(test);
	}
	
	@Test
	public void hoerenTest() {
		assertTrue(spieler.hoeren(0));
	}
	
	@Test
	public void hoerenTest2() {
		assertTrue(spieler.hoeren(18));
	}
	
	@Test
	public void hoerenTest3() {
		assertTrue(spieler.hoeren(20));
	}
	
	@Test
	public void hoerenTest4() {
		assertFalse(spieler.hoeren(35));
	}
	
	@Test
	public void sagenTest() {
		assertTrue(spieler.sagen(0));
	}
	
	@Test
	public void sagenTest2() {
		assertTrue(spieler.sagen(18));
	}
	
	@Test
	public void farbeTest() {
		assertEquals(null, spieler.farbe());
	}
}
