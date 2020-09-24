import Personnel.President;
import Personnel.Supervisor;
import Personnel.VicePresident;
import Personnel.Worker;

// TODO
public class Driver {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //Initialize company names
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
        president.setUnderlings(new String[]{vicePresident1.getName(),vicePresident2.getName()});
        vicePresident1.setUnderlings(new String[]{supervisor1.getName(),supervisor2.getName(),supervisor3.getName()});
        vicePresident2.setUnderlings(new String[]{supervisor4.getName(),supervisor5.getName(),supervisor6.getName()});
        supervisor1.setUnderlings(new String[]{worker1.getName(),worker2.getName(),worker3.getName(),worker4.getName(),worker5.getName()});
        supervisor2.setUnderlings(new String[]{worker6.getName(),worker7.getName(),worker8.getName(),worker9.getName(),worker10.getName()});
        supervisor3.setUnderlings(new String[]{worker11.getName(),worker12.getName(),worker13.getName(),worker14.getName(),worker15.getName()});
        supervisor4.setUnderlings(new String[]{worker16.getName(),worker17.getName(),worker18.getName(),worker19.getName(),worker20.getName()});
        supervisor5.setUnderlings(new String[]{worker21.getName(),worker22.getName(),worker23.getName(),worker24.getName(),worker25.getName()});
        supervisor6.setUnderlings(new String[]{worker26.getName(),worker27.getName(),worker28.getName(),worker29.getName(),worker30.getName()});
    }
}
