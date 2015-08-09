package skat09.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedSet;

import skat09.Messages;
import skat09.Table;
import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.NullGame;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.playingcard.Value;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;

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
public class CLIOutput extends Output {
	
	/**
	 * Der Tisch, auf dem gespielt wird
	 */
	private Table tisch;
	/**
	 * Der BufferedReader wird ben&ouml;tigt, um den Text, den der Benutzer
	 * eingibt, einzulesen
	 */
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
			System.in));
	/**
	 * Diese Variable ist true, falls die vergangenen Stiche angezeigt werden
	 * sollen
	 */
	private boolean showLastTricksHelp
			= false;
	/**
	 * Diese Variable ist true, falls die spielbaren Karten angezeigt werden
	 * sollen
	 */
	private boolean showPlayableCardsHelp = false;

	
	
	/**
	 * Der Konstruktor der Klasse CLIAusgabe
	 * 
	 * @param table
	 *            Der Tisch, auf dem gespielt wird
	 */
	public CLIOutput(Table table) {

		System.out.println(Messages.getI18n("application.welcome"));
		this.tisch = table;

	}

	/**
	 * Methode fordert den Spieler auf seine Eingabe zu wiederholen, falls sie
	 * fehlerhaft war.
	 */
	public void wrongInputHint() {

		System.out.println(Messages.getI18n("application.output.input.wrong"));
	}

	/**
	 * L&auml;sst den menschlichen Spieler entscheiden, ob er bei einem
	 * bestimmten Reizwert mitgeht oder weg ist.
	 * 
	 * @param value
	 *            - aktuell gebotener Reizwert
	 */
	public boolean respond(int value) {
		boolean correctInput = false;
		boolean result = false;

		System.out.println(Messages.getI18n("game.commandline.hold", value,
				Messages.getI18n("game.commandline.bidding.g"),
				Messages.getI18n("game.commandline.bidding.p")));

		String input = readInput();

		while (!correctInput) {
			if (input.equalsIgnoreCase(Messages
					.getI18n("game.commandline.bidding.g"))) {

				result = true;
				correctInput = true;
			} else if (input.equalsIgnoreCase(Messages.getI18n("game.commandline.bidding.p"))) {

				result = false;
				correctInput = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}

		return result;
	}

	@Override
	public void weg(IPlayer spieler) {

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
	public void play() {

	}

	@Override
	public String name() {
		System.out.println(Messages.getI18n("game.name.enter"));
		String s = "";

		try {

			s = bufferedReader.readLine();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public String adversary(int nummer) {

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

			s = bufferedReader.readLine();
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
					s = adversary(nummer);
				}
			}
		}
		return s;
	}

	@Override
	public String askForVariant() {

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

			s = bufferedReader.readLine();
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
					s = askForVariant();
				}
			}
		}
		hilfe();
		return s;

	}

	/**
	 * Fragt den Spieler, ob er hand spielen m&ouml;chte.
	 * 
	 * @return true, falls handgame
	 */
	public boolean handgame() {

		System.out.println(Messages.getI18n("game.skat.take"));

		return !jaNeinAbfrage();
	}

	@Override
	public int druecken(ArrayList<PlayingCard> blatt, int nummer) {

		IGameVariety spielart = new GrandGame();
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
		tisch.gibMenschlicherSpieler().sortHand(spielart);

		// Alle Karten auflisten
		System.out.println(Messages.getI18n("game.deck.yours") + ":");
		for (int i = 0; i < blatt.size(); i++) {

			PlayingCard karte = blatt.get(i);
			Suit farbe = karte.getSuit();
			Value wert = karte.getValue();
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
	public IGameVariety declareSuit() {

		int zaehler = 0;
		int ergebnis = -1;
		IGameVariety rueckgabe = null;

		System.out.println(Messages.getI18n("game.type.number.choose"));
		for (Enum<GameVarietyName> spielartbezeichnung : GameVarietyName
				.values()) {

			System.out.println(zaehler + ": " + spielartbezeichnung);
			zaehler++;
		}

		ergebnis = intEinlesen();

		switch (ergebnis) {

		case 0:
			rueckgabe = new GrandGame();
			break;
		case 1:
			rueckgabe = new NullGame();
			break;
		case 2:
			rueckgabe = new SuitGame(null);
			break;
		default:
			System.out.println(Messages.getI18n("game.type.input.wrong"));
			System.out.println();
			declareSuit();
		}

		return rueckgabe;
	}

	@Override
	public SuitGame suitGame() {

		SuitGame rueckgabe = null;
		int zaehler = 0;
		int ergebnis = -1;

		System.out.println(Messages.getI18n("game.commandline.trump.choose"));
		for (Enum<Suit> farbe : Suit.values()) {

			System.out.println(zaehler + ": " + farbe);
			zaehler++;
		}

		// G&uuml;tige Zahl readInput
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
			rueckgabe = new SuitGame(Suit.BELLS);
			break;
		case 1:
			rueckgabe = new SuitGame(Suit.HEARTS);
			break;
		case 2:
			rueckgabe = new SuitGame(Suit.LEAVES);
			break;
		case 3:
			rueckgabe = new SuitGame(Suit.ACORNS);
			break;
		default:
			System.out.println(Messages.getI18n("game.color.input.wrong"));
		}
		return rueckgabe;
	}

	@Override
	public void outputHand(IPlayer spieler) {

		ArrayList<PlayingCard> blatt = spieler.getHand();

		System.out.println(Messages.getI18n("game.hand.card.yours"));

		for (int i = 0; i < blatt.size(); i++) {

			PlayingCard karte = blatt.get(i);
			// Farbe suitGame = karte.getFarbe();
			// Wert wert = karte.getWert();
			System.out.println(i + ": " + karte.toString());
		}

		System.out.println();
	}

	@Override
	public PlayingCard playCard(PlayingCard[] gespielteKarten, IPlayer spieler) {

		PlayingCard ergebnis = null;
		ArrayList<PlayingCard> blatt = spieler.getHand();
		// gueltig wird true gesetzt, wenn eine gueltige Zahl von Konsole
		// eingelesen wurde.
		boolean gueltig = false;

		// Hilfen:
		if (showPlayableCardsHelp) {
			hilfeSpielbar(gespielteKarten);
		}
		if (showLastTricksHelp) {
			hilfeStiche();
		}

		// Handkarten zeigen
		System.out
				.println(Messages.getI18n("game.commandline.card.play.input"));
		leerzeile();
		outputHand(spieler);

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
									gespielteKarten[i].getOwner().getName(),
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

				s = bufferedReader.readLine();
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
	public void trickWon(IPlayer spieler) {

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
	public void outputSkat(PlayingCard[] skat) {

		System.out.println(Messages.getI18n("game.skat.cards.in"));
		System.out.println(Messages.getI18n("game.skat.cards",
				skat[0].toString(), skat[1].toString()));
		System.out.println();
	}

	@Override
	public void auswertung(boolean gewonnen) {
		if (tisch.getSpielart().getGameVariety() != GameVarietyName.RAMSCH) {
			if (gewonnen == true) {
				System.out.println(Messages.getI18n("player.won", tisch
						.ermittleAlleinspieler().getName()));
				System.out.println(Messages.getI18n(
						"player.win.score",
						tisch.ermittleAlleinspieler()
								.getGames()
								.get(tisch.ermittleAlleinspieler().getGames()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.loose", tisch
						.ermittleAlleinspieler().getName()));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						tisch.ermittleAlleinspieler()
								.getGames()
								.get(tisch.ermittleAlleinspieler().getGames()
										.size() - 1)));
			}
		} else {
			if (gewonnen) {
				System.out.println(Messages.getI18n("player.winner") + " ");
				System.out.println(Messages.getI18n(
						"player.winner.score",
						tisch.gibMenschlicherSpieler()
								.getGames()
								.get(tisch.gibMenschlicherSpieler().getGames()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.looser"));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						tisch.gibMenschlicherSpieler()
								.getGames()
								.get(tisch.gibMenschlicherSpieler().getGames()
										.size() - 1)));
			}
		}
	}

	@Override
	public void gameOver() {
		System.out.println(Messages.getI18n("game.over"));
		System.out.println(Messages.getI18n("game.skat.in.was"));
		PlayingCard[] skat = tisch.getSkat();
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
	public void hhVSgewinner(IPlayer gewinner) {

		String hinterhand = tisch.getHinterhand().getName();
		System.out.println(Messages.getI18n("game.bidding.against", hinterhand,
				gewinner.getName()));
	}

	@Override
	public void spielEinpassen() {

		System.out.println(Messages.getI18n("game.bidding.cancel"));
	}

	@Override
	public void gameBegins() {

		System.out.println(Messages.getI18n("game.starts"));
	}

	@Override
	public void anotherCard() {

		System.out
				.println(Messages.getI18n("game.card.forbidden.other.choose"));
	}

	@Override
	public void augen(int augen) {
		System.out.println(Messages.getI18n("player.declarer.points", tisch
				.ermittleAlleinspieler().getName(), augen));

	}

	@Override
	public void points(int punkte) {
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
	public void gewinntReizen(IPlayer spieler) {

		System.out.println(Messages.getI18n(
				"player.win.bidding.middlehand.forehand", spieler.getName()));
	}

	@Override
	public void trump() {
		if (tisch.getSpielart().getGameVariety() != GameVarietyName.RAMSCH) {
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
	public void newGame() {
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
	public String readInput() {

		String s = "";

		try {
			s = bufferedReader.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return s;
	}

	@Override
	public void spieltKarte(IPlayer spieler, PlayingCard karte) {

		System.out.println(Messages.getI18n("player.name.playing.card",
				spieler.getName(), karte.toString()));
	}

	@Override
	public boolean quitGame() {

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

				string = bufferedReader.readLine();
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
	public void outputPoints() {
		System.out.println("******** ****** ********");
		System.out.println(Messages.getI18n("game.score.list.current"));
		System.out.println(tisch.getSpieler1().getName() + "     "
				+ tisch.getSpieler2().getName() + "     "
				+ tisch.getSpieler3().getName());
		for (int i = 0; i < tisch.getSpieler1().getGames().size(); i++) {
			System.out.println(tisch.getSpieler1().getGames().get(i) + "     "
					+ tisch.getSpieler2().getGames().get(i)
					+ "               "
					+ tisch.getSpieler3().getGames().get(i));
		}
		System.out.println("******** ****** ********");
	}

	@Override
	public void deleteTable() {

	}

	@Override
	public String getDeckSelection() {
		System.out.println(Messages.getI18n("game.skat.deck.choose"));
		System.out.println(Messages.getI18n("game.skat.deck.enter",
				Messages.getI18n("game.skat.deck.f"),
				Messages.getI18n("game.skat.deck.g")));
		String s = "";

		try {

			s = bufferedReader.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		return s;

	}

	@Override
	public String askForSixSkat() {
		System.out.println(Messages.getI18n("game.skat.variant.six.play"));
		System.out.println(Messages.getI18n("application.yes.no.enter",
				Messages.getI18n("application.y"),
				Messages.getI18n("application.n")));
		String s = "";

		try {

			s = bufferedReader.readLine();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		if (s.compareTo(Messages.getI18n("application.y")) != 0) {
			if (s.compareTo(Messages.getI18n("application.n")) != 0) {
				System.out.println(Messages
						.getI18n("game.commandline.input.short.wrong"));
				s = askForVariant();
			}
		}
		return s;
	}

	@Override
	public void openGameTable() {

	}

	@Override
	public void showPosition() {

	}

	@Override
	public void cleanUpGUI() {

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
				showPlayableCardsHelp = true;
			}
			System.out.println(Messages
					.getI18n("game.trick.last.show.question"));
			ergebnis = jaNeinAbfrage();
			if (ergebnis) {
				showLastTricksHelp = true;
			}
		}
	}

	/**
	 * Gibt die spielbaren Karten aus.
	 * 
	 * @param gespielteKarten
	 *            Die Karten, die sich schon auf dem Tisch befinden
	 */
	public void hilfeSpielbar(PlayingCard[] gespielteKarten) {
		ArrayList<PlayingCard> karten = tisch.gibMenschlicherSpieler()
				.playableCards(gespielteKarten);
		System.out.println(Messages.getI18n("game.playable.cards"));
		for (PlayingCard karte : karten) {
			System.out.println(karte.toString());
		}
	}

	/**
	 * Gibt die vergangenen Stiche aus;
	 */
	public void hilfeStiche() {
		ArrayList<PlayingCard> karten = tisch.getSpieler1()
				.getAllPlayedCards();
		System.out.println(Messages.getI18n("game.tricks.last"));
		for (PlayingCard karte : karten) {
			System.out.println(karte.toString());
		}
	}

	@Override
	public void statistics() {
		// Titel
		System.out.println("*************************************");
		System.out.println("**************"
				+ Messages.getI18n("game.statistic") + "**************");
		System.out.println("");

		IPlayer[] allPlayer = tisch.getAllPlayer();
		for (IPlayer player : allPlayer) {
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
	private void statisticOut(Table table, IPlayer player) {

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
				table.anzahlderGewinne(player), player.getGames().size()));
		System.out.print(Messages.getI18n(
				"game.statistic.declarer.quantity.hand", player.getName())
				+ "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.hand.result",
				player.getHandGames(), player.getGames().size()));
	}
}
