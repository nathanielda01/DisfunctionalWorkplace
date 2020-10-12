package Personnel;

// TODO
public class President extends Employee implements EmployeePower {
    // Variables
    public final static int MAX_UNDER = 2;

    // Methods
    public President(String name) {
        setName(name);
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
