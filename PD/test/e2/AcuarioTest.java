package e2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AcuarioTest {
    private Acuario acuario;

    @BeforeEach

    void setUp() {
        Tanque t1 = new Tanque("Focas");
        Sensor s1 = new Sensor("Piscina de las focas", "Exterior", "Oxígeno");
        Alerta a1 = new Alerta("Control de oxígeno focas ", s1, 0.0, 2000.0, -500.0, 5000.0);
        Personal p1 = new Personal("Juan");
        Personal p2 = new Personal("Manolo");
        Personal p3 = new Personal("Pablo");
        //AÑADIMOS VARIOS PERSONALES PARA COMPROBAR QUE NO REPETIMOS ALARMAS AL SACAR TODAS LAS DEL TANQUE
        a1.addObserver(p1);
        a1.addObserver(p2);
        a1.addObserver(p3);
        s1.addObserver(a1);
        t1.addSensor(s1);
        t1.addPersonal(p1);
        t1.addPersonal(p2);
        t1.addPersonal(p3);
        Dispositivo d1 = new Dispositivo("Led roja",-500,"Oxígeno",Condicion.MENOR);
        Dispositivo d2 = new Dispositivo("Led amarilla",0,"Oxígeno",Condicion.MAYOR);
        a1.addObserver(d1);
        a1.addObserver(d2);
        this.acuario = new Acuario("Aquarium Finisterrae Coruña");
        this.acuario.addTanque(t1);
        this.acuario.addDispositivo(d1);
        this.acuario.addDispositivo(d2);
        s1.setValor(-1000.0);
        s1.setValor(4000.0);
        s1.setValor(3000.0);
    }
    @Test
    void testBasic() {
        this.setUp();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String fecha = format.format(new Date());
        String resultadoBoletin = "Alertas de Mantenimiento Focas\nAlertas ROJAS :\n* Alerta ROJA :\n Piscina de las focas , Exterior\n Control de oxígeno focas  : parámetro Oxígeno , nivel -1000.0\n " + fecha + "\nAlertas NARANJAS :\n* Alerta NARANJA :\n Piscina de las focas , Exterior\n Control de oxígeno focas  : parámetro Oxígeno , nivel 4000.0\n " + fecha + "\n* Alerta NARANJA :\n Piscina de las focas , Exterior\n Control de oxígeno focas  : parámetro Oxígeno , nivel 3000.0\n " + fecha + "\n";
        Assertions.assertEquals(resultadoBoletin, this.acuario.getTanque("Focas").mostarInformes());

        Tanque t2 = new Tanque("Tiburones");
        //Comprobamos que añade tanques
        acuario.addTanque(t2);
        assertEquals(t2,acuario.getTanque("Tiburones"));
        //Que no permite guardar tanques repetidos
        assertThrows(IllegalArgumentException.class,()->acuario.addTanque(t2));
        //Comprobamos la funcionalidad del remove
        acuario.removeTanque(t2);
        assertEquals(null,acuario.getTanque("Tiburones"));
        //Tenemos un nuevo personal, nuevo sensor para el tanque de focas y sus 2 correspondientes alarmas.
        Personal p1 = new Personal("Roberto");
        Sensor s1 = new Sensor("Piscina de las focas", "Exterior", "temperatura");
        Alerta a1 = new Alerta("Control de temperatura para focas ", s1, 15, 30, 10, 50);
        Alerta a2 = new Alerta("Control de temperatura para nadadores con focas ", s1, 20, 30, 15, 50);
        //Roberto quiere suscribirse a las alertas de temperatura del tanque de focas porque el nada con ellas para limpiar el tanque
        a1.addObserver(p1);
        a2.addObserver(p1);
        //Añadimos las alarmas a la lista del sensor
        s1.addObserver(a1);
        s1.addObserver(a2);
        //Por ultimo añadimos el sensor al tanque
        acuario.getTanque("Focas").addSensor(s1);
        acuario.getTanque("Focas").addPersonal(p1);
        //Comprobamos que no ha cambiado el informe
        Assertions.assertEquals(resultadoBoletin, acuario.getTanque("Focas").mostarInformes());
        //Le damos valor al sensor de temperatura de 12 para que nos salte alerta A1 Naranja y A2 Roja
        s1.setValor(12);
        String fecha2 = format.format(new Date());
        String resultado = "Alertas de Mantenimiento Focas\n" +
                "Alertas ROJAS :\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel -1000.0\n" +
                " "+fecha+"\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para nadadores con focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel 3000.0\n" +
                " "+fecha+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel 4000.0\n" +
                " "+fecha+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n";
        assertEquals(resultado, acuario.getTanque("Focas").mostarInformes());
        //Comporbamos tambien que solo saque las alertas a la que está suscrito nuestro personal Roberto.
        resultado = "Alertas de Mantenimiento del Personal : Roberto\n" +
                "Alertas ROJAS :\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para nadadores con focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n";
        assertEquals(resultado,p1.mostrarInformes());
        //Al tanque anterior creado: Tiburones vamos a añadirle un personal y un sensor similar al anterior
        Personal p2 = new Personal("Amador");
        Sensor s2 = new Sensor("Piscina de las tiburones", "Exterior", "temperatura");
        Alerta a21 = new Alerta("Control de temperatura para tiburones ", s2, 15, 30, 10, 50);
        Alerta a22 = new Alerta("Control de temperatura para nadadores con tiburones ", s2, 20, 30, 15, 50);
        //Realizamos el mismo proceso que con el anterior personal
        a21.addObserver(p2);
        a22.addObserver(p2);
        //Además Amador quiere estar pendiente de la alerta de nadador con focas por si necesita suplir la baja de Roberto.
        a2.addObserver(p2);
        //Roberto por otra parte tambien quiere estar alerta por si necesita suplir la baja de Amador.
        a22.addObserver(p1);
        //Añadimos las alarmas a la lista del sensor
        s2.addObserver(a21);
        s2.addObserver(a22);
        //Por ultimo añadimos el sensor al tanque
        acuario.addTanque(t2);
        acuario.getTanque("Tiburones").addSensor(s1);
        acuario.getTanque("Tiburones").addPersonal(p1);
        s2.setValor(35);
        String fecha3 = format.format(new Date());
        resultado = "REVISION ALERTAS TANQUES DE AQUARIUM FINISTERRAE CORUÑA :\n" +
                "\n" +
                "Alertas de Mantenimiento Focas\n" +
                "Alertas ROJAS :\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel -1000.0\n" +
                " "+fecha+"\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para nadadores con focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel 3000.0\n" +
                " "+fecha+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de oxígeno focas  : parámetro Oxígeno , nivel 4000.0\n" +
                " "+fecha+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n" +
                "\n" +
                "Alertas de Mantenimiento Tiburones\n" +
                "Alertas ROJAS :\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para nadadores con focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha3+"\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha3+"\n";
        assertEquals(resultado, acuario.mostrarInformesAcuario());
        //Comprobamos que un personal recibe alerta de un tanque en el que no está pero si que está suscrito a la alerta.
        //Como la alerta fue previa a su suscripcion no le sale la alerta
        resultado = "Alertas de Mantenimiento del Personal : "+p2.getNombre()+"\n" +
                "Alertas ROJAS :\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las tiburones , Exterior\n" +
                " Control de temperatura para tiburones  : parámetro temperatura , nivel 35.0\n" +
                " "+fecha3+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las tiburones , Exterior\n" +
                " Control de temperatura para nadadores con tiburones  : parámetro temperatura , nivel 35.0\n" +
                " "+fecha3+"\n";
        assertEquals(resultado,p2.mostrarInformes());
        //Pero Roberto si que debe de tener ambas alertas
        resultado="Alertas de Mantenimiento del Personal : "+p1.getNombre()+"\n" +
                "Alertas ROJAS :\n" +
                "* Alerta ROJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para nadadores con focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha2+"\n" +
                "Alertas NARANJAS :\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las focas , Exterior\n" +
                " Control de temperatura para focas  : parámetro temperatura , nivel 12.0\n" +
                " "+fecha3+"\n" +
                "* Alerta NARANJA :\n" +
                " Piscina de las tiburones , Exterior\n" +
                " Control de temperatura para nadadores con tiburones  : parámetro temperatura , nivel 35.0\n" +
                " "+fecha3+"\n";
        assertEquals(resultado,p1.mostrarInformes());
        resultado ="REVISION DISPOSITIVOS DE TANQUES DE AQUARIUM FINISTERRAE CORUÑA :\n" +
                "- LED AMARILLA : Dispositivo actuando en Piscina de las focas , Exterior\n";
        assertEquals(resultado,acuario.mostrarDispositivos());
    }
}