package Personnel;

// TODO
public class Worker extends Employee {
    // Variables
    static final String VACANT = "vacant";
    // Methods
    public Worker() {
        setName(VACANT);
    }

    public void print() {
        if (!isEmpty()) {
            System.out.println("\t\t\tWorker: " + getName());
        }
    }
}
