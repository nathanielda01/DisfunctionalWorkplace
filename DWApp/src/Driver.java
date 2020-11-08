import Company.Organization;

import java.util.Scanner;

public class Driver {

    static Organization organization;

    public static void main(String[] args) {
        organization = new Organization();
        organization.loadOrganization("TestOrg.txt");
        organization.printWelcome();
        appMenu(organization);
    }

    public static void appMenu(Organization organization) {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        try {
            while (!input.equals("Exit")) {
                promptUser();
                input = (input.replaceAll(input, scanner.nextLine().strip()));

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
        System.out.println("To let someone quit type: \"Quit\"");
        System.out.println("To lay off someone type: \"Layoff\"");
        System.out.println("To transfer someone type: \"Transfer\"");
        System.out.println("To promote someone type: \"Promote\"");
        System.out.println("To print current organization type: \"Print\"");
        System.out.println("To quit the application type: \"Exit\"");
        System.out.print("Input: ");
    }
}
