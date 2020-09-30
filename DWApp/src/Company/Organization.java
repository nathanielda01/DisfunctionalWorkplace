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
        VicePresident vicePresident1 = new VicePresident("Joe Rogan");
        VicePresident vicePresident2 = new VicePresident("Bill Gates");
        Supervisor supervisor1 = new Supervisor("Jon Snow");
        Supervisor supervisor2 = new Supervisor("Rebecca Black");
        Supervisor supervisor3 = new Supervisor("Morgan Freeman");
        Supervisor supervisor4 = new Supervisor("Stuart Little");
        Supervisor supervisor5 = new Supervisor("Tom Brady");
        Supervisor supervisor6 = new Supervisor("Michele Obama");
        Worker worker1 = new Worker("Ray Lewis");
        Worker worker2 = new Worker("Tom Cruise");
        Worker worker3 = new Worker("Cameron Diaz");
        Worker worker4 = new Worker("Jennifer Aniston");
        Worker worker5 = new Worker("Brad Pitt");
        Worker worker6 = new Worker("Will Smith");
        Worker worker7 = new Worker("Harrison Ford");
        Worker worker8 = new Worker("Matt Damon");
        Worker worker9 = new Worker("Natalie Portman");
        Worker worker10 = new Worker("Anne Hathaway");
        Worker worker11 = new Worker("Emma Stone");
        Worker worker12 = new Worker("Emily Blunt");
        Worker worker13 = new Worker("Lebron James");
        Worker worker14 = new Worker("Lionel Messi");
        Worker worker15 = new Worker("Serena Williams");
        Worker worker16 = new Worker("Kevin Durant");
        Worker worker17 = new Worker("Kobe Bryant");
        Worker worker18 = new Worker("Tiger Woods");
        Worker worker19 = new Worker("Justin Bieber");
        Worker worker20 = new Worker("Orlando Bloom");
        Worker worker21 = new Worker("Taylor Swift");
        Worker worker22 = new Worker("Abraham Lincoln");
        Worker worker23 = new Worker("Bill Clinton");
        Worker worker24 = new Worker("Katy Perry");
        Worker worker25 = new Worker("David Beckham");
        Worker worker26 = new Worker("Elton John");
        Worker worker27 = new Worker("Paris Hilton");
        Worker worker28 = new Worker("Selena Gomez");
        Worker worker29 = new Worker("Mariah Carey");
        Worker worker30 = new Worker("Nick Jonas");

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
        return null;
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
}
