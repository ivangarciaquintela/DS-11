package e3;

import java.util.Map;


public class StrategyEmail implements LoginStrategy {
    Map<String, String> map;
    @Override
    public boolean validateId(String id) {
        if ((id.contains("@"))&&((id.endsWith(".com"))||(id.endsWith(".es")))){
            return true;
        } else return false;

    }

    @Override
    public boolean authenticatePassword(String id, String password) {

        if (validateId(id)){
            if (map.containsKey(password)) {
                return this.map.get(password).equals(id);
            } return false;
        } else return false;
    }


}
