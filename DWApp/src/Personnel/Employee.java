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

    public void powerToFire(boolean canFire) { this.canFire = canFire; }
    public void powerToHire(boolean canHire) { this.canHire = canHire; }
    public void powerToLayoff(boolean canLayoff) { this.canLayoff = canFire; }
    public void powerToTransfer(boolean canTransfer) { this.canTransfer = canTransfer; }
    public void powerToQuit(boolean canQuit) { this.canQuit = canQuit; }
    public void powerToPromote(boolean canPromote) { this.canPromote = canPromote; }
    public void canBePromoted(boolean isPromotable) { this.isPromotable = isPromotable; }
}
