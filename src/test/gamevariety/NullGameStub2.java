package test.gamevariety;

import java.util.ArrayList;

import main.gamevariety.GameVariety;
import main.gamevariety.INullGame;
import main.playingcard.PlayingCard;


public class NullGameStub2 extends GameVariety implements INullGame {

	private int calls = 0;
	
	@Override
	public boolean checkedPlayedCards(ArrayList<PlayingCard> hand,
			PlayingCard[] playedCards, PlayingCard cardToCheck) {
		
		boolean result = false;
		calls++;
		if(calls == 3) {
			
			result = true;
		}
		return result;
	}

	@Override
	public PlayingCard higherCard(PlayingCard card1, PlayingCard card2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int evaluateCard(PlayingCard card) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PlayingCard sortCard(PlayingCard card1, PlayingCard card2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
