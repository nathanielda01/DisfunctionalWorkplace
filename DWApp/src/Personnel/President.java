package Personnel;

// TODO
public class President extends Employee{
    // Variables
    private final static int MAX_UNDER = 2;
    static final String VACANT = "vacant";
    private final VicePresident[] vicePresidents = new VicePresident[MAX_UNDER];
    public final static int MAX_UNDER = 2;

    // Methods
    public President() {
        setName("vacant");
        underlingCount = 0;
        for (int i = 0; i < vicePresidents.length; i++) {
            vicePresidents[i] = new VicePresident();
            vicePresidents[i].setName(VACANT);
        }
    public President(String name) {
        setName(name);
        setCanFire(true);
        setCanHire(true);
    }

    @Override
    public void fire() {

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

    @Override
    public void transfer() {

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
