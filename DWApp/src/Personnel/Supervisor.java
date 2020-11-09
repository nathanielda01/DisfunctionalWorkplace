package Personnel;

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
            workers[i].manager = this;
            workers[i].setName(VACANT);
        }

        canFire = true;
        canHire = true;
        canTransfer = false;
        canPromote = false;
        canLayoff = true;
        isPromotable = true;
        canQuit = true;
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

    public boolean empty() {
        for (int i = 0; i < workers.length; i++) {
            if (!workers[i].getName().equals(VACANT)) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        if (name.equals(VACANT) && this.empty()) {
            return;
        }
        System.out.println("\t\tSupervisor: " + getName());

        for (Worker worker : workers) {
            worker.print();
        }
    }
}
