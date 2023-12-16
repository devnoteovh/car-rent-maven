package ovh.devnote.rent.car.authenticate;

import ovh.devnote.rent.car.db.UserRepository;
import ovh.devnote.rent.car.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class Authenticator {
    public Authenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    private final String seed = "v_#(jxXlmQ+Eh&[k[^Xtu{26=;GT_cW${;KhjVQ.";
    public static String loggedUserRole;
    public static String loggedUserLogin;

    public boolean authenticate(String login, String password) {
        User user = this.userRepository.findByLogin(login);
        if(user != null &&
                DigestUtils.md5Hex(password+seed).equals(user.getPassword())) {
            loggedUserRole = user.getRole();
            loggedUserLogin = user.getLogin();
            return true;
        }
        return false;
    }

    public String hashing(String password){
        return DigestUtils.md5Hex(password+seed);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
