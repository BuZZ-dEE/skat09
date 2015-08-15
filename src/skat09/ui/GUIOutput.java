package skat09.ui;

import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import skat09.Messages;
import skat09.Table;
import skat09.gamevariety.GrandGame;
import skat09.gamevariety.NullGame;
import skat09.gamevariety.SuitGame;
import skat09.player.PlayerEnum;
import skat09.playingcard.PlayingCard;
import skat09.playingcard.Suit;
import skat09.test.interfaces.IGameVariety;
import skat09.test.interfaces.IPlayer;
import skat09.ui.gui.*;


/**
 * Diese Klasse besch&auml;ftigt sich mit der gesamten Ausgabe des Spiels fuer
 * den menschlichen Spieler.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class GUIOutput extends Output {
	
	/**
	 * Der Tisch, auf dem gespielt wird
	 */
	private Table tisch;
	/**
	 * Das Hauptfenster des Spiels
	 */
	private GameTableFrame hfenster;
	/**
	 * Das Einstellungsfenster des Spiels
	 */
	private SetupFrame fenster;

	/**
	 * Der Konstruktor der Klasse GUIAusgabe
	 * 
	 * @param tisch
	 *           Der Tisch, auf dem gespielt wird
	 */
	public GUIOutput(Table tisch) {

		this.tisch = tisch;

		fenster = new SetupFrame(this);

	}

	@Override
	public boolean respond(int wert) {
		hfenster.hoeren(wert);
		warte();
		boolean erg = hfenster.getHoeren();
		return erg;
	}

	@Override
	public boolean bid(int alterWert) {
		hfenster.sagen(alterWert);
		warte();
		boolean erg = hfenster.getSagen();
		return erg;
	}

	@Override
	public void play() {

	}

	@Override
	public String name() {

		return fenster.getName();

	}

	@Override
	public String adversary(int nummer) {

		String s = "";
		if (nummer == 1) {

			if (fenster.getMitspieler1() == PlayerEnum.GRANNY) {

				s = Messages.getI18n("game.commandline.adversary.type.granny.abbr");
			} else if (fenster.getMitspieler1() == PlayerEnum.RULECOMPLIANT) {

				s = Messages.getI18n("game.commandline.adversary.type.normal.abbr");
			} else if (fenster.getMitspieler1() == PlayerEnum.SMART) {

				s = Messages.getI18n("game.commandline.adversary.type.smart.abbr");
			}
		}
		if (nummer == 2) {

			if (fenster.getMitspieler2() == PlayerEnum.GRANNY) {

				s = Messages.getI18n("game.commandline.adversary.type.granny.abbr");
			} else if (fenster.getMitspieler2() == PlayerEnum.RULECOMPLIANT) {

				s = Messages.getI18n("game.commandline.adversary.type.normal.abbr");
			} else if (fenster.getMitspieler2() == PlayerEnum.SMART) {

				s = Messages.getI18n("game.commandline.adversary.type.smart.abbr");
			}
		}
		return s;
	}

	@Override
	public String askForVariant() {
		String erg;
		int i = fenster.getSkatWahl();

		if (i == 1) {
			erg = Messages.getI18n("game.commandline.skat.type.international.abbr");
		} else if (i == 2) {
			erg = Messages.getI18n("game.commandline.skat.type.bock.abbr");
		} else
			erg = Messages.getI18n("game.commandline.skat.type.robber.abbr");

		return erg;

	}

	@Override
	public void wrongInputHint() {

		JFrame frame = new JFrame(Messages.getI18n("application.attention"));
		frame.add(new JLabel(Messages.getI18n("application.attention.wrong.input")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	@Override
	public boolean handgame() {
		hfenster.handspiel();
		warte();
		boolean wert = hfenster.getHandspiel();
		return wert;
	}

	@Override
	public int druecken(ArrayList<PlayingCard> blatt, int nummer) {

		hfenster.blattneu(tisch.getHumanPlayer());
		hfenster.setZug(true);
		warte();
		int kartennummer = hfenster.getGewaehlteKarte();
		hfenster.setZug(false);
		return kartennummer;
	}

	@Override
	public SuitGame suitGame() {
		SuitGame spiel = null;
		String farbe = hfenster.getFarbe();
		spiel = declareSuitGame(farbe);
		return spiel;
	}

	@Override
	public boolean ouvert() {
		hfenster.ouvert();
		warte();
		boolean wert = hfenster.getOuvert();
		return wert;
	}

	@Override
	public boolean schneider() {
		hfenster.schneider();
		warte();
		boolean wert = hfenster.getSchneider();
		return wert;
	}

	@Override
	public boolean schwarz() {
		hfenster.schwarz();
		warte();
		boolean wert = hfenster.getSchwarz();
		return wert;
	}

	@Override
	public IGameVariety declareSuit() {
		IGameVariety spiel = null;
		hfenster.ansagen();
		warte();
		String spielart = hfenster.getSpielart();
		if (spielart.compareTo("Grand") == 0) {
			spiel = new GrandGame();
		} else if (spielart.compareTo("Null") == 0) {
			spiel = new NullGame();
		} else {
// TODO why not play = new Farbspiel(spielart);
			spiel = new SuitGame(null);
		}

		return spiel;
	}

	@Override
	public PlayingCard playCard(PlayingCard[] gespielteKarten, IPlayer spieler)
			throws IOException {
		hfenster.setGespielteKarten(gespielteKarten);
		zeigegespielteKarten(gespielteKarten);
		outputHand(spieler);
		hfenster.setZug(true);
		warte();
		int kartennummer = hfenster.getGewaehlteKarte();
		PlayingCard karte = spieler.getHand().remove(kartennummer);
		hfenster.setZug(false);
		return karte;
	}

	private void zeigegespielteKarten(PlayingCard[] gespielteKarten) {
		hfenster.tischRaumen();
		if (gespielteKarten[0] != null) {
			hfenster.spieltKarte(gespielteKarten[0]);
			if (gespielteKarten[1] != null) {
				hfenster.spieltKarte(gespielteKarten[1]);
			}
		}

	}

	@Override
	public void trickWon(IPlayer spieler) {
		hfenster.stichGewonnen(spieler);

	}

	@Override
	public void outputHand(IPlayer spieler) throws IOException {
		hfenster.blattAusgeben(spieler);
		hfenster.gegnerKarten(tisch.getSpieler2(), tisch.getSpieler3());

	}

	@Override
	public void outputSkat(PlayingCard[] skat) throws IOException {
		hfenster.skatAusgeben(skat);

	}

	@Override
	public void showEvaluation(boolean isWon) {
		hfenster.auswertung(isWon);

	}

	@Override
	public void gameOver() {
		hfenster.skataufTisch();
		warte();
	}

	@Override
	public void pass(IPlayer spieler) {

	}

	@Override
	public void anotherCard() {
		hfenster.andereKarte();

	}

	@Override
	public void hinterhandVsWinner(IPlayer winner) {

	}

	@Override
	public void blankLine() {

	}

	@Override
	public void mittelhandVsVorhand() {

	}

	@Override
	public void gameBegins() {
		hfenster.spielBeginnt();

	}

	@Override
	public void passGame() {

	}

	@Override
	public void augen(int augen) {
		hfenster.setAugen(augen);
	}

	@Override
	public void points(int punkte) {

	}

	@Override
	public void showDeclarer() {
		hfenster.alleinspieler();

	}

	@Override
	public void trump() {
		hfenster.trumpf();

	}

	@Override
	public void newGame() {

	}

	@Override
	public String readInput() {
		return null;
	}

	@Override
	public boolean reizAgent() {

		hfenster.reizagent();
		warte();
		boolean erg = hfenster.getAgent();
		return erg;
	}

	@Override
	public int reizlimitFestlegen() {
		boolean fertig = false;
		hfenster.reizLimfest();
		warte();

		int wert = hfenster.getReizlim();
		while (!fertig) {

			SortedSet<Integer> reizwerte = tisch.getReizwerte();
			if (reizwerte.contains(wert) || wert == 0) {
				fertig = true;
			} else {
				hfenster.reizLimfest();
				warte();
				wert = hfenster.getReizlim();
			}
		}
		return wert;
	}

	@Override
	public void spieltKarte(IPlayer spieler, PlayingCard karte) {
		if (tisch.getHumanPlayer().getName() == spieler.getName()) {
			hfenster.menschSpieltKarte();
		} else {
			hfenster.spieltKarte(karte);
		}

	}

	@Override
	public boolean quitGame() {
		return false;
	}

	@Override
	public void outputPoints() {

	}

	/**
	 * Die GUIAusgabe soll solange warten, bis die Variable release wieder auf
	 * true gesetzt wird
	 */
	public void warte() {
		while (!getRelease()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRelease(false);
	}

	/**
	 * Fragt ab welche Farbe gespielt werden soll
	 * @param s Der String, der die Farbe beinhaltet
	 * @return Das gew&auml;hlte Farbspiel
	 */
	public SuitGame declareSuitGame(String s) {
		SuitGame suitGame = null;
		
		if (s.compareTo(Messages.getI18n("game.type.german.herz")) == 0) {
			suitGame = new SuitGame(Suit.HEARTS);
		} else if (s.compareTo(Messages.getI18n("game.type.diamonds")) == 0
				|| s.compareTo(Messages.getI18n("game.type.german.schellen")) == 0) {
			suitGame = new SuitGame(Suit.BELLS);
		} else if (s.compareTo(Messages.getI18n("game.type.spades")) == 0
				|| s.compareTo(Messages.getI18n("game.type.german.gruen")) == 0) {
			suitGame = new SuitGame(Suit.LEAVES);
		} else if (s.compareTo(Messages.getI18n("game.type.clubs")) == 0
				|| s.compareTo(Messages.getI18n("game.type.german.eichel")) == 0) {
			suitGame = new SuitGame(Suit.ACORNS);
		} else {
			System.out.println("Fehler in farbeansagen()");
		}
		
		return suitGame;
	}

	@Override
	public void deleteTable() {
		hfenster.tischRaumen();

	}

	@Override
	public String getDeckSelection() {
		String s = "";
		if (fenster.getDeutsch() == true) {
			s = Messages.getI18n("game.skat.deck.g");
		} else {
			s = Messages.getI18n("game.skat.deck.f");
		}
		return s;
	}

	@Override
	public String askForSixSkat() {
		String erg;
		boolean b = fenster.getSechserkat();

		if (b) {
			erg = Messages.getI18n("application.y");
		} else {
			erg = Messages.getI18n("application.n");
		}

		return erg;
	}

	@Override
	public void openGameTable() {
		hfenster = new GameTableFrame(this.tisch, this);
	}

	@Override
	public void showPosition() {
		hfenster.gegnerKarten(tisch.getSpieler2(), tisch.getSpieler3());

	}

	@Override
	public void cleanUpGUI() {
		hfenster.spielAufrauemen();
	}

	@Override
	public void statistics() {
		hfenster.statistik();
		hfenster.neuesSpiel();
		warte();

	}

}
