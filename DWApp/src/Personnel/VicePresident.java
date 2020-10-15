package Personnel;

// TODO
public class VicePresident extends Employee {
    // Variables
    public static final int MAX_UNDER = 3;
    static final String VACANT = "vacant";
    private final Supervisor[] supervisors = new Supervisor[MAX_UNDER];

    // Methods
    public VicePresident() {
        setName(VACANT);
        this.position = "Vice President";
        for (int i = 0; i < supervisors.length; i++) {
            supervisors[i] = new Supervisor();
            supervisors[i].setManager(this);
            supervisors[i].setName(VACANT);
        }

        setCanFire(true);
        setCanHire(true);
        setCanTransfer(true);
        setCanPromote(true);
        setCanQuit(true);
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
