package e1;

public class Miembro extends Humano{

    private Profesion profesion;
    
    private double horas;
    private int anosExp;
    private boolean plus;

    public Miembro(String nombre, String apellido, String dni, String tlf, String nacionalidad, Profesion profesion, double horas, int anosExp, boolean plus) {
        super(nombre, apellido, dni, tlf, nacionalidad);
        this.profesion = profesion;
        this.horas = horas;
        this.anosExp = anosExp;
        this.plus = plus;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public double getHoras() {
        return horas;
    }

    public int getAnosExp() {
        return anosExp;
    }

    public boolean isPlus() {
        return plus;
    }


    public double getSalario() {
        double salBase = getProfesion().getImpHora()*getHoras();
        switch (this.profesion){
            case INTERPRETE :
                return (isPlus())?salBase*3:salBase;
            case ESPECIALISTA:
                return (isPlus())?salBase+1000:salBase;
            case GUIONISTA:
                return (isPlus())?salBase+4000:salBase;
            case DIRECTOR:
                return salBase+(getAnosExp()*1000);
            default:
                return salBase;
        }
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(this.getNombre()+" "+this.getApellido()+" ( "+getProfesion().getNombre());
        switch (getProfesion()){
            case ESPECIALISTA :
                r.append(plus?" performer with extra for danger":"");break;
            case INTERPRETE:
                r.append(plus?" protagonist":" secondary");break;
            case DIRECTOR:
                r.append((anosExp>0)?" , "+anosExp+" years of experience":"");break;
            case GUIONISTA:
                r.append(plus?" , original screenplay":"");break;
        }
        r.append( " )");
        return r.toString();
    }
}
