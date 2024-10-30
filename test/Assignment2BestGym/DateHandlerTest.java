package Assignment2BestGym;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DateHandlerTest {
    private final DateHandler dateHandler = new DateHandler();

    @Test
    public void testPaymentWithinOneYear(){
        //Testar ifall dagens datum och 8 månader sen är inom ett år från idag och att 13 månader är utanför.
        LocalDate today = LocalDate.now();
        assertEquals(dateHandler.isPaymentWithinOneYear(today),true);

        LocalDate eightMonthsAgo = LocalDate.now().minusMonths(8);
        assertEquals(dateHandler.isPaymentWithinOneYear(eightMonthsAgo),true);

        LocalDate thirteenMonthsAgo = LocalDate.now().minusMonths(13);
        assertNotEquals(dateHandler.isPaymentWithinOneYear(thirteenMonthsAgo),true);
    }
}