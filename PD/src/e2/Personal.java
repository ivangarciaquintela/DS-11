
package e2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Personal implements Observer {
    private String nombre;
    private PriorityQueue<Informe> informes;

    public Personal(String nombre) {
        this.nombre = nombre;
        this.informes = new PriorityQueue();
    }

    public String getNombre() {
        return this.nombre;
    }

    public PriorityQueue<Informe> getInformes() {
        return new PriorityQueue(this.informes);
    }

    public String mostrarInformes() {
        StringBuilder res = new StringBuilder("Alertas de Mantenimiento del Personal : " + this.nombre + "\n");
        res.append("Alertas ROJAS :\n");
        for (Informe i : informes) {
            if(i.isRoja())
                res.append(i.toString());
        }
        res.append("Alertas NARANJAS :\n");
        for (Informe i : informes) {
            if(!i.isRoja())
                res.append(i.toString());
        }
        return res.toString();
    }

    public void update(Observable o) {
        Alerta a = (Alerta)o;
        Informe i = new Informe(a);
        this.informes.add(i);
    }
}
