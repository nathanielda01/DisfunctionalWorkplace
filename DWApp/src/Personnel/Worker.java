package Personnel;

// TODO
public class Worker extends Employee implements EmployeePower {
    // Variables

    // Methods
    public Worker(String name, Employee manager) {
        setName(name);
        this.setManager(manager);
        setCanFire(true);
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
