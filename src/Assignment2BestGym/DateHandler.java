package Assignment2BestGym;

import java.time.LocalDate;

public class DateHandler {

    // Kollar ifall senaste betalning är inom ett år från dagens datum
    public boolean isPaymentWithinOneYear(LocalDate paymentDate) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1).minusDays(1);
        return paymentDate.isAfter(oneYearAgo);
    }
}
