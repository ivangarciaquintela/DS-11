package e4;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Movie {
    private String title;
    private ArrayList<MovieRating> ratings;
    /**
     * * Creates a new movie with the list of ratings empty .
     * * @param title Movie title .
     * */
    public Movie ( String title ) {
        this.title =title;
        this.ratings = new ArrayList<>();
    }
    /**
     * Returns the movie title
     * @return the movie title .
     */
    public String getTitle () {
        return this.title;
    }
    /**
     * Inserts a new movieRating .
     * It is allowed to insert NOT_RATED .
     * @param movieRating MovieRating to be inserted .
     */
    public void insertRating ( MovieRating movieRating ) {
        this.ratings.add(movieRating);
    }
    /**
     * Check if this movie has any rating .
     * @return true if and only if there is a valuation other than NOT_RATED .
     */
    private boolean isRated () {
        for(MovieRating r : ratings){
            if(r!=MovieRating.NOT_RATED)
                return true;
        }
        return false;
    }
    /**
     * Gets the highest rating for this movie .
     * @return maximum rating ; or NOT_RATED if there are no ratings .
     */
    public MovieRating maximumRating () {
        MovieRating max = MovieRating.NOT_RATED;
        for (MovieRating r:ratings) {
            if(r.getValor()>max.getValor())
                max= r;
        }
        return max;
    }
    /**
     * Calculate the numerical average rating of this movie .
     * NOT_RATED values are not considered .
     * @return Numerical average rating of this movie .
     * @throws java . util . N o S u c h E l e m e n t E x c e p t i o n if there are no valid ratings .
     */
    public double averageRating () {
        float avg = 0f;
        int i = 0;
        for (MovieRating r:ratings) {
            if(r.getValor()>MovieRating.NOT_RATED.getValor()) {
                avg += r.getValor();
                i++;
            }
        }
        if(i>0){
            return avg/i;
        }
        else
            throw new NoSuchElementException("No hay suficiente valoraciones.");
    }
}
