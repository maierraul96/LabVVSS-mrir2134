package note.repository;

import note.controller.NoteController;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.utils.ClasaException;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClasaRepositoryMockTest {
    private NoteController ctrl;

    @Before
    public void setup() {
        ctrl = new NoteController();
        ctrl.readElevi("eleviTest.txt");
        ctrl.readNote("noteTest.txt");
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
    }

    @Test
    public void calculeazaMedii1() {
        try {
            ctrl.calculeazaMedii();
            assertTrue(false);
        } catch (ClasaException e){
            assertEquals(Constants.emptyRepository, e.getMessage());
        }
    }

    @Test
    public void calculeazaMedii2() {
        Elev e1 = new Elev(123,"Mircea");
        Nota n1 = new Nota(123, "Matematica", 8.0);
        Nota n2 = new Nota(123, "Romana", 9.0);
        ctrl.addElev(e1);
        try {
            ctrl.addNota(n1);
            ctrl.addNota(n2);
            assertTrue(true);
            List<Medie> medii = ctrl.calculeazaMedii();
            assertEquals(1, medii.size());
            assertTrue(medii.get(0).getMedie() == 8.5);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void calculeazaMedii3() {
        Elev e1 = new Elev(123, "Marina");
        ctrl.addElev(e1);
        try {
            List<Medie> medii = ctrl.calculeazaMedii();
            assertEquals(1, medii.size());
            assertTrue(medii.get(0).getMedie() == 0.0);
        } catch (ClasaException e) {
            assert false;
        }
    }

    @Test
    public void calculeazaMedii4() {
        Elev e1 = new Elev(345, "Arthur");
        Elev e2 = new Elev(234, "Maya");
        Elev e3 = new Elev(221,"Rares");
        Elev e4 = new Elev(123, "Aura");
        Nota n1 = new Nota(345, "Sport", 10);
        Nota n2 = new Nota (234, "Romana", 8.5);
        Nota n3 = new Nota(221, "Sport", 6.9);
        Nota n4 = new Nota(123, "Matematica", 5.8);
        try {
            ctrl.addElev(e1); ctrl.addElev(e2); ctrl.addElev(e3); ctrl.addElev(e4);
            ctrl.addNota(n1);
            ctrl.addNota(n2);
            ctrl.addNota(n3);
            ctrl.addNota(n4);
            ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
            List<Medie> medii = ctrl.calculeazaMedii();
            assertTrue(true);
            assertTrue(medii.get(2).getMedie() > 1);
            assertEquals(4,medii.size());
        } catch(ClasaException ex) {
            assertTrue(false);
        }
    }

    @Test
    public void testCorigenti(){
        Elev e1 = new Elev(1,"Marian");
        Elev e2 = new Elev(2, "Dana");
        Nota nM = new Nota(1,"Sport", 10);
        Nota nD = new Nota(2,"Sport", 4);
        Nota nM2 = new Nota(1, "Matem", 3.2);
        Nota nD2 = new Nota(2, "Matem", 9);

        ctrl.addElev(e1); ctrl.addElev(e2);
        try {
            ctrl.addNota(nM); ctrl.addNota(nM2); ctrl.addNota(nD); ctrl.addNota(nD2);
            ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
            List<Corigent> corigenti = ctrl.getCorigenti();
            assertEquals(2, corigenti.size());
            assertTrue(corigenti.get(0).getNumeElev() == "Dana");
            assertTrue(corigenti.get(1).getNrMaterii() == 1);
        } catch (ClasaException ex) {
            System.out.println(ex.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testCorigentiFail() {
        try {

            Elev e1 = new Elev(2, "Apc");
            Nota nota = new Nota(2,"Mate", 2);
            ctrl.addElev(e1); ctrl.addNota(nota);
            ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
            ctrl.calculeazaMedii();
            assertTrue(false);
        } catch (ClasaException ex) {
            assertTrue(true);
            assertEquals("Lungimea materiei este invalida", ex.getMessage());
        }
    }
}