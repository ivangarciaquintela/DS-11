package e3;

import java.util.Map;

public class LoginScreen {
    public LoginStrategy loginStrategy;
    public MfaStrategy mfaStrategy;
    Map<String, String> map;
    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }
    public void setMfaStrategy(MfaStrategy mfaStrategy) {
        this.mfaStrategy = mfaStrategy;
    }
    public String MFA(String id, String password) {
        if ((loginStrategy.validateId(id))&&(loginStrategy.authenticatePassword(id, password))) {
            return mfaStrategy.generateCode();
        } else return null;
    }
}
