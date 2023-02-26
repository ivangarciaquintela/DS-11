package e1;

public class Humano {
    private String nombre;
    private String apellido;
    private String dni;
    private String tlf;
    private String nacionalidad;

    public Humano(String nombre, String apellido, String dni, String tlf, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.tlf = tlf;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getTlf() {
        return tlf;
    }


    public String getNacionalidad() {
        return nacionalidad;
    }
}
