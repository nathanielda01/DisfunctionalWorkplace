import Company.Organization;

import java.util.Scanner;
import java.io.File;

public class Driver {

    static Organization organization;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        organization = new Organization();
        organization.printWelcome();
        String input = "";
        File inputFile = new File("");

        while(!inputFile.exists()) {
            System.out.println("Would you like to enter an input file for the application? \"yes\" or \"no\"");
            input = (input.replaceAll(input, scanner.nextLine().strip()));

            if (input.equals("yes")) {
                System.out.print("Please enter the file path: ");
                String fileString = scanner.nextLine().strip();
                inputFile = new File(fileString);
                if (inputFile.exists()) {
                    organization.loadOrganization(fileString);
                } else {
                    System.out.println("Invalid file path.");
                }

            } else if (input.equals("no")) {
                System.out.println("Loading default text file for application.\n");
                organization.loadOrganization("TestOrg.txt");
                break;
            } else {
                System.out.println("Invalid input.");
            }

        }

        appMenu(organization);
    }

    public static void appMenu(Organization organization) {
        String input = "";
        //Scanner scanner = new Scanner(System.in);

        try {
            while (!input.equals("Exit")) {
                promptUser();
                input = (input.replaceAll(input, scanner.nextLine().strip()));
                System.out.println();

                organization.executeAction(input);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void promptUser() {
        System.out.println("***Company Actions***");
        System.out.println("To hire someone type: \"Hire\"");
        System.out.println("To fire someone type: \"Fire\"");
        System.out.println("Let someone quit type: \"Quit\"");
        System.out.println("Lay off someone type: \"Layoff\"");
        System.out.println("To transfer someone type: \"Transfer\"");
        System.out.println("To promote someone type: \"Promote\"");
        System.out.println("To print current organization type: \"Print\"");
        System.out.println("To quit the application type: \"Exit\"");
        System.out.print("Input: ");
    }
}
