package test.player;

import main.gamevariety.GrandGame;
import main.player.Granny;
import main.playingcard.PlayingCard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GrannyTest {

	private Granny granny = new Granny("Franz");
	PlayingCard playingCard1 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.SEVEN);
	PlayingCard playingCard2 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.EIGHT);
	PlayingCard playingCard3 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.NINE);
	PlayingCard playingCard4 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.TEN);
	PlayingCard playingCard5 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.UNDER_KNAVE);
	PlayingCard playingCard6 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.OVER_KNAVE);
	PlayingCard playingCard7 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.KING);
	PlayingCard playingCard8 = new PlayingCard(PlayingCard.Suit.BELLS, PlayingCard.Rank.DAUS);
	PlayingCard playingCard9 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.SEVEN);
	PlayingCard playingCard10 = new PlayingCard(PlayingCard.Suit.HEARTS, PlayingCard.Rank.EIGHT);

	ArrayList<PlayingCard> grannyHand = new ArrayList<PlayingCard>();
	
	public GrannyTest(){
		grannyHand.add(playingCard1);
		grannyHand.add(playingCard2);
		grannyHand.add(playingCard3);
		grannyHand.add(playingCard4);
		grannyHand.add(playingCard5);
		grannyHand.add(playingCard6);
		grannyHand.add(playingCard7);
		grannyHand.add(playingCard8);
		grannyHand.add(playingCard9);
		grannyHand.add(playingCard10);
		granny.setHand(grannyHand);
		
	}


	/**
	 * Ueberprueft, ob die Oma in Richtiger Reihenfolge
	 * die richtige Karte wirft
	 */
	@Test
	public void playCardTest(){
		boolean temp = true;
	for (int i=0; i<10; i++){
		if (!grannyHand.get(grannyHand.size()-1).equals(granny.playCard(null))){
			temp = false;
		}
	}
		
		assertTrue(temp);
	}
	
	@Test
	public void bidTest(){
		
		assertEquals(false,(granny.bid(18)));
	}
	
	@Test
	public void respondTest(){
		
		assertFalse(granny.respond(18));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void drueckenTest(){
		
		PlayingCard[] temp = new PlayingCard[2];
		temp[0] = granny.getHand().get(1);
		temp[1] = granny.getHand().get(2);
		assertEquals(null, granny.druecken(temp));
		
	}
	
	@Test
	public void handGameTest(){
		
		assertTrue(granny.handgame());
	}
	
	@Test
	public void ouvertTest(){
		assertFalse(granny.ouvert());
	}
	
	@Test
	public void schneiderTest(){
		assertFalse(granny.schneider());
	}
	
	@Test
	public void schwarzTest(){
		
		assertFalse(granny.schwarz());
	}
	
	@Test
	public void declareGameTest(){
		boolean test = false;
		if (granny.declareGame() instanceof GrandGame) {
			test = true;
		}
		assertEquals(true, test);
	}
	
	@Test
	public void suitTest(){
		
		assertEquals(null, granny.suit());
	}
}