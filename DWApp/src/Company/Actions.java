package Company;

import Personnel.*;

import java.util.Scanner;

public class Actions {
    // Variables
    private Organization organization;
    private Employee managerRef;
    private String input = "";
    private String employeeInput = "";
    private String managerInput = "";
    private String hireInput = "";
    private String fireInput = "";
    private String quitInput = "";
    private String layoffInput = "";
    private String transferInput = "";
    private String promoteInput = "";
    private Scanner scanner = new Scanner(System.in);

    // Methods
    public Actions(Organization organization) {
        this.organization = organization;
    }

    public void execute(String action) {
        switch (action) {
            case "Hire":
                hire();
                break;
            case "Fire":
                fire();
                break;
            case "Quit":
                quit();
                break;
            case "Layoff":
                layoff();
                break;
            case "Transfer":
                transfer();
                break;
            case "Promote":
                promote();
                break;
            case "Print":
                organization.printOrganization();
                break;
            default:
                System.out.println(action + " is not a valid action.");
                break;
        }
    }

    private void layoff() {
        promptForNames();

        if (organization.employeeNameExists(employeeInput) && !employeeInput.equals(organization.getPresident().getName())) {
            Employee layoffEmp = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput))
                managerRef = organization.search(managerInput);
            else {
                System.out.println("Warning: Layoff manager does not exist\n");
                return;
            }

            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanLayoff()) {
                organization.layoffEmployee(managerRef, layoffEmp);
            } else {
                System.out.println("Warning: Specified layoff manager is not authorized to layoff employees.\n");
                return;
            }
        } else {
            if (layoffInput.equals(organization.getPresident().getName()))
                System.out.println("Warning: The president cannot be laid off!\n");
            else
                System.out.println("Warning: That employee does not exist, cannot be laid off!\n");
        }
        return;
    }

    private void quit() {
        System.out.print("Enter employee's name quiting: ");
        quitInput = (quitInput.replaceAll(quitInput, scanner.nextLine().strip()));
        quitInput = quitInput.replaceAll("[^a-zA-Z]+", "");
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
        return;
    }

    private void fire() {
        promptForNames();

        if (organization.employeeNameExists(employeeInput) && !employeeInput.equals(organization.getPresident().getName())) {

            Employee beingFired = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput))
                managerRef = organization.search(managerInput);
            else {
                System.out.println("Warning: Firing manager does not exist");
                return;
            }

            if (!managerRef.getName().equals(organization.VACANT) && managerRef.getCanFire())
                organization.fireEmployee(managerRef, beingFired);
            else {
                System.out.println("Warning: Specified firing manager is not authorized to fire employees.\n");
                return;
            }
        } else {
            if (employeeInput.equals(organization.getPresident().getName()))
                System.out.println("Warning: The president cannot be fired!\n");
            else
                System.out.println("Warning: That employee does not exist, cannot quit!\n");
        }
        return;
    }

    private void hire() {
        promptForNames();

        if (!organization.employeeNameExists(employeeInput)) {

            if (organization.employeeNameExists(managerInput)) {
                managerRef = organization.search(managerInput);
            } else {
                System.out.println("Warning: Hiring manager does not exist");
                return;
            }

            if (!managerRef.getName().equals(Organization.VACANT) && managerRef.getCanHire()) {
                organization.fillVacancy(managerRef, employeeInput);
            } else {
                System.out.println("Warning: Hiring manager cannot hire anyone!");
                return;
            }

        } else {
            System.out.println("Warning: That employee already exists, cannot hire!\n");
            return;
        }
        return;
    }

    private void promote() {
        Employee promoteEmp;
        Supervisor promoteSupervisor;
        Worker promoteWorker;

        promptForNames();

        if (organization.employeeNameExists(employeeInput)) {
            promoteEmp = organization.search(employeeInput);
        } else {
            System.out.println("Warning: That employee does not exist, cannot promote!\n");
            return;
        }

        if (organization.employeeNameExists(managerInput))
            managerRef = organization.search(managerInput);
        else {
            System.out.println("Warning: Promoting manager does not exist.\n");
            return;
        }

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
                                return;
                            }
                        }
                    }
                    System.out.println("No supervisor slots available in " + vpRef.getName() + "'s branch.");
            }

        } else {
            System.out.println("Warning: Promoting manager cannot promote employee.\n");
            return;
        }

        return;
    }

    private void transfer() {
        promptForNames();

        if (organization.employeeNameExists(employeeInput)) {
            Employee transferEmp = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput)) {
                managerRef = organization.search(managerInput);
            }
            else {
                System.out.println("Warning: Transferring manager does not exist.\n");
                return;
            }

            if (!managerRef.getCanTransfer()) {
                System.out.println(managerRef.getName() + " does not have the power to transfer employees.");
                return;
            }

            switch (managerRef.getPosition()) {
                case "President":
                    while (organization.search(Organization.VACANT) != null) {
                        Employee vacancy = organization.search(Organization.VACANT);
                        if (vacancy.getPosition().equals(transferEmp.getPosition())) {
                            vacancy.setName(transferEmp.getName());
                            transferEmp.setName(Organization.VACANT);
                            System.out.println("Transfer complete: " + vacancy.getName() +
                                    " is now managed by " + vacancy.getManager().getName());
                            break;
                        } else {
                            vacancy.setName("-1");
                        }
                    }
                    while (organization.search("-1") != null) {
                        organization.search("-1").setName(Organization.VACANT);
                    }
                    break;
                case "Vice President":
                    VicePresident vpRef = (VicePresident) managerRef;
                    if (transferEmp.getPosition().equals("Supervisor") && transferEmp.getManager().getName().equals(vpRef.getName())) {
                        for (int i = 0; i < vpRef.getSupervisors().length; i++) {
                            if (vpRef.getSupervisors()[i].getName().equals(Organization.VACANT)) {
                                vpRef.getSupervisors()[i].setName(transferEmp.getName());
                                transferEmp.setName(Organization.VACANT);
                                break;
                            }
                        }
                        if (!transferEmp.getName().equals(Organization.VACANT)) {
                            System.out.println(vpRef.getName() + " has no Supervisor vacancies available to transfer " + transferEmp.getName());
                        }
                    } else if (transferEmp.getPosition().equals("Worker") && transferEmp.getManager().getManager().getName().equals(vpRef.getName())) {
                        for (int i = 0; i < vpRef.getSupervisors().length; i++) {
                            for (int j = 0; j < vpRef.getSupervisors()[i].getWorkers().length; j++) {
                                if (vpRef.getSupervisors()[i].getWorkers()[j].getName().equals(Organization.VACANT)) {
                                    vpRef.getSupervisors()[i].getWorkers()[j].setName(transferEmp.getName());
                                    transferEmp.setName(Organization.VACANT);
                                }
                            }
                        }

                        if (!transferEmp.getName().equals(Organization.VACANT)) {
                            System.out.println(vpRef.getName() + " has no Worker vacancies available to transfer " + transferEmp.getName());
                        }
                    } else {
                        System.out.println("Error: Vice Presidents may not transfer other Vice Presidents or themselves.");
                    }
                    break;
                default:
                    System.out.println(managerRef.getName() + " is a " + managerRef.getPosition() + ". " +
                            managerRef.getPosition() + "s do not have the power to transfer.");
                    break;
            }
        } else {
            System.out.println("Warning: That employee does not exist, cannot transfer!\n");
        }
        return;
    }

    private void promptForNames() {
        System.out.print("Enter employee's name: ");
        employeeInput = (layoffInput.replaceAll(layoffInput, scanner.nextLine().strip()));
        employeeInput = employeeInput.replaceAll("[^a-zA-Z]+", "");
        System.out.print("Enter manager's name: ");
        managerInput = (input.replaceAll(input, scanner.nextLine().strip()));
        managerInput = managerInput.replaceAll("[^a-zA-Z]+", "");
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
