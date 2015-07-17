package skat09.spieler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Spielartbezeichnung;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.ISpielart;
import skat09.test.interfaces.ISpieler;


/**
 * Diese Klasse Spieler simuliert einen Skatspieler mit all seinen
 * M&ouml;glichkeiten am Spiel teilzunehmen. Ein Spieler hat zum Beispiel ein
 * Blatt, eine Position am Spieltisch, kann reizen und Karten Spielen.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 */

abstract public class Player implements ISpieler {

	//
	// Datenfelder
	//

	/**
	 *  Name des Spielers
	 */
	protected String name;

	/**
	 *  Position des Spielers am Tisch
	 */
	protected Position position;

	/**
	 *  Handkarten des Spielers
	 */
	protected ArrayList<Spielkarte> blatt;

	/**
	 * enth&auml;lt vom Spieler gewonnene Stiche
	 */
	protected ArrayList<Spielkarte> stiche;

	/**
	 *  Flag = true >> Spieler ist Alleinspieler
	 */
	protected boolean istAlleinspieler;

	/**
	 *  Ein Spieler kennt seinen Mitspieler, wenn er einen hat.
	 */
	protected ISpieler mitspieler;

	/**
	 *  Liste der gespielten Spiele, enth&auml;lt Punkte
	 */
	protected ArrayList<Integer> spiele;

	/**
	 *  Spieler braucht die Spielart um die Karte zu pr&uuml;fen
	 */
	protected ISpielart spielart;

	/**
	 * H&auml;lt die Karten nach der Skataufnahme
	 */
	protected ArrayList<Spielkarte> nachSkat;
	
	/**
	 *  Damit der Spieler alle Karten kennt
	 */
	protected ArrayList<Spielkarte> deck;
	
	/**
	 * Merkt sich fuer den Alleinspieler, was im Skat lag.
	 */
	protected ArrayList<Spielkarte> skat;

	/**
	 *  Spieler kann sich die gespielten Karten merken
	 */
	protected ArrayList<Spielkarte> alleGespielteKarten;

	/**
	 * Das restliche Blatt enth&auml;hlt die nicht spielbaren Karten
	 */
	private ArrayList<Spielkarte> restblatt = new ArrayList<Spielkarte>();
	
	/**
	 *  Spieler kann seine Truempfe in diesem Array speichern, um sie zu zaehlen
	 */
	private Spielkarte[] truempfe = new Spielkarte[12];
	
	/**
	 * Anzahl der Handspiele eines Spielers
	 */
	private int handspiele = 0;

	/**
	 * Erzeugt ein neues Spielerobjekt.
	 * 
	 * @param name
	 *            - name des Spielers
	 * 
	 */
	public Player(String name) {

		spiele = new ArrayList<Integer>();
		this.name = name;
		stiche = new ArrayList<Spielkarte>();
		alleGespielteKarten = new ArrayList<Spielkarte>();
	}

	//
	// get-Methoden
	//

	//@Override
	public String getName() {

		return name;
	}

	//@Override
	public Position getPosition() {

		return position;
	}

	//@Override
	public ArrayList<Spielkarte> getBlatt() {

		return blatt;
	}

	//@Override
	public ArrayList<Spielkarte> getStiche() {

		return stiche;
	}

	//@Override
	public boolean getIstAlleinspieler() {

		return istAlleinspieler;
	}

	//@Override
	public ArrayList<Integer> getSpiele() {

		return spiele;
	}

	//@Override
	public ISpieler getMitspieler() {

		return mitspieler;
	}
	
	public ArrayList<Spielkarte> getSkat() {
		
		return skat;
	}
	//@Override
	public ArrayList<Spielkarte> getAllegespieltenkarten() {
		return alleGespielteKarten;
	}

	//@Override
	public ArrayList<Spielkarte> getRestblatt() {

		return restblatt;
	}

	//@Override
	public ISpielart getSpielart() {

		return this.spielart;
	}
	
	//@Override
	public int getHandspiele() {
		return handspiele;
	}

	//
	// set-Methoden
	//

	//@Override
	public void setHandspiele(int handspiele) {
		this.handspiele = handspiele;
	}



	//@Override
	public void setSpielart(ISpielart spielart) {

		this.spielart = spielart;
	}

	//@Override
	public void setPosition(Position position) {

		this.position = position;
	}

	//@Override
	public void setBlatt(ArrayList<Spielkarte> blatt) {

		this.blatt = blatt;
	}

	//@Override
	public void setIstAlleinspieler(boolean istAlleinspieler) {

		this.istAlleinspieler = istAlleinspieler;
	}

	//@Override
	public void setMitspieler(ISpieler mitspieler) {

		this.mitspieler = mitspieler;
	}
	
	//@Override
	public void setSpiele(ArrayList<Integer> spiele) {
		this.spiele = spiele;

	}

	//@Override
	public void setStiche(ArrayList<Spielkarte> stiche) {

		this.stiche = stiche;
	}

	//@Override
	public void setName(String name) {
		this.name = name;
	}
	
	//@Override
	public void setAlleGespieltenKarten(ArrayList<Spielkarte> karten) {
		this.alleGespielteKarten = karten;
	}
	
	//@Override
	public void setTruempfe(Spielkarte[] truempfe) {
		this.truempfe = truempfe;
	}
	
	//@Override
	public void setDeck(ArrayList<Spielkarte> deck) {
		
		this.deck = deck;
	}
	
	//@Override
	public void setSkat(ArrayList<Spielkarte> skat) {
		
		this.skat = skat;
	}

	//
	// weitere Methoden
	//

	//@Override
	public void stichHinzufuegen(Spielkarte[] stich) {

		for (int i = 0; i < stich.length; i++) {

			stiche.add(stich[i]);
		}
	}

	/**
	 * F&uuml;gt einen gewonnenen Stich zu den bisher gespielten Stichen hinzu.
	 * @param gespielteKarten
	 * 
	 */
	//@Override
	public void gespielteKartenHinzufuegen(Spielkarte[] gespielteKarten) {

		for (int i = 0; i < gespielteKarten.length; i++) {

			alleGespielteKarten.add(gespielteKarten[i]);
		}
	}

	//@Override
	public ArrayList<Spielkarte> spielbareKarten(Spielkarte[] gespielteKarten) {

		ArrayList<Spielkarte> spielbareKarten = new ArrayList<Spielkarte>();

		for (int i = 0; i < blatt.size(); i++) {

			if (spielart.gespielteKartePruefen(blatt, gespielteKarten, blatt.get(i))) {

				spielbareKarten.add(blatt.get(i));
			}
			
			else {
				
				restblatt.add(blatt.get(i));
			}
		}
		
		
//		if (spielbareKarten.size() == 0) {
//			
//			spielbareKarten.addAll(blatt);
//		}
		
		return spielbareKarten;
	}

	//@Override
	abstract public Spielkarte spieleKarte(Spielkarte[] gespielteKarten)
			throws IOException;
	
	//@Override
	public Spielkarte zufaelligErlaubteKarteSpielen(Spielkarte[] gespielteKarten) {

		Spielkarte ergebnis = null;
		Random zufall = new Random();
		int zahl = zufall.nextInt(blatt.size());

		while (!spielart.gespielteKartePruefen(blatt, gespielteKarten, blatt
				.get(zahl))) {

			zahl = zufall.nextInt(blatt.size());
		}
		ergebnis = blatt.remove(zahl);

		return ergebnis;
	}

	//@Override
	abstract public Spielkarte[] druecken(Spielkarte[] skat);

	//
	// Methoden zur Spielansage
	//

	//@Override
	abstract public ISpielart spielAnsagen();

	//@Override
	abstract public boolean handspiel();

	//@Override
	abstract public boolean ouvert();

	//@Override
	abstract public boolean schneider();

	//@Override
	abstract public boolean schwarz();

	//@Override
	abstract public Farbspiel farbe();

	//@Override
	public ArrayList<Integer> neuerEintrag(int punkte) {

		spiele.add(punkte);
		return spiele;
	}

	//@Override
	abstract public boolean hoeren(int reizwert);

	//@Override
	abstract public boolean sagen(int alterWert);

	//@Override
	public void blattSortieren(final ISpielart spielart) {

		Comparator<Spielkarte> comp = new Comparator<Spielkarte>() {

			public int compare(Spielkarte karte1, Spielkarte karte2) {

				int ergebnis;

				if (karte1.equals(spielart.sortiereKarte(karte1, karte2))) {

					ergebnis = 1;
				}

				else if (karte1.equals(karte2)) {

					ergebnis = 0;
				}

				else {

					ergebnis = -1;
				}

				return ergebnis;
			}
		};
		
		Collections.sort(blatt, comp);
	}

	//@Override
	public abstract boolean agent();

	//@Override
	public boolean equals(ISpieler spieler) {

		boolean erg = false;

		if (spieler.getName().equals(this.getName())) {

			erg = true;
		}

		return erg;
	}

	//@Override
	public abstract int reizlimitFestlegen();
	
	//@Override
	public Spielkarte[] spitzenEinordnen() {

		truempfe = new Spielkarte[12];

		if (spielart.getSpielart() == Spielartbezeichnung.GRAND) {

			bubeneinordnen();
		}
		
		if (spielart.getSpielart() == Spielartbezeichnung.FARBE) {

			bubeneinordnen();
			farbeneinordnen();
		}
		
		return this.truempfe;
	}
	
	//@Override
	public void bubeneinordnen() {
		
		int kartenwert = 0;
		ArrayList<Spielkarte> blatt = getBlatt();

		for (int i = 0; i < blatt.size(); i++) {
			
			if (blatt.get(i).getWert() == Wert.BUBE) {

				kartenwert = spielart.karteBewerten(blatt.get(i));
				truempfe[bubeneinordnenhilf(kartenwert)] = blatt.get(i);
			}
		}
	}

	//@Override
	public int bubeneinordnenhilf(int bubenwert) {
		
		int erg = -1;
		
		if (bubenwert == 14) {
			
			erg = 3;
		}
		
		if (bubenwert == 15) {
			
			erg = 2;
		}
		
		if (bubenwert == 16) {
			
			erg = 1;
		}
		
		if (bubenwert == 17) {
			
			erg = 0;
		}
		
		return erg;
	}

	//@Override
	public void farbeneinordnen() {
		
		int kartenwert = 0;
		Farbspiel spiel = (Farbspiel) spielart;
		ArrayList<Spielkarte> blatt = getBlatt();

		for (int i = 0; i < blatt.size(); i++) {

			if (blatt.get(i).getWert() != Wert.BUBE && blatt.get(i).getFarbe() == spiel.getTrumpffarbe()) {

				kartenwert = spielart.karteBewerten(blatt.get(i));
				truempfe[farbeeinordnenhilf(kartenwert)] = blatt.get(i);
			}
		}
	}

	//@Override
	public int farbeeinordnenhilf(int i) {
		
		int erg = -1;
		
		if (i == 6) {
			
			erg = 11;
		}
		
		if (i == 7) {
			
			erg = 10;
		}
		
		if (i == 8) {
			
			erg = 9;
		}
		
		if (i == 9) {
			
			erg = 8;
		}
		
		if (i == 10) {
			
			erg = 7;
		}
		
		if (i == 11) {
			
			erg = 6;
		}
		
		if (i == 12) {
			
			erg = 5;
		}
		
		if (i == 13) {
			
			erg = 4;
		}
		
		return erg;
	}
	
	//@Override
	public int spitzenZahl() {
		
		int erg = 0;

		if (truempfe[0] != null) {
			
			erg = spitzenMit(erg);
		}
		
		else {
			
			erg = spitzenOhne(erg);
		}

		return erg;
	}
	
	//@Override
	public int spitzenMit(int erg) {

		for (int i = 0; i < truempfe.length; i++) {

			if (truempfe[i] == null) {

				erg = i;
				break;
			}
		}
		return erg;
	}
	
	//@Override
	public int spitzenOhne(int erg) {

		for (int i = 0; i < truempfe.length; i++) {
			
			if (truempfe[i] != null) {
				
				erg = -i;
				break;
			}
		}
		
		if (erg == 0) {

			if (spielart instanceof Grandspiel) {

				erg = -4;
			}
			
			if (spielart instanceof Farbspiel) {

				erg = -11;
			}
		}
		
		return erg;
	}
}