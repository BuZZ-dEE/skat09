package skat09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

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
	private boolean spielEinpassen;

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
		spielEinpassen = false;
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
		anmelden();
		chooseSkatVariant();
		waehleGegner();
		namenVergleich();
		waehleSkatblatt();
		table.positionInitialisieren();

		do {
			bereiteSpielvor();
			output.positionAnzeigen();
			output.newGame();

			if (table.getVariante() == SkatVariant.SKAT) {
				spielIntSkat();
			}

			else if (table.getVariante() == SkatVariant.RAMSCHBOCK) {
				spielRamschBock();
			}

			else if (table.getVariante() == SkatVariant.RAEUBER) {
				spielRaeuberskat();
			}

			isContinue = !spielBeenden();

		} while (isContinue);

		System.exit(1);
	}

	//@Override
	public void anmelden() {

		String name = "";
		name = output.name();
		IPlayer spieler = new HumanPlayer(name, this);
		table.setSpieler1(spieler);

	}

	//@Override
	public void waehleGegner() {

		String s;

		s = output.gegner(1);

		// Eingabe o fuer Oma
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			IPlayer spieler2 = new Granny("Oma Karla");
			table.setSpieler2(spieler2);
		}
		// Eingabe r fuer regelkonformer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			IPlayer spieler2 = new RuleCompliantPlayer("Hans");
			table.setSpieler2(spieler2);
		}
		// Eingabe s fuer schlauer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			IPlayer spieler2 = new SmartPlayer("Heinz");
			table.setSpieler2(spieler2);
		}

		// Zweiten Gegegner auf die gleiche Weise waehlen
		s = output.gegner(2);

		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			IPlayer spieler3 = new Granny("Oma Berta");
			table.setSpieler3(spieler3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			IPlayer spieler3 = new RuleCompliantPlayer("Franz");
			table.setSpieler3(spieler3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			IPlayer spieler3 = new SmartPlayer("Wolfgang");
			table.setSpieler3(spieler3);
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
			table.setSechserskat(true);
		}
	}

	//@Override
	public void waehleSkatblatt() {

		String s;

		s = output.getBlattwahl();
		if (s.equals(Messages.getI18n("game.skat.deck.g"))) {
			PlayingCard.setGermanDeck(true);
		} else if (s.equals(Messages.getI18n("game.skat.deck.f"))) {

			PlayingCard.setGermanDeck(false);
		} else {
			waehleSkatblatt();
		}
	}

	//@Override
	public void leiteReizen() throws IOException {

		// Positionen der Spieler ermitteln
		IPlayer vorhand = table.getVorhand();
		IPlayer mittelhand = table.getMittelhand();
		IPlayer hinterhand = table.getHinterhand();

		// ermitteln, ob menschlicher Spieler den Reizagent benutzen will
		IPlayer mensch = table.gibMenschlicherSpieler();
		output.blattAusgeben(mensch);
		boolean reizagent = mensch.agent();

		// Für den menschlichen Spieler das Blatt ausgeben
		// mensch = tisch.gibMenschlicherSpieler();

		// Falls Reizagent aktiviert wurde >> reizlimit abfragen
		int reizlimit = -1;
		if (reizagent) {

			reizlimit = mensch.setBidLimit();
		}
		table.setReizagentWert(reizlimit);
		// Gewinner des Reizens zwischen Mittelhand und Vorhand
		IPlayer gewinner = null;

		// Alleinspieler - Spieler der das gesamte Reizen gewonnen hat
		IPlayer alleinspieler = null;

		// reizen Mittelhand vs. Vorhand
		output.mhVSvh();
		output.leerzeile();
		gewinner = reizen1(vorhand, mittelhand);

		// reizen Hinterhand vs. Gewinner
		output.hhVSgewinner(gewinner);
		output.leerzeile();
		alleinspieler = reizen2(gewinner, hinterhand);

		// Auswertung: Falls niemand spielen will, spielEinpassen setzten,
		// ansonsten Alleinspieler im Spiele setzen
		if (alleinspieler == null) {

			spielEinpassen = true;
		}

		else {

			alleinspieler.setIsDeclarer(true);
			output.alleinspieler();
		}
	}

	//@Override
	public IPlayer reizen2(IPlayer spieler1, IPlayer spieler2) {

		// Gewinner des reizens
		IPlayer gewinner = null;

		boolean sagen = false;
		boolean hoeren = false;
		int reizwert = table.getReizwert();

		if (reizwert == 18) {

			// Spieler sagen lassen oder Reizagent &uuml;bernimmt
			sagen = reizenOderReizagent(spieler2, reizwert, true);

			if (!sagen) {

				output.weg(spieler2);

				// Spieler respond lassen oder Reizagent &uuml;bernimmt
				hoeren = reizenOderReizagent(spieler1, reizwert, true);

				if (!hoeren) {

					gewinner = null;
				} else {

					gewinner = spieler1;
				}
			} else {

				// Spieler h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				hoeren = reizenOderReizagent(spieler1, reizwert, false);

				if (!hoeren) {

					gewinner = spieler2;
					output.weg(spieler1);
				} else {

					// Reizwert erh&ouml;en und am Tisch setzen
					reizwert = table.naechstHoehererReizwert(reizwert);
					table.setReizwert(reizwert);
					gewinner = reizen1(spieler1, spieler2);
				}
			}
		}

		// falls der Reizwert nicht 18 war, wird normal gereizt, bis ein Spieler
		// weg ist.
		else {

			gewinner = reizen1(spieler1, spieler2);
		}
		return gewinner;
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
	public IPlayer reizen1(IPlayer spieler1, IPlayer spieler2) {

		// Gewinner des Reizens
		IPlayer gewinner = null;

		boolean fertig = false;
		boolean sagen = false;
		boolean hoeren = false;
		int reizwert = table.getReizwert();

		while (!fertig) {

			// Spieler2 sagen lassen oder Reizagent &uuml;bernimmt
			sagen = reizenOderReizagent(spieler2, reizwert, true);

			// erste if Schleife ueberprueft ob nur ein spieler weg sein
			// darf und liefert dann vorzeitig den gewinner
			if (!sagen) {

				gewinner = spieler1;
				output.weg(spieler2);
				break;
			} else {

				// Spieler1 h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				hoeren = reizenOderReizagent(spieler1, reizwert, false);

				if (!hoeren) {

					gewinner = spieler2;
					output.weg(spieler1);
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

		output.spielBeginnt();
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
		IPlayer mensch = table.gibMenschlicherSpieler();
		output.blattAusgeben(mensch);

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
			output.skatAusgeben(table.getSkat());
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
	public void bereiteSpielvor() {

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
				if (table.getVariante() == SkatVariant.SKAT
						|| table.getVariante() == SkatVariant.RAMSCHBOCK) {
					
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

		output.auswertung(gewonnen);
		output.punkteAusgeben();
		output.statistik();

	}

	//@Override
	public void aufrauemen() {

		spielEinpassen = false;

		PlayingCard[] skat = new PlayingCard[3];
		table.setSkat(skat);
		table.setSpielart(null);
		table.setReizwert(18);
		table.setReizagentWert(0);
		table.setBock(false);
		table.setHandspiel(false);
		table.setSchneider(false);
		table.setSchwarz(false);
		table.setOuvert(false);

		if (table.getVariante() == SkatVariant.RAMSCHBOCK
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
		output.guiAufraumen();
	}

	//@Override
	public void stichAuswertung(PlayingCard[] gespielteKarten, IPlayer gewinner) {
		output.tischLoeschen();
		for (int i = 0; i < 3; i++) {

			PlayingCard karte = gespielteKarten[i];
			IPlayer besitzer = karte.getOwner();
			output.spieltKarte(besitzer, karte);
		}
		output.leerzeile();
		output.stichGewonnen(gewinner);
		output.leerzeile();
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
	public boolean spielBeenden() {

		return output.spielBeenden();
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
	public void einpassen() throws IOException {

		output.spielEinpassen();
		output.leerzeile();
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
	public void spielRaeuberskat() throws IOException {
		entscheideraeuberspiel();
		output.alleinspieler();
		normalerSpielverlauf();
	}

	//@Override
	public void spielRamschBock() throws IOException {
		if (table.getSpaltarsch() && table.getRamschrunden() == 0) {
			table.setBock(true);
			table.setBockrunden(table.getBockrunden() - 1);
		}

		if (!table.getSpaltarsch()
				|| (table.getSpaltarsch() && table.getRamschrunden() == 0)) {
			leiteReizen();

			if (!spielEinpassen) {
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
	public void spielIntSkat() throws IOException {
		leiteReizen();
		if (!spielEinpassen) {
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
	public void namenVergleich() {

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