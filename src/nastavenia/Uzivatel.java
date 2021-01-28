package nastavenia;

public class Uzivatel {
    private String meno;
    private String priezvisko;
    private String pohlavie;
    private int vek;
    private double hmotnost;


    public Uzivatel(String Meno, String Priezvisko, int Vek, double Hmotnost, String Pohlavie){
        this.meno = Meno;
        this.priezvisko = Priezvisko;
        this.vek = Vek;
        this.hmotnost = Hmotnost;
        this.pohlavie = Pohlavie;
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
