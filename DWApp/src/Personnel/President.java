package Personnel;

// TODO
public class President extends Employee implements EmployeePower {
    // Variables
    private final static int MAX_UNDER = 2;

    {
        // President Setup
        underlings = new String[MAX_UNDER];
        underlingCount = 0;
        manager = null;
        canFire = true;
        canHire = true;
        canLayoff = true;
        canPromote = true;
        canQuit = false;
        canTransfer = false;
        isPromotable = false;
    }

    // Methods
    public President(String name) {
        setName(name);
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
