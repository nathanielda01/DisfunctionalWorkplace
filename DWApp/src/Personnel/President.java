package Personnel;

// TODO
public class President extends Employee{
    // Variables
    public final static int MAX_UNDER = 2;
    static final String VACANT = "vacant";
    private VicePresident[] vicePresidents = new VicePresident[MAX_UNDER];

    // Methods
    public President() {
        setName("vacant");
        setManager(null);
        this.position = "President";
        for (int i = 0; i < vicePresidents.length; i++) {
            vicePresidents[i] = new VicePresident();
            vicePresidents[i].setManager(this);
            vicePresidents[i].setName(VACANT);
        }
        setCanFire(true);
        setCanHire(true);
        setCanTransfer(true);
        setCanPromote(true);
        setCanLayoff(true);
        setCanBePromoted(false);
        setCanQuit(false);
    }

    public VicePresident[] getVPs() {
        return vicePresidents;
    }

    public void print() {
        System.out.println("President: " + getName());

        for (VicePresident vice: vicePresidents) {
            vice.print();
        }
    }
}
