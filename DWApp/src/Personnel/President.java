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
        underlingCount = 0;
        for (int i = 0; i < vicePresidents.length; i++) {
            vicePresidents[i] = new VicePresident();
            vicePresidents[i].setManager(this);
            vicePresidents[i].setName(VACANT);
        }
        setCanFire(true);
        setCanHire(true);
        setCanTransfer(true);
        setCanPromote(true);
    }

    public VicePresident[] getVPs() {
        return vicePresidents;
    }

    public void addUnderling(String name) {
        for (int i = 0; i < vicePresidents.length; i++) {
            if(vicePresidents[i].name.equals(VACANT)) {
                vicePresidents[i].name = name;
                underlingCount++;
            }
        }
    }

    public void deleteUnderling(int index) {
        underlings[index].name = VACANT;
        underlingCount--;
    }

    public void print() {
        System.out.println("President: " + getName());

        for (VicePresident vice: vicePresidents) {
            vice.print();
        }
    }
}
