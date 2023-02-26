package e3;
import java.nio.charset.*;
import java.util.*;

public class MfaWebCode implements MfaStrategy{

    @Override
    public String generateCode() {
        int length = 15;
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuilder buffer;
        String webCode;

        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, StandardCharsets.UTF_8);

        buffer = new StringBuilder();

        //remove all spacial char 
        webCode
                = mystring
                .replaceAll("[^0-9]", "");

        //random selection
        for (int m = 0; m < webCode.length(); m++) {

            if (Character.isDigit(webCode.charAt(m))
                    && (length > 0)) {

                buffer.append(webCode.charAt(m));
                length--;
            }
        }

        // the resulting string 
        return buffer.toString();
    }
}
