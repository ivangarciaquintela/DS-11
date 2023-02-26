package e4;

public enum MovieRating {
    NOT_RATED(-1),AWFUL(0),BAD(2),MEDIOCRE(4),GOOD(6),EXCELLENT(8),MASTERPIECE(10);

    private final int valor;
    MovieRating(int valor){
        this.valor= valor;
    }

    public int getValor(){
        return this.valor;
    }
}
