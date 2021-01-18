package hlavneMenu;

public class PitnyRezim {
    double hmotnost;
    static int konstantaHm = 15;
    static double konstantaLt1 = 0.5;
    static double konstantaLt2 = 0.7;

    public PitnyRezim(double hmotnost){
        this.hmotnost = hmotnost;
    }

    public double vypocet1(){
        return ((hmotnost/konstantaHm)*konstantaLt1);
    }

    public double vypocet2(){
        return (hmotnost/konstantaHm)*konstantaLt2;
    }

    public static void main(String[] args) {
        PitnyRezim pitnyRezim = new PitnyRezim(65);

        System.out.println(pitnyRezim.vypocet1());
        System.out.println(pitnyRezim.vypocet2());
    }
}
