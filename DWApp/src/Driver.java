import Company.Organization;

import java.util.Scanner;


// TODO
public class Driver {

    public static void main(String[] args) {

        Organization organization = new Organization();
        organization.printWelcome();
        organization.printOrganization();
        appMenu();
    }

    public static void appMenu() {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        try {
            while (!input.equals("Quit")) {
                System.out.println("***Company Actions***");
                System.out.println("To hire someone type: \"Hire: New Name\"");
                System.out.println("To fire someone type: \"Fire: Full Name\"");
                System.out.println("Let someone quit type: \"Quit: Full Name\"");
                System.out.println("Lay off someone type: \"Layoff: Full Name\"");
                System.out.println("To transfer someone type: \"Transfer: Full Name\"");
                System.out.println("To promote someone type: \"Promote: Full Name\"");
                System.out.println("To print current organization type: \"Print\"");
                System.out.println("To quit the application type: \"Quit\"");
                System.out.print("Input: ");
                input = (input.replaceAll(input, scanner.nextLine().strip()));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
