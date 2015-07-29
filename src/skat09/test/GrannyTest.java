package skat09.test;

import java.util.ArrayList;
//import java.util.Random;




import org.junit.Test;

import skat09.gamevariety.GrandGame;
import skat09.player.Granny;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import static org.junit.Assert.*;

//import de.uniluebeck.skat09.*;
public class GrannyTest {

	private Granny oma = new Granny("Franz");
	PlayingCard spielkarte1 = new PlayingCard(Suit.BELLS, Value.SEVEN);
	PlayingCard spielkarte2 = new PlayingCard(Suit.BELLS, Value.EIGHT);
	PlayingCard spielkarte3 = new PlayingCard(Suit.BELLS, Value.NINE);
	PlayingCard spielkarte4 = new PlayingCard(Suit.BELLS, Value.TEN);
	PlayingCard spielkarte5 = new PlayingCard(Suit.BELLS, Value.UNDER_KNAVE);
	PlayingCard spielkarte6 = new PlayingCard(Suit.BELLS, Value.OVER_KNAVE);
	PlayingCard spielkarte7 = new PlayingCard(Suit.BELLS, Value.KING);
	PlayingCard spielkarte8 = new PlayingCard(Suit.BELLS, Value.DAUS);
	PlayingCard spielkarte9 = new PlayingCard(Suit.HEARTS, Value.SEVEN);
	PlayingCard spielkarte10 = new PlayingCard(Suit.HEARTS, Value.EIGHT);

	ArrayList<PlayingCard> omablatt = new ArrayList<PlayingCard>();
	
	public GrannyTest(){
		omablatt.add(spielkarte1);
		omablatt.add(spielkarte2);
		omablatt.add(spielkarte3);
		omablatt.add(spielkarte4);
		omablatt.add(spielkarte5);
		omablatt.add(spielkarte6);
		omablatt.add(spielkarte7);
		omablatt.add(spielkarte8);
		omablatt.add(spielkarte9);
		omablatt.add(spielkarte10);
		oma.setHand(omablatt);
		
	}


	/**
	 * Ueberprueft, ob die Oma in Richtiger Reihenfolge
	 * die richtige Karte wirft
	 */
	@Test
	public void testeGibKarte(){
		boolean temp = true;
	for (int i=0; i<10; i++){
		if (!omablatt.get(omablatt.size()-1).equals(oma.playCard(null))){
			temp = false;
		}
	}
		
		assertTrue(temp);
	}
	
	@Test
	public void testsagen(){
		
		assertEquals(false,(oma.bid(18)));
	}
	
	@Test
	public void testhoeren(){
		
		assertFalse(oma.respond(18));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testdruecken(){
		
		PlayingCard[] temp = new PlayingCard[2];
		temp[0] = oma.getHand().get(1);
		temp[1] = oma.getHand().get(2);
		assertEquals(null,oma.druecken(temp));
		
	}
	
	@Test
	public void testhandspiel(){
		
		assertTrue(oma.handgame());
	}
	
	@Test
	public void testouvert(){
		assertFalse(oma.ouvert());
	}
	
	@Test
	public void testschneider(){
		assertFalse(oma.schneider());
	}
	
	@Test
	public void testschwarz(){
		
		assertFalse(oma.schwarz());
	}
	
	@Test
	public void testSpielAnsagen(){
		boolean test = false;
		if (oma.declareGame() instanceof GrandGame) {
			test = true;
		}
		assertEquals(true, test);
	}
	
	@Test
	public void testFarbe(){
		
		assertEquals(null, oma.suit());
	}
}