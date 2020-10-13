package Personnel;

// TODO
public class VicePresident extends Employee {
    // Variables
    static final int MAX_UNDER = 3;
    static final String VACANT = "vacant";
    private final Supervisor[] supervisors = new Supervisor[MAX_UNDER];

    // Methods
    public VicePresident() {
        underlingCount = 0;
        setName("vacant");
        for (int i = 0; i < supervisors.length; i++) {
            supervisors[i] = new Supervisor();
            supervisors[i].setName(VACANT);
        }
    }

    public Supervisor[] getSupervisors() {
        return supervisors;
    }

    public void print() {
        System.out.println("\tVice President: " + getName());

        for (Supervisor supervisor: supervisors) {
            supervisor.print();
        }
    }
}
