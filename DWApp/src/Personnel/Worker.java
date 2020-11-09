package Personnel;

public class Worker extends Employee {
    // Variables
    static final String VACANT = "vacant";

    // Methods
    public Worker() {
        setName(VACANT);
        this.position = "Worker";

        canFire = false;
        canHire = false;
        canTransfer = false;
        canPromote = false;
        canLayoff = false;
        isPromotable = true;
        canQuit = true;
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
