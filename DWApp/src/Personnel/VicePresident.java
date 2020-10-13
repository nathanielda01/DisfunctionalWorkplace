package Personnel;

// TODO
public class VicePresident extends Employee implements EmployeePower {
    // Variables
    public static final int MAX_UNDER = 3;

    // Methods
    public VicePresident(String name, Employee manager) {
        setName(name);
        this.setManager(manager);
        setCanFire(true);
        setCanHire(true);
        setCanTransfer(true);
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
