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


    public int getUnderlingCount() { return underlingCount; }
    public void setUnderlingCount(int underlingCount) { this.underlingCount = underlingCount; }

    public boolean isEmpty() {
        if (name.equals(VACANT)) {
            return true;
        }
        return  false;
    }




    public void setCanFire(boolean canFire) { this.canFire = canFire; }
    public void setCanHire(boolean canHire) { this.canHire = canHire; }
    public boolean getCanFire() { return canFire; }
    public boolean getCanHire() { return canHire; }
    public void setCanLayoff(boolean canLayoff) { this.canLayoff = canLayoff; }
    public boolean getCanLayoff() {
        return canLayoff;
    }

    public void setCanTransfer(boolean canTransfer) { this.canTransfer = canTransfer; }
    public boolean getCanTransfer() { return  canTransfer; }
    public boolean getCanQuit() {
        return canQuit;
    }
    public void setCanQuit(boolean canQuit) {
        this.canQuit = canQuit;
    }

    public void setCanPromote(boolean canPromote) { this.canPromote = canPromote; }
    public boolean getCanPromote() {return canPromote;}
    public void setCanBePromoted(boolean isPromotable) { this.isPromotable = isPromotable; }
    public boolean getCanBePromotable() {
        return isPromotable;
    }
}
