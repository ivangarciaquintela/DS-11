
package e2;

import java.io.PrintStream;

public class Dispositivo implements Observer {
    private String nombre;
    private double valor;
    private String parametro;
    private String ubicacion;
    private Condicion condicion;
    private boolean activo;
    public Dispositivo(String nombre,double valor, String parametro, Condicion condicion) {
        this.valor = valor;
        this.nombre = nombre;
        this.parametro = parametro;
        this.condicion = condicion;
    }
    public String getNombre() {
        return nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "- "+nombre.toUpperCase()+" : Dispositivo actuando en "+ubicacion;
    }

    public void update(Observable o) {
        Alerta a = (Alerta)o;
        Sensor s = a.getSensor();
        ubicacion = s.getNombre()+" , "+s.getUbicacion();
        if(s.getParametro().equals(parametro)){
            switch (condicion){
                case MENOR -> activo = (s.getValor()<valor);
                case MAYOR -> activo = (s.getValor()>valor);
                case IGUALMAYOR -> activo = (s.getValor()>=valor);
                case IGUALMENOR -> activo = (s.getValor()<=valor);
            }

        }
        System.out.println(toString());
    }
}