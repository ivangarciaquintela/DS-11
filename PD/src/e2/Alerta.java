package e2;

import java.util.ArrayList;

public class Alerta implements Observer, Observable {
    private Sensor sensor;
    private String nombre;
    private double min;
    private double max;
    private double minNaranja;
    private double maxNaranja;
    private boolean roja;
    private boolean naranja;
    private ArrayList<Observer> observers;

    public Alerta(String nombre, Sensor sensor, double min, double max, double minNaranja, double maxNaranja) {
        this.nombre = nombre;
        this.sensor = sensor;
        this.min = min;
        this.max = max;
        this.minNaranja = minNaranja;
        this.maxNaranja = maxNaranja;
        this.naranja = false;
        this.roja = false;
        this.observers = new ArrayList();
    }

    public String getNombre() {
        return this.nombre;
    }

    public Sensor getSensor() {
        return this.sensor;
    }

    public boolean isRoja() {
        return this.roja;
    }

    public boolean isNaranja() {
        return this.naranja;
    }

    public void update(Observable o) {
        Sensor s = (Sensor)o;
        double valor = s.getValor();
        if (!(valor < this.min) && !(valor > this.max)) {
            this.roja = false;
            this.naranja = false;
        } else if (!(valor < this.minNaranja) && !(valor > this.maxNaranja)) {
            this.naranja = true;
            this.roja = false;
            this.notifyObservers();
        } else {
            this.roja = true;
            this.naranja = false;
            this.notifyObservers();
        }

    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(this);
        }
    }
}