package Personnel;

// TODO
public class VicePresident extends Employee implements EmployeePower {
    // Variables
    static final int MAX_UNDER = 3;

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
        isPromotable = false;
    }

    // Methods
    public VicePresident(String name, President president) {
        this.name = name;
        manager = president;
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
