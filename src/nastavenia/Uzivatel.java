package nastavenia;

import connection.DatabaseCon;

public class Uzivatel {
    private String meno;
    private String priezvisko;
    private String pohlavie;
    private int vek;
    private double hmotnost;

    public Uzivatel(){

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

    public void setVek(Integer vek) {
        this.vek = vek;
    }

    public double getHmotnost(){
        return hmotnost;
    }

    public void setHmotnost(Double hmotnost){
        this.hmotnost = hmotnost;
    }

    private static Uzivatel uzivatel = new Uzivatel();

    public static Uzivatel getInstance() {


        if (uzivatel == null) {
            return new Uzivatel();

        } else {
            return uzivatel;
        }
    }
}
