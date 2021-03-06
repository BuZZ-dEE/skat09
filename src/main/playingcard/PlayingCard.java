package main.playingcard;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import main.player.IPlayer;
import main.ui.gui.SvgImageProcessing;

/**
 * Die Klasse Spielkarte erzeugt eine Spielkarte with einer Farbe und einem Wert.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class PlayingCard implements Comparable<PlayingCard> {

	/**
	 * Feld f&uuml;r die Farbe
	 */
	private final Suit suit;
	/**
	 * Feld f&uuml;r den Wert
	 */
	private final Rank rank;
	/**
	 * Feld f&uuml;r den Besitzer
	 */
	private IPlayer owner;
	/**
	 * Feld das angibt, ob deutsches oder franz&ouml;sisches Blatt benutzt wird
	 */
	private static boolean germanDeck;

    public enum Suit {

        /**
         *  Bells / Diamonds / Karo
         */
        BELLS(9),
        /**
         * Hearts / Herz
         */
        HEARTS(10),
        /**
         * Spades / Leaves / Pik
         */
        LEAVES(11),
        /**
         * Acorns / Clubs / Kreuz
         */
        ACORNS(12);

        /**
         * Der Wert, den ein Element aus der Enum haben kann
         */
        private int suitValue;

        /**
         * Der Konstruktor der Enum Farbe
         * @param suitValue Der Ordnungswert, den eine Farbe haben soll
         */
        Suit(int suitValue) {

            this.suitValue = suitValue;
        }

        /**
         * Gibt den Ordnungswert eines Elements zur&uuml;ck
         * @return Den Wert eines Elements
         */
        public int value() {

            return suitValue;
        }
    }

    public enum Rank {

        /**
         * Halter des Farbwertes 6
         */
        SIX(6), // TODO why 6, six-skat has other rules?
        /**
         * Halter des Farbwertes 7
         */
        SEVEN(0),
        /**
         * Halter des Farbwertes 8
         */
        EIGHT(0),
        /**
         * Halter des Farbwertes 9
         */
        NINE(0),
        /**
         * Halter des Farbwertes 10
         */
        TEN(10),
        /**
         * Halter des Farbwertes Bube
         * Under knave (Unter) == Jack (Junge / Bube)
         */
        UNDER_KNAVE(2),
        /**
         * Halter des Farbwertes Dame
         * Over knave (Ober) == Queen (Dame)
         */
        OVER_KNAVE(3),
        /**
         * Halter des Farbwertes König
         * King == King (König)
         */
        KING(4),
        /**
         * Halter des Farbwertes Ass
         * Daus (Daus) == Ace (Ass)
         */
        DAUS(11);

        /**
         * Der Wert, den ein Element aus der Enum haben kann
         */
        private int rankValue;

        /**
         * Der Konstruktor der Enum Rank
         * @param rankValue Der Ordnungswert, den ein Rank haben soll
         */
		Rank(int rankValue) {
            this.rankValue = rankValue;
        }

        /**
         * Gibt den Ordnungswert eines Elements zur&uuml;ck
         * @return Den Wert eines Elements
         */
        public int value() {

            return rankValue;
        }
    }
	
	/**
	 * Konstruktor der Klasse Spielkarte. Setzt die Farbe und den Wert der Karte
	 * @param suit Die Farbe der zu erzeugenden Karte
	 * @param rank Der Wert der zu erzeugenden Karte
	 */
	public PlayingCard(Suit suit, Rank rank) {
		this.rank = rank;
		this.suit = suit;
		//deutsch = false;
	}

	/**
	 * Gibt die Farbe einer Karte zur&uuml;ck.
	 * 
	 * @return Farbe der Karte
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Liefert den Inhalt der Variable deutsch.
	 * 
	 * @return true, falls die Karte in deutscher Sprache ist
	 */
	public static boolean isGermanDeck() {
		return germanDeck;
	}

	/**
	 * Gibt den Wert einer Karte zur&uuml;ck.
	 * 
	 * @return wert der Karte
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Gibt den Besitzer zur&uuml;
	 * @return der Besitzer der Karte
	 */
	public IPlayer getOwner() {
		return owner;
	}

	/**
	 * Setzt den Besitzer einer Karte neu.
	 * 
	 * @param owner
	 *            - der zuk&uuml;nfitge Besitzer der Karte
	 */
	public void setOwner(IPlayer owner) {
		this.owner = owner;
	}
	
	/**
	 * Setzt die Variable deutsch neu.
	 * 
	 * @param deck - boolean Wert, der angibt, ob es sich um eine deutsche Karte 
	 * handelt.
	 */
	public static void setGermanDeck(boolean deck) {
		germanDeck = deck;
	}

	/**
	 * Get card name.
	 * 
	 * @return the card name as string
	 * 
	 * @version 20.07.2015 22:42:38
	 */
	public String toString() {
		String string = "";
		if (germanDeck == true) {
			string = suit + " " + rank;
		} else {
			String frenchSuit = frenchSuit();
			String frenchValue = frenchRank();
			string = frenchSuit + " " + frenchValue;
		}
		
		
		return string;
	}

	/**
	 * equals vergleich die Spielkarte with einer &uuml;bergebenen Spielkarte und
	 * stellt fest, ob die Datenfelder der beiden Karten gleich sind.
	 * 
	 * @param card
	 *            Karte with der verglichen werden soll
	 * @return true, falls die beiden Karten gleich sind
	 */
	public boolean equals(PlayingCard card) {

		boolean result;

		result = this.suit.ordinal() == card.getSuit().ordinal()
				&& this.rank.ordinal() == card.getRank().ordinal();

		return result;
	}
	
	/**
	 * Vergleicht zwei Spielkarten gem&auml;ß der compareTo Konvention.
	 * 
	 * Achtung, die Methode ist nur zur &Uuml;berpr&uuml;fung von 
	 * Gleichheit zweier Karten implementiert, sie kann (noch) nicht
	 * zur Sortierung verwendet werden!
	 * 
	 * @param card - spielkarte, die with dem aktuellen Objekt verglichen werden soll
	 * @return 0 falls gleich, ansonsten negativen/positiven Wert
	 */
	public int compareTo(PlayingCard card) {
		
		int result = Integer.MAX_VALUE;
		
		if (rank == card.getRank() && suit == card.getSuit()) {
			
			result = 0;
		}
		return result;
	}
	
	/**
	 * Change german suit to french pendant.
	 * 
	 * @return the french name for current suit.
	 * 
	 * @version 20.07.2015 22:20:23
	 */
	public String frenchSuit() {
		String frenchSuit = "";
		if (suit.equals(Suit.LEAVES)) {
			frenchSuit = "SPADES";
		}
		if (suit.equals(Suit.HEARTS)) {
			frenchSuit = ""+suit;
		}
		if (suit.equals(Suit.ACORNS)) {
			frenchSuit = "CLUBS";
		}
		if (suit.equals(Suit.BELLS)) {
			frenchSuit = "DIAMONDS";
		}
		return frenchSuit;
	}
	
	/**
	 * Change german value to french pendant.
	 * 
	 * @return the french suit value for current german.
	 * 
	 * @version 20.07.2015 22:21:36
	 */
	public String frenchRank() {
		String frenchRank ="";
		if (rank == Rank.UNDER_KNAVE) {
			frenchRank = "JACK";
		} else if (rank == Rank.OVER_KNAVE) {
			frenchRank = "QUEEN";
		} else if (rank == Rank.DAUS) {
			frenchRank = "ACE";
		} else {
			frenchRank = "" + rank;
		}
		return frenchRank;
	}
	
	/**
	 * Gibt den Dateipfad einer Karte zur&uuml;ck.
	 * 
	 * @return Den Dateipfad einer Karte
	 */
	public String filePath() {
		String filePath = "";
		if (germanDeck) {
			filePath += "germancards/";
		} else {
			filePath += "frenchcards/";
//			dateipfad += "svg/";
		}
		String cardFilename = this.toString().replace(' ', '_');
		filePath += cardFilename;
		return filePath;
	}
	
	/**
	 * Get the complete path URL to the given card.
	 * 
	 * @return the path for the card
	 * 
	 * @since 13.06.2015 21:45:49
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public URL getCardPath() {
		return this.getClass().getClassLoader().getResource("img/" + filePath() + ".png");
	}
	
	/**
	 * Get the image for the given SVG card.
	 * 
	 * @return the image for the card
	 * 
	 * @since 14.06.2015 22:45:57
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public Image getCardImage() {
		File svgFile;
		try {
			svgFile = new File(this.getClass().getClassLoader().getResource("img/" + filePath() + ".svg").toURI());
			try {
				return SvgImageProcessing.rasterize(svgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
