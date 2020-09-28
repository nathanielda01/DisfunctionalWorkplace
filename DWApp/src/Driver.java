import Personnel.President;
import Personnel.Supervisor;
import Personnel.VicePresident;
import Personnel.Worker;

// TODO
public class Driver {

    public static void main(String[] args) {
        System.out.println("Hello World!");



        //Setup Hierarchy
        president.setUnderlings(new String[]{vicePresident1.getName(),vicePresident2.getName()});
        vicePresident1.setUnderlings(new String[]{supervisor1.getName(),supervisor2.getName(),supervisor3.getName()});
        vicePresident2.setUnderlings(new String[]{supervisor4.getName(),supervisor5.getName(),supervisor6.getName()});
        supervisor1.setUnderlings(new String[]{worker1.getName(),worker2.getName(),worker3.getName(),worker4.getName(),worker5.getName()});
        supervisor2.setUnderlings(new String[]{worker6.getName(),worker7.getName(),worker8.getName(),worker9.getName(),worker10.getName()});
        supervisor3.setUnderlings(new String[]{worker11.getName(),worker12.getName(),worker13.getName(),worker14.getName(),worker15.getName()});
        supervisor4.setUnderlings(new String[]{worker16.getName(),worker17.getName(),worker18.getName(),worker19.getName(),worker20.getName()});
        supervisor5.setUnderlings(new String[]{worker21.getName(),worker22.getName(),worker23.getName(),worker24.getName(),worker25.getName()});
        supervisor6.setUnderlings(new String[]{worker26.getName(),worker27.getName(),worker28.getName(),worker29.getName(),worker30.getName()});
    }
}
