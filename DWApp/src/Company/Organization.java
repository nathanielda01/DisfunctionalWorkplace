package Company;
import Personnel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// TODO
public class Organization {
    // Variables
    static final int PRES_MAX = 2;
    static final int VP_MAX = 3;
    static final int SUP_MAX = 5;
    public static final String VACANT = "vacant";
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
        switch (manager.getClass().getSimpleName()) {
            case "President":
                President president = (President) manager;
                for(int i = 0; i < president.getVPs().length; i++)
                    if (president.getVPs()[i].getName().equals(VACANT))
                        president.getVPs()[i].setName(empName);
                break;
            case "VicePresident":
                VicePresident vp = (VicePresident) manager;
                for(int i = 0; i < vp.getSupervisors().length; i++)
                    if(vp.getSupervisors()[i].getName().equals(VACANT))
                        vp.getSupervisors()[i].setName(empName);
                break;
            case "Supervisor":
                Supervisor supervisor = (Supervisor) manager;
                for(int i = 0; i < supervisor.getWorkers().length; i++)
                    if(supervisor.getWorkers()[i].getName().equals(VACANT))
                        supervisor.getWorkers()[i].setName(empName);
                break;
        }
        return;

        /*for (int i = 0; i < manager.getUnderlings().length; i++) {
            if (manager.getUnderlings()[i].getName().equals(VACANT)) {
                switch (manager.getClass().getSimpleName()) {
                    case "President":
                        President president = (President) manager;
                        president.getVPs()[i].setName(empName);
                        break;
                    case "VicePresident":
                        VicePresident vp = (VicePresident) manager;
                        vp.getSupervisors()[i].setName(empName);
                        break;
                    case "Supervisor":
                        Supervisor supervisor = (Supervisor) manager;
                        supervisor.getWorkers()[i].setName(empName);
                        break;
                }
                return;
            }
        }*/
    }

    public void fireEmployee(Employee manager, Employee worker) {

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

        /*for (int i = 0; i < manager.getUnderlings().length; i++) {
            if (manager.getUnderlings()[i].getName().equals(worker.getName())) {
                manager.getUnderlings()[i].setName(VACANT);
            }
        }*/
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
        if (manager.getClass().getSimpleName().equals("President")) {
            President specificManager = (President) manager;
        }

        switch (worker.getClass().getSimpleName()) {
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
        //Check if user specified layoff manager is in hierarchy
        if (!worker.getManager().getName().equals(layoffManager.getName()) &&
                !worker.getManager().getManager().getName().equals(layoffManager.getName()) &&
                        !worker.getManager().getManager().getManager().getName().equals(layoffManager.getName())) {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
            return;
        }

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
    }

    public void layoffEmployeeSupervisor(Employee layoffManager, Supervisor worker) {
        //Check if user specified layoff manager is in hierarchy
        if (!worker.getManager().getName().equals(layoffManager.getName()) &&
                !worker.getManager().getManager().getName().equals(layoffManager.getName())) {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
            return;
        }

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
    }

    public void layoffEmployeeVP(Employee layoffManager, VicePresident worker) {
        //Check if user specified layoff manager is in hierarchy
        if (!worker.getManager().getName().equals(layoffManager.getName())) {
            System.out.println("Warning: Layoff manager is not employee's manager or upper management, failed to layoff.\n");
            return;
        }

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
}


