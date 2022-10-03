package e2;

public class SocialDistance {

    public static char [][] seatingPeople ( char [][] layout ) {
        int height = layout.length;
        int width = layout[0].length;
        //int x  = (int)Math.random() * height;
        //int y  = (int)Math.random() * width;
        for (int i = 0; i < layout.length ; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if(libre(layout,i,j))
                    layout[i][j] = '#';
            }
        }
        for (int i = 0; i < layout.length ; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if ((layout[i][j]=='A') && (sumalibres(layout, i, j)<4))
                    layout[i][j] = '#';
            }
        }

        /**comprobar si hay + de 4 sentados al rededor**/
        return layout;
    }


    private static int sumalibres(char [][] layout, int x, int y){
        return ((libreUP(layout, x, y))?1:0)+
                ((libreDOWN(layout, x, y))?1:0)+
                ((libreRIGHT(layout, x, y))?1:0)+
                ((libreLEFT(layout, x, y))?1:0)+
                ((libreLUP(layout, x, y))?1:0)+
                ((libreRUP(layout, x, y))?1:0)+
                ((libreRDOWN(layout, x, y))?1:0)+
                ((libreLDOWN(layout, x, y))?1:0);
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

    public static void main(String[] args) {
        /*char [][] l = new char[5][5];
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l[i].length; j++) {
                int d = (int)(Math.random()*5);
                if(d<1){
                    l[i][j]='.';
                }else{
                    l[i][j]='A';
                }
            }
        }*/
        char [][] l = { {'A','.','A','A','.'},
                        {'A','A','A','A','A'},
                        {'A','.','A','.','A'},
                        {'A','A','A','A','.'},
                        {'A','.','A','A','.'}};
        var b = l;
        seatingPeople(l);
        var a = "";
    }

}
