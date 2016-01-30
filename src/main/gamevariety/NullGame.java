package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;


/**
 * Die Klasse Nullspiel implementiert alle Regeln des Nullspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verfuegung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class NullGame extends GameVariety implements INullGame {

	/**
	 * Instanziert ein Null - Spiel
	 */
	public NullGame() {
		setGameVariety(Name.NULL);
	}

	
	@Override
	public boolean checkedPlayedCards(ArrayList<PlayingCard> deck,
			PlayingCard[] playedCards, PlayingCard cardToCheck) {

		boolean result = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (playedCards[0] == null) {

			result = true;
		} else {

			result = followingSuit(deck, playedCards, cardToCheck);
		}

		return result;
	}

	
	@Override
	public boolean followingSuit(ArrayList<PlayingCard> deck,
			PlayingCard[] playedCards, PlayingCard cardToCheck) {

		boolean result = true;

		// Wenn die Farbe korrekt bedient wurde gib true zurueck.
		if (playedCards[0].getSuit() == cardToCheck.getSuit()) {

			result = true;
		} else { // nachsehen, ob der Spieler die Farbe bedienen konnte.

			for (int i = 0; i < deck.size(); i++) {

				if (deck.get(i).getSuit() == playedCards[0].getSuit()) {

					result = false;
					break;
				}
			}
		}

		return result;
	}

	
	@Override
	public PlayingCard higherCard(PlayingCard card1, PlayingCard card2) {

		PlayingCard highestCard = null;

		if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);
		} else {

			highestCard = card1;
		}
		
		return highestCard;
	}

	
	@Override
	public PlayingCard sortCard(PlayingCard card1, PlayingCard card2) {

		PlayingCard highestCard = null;

		if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);

		} else {

			highestCard = higherSuit(card1, card2);
		}

		return highestCard;
	}


	
	@Override
	public int evaluateCard(PlayingCard card) {

		PlayingCard.Rank value = card.getRank();
		int result = -1;

		switch (value) {

		case SIX:
			result = PlayingCard.Rank.SIX.ordinal();
			break;
		case SEVEN:
			result = PlayingCard.Rank.SEVEN.ordinal();
			break;
		case EIGHT:
			result = PlayingCard.Rank.EIGHT.ordinal();
			break;
		case NINE:
			result = PlayingCard.Rank.NINE.ordinal();
			break;
		case TEN:
			result = PlayingCard.Rank.TEN.ordinal();
			break;
		case UNDER_KNAVE:
			result = PlayingCard.Rank.UNDER_KNAVE.ordinal();
			break;
		case OVER_KNAVE:
			result = PlayingCard.Rank.OVER_KNAVE.ordinal();
			break;
		case KING:
			result = PlayingCard.Rank.KING.ordinal();
			break;
		case DAUS:
			result = PlayingCard.Rank.DAUS.ordinal();
			break;
		// default:
		// result = -1;
		//
		}
		return result;
	}


	@Override
	public String toString() {
		return "Null";
	}

}
