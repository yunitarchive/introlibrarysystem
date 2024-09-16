public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private boolean registered;
    private boolean loggedIn;

    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.registered = false;
        this.loggedIn = false;
    }

    public String getId() {
        return id;
    }

    public void register() {
        if (username == null || password == null) {
            System.out.println("Username and password must be set before registration.");
            return;
        }
        registered = true;
        System.out.println(name + " has been registered.");
    }

    public void login(String username, String password) throws Exception {
        if (!registered) {
            throw new Exception("User is not registered.");
        }
        if (!this.username.equals(username) || !this.password.equals(password)) {
            throw new Exception("Invalid username or password.");
        }
        loggedIn = true;
        System.out.println(name + " has logged in.");
    }

    public void logout() {
        loggedIn = false;
        System.out.println(name + " has logged out.");
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
