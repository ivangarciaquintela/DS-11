package e1;

public enum Profesion {
    GUIONISTA("Screenwriter",70),
    MUSICO("Musician",60),
    PRODUCTOR("Producer",90),
    DIRECTOR("Director",100),
    INTERPRETE("Actor",200),
    ESPECIALISTA("Stunt",40),
    DOBLADOR("Dubber",20);
    private final String nombre;
    private final double impHora;

    Profesion( String nombre , double impHora)
    {
        this.nombre = nombre;
        this.impHora = impHora;
    }

    public String getNombre() {
        return nombre;
    }

    public double getImpHora() {
        return impHora;
    }
}
