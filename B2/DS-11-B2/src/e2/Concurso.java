package e2;

public class Concurso {
    public String selectCandidates(TVRealityList lista, int k){

        var iterador = lista.iterator();
        while (iterador.hasNext()) {
            for (int i = 0; i < k-1 ; i++) {
                iterador.next();
            }
            var a = "";
            iterador.remove();
        }

        return iterador.next();
    }

}
