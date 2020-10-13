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
}
