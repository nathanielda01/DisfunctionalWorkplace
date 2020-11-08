package Personnel;

public abstract class Employee {
    // Variables
    protected String name;
    protected Employee manager;
    protected boolean canFire;
    protected boolean canHire;
    protected boolean canLayoff;
    protected boolean canTransfer;
    protected boolean canQuit;
    protected boolean canPromote;
    protected boolean isPromotable;
    protected String position;
    private boolean searchKey = false;
    static final String VACANT = "vacant";

    // Methods
    public Employee() { }

    public String getName() { return name; }
    public void setName(String name) {
        if (name.equals("")) {
            this.name = VACANT;
        }
        name = name.replaceAll("[^a-zA-Z]+", "");
        if (name.matches("^[a-zA-Z'-]+$")) {
            this.name = name;
        }
        else {
            this.name = VACANT;
        }
    }

    public boolean getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(boolean searchKey) {
        this.searchKey = searchKey;
    }

    public Employee getManager() {
        return manager;
    }

    public boolean isEmpty() {
        if (name.equals(VACANT)) {
            return true;
        }
        return  false;
    }


    public boolean getCanFire() { return canFire; }
    public boolean getCanHire() { return canHire; }
    public boolean getCanLayoff() { return canLayoff; }
    public boolean getCanTransfer() { return  canTransfer; }
    public boolean getCanQuit() { return canQuit; }
    public boolean getCanPromote() {return canPromote;}
    public boolean getCanBePromoted() { return isPromotable; }

    public String getPosition() { return position; }
}
