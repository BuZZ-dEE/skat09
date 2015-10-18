package main.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedSet;

import main.Messages;
import main.Table;
import main.gamevariety.GameVarietyName;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.player.IPlayer;
import main.playingcard.PlayingCard;
import main.playingcard.Suit;
import main.playingcard.Value;

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
	private Table table;
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
		this.table = table;

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
	 * bestimmten Reizwert mitgeht oder pass ist.
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
	public void pass(IPlayer player) {

		String name = player.getName();

		System.out.println(Messages.getI18n("game.hear.off", name));
		blankLine();
	}

	/**
	 * Fragt den Spieler welchen Wert er reizen will und liefert ihn
	 * zur&uuml;ck.
	 * 
	 * @param biddingValue
	 *            - der aktuell gebotene Reizwert
	 */
	public boolean bid(int biddingValue) {

		System.out.println(Messages.getI18n("game.say.value", biddingValue));

		return askForYesNo();
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
	public String adversary(int number) {

		String s = "";

		if (number == 1) {

			System.out.println(Messages
					.getI18n("game.commandline.adversary.first.choose"));

		} else if (number == 2) {

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
					s = adversary(number);
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
		help();
		return s;

	}

	/**
	 * Fragt den Spieler, ob er hand spielen m&ouml;chte.
	 * 
	 * @return true, falls handgame
	 */
	public boolean handgame() {

		System.out.println(Messages.getI18n("game.skat.take"));

		return !askForYesNo();
	}

	@Override
	public int druecken(ArrayList<PlayingCard> hand, int number) {

		IGameVariety spielart = new GrandGame();
		int result = -1;

		if (number == 1) {
			System.out.println(Messages
					.getI18n("game.skat.choose.press.card.first"));
		} else if (number == 2) {
			System.out.println(Messages.getI18n("game.skat.press.card.second"));
		} else if (number == 3) { // TODO why this case?
			System.out.println(Messages.getI18n("game.skat.press.card.third"));
		}

		// Karten sortieren
		table.getHumanPlayer().sortHand(spielart);

		// Alle Karten auflisten
		System.out.println(Messages.getI18n("game.deck.yours") + ":");
		for (int i = 0; i < hand.size(); i++) {

			PlayingCard card = hand.get(i);
			Suit suit = card.getSuit();
			Value value = card.getValue();
			System.out.println(i + ": " + suit + " " + value); // TODO also
																// translate
		}

		// Einlesen
		// TODO Sechserskat anpassen!
		boolean correctInput = false;
		while (!correctInput) {

			result = readIntFromCommandLine();

			if (result > -1 && result < 12) {

				correctInput = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		return result;
	}

	@Override
	public boolean schneider() {

		System.out.println(Messages.getI18n("game.schneider.play.choose"));

		return askForYesNo();
	}

	@Override
	public boolean schwarz() {

		System.out.println(Messages.getI18n("game.schwarz.play.choose"));

		return askForYesNo();
	}

	@Override
	public boolean ouvert() {

		System.out.println(Messages.getI18n("game.ouvert.play.choose"));

		return askForYesNo();
	}

	@Override
	public IGameVariety declareSuit() {

		int counter = 0;
		int result = -1;
		IGameVariety gameVariety = null;

		System.out.println(Messages.getI18n("game.type.number.choose"));
		for (Enum<GameVarietyName> gameVarietyName : GameVarietyName
				.values()) {

			System.out.println(counter + ": " + gameVarietyName);
			counter++;
		}

		result = readIntFromCommandLine();

		switch (result) {

		case 0:
			gameVariety = new GrandGame();
			break;
		case 1:
			gameVariety = new NullGame();
			break;
		case 2:
			gameVariety = new SuitGame(null);
			break;
		default:
			System.out.println(Messages.getI18n("game.type.input.wrong"));
			System.out.println();
			declareSuit();
		}

		return gameVariety;
	}

	@Override
	public SuitGame suitGame() {

		SuitGame suitGame = null;
		int counter = 0;
		int result = -1;

		System.out.println(Messages.getI18n("game.commandline.trump.choose"));
		for (Enum<Suit> suit : Suit.values()) {

			System.out.println(counter + ": " + suit);
			counter++;
		}

		// G&uuml;tige Zahl readInput
		boolean correctInput = false;
		while (!correctInput) {

			result = readIntFromCommandLine();

			if (-1 < result && result < 4) {

				correctInput = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		switch (result) {

		case 0:
			suitGame = new SuitGame(Suit.BELLS);
			break;
		case 1:
			suitGame = new SuitGame(Suit.HEARTS);
			break;
		case 2:
			suitGame = new SuitGame(Suit.LEAVES);
			break;
		case 3:
			suitGame = new SuitGame(Suit.ACORNS);
			break;
		default:
			System.out.println(Messages.getI18n("game.color.input.wrong"));
		}
		return suitGame;
	}

	@Override
	public void outputHand(IPlayer player) {

		ArrayList<PlayingCard> hand = player.getHand();

		System.out.println(Messages.getI18n("game.hand.card.yours"));

		for (int i = 0; i < hand.size(); i++) {

			PlayingCard card = hand.get(i);
			System.out.println(i + ": " + card.toString());
		}

		System.out.println();
	}

	@Override
	public PlayingCard playCard(PlayingCard[] playedCards, IPlayer player) {

		PlayingCard result = null;
		ArrayList<PlayingCard> hand = player.getHand();
		// valid wird true gesetzt, wenn eine gueltige Zahl von Konsole
		// eingelesen wurde.
		boolean valid = false;

		// Hilfen:
		if (showPlayableCardsHelp) {
			showPlayableCardsHelp(playedCards);
		}
		if (showLastTricksHelp) {
			showPastTricksHelp();
		}

		// Handkarten zeigen
		System.out
				.println(Messages.getI18n("game.commandline.card.play.input"));
		blankLine();
		outputHand(player);

		// Tischkarten zeigen
		if (playedCards[0] == null) {

			System.out.println(Messages.getI18n("game.getOut.you"));
		} else {

			System.out.println(Messages.getI18n("game.table.cards.on"));

			for (int i = 0; i < 3; i++) {

				if (playedCards[i] != null) {
					System.out.println(i
							+ ": "
							+ Messages.getI18n("player.card.played",
									playedCards[i].getOwner().getName(),
									playedCards[i].toString()));
				}
			}
		}
		// Einlesen und Ausgeben
		int number = readIntFromCommandLine();

		while (!valid) {
			if (number <= hand.size() - 1) {
				valid = true;
			} else {
				System.out.println(Messages
						.getI18n("game.commandline.card.number.input.wrong"));
				number = readIntFromCommandLine();
			}
		}
		result = hand.remove(number);
		return result;
	}

	/**
	 * Diese Methode liest eine Zahl von der Konsole aus und gibt sie
	 * zur&uuml;ck.
	 */
	public int readIntFromCommandLine() {

		boolean correctInput = false;
		String s = "";
		int result = -1;

		// Einlesen
		while (!correctInput) {
			try {

				s = bufferedReader.readLine();
			}

			catch (IOException e) {

				e.printStackTrace();
			}
			// String to int
			try {
				result = Integer.parseInt(s);
				correctInput = true;
			} catch (Exception E) {
				System.out.println(Messages
						.getI18n("game.commandline.input.not.number"));
				correctInput = false;
			}
		}
		return result;
	}

	@Override
	public void trickWon(IPlayer player) {

		System.out.println(">> "
				+ Messages.getI18n("player.trick.won", player.getName()));
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
	public void showEvaluation(boolean isWon) {
		if (table.getGameVariety().getGameVariety() != GameVarietyName.RAMSCH) {
			if (isWon == true) {
				System.out.println(Messages.getI18n("player.won", table
						.getDeclarer().getName()));
				System.out.println(Messages.getI18n(
						"player.win.score",
						table.getDeclarer()
								.getGames()
								.get(table.getDeclarer().getGames()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.loose", table
						.getDeclarer().getName()));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						table.getDeclarer()
								.getGames()
								.get(table.getDeclarer().getGames()
										.size() - 1)));
			}
		} else {
			if (isWon) {
				System.out.println(Messages.getI18n("player.winner") + " ");
				System.out.println(Messages.getI18n(
						"player.winner.score",
						table.getHumanPlayer()
								.getGames()
								.get(table.getHumanPlayer().getGames()
										.size() - 1)));
			} else {
				System.out.println(Messages.getI18n("player.looser"));
				System.out.println(Messages.getI18n(
						"player.looser.score",
						table.getHumanPlayer()
								.getGames()
								.get(table.getHumanPlayer().getGames()
										.size() - 1)));
			}
		}
	}

	@Override
	public void gameOver() {
		System.out.println(Messages.getI18n("game.over"));
		System.out.println(Messages.getI18n("game.skat.in.was"));
		PlayingCard[] skat = table.getSkat();
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
	public void mittelhandVsVorhand() {

		String mittelhand = table.getMittelhand().getName();
		String vorhand = table.getVorhand().getName();
		System.out.println(Messages.getI18n("game.bidding.against", mittelhand,
				vorhand));
	}

	/**
	 * Gibt eine Leerzeile auf der Konsole aus.
	 */
	public void blankLine() {

		System.out.println();
	}

	@Override
	public void hinterhandVsWinner(IPlayer winner) {

		String hinterhand = table.getHinterhand().getName();
		System.out.println(Messages.getI18n("game.bidding.against", hinterhand,
				winner.getName()));
	}

	@Override
	public void passGame() {

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
		System.out.println(Messages.getI18n("player.declarer.points", table
                .getDeclarer().getName(), augen));

	}

	@Override
	public void points(int punkte) {
		System.out.println(Messages.getI18n("player.declarer.score", table
                .getDeclarer().getName(), punkte));
	}

	@Override
	public void showDeclarer() {
		System.out.println(Messages.getI18n("player.name.playing", table
                .getDeclarer().getName()));

	}

	/**
	 * Gibt an welcher Spieler das Reizen gewonnen hat
	 * 
	 * @param player
	 */
	public void gewinntReizen(IPlayer player) {

		System.out.println(Messages.getI18n(
                "player.win.bidding.middlehand.forehand", player.getName()));
	}

	@Override
	public void trump() {
		if (table.getGameVariety().getGameVariety() != GameVarietyName.RAMSCH) {
			System.out.println(Messages.getI18n("player.name.playing.game",
					table.getDeclarer().getName(), table
							.getGameVariety().toString()));
			System.out.println("");
		} else {
			System.out.println(Messages.getI18n("game.ramsch.playing"));
			System.out.println("");
		}
	}

	@Override
	public void newGame() {
		blankLine();
		System.out.println("*************************************");
		System.out.println(Messages.getI18n("game.new.begins"));
		blankLine();

	}

	@Override
	public boolean reizAgent() {

		System.out.println(Messages.getI18n("game.agent.bidding.use"));

		return askForYesNo();
	}

	@Override
	public int reizlimitFestlegen() {

		int result = -1;
		boolean ready = false;

		System.out.println(Messages.getI18n("game.agent.bidding.you.use"));
		System.out.println(Messages.getI18n("game.agent.bidding.value.enter"));
		System.out.println(Messages.getI18n("game.agent.bidding.zero.cancel"));
		result = readIntFromCommandLine();
		while (!ready) {

			SortedSet<Integer> reizwerte = table.getBiddingValues();
			if (reizwerte.contains(result) || result == 0) {

				ready = true;
			} else {

				result = readIntFromCommandLine();
			}
		}
		return result;
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
	public void spieltKarte(IPlayer player, PlayingCard card) {

		System.out.println(Messages.getI18n("player.name.playing.card",
				player.getName(), card.toString()));
	}

	@Override
	public boolean quitGame() {

		System.out.println(Messages.getI18n("player.game.end"));

		return askForYesNo();
	}

	/**
	 * Fragt den Benutzer, ob er ja oder nein bid m&oouml;chte.
	 * 
	 * @return true, falls ja; false falls nein
	 */
	public boolean askForYesNo() {

		System.out.println(Messages.getI18n("application.yes.no.enter",
				Messages.getI18n("application.y"),
				Messages.getI18n("application.n")));

		boolean correctInput = false;
		boolean result = false;
		String string = "";

		while (!correctInput) {
			try {

				string = bufferedReader.readLine();
			}

			catch (IOException e) {

				e.printStackTrace();
			}
			if (string.equalsIgnoreCase(Messages.getI18n("application.y"))) {

				result = true;
				correctInput = true;
			} else if (string.equalsIgnoreCase(Messages
					.getI18n("application.n"))) {

				result = false;
				correctInput = true;
			} else {

				System.out.println(Messages.getI18n("application.input.wrong"));
			}
		}
		return result;
	}

	@Override
	public void outputPoints() {
		System.out.println("******** ****** ********");
		System.out.println(Messages.getI18n("game.score.list.current"));
		System.out.println(table.getPlayer1().getName() + "     "
				+ table.getPlayer2().getName() + "     "
				+ table.getPlayer3().getName());
		for (int i = 0; i < table.getPlayer1().getGames().size(); i++) {
			System.out.println(table.getPlayer1().getGames().get(i) + "     "
					+ table.getPlayer2().getGames().get(i)
					+ "               "
					+ table.getPlayer3().getGames().get(i));
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
	public void help() {
		System.out.println(Messages.getI18n("game.help.want"));
		boolean result = askForYesNo();
		if (result) {
			System.out.println(Messages.getI18n("game.playable.cards.show.question"));
			result = askForYesNo();
			if (result) {
				showPlayableCardsHelp = true;
			}
			System.out.println(Messages
					.getI18n("game.trick.last.show.question"));
			result = askForYesNo();
			if (result) {
				showLastTricksHelp = true;
			}
		}
	}

	/**
	 * Gibt die spielbaren Karten aus.
	 * 
	 * @param playedCards
	 *            Die Karten, die sich schon auf dem Tisch befinden
	 */
	public void showPlayableCardsHelp(PlayingCard[] playedCards) {
		ArrayList<PlayingCard> cards = table.getHumanPlayer()
				.playableCards(playedCards);
		System.out.println(Messages.getI18n("game.playable.cards"));
		for (PlayingCard card : cards) {
			System.out.println(card.toString());
		}
	}

	/**
	 * Gibt die vergangenen Stiche aus;
	 */
	public void showPastTricksHelp() {
		ArrayList<PlayingCard> cards = table.getPlayer1()
				.getAllPlayedCards();
		System.out.println(Messages.getI18n("game.tricks.last"));
		for (PlayingCard card : cards) {
			System.out.println(card.toString());
		}
	}

	@Override
	public void statistics() {
		// Titel
		System.out.println("*************************************");
		System.out.println("**************"
				+ Messages.getI18n("game.statistic") + "**************");
		System.out.println("");

		IPlayer[] allPlayer = table.getAllPlayer();
		for (IPlayer player : allPlayer) {
			statisticOut(table, player);
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
				table.getPercentDeclarer(player)));
		System.out.print(Messages
				.getI18n("game.statistic.declarer.quantity.won") + "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.won.result",
				table.getDeclarersWonGamesSum(player), player.getGames().size()));
		System.out.print(Messages.getI18n(
				"game.statistic.declarer.quantity.hand", player.getName())
				+ "   ");
		System.out.println(Messages.getI18n(
				"game.statistic.declarer.quantity.hand.result",
				player.getHandGames(), player.getGames().size()));
	}
}
