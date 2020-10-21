package Company;

import Personnel.*;

import java.util.Scanner;

public class Actions {
    // Variables
    private Organization organization;
    private Employee managerRef;
    private String input = "";
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
            case "Transfer":
                transfer();
                break;
            case "Promote":
                promote();
                break;
            default:
                System.out.println(action + " is not a valid action.");
        }
    }

    private void promote() {
        Employee promoteEmp;
        Supervisor promoteSupervisor;
        Worker promoteWorker;

        System.out.print("Enter employee's name being promoted: ");
        promoteInput = (promoteInput.replaceAll(promoteInput, scanner.nextLine().strip()));
        System.out.println();
        if (organization.employeeNameExists(promoteInput)) {
            promoteEmp = organization.search(promoteInput);
        } else {
            System.out.println("Warning: That employee does not exist, cannot promote!\n");
            return;
        }

        System.out.print("Enter promoting manager: ");
        input = (input.replaceAll(input, scanner.nextLine().strip()));
        System.out.println();

        if (organization.employeeNameExists(input))
            managerRef = organization.search(input);
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
                                break;
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
        System.out.print("Enter employee's name being transferred: ");
        transferInput = (transferInput.replaceAll(transferInput, scanner.nextLine().strip()));
        System.out.println();
        if (organization.employeeNameExists(transferInput)) {
            Employee transferEmp = organization.search(transferInput);

            System.out.print("Enter transferring manager: ");
            input = (input.replaceAll(input, scanner.nextLine().strip()));
            System.out.println("\nVerifying...");

            if (organization.employeeNameExists(input)) {
                System.out.println("Employee found as:");
                managerRef = organization.search(input);
                System.out.println("Name: " + managerRef.getName() + " Position: " + managerRef.getPosition());
            }
            else {
                System.out.println("Warning: Transferring manager does not exist.\n");
                return;
            }

            if (managerRef.getCanTransfer()) {
                System.out.println(managerRef.getName() + " has the power to transfer employees.");
            }
            else {
                System.out.println(managerRef.getName() + " does not have the power to transfer employees.");
                return;
            }

            switch (managerRef.getPosition()) {
                case "President":
                    System.out.println("Searching for vacancies...");
                    while (organization.search(Organization.VACANT) != null) {
                        Employee vacancy = organization.search(Organization.VACANT);
                        System.out.println("Vacancy found.");
                        if (vacancy.getPosition().equals(transferEmp.getPosition())) {
                            System.out.println("Initiating transfer...");
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
}
