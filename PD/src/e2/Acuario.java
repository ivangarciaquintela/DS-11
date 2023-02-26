package e2;

import java.util.ArrayList;
import java.util.Iterator;

public class Acuario {
    private String nombre;
    private ArrayList<Tanque> tanques;
    private ArrayList<Dispositivo> dispositivos;

    public Acuario(String nombre) {
        this.nombre = nombre;
        this.tanques = new ArrayList();
        this.dispositivos = new ArrayList();
    }

    public Tanque getTanque(String tanque) {
        for (Tanque t: tanques) {
         if(t.getNombre().equals(tanque))
             return t;
        }
        return null;
    }

    public void addTanque(Tanque t) {
        if (tanques.contains(t)) {
            throw new IllegalArgumentException("El acuario ya contiene este tanque.");
        }
        tanques.add(t);
    }
    public void addDispositivo(Dispositivo d) {
        if (dispositivos.contains(d)) {
            throw new IllegalArgumentException("El acuario ya contiene este tanque.");
        }
        dispositivos.add(d);
    }

    public void removeTanque(Tanque t) {
        if (this.tanques.isEmpty()) {
            throw new IllegalCallerException("No hay tanques registrados en el acuario.");
        } else if (!tanques.remove(t)) {
            throw new IllegalCallerException("No se encuentra dicho tanque en el acuario.");
        }
    }

    public String mostrarInformesAcuario() {
        StringBuilder res = new StringBuilder("REVISION ALERTAS TANQUES DE " + this.nombre.toUpperCase() + " :\n");
        for (Tanque t: tanques) {
            if (t.hasSensores()) {
                res.append("\n");
                res.append(t.mostarInformes());
            }
        }
        return res.toString();
    }

    public String mostrarDispositivos() {
        StringBuilder res = new StringBuilder("REVISION DISPOSITIVOS DE TANQUES DE " + this.nombre.toUpperCase() + " :\n");
        for (Dispositivo d: dispositivos) {
            if(d.isActivo())
                res.append(d.toString()+"\n");
        }
        return res.toString();
    }
}
