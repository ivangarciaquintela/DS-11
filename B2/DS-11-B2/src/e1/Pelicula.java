package e1;

import java.util.ArrayList;

public class Pelicula {
    private String titulo;
    private double recaudacion;
    private ArrayList<Miembro> lTecnico;
    private ArrayList<Miembro> lArtistico;

    public Pelicula(String titulo, double recaudacion, ArrayList<Miembro> lTecnico, ArrayList<Miembro> lArtistico) {
       for (Miembro m: lTecnico) {
            if(!isTecnico(m))
                throw new IllegalArgumentException("Parametros Incorrectos");
        }
        for (Miembro m: lArtistico) {
            if(!isArtistico(m))
                throw new IllegalArgumentException("Parametros Incorrectos");
        }
        this.titulo = titulo;
        this.recaudacion = recaudacion;
        this.lTecnico = lTecnico;
        this.lArtistico = lArtistico;

    }
    public Pelicula(String titulo, double recaudacion) {
        this.titulo = titulo;
        this.recaudacion = recaudacion;
        this.lArtistico = new ArrayList<Miembro>();
        this.lTecnico = new ArrayList<Miembro>();
    }
    public boolean isTecnico(Miembro m){
        switch (m.getProfesion()){
            case PRODUCTOR : case MUSICO: case DIRECTOR: case GUIONISTA: return true;
            default: return false;
        }
    }

    public boolean isArtistico(Miembro m){
        switch (m.getProfesion()){
            case INTERPRETE: case DOBLADOR:case ESPECIALISTA: return true;
            default: return false;
        }
    }
    public boolean addMiembro(Miembro m){
        if(isTecnico(m))
            return lTecnico.add(m);
        if(isArtistico(m))
            return lArtistico.add(m);

        throw new IllegalArgumentException("El objeto introducido es incorrecto");
    }

    public String getTitulo() {
        return titulo;
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    public ArrayList<Miembro> getlTecnico() {
        return lTecnico;
    }

    public ArrayList<Miembro> getlArtistico() {
        return lArtistico;
    }

    public double getRoyaltie(Miembro m){
        switch (m.getProfesion()){
            case GUIONISTA : case DIRECTOR:
                return getRecaudacion()*0.05;
            case MUSICO:
                return  getRecaudacion()*0.04;
            case PRODUCTOR:
                return  getRecaudacion()*0.02;
            default:
                return -1;
        }
    }

    public String printSalaries(){
        double sal = 0;
        StringBuilder resultado = new StringBuilder();
        ArrayList<Miembro> lista = new ArrayList<>();
        lista.addAll(lArtistico);
        lista.addAll(lTecnico);
        for (Miembro m: lista) {
            double msal = m.getSalario();
            sal +=msal;
            resultado.append(m.toString()+": "+msal+" euro");
            resultado.append("\n");
        }
        resultado.append("The total payroll for "+getTitulo()+" is "+sal+" euro");
        return resultado.toString();
    }

    public String printRoyalties(){
        StringBuilder resultado = new StringBuilder();
        for (Miembro m : lTecnico) {
            double roy = getRoyaltie(m);
            if(roy>=0){
                resultado.append(m.getNombre()+" "+m.getApellido()+" ( "+m.getProfesion().getNombre()+" ): "+roy+" euro");
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }


}
