package skat09.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import skat09.Table;
import skat09.spielart.SuitGame;
import skat09.spielart.Grandspiel;
import skat09.spielart.Spielart;
import skat09.spieler.HumanPlayer;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.IController;
import skat09.test.interfaces.ISpielart;
import skat09.test.stub.OutputStub;
import skat09.test.stub.ControllerStub;
import skat09.test.stub.NullGameStub;


public class HumanPlayerTest {

	Table tisch = new Table();
	IOutput ausgabe = new OutputStub(null,null,null);
	IController controller = new ControllerStub(tisch, ausgabe);
	HumanPlayer mensch = new HumanPlayer("Peter", controller);

	@Before
	public void setUp() throws Exception {

		mensch.setBlatt(new ArrayList<Spielkarte>());
	}

	@Test
	public void hoerenTest1() {

		assertTrue(mensch.hoeren(20));
	}

	@Test
	public void hoerenTest2() {

		assertFalse(mensch.hoeren(23));
	}

	@Test
	public void sagenTest1() {

		assertTrue(mensch.sagen(20));
	}

	@Test
	public void sagenTest2() {

		assertFalse(mensch.sagen(23));
	}

	@Test
	public void drueckenTest() {

		Spielkarte[] skat = new Spielkarte[3];
		skat[0] = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		skat[1] = new Spielkarte(Farbe.KREUZ, Wert.KOENIG);
		skat[2] = new Spielkarte(Farbe.KREUZ, Wert.ASS);
		assertArrayEquals(skat, mensch.druecken(skat));
	}

	@Test
	public void drueckenTest2() {

		Spielkarte[] skat = new Spielkarte[3];
		skat[0] = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		skat[1] = new Spielkarte(Farbe.KARO, Wert.ACHT);
		skat[2] = null;
		assertArrayEquals(skat, mensch.druecken(skat));
	}

	@Test
	public void spieleKarteTest() {

		boolean ergebnis = false;

		mensch.setSpielart(new Grandspiel());
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		gespielteKarten[0] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		gespielteKarten[1] = new Spielkarte(Farbe.HERZ, Wert.NEUN);
		if (karte.equals(mensch.spieleKarte(gespielteKarten))) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void spieleKarteTest2() {

		Spielart nullspiel = new NullGameStub();
		boolean ergebnis = false;

		mensch.setSpielart(nullspiel);
		Spielkarte karte = new Spielkarte(Farbe.KREUZ, Wert.BUBE);
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		
		if (karte.equals(mensch.spieleKarte(gespielteKarten))) {
				ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void handspielTest() {

		assertFalse(mensch.handspiel());
	}

	@Test
	public void ouvertTest() {

		assertTrue(mensch.ouvert());
	}

	@Test
	public void schneiderTest() {

		assertTrue(mensch.schneider());
	}

	@Test
	public void schwarzTest() {

		assertFalse(mensch.schwarz());
	}

	@Test
	public void spielAnsagenTest() {

		boolean ergebnis = false;

		ISpielart spielart = new Grandspiel();
		if (spielart.getSpielart().equals(mensch.spielAnsagen().getSpielart())) {

			ergebnis = true;
		}
		assertTrue(ergebnis);
	}

	@Test
	public void farbeTest() {

		SuitGame farbspiel = new SuitGame(Farbe.PIK);
		assertEquals(farbspiel.getTrumpffarbe(), mensch.farbe()
				.getTrumpffarbe());
	}

	@Test
	public void reizlimitFestlegen() {

		assertEquals(23, mensch.reizlimitFestlegen());
	}

	@Test
	public void reizAgentTest() {

		assertFalse(mensch.agent());
	}
}
