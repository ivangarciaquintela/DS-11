package e3;


public interface LoginStrategy {
    boolean validateId ( String id );
    boolean authenticatePassword ( String id , String password );
}
