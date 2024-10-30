package Assignment2BestGym;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BestGymMain {
    public static void main(String[] args) {

        // Laddar medlemmar från listan med nuvarande medlemmar.
        List<Member> members = FileReader.loadMembers();
        Scanner scanner = new Scanner(System.in);

        //While loop för att slippa starta om efter varje input, t.ex. bra när de kommer flera medlemmar samtidigt.
        while (true) {
            System.out.println("Enter the customer's name or ID (or type 'exit' to quit):");
            String input = null;

            // Läser input i terminalen
            try {
                input = scanner.nextLine().trim();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter text or numbers only.");
                scanner.nextLine(); // Felhantering vid otillåten input, t.ex. siffror
                continue;
            } catch (Exception e) {
                System.out.println("An error occurred while reading input. Please try again.");
                e.printStackTrace(); //Övriga fel som mismatchexception inte hanterar
                continue;
            }

            // Stänger ner programmet oavsett stor eller liten bokstav.
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting the system.");
                break;
            }

            // Läser av ifall input från terminalen stämmer överens med medlemslistan, annars erbjud medlemskap.
            // Vid korrekt input så sparas besök i GymVisited filen
            Optional<Member> memberOpt = FileReader.findMember(members, input);
            if (memberOpt.isPresent()) {
                Member member = memberOpt.get();
                if (member.isActiveMember()) {
                    System.out.println("Customer is a current member. Access granted.");
                    FileReader.logAttendance(member); // Log attendance for active members
                } else {
                    System.out.println("Customer is a former member. Ask if they want to renew their membership");
                }
            } else {
                System.out.println("Customer not found. Ask if they want to become a member");
            }
        }
        scanner.close();
    }
}
