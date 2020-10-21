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
                sleep(1000);
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
                        if (organization.employeeNameExists(fireInput)) {

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

                            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanFire() && beingFired.getManager().getName() == managerRef.getName())
                                organization.fireEmployee(managerRef, beingFired);
                            else {
                                System.out.println("Warning: Firing manager is not employee's direct manager\n");
                                break;
                            }
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

                            /*System.out.print("Enter direct manager: ");
                            input = (input.replaceAll(input, scanner.nextLine().strip()));
                            System.out.println();

                            if (organization.employeeNameExists(input))
                                managerRef = organization.search(input);
                            else {
                                System.out.println("Warning: Direct manager does not exist\n");
                                break;
                            }*/

                            if (/*!managerRef.getName().equals(organization.VACANT) && */quitEmp.getCanQuit()) {
                                quitEmp.setName(Organization.VACANT);
                            }
                            else {
                                System.out.print("Unfortunately, President " + organization.getPresident().getName() +
                                        " cannot quit. They will stay here.");
                                sleep(1000);
                                System.out.print(" . ");
                                sleep(1000);
                                System.out.print(" . ");
                                sleep(1000);
                                System.out.print("forever.");
                                System.out.println();
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
                            else {
                                System.out.println("Warning: Direct manager does not exist\n");
                                break;
                            }

                            if (!managerRef.getName().equals(organization.VACANT) && layoffEmp.getManager().getName() == managerRef.getName()) {
                                // TODO move them to another position within company if open, closest first
                                // TODO if no comparable openings, let go
                            } else {
                                System.out.println("Warning: Direct manager is not employee's manager, failed to layoff.\n");
                                break;
                            }
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot layoff!\n");
                        }
                        break;
                    case "Transfer":
                        organization.executeAction(input);
                        break;
                    case "Promote":
                        organization.executeAction(input);
                        break;
                        /*Employee promoteEmp;

                        System.out.print("Enter employee's name being promoted: ");
                        promoteInput = (promoteInput.replaceAll(promoteInput, scanner.nextLine().strip()));
                        System.out.println();
                        if (organization.employeeNameExists(promoteInput)) {
                            promoteEmp = organization.search(promoteInput);
                        } else {
                            System.out.println("Warning: That employee does not exist, cannot promote!\n");
                            break;
                        }

                        System.out.print("Enter promoting manager: ");
                        input = (input.replaceAll(input, scanner.nextLine().strip()));
                        System.out.println();

                        if (organization.employeeNameExists(input))
                            managerRef = organization.search(input);
                        else {
                            System.out.println("Warning: Promoting manager does not exist.\n");
                            break;
                        }

//                      President --Promotes--> Supervisors
//                      Vice President --Promotes--> Workers

                        Supervisor promoteSupervisor;
                        Worker promoteWorker;
                        if (managerRef.getCanPromote()) {

                            switch (managerRef.getPosition()) {
                                case "President":
                                    President presRef = (President)managerRef;
                                    try {
                                        promoteSupervisor = (Supervisor)promoteEmp;
                                    }
                                    catch (Exception e) {
                                        System.out.println(e + ": employee level is not acceptable for a President to" +
                                                " promote as the President will not become the direct manager of the promoted");
                                        break;
                                    }
                                    for (VicePresident vp : presRef.getVPs()) {
                                        if (vp.getName().equals(Organization.VACANT)) {
                                            if (!vp.contains(promoteSupervisor.getName())) {
                                                vp.setName(promoteSupervisor.getName());
                                                promoteSupervisor.setName(Organization.VACANT);
                                            }
                                            else {
                                                System.out.println("Error: " + promoteSupervisor.getName() + " is in " +
                                                        "the same branch as the Vice President position, and is " +
                                                        "therefore unable to be promoted.");
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                case "Vice President":
                                    VicePresident vpRef = (VicePresident)managerRef;
                                    try {
                                        promoteWorker = (Worker)promoteEmp;
                                    }
                                    catch (Exception e) {
                                        System.out.println(e + ": employee level is not acceptable for a Vice President to" +
                                                " promote as the Vice President will not become the direct manager of the promoted");
                                        break;
                                    }

                                    for (int i = 0; i < vpRef.getSupervisors().length; i++) {
                                        if (vpRef.getSupervisors()[i].getName().equals(Organization.VACANT)) {
                                            if (!vpRef.getSupervisors()[i].contains(promoteWorker.getName())) {
                                                vpRef.getSupervisors()[i].setName(promoteWorker.getName());
                                                promoteWorker.setName(Organization.VACANT);
                                                break;
                                            }
                                        }
                                    }
                                    System.out.println("No supervisor slots available in " + vpRef.getName() + "'s branch.");
                            }

                        } else {
                            System.out.println("Warning: Promoting manager cannot promote employee.\n");
                            break;
                        }

                        break;*/
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
