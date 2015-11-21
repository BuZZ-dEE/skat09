package main;

import java.io.IOException;
import java.util.Observable;

import main.gamevariety.IGameVariety;
import main.player.IPlayer;
import main.playingcard.PlayingCard;
import main.ui.IOutput;


/**
 * Das Interface IController beinhaltet die Methodenr&uuml;mpfe f&uuml;r 
 * den Controller
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 *
 */
public interface IController {

	/**
	 * Liefert das Ausgabeobjekt zur&uuml;ck.
	 * 
	 * @return das Ausgabeobjekt
	 */
	IOutput getOutput();

	/**
	 * gibt das Spiel wieder frei, das vorher f&uuml;r eine GUI-Eingabe
	 * angehalten wurde
	 */
	void release();

	/**
	 * Leitet das gesamte Spiel.
	 * 
	 * @throws IOException
	 */
	void play() throws IOException;

	/**
	 * Leitet die Anmeldung
	 * 
	 */
	void logIn();

	/**
	 * Menschlicher Spieler soll seine Gegner w&auml;hlen.
	 */
	void selectAdversary();
	/**
	 * Die vom menschlichen Spieler gew&auml;hlte Skatart wird gesetzt
	 */
	void chooseSkatVariant();

	/**
	 * Die vom menschlichen Spieler gew&auml;hlte Blattart wird gesetzt
	 */
	void selectSkatDeck();

	/**
	 * Leitet das Reizen und ruft die Untermethode reizen auf, um den
	 * Alleinspieler zu ermitteln. Im Anschluss wird das Alleinspielerflag beim
	 * Alleinspieler gesetzt und der Reizwert im Tisch gesetzt um sp&auml;ter
	 * die Auswertung vornehmen zu k&ouml;nnen.
	 * 
	 * @throws IOException
	 */
	void coordinateBidding() throws IOException;

	/**
	 * Organisiert das Reizen zwischen zwei Spielern und liefert den Gewinner.
	 * Falls beide Spieler nicht spielen wollen, kann null zur&uuml;ck geliefert
	 * werden!
	 * 
	 * @param spieler1
	 *            - Spieler der h&ouml;ren muss.
	 * @param spieler2
	 *            - Spieler der sagen muss.
	 * @return Spieler, der gewonnen hat oder null.
	 */
	IPlayer bidding2(IPlayer spieler1, IPlayer spieler2);

	/**
	 * L&auml;sst den Reizagenten das Reizen durchf&uuml;hren oder den Spieler.
	 * 
	 * @param spieler
	 * @param reizwert
	 * @return true, wenn spieler mitgeht
	 */
	boolean bidOrBiddingAgent(IPlayer spieler, int reizwert,
							  boolean sagen);

	/**
	 * Organisiert das Reizen zwischen zwei Spielern und liefert den Gewinner.
	 * Es muss einen Gewinner geben!
	 * 
	 * @param spieler1
	 *            - Spieler der h&ouml;ren muss.
	 * @param spieler2
	 *            - Spieler der sagen muss.
	 * @return Spieler, der gewonnen hat.
	 */
	IPlayer bidding1(IPlayer spieler1, IPlayer spieler2);

	/**
	 * Setzt die Vorhand als Alleinspieler, falls die Variante R&auml;uberskat
	 * gew&auml;hlt wurde.
	 */
	void decideRaeuberGame();

	/**
	 * Leitet das Spiel, nachdem das Reizen abgeschlossen ist.
	 * @throws IOException 
	 */
	void leadGame() throws NullPointerException, IOException;

	/**
	 * Leitet alle Aktionen, die der Alleinspieler durchf&uuml;ren muss, bevor
	 * das Spiel beginnen kann. Dazu geh&ouml;ren Ansage des Handspiels,
	 * eventuelles dr&uuml;cken, sowie Ansage von schneider, schwarz und ouvert.
	 * 
	 * @throws IOException
	 */
	void declarerActions() throws IOException;

	/**
	 * Diese Methode bereitet alles f$uuml;r ein Spiel vor.
	 */
	void prepareGame();

	/**
	 * Diese Methode wertet das Spiel aus.
	 */
	void evaluation();

	/**
	 * Die Methode r&auml;umt den Tisch auf. Die Karten der Spieler, sowie der
	 * Skat und die eingezogenen Stiche der Spieler werden gel&ouml;scht.
	 */
	void cleanUp();

	/**
	 * Diese Methode gibt auf der Konsole aus, welcher Spieler welche Karte
	 * gespielt hat und wer den gesamten Stich gewonnen hat.
	 * 
	 * @param gespielteKarten
	 *            - die drei auf den Tisch gespielten Karten
	 * @param gewinner
	 *            - vorher ermittelter Gewinner des Stichs
	 */
	void outputTrickEvaluation(PlayingCard[] gespielteKarten,
							   IPlayer gewinner);

	/**
	 * Falls der Reizagent aktiviert wurde und der Spieler menschlich ist lasse
	 * den Reizagenten h&oumlren/sagen, ansonsten macht die Methode nichts.
	 * 
	 * @param spieler
	 *            , der h&ouml;ren oder sagen m&uuml;sste
	 *            
	 * @return false, wenn Spieler weg, true, wenn Spieler mitgeht, true,
	 * falls der reizagent sagt, false, wenn er h&ouml;rt
	 */
	boolean bidAgent(IPlayer spieler);

	/**
	 * gibt zur&uuml;ck, ob das Spiel beendet wird
	 * @return boolean
	 */
	boolean quitGame();

	/**
	 * Gibt dem im Skat liegenden Karten den Alleinspieler als Besitzer.
	 */
	void assignSkatCardsToOwner();

	/**
	 * Setzt die Flags schneider, schwarz und ouvert im Tisch.
	 * 
	 * @param alleinspieler
	 *            - der Alleinspieler der jeweiligen Runde
	 * @param spielart
	 *            - vom Alleinspieler angesagte Spielart
	 */
	void setFlags(IPlayer alleinspieler, IGameVariety spielart);

	/**
	 * Falls ein Spieler eine Karte spielt, wird dies &uuml;ber die Ausgabe
	 * bekannt gegeben.
	 */
	void update(Observable tisch, Object gespielteKarten);

	/**
	 * h&auml;lt das Spiel an, damit der Spieler in der GUI Eingaben
	 * setzen kann
	 */
	void waiting();

	/**
	 * Wird ausgef&uuml;hrt, falls eingepasst wurde.
	 * 
	 * @throws IOException
	 */
	void passGame() throws IOException;

	/**
	 * Spielvorbereitung im Fall, dass geramscht wird.
	 * @throws IOException 
	 * @throws NullPointerException 
	 */
	void ramschen() throws NullPointerException, IOException;

	/**
	 * Spielverlauf bei normalem Spiel (nicht Ramsch)
	 * 
	 * @throws IOException
	 */
	void normalGamePlay() throws IOException;

	/**
	 * Spielablauf Rauberskat
	 * 
	 * @throws IOException
	 */
	void playRaeuberskat() throws IOException;

	/**
	 * Spielablauf mit Ramsch und Bock
	 * 
	 * @throws IOException
	 */
	void playRamschBock() throws IOException;

	/**
	 * Spielablauf nach internationaler Skatordnung
	 * 
	 * @throws IOException
	 */
	void playIntSkat() throws IOException;

	/**
	 * Falls ein intelligenter Computerspieler mitspielt, m&uuml;ss dieser
	 * die Zahl seiner Spitzen kennen. Diese Methode berechnet die Spitzen
	 * des Computerspielers und teil sie dem Computerspieler mit.
	 */
	void initializeSmartPlayer();
	
	/**
	 * Da die Spieler anhand ihrer Namen verglichen werden, darf kein Spieler
	 * wie ein anderer heißen.
	 */
	void namesComparison();

}