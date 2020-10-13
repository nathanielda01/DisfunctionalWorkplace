package Company;

import Personnel.*;

// TODO
public class Organization {
    // Variables
    private String name;
    private President president;

    // Methods
    public Organization() {
        President president = new President("Jeff Bezos");
        VicePresident vicePresident1 = new VicePresident("Joe Rogan", president);
        VicePresident vicePresident2 = new VicePresident("Bill Gates", president);
        Supervisor supervisor1 = new Supervisor("Jon Snow", vicePresident1);
        Supervisor supervisor2 = new Supervisor("Rebecca Black", vicePresident1);
        Supervisor supervisor3 = new Supervisor("Morgan Freeman", vicePresident1);
        Supervisor supervisor4 = new Supervisor("Stuart Little", vicePresident2);
        Supervisor supervisor5 = new Supervisor("Tom Brady", vicePresident2);
        Supervisor supervisor6 = new Supervisor("Michele Obama", vicePresident2);
        Worker worker1 = new Worker("Ray Lewis", supervisor1);
        Worker worker2 = new Worker("Tom Cruise", supervisor1);
        Worker worker3 = new Worker("Cameron Diaz", supervisor1);
        Worker worker4 = new Worker("Jennifer Aniston", supervisor1);
        Worker worker5 = new Worker("Brad Pitt", supervisor1);
        Worker worker6 = new Worker("Will Smith", supervisor2);
        Worker worker7 = new Worker("Harrison Ford", supervisor2);
        Worker worker8 = new Worker("Matt Damon", supervisor2);
        Worker worker9 = new Worker("Natalie Portman", supervisor2);
        Worker worker10 = new Worker("Anne Hathaway", supervisor2);
        Worker worker11 = new Worker("Emma Stone", supervisor3);
        Worker worker12 = new Worker("Emily Blunt", supervisor3);
        Worker worker13 = new Worker("Lebron James", supervisor3);
        Worker worker14 = new Worker("Lionel Messi", supervisor3);
        Worker worker15 = new Worker("Serena Williams", supervisor3);
        Worker worker16 = new Worker("Kevin Durant", supervisor4);
        Worker worker17 = new Worker("Kobe Bryant", supervisor4);
        Worker worker18 = new Worker("Tiger Woods", supervisor4);
        Worker worker19 = new Worker("Justin Bieber", supervisor4);
        Worker worker20 = new Worker("Orlando Bloom", supervisor4);
        Worker worker21 = new Worker("Taylor Swift", supervisor5);
        Worker worker22 = new Worker("Abraham Lincoln", supervisor5);
        Worker worker23 = new Worker("Bill Clinton", supervisor5);
        Worker worker24 = new Worker("Katy Perry", supervisor5);
        Worker worker25 = new Worker("David Beckham", supervisor5);
        Worker worker26 = new Worker("Elton John", supervisor6);
        Worker worker27 = new Worker("Paris Hilton", supervisor6);
        Worker worker28 = new Worker("Selena Gomez", supervisor6);
        Worker worker29 = new Worker("Mariah Carey", supervisor6);
        Worker worker30 = new Worker("Nick Jonas", supervisor6);

        //Setup Hierarchy
        president.setUnderlings(new Employee[]{vicePresident1, vicePresident2});
        vicePresident1.setUnderlings(new Employee[]{supervisor1, supervisor2, supervisor3});
        vicePresident2.setUnderlings(new Employee[]{supervisor4, supervisor5, supervisor6});
        supervisor1.setUnderlings(new Employee[]{worker1, worker2, worker3, worker4, worker5});
        supervisor2.setUnderlings(new Employee[]{worker6, worker7, worker8, worker9, worker10});
        supervisor3.setUnderlings(new Employee[]{worker11,worker12,worker13,worker14,worker15});
        supervisor4.setUnderlings(new Employee[]{worker16,worker17,worker18,worker19,worker20});
        supervisor5.setUnderlings(new Employee[]{worker21,worker22,worker23,worker24,worker25});
        supervisor6.setUnderlings(new Employee[]{worker26,worker27,worker28,worker29,worker30});

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

        for (int i = 0; i < president.getUnderlingCount(); i++) {
            if (president.getUnderlings()[i].getName().equals(name)) {
                return president.getUnderlings()[i];
            }
            for (int j = 0; j < president.getUnderlings()[i].getUnderlingCount(); j++) {
                if (president.getUnderlings()[i].getUnderlings()[j].getName().equals(name)) {
                    return president.getUnderlings()[i].getUnderlings()[j];
                }
                for (int k = 0; k < president.getUnderlings()[i].getUnderlings()[j].getUnderlingCount(); k++) {
                    if (president.getUnderlings()[i].getUnderlings()[j].getUnderlings()[k].getName().equals(name)) {
                        return president.getUnderlings()[i].getUnderlings()[j].getUnderlings()[k];
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

        for (int i = 0; i < president.getUnderlingCount(); i++) {
            if (president.getUnderlings()[i].getName().equals(name)) {
                return true;
            }
            for (int j = 0; j < president.getUnderlings()[i].getUnderlingCount(); j++) {
                if (president.getUnderlings()[i].getUnderlings()[j].getName().equals(name)) {
                    return true;
                }
                for (int k = 0; k < president.getUnderlings()[i].getUnderlings()[j].getUnderlingCount(); k++) {
                    if (president.getUnderlings()[i].getUnderlings()[j].getUnderlings()[k].getName().equals(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void loadOrganization(String filename) {

    }

    public void printOrganization() {
        //Print president
        System.out.println("President: " + president.getName() + "\n");

        //Print organization
        for(int i = 0; i < president.getUnderlingCount(); i++) {
            System.out.println("Vice President: " + president.getUnderlings()[i].getName());
            for (int j = 0; j < president.getUnderlings()[i].getUnderlingCount(); j++) {
                System.out.println("Supervisor: " + president.getUnderlings()[i].getUnderlings()[j].getName());
                for (int k = 0; k < president.getUnderlings()[i].getUnderlings()[j].getUnderlingCount(); k++) {
                    System.out.println("Workers: " + president.getUnderlings()[i].getUnderlings()[j].getUnderlings()[k].getName());
                }
            }
            System.out.println();
        }
    }

    public void printWelcome() {
        System.out.println("Dysfunctional Organization Application");
        System.out.println("\nCurrent organization of Wacky Widget company:\n");
    }

    public void fillVacancy(Employee manager, String empName) {
        for (int i = 0; i < manager.getUnderlingCount(); i++) {
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
}
