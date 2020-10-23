import Company.Actions;
import Company.Organization;
import Personnel.*;

import java.util.Scanner;


// TODO
public class Driver {

    static Organization organization;
    static Actions actions;
    static Employee managerRef;

    public static void main(String[] args) {
        organization = new Organization();
        organization.printWelcome();
        organization.loadOrganization("TestOrg.txt");
        appMenu(organization);
    }

    public static void appMenu(Organization organization) {
        String input = "";
        String hireInput = "";
        String fireInput = "";
        String quitInput = "";
        String layoffInput = "";
        String transferInput = "";
        String promoteInput = "";
        Scanner scanner = new Scanner(System.in);

        try {
            while (!input.equals("Exit")) {
                sleep(500);
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
                        hireInput = (hireInput.replaceAll(hireInput, scanner.nextLine().strip()));
                        System.out.println();

                        if (!organization.employeeNameExists(hireInput)) {

                            Employee beingHired = organization.search(hireInput);

                            System.out.print("Enter hiring manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input)) {
                                managerRef = organization.search(input);
                            } else {
                                System.out.println("Warning: Hiring manager does not exist");
                                break;
                            }

                            if (!managerRef.getName().equals(Organization.VACANT) && managerRef.getCanHire()) {
                                organization.fillVacancy(managerRef, hireInput);
                            } else {
                                System.out.println("Warning: Hiring manager cannot hire anyone!");
                                break;
                            }

                        } else {
                            System.out.println("Warning: That employee already exists, cannot hire!\n");
                            break;
                        }
                        break;
                    case "Fire":
                        System.out.print("Enter employee's name being fired: ");
                        fireInput = (fireInput.replaceAll(fireInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(fireInput) && !fireInput.equals(organization.getPresident().getName())) {

                            Employee beingFired = organization.search(fireInput);

                            System.out.print("Enter firing manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Firing manager does not exist");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanFire())
                                organization.fireEmployee(managerRef, beingFired);
                            else {
                                System.out.println("Warning: Specified firing manager is not authorized to fire employees.\n");
                                break;
                            }
                        } else {
                            if (fireInput.equals(organization.getPresident().getName()))
                                System.out.println("Warning: The president cannot be fired!\n");
                            else
                                System.out.println("Warning: That employee does not exist, cannot quit!\n");
                        }
                        break;
                    case "Quit":
                        System.out.print("Enter employee's name quiting: ");
                        quitInput = (quitInput.replaceAll(quitInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(quitInput) && !quitInput.equals(organization.getPresident().getName())) {
                            Employee quitEmp = organization.search(quitInput);

                            if (quitEmp.getCanQuit()) {
                                quitEmp.setName(Organization.VACANT);
                            }

                        } else {
                            if (quitInput.equals(organization.getPresident().getName())) {
                                System.out.print("Unfortunately, President " + organization.getPresident().getName() +
                                        " cannot quit. They will stay here.");
                                sleep(1000);
                                System.out.print(" .");
                                sleep(1000);
                                System.out.print(" . ");
                                sleep(1000);
                                System.out.print("forever.");
                                System.out.println("\n");
                            }
                            else
                                System.out.println("Warning: That employee does not exist, cannot quit!\n");
                        }
                        break;
                    case "Layoff":
                        System.out.print("Enter employee's name being laid off: ");
                        layoffInput = (layoffInput.replaceAll(layoffInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(layoffInput) && !layoffInput.equals(organization.getPresident().getName())) {
                            Employee layoffEmp = organization.search(layoffInput);

                            System.out.print("Enter layoff manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Layoff manager does not exist\n");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanLayoff()) {
                                organization.layoffEmployee(managerRef, layoffEmp);
                            } else {
                                System.out.println("Warning: Specified layoff manager is not authorized to layoff employees.\n");
                                break;
                            }
                        } else {
                            if (layoffInput.equals(organization.getPresident().getName()))
                                System.out.println("Warning: The president cannot be laid off!\n");
                            else
                                System.out.println("Warning: That employee does not exist, cannot be laid off!\n");
                        }
                        break;
                    case "Transfer":
                        organization.executeAction(input);
                        break;
                    case "Promote":
                        organization.executeAction(input);
                        break;
                    case "Print":
                        organization.printOrganization();
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
