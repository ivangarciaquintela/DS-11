package e3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
import java.nio.charset.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    final private LoginScreen loginScreen = new LoginScreen();
    final private StrategyEmail strategyEmail= new StrategyEmail();
    final private StrategyPhoneNumber strategyPhoneNumber= new StrategyPhoneNumber();
    final private StrategyDNI strategyDNI= new StrategyDNI();
    final private MfaAlphanumericCode mfaAlphanumericCode = new MfaAlphanumericCode();
    final private MfaSMS mfaSMS = new MfaSMS();
    final private MfaWebCode mfaWebCode = new MfaWebCode();
    Map<String, String> mapEmail;
    Map<String, String> mapPhone;
    Map<String, String> mapDNI;


    @BeforeEach
    public void setUp(){
        mapEmail = new HashMap<>();
        mapPhone = new HashMap<>();
        mapDNI = new HashMap<>();
        mapEmail.put("1234malaga", "pepegarcia@gmail.com");
        mapPhone.put("666Coffee","622872364");
        mapDNI.put("Panoram4","32725341K");
        strategyEmail.map = mapEmail;
        strategyPhoneNumber.map = mapPhone;
        strategyDNI.map = mapDNI;
    }

    @Test
    public void validateCorrectIdTest(){
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "pepegarcia@gmail.com";
        assertTrue(loginScreen.loginStrategy.validateId(id));
        loginScreen.setLoginStrategy(strategyPhoneNumber);
        id = "622872364";
        assertTrue(loginScreen.loginStrategy.validateId(id));
        loginScreen.setLoginStrategy(strategyDNI);
        id = "32725341K";
        assertTrue(loginScreen.loginStrategy.validateId(id));
    }

    @Test
    public void validateIncorrectIdTest(){
        //Email Login
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "ManuelLopez";
        assertFalse(loginScreen.loginStrategy.validateId(id));
        //Phone Number Login
        loginScreen.setLoginStrategy(strategyPhoneNumber);
        id = "622a72364";
        assertFalse(loginScreen.loginStrategy.validateId(id));
        id = "622";
        assertFalse(loginScreen.loginStrategy.validateId(id));
        //DNI Login
        loginScreen.setLoginStrategy(strategyDNI);
        id = "3272534K1";
        assertFalse(loginScreen.loginStrategy.validateId(id));
        id = "327253412";
        assertFalse(loginScreen.loginStrategy.validateId(id));
        id = "3272";
        assertFalse(loginScreen.loginStrategy.validateId(id));

    }

    @Test
    public void authenticateCorrectPasswordTest(){
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "pepegarcia@gmail.com";
        String password = "1234malaga";
        assertTrue(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyPhoneNumber);
        id = "622872364";
        password = "666Coffee";
        assertTrue(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyDNI);
        id = "32725341K";
        password = "Panoram4";
        assertTrue(loginScreen.loginStrategy.authenticatePassword(id, password));
    }

    @Test
    public void authenticateIncorrectPasswordTest(){
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "pepegarcia@gmail.com";
        String password = "nomeacuerdo";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyPhoneNumber);
        id = "622872364";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyDNI);
        id = "32725341K";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
    }

    @Test
    public void authenticateNonExistentIdTest(){
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "josefernandez@gmail.com";
        String password = "1234malaga";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyPhoneNumber);
        id = "644767915";
        password = "666Coffee";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
        loginScreen.setLoginStrategy(strategyDNI);
        id = "32735140B";
        password = "Panoram4";
        assertFalse(loginScreen.loginStrategy.authenticatePassword(id, password));
    }

    @Test
    public void mfaCorrectFormatTest(){
        loginScreen.setLoginStrategy(strategyEmail);
        String id = "pepegarcia@gmail.com";
        String password = "1234malaga";
        loginScreen.setMfaStrategy(mfaAlphanumericCode);
        String alphanumeric = loginScreen.MFA(id, password);
        assertEquals(10, alphanumeric.length());
        assertTrue(alphanumeric.chars().allMatch( Character::isLetterOrDigit ));
        loginScreen.setMfaStrategy(mfaSMS);
        String sms = loginScreen.MFA(id, password);
        assertEquals(6, sms.length());
        assertTrue(sms.chars().allMatch( Character::isDigit ));
        loginScreen.setMfaStrategy(mfaWebCode);
        String webCode = loginScreen.MFA(id, password);
        assertEquals(15, webCode.length());
        assertTrue(webCode.chars().allMatch( Character::isDigit ));

    }


}
