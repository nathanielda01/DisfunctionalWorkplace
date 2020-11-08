package Company;

import Personnel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Organization {
    // Variables
    static final int PRES_MAX = 2;
    static final int VP_MAX = 3;
    static final int SUP_MAX = 5;
    public static final String VACANT = "vacant";
    private Actions actions;
    private String name;
    private President president;

    // Methods
    public Organization() {
        actions = new Actions(this);
        President president = new President();
        setPresident(president);
    }

    public President getPresident() { return president; }

    private void setPresident(President president) {
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
        if (this.search(name) != null) {
            return true;
        }
        return false;
    }

    public void loadOrganization(String filename) {
        Scanner scanner;

        File file = new File(filename);
        try {
            scanner = new Scanner(file);

            while (search(VACANT) != null && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = validateName(line);

                if (!employeeNameExists(line)) {
                    Employee employee = search(VACANT);
                    employee.setName(line);
                }
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
        switch (manager.getPosition()) {
            case "President":
                President president = (President) manager;
                for (int i = 0; i < president.getVPs().length; i++) {
                    if (president.getVPs()[i].getName().equals(VACANT) && !employeeNameExists(empName)) {
                        president.getVPs()[i].setName(empName);
                        return;
                    }
                }
                System.out.println("Warning: No vacancy under " + president.getName() + "\n");
                break;

            case "Vice President":
                VicePresident vp = (VicePresident) manager;
                for (int i = 0; i < vp.getSupervisors().length; i++) {
                    if (vp.getSupervisors()[i].getName().equals(VACANT)) {
                        vp.getSupervisors()[i].setName(empName);
                        return;
                    }
                }
                System.out.println("Warning: No vacancy under " + vp.getName() + "\n");
                break;

            case "Supervisor":
                Supervisor supervisor = (Supervisor) manager;
                for(int i = 0; i < supervisor.getWorkers().length; i++) {
                    if(supervisor.getWorkers()[i].getName().equals(VACANT)) {
                        supervisor.getWorkers()[i].setName(empName);
                        return;
                    }
                }
                System.out.println("Warning: No vacancy under " + supervisor.getName() + "\n");
                break;
        }
        return;

    }

    public void fireEmployee(Employee manager, Employee worker) {
        //Check if user specified manager is in worker hierarchy
        if (!isManager(manager, worker)) {
            System.out.println("Warning: Firing manager is not employee's manager or upper management, failed to fire!\n");
            return;
        }


        switch (worker.getClass().getSimpleName()) {
            case "Worker":
                Supervisor supervisor = (Supervisor) worker.getManager();
                for(int i = 0; i < supervisor.getWorkers().length; i++)
                    if(supervisor.getWorkers()[i].getName().equals(worker.getName()))
                        supervisor.getWorkers()[i].setName(VACANT);
                break;
            case "Supervisor":
                VicePresident vp = (VicePresident) worker.getManager();
                for(int i = 0; i < vp.getSupervisors().length; i++)
                    if(vp.getSupervisors()[i].getName().equals(worker.getName()))
                        vp.getSupervisors()[i].setName(VACANT);
                break;
            case "VicePresident":
                President president = (President) worker.getManager();
                for(int i = 0; i < president.getVPs().length; i++)
                    if (president.getVPs()[i].getName().equals(worker.getName()))
                        president.getVPs()[i].setName(VACANT);
                break;
        }
    }

    public void quitEmployee(Employee manager, Employee worker) {
        switch (manager.getClass().getSimpleName()) {
            case "President":
                President president = (President) manager;
                for(int i = 0; i < president.getVPs().length; i++)
                    if (president.getVPs()[i].getName().equals(worker.getName()))
                        president.getVPs()[i].setName(VACANT);
                break;
            case "VicePresident":
                VicePresident vp = (VicePresident) manager;
                for(int i = 0; i < vp.getSupervisors().length; i++)
                    if(vp.getSupervisors()[i].getName().equals(worker.getName()))
                        vp.getSupervisors()[i].setName(VACANT);
                break;
            case "Supervisor":
                Supervisor supervisor = (Supervisor) manager;
                for(int i = 0; i < supervisor.getWorkers().length; i++)
                    if(supervisor.getWorkers()[i].getName().equals(worker.getName()))
                        supervisor.getWorkers()[i].setName(VACANT);
                break;
        }
        return;
    }

    public void layoffEmployee(Employee manager, Employee worker) {

        switch (worker.getPosition()) {
            case "VicePresident":
                layoffEmployeeVP(manager, (VicePresident) worker);
                break;
            case "Supervisor":
                layoffEmployeeSupervisor(manager, (Supervisor) worker);
                break;
            case "Worker":
                layoffEmployeeWorker(manager, (Worker) worker);
                break;
        }
        return;
    }

    public void layoffEmployeeWorker(Employee layoffManager, Worker worker) {
        //Check if user specified layoff manager is in employee hierarchy
        if (isManager(layoffManager, worker)) {
            Supervisor manager = (Supervisor) worker.getManager();

            for (int i = 0; i < manager.getWorkers().length; i++) {
                if (manager.getWorkers()[i].getName().equals(VACANT)) {
                    manager.getWorkers()[i].setName(worker.getName());
                    worker.setName(VACANT);
                    return;
                }
            }

            //Reaching this point means no immediate opening under supervisor, move to VP group
            VicePresident vp = (VicePresident) manager.getManager();
            for (int i = 0; i < vp.getSupervisors().length; i++) {
                for (int j = 0; j < vp.getSupervisors()[i].getWorkers().length; j++) {
                    if(vp.getSupervisors()[i].getWorkers()[j].getName().equals(VACANT)) {
                        vp.getSupervisors()[i].getWorkers()[j].setName(worker.getName());
                        worker.setName(VACANT);
                        return;
                    }
                }
            }

            //Reaching this point means no opening in VP hierarchy, move to other hierarchy
            for(int i = 0; i < president.getVPs().length; i++) {
                //gets the other vp not checked previously
                if (!president.getVPs()[i].getName().equals(vp.getName())) {
                    for (int j = 0; j < president.getVPs()[i].getSupervisors().length; j++) {
                        for(int k = 0; k < president.getVPs()[i].getSupervisors()[j].getWorkers().length; k++) {
                            if (president.getVPs()[i].getSupervisors()[j].getWorkers()[k].getName().equals(VACANT)) {
                                president.getVPs()[i].getSupervisors()[j].getWorkers()[k].setName(worker.getName());
                                worker.setName(VACANT);
                                return;
                            }
                        }
                    }
                }
            }

            //Reaching this point means no openings at worker level, worker is let go
            worker.setName(VACANT);
        } else {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
        }
    }

    public void layoffEmployeeSupervisor(Employee layoffManager, Supervisor worker) {
        //Check if user specified layoff manager is in hierarchy
        if (isManager(layoffManager, worker)) {
            VicePresident manager = (VicePresident) worker.getManager();

            for (int i = 0; i < manager.getSupervisors().length; i++) {
                if(manager.getSupervisors()[i].getName().equals(VACANT)) {
                    manager.getSupervisors()[i].setName(worker.getName());
                    worker.setName(VACANT);
                    return;
                }
            }

            //Reaching this point means no immediate opening under VP, moving to other VP group
            for (int i = 0; i < president.getVPs().length; i++) {
                //gets the other VP group not checked
                if (!president.getVPs()[i].getName().equals(manager.getName())) {
                    for (int j = 0; j < president.getVPs()[i].getSupervisors().length; j++) {
                        if(president.getVPs()[i].getSupervisors()[j].getName().equals(VACANT)) {
                            president.getVPs()[i].getSupervisors()[j].setName(worker.getName());
                            worker.setName(VACANT);
                            return;
                        }
                    }
                }
            }

            //Reaching this point means no openings at supervisor level, supervisor is let go
            worker.setName(VACANT);
        } else {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
        }

    }

    public void layoffEmployeeVP(Employee layoffManager, VicePresident worker) {
        //Check if user specified layoff manager is in hierarchy
        if (isManager(layoffManager, worker)) {

            for (int i = 0; i < president.getVPs().length; i++) {
                if (president.getVPs()[i].getName().equals(VACANT)) {
                    president.getVPs()[i].setName(worker.getName());
                    worker.setName(VACANT);
                    return;
                }
            }

            //Reaching this point means no VP openings, VP is let go
            worker.setName(VACANT);
        }
        else {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
        }
    }

    public boolean isManager(Employee manager, Employee worker) {
        switch (worker.getClass().getSimpleName()) {
            case "Worker":
                if (worker.getManager().getName().equals(manager.getName()) ||
                        worker.getManager().getManager().getName().equals(manager.getName()) ||
                        worker.getManager().getManager().getManager().getName().equals(manager.getName())) {
                    return true;
                } else
                    return false;
            case "Supervisor":
                if (worker.getManager().getName().equals(manager.getName()) ||
                        worker.getManager().getManager().getName().equals(manager.getName())) {
                    return true;
                } else
                    return false;
            case "VicePresident":
                if (worker.getManager().getName().equals(manager.getName())) {
                    return true;
                } else
                    return false;
        }
        return false;
    }

    public void executeAction(String action) {
        actions.execute(action);
    }

    private String validateName(String name) {
        name = name.replaceAll("[^a-zA-Z]+", "");
        return name;
    }
}


