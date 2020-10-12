package Personnel;

// TODO
public class Supervisor extends Employee implements EmployeePower {
    // Variables
    public static final int MAX_UNDER = 5;

    // Methods
    public Supervisor(String name, Employee manager) {
        setName(name);
        this.setManager(manager);
        setCanFire(true);
        setCanHire(true);
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
