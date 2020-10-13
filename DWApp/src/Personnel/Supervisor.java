package Personnel;

// TODO
public class Supervisor extends Employee {
    // Variables
    static final int MAX_UNDER = 5;
    private final Worker[] workers = new Worker[MAX_UNDER];
    static final String VACANT = "vacant";

    // Methods
    public Supervisor() {
        underlingCount = 0;
        setName(VACANT);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
            workers[i].setName(VACANT);
        }
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void print() {
        System.out.println("\t\tSupervisor: " + getName());

        for (Worker worker : workers) {
            worker.print();
        }
    }
}
