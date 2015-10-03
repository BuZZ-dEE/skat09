package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;
import test.interfaces.IGameVariety;


/**
 * Die Klasse Spielart legt fest, ob es sich bei dem Spiel um ein Null-, Grand-,
 * oder Farbspiel handelt und bietet die entsprechenden Regeln an.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
abstract public class GameVariety implements IGameVariety {
	
	private GameVarietyName gameVarietyName;
	

	@Override
	public GameVarietyName getGameVariety() {
		return gameVarietyName;
	}
	
	@Override
	public void setGameVariety(GameVarietyName gameVarietyName) {
		this.gameVarietyName = gameVarietyName;
	}

	@Override
	abstract public boolean checkedPlayedCards(ArrayList<PlayingCard> deck,
			PlayingCard[] playedCards, PlayingCard cardToCheck);
	
	@Override
	public boolean followingSuit(ArrayList<PlayingCard> deck, PlayingCard[] playedCards, PlayingCard cardToCheck) {
		
		boolean result = true;
		
		// If suit follow is okay return true
		if (playedCards[0].getSuit() == cardToCheck.getSuit() && cardToCheck.getValue() != Value.UNDER_KNAVE) {

			result = true;
		}
		
		// Check if player could follow the suit
		else {

			for (int i = 0; i < deck.size(); i++) {

				if (deck.get(i).getSuit() == playedCards[0].getSuit() && deck.get(i).getValue() != Value.UNDER_KNAVE) {

					result = false;
					break;
				}
			}
		}
		
		return result;
	}

	@Override
	abstract public PlayingCard higherCard(PlayingCard card1, PlayingCard card2);

	@Override
	abstract public PlayingCard sortCard(PlayingCard card1,
			PlayingCard card2);

	@Override
	public abstract String toString();

	@Override
	public PlayingCard higherUnderKnave(PlayingCard card11, PlayingCard card2) {

		PlayingCard result;

		if (evaluateUnderKnave(card11) < evaluateUnderKnave(card2)) {

			result = card2;
		}

		else {

			result = card11;
		}

		return result;
	}
	
	@Override
	public PlayingCard higherSuit(PlayingCard card1, PlayingCard card2) {
		
		PlayingCard result;
		
		if (card1.getSuit() == Suit.ACORNS
				&& card2.getSuit() != Suit.ACORNS) {

			result = card1;
		}

		else if (card1.getSuit() == Suit.LEAVES
				&& card2.getSuit() != Suit.ACORNS) {

			result = card1;
		}

		else if (card1.getSuit() == Suit.HEARTS
				&& (card2.getSuit() != Suit.ACORNS && card2.getSuit() != Suit.LEAVES)) {

			result = card1;
		}

//		 else if (card1.getSuit() == Suit.BELLS
//		 && (card2.getSuit() != Suit.ACORNS
//		 && card2.getSuit() != Suit.LEAVES && card2.getSuit() !=
//		 Suit.HEARTS)) {
//		
//		 result = card1;
//		 }

		else {
			
			result = card2;
		}

		
		return result;
	}
	
	@Override
	public PlayingCard higherCardOneUnderKnave(PlayingCard card1, PlayingCard card2) {

		PlayingCard result;

		if (card1.getValue() == Value.UNDER_KNAVE) {

			result = card1;
		}

		else {

			result = card2;
		}

		return result;
	}
	
	@Override
	public PlayingCard higherCardSuit(PlayingCard card1, PlayingCard card2) {

		PlayingCard result;

		if (evaluateCard(card1) < evaluateCard(card2)) {

			result = card2;
		}

		else {

			result = card1;
		}

		return result;
	}
	
	@Override
	public int evaluateCard(PlayingCard card) {

		Value value = card.getValue();
		int result = 0;

		switch (value) {
		case SIX:
			result = 6;
			break;
		case SEVEN:
			result = 7;
			break;
		case EIGHT:
			result = 8;
			break;
		case NINE:
			result = 9;
			break;
		case OVER_KNAVE:
			result = 10;
			break;
		case KING:
			result = 11;
			break;
		case TEN:
			result = 12;
			break;
		case DAUS:
			result = 13;
			break;
		case UNDER_KNAVE:
			result = evaluateUnderKnave(card);
			break;
		default:
			result = -1;

		}
		return result;
	}

	@Override
	public int evaluateUnderKnave(PlayingCard card) {

		Suit suit = card.getSuit();
		int result = 0;

		switch (suit) {

		case BELLS:
			result = 14;
			break;
		case HEARTS:
			result = 15;
			break;
		case LEAVES:
			result = 16;
			break;
		case ACORNS:
			result = 17;
			break;
		default:
			result = -1;

		}
		return result;
	}

}
