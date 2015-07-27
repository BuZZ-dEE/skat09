package skat09.player;



import skat09.gamevariety.NullGame;
import skat09.gamevariety.SuitGame;
import skat09.playingcard.PlayingCard;
import skat09.test.interfaces.IGameVariety;

/**
 * Die Klasse regelkonformer Spieler erzeugt einen Computerspieler, der sich an
 * die Regeln h&auml;lt, dessen Aktionen jedoch vom Spielestand unabh&auml;ngig
 * sind und somit nicht immer Sinn machen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class RuleCompliantPlayer extends Player {

	//
	// Datenfelder
	//

	//
	// Konstruktor
	//
	
	/**
	 * Der Konstruktor der Klasse RegelkonformerSpieler
	 * @param name Der Name des Spielers
	 */
	public RuleCompliantPlayer(String name) {

		super(name);
	}

	//
	// get-Methoden
	//

	//
	// set-Methoden
	//

	//
	// weitere Methoden
	//

	@Override
	public PlayingCard playCard(PlayingCard[] gespielteKarten) {
		
		PlayingCard ergebnis = null;
	
		ergebnis = playRamdonAllowedCard(gespielteKarten);
		
		return ergebnis;
	}

	/**
	 * Der regelkonforme Spieler ist nie Alleinspieler und dr&uuml;ckt deshalb auch
	 * nie.
	 */
	public PlayingCard[] druecken(PlayingCard[] skat) {

		return null;
	}

	/**
	 * Der regelkonforme Spieler ist nie Alleinspieler und kann deshalb nie
	 * handspiel ansagen.
	 */
	public boolean handgame() {

		return true;
	}

	/**
	 * Der regelkonforme Spieler ist nie Alleinspieler und kann deshalb nicht
	 * ouvert ansagen.
	 */
	public boolean ouvert() {

		return false;
	}

	/**
	 * Der regelkonforme Spieler ist nie Alleinspieler und kann deshalb nicht
	 * schneider ansagen.
	 */
	public boolean schneider() {

		return false;
	}

	/**
	 * Der regelkonforme Spieler ist nie Alleinspieler und kann deshalb nicht
	 * schwarz ansagen.
	 */
	public boolean schwarz() {

		return false;
	}

	/**
	 * Der regelkonforme Spieler ist immer weg und sagt deshalb auch kein Spiel
	 * an.
	 */
	public IGameVariety declareGame() {

		return new NullGame();
	}

	/**
	 * Der regelkonforme Spieler ist beim h&ouml;ren immer bei 22 weg.
	 * 
	 * @param reizwert
	 *            der aktuelle Reizwert
	 */
	public boolean hoeren(int reizwert) {

		boolean ergebnis = false;
		
		if (reizwert <= 22) {

			ergebnis = true;
		}
		
		return ergebnis;
	}

	/**
	 * L&auml;sst den regelkonformen Spieler. Der Spieler ist zurzeit bei 22 weg.
	 * 
	 * @return reizwert des regelkonformen Spielers
	 */
	public boolean sagen(int reizwert) {

		boolean ergebnis = false;

		if (reizwert <= 22) {

			ergebnis = true;
		}
		
		return ergebnis;
	}

	@Override
	public SuitGame suit() {
		
		return null;
	}

	@Override
	public boolean agent() {
		
		return false;
	}

	@Override
	public int setBidLimit() {
		
		return 0;
	}
}
