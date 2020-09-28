package Company;

import Personnel.*;

// TODO
public class Organization {
    // Variables
    private String name;
    private President president;

    // Methods
    public Organization() {
        //Initialize company names
        name = "Big Name Company";
        president = new President("Jeff Bezos");
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
    }

    public Organization(boolean custom) {
        if (custom) {

        }
        else {
            new Organization();
        }
    }

    public Organization(String filename) {
        loadOrganization(filename);
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
