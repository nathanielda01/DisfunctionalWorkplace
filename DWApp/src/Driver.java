import Company.Organization;
import Personnel.*;

import java.util.Scanner;


// TODO
public class Driver {

    static Organization organization;
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
                        if (!organization.employeeNameExists(input)) {
                            System.out.print("Enter hiring manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();
                            managerRef = organization.search(input);
                            if (managerRef != null && managerRef.getCanHire()) {
                                switch (managerRef.getClass().getSimpleName()) {
                                    case "President":
                                        President president = (President) managerRef;
                                        if (president.getUnderlingCount() < President.MAX_UNDER) {
                                            organization.fillVacancy(president, hireInput); // TODO Nate A.
                                        } else {
                                            System.out.println("Warning: No vacancy directly under manager\n");
                                        }
                                        break;
                                    case "VicePresident":
                                        VicePresident vp = (VicePresident) managerRef;
                                        if(vp.getUnderlingCount() < VicePresident.MAX_UNDER) {
                                            organization.fillVacancy(vp, hireInput); // TODO Nate A.
                                        } else {
                                            System.out.println("Warning: No vacancy directly under manager\n");
                                        }
                                        break;
                                    case "Supervisor":
                                        Supervisor supervisor = (Supervisor) managerRef;
                                        if (supervisor.getUnderlingCount() < Supervisor.MAX_UNDER) {
                                            organization.fillVacancy(supervisor, hireInput); // TODO Nate A.
                                        } else {
                                            System.out.println("Warning: No vacancy directly under manager\n");
                                        }
                                        break;
                                }
                            } else {
                                System.out.println("Warning: No employee exists with that name\n");
                            }
                        } else {
                            System.out.println("Warning: That employee already exists, cannot hire!\n");
                        }
                        break;
                    case "Fire":
                        System.out.print("Enter employee's name being fired: ");
                        fireInput = (fireInput.replaceAll(fireInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(fireInput)) {

                            Employee beingFired = organization.search(fireInput);

                            System.out.print("Enter firing manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else
                                System.out.println("Warning: Firing manager does not exist");

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanFire() && beingFired.getManager().getName() == managerRef.getName())
                                // TODO string method removes having to update underlings if a manager is being fired
                                organization.fireEmployee(managerRef, beingFired); // TODO Nate A.
                            else
                                System.out.println("Warning: Firing manager is not employee's direct manager\n");

                        } else {
                            System.out.println("Warning: That employee does not exist, cannot fire!\n");
                        }
                        break;
                    case "Quit":
                        System.out.print("Enter employee's name quiting: ");
                        quitInput = (quitInput.replaceAll(quitInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(quitInput)) {
                            Employee quitEmp = organization.search(quitInput);

                            System.out.print("Enter direct manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else
                                System.out.println("Warning: Direct manager does not exist\n");

                            if (managerRef != null && quitEmp.getManager().getName() == managerRef.getName()) {
                                // TODO let them quit, change string name to "" Nate A.
                            } else {
                                System.out.println("Warning: Direct manager is not quiting employee's manager, failed to quit.\n");
                            }
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot quit!\n");
                        }
                        break;
                    case "Layoff":
                        System.out.print("Enter employee's name being laid off: ");
                        layoffInput = (layoffInput.replaceAll(layoffInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(layoffInput)) {
                            Employee layoffEmp = organization.search(layoffInput);

                            System.out.print("Enter direct manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else
                                System.out.println("Warning: Direct manager does not exist\n");

                            if (managerRef != null && layoffEmp.getManager().getName() == managerRef.getName()) {
                                // TODO move them to another position within company if open, closest first
                                // TODO if no comparable openings, let go
                            } else {
                                System.out.println("Warning: Direct manager is not employee's manager, failed to layoff.\n");
                            }
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot layoff!\n");
                        }
                        break;
                    case "Transfer":
                        System.out.print("Enter employee's name being transferred: ");
                        transferInput = (transferInput.replaceAll(transferInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(transferInput)) {
                            Employee transferEmp = organization.search(transferInput);

                            System.out.print("Enter transferring manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else
                                System.out.println("Warning: Transferring manager does not exist.\n");

                            if (managerRef != null && managerRef.getCanTransfer()) {
                                // TODO check if transfer employee is in manager's heiarchy
                                // TODO transfer employee
                            } else {
                                System.out.println("Warning: Transferring manager cannot transfer employee.\n");
                            }
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot transfer!\n");
                        }
                        break;
                    case "Promote":
                        System.out.print("Enter employee's name being promoted: ");
                        promoteInput = (promoteInput.replaceAll(promoteInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(promoteInput)) {
                            Employee promoteEmp = organization.search(promoteInput);

                            System.out.print("Enter promoting manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else
                                System.out.println("Warning: Promoting manager does not exist.\n");

                            if (managerRef != null && managerRef.getCanPromote()) {
                                // TODO check promoting managers level and employee to be promoted level
                                // TODO promote employee
                            } else {
                                System.out.println("Warning: Promoting manager cannot promote employee.\n");
                            }
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot promote!\n");
                        }
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
}
