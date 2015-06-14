package skat09.spielkarte;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.commons.io.FileUtils;

import skat09.test.interfaces.ISpieler;

/**
 * Die Klasse Spielkarte erzeugt eine Spielkarte mit einer Farbe und einem Wert.
 * 
 * @author Ann-Christine Kycler, Sebastian Schlatow, Mathias Stoislow, Martin Bruhns
 * @version 03.07.2009
 * 
 */
public class Spielkarte implements Comparable<Spielkarte> {

	//
	// Datenfelder
	//

	/**
	 * Feld f&uuml;r die Farbe
	 */
	private Farbe farbe;
	/**
	 * Feld f&uuml;r den Wert
	 */
	private Wert wert;
	/**
	 * Feld f&uuml;r den Besitzer
	 */
	private ISpieler besitzer;
	/**
	 * Feld das angibt, ob deutsches oder franz&ouml;sisches Blatt benutzt wird
	 */
	private static boolean deutsch;

	//
	// Konstruktor
	//
	/**
	 * Konstruktor der Klasse Spielkarte. Setzt die Farbe und den Wert der Karte
	 * @param farbe Die Farbe der zu erzeugenden Karte
	 * @param wert Der Wert der zu erzeugenden Karte
	 */
	public Spielkarte(Farbe farbe, Wert wert) {
		this.wert = wert;
		this.farbe = farbe;
		//deutsch = false;
	}

	//
	// get Methoden
	//

	/**
	 * Gibt die Farbe einer Karte zur&uuml;ck.
	 * 
	 * @return Farbe der Karte
	 */
	public Farbe getFarbe() {
		return farbe;
	}
	
	/**
	 * Liefert den Inhalt der Variable deutsch.
	 * 
	 * @return true, falls die Karte in deutscher Sprache ist
	 */
	public static boolean getDeutsch() {
		return deutsch;
	}

	/**
	 * Gibt den Wert einer Karte zur&uuml;ck.
	 * 
	 * @return wert der Karte
	 */
	public Wert getWert() {
		return wert;
	}

	/**
	 * Gibt den Besitzer zur&uuml;
	 * @return der Besitzer der Karte
	 */
	public ISpieler getBesitzer() {
		return besitzer;
	}

	//
	// set-Methoden
	// 

	/**
	 * Setzt den Besitzer einer Karte neu.
	 * 
	 * @param besitzer
	 *            - der zuk&uuml;nfitge Besitzer der Karte
	 */
	public void setBesitzer(ISpieler besitzer) {
		this.besitzer = besitzer;
	}
	
	/**
	 * Setzt die Variable deutsch neu.
	 * 
	 * @param blatt - boolean Wert, der angibt, ob es sich um eine deutsche Karte 
	 * handelt.
	 */
	public static void setDeutsch(boolean blatt) {
		deutsch = blatt;
	}

	//
	// Other methods
	//

	/**
	 * Gibt die Eigenschaften einer Karte als String aus.
	 * @return Eigenschaften der Karte
	 */
	public String toString() {
		String string = "";
		if (deutsch == false) {
		string = farbe + " " + wert;
		} else {
			String deutfarbe = deutFarbe();
			String deutwert = deutWert();
			string = deutfarbe + " " + deutwert;
		}
		
		
		return string;
	}

	/**
	 * equals vergleich die Spielkarte mit einer &uuml;bergebenen Spielkarte und
	 * stellt fest, ob die Datenfelder der beiden Karten gleich sind.
	 * 
	 * @param karte
	 *            Karte mit der verglichen werden soll
	 * @return true, falls die beiden Karten gleich sind
	 */
	public boolean equals(Spielkarte karte) {

		boolean ergebnis;

		if (this.farbe.ordinal() == karte.getFarbe().ordinal()
				&& this.wert.ordinal() == karte.getWert().ordinal()) {

			ergebnis = true;
		}

		else {

			ergebnis = false;
		}

		return ergebnis;
	}
	
	/**
	 * Vergleicht zwei Spielkarten gem&auml;ß der compareTo Konvention.
	 * 
	 * Achtung, die Methode ist nur zur &Uuml;berpr&uuml;fung von 
	 * Gleichheit zweier Karten implementiert, sie kann (noch) nicht
	 * zur Sortierung verwendet werden!
	 * 
	 * @param karte - spielkarte, die mit dem aktuellen Objekt verglichen werden soll
	 * @return 0 falls gleich, ansonsten negativen/positiven Wert
	 */
	public int compareTo(Spielkarte karte) {
		
		int ergebnis = Integer.MAX_VALUE;
		
		if (wert == karte.getWert() && farbe == karte.getFarbe()) {
			
			ergebnis = 0;
		}
		return ergebnis;
	}
	
	/**
	 * Wandelt franz&ouml;sische Farbe in deutsche Farbe um.
	 * @return Die Methode gibt den deutschen Begriff f&uuml;r die Kartenfarbe wieder.
	 */
	public String deutFarbe() {
		String deutfarbe = "";
		if (farbe.equals(Farbe.PIK)) {
			deutfarbe = "GRUEN";
		}
		if (farbe.equals(Farbe.HERZ)) {
			deutfarbe = ""+farbe;
		}
		if (farbe.equals(Farbe.KREUZ)) {
			deutfarbe = "EICHEL";
		}
		if (farbe.equals(Farbe.KARO)) {
			deutfarbe = "SCHELLEN";
		}
		return deutfarbe;
	}
	
	/**
	 * Gibt den deutschen Wert einer Spielkarte zur&uuml;ck.
	 * @return Den deutschen Wert einer Spielkarte als String
	 */
	public String deutWert() {
		String deutwert ="";
		if (wert == Wert.BUBE) {
			deutwert = "UNTER";
		} else if (wert == Wert.DAME) {
			deutwert = "OBER";
		} else if (wert == Wert.ASS) {
			deutwert = "DAUS";
		} else {
			deutwert = "" + wert;
		}
		return deutwert;
	}
	
	/**
	 * Gibt den Dateipfad einer Karte zur&uuml;ck.
	 * 
	 * @return Den Dateipfad einer Karte
	 */
	public String dateiPfad() {
		String dateipfad = "";
		if (deutsch) {
			dateipfad += "deutkarten/";
		} else {
			dateipfad += "frankarten/";
//			dateipfad += "svg/";
		}
		String neuerString = this.toString().replace(' ', '_');
		dateipfad += neuerString;
		return dateipfad;
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
		return this.getClass().getClassLoader().getResource("img/" + dateiPfad() + ".png");
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
			svgFile = new File(this.getClass().getClassLoader().getResource("img/" + dateiPfad() + ".svg").toURI());
			try {
				return rasterize(svgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage rasterize(File svgFile) throws IOException {

	    final BufferedImage[] imagePointer = new BufferedImage[1];

	    // Rendering hints can't be set programatically, so
	    // we override defaults with a temporary stylesheet.
	    // These defaults emphasize quality and precision, and
	    // are more similar to the defaults of other SVG viewers.
	    // SVG documents can still override these defaults.
	    String css = "svg {" +
	            "shape-rendering: geometricPrecision;" +
	            "text-rendering:  geometricPrecision;" +
	            "color-rendering: optimizeQuality;" +
	            "image-rendering: optimizeQuality;" +
	            "}";
	    File cssFile = File.createTempFile("batik-default-override-", ".css");
	    FileUtils.writeStringToFile(cssFile, css);

	    TranscodingHints transcoderHints = new TranscodingHints();
	    transcoderHints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
	    transcoderHints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION,
	            SVG12DOMImplementation.getDOMImplementation());
	    transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI,
	            SVGConstants.SVG_NAMESPACE_URI);
	    transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
	    transcoderHints.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, cssFile.toURI().toString());

	    try {

	        TranscoderInput input = new TranscoderInput(new FileInputStream(svgFile));

	        ImageTranscoder t = new ImageTranscoder() {

	            @Override
	            public BufferedImage createImage(int w, int h) {
	                return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	            }

	            @Override
	            public void writeImage(BufferedImage image, TranscoderOutput out)
	                    throws TranscoderException {
	                imagePointer[0] = image;
	            }
	        };
	        t.setTranscodingHints(transcoderHints);
	        t.transcode(input, null);
	    }
	    catch (TranscoderException ex) {
	        ex.printStackTrace();
	        throw new IOException("Couldn't convert " + svgFile);
	    }
	    finally {
	        cssFile.delete();
	    }

	    return imagePointer[0];
	}
}
