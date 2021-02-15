package hlavneMenu;

public class Mesto {
    String mesto;

    String najTeplota;

    String minTeplota;

    String oblacno;

    String datum;

    double pitnyRezim;

    public  Mesto(){

    }

    public Mesto(String mesto, String najTeplota, String minTeplota, String oblacno, String datum) {
        this.mesto = mesto;
        this.najTeplota = najTeplota;
        this.minTeplota = minTeplota;
        this.oblacno = oblacno;
        this.datum = datum;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getNajTeplota() {
        return najTeplota;
    }

    public void setNajTeplota(String najTeplota) {
        this.najTeplota = najTeplota;
    }

    public String getMinTeplota() {
        return minTeplota;
    }

    public void setMinTeplota(String minTeplota) {
        this.minTeplota = minTeplota;
    }

    public String getOblacno() {
        return oblacno;
    }

    public void setOblacno(String oblacno) {
        this.oblacno = oblacno;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getPitnyRezim() {
        return pitnyRezim;
    }

    public void setPitnyRezim(double pitnyRezim) {
        this.pitnyRezim = pitnyRezim;
    }
}
