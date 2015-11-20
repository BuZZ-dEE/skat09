package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import main.gamevariety.GameVariety;
import main.gamevariety.GrandGame;
import main.gamevariety.IGameVariety;
import main.gamevariety.NullGame;
import main.gamevariety.Ramsch;
import main.gamevariety.SuitGame;
import main.player.Granny;
import main.player.HumanPlayer;
import main.player.IHumanPlayer;
import main.player.IPlayer;
import main.player.RuleCompliantPlayer;
import main.player.SmartPlayer;
import main.playingcard.PlayingCard;
import main.ui.IOutput;


/**
 *
 * Der Controller ist die Logik des Spiels, alle relevanten Ereignisse im Spiel werden
 * hier definiert und koordiniert 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 * @version 23.07.2015 00:20:54
 *
 */
public class Controller implements Observer, IController {

	/**
	 * The skat table for the game.
	 */
	private Table table;
	
	/**
	 * Output for the game..
	 */
	private IOutput output;

	
	/**
	 * wird gesetzt, falls niemand spielen will und somit das Spiel einzupassen
	 * ist
	 */
	private boolean passGame;

	/**
	 * 
	 * @param table wird übergeben um darauf arbeiten zu k&ouml;nnen
	 * @param output wir ben&ouml;tigt um das Spiel ausgeben zu k
	 * 
	 * 
	 */
	public Controller(Table table, IOutput output) {
		this.table = table;
		this.output = output;
		passGame = false;
	}

	//@Override
	public IOutput getOutput() {

		return output;
	}

	/**
	 * Dient zur r&uuml;ckgabe des Tisches
	 * @return tisch gibt den Tisch zur&uuml;ck
	 */
	public Table getTable() {

		return table;
	}

	//@Override
	public void release() {

		table = null;
		output = null;
	}

	//@Override
	public void play() throws IOException {

		boolean isContinue = true;
		logIn();
		chooseSkatVariant();
		selectAdversary();
		namesComparison();
		selectSkatDeck();
		table.initializePositions();

		do {
			prepareGame();
			output.showPosition();
			output.newGame();

			if (table.getVariant() == SkatVariant.SKAT) {
				playIntSkat();
			}

			else if (table.getVariant() == SkatVariant.RAMSCHBOCK) {
				playRamschBock();
			}

			else if (table.getVariant() == SkatVariant.RAEUBER) {
				playRaeuberskat();
			}

			isContinue = !quitGame();

		} while (isContinue);

		System.exit(1);
	}

	//@Override
	public void logIn() {

		String name = "";
		name = output.name();
		IPlayer player = new HumanPlayer(name, this);
		table.setPlayer1(player);

	}

	//@Override
	public void selectAdversary() {

		String s;

		s = output.adversary(1);

		// Eingabe o fuer Oma
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			IPlayer player2 = new Granny("Oma Karla");
			table.setPlayer2(player2);
		}
		// Eingabe r fuer regelkonformer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			IPlayer player2 = new RuleCompliantPlayer("Hans");
			table.setPlayer2(player2);
		}
		// Eingabe s fuer schlauer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			IPlayer player2 = new SmartPlayer("Heinz");
			table.setPlayer2(player2);
		}

		// Zweiten Gegegner auf die gleiche Weise waehlen
		s = output.adversary(2);

		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			IPlayer player3 = new Granny("Oma Berta");
			table.setPlayer3(player3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			IPlayer player3 = new RuleCompliantPlayer("Franz");
			table.setPlayer3(player3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			IPlayer player3 = new SmartPlayer("Wolfgang");
			table.setPlayer3(player3);
		}
	}

	//@Override
	public void chooseSkatVariant() {
		
		String s;
		s = output.askForVariant();

		if (s.equals(Messages.getI18n("game.commandline.skat.type.robber.abbr"))) {

			table.setVariant(SkatVariant.RAEUBER);
		}
		if (s.equals(Messages.getI18n("game.commandline.skat.type.international.abbr"))) {

			table.setVariant(SkatVariant.SKAT);
		}
		if (s.equals(Messages.getI18n("game.commandline.skat.type.bock.abbr"))) {
			table.setVariant(SkatVariant.RAMSCHBOCK);
		}

		s = output.askForSixSkat();
		if (s.equals(Messages.getI18n("application.y"))) {
			table.setSixSkat(true);
		}
	}

	//@Override
	public void selectSkatDeck() {

		String s;

		s = output.getDeckSelection();
		if (s.equals(Messages.getI18n("game.skat.deck.g"))) {
			PlayingCard.setGermanDeck(true);
		} else if (s.equals(Messages.getI18n("game.skat.deck.f"))) {

			PlayingCard.setGermanDeck(false);
		} else {
			selectSkatDeck();
		}
	}

	//@Override
	public void coordinateBidding() throws IOException {

		// Positionen der Spieler ermitteln
		IPlayer vorhand = table.getVorhand();
		IPlayer mittelhand = table.getMittelhand();
		IPlayer hinterhand = table.getHinterhand();

		// ermitteln, ob menschlicher Spieler den Reizagent benutzen will
		IPlayer human = table.getHumanPlayer();
		output.outputHand(human);
		boolean isUseBiddingAgent = human.agent();

		// Für den menschlichen Spieler das Blatt ausgeben
		// human = tisch.getHumanPlayer();

		// Falls Reizagent aktiviert wurde >> biddingLimit abfragen
		int biddingLimit = -1;
		if (isUseBiddingAgent) {

			biddingLimit = human.setBidLimit();
		}
		table.setBiddingAgentValue(biddingLimit);
		// Gewinner des Reizens zwischen Mittelhand und Vorhand
		IPlayer winner = null;

		// Alleinspieler - Spieler der das gesamte Reizen gewonnen hat
		IPlayer declarer = null;

		// reizen Mittelhand vs. Vorhand
		output.mittelhandVsVorhand();
		output.blankLine();
		winner = bidding1(vorhand, mittelhand);

		// reizen Hinterhand vs. Gewinner
		output.hinterhandVsWinner(winner);
		output.blankLine();
		declarer = bidding2(winner, hinterhand);

		// Auswertung: Falls niemand spielen will, passGame setzten,
		// ansonsten Alleinspieler im Spiele setzen
		if (declarer == null) {

			passGame = true;
		}

		else {

			declarer.setIsDeclarer(true);
			output.showDeclarer();
		}
	}

	//@Override
	public IPlayer bidding2(IPlayer player1, IPlayer player2) {

		// Gewinner des reizens
		IPlayer winner = null;

		boolean bid = false;
		boolean respond = false;
		int biddingValue = table.getBiddingValue();

		if (biddingValue == 18) {

			// Spieler bid lassen oder Reizagent &uuml;bernimmt
			bid = bidOrBiddingAgent(player2, biddingValue, true);

			if (!bid) {

				output.pass(player2);

				// Spieler respond lassen oder Reizagent &uuml;bernimmt
				respond = bidOrBiddingAgent(player1, biddingValue, true);

				if (!respond) {

					winner = null;
				} else {

					winner = player1;
				}
			} else {

				// Spieler h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				respond = bidOrBiddingAgent(player1, biddingValue, false);

				if (!respond) {

					winner = player2;
					output.pass(player1);
				} else {

					// Reizwert erh&ouml;en und am Tisch setzen
					biddingValue = table.nextGreaterBiddingValue(biddingValue);
					table.setBiddingValue(biddingValue);
					winner = bidding1(player1, player2);
				}
			}
		}

		// falls der Reizwert nicht 18 war, wird normal gereizt, bis ein Spieler
		// pass ist.
		else {

			winner = bidding1(player1, player2);
		}
		return winner;
	}

	//@Override
	public boolean bidOrBiddingAgent(IPlayer player, int biddingValue,
                                     boolean isBidding) {

		boolean result = false;

		if (table.getBiddingAgentValue() >= 0
				&& player instanceof IHumanPlayer) {

			result = bidAgent(player);
		} else {

			if (isBidding) {

				result = player.bid(biddingValue);
			} else {

				result = player.respond(biddingValue);
			}
		}
		return result;
	}

	//@Override
	public IPlayer bidding1(IPlayer player1, IPlayer player2) {

		// Gewinner des Reizens
		IPlayer winner = null;

		boolean ready = false;
		boolean bid = false;
		boolean resond = false;
		int biddingValue = table.getBiddingValue();

		while (!ready) {

			// Spieler2 bid lassen oder Reizagent &uuml;bernimmt
			bid = bidOrBiddingAgent(player2, biddingValue, true);

			// erste if Schleife ueberprueft ob nur ein spieler pass sein
			// darf und liefert dann vorzeitig den winner
			if (!bid) {

				winner = player1;
				output.pass(player2);
				break;
			} else {

				// Spieler1 h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				resond = bidOrBiddingAgent(player1, biddingValue, false);

				if (!resond) {

					winner = player2;
					output.pass(player1);
					break;
				}
			}
			biddingValue = table.nextGreaterBiddingValue(biddingValue);
			table.setBiddingValue(biddingValue); // neu!
		}
		// neuen Reizwert am Tisch setzen
		table.setBiddingValue(biddingValue);
		return winner;
	}

	//@Override
	public void decideRaeuberGame() {

		table.getVorhand().setIsDeclarer(true);

	}

	//@Override
	public void leadGame() throws NullPointerException, IOException {

		IPlayer player1 = table.getVorhand();
		IPlayer player2 = null;
		IPlayer player3 = null;
		PlayingCard[] playedCards;

		output.gameBegins();
		int tricksCount = 10;

		if (table.getSixSkat() == true) {
			tricksCount = 11;
		}

		for (int i = 0; i < tricksCount; i++) {

			// Spieler initialisieren
			player2 = table.nextPlayer(player1);
			player3 = table.nextPlayer(player2);

			// Mitspieler setzen
			table.setTeammate();

			// jeden Spieler eine Karte Spielen lassen und Tisch aktualisieren
			playedCards = table.getPlayedCards();
			playedCards[0] = player1.playCard(playedCards);
			table.setPlayedCards(playedCards);

			playedCards[1] = player2.playCard(playedCards);
			table.setPlayedCards(playedCards);

			playedCards[2] = player3.playCard(playedCards);
			table.setPlayedCards(playedCards);

			// Spieler1 neu setzen
			player1 = table.evaluateTrick(table.getGameVariety(),
					playedCards);

			// Spieler1 den gewonnenen Stich geben.
			player1.addTrick(playedCards);

			// jedem Spieler mitteilen, welche Karten gespielt wurden
			table.getPlayer1().addPlayedCards(playedCards);
			table.getPlayer2().addPlayedCards(playedCards);
			table.getPlayer3().addPlayedCards(playedCards);

			// Stichauswertung ausgeben
			outputTrickEvaluation(playedCards, player1);

			if (table.getGameVariety().getGameVariety() == GameVariety.Name.NULL
					&& player1.getName() == table.getDeclarer()
							.getName()) {
				
				PlayingCard[] leer = new PlayingCard[3];
				table.setPlayedCards(leer);
				break;
			}

			// Tisch aufraeumen
			PlayingCard[] leer = new PlayingCard[3];
			table.setPlayedCards(leer);
		}
	}

	//@Override
	public void declarerActions() throws IOException {

		// Der Spieler wird gefragt, ob er Hand spielt
		IPlayer declarer = table.getDeclarer();
		IPlayer human = table.getHumanPlayer();
		output.outputHand(human);

		table.setHandGame(declarer.handgame());
		declarer.setHandGames(declarer.getHandGames() + 1);
		IGameVariety gameVariety = null;
		PlayingCard[] skat = null;

		// Falls der Spieler kein Handspiel macht, muss er zwei Karten
		// dr&uuml;cken.
		if (!table.getHandGame()) {

			// Skat dem Spieler anzeigen
			// Skat vom Tisch holen, dem Spieler geben und gedr&uuml;ckte Karten
			// wieder zum Tisch geben.
			output.outputSkat(table.getSkat());
			table.setSkat(declarer.druecken(table.getSkat()));
		}
		// Spielansage
		gameVariety = declarer.declareGame();

		// Falls es sich um ein Farbspiel handelt wird eine Trumpffarbe
		// ben&ouml;tigt.
		if (gameVariety instanceof SuitGame) {

			gameVariety = declarer.suit();
		}
		// Spielart ist ermittelt und wird am Tisch sowie bei den Spielern
		// gesetzt.
		table.setGameVariety(gameVariety);
		table.getPlayer1().setGameVariety(gameVariety);
		table.getPlayer2().setGameVariety(gameVariety);
		table.getPlayer3().setGameVariety(gameVariety);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		// Dies geschieht nur für die menschlichen Spieler.
		for (IPlayer allPlayer : new IPlayer[] { table.getPlayer1(),
				table.getPlayer2(), table.getPlayer3() }) {

			if (allPlayer instanceof IHumanPlayer) {

				((IPlayer) allPlayer).sortHand(gameVariety);
			}
		}

		// Den Skat in das Blatt einordnen, um die Spitzen zu z&auml;hlen.
		skat = table.getSkat();
		declarer.getHand().add(skat[0]);
		declarer.getHand().add(skat[1]);

		if (table.getSixSkat()) {

			declarer.getHand().add(skat[2]);
		}

		declarer.arrangeMatadorsJackStraitOrder();
		if (table.getSixSkat()) {

			declarer.getHand().remove(13);
			declarer.getHand().remove(12);
			declarer.getHand().remove(11);
		} else {

			declarer.getHand().remove(11);
			declarer.getHand().remove(10);
		}


		// &Uuml;berpr&uuml;fen, ob schneider/schwarz/ouvert gefragt werden muss
		// und entsprechende Variablen am Tisch setzen.
		setFlags(declarer, gameVariety);
		initializeSmartPlayer();
	}

	//@Override
	public void prepareGame() {

		table.createDeck();

		for (IPlayer allPlayer : new IPlayer[] { table.getPlayer1(),
				table.getPlayer2(), table.getPlayer3() }) {
			if (allPlayer instanceof SmartPlayer) {
				
				((SmartPlayer) allPlayer)
						.setDeck(new ArrayList<PlayingCard>((table.getDeck())));
			}
		}

		table.shuffleDeck();
		table.dealOutCards();

		// für alle menschlichen Spieler das Blatt nach Grandspiel sortieren
		for (IPlayer allPlayer : new IPlayer[] { table.getPlayer1(),
				table.getPlayer2(), table.getPlayer3() }) {

			if (allPlayer instanceof HumanPlayer) {

				((HumanPlayer) allPlayer)
						.sortHand(new GrandGame());
			}

			if (allPlayer instanceof SmartPlayer) {

				((SmartPlayer) allPlayer).setAnfangsblatt(allPlayer
						.getHand());
				if (table.getVariant() == SkatVariant.SKAT
						|| table.getVariant() == SkatVariant.RAMSCHBOCK) {
					
					((SmartPlayer) allPlayer).bestimmeMaxReizwert();
				}
			}
		}

		table.giveCardsToOwner();

	}

	//@Override
	public void evaluation() {
		// Spieler spieler = tisch.getDeclarer();
		boolean won = false;
		int augen = 0;

		output.gameOver();
		if (table.getGameVariety().getGameVariety() != main.gamevariety.GameVariety.Name.RAMSCH) {
			
			augen = table.calculateAugen(table.getDeclarer().getTricks());
			int points = table.calculatePoints(augen);
			output.augen(augen);
			output.points(points);
		}
		won = table.evaluateGame();

		output.showEvaluation(won);
		output.outputPoints();
		output.statistics();

	}

	//@Override
	public void cleanUp() {

		passGame = false;

		PlayingCard[] skat = new PlayingCard[3];
		table.setSkat(skat);
		table.setGameVariety(null);
		table.setBiddingValue(18);
		table.setBiddingAgentValue(0);
		table.setBock(false);
		table.setHandGame(false);
		table.setSchneider(false);
		table.setSchwarz(false);
		table.setOuvert(false);

		if (table.getVariant() == SkatVariant.RAMSCHBOCK
				&& table.getSpaltarsch() && table.getBockRounds() == 0) {
			table.setSpaltarsch(false);
		}

		for (IPlayer allPlayer : new IPlayer[] { table.getPlayer1(),
				table.getPlayer2(), table.getPlayer3() }) {

			allPlayer.setHand(null);
			allPlayer.setIsDeclarer(false);
			allPlayer.setGameVariety(null);
			allPlayer.setTricks(new ArrayList<PlayingCard>());
			allPlayer.setAllPlayedCards(new ArrayList<PlayingCard>());
		}
		output.cleanUpGUI();
	}

	//@Override
	public void outputTrickEvaluation(PlayingCard[] playedCards, IPlayer winner) {
		output.deleteTable();
		for (int i = 0; i < 3; i++) {

			PlayingCard card = playedCards[i];
			IPlayer owner = card.getOwner();
			output.spieltKarte(owner, card);
		}
		output.blankLine();
		output.trickWon(winner);
		output.blankLine();
	}

	//@Override
	public boolean bidAgent(IPlayer player) {

		boolean result = false;

		int biddingValue = table.getBiddingValue();
		int maxBiddingValue = table.getBiddingAgentValue();

		//Falls der Spieler passen will
		if (maxBiddingValue == 0) {
			
			result = false;
		}
		else if (maxBiddingValue >= biddingValue) {

			result = true;
		}

		return result;
	}

	//@Override
	public boolean quitGame() {

		return output.quitGame();
	}

	//@Override
	public void assignSkatCardsToOwner() {

		IPlayer declarer = table.getDeclarer();
		PlayingCard[] skat = table.getSkat();
		skat[0].setOwner(declarer);
		skat[1].setOwner(declarer);
		if (table.getSixSkat()) {
			skat[2].setOwner(declarer);
		}
		table.setSkat(skat);
	}

	//@Override
	public void setFlags(IPlayer declarer, IGameVariety gameVariety) {

		if (table.getHandGame()) {

			if (gameVariety instanceof NullGame) {

				table.setSchneider(false);
				table.setSchwarz(false);
				table.setOuvert(declarer.ouvert());
			} else if (declarer.schneider()) {

				table.setSchneider(true);

				if (declarer.schwarz()) {

					table.setSchwarz(true);

					if (declarer.ouvert()) {

						table.setOuvert(true);
					}
				}
			}
		} else {

			if (gameVariety instanceof NullGame) {

				table.setSchneider(true);
				table.setSchwarz(true);
				table.setOuvert(declarer.ouvert());
			} else {

				table.setSchneider(false);
				table.setSchwarz(false);
				table.setOuvert(false);
			}
		}
	}

	//@Override
	public void update(Observable table, Object playedCards) {
		//
		// Spielkarte[] gespielterKarten = this.tisch.getPlayedCards();
		// if (gespielteKarte[0] == null) {
		// //Stich dem Gewinner ueberreicht.
		// }
		// else
	}

	//@Override
	public void waiting() {
		while (!output.getRelease()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		output.setRelease(false);
	}

	//@Override
	public void passGame() throws IOException {

		output.passGame();
		output.blankLine();
		table.changePosition();
		cleanUp();
	}

	//@Override
	public void ramschen() throws NullPointerException, IOException {
		IGameVariety gameVariety = new Ramsch();
		table.setGameVariety(gameVariety);
		table.getPlayer1().setGameVariety(gameVariety);
		table.getPlayer2().setGameVariety(gameVariety);
		table.getPlayer3().setGameVariety(gameVariety);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		table.getPlayer1().sortHand(gameVariety);
		table.getPlayer2().sortHand(gameVariety);
		table.getPlayer3().sortHand(gameVariety);
		output.trump();
		leadGame();
		evaluation();
		cleanUp();
		table.changePosition();
	}

	//@Override
	public void normalGamePlay() throws IOException {
		assignSkatCardsToOwner();
		declarerActions();
		output.trump();
		leadGame();
		evaluation();
		cleanUp();
		table.changePosition();
	}

	//@Override
	public void playRaeuberskat() throws IOException {
		decideRaeuberGame();
		output.showDeclarer();
		normalGamePlay();
	}

	//@Override
	public void playRamschBock() throws IOException {
		if (table.getSpaltarsch() && table.getRamschRounds() == 0) {
			table.setBock(true);
			table.setBockRounds(table.getBockRounds() - 1);
		}

		if (!table.getSpaltarsch()
				|| (table.getSpaltarsch() && table.getRamschRounds() == 0)) {
			coordinateBidding();

			if (!passGame) {
				normalGamePlay();
			} else {
				ramschen();

			}
		} else {
			ramschen();
			table.setRamschRounds(table.getRamschRounds() - 1);
		}
	}

	//@Override
	public void playIntSkat() throws IOException {
		coordinateBidding();
		if (!passGame) {
			normalGamePlay();
		} else {
			passGame();
		}
	}

	//@Override
	public void initializeSmartPlayer() {

		// für alle schlauen Spieler das Deck setzen.
		for (IPlayer allPlayer : new IPlayer[] { table.getPlayer1(),
				table.getPlayer2(), table.getPlayer3() }) {

			if (allPlayer instanceof SmartPlayer) {

				if (allPlayer.isDeclarer()) {

					allPlayer.setSkat(new ArrayList<PlayingCard>(Arrays
							.asList(table.getSkat())));
				}
			}
		}
	}

	//@Override
	public void namesComparison() {

		String player1 = table.getPlayer1().getName();
		String player2 = table.getPlayer2().getName();
		String player3 = table.getPlayer3().getName();

		if (player1.equals(player2) && player1.equals(player3)
				&& player2.equals(player3)) {
			player1 = player1 + 1;
			player2 = player2 + 2;
			player3 = player3 + 3;
		} else if (player1.equals(player2)) {

			player1 = player1 + 1;
			player2 = player2 + 2;
		} else if (player1.equals(player3)) {

			player1 = player1 + 1;
			player3 = player3 + 2;
		} else if (player2.equals(player3)) {

			player2 = player2 + 1;
			player3 = player3 + 2;
		}
		table.getPlayer1().setName(player1);
		table.getPlayer2().setName(player2);
		table.getPlayer3().setName(player3);
	}
}