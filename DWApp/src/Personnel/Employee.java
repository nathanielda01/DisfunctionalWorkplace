package Personnel;

// TODO
public abstract class Employee {
    // Variables
    protected String name;
    protected Employee[] underlings;
    protected int underlingCount;
    protected Employee manager;
    protected boolean canFire;
    protected boolean canHire;
    protected boolean canLayoff;
    protected boolean canTransfer;
    protected boolean canQuit;
    protected boolean canPromote;
    protected boolean isPromotable;
    static final String VACANT = "vacant";

    // Methods
    public Employee() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Employee getManager() {
        return manager;
    }
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee[] getUnderlings() { return underlings; }
    public void setUnderlings(Employee[] underlings) {
        this.underlings = underlings;
        setUnderlingCount(underlings.length);
    }

    public int getUnderlingCount() { return underlingCount; }
    public void setUnderlingCount(int underlingCount) { this.underlingCount = underlingCount; }

    public boolean isEmpty() {
        if (name.equals(VACANT)) {
            return true;
        }
        return  false;
    }

    public boolean hasNoEmployees() {
        for (int i = 0; i < underlings.length; i++) {
            if (!underlings[i].name.equals(VACANT)) {
                return false;
            }
        }
        return true;
    }



    public void setCanFire(boolean canFire) { this.canFire = canFire; }
    public void setCanHire(boolean canHire) { this.canHire = canHire; }
    public boolean getCanFire() { return canFire; }
    public boolean getCanHire() { return canHire; }
    public void powerToLayoff(boolean canLayoff) { this.canLayoff = canFire; }
    public void setCanTransfer(boolean canTransfer) { this.canTransfer = canTransfer; }
    public boolean getCanTransfer() { return  canTransfer; }
    public void powerToQuit(boolean canQuit) { this.canQuit = canQuit; }
    public void setCanPromote(boolean canPromote) { this.canPromote = canPromote; }
    public boolean getCanPromote() {return canPromote;}
    public void canBePromoted(boolean isPromotable) { this.isPromotable = isPromotable; }
}
