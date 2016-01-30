package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;


/**
 * Die Klasse Grandspiel implementiert alle Regeln des Grandspiels und stellt diese für ein Skat-Spiel zur Verfügung.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class Ramsch extends GameVariety {
	
	public Ramsch() {
		setGameVariety(Name.RAMSCH);
	}

	@Override
	public boolean checkedPlayedCards(ArrayList<PlayingCard> deck,
			PlayingCard[] playedCards, PlayingCard cardToCheck) {
		boolean result = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (playedCards[0] == null) {

			result = true;
		} else if (playedCards[0].getRank() == PlayingCard.Rank.UNDER_KNAVE) {
			
			result = followingUnderKnave(deck, playedCards, cardToCheck);
		} else {
			
			result = followingSuit(deck, playedCards, cardToCheck);
		}
		
		return result;
	}
	
	/**
	 * Wenn die zuerst gespielte Karte ein Bube war wird gepr&uuml;ft,
	 * ob der Spieler noch Buben hat.
	 * 
	 * @param deck - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param playedCards - Die Karten, die schon gespielt wurden.
	 * @param cardToCheck - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	public boolean followingUnderKnave(ArrayList<PlayingCard> deck, PlayingCard[] playedCards, PlayingCard cardToCheck) {
		
		boolean result = true;
		
		// Wenn Bube gespielt wurde und korrekt bedient wurde gib true zurueck.
		if (cardToCheck.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

			result = true;
		} else { // Wenn nicht bedient wurde, schaue, ob bedient werden konnte.

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < deck.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (deck.get(i).getRank() == PlayingCard.Rank.UNDER_KNAVE) {

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

		if (card1.getRank() == PlayingCard.Rank.UNDER_KNAVE && card2.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

			highestCard = higherUnderKnave(card1, card2);

		} else if (card1.getRank() == PlayingCard.Rank.UNDER_KNAVE || card2.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

			highestCard = higherCardOneUnderKnave(card1, card2);

		} else if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);

		} else {

			highestCard = card1;
		}

		return highestCard;
	}

	@Override
	public PlayingCard sortCard(PlayingCard card1, PlayingCard card2) {
		PlayingCard highestCard = null;

		if (card1.getRank() == PlayingCard.Rank.UNDER_KNAVE && card2.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

			highestCard = higherUnderKnave(card1, card2);

		} else if (card1.getRank() == PlayingCard.Rank.UNDER_KNAVE || card2.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

			highestCard = higherCardOneUnderKnave(card1, card2);

		} else if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);

		} else {
			
			highestCard = higherSuit(card1, card2);
		}

		return highestCard;
	}

	@Override
	public String toString() {
		return "Ramsch";
	}
	
	/**
	 * Ordnet einer Karte einen Zahlenwert zu. Die geschieht in der Wertigkeit der Karten
	 * f&uuml;r die jeweilige Spielart.
	 * 
	 * @param card - Die Karte der ein Zahlenwert zugeordnet werden soll.
	 * @return Der Zahlenwert der jeweiligen Karte.
	 */
	public int evaluateCard(PlayingCard card) {

		PlayingCard.Rank rank = card.getRank();
		int result = 0;

		switch (rank) {
		case SIX:
			result = 7;
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
		case TEN:
			result = 10;
			break;	
		case OVER_KNAVE:
			result = 11;
			break;
		case KING:
			result = 12;
			break;
		case DAUS:
			result = 13;
			break;
		case UNDER_KNAVE:
			result = evaluateUnderKnave(card);
			break;
//		default:
//			result = -1;

		}
		return result;
	}

}
