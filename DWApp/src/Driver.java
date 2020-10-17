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

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanHire()) {
                                organization.fillVacancy(managerRef, hireInput);
                            } else {
                                //TODO print here or in fill vacancy if there is no space?
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

                            System.out.print("Enter direct manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Direct manager does not exist\n");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && quitEmp.getManager().getName().equals(managerRef.getName())) {
                                organization.quitEmployee(managerRef, quitEmp);
                            } else {
                                System.out.println("Warning: Direct manager is not quiting employee's manager, failed to quit.\n");
                                break;
                            }
                        } else {
                            if (quitInput.equals(organization.getPresident().getName()))
                                System.out.println("Warning: The president cannot quit!\n");
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
                        System.out.print("Enter employee's name being transferred: ");
                        transferInput = (transferInput.replaceAll(transferInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(transferInput) && !transferInput.equals(organization.getPresident().getName())) {
                            Employee transferEmp = organization.search(transferInput);

                            System.out.print("Enter transferring manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Transferring manager does not exist.\n");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanTransfer()) {
                                // TODO check if transfer employee is in manager's heiarchy
                                // TODO transfer employee
                            } else {
                                System.out.println("Warning: Transferring manager cannot transfer employees.\n");
                                break;
                            }

                        } else {
                            if (transferInput.equals(organization.getPresident().getName()))
                                System.out.println("Warning: The president cannot be transferred!\n");
                            else
                                System.out.println("Warning: That employee does not exist, cannot be transferred!\n");
                        }
                        break;
                    case "Promote":
                        System.out.print("Enter employee's name being promoted: ");
                        promoteInput = (promoteInput.replaceAll(promoteInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(promoteInput) ) { //TODO add function logic that checks if employee is president or vp
                            Employee promoteEmp = organization.search(promoteInput);

                            System.out.print("Enter promoting manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Promoting manager does not exist.\n");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanPromote()) {
                                // TODO check promoting managers level and employee to be promoted level
                                // TODO promote employee
                            } else {
                                System.out.println("Warning: Promoting manager cannot promote employee.\n");
                                break;
                            }

                        } else {
                            if (promoteInput.equals(organization.getPresident().getName())) //TODO check if input is president or vp
                                System.out.println("Warning: The president or vice presidents cannot be promoted!\n");
                            else
                                System.out.println("Warning: That employee does not exist, cannot be promoted!\n");
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
