package main.gamevariety;

import java.util.ArrayList;

import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;


/**
 * Die Klasse Farbspiel implementiert alle Regeln des Farbspiels und stellt
 * diese f&uuml;r ein Skat-Spiel zur Verf&uuml;gung.
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */
public class SuitGame extends GameVariety {

	private Suit trumpSuit;

	/**
	 * Instanziert ein Farb - Spiel
	 */
	public SuitGame(Suit trumpSuit) {

		this.trumpSuit = trumpSuit;
		setGameVariety(GameVarietyName.SUIT);

	}

	/**
	 * gibt die Trumpffarbe zurueck.
	 * 
	 * @return trumpffarbe
	 */
	public Suit getTrumpSuit() {
		return trumpSuit;
	}
	
	@Override
	public boolean checkedPlayedCards(ArrayList<PlayingCard> deck, PlayingCard[] playedCards, PlayingCard cardToCheck) {

		boolean result = false;

		// Wenn noch keine Karte gespielt wurde, darf die Karte gespielt werden.
		if (playedCards[0] == null) {

			result = true;
		} else if (playedCards[0].getValue() == Value.UNDER_KNAVE || playedCards[0].getSuit() == getTrumpSuit()) {
			
			result = followingUnderKnaveOrTrump(deck, playedCards, cardToCheck);
		} else {
			
			result = followingSuit(deck, playedCards, cardToCheck);
		}
		
		return result;
	}
	
	/**
	 * Wenn die zuerst gespielte Karte ein Bube oder Trumpf war wird gepr&uuml;ft,
	 * ob der Spieler noch Buben oder Trumpf hat.
	 * 
	 * @param deck - Das Blatt des Spielers, der eine Karte spielen m&ouml;chte.
	 * @param playedCards - Die Karten, die schon gespielt wurden.
	 * @param cardToCheck - Die Karte, die der Spieler spielen m&ouml;chte.
	 * @return true, wenn Karte gespielt werden darf
	 */
	public boolean followingUnderKnaveOrTrump(ArrayList<PlayingCard> deck, PlayingCard[] playedCards, PlayingCard cardToCheck) {
		
		boolean result = true;
		
		// Wenn Bube oder Trumpf gespielt wurde und korrekt bedient wurde gib true zurueck.
		if (cardToCheck.getValue() == Value.UNDER_KNAVE || getTrumpSuit() == cardToCheck.getSuit()) {

			result = true;
		} else { // Wenn nicht bedient wurde, schaue, ob bedient werden konnte.

			// Nach Buben oder Trumpf suchen
			for (int i = 0; i < deck.size(); i++) {

				// Hatte der Spieler Bube/Trumpf darf er diese Karte nicht spielen, sonst schon.
				if (deck.get(i).getValue() == Value.UNDER_KNAVE || deck.get(i).getSuit() == getTrumpSuit()) {

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

		if (card1.getValue() == Value.UNDER_KNAVE && card2.getValue() == Value.UNDER_KNAVE) {

			highestCard = higherUnderKnave(card1, card2);
			
		} else if (card1.getValue() == Value.UNDER_KNAVE || card2.getValue() == Value.UNDER_KNAVE) {

			highestCard = higherCardOneUnderKnave(card1, card2);
			
		} else if (card1.getSuit() == trumpSuit
				&& card2.getSuit() == trumpSuit) {

			highestCard = higherCardSuit(card1, card2);
			
		} else if (card1.getSuit() == trumpSuit
				|| card2.getSuit() == trumpSuit) {

			highestCard = higherCardOneTrump(card1, card2);
			
		} else if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);
			
		} else {

			highestCard = card1;
		}

		return highestCard;
	}
	
	/**
	 * Liefert von 2 Karten die h&ouml;here zur&uuml;ck, wobei eine Karte davon Trumpf ist.
	 * Die andere Karte ist nicht Trumpf und auch kein Bube.
	 * 
	 * @param card1 - die erste Karte
	 * @param card2 - die zweite Karte
	 * @return Der Bube wird zur&uuml;ck geliefert.
	 */
	public PlayingCard higherCardOneTrump(PlayingCard card1, PlayingCard card2) {
		
		PlayingCard result;
		
		if (card1.getSuit() == trumpSuit) {

			result = card1;
		} else {

			result = card2;
		}
		
		return result;
	}

	@Override
	public PlayingCard sortCard(PlayingCard card1, PlayingCard card2) {

		PlayingCard highestCard = null;

		if (card1.getValue() == Value.UNDER_KNAVE && card2.getValue() == Value.UNDER_KNAVE) {

			highestCard = higherUnderKnave(card1, card2);
			
		} else if (card1.getValue() == Value.UNDER_KNAVE || card2.getValue() == Value.UNDER_KNAVE) {

			highestCard = higherCardOneUnderKnave(card1, card2);
			
		} else if (card1.getSuit() == trumpSuit
				&& card2.getSuit() == trumpSuit) {

			highestCard = higherCardSuit(card1, card2);

		} else if (card1.getSuit() == trumpSuit
				|| card2.getSuit() == trumpSuit) {

			highestCard = higherCardOneTrump(card1, card2);

		} else if (card1.getSuit() == card2.getSuit()) {

			highestCard = higherCardSuit(card1, card2);

		} else {
			
			highestCard = higherSuit(card1, card2);
		}
		
		return highestCard;

	}
	
	@Override
	public String toString() {
		return trumpSuit.toString();
	}

}
