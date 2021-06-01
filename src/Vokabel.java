public class Vokabel {

    private String vokabel;
    private String antwort;

    public Vokabel(String vokabel, String antwort) {
        this.vokabel = vokabel;
        this.antwort = antwort;
    }

    public Vokabel(){
    }

    public String getVokabel() {
        return vokabel;
    }

    public void setVokabel(String vokabel) {
        this.vokabel = vokabel;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    @Override
    public String toString() {
        return this.vokabel + ";" + this.antwort;
    }
}
