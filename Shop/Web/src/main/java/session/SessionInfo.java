package session;

import model.User;

public class SessionInfo {
    private User user;
    private String locale;
   private boolean logged;


    public void logIn(User user) {
        user.setPassword("");
        user.setSalt("");
        this.user = user;
        this.logged = true;
    }

    public void logOut() {
        this.user = null;
        this.logged = false;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SessionInfo{");

        builder.append("user=");
        builder.append(getUser());
        builder.append(", ");

        builder.append("locale=");
      builder.append(getLocale());
        builder.append(", ");

        builder.append("}");
        return builder.toString();
    }
}
