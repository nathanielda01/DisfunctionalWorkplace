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
    private String quitInput = "";
    private String layoffInput = "";
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
                System.out.println("Error: " + action + " is not a valid action.\n");
                break;
        }
    }

    private void hire() {
        // Input validation gauntlet
        if (!promptForEmployeeName()) {
            return;
        }
        if (organization.employeeNameExists(employeeInput)) {
            System.out.println("Error: That employee already exists.\n");
            return;
        }
        if (!promptForManagerName()) {
            return;
        }
        managerRef = organization.search(managerInput);
        if (managerRef == null) {
            System.out.println("Error: That manager doesn't exist.\n");
            return;
        }

        // Input was valid, try hiring
        organization.hireEmployee(managerRef, employeeInput);
    }

    private void fire() {
        if (!promptForNames()) {
            return;
        }

        if (organization.employeeNameExists(employeeInput) && !employeeInput.equals(organization.getPresident().getName())) {
            Employee beingFired = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput))
                managerRef = organization.search(managerInput);
            else {
                System.out.println("Error: That manager does not exist.\n");
                return;
            }

            if (!managerRef.getName().equals(Organization.VACANT) && managerRef.getCanFire())
                organization.fireEmployee(managerRef, beingFired);
            else {
                System.out.println("Error: That manager is not authorized to fire employees.\n");
                return;
            }
        } else {
            if (employeeInput.equals(organization.getPresident().getName()))
                System.out.println("Error: The president cannot be fired.\n");
            else
                System.out.println("Error: That employee does not exist.\n");
        }

        return;
    }

    private void quit() {
        System.out.print("Enter quiting employee's name: ");
        quitInput = (quitInput.replaceAll(quitInput, scanner.nextLine().strip()));
        quitInput = quitInput.replaceAll("[^a-zA-Z]+", "");
        if (organization.employeeNameExists(quitInput) && !quitInput.equals(organization.getPresident().getName())) {
            Employee quitEmp = organization.search(quitInput);

            if (quitEmp.getCanQuit()) {
                organization.quitEmployee(quitEmp);
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
                System.out.println("Error: That employee does not exist.\n");
        }

        return;
    }

    private void layoff() {
        if (!promptForNames()) {
            return;
        }

        if (organization.employeeNameExists(employeeInput) && !employeeInput.equals(organization.getPresident().getName())) {
            Employee layoffEmp = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput))
                managerRef = organization.search(managerInput);
            else {
                System.out.println("Error: That manager does not exist.\n");
                return;
            }

            if (!managerRef.getName().equals(Organization.VACANT) && managerRef.getCanLayoff()) {
                organization.layoffEmployee(managerRef, layoffEmp);
            } else {
                System.out.println("Error: That manager is not authorized to layoff employees.\n");
                return;
            }
        } else {
            if (layoffInput.equals(organization.getPresident().getName()))
                System.out.println("Error: The president cannot be laid off.\n");
            else
                System.out.println("Error: That employee does not exist.\n");
        }

        return;
    }

    private void transfer() {
        if (!promptForNames()) {
            return;
        }

        if (organization.employeeNameExists(employeeInput)) {
            Employee transferEmp = organization.search(employeeInput);

            if (organization.employeeNameExists(managerInput)) {
                managerRef = organization.search(managerInput);
            }
            else {
                System.out.println("Error: That manager does not exist.\n");
                return;
            }

            if (!managerRef.getCanTransfer()) {
                System.out.println("Error: That manager is not authorized to transfer employees.\n");
                return;
            }

            switch (managerRef.getPosition()) {
                case "President":
                    while (organization.search(Organization.VACANT) != null) {
                        Employee vacancy = organization.search(Organization.VACANT);
                        if (vacancy.getPosition().equals(transferEmp.getPosition())) {
                            vacancy.setName(transferEmp.getName());
                            transferEmp.setName(Organization.VACANT);
                            System.out.println("Success! " + vacancy.getName() + " was transferred.\n");
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
                                System.out.println("Success! " + vpRef.getSupervisors()[i].getName() + " was transferred.\n");
                                break;
                            }
                        }
                        if (!transferEmp.getName().equals(Organization.VACANT)) {
                            System.out.println("Error: That manager has no Supervisor vacancies\n");
                        }
                    } else if (transferEmp.getPosition().equals("Worker") && transferEmp.getManager().getManager().getName().equals(vpRef.getName())) {
                        for (int i = 0; i < vpRef.getSupervisors().length; i++) {
                            for (int j = 0; j < vpRef.getSupervisors()[i].getWorkers().length; j++) {
                                if (vpRef.getSupervisors()[i].getWorkers()[j].getName().equals(Organization.VACANT)) {
                                    vpRef.getSupervisors()[i].getWorkers()[j].setName(transferEmp.getName());
                                    transferEmp.setName(Organization.VACANT);
                                    System.out.println("Success! " + vpRef.getSupervisors()[i].getWorkers()[j].getName() + " was transferred.\n");
                                }
                            }
                        }

                        if (!transferEmp.getName().equals(Organization.VACANT)) {
                            System.out.println("Error: That manager has no Worker vacancies");
                        }
                    } else {
                        System.out.println("Error: Vice Presidents may not transfer other Vice Presidents or themselves.\n");
                    }
                    break;
                default:
                    System.out.println("Error: That manager is not authorized to transfer employees.\n");
                    break;
            }
        } else {
            System.out.println("Error: That employee does not exist.\n");
        }

        return;

    }

    private void promote() {
        Employee promoteEmp;
        Supervisor promoteSupervisor;
        Worker promoteWorker;

        if (!promptForNames()) {
            return;
        }

        if (organization.employeeNameExists(employeeInput)) {
            promoteEmp = organization.search(employeeInput);
        } else {
            System.out.println("Error: That employee does not exist.\n");
            return;
        }

        if (organization.employeeNameExists(managerInput))
            managerRef = organization.search(managerInput);
        else {
            System.out.println("Error: That manager does not exist.\n");
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
                                " promote as the President will not become the direct manager of the promoted\n");
                        break;
                    }
                    for (VicePresident vp : presRef.getVPs()) {
                        if (vp.getName().equals(Organization.VACANT)) {
                            if (!vp.contains(promoteSupervisor.getName())) {
                                vp.setName(promoteSupervisor.getName());
                                promoteSupervisor.setName(Organization.VACANT);
                                System.out.println("Success! " + vp.getName() + " was promoted.\n");
                            }
                            else {
                                System.out.println("Error: " + promoteSupervisor.getName() + " is in " +
                                        "the same branch as the Vice President position, and is " +
                                        "therefore unable to be promoted.\n");
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
                                " promote as the Vice President will not become the direct manager of the promoted\n");
                        break;
                    }

                    for (int i = 0; i < vpRef.getSupervisors().length; i++) {
                        if (vpRef.getSupervisors()[i].getName().equals(Organization.VACANT)) {
                            if (!vpRef.getSupervisors()[i].contains(promoteWorker.getName())) {
                                vpRef.getSupervisors()[i].setName(promoteWorker.getName());
                                promoteWorker.setName(Organization.VACANT);
                                System.out.println("Success! " + vpRef.getSupervisors()[i].getName() + " was promoted.\n");
                                return;
                            }
                        }
                    }
                    System.out.println("Error: No supervisor slots available in " + vpRef.getName() + "'s branch.\n");
            }

        } else {
            System.out.println("Error: That manager cannot promote employee.\n");
            return;
        }

        return;

    }

    private boolean promptForEmployeeName() {
        boolean validInput = true;

        System.out.print("Enter employee's name: ");
        employeeInput = scanner.nextLine().strip().replaceAll("[^a-zA-Z]+", "");

        if (employeeInput.equals("")) {
            validInput = false;
            System.out.println("Error: Must enter a valid name.\n");
        }

        return validInput;
    }

    private boolean promptForManagerName() {
        boolean validInput = true;

        System.out.print("Enter manager's name: ");
        managerInput = scanner.nextLine().strip().replaceAll("[^a-zA-Z]+", "");

        if (managerInput.equals("")) {
            validInput = false;
            System.out.println("Error: Must enter a valid name.\n");
        }

        return validInput;
    }

    private boolean promptForNames() {
        boolean validEmpName = true;
        boolean validManName = true;

        System.out.print("Enter employee's name: ");
        /*employeeInput = scanner.nextLine().strip();

        if (employeeInput.equals("")) {
            System.out.println("Error: Must enter a name.\n");
        }*/
        employeeInput = (layoffInput.replaceAll(layoffInput, scanner.nextLine().strip()));
        employeeInput = employeeInput.replaceAll("[^a-zA-Z]+", "");

        if (employeeInput.equals("")) {
            System.out.println("Error: Invalid employee name.\n");
            validEmpName = false;
        } else {
            System.out.print("Enter manager's name: ");
            managerInput = (input.replaceAll(input, scanner.nextLine().strip()));
            managerInput = managerInput.replaceAll("[^a-zA-Z]+", "");

            if (managerInput.equals("")) {
                System.out.println("Error: Invalid manager name.\n");
                validManName = false;
            }
        }

        return (validEmpName && validManName);
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
