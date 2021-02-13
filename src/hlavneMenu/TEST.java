package hlavneMenu;

public class TEST {
    String nazov;

    private static TEST instance = null;

    private TEST(){
        nazov = "UVODNA";
    }


    public static TEST getInstance(){
        if(instance == null){
            return instance = new TEST();
        }else {
            return instance;
        }
    }


    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
}
