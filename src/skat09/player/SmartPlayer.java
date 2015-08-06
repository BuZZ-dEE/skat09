package skat09.player;

import java.util.ArrayList;
import java.util.Random;

import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.NullGame;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IGameVariety;


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

	//
	// Datenfelder
	//

	// Anzahl der Karten von einer Farbe
	int anzahlKartenVonFarbe;

	// Reizwert, bis zu dem der schlaue Spieler mitgeht.
	int maxReizwert;

	// Spielart, die der Schlaue Spieler spielen wuerde
	IGameVariety zuspielendeSpielart;

	// Das Anfangsblatt
	ArrayList<PlayingCard> anfangsBlatt;

	//
	// Konstruktor
	//

	public SmartPlayer(String name) {

		super(name);
		maxReizwert = -1;
		gameVariety = null;
	}

	//
	// get-Methoden
	//

	/**
	 * Gibt den maximalen Reizwert des Spielers zurück.
	 * 
	 * @return der maximale Reizwert, den der Spieler reizen kann
	 */
	public int getMaxReizwert() {

		return maxReizwert;
	}

	/**
	 * Gibt das Blatt, dass der Spieler am Anfang hat, bevor er irgendeine Karte
	 * gespielt hat.
	 * 
	 * @return das anfangsblatt, das der spieler bekommt, wenn ausgeteilt wurde
	 */
	public ArrayList<PlayingCard> getAnfangsBlatt() {

		return anfangsBlatt;
	}

	/**
	 * setzt das Anfangsblatt, das der Spieler zu beginn besitzt
	 * 
	 * @param blatt
	 */
	@SuppressWarnings("unchecked")
	public void setAnfangsblatt(ArrayList<PlayingCard> blatt) {

		anfangsBlatt = (ArrayList<PlayingCard>) blatt.clone();
	}

	/**
	 * setzt den Maximalen reizwert
	 * 
	 * @param maxReizwert
	 */
	public void setMaxReizwert(int maxReizwert) {

		this.maxReizwert = maxReizwert;
	}

	//
	// weitere Methoden
	//

	@Override
	public PlayingCard playCard(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = grandSpielen(gespielteKarten);
		}

		else if (gameVariety.getGameVariety() == GameVarietyName.SUIT) {

			ergebnis = farbeSpielen(gespielteKarten);
		}

		else if (gameVariety.getGameVariety() == GameVarietyName.NULL) {

			ergebnis = nullSpielen(gespielteKarten);
		}

		else if (gameVariety.getGameVariety() == GameVarietyName.RAMSCH) {

			ergebnis = ramschSpielen(gespielteKarten);
		}

		hand.remove(ergebnis);

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Grandspiel Sinn macht.
	 * 
	 * @param gespielteKarten
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Grands gespielt werden soll
	 */
	public PlayingCard grandSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gespielteKarten[0] == null) {

			ergebnis = rauskommenGrand(gespielteKarten);
		}

		else if (gespielteKarten[1] == null) {
			ergebnis = alsZweiterKarteSpielenGrand(gespielteKarten);
		}

		else if (gespielteKarten[2] == null) {

			ergebnis = alsDritterKarteSpielenGrand(gespielteKarten);
		}

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Farbspiel Sinn macht.
	 * 
	 * @param gespielteKarten
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Farbspiels gespielt werden soll
	 */
	public PlayingCard farbeSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gespielteKarten[0] == null) {

			ergebnis = rauskommen(gespielteKarten);
		}

		else if (gespielteKarten[1] == null) {

			ergebnis = alsZweiterKarteSpielen(gespielteKarten);
		}

		else if (gespielteKarten[2] == null) {

			ergebnis = alsDritterKarteSpielen(gespielteKarten);
		}

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Nullspiel Sinn macht.
	 * 
	 * @param gespielteKarten
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Nullspiels gespielt werden soll
	 */
	public PlayingCard nullSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gespielteKarten[0] == null) {

			ergebnis = rauskommenNull(gespielteKarten);
		}

		else if (gespielteKarten[1] == null) {

			ergebnis = alsZweiterKarteSpielenNull(gespielteKarten);
		}

		else if (gespielteKarten[2] == null) {

			ergebnis = alsDritterKarteSpielenNull(gespielteKarten);
		}

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uml;r, dass der Spieler eine Karte zum Spielen
	 * auswählt, die in einem Nullspiel Sinn macht.
	 * 
	 * @param gespielteKarten
	 *            - die Karten, die auf dem Tisch liegen
	 * @return die Karte, die im Falle eines Nullspiels gespielt werden soll
	 */
	public PlayingCard ramschSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gespielteKarten[0] == null) {

			ergebnis = rauskommenRamsch(gespielteKarten);
		}

		else if (gespielteKarten[1] == null) {

			ergebnis = alsZweiterKarteSpielenRamsch(gespielteKarten);
		}

		else if (gespielteKarten[2] == null) {

			ergebnis = alsDritterKarteSpielenRamsch(gespielteKarten);
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard rauskommenGrand(PlayingCard[] gespielteKarten) {

		ArrayList<PlayingCard> farbe = new ArrayList<PlayingCard>();
		PlayingCard ergebnis = null;
		Random zufallszahl = new Random();

		if (isDeclarer) {

			ergebnis = alleinspielerRauskommenGrand(gespielteKarten);
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsZweiterKarteSpielenGrand(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten);
		}

		else {

			if (gespielteKarten[0].getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				if (gameVariety
						.higherCard(
								gespielteKarten[0],
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));

				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsDritterKarteSpielenGrand(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = alleinspieleralsDritterKarteSpielenGrand(gespielteKarten);
		}

		else {

			if (gameVariety.higherCard(gespielteKarten[0], gespielteKarten[1])
					.getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard rauskommen(PlayingCard[] gespielteKarten) {

		ArrayList<PlayingCard> farbe = new ArrayList<PlayingCard>();
		PlayingCard ergebnis = null;
		Random zufallszahl = new Random();

		if (isDeclarer) {

			ergebnis = playRamdonAllowedCard(gespielteKarten);
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsZweiterKarteSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = playRamdonAllowedCard(gespielteKarten);
		}

		else {

			if (gespielteKarten[0].getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				if (gameVariety
						.higherCard(
								gespielteKarten[0],
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsDritterKarteSpielen(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = playRamdonAllowedCard(gespielteKarten);
		}

		else {

			if (gameVariety.higherCard(gespielteKarten[0], gespielteKarten[1])
					.getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard rauskommenNull(PlayingCard[] gespielteKarten) {

		ArrayList<PlayingCard> farbe = new ArrayList<PlayingCard>();
		PlayingCard ergebnis = null;
		Random zufallszahl = new Random();

		if (isDeclarer) {

			ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
		}

		else {

			if (teammate.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (teammate.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = playRamdonAllowedCard(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsZweiterKarteSpielenNull(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
		}

		else {

			if (gespielteKarten[0].getOwner().equals(teammate)) {

				ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
			}
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsDritterKarteSpielenNull(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (isDeclarer) {

			ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
		}

		else {

			if (gameVariety.higherCard(gespielteKarten[0], gespielteKarten[1])
					.getOwner().equals(teammate)) {

				ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
			}

			else {

				if (gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))
						&& gameVariety
								.higherCard(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
				}
			}
		}

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und rauskommen soll, eine Karte spielt.
	 * 
	 * @param gespielteKarten
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall ist das Array aber leer
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard alleinspielerRauskommenGrand(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;
		Random zufall = new Random();
		ArrayList<PlayingCard> buben = kartenEinesWertes(hand, Value.UNDER_KNAVE);
		ArrayList<PlayingCard> asse = kartenEinesWertes(hand, Value.DAUS);
		ArrayList<PlayingCard> zehnen = kartenEinesWertes(hand, Value.TEN);

		if (buben.size() > 2) {

			ergebnis = buben.get(zufall.nextInt(buben.size()));
		}

		else if (buben.size() == 2) {

			ergebnis = bubeSpielen(gespielteKarten, buben, zufall);

		}

		else if (!asse.isEmpty()) {

			for (PlayingCard karte : asse) {

				if (kartenEinerFarbe(anfangsBlatt, karte.getSuit()).size() <= 4) {

					ergebnis = asse.get(zufall.nextInt(asse.size()));
					break;

				}
			}

		}

		else if (!zehnen.isEmpty()) {

			for (PlayingCard karte : zehnen) {

				// if (kartenEinerFarbe(anfangsBlatt,
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

				if (kartenEinerFarbe(anfangsBlatt, karte.getSuit()).size() <= 4
						&& enthalten) {

					ergebnis = zehnen.get(zufall.nextInt(zehnen.size()));
					break;
				}
			}
		}

		if (ergebnis == null) {

			ergebnis = hand.get(zufall.nextInt(hand.size()));
		}

		return ergebnis;
	}

	/**
	 * Die Methode gibt aus einer Arrayliste mit Buben einen ausgewählten
	 * zufälligen Buben zurück. Dies tut sie aber nur, wenn in der Liste der
	 * Kreuz Bube oder der Pik Bube vorhanden ist.
	 * 
	 * @param gespielteKarten
	 *            - die bisher gespielten Karten auf dem Tisch
	 * @param buben
	 *            - die Arrayliste mit den Buben
	 * @param zufall
	 *            - Zufallswert
	 * @return der zufällig aus der Liste ausgewählte Bube
	 */
	public PlayingCard bubeSpielen(PlayingCard[] gespielteKarten,
			ArrayList<PlayingCard> buben, Random zufall) {

		PlayingCard ergebnis = null;

		for (PlayingCard karte : buben) {

			if (karte.equals(new PlayingCard(Suit.ACORNS, Value.UNDER_KNAVE))
					|| karte.equals(new PlayingCard(Suit.LEAVES, Value.UNDER_KNAVE))) {

				ergebnis = buben.get(zufall.nextInt(buben.size()));
				break;

			}
		}

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und als Zweiter eine Karte spielen soll, eine Karte
	 * spielt.
	 * 
	 * @param gespielteKarten
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall ist in dem Array eine Karte
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard alleinspieleralsZweiterKarteSpielenGrand(
			PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (gameVariety
				.higherCard(
						gespielteKarten[0],
						hoechsteSpielbareKarte(playableCards(gespielteKarten)))
				.equals(
						hoechsteSpielbareKarte(playableCards(gespielteKarten)))

				&& gameVariety
						.higherCard(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)),
								hoechsteSpielbareKarte(gegnerMoeglicheSpielbareKarten(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(playableCards(gespielteKarten)))) {

			ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
		}

		else {

			ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
		}

		// TODO loeschen
		System.out.println("alleinspieleralsZweiterKarteSpielenGrand"
				+ ergebnis.toString());

		return ergebnis;
	}

	/**
	 * Die Methode sorgt daf&uuml;r, dass der Spieler, wenn Grand gespielt wird,
	 * er Alleinspieler ist und als Dritter eine Karte spielen soll, eine Karte
	 * spielt.
	 * 
	 * @param gespielteKarten
	 *            - die aktuell gespielten Karten, die auf dem Tisch liegen. in
	 *            diesem Fall sind in dem Array zwei Karten
	 * @return die Karte, die gespielt wird
	 */
	public PlayingCard alleinspieleralsDritterKarteSpielenGrand(
			PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		if (((gameVariety.higherCard(
				hoechsteSpielbareKarte(playableCards(gespielteKarten)),
				gespielteKarten[0])
				.equals(hoechsteSpielbareKarte(playableCards(gespielteKarten)))) && gameVariety
				.higherCard(
						hoechsteSpielbareKarte(playableCards(gespielteKarten)),
						gespielteKarten[1])
				.equals(
						hoechsteSpielbareKarte(playableCards(gespielteKarten))))
				&& ((augenKarte(gespielteKarten[0]) > 3) && (augenKarte(gespielteKarten[1]) > 3))) {

			ergebnis = hoechsteSpielbareKarte(playableCards(gespielteKarten));
		}

		else {

			ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));
		}

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler spielt kommt raus, d.h. er spielt zuerst eine Karte.
	 * Das Array der gespielten Karten ist in diesem Fall leer.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard rauskommenRamsch(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als zweiter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten ist in diesem Fall eine Karte drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsZweiterKarteSpielenRamsch(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));

		return ergebnis;
	}

	/**
	 * Der schlaue Spieler ist als dritter dran eine Karte zu spielen. In dem
	 * Array der gespielten Karten sind in diesem Fall zwei Karten drin.
	 * 
	 * @param gespielteKarten
	 *            - Karten, die schon von anderen Mitspielern gespielt wurden
	 * @return die Karte, die der Spieler spielt
	 */
	public PlayingCard alsDritterKarteSpielenRamsch(PlayingCard[] gespielteKarten) {

		PlayingCard ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(playableCards(gespielteKarten));

		return ergebnis;
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
	 * @param gespielteKarten
	 *            - Karten, die gerade auf dem Tisch liegen
	 * @return Ein Liste mit den m&oumlglichen spielbaren Karten.
	 */
	public ArrayList<PlayingCard> gegnerMoeglicheSpielbareKarten(
			PlayingCard[] gespielteKarten) {

		System.out.println("gegnermoeglicheSpielbareKarten");
		ArrayList<PlayingCard> ergebnis = new ArrayList<PlayingCard>();
		ArrayList<PlayingCard> mGK = moeglicheGegnerKarten(allPlayedCards,
				gespielteKarten);

		for (int i = 0; i < mGK.size(); i++) {

			if (gameVariety
					.checkedPlayedCards(mGK, gespielteKarten, mGK.get(i))) {

				ergebnis.add(mGK.get(i));
			}
		}

		return ergebnis;
	}

	/**
	 * Die Methode ermittelt die Karten, die beide Gegner zusammen haben.
	 * 
	 * @param alleGespielteKarten
	 *            - alle Karten, die bisher gespielt wurden
	 * @param gespieltenKarten
	 *            - Karten, die gerade auf dem Tisch liegen
	 * @return Eine Liste mit den Karten, die beide Gegner zusammen haben.
	 */
	public ArrayList<PlayingCard> moeglicheGegnerKarten(
			ArrayList<PlayingCard> alleGespielteKarten,
			PlayingCard[] gespieltenKarten) {

		ArrayList<PlayingCard> ergebnis = new ArrayList<PlayingCard>();

		if (!deck.isEmpty()) {
			ergebnis.addAll(deck);
		}
		if (hand != null && !hand.isEmpty()) {
			ergebnis.removeAll(hand);
		}
		if (alleGespielteKarten != null && !alleGespielteKarten.isEmpty()) {
			ergebnis.removeAll(alleGespielteKarten);
		}
		if (skat != null && !skat.isEmpty()) {
			ergebnis.removeAll(skat);
		}

		for (int i = 0; i < gespieltenKarten.length; i++) {

			ergebnis.remove(gespieltenKarten[i]);
		}

		// TODO loeschen
		for (PlayingCard karte : ergebnis) {
			System.out.println("moeglicheGegnerKarten" + karte.toString());
		}

		return ergebnis;
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
	// ArrayList<Spielkarte> ergebnis = moeglicheGegnerKarten(
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
	 * @param kartenliste
	 *            - Liste für die in Abh&auml;ngigkeit ihrer L&auml;nge eine
	 *            Zufallszahl generiert werden soll.
	 * @return Die Zufallszahl
	 */
	public int zufallszahl(ArrayList<PlayingCard> kartenliste) {

		int ergebnis = -1;
		Random zufall = new Random();

		if (kartenliste.size() > 0) {

			ergebnis = zufall.nextInt(kartenliste.size());
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r eine Karte die n&auml;chst h&ouml;here Karte
	 * der gleichen Farbe zur&uuml;ck. Buben werden nicht direkt betrachtet.
	 * 
	 * @param karte
	 *            - karte zu der die n&aumlchst h&ouml;here Karte der gleichen
	 *            Farbe bestimmt werden soll
	 * @return die n&aumlchst h&ouml;here Karte
	 */
	public PlayingCard naechstHoehereKarte(Suit farbe, PlayingCard karte) {

		PlayingCard ergebnis = null;

		switch (karte.getValue()) {

		case SIX:
			ergebnis = new PlayingCard(farbe, Value.SEVEN);
			break;
		case SEVEN:
			ergebnis = new PlayingCard(farbe, Value.EIGHT);
			break;
		case EIGHT:
			ergebnis = new PlayingCard(farbe, Value.NINE);
			break;
		case NINE:
			ergebnis = naechstHoehereKarteNeun(farbe);
			break;
		case OVER_KNAVE:
			ergebnis = new PlayingCard(farbe, Value.KING);
			break;
		case KING:
			ergebnis = naechstHoehereKarteKoenig(farbe);
			break;
		case TEN:
			ergebnis = naechstHoehereKarteZehn(farbe);
			break;
		case DAUS:
			ergebnis = null;
			break;
		case UNDER_KNAVE:
			ergebnis = naechstHoehereKarteBube(farbe);
			break;
		default:
			ergebnis = null;
			break;
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "NEUN" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe von der die n&auml;chst niedrigere Karte verlang wird
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteNeun(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.OVER_KNAVE);
		} else {

			ergebnis = new PlayingCard(farbe, Value.TEN);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "KOENIG" die n&auml;chst
	 * h&ouml;here Karte der gleichen Farbe zurück. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe von der die n&auml;chst niedrigere Karte verlangt wird
	 * @return die n&aumlchst h&ouml;here Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstHoehereKarteKoenig(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.TEN);
		} else {

			ergebnis = new PlayingCard(farbe, Value.DAUS);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "ZEHN" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe der Nachfolgerkarte
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteZehn(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.DAUS);
		} else {

			ergebnis = new PlayingCard(farbe, Value.OVER_KNAVE);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "BUBE" die
	 * n&auml;chst h&ouml;here Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe der Nachfolgerkarte
	 * @return die n&aumlchst h&ouml;here Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstHoehereKarteBube(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND
				|| gameVariety.getGameVariety() == GameVarietyName.RAMSCH) {

			ergebnis = null;
		}

		else {

			ergebnis = new PlayingCard(farbe, Value.OVER_KNAVE);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r eine Karte die n&auml;chst niedrigere Karte
	 * der gleichen Farbe zur&uuml;ck. Buben werden nicht direkt betrachtet.
	 * 
	 * @param farbe
	 *            - Farbe der Folgekarte
	 * @param karte
	 *            - karte zu der die n&aumlchst niedrigere Karte der gleichen
	 *            Farbe bestimmt werden soll
	 * @return die n&aumlchst niedrigere Karte
	 */
	public PlayingCard naechstNiedrigereKarte(Suit farbe, PlayingCard karte) {

		PlayingCard ergebnis = null;

		switch (karte.getValue()) {

		case SIX:
			ergebnis = null;
			break;
		case SEVEN:
			ergebnis = new PlayingCard(farbe, Value.SIX);
			break;
		case EIGHT:
			ergebnis = new PlayingCard(farbe, Value.SEVEN);
			break;
		case NINE:
			ergebnis = new PlayingCard(farbe, Value.EIGHT);
			break;
		case OVER_KNAVE:
			ergebnis = naechstNiedrigereKarteDame(farbe);
			break;
		case KING:
			ergebnis = new PlayingCard(farbe, Value.OVER_KNAVE);
			break;
		case TEN:
			ergebnis = naechstNiedrigereKarteZehn(farbe);
			break;
		case DAUS:
			ergebnis = naechstNiedrigereKarteAss(farbe);
			break;
		case UNDER_KNAVE:
			ergebnis = naechstNiedrigereKarteBube(farbe);
			break;
		default:
			ergebnis = null;
			break;
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "DAME" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - suitGame der Folgekarte
	 * @return die n&aumlchst niedrigere Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstNiedrigereKarteDame(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.NINE);
		} else {

			ergebnis = new PlayingCard(farbe, Value.TEN);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert f&uuml;r die Karte mit dem Wert "ZEHN" die
	 * n&auml;chst niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies
	 * geschieht unter Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe von der die n&auml;chst niedrigste Karte verlangt wird
	 * @return die n&aumlchst niedrigere Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public PlayingCard naechstNiedrigereKarteZehn(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.KING);
		} else {

			ergebnis = new PlayingCard(farbe, Value.NINE);
		}

		return ergebnis;
	}

	/**
	 * Die Methode liefert für die Karte mit dem Wert "ASS" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe der zu suchenden Karte"
	 * @return die n&aumlchst niedrigere Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstNiedrigereKarteAss(Suit farbe) {

		PlayingCard ergebnis = null;

		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND) {

			ergebnis = new PlayingCard(farbe, Value.TEN);
		} else {

			ergebnis = new PlayingCard(farbe, Value.KING);
		}

		return ergebnis;
	}
	
	/**
	 * Die Methode liefert für die Karte mit dem Wert "BUBE" die n&auml;chst
	 * niedrigere Karte der gleichen Farbe zur&uuml;ck. Dies geschieht unter
	 * Betrachtung der Spielart.
	 * 
	 * @param farbe
	 *            - Farbe der zu suchenden Karte
	 * @return die n&aumlchst niedrigere Karte, in Abhängigkeit der Spielart
	 */
	public PlayingCard naechstNiedrigereKarteBube(Suit farbe) {
		
		PlayingCard ergebnis = null;
		
		if (gameVariety.getGameVariety() == GameVarietyName.SUIT
				|| gameVariety.getGameVariety() == GameVarietyName.GRAND
				|| gameVariety.getGameVariety() == GameVarietyName.RAMSCH) {

			ergebnis = null;
		} else {

			ergebnis = new PlayingCard(farbe, Value.TEN);
		}
		
		return ergebnis;
	}

	/**
	 * Diese Methode sucht in einem Blatt Karten eines bestimmten Wertes und
	 * packt diese in eine ArrayList.
	 * 
	 * @param blatt
	 *            - Das Blatt aus dem die Arrayliste eines Wertes geholt werden
	 *            soll.
	 * @param wert
	 *            - Der Wert einer Karte. z.B.: Ein Ass
	 * @return Die ArrayList mit den Assen.
	 */
	public ArrayList<PlayingCard> kartenEinesWertes(ArrayList<PlayingCard> blatt,
			Value wert) {

		ArrayList<PlayingCard> ergebnis = new ArrayList<PlayingCard>(4);

		for (PlayingCard karte : blatt) {

			if (karte.getValue() == wert) {

				ergebnis.add(karte);
			}
		}

		return ergebnis;
	}

	/**
	 * Diese Methode sucht in einem Blatt Karten einer bestimmten Farbe und
	 * packt diese in eine ArrayList. Buben werden nicht ber&uuml;cksichtigt.
	 * 
	 * @param blatt
	 *            - Das Blatt aus dem die Arrayliste einer Farbe geholt werden
	 *            soll.
	 * @param farbe
	 *            - Die Farbe einer Karte. z.B.: Kreuz
	 * @return Die ArrayList mit den Karten der Farbe Kreuz.
	 */
	public ArrayList<PlayingCard> kartenEinerFarbe(ArrayList<PlayingCard> blatt,
			Suit farbe) {

		ArrayList<PlayingCard> ergebnis = new ArrayList<PlayingCard>();

		for (PlayingCard karte : blatt) {

			if (karte.getSuit() == farbe && karte.getValue() != Value.UNDER_KNAVE) {

				ergebnis.add(karte);
			}
		}

		return ergebnis;
	}

	/**
	 * Es wird die h&ouml;chste spielbare Karte aus den spielbaren Karten
	 * bestimmt und zur&uuml;ck geliefert.
	 * 
	 * @param spielbareKarten
	 *            - Eine Liste mit den m&ouml;glichen zu spielenden Karten.
	 * @return Die h&ouml;chste spielbare Karte.
	 */
	public PlayingCard hoechsteSpielbareKarte(
			ArrayList<PlayingCard> spielbareKarten) {

		PlayingCard ergebnis = spielbareKarten.get(0);

		for (int i = 1; i < spielbareKarten.size(); i++) {

			ergebnis = hoechsteSpielbareKarteBestimmen(ergebnis,
					spielbareKarten.get(i));
		}

		return ergebnis;
	}

	/**
	 * Es wird die niedrigste spielbare Karte aus den spielbaren Karten bestimmt
	 * und zur&uuml;ck geliefert. Die Farbe ist bei der Auswertung nicht
	 * relevant.
	 * 
	 * @param spielbareKarten
	 *            - Eine Liste mit den m&ouml;glichen zu spielenden Karten.
	 * @return Die niedrigste spielbare Karte.
	 */
	public PlayingCard niedrigsteSpielbareKarte(
			ArrayList<PlayingCard> spielbareKarten) {

		PlayingCard ergebnis = spielbareKarten.get(0);

		for (int i = 1; i < spielbareKarten.size(); i++) {

			ergebnis = niedrigsteSpielbareKarteBestimmen(ergebnis,
					spielbareKarten.get(i));
		}

		return ergebnis;
	}

	/**
	 * Von zwei Karten wird h&ouml;chste Karte bestimmt und zur&uuml;ck
	 * geliefert. Dies geschieht in Abh&auml;ngigkeit von der Spielart und nur
	 * aufgrund der Kartenwerte, d.h. unabh&auml;ngig von der Farbe.
	 * 
	 * @param karte1
	 *            - Die erste Karte.
	 * @param karte2
	 *            - Die zweite Karte.
	 * @return - Die h&ouml;here von zwei Karten.
	 */
	public PlayingCard hoechsteSpielbareKarteBestimmen(PlayingCard karte1,
			PlayingCard karte2) {

		PlayingCard ergebnis = null;

		if (gameVariety.evaluateCard(karte1) == gameVariety.evaluateCard(karte2)) {

			ergebnis = karte1;
		}

		else if (gameVariety.evaluateCard(karte1) < gameVariety
				.evaluateCard(karte2)) {

			ergebnis = karte2;
		}

		else if (gameVariety.evaluateCard(karte1) > gameVariety
				.evaluateCard(karte2)) {

			ergebnis = karte1;
		}

		return ergebnis;
	}

	/**
	 * Von zwei Karten wird niedrigste Karte bestimmt und zur&uuml;ck geliefert.
	 * Dies geschieht in Abh&auml;ngigkeit von der Spielart und nur aufgrund der
	 * Kartenwerte, d.h. unabh&auml;ngig von der Farbe.
	 * 
	 * @param karte1
	 *            - Die erste Karte.
	 * @param karte2
	 *            - Die zweite Karte.
	 * @return - Die niedrigere von zwei Karten.
	 */
	public PlayingCard niedrigsteSpielbareKarteBestimmen(PlayingCard karte1,
			PlayingCard karte2) {

		PlayingCard ergebnis = null;

		if (gameVariety.evaluateCard(karte1) == gameVariety.evaluateCard(karte2)) {

			ergebnis = karte1;
		}

		else if (gameVariety.evaluateCard(karte1) < gameVariety
				.evaluateCard(karte2)) {

			ergebnis = karte1;
		}

		else if (gameVariety.evaluateCard(karte1) > gameVariety
				.evaluateCard(karte2)) {

			ergebnis = karte2;
		}

		if (ergebnis == null) {
			ergebnis = karte1;
		}

		return ergebnis;
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

		IGameVariety zuSpielendeSpielart = bestimmeSpielart();

		if (zuSpielendeSpielart == null) {

			zuSpielendeSpielart = new NullGame();
		}

		return zuSpielendeSpielart;
	}

	@Override
	public boolean respond(int reizwert) {

		boolean ergebnis = false;

		if (reizwert <= maxReizwert) {

			ergebnis = true;
		}

		return ergebnis;
	}

	@Override
	public boolean bid(int reizWert) {

		boolean ergebnis = false;

		if (reizWert <= maxReizwert) {

			ergebnis = true;
		}

		return ergebnis;
	}

	@Override
	public SuitGame suit() {

		SuitGame farbe = new SuitGame(ermittleTrumpffarbe());

		return farbe;
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

		IGameVariety zuReizendeSpielart = bestimmeSpielart();
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

			maxReizwert = -1;
		}
	}

	/**
	 * Ermittelt den maxReizwert, sofern ein Farbspiel gespielt wird.
	 * 
	 * @param ermittelteSpitzen
	 *            - Spitzen des Spielers
	 */
	public void maxReizwertFarbe(int ermittelteSpitzen) {

		Suit farbe = ermittleTrumpffarbe();

		maxReizwert = (ermittelteSpitzen + 1) * farbe.value();
	}

	/**
	 * Berechnet den maxReizwert des Spielers, sofern ein Grandspiel gemacht
	 * wird.
	 * 
	 * @param ermittelteSpitzen
	 *            - die Spitzen des Spielers
	 */
	public void maxReizwertGrand(int ermittelteSpitzen) {

		maxReizwert = (ermittelteSpitzen + 1) * 24;
	}

	/**
	 * Berechnet den maxReizwert des Spielers bei einem Nullspiel.
	 * 
	 * Befindet sich im Ausbau.
	 */
	public void maxReizwertNull() {

		maxReizwert = 23;
	}

	/**
	 * Ermittelt die Spitzen des Spielers f&uuml;r eine bestimmte Spielart.
	 * 
	 * @param zuReizendeSpielart
	 *            - Spielart, f&uuml;r die die Spitzen ermittelt werden soll
	 * @return Zahl der Spitzen
	 */
	public int ermittleSpitzen(IGameVariety zuReizendeSpielart) {

		int ergebnis = 0;
		PlayingCard[] spitzen;

		if (zuReizendeSpielart.getGameVariety() == GameVarietyName.SUIT) {

			spitzen = farbeSpitzen(zuReizendeSpielart);
			ergebnis = spitzenZaehlen(spitzen);
		}

		else if (zuReizendeSpielart.getGameVariety() == GameVarietyName.GRAND) {

			spitzen = grandSpitzen(zuReizendeSpielart);
			ergebnis = spitzenZaehlen(spitzen);
		}

		return ergebnis;
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

		int ergebnis = 0;

		if (spitzen[0] != null) {

			ergebnis = mit(spitzen);
		}

		else {

			ergebnis = ohne(spitzen);
		}

		return ergebnis;
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

		int ergebnis = 0;

		for (int i = 1; i < spitzen.length; i++) {

			if (spitzen[i] != null) {

				ergebnis = i;
				break;
			}
		}

		return ergebnis;
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

		int ergebnis = 0;

		for (int i = 1; i < spitzen.length; i++) {

			if (spitzen[i] == null) {

				ergebnis = i;
				break;
			}
		}

		return ergebnis;
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

		int kartenwert = 0;
		PlayingCard[] spitzen = new PlayingCard[13];
		ArrayList<PlayingCard> langeFarbe = ermittleKurzeLangeFarbe(true);

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getValue() == Value.UNDER_KNAVE) {

				kartenwert = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankUnderKnavesHelp(kartenwert)] = hand.get(i);
			}

			else if (hand.get(i).getSuit() == langeFarbe.get(0).getSuit()) {

				kartenwert = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankSuitHelp(kartenwert)] = hand.get(i);
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

		int kartenwert = 0;
		PlayingCard[] spitzen = new PlayingCard[4];

		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getValue() == Value.UNDER_KNAVE) {

				kartenwert = zuReizendeSpielart.evaluateCard(hand.get(i));
				spitzen[rankUnderKnavesHelp(kartenwert)] = hand.get(i);
			}
		}

		return spitzen;
	}

	/**
	 * Diese Methode entscheidet, welche Spielart der schlaue Spieler mit seinen
	 * Handkarten w&auml;len w&uuml;rde.
	 */
	public IGameVariety bestimmeSpielart() {

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

		ArrayList<PlayingCard> langeFarbe = ermittleKurzeLangeFarbe(true);

		// Spielart auf die der Spieler reizt
		IGameVariety zuReizendeSpielart;

		for (PlayingCard karte : hand) {

			if (karte.getValue() == Value.SEVEN || karte.getValue() == Value.EIGHT
					|| karte.getValue() == Value.NINE) {

				kartenKleiner10++;
			}

			if (karte.getValue() == Value.UNDER_KNAVE) {

				buben++;
			}

			if (karte.getValue() == Value.DAUS || karte.getValue() == Value.TEN) {

				kartenGroesserKoenig++;
			}
		}

		anzahlKartenVonFarbe = langeFarbe.size();

		// Auswertung
		if (kartenKleiner10 >= 5) {

			zuReizendeSpielart = new NullGame();
		}

		else if (buben >= 2 && kartenGroesserKoenig >= 4) {

			zuReizendeSpielart = new GrandGame();
		}

		else if ((buben >= 2 && anzahlKartenVonFarbe >= 4)
				|| (buben < 2 && anzahlKartenVonFarbe >= 6)) {

			zuReizendeSpielart = new SuitGame(langeFarbe.get(0).getSuit());
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
	 * @param lang
	 *            - Wenn lang gleich true ist, wird die lange Farbe ermittelt,
	 *            ansonsten die Kurze.
	 * @return Die ArrayList von der Farbe mit den Karten, die am
	 *         h&auml;ufigsten bzw. am wenigsten vorkommt.
	 */
	public ArrayList<PlayingCard> ermittleKurzeLangeFarbe(boolean lang) {

		ArrayList<PlayingCard> karo = kartenEinerFarbe(hand, Suit.BELLS);
		ArrayList<PlayingCard> herz = kartenEinerFarbe(hand, Suit.HEARTS);
		ArrayList<PlayingCard> pik = kartenEinerFarbe(hand, Suit.LEAVES);
		ArrayList<PlayingCard> kreuz = kartenEinerFarbe(hand, Suit.ACORNS);
		ArrayList<PlayingCard> gewinner = new ArrayList<PlayingCard>();

		// Die übergegebene Variable lang ist true, d.h. man möchte die lange
		// Farbe ermitteln
		if (lang) {

			gewinner = ermittleLangeFarbe(karo, herz, pik, kreuz);
		}

		// Die übergegebene Variable lang ist false, d.h. man möchte die kurze
		// Farbe ermitteln
		else {

			gewinner = ermittleKurzeFarbe(karo, herz, pik, kreuz);
		}

		return gewinner;
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

		ArrayList<PlayingCard> kurzeFarbe = new ArrayList<PlayingCard>();

		if ((karo.size() < herz.size())
				&& (karo.size() != 0 && herz.size() != 0)) {

			kurzeFarbe = karo;
		}

		else {

			if ((karo.size() != 0 && herz.size() != 0)) {

				kurzeFarbe = herz;
			}
		}

		if ((pik.size() <= kurzeFarbe.size()) && pik.size() != 0) {

			kurzeFarbe = pik;
		}

		if ((kreuz.size() <= kurzeFarbe.size()) && kreuz.size() != 0) {

			kurzeFarbe = kreuz;
		}

		return kurzeFarbe;
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

		ArrayList<PlayingCard> langeFarbe = new ArrayList<PlayingCard>();

		if (karo.size() > herz.size()) {

			langeFarbe = karo;
		}

		else {

			langeFarbe = herz;
		}

		if (pik.size() >= langeFarbe.size()) {

			langeFarbe = pik;
		}

		if (kreuz.size() >= langeFarbe.size()) {

			langeFarbe = kreuz;
		}

		return langeFarbe;
	}

	/**
	 * Ermittelt die Trumpffarbe, indem die Farbe als Trumpf gew&auml;lt wird,
	 * von der der Spieler am meisten hat.
	 * 
	 * @return Farbe, die am h&auml;ufigsten Vorkommt.
	 */
	public Suit ermittleTrumpffarbe() {

		Suit ergebnis = null;
		ArrayList<PlayingCard> gewinner = ermittleKurzeLangeFarbe(true);

		// Gewinner feststellen
		switch (gewinner.get(0).getSuit()) {

		case BELLS:
			ergebnis = Suit.BELLS;
			break;
		case HEARTS:
			ergebnis = Suit.HEARTS;
			break;
		case LEAVES:
			ergebnis = Suit.LEAVES;
			break;
		case ACORNS:
			ergebnis = Suit.ACORNS;
			break;
		default:
			System.err.println("Fehler in ermittleTrumpfarbe!");
		}
		return ergebnis;
	}

	/**
	 * Ermittelt die Augen eines Spielers nach Spielschluss.
	 * 
	 * @param stiche
	 *            - Stiche, die der Spieler gewonnen hat
	 * @return Augen des Spielers
	 */
	public int werteAugen(ArrayList<PlayingCard> stiche) {

		int erg = 0;

		for (int i = 0; i < stiche.size(); i++) {

			if (stiche.get(i).getValue() == Value.DAUS) {

				erg += 11;

			} else if (stiche.get(i).getValue() == Value.TEN) {

				erg += 10;

			} else if (stiche.get(i).getValue() == Value.UNDER_KNAVE) {

				erg += 2;

			} else if (stiche.get(i).getValue() == Value.OVER_KNAVE) {

				erg += 3;

			} else if (stiche.get(i).getValue() == Value.KING) {

				erg += 4;

			} else if (stiche.get(i).getValue() == Value.SIX) {

				erg += 6;
			}
		}

		return erg;
	}

	/**
	 * Diese Methode liefert für eine Karte den entsprechenden Augenwert.
	 * 
	 * @param karte
	 *            - Die Karte f&uml;r die der Augenwert bestimmt werden soll.
	 * @return Die Augenzahl der &uml;bergebenen Karte.
	 */
	public int augenKarte(PlayingCard karte) {

		int ergebnis;

		switch (karte.getValue()) {
		case SIX:
			ergebnis = 6;
			break;
		case OVER_KNAVE:
			ergebnis = 3;
			break;
		case KING:
			ergebnis = 4;
			break;
		case TEN:
			ergebnis = 10;
			break;
		case DAUS:
			ergebnis = 11;
			break;
		case UNDER_KNAVE:
			ergebnis = 2;
			break;
		default:
			ergebnis = 0;
			break;
		}

		return ergebnis;
	}
}
