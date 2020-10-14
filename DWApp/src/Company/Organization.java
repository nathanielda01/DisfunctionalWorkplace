package Company;

import Personnel.Employee;
import Personnel.President;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// TODO
public class Organization {
    // Variables
    static final int PRES_MAX = 2;
    static final int VP_MAX = 3;
    static final int SUP_MAX = 5;
    static final String VACANT = "vacant";
    private String name;
    private President president;

    // Methods
    public Organization() {
        President president = new President();
        setPresident(president);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public President getPresident() { return president; }

    //Singleton method, can only set president once
    public void setPresident(President president) {
        if (this.president == null) {
            this.president = president;
        }
    }

    public Employee search(String name) {

        if (president.getName().equals(name)) {
            return president;
        }

        for (int i = 0; i < president.getVPs().length; i++) {
            if (president.getVPs()[i].getName().equals(name)) {
                return president.getVPs()[i];
            }
            for (int j = 0; j < president.getVPs()[i].getSupervisors().length; j++) {
                if (president.getVPs()[i].getSupervisors()[j].getName().equals(name)) {
                    return president.getVPs()[i].getSupervisors()[j];
                }
                for (int k = 0; k < president.getVPs()[i].getSupervisors()[j].getWorkers().length; k++) {
                    if (president.getVPs()[i].getSupervisors()[j].getWorkers()[k].getName().equals(name)) {
                        return president.getVPs()[i].getSupervisors()[j].getWorkers()[k];
                    }
                }
            }
        }
        return null;
    }

    public boolean employeeNameExists(String name) {
        if (president.getName().equals(name)) {
            return true;
        }

        for (int i = 0; i < president.getVPs().length; i++) {
            if (president.getVPs()[i].getName().equals(name)) {
                return true;
            }
            for (int j = 0; j < president.getVPs()[i].getSupervisors().length; j++) {
                if (president.getVPs()[i].getSupervisors()[j].getName().equals(name)) {
                    return true;
                }
                for (int k = 0; k < president.getVPs()[i].getSupervisors()[j].getWorkers().length; k++) {
                    if (president.getVPs()[i].getSupervisors()[j].getWorkers()[k].getName().equals(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void loadOrganization(String filename) {
        Scanner scanner;

        File file = new File(filename);
        try {
            scanner = new Scanner(file);

            while (search(VACANT) != null && scanner.hasNextLine()) {
                Employee e = search(VACANT);
                e.setName(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printOrganization() {
        president.print();
    }

    public void printWelcome() {
        System.out.println("Dysfunctional Organization Application");
        System.out.println("\nCurrent organization of Wacky Widget company:\n");

    }


    public void fillVacancy(Employee manager, String empName) {
        for (int i = 0; i < manager.getUnderlings().length; i++) {
            if (manager.getUnderlings()[i] == null) {
                switch (manager.getClass().getSimpleName()) {
                    case "President":
                        manager.getUnderlings()[i] = new VicePresident(empName, manager);
                        break;
                    case "VicePresident":
                        manager.getUnderlings()[i] = new Supervisor(empName, manager);
                        break;
                    case "Supervisor":
                        manager.getUnderlings()[i] = new Worker(empName, manager);
                        break;
                }
                return;
            }
        }
    }

    public void fireEmployee(Employee manager, Employee worker) {
        for (int i = 0; i < manager.getUnderlings().length; i++) {
            if (manager.getUnderlings()[i].getName().equals(worker.getName())) {
                manager.getUnderlings()[i] = null;
            }
        }
    }
}
