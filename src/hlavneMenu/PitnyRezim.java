package hlavneMenu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PitnyRezim {

    static int konstantaHm = 15;
    static double konstantaLt1 = 0.5;
    static double konstantaLt2 = 0.7;


    public static double vypocet(double vaha, double pocasie){
        if(pocasie>20){
            return (vaha/konstantaHm)*konstantaLt2;
        }else {
            return ((vaha/konstantaHm)*konstantaLt1);
        }

    }


    public static void main(String[] args) {


        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        System.out.println(myDateObj.getDayOfWeek());


        LocalDate initialDate = LocalDate.parse("2007-05-10");





        //System.out.println(pitnyRezim.vypocet1());
        //System.out.println(pitnyRezim.vypocet2());
    }
}
