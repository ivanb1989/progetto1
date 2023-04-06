package it.mario.model;

public class Impiegati {
    private int id;
    private String cognome;
    private int eta;
    private int stipendio;

    public Impiegati(int id, String cognome, int eta, int stipendio) {
        this.id = id;
        this.cognome = cognome;
        this.eta = eta;
        this.stipendio = stipendio;
    }

    public Impiegati() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getStipendio() {
        return stipendio;
    }

    public void setStipendio(int stipendio) {
        this.stipendio = stipendio;
    }

    //------------------------------------------//


    @Override
    public String toString() {
        return "Impiegato[" +
                "id=" + id +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", stipendio=" + stipendio +
                ']';
    }
}
