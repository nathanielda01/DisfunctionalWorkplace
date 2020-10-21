package Personnel;

// TODO
public class Worker extends Employee {
    // Variables
    static final String VACANT = "vacant";

    // Methods
    public Worker() {
        setName(VACANT);
        this.position = "Worker";
        setCanHire(false);
        setCanFire(false);
        setCanPromote(false);
        setCanBePromoted(true);
        setCanTransfer(false);
        setCanQuit(true);
        setCanLayoff(false);
    }

    public void print() {
        if (!isEmpty()) {
            System.out.println("\t\t\tWorker: " + getName());
        }
    }

    public VicePresident getVicePresident() {
        Supervisor supervisor = (Supervisor)this.getManager();
        return (VicePresident)supervisor.getManager();
    }
}
