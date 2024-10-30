package Assignment2BestGym;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    //Skapar värden för en test medlem
    private long idNumber = 9110261234L;
    private String name = "Johan Smith";
    private LocalDate lastPayment = LocalDate.of(2024, 1, 22);

    Member m1 = new Member(name, idNumber, lastPayment);

    //Testar att värden sätts korrekt för medlemmar
    @Test
    public void testCreateMember() {
        assertEquals(m1.getName(), "Johan Smith");
        assertEquals(m1.getLastPayment(), lastPayment);
        assertEquals(m1.getIdNumber(), idNumber);
        assertNotEquals(m1.getName(), "Karl Carlsson");
    }
}
