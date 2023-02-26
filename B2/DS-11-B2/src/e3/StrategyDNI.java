package e3;

import java.util.Map;

public class StrategyDNI implements LoginStrategy{
    Map<String, String> map;
    @Override
    public boolean validateId(String id) {
        try {
            if (id.length()==9){
                String numbers =id.substring(0,8);
                int Value = Integer.parseInt(numbers);
                if ((Character.isLetter(id.charAt(8)))){
                    return true;
                } else return false;

            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
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
