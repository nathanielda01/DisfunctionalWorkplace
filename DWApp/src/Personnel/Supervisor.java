package Personnel;

// TODO
public class Supervisor extends Employee implements EmployeePower {
    // Variables
    static final int MAX_UNDER = 5;

    {
        // Vice President Setup
        underlings = new String[MAX_UNDER];
        underlingCount = 0;
        canFire = true;
        canHire = true;
        canLayoff = true;
        canPromote = true;
        canQuit = true;
        canTransfer = true;
        isPromotable = true;
    }

    // Methods
    public Supervisor(String name, VicePresident vicePresident) {
        this.name = name;
        manager = vicePresident;
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
