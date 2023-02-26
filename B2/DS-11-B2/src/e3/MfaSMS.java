package e3;

import java.security.SecureRandom;

public class MfaSMS implements MfaStrategy{
    @Override
    public String generateCode() {
        int length = 6;
        String NUMBER = "0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // 0-62 (exclusivo), retorno aleatorio 0-61
            int rndCharAt = random.nextInt(NUMBER.length());
            char rndChar = NUMBER.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }
}
