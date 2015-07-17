package skat09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import skat09.Controller;
import skat09.SkatVariant;
import skat09.Table;
import skat09.spielart.Farbspiel;
import skat09.spielart.Grandspiel;
import skat09.spielart.Nullspiel;
import skat09.spieler.HumanPlayer;
import skat09.spieler.Oma;
import skat09.spieler.Position;
import skat09.spieler.RegelkonformerSpieler;
import skat09.spieler.SmartPlayer;
import skat09.spieler.Player;
import skat09.spielkarte.Farbe;
import skat09.spielkarte.Spielkarte;
import skat09.spielkarte.Wert;
import skat09.test.interfaces.IOutput;
import skat09.test.interfaces.ISpieler;
import skat09.test.stub.OutputStub;
import skat09.test.stub.MenschlicherSpielerStub;
import skat09.test.stub.MenschlicherSpielerStub2;
import skat09.ui.Output;
import skat09.ui.CLIOutput;


public class ControllerTest {
	
	private Table tisch;
	private Output ausgabe;
	private Controller controller;
	
	
	public ControllerTest(){
		
		tisch = new Table();
		ausgabe = new CLIOutput(tisch);
		controller = new Controller(tisch,ausgabe);
		
	}
	
	@Test
	public void getAusgabeTest() {
		assertEquals(ausgabe,controller.getAusgabe());
	}
	
	
	@Test
	public void releaseTest() {
		
		boolean erfuellt;
		controller.release();
		
		if (controller.getTisch() == null && controller.getAusgabe() == null){
			erfuellt = true;
			
			assertTrue(erfuellt);
		}
	}
	
	@Test
	public void anmeldenTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controller2 = new Controller(tisch, stubbie);
		controller2.anmelden();
		assertEquals("Knut",controller2.getAusgabe().name());
	}
	
	@Test
	public void waehleGegnerTest1(){
		
		OutputStub gegner1 = new OutputStub("o",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new Oma("Oma Karla");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}

	@Test
	public void waehleGegnerTest2(){
		
		OutputStub gegner1 = new OutputStub("o",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new Oma("Oma Berta");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest3(){
		
		OutputStub gegner1 = new OutputStub("r",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new RegelkonformerSpieler("Hans");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest4(){
		
		OutputStub gegner1 = new OutputStub("r",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new RegelkonformerSpieler("Franz");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest5(){
		
		OutputStub gegner1 = new OutputStub("s",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new SmartPlayer("Heinz");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler2())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleGegnerTest6(){
		
		OutputStub gegner1 = new OutputStub("s",null,null);
		Controller controller = new Controller(tisch,gegner1);
		controller.waehleGegner();
		
		ISpieler spieler = new SmartPlayer("Wolfgang");
		
		boolean pruefe = false;
		
		if (spieler.equals(controller.getTisch().getSpieler3())) {
				pruefe = true;
		}
		
		assertTrue(pruefe);
		
	}
	
	@Test
	public void waehleSkartartTest1() {
		
		OutputStub skat1 = new OutputStub(null,"r",null);
		Controller controller = new Controller(tisch,skat1);
		controller.waehleSkatart();
		SkatVariant variante = SkatVariant.RAEUBER;
		assertEquals(variante,controller.getTisch().getVariante());
	}
	
	@Test
	public void waehleSkartartTest2() {
		
		OutputStub skat1 = new OutputStub(null,"i",null);
		Controller controller = new Controller(tisch,skat1);
		controller.waehleSkatart();
		SkatVariant variante = SkatVariant.SKAT;
		assertEquals(variante,controller.getTisch().getVariante());
	}
	
	@Test
	public void waehleSkartartTest3() {
		
		OutputStub skat1 = new OutputStub(null,"b",null);
		Controller controller = new Controller(tisch,skat1);
		controller.waehleSkatart();
		SkatVariant variante = SkatVariant.RAMSCHBOCK;
		assertEquals(variante,controller.getTisch().getVariante());
	}
	
	@Test
	public void waehleSkatBlattTest1() {
		
		OutputStub wahl1 = new OutputStub(null,null,"f");
		Controller controller = new Controller(tisch,wahl1);
		controller.waehleSkatblatt();
		assertFalse(Spielkarte.getDeutsch());
		}
	
	@Test
	public void waehleSkatBlattTest2() {
		
		OutputStub wahl1 = new OutputStub(null,null,"d");
		Controller controller = new Controller(tisch,wahl1);
		controller.waehleSkatblatt();
		assertTrue(Spielkarte.getDeutsch());
		}
	
	@Test
	public void leiteReizenTest() {
		
		Player spieler = new Oma("Hannelore");
		tisch.setSpieler1(spieler);
		tisch.setSpieler2(new Oma("Lara"));
		tisch.setSpieler3(new Oma("Hoi"));
		tisch.positionInitialisieren();
		IOutput ausgabe = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch, ausgabe);
		try {
			controller2.leiteReizen();
		} catch (IOException e) {
			e.printStackTrace();
		}
				assertEquals(null, controller.getTisch().ermittleAlleinspieler());
	}
	
	@Test
	public void leiteReizenTest2() {
		
		ISpieler spieler = new MenschlicherSpielerStub("Hannelore");
		tisch.setSpieler1(spieler);
		tisch.setSpieler2(new RegelkonformerSpieler("Lara"));
		tisch.setSpieler3(new RegelkonformerSpieler("Hoi"));
		tisch.positionInitialisieren();
		IOutput ausgabe = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch, ausgabe);
		try {
			controller2.leiteReizen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(spieler.getName(), controller.getTisch().ermittleAlleinspieler().getName());
	}
	
	@Test
	public void reizenOderReizagent() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(20);
		
		assertTrue(controller.reizenOderReizagent(spieler1, 18, true));
	}
	
	@Test
	public void reizenOderReizagent2() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(20);
		
		assertTrue(controller.reizenOderReizagent(spieler1, 23, true));
	}
	
	@Test
	public void reizenOderReizagent3() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(0);
		
		assertTrue(controller.reizenOderReizagent(spieler1, 20, true));
	}
	
	@Test
	public void reizenOderReizagent4() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(0);
		
		assertFalse(controller.reizenOderReizagent(spieler1, 23, true));
	}
	
	@Test
	public void reizenOderReizagent5() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(0);
		
		assertTrue(controller.reizenOderReizagent(spieler1, 20, false));
	}
	
	@Test
	public void reizenOderReizagent6() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Benn");
		controller.getTisch().setSpieler1(spieler1);
		ISpieler spieler2 = new RegelkonformerSpieler("Mike");
		controller.getTisch().setSpieler2(spieler2);
		ISpieler spieler3 = new RegelkonformerSpieler("Sven");
		controller.getTisch().setSpieler3(spieler3);
		controller.getTisch().setReizagentWert(0);
		
		assertFalse(controller.reizenOderReizagent(spieler1, 23, false));
	}
	
	@Test
	public void reizen1Test1() {
		
		ISpieler spieler1 = new Oma("Hannelore");
		ISpieler spieler2 = new Oma("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler1.getName(), controller.reizen1(spieler1, spieler2).getName());
	}
	
	@Test
	public void reizen1Test2() {
		
		ISpieler spieler1 = new Oma("Hannelore");
		ISpieler spieler2 = new RegelkonformerSpieler("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler2.getName(), controller.reizen1(spieler1, spieler2).getName());
	}
	
	
	@Test
	public void reizen2Test1() {
		
		ISpieler spieler1 = new Oma("Hannelore");
		ISpieler spieler2 = new Oma("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(null, controller.reizen2(spieler1, spieler2));
	}
	
	@Test
	public void reizen2Test2() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub("Hannelore");
		ISpieler spieler2 = new MenschlicherSpielerStub2("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler1, controller.reizen2(spieler1, spieler2));
	}
	
	@Test
	public void reizen2Test3() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub2("Hannelore");
		ISpieler spieler2 = new MenschlicherSpielerStub("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler2, controller.reizen2(spieler1, spieler2));
	}
	
	@Test
	public void reizen2Test4() {
		
		ISpieler spieler1 = new MenschlicherSpielerStub2("Hannelore");
		ISpieler spieler2 = new Oma("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler1, controller.reizen2(spieler1, spieler2));
	}
	
	@Test
	public void reizen2Test5() {
		
		ISpieler spieler1 = new Oma("Hannelore");
		ISpieler spieler2 = new MenschlicherSpielerStub2("Friedel");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		
		assertEquals(spieler2, controller.reizen2(spieler1, spieler2));
	}
	
	
	@Test
	public void entscheideRauberspielTest() {
		
		
		tisch.setSpieler1(new Oma("Hildegard"));
		tisch.setSpieler2(new Oma("Gräfin Johanita von Schwanenstein"));
		
		tisch.setSpieler3(new Oma("Zensursula"));
		tisch.getSpieler1().setPosition(Position.VORHAND);
		controller.entscheideraeuberspiel();
		
		assertTrue(tisch.getVorhand().getIstAlleinspieler());
		

	}
	
	@Test
	public void bereiteSpielvorTest() {
		
		tisch.setSpieler1(new HumanPlayer("Hildegard",controller));
		SmartPlayer sspieler = new SmartPlayer("Gräfin Johanita von Schwanenstein");
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.BUBE));
		sspieler.setBlatt(blatt);
		sspieler.setAnfangsblatt(blatt);
		tisch.setSpieler2(sspieler);
		tisch.setSpieler3(new Oma("Zensursula"));
		
		Controller controller = new Controller(tisch, ausgabe);
		
		controller.bereiteSpielvor();
			boolean hatnamen = true;
		for (Spielkarte karte : tisch.getSpieler1().getBlatt()) {
			if (karte.getBesitzer() == null) {
				hatnamen = false;
			}
				}
		for (Spielkarte karte : tisch.getSpieler2().getBlatt()) {
			if (karte.getBesitzer() == null) {
				hatnamen = false;
			}
				}
		for (Spielkarte karte : tisch.getSpieler3().getBlatt()) {
			if (karte.getBesitzer() == null) {
				hatnamen = false;
			}
				}
		assertTrue(hatnamen);
	}
	
	@Test
	public void alleinspielerAktionenTest() {
		
		Table tisch2 = new Table();
		OutputStub ausgabe2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch2, ausgabe2);
		MenschlicherSpielerStub mensch = new MenschlicherSpielerStub("Bernd");
		mensch.setIstAlleinspieler(true);
		mensch.setBlatt(new ArrayList<Spielkarte>());
		for (int i = 0; i < 10; i++) {
			mensch.getBlatt().add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		}
		controller2.getTisch().setSpieler1(mensch);
		Player spieler2 = new Oma("Basti");
		Player spieler3 = new Oma("ungluecklich");
		controller2.getTisch().setSpieler2(spieler2);
		controller2.getTisch().setSpieler3(spieler3);
		controller2.getTisch().setSechserskat(true);
		Spielkarte[] skat = new Spielkarte[3];
		skat[0] = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		skat[1] = new Spielkarte(Farbe.HERZ, Wert.SECHS);
		skat[2] = new Spielkarte(Farbe.PIK, Wert.DAME);
		controller2.getTisch().setSkat(skat);
		controller2.getTisch().setSpielart(new Farbspiel(Farbe.KARO));

		
		//handspiel pruefen
		boolean handspiel = false;
		if (!controller2.getTisch().getHandspiel()) {
			handspiel = true;
		}
		
		//spielart pruefen
		boolean spielart2 = false;
		System.out.println(controller2.getTisch().getSpielart().toString());
		if (controller2.getTisch().getSpielart().toString().equals((new Farbspiel(Farbe.KARO)).toString())) {
			spielart2 = true;
		}
		
		try {
			controller2.alleinspielerAktionen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean allesKorrekt = false;
		if (handspiel && spielart2) {
			allesKorrekt = true;
		}
		
		assertTrue(allesKorrekt);
	}
	
	@Test
	public void auswertungTest() {
		
		OutputStub ausgabe = new OutputStub(null, null, null);
		Table tisch2 = new Table();
		Controller controller = new Controller(tisch2, ausgabe);
		
		ISpieler spieler1 = new Oma("Erna");
		ISpieler spieler2 = new Oma("Roy");
		ISpieler spieler3 = new Oma("Marry");
		
		spieler1.setIstAlleinspieler(true);
		
		ArrayList<Spielkarte> blatt1 = new ArrayList<Spielkarte>(); 
		blatt1.add(new Spielkarte(Farbe.KREUZ, Wert.BUBE));
		blatt1.add(new Spielkarte(Farbe.KREUZ, Wert.ASS));
		blatt1.add(new Spielkarte(Farbe.KREUZ, Wert.ZEHN));
		blatt1.add(new Spielkarte(Farbe.KREUZ, Wert.DAME));
		blatt1.add(new Spielkarte(Farbe.KREUZ, Wert.KOENIG));
		blatt1.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		blatt1.add(new Spielkarte(Farbe.KARO, Wert.ZEHN));
		blatt1.add(new Spielkarte(Farbe.PIK, Wert.ASS));
		blatt1.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		spieler1.setStiche(blatt1);
		spieler1.setBlatt(blatt1);
		spieler1.setSpielart(new Farbspiel(Farbe.KREUZ));
		spieler1.spitzenEinordnen();
		
		ArrayList<Spielkarte> blatt2 = new ArrayList<Spielkarte>(); 
		blatt2.add(new Spielkarte(Farbe.PIK, Wert.NEUN));
		blatt2.add(new Spielkarte(Farbe.PIK, Wert.SECHS));
		blatt2.add(new Spielkarte(Farbe.PIK, Wert.ZEHN));
		blatt2.add(new Spielkarte(Farbe.PIK, Wert.DAME));
		spieler2.setStiche(blatt2);
		
		ArrayList<Spielkarte> blatt3 = new ArrayList<Spielkarte>(); 
		blatt3.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		blatt3.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt3.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt3.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		spieler3.setStiche(blatt3);
		
		tisch2.setSpieler1(spieler1);
		tisch2.setSpieler2(spieler2);
		tisch2.setSpieler3(spieler3);
		tisch2.setSpielart(new Farbspiel(Farbe.KREUZ));
		
		controller.auswertung();
		
		OutputStub ausgabe2 = (OutputStub) controller.getAusgabe();
		assertEquals(24, ausgabe2.getPunkte());
		
	}
	
	@Test
	public void aufrauemenTest() {
		
		
		Spielkarte[] skatkarten = new Spielkarte[3];
		skatkarten[0] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		controller.getTisch().setSkat(skatkarten);
		controller.getTisch().setReizagentWert(120);
		controller.getTisch().setReizwert(23);
		controller.getTisch().setBock(true);
		controller.getTisch().setVariante(SkatVariant.RAMSCHBOCK);
		controller.getTisch().setSpaltarsch(true);
		controller.getTisch().setBockrunden(0);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
	
		Player spieler1 = new Oma("Joy");
		spieler1.setBlatt(blatt);
		spieler1.setSpielart(new Grandspiel());
		spieler1.setIstAlleinspieler(true);
		Player spieler2 = new RegelkonformerSpieler("Leon");
		spieler2.setBlatt(blatt);
		spieler2.setStiche(blatt);
		Player spieler3 = new RegelkonformerSpieler("John Wayne");
		spieler3.setBlatt(blatt);
		spieler3.setAlleGespieltenKarten(blatt);
		
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		
		controller.aufrauemen();
		
		Table tisch3 = controller.getTisch();
				
		boolean spieler3Aufgeraeumt = false;

		ISpieler sp3 = controller.getTisch().getSpieler3();
		if (sp3.getBlatt() == null && sp3.getIstAlleinspieler() == false &&
				sp3.getStiche().size() == 0 && sp3.getAllegespieltenkarten().size() == 0) {
			spieler3Aufgeraeumt = true;
		}
		
		assertTrue(spieler3Aufgeraeumt);
	}
	
	@Test
	public void aufraeumenTest2() {
		
		Spielkarte[] skatkarten = new Spielkarte[3];
		skatkarten[0] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		controller.getTisch().setSkat(skatkarten);
		controller.getTisch().setReizagentWert(120);
		controller.getTisch().setReizwert(23);
		controller.getTisch().setBock(true);
		controller.getTisch().setVariante(SkatVariant.RAMSCHBOCK);
		controller.getTisch().setSpaltarsch(true);
		controller.getTisch().setBockrunden(0);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
	
		Player spieler1 = new Oma("Joy");
		spieler1.setBlatt(blatt);
		spieler1.setSpielart(new Grandspiel());
		spieler1.setIstAlleinspieler(true);
		Player spieler2 = new RegelkonformerSpieler("Leon");
		spieler2.setBlatt(blatt);
		spieler2.setStiche(blatt);
		Player spieler3 = new RegelkonformerSpieler("John Wayne");
		spieler3.setBlatt(blatt);
		spieler3.setAlleGespieltenKarten(blatt);
		
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		
		controller.aufrauemen();
		
		Table tisch3 = controller.getTisch();
		
		boolean tischAufgeraeumt = false;
		
		if (tisch3.getSkat()[0] == null && tisch3.getReizagentWert() == 0 && tisch3.getReizwert() == 18 &&
				tisch3.getBock() == false && tisch3.getSpaltarsch() == false) {
			tischAufgeraeumt = true;
		}
		
		assertTrue(tischAufgeraeumt);
	}
	
	@Test
	public void aufraeumenTest3() {
		
		Spielkarte[] skatkarten = new Spielkarte[3];
		skatkarten[0] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		controller.getTisch().setSkat(skatkarten);
		controller.getTisch().setReizagentWert(120);
		controller.getTisch().setReizwert(23);
		controller.getTisch().setBock(true);
		controller.getTisch().setVariante(SkatVariant.RAMSCHBOCK);
		controller.getTisch().setSpaltarsch(true);
		controller.getTisch().setBockrunden(0);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
	
		Player spieler1 = new Oma("Joy");
		spieler1.setBlatt(blatt);
		spieler1.setSpielart(new Grandspiel());
		spieler1.setIstAlleinspieler(true);
		Player spieler2 = new RegelkonformerSpieler("Leon");
		spieler2.setBlatt(blatt);
		spieler2.setStiche(blatt);
		Player spieler3 = new RegelkonformerSpieler("John Wayne");
		spieler3.setBlatt(blatt);
		spieler3.setAlleGespieltenKarten(blatt);
		
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		
		controller.aufrauemen();
		
		Table tisch3 = controller.getTisch();
		
		boolean spieler1Aufgeraeumt = false;
		
		ISpieler sp1 = controller.getTisch().getSpieler1();
		if (sp1.getBlatt() == null && !sp1.getIstAlleinspieler() &&
				sp1.getStiche().size() == 0 && sp1.getAllegespieltenkarten().size() == 0) {
			spieler1Aufgeraeumt = true;
		}
		
		assertTrue(spieler1Aufgeraeumt);
	}
	
	@Test
	public void aufraeumen4Test() {
	
		Spielkarte[] skatkarten = new Spielkarte[3];
		skatkarten[0] = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		controller.getTisch().setSkat(skatkarten);
		controller.getTisch().setReizagentWert(120);
		controller.getTisch().setReizwert(23);
		controller.getTisch().setBock(true);
		controller.getTisch().setVariante(SkatVariant.RAMSCHBOCK);
		controller.getTisch().setSpaltarsch(true);
		controller.getTisch().setBockrunden(0);
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.KARO, Wert.SECHS));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.BUBE));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.NEUN));
	
		Player spieler1 = new Oma("Joy");
		spieler1.setBlatt(blatt);
		spieler1.setSpielart(new Grandspiel());
		spieler1.setIstAlleinspieler(true);
		Player spieler2 = new RegelkonformerSpieler("Leon");
		spieler2.setBlatt(blatt);
		spieler2.setStiche(blatt);
		Player spieler3 = new RegelkonformerSpieler("John Wayne");
		spieler3.setBlatt(blatt);
		spieler3.setAlleGespieltenKarten(blatt);
		
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		
		controller.aufrauemen();
		
		Table tisch3 = controller.getTisch();
		
		boolean spieler2Aufgeraeumt = false;
		
		ISpieler sp2 = controller.getTisch().getSpieler2();
		if (sp2.getBlatt() == null && sp2.getIstAlleinspieler() == false &&
				sp2.getStiche().size() == 0 && sp2.getAllegespieltenkarten().size() == 0) {
			spieler2Aufgeraeumt = true;
		}
		
		assertTrue(spieler2Aufgeraeumt);
	}
	
	@Test
	public void stichAuswertungTest() {
		
		Table tisch2 = new Table();
		OutputStub ausgabe2 = new OutputStub(null, null, null);
		Controller controller2 = new Controller(tisch2, ausgabe2);
		
		Player gewinner = new Oma("Gustav Gans");
		Player s2 = new Oma("Benny");
		Player s3 = new Oma("Daniel");
		Spielkarte[] gespielteKarten = new Spielkarte[3];
		Spielkarte k1 = new Spielkarte(Farbe.HERZ, Wert.BUBE);
		Spielkarte k2 = new Spielkarte(Farbe.HERZ, Wert.ASS);
		Spielkarte k3 = new Spielkarte(Farbe.KARO, Wert.SIEBEN);
		k1.setBesitzer(gewinner);
		k2.setBesitzer(s2);
		k3.setBesitzer(s3);
		gespielteKarten[0] = k1;
		gespielteKarten[1] = k2;
		gespielteKarten[2] = k3;
		
		controller2.stichAuswertung(gespielteKarten, gewinner);
		
		OutputStub ausgabe = (OutputStub) controller2.getAusgabe();
		assertEquals(3, ausgabe.getGespielteKartenZahl());
		
		
	}
	
	@Test
	public void reizagentTest() {
		
		Player spieler = new Oma("Tini");
		tisch.setReizwert(23);
		tisch.setReizagentWert(30);
		assertTrue(controller.reizagent(spieler));
	}
	
	@Test
	public void reizagentTest2() {
		
		Player spieler = new Oma("Tini");
		tisch.setReizwert(30);
		tisch.setReizagentWert(23);
		assertFalse(controller.reizagent(spieler));
	}
	
	@Test
	public void skartkartenBesitzerGebenTest() {
		
		ISpieler oma = new Oma("o1");
		controller.getTisch().setSpieler1(oma);
		controller.getTisch().getSpieler1().setIstAlleinspieler(true);
		controller.getTisch().setSpieler2(new Oma("o2"));
		controller.getTisch().setSpieler3(new Oma("o3"));
		controller.getTisch().setSechserskat(true);
		controller.getTisch().erstelleDeck();
		controller.getTisch().kartenAusteilen();
		controller.getTisch().kartenBesitzergeben();
		
		controller.skatkartenBesitzergeben();
		
		Spielkarte[] skat = controller.getTisch().getSkat();
		boolean initErfolgreich = false;
		if(skat[0].getBesitzer().equals(oma) && skat[1].getBesitzer().equals(oma) && 
				skat[2].getBesitzer().equals(oma)) {

			initErfolgreich = true;
		}
		assertTrue(initErfolgreich);
	}
	
	@Test
	public void flagsSetzenTest1() {
		
		tisch.setSpieler1(new Oma("Erna"));
		tisch.getSpieler1().setIstAlleinspieler(true);
		tisch.setSpieler2(new Oma("Renate"));
		tisch.setSpieler3(new Oma("Mochochocho"));
		tisch.setSpielart(new Nullspiel());
		controller.flagsSetzen(tisch.getSpieler1(), tisch.getSpielart());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest2() {
		
		tisch.setSpieler1(new Oma("Erna"));
		tisch.getSpieler1().setIstAlleinspieler(true);
		tisch.setSpieler2(new Oma("Renate"));
		tisch.setSpieler3(new Oma("Mochochocho"));
		tisch.setSpielart(new Nullspiel());
		tisch.setHandspiel(false);
		controller.flagsSetzen(tisch.getSpieler1(), tisch.getSpielart());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest3() {
		
		tisch.setSpieler1(new Oma("Erna"));
		tisch.getSpieler1().setIstAlleinspieler(true);
		tisch.setSpieler2(new Oma("Renate"));
		tisch.setSpieler3(new Oma("Mochochocho"));
		tisch.setSpielart(new Grandspiel());
		controller.flagsSetzen(tisch.getSpieler1(), tisch.getSpielart());
		
		boolean ergebnis = false;
		if (!tisch.getSchneider() && !tisch.getSchwarz() && !tisch.getOuvert()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void flagsSetzenTest4() {
		
		tisch.setSpieler1(new MenschlicherSpielerStub("Bert"));
		tisch.getSpieler1().setIstAlleinspieler(true);
		tisch.setSpieler2(new Oma("Renate"));
		tisch.setSpieler3(new Oma("Mochochocho"));
		tisch.setSpielart(new Grandspiel());
		tisch.setHandspiel(true);
		controller.flagsSetzen(tisch.getSpieler1(), tisch.getSpielart());
		
		boolean ergebnis = false;
		if (tisch.getSchneider() && tisch.getSchwarz() && tisch.getOuvert()) {
			ergebnis = true;
		}
		assertTrue(ergebnis);
	}
	
	@Test
	public void ramschenTest() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.ramschen();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());
	}
	
	@Test
	public void normalerSpielverlaufTest() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.normalerSpielverlauf();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielRaeuberskatTest() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielRaeuberskat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielRamschBockTest() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.setSpaltarsch(true);
		tisch2.setRamschrunden(0);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielRamschBockTest2() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.setSpaltarsch(true);
		tisch2.setRamschrunden(1);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielRamschBockTest3() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new RegelkonformerSpieler("Hilde"));
		tisch2.setSpieler2(new RegelkonformerSpieler("Heide"));
		tisch2.setSpieler3(new RegelkonformerSpieler("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielRamschBock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielIntSkatTest() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new Oma("Hilde"));
		tisch2.setSpieler2(new Oma("Heide"));
		tisch2.setSpieler3(new Oma("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void spielIntSkatTest2() {
		
		Table tisch2 = new Table();
		tisch2.setSpieler1(new RegelkonformerSpieler("Hilde"));
		tisch2.setSpieler2(new RegelkonformerSpieler("Heide"));
		tisch2.setSpieler3(new RegelkonformerSpieler("Harald"));
		tisch2.getSpieler1().setIstAlleinspieler(true);
		tisch2.positionInitialisieren();
		tisch2.erstelleDeck();
		tisch2.kartenAusteilen();
		tisch2.kartenBesitzergeben();
		Output ausgabe = new CLIOutput(tisch2);
		Controller controller2 = new Controller(tisch2, ausgabe);
		try {
			controller2.spielIntSkat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(Position.MITTELHAND, controller2.getTisch().getSpieler3().getPosition());	
	}
	
	@Test
	public void schlauerSpielerInitTest() {
		
		Player spieler1 = new SmartPlayer("Halo");
		Player spieler2 = new SmartPlayer("Evin");
		Player spieler3 = new RegelkonformerSpieler("Bernd");
		
		ArrayList<Spielkarte> blatt = new ArrayList<Spielkarte>();
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ACHT));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.NEUN));
		blatt.add(new Spielkarte(Farbe.HERZ, Wert.ZEHN));
		blatt.add(new Spielkarte(Farbe.PIK, Wert.SIEBEN));
		blatt.add(new Spielkarte(Farbe.KARO, Wert.ASS));
		
		spieler1.setBlatt(blatt);
		spieler2.setBlatt(blatt);
		spieler3.setBlatt(blatt);
		
		spieler1.setIstAlleinspieler(true);
		
		Spielkarte[] skat = new Spielkarte[3];
		skat[0] = new Spielkarte(Farbe.HERZ, Wert.ACHT);
		skat[1] = new Spielkarte(Farbe.PIK, Wert.ASS);
		skat[2] = new Spielkarte(Farbe.KREUZ, Wert.DAME);
		tisch.setSkat(skat);
		tisch.setSpieler1(spieler1);
		tisch.setSpieler2(spieler2);
		tisch.setSpieler3(spieler3);
		ArrayList<Spielkarte> skat2 = new ArrayList<Spielkarte>(Arrays.asList(skat));
		
		SmartPlayer ergebnis = (SmartPlayer) tisch.getSpieler1();
		
		controller.schlauerSpielerInit();
		
		assertEquals(skat2, ergebnis.getSkat());
	}
	
	@Test
	public void namenVergleichTest() {
		
		ISpieler spieler1 = new Oma("Gertrud");
		ISpieler spieler2 = new Oma("Gertrud");
		ISpieler spieler3 = new Oma("Gertrud");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		controller.namenVergleich();
		assertEquals("Gertrud1", controller.getTisch().getSpieler1().getName());
	}
	
	@Test
	public void namenVergleichTest2() {
		
		ISpieler spieler1 = new Oma("Gertrud");
		ISpieler spieler2 = new Oma("Gertrud");
		ISpieler spieler3 = new Oma("Hans");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		controller.namenVergleich();
		assertEquals("Gertrud2", controller.getTisch().getSpieler2().getName());
	}
	
	@Test
	public void namenVergleichTest3() {
		
		ISpieler spieler1 = new Oma("Gertrud");
		ISpieler spieler2 = new Oma("Hans");
		ISpieler spieler3 = new Oma("Gertrud");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		controller.namenVergleich();
		assertEquals("Gertrud2", controller.getTisch().getSpieler3().getName());
	}
	
	@Test
	public void namenVergleichTest4() {
		
		ISpieler spieler1 = new Oma("Hans");
		ISpieler spieler2 = new Oma("Gertrud");
		ISpieler spieler3 = new Oma("Gertrud");
		controller.getTisch().setSpieler1(spieler1);
		controller.getTisch().setSpieler2(spieler2);
		controller.getTisch().setSpieler3(spieler3);
		controller.namenVergleich();
		assertEquals("Gertrud1", controller.getTisch().getSpieler2().getName());
	}
	
	@Test
	public void spielbeendenTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controll = new Controller(tisch,stubbie);
		
		assertTrue(controll.spielBeenden());
	}
	
	@Test
	public void warteTest() {
		
		OutputStub stubbie = new OutputStub(null,null,null);
		Controller controll = new Controller(tisch,stubbie);
		controll.warte();
		assertTrue(controll.getAusgabe().getRelease());
	}
}
