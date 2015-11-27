package main.player;

import java.util.ArrayList;
import java.util.Random;

import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;


/**
 * Die Klasse schlauer Spieler erzeugt einen Computerspieler, der sich dem Spiel
 * angemessen verh&auml;lt und &uuml;ber eine gewisse k&uuml;nstliche
 * Intelligenz verf&uuml;gt. Verschiedene Implementierungen sind denkbar.
 * 
 * @author Anne-Christin Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class SmartPlayer extends Player {

	int cardsOfsuitCount;

	int maxBiddingValue;

	IGameVariety gameVarietyToPlay;

	ArrayList<PlayingCard> startHand;
	
	

	public SmartPlayer(String name) {

		super(name);
		maxBiddingValue = -1;
		gameVariety = null;
	}

	/**
	 * Gibt den maximalen Reizwert des Spielers zurück.
	 * 
	 * @return der maximale Reizwert, den der Spieler reizen kann
	 */
	public int getMaxBiddingValue() {

		return maxBiddingValue;
	}

	/**
	 * Gibt das Blatt, dass der Spieler am Anfang hat, bevor er irgendeine Karte
	 * gespielt hat.
	 * 
	 * @return das anfangsblatt, das der spieler bekommt, wenn ausgeteilt wurde
	 */
	public ArrayList<PlayingCard> getStartHand() {

		return startHand;
	}

	/**
	 * setzt das Anfangsblatt, das der Spieler zu beginn besitzt
	 * 
	 * @param hand
	 */
	@SuppressWarnings("unchecked")
	public void setStartHand(ArrayList<PlayingCard> hand) {

		startHand = (ArrayList<PlayingCard>) hand.clone();
	}

	/**
	 * setzt den Maximalen reizwert
	 * 
	 * @param maxBiddingValue
	 */
	public void setMaxBiddingValue(int maxBiddingValue) {

		this.maxBiddingValue = maxBiddingValue;
	}

	@Override
	public PlayingCard playCard(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = playGrand(playedCards);
		}

		else if (gameVariety.getGameVariety() == GameVariety.Name.SUIT) {

			result = playSuit(playedCards);
		}

		else if (gameVariety.getGameVariety() == GameVariety.Name.NULL) {

			result = playNull(playedCards);
		}

		else if (gameVariety.getGameVariety() == GameVariety.Name.RAMSCH) {

			result = playRamsch(playedCards);
		}

		hand.remove(result);

		return result;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Grandspiel Sinn macht.
	 * 
	 * @param playedCards
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Grands gespielt werden soll
	 */
	public PlayingCard playGrand(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (playedCards[0] == null) {

			result = playFirstCardGrand(playedCards);
		}

		else if (playedCards[1] == null) {
			result = playSecondCardGrand(playedCards);
		}

		else if (playedCards[2] == null) {

			result = playThirdCardGrand(playedCards);
		}

		return result;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Farbspiel Sinn macht.
	 * 
	 * @param playedCards
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Farbspiels gespielt werden soll
	 */
	public PlayingCard playSuit(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (playedCards[0] == null) {

			result = playFirstCardSuit(playedCards);
		}

		else if (playedCards[1] == null) {

			result = playSecondCardSuit(playedCards);
		}

		else if (playedCards[2] == null) {

			result = playThirdCardSuit(playedCards);
		}

		return result;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Nullspiel Sinn macht.
	 * 
	 * @param playedCards
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Nullspiels gespielt werden soll
	 */
	public PlayingCard playNull(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (playedCards[0] == null) {

			result = playFirstCardNull(playedCards);
		}

		else if (playedCards[1] == null) {

			result = playSecondCardNull(playedCards);
		}

		else if (playedCards[2] == null) {

			result = playThirdCardNull(playedCards);
		}

		return result;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Nullspiel Sinn macht.
	 * 
	 * @param playedCards
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Nullspiels gespielt werden soll
	 */
	public PlayingCard playRamsch(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (playedCards[0] == null) {

			result = playFirstCardRamsch(playedCards);
		}

		else if (playedCards[1] == null) {

			result = playSecondCardRamsch(playedCards);
		}

		else if (playedCards[2] == null) {

			result = playThirdCardRamsch(playedCards);
		}

		return result;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playFirstCardGrand(PlayingCard[] playedCards) {

		ArrayList<PlayingCard> suit = new ArrayList<PlayingCard>();
		PlayingCard result = null;
		Random random = new Random();

		if (isDeclarer) {

			result = playFirstCardGrandAsDeclarer(playedCards);
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				suit = determineShortLongSuit(false);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				suit = determineShortLongSuit(true);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playSecondCardGrand(PlayingCard[] playedCards) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = alleinspieleralsZweiterKarteSpielenGrand(playedCards);
		}

		else {

			if (playedCards[0].getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				if (gameVariety
						.higherCard(
								playedCards[0],
								hoechsteSpielbareKarte(playableCards(playedCards)))
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(playedCards));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(playedCards));

				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playThirdCardGrand(PlayingCard[] playedCards) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = alleinspieleralsDritterKarteSpielenGrand(playedCards);
		}

		else {

			if (gameVariety.higherCard(playedCards[0], playedCards[1])
					.getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(playedCards)),
								playedCards[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(playedCards)),
										playedCards[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(playedCards)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(playedCards));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(playedCards));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playFirstCardSuit(PlayingCard[] playedCards) {

		ArrayList<PlayingCard> suit = new ArrayList<PlayingCard>();
		PlayingCard result = null;
		Random random = new Random();

		if (isDeclarer) {

			result = playRamdonAllowedCard(playedCards);
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				suit = determineShortLongSuit(false);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				suit = determineShortLongSuit(true);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playSecondCardSuit(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (isDeclarer) {

			result = playRamdonAllowedCard(playedCards);
		}

		else {

			if (playedCards[0].getOwner().equals(teammate)) {

				result = hoechsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				if (gameVariety
						.higherCard(
								playedCards[0],
								hoechsteSpielbareKarte(playableCards(playedCards)))
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))) {

					result = hoechsteSpielbareKarte(playableCards(playedCards));
				}

				else {

					result = niedrigsteSpielbareKarte(playableCards(playedCards));
				}
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playThirdCardSuit(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (isDeclarer) {

			result = playRamdonAllowedCard(playedCards);
		}

		else {

			if (gameVariety.higherCard(playedCards[0], playedCards[1])
					.getOwner().equals(teammate)) {

				result = hoechsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(playedCards)),
								playedCards[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(playedCards)),
										playedCards[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(playedCards)))) {

					result = hoechsteSpielbareKarte(playableCards(playedCards));
				}

				else {

					result = niedrigsteSpielbareKarte(playableCards(playedCards));
				}
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playFirstCardNull(PlayingCard[] playedCards) {

		ArrayList<PlayingCard> suit = new ArrayList<PlayingCard>();
		PlayingCard result = null;
		Random random = new Random();

		if (isDeclarer) {

			result = niedrigsteSpielbareKarte(playableCards(playedCards));
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				suit = determineShortLongSuit(false);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				suit = determineShortLongSuit(true);

				if (suit.isEmpty()) {

					result = playRamdonAllowedCard(playedCards);
				}

				else {

					result = suit.get(random.nextInt(suit.size()));
				}
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playSecondCardNull(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (isDeclarer) {

			result = niedrigsteSpielbareKarte(playableCards(playedCards));
		}

		else {

			if (playedCards[0].getOwner().equals(teammate)) {

				result = niedrigsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				result = niedrigsteSpielbareKarte(playableCards(playedCards));
			}
		}

		return result;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playThirdCardNull(PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (isDeclarer) {

			result = niedrigsteSpielbareKarte(playableCards(playedCards));
		}

		else {

			if (gameVariety.higherCard(playedCards[0], playedCards[1])
					.getOwner().equals(teammate)) {

				result = hoechsteSpielbareKarte(playableCards(playedCards));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(playedCards)),
								playedCards[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(playedCards)),
										playedCards[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(playedCards)))) {

					result = niedrigsteSpielbareKarte(playableCards(playedCards));
				}

				else {

					result = niedrigsteSpielbareKarte(playableCards(playedCards));
				}
			}
		}

		return result;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und playFirstCardSuit soll, eine Karte spielt.
	 * 
	 * @param playedCards
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall ist das Array aber leer
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard playFirstCardGrandAsDeclarer(PlayingCard[] playedCards) {

		PlayingCard result = null;
		Random random = new Random();
		ArrayList<PlayingCard> buben = cardsOfRank(hand, PlayingCard.Rank.UNDER_KNAVE);
		ArrayList<PlayingCard> asse = cardsOfRank(hand, PlayingCard.Rank.DAUS);
		ArrayList<PlayingCard> zehnen = cardsOfRank(hand, PlayingCard.Rank.TEN);

		if (buben.size() > 2) {

			result = buben.get(random.nextInt(buben.size()));
		}

		else if (buben.size() == 2) {

			result = bubeSpielen(playedCards, buben, random);

		}

		else if (!asse.isEmpty()) {

			for (PlayingCard karte : asse) {

				if (cardsOfSuit(startHand, karte.getSuit()).size() <= 4) {

					result = asse.get(random.nextInt(asse.size()));
					break;

				}
			}

		}

		else if (!zehnen.isEmpty()) {

			for (PlayingCard karte : zehnen) {

				// if (cardsOfSuit(anfangsBlatt,
				// karte.getFarbe()).size() <= 4
				// && alleGespielteKarten.contains(naechstHoehereKarte(
				// karte.getFarbe(), karte))) {
				//
				// ergebnis = zehnen.get(zufall.nextInt(zehnen.size()));
				// break;
				// }

				boolean enthalten = false;
				PlayingCard hoehereKarte = naechstHoehereKarte(karte.getSuit(),
						karte);
				for (PlayingCard karte2 : allPlayedCards) {

					if (karte2.equals(hoehereKarte)) {
						enthalten = true;
					}
				}

				if (cardsOfSuit(startHand, karte.getSuit()).size() <= 4
						&& enthalten) {

					result = zehnen.get(random.nextInt(zehnen.size()));
					break;
				}
			}
		}

		if (result == null) {

			result = hand.get(random.nextInt(hand.size()));
		}

		return result;
	}

	/**
	 * Die Methode gibt aus einer Arrayliste mit Buben einen ausgewählten
	 * zufälligen Buben zurück. Dies tut sie aber nur, wenn in der Liste der
	 * Kreuz Bube oder der Pik Bube vorhanden ist.
	 * 
	 * @param playedCards
	 *            - die bisher gespielten Karten auf dem Tisch
	 * @param buben
	 *            - die Arrayliste mit den Buben
	 * @param zufall
	 *            - Zufallswert
	 * @return der zufällig aus der Liste ausgewählte Bube
	 */
	public PlayingCard bubeSpielen(PlayingCard[] playedCards,
			ArrayList<PlayingCard> buben, Random zufall) {

		PlayingCard result = null;

		for (PlayingCard card : buben) {

			if (card.equals(new PlayingCard(PlayingCard.Suit.ACORNS, PlayingCard.Rank.UNDER_KNAVE))
					|| card.equals(new PlayingCard(PlayingCard.Suit.LEAVES, PlayingCard.Rank.UNDER_KNAVE))) {

				result = buben.get(zufall.nextInt(buben.size()));
				break;

			}
		}

		return result;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und als Zweiter eine Karte spielen soll, eine Karte
	 * spielt.
	 * 
	 * @param playedCards
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall ist in dem Array eine Karte
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard alleinspieleralsZweiterKarteSpielenGrand(
			PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (gameVariety
				.higherCard(
						playedCards[0],
						hoechsteSpielbareKarte(playableCards(playedCards)))
				.equals(
						hoechsteSpielbareKarte(playableCards(playedCards)))

				&& gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(playedCards)),
								hoechsteSpielbareKarte(gegnerMoeglicheSpielbareKarten(playedCards)))
						.equals(
								hoechsteSpielbareKarte(playableCards(playedCards)))) {

			result = hoechsteSpielbareKarte(playableCards(playedCards));
		}

		else {

			result = niedrigsteSpielbareKarte(playableCards(playedCards));
		}

		// TODO loeschen
		System.out.println("alleinspieleralsZweiterKarteSpielenGrand"
				+ result.toString());

		return result;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und als Dritter eine Karte spielen soll, eine Karte
	 * spielt.
	 * 
	 * @param playedCards
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall sind in dem Array zwei Karten
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard alleinspieleralsDritterKarteSpielenGrand(
			PlayingCard[] playedCards) {

		PlayingCard result = null;

		if (((gameVariety.higherCard(
				hoechsteSpielbareKarte(playableCards(playedCards)),
				playedCards[0])
				.equals(hoechsteSpielbareKarte(playableCards(playedCards)))) && gameVariety
				.higherCard(
						hoechsteSpielbareKarte(playableCards(playedCards)),
						playedCards[1])
				.equals(
						hoechsteSpielbareKarte(playableCards(playedCards))))
				&& ((augenKarte(playedCards[0]) > 3) && (augenKarte(playedCards[1]) > 3))) {

			result = hoechsteSpielbareKarte(playableCards(playedCards));
		}

		else {

			result = niedrigsteSpielbareKarte(playableCards(playedCards));
		}

		return result;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playFirstCardRamsch(PlayingCard[] playedCards) {

		PlayingCard result = null;

		result = niedrigsteSpielbareKarte(playableCards(playedCards));

		return result;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playSecondCardRamsch(PlayingCard[] playedCards) {

		PlayingCard result = null;

		result = niedrigsteSpielbareKarte(playableCards(playedCards));

		return result;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param playedCards
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard playThirdCardRamsch(PlayingCard[] playedCards) {

		PlayingCard result = null;

		result = niedrigsteSpielbareKarte(playableCards(playedCards));

		return result;
	}

	// /**
	// * Diese Methode
	// * @param karte
	// * @return
	// */
	// public ArrayList<Spielkarte>
	// rauskommenGegnerMoeglicheSpielbareKarten(ArrayList<Spielkarte> blatt) {
	//		
	// ArrayList<Spielkarte> ergebnis = null;
	// Spielkarte[] zuSpielendeKarte = new Spielkarte[3];
	//		
	// for (Spielkarte karte : blatt) {
	//			
	// zuSpielendeKarte[0] = karte;
	// ergebnis.addAll(gegnerMoeglicheSpielbareKarten(zuSpielendeKarte));
	// }
	//		
	// return ergebnis;
	// }

	/**
	 * Diese Methode bestimmt aus den Karten, die beide Gegener zusammen haben,
	 * die m&oumlglichen spielbaren Karten. Diese Methode funktionert nur, wenn
	 * eine Karte auf dem Tisch liegt.
	 * 
	 * @param playedCards
	 *            - Karten, die gerade auf dem Tisch liegen
	 * @return Ein Liste mit den m&oumlglichen spielbaren Karten.
	 */
	public ArrayList<PlayingCard> gegnerMoeglicheSpielbareKarten(
			PlayingCard[] playedCards) {

		System.out.println("gegnermoeglicheSpielbareKarten");
		ArrayList<PlayingCard> result = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> possibleAdversaryCards = possibleAdversaryCards(allPlayedCards,
				playedCards);

		for (int i = 0; i < possibleAdversaryCards.size(); i++) {

			if (gameVariety
					.checkedPlayedCards(possibleAdversaryCards, playedCards, possibleAdversaryCards.get(i))) {

				result.add(possibleAdversaryCards.get(i));
			}
		}

		return result;
	}

	/**
	 * Die Methode ermittelt die Karten, die beide Gegner zusammen haben.
	 * 
	 * @param allPlayedCards
	 *            - alle Karten, die bisher gespielt wurden
	 * @param playedCards
	 *            - Karten, die gerade auf dem Tisch liegen
	 * @return Eine Liste mit den Karten, die beide Gegner zusammen haben.
	 */
	public ArrayList<PlayingCard> possibleAdversaryCards(
			ArrayList<PlayingCard> allPlayedCards,
			PlayingCard[] playedCards) {

		ArrayList<PlayingCard> result = new ArrayList<PlayingCard>();

		if (!deck.isEmpty()) {
			result.addAll(deck);
		}
		if (hand != null && !hand.isEmpty()) {
			result.removeAll(hand);
		}
		if (allPlayedCards != null && !allPlayedCards.isEmpty()) {
			result.removeAll(allPlayedCards);
		}
		if (skat != null && !skat.isEmpty()) {
			result.removeAll(skat);
		}

		for (int i = 0; i < playedCards.length; i++) {

			result.remove(playedCards[i]);
		}

		// TODO loeschen
		for (PlayingCard card : result) {
			System.out.println("possibleAdversaryCards" + card.toString());
		}

		return result;
	}

	// Es wird nicht gecheatet!!!
	// /**
	// * Diese Methode entfernt aus den Karten, die beide anderen Spieler
	// zusammen
	// * haben, die Karten des Mitspielers, die schon gespielt wurden, damit der
	// Spieler weiß, welche Karten
	// * der Alleinspieler noch haben k&ouml;nnte.
	// *
	// * @param alleGespielteKarten
	// * - alle Karten, die bisher gespielt wurden
	// * @param gespieltenKarten
	// * - Karten, die gerade auf dem Tisch liegen
	// * @return die m&omul;glichen Karten des Alleinspielers
	// */
	// public ArrayList<Spielkarte> moeglicheKartenAlleinspieler(
	// ArrayList<Spielkarte> alleGespielteKarten,
	// Spielkarte[] gespieltenKarten) {
	//
	// ArrayList<Spielkarte> ergebnis = possibleAdversaryCards(
	// alleGespielteKarten, gespieltenKarten);
	//
	// for (Spielkarte karte : ergebnis) {
	//
	// if (karte.getBesitzer().equals(mitspieler)) {
	//
	// ergebnis.remove(karte);
	// }
	// }
	//
	// return ergebnis;
	// }

	/**
	 * Die Methode berechnet für eine Arraylist von Spielkarten in
	 * Abh&auml;ngigkeit der Gr&ouml;ße ein Zufallszahl.
	 * 
	 * @param cards
	 *            - Liste für die in Abh&auml;ngigkeit ihrer L&auml;nge eine
	 *            Zufallszahl generiert werden soll.
	 * @return Die Zufallszahl
	 */
	public int randomNumber(ArrayList<PlayingCard> cards) {

		int result = -1;
		Random zufall = new Random();

		if (cards.size() > 0) {

			result = zufall.nextInt(cards.size());
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r eine Karte die n&auml;chst h&ouml;here Karte
	 * der gleichen Farbe zur&uuml;ck. Buben werden nicht direkt betrachtet.
	 * 
	 * @param card
	 *            - karte zu der die n&aumlchst h&ouml;here Karte der gleichen
	 *            Farbe bestimmt werden soll
	 * @return die n&aumlchst h&ouml;here Karte
	 */
	public PlayingCard naechstHoehereKarte(PlayingCard.Suit suit, PlayingCard card) {

		PlayingCard result = null;

		switch (card.getRank()) {

		case SIX:
			result = new PlayingCard(suit, PlayingCard.Rank.SEVEN);
			break;
		case SEVEN:
			result = new PlayingCard(suit, PlayingCard.Rank.EIGHT);
			break;
		case EIGHT:
			result = new PlayingCard(suit, PlayingCard.Rank.NINE);
			break;
		case NINE:
			result = naechstHoehereKarteNeun(suit);
			break;
		case OVER_KNAVE:
			result = new PlayingCard(suit, PlayingCard.Rank.KING);
			break;
		case KING:
			result = naechstHoehereKarteKoenig(suit);
			break;
		case TEN:
			result = naechstHoehereKarteZehn(suit);
			break;
		case DAUS:
			result = null;
			break;
		case UNDER_KNAVE:
			result = naechstHoehereKarteBube(suit);
			break;
		default:
			result = null;
			break;
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "NEUN" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe von der die n&auml;chst niedrigere Karte verlang wird
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteNeun(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.OVER_KNAVE);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.TEN);
		}

		return result;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "KOENIG" die n&auml;chst
	 * h&ouml;here Karte der gleichen Farbe zurück. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe von der die n&auml;chst niedrigere Karte verlangt wird
	 * @return die n&aumlchst h&ouml;here Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstHoehereKarteKoenig(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.TEN);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.DAUS);
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "ZEHN" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe der Nachfolgerkarte
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteZehn(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.DAUS);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.OVER_KNAVE);
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "BUBE" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe der Nachfolgerkarte
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteBube(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND
				|| gameVariety.getGameVariety() == GameVariety.Name.RAMSCH) {

			result = null;
		}

		else {

			result = new PlayingCard(suit, PlayingCard.Rank.OVER_KNAVE);
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r eine Karte die n&auml;chst niedrigere Karte
	 * der gleichen Farbe zur&uuml;ck. Buben werden nicht direkt betrachtet.
	 * 
	 * @param suit
	 *            - Farbe der Folgekarte
	 * @param card
	 *            - karte zu der die n&aumlchst niedrigere Karte der gleichen
	 *            Farbe bestimmt werden soll
	 * @return die n&aumlchst niedrigere Karte
	 */
	public PlayingCard naechstNiedrigereKarte(PlayingCard.Suit suit, PlayingCard card) {

		PlayingCard result = null;

		switch (card.getRank()) {

		case SIX:
			result = null;
			break;
		case SEVEN:
			result = new PlayingCard(suit, PlayingCard.Rank.SIX);
			break;
		case EIGHT:
			result = new PlayingCard(suit, PlayingCard.Rank.SEVEN);
			break;
		case NINE:
			result = new PlayingCard(suit, PlayingCard.Rank.EIGHT);
			break;
		case OVER_KNAVE:
			result = naechstNiedrigereKarteDame(suit);
			break;
		case KING:
			result = new PlayingCard(suit, PlayingCard.Rank.OVER_KNAVE);
			break;
		case TEN:
			result = naechstNiedrigereKarteZehn(suit);
			break;
		case DAUS:
			result = naechstNiedrigereKarteAss(suit);
			break;
		case UNDER_KNAVE:
			result = naechstNiedrigereKarteBube(suit);
			break;
		default:
			result = null;
			break;
		}

		return result;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "DAME" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - suitGame der Folgekarte
	 * @return die n&aumlchst niedrigere Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstNiedrigereKarteDame(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.NINE);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.TEN);
		}

		return result;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "ZEHN" die
	 * n&auml;chst niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe von der die n&auml;chst niedrigste Karte verlangt wird
	 * @return die n&aumlchst niedrigere Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstNiedrigereKarteZehn(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.KING);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.NINE);
		}

		return result;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "ASS" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe der zu suchenden Karte"
	 * @return die n&aumlchst niedrigere Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstNiedrigereKarteAss(PlayingCard.Suit suit) {

		PlayingCard result = null;

		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND) {

			result = new PlayingCard(suit, PlayingCard.Rank.TEN);
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.KING);
		}

		return result;
	}
	
	/**
	 * Die Methode liefert für die Karte mit dem Wert "BUBE" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param suit
	 *            - Farbe der zu suchenden Karte
	 * @return die n&aumlchst niedrigere Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstNiedrigereKarteBube(PlayingCard.Suit suit) {
		
		PlayingCard result = null;
		
		if (gameVariety.getGameVariety() == GameVariety.Name.SUIT
				|| gameVariety.getGameVariety() == GameVariety.Name.GRAND
				|| gameVariety.getGameVariety() == GameVariety.Name.RAMSCH) {

			result = null;
		} else {

			result = new PlayingCard(suit, PlayingCard.Rank.TEN);
		}
		
		return result;
	}

	/**
	 * Diese Methode sucht in einem Blatt Karten eines bestimmten Wertes und
	 * packt diese in eine ArrayList.
	 * 
	 * @param hand
	 *            - Das Blatt aus dem die Arrayliste eines Wertes geholt werden
	 *            soll.
	 * @param rank
	 *            - Der Wert einer Karte. z.B.: Ein Ass
	 * @return Die ArrayList mit den Assen.
	 */
	public ArrayList<PlayingCard> cardsOfRank(ArrayList<PlayingCard> hand,
			PlayingCard.Rank rank) {

		ArrayList<PlayingCard> result = new ArrayList<PlayingCard>(4);

		for (PlayingCard card : hand) {

			if (card.getRank() == rank) {

				result.add(card);
			}
		}

		return result;
	}

	/**
	 * Diese Methode sucht in einem Blatt Karten einer bestimmten Farbe und
	 * packt diese in eine ArrayList. Buben werden nicht ber&uuml;cksichtigt.
	 * 
	 * @param hand
	 *            - Das Blatt aus dem die Arrayliste einer Farbe geholt werden
	 *            soll.
	 * @param suit
	 *            - Die Farbe einer Karte. z.B.: Kreuz
	 * @return Die ArrayList mit den Karten der Farbe Kreuz.
	 */
	public ArrayList<PlayingCard> cardsOfSuit(ArrayList<PlayingCard> hand,
			PlayingCard.Suit suit) {

		ArrayList<PlayingCard> result = new ArrayList<PlayingCard>();

		for (PlayingCard card : hand) {

			if (card.getSuit() == suit && card.getRank() != PlayingCard.Rank.UNDER_KNAVE) {

				result.add(card);
			}
		}

		return result;
	}

	/**
	 * Es wird die h&ouml;chste spielbare Karte aus den spielbaren Karten
	 * bestimmt und zur&uuml;ck geliefert.
	 * 
	 * @param playedCards
	 *            - Eine Liste mit den m&ouml;glichen zu spielenden Karten.
	 * @return Die h&ouml;chste spielbare Karte.
	 */
	public PlayingCard hoechsteSpielbareKarte(
			ArrayList<PlayingCard> playedCards) {

		PlayingCard result = playedCards.get(0);

		for (int i = 1; i < playedCards.size(); i++) {

			result = hoechsteSpielbareKarteBestimmen(result,
					playedCards.get(i));
		}

		return result;
	}

	/**
	 * Es wird die niedrigste spielbare Karte aus den spielbaren Karten bestimmt
	 * und zur&uuml;ck geliefert. Die Farbe ist bei der Auswertung nicht
	 * relevant.
	 * 
	 * @param playedCards
	 *            - Eine Liste mit den m&ouml;glichen zu spielenden Karten.
	 * @return Die niedrigste spielbare Karte.
	 */
	public PlayingCard niedrigsteSpielbareKarte(
			ArrayList<PlayingCard> playedCards) {

		PlayingCard result = playedCards.get(0);

		for (int i = 1; i < playedCards.size(); i++) {

			result = niedrigsteSpielbareKarteBestimmen(result,
					playedCards.get(i));
		}

		return result;
	}

	/**
	 * Von zwei Karten wird h&ouml;chste Karte bestimmt und zur&uuml;ck
	 * geliefert. Dies geschieht in Abh&auml;ngigkeit von der Spielart und nur
	 * aufgrund der Kartenwerte, d.h. unabh&auml;ngig von der Farbe.
	 * 
	 * @param card1
	 *            - Die erste Karte.
	 * @param card2
	 *            - Die zweite Karte.
	 * @return - Die h&ouml;here von zwei Karten.
	 */
	public PlayingCard hoechsteSpielbareKarteBestimmen(PlayingCard card1,
			PlayingCard card2) {

		PlayingCard result = null;

		if (gameVariety.evaluateCard(card1) == gameVariety.evaluateCard(card2)) {

			result = card1;
		}

		else if (gameVariety.evaluateCard(card1) < gameVariety
				.evaluateCard(card2)) {

			result = card2;
		}

		else if (gameVariety.evaluateCard(card1) > gameVariety
				.evaluateCard(card2)) {

			result = card1;
		}

		return result;
	}

	/**
	 * Von zwei Karten wird niedrigste Karte bestimmt und zur&uuml;ck geliefert.
	 * Dies geschieht in Abh&auml;ngigkeit von der Spielart und nur aufgrund der
	 * Kartenwerte, d.h. unabh&auml;ngig von der Farbe.
	 * 
	 * @param card1
	 *            - Die erste Karte.
	 * @param card2
	 *            - Die zweite Karte.
	 * @return - Die niedrigere von zwei Karten.
	 */
	public PlayingCard niedrigsteSpielbareKarteBestimmen(PlayingCard card1,
			PlayingCard card2) {

		PlayingCard result = null;

		if (gameVariety.evaluateCard(card1) == gameVariety.evaluateCard(card2)) {

			result = card1;
		}

		else if (gameVariety.evaluateCard(card1) < gameVariety
				.evaluateCard(card2)) {

			result = card1;
		}

		else if (gameVariety.evaluateCard(card1) > gameVariety
				.evaluateCard(card2)) {

			result = card2;
		}

		if (result == null) {
			result = card1;
		}

		return result;
	}

	// Der SchlaueSpieler spielt hand und muss deshalb noch nicht druecken.
	@Override
	public PlayingCard[] druecken(PlayingCard[] skat) {
		// Auto-generated method stub
		return null;
	}

	@Override
	public boolean handgame() {
		// Auto-generated method stub
		return true;
	}

	@Override
	public boolean ouvert() {
		// Auto-generated method stub
		return false;
	}

	@Override
	public boolean schneider() {
		// Auto-generated method stub
		return false;
	}

	@Override
	public boolean schwarz() {
		// Auto-generated method stub
		return false;
	}

	@Override
	public IGameVariety declareGame() {

		IGameVariety gameVarietyToPlay = determineGameVariety();

		if (gameVarietyToPlay == null) {

			gameVarietyToPlay = new NullGame();
		}

		return gameVarietyToPlay;
	}

	@Override
	public boolean respond(int biddingValue) {

		boolean result = false;

		if (biddingValue <= maxBiddingValue) {

			result = true;
		}

		return result;
	}

	@Override
	public boolean bid(int biddingValue) {

		boolean result = false;

		if (biddingValue <= maxBiddingValue) {

			result = true;
		}

		return result;
	}

	@Override
	public SuitGame suit() {

		SuitGame suit = new SuitGame(ermittleTrumpffarbe());

		return suit;
	}

	@Override
	// Benutzt den Agenten nicht.
	public boolean agent() {

		return false;
	}

	@Override
	// Agent wird nich benutzt, demnach muss auch kein
	// Reizlimit festgelegt werden.
	public int setBidLimit() {

		return 0;
	}

	public void bestimmeMaxReizwert() {

		IGameVariety zuReizendeSpielart = determineGameVariety();
		System.out.println(zuReizendeSpielart);

		if (zuReizendeSpielart != null) {

			switch (zuReizendeSpielart.getGameVariety()) {

			case SUIT:
				maxReizwertFarbe(ermittleSpitzen(zuReizendeSpielart));
				break;

			case GRAND:
				maxReizwertGrand(ermittleSpitzen(zuReizendeSpielart));
				break;

			case NULL:
				maxReizwertNull();
				break;
			default:
				break;
			}
		}

		else {

			maxBiddingValue = -1;
		}
	}

	/**
	 * Ermittelt den maxReizwert, sofern ein Farbspiel gespielt wird.
	 * 
	 * @param ermittelteSpitzen
	 *            - Spitzen des Spielers
	 */
	public void maxReizwertFarbe(int ermittelteSpitzen) {

		PlayingCard.Suit suit = ermittleTrumpffarbe();

		maxBiddingValue = (ermittelteSpitzen + 1) * suit.value();
	}

	/**
	 * Berechnet den maxReizwert des Spielers, sofern ein Grandspiel gemacht
	 * wird.
	 * 
	 * @param ermittelteSpitzen
	 *            - die Spitzen des Spielers
	 */
	public void maxReizwertGrand(int ermittelteSpitzen) {

		maxBiddingValue = (ermittelteSpitzen + 1) * 24;
	}

	/**
	 * Berechnet den maxReizwert des Spielers bei einem Nullspiel.
	 * 
	 * Befindet sich im Ausbau.
	 */
	public void maxReizwertNull() {

		maxBiddingValue = 23;
	}

	/**
	 * Ermittelt die Spitzen des Spielers f&uuml;r eine bestimmte Spielart.
	 * 
	 * @param zuReizendeSpielart
	 *            - Spielart, f&uuml;r die die Spitzen ermittelt werden soll
	 * @return Zahl der Spitzen
	 */
	public int ermittleSpitzen(IGameVariety zuReizendeSpielart) {

		int result = 0;
		PlayingCard[] spitzen;

		if (zuReizendeSpielart.getGameVariety() == GameVariety.Name.SUIT) {

			spitzen = farbeSpitzen(zuReizendeSpielart);
			result = spitzenZaehlen(spitzen);
		}

		else if (zuReizendeSpielart.getGameVariety() == GameVariety.Name.GRAND) {

			spitzen = grandSpitzen(zuReizendeSpielart);
			result = spitzenZaehlen(spitzen);
		}

		return result;
	}

	/**
	 * Ermittelt die Anzahl der Spitzen in einem &uuml;bergebenen Array, dass
	 * die Karten einer Farbe geordnet enth&auml;lt.
	 * 
	 * @param spitzen
	 *            - sortiertes Array mit den Karten einer Farbe
	 * @return Anzahl der Spitzen
	 */
	public int spitzenZaehlen(PlayingCard[] spitzen) {

		int result = 0;

		if (spitzen[0] != null) {

			result = mit(spitzen);
		}

		else {

			result = ohne(spitzen);
		}

		return result;
	}

	/**
	 * Bekommt ein SpielkartenArray mit allen Karten einer Farbe, absteigend
	 * geordnet und ermittelt daraus die Anzahl der fehlenden Spitzen.
	 * 
	 * @param spitzen
	 *            - Array, dass alle Karten einer Farbe enth&auml;lt
	 * @return anzahl der Spitzen
	 */
	public int ohne(PlayingCard[] spitzen) {

		int result = 0;

		for (int i = 1; i < spitzen.length; i++) {

			if (spitzen[i] != null) {

				result = i;
				break;
			}
		}

		return result;
	}

	/**
	 * Bekommt ein SpielkartenArray mit allen Karten einer Farbe, absteigend
	 * geordnet und ermittelt daraus die Anzahl der Spitzen.
	 * 
	 * @param spitzen
	 *            - Array, dass alle Karten einer Farbe enth&auml;lt
	 * @return anzahl der Spitzen
	 */
	public int mit(PlayingCard[] spitzen) {

		int result = 0;

		for (int i = 1; i < spitzen.length; i++) {

			if (spitzen[i] == null) {

				result = i;
				break;
			}
		}

		return result;
	}

	/**
	 * Gibt f&uuml;r ein &uuml;bergebenes Farbspiel die Spitzen in einem Array
	 * zur&uuml;ck.
	 * 
	 * @param zuReizendeSpielart
	 *            - das Farbspiel
	 * @return Array, dass die Spitzen enth&auml;lt
	 */
	public PlayingCard[] farbeSpitzen(IGameVariety zuReizendeSpielart) {

		int cardValue = 0;
		PlayingCard[] spitzen = new PlayingCard[13];
		ArrayList<PlayingCard> longSuit = determineShortLongSuit(true);

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getRank() == PlayingCard.Rank.UNDER_KNAVE) {

				cardValue = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankUnderKnavesHelp(cardValue)] = hand.get(i);
			}

			else if (hand.get(i).getSuit() == longSuit.get(0).getSuit()) {

				cardValue = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankSuitHelp(cardValue)] = hand.get(i);
			}
		}

		return spitzen;
	}

	/**
	 * Liefert ein Array mit allen Spitzen f&uuml;r ein Grandspiel.
	 * 
	 * @param zuReizendeSpielart
	 *            - das Grandspiel
	 * @return Array mit Spitzen
	 */
	public PlayingCard[] grandSpitzen(IGameVariety zuReizendeSpielart) {

		int cardValue = 0;
		PlayingCard[] spitzen = new PlayingCard[4];

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getRank() == PlayingCard.Rank.UNDER_KNAVE) {

				cardValue = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankUnderKnavesHelp(cardValue)] = hand.get(i);
			}
		}

		return spitzen;
	}

	/**
	 * Diese Methode entscheidet, welche Spielart der schlaue Spieler mit seinen
	 * Handkarten w&auml;len w&uuml;rde.
	 */
	public IGameVariety determineGameVariety() {

		// Damit der Computerspieler ein Nullspiel macht, darf er
		// keinen Buben auf der Hand haben und zudem muss er mindestens
		// 9 Karten mit den Wert 7,8 oder 9 haben.

		// Damit der Computerspieler einen Grand spielt, muss er
		// mindestens 4 Buben, oder 3 Buben und 3 Karten mit dem
		// Wert Ass oder 10 haben

		// Sonst wird Farbe gespielt, wobei Trumpfarbe die Farbe
		// ist, von der der Spieler die meisten Karten hat

		int kartenKleiner10 = 0;
		int buben = 0;
		int kartenGroesserKoenig = 0;

		ArrayList<PlayingCard> longSuit = determineShortLongSuit(true);

		// Spielart auf die der Spieler reizt
		IGameVariety zuReizendeSpielart;

		for (PlayingCard card : hand) {

			if (card.getRank() == PlayingCard.Rank.SEVEN || card.getRank() == PlayingCard.Rank.EIGHT
					|| card.getRank() == PlayingCard.Rank.NINE) {

				kartenKleiner10++;
			}

			if (card.getRank() == PlayingCard.Rank.UNDER_KNAVE) {

				buben++;
			}

			if (card.getRank() == PlayingCard.Rank.DAUS || card.getRank() == PlayingCard.Rank.TEN) {

				kartenGroesserKoenig++;
			}
		}

		cardsOfsuitCount = longSuit.size();

		// Auswertung
		if (kartenKleiner10 >= 5) {

			zuReizendeSpielart = new NullGame();
		}

		else if (buben >= 2 && kartenGroesserKoenig >= 4) {

			zuReizendeSpielart = new GrandGame();
		}

		else if ((buben >= 2 && cardsOfsuitCount >= 4)
				|| (buben < 2 && cardsOfsuitCount >= 6)) {

			zuReizendeSpielart = new SuitGame(longSuit.get(0).getSuit());
		}

		else {

			zuReizendeSpielart = null;
		}

		return zuReizendeSpielart;
	}

	/**
	 * Ermittelt die kurze oder lange Farbe und speichert diese in einer
	 * ArrayList. Buben werden nicht beachtet und werden entfernt.
	 * 
	 * @param isLong
	 *            - Wenn lang gleich true ist, wird die lange Farbe ermittelt,
	 *            ansonsten die Kurze.
	 * @return Die ArrayList von der Farbe mit den Karten, die am
	 *         h&auml;ufigsten bzw. am wenigsten vorkommt.
	 */
	public ArrayList<PlayingCard> determineShortLongSuit(boolean isLong) {

		ArrayList<PlayingCard> karo = cardsOfSuit(hand, PlayingCard.Suit.BELLS);
		ArrayList<PlayingCard> herz = cardsOfSuit(hand, PlayingCard.Suit.HEARTS);
		ArrayList<PlayingCard> pik = cardsOfSuit(hand, PlayingCard.Suit.LEAVES);
		ArrayList<PlayingCard> kreuz = cardsOfSuit(hand, PlayingCard.Suit.ACORNS);
		ArrayList<PlayingCard> winner = new ArrayList<PlayingCard>();

		// Die übergegebene Variable lang ist true, d.h. man möchte die lange
		// Farbe ermitteln
		if (isLong) {

			winner = ermittleLangeFarbe(karo, herz, pik, kreuz);
		}

		// Die übergegebene Variable lang ist false, d.h. man möchte die kurze
		// Farbe ermitteln
		else {

			winner = ermittleKurzeFarbe(karo, herz, pik, kreuz);
		}

		return winner;
	}

	/**
	 * Die Methode vergleicht die Arraylisten der Karten von einer Farbe und
	 * liefert die Liste der Karten von einer Farbe zur&uuml;ck, von der am
	 * wenigsten Karten vorhanden sind.
	 * 
	 * @param karo
	 *            - Die Liste mit der Anzahl der Karo-Karten.
	 * @param herz
	 *            - Die Liste mit der Anzahl der Herz-Karten.
	 * @param pik
	 *            - Die Liste mit der Anzahl der Pik-Karten.
	 * @param kreuz
	 *            - Die Liste mit der Anzahl der Kreuz-Karten.
	 * @return - Die Liste mit den wenigsten Karten von einer Farbe.
	 */
	public ArrayList<PlayingCard> ermittleKurzeFarbe(ArrayList<PlayingCard> karo,
			ArrayList<PlayingCard> herz, ArrayList<PlayingCard> pik,
			ArrayList<PlayingCard> kreuz) {

		ArrayList<PlayingCard> shortSuit = new ArrayList<PlayingCard>();

		if ((karo.size() < herz.size())
				&& (karo.size() != 0 && herz.size() != 0)) {

			shortSuit = karo;
		}

		else {

			if ((karo.size() != 0 && herz.size() != 0)) {

				shortSuit = herz;
			}
		}

		if ((pik.size() <= shortSuit.size()) && pik.size() != 0) {

			shortSuit = pik;
		}

		if ((kreuz.size() <= shortSuit.size()) && kreuz.size() != 0) {

			shortSuit = kreuz;
		}

		return shortSuit;
	}

	/**
	 * Die Methode vergleicht die Arraylisten der Karten von einer Farbe und
	 * liefert die Liste der Karten von einer Farbe zurück, von der am meisten
	 * Karten vorhanden sind.
	 * 
	 * @param karo
	 *            - Die Liste mit der Anzahl der Karo-Karten.
	 * @param herz
	 *            - Die Liste mit der Anzahl der Herz-Karten.
	 * @param pik
	 *            - Die Liste mit der Anzahl der Pik-Karten.
	 * @param kreuz
	 *            - Die Liste mit der Anzahl der Kreuz-Karten.
	 * @return - Die Liste mit den meisten Karten von einer Farbe.
	 */
	public ArrayList<PlayingCard> ermittleLangeFarbe(ArrayList<PlayingCard> karo,
			ArrayList<PlayingCard> herz, ArrayList<PlayingCard> pik,
			ArrayList<PlayingCard> kreuz) {

		ArrayList<PlayingCard> longSuit = new ArrayList<PlayingCard>();

		if (karo.size() > herz.size()) {

			longSuit = karo;
		}

		else {

			longSuit = herz;
		}

		if (pik.size() >= longSuit.size()) {

			longSuit = pik;
		}

		if (kreuz.size() >= longSuit.size()) {

			longSuit = kreuz;
		}

		return longSuit;
	}

	/**
	 * Ermittelt die Trumpffarbe, indem die Farbe als Trumpf gew&auml;lt wird,
	 * von der der Spieler am meisten hat.
	 * 
	 * @return Farbe, die am h&auml;ufigsten Vorkommt.
	 */
	public PlayingCard.Suit ermittleTrumpffarbe() {

		PlayingCard.Suit result = null;
		ArrayList<PlayingCard> winner = determineShortLongSuit(true);

		// Gewinner feststellen
		switch (winner.get(0).getSuit()) {

		case BELLS:
			result = PlayingCard.Suit.BELLS;
			break;
		case HEARTS:
			result = PlayingCard.Suit.HEARTS;
			break;
		case LEAVES:
			result = PlayingCard.Suit.LEAVES;
			break;
		case ACORNS:
			result = PlayingCard.Suit.ACORNS;
			break;
		default:
			System.err.println("Fehler in ermittleTrumpfarbe!");
		}
		return result;
	}

	/**
	 * Ermittelt die Augen eines Spielers nach Spielschluss.
	 * 
	 * @param tricks
	 *            - Stiche, die der Spieler gewonnen hat
	 * @return Augen des Spielers
	 */
	public int werteAugen(ArrayList<PlayingCard> tricks) {

		int result = 0;

		for (int i = 0; i < tricks.size(); i++) {

			if (tricks.get(i).getRank() == PlayingCard.Rank.DAUS) {

				result += 11;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.TEN) {

				result += 10;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.UNDER_KNAVE) {

				result += 2;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.OVER_KNAVE) {

				result += 3;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.KING) {

				result += 4;

			} else if (tricks.get(i).getRank() == PlayingCard.Rank.SIX) {

				result += 6;
			}
		}

		return result;
	}

	/**
	 * Diese Methode liefert für eine Karte den entsprechenden Augenwert.
	 * 
	 * @param card
	 *            - Die Karte f&uml;r die der Augenwert bestimmt werden soll.
	 * @return Die Augenzahl der &uml;bergebenen Karte.
	 */
	public int augenKarte(PlayingCard card) {

		int result;

		switch (card.getRank()) {
		case SIX:
			result = 6;
			break;
		case OVER_KNAVE:
			result = 3;
			break;
		case KING:
			result = 4;
			break;
		case TEN:
			result = 10;
			break;
		case DAUS:
			result = 11;
			break;
		case UNDER_KNAVE:
			result = 2;
			break;
		default:
			result = 0;
			break;
		}

		return result;
	}
}
