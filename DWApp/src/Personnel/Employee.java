package Personnel;

// TODO
public abstract class Employee {
    // Variables
    protected String name;
    protected String[] underlings;
    protected int underlingCount;
    protected Employee manager;
    protected boolean canFire;
    protected boolean canHire;
    protected boolean canLayoff;
    protected boolean canTransfer;
    protected boolean canQuit;
    protected boolean canPromote;
    protected boolean isPromotable;

    // Methods
    public Employee() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String[] getUnderlings() { return underlings; }
    public void setUnderlings(String[] underlings) { this.underlings = underlings; }

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
