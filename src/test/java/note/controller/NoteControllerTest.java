package note.controller;

import note.model.Nota;
import note.repository.ClasaRepository;
import note.repository.EleviRepository;
import note.repository.NoteRepository;
import note.utils.ClasaException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteControllerTest {
    private NoteController ctrl;

    @Before
    public void setup(){
        ctrl = new NoteController();
        ctrl.readElevi("eleviTest.txt");
        ctrl.readNote("noteTest.txt");
        ctrl.creeazaClasa(ctrl.getElevi(),ctrl.getNote());
    }

    @Test
    public void testAddNota1() throws ClasaException{
        Nota n1 = new Nota(2010, "Matematica", 8);
        ctrl.addNota(n1);
        assertEquals(1, ctrl.getNote().size());
    }

    @Test
    public void testAddNota2() throws ClasaException{
        Nota n1 = new Nota(2010, "Matematica", -3);
        try{
            ctrl.addNota(n1);
            assertTrue(false);
        } catch (ClasaException ex){
            assertEquals("Nota introdusa nu este corecta",ex.getMessage());
        }
    }

    @Test
    public void testAddNota3() throws ClasaException{
        Nota n1 = new Nota(
                2010,
                "Matematicocococococococococococoooooooooosdfksdnfkjndfjsbhbgsfhdbghsbfgjlbdbglsbdhjsbglbsgj,dsbhsbglbdbgsbgljbgg",
                -6
        );
        try{
            ctrl.addNota(n1);
            assertTrue(false);
        } catch (ClasaException ex){
            assertEquals("Lungimea materiei este invalida",ex.getMessage());
        }
    }

    @Test
    public void testAddNota4() throws ClasaException{
        try{
            Nota n1 = new Nota(
                    2010,
                    "Romana",
                    Double.parseDouble("fsfdsg")
            );

            ctrl.addNota(n1);
            assertTrue(false);
        } catch (Exception ex){
            assertEquals("For input string: \"fsfdsg\"", ex.getMessage());
        }
    }

    @Test
    public void testAddNota5() throws ClasaException{
        Nota n1 = new Nota(
                2121,
                "",
                9
        );
        try{
            ctrl.addNota(n1);
            assertTrue(false);
        } catch (ClasaException ex){
            assertEquals("Lungimea materiei este invalida",ex.getMessage());
        }
    }

    @Test
    public void testAddNota6() throws ClasaException{
        Nota n1 = new Nota(
                2121,
                "Matematica",
                10
        );
        try{
            ctrl.addNota(n1);
            assertTrue(true);
        } catch (ClasaException ex){
            assertTrue(false);
        }
    }

    @Test
    public void testAddNota7() throws ClasaException{
        Nota n1 = new Nota(
                2121,
                "Sport",
                0
        );
        try{
            ctrl.addNota(n1);
            assertTrue(false);
        } catch (ClasaException ex){
            assertEquals("Nota introdusa nu este corecta", ex.getMessage());
        }
    }

    @Test
    public void testAddNota8() throws ClasaException{
        Nota n1 = new Nota(
                2121,
                "SportSportSportSp19",
                6
        );
        try{
            ctrl.addNota(n1);
            assertTrue(true);
        } catch (ClasaException ex) {
            System.out.println(ex);
            assertTrue(false);
        }
    }

}