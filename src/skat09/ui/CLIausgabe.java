package skat09.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedSet;

import skat09.Messages;
import skat09.Tisch;
import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;



/**
 * Diese Klasse wird das Spiel f&uuml;r den menschlichen Spieler &uuml;ber die
 * Konsole abbilden. Der Spieler soll so bef&auml;higt werden, Informationen
 * &uuml;ber den aktuellen Spielestand einzuholen, sowie seine Z&uuml;ge zu
 * machen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin
 *         Bruhns
 * @version 03.07.2009
 */
public class CLIausgabe extends Ausgabe {

	//
	// Datenfelder
	//
	/**
	 * Der Tisch, auf dem gespielt wird
	 */
	private Tisch tisch;
	/**
	 * Der BufferedReader wird ben&ouml;tigt, um den Text, den der Benutzer
	 * eingibt, einzulesen
	 */
	private BufferedReader eingabe = new BufferedReader(new InputStreamReader(
			System.in));
	/**
	 * Diese Variable ist true, falls die vergangenen Stiche angezeigt werden
	 * sollen
	 */
	private boolean hilfestiche = false;
	/**
	 * Diese Variable ist true, falls die spielbaren Karten angezeigt werden
	 * sollen
	 */
	private boolean hilfespielbar = false;

	//
	// Konstruktor
	//
	/**
	 * Der Konstruktor der Klasse CLIAusgabe
	 * 
	 * @param tisch
	 *            Der Tisch, auf dem gespielt wird
	 */
	public CLIausgabe(Tisch tisch) {
		
		System.out.println(Messages.getI18n("application.welcome"));
		this.tisch = tisch;

	}

	/**
	 * Methode fordert den Spieler auf seine Eingabe zu wiederholen, falls sie
	 * fehlerhaft war.
	 */
	public void falscheEingabe() {

		System.out
				.println(Messages.getI18n("application.output.input.wrong"));
	}

	/**
	 * L&auml;sst den menschlichen Spieler entscheiden, ob er bei einem
	 * bestimmten Reizwert mitgeht oder weg ist.
	 * 
	 * @param wert
	 *            - aktuell gebotener Reizwert
	 */
	public boolean hoeren(int wert) {
		boolean eingabeKorrekt = false;
		boolean ergebnis = false;

		System.out.println(Messages.getI18n("game.commandline.hold", wert));

		String eingabe = einlesen();

		while (!eingabeKorrekt) {
			// TODO also i18n user input
			if (eingabe.equalsIgnoreCase("m")) {

				ergebnis = true;
				eingabeKorrekt = true;
			}

			else if (eingabe.equalsIgnoreCase("a")) {

				ergebnis = false;
				eingabeKorrekt = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		return ergebnis;
	}

	@Override
	public void weg(ISpieler spieler) {

		String name = spieler.getName();

		System.out.println(Messages.getI18n("game.hear.off", name));
		leerzeile();
	}

	/**
	 * Fragt den Spieler welchen Wert er reizen will und liefert ihn
	 * zur&uuml;ck.
	 * 
	 * @param reizWert
	 *            - der aktuell gebotene Reizwert
	 */
	public boolean sagen(int reizWert) {

		System.out.println(Messages.getI18n("game.say.value", reizWert));

		return jaNeinAbfrage();
	}

	@Override
	public void spiel() {

	}

	@Override
	public String name() {
		System.out.println(Messages.getI18n("game.name.enter"));
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public String gegner(int nummer) {

		String s = "";

		if (nummer == 1) {

			System.out.println("Bitte waehlen Sie ihren ersten Gegner!");
		}

		else if (nummer == 2) {

			System.out.println("Bitte waehlen Sie ihren zweiten Gegner!");
		}

		else {

			System.out.println("Methode gegner >> falscher Parameter!");
		}

		System.out
				.println("Druecken Sie [o] fuer Oma, [r] fuer "
						+ "regelkonformen Spieler oder [s] fuer einen schlauen Spieler!");
		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo("o") != 0) {
			if (s.compareTo("r") != 0) {
				if (s.compareTo("s") != 0) {

					System.out
							.println("Sie haben einen falschen Buchstaben angegeben! "
									+ "Waehlen sie nocheinmal.");
					s = gegner(nummer);
				}
			}
		}
		return s;
	}

	@Override
	public String frageVariante() {

		System.out.println("Bitte waehlen sie die gewuenschte Skatvariante!");
		System.out
				.println("Druecken sie [r] fuer Rauberskat, [i] fuer Skat nach der internationalen Skatordnung, [b] fuer Skat mit Ramsch/Bockrunden");
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo("i") != 0) {
			if (s.compareTo("r") != 0) {
				if (s.compareTo("b") != 0) {
					System.out
							.println("Sie haben einen falschen Buchstaben angegeben! "
									+ "Waehlen sie nocheinmal.");
					s = frageVariante();
				}
			}
		}
		hilfe();
		return s;

	}

	/**
	 * Fragt den Spieler, ob er hand spielen m&ouml;chte.
	 * 
	 * @return true, falls handspiel
	 */
	public boolean handspiel() {

		System.out.println("Moechten Sie den Skat aufnehmen?");

		return !jaNeinAbfrage();
	}

	@Override
	public int druecken(ArrayList<Spielkarte> blatt, int nummer) {

		ISpielart spielart = new Grandspiel();
		int ergebnis = -1;

		if (nummer == 1) {
			System.out
					.println("Sie haben den Skat aufgenommen. Bitte waehlen Sie die erste Karte, "
							+ "die gedrueckt werden soll, indem Sie die entsprechende Nummer eingeben.");
		} else if (nummer == 2) {
			System.out.println("Bitte druecken Sie nun die 2. Karte.");
		} else if (nummer == 3) {
			System.out.println("Bitte druecken Sie nun die 3. Karte.");
		}

		// Karten sortieren
		tisch.gibMenschlicherSpieler().blattSortieren(spielart);

		// Alle Karten auflisten
		System.out.println("Ihr Blatt:");
		for (int i = 0; i < blatt.size(); i++) {

			Spielkarte karte = blatt.get(i);
			Farbe farbe = karte.getFarbe();
			Wert wert = karte.getWert();
			System.out.println(i + ": " + farbe + " " + wert);
		}

		// Einlesen
		// TODO Sechserskat anpassen!
		boolean eingabeKorrekt = false;
		while (!eingabeKorrekt) {

			ergebnis = intEinlesen();

			if (ergebnis > -1 && ergebnis < 12) {

				eingabeKorrekt = true;
			} else {

				System.out.println("Falsche Eingabe. Versuchen Sie es erneut!");
			}
		}
		return ergebnis;
	}

	@Override
	public boolean schneider() {

		System.out.println("Moechten Sie schneider ansagen?");

		return jaNeinAbfrage();
	}

	@Override
	public boolean schwarz() {

		System.out.println("Moechten Sie schwarz ansagen?");

		return jaNeinAbfrage();
	}

	@Override
	public boolean ouvert() {

		System.out.println("Moechten Sie ouvert spielen?");

		return jaNeinAbfrage();
	}

	@Override
	public ISpielart spielAnsagen() {

		int zaehler = 0;
		int ergebnis = -1;
		ISpielart rueckgabe = null;

		System.out
				.println("Bitte waehlen Sie ihre Spielart aus durch Eingabe der"
						+ " entsprechenden Nummer.");
		for (Enum<Spielartbezeichnung> spielartbezeichnung : Spielartbezeichnung.values()) {

			System.out.println(zaehler + ": " + spielartbezeichnung);
			zaehler++;
		}

		ergebnis = intEinlesen();

		switch (ergebnis) {

		case 0:
			rueckgabe = new Grandspiel();
			break;
		case 1:
			rueckgabe = new Nullspiel();
			break;
		case 2:
			rueckgabe = new Farbspiel(null);
			break;
		default:
			System.out.println("Fehler: Diese Spielart existiert nicht!");
			System.out.println();
			spielAnsagen();
		}

		return rueckgabe;
	}

	@Override
	public Farbspiel farbe() {

		Farbspiel rueckgabe = null;
		int zaehler = 0;
		int ergebnis = -1;

		System.out.println("Welche Farbe soll Trumpf sein? Waehlen Sie die"
				+ " entsprechende Nummer!");
		for (Enum<Farbe> farbe : Farbe.values()) {

			System.out.println(zaehler + ": " + farbe);
			zaehler++;
		}

		// G&uuml;tige Zahl einlesen
		boolean eingabeKorrekt = false;
		while (!eingabeKorrekt) {

			ergebnis = intEinlesen();

			if (-1 < ergebnis && ergebnis < 4) {

				eingabeKorrekt = true;
			} else {

				System.out
						.println("Falsche Eingabe. Bitte versuchen Sie es erneut!");
			}
		}
		switch (ergebnis) {

		case 0:
			rueckgabe = new Farbspiel(Farbe.KARO);
			break;
		case 1:
			rueckgabe = new Farbspiel(Farbe.HERZ);
			break;
		case 2:
			rueckgabe = new Farbspiel(Farbe.PIK);
			break;
		case 3:
			rueckgabe = new Farbspiel(Farbe.KREUZ);
			break;
		default:
			System.out.println("Fehler: Diese Farbe existiert nicht!");
		}
		return rueckgabe;
	}

	@Override
	public void blattAusgeben(ISpieler spieler) {

		ArrayList<Spielkarte> blatt = spieler.getBlatt();

		System.out.println("Sie haben die folgenden Handkarten:");

		for (int i = 0; i < blatt.size(); i++) {

			Spielkarte karte = blatt.get(i);
			// Farbe farbe = karte.getFarbe();
			// Wert wert = karte.getWert();
			System.out.println(i + ": " + karte.toString());
		}

		System.out.println();
	}

	@Override
	public Spielkarte spieleKarte(Spielkarte[] gespielteKarten, ISpieler spieler) {

		Spielkarte ergebnis = null;
		ArrayList<Spielkarte> blatt = spieler.getBlatt();
		// gueltig wird true gesetzt, wenn eine gueltige Zahl von Konsole
		// eingelesen wurde.
		boolean gueltig = false;

		// Hilfen:
		if (hilfespielbar) {
			hilfeSpielbar(gespielteKarten);
		}
		if (hilfestiche) {
			hilfeStiche();
		}

		// Handkarten zeigen
		System.out.println("Bitte spielen Sie eine Karte durch Zahleneingabe!");
		leerzeile();
		blattAusgeben(spieler);

		// Tischkarten zeigen
		if (gespielteKarten[0] == null) {

			System.out.println("Sie kommen raus.");
		} else {

			System.out.println("Die folgenden Karten liegen auf dem Tisch:");

			for (int i = 0; i < 3; i++) {

				if (gespielteKarten[i] != null) {
					System.out.println(i + ": "
							+ gespielteKarten[i].getBesitzer().getName()
							+ " spielte " + gespielteKarten[i].toString());
				}
			}
		}
		// Einlesen und Ausgeben
		int zahl = intEinlesen();

		while (!gueltig) {
			if (zahl <= blatt.size() - 1) {
				gueltig = true;
			} else {
				System.out.println("Diese Karte existiert nicht. Bitte geben "
						+ "Sie eine gueltige Zahl an!");
				zahl = intEinlesen();
			}
		}
		ergebnis = blatt.remove(zahl);
		return ergebnis;
	}

	/**
	 * Diese Methode liest eine Zahl von der Konsole aus und gibt sie
	 * zur&uuml;ck.
	 */
	public int intEinlesen() {

		boolean eingabeKorrekt = false;
		String s = "";
		int ergebnis = -1;

		// Einlesen
		while (!eingabeKorrekt) {
			try {

				s = eingabe.readLine();
			}

			catch (IOException e) {

				e.printStackTrace();
			}
			// String to int
			try {
				ergebnis = Integer.parseInt(s);
				eingabeKorrekt = true;
			} catch (Exception E) {
				System.out
						.println("Das war keine Zahl! Bitte Eingabe wiederholen.");
				eingabeKorrekt = false;
			}
		}
		return ergebnis;
	}

	@Override
	public void stichGewonnen(ISpieler spieler) {

		System.out.println(">> " + Messages.getI18n("player.trick.won", spieler.getName()));
	}

	/**
	 * Diese Methode l&ouml;scht den gesamten Anzeigebereich der Konsole.
	 */
	public void clear() {

		for (int i = 0; i < 200; i++) {
			System.out.println(" ");
		}
	}

	@Override
	public void skatAusgeben(Spielkarte[] skat) {

		System.out.println("Die folgenden Karten lagen im Skat:");
		System.out.println(skat[0].toString() + " und " + skat[1].toString());
		System.out.println();
	}

	@Override
	public void auswertung(boolean gewonnen) {
		if (tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			if (gewonnen == true) {
				System.out.println(tisch.ermittleAlleinspieler().getName()
						+ " hat gewonnen!");
				System.out.println(tisch.ermittleAlleinspieler().getSpiele()
						.get(
								tisch.ermittleAlleinspieler().getSpiele()
										.size() - 1)
						+ " Punkte wurden erreicht!");
			} else {
				System.out.println(tisch.ermittleAlleinspieler().getName()
						+ " hat verloren!");
				System.out.println(tisch.ermittleAlleinspieler().getSpiele()
						.get(
								tisch.ermittleAlleinspieler().getSpiele()
										.size() - 1)
						+ " Punkte wurden abgezogen!");
			}
		} else {
			if (gewonnen) {
				System.out.println("Sie haben gewonnen! ");
				System.out.println("Sie haben "
						+ tisch.gibMenschlicherSpieler().getSpiele().get(
								tisch.gibMenschlicherSpieler().getSpiele()
										.size() - 1) + "Punkte erreicht!");
			} else {
				System.out.println("Sie haben verloren! ");
				System.out.println(tisch.gibMenschlicherSpieler().getSpiele()
						.get(
								tisch.gibMenschlicherSpieler().getSpiele()
										.size() - 1)
						+ "Punkte wurden abgezogen!");
			}
		}
	}

	@Override
	public void spielBeendet() {
		System.out.println("Das Spiel ist beendet!");
		System.out.println("Im Skat lag:");
		Spielkarte[] skat = tisch.getSkat();
		System.out.println("Karte 1: " + skat[0].toString());
		System.out.println("Karte 1: " + skat[1].toString());
		if (skat[2] != null) {
			System.out.println("Karte 1: " + skat[2].toString());
		}

	}

	@Override
	public void mhVSvh() {

		String mittelhand = tisch.getMittelhand().getName();
		String vorhand = tisch.getVorhand().getName();
		System.out.println(mittelhand + " reizt gegen " + vorhand);
	}

	/**
	 * Gibt eine Leerzeile auf der Konsole aus.
	 */
	public void leerzeile() {

		System.out.println();
	}

	@Override
	public void hhVSgewinner(ISpieler gewinner) {

		String hinterhand = tisch.getHinterhand().getName();
		System.out.println(hinterhand + " reizt gegen " + gewinner.getName());
	}

	@Override
	public void spielEinpassen() {

		System.out.println("Niemand moechte Spielen. Das Spiel wird eingepasst"
				+ " und der naechste Spieler ist an der Reihe.");
	}

	@Override
	public void spielBeginnt() {

		System.out.println("Achtung: Das Spiel beginnt!");
	}

	@Override
	public void andereKarte() {

		System.out
				.println("Diese Karte darf nicht gespielt werden! Waehlen Sie "
						+ "eine andere Karte!");
	}

	@Override
	public void augen(int augen) {
		System.out.println("Der Alleinspieler "
				+ tisch.ermittleAlleinspieler().getName() + " hat " + augen
				+ " Augen erspielt!");

	}

	@Override
	public void punkte(int punkte) {
		System.out.println("Der Alleinspieler "
				+ tisch.ermittleAlleinspieler().getName() + " hat " + punkte
				+ " Punkte erreicht!");
	}

	@Override
	public void alleinspieler() {
		System.out
				.println(tisch.ermittleAlleinspieler().getName() + " spielt!");

	}

	/**
	 * Gibt an welcher Spieler das Reizen gewonnen hat
	 * 
	 * @param spieler
	 */
	public void gewinntReizen(ISpieler spieler) {

		System.out.println(spieler.getName() + "gewinnt das Reizen zwischen "
				+ "Mittelhand und Vorhand");
	}

	@Override
	public void trumpf() {
		if (tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			System.out.println(tisch.ermittleAlleinspieler().getName()
					+ " spielt " + tisch.getSpielart().toString() + "!");
			System.out.println("");
		} else {
			System.out.println("Es wird Ramsch gespielt!");
			System.out.println("");
		}
	}

	@Override
	public void neuesSpiel() {
		leerzeile();
		System.out.println("*************************************");
		System.out.println("Ein neues Spiel beginnt!");
		leerzeile();

	}

	@Override
	public boolean reizAgent() {

		System.out.println("Moechten Sie den Reizagenten benutzen?");

		return jaNeinAbfrage();
	}

	@Override
	public int reizlimitFestlegen() {

		int ergebnis = -1;
		boolean fertig = false;

		System.out.println("Sie verwenden den Reizagenten.");
		System.out.println("Bis zu welchem Wert moechten Sie reizen?");
		System.out.println("Geben Sie [0] ein, um zu passen.");
		ergebnis = intEinlesen();
		while (!fertig) {

			SortedSet<Integer> reizwerte = tisch.getReizwerte();
			if (reizwerte.contains(ergebnis) || ergebnis == 0) {

				fertig = true;
			} else {

				ergebnis = intEinlesen();
			}
		}
		return ergebnis;
	}

	@Override
	public String einlesen() {

		String s = "";
		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void spieltKarte(ISpieler spieler, Spielkarte karte) {

		System.out.println(spieler.getName() + " hat " + karte.toString()
				+ " gespielt");
	}

	@Override
	public boolean spielBeenden() {

		System.out.println("Moechten Sie das Spiel beenden?");

		return jaNeinAbfrage();
	}

	/**
	 * Fragt dem Benutzer, ob er ja oder nein sagen m&oouml;chte.
	 * 
	 * @return true, falls ja; false falls nein
	 */
	public boolean jaNeinAbfrage() {

		System.out.println("Waehlen Sie [j] fuer ja oder [n] fuer nein");

		boolean eingabeKorrekt = false;
		boolean ergebnis = false;
		String string = "";

		while (!eingabeKorrekt) {
			try {

				string = eingabe.readLine();
			}

			catch (IOException e) {

				e.printStackTrace();
			}
			if (string.equalsIgnoreCase("j")) {

				ergebnis = true;
				eingabeKorrekt = true;
			} else if (string.equalsIgnoreCase("n")) {

				ergebnis = false;
				eingabeKorrekt = true;
			} else {

				System.out.println("Falsche Eingabe. Versuchen Sie es erneut!");
			}
		}
		return ergebnis;
	}

	@Override
	public void punkteAusgeben() {
		System.out.println("******** ****** ********");
		System.out.println("Die aktuelle Punkteliste:");
		System.out.println(tisch.getSpieler1().getName() + "     "
				+ tisch.getSpieler2().getName() + "     "
				+ tisch.getSpieler3().getName());
		for (int i = 0; i < tisch.getSpieler1().getSpiele().size(); i++) {
			System.out.println(tisch.getSpieler1().getSpiele().get(i) + "     "
					+ tisch.getSpieler2().getSpiele().get(i)
					+ "               "
					+ tisch.getSpieler3().getSpiele().get(i));
		}
		System.out.println("******** ****** ********");
	}

	@Override
	public void tischLoeschen() {

	}

	@Override
	public String getBlattwahl() {
		System.out.println("Bitte waehlen Sie das Skatblatt!");
		System.out
				.println("Waehlen Sie [f] fuer das franzoesische Blatt, [d] fuer ein deutsches Blatt!");
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		return s;

	}

	@Override
	public String frageSechserskat() {
		System.out.println("Moechten Sie Sechserskat spielen?");
		System.out.println("Druecken sie [j] fuer ja, [n] fuer nein!");
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo("j") != 0) {
			if (s.compareTo("n") != 0) {
				System.out
						.println("Sie haben einen falschen Buchstaben angegeben! "
								+ "Waehlen sie nocheinmal.");
				s = frageVariante();
			}
		}
		return s;
	}

	@Override
	public void hauptfensterOeffnen() {

	}

	@Override
	public void positionAnzeigen() {

	}

	@Override
	public void guiAufraumen() {

	}

	/**
	 * Diese Methode fragt, ob der Spieler Hilfen haben m&ouml;chte und setzt
	 * die Flags daf&uuml;r. Der Spieler kann entscheiden, ob er die vergangenen
	 * Stiche einsehen will und ob er die spielbaren Karten angezeigt haben
	 * will.
	 */
	public void hilfe() {
		System.out.println("Wollen Sie eine Hilfe haben?");
		boolean ergebnis = jaNeinAbfrage();
		if (ergebnis) {
			System.out
					.println("Sollen die spielbaren Karten angezeigt werden?");
			ergebnis = jaNeinAbfrage();
			if (ergebnis) {
				hilfespielbar = true;
			}
			System.out
					.println("Sollen die vergangenen Stiche angezeigt werden?");
			ergebnis = jaNeinAbfrage();
			if (ergebnis) {
				hilfestiche = true;
			}
		}
	}

	/**
	 * Gibt die spielbaren Karten aus.
	 * 
	 * @param gespielteKarten
	 *            Die Karten, die sich schon auf dem Tisch befinden
	 */
	public void hilfeSpielbar(Spielkarte[] gespielteKarten) {
		ArrayList<Spielkarte> karten = tisch.gibMenschlicherSpieler()
				.spielbareKarten(gespielteKarten);
		System.out.println("Die spielbaren Karten:");
		for (Spielkarte karte : karten) {
			System.out.println(karte.toString());
		}
	}

	/**
	 * Gibt die vergangenen Stiche aus;
	 */
	public void hilfeStiche() {
		ArrayList<Spielkarte> karten = tisch.getSpieler1()
				.getAllegespieltenkarten();
		System.out.println("Die vergangenen Stiche:");
		for (Spielkarte karte : karten) {
			System.out.println(karte.toString());
		}
	}

	@Override
	public void statistik() {
		// Titel
		System.out.println("*************************************");
		System.out.println("**************Statistik**************");
		System.out.println("");

		// Daten des ersten Spielers
		System.out.println(tisch.getSpieler1().getName() + ":");
		System.out.print("Wie haeufig ist der Spieler Alleinspieler?   ");
		System.out.println(tisch.getProzentAllein(tisch.getSpieler1())
				+ " Prozent aller Spiele");
		System.out
				.print("Wie viele Spiele wurden als Alleinspieler gewonnen?   ");
		System.out.println(tisch.anzahlderGewinne(tisch.getSpieler1())
				+ "Spiele von " + tisch.getSpieler1().getSpiele().size()
				+ " Spielen");
		System.out.print("Wie oft spielt " + tisch.getSpieler1().getName()
				+ " Hand?   ");
		System.out.println(tisch.getSpieler1().getHandspiele()
				+ " Handspiele von " + tisch.getSpieler1().getSpiele().size()
				+ " Spielen");

		// Daten des zweiten Spielers
		System.out.println(tisch.getSpieler2().getName() + ":");
		System.out.print("Wie haeufig ist der Spieler Alleinspieler?   ");
		System.out.println(tisch.getProzentAllein(tisch.getSpieler2())
				+ " Prozent aller Spiele");
		System.out
				.print("Wie viele Spiele wurden als Alleinspieler gewonnen?   ");
		System.out.println(tisch.anzahlderGewinne(tisch.getSpieler2())
				+ "Spiele von " + tisch.getSpieler2().getSpiele().size()
				+ " Spielen");
		System.out.print("Wie oft spielt " + tisch.getSpieler2().getName()
				+ " Hand?   ");
		System.out.println(tisch.getSpieler2().getHandspiele()
				+ " Handspiele von " + tisch.getSpieler2().getSpiele().size()
				+ " Spielen");

		statistik2();

	}

	/**
	 * Diese Methode ist eine Hilfsmethode f&uuml;r statistik() und wird nur von
	 * dieser Methode aufgerufen
	 */
	public void statistik2() {
		// Daten des dritten Spielers
		System.out.println(tisch.getSpieler3().getName() + ":");
		System.out.print("Wie haeufig ist der Spieler Alleinspieler?   ");
		System.out.println(tisch.getProzentAllein(tisch.getSpieler3())
				+ " Prozent aller Spiele");
		System.out
				.print("Wie viele Spiele wurden als Alleinspieler gewonnen?   ");
		System.out.println(tisch.anzahlderGewinne(tisch.getSpieler3())
				+ "Spiele von " + tisch.getSpieler3().getSpiele().size()
				+ " Spielen");
		System.out.print("Wie oft spielt " + tisch.getSpieler3().getName()
				+ " Hand?   ");
		System.out.println(tisch.getSpieler3().getHandspiele()
				+ " Handspiele von " + tisch.getSpieler3().getSpiele().size()
				+ " Spielen");
	}

}
