package hlavneMenu;

import javafx.beans.property.SimpleStringProperty;

public class Pocasie {
    private final SimpleStringProperty den = new SimpleStringProperty("");
    private final SimpleStringProperty oblacnost = new SimpleStringProperty("");
    private final SimpleStringProperty najvyzsiaTeplota = new SimpleStringProperty("");
    private final SimpleStringProperty najnizsiaTeplota = new SimpleStringProperty("");
    private final SimpleStringProperty pitnyRezim = new SimpleStringProperty("");

    public Pocasie() {
        this("", "", "","","");
    }

    public Pocasie(String den, String oblacnost, String najvyzsiaTeplota, String najnizsiaTeplota, String pitnyRezim) {
        setDen(den);
        setOblacnost(oblacnost);
        setNajnizsiaTeplota(najnizsiaTeplota);
        setNajvyzsiaTeplota(najvyzsiaTeplota);
        setPitnyRezim(pitnyRezim);
    }


    public String getDen() {
        return den.get();
    }

    public void setDen(String fName) {
        den.set(fName);
    }

    public String getOblacnost() {
        return this.oblacnost.get();
    }

    public void setOblacnost(String fName) {
        oblacnost.set(fName);
    }

    public String getNajnizsiaTeplota() {
        return najnizsiaTeplota.get();
    }

    public void setNajnizsiaTeplota(String fTeplota) {
        najnizsiaTeplota.set(fTeplota);
    }

    public String getNajvyzsiaTeplota() {
        return najvyzsiaTeplota.get();
    }

    public void setNajvyzsiaTeplota(String fTeplota) {
        najvyzsiaTeplota.set(fTeplota);
    }

    public String getPitnyRezim() {
        return najnizsiaTeplota.get();
    }

    public void setPitnyRezim(String fRezim) {
        pitnyRezim.set(fRezim);
    }
}

