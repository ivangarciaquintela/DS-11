package e2;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ConcursoTest {
    private Concurso concurso;
    ArrayList<String> lista;
    @BeforeEach
    void setUp() {
        lista = new ArrayList<>();
        lista.add("1");
        lista.add("2");
        lista.add("3");
        lista.add("4");
        lista.add("5");
    }

    @org.junit.jupiter.api.Test
    void testBasic(){
        setUp();
        concurso = new Concurso();
        assertEquals("1",    concurso.selectCandidates(new TVRealityListRebote((List<String>) lista.clone()),3));
        assertEquals("4",    concurso.selectCandidates(new TVRealityListCircular((List<String>) lista.clone()),3));


    }
    @org.junit.jupiter.api.Test
    void testCompleto(){
        setUp();
        concurso = new Concurso();
        TVRealityListRebote lr = new TVRealityListRebote();
        TVRealityListCircular lc= new TVRealityListCircular();
        for (String s : lista) {
            lr.add(s);
            lc.add(s);
        }

        assertEquals(5,    lc.size());
        assertEquals(5,    lr.size());
        assertEquals("1",    concurso.selectCandidates(lr,3));
        assertEquals("4",    concurso.selectCandidates(lc,3));
        TVRealityListRebote.ReboteIterator<String> i = (TVRealityListRebote.ReboteIterator<String>) lr.iterator();
        assertThrows(IllegalStateException.class, () ->i.remove());
        lr = new TVRealityListRebote();
        TVRealityListRebote.ReboteIterator<String> b = (TVRealityListRebote.ReboteIterator<String>) lr.iterator();
        assertThrows(NoSuchElementException.class, () ->b.next());


    }
}