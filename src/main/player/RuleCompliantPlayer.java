package main.player;



import main.gamevariety.IGameVariety;
import main.gamevariety.NullGame;
import main.gamevariety.SuitGame;
import main.playingcard.PlayingCard;

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
	
	/**
	 * Der Konstruktor der Klasse RegelkonformerSpieler
	 * @param name Der Name des Spielers
	 */
	public RuleCompliantPlayer(String name) {

		super(name);
	}

	@Override
	public PlayingCard playCard(PlayingCard[] playedCards) {
		
		PlayingCard result = null;
	
		result = playRamdonAllowedCard(playedCards);
		
		return result;
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
	 * handgame ansagen.
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
	 * Der regelkonforme Spieler ist immer pass und sagt deshalb auch kein Spiel
	 * an.
	 */
	public IGameVariety declareGame() {

		return new NullGame();
	}

	/**
	 * Der regelkonforme Spieler ist beim h&ouml;ren immer bei 22 pass.
	 * 
	 * @param biddingValue
	 *            der aktuelle Reizwert
	 */
	public boolean respond(int biddingValue) {

		boolean result = false;
		
		if (biddingValue <= 22) {

			result = true;
		}
		
		return result;
	}

	/**
	 * L&auml;sst den regelkonformen Spieler. Der Spieler ist zurzeit bei 22 pass.
	 * 
	 * @return reizwert des regelkonformen Spielers
	 */
	public boolean bid(int biddingValue) {

		boolean result = false;

		if (biddingValue <= 22) {

			result = true;
		}
		
		return result;
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
