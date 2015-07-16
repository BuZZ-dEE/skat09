package skat09.test.interfaces;

import java.io.IOException;
import java.util.Observable;

import skat09.spielkarte.Spielkarte;


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
	public abstract IOutput getAusgabe();

	/**
	 * gibt das Spiel wieder frei, das vorher f&uuml;r eine GUI-Eingabe
	 * angehalten wurde
	 */
	public abstract void release();

	/**
	 * Leitet das gesamte Spiel.
	 * 
	 * @throws IOException
	 */
	public abstract void spiel() throws IOException;

	/**
	 * Leitet die Anmeldung
	 * 
	 */
	public abstract void anmelden();

	/**
	 * Menschlicher Spieler soll seine Gegner w&auml;hlen.
	 */
	public abstract void waehleGegner();
	/**
	 * Die vom menschlichen Spieler gew&auml;hlte Skatart wird gesetzt
	 */
	public abstract void waehleSkatart();

	/**
	 * Die vom menschlichen Spieler gew&auml;hlte Blattart wird gesetzt
	 */
	public abstract void waehleSkatblatt();

	/**
	 * Leitet das Reizen und ruft die Untermethode reizen auf, um den
	 * Alleinspieler zu ermitteln. Im Anschluss wird das Alleinspielerflag beim
	 * Alleinspieler gesetzt und der Reizwert im Tisch gesetzt um sp&auml;ter
	 * die Auswertung vornehmen zu k&ouml;nnen.
	 * 
	 * @throws IOException
	 */
	public abstract void leiteReizen() throws IOException;

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
	public abstract ISpieler reizen2(ISpieler spieler1, ISpieler spieler2);

	/**
	 * L&auml;sst den Reizagenten das Reizen durchf&uuml;hren oder den Spieler.
	 * 
	 * @param spieler
	 * @param reizwert
	 * @return true, wenn spieler mitgeht
	 */
	public abstract boolean reizenOderReizagent(ISpieler spieler, int reizwert, 
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
	public abstract ISpieler reizen1(ISpieler spieler1, ISpieler spieler2);

	/**
	 * Setzt die Vorhand als Alleinspieler, falls die Variante R&auml;uberskat
	 * gew&auml;hlt wurde.
	 */
	public abstract void entscheideraeuberspiel();

	/**
	 * Leitet das Spiel, nachdem das Reizen abgeschlossen ist.
	 * @throws IOException 
	 */
	public abstract void leiteSpiel() throws NullPointerException, IOException;

	/**
	 * Leitet alle Aktionen, die der Alleinspieler durchf&uuml;ren muss, bevor
	 * das Spiel beginnen kann. Dazu geh&ouml;ren Ansage des Handspiels,
	 * eventuelles dr&uuml;cken, sowie Ansage von schneider, schwarz und ouvert.
	 * 
	 * @throws IOException
	 */
	public abstract void alleinspielerAktionen() throws IOException;

	/**
	 * Diese Methode bereitet alles f$uuml;r ein Spiel vor.
	 */
	public abstract void bereiteSpielvor();

	/**
	 * Diese Methode wertet das Spiel aus.
	 */
	public abstract void auswertung();

	/**
	 * Die Methode r&auml;umt den Tisch auf. Die Karten der Spieler, sowie der
	 * Skat und die eingezogenen Stiche der Spieler werden gel&ouml;scht.
	 */
	public abstract void aufrauemen();

	/**
	 * Diese Methode gibt auf der Konsole aus, welcher Spieler welche Karte
	 * gespielt hat und wer den gesamten Stich gewonnen hat.
	 * 
	 * @param gespielteKarten
	 *            - die drei auf den Tisch gespielten Karten
	 * @param gewinner
	 *            - vorher ermittelter Gewinner des Stichs
	 */
	public abstract void stichAuswertung(Spielkarte[] gespielteKarten,
			ISpieler gewinner);

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
	public abstract boolean reizagent(ISpieler spieler);

	/**
	 * gibt zur&uuml;ck, ob das Spiel beendet wird
	 * @return boolean
	 */
	public abstract boolean spielBeenden();

	/**
	 * Gibt dem im Skat liegenden Karten den Alleinspieler als Besitzer.
	 */
	public abstract void skatkartenBesitzergeben();

	/**
	 * Setzt die Flags schneider, schwarz und ouvert im Tisch.
	 * 
	 * @param alleinspieler
	 *            - der Alleinspieler der jeweiligen Runde
	 * @param spielart
	 *            - vom Alleinspieler angesagte Spielart
	 */
	public abstract void flagsSetzen(ISpieler alleinspieler, ISpielart spielart);

	/**
	 * Falls ein Spieler eine Karte spielt, wird dies &uuml;ber die Ausgabe
	 * bekannt gegeben.
	 */
	public abstract void update(Observable tisch, Object gespielteKarten);

	/**
	 * h&auml;lt das Spiel an, damit der Spieler in der GUI Eingaben
	 * setzen kann
	 */
	public abstract void warte();

	/**
	 * Wird ausgef&uuml;hrt, falls eingepasst wurde.
	 * 
	 * @throws IOException
	 */
	public abstract void einpassen() throws IOException;

	/**
	 * Spielvorbereitung im Fall, dass geramscht wird.
	 * @throws IOException 
	 * @throws NullPointerException 
	 */
	public abstract void ramschen() throws NullPointerException, IOException;

	/**
	 * Spielverlauf bei normalem Spiel (nicht Ramsch)
	 * 
	 * @throws IOException
	 */
	public abstract void normalerSpielverlauf() throws IOException;

	/**
	 * Spielablauf Rauberskat
	 * 
	 * @throws IOException
	 */
	public abstract void spielRaeuberskat() throws IOException;

	/**
	 * Spielablauf mit Ramsch und Bock
	 * 
	 * @throws IOException
	 */
	public abstract void spielRamschBock() throws IOException;

	/**
	 * Spielablauf nach internationaler Skatordnung
	 * 
	 * @throws IOException
	 */
	public abstract void spielIntSkat() throws IOException;

	/**
	 * Falls ein intelligenter Computerspieler mitspielt, m&uuml;ss dieser
	 * die Zahl seiner Spitzen kennen. Diese Methode berechnet die Spitzen
	 * des Computerspielers und teil sie dem Computerspieler mit.
	 */
	public abstract void schlauerSpielerInit();
	
	/**
	 * Da die Spieler anhand ihrer Namen verglichen werden, darf kein Spieler
	 * wie ein anderer heißen.
	 */
	public void namenVergleich();

}