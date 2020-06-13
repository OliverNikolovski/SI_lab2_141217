import java.util.List;

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {
    public boolean function (User user, List<String> allUsers) { // 1
        if (user!=null) { // 1
            if (user.getUsername()!=null && user.getEmail()!=null && !allUsers.contains(user.getUsername())) { // 2
                boolean atChar = false, dotChar = false; // 4
                for (int i=0;i<user.getEmail().length();i++) { // 5
                    if (user.getEmail().charAt(i)=='@') // 6
                        atChar = true; // 7
                    if (atChar && user.getEmail().charAt(i)=='.') { // 8
                        dotChar = true; // 9
                    }
                }
                if (atChar && dotChar) { // 10
                    return true; // 11
                }
            }
        }
        return false; // 3
    } // 12
}
