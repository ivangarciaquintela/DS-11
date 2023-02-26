package e2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Tanque {
    private String nombre;
    private ArrayList<Sensor> sensores;
    private ArrayList<Personal> personal;

    public Tanque(String nombre) {
        this.nombre = nombre;
        this.sensores = new ArrayList();
        this.personal = new ArrayList();
    }

    public void addSensor(Sensor s) {
        this.sensores.add(s);
    }

    public void removeSensor(Sensor s) {
        this.sensores.remove(s);
    }

    public boolean hasSensores() {
        return !this.sensores.isEmpty();
    }

    public void addPersonal(Personal p) {
        this.personal.add(p);
    }

    public void removePersonal(Personal p) {
        this.personal.remove(p);
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Tanque t = (Tanque)obj;
            return t.nombre.equals(this.nombre);
        }
    }

    private PriorityQueue<Informe> informesTanque() {
        PriorityQueue<Informe> aux = new PriorityQueue();
        for (Personal p : personal) {
            for (Informe i: p.getInformes()) {
                if (sensores.stream().anyMatch(x -> x.getNombre().equals(i.getNombreSensor())) && !aux.contains(i))
                    aux.add(i);
            }
        }
        return aux;
    }

    public String mostarInformes() {
        StringBuilder res = new StringBuilder("Alertas de Mantenimiento " + this.nombre + "\n");
        PriorityQueue<Informe> aux = informesTanque();
        res.append("Alertas ROJAS :\n");
        for (Informe i : aux) {
            if(i.isRoja())
                res.append(i.toString());
        }
        res.append("Alertas NARANJAS :\n");
        for (Informe i : aux) {
            if(!i.isRoja())
                res.append(i.toString());
        }
        return res.toString();
    }
}
