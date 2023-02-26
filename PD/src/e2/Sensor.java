package e2;

import java.util.ArrayList;
import java.util.Iterator;

public class Sensor implements Observable {
    private String nombre;
    private String ubicacion;
    private String parametro;
    private double valor;
    private ArrayList<Observer> alertas;

    public Sensor(String nombre, String ubicacion, String parametro) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.parametro = parametro;
        this.valor = 0.0;
        this.alertas = new ArrayList();
    }

    public String getParametro() {
        return this.parametro;
    }

    public double getValor() {
        return this.valor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setValor(double valor) {
        this.valor = valor;
        this.notifyObservers();
    }

    public void addObserver(Observer o) {
        this.alertas.add(o);
    }

    public void removeObserver(Observer o) {
        this.alertas.add(o);
    }

    public void notifyObservers() {
        for (Observer a:alertas) {
            a.update(this);
        }

    }
}