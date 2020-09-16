package Company;

import Personnel.Employee;
import Personnel.President;

// TODO
public class Organization {
    // Variables
    private String name;
    private President president;

    // Methods
    public Organization() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public President getPresident() { return president; }

    public Employee search(String name) {
        return null;
    }

    public void loadOrganization(String filename) {

    }

    public void printOranization() {

    }
}
