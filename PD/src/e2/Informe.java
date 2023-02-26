package e2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Informe implements Comparable<Informe> {
    private boolean roja;
    private String nombreSensor;
    private String nombreAlerta;
    private String ubicacionSensor;
    private String parametro;
    private double valor;
    private Date fecha;

    public Informe(Alerta a) {
        Sensor s = a.getSensor();
        this.roja = a.isRoja();
        this.nombreSensor = s.getNombre();
        this.ubicacionSensor = s.getUbicacion();
        this.nombreAlerta = a.getNombre();
        this.parametro = s.getParametro();
        this.valor = s.getValor();
        this.fecha = new Date();
    }

    public String getNombreSensor() {
        return this.nombreSensor;
    }

    public String getNombreAlerta() {
        return this.nombreAlerta;
    }

    public String getUbicacionSensor() {
        return this.ubicacionSensor;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean isRoja() {
        return this.roja;
    }

    public double getValor() {
        return this.valor;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public int compareTo(Informe i) {
        if (this.roja && !i.isRoja()) {
            return -1;
        } else {
            return !this.roja && i.isRoja() ? 1 : this.fecha.compareTo(i.getFecha());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Informe i = (Informe)obj;
            return i.getFecha().equals(fecha) && i.getValor() == valor && i.getNombreAlerta().equals(nombreAlerta);
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        res.append("* Alerta " + (this.isRoja() ? "ROJA" : "NARANJA") + " :\n");
        res.append(" " + getNombreSensor() + " , " + getUbicacionSensor() + "\n");
        res.append(" " + getNombreAlerta() + " : parámetro " + getParametro() + " , nivel " + getValor() + "\n");
        res.append(" " + format.format(this.fecha) + "\n");
        return res.toString();
    }
}