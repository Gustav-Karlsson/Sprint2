package Assignment2BestGym;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileReader {
    private static final String MEMBER_LIST_PATH = "src/Assignment2BestGym/MemberList";
    private static final String GYM_VISIT_LOG_PATH = "src/Assignment2BestGym/GymVisited";

    // Laddar in listan med medlemmar från länken ovan och sparar till en lista
    public static List<Member> loadMembers() {
        List<Member> gymMembers = new ArrayList<>();

        //Läser in medlemmar från MembersList och skapar medlemmar i arraylisten
        try (BufferedReader reader = Files.newBufferedReader(Path.of(MEMBER_LIST_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Delar upp namn och id nummer
                long idNumber = Long.parseLong(parts[0]); // parsear id
                String name = parts[1]; // Hämtar namn
                LocalDate lastPaymentDate = LocalDate.parse(reader.readLine().trim()); //Läser betalningsdatum
                gymMembers.add(new Member(name, idNumber, lastPaymentDate)); //Skapar objektet
            }
        } catch (IOException e) {
            System.out.println("Error reading members file: " + e.getMessage());
            e.printStackTrace();
        }
        return gymMembers;
    }

    // Metod för att leta efter medlemmar från filen med medlemmar
    public static Optional<Member> findMember(List<Member> members, String query) {
        try {
            long id = Long.parseLong(query); //parsear query till long för att söka på id
            return members.stream().filter(m -> m.getIdNumber() == id).findFirst(); // Söker på ID
        } catch (NumberFormatException e) {
            return members.stream().filter(m -> m.getName().equalsIgnoreCase(query)).findFirst(); // Söker på namn
        }
    }

    // Registrerar besök från aktiva medlemmar till GymVisits filen eller skapar filen ifall den inte finns
    public static void logAttendance(Member member) {
        String logEntry = member.getName() + ", " + member.getIdNumber() + ", " + LocalDate.now() + "\n"; //Mall för registrering
        try {
            Files.write(Path.of(GYM_VISIT_LOG_PATH), logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Logged attendance for " + member.getName());
        } catch (IOException e) {
            System.out.println("Error logging attendance: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
