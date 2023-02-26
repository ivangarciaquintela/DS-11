package e2;

import com.sun.jdi.event.ClassUnloadEvent;

import java.util.Arrays;

public class SocialDistance {

    public static char [][] seatingPeople ( char [][] layout ) {
        try{
            esCorrecta(layout);
            while(haySitio(layout)){
                layout = sentarse(layout);
                layout = levantarse(layout);
            }
            return layout;
        }catch (Exception E){
            throw new IllegalArgumentException("Datos incorrectos");
        }

    }
    private static char[][] sentarse(char[][] layout){
        char[][] aux = new char[layout.length][layout[0].length];
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if(layout[i][j]=='A'&&libre(layout,i,j))
                    aux[i][j] = '#';
                else
                    aux[i][j]=layout[i][j];
            }
        }
        return aux;
    }

    private static char[][] levantarse(char[][] layout){
        char[][] aux = new char[layout.length][layout[0].length];
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if(layout[i][j]=='#'&& sumalibres(layout,i,j)>=4)
                    aux[i][j] = 'A';
                else
                    aux[i][j]=layout[i][j];
            }
        }
        return aux;
    }
    private static boolean haySitio(char [][] layout){
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if((layout[i][j]=='A' && libre(layout,i,j))
                    ||(layout[i][j]=='#' && (sumalibres(layout,i,j)>4)))
                    return true;
            }
        }
        return false;
    }
    private static void esCorrecta(char[][] layout){
        int w = layout[0].length;
        for (int i = 0; i < layout.length; i++) {
            if((layout[i].length != w))
                throw new IllegalArgumentException("Parametros Incorrectos");
            for (int j = 0; j < layout[i].length; j++) {
                if(layout[i][j]!='A'&&layout[i][j]!='#'&&layout[i][j]!='.')
                    throw new IllegalArgumentException("Parametros Incorrectos");
            }
        }
    }
    private static int sumalibres(char [][] layout, int x, int y){
        return ((!libreUP(layout, x, y))?1:0)+
                ((!libreDOWN(layout, x, y))?1:0)+
                ((!libreRIGHT(layout, x, y))?1:0)+
                ((!libreLEFT(layout, x, y))?1:0)+
                ((!libreLUP(layout, x, y))?1:0)+
                ((!libreRUP(layout, x, y))?1:0)+
                ((!libreRDOWN(layout, x, y))?1:0)+
                ((!libreLDOWN(layout, x, y))?1:0);
    }

    private static boolean libre(char [][] layout, int x, int y){
        return (layout[x][y]=='A') &&
                libreUP(layout, x, y) &&
                libreDOWN(layout, x, y) &&
                libreRIGHT(layout, x, y) &&
                libreLEFT(layout, x, y) &&
                libreLUP(layout, x, y) &&
                libreRUP(layout, x, y) &&
                libreRDOWN(layout, x, y) &&
                libreLDOWN(layout, x, y);
    }
    private static boolean libreUP(char [][] layout, int x, int y) {
        return (y>0)?layout[x][y-1]!='#':true;
    }
    private static boolean libreDOWN(char [][] layout, int x, int y) {
        return (y<(layout[0].length-1))?layout[x][y+1]!='#':true;
    }
    private static boolean libreRIGHT(char [][] layout, int x, int y) {
        return (x<layout.length-1)?layout[x+1][y]!='#':true;
    }
    private static boolean libreLEFT(char [][] layout, int x, int y) {
        return (x>0)?layout[x-1][y]!='#':true;
    }

    private static boolean libreRUP(char [][] layout, int x, int y) {
        return (y>0&&x<layout.length-1)?layout[x+1][y-1]!='#':true;
    }
    private static boolean libreRDOWN(char [][] layout, int x, int y) {
        return (y<layout[0].length-1&&x<layout.length-1)?layout[x+1][y+1]!='#':true;
    }
    private static boolean libreLUP(char [][] layout, int x, int y) {
        return (x>0&&y>0)?layout[x-1][y-1]!='#':true;
    }
    private static boolean libreLDOWN(char [][] layout, int x, int y) {
        return (x>0&&y<layout[0].length-1)?layout[x-1][y+1]!='#':true;
    }


}
