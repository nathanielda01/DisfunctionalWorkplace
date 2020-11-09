package Personnel;

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
            supervisors[i].manager = this;
            supervisors[i].setName(VACANT);
        }

        canFire = true;
        canHire = true;
        canTransfer = true;
        canPromote = true;
        canLayoff = true;
        isPromotable = false;
        canQuit = true;
    }

    public Supervisor[] getSupervisors() {
        return supervisors;
    }

    public boolean empty() {
        for (int i = 0; i < supervisors.length; i++) {
            if (!supervisors[i].getName().equals(VACANT)) {
                return false;
            }
            if (!supervisors[i].empty()) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        if (name.equals(VACANT) && this.empty()) {
            return;
        }
        System.out.println("\tVice President: " + getName());

        for (Supervisor supervisor: supervisors) {
            supervisor.print();
        }
    }

    public boolean contains(String name) {
        for (int i = 0; i < getSupervisors().length; i++) {
            if (getSupervisors()[i].getName().equals(name)) {
                return true;
            }
            for (int j = 0; j < getSupervisors()[i].getWorkers().length; j++) {
                if (getSupervisors()[i].getWorkers()[j].getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
