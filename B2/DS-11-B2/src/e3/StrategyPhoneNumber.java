package e3;

import java.util.Map;


public class StrategyPhoneNumber implements LoginStrategy{
    Map<String, String> map;
    @Override
    public boolean validateId(String id) {
        if(id.length()==9) {
            try {
                int Value = Integer.parseInt(id);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
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
