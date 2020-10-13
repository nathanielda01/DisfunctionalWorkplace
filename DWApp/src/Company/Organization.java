package Company;

import Personnel.*;

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

    public boolean exists(String name) {
        if (president.getName().equals(name)) {
            return true;
        }

        for (int i = 0; i < PRES_MAX; i++) {
            if (president.getVPs()[i].getName().equals(name)) {
                return true;
            }
            for (int j = 0; j < VP_MAX; j++) {
                if (president.getVPs()[i].getSupervisors()[j].getName().equals(name)) {
                    return true;
                }
                for (int k = 0; k < SUP_MAX; k++) {
                    if (president.getVPs()[i].getSupervisors()[j].getWorkers()[k].getName().equals(name)) {
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
        president.print();
    }

    public void printWelcome() {
        System.out.println("Dysfunctional Organization Application");
        System.out.println("\nCurrent organization of Wacky Widget company:\n");

    }
}
