package e4;

public class Movie {
    /**
     * * Creates a new movie with the list of ratings empty .
     * * @param title Movie title .
     * */
    public Movie ( String title ) { /* ... */ }
    /**
     * Returns the movie title
     * @return the movie title .
     */
    public String getTitle () { return "";}
    /**
     * Inserts a new movieRating .
     * It is allowed to insert NOT_RATED .
     * @param movieRating MovieRating to be inserted .
     */
    public void insertRating ( MovieRating movieRating ) { /* ... */ }
    /**
     * Check if this movie has any rating .
     * @return true if and only if there is a valuation other than NOT_RATED .
     */
    private boolean isRated () { return true;}
    /**
     * Gets the highest rating for this movie .
     * @return maximum rating ; or NOT_RATED if there are no ratings .
     */
    public MovieRating maximumRating () { return MovieRating.MEDIOCRE;}
    /**
     * Calculate the numerical average rating of this movie .
     * NOT_RATED values are not considered .
     * @return Numerical average rating of this movie .
     * @throws java . util . N o S u c h E l e m e n t E x c e p t i o n if there are no valid ratings .
     */
    public double averageRating () { return 0.0;}
}
