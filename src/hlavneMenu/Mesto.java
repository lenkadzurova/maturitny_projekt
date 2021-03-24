package hlavneMenu;

public class Mesto {
    int id;

    String mesto;

    double najTeplota;

    double minTeplota;

    String oblacno;

    String datum;

    double vypocetPitnehoRezimu;

    String pitnyRezim;


    String vyhodnotiePitia;

    public Mesto() {

    }

    public Mesto(String mesto, double najTeplota, double minTeplota, String oblacno, String datum) {
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

    public double getNajTeplota() {
        return najTeplota;
    }

    public void setNajTeplota(double najTeplota) {
        this.najTeplota = najTeplota;
    }

    public double getMinTeplota() {
        return minTeplota;
    }

    public void setMinTeplota(double minTeplota) {
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

    public double getVypocetPitnehoRezimu() {
        return vypocetPitnehoRezimu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVypocetPitnehoRezimu(double vypocetPitnehoRezimu) {
        this.vypocetPitnehoRezimu = vypocetPitnehoRezimu;
    }

    public String getPitnyRezim() {
        return pitnyRezim;
    }

    public void setPitnyRezim(String pitnyRezim) {
        this.pitnyRezim = pitnyRezim;
    }

    public String getVyhodnotiePitia() {
        return vyhodnotiePitia;
    }

    public void setVyhodnotiePitia(String vyhodnotiePitia) {
        this.vyhodnotiePitia = vyhodnotiePitia;
    }
}

