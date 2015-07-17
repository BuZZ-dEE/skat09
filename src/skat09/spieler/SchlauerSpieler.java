package skat09.spieler;

import java.util.ArrayList;
import java.util.Random;

import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.ISpielart;


/**
 * Die Klasse schlauer Spieler erzeugt einen Computerspieler, der sich dem Spiel
 * angemessen verh&auml;lt und &uuml;ber eine gewisse k&uuml;nstliche
 * Intelligenz verf&uuml;gt. Verschiedene Implementierungen sind denkbar.
 * 
 * @author Anne-Christin Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class SchlauerSpieler extends Player {

	//
	// Datenfelder
	//

	// Anzahl der Karten von einer Farbe
	int anzahlKartenVonFarbe;

	// Reizwert, bis zu dem der schlaue Spieler mitgeht.
	int maxReizwert;

	// Spielart, die der Schlaue Spieler spielen wuerde
	ISpielart zuspielendeSpielart;

	// Das Anfangsblatt
	ArrayList<Spielkarte> anfangsBlatt;

	//
	// Konstruktor
	//

	public SchlauerSpieler(String name) {

		super(name);
		maxReizwert = -1;
		spielart = null;
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
	public ArrayList<Spielkarte> getAnfangsBlatt() {

		return anfangsBlatt;
	}

	/**
	 * setzt das Anfangsblatt, das der Spieler zu beginn besitzt
	 * 
	 * @param blatt
	 */
	@SuppressWarnings("unchecked")
	public void setAnfangsblatt(ArrayList<Spielkarte> blatt) {

		anfangsBlatt = (ArrayList<Spielkarte>) blatt.clone();
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
	public Spielkarte spieleKarte(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = grandSpielen(gespielteKarten);
		}

		else if (spielart.getSpielart() == Spielartbezeichnung.FARBE) {

			ergebnis = farbeSpielen(gespielteKarten);
		}

		else if (spielart.getSpielart() == Spielartbezeichnung.NULL) {

			ergebnis = nullSpielen(gespielteKarten);
		}

		else if (spielart.getSpielart() == Spielartbezeichnung.RAMSCH) {

			ergebnis = ramschSpielen(gespielteKarten);
		}

		blatt.remove(ergebnis);

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
	public Spielkarte grandSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

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
	public Spielkarte farbeSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

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
	public Spielkarte nullSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

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
	public Spielkarte ramschSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

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
	public Spielkarte rauskommenGrand(Spielkarte[] gespielteKarten) {

		ArrayList<Spielkarte> farbe = new ArrayList<Spielkarte>();
		Spielkarte ergebnis = null;
		Random zufallszahl = new Random();

		if (istAlleinspieler) {

			ergebnis = alleinspielerRauskommenGrand(gespielteKarten);
		}

		else {

			if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
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
	public Spielkarte alsZweiterKarteSpielenGrand(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = alleinspieleralsZweiterKarteSpielenGrand(gespielteKarten);
		}

		else {

			if (gespielteKarten[0].getBesitzer().equals(mitspieler)) {

				ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				if (spielart
						.hoehereKarte(
								gespielteKarten[0],
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));

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
	public Spielkarte alsDritterKarteSpielenGrand(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = alleinspieleralsDritterKarteSpielenGrand(gespielteKarten);
		}

		else {

			if (spielart.hoehereKarte(gespielteKarten[0], gespielteKarten[1])
					.getBesitzer().equals(mitspieler)) {

				ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				if (spielart
						.hoehereKarte(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
						&& spielart
								.hoehereKarte(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte rauskommen(Spielkarte[] gespielteKarten) {

		ArrayList<Spielkarte> farbe = new ArrayList<Spielkarte>();
		Spielkarte ergebnis = null;
		Random zufallszahl = new Random();

		if (istAlleinspieler) {

			ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
		}

		else {

			if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
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
	public Spielkarte alsZweiterKarteSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
		}

		else {

			if (gespielteKarten[0].getBesitzer().equals(mitspieler)) {

				ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				if (spielart
						.hoehereKarte(
								gespielteKarten[0],
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte alsDritterKarteSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
		}

		else {

			if (spielart.hoehereKarte(gespielteKarten[0], gespielteKarten[1])
					.getBesitzer().equals(mitspieler)) {

				ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				if (spielart
						.hoehereKarte(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
						&& spielart
								.hoehereKarte(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

					ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte rauskommenNull(Spielkarte[] gespielteKarten) {

		ArrayList<Spielkarte> farbe = new ArrayList<Spielkarte>();
		Spielkarte ergebnis = null;
		Random zufallszahl = new Random();

		if (istAlleinspieler) {

			ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
		}

		else {

			if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 1) % 3)) {

				farbe = ermittleKurzeLangeFarbe(false);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
				}

				else {

					ergebnis = farbe.get(zufallszahl.nextInt(farbe.size()));
				}
			}

			else if (mitspieler.getPosition().ordinal() == ((position.ordinal() + 2) % 3)) {

				farbe = ermittleKurzeLangeFarbe(true);

				if (farbe.isEmpty()) {

					ergebnis = zufaelligErlaubteKarteSpielen(gespielteKarten);
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
	public Spielkarte alsZweiterKarteSpielenNull(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
		}

		else {

			if (gespielteKarten[0].getBesitzer().equals(mitspieler)) {

				ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte alsDritterKarteSpielenNull(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (istAlleinspieler) {

			ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
		}

		else {

			if (spielart.hoehereKarte(gespielteKarten[0], gespielteKarten[1])
					.getBesitzer().equals(mitspieler)) {

				ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
			}

			else {

				if (spielart
						.hoehereKarte(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
								gespielteKarten[0])
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
						&& spielart
								.hoehereKarte(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
										gespielteKarten[1])
								.equals(
										hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
				}

				else {

					ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte alleinspielerRauskommenGrand(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;
		Random zufall = new Random();
		ArrayList<Spielkarte> buben = kartenEinesWertes(blatt, Wert.BUBE);
		ArrayList<Spielkarte> asse = kartenEinesWertes(blatt, Wert.ASS);
		ArrayList<Spielkarte> zehnen = kartenEinesWertes(blatt, Wert.ZEHN);

		if (buben.size() > 2) {

			ergebnis = buben.get(zufall.nextInt(buben.size()));
		}

		else if (buben.size() == 2) {

			ergebnis = bubeSpielen(gespielteKarten, buben, zufall);

		}

		else if (!asse.isEmpty()) {

			for (Spielkarte karte : asse) {

				if (kartenEinerFarbe(anfangsBlatt, karte.getFarbe()).size() <= 4) {

					ergebnis = asse.get(zufall.nextInt(asse.size()));
					break;

				}
			}

		}

		else if (!zehnen.isEmpty()) {

			for (Spielkarte karte : zehnen) {

				// if (kartenEinerFarbe(anfangsBlatt,
				// karte.getFarbe()).size() <= 4
				// && alleGespielteKarten.contains(naechstHoehereKarte(
				// karte.getFarbe(), karte))) {
				//
				// ergebnis = zehnen.get(zufall.nextInt(zehnen.size()));
				// break;
				// }

				boolean enthalten = false;
				Spielkarte hoehereKarte = naechstHoehereKarte(karte.getFarbe(),
						karte);
				for (Spielkarte karte2 : alleGespielteKarten) {

					if (karte2.equals(hoehereKarte)) {
						enthalten = true;
					}
				}

				if (kartenEinerFarbe(anfangsBlatt, karte.getFarbe()).size() <= 4
						&& enthalten) {

					ergebnis = zehnen.get(zufall.nextInt(zehnen.size()));
					break;
				}
			}
		}

		if (ergebnis == null) {

			ergebnis = blatt.get(zufall.nextInt(blatt.size()));
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
	public Spielkarte bubeSpielen(Spielkarte[] gespielteKarten,
			ArrayList<Spielkarte> buben, Random zufall) {

		Spielkarte ergebnis = null;

		for (Spielkarte karte : buben) {

			if (karte.equals(new Spielkarte(Farbe.KREUZ, Wert.BUBE))
					|| karte.equals(new Spielkarte(Farbe.PIK, Wert.BUBE))) {

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
	public Spielkarte alleinspieleralsZweiterKarteSpielenGrand(
			Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (spielart
				.hoehereKarte(
						gespielteKarten[0],
						hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))
				.equals(
						hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))

				&& spielart
						.hoehereKarte(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
								hoechsteSpielbareKarte(gegnerMoeglicheSpielbareKarten(gespielteKarten)))
						.equals(
								hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) {

			ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
		}

		else {

			ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte alleinspieleralsDritterKarteSpielenGrand(
			Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		if (((spielart.hoehereKarte(
				hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
				gespielteKarten[0])
				.equals(hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)))) && spielart
				.hoehereKarte(
						hoechsteSpielbareKarte(spielbareKarten(gespielteKarten)),
						gespielteKarten[1])
				.equals(
						hoechsteSpielbareKarte(spielbareKarten(gespielteKarten))))
				&& ((augenKarte(gespielteKarten[0]) > 3) && (augenKarte(gespielteKarten[1]) > 3))) {

			ergebnis = hoechsteSpielbareKarte(spielbareKarten(gespielteKarten));
		}

		else {

			ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));
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
	public Spielkarte rauskommenRamsch(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));

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
	public Spielkarte alsZweiterKarteSpielenRamsch(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));

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
	public Spielkarte alsDritterKarteSpielenRamsch(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;

		ergebnis = niedrigsteSpielbareKarte(spielbareKarten(gespielteKarten));

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
	public ArrayList<Spielkarte> gegnerMoeglicheSpielbareKarten(
			Spielkarte[] gespielteKarten) {

		System.out.println("gegnermoeglicheSpielbareKarten");
		ArrayList<Spielkarte> ergebnis = new ArrayList<Spielkarte>();
		ArrayList<Spielkarte> mGK = moeglicheGegnerKarten(alleGespielteKarten,
				gespielteKarten);

		for (int i = 0; i < mGK.size(); i++) {

			if (spielart
					.gespielteKartePruefen(mGK, gespielteKarten, mGK.get(i))) {

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
	public ArrayList<Spielkarte> moeglicheGegnerKarten(
			ArrayList<Spielkarte> alleGespielteKarten,
			Spielkarte[] gespieltenKarten) {

		ArrayList<Spielkarte> ergebnis = new ArrayList<Spielkarte>();

		if (!deck.isEmpty()) {
			ergebnis.addAll(deck);
		}
		if (blatt != null && !blatt.isEmpty()) {
			ergebnis.removeAll(blatt);
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
		for (Spielkarte karte : ergebnis) {
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
	public int zufallszahl(ArrayList<Spielkarte> kartenliste) {

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
	public Spielkarte naechstHoehereKarte(Farbe farbe, Spielkarte karte) {

		Spielkarte ergebnis = null;

		switch (karte.getWert()) {

		case SECHS:
			ergebnis = new Spielkarte(farbe, Wert.SIEBEN);
			break;
		case SIEBEN:
			ergebnis = new Spielkarte(farbe, Wert.ACHT);
			break;
		case ACHT:
			ergebnis = new Spielkarte(farbe, Wert.NEUN);
			break;
		case NEUN:
			ergebnis = naechstHoehereKarteNeun(farbe);
			break;
		case DAME:
			ergebnis = new Spielkarte(farbe, Wert.KOENIG);
			break;
		case KOENIG:
			ergebnis = naechstHoehereKarteKoenig(farbe);
			break;
		case ZEHN:
			ergebnis = naechstHoehereKarteZehn(farbe);
			break;
		case ASS:
			ergebnis = null;
			break;
		case BUBE:
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
	public Spielkarte naechstHoehereKarteNeun(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.DAME);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.ZEHN);
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
	public Spielkarte naechstHoehereKarteKoenig(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.ZEHN);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.ASS);
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
	public Spielkarte naechstHoehereKarteZehn(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.ASS);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.DAME);
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
	public Spielkarte naechstHoehereKarteBube(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND
				|| spielart.getSpielart() == Spielartbezeichnung.RAMSCH) {

			ergebnis = null;
		}

		else {

			ergebnis = new Spielkarte(farbe, Wert.DAME);
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
	public Spielkarte naechstNiedrigereKarte(Farbe farbe, Spielkarte karte) {

		Spielkarte ergebnis = null;

		switch (karte.getWert()) {

		case SECHS:
			ergebnis = null;
			break;
		case SIEBEN:
			ergebnis = new Spielkarte(farbe, Wert.SECHS);
			break;
		case ACHT:
			ergebnis = new Spielkarte(farbe, Wert.SIEBEN);
			break;
		case NEUN:
			ergebnis = new Spielkarte(farbe, Wert.ACHT);
			break;
		case DAME:
			ergebnis = naechstNiedrigereKarteDame(farbe);
			break;
		case KOENIG:
			ergebnis = new Spielkarte(farbe, Wert.DAME);
			break;
		case ZEHN:
			ergebnis = naechstNiedrigereKarteZehn(farbe);
			break;
		case ASS:
			ergebnis = naechstNiedrigereKarteAss(farbe);
			break;
		case BUBE:
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
	 *            - farbe der Folgekarte
	 * @return die n&aumlchst niedrigere Karte, in Abh&auml;ngigkeit der
	 *         Spielart
	 */
	public Spielkarte naechstNiedrigereKarteDame(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.NEUN);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.ZEHN);
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
	public Spielkarte naechstNiedrigereKarteZehn(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.KOENIG);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.NEUN);
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
	public Spielkarte naechstNiedrigereKarteAss(Farbe farbe) {

		Spielkarte ergebnis = null;

		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			ergebnis = new Spielkarte(farbe, Wert.ZEHN);
		} else {

			ergebnis = new Spielkarte(farbe, Wert.KOENIG);
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
	public Spielkarte naechstNiedrigereKarteBube(Farbe farbe) {
		
		Spielkarte ergebnis = null;
		
		if (spielart.getSpielart() == Spielartbezeichnung.FARBE
				|| spielart.getSpielart() == Spielartbezeichnung.GRAND
				|| spielart.getSpielart() == Spielartbezeichnung.RAMSCH) {

			ergebnis = null;
		} else {

			ergebnis = new Spielkarte(farbe, Wert.ZEHN);
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
	public ArrayList<Spielkarte> kartenEinesWertes(ArrayList<Spielkarte> blatt,
			Wert wert) {

		ArrayList<Spielkarte> ergebnis = new ArrayList<Spielkarte>(4);

		for (Spielkarte karte : blatt) {

			if (karte.getWert() == wert) {

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
	public ArrayList<Spielkarte> kartenEinerFarbe(ArrayList<Spielkarte> blatt,
			Farbe farbe) {

		ArrayList<Spielkarte> ergebnis = new ArrayList<Spielkarte>();

		for (Spielkarte karte : blatt) {

			if (karte.getFarbe() == farbe && karte.getWert() != Wert.BUBE) {

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
	public Spielkarte hoechsteSpielbareKarte(
			ArrayList<Spielkarte> spielbareKarten) {

		Spielkarte ergebnis = spielbareKarten.get(0);

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
	public Spielkarte niedrigsteSpielbareKarte(
			ArrayList<Spielkarte> spielbareKarten) {

		Spielkarte ergebnis = spielbareKarten.get(0);

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
	public Spielkarte hoechsteSpielbareKarteBestimmen(Spielkarte karte1,
			Spielkarte karte2) {

		Spielkarte ergebnis = null;

		if (spielart.karteBewerten(karte1) == spielart.karteBewerten(karte2)) {

			ergebnis = karte1;
		}

		else if (spielart.karteBewerten(karte1) < spielart
				.karteBewerten(karte2)) {

			ergebnis = karte2;
		}

		else if (spielart.karteBewerten(karte1) > spielart
				.karteBewerten(karte2)) {

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
	public Spielkarte niedrigsteSpielbareKarteBestimmen(Spielkarte karte1,
			Spielkarte karte2) {

		Spielkarte ergebnis = null;

		if (spielart.karteBewerten(karte1) == spielart.karteBewerten(karte2)) {

			ergebnis = karte1;
		}

		else if (spielart.karteBewerten(karte1) < spielart
				.karteBewerten(karte2)) {

			ergebnis = karte1;
		}

		else if (spielart.karteBewerten(karte1) > spielart
				.karteBewerten(karte2)) {

			ergebnis = karte2;
		}

		if (ergebnis == null) {
			ergebnis = karte1;
		}

		return ergebnis;
	}

	// Der SchlaueSpieler spielt hand und muss deshalb noch nicht druecken.
	@Override
	public Spielkarte[] druecken(Spielkarte[] skat) {
		// Auto-generated method stub
		return null;
	}

	@Override
	public boolean handspiel() {
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
	public ISpielart spielAnsagen() {

		ISpielart zuSpielendeSpielart = bestimmeSpielart();

		if (zuSpielendeSpielart == null) {

			zuSpielendeSpielart = new Nullspiel();
		}

		return zuSpielendeSpielart;
	}

	@Override
	public boolean hoeren(int reizwert) {

		boolean ergebnis = false;

		if (reizwert <= maxReizwert) {

			ergebnis = true;
		}

		return ergebnis;
	}

	@Override
	public boolean sagen(int reizWert) {

		boolean ergebnis = false;

		if (reizWert <= maxReizwert) {

			ergebnis = true;
		}

		return ergebnis;
	}

	@Override
	public Farbspiel farbe() {

		Farbspiel farbe = new Farbspiel(ermittleTrumpffarbe());

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
	public int reizlimitFestlegen() {

		return 0;
	}

	public void bestimmeMaxReizwert() {

		ISpielart zuReizendeSpielart = bestimmeSpielart();
		System.out.println(zuReizendeSpielart);

		if (zuReizendeSpielart != null) {

			switch (zuReizendeSpielart.getSpielart()) {

			case FARBE:
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

		Farbe farbe = ermittleTrumpffarbe();

		maxReizwert = (ermittelteSpitzen + 1) * farbe.wert();
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
	public int ermittleSpitzen(ISpielart zuReizendeSpielart) {

		int ergebnis = 0;
		Spielkarte[] spitzen;

		if (zuReizendeSpielart.getSpielart() == Spielartbezeichnung.FARBE) {

			spitzen = farbeSpitzen(zuReizendeSpielart);
			ergebnis = spitzenZaehlen(spitzen);
		}

		else if (zuReizendeSpielart.getSpielart() == Spielartbezeichnung.GRAND) {

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
	public int spitzenZaehlen(Spielkarte[] spitzen) {

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
	public int ohne(Spielkarte[] spitzen) {

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
	public int mit(Spielkarte[] spitzen) {

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
	public Spielkarte[] farbeSpitzen(ISpielart zuReizendeSpielart) {

		int kartenwert = 0;
		Spielkarte[] spitzen = new Spielkarte[13];
		ArrayList<Spielkarte> langeFarbe = ermittleKurzeLangeFarbe(true);

		for (int i = 0; i < blatt.size(); i++) {

			if (blatt.get(i).getWert() == Wert.BUBE) {

				kartenwert = zuReizendeSpielart.karteBewerten(blatt.get(i));
				spitzen[bubeneinordnenhilf(kartenwert)] = blatt.get(i);
			}

			else if (blatt.get(i).getFarbe() == langeFarbe.get(0).getFarbe()) {

				kartenwert = zuReizendeSpielart.karteBewerten(blatt.get(i));
				spitzen[farbeeinordnenhilf(kartenwert)] = blatt.get(i);
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
	public Spielkarte[] grandSpitzen(ISpielart zuReizendeSpielart) {

		int kartenwert = 0;
		Spielkarte[] spitzen = new Spielkarte[4];

		for (int i = 0; i < blatt.size(); i++) {

			if (blatt.get(i).getWert() == Wert.BUBE) {

				kartenwert = zuReizendeSpielart.karteBewerten(blatt.get(i));
				spitzen[bubeneinordnenhilf(kartenwert)] = blatt.get(i);
			}
		}

		return spitzen;
	}

	/**
	 * Diese Methode entscheidet, welche Spielart der schlaue Spieler mit seinen
	 * Handkarten w&auml;len w&uuml;rde.
	 */
	public ISpielart bestimmeSpielart() {

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

		ArrayList<Spielkarte> langeFarbe = ermittleKurzeLangeFarbe(true);

		// Spielart auf die der Spieler reizt
		ISpielart zuReizendeSpielart;

		for (Spielkarte karte : blatt) {

			if (karte.getWert() == Wert.SIEBEN || karte.getWert() == Wert.ACHT
					|| karte.getWert() == Wert.NEUN) {

				kartenKleiner10++;
			}

			if (karte.getWert() == Wert.BUBE) {

				buben++;
			}

			if (karte.getWert() == Wert.ASS || karte.getWert() == Wert.ZEHN) {

				kartenGroesserKoenig++;
			}
		}

		anzahlKartenVonFarbe = langeFarbe.size();

		// Auswertung
		if (kartenKleiner10 >= 5) {

			zuReizendeSpielart = new Nullspiel();
		}

		else if (buben >= 2 && kartenGroesserKoenig >= 4) {

			zuReizendeSpielart = new Grandspiel();
		}

		else if ((buben >= 2 && anzahlKartenVonFarbe >= 4)
				|| (buben < 2 && anzahlKartenVonFarbe >= 6)) {

			zuReizendeSpielart = new Farbspiel(langeFarbe.get(0).getFarbe());
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
	public ArrayList<Spielkarte> ermittleKurzeLangeFarbe(boolean lang) {

		ArrayList<Spielkarte> karo = kartenEinerFarbe(blatt, Farbe.KARO);
		ArrayList<Spielkarte> herz = kartenEinerFarbe(blatt, Farbe.HERZ);
		ArrayList<Spielkarte> pik = kartenEinerFarbe(blatt, Farbe.PIK);
		ArrayList<Spielkarte> kreuz = kartenEinerFarbe(blatt, Farbe.KREUZ);
		ArrayList<Spielkarte> gewinner = new ArrayList<Spielkarte>();

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
	public ArrayList<Spielkarte> ermittleKurzeFarbe(ArrayList<Spielkarte> karo,
			ArrayList<Spielkarte> herz, ArrayList<Spielkarte> pik,
			ArrayList<Spielkarte> kreuz) {

		ArrayList<Spielkarte> kurzeFarbe = new ArrayList<Spielkarte>();

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
	public ArrayList<Spielkarte> ermittleLangeFarbe(ArrayList<Spielkarte> karo,
			ArrayList<Spielkarte> herz, ArrayList<Spielkarte> pik,
			ArrayList<Spielkarte> kreuz) {

		ArrayList<Spielkarte> langeFarbe = new ArrayList<Spielkarte>();

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
	public Farbe ermittleTrumpffarbe() {

		Farbe ergebnis = null;
		ArrayList<Spielkarte> gewinner = ermittleKurzeLangeFarbe(true);

		// Gewinner feststellen
		switch (gewinner.get(0).getFarbe()) {

		case KARO:
			ergebnis = Farbe.KARO;
			break;
		case HERZ:
			ergebnis = Farbe.HERZ;
			break;
		case PIK:
			ergebnis = Farbe.PIK;
			break;
		case KREUZ:
			ergebnis = Farbe.KREUZ;
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
	public int werteAugen(ArrayList<Spielkarte> stiche) {

		int erg = 0;

		for (int i = 0; i < stiche.size(); i++) {

			if (stiche.get(i).getWert() == Wert.ASS) {

				erg += 11;

			} else if (stiche.get(i).getWert() == Wert.ZEHN) {

				erg += 10;

			} else if (stiche.get(i).getWert() == Wert.BUBE) {

				erg += 2;

			} else if (stiche.get(i).getWert() == Wert.DAME) {

				erg += 3;

			} else if (stiche.get(i).getWert() == Wert.KOENIG) {

				erg += 4;

			} else if (stiche.get(i).getWert() == Wert.SECHS) {

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
	public int augenKarte(Spielkarte karte) {

		int ergebnis;

		switch (karte.getWert()) {
		case SECHS:
			ergebnis = 6;
			break;
		case DAME:
			ergebnis = 3;
			break;
		case KOENIG:
			ergebnis = 4;
			break;
		case ZEHN:
			ergebnis = 10;
			break;
		case ASS:
			ergebnis = 11;
			break;
		case BUBE:
			ergebnis = 2;
			break;
		default:
			ergebnis = 0;
			break;
		}

		return ergebnis;
	}
}
