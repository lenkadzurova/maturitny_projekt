package nastavenia;

public class Uzivatel {
    private String meno;
    private String priezvisko;
    private String pohlavie;
    private int vek;
    private double hmotnost;


    public Uzivatel(String jehoMeno, String jehoPriezvisko, int jehoVek, double jehoHmotnost){
        this.meno = jehoMeno;
        this.priezvisko = jehoPriezvisko;
        this.vek = jehoVek;
        this.hmotnost = jehoHmotnost;
        pohlavie = "muž";
    }

    public Uzivatel(String jejMeno, String jejPriezvisko, double jejHmotnost, int jejVek){
        this.meno = jejMeno;
        this.priezvisko = jejPriezvisko;
        this.vek = jejVek;
        this.hmotnost = jejHmotnost;
        pohlavie = "žena";
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko(){
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko){
        this.priezvisko = priezvisko;
    }


    public String getPohlavie() {
        return pohlavie;
    }

    public void setPohlavie(String pohlavie) {
        this.pohlavie = pohlavie;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public double getHmotnost(){
        return hmotnost;
    }

    public void setHmotnost(){
        this.hmotnost = hmotnost;
    }


}
