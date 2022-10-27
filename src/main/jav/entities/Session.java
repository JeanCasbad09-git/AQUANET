package entities;

public final class Session {
	private static Session session;

    private String loggedUser;

    private Session() {}

    public static Session getCurrentInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public void setLoggedUser(String username) {
        loggedUser = username;
    }

    public String getLoggedUser() {
        return loggedUser;
    }
}
