package bog;

/**
 * Created by zac on 02.04.17.
 */

public class UserModel {
    private String username;
    private String password;
    private String data;
    private String vector;

    public UserModel() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getData() {
        return data;
    }

    public String getVector() {
        return vector;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
