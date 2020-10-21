package Personnel;

// TODO
public class Supervisor extends Employee {
    // Variables
    public static final int MAX_UNDER = 5;
    private final Worker[] workers = new Worker[MAX_UNDER];
    static final String VACANT = "vacant";

    // Methods
    public Supervisor() {
        setName(VACANT);
        this.position = "Supervisor";
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
            workers[i].setManager(this);
            workers[i].setName(VACANT);
        }
        setCanHire(true);
        setCanFire(true);
        setCanPromote(false);
        setCanBePromoted(true);
        setCanTransfer(false);
        setCanLayoff(true);
        setCanQuit(true);
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public boolean contains(String name) {
        for (int i = 0; i < workers.length; i++) {
            if (workers[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("\t\tSupervisor: " + getName());

        for (Worker worker : workers) {
            worker.print();
        }
    }
}
