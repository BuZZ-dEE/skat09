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

		System.out.println(Messages.getI18n("application.output.input.wrong"));
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

		System.out.println(Messages.getI18n("game.commandline.hold", wert,
				Messages.getI18n("game.commandline.bidding.g"),
				Messages.getI18n("game.commandline.bidding.p")));

		String eingabe = einlesen();

		while (!eingabeKorrekt) {
			if (eingabe.equalsIgnoreCase(Messages
					.getI18n("game.commandline.bidding.g"))) {

				ergebnis = true;
				eingabeKorrekt = true;
			}

			else if (eingabe.equalsIgnoreCase(Messages.getI18n("game.commandline.bidding.p"))) {

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

			System.out.println(Messages
					.getI18n("game.commandline.adversary.first.choose"));

		} else if (nummer == 2) {

			System.out.println(Messages
					.getI18n("game.commandline.adversary.second.choose"));

		} else {

			System.out.println(Messages
					.getI18n("game.commandline.adversary.parameter.wrong"));
		}

		System.out
				.println(Messages.getI18n(
						"game.commandline.adversary.type.choose",
						Messages.getI18n("game.commandline.adversary.type.granny.abbr"),
						Messages.getI18n("game.commandline.adversary.type.normal.abbr"),
						Messages.getI18n("game.commandline.adversary.type.smart.abbr")));
		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo(Messages
				.getI18n("game.commandline.adversary.type.granny.abbr")) != 0) {
			if (s.compareTo(Messages
					.getI18n("game.commandline.adversary.type.normal.abbr")) != 0) {
				if (s.compareTo(Messages
						.getI18n("game.commandline.adversary.type.smart.abbr")) != 0) {

					System.out.println(Messages
							.getI18n("game.commandline.input.short.wrong"));
					s = gegner(nummer);
				}
			}
		}
		return s;
	}

	@Override
	public String frageVariante() {

		System.out.println(Messages
				.getI18n("game.commandline.skat.type.choose.question"));
		System.out
				.println(Messages.getI18n(
						"game.commandline.skat.type.choose",
						Messages.getI18n("game.commandline.skat.type.robber.abbr"),
						Messages.getI18n("game.commandline.skat.type.international.abbr"),
						Messages.getI18n("game.commandline.skat.type.bock.abbr")));
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo(Messages
				.getI18n("game.commandline.skat.type.international.abbr")) != 0) {
			if (s.compareTo(Messages
					.getI18n("game.commandline.skat.type.robber.abbr")) != 0) {
				if (s.compareTo(Messages
						.getI18n("game.commandline.skat.type.bock.abbr")) != 0) {
					System.out.println(Messages
							.getI18n("game.commandline.input.short.wrong"));
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

		System.out.println(Messages.getI18n("game.skat.take"));

		return !jaNeinAbfrage();
	}

	@Override
	public int druecken(ArrayList<Spielkarte> blatt, int nummer) {

		ISpielart spielart = new Grandspiel();
		int ergebnis = -1;

		if (nummer == 1) {
			System.out.println(Messages
					.getI18n("game.skat.choose.press.card.first"));
		} else if (nummer == 2) {
			System.out.println(Messages.getI18n("game.skat.press.card.second"));
		} else if (nummer == 3) { // TODO why this case?
			System.out.println(Messages.getI18n("game.skat.press.card.third"));
		}

		// Karten sortieren
		tisch.gibMenschlicherSpieler().blattSortieren(spielart);

		// Alle Karten auflisten
		System.out.println(Messages.getI18n("game.deck.yours") + ":");
		for (int i = 0; i < blatt.size(); i++) {

			Spielkarte karte = blatt.get(i);
			Farbe farbe = karte.getFarbe();
			Wert wert = karte.getWert();
			System.out.println(i + ": " + farbe + " " + wert); // TODO also
																// translate
		}

		// Einlesen
		// TODO Sechserskat anpassen!
		boolean eingabeKorrekt = false;
		while (!eingabeKorrekt) {

			ergebnis = intEinlesen();

			if (ergebnis > -1 && ergebnis < 12) {

				eingabeKorrekt = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		return ergebnis;
	}

	@Override
	public boolean schneider() {

		System.out.println(Messages.getI18n("game.schneider.play.choose"));

		return jaNeinAbfrage();
	}

	@Override
	public boolean schwarz() {

		System.out.println(Messages.getI18n("game.schwarz.play.choose"));

		return jaNeinAbfrage();
	}

	@Override
	public boolean ouvert() {

		System.out.println(Messages.getI18n("game.ouvert.play.choose"));

		return jaNeinAbfrage();
	}

	@Override
	public ISpielart spielAnsagen() {

		int zaehler = 0;
		int ergebnis = -1;
		ISpielart rueckgabe = null;

		System.out.println(Messages.getI18n("game.type.number.choose"));
		for (Enum<Spielartbezeichnung> spielartbezeichnung : Spielartbezeichnung
				.values()) {

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
			System.out.println(Messages.getI18n("game.type.input.wrong"));
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

		System.out.println(Messages.getI18n("game.commandline.trump.choose"));
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

				System.out.println(Messages.getI18n("application.input.wrong"));
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
			System.out.println(Messages.getI18n("game.color.input.wrong"));
		}
		return rueckgabe;
	}

	@Override
	public void blattAusgeben(ISpieler spieler) {

		ArrayList<Spielkarte> blatt = spieler.getBlatt();

		System.out.println(Messages.getI18n("game.hand.card.yours"));

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
		System.out
				.println(Messages.getI18n("game.commandline.card.play.input"));
		leerzeile();
		blattAusgeben(spieler);

		// Tischkarten zeigen
		if (gespielteKarten[0] == null) {

			System.out.println(Messages.getI18n("game.getOut.you"));
		} else {

			System.out.println(Messages.getI18n("game.table.cards.on"));

			for (int i = 0; i < 3; i++) {

				if (gespielteKarten[i] != null) {
					System.out.println(i
							+ ": "
							+ Messages.getI18n("player.card.played",
									gespielteKarten[i].getBesitzer().getName(),
									gespielteKarten[i].toString()));
				}
			}
		}
		// Einlesen und Ausgeben
		int zahl = intEinlesen();

		while (!gueltig) {
			if (zahl <= blatt.size() - 1) {
				gueltig = true;
			} else {
				System.out.println(Messages
						.getI18n("game.commandline.card.number.input.wrong"));
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
				System.out.println(Messages
						.getI18n("game.commandline.input.not.number"));
				eingabeKorrekt = false;
			}
		}
		return ergebnis;
	}

	@Override
	public void stichGewonnen(ISpieler spieler) {

		System.out.println(">> "
				+ Messages.getI18n("player.trick.won", spieler.getName()));
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

		System.out.println(Messages.getI18n("game.skat.cards.in"));
		System.out.println(Messages.getI18n("game.skat.cards",
				skat[0].toString(), skat[1].toString()));
		System.out.println();
	}

	@Override
	public void auswertung(boolean gewonnen) {
		if (tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			if (gewonnen == true) {
				System.out.println(Messages.getI18n("player.won", tisch
						.ermittleAlleinspieler().getName()));
				System.out.println(Messages.getI18n(
						"player.win.score",
						tisch.ermittleAlleinspieler()
								.getSpiele()
								.get(tisch.ermittleAlleinspieler().getSpiele()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.loose", tisch
						.ermittleAlleinspieler().getName()));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						tisch.ermittleAlleinspieler()
								.getSpiele()
								.get(tisch.ermittleAlleinspieler().getSpiele()
										.size() - 1)));
			}
		} else {
			if (gewonnen) {
				System.out.println(Messages.getI18n("player.winner") + " ");
				System.out.println(Messages.getI18n(
						"player.winner.score",
						tisch.gibMenschlicherSpieler()
								.getSpiele()
								.get(tisch.gibMenschlicherSpieler().getSpiele()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.looser"));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						tisch.gibMenschlicherSpieler()
								.getSpiele()
								.get(tisch.gibMenschlicherSpieler().getSpiele()
										.size() - 1)));
			}
		}
	}

	@Override
	public void spielBeendet() {
		System.out.println(Messages.getI18n("game.over"));
		System.out.println(Messages.getI18n("game.skat.in.was"));
		Spielkarte[] skat = tisch.getSkat();
		System.out.println(Messages.getI18n("game.skat.card.first",
				skat[0].toString()));
		System.out.println(Messages.getI18n("game.skat.card.second",
				skat[1].toString()));
		if (skat[2] != null) {
			System.out.println(Messages.getI18n("game.skat.card.third",
					skat[2].toString()));
		}

	}

	@Override
	public void mhVSvh() {

		String mittelhand = tisch.getMittelhand().getName();
		String vorhand = tisch.getVorhand().getName();
		System.out.println(Messages.getI18n("game.bidding.against", mittelhand,
				vorhand));
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
		System.out.println(Messages.getI18n("game.bidding.against", hinterhand,
				gewinner.getName()));
	}

	@Override
	public void spielEinpassen() {

		System.out.println(Messages.getI18n("game.bidding.cancel"));
	}

	@Override
	public void spielBeginnt() {

		System.out.println(Messages.getI18n("game.starts"));
	}

	@Override
	public void andereKarte() {

		System.out
				.println(Messages.getI18n("game.card.forbidden.other.choose"));
	}

	@Override
	public void augen(int augen) {
		System.out.println(Messages.getI18n("player.declarer.points", tisch
				.ermittleAlleinspieler().getName(), augen));

	}

	@Override
	public void punkte(int punkte) {
		System.out.println(Messages.getI18n("player.declarer.score", tisch
				.ermittleAlleinspieler().getName(), punkte));
	}

	@Override
	public void alleinspieler() {
		System.out.println(Messages.getI18n("player.name.playing", tisch
				.ermittleAlleinspieler().getName()));

	}

	/**
	 * Gibt an welcher Spieler das Reizen gewonnen hat
	 * 
	 * @param spieler
	 */
	public void gewinntReizen(ISpieler spieler) {

		System.out.println(Messages.getI18n(
				"player.win.bidding.middlehand.forehand", spieler.getName()));
	}

	@Override
	public void trumpf() {
		if (tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			System.out.println(Messages.getI18n("player.name.playing.game",
					tisch.ermittleAlleinspieler().getName(), tisch
							.getSpielart().toString()));
			System.out.println("");
		} else {
			System.out.println(Messages.getI18n("game.ramsch.playing"));
			System.out.println("");
		}
	}

	@Override
	public void neuesSpiel() {
		leerzeile();
		System.out.println("*************************************");
		System.out.println(Messages.getI18n("game.new.begins"));
		leerzeile();

	}

	@Override
	public boolean reizAgent() {

		System.out.println(Messages.getI18n("game.agent.bidding.use"));

		return jaNeinAbfrage();
	}

	@Override
	public int reizlimitFestlegen() {

		int ergebnis = -1;
		boolean fertig = false;

		System.out.println(Messages.getI18n("game.agent.bidding.you.use"));
		System.out.println(Messages.getI18n("game.agent.bidding.value.enter"));
		System.out.println(Messages.getI18n("game.agent.bidding.zero.cancel"));
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

		System.out.println(Messages.getI18n("player.name.playing.card",
				spieler.getName(), karte.toString()));
	}

	@Override
	public boolean spielBeenden() {

		System.out.println(Messages.getI18n("player.game.end"));

		return jaNeinAbfrage();
	}

	/**
	 * Fragt den Benutzer, ob er ja oder nein sagen m&oouml;chte.
	 * 
	 * @return true, falls ja; false falls nein
	 */
	public boolean jaNeinAbfrage() {

		System.out.println(Messages.getI18n("application.yes.no.enter",
				Messages.getI18n("application.y"),
				Messages.getI18n("application.n")));

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
			if (string.equalsIgnoreCase(Messages.getI18n("application.y"))) {

				ergebnis = true;
				eingabeKorrekt = true;
			} else if (string.equalsIgnoreCase(Messages
					.getI18n("application.n"))) {

				ergebnis = false;
				eingabeKorrekt = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		return ergebnis;
	}

	@Override
	public void punkteAusgeben() {
		System.out.println("******** ****** ********");
		System.out.println(Messages.getI18n("game.score.list.current"));
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
		System.out.println(Messages.getI18n("game.skat.deck.choose"));
		System.out.println(Messages.getI18n("game.skat.deck.enter",
				Messages.getI18n("game.skat.deck.f"),
				Messages.getI18n("game.skat.deck.g")));
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
		System.out.println(Messages.getI18n("game.skat.variant.six.play"));
		System.out.println(Messages.getI18n("application.yes.no.enter",
				Messages.getI18n("application.y"),
				Messages.getI18n("application.n")));
		String s = "";

		try {

			s = eingabe.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo(Messages.getI18n("application.y")) != 0) {
			if (s.compareTo(Messages.getI18n("application.n")) != 0) {
				System.out.println(Messages
						.getI18n("game.commandline.input.short.wrong"));
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
		System.out.println(Messages.getI18n("game.help.want"));
		boolean ergebnis = jaNeinAbfrage();
		if (ergebnis) {
			System.out.println(Messages.getI18n("game.playable.cards.show.question"));
			ergebnis = jaNeinAbfrage();
			if (ergebnis) {
				hilfespielbar = true;
			}
			System.out.println(Messages
					.getI18n("game.trick.last.show.question"));
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
		System.out.println(Messages.getI18n("game.playable.cards"));
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
		System.out.println(Messages.getI18n("game.tricks.last"));
		for (Spielkarte karte : karten) {
			System.out.println(karte.toString());
		}
	}

	@Override
	public void statistik() {
		// Titel
		System.out.println("*************************************");
		System.out.println("**************"
				+ Messages.getI18n("game.statistic") + "**************");
		System.out.println("");

		ISpieler[] allPlayer = tisch.getAllPlayer();
		for (ISpieler player : allPlayer) {
			statisticOut(tisch, player);
		}
	}

	/**
	 * Output statistics for a player from the game table.
	 * 
	 * @param {Tisch} table , the game table
	 * @param {ISpieler} player , the player the get the statistics from.
	 * 
	 * @since 11.06.2015 22:14:56
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	private void statisticOut(Tisch table, ISpieler player) {

		System.out.println(player.getName() + ":");
		System.out.print(Messages.getI18n("game.statistic.declarer.quantity")
				+ "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.percent.result",
				table.getProzentAllein(player)));
		System.out.print(Messages
				.getI18n("game.statistic.declarer.quantity.won") + "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.won.result",
				table.anzahlderGewinne(player), player.getSpiele().size()));
		System.out.print(Messages.getI18n(
				"game.statistic.declarer.quantity.hand", player.getName())
				+ "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.hand.result",
				player.getHandspiele(), player.getSpiele().size()));
	}
}
