package skat09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spielart.Ramsch;
import skat09.spielart.Spielartbezeichnung;
import skat09.spieler.HumanPlayer;
import skat09.spieler.Oma;
import skat09.spieler.RuleCompliantPlayer;
import skat09.spieler.SmartPlayer;
import skat09.spielkarte.Spielkarte;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.IHumanPlayer;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;


/**
 *
 * Der Controller ist die Logik des Spiels, alle relevanten Ereignisse im Spiel werden
 * hier definiert und koordiniert 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 *
 */
public class Controller implements Observer, IController {

	/**
	 * H&auml;lt einen Tisch, auf dem das Spiel stattfindet
	 */
	private Table tisch;
	
	/**
	 * eine Ausgabe, auf der das Spiel ausgegeben wird
	 */
	private IOutput ausgabe;

	
	/**
	 * wird gesetzt, falls niemand spielen will und somit das Spiel einzupassen
	 * ist
	 */
	private boolean spielEinpassen;

	/**
	 * 
	 * @param tisch wird übergeben um darauf arbeiten zu k&ouml;nnen
	 * @param ausgabe wir ben&ouml;tigt um das Spiel ausgeben zu k
	 * 
	 * 
	 */
	public Controller(Table tisch, IOutput ausgabe) {
		this.tisch = tisch;
		this.ausgabe = ausgabe;
		spielEinpassen = false;
	}

	//@Override
	public IOutput getAusgabe() {

		return ausgabe;
	}

	/**
	 * Dient zur r&uuml;ckgabe des Tisches
	 * @return tisch gibt den Tisch zur&uuml;ck
	 */
	public Table getTisch() {

		return tisch;
	}

	//@Override
	public void release() {

		tisch = null;
		ausgabe = null;
	}

	//@Override
	public void spiel() throws IOException {

		boolean weiterspielen = true;
		anmelden();
		waehleSkatart();
		waehleGegner();
		namenVergleich();
		waehleSkatblatt();
		tisch.positionInitialisieren();

		do {
			bereiteSpielvor();
			ausgabe.positionAnzeigen();
			ausgabe.neuesSpiel();

			if (tisch.getVariante() == SkatVariant.SKAT) {
				spielIntSkat();
			}

			else if (tisch.getVariante() == SkatVariant.RAMSCHBOCK) {
				spielRamschBock();
			}

			else if (tisch.getVariante() == SkatVariant.RAEUBER) {
				spielRaeuberskat();
			}

			weiterspielen = !spielBeenden();

		} while (weiterspielen);

		System.exit(1);
	}

	//@Override
	public void anmelden() {

		String name = "";
		name = ausgabe.name();
		ISpieler spieler = new HumanPlayer(name, this);
		tisch.setSpieler1(spieler);

	}

	//@Override
	public void waehleGegner() {

		String s;

		s = ausgabe.gegner(1);

		// Eingabe o fuer Oma
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			ISpieler spieler2 = new Oma("Oma Karla");
			tisch.setSpieler2(spieler2);
		}
		// Eingabe r fuer regelkonformer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			ISpieler spieler2 = new RuleCompliantPlayer("Hans");
			tisch.setSpieler2(spieler2);
		}
		// Eingabe s fuer schlauer Spieler
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			ISpieler spieler2 = new SmartPlayer("Heinz");
			tisch.setSpieler2(spieler2);
		}

		// Zweiten Gegegner auf die gleiche Weise waehlen
		s = ausgabe.gegner(2);

		if (s.equals(Messages.getI18n("game.commandline.adversary.type.granny.abbr"))) {

			ISpieler spieler3 = new Oma("Oma Berta");
			tisch.setSpieler3(spieler3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.normal.abbr"))) {

			ISpieler spieler3 = new RuleCompliantPlayer("Franz");
			tisch.setSpieler3(spieler3);
		}
		if (s.equals(Messages.getI18n("game.commandline.adversary.type.smart.abbr"))) {

			ISpieler spieler3 = new SmartPlayer("Wolfgang");
			tisch.setSpieler3(spieler3);
		}
	}

	//@Override
	public void waehleSkatart() {
		
		String s;
		s = ausgabe.frageVariante();

		if (s.equals(Messages.getI18n("game.commandline.skat.type.robber.abbr"))) {

			tisch.setVariante(SkatVariant.RAEUBER);
		}
		if (s.equals(Messages.getI18n("game.commandline.skat.type.international.abbr"))) {

			tisch.setVariante(SkatVariant.SKAT);
		}
		if (s.equals(Messages.getI18n("game.commandline.skat.type.bock.abbr"))) {
			tisch.setVariante(SkatVariant.RAMSCHBOCK);
		}

		s = ausgabe.frageSechserskat();
		if (s.equals(Messages.getI18n("application.y"))) {
			tisch.setSechserskat(true);
		}
	}

	//@Override
	public void waehleSkatblatt() {

		String s;

		s = ausgabe.getBlattwahl();
		if (s.equals(Messages.getI18n("game.skat.deck.g"))) {
			Spielkarte.setDeutsch(true);
		} else if (s.equals(Messages.getI18n("game.skat.deck.f"))) {

			Spielkarte.setDeutsch(false);
		} else {
			waehleSkatblatt();
		}
	}

	//@Override
	public void leiteReizen() throws IOException {

		// Positionen der Spieler ermitteln
		ISpieler vorhand = tisch.getVorhand();
		ISpieler mittelhand = tisch.getMittelhand();
		ISpieler hinterhand = tisch.getHinterhand();

		// ermitteln, ob menschlicher Spieler den Reizagent benutzen will
		ISpieler mensch = tisch.gibMenschlicherSpieler();
		ausgabe.blattAusgeben(mensch);
		boolean reizagent = mensch.agent();

		// Für den menschlichen Spieler das Blatt ausgeben
		// mensch = tisch.gibMenschlicherSpieler();

		// Falls Reizagent aktiviert wurde >> reizlimit abfragen
		int reizlimit = -1;
		if (reizagent) {

			reizlimit = mensch.reizlimitFestlegen();
		}
		tisch.setReizagentWert(reizlimit);
		// Gewinner des Reizens zwischen Mittelhand und Vorhand
		ISpieler gewinner = null;

		// Alleinspieler - Spieler der das gesamte Reizen gewonnen hat
		ISpieler alleinspieler = null;

		// reizen Mittelhand vs. Vorhand
		ausgabe.mhVSvh();
		ausgabe.leerzeile();
		gewinner = reizen1(vorhand, mittelhand);

		// reizen Hinterhand vs. Gewinner
		ausgabe.hhVSgewinner(gewinner);
		ausgabe.leerzeile();
		alleinspieler = reizen2(gewinner, hinterhand);

		// Auswertung: Falls niemand spielen will, spielEinpassen setzten,
		// ansonsten Alleinspieler im Spiele setzen
		if (alleinspieler == null) {

			spielEinpassen = true;
		}

		else {

			alleinspieler.setIstAlleinspieler(true);
			ausgabe.alleinspieler();
		}
	}

	//@Override
	public ISpieler reizen2(ISpieler spieler1, ISpieler spieler2) {

		// Gewinner des reizens
		ISpieler gewinner = null;

		boolean sagen = false;
		boolean hoeren = false;
		int reizwert = tisch.getReizwert();

		if (reizwert == 18) {

			// Spieler sagen lassen oder Reizagent &uuml;bernimmt
			sagen = reizenOderReizagent(spieler2, reizwert, true);

			if (!sagen) {

				ausgabe.weg(spieler2);

				// Spieler hoeren lassen oder Reizagent &uuml;bernimmt
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
					ausgabe.weg(spieler1);
				} else {

					// Reizwert erh&ouml;en und am Tisch setzen
					reizwert = tisch.naechstHoehererReizwert(reizwert);
					tisch.setReizwert(reizwert);
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
	public boolean reizenOderReizagent(ISpieler spieler, int reizwert,
			boolean sagen) {

		boolean ergebnis = false;

		if (tisch.getReizagentWert() >= 0
				&& spieler instanceof IHumanPlayer) {

			ergebnis = reizagent(spieler);
		} else {

			if (sagen) {

				ergebnis = spieler.sagen(reizwert);
			} else {

				ergebnis = spieler.hoeren(reizwert);
			}
		}
		return ergebnis;
	}

	//@Override
	public ISpieler reizen1(ISpieler spieler1, ISpieler spieler2) {

		// Gewinner des Reizens
		ISpieler gewinner = null;

		boolean fertig = false;
		boolean sagen = false;
		boolean hoeren = false;
		int reizwert = tisch.getReizwert();

		while (!fertig) {

			// Spieler2 sagen lassen oder Reizagent &uuml;bernimmt
			sagen = reizenOderReizagent(spieler2, reizwert, true);

			// erste if Schleife ueberprueft ob nur ein spieler weg sein
			// darf und liefert dann vorzeitig den gewinner
			if (!sagen) {

				gewinner = spieler1;
				ausgabe.weg(spieler2);
				break;
			} else {

				// Spieler1 h&ouml;ren lassen oder Reizagent &uuml;bernimmt
				hoeren = reizenOderReizagent(spieler1, reizwert, false);

				if (!hoeren) {

					gewinner = spieler2;
					ausgabe.weg(spieler1);
					break;
				}
			}
			reizwert = tisch.naechstHoehererReizwert(reizwert);
			tisch.setReizwert(reizwert); // neu!
		}
		// neuen Reizwert am Tisch setzen
		tisch.setReizwert(reizwert);
		return gewinner;
	}

	//@Override
	public void entscheideraeuberspiel() {

		tisch.getVorhand().setIstAlleinspieler(true);

	}

	//@Override
	public void leiteSpiel() throws NullPointerException, IOException {

		ISpieler spieler1 = tisch.getVorhand();
		ISpieler spieler2 = null;
		ISpieler spieler3 = null;
		Spielkarte[] gespielteKarten;

		ausgabe.spielBeginnt();
		int anzahlstiche = 10;

		if (tisch.getSechserskat() == true) {
			anzahlstiche = 11;
		}

		for (int i = 0; i < anzahlstiche; i++) {

			// Spieler initialisieren
			spieler2 = tisch.naechsterSpieler(spieler1);
			spieler3 = tisch.naechsterSpieler(spieler2);

			// Mitspieler setzen
			tisch.mitspielerSetzen();

			// jeden Spieler eine Karte Spielen lassen und Tisch aktualisieren
			gespielteKarten = tisch.getGespielteKarten();
			gespielteKarten[0] = spieler1.spieleKarte(gespielteKarten);
			tisch.setGespielteKarten(gespielteKarten);

			gespielteKarten[1] = spieler2.spieleKarte(gespielteKarten);
			tisch.setGespielteKarten(gespielteKarten);

			gespielteKarten[2] = spieler3.spieleKarte(gespielteKarten);
			tisch.setGespielteKarten(gespielteKarten);

			// Spieler1 neu setzen
			spieler1 = tisch.stichAuswerten(tisch.getSpielart(),
					gespielteKarten);

			// Spieler1 den gewonnenen Stich geben.
			spieler1.stichHinzufuegen(gespielteKarten);

			// jedem Spieler mitteilen, welche Karten gespielt wurden
			tisch.getSpieler1().gespielteKartenHinzufuegen(gespielteKarten);
			tisch.getSpieler2().gespielteKartenHinzufuegen(gespielteKarten);
			tisch.getSpieler3().gespielteKartenHinzufuegen(gespielteKarten);

			// Stichauswertung ausgeben
			stichAuswertung(gespielteKarten, spieler1);

			if (tisch.getSpielart().getSpielart() == Spielartbezeichnung.NULL
					&& spieler1.getName() == tisch.ermittleAlleinspieler()
							.getName()) {
				
				Spielkarte[] leer = new Spielkarte[3];
				tisch.setGespielteKarten(leer);
				break;
			}

			// Tisch aufraeumen
			Spielkarte[] leer = new Spielkarte[3];
			tisch.setGespielteKarten(leer);
		}
	}

	//@Override
	public void alleinspielerAktionen() throws IOException {

		// Der Spieler wird gefragt, ob er Hand spielt
		ISpieler alleinspieler = tisch.ermittleAlleinspieler();
		ISpieler mensch = tisch.gibMenschlicherSpieler();
		ausgabe.blattAusgeben(mensch);

		tisch.setHandspiel(alleinspieler.handspiel());
		alleinspieler.setHandspiele(alleinspieler.getHandspiele() + 1);
		ISpielart spielart = null;
		Spielkarte[] skat = null;

		// Falls der Spieler kein Handspiel macht, muss er zwei Karten
		// dr&uuml;cken.
		if (!tisch.getHandspiel()) {

			// Skat dem Spieler anzeigen
			// Skat vom Tisch holen, dem Spieler geben und gedr&uuml;ckte Karten
			// wieder zum Tisch geben.
			ausgabe.skatAusgeben(tisch.getSkat());
			tisch.setSkat(alleinspieler.druecken(tisch.getSkat()));
		}
		// Spielansage
		spielart = alleinspieler.spielAnsagen();

		// Falls es sich um ein Farbspiel handelt wird eine Trumpffarbe
		// ben&ouml;tigt.
		if (spielart instanceof Farbspiel) {

			spielart = alleinspieler.farbe();
		}
		// Spielart ist ermittelt und wird am Tisch sowie bei den Spielern
		// gesetzt.
		tisch.setSpielart(spielart);
		tisch.getSpieler1().setSpielart(spielart);
		tisch.getSpieler2().setSpielart(spielart);
		tisch.getSpieler3().setSpielart(spielart);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		// Dies geschieht nur für die menschlichen Spieler.
		for (ISpieler alleSpieler : new ISpieler[] { tisch.getSpieler1(),
				tisch.getSpieler2(), tisch.getSpieler3() }) {

			if (alleSpieler instanceof IHumanPlayer) {

				((ISpieler) alleSpieler).blattSortieren(spielart);
			}
		}

		// Den Skat in das Blatt einordnen, um die Spitzen zu z&auml;hlen.
		skat = tisch.getSkat();
		alleinspieler.getBlatt().add(skat[0]);
		alleinspieler.getBlatt().add(skat[1]);

		if (tisch.getSechserskat()) {

			alleinspieler.getBlatt().add(skat[2]);
		}

		alleinspieler.spitzenEinordnen();
		if (tisch.getSechserskat()) {

			alleinspieler.getBlatt().remove(13);
			alleinspieler.getBlatt().remove(12);
			alleinspieler.getBlatt().remove(11);
		} else {

			alleinspieler.getBlatt().remove(11);
			alleinspieler.getBlatt().remove(10);
		}


		// &Uuml;berpr&uuml;fen, ob schneider/schwarz/ouvert gefragt werden muss
		// und entsprechende Variablen am Tisch setzen.
		flagsSetzen(alleinspieler, spielart);
		schlauerSpielerInit();
	}

	//@Override
	public void bereiteSpielvor() {

		tisch.erstelleDeck();

		for (ISpieler alleSpieler : new ISpieler[] { tisch.getSpieler1(),
				tisch.getSpieler2(), tisch.getSpieler3() }) {
			if (alleSpieler instanceof SmartPlayer) {
				
				((SmartPlayer) alleSpieler)
						.setDeck(new ArrayList<Spielkarte>((tisch.getDeck())));
			}
		}

		tisch.mischeDeck();
		tisch.kartenAusteilen();

		// für alle menschlichen Spieler das Blatt nach Grandspiel sortieren
		for (ISpieler alleSpieler : new ISpieler[] { tisch.getSpieler1(),
				tisch.getSpieler2(), tisch.getSpieler3() }) {

			if (alleSpieler instanceof HumanPlayer) {

				((HumanPlayer) alleSpieler)
						.blattSortieren(new Grandspiel());
			}

			if (alleSpieler instanceof SmartPlayer) {

				((SmartPlayer) alleSpieler).setAnfangsblatt(alleSpieler
						.getBlatt());
				if (tisch.getVariante() == SkatVariant.SKAT
						|| tisch.getVariante() == SkatVariant.RAMSCHBOCK) {
					
					((SmartPlayer) alleSpieler).bestimmeMaxReizwert();
				}
			}
		}

		tisch.kartenBesitzergeben();

	}

	//@Override
	public void auswertung() {
		// Spieler spieler = tisch.ermittleAlleinspieler();
		boolean gewonnen = false;
		int augen = 0;

		ausgabe.spielBeendet();
		if (tisch.getSpielart().getSpielart() != Spielartbezeichnung.RAMSCH) {
			
			augen = tisch.werteAugen(tisch.ermittleAlleinspieler().getStiche());
			int punkte = tisch.wertePunkte(augen);
			ausgabe.augen(augen);
			ausgabe.punkte(punkte);
		}
		gewonnen = tisch.spielAuswerten();

		ausgabe.auswertung(gewonnen);
		ausgabe.punkteAusgeben();
		ausgabe.statistik();

	}

	//@Override
	public void aufrauemen() {

		spielEinpassen = false;

		Spielkarte[] skat = new Spielkarte[3];
		tisch.setSkat(skat);
		tisch.setSpielart(null);
		tisch.setReizwert(18);
		tisch.setReizagentWert(0);
		tisch.setBock(false);
		tisch.setHandspiel(false);
		tisch.setSchneider(false);
		tisch.setSchwarz(false);
		tisch.setOuvert(false);

		if (tisch.getVariante() == SkatVariant.RAMSCHBOCK
				&& tisch.getSpaltarsch() && tisch.getBockrunden() == 0) {
			tisch.setSpaltarsch(false);
		}

		for (ISpieler alleSpieler : new ISpieler[] { tisch.getSpieler1(),
				tisch.getSpieler2(), tisch.getSpieler3() }) {

			alleSpieler.setBlatt(null);
			alleSpieler.setIstAlleinspieler(false);
			alleSpieler.setSpielart(null);
			alleSpieler.setStiche(new ArrayList<Spielkarte>());
			alleSpieler.setAlleGespieltenKarten(new ArrayList<Spielkarte>());
		}
		ausgabe.guiAufraumen();
	}

	//@Override
	public void stichAuswertung(Spielkarte[] gespielteKarten, ISpieler gewinner) {
		ausgabe.tischLoeschen();
		for (int i = 0; i < 3; i++) {

			Spielkarte karte = gespielteKarten[i];
			ISpieler besitzer = karte.getBesitzer();
			ausgabe.spieltKarte(besitzer, karte);
		}
		ausgabe.leerzeile();
		ausgabe.stichGewonnen(gewinner);
		ausgabe.leerzeile();
	}

	//@Override
	public boolean reizagent(ISpieler spieler) {

		boolean ergebnis = false;

		int reizwert = tisch.getReizwert();
		int maxReizwert = tisch.getReizagentWert();

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

		return ausgabe.spielBeenden();
	}

	//@Override
	public void skatkartenBesitzergeben() {

		ISpieler alleinspieler = tisch.ermittleAlleinspieler();
		Spielkarte[] skat = tisch.getSkat();
		skat[0].setBesitzer(alleinspieler);
		skat[1].setBesitzer(alleinspieler);
		if (tisch.getSechserskat()) {
			skat[2].setBesitzer(alleinspieler);
		}
		tisch.setSkat(skat);
	}

	//@Override
	public void flagsSetzen(ISpieler alleinspieler, ISpielart spielart) {

		if (tisch.getHandspiel()) {

			if (spielart instanceof Nullspiel) {

				tisch.setSchneider(false);
				tisch.setSchwarz(false);
				tisch.setOuvert(alleinspieler.ouvert());
			} else if (alleinspieler.schneider()) {

				tisch.setSchneider(true);

				if (alleinspieler.schwarz()) {

					tisch.setSchwarz(true);

					if (alleinspieler.ouvert()) {

						tisch.setOuvert(true);
					}
				}
			}
		} else {

			if (spielart instanceof Nullspiel) {

				tisch.setSchneider(true);
				tisch.setSchwarz(true);
				tisch.setOuvert(alleinspieler.ouvert());
			} else {

				tisch.setSchneider(false);
				tisch.setSchwarz(false);
				tisch.setOuvert(false);
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
		while (!ausgabe.getRelease()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ausgabe.setRelease(false);
	}

	//@Override
	public void einpassen() throws IOException {

		ausgabe.spielEinpassen();
		ausgabe.leerzeile();
		tisch.positionWechseln();
		aufrauemen();
	}

	//@Override
	public void ramschen() throws NullPointerException, IOException {
		ISpielart spielart = new Ramsch();
		tisch.setSpielart(spielart);
		tisch.getSpieler1().setSpielart(spielart);
		tisch.getSpieler2().setSpielart(spielart);
		tisch.getSpieler3().setSpielart(spielart);

		// Sortieren der Spielerbl&auml;tter nachdem Spielart nun bekannt ist.
		tisch.getSpieler1().blattSortieren(spielart);
		tisch.getSpieler2().blattSortieren(spielart);
		tisch.getSpieler3().blattSortieren(spielart);
		ausgabe.trumpf();
		leiteSpiel();
		auswertung();
		aufrauemen();
		tisch.positionWechseln();
	}

	//@Override
	public void normalerSpielverlauf() throws IOException {
		skatkartenBesitzergeben();
		alleinspielerAktionen();
		ausgabe.trumpf();
		leiteSpiel();
		auswertung();
		aufrauemen();
		tisch.positionWechseln();
	}

	//@Override
	public void spielRaeuberskat() throws IOException {
		entscheideraeuberspiel();
		ausgabe.alleinspieler();
		normalerSpielverlauf();
	}

	//@Override
	public void spielRamschBock() throws IOException {
		if (tisch.getSpaltarsch() && tisch.getRamschrunden() == 0) {
			tisch.setBock(true);
			tisch.setBockrunden(tisch.getBockrunden() - 1);
		}

		if (!tisch.getSpaltarsch()
				|| (tisch.getSpaltarsch() && tisch.getRamschrunden() == 0)) {
			leiteReizen();

			if (!spielEinpassen) {
				normalerSpielverlauf();
			} else {
				ramschen();

			}
		} else {
			ramschen();
			tisch.setRamschrunden(tisch.getRamschrunden() - 1);
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
		for (ISpieler alleSpieler : new ISpieler[] { tisch.getSpieler1(),
				tisch.getSpieler2(), tisch.getSpieler3() }) {

			if (alleSpieler instanceof SmartPlayer) {

				if (alleSpieler.getIstAlleinspieler()) {

					alleSpieler.setSkat(new ArrayList<Spielkarte>(Arrays
							.asList(tisch.getSkat())));
				}
			}
		}
	}

	//@Override
	public void namenVergleich() {

		String spieler1 = tisch.getSpieler1().getName();
		String spieler2 = tisch.getSpieler2().getName();
		String spieler3 = tisch.getSpieler3().getName();

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
		tisch.getSpieler1().setName(spieler1);
		tisch.getSpieler2().setName(spieler2);
		tisch.getSpieler3().setName(spieler3);
	}
}