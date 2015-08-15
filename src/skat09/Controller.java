package skat09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import skat09.gamevariety.GameVarietyName;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.NullGame;
import skat09.gamevariety.Ramsch;
import skat09.gamevariety.SuitGame;
import skat09.player.Granny;
import skat09.player.HumanPlayer;
import skat09.player.RuleCompliantPlayer;
import skat09.player.SmartPlayer;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;


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
		int biddingValue = table.getReizwert();

		if (biddingValue == 18) {

			// Spieler bid lassen oder Reizagent &uuml;bernimmt
			bid = reizenOderReizagent(player2, biddingValue, true);

			if (!bid) {

				output.pass(player2);

				// Spieler respond lassen oder Reizagent &uuml;bernimmt
				respond = reizenOderReizagent(player1, biddingValue, true);

				if (!respond) {

					winner = null;
				} else {

					winner = player1;
				}
			} else {

				// Spieler h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				respond = reizenOderReizagent(player1, biddingValue, false);

				if (!respond) {

					winner = player2;
					output.pass(player1);
				} else {

					// Reizwert erh&ouml;en und am Tisch setzen
					biddingValue = table.naechstHoehererReizwert(biddingValue);
					table.setReizwert(biddingValue);
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
	public boolean reizenOderReizagent(IPlayer spieler, int reizwert,
			boolean sagen) {

		boolean ergebnis = false;

		if (table.getReizagentWert() >= 0
				&& spieler instanceof IHumanPlayer) {

			ergebnis = reizagent(spieler);
		} else {

			if (sagen) {

				ergebnis = spieler.bid(reizwert);
			} else {

				ergebnis = spieler.respond(reizwert);
			}
		}
		return ergebnis;
	}

	//@Override
	public IPlayer bidding1(IPlayer spieler1, IPlayer spieler2) {

		// Gewinner des Reizens
		IPlayer gewinner = null;

		boolean fertig = false;
		boolean sagen = false;
		boolean hoeren = false;
		int reizwert = table.getReizwert();

		while (!fertig) {

			// Spieler2 bid lassen oder Reizagent &uuml;bernimmt
			sagen = reizenOderReizagent(spieler2, reizwert, true);

			// erste if Schleife ueberprueft ob nur ein spieler pass sein
			// darf und liefert dann vorzeitig den gewinner
			if (!sagen) {

				gewinner = spieler1;
				output.pass(spieler2);
				break;
			} else {

				// Spieler1 h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				hoeren = reizenOderReizagent(spieler1, reizwert, false);

				if (!hoeren) {

					gewinner = spieler2;
					output.pass(spieler1);
					break;
				}
			}
			reizwert = table.naechstHoehererReizwert(reizwert);
			table.setReizwert(reizwert); // neu!
		}
		// neuen Reizwert am Tisch setzen
		table.setReizwert(reizwert);
		return gewinner;
	}

	//@Override
	public void entscheideraeuberspiel() {

		table.getVorhand().setIsDeclarer(true);

	}

	//@Override
	public void leiteSpiel() throws NullPointerException, IOException {

		IPlayer spieler1 = table.getVorhand();
		IPlayer spieler2 = null;
		IPlayer spieler3 = null;
		PlayingCard[] gespielteKarten;

		output.gameBegins();
		int anzahlstiche = 10;

		if (table.getSechserskat() == true) {
			anzahlstiche = 11;
		}

		for (int i = 0; i < anzahlstiche; i++) {

			// Spieler initialisieren
			spieler2 = table.naechsterSpieler(spieler1);
			spieler3 = table.naechsterSpieler(spieler2);

			// Mitspieler setzen
			table.mitspielerSetzen();

			// jeden Spieler eine Karte Spielen lassen und Tisch aktualisieren
			gespielteKarten = table.getGespielteKarten();
			gespielteKarten[0] = spieler1.playCard(gespielteKarten);
			table.setGespielteKarten(gespielteKarten);

			gespielteKarten[1] = spieler2.playCard(gespielteKarten);
			table.setGespielteKarten(gespielteKarten);

			gespielteKarten[2] = spieler3.playCard(gespielteKarten);
			table.setGespielteKarten(gespielteKarten);

			// Spieler1 neu setzen
			spieler1 = table.stichAuswerten(table.getSpielart(),
					gespielteKarten);

			// Spieler1 den gewonnenen Stich geben.
			spieler1.addTrick(gespielteKarten);

			// jedem Spieler mitteilen, welche Karten gespielt wurden
			table.getSpieler1().addPlayedCards(gespielteKarten);
			table.getSpieler2().addPlayedCards(gespielteKarten);
			table.getSpieler3().addPlayedCards(gespielteKarten);

			// Stichauswertung ausgeben
			stichAuswertung(gespielteKarten, spieler1);

			if (table.getSpielart().getGameVariety() == GameVarietyName.NULL
					&& spieler1.getName() == table.ermittleAlleinspieler()
							.getName()) {
				
				PlayingCard[] leer = new PlayingCard[3];
				table.setGespielteKarten(leer);
				break;
			}

			// Tisch aufraeumen
			PlayingCard[] leer = new PlayingCard[3];
			table.setGespielteKarten(leer);
		}
	}

	//@Override
	public void alleinspielerAktionen() throws IOException {

		// Der Spieler wird gefragt, ob er Hand spielt
		IPlayer alleinspieler = table.ermittleAlleinspieler();
		IPlayer mensch = table.getHumanPlayer();
		output.outputHand(mensch);

		table.setHandspiel(alleinspieler.handgame());
		alleinspieler.setHandGames(alleinspieler.getHandGames() + 1);
		IGameVariety spielart = null;
		PlayingCard[] skat = null;

		// Falls der Spieler kein Handspiel macht, muss er zwei Karten
		// dr&uuml;cken.
		if (!table.getHandspiel()) {

			// Skat dem Spieler anzeigen
			// Skat vom Tisch holen, dem Spieler geben und gedr&uuml;ckte Karten
			// wieder zum Tisch geben.
			output.outputSkat(table.getSkat());
			table.setSkat(alleinspieler.druecken(table.getSkat()));
		}
		// Spielansage
		spielart = alleinspieler.declareGame();

		// Falls es sich um ein Farbspiel handelt wird eine Trumpffarbe
		// ben&ouml;tigt.
		if (spielart instanceof SuitGame) {

			spielart = alleinspieler.suit();
		}
		// Spielart ist ermittelt und wird am Tisch sowie bei den Spielern
		// gesetzt.
		table.setSpielart(spielart);
		table.getSpieler1().setGameVariety(spielart);
		table.getSpieler2().setGameVariety(spielart);
		table.getSpieler3().setGameVariety(spielart);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		// Dies geschieht nur für die menschlichen Spieler.
		for (IPlayer alleSpieler : new IPlayer[] { table.getSpieler1(),
				table.getSpieler2(), table.getSpieler3() }) {

			if (alleSpieler instanceof IHumanPlayer) {

				((IPlayer) alleSpieler).sortHand(spielart);
			}
		}

		// Den Skat in das Blatt einordnen, um die Spitzen zu z&auml;hlen.
		skat = table.getSkat();
		alleinspieler.getHand().add(skat[0]);
		alleinspieler.getHand().add(skat[1]);

		if (table.getSechserskat()) {

			alleinspieler.getHand().add(skat[2]);
		}

		alleinspieler.spitzenEinordnen();
		if (table.getSechserskat()) {

			alleinspieler.getHand().remove(13);
			alleinspieler.getHand().remove(12);
			alleinspieler.getHand().remove(11);
		} else {

			alleinspieler.getHand().remove(11);
			alleinspieler.getHand().remove(10);
		}


		// &Uuml;berpr&uuml;fen, ob schneider/schwarz/ouvert gefragt werden muss
		// und entsprechende Variablen am Tisch setzen.
		flagsSetzen(alleinspieler, spielart);
		schlauerSpielerInit();
	}

	//@Override
	public void prepareGame() {

		table.erstelleDeck();

		for (IPlayer alleSpieler : new IPlayer[] { table.getSpieler1(),
				table.getSpieler2(), table.getSpieler3() }) {
			if (alleSpieler instanceof SmartPlayer) {
				
				((SmartPlayer) alleSpieler)
						.setDeck(new ArrayList<PlayingCard>((table.getDeck())));
			}
		}

		table.mischeDeck();
		table.kartenAusteilen();

		// für alle menschlichen Spieler das Blatt nach Grandspiel sortieren
		for (IPlayer alleSpieler : new IPlayer[] { table.getSpieler1(),
				table.getSpieler2(), table.getSpieler3() }) {

			if (alleSpieler instanceof HumanPlayer) {

				((HumanPlayer) alleSpieler)
						.sortHand(new GrandGame());
			}

			if (alleSpieler instanceof SmartPlayer) {

				((SmartPlayer) alleSpieler).setAnfangsblatt(alleSpieler
						.getHand());
				if (table.getVariant() == SkatVariant.SKAT
						|| table.getVariant() == SkatVariant.RAMSCHBOCK) {
					
					((SmartPlayer) alleSpieler).bestimmeMaxReizwert();
				}
			}
		}

		table.kartenBesitzergeben();

	}

	//@Override
	public void auswertung() {
		// Spieler spieler = tisch.ermittleAlleinspieler();
		boolean gewonnen = false;
		int augen = 0;

		output.gameOver();
		if (table.getSpielart().getGameVariety() != GameVarietyName.RAMSCH) {
			
			augen = table.werteAugen(table.ermittleAlleinspieler().getTricks());
			int punkte = table.wertePunkte(augen);
			output.augen(augen);
			output.points(punkte);
		}
		gewonnen = table.spielAuswerten();

		output.showEvaluation(gewonnen);
		output.outputPoints();
		output.statistics();

	}

	//@Override
	public void aufrauemen() {

		passGame = false;

		PlayingCard[] skat = new PlayingCard[3];
		table.setSkat(skat);
		table.setSpielart(null);
		table.setReizwert(18);
		table.setBiddingAgentValue(0);
		table.setBock(false);
		table.setHandspiel(false);
		table.setSchneider(false);
		table.setSchwarz(false);
		table.setOuvert(false);

		if (table.getVariant() == SkatVariant.RAMSCHBOCK
				&& table.getSpaltarsch() && table.getBockrunden() == 0) {
			table.setSpaltarsch(false);
		}

		for (IPlayer alleSpieler : new IPlayer[] { table.getSpieler1(),
				table.getSpieler2(), table.getSpieler3() }) {

			alleSpieler.setHand(null);
			alleSpieler.setIsDeclarer(false);
			alleSpieler.setGameVariety(null);
			alleSpieler.setTricks(new ArrayList<PlayingCard>());
			alleSpieler.setAllPlayedCards(new ArrayList<PlayingCard>());
		}
		output.cleanUpGUI();
	}

	//@Override
	public void stichAuswertung(PlayingCard[] gespielteKarten, IPlayer gewinner) {
		output.deleteTable();
		for (int i = 0; i < 3; i++) {

			PlayingCard karte = gespielteKarten[i];
			IPlayer besitzer = karte.getOwner();
			output.spieltKarte(besitzer, karte);
		}
		output.blankLine();
		output.trickWon(gewinner);
		output.blankLine();
	}

	//@Override
	public boolean reizagent(IPlayer spieler) {

		boolean ergebnis = false;

		int reizwert = table.getReizwert();
		int maxReizwert = table.getReizagentWert();

		//Falls der Spieler passen will
		if (maxReizwert == 0) {
			
			ergebnis = false;
		}
		else if (maxReizwert >= reizwert) {

			ergebnis = true;
		}

		return ergebnis;
	}

	//@Override
	public boolean quitGame() {

		return output.quitGame();
	}

	//@Override
	public void skatkartenBesitzergeben() {

		IPlayer alleinspieler = table.ermittleAlleinspieler();
		PlayingCard[] skat = table.getSkat();
		skat[0].setOwner(alleinspieler);
		skat[1].setOwner(alleinspieler);
		if (table.getSechserskat()) {
			skat[2].setOwner(alleinspieler);
		}
		table.setSkat(skat);
	}

	//@Override
	public void flagsSetzen(IPlayer alleinspieler, IGameVariety spielart) {

		if (table.getHandspiel()) {

			if (spielart instanceof NullGame) {

				table.setSchneider(false);
				table.setSchwarz(false);
				table.setOuvert(alleinspieler.ouvert());
			} else if (alleinspieler.schneider()) {

				table.setSchneider(true);

				if (alleinspieler.schwarz()) {

					table.setSchwarz(true);

					if (alleinspieler.ouvert()) {

						table.setOuvert(true);
					}
				}
			}
		} else {

			if (spielart instanceof NullGame) {

				table.setSchneider(true);
				table.setSchwarz(true);
				table.setOuvert(alleinspieler.ouvert());
			} else {

				table.setSchneider(false);
				table.setSchwarz(false);
				table.setOuvert(false);
			}
		}
	}

	//@Override
	public void update(Observable tisch, Object gespielteKarten) {
		//		
		// Spielkarte[] gespielterKarten = this.tisch.getGespielteKarten();
		// if (gespielteKarte[0] == null) {
		// //Stich dem Gewinner ueberreicht.
		// }
		// else
	}

	//@Override
	public void warte() {
//		while (!output.getRelease()) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		output.setRelease(false);
		
		
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	while (!output.getRelease()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    	e.printStackTrace();
                    }
            	}
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	output.setRelease(false);
            }
        });
        new Thread(sleeper).start();
	}

	//@Override
	public void einpassen() throws IOException {

		output.passGame();
		output.blankLine();
		table.positionWechseln();
		aufrauemen();
	}

	//@Override
	public void ramschen() throws NullPointerException, IOException {
		IGameVariety spielart = new Ramsch();
		table.setSpielart(spielart);
		table.getSpieler1().setGameVariety(spielart);
		table.getSpieler2().setGameVariety(spielart);
		table.getSpieler3().setGameVariety(spielart);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		table.getSpieler1().sortHand(spielart);
		table.getSpieler2().sortHand(spielart);
		table.getSpieler3().sortHand(spielart);
		output.trump();
		leiteSpiel();
		auswertung();
		aufrauemen();
		table.positionWechseln();
	}

	//@Override
	public void normalerSpielverlauf() throws IOException {
		skatkartenBesitzergeben();
		alleinspielerAktionen();
		output.trump();
		leiteSpiel();
		auswertung();
		aufrauemen();
		table.positionWechseln();
	}

	//@Override
	public void playRaeuberskat() throws IOException {
		entscheideraeuberspiel();
		output.showDeclarer();
		normalerSpielverlauf();
	}

	//@Override
	public void playRamschBock() throws IOException {
		if (table.getSpaltarsch() && table.getRamschrunden() == 0) {
			table.setBock(true);
			table.setBockrunden(table.getBockrunden() - 1);
		}

		if (!table.getSpaltarsch()
				|| (table.getSpaltarsch() && table.getRamschrunden() == 0)) {
			coordinateBidding();

			if (!passGame) {
				normalerSpielverlauf();
			} else {
				ramschen();

			}
		} else {
			ramschen();
			table.setRamschrunden(table.getRamschrunden() - 1);
		}
	}

	//@Override
	public void playIntSkat() throws IOException {
		coordinateBidding();
		if (!passGame) {
			normalerSpielverlauf();
		} else {
			einpassen();
		}
	}

	//@Override
	public void schlauerSpielerInit() {

		// für alle schlauen Spieler das Deck setzen.
		for (IPlayer alleSpieler : new IPlayer[] { table.getSpieler1(),
				table.getSpieler2(), table.getSpieler3() }) {

			if (alleSpieler instanceof SmartPlayer) {

				if (alleSpieler.isDeclarer()) {

					alleSpieler.setSkat(new ArrayList<PlayingCard>(Arrays
							.asList(table.getSkat())));
				}
			}
		}
	}

	//@Override
	public void namesComparison() {

		String spieler1 = table.getSpieler1().getName();
		String spieler2 = table.getSpieler2().getName();
		String spieler3 = table.getSpieler3().getName();

		if (spieler1.equals(spieler2) && spieler1.equals(spieler3)
				&& spieler2.equals(spieler3)) {
			spieler1 = spieler1 + 1;
			spieler2 = spieler2 + 2;
			spieler3 = spieler3 + 3;
		} else if (spieler1.equals(spieler2)) {

			spieler1 = spieler1 + 1;
			spieler2 = spieler2 + 2;
		} else if (spieler1.equals(spieler3)) {

			spieler1 = spieler1 + 1;
			spieler3 = spieler3 + 2;
		} else if (spieler2.equals(spieler3)) {

			spieler2 = spieler2 + 1;
			spieler3 = spieler3 + 2;
		}
		table.getSpieler1().setName(spieler1);
		table.getSpieler2().setName(spieler2);
		table.getSpieler3().setName(spieler3);
	}
}