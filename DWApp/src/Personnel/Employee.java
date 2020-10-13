package Personnel;

// TODO
public abstract class Employee {
    // Variables
    private String name;
    private Employee[] underlings;
    private int underlingCount;
    private Employee manager;
    private boolean canFire;
    private boolean canHire;
    private boolean canLayoff;
    private boolean canTransfer;
    private boolean canQuit;
    private boolean canPromote;
    private boolean isPromotable;

    // Methods
    public Employee() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Employee[] getUnderlings() { return underlings; }
    public void setUnderlings(Employee[] underlings) {
        this.underlings = underlings;
        setUnderlingCount(underlings.length);
    }

    public int getUnderlingCount() { return underlingCount; }
    public void setUnderlingCount(int underlingCount) { this.underlingCount = underlingCount; }

    public Employee getManager() { return manager; }
    public void setManager(Employee manager) { this.manager = manager; }

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
