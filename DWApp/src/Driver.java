import Company.Organization;
import Personnel.*;

import java.util.Scanner;


// TODO
public class Driver {

    static Organization organization;
    static Employee employeeRef;

    public static void main(String[] args) {
        organization = new Organization();
        organization.printWelcome();
        organization.printOrganization();
        appMenu();
    }

    public static void appMenu() {
        String input = "";
        String hireInput = "";
        Scanner scanner = new Scanner(System.in);

        try {
            while (!input.equals("Exit")) {
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
                input = (input.replaceAll(input, scanner.nextLine().strip()));
                System.out.println();

                switch (input) {
                    case "Hire":
                        System.out.print("Enter new hire name: ");
                        hireInput = (hireInput.replaceAll(input, scanner.nextLine().strip()));
                        System.out.println();
                        if (!organization.employeeNameExists(input)) {
                            System.out.print("Enter hiring manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();
                            employeeRef = organization.search(input);
                            if (employeeRef != null && employeeRef.getCanHire()) {
                                switch (employeeRef.getClass().getSimpleName()) {
                                    case "President":
                                        President president = (President)employeeRef;
                                        if (president.getUnderlings().length < president.MAX_UNDER) {
                                            organization.fillVacancy(president, hireInput);
                                        } else {
                                            System.out.println("Warning: No vacancy directly under manager");
                                        }
                                        break;
                                    case "VicePresident":
                                        VicePresident vp = (VicePresident)employeeRef;
                                        break;
                                    case "Supervisor":
                                        Supervisor supervisor = (Supervisor) employeeRef;
                                        break;
                                }
                            } else {
                                System.out.println("Warning: No employee exists with that name");
                            }
                        } else {
                            System.out.println("Warning: That employee already exists, cannot hire!");
                            System.out.println();
                        }
                        break;
                    case "Fire":
                        break;
                    case "Quit":
                        break;
                    case "Layoff":
                        break;
                    case "Transfer":
                        break;
                    case "Promote":
                        break;
                    case "Print":
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
