package Personnel;

// TODO
public class Worker extends Employee implements EmployeePower {
    // Variables
    static final int MAX_UNDER = 0;

    {
        // Vice President Setup
        underlings = new String[MAX_UNDER];
        underlingCount = 0;
        canFire = false;
        canHire = false;
        canLayoff = false;
        canPromote = false;
        canQuit = true;
        canTransfer = true;
        isPromotable = true;
    }
    // Methods
    public Worker(String name, Supervisor supervisor) {
        this.name = name;
        manager = supervisor;
    }

    @Override
    public void fire() {

    }

    @Override
    public void hire() {

    }

    @Override
    public void layoff() {

    }

    @Override
    public void transfer() {

    }

    @Override
    public void promote() {

    }
}
