package e1;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {
    private Pelicula pelicula;
    @BeforeEach
    void setUp() {
        Miembro m0 = new Miembro("Josep", "Abad","32716631M", "652621780","Senegal",Profesion.ESPECIALISTA,100,4,true);
        Miembro m1 = new Miembro("Ainet", "Jounou","32716631M", "652621780","Senegal",Profesion.INTERPRETE,90,4,true);
        Miembro m2 = new Miembro("Xenia", "Roset","32716631M", "652621780","Senegal",Profesion.INTERPRETE,50,0,false);
        Miembro m3 = new Miembro("Cristina", "Puig","32716631M", "652621780","Senegal",Profesion.DOBLADOR,20,7,false);
        Miembro m4 = new Miembro("Carla", "Simon","32716631M", "652621780","Senegal",Profesion.DIRECTOR,500,7,false);
        Miembro m5 = new Miembro("Maria", "Zamora","32716631M", "652621780","Senegal",Profesion.PRODUCTOR,100,10,false);
        Miembro m6 = new Miembro("Andrea", "Koch","32716631M", "652621780","Senegal",Profesion.MUSICO,200,10,false);
        Miembro m7 = new Miembro("Arnau", "Vilaro","32716631M", "652621780","Senegal",Profesion.GUIONISTA,300,10,true);
        pelicula = new Pelicula("Alcarras",300000);
        pelicula.addMiembro(m0);
        pelicula.addMiembro(m1);
        pelicula.addMiembro(m2);
        pelicula.addMiembro(m3);
        pelicula.addMiembro(m4);
        pelicula.addMiembro(m5);
        pelicula.addMiembro(m6);
        pelicula.addMiembro(m7);
    }
    @org.junit.jupiter.api.Test
    void testBasic(){
        Pelicula p = new Pelicula("Alcarras", 300000);
        assertEquals("The total payroll for Alcarras is 0.0 euro",p.printSalaries());
        int beforeAdd = p.getlArtistico().size() + p.getlTecnico().size();
        p.addMiembro(new Miembro("Andrea", "Koch","32716631M", "652621780","Senegal",Profesion.GUIONISTA,300,10,true));
        //COMPROBAMOS QUE AÑADE
        assertNotEquals(beforeAdd, p.getlArtistico().size()+p.getlTecnico().size());
        setUp();
        var artistico = pelicula.getlArtistico();
        var tecnico = pelicula.getlTecnico();
        assertThrows(IllegalArgumentException.class, () -> new Pelicula("Alcarras",300000,artistico,artistico));
        assertThrows(IllegalArgumentException.class, () -> new Pelicula("Alcarras",300000,tecnico,tecnico));


    }
    @org.junit.jupiter.api.Test
    void addMiembro() {

        Pelicula p = new Pelicula("Alcarras", 300000);
        int beforeAdd = p.getlArtistico().size() + p.getlTecnico().size();
        p.addMiembro(new Miembro("Andrea", "Koch","32716631M", "652621780","Senegal",Profesion.GUIONISTA,300,10,true));
        //COMPROBAMOS QUE AÑADE
        assertNotEquals(beforeAdd, p.getlArtistico().size()+p.getlTecnico().size());
    }

    @org.junit.jupiter.api.Test
    void getRoyaltie() {
    }

    @org.junit.jupiter.api.Test
    void printSalaries() {
        setUp();
        String resultado = "Josep Abad ( Stunt performer with extra for danger ): 5000.0 euro\n"+
        "Ainet Jounou ( Actor protagonist ): 54000.0 euro\n"+
        "Xenia Roset ( Actor secondary ): 10000.0 euro\n"+
        "Cristina Puig ( Dubber ): 400.0 euro\n"+
        "Carla Simon ( Director , 7 years of experience ): 57000.0 euro\n"+
        "Maria Zamora ( Producer ): 9000.0 euro\n"+
        "Andrea Koch ( Musician ): 12000.0 euro\n"+
        "Arnau Vilaro ( Screenwriter , original screenplay ): 25000.0 euro\n"+
        "The total payroll for Alcarras is 172400.0 euro";
        assertEquals(resultado, pelicula.printSalaries());

    }

    @org.junit.jupiter.api.Test
    void printRoyalties() {
        setUp();
        String resultado =
                "Carla Simon ( Director ): 15000.0 euro\n"+
        "Maria Zamora ( Producer ): 6000.0 euro\n"+
        "Andrea Koch ( Musician ): 12000.0 euro\n"+
        "Arnau Vilaro ( Screenwriter ): 15000.0 euro\n";
        assertEquals(resultado, pelicula.printRoyalties());
    }
}