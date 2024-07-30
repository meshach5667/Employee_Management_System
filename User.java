public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private String employeeName; 

    public User(String username, String password, boolean isAdmin, String employeeName) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
