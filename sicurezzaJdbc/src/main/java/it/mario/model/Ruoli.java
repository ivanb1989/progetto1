package it.mario.model;


public class Ruoli {

    private final String prefisso = "ROLE_";
    private String user;
    private String ruolo;

    public Ruoli(String user, String ruolo) {
        this.user = user;
        this.ruolo =prefisso+ruolo;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = prefisso+ruolo.toUpperCase();
    }

    public String getUser() {
        return user;
    }

    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String toString() {
        return "Ruoli{" +
                "prefisso='" + prefisso + '\'' +
                ", user='" + user + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}
