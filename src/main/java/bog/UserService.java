package bog;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zac on 02.04.17.
 */

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Nullable
    public IdResponse registration(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.registration(user);
    }

    public boolean setData(UserModel user) {
        final String passwordHash = userDAO.getPassword(user.getUsername());
        if (passwordEncoder.matches(user.getPassword(), passwordHash)) {
            return userDAO.setData(user);
        }
        return false;
    }

    @Nullable
    public String getData(String username, String password) {
        final String passwordHash = userDAO.getPassword(username);
        if (passwordEncoder.matches(password, passwordHash)) {
            return userDAO.getData(username);
        }
        return null;
    }
}