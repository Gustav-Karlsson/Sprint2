package Assignment2BestGym;

import java.time.LocalDate;

public class Member {
    private String name;
    private long idNumber;
    private LocalDate lastPayment;

    //Constructor för att skapa ett member objekt med namn, id och senaste betalningsdatum
    public Member(String name, long idNumber, LocalDate lastPayment) {
        this.name = name;
        this.idNumber = idNumber;
        this.lastPayment = lastPayment;
    }

    // Getter för namn, id, senaste betalning
    public String getName() {
        return name;
    }


    public long getIdNumber() {
        return idNumber;
    }


    public LocalDate getLastPayment() {
        return lastPayment;
    }


    //Använder DateHandler metod för att visa ifall medlemmen är aktiv eller inte
    public boolean isActiveMember() {
        DateHandler dateHandler = new DateHandler();
        return dateHandler.isPaymentWithinOneYear(lastPayment);
    }
}
