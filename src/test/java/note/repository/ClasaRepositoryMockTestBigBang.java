package note.repository;

import note.controller.NoteController;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.utils.ClasaException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClasaRepositoryMockTestBigBang {
    private NoteController ctrl;

    @Before
    public void setup() {
        ctrl = new NoteController();
        ctrl.readElevi("eleviTest.txt");
        ctrl.readNote("noteTest.txt");
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
    }

    @Test
    public void testA() throws ClasaException {
        Nota n1 = new Nota(
                2121,
                "Matematica",
                10
        );

        NoteRepositoryMock noteRepo = new NoteRepositoryMock();
        try{
            noteRepo.addNota(n1);
            assertTrue(true);
        } catch (ClasaException ex){
            assertTrue(false);
        }
    }

    @Test
    public void testB() {
        Elev e1 = new Elev(123,"Mircea");
        Nota n1 = new Nota(123, "Matematica", 8.0);
        Nota n2 = new Nota(123, "Romana", 9.0);
        ClasaRepositoryMock clasaRepo = new ClasaRepositoryMock();
        clasaRepo.creazaClasa(Arrays.asList(e1),Arrays.asList(n1, n2));
        try {
            assertTrue(true);
            List<Medie> medii = clasaRepo.calculeazaMedii();
            assertEquals(1, medii.size());
            assertTrue(medii.get(0).getMedie() == 8.5);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testC() {
        Elev e1 = new Elev(1,"Marian");
        Elev e2 = new Elev(2, "Dana");
        Nota nM = new Nota(1,"Sport", 10);
        Nota nD = new Nota(2,"Sport", 4);
        Nota nM2 = new Nota(1, "Matem", 3.2);
        Nota nD2 = new Nota(2, "Matem", 9);
        ClasaRepositoryMock clasaRepo = new ClasaRepositoryMock();

        clasaRepo.creazaClasa(Arrays.asList(e1, e2), Arrays.asList(nM, nM2, nD, nD2));
        List<Corigent> corigenti = clasaRepo.getCorigenti();
        assertEquals(2, corigenti.size());
        assertTrue(corigenti.get(0).getNumeElev() == "Dana");
        assertTrue(corigenti.get(1).getNrMaterii() == 1);
    }

    @Test
    public void testP() {
        Elev e1 = new Elev(3, "Spac");
        Nota n1 = new Nota(3,"MAtematica", 5);
        Nota n2 = new Nota(3, "Chineza", 1);
        try {
            ctrl.addElev(e1);
            assertTrue(ctrl.getElevi().size() == 1);

            ctrl.addNota(n1); ctrl.addNota(n2);
            ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
            assertEquals(2, ctrl.getNote().size());

            List<Corigent> corigenti = ctrl.getCorigenti();
            assertEquals(1,corigenti.size());

            List<Medie> medii = ctrl.calculeazaMedii();
            assertTrue(medii.get(0).getMedie() == 3);

        } catch (ClasaException exc) {
            assertTrue(false);
        }
    }
}